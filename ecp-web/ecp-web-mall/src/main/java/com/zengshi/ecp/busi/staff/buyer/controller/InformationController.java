/** 
 * Project Name:ecp-web-demo 
 * File Name:DemoController.java 
 * Package Name:com.zengshi.ecp.busi.demo.controller 
 * Date:2015-8-5下午2:51:38 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.staff.buyer.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.common.vo.RegisterVO;
import com.zengshi.ecp.busi.staff.buyer.vo.AuthStaffPwdVO;
import com.zengshi.ecp.busi.staff.buyer.vo.CompanyInfoVO;
import com.zengshi.ecp.busi.staff.buyer.vo.CustInfoVO;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanyInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanyInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAuthstrReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAuthstrResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustGrowInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustGrowInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustLevelInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAuthStaffRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICompanyManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustCheckRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustLevelInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.paas.captcha.impl.CaptchaServlet;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.ResourceMsgUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;


/**
 * Title: ECP <br>
 * Project Name:ecp-web-demo <br>
 * Description: <br>
 * Date:2015-8-5下午2:51:38  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author chenyz3
 * @version  
 * @since JDK 1.6 
 */
@Controller
@RequestMapping(value="/information")
public class InformationController extends EcpBaseController {
    
    private static String MODULE = InformationController.class.getName();
    
    @Resource
    private ICustLevelInfoRSV custLevelInfoRSV;
    
    @Resource
    private ICustInfoRSV custInfoRSV;
    
    @Resource
    private IAuthStaffRSV authStaffRSV;
    
    @Resource
    private ICustManageRSV custManageRSV;
    
    @Resource
    private ICompanyManageRSV companyManageRSV;
    
    @Resource
    private ICustCheckRSV custCheckRSV;
    
    @RequestMapping(value="/index")
    public String init(Model model){
        CustInfoReqDTO dto = new CustInfoReqDTO();
        dto.setId(dto.getStaff().getId());
        CustInfoResDTO custres =  custManageRSV.findCustInfoById(dto.getStaff().getId());
        dto.setCustLevelCode(custres.getCustLevelCode());
        dto.setCustGrowValue(custres.getCustGrowValue());
        CustLevelInfoResDTO dto2=   custLevelInfoRSV.queryValueGap(dto);
        CustAuthstrReqDTO dto3 = new CustAuthstrReqDTO();
        dto3.setStaffId(custres.getId());
        CustAuthstrResDTO custresDTO = custCheckRSV.queryCustAuthstr(dto3);
        dto.setId(dto.getStaff().getId());
        model.addAttribute("custInfo", custres);
        model.addAttribute("gaplevel", dto2);
        model.addAttribute("growinfo", getCustGrowInfo(custres.getId()));
        model.addAttribute("custAuthstr", custresDTO);
        //@author：chenyz3 作用：企业资质信息
        if(StaffConstants.custInfo.CUST_TYPE_P.equals(custres.getCustType())){
            if(custresDTO != null){
            	CompanyInfoResDTO res = new CompanyInfoResDTO();
            	ObjectCopyUtil.copyObjValue(custresDTO, res, null, false);
            	model.addAttribute("companySignInfo", res);//审核未通过，把未通过的企业信息带出来，继续修改后提交
            }
        }else{
            model.addAttribute("companySignInfo", getComPanyInfo(custres.getCompanyId()));
        }
        
        return "/staff/buyer/custinformation/member-myinformation";
    }
    
    public List<CustGrowInfoResDTO> getCustGrowInfo(Long staffId){
        CustGrowInfoReqDTO dto = new CustGrowInfoReqDTO();
        dto.setStaffId(staffId);
        dto.setPageNo(1);
        dto.setPageSize(20);
        PageResponseDTO<CustGrowInfoResDTO> page = custLevelInfoRSV.queryCustGrowInfo(dto);
        return page.getResult();
    }
    
