/** 
 * Project Name:ecp-web-demo 
 * File Name:DemoController.java 
 * Package Name:com.zengshi.ecp.busi.demo.controller 
 * Date:2015-8-5下午2:51:38 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.sys.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.jfree.util.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.sys.vo.BaseEmailVO;
import com.zengshi.ecp.busi.sys.vo.MsgManageVO;
import com.zengshi.ecp.busi.sys.vo.MsgTemplateVO;
import com.zengshi.ecp.general.sys.mc.dto.McParamsDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.sys.dubbo.dto.BaseEmailReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.BaseEmailResDTO;
import com.zengshi.ecp.sys.dubbo.dto.McParamsWithOtherTypesDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgDefineReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgDefineResDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgMailReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgMailResDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgSendReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgSiteReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgSiteResDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgSmsReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgSmsResDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IMcSyncSendRSV;
import com.zengshi.ecp.sys.dubbo.interfaces.IMsgManageRSV;
import com.zengshi.ecp.sys.dubbo.util.BaseMsgConstants;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.ResourceMsgUtil;


/**
 * Title: ECP <br>
 * Project Name:ecp-web-demo <br>
 * Description: 消息中心<br>
 * Date:2015-8-5下午2:51:38  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.6 
 */
@Controller
@RequestMapping(value="/msg/mgr")
public class MsgMgrController extends EcpBaseController {
    
    private static String MODULE = MsgMgrController.class.getName();
    
    @Resource
    private IMsgManageRSV msgManageRSV;
    
    @Resource
    private IMcSyncSendRSV mcSyncSendRSV;
    
    
    /**
     * 
     * grid:(消息模板列表). <br/> 
     * 
     * @author huangxl5
     * @return 
     * @since JDK 1.6
     */

    @RequestMapping(value="/grid")
    public String grid(){
    	return "/msg/msg-send-grid";
    }
   
    @RequestMapping("/gridlist")
    @ResponseBody
    public Model gridList(Model model, EcpBasePageReqVO vo) throws Exception{
        
        MsgDefineReqDTO req = vo.toBaseInfo(MsgDefineReqDTO.class);
        PageResponseDTO<MsgDefineResDTO> t = msgManageRSV.listMsgDefine(req);
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
        return super.addPageToModel(model, respVO);
    }
    
