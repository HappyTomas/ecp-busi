package com.zengshi.ecp.busi.staff.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.staff.vo.AuthStaffVO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AuthRoleResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAuthStaffRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 用户管理<br>
 * Date:2015年9月15日下午6:16:55  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value="/authstaff")
public class AuthStaffController extends EcpBaseController {
    
    private static final String MODULE = AuthStaffController.class.getName();
    
    @Resource
    private IAuthStaffRSV authStaffRSV;
    
    /**
     * 
     * grid:(跳转到用户管理列表). <br/> 
     * 
     * @author linby 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/grid")
    public String grid(){
        return "/staff/authstaff/staff-grid";
    }
    
    /**
     * 
     * gridList:(查询用户列表数据). <br/> 
     * 
     * @author linby 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/gridlist")
    @ResponseBody
    public Model gridList(Model model, AuthStaffVO vo) throws Exception{
        
        AuthStaffReqDTO info = vo.toBaseInfo(AuthStaffReqDTO.class);
        info.setId(vo.getId());
        info.setStaffCode(vo.getStaffCode());
        info.setStaffClass(vo.getStaffClass());
        info.setStaffFlag(vo.getStaffFlag());
        PageResponseDTO<AuthStaffResDTO> t = authStaffRSV.listAuthStaff(info);
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        @SuppressWarnings("rawtypes")
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);

        return super.addPageToModel(model, respVO);
        
    }
    
    /**
     * 
     * add:(跳转到用户添加页面). <br/> 
     * 
     * @author linby 
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/add")
    public String add() throws Exception{
        return "/staff/authstaff/staff-add";
    }
    
    /**
     * 
     * addStaff:(添加用户). <br/> 
     * 
     * @author linby 
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/addstaff")
    @ResponseBody
    public EcpBaseResponseVO addStaff(@Valid AuthStaffVO vo) throws Exception{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        try {
//            authStaffRSV.saveAuthRole(reqDto);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return res;
    }
    
    /**
     * 
     * update:(跳转到用户修改页面). <br/> 
     * 
     * @author linby 
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/update")
    public String update(Model model, @Valid AuthStaffVO vo) throws Exception{
        AuthStaffReqDTO  reqDto = new AuthStaffReqDTO();
        ObjectCopyUtil.copyObjValue(vo, reqDto, null, false);
        AuthRoleResDTO resDto = null;
        model.addAttribute("staffInfo", resDto);
        return "/staff/authstaff/staff-update";
    }
    
    /**
     * 
     * updateStaff:(修改用户). <br/> 
     * 
     * @author linby 
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/updatestaff")
    @ResponseBody
    public EcpBaseResponseVO updateRole(@Valid AuthStaffVO vo) throws Exception{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        try {
//            roleManageRSV.updateAuthRole(reqDto);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return res;
    }
    
    /**
     * 
     * deleteStaff:(删除用户). <br/> 
     * 
     * @author linby 
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/deletestaff")
    @ResponseBody
    public EcpBaseResponseVO deleteRole(@Valid AuthStaffVO vo) throws Exception{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        try {
//            roleManageRSV.deleteAuthRoleById(reqDto);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "删除用户失败", e);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return res;
    }
    
}

