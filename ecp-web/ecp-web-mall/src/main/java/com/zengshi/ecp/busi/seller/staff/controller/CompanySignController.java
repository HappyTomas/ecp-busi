package com.zengshi.ecp.busi.seller.staff.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.seller.staff.vo.CompanySignVO;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dubbo.dto.CompanySignReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanySignResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAuthstrReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAuthstrResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICompanySignRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustCheckRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 企业入驻<br>
 * Date:2016-4-11下午5:53:39  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value="/sign/companysign")
public class CompanySignController extends EcpBaseController{
    
    private static final String MODULE = CompanySignController.class.getName();
    
    @Resource
    private ICompanySignRSV companySignRSV;
    
    @Resource
    private ICustManageRSV custManageRSV;
    
    @Resource
    private ICustCheckRSV custCheckRSV;
    
    /**
     * 
     * judgeCheckWait:(校验企业入驻申请流程状态). <br/> 
     * 
     * @author huangxl5
     * @return 
     * @since JDK 1.6
     */
    private boolean judgeCheckWait(Model model) {
        CustInfoReqDTO custinfo = new CustInfoReqDTO();
        custinfo.setId(custinfo.getStaff().getId());
        CustInfoResDTO cust = custManageRSV.findCustInfoById(custinfo.getStaff().getId());
        if(cust==null){
        	return false;
        }
        if(StaffConstants.custInfo.SHOP_FLAG_YES.equals(cust.getCustShopFlag())){
        	model.addAttribute("custShopFlag", cust.getCustShopFlag());
        	return true;
        }
        custinfo.setCustType(cust.getCustType());
        CompanySignResDTO dto = companySignRSV.find(custinfo);
        if(dto != null && "1".equals(dto.getCheckStatus())) {
            return true;
        }
        if (model.containsAttribute("remind")) {
        	//审核不通过
            if(dto != null && "3".equals(dto.getCheckStatus())) {
            	model.addAttribute("signFail", "1");
            	model.addAttribute("resultMsg", dto.getCheckRemark());
                return true;
            }
        }
        
        CustAuthstrReqDTO authstrReqDTO = new CustAuthstrReqDTO();
        authstrReqDTO.setStaffId(custinfo.getStaff().getId());
        CustAuthstrResDTO authstrResDTO =  custCheckRSV.queryCustAuthstr(authstrReqDTO);
        if(null!=authstrResDTO&&StringUtil.isNotBlank(authstrResDTO.getStaffCode())){
        	model.addAttribute("checkFail", authstrResDTO.getCheckStatus());
        	model.addAttribute("resultMsg",authstrResDTO.getCheckRemark());
        	return true;
        }
        return false;
    }
    