    @RequestMapping(value="/saveMsgSend")
    @ResponseBody
    public EcpBaseResponseVO saveMsgSend(@ModelAttribute MsgManageVO paramVO){
        //参数设置
        MsgSendReqDTO req = new MsgSendReqDTO();
        req.setMsgCode(paramVO.getMsgCode());
        req.setSendFlag(paramVO.getFlag());
        req.setSendType(paramVO.getSendType());
        req.setCreateStaff(req.getStaff().getId());
        req.setUpdateStaff(req.getStaff().getId());
        EcpBaseResponseVO resultVo = new EcpBaseResponseVO();
        try {
            //调用业务方法，更新数据
            msgManageRSV.updateMsgSend(req);
            resultVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (Exception e) {
            resultVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
        }
        return resultVo;
    }
    
    /**
     * 
     * findTemplate:(跳转到模板编辑页面). <br/> 
     * 
     * @author huangxl5
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping("/findTemplate")
    public String findTemplate(Model model,@RequestParam(value="msgCode") String msgCode) {
        //查询站内短信模板
        MsgSiteReqDTO siteReq = new MsgSiteReqDTO();
        siteReq.setMsgCode(msgCode);
        MsgSiteResDTO siteRes = msgManageRSV.findMsgSite(siteReq);
        //查询手机短信模板
        MsgSmsReqDTO smsReq = new MsgSmsReqDTO();
        smsReq.setMsgCode(msgCode);
        MsgSmsResDTO smsRes = msgManageRSV.findMsgSms(smsReq);
        //查询邮件模板
        MsgMailReqDTO mailReq = new MsgMailReqDTO();
        mailReq.setMsgCode(msgCode);
        MsgMailResDTO mailRes = msgManageRSV.findMsgMail(mailReq);
        
        model.addAttribute("siteRes", siteRes);
        model.addAttribute("smsRes", smsRes);
        model.addAttribute("mailRes", mailRes);
        
        //查询三种发送方式的开关状态
        MsgSendReqDTO sendReq = new MsgSendReqDTO();
        sendReq.setMsgCode(msgCode);
        sendReq.setSendType(BaseMsgConstants.SEND_TYPE_INSITE);
        model.addAttribute("siteFlag", msgManageRSV.findMsgSend(sendReq));
        sendReq.setSendType(BaseMsgConstants.SEND_TYPE_SMS);
        model.addAttribute("smsFlag", msgManageRSV.findMsgSend(sendReq));
        sendReq.setSendType(BaseMsgConstants.SEND_TYPE_EMAIL);
        model.addAttribute("emailFlag", msgManageRSV.findMsgSend(sendReq));
        model.addAttribute("msgCode", msgCode);
        
        return "/msg/edit/msg-template-edit";
    }
    
    @RequestMapping(value="/template/save")
    @ResponseBody
    public EcpBaseResponseVO templateSave(@Valid @ModelAttribute MsgTemplateVO templateVO) throws Exception{
        EcpBaseResponseVO result = new EcpBaseResponseVO();
        LogUtil.info(MODULE, "======== 模板信息保存  开始   =======");
        /*1、更新站内短信模板*/
        MsgSiteReqDTO siteReq = new MsgSiteReqDTO();
        siteReq.setMsgCode(templateVO.getMsgCode());
        siteReq.setMsgSiteTemplate(templateVO.getMsgSiteTemplate());
        msgManageRSV.updateMsgSite(siteReq);
        
        /*2、更新手机短信模板*/
        MsgSmsReqDTO smsReq = new MsgSmsReqDTO();
        smsReq.setMsgCode(templateVO.getMsgCode());
        smsReq.setSmsTemplate(templateVO.getSmsTemplate());
        msgManageRSV.updateMsgSms(smsReq);
        
        /*3、更新邮件模板*/
        MsgMailReqDTO mailReq = new MsgMailReqDTO();
        mailReq.setMsgCode(templateVO.getMsgCode());
        mailReq.setMailTitleTemplate(templateVO.getMailTitleTemplate());
        mailReq.setMailBodyTemplate(templateVO.getMailBodyTemplate());
        msgManageRSV.updateMsgEmail(mailReq);
        
        result.setResultMsg(ResourceMsgUtil.getMessage("web.staff.101001", new String[]{}));
        LogUtil.info(MODULE, "======== 模板信息保存  结束   =======");

        return result;
    }
    /**
     * 
     * mailset:(跳转到邮件服务配置页面). <br/> 
     * 
     * @author huangxl5
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping("/mailset")
    public String mailset(Model model) {
        BaseEmailResDTO emailRes = msgManageRSV.findBaseEmail();
        model.addAttribute("emailRes", emailRes);
        return "/msg/mail-set-edit";
    }
    
    /**
     * 
     * mailsetSave:(保存邮件服务配置). <br/> 
     * 
     * @author huangxl5
     * @param templateVO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/mailset/save")
    @ResponseBody
    public EcpBaseResponseVO mailsetSave(@Valid @ModelAttribute BaseEmailVO emailVO) throws Exception{
        EcpBaseResponseVO result = new EcpBaseResponseVO();
        BaseEmailReqDTO emailReq = new BaseEmailReqDTO();
        ObjectCopyUtil.copyObjValue(emailVO, emailReq, null, false);
        msgManageRSV.saveBaseEmail(emailReq);
        return result;
    }
    
    @RequestMapping(value="/send/test")
    @ResponseBody
    public EcpBaseResponseVO sendTest(@Valid @ModelAttribute BaseEmailVO emailVO) throws Exception{
        EcpBaseResponseVO result = new EcpBaseResponseVO();
        LogUtil.info(MODULE, "======== 邮件发送测试 开始   =======");
        //发送消息：站内消息、手机短信、邮件；三种信息视开关是否开启而定
        McParamsWithOtherTypesDTO types = new McParamsWithOtherTypesDTO();
        types.setMsgCode(StaffConstants.Msg.MC_CARD_APPLY);
        types.email(emailVO.getRecEmail());
        //此方法异常被吃了，所以不会抛异常，这里要注意
        mcSyncSendRSV.sendMsgBySpecial(types);
        LogUtil.info(MODULE, "======== 邮件发送测试  结束   =======");

        return result;
    }
}


