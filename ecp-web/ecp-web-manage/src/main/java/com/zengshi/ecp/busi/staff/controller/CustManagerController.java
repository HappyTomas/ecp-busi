/** 
 * Project Name:ecp-web-demo 
 * File Name:DemoController.java 
 * Package Name:com.zengshi.ecp.busi.demo.controller 
 * Date:2015-8-5下午2:51:38 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.busi.staff.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.staff.vo.CustInfoListVO;
import com.zengshi.ecp.busi.staff.vo.CustInfoVO;
import com.zengshi.ecp.busi.staff.vo.CustThirdCodeVO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustThirdCodeReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustThirdCodeRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.ResourceMsgUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-demo <br>
 * Description: <br>
 * Date:2015-8-5下午2:51:38 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "/cust")
public class CustManagerController extends EcpBaseController {

    private static String MODULE = CustManagerController.class.getName();

    @Resource
    private ICustManageRSV custManageRSV;
    
    @Resource
    private ICustThirdCodeRSV custThirdCodeRSV;
    

    /**
     * 
     * grid:(用户管理列表). <br/>
     * 
     * @author wangbh
     * @return
     * @since JDK 1.7
     */
    @RequestMapping(value = "/grid/{companyId}")
    public String gridc(Model model,@PathVariable(value="companyId") String companyId) {
        model.addAttribute("companyId", companyId);
        return "/staff/custmanage/custmanage-grid";
    }

    @RequestMapping(value = "/grid")
    public String grid(Model model,@RequestParam(value="companyId",required = false) String companyId) {
        model.addAttribute("companyId", companyId);
        return "/staff/custmanage/custmanage-grid";
    }
    
    
    
    
    @RequestMapping(value = "/more")
    public String more(Model model) {
        return "/staff/custmanage/custmanage-form-more";
    }
    
    
    
    @RequestMapping(value = "/edit")
    public String edit(Model model,@RequestParam("staffId") String staffId) {
        CustInfoResDTO dto = custManageRSV.findCustInfoById(Long.parseLong(staffId));        model.addAttribute("custInfo", dto);
        model.addAttribute("staffId", staffId);
        return "/staff/custmanage/custmanage-edit";
    }
    

    @RequestMapping(value = "/savecust")
    @ResponseBody
    public EcpBaseResponseVO saveCust(@Valid
    CustInfoVO custInfo) throws Exception {

        LogUtil.info(MODULE, "==========" + JSONObject.toJSONString(custInfo));

        CustInfoReqDTO dto = new CustInfoReqDTO();
        dto.setStaffCode(custInfo.getStaffCode());
        dto.setGender(custInfo.getGender());
        dto.setStaffPassword(custInfo.getStaffPasswd());
        dto.setNickname(custInfo.getNickName());
        if(null!=custInfo.getCustBirthday()){
        	dto.setCustBirthday((new java.sql.Timestamp(custInfo.getCustBirthday().getTime())));
        }
        dto.setSerialNumber(custInfo.getSerialNumber());
        dto.setEmail(custInfo.getEmail());
        dto.setCustName(custInfo.getCustName());
        dto.setProvinceCode(custInfo.getProvinceCode());
        dto.setCityCode(custInfo.getCityCode());
        dto.setCountyCode(custInfo.getCountyCode());
        dto.setTelephone(custInfo.getTelephone());
        dto.setDatailedAddress(custInfo.getDatailedAddress());
        if (!StringUtil.isBlank(StringUtil.toString(custInfo.getCompanyId()))) {
            dto.setCompanyId(custInfo.getCompanyId());
            dto.setCustType(StaffConstants.custInfo.CUST_TYPE_C);
        } else {
            dto.setCustType(StaffConstants.custInfo.CUST_TYPE_P);
        }
        if (!StringUtil.isBlank(StringUtil.toString(custInfo.getShopId()))) {
            dto.setShopId(custInfo.getShopId());
            dto.setCustShopFlag(StaffConstants.custInfo.SHOP_FLAG_YES);
        } else {
            dto.setCustShopFlag(StaffConstants.custInfo.SHOP_FLAG_NO);
        }
        dto.setDisturbFlag(custInfo.getDisturbFlag());
        dto.setCustLevelCode(custInfo.getCustLevelCode());
        dto.setCreateStaff(dto.getStaff().getId());
        dto.setUpdateStaff(dto.getStaff().getId());
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        custManageRSV.saveCustInfo(dto);
        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);

        return vo;
    }
    
    @RequestMapping(value = "/updatecust")
    @ResponseBody
    public EcpBaseResponseVO updateCust(@Valid
    CustInfoVO custInfo,@RequestParam("staffId") String staffId) throws Exception {

        LogUtil.info(MODULE, "==========" + JSONObject.toJSONString(custInfo));

        CustInfoReqDTO dto = new CustInfoReqDTO();
        CustInfoResDTO custRes = custManageRSV.findCustInfoById(Long.parseLong(staffId));
        ObjectCopyUtil.copyObjValue(custRes, dto, null, true);
        dto.setNickname(custInfo.getNickName());
        dto.setGender(custInfo.getGender());
        if(null!=custInfo.getCustBirthday()){
        dto.setCustBirthday((new java.sql.Timestamp(custInfo.getCustBirthday().getTime())));
        }
        dto.setSerialNumber(custInfo.getSerialNumber());
        dto.setCustName(custInfo.getCustName());
        dto.setProvinceCode(custInfo.getProvinceCode());
        dto.setCityCode(custInfo.getCityCode());
        dto.setCountyCode(custInfo.getCountyCode());
        dto.setTelephone(custInfo.getTelephone());
        dto.setDatailedAddress(custInfo.getDatailedAddress());
        //如果是企业管理员，则companyId与shopId不能变更
        if (!StaffConstants.custInfo.CUST_TYPE_ADMIN.equals(custInfo.getCustType())) {
        	if (!StringUtil.isBlank(StringUtil.toString(custInfo.getCompanyId()))) {
                dto.setCustType(StaffConstants.custInfo.CUST_TYPE_C);
            } else {
                dto.setCustType(StaffConstants.custInfo.CUST_TYPE_P);
            }
            dto.setCompanyId(custInfo.getCompanyId());
            if (!StringUtil.isBlank(StringUtil.toString(custInfo.getShopId()))) {
                dto.setCustShopFlag(StaffConstants.custInfo.SHOP_FLAG_YES);
            } else {
                dto.setCustShopFlag(StaffConstants.custInfo.SHOP_FLAG_NO);
            }
            dto.setShopId(custInfo.getShopId());
        }
        dto.setDisturbFlag(custInfo.getDisturbFlag());
        dto.setCustLevelCode(custInfo.getCustLevelCode());
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        custManageRSV.updateCustInfoForEmpty(dto);
        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return vo;
    }
    
    
    /**
     * 
     * gridList:(会员列表). <br/> 
     * 
     * @author wangbh
     * @param model
     * @param vo
     * @param custInfoList
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    
    @RequestMapping("/gridlist")
    @ResponseBody
    public Model gridList(Model model, EcpBasePageReqVO vo, @ModelAttribute CustInfoListVO custInfoList) throws Exception {
        CustInfoReqDTO info = vo.toBaseInfo(CustInfoReqDTO.class);
        if (StringUtil.isBlank(custInfoList.getCompanyId())) {
            info.setCompanyId(0L);
        } else {
            info.setCompanyId(Long.parseLong(custInfoList.getCompanyId()));
        }
        info.setStaffCode(custInfoList.getStaffCode());
        info.setSerialNumber(custInfoList.getSerialNumber());
        info.setCustLevelCode(custInfoList.getCustLevelCode());
        PageResponseDTO<CustInfoResDTO> t = custManageRSV.listCustInfo(info);
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
        
       return super.addPageToModel(model, respVO);

    }
    /**
     * 
     * custInvalid:(会员列表：使失效). <br/> 
     * 
     * @author huangxl 
     * @param model
     * @param staffId
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    @RequestMapping("/gridlist/custinvalid")
    @ResponseBody
    public EcpBaseResponseVO custInvalid(Model model,  @RequestParam("staffId")Long staffId) throws Exception {
        EcpBaseResponseVO result = new EcpBaseResponseVO();
        CustInfoReqDTO custInfo = new CustInfoReqDTO();
        custInfo.setId(staffId);
        custInfo.setStatus("3");
        custInfo.setUpdateTime(DateUtil.getSysDate());
        int resCount = custManageRSV.updateCustStatus(custInfo);
        if (resCount > 0) {
            result.setResultMsg(ResourceMsgUtil.getMessage("web.staff.101001", new String[]{}));
        } else {
            result.setResultMsg(ResourceMsgUtil.getMessage("web.staff.101000", new String[]{}));
        }
        return result;
    }
    /**
     * 
     * custInvalid:(会员列表：使生效). <br/> 
     * 
     * @author huangxl 
     * @param model
     * @param staffId
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    @RequestMapping("/gridlist/custvalid")
    @ResponseBody
    public EcpBaseResponseVO custValid(Model model,  @RequestParam("staffId")Long staffId) throws Exception {
        EcpBaseResponseVO result = new EcpBaseResponseVO();
        CustInfoReqDTO custInfo = new CustInfoReqDTO();
        custInfo.setId(staffId);
        custInfo.setStatus("1");
        custInfo.setUpdateTime(DateUtil.getSysDate());
        int resCount = custManageRSV.updateCustStatus(custInfo);
        if (resCount > 0) {
            result.setResultMsg(ResourceMsgUtil.getMessage("web.staff.101001", new String[]{}));
        } else {
            result.setResultMsg(ResourceMsgUtil.getMessage("web.staff.101000", new String[]{}));
        }
        return result;
    }

    /**
     * 
     * custInvalid:(会员列表：锁定). <br/> 
     * 
     * @author huangxl 
     * @param model
     * @param staffId
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    @RequestMapping("/gridlist/custlock")
    @ResponseBody
    public EcpBaseResponseVO custLock(Model model,  @RequestParam("staffId")Long staffId) throws Exception {
        EcpBaseResponseVO result = new EcpBaseResponseVO();
        CustInfoReqDTO custInfo = new CustInfoReqDTO();
        custInfo.setId(staffId);
        custInfo.setStatus("2");
        custInfo.setUpdateTime(DateUtil.getSysDate());
        int resCount = custManageRSV.updateCustStatus(custInfo);
        if (resCount > 0) {
            result.setResultMsg(ResourceMsgUtil.getMessage("web.staff.101001", new String[]{}));
        } else {
            result.setResultMsg(ResourceMsgUtil.getMessage("web.staff.101000", new String[]{}));
        }
        return result;
    }
    
    /**
     * 
     * custInvalid:(会员列表：密码重置). <br/> 
     * 
     * @author huangxl 
     * @param model
     * @param staffId
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    @RequestMapping("/gridlist/pwdreset")
    @ResponseBody
    public EcpBaseResponseVO custPwdReset(Model model,  @RequestParam("staffId")Long staffId) throws Exception {
        EcpBaseResponseVO result = new EcpBaseResponseVO();
        AuthStaffReqDTO req = new AuthStaffReqDTO();
        req.setId(staffId);
        int resCount = custManageRSV.resetPwd(req);
        if (resCount > 0) {
            result.setResultMsg(ResourceMsgUtil.getMessage("web.staff.101001", new String[]{}));
        } else {
            result.setResultMsg(ResourceMsgUtil.getMessage("web.staff.101000", new String[]{}));
        }
        return result;
    }
    
    
}
