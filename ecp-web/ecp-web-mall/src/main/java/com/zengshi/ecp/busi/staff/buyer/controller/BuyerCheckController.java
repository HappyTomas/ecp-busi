package com.zengshi.ecp.busi.staff.buyer.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dubbo.dto.CustEmailLogInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.sys.dubbo.dto.McParamsWithOtherTypesDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IMcSyncSendRSV;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.CipherUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.SHA1Util;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 手机、邮箱校验<br>
 * Date:2016-4-21上午10:09:58  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value="/buyer/check")
public class BuyerCheckController extends EcpBaseController {
    
    @Resource
    private ICustInfoRSV custInfoRSV;
    
    @Resource
    private ICustManageRSV custManageRSV;
    
    @Resource
    private IMcSyncSendRSV mcSyncSendRSV;
    
    /**
     * 
     * mail:(跳转到邮箱绑定页面). <br/> 
     * 
     * @author huangxl5
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/mail")
    public String mail(Model model){
        
        return "/staff/buyer/custinformation/mail-bind1";
    }
    
    /**
     * 
     * mailCheckWaiting:(跳转到邮箱等待验证页面). <br/> 
     * 
     * @author huangxl5
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/mailCheckWaiting")
    public String mailCheckWaiting(Model model,@RequestParam("email") String email) throws BusinessException{
        
        /*1、先校验邮箱是否存在，存在则抛出异常*/
        CustInfoReqDTO req = new CustInfoReqDTO();
        req.setEmail(email);
        CustInfoResDTO res = custManageRSV.findCustInfo(req);
        if (res != null && StringUtil.isNotBlank(res.getStaffCode())) {
            model.addAttribute("resultMsg", "该邮箱在系统中已经存在，无法进行绑定操作。");
            model.addAttribute("result", "0");
            //说明用户已经绑定了该邮箱
            if (res.getStaffCode().equals(req.getStaff().getStaffCode())) {
                model.addAttribute("result", "1");
            }
            return "/staff/buyer/custinformation/mail-bind3";
        }
        
        /*2、给用户设一个校验用的key到缓存中*/
        //邮箱校验存入缓存中的key 
        String emailCheckKey = StaffConstants.Msg.EMAIL_CHECK_PRE + req.getStaff().getStaffCode();
        String time = DateUtil.getDateString("yyyy-MM-dd HH:mm:ss");
        //邮箱校验存入缓存中的value
        String emailCheckValue = req.getStaff().getStaffCode() + time;
        String shaValue = SHA1Util.encode(emailCheckValue);
        CacheUtil.addItem(emailCheckKey, shaValue, 60 * 60 * 10);//放入缓存，有效时间为10小时
        
        /*3、保存邮箱校验日志 */
        CustEmailLogInfoReqDTO emailReq = new CustEmailLogInfoReqDTO();
        emailReq.setStaffId(emailReq.getStaff().getId());
        emailReq.setCustEmail(email);
        emailReq.setIsSuccess("2");
        long log_id = custInfoRSV.saveCustEmailLog(emailReq);
        
        /*4、给用户发送一个电子邮件*/
        McParamsWithOtherTypesDTO types = new McParamsWithOtherTypesDTO();
        types.setMsgCode(StaffConstants.Msg.MC_EMAIL_CHECK);
        types.email(email);
        Map<String,String> params = new HashMap<String,String>();
        params.put("staffCode", req.getStaff().getStaffCode());
        
        CmsSiteRespDTO site = CmsCacheUtil.getCmsSiteCache(1L);
        //数据加密
        String data = CipherUtil.encrypt(req.getStaff().getStaffCode()+"&"+shaValue+"&"+email+"&"+log_id);
        String checkUrl = site.getSiteUrl() + "/buyer/check/mailcheck?key=" + data;
        params.put("checkUrl", checkUrl);
        types.setMsgParams(params);
        //此方法异常被吃了，所以不会抛异常，这里要注意
        mcSyncSendRSV.sendMsgBySpecial(types);
        
