/** 
 * Project Name:ecp-services-sys 
 * File Name:MsgSendOperateByInsite.java 
 * Package Name:com.zengshi.ecp.sys.service.busi.impl 
 * Date:2016年3月15日上午11:00:47 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.service.busi.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.general.sys.mc.dto.McUserInfo;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dao.model.MsgInsite;
import com.zengshi.ecp.sys.dao.model.MsgSendDetail;
import com.zengshi.ecp.sys.dubbo.dto.MsgSiteReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgSiteResDTO;
import com.zengshi.ecp.sys.dubbo.util.EcpSysCodeConstants;
import com.zengshi.ecp.sys.dubbo.util.SysStringTemplateUtil;
import com.zengshi.ecp.sys.service.busi.interfaces.IMsgInsiteSV;
import com.zengshi.ecp.sys.service.busi.interfaces.IMsgSendOperateSV;
import com.zengshi.ecp.sys.service.common.interfaces.IMsgManageSV;
import com.zengshi.paas.utils.LogUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: 站内消息发送<br>
 * Date:2016年3月15日上午11:00:47  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
@Service("msgSendOperateByInsite")
public class MsgSendOperateByInsiteSVImpl implements IMsgSendOperateSV {
    
    private final static String MODULE = MsgSendOperateByInsiteSVImpl.class.getName();
    
    @Resource(name="msgInsiteSV")
    private IMsgInsiteSV msgInsiteSV;
    
    @Resource(name="msgManageSV")
    private IMsgManageSV msgManageSV;

    /** 
     * 站内短信发送；
     * @see com.zengshi.ecp.sys.service.busi.interfaces.IMsgSendOperateSV#sendMsgOperate(com.zengshi.ecp.sys.dao.model.MsgSendDetail, com.zengshi.ecp.sys.dao.model.MsgInfo, com.zengshi.ecp.general.sys.mc.dto.McUserInfo) 
     */
    @Override
    public void sendMsgOperate(MsgSendDetail msgDetail, Map<String,String> attrMap, McUserInfo userInfo) throws BusinessException {
        
        MsgSiteResDTO msgSite = this.fetchMsgTemplate(msgDetail.getMsgCode());
        if(msgSite == null || StringUtils.isEmpty(msgSite.getMsgSiteTemplate())){
            LogUtil.error(MODULE, "消息编码："+msgDetail.getMsgCode()+"；获取的站内消息模板为空；");
            ///异常；跳出；
            throw new BusinessException(EcpSysCodeConstants.SYS_MSG_SEND_INSITE_TEMPLATE_NULL,new String[]{msgDetail.getMsgCode()});
        }
        ///生成发送消息；
        String msgContext = SysStringTemplateUtil.genString(msgSite.getMsgSiteTemplate(), attrMap);
        //回填msgDetail
        msgDetail.setMsgMemo(msgContext);
        //补充判断，用于处理一些 直接根据其它消息接收方式发送的 方法；这个时候，接收的用户ID 是空的；
        if( msgDetail.getRecUserId() == null || msgDetail.getRecUserId() < 1L ){
            LogUtil.error(MODULE, "消息编码："+msgDetail.getMsgCode()+"；并没有接收站内短信的用户ID");
            msgDetail.setSendTag("发送失败，该消息并没有接收的用户信息");
            throw new BusinessException(EcpSysCodeConstants.SYS_MSG_SEND_INSITE_USERID_NULL);
        } else {
            msgDetail.setRecCode(msgDetail.getRecUserId()+"");
        }
        
        
        /**
         * Name          Type           Nullable Default Comments     
------------- -------------- -------- ------- ------------ 
STAFF_ID      NUMBER(16)                      站内消息的接收用户 
MSG_INFO_ID   NUMBER(16)                      对接消息表的消息ID 
MSG_CODE      VARCHAR2(16)                    消息编码     
MSG_CONTEXT   VARCHAR2(1024) Y                消息的具体内容 
READ_FLAG     VARCHAR2(2)    Y                00：未读；10：已读 
REC_TIME      DATE           Y                用户接收到该消息的时间 
FROM_STAFF_ID NUMBER(16)     Y                发送该站内消息的账户ID 
CREATE_TIME   DATE           Y                创建时间     
CREATE_STAFF  NUMBER(16)     Y                创建人       
UPDATE_TIME   DATE           Y                修改时间     
UPDATE_STAFF  NUMBER(16)     Y                更新帐号ID 
         */
        
        
        MsgInsite insite = new MsgInsite();
        insite.setMsgCode(msgDetail.getMsgCode());
        insite.setStaffId(msgDetail.getRecUserId());
        insite.setMsgInfoId(msgDetail.getMsgInfoId());
        insite.setMsgCode(msgDetail.getMsgCode());
        insite.setMsgContext(msgContext);
        
        //具体站内消息发出；
        msgInsiteSV.insertMsgInsite(insite);
        
    }
    
    
    /**
     * 
     * fetchMsgTemplate: 根据消息编码获取站内消息模板 <br/> 
     * 
     * @author yugn 
     * @param msgCode
     * @return 
     * @since JDK 1.6
     */
    private MsgSiteResDTO fetchMsgTemplate(String msgCode){
        
        MsgSiteReqDTO reqDTO = new MsgSiteReqDTO();
        reqDTO.setMsgCode(msgCode);
        
        return msgManageSV.findMsgSite(reqDTO);
    }
    
    
    

}

