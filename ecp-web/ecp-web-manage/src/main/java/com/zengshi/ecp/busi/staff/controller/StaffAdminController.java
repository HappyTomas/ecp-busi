package com.zengshi.ecp.busi.staff.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.util.WebContextUtil;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.staff.vo.AuthStaffAdminVO;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AuthPrivilegeResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthRoleReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthRoleResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffAdminReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffAdminResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffGroupReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffGroupResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAuthAdminRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IAuthStaffRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IRoleManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffGroupManageRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ResourceMsgUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 管理员信息管理<br>
 * Date:2015年9月15日下午6:19:26  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value="/staffadmin")
public class StaffAdminController extends EcpBaseController {
    
    private static final String MODULE = StaffAdminController.class.getName();
    
    @Resource
    private IAuthAdminRSV authAdminRSV; //管理员
    
    @Resource
    private IAuthStaffRSV authStaffRSV;
    
    @Resource
    private ICustManageRSV custManageRSV;
    
    @Resource
    private IRoleManageRSV roleManageRSV;
    
    @Resource
    private IStaffGroupManageRSV staffGroupManageRSV;
    
    
    
    /**
     * 
     * grid:(跳转到管理员管理列表). <br/> 
     * 
     * @author linby 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/grid")
    public String grid(Model model){
        
        AuthRoleReqDTO authRoleReq =  new AuthRoleReqDTO();
        authRoleReq.setPageNo(0);//设为0，查询全部
        authRoleReq.setSysCode("2000");//只查询管理平台的角色
        authRoleReq.setStatus("1");//查询有效的角色
        PageResponseDTO<AuthRoleResDTO> authRoleRes = roleManageRSV.listAuthRole(authRoleReq);
        
        AuthStaffGroupReqDTO staffGroupReq = new AuthStaffGroupReqDTO();
        staffGroupReq.setPageNo(0);//设为0，查询全部
        staffGroupReq.setStaffClass("10");//只查询管理平台的用户组
        staffGroupReq.setStatus("1");//查询有效的用户组
        PageResponseDTO<AuthStaffGroupResDTO> staffGroupRes = staffGroupManageRSV.listAuthStaffGroup(staffGroupReq);
        
        model.addAttribute("roleList", authRoleRes.getResult());
        model.addAttribute("staffGroupList", staffGroupRes.getResult());
        return "/staff/staffadmin/admin-grid";
    }
    
    /**
     * 
     * gridList:(查询管理员列表数据). <br/> 
     * 
     * @author linby 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/gridlist")
    @ResponseBody
    public Model gridList(Model model, AuthStaffAdminVO vo) throws Exception{
        
        AuthStaffAdminReqDTO info = vo.toBaseInfo(AuthStaffAdminReqDTO.class);
        info.setStaffCode(vo.getStaffCode());//登录号
        info.setSerialNumber(vo.getSerialNumber());//手机号码
        info.setAliasName(vo.getAliasName());//昵称
        info.setStaffName(vo.getStaffName());//真实姓名
        info.setStaffEmail(vo.getStaffEmail());//邮箱
        info.setStaffRole(vo.getStaffRole());//所属角色
        info.setStaffGroup(vo.getStaffGroup());//所属用户
        PageResponseDTO<AuthStaffAdminResDTO> t = authAdminRSV.listAuthStaffAdmin(info);
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        @SuppressWarnings("rawtypes")
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);

        return super.addPageToModel(model, respVO);
        
    }
    
    /**
     * 
     * add:(跳转到管理员添加页面). <br/> 
     * 
     * @author linby 
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/add")
    public String add(Model model) throws Exception{
        AuthRoleReqDTO authRoleReq =  new AuthRoleReqDTO();
        authRoleReq.setPageNo(0);//设为0，查询全部
        authRoleReq.setSysCode(StaffConstants.PublicParam.SYS_CODE_MANAGE);//只查询管理平台的角色
        authRoleReq.setStatus(StaffConstants.PublicParam.STATUS_ACTIVE);//查询有效的角色
        PageResponseDTO<AuthRoleResDTO> authRoleRes = roleManageRSV.listAuthRole(authRoleReq);
        
        AuthStaffGroupReqDTO staffGroupReq = new AuthStaffGroupReqDTO();
        staffGroupReq.setPageNo(0);//设为0，查询全部
        staffGroupReq.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_M);//只查询管理平台的用户组
        staffGroupReq.setStatus(StaffConstants.PublicParam.STATUS_ACTIVE);//查询有效的用户组
        PageResponseDTO<AuthStaffGroupResDTO> staffGroupRes = staffGroupManageRSV.listAuthStaffGroup(staffGroupReq);
        
        model.addAttribute("roleList", authRoleRes.getResult());
        model.addAttribute("staffGroupList", staffGroupRes.getResult());
        return "/staff/staffadmin/admin-add";
    }
    
    /**
     * 
     * addAdmin:(添加管理员). <br/> 
     * 
     * @author linby 
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/addadmin")
    @ResponseBody
    public EcpBaseResponseVO addAdmin(@Valid AuthStaffAdminVO vo) throws Exception{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        AuthStaffAdminReqDTO reqDto = new AuthStaffAdminReqDTO();
        reqDto.setAliasName(vo.getAliasName());//昵称
        reqDto.setStaffName(vo.getStaffName());//真实姓名
        reqDto.setSerialNumber(vo.getSerialNumber());//手机号码
        reqDto.setStaffEmail(vo.getStaffEmail());//邮箱
        reqDto.setStaffCode(vo.getStaffCode());//登录工号
        reqDto.setCreateFrom(StaffConstants.authStaff.CREATE_FROM_MANAGER);//管理员新增
        reqDto.setStaffPasswd(vo.getStaffPasswd());//密码
        reqDto.setCreateStaff(reqDto.getStaff().getId());
        reqDto.setUpdateStaff(reqDto.getStaff().getId());
        reqDto.setStaffRole(vo.getStaffRole());//用户权限：角色
        reqDto.setStaffGroup(vo.getStaffGroup());//用户权限：用户组
        authAdminRSV.saveAuthStaffAdmin(reqDto);
        res.setResultMsg(ResourceMsgUtil.getMessage("web.staff.101001", new String[]{}));
        return res;
    }
    
    /**
     * 
     * update:(跳转到管理员修改页面). <br/> 
     * 
     * @author linby 
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/update")
    public String update(Model model, @RequestParam("adminId") String adminId,@RequestParam("staffRole")String staffRole,@RequestParam("staffGroup")String staffGroup) throws Exception{
        /*获取管理员用户相关信息*/
        AuthStaffAdminReqDTO  reqDto = new AuthStaffAdminReqDTO();
        reqDto.setId(Long.valueOf(adminId));
        AuthStaffAdminResDTO resDto = authAdminRSV.findAuthStaffAdminById(reqDto);
        AuthStaffResDTO res = authStaffRSV.findAuthStaffById(Long.valueOf(adminId));
        resDto.setStaffPasswd(res.getStaffPasswd());
        model.addAttribute("adminInfo", resDto);
        
