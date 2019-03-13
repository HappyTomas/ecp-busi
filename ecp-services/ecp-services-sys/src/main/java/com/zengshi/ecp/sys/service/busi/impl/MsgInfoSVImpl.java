/** 
 * Project Name:ecp-services-sys 
 * File Name:MsgInfoSVImpl.java 
 * Package Name:com.zengshi.ecp.sys.service.busi.impl 
 * Date:2016年3月11日下午5:15:36 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.service.busi.impl;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.general.sys.mc.dto.McParamsDTO;
import com.zengshi.ecp.sys.dao.mapper.busi.MsgInfoMapper;
import com.zengshi.ecp.sys.dao.model.MsgInfo;
import com.zengshi.ecp.sys.dao.model.MsgInfoCriteria;
import com.zengshi.ecp.sys.dubbo.util.BaseMsgConstants;
import com.zengshi.ecp.sys.service.busi.interfaces.IMsgInfoSV;
import com.zengshi.paas.utils.DateUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2016年3月11日下午5:15:36  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
@Service("msgInfoSV")
public class MsgInfoSVImpl implements IMsgInfoSV {

    @Resource(name="seq_msg_info_id")
    private PaasSequence seqMsgInfoId;
    
    @Resource(name="msgInfoMapper")
    private MsgInfoMapper msgInfoMapper;
    
    
    @Override
    public MsgInfo initInsertMsgInfo(McParamsDTO paramsDTO) throws Exception {
        
        MsgInfo info = new MsgInfo();
        /**
         * MSG_INFO_ID     NUMBER(16)                     主键                             
MSG_CODE        VARCHAR2(16)                   消息编码                         
MSG_PARAMS      VARCHAR2(512) Y                消息参数串；JSON串的格式：{"key":"value"} 
SEND_USER_ID    NUMBER(16)    Y                如果没有填写，则默认为：系统管理员（1000） 
REC_USER_ID     NUMBER(16)    Y                需要接收消息的用户               
REC_TIME        DATE          Y                消息接收到的时间                 
MSG_INFO_STATUS VARCHAR2(2)   Y                该条消息的处理状态；00 待处理；10：处理成功；21：未启用； 
MSG_COMMEN      VARCHAR2(512) Y                消息处理备注；用于记录处理失败的原因描述 
CREATE_TIME     DATE          Y                创建时间                         
CREATE_STAFF    NUMBER(16)    Y                创建人                           
UPDATE_TIME     DATE          Y                修改时间                         
UPDATE_STAFF    NUMBER(16)    Y                更新帐号ID 
         */
        info.setMsgInfoId(seqMsgInfoId.nextValue()); //序列，主键；
        info.setMsgCode(paramsDTO.getMsgCode()); // 消息编码
        
        //消息参数，转为json
        info.setMsgParams(JSONObject.toJSONString(paramsDTO.getMsgParams()));
        //发送用户 ；如果发送用户为空，则默认为 1000;
        if(paramsDTO.getFromUserId() == null || paramsDTO.getFromUserId() < 1L){
            info.setSendUserId(BaseMsgConstants.SYS_ADMIN_STAFF_ID);
        } else {
            info.setSendUserId(paramsDTO.getFromUserId());
        }
        //接收用户；
        info.setRecUserId(paramsDTO.getToUserId());
        //时间
        Timestamp now = DateUtil.getSysDate();
        info.setRecTime(now);
        info.setCreateTime(now);
        info.setCreateStaff(info.getSendUserId());
        //处理表示，待处理中；
        info.setMsgInfoStatus(BaseMsgConstants.SYS_MSG_SEND_INFO_STATUS_WAIT);
        
        this.msgInfoMapper.insert(info);
        
        return info;
        
    }


    @Override
    public int updateMsgInfo2DoneById(long msgInfoId) {
        //this.msgInfoMapper.updateByPrimaryKeySelective(record)
        MsgInfo info = new MsgInfo();
        info.setMsgInfoStatus(BaseMsgConstants.SYS_MSG_SEND_INFO_STATUS_DONE);
        
        MsgInfoCriteria criteria = new MsgInfoCriteria();
        criteria.createCriteria()
        .andMsgInfoStatusEqualTo(BaseMsgConstants.SYS_MSG_SEND_INFO_STATUS_WAIT)
        .andMsgInfoIdEqualTo(msgInfoId);
        
        return this.msgInfoMapper.updateByExampleSelective(info, criteria);
    }


    @Override
    public int updateMsgInfo2UnDoById(long msgInfoId) {
        MsgInfo info = new MsgInfo();
        info.setMsgInfoStatus(BaseMsgConstants.SYS_MSG_SEND_INFO_STATUS_UNDO);
        
        MsgInfoCriteria criteria = new MsgInfoCriteria();
        criteria.createCriteria()
        .andMsgInfoStatusEqualTo(BaseMsgConstants.SYS_MSG_SEND_INFO_STATUS_WAIT)
        .andMsgInfoIdEqualTo(msgInfoId);
        
        return this.msgInfoMapper.updateByExampleSelective(info, criteria);
    }


    @Override
    public MsgInfo fetchMsgInfoById(long msgInfoId) {
        
        return this.msgInfoMapper.selectByPrimaryKey(msgInfoId);
    }
    
    
    
    
    
}

