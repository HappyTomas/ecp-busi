/** 
 * Project Name:ecp-services-sys 
 * File Name:McSyncSendRSVImpl.java 
 * Package Name:com.zengshi.ecp.sys.dubbo.impl 
 * Date:2016年3月14日下午8:31:07 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.general.sys.mc.dto.McParamsDTO;
import com.zengshi.ecp.general.sys.mc.dto.McUserInfo;
import com.zengshi.ecp.general.sys.mc.dto.McUserInfoReqDTO;
import com.zengshi.ecp.general.sys.mc.interfaces.IMcUserRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dao.model.MsgInfo;
import com.zengshi.ecp.sys.dao.model.MsgSend;
import com.zengshi.ecp.sys.dao.model.MsgSendDetail;
import com.zengshi.ecp.sys.dubbo.dto.McParamsWithOtherTypesDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IMcSyncSendRSV;
import com.zengshi.ecp.sys.service.busi.interfaces.IMsgDetailInfoSV;
import com.zengshi.ecp.sys.service.busi.interfaces.IMsgInfoSV;
import com.zengshi.ecp.sys.service.busi.interfaces.IMsgSendSV;
import com.zengshi.ecp.sys.service.common.interfaces.IMsgManageSV;
import com.zengshi.paas.utils.LogUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: 同步发送消息服务<br>
 * Date:2016年3月14日下午8:31:07  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class McSyncSendRSVImpl implements IMcSyncSendRSV {
    
    private static final String MODULE = McSyncSendRSVImpl.class.getName();
    
    @Resource(name="msgInfoSV")
    private IMsgInfoSV msgInfoSV;
    
    @Resource(name="msgManageSV")
    private IMsgManageSV msgManageSV;
    
    @Resource(name="msgDetailInfoSV")
    private IMsgDetailInfoSV msgDetailInfoSV;
    
    @Autowired(required=false)
    private IMcUserRSV mcUserRSV;
    
    @Resource(name="msgSendSV")
    private IMsgSendSV msgSendSV;

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.sys.dubbo.interfaces.IMcSyncSendRSV#sendMsg(com.zengshi.ecp.general.sys.mc.dto.McParamsDTO) 
     */
    @Override
    public void sendMsg(McParamsDTO msgParams) throws BusinessException {
        
        //数据验证，toUserId必须验证；
        validMsgParams(msgParams,true);
        
        //获取用户信息；该部分同时也带有用户校验的功能；
        McUserInfo userInfo = fetchUserInfo(msgParams.getToUserId()); 

        this.sendMsgGeneral(msgParams, userInfo);
    }
    
    /**
     * 
     * sendMsgGeneral:通用发送消息； <br/> 
     * 
     * @author yugn 
     * @param msgParams  发送消息信息；
     * @param userInfo   用户资料
     * @throws BusinessException 
     * @since JDK 1.6
     */
    private void sendMsgGeneral(McParamsDTO msgParams, McUserInfo userInfo) throws BusinessException{
        //初始化待发送消息信息
        MsgInfo msgInfo = initMsgInfo(msgParams);
                
        ///根据消息编码获取启用的消息发送方式；
        List<MsgSend> msgSends = null;
        try{
            msgSends = msgManageSV.fetchActivedMsgSendByMsgCode(msgParams.getMsgCode());
        } catch(Exception err){
            //日志记录；获取失败，不抛出异常；对外不认为失败；
            //直接结束，需要后端人员检查；
            LogUtil.error(MODULE, "根据消息编码:"+msgInfo.getMsgCode()+" 获取启用消息失败；"+err.getMessage(), err);
            return ;
        }
        
        ///具体发送消息生成
        List<MsgSendDetail> details =  null;
        ///该消息未找到启用的消息发送方式；
        if(msgSends == null || msgSends.isEmpty()){
            //日志记录；
            LogUtil.error(MODULE, "该消息编码未启用；"+msgInfo.getMsgInfoId());
            msgInfoSV.updateMsgInfo2UnDoById(msgInfo.getMsgInfoId());
            return;
        } else {
            //List<MsgSend>  msgSends 循环发送，并写入具体的发送消息表；并更新状态为已发送；
            details = this.msgDetailInfoSV.saveMsgDetailByInfo(msgSends, msgInfo);
        }
        
        List<String> errSendTypes = new ArrayList<String>();
        ///后续分别调用具体的发送方法；分线程调用即可；
        if(details == null || details.isEmpty()){
            //结束；
            return ;
        } else {
            for(MsgSendDetail detail: details){
                try{
                    this.msgSendSV.sendMsg(detail, msgParams.getMsgParams(), userInfo);
                    
                } catch(Exception err){
                    LogUtil.error(MODULE, "发送消息"+detail+"；失败；描述："+err.getMessage(),err);
                    errSendTypes.add(detail.getSendType());
                    ///此处异常要吃掉，留待后续处理；避免影响后面的异常处理；
                }
            }
        }
    }
    
    /**
     * 
     * validMsgParams: 消息发送入参校验 <br/> 
     * 
     * @author yugn 
     * @param msgParams
     * @throws BusinessException 
     * @since JDK 1.6
     */
    private void validMsgParams(McParamsDTO msgParams,boolean validUser) throws BusinessException{
        if(msgParams == null){
            throw new BusinessException("sys.msg.send.param.null");
        }
        if(StringUtils.isEmpty(msgParams.getMsgCode())){
            throw new BusinessException("sys.msg.send.param.msgcode.null");
        }
        if(validUser && msgParams.getToUserId() < 1){
            throw new BusinessException("sys.msg.send.param.touser.invalid",new String[] {msgParams.getToUserId()+""});
        }
    }
    
    
    
    /**
     * 
     * initMsgInfo: 初始化写入消息待发送表<br/> 
     * 
     * @author yugn 
     * @param msgParams
     * @return 
     * @since JDK 1.6
     */
    private MsgInfo initMsgInfo( McParamsDTO msgParams){
      //写入待发送消息表； //调用 msgInfoSV.init
        MsgInfo msgInfo = null;
        try{
            msgInfo = msgInfoSV.initInsertMsgInfo(msgParams);
        } catch(Exception err){
            LogUtil.error(MODULE, "写入待发送消息表异常，异常描述："+err.getMessage(), err);
            throw new BusinessException("sys.msg.send.init.error");
        }
        return msgInfo;
    }
    
    /**
     * 
     * fetchUserInfo: 根据用户ID，获取用户信息 <br/> 
     * 
     * @author yugn 
     * @param userId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    private McUserInfo fetchUserInfo(Long userId) throws BusinessException{
        
        McUserInfo userInfo = new McUserInfo();
        
        ///如果入参的用户Id 为空，或者  < 1 ,表示该用户ID无效，不需要后续的获取操作了；
        if(userId == null || userId < 1L){ 
            userInfo.setUserId(-1);
            return userInfo;
        } else {
            userInfo.setUserId(userId);
        }
        
        // 根据接收人信息，获取用户信息；
        McUserInfoReqDTO req = new McUserInfoReqDTO();
        req.setUserId(userId);
       
        ////如果有注入 mcUserRSV ,则获取；否则输出描述，没有对应的获取用户方法，则只处理站内消息；
        if(mcUserRSV != null){
            try{
                userInfo = mcUserRSV.fetchUserInfoByUserId(req);
                if(userInfo == null){
                    throw new BusinessException("sys.msg.send.param.touser.nouser",new String[]{req.getUserId()+""});
                } else {
                    return userInfo;
                }
            } catch(BusinessException err){
                LogUtil.error(MODULE, "根据用户ID："+req.getUserId()+"获取用户资料异常："+err.getMessage(), err);
                throw err;
            }
            
        } else {
            LogUtil.info(MODULE, "获取用户信息的服务没有注入，所以不获取用户信息，只使用用户ID，则只启动站内短信处理");
            return userInfo;
        }
    }

    @Override
    public void sendMsgBySpecial(McParamsWithOtherTypesDTO msgParams) throws BusinessException {
        
        //数据验证，放开对 toUserId的校验；
        validMsgParams(msgParams,false);
        
        //获取用户信息；该部分同时也带有用户校验的功能；
        McUserInfo userInfo = fetchUserInfo(msgParams.getToUserId()); 
        
        ///设置 额外的手机号码信息（如果有配置额外的手机号码，则增加号码信息）
        if(StringUtils.isEmpty(msgParams.phoneNo())){
            
        } else {
            userInfo.setPhoneNo(msgParams.phoneNo());
        }
        
        ///设置 额外的email信息（如果有配置额外的email，则增加email信息）
        if(StringUtils.isEmpty(msgParams.email())){
            
        } else {
            userInfo.setEmail(msgParams.email());
        }
        
        //消息发送；
        this.sendMsgGeneral(msgParams, userInfo);
    }
    
    

}