        /*获取角色下拉列表及设置默认选中*/
        List<AuthRoleResDTO> roleResust = new ArrayList<AuthRoleResDTO>();
        AuthRoleReqDTO authRoleReq =  new AuthRoleReqDTO();
        authRoleReq.setPageNo(0);//设为0，查询全部
        authRoleReq.setSysCode(StaffConstants.PublicParam.SYS_CODE_MANAGE);//只查询管理平台的角色
        authRoleReq.setStatus(StaffConstants.PublicParam.STATUS_ACTIVE);//查询有效的角色
        PageResponseDTO<AuthRoleResDTO> authRoleRes = roleManageRSV.listAuthRole(authRoleReq);
        //设置角色默认选中
        if (StringUtil.isNotBlank(staffRole)) {
            String [] roleArray = staffRole.split(",");
            if (CollectionUtils.isNotEmpty(authRoleRes.getResult())) {
                List<AuthRoleResDTO> roleList = authRoleRes.getResult();
                for (AuthRoleResDTO authRole : roleList) {
                    for (int i = 0; i < roleArray.length; i++) {
                        if (roleArray[i].equals(authRole.getId().toString())) {
                            authRole.setStatus("-9999");
                            break;
                        }
                    }
                    roleResust.add(authRole);
                }
            }
        } else {
            roleResust = authRoleRes.getResult();
        }
        