    //获取审核通过的企业资质
    public CompanyInfoResDTO getComPanyInfo(Long companyId){
        CompanyInfoResDTO res = companyManageRSV.findCompanyInfoById(companyId);
        return res;
    }
    
    
    @RequestMapping(value="/savecompany")
    @ResponseBody
    public HashMap<String,Object> saveCompany(Model model,HttpServletRequest request,@Valid CompanyInfoVO companyInfoVO,@RequestParam(value="captchaCode",required = false) String captchaCode){
        boolean flag = CaptchaServlet.verifyCaptcha(request, captchaCode);
        if(!flag){
            throw new BusinessException("验证码错误");
        }
        HashMap<String,Object> map = new HashMap<String, Object>();
        CustAuthstrReqDTO dto = new CustAuthstrReqDTO();
        ObjectCopyUtil.copyObjValue(companyInfoVO, dto, null, false);
        dto.setStaffId(new Long(119));
        dto.setStaffCode("hxll5");
        dto.setStatus(StaffConstants.companyInfo.COMPANY_STATUS_VALID);
        try {
            custInfoRSV.saveCustAuthstr(dto);
            map.put("success", 1);
        } catch (Exception e) {
            map.put("success", 2);
        }
      
        return map;
        
    }
    
    
    /**
     * 
     * updateCompany:(更新保存企业资质). <br/> 
     * @author chenyz3 
     * @param model
     * @param request
     * @param companyInfoVO
     * @param captchaCode
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/updatecompany")
    @ResponseBody
    public EcpBaseResponseVO updateCompany(Model model,HttpServletRequest request,@Valid CompanyInfoVO companyInfoVO,@RequestParam(value="captchaCode",required = false) String captchaCode){
        boolean flag = CaptchaServlet.verifyCaptcha(request, captchaCode);
        if(!flag){
            throw new BusinessException("验证码错误");
        }
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        try {
            CompanyInfoReqDTO reqDTO = new CompanyInfoReqDTO();
            ObjectCopyUtil.copyObjValue(companyInfoVO, reqDTO, null, false);
            reqDTO.setId(companyInfoVO.getCompanyId());
            companyManageRSV.updateCompanyInfo(reqDTO);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            res.setResultMsg("操作成功");
        } catch (Exception e) {
            res.setResultFlag("fail");
            res.setResultMsg("操作失败" + e.getMessage());
        }
       
        return res;
    }
    
    
    /**
     * 
     * regist:(企业资质提交申请). <br/> 
     * @author chenyz3 
     * @param request
     * @param registerVO
     * @param companyInfoVO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/registcomany")
    @ResponseBody
    public EcpBaseResponseVO regist(HttpServletRequest request,RegisterVO registerVO,CompanyInfoVO companyInfoVO) throws Exception{
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        String CaptchaCode =  CaptchaServlet.getCaptchaCode(request);
        if(null==CaptchaCode||!CaptchaCode.equals(registerVO.getCaptchaCode())){
            vo.setResultFlag("fail");
            vo.setResultMsg("验证码输入错误，请重新输入!");
            return vo;
        }
        try {
            CustAuthstrReqDTO reqDTO = new CustAuthstrReqDTO();
            ObjectCopyUtil.copyObjValue(companyInfoVO, reqDTO, null, false);
            reqDTO.setStaffCode(reqDTO.getStaff().getStaffCode());
            reqDTO.setStaffId(reqDTO.getStaff().getId());
            CustAuthstrResDTO custresDTO = custCheckRSV.queryCustAuthstr(reqDTO);//如果申请表中已经存在记录，则更新它的审核状态为待审核。
            if(null!=custresDTO && null!=custresDTO.getStaffId()){
            	reqDTO.setId(custresDTO.getId());
            	reqDTO.setCheckStatus("1");//表示待审核
            	custCheckRSV.updateCustAuthstr(reqDTO);
            }else{
            	 custCheckRSV.saveCustAuthstr(reqDTO);
            }
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            vo.setResultMsg("申请成功");
        } catch (Exception e) {
            vo.setResultFlag("fail");
            vo.setResultMsg("提交申请失败!");
        }
        return vo;
    }
    
    
    @RequestMapping(value="/savecustinfo")
    @ResponseBody
    public  EcpBaseResponseVO saveCustInfo(Model model,@Valid CustInfoVO custInfoVO)throws BusinessException{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        CustInfoReqDTO req = new CustInfoReqDTO();
        ObjectCopyUtil.copyObjValue(custInfoVO, req, null, false);
        if(StringUtil.isBlank(req.getDisturbFlag())){
            req.setDisturbFlag("0");
        }
        req.setId(Long.parseLong(custInfoVO.getStaffId()));
        custManageRSV.updateCustInfo(req);
        res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return res;
    }
    
    @RequestMapping(value = "/icon",method = RequestMethod.POST)
    @ResponseBody
    public EcpBaseResponseVO licenceImg(@RequestParam(value = "custPic") String custPic) throws Exception {
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        try {
            CustInfoReqDTO dto = new CustInfoReqDTO();
            dto.setId(dto.getStaff().getId());
            dto.setCustPic(custPic);
            custInfoRSV.saveCustPic(dto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("上传失败!");
        }
        res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return res;
            
    }
    /**
     * 
     * pwd:(跳转到密码修改页面). <br/> 
     * 
     * @author huangxl 
     * @param model
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/pwd")
    public String pwd(Model model) throws Exception{
        BaseInfo baseInfo = new BaseInfo();
        model.addAttribute("staffId",baseInfo.getStaff().getId());
        return "/staff/buyer/custinformation/cust-pwd-update";
    }
    /**
     * 
     * pwdUpdate:(保存修改后的密码). <br/> 
     * 
     * @author huangxl 
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/pwdupdate")
    @ResponseBody
    public EcpBaseResponseVO pwdUpdate(@Valid AuthStaffPwdVO vo) throws Exception{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        AuthStaffReqDTO req = new AuthStaffReqDTO();
        req.setId(vo.getStaffId());//id
        req.setStaffPasswd(vo.getStaffPasswd());//新密码
        req.setStaffPwdOld(vo.getStaffPasswdOld());//旧密码
        authStaffRSV.updatePwdById(req);
        res.setResultMsg("密码修改成功。");
        return res;
    }
}