    /**
     * 
     * remind:(跳转到企业入驻须知). <br/> 
     * 
     * @author huangxl5 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/remind")
    public String remind(Model model,@RequestParam(value="reApply",required=false) String reApply){
    	model.addAttribute("remind", "remind");//设置标识为remind的请求，在judgeCheckWait里会用到
    	//如果是审核不通过，重新发起，则直接跳转到申请起始页面
    	if ("1".equals(reApply)) {
    		return "/seller/staff/company/sign-remind";
    	}
        //判断该用户是否已经提交了企业入驻申请,且在等待审核中
        CompanySignReqDTO req = new CompanySignReqDTO();
        if(judgeCheckWait(model)) {
            model.addAttribute("staffcode", req.getStaff().getStaffCode());
            return "/seller/staff/company/sign-done";
        } else {
            return "/seller/staff/company/sign-remind";
        }
    }
    
    /**
     * 
     * accompany:(跳转到公司信息认证页面). <br/> 
     * 
     * @author huangxl5
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/accompany")
    public String accompany(Model model) {
        //判断该用户是否已经提交了企业入驻申请,且在等待审核中
        CompanySignReqDTO reqDTO = new CompanySignReqDTO();
        if(judgeCheckWait(model)) {
            model.addAttribute("staffcode", reqDTO.getStaff().getStaffCode());
            return "/seller/staff/company/sign-done";
        }
        LogUtil.info(MODULE, "====== 企业信息认证  开始 ====== ");
        CustInfoReqDTO custinfo = new CustInfoReqDTO();
        custinfo.setId(custinfo.getStaff().getId());
        CustInfoResDTO cust = custManageRSV.findCustInfoById(custinfo.getStaff().getId());
        custinfo.setCustType(cust.getCustType());
        CompanySignResDTO dto = companySignRSV.find(custinfo);
        CompanySignVO companySignInfo = new CompanySignVO();
        if(dto!=null) {
            ObjectCopyUtil.copyObjValue(dto, companySignInfo, null, false);
        }
        //2.将企业法人照片与工商营业执照图片的key值转换为url
        String legalPersonImageURL = ImageUtil.getImageUrl(companySignInfo.getLegalPersonImage());
        companySignInfo.setLegalPersonImageURL(legalPersonImageURL);
        String licencePathURL = ImageUtil.getImageUrl(companySignInfo.getLicencePath());
        companySignInfo.setLicencePathURL(licencePathURL);
        String taxPathURL = ImageUtil.getStaticDocUrl(companySignInfo.getTaxPath(), FileUtil.getFileType(companySignInfo.getTaxPath()));
        companySignInfo.setTaxPathURL(taxPathURL);
        
        model.addAttribute("companySignInfo", companySignInfo);
        
        LogUtil.info(MODULE, "====== 企业信息认证  结束 ====== ");

        return "/seller/staff/company/sign-accompany";
    }

    /**
     * 
     * acshop:(跳转到输入店铺信息页面). <br/> 
     * 
     * @author huangxl5
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/acshop")
    public String acshop(Model model) {
        CustInfoReqDTO custinfo = new CustInfoReqDTO();
        //判断该用户是否已经提交了企业入驻申请,且在等待审核中
        if(judgeCheckWait(model)) {
            model.addAttribute("staffcode", custinfo.getStaff().getStaffCode());
            return "/seller/staff/company/sign-done";
        }
        custinfo.setId(custinfo.getStaff().getId());
        CustInfoResDTO cust = custManageRSV.findCustInfoById(custinfo.getStaff().getId());
        custinfo.setCustType(cust.getCustType());
        CompanySignResDTO dto = companySignRSV.find(custinfo);
        CompanySignVO companySignInfo = new CompanySignVO();
        if(dto != null) {
            ObjectCopyUtil.copyObjValue(dto, companySignInfo, null, false);
            companySignInfo.setLogoPathURL(ImageUtil.getImageUrl(companySignInfo.getLogoPath()));
        }
        List<BaseParamDTO> shopDistributeList =BaseParamUtil.fetchParamList("STAFF_SHOP_DISTRIBUTION_WAY");
        model.addAttribute("shopDistributeList", shopDistributeList);
        model.addAttribute("companySignInfo", companySignInfo);
        return "/seller/staff/company/sign-acshop";
    }
    
    /**
     * 
     * acdone:(认证已完成). <br/> 
     * 
     * @author huangxl5
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/acdone")
    public String acdone() {
        return "/seller/staff/company/sign-done";
    }
    
    
    /**
     * 
     * saveCompany:(保存企业申请信息). <br/> 
     * 
     * @author huangxl5
     * @param companySignVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/saveCompany")
    @ResponseBody
    public EcpBaseResponseVO saveCompany(@Valid @ModelAttribute CompanySignVO companySignVO) {
        CompanySignReqDTO reqDTO = new CompanySignReqDTO();
        ObjectCopyUtil.copyObjValue(companySignVO, reqDTO, null, false);
        reqDTO.setCheckStatus("0");//设置审核状态为企业草稿状态
        reqDTO.setStaffCode(reqDTO.getStaff().getStaffCode());
        /*调用业务方法，保存公司信息*/
        companySignRSV.save(reqDTO, null);
        EcpBaseResponseVO resultVo = new EcpBaseResponseVO();
        resultVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return resultVo;
    }
    
    /**
     * 
     * saveShop:(企业入驻，保存店铺信息). <br/> 
     * 
     * @author huangxl5
     * @param companySignVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/saveShop")
    @ResponseBody
    public EcpBaseResponseVO saveShop(@Valid @ModelAttribute CompanySignVO companySignVO) {
        CompanySignReqDTO reqDTO = new CompanySignReqDTO();
        ObjectCopyUtil.copyObjValue(companySignVO, reqDTO, null, false);
        reqDTO.setCheckStatus("1");//设置审核状态为待审核状态
        reqDTO.setStaffCode(reqDTO.getStaff().getStaffCode());
        reqDTO.setCompanyCreateStaff(reqDTO.getStaff().getId());
        /*调用业务方法，保存店铺信息*/
        companySignRSV.save(reqDTO, null);
        EcpBaseResponseVO resultVo = new EcpBaseResponseVO();
        resultVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return resultVo;
    }
}

