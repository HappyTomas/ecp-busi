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
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.staff.vo.ImHotlineInfoVO;
import com.zengshi.ecp.busi.staff.vo.ImStaffHotlineVO;
import com.zengshi.ecp.im.dubbo.dto.ImHotlineInfoReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImHotlineInfoResDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineResDTO;
import com.zengshi.ecp.im.dubbo.interfaces.IStaffHotlineRSV;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffGroupManageRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.ResourceMsgUtil;

/**
 *  
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 客服管理<br>
 * Date:2016年11月1日下午8:55:05  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author panjs
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "/custservice")
public class CustServiceController extends EcpBaseController {

    private static String MODULE = CustManagerController.class.getName();

    @Resource
    private IStaffHotlineRSV staffHotlineRSV; 
    
    @Resource
    private IStaffGroupManageRSV staffGroupManageRSV;

    /**
     * 
     * grid:(客服人员). <br/>  
     * 
     * @author panjs 
     * @param model
     * @param companyId
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/grid")
    public String grid(Model model) {
        return "/staff/custservice/custservice-grid";
    }
    
    /**
     * 
     * thirdGrid:(第三方平台). <br/> 
     * 
     * @author huangxl5 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/third/grid")
    public String thirdGrid(Model model) {
        return "/staff/custservice/custservice-third-grid";
    }
    
    /**
     * 
     * gridList:(客服人员列表). <br/>  
     * 
     * @author panjs 
     * @param model
     * @param vo
     * @param custInfoList
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/gridlist")
    @ResponseBody
    public Model gridList(Model model, @ModelAttribute ImHotlineInfoVO vo) throws Exception {
        ImHotlineInfoReqDTO reqDto = vo.toBaseInfo(ImHotlineInfoReqDTO.class);
        ObjectCopyUtil.copyObjValue(vo, reqDto, null, false); 
        reqDto.setPlatSource("00");//只查本平台的客服
        PageResponseDTO<ImHotlineInfoResDTO> t = staffHotlineRSV.getHotlineList(reqDto);
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
        return super.addPageToModel(model, respVO);
    }
    /**
     * 
     * thirdGridList:(第三方平台客服列表). <br/> 
     * 
     * @author huangxl5 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/third/gridlist")
    @ResponseBody
    public Model thirdGridList(Model model, @ModelAttribute ImHotlineInfoVO vo) throws Exception {
        ImHotlineInfoReqDTO reqDto = vo.toBaseInfo(ImHotlineInfoReqDTO.class);
        ObjectCopyUtil.copyObjValue(vo, reqDto, null, false); 
        reqDto.setPlatSource("01");//只查第三方平台的客服
        PageResponseDTO<ImHotlineInfoResDTO> t = staffHotlineRSV.getHotlineList(reqDto);
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
        return super.addPageToModel(model, respVO);
    }
    
    /**
     * 
     * custGrid:(用户选择页面). <br/>  
     * 
     * @author panjs 
     * @param model
     * @param companyId
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/custgrid")
    public String custGrid(Model model) {
        BaseInfo baseInfo = new BaseInfo<>();
        model.addAttribute("staffCode", baseInfo.getStaff().getStaffCode());
        return "/staff/custservice/cust/cust-grid";
    } 
    
    /**
     * 
     * add:(新增客服页面). <br/>  
     * 
     * @author panjs 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/add")
    public String add(Model model){
        return "/staff/custservice/custservice-add";
    }
    /**
     * 
     * thirdAdd:(第三方平台新增客服). <br/> 
     * @author huangxl5 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    
    @RequestMapping(value="/third/add")
    public String thirdAdd(Model model){
        return "/staff/custservice/custservice-third-add";
    }
    
    /**
     * 
     * edit:(编辑客服页面). <br/>  
     * 
     * @author panjs 
     * @param model
     * @param ids
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/edit")
    public String edit(Model model,@Valid ImStaffHotlineVO vo){
        ImStaffHotlineReqDTO dto = new ImStaffHotlineReqDTO(); 
        ObjectCopyUtil.copyObjValue(vo, dto, null, false);
        ImStaffHotlineResDTO info = staffHotlineRSV.getStaffHotline(dto);  
        model.addAttribute("info", info);
        return "/staff/custservice/custservice-edit";
    }
    
    @RequestMapping(value="/third/edit")
    public String thirdEdit(Model model,@Valid ImStaffHotlineVO vo){
        ImStaffHotlineReqDTO dto = new ImStaffHotlineReqDTO(); 
        ObjectCopyUtil.copyObjValue(vo, dto, null, false);
        ImStaffHotlineResDTO info = staffHotlineRSV.getStaffHotline(dto);  
        model.addAttribute("info", info);
        String platName = BaseParamUtil.fetchParamValue("IM_PLAT_SOURCE", info.getShopId()+"");
        model.addAttribute("platName", platName);
        return "/staff/custservice/custservice-third-edit";
    }
    

    /**
     * 
     * saveCust:(添加客服信息). <br/>  
     * 
     * @author panjs 
     * @param custInfo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/savecust")
    @ResponseBody
    public EcpBaseResponseVO saveCust(@Valid ImStaffHotlineVO vo) throws Exception {
        EcpBaseResponseVO respVo = new EcpBaseResponseVO(); 
        try {
        	if(vo.getModuleType().equals("0")){
                vo.setShopId(0l);
            }
            ImStaffHotlineReqDTO dto = new ImStaffHotlineReqDTO(); 
            ObjectCopyUtil.copyObjValue(vo, dto, null, false);
            dto.setPlatSource("00");//本平台
            staffHotlineRSV.addHotlineStaff(dto); 
            respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS); 
        } catch (BusinessException e) {
            respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            respVo.setResultMsg(e.getMessage());
            LogUtil.error(MODULE, e.getErrorMessage(), e);
        }
        return respVo;
    }
    
    /**
     * 
     * thirdSaveCust:(第三方平台客服信息保存). <br/> 
     * 
     * @author huangxl5 
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/third/savecust")
    @ResponseBody
    public EcpBaseResponseVO thirdSaveCust(@Valid ImStaffHotlineVO vo) throws Exception {
        EcpBaseResponseVO respVo = new EcpBaseResponseVO(); 
        try {
            ImStaffHotlineReqDTO dto = new ImStaffHotlineReqDTO(); 
            ObjectCopyUtil.copyObjValue(vo, dto, null, false);
            dto.setPlatSource("01");//第三方平台
            staffHotlineRSV.addHotlineStaff(dto); 
            respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS); 
        } catch (BusinessException e) {
            respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            respVo.setResultMsg(e.getMessage());
            LogUtil.error(MODULE, e.getErrorMessage(), e);
        }
        return respVo;
    }
    
    /**
     * 
     * updateCust:(修改客服信息). <br/>  
     * 
     * @author panjs 
     * @param custInfo
     * @param staffId
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/updatecust")
    @ResponseBody
    public EcpBaseResponseVO updateCust(@Valid ImHotlineInfoVO vo) throws Exception {
        EcpBaseResponseVO respVo = new EcpBaseResponseVO(); 
        try {
            ImHotlineInfoReqDTO dto = new ImHotlineInfoReqDTO(); 
            ObjectCopyUtil.copyObjValue(vo, dto, null, false);
            if(vo.getModuleType().equals("0")){
                vo.setShopId(0l);
            }
            staffHotlineRSV. updateHotlineInfo(dto); 
            respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            respVo.setResultMsg(e.getMessage());
            LogUtil.error(MODULE, e.getErrorMessage(), e);
        }
        return respVo;
    }
    
    
    /**
     * 
     * thirdUpdateCust:(第三方平台客服信息编辑). <br/> 
     * 
     * @author huangxl5 
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/third/updatecust")
    @ResponseBody
    public EcpBaseResponseVO thirdUpdateCust(@Valid ImHotlineInfoVO vo) throws Exception {
        EcpBaseResponseVO respVo = new EcpBaseResponseVO(); 
        try {
            ImHotlineInfoReqDTO dto = new ImHotlineInfoReqDTO(); 
            ObjectCopyUtil.copyObjValue(vo, dto, null, false);
            staffHotlineRSV.updateHotlineInfo(dto); 
            respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            respVo.setResultMsg(e.getMessage());
            LogUtil.error(MODULE, e.getErrorMessage(), e);
        }
        return respVo;
    }
    
    /**
     * 
     * custValid:(客服列表：使生效). <br/>  
     * 
     * @author panjs 
     * @param model
     * @param staffId
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/custvalid")
    @ResponseBody
    public EcpBaseResponseVO custValid(Model model,  @RequestParam("id")Long id) throws Exception {
        EcpBaseResponseVO result = new EcpBaseResponseVO();
        ImHotlineInfoReqDTO dto = new ImHotlineInfoReqDTO();
        dto.setId(id);
        dto.setStatus("1");
        dto.setUpdateTime(DateUtil.getSysDate());
        int resCount = staffHotlineRSV.updateHotlineInfo(dto);
        if (resCount > 0) {
            result.setResultMsg(ResourceMsgUtil.getMessage("web.staff.101001", new String[]{}));
        } else {
            result.setResultMsg(ResourceMsgUtil.getMessage("web.staff.101000", new String[]{}));
        }
        return result;
    } 
    
    /**
     * 
     * custInvalid:(客服列表：使失效). <br/>  
     * 
     * @author panjs 
     * @param model
     * @param staffId
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/custinvalid")
    @ResponseBody
    public EcpBaseResponseVO custInvalid(Model model,  @RequestParam("id")Long id) throws Exception {
        EcpBaseResponseVO result = new EcpBaseResponseVO();
        ImHotlineInfoReqDTO dto = new ImHotlineInfoReqDTO();
        dto.setId(id);
        dto.setStatus("0");
        dto.setUpdateTime(DateUtil.getSysDate());
        int resCount = staffHotlineRSV.updateHotlineInfo(dto);
        if (resCount > 0) {
            result.setResultMsg(ResourceMsgUtil.getMessage("web.staff.101001", new String[]{}));
        } else {
            result.setResultMsg(ResourceMsgUtil.getMessage("web.staff.101000", new String[]{}));
        }
        return result;
    }

}
