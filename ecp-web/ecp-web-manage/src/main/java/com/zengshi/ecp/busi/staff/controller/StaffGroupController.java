package com.zengshi.ecp.busi.staff.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.staff.vo.AuthStaffGroupVO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AuthRoleReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthRoleResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffGroupReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffGroupResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IRoleManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffGroupManageRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015年9月22日下午2:59:01 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value = "/staffgroup")
public class StaffGroupController extends EcpBaseController {

    private static final String MODULE = StaffGroupController.class.getName();

    @Resource
    private IStaffGroupManageRSV staffGroupManageRSV; // 用户组

    @Resource
    private IRoleManageRSV roleManageRSV;

    @RequestMapping(value = "/grid")
    public String grid(Model model) {
        AuthRoleReqDTO dto = new AuthRoleReqDTO();
        dto.setPageNo(-1);
        PageResponseDTO<AuthRoleResDTO> page = roleManageRSV.listAuthRole(dto);
        model.addAttribute("rolelist", page.getResult());
        return "/staff/staffgroup/group-grid";
    }

    @RequestMapping(value = "/gridlist")
    @ResponseBody
    public Model gridList(Model model, AuthStaffGroupVO vo) throws Exception {

        AuthStaffGroupReqDTO info = vo.toBaseInfo(AuthStaffGroupReqDTO.class);
        if (StringUtil.isNotBlank(vo.getStatus())) {
            info.setStatus(vo.getStatus());
        }
        if (StringUtil.isNotBlank(vo.getGroupName())) {
            info.setGroupName(vo.getGroupName());
        }
        if (StringUtil.isNotBlank(vo.getStaffClass())) {
            info.setStaffClass(vo.getStaffClass());
        }
        if (StringUtil.isNotBlank(vo.getRoleId())) {
            info.setRoleId(Long.parseLong(vo.getRoleId()));
        }
        info.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_M);
        PageResponseDTO<AuthStaffGroupResDTO> t = staffGroupManageRSV.listAuthStaffGroup(info);
        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        @SuppressWarnings("rawtypes")
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);

        return super.addPageToModel(model, respVO);

    }

    @RequestMapping(value = "/add")
    public String add(Model model) throws Exception {
        AuthRoleReqDTO dto = new AuthRoleReqDTO();
        dto.setPageNo(-1);
        dto.setStatus(StaffConstants.StaffGroup.STAFF_GROUP_STATUS_ACTIVE);
        dto.setSysCode(StaffConstants.PublicParam.SYS_CODE_MANAGE);
        PageResponseDTO<AuthRoleResDTO> page = roleManageRSV.listAuthRole(dto);
        model.addAttribute("rolelist", page.getResult());
        return "/staff/staffgroup/group-add";
    }

    /**
     * 
     * addGroup:(新增分组). <br/>
     * 
     * @author wangbh
     * @param vo
     * @return
     * @throws Exception
     * @since JDK 1.7
     */
    @RequestMapping(value = "/addgroup")
    @ResponseBody
    public EcpBaseResponseVO addGroup(@Valid
    AuthStaffGroupVO vo) throws Exception {
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        AuthStaffGroupReqDTO reqDto = new AuthStaffGroupReqDTO();

        reqDto.setGroupName(vo.getGroupName());
        reqDto.setStaffClass(vo.getStaffClass());
        reqDto.setGroupFlag(StaffConstants.StaffGroup.STAFF_GROUP_FLAG_CUSTOM);
        reqDto.setStatus(StaffConstants.StaffGroup.STAFF_GROUP_STATUS_ACTIVE);
        reqDto.setRoleId(Long.parseLong(vo.getRoleId()));
        reqDto.setCreateStaff(reqDto.getStaff().getId());
        staffGroupManageRSV.saveStaffGroup(reqDto);
        res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return res;
    }
    
    /**
     * 
     * update:(编辑页面). <br/> 
     * 
     * @author wangbh
     * @param model
     * @param groupId
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    @RequestMapping(value = "/edit")
    public String update(Model model,@RequestParam("groupId") String groupId) throws Exception {
        AuthRoleReqDTO dto = new AuthRoleReqDTO();
        dto.setPageNo(-1);
        dto.setStatus(StaffConstants.StaffGroup.STAFF_GROUP_STATUS_ACTIVE);
        dto.setSysCode(StaffConstants.PublicParam.SYS_CODE_MANAGE);
        PageResponseDTO<AuthRoleResDTO> page = roleManageRSV.listAuthRole(dto);
        model.addAttribute("rolelist", page.getResult());
        
        AuthStaffGroupReqDTO res = new AuthStaffGroupReqDTO();
        res.setId(Long.parseLong(groupId));
        AuthStaffGroupResDTO resDto = staffGroupManageRSV.findStaffGroupById(res);
        model.addAttribute("dto", resDto);
        return "/staff/staffgroup/group-edit";
    }

    /**
     * 
     * updateGroup:(编辑用户分组). <br/> 
     * 
     * @author wangbh
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    @RequestMapping(value = "/editgroup")
    @ResponseBody
    public EcpBaseResponseVO updateGroup(@Valid
    AuthStaffGroupVO vo) throws Exception {
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        AuthStaffGroupReqDTO reqDto = new AuthStaffGroupReqDTO();
        ObjectCopyUtil.copyObjValue(vo, reqDto, null, false);
        reqDto.setId(vo.getGroupId());
        staffGroupManageRSV.updateStaffGroup(reqDto);
       
        return res;
    }

    /**
     * 
     * deleteGroup:(删除用户组). <br/>
     * 
     * @author linby
     * @param vo
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping(value = "/deletegroup")
    @ResponseBody
    public EcpBaseResponseVO deleteGroup(@Valid
    AuthStaffGroupVO vo) throws Exception {
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        AuthStaffGroupReqDTO reqDto = new AuthStaffGroupReqDTO();
        ObjectCopyUtil.copyObjValue(vo, reqDto, null, false);
        reqDto.setId(vo.getGroupId());
        try {
            staffGroupManageRSV.deleteStaffGroupById(reqDto);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return res;
    }
}
