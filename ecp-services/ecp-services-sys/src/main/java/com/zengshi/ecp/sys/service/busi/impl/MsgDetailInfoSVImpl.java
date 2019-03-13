/** 
 * Project Name:ecp-services-sys 
 * File Name:MsgDetailInfoSVImpl.java 
 * Package Name:com.zengshi.ecp.sys.service.busi.impl 
 * Date:2016年3月14日下午8:55:59 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.service.busi.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.sys.dao.mapper.busi.MsgSendDetailMapper;
import com.zengshi.ecp.sys.dao.model.MsgInfo;
import com.zengshi.ecp.sys.dao.model.MsgSend;
import com.zengshi.ecp.sys.dao.model.MsgSendDetail;
import com.zengshi.ecp.sys.dao.model.MsgSendDetailCriteria;
import com.zengshi.ecp.sys.dubbo.util.BaseMsgConstants;
import com.zengshi.ecp.sys.service.busi.interfaces.IMsgDetailInfoSV;
import com.zengshi.ecp.sys.service.busi.interfaces.IMsgInfoSV;
import com.zengshi.paas.utils.DateUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2016年3月14日下午8:55:59  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
@Service("msgDetailInfoSV")
public class MsgDetailInfoSVImpl implements IMsgDetailInfoSV {
    
    @Resource(name="seq_msg_detail_id")
    private PaasSequence seq_msg_detail_id;
    
    @Resource(name="msgInfoSV")
    private IMsgInfoSV msgInfoSV;
    
    @Resource(name="msgSendDetailMapper")
    private MsgSendDetailMapper msgSendDetailMapper;

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.sys.service.busi.interfaces.IMsgDetailInfoSV#saveMsgDetailByInfo(java.util.List, com.zengshi.ecp.sys.dao.model.MsgInfo) 
     */
    @Override
    public List<MsgSendDetail> saveMsgDetailByInfo(List<MsgSend> msgSends, MsgInfo msgInfo) {
        
        List<MsgSendDetail> details = new ArrayList<>();
        if(msgSends == null || msgSends.isEmpty()){
            ///不处理；
            
        } else {
            for(MsgSend msgSend : msgSends){
                details.add(this.saveMsgDetail(msgSend, msgInfo));
            }
        }
        
        //处理之后，更新 msgInfo 的状态为 已处理的状态；
        msgInfoSV.updateMsgInfo2DoneById(msgInfo.getMsgInfoId());
        
        return details;
    }
    
    /**
     * 
     * saveMsgDetail: 记录单条的消息发送内容；<br/> 
     * 
     * @author yugn 
     * @param msgSend
     * @param msgInfo
     * @return 
     * @since JDK 1.6
     */
    private MsgSendDetail saveMsgDetail(MsgSend msgSend, MsgInfo msgInfo){
        MsgSendDetail detail = new MsgSendDetail();
        /**
         * SQL> desc t_msg_send_detail_01;
Name          Type           Nullable Default Comments                                  
------------- -------------- -------- ------- ----------------------------------------- 
MSG_DETAIL_ID NUMBER(16)                                                                
MSG_INFO_ID   NUMBER(16)                                                                
MSG_CODE      VARCHAR2(16)   Y                                                          
REC_USER_ID   NUMBER(16)     Y                需要接收消息的用户                        
SEND_TYPE     VARCHAR2(4)                     发送方式                                  
SEND_TIME     DATE           Y                                                          
REC_CODE      VARCHAR2(256)  Y                表示接收这条信息的号码（可能是用户ID、手机号码、email地址） 
MSG_MEMO      VARCHAR2(1024) Y                                                          
SEND_STATUS   VARCHAR2(2)    Y                发送状态 ： 00 初始状态；10：发送成功；20：发送失败 
SEND_TAG      VARCHAR2(1024) Y                发送的描述信息；如果是发送失败，用于描述发送失败的原因，例如：手机号码为空之类的； 
CREATE_TIME   DATE           Y                                                          
CREATE_STAFF  NUMBER(16)     Y                创建人                                    
UPDATE_TIME   DATE           Y                修改时间                                  
UPDATE_STAFF  NUMBER(16)     Y                更新帐号ID  
         */
        detail.setMsgDetailId(seq_msg_detail_id.nextValue());
        detail.setMsgInfoId(msgInfo.getMsgInfoId());
        detail.setMsgCode(msgInfo.getMsgCode());
        detail.setRecUserId(msgInfo.getRecUserId());
        
        //发送方式：取 msgSend的 sendType
        detail.setSendType(msgSend.getSendType());
        // 状态是初始状态；
        detail.setSendStatus(BaseMsgConstants.SYS_MSG_SEND_DETAIL_STATUS_INIT);
        
        detail.setCreateStaff(msgInfo.getCreateStaff());
        detail.setCreateTime(DateUtil.getSysDate());
        
        this.msgSendDetailMapper.insert(detail);
        return detail;
    }

    @Override
    public int updateMsgDetailSuccessByDetail(MsgSendDetail msgDetailInfo) {
        MsgSendDetail info = new MsgSendDetail();   
        info.setRecCode(msgDetailInfo.getRecCode());  //设置接收编码；
        info.setMsgMemo(msgDetailInfo.getMsgMemo());  //设置消息内容
        info.setSendTime(DateUtil.getSysDate());  //设置发送时间
        info.setSendStatus(BaseMsgConstants.SYS_MSG_SEND_DETAIL_STATUS_SUCCESS);
        
        MsgSendDetailCriteria criteria = new MsgSendDetailCriteria();
        criteria.createCriteria()
        .andMsgDetailIdEqualTo(msgDetailInfo.getMsgDetailId())
        .andSendStatusEqualTo(BaseMsgConstants.SYS_MSG_SEND_DETAIL_STATUS_INIT);
        
        return this.msgSendDetailMapper.updateByExampleSelective(info, criteria);
    }

    @Override
    public int updateMsgDetailErrorByDetail(MsgSendDetail msgDetailInfo) {
        MsgSendDetail info = new MsgSendDetail();   
        info.setRecCode(msgDetailInfo.getRecCode());  //设置接收编码；
        info.setMsgMemo(msgDetailInfo.getMsgMemo()); //设置需要发送的信息；
        info.setSendTag(msgDetailInfo.getSendTag());  //设置 异常描述
        info.setSendStatus(BaseMsgConstants.SYS_MSG_SEND_DETAIL_STATUS_ERROR); //发送状态；
        
        MsgSendDetailCriteria criteria = new MsgSendDetailCriteria();
        criteria.createCriteria()
        .andMsgDetailIdEqualTo(msgDetailInfo.getMsgDetailId())
        .andSendStatusEqualTo(BaseMsgConstants.SYS_MSG_SEND_DETAIL_STATUS_INIT);
        
        return this.msgSendDetailMapper.updateByExampleSelective(info, criteria);
    }
    
    
    

}

