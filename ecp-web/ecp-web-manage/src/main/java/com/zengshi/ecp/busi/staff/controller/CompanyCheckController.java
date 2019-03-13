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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.staff.vo.CompanyCheckVO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CompanySignReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanySignResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAuthstrReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICompanyCheckRSV;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;


/**
 * Title: ECP <br>
 * Project Name:ecp-web-demo <br>
 * Description: <br>
 * Date:2015-8-5下午2:51:38  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
@Controller
@RequestMapping(value="/companycheck")
public class CompanyCheckController extends EcpBaseController {
    
    private static String MODULE = CompanyCheckController.class.getName();
    
    
    @Resource
    private ICompanyCheckRSV companyCheckRSV;
    
    
    /**
     * 
     * grid:(待审核页). <br/> 
     * 
     * @author wangbh
     * @return 
     * @since JDK 1.7
     */

    @RequestMapping(value="/grid")
    public String grid(){
    	return "/staff/companycheck/companycheck-grid";
    }
    

    @RequestMapping(value="/checkindex")
    public String checkindex(Model model,@RequestParam("companyId")String companyId,@RequestParam("ids")String id){
        CompanySignReqDTO info = new CompanySignReqDTO();
        info.setCompanyId(Long.parseLong(companyId));
        CompanySignResDTO dto =  companyCheckRSV.queryCompanySign(info);
        model.addAttribute("CompanySign", dto);
        model.addAttribute("shoplogo", ImageUtil.getImageUrl(dto.getLogoPath()+"_200x250"));
        model.addAttribute("licence", ImageUtil.getImageUrl(dto.getLicencePath()+"_200x250"));
        model.addAttribute("legalPersonimage", ImageUtil.getImageUrl(dto.getLegalPersonImage()+"_200x250"));
        model.addAttribute("companyId", companyId);
        model.addAttribute("taxPath", ImageUtil.getStaticDocUrl(dto.getTaxPath(), FileUtil.getFileType(dto.getTaxPath())));
        model.addAttribute("Id", id);
        return "/staff/companycheck/companycheck-form-check";
    }
    
    @RequestMapping(value="/checkremark")
    public String checkremark(){
        
        return "/staff/companycheck/remark/check-remark";
    }
    
    @RequestMapping(value="/checkok")
    @ResponseBody
    public EcpBaseResponseVO check(@RequestParam("companyId")String companyId) throws Exception{
        LogUtil.info(this.getClass().getName(), "========checkok");
        CompanySignReqDTO info = new CompanySignReqDTO();
        info.setCompanyId(Long.parseLong(companyId));
        companyCheckRSV.updateCheckCompany(info);
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return vo;
    }
    
    @RequestMapping(value="/oncheck")
    @ResponseBody
    public EcpBaseResponseVO nocheck(@RequestParam("companyId")String companyId,@RequestParam("remark")String remark,@RequestParam("id")String id) throws Exception{
        LogUtil.info(this.getClass().getName(), "========nocheck");
        CompanySignReqDTO info = new CompanySignReqDTO();
        if(StringUtil.isBlank(remark)){
            throw new BusinessException("不通过审核理由不能为空");
        }
        info.setCompanyId(Long.parseLong(companyId));
        info.setId(Long.parseLong(id));
        info.setCheckRemark(remark);
        info.setCheckStaff(info.getStaff().getId());
        companyCheckRSV.updateNoCheckCompany(info);
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return vo;
    }
    
    @RequestMapping(value="/remove")
    @ResponseBody
    public EcpBaseResponseVO remove(@RequestParam("id")String id)throws Exception{
        
        CustAuthstrReqDTO info = new CustAuthstrReqDTO();
        info.setId(Long.parseLong(id));
       // companyCheckRSV.removeCustAuthstr(info);
        LogUtil.info(this.getClass().getName(), "========remove over");
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return vo;
    }
    
    
    @RequestMapping("/gridlist")
    @ResponseBody
    public Model gridList(Model model, EcpBasePageReqVO vo,@Valid @ModelAttribute("CompanyCheck") CompanyCheckVO CompanyCheck) throws Exception{
        
        CompanySignReqDTO info = vo.toBaseInfo(CompanySignReqDTO.class);
        info.setStaffCode(CompanyCheck.getStaffCode());
        info.setCheckStatus(CompanyCheck.getCheckStatus());
        PageResponseDTO<CompanySignResDTO> t = companyCheckRSV.queryCompanySignList(info);
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);

        return super.addPageToModel(model, respVO);
        
    }
    

}