        return "/staff/buyer/custinformation/mail-bind2";
    }
    
    /**
     * 
     * mailCheck:(邮箱确认操作). <br/> 
     * 
     * @author huangxl5
     * @param model
     * @param key
     * @param value
     * @param email
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @RequestMapping(value="/mailcheck")
    public String mailCheck(Model model,@RequestParam("key") String key) throws BusinessException{
        String resultMsg = "";//返回的结果信息
        String result = "";
        //没有参数则直接返回
        if (StringUtil.isBlank(key)) {
            result = "0";
            return "/staff/buyer/custinformation/mail-bind3";
        }
        
        try {
            String dataStr = CipherUtil.decrypt(key);
            String [] data = dataStr.split("&");//把数据分割：1.staffCode;2.对应value值;3.email;4.logid
            String emailKey = StaffConstants.Msg.EMAIL_CHECK_PRE + data[0];
            Object emailValue = CacheUtil.getItem(emailKey);
            
            /*校验成功*/
            if (emailValue != null && String.valueOf(emailValue).equals(data[1])) {
                /*更新用户的邮箱字段*/
                CustInfoReqDTO custReq = new CustInfoReqDTO();
                custReq.setStaffCode(data[0]);
                CustInfoResDTO custRes = custManageRSV.findCustInfo(custReq);
                if (custRes != null && custRes.getId() != null && custRes.getId() != 0L) {
                    custReq.setId(custRes.getId());
                    custReq.setEmail(data[2]);
                    custManageRSV.updateCustInfo(custReq);
                }
                /*删除缓存中的值*/
                CacheUtil.delItem(emailKey);
                result = "1";
                resultMsg = "恭喜你，邮箱绑定成功！";
            } else {
                result = "0";
                resultMsg = "对不起！邮箱绑定验证失败。请您重新发起邮箱绑定请求。";
            }
            /*写邮箱校验日志*/
            CustEmailLogInfoReqDTO logReq = new CustEmailLogInfoReqDTO();
            logReq.setId(Long.parseLong(data[3]));
            logReq.setIsSuccess(result);
            custInfoRSV.updateCustEmailLog(logReq);
        } catch (Exception e) {
            result = "0";
            resultMsg = "邮箱验证超过有效期或邮箱验证的密钥不正确。";
        }
        
        model.addAttribute("resultMsg", resultMsg);
        model.addAttribute("result", result);
        return "/staff/buyer/custinformation/mail-bind3";
    }
    
    /**
     * 
     * phone:(跳转到手机绑定页面). <br/> 
     * 
     * @author huangxl5
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/phone")
    public String phone(Model model){
        
        return "/staff/buyer/custinformation/phone-bind";
    }
    
    /**
     * 
     * checkCode:(获取手机验证码). <br/> 
     * 
     * @author huangxl5
     * @param model
     * @param phone
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @RequestMapping(value="/checkcode")
    @ResponseBody
    public  EcpBaseResponseVO checkCode(Model model,@RequestParam("phone") String phone,HttpServletRequest request)throws BusinessException{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        
        /*校验手机号码是不是在系统中已经存在*/
        CustInfoReqDTO custInfoReq = new CustInfoReqDTO();
        custInfoReq.setSerialNumber(phone);
        CustInfoResDTO custInfoRes = custManageRSV.findCustInfo(custInfoReq);
        if (custInfoRes != null) {
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            res.setResultMsg("该手机号码已绑定其他用户。");
            return res;
        }
        
        /*获取6位随机数100000~999999*/
        Random random = new Random();
        int result = random.nextInt(899999);
        result += 100000;//为了保证是6位，这里给加上最小的6位数
        
        /*把6位随机数放入缓存，有登录时，取staffId,未登录时，取sessionId，当作key*/
        String phoneKey = "";
        if (custInfoReq.getStaff().getId() != 0L) {
            phoneKey = StaffConstants.Msg.PHONE_CHECK_PRE + custInfoReq.getStaff().getId();
        } else {
            phoneKey = StaffConstants.Msg.PHONE_CHECK_PRE + request.getSession().getId();
        }
        
        /*校验用户当天获取验证码的次数是否超过了平台的限制*/
        Object sendCnt = CacheUtil.getItem(phoneKey + "_CNT");//已经发送的短信次数
        int smsSendCnt = 1;
        if (sendCnt != null) {
            smsSendCnt = Integer.parseInt(String.valueOf(sendCnt));
            //获取平台配置的用户当日最大可获取的手机验证码次数
            String cnt = BaseParamUtil.fetchParamValue("SMS_CHECK_CODE_CNT", "MAX_CNT");
            if (smsSendCnt > Integer.parseInt(cnt)) {
                res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                res.setResultMsg("获取次数超过当天最大限制10次，请明天再试。");
                return res;
            }
        }
        CacheUtil.addItem(phoneKey, phone + result,60*5);//缓存有效期为5分钟
        
        /*发送手机短信给用户*/
        McParamsWithOtherTypesDTO types = new McParamsWithOtherTypesDTO();
        types.setMsgCode(StaffConstants.Msg.MC_PHONE_CHECK);
        types.phoneNo(phone);//手机号码
        Map<String,String> params = new HashMap<String,String>();
        params.put("checkCode", result + "");
        types.setMsgParams(params);
        mcSyncSendRSV.sendMsgBySpecial(types);//此方法异常被吃了，所以不会抛异常，这里要注意
        
        smsSendCnt++;//获取手机短信验证码的次数加1
        CacheUtil.addItem(phoneKey + "_CNT", smsSendCnt, 60 *60 *24);//有效期为一天
        /*返回结果到页面*/
        res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return res;
    }
    
    /**
     * 
     * checkPhone:(手机验证). <br/> 
     * 
     * @author huangxl5
     * @param model
     * @param phone
     * @param checkCode
     * @param request
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @RequestMapping(value="/checkphone")
    @ResponseBody
    public  EcpBaseResponseVO checkPhone(Model model,@RequestParam("phone") String phone,@RequestParam("checkCode") String checkCode,HttpServletRequest request)throws BusinessException{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        
        //手机号码或验证码为空，直接失败
        if (StringUtil.isBlank(phone) || StringUtil.isBlank(checkCode)) {
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            return res;
        }
        
        /*1、取出缓存中的验证码*/
        String cacheCheckCode = ParamsTool.getPhoneCheckCode(request);
        
        /*2、核对验证码，不通过则返回页面*/
        if (cacheCheckCode == null || !String.valueOf(cacheCheckCode).equals(phone + checkCode)) {
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            return res;
        }
        
        /*3、验证码校验通过，则把用户的手机号码替换掉*/
        CustInfoReqDTO custInfoReq = new CustInfoReqDTO();
        custInfoReq.setSerialNumber(phone);
        custInfoReq.setStaffCode(custInfoReq.getStaff().getStaffCode());
        custInfoReq.setId(custInfoReq.getStaff().getId());
        custManageRSV.updatePhoneByStaffId(custInfoReq);
        
        /*4、把缓存里的验证码移除*/
        ParamsTool.removePhoneCheckCode(request);
        
        res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return res;
    }
}