        /*获取用户组下拉列表及设置默认选中*/
        List<AuthStaffGroupResDTO> groupResust = new ArrayList<AuthStaffGroupResDTO>();
        AuthStaffGroupReqDTO staffGroupReq = new AuthStaffGroupReqDTO();
        staffGroupReq.setPageNo(0);//设为0，查询全部
        staffGroupReq.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_M);//只查询管理平台的用户组
        staffGroupReq.setStatus(StaffConstants.PublicParam.STATUS_ACTIVE);//查询有效的用户组
        PageResponseDTO<AuthStaffGroupResDTO> staffGroupRes = staffGroupManageRSV.listAuthStaffGroup(staffGroupReq);
        //设置用户组默认选中
        if (StringUtil.isNotBlank(staffGroup)) {
            String [] groupArray = staffGroup.split(",");
            if (CollectionUtils.isNotEmpty(staffGroupRes.getResult())) {
                List<AuthStaffGroupResDTO> groupList = staffGroupRes.getResult();
                for (AuthStaffGroupResDTO authGroup : groupList) {
                    for (int i = 0; i < groupArray.length; i++) {
                        if (groupArray[i].equals(authGroup.getId().toString())) {
                            authGroup.setStatus("-9999");
                            break;
                        }
                    }
                    groupResust.add(authGroup);
                }
            }
        } else {
            groupResust = staffGroupRes.getResult();
        }
        model.addAttribute("staffGroupTemp", staffGroup);//所属用户组
        model.addAttribute("staffRoleTemp", staffRole);//所属角色
        model.addAttribute("roleList", roleResust);
        model.addAttribute("staffGroupList", groupResust);
        return "/staff/staffadmin/admin-update";
    }
    
    /**
     * 
     * updateAdmin:(修改管理员). <br/> 
     * 
     * @author linby 
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/updateadmin")
    @ResponseBody
    public EcpBaseResponseVO updateAdmin(@Valid AuthStaffAdminVO vo) throws Exception{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        AuthStaffAdminReqDTO reqDto = new AuthStaffAdminReqDTO();
        reqDto.setId(vo.getStaffId());
        reqDto.setStaffName(vo.getStaffName());
        reqDto.setSerialNumber(vo.getSerialNumber());
        reqDto.setStaffEmail(vo.getStaffEmail());
        reqDto.setStaffRoleEdit(vo.getStaffRoleEdit());
        reqDto.setStaffGroupEdit(vo.getStaffGroupEdit());
        reqDto.setStaffRole(vo.getStaffRole());
        reqDto.setStaffGroup(vo.getStaffGroup());
        authAdminRSV.updateAuthStaffAdminById(reqDto);
        res.setResultMsg(ResourceMsgUtil.getMessage("web.staff.101001", new String[]{}));
        return res;
    }
    
    /**
     * 
     * deleteAdmin:(删除管理员). <br/> 
     * 
     * @author linby 
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/deleteadmin")
    @ResponseBody
    public EcpBaseResponseVO deleteAdmin(@Valid AuthStaffAdminVO vo) throws Exception{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        AuthStaffAdminReqDTO reqDto = new AuthStaffAdminReqDTO();
        try {
            authAdminRSV.deleteAuthStaffAdminById(reqDto);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return res;
    }
    /**
     * 
     * updateAdminFlag:(更新管理人员状态). <br/> 
     * 
     * @author huangxl 
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    @RequestMapping(value="/updateflag")
    @ResponseBody
    public EcpBaseResponseVO updateAdminFlag(@Valid AuthStaffAdminVO vo) throws Exception{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        AuthStaffAdminReqDTO reqDto = new AuthStaffAdminReqDTO();
        reqDto.setId(vo.getId());
        reqDto.setStaffFlag(vo.getStaffFlag());
        try {
            authAdminRSV.updateAuthStaffAdminFlag(reqDto);
            res.setResultMsg(ResourceMsgUtil.getMessage("web.staff.101001", new String[]{}));
        } catch (BusinessException e) {
            res.setResultMsg(ResourceMsgUtil.getMessage("web.staff.101000", new String[]{}));
        }
        return res;
    }
    
    /**
     * 
     * updateAdmin:(重置管理员密码). <br/> 
     * 
     * @author huangxl 
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    @RequestMapping(value="/pwdreset")
    @ResponseBody
    public EcpBaseResponseVO resetAdminPwd(@RequestParam("staffId") Long staffId) throws Exception{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        AuthStaffReqDTO req = new AuthStaffReqDTO();
        req.setId(staffId);
        int resCount = custManageRSV.resetPwd(req);
        if (resCount > 0) {
            res.setResultMsg(ResourceMsgUtil.getMessage("web.staff.101001", new String[]{}));
        } else {
            res.setResultMsg(ResourceMsgUtil.getMessage("web.staff.101000", new String[]{}));
        }
        return res;
    }
    
    /**
     * 
     * update:(跳转到管理员修改密码页面). <br/> 
     * 
     * @author huangxl6 
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    @RequestMapping(value="/pwd")
    public String pwd(Model model) throws Exception{
        BaseInfo baseInfo = new BaseInfo();
        model.addAttribute("staffId",baseInfo.getStaff().getId());
        return "/staff/staffadmin/admin-pwd-update";
    }
    
    /**
     * 
     * updateAdmin:(修改密码). <br/> 
     * 
     * @author huangxl 
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    @RequestMapping(value="/pwdupdate")
    @ResponseBody
    public EcpBaseResponseVO pwdUpdate(@Valid AuthStaffAdminVO vo) throws Exception{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        AuthStaffReqDTO req = new AuthStaffReqDTO();
        req.setId(vo.getStaffId());//id
        req.setStaffPasswd(vo.getStaffPasswd());//新密码
        req.setStaffPwdOld(vo.getStaffPasswdOld());//旧密码
        authStaffRSV.updatePwdById(req);
        res.setResultMsg(ResourceMsgUtil.getMessage("web.staff.101001", new String[]{}));
        return res;
    }
    @RequestMapping(value="/admininfo")
    public String admininfo(Model model) throws Exception{
        /*获取管理员用户相关信息*/
        AuthStaffAdminReqDTO info = new AuthStaffAdminReqDTO();
        info.setStaffCode(WebContextUtil.getCurrentUser().getStaffCode());//登录号
        info.setId(WebContextUtil.getCurrentUser().getStaffId());
        info.setPageNo(0);

        PageResponseDTO<AuthStaffAdminResDTO> t = authAdminRSV.listAuthStaffAdmin(info);
        
        AuthStaffAdminResDTO resDto = null;
        if(!CollectionUtils.isEmpty(t.getResult()))
        {
            resDto = t.getResult().get(0);
            LogUtil.info(MODULE, "获取到的资料信息："+resDto.toString());
        }
        model.addAttribute("adminInfo", resDto);

        return "/staff/staffadmin/admin-info";
    }
    
    
}

