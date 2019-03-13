package com.zengshi.ecp.busi.staff.controller;

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
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.staff.vo.AuthMenuVO;
import com.zengshi.ecp.busi.staff.vo.AuthRoleVO;
import com.zengshi.ecp.busi.staff.vo.DataFuncVO;
import com.zengshi.ecp.busi.staff.vo.SaveAuthRoleVO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AuthMenuReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthMenuResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthRoleReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthRoleResDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFuncReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFuncZTreeNodeDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IDataAuthManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IMenuManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IRoleManageRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 角色管理<br>
 * Date:2015年9月15日下午6:18:10  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value="/authrole")
public class AuthRoleController extends EcpBaseController {
    
    private static final String MODULE = AuthRoleController.class.getName();
    
    @Resource
    private IRoleManageRSV roleManageRSV; //角色
    
    @Resource
    private IMenuManageRSV menuManageRSV; //菜单
    
    @Resource
    private IDataAuthManageRSV dataAuthManageRSV;  //数据权限dubbo
    
    /**
     * 
     * grid:(跳转到角色管理列表). <br/> 
     * 
     * @author linby 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/grid")
    public String grid(){
        return "/staff/authrole/role-grid";
    }
    
    /**
     * 
     * gridList:(查询角色列表数据). <br/> 
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
    public Model gridList(Model model, AuthRoleVO vo) throws Exception{
        
        AuthRoleReqDTO info = vo.toBaseInfo(AuthRoleReqDTO.class);
        ObjectCopyUtil.copyObjValue(vo, info, null, false);
        if(vo.getListAll()!=null){
            info.setPageNo(0);//不分页查询
        }
        PageResponseDTO<AuthRoleResDTO> t = roleManageRSV.listAuthRole(info);
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        @SuppressWarnings("rawtypes")
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);

        return super.addPageToModel(model, respVO);
        
    }
    
    /**
     * 
     * add:(跳转到角色添加页面). <br/> 
     * 
     * @author linby 
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/add")
    public String add() throws Exception{
        return "/staff/authrole/role-add";
    }
    
    /**
     * 
     * addRole:(添加角色). <br/> 
     * 
     * @author linby 
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/addrole")
    @ResponseBody
    public EcpBaseResponseVO addRole(@Valid SaveAuthRoleVO vo) throws Exception{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        AuthRoleReqDTO reqDto = new AuthRoleReqDTO();
        ObjectCopyUtil.copyObjValue(vo, reqDto, null, false);
        try {
            roleManageRSV.saveAuthRole(reqDto);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "添加角色失败", e);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            res.setResultMsg(e.getMessage());
        }
        return res;
    }
    
    /**
     * 
     * update:(跳转到角色修改页面). <br/> 
     * 
     * @author linby 
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/update")
    public String update(Model model, @RequestParam("roleId") String roleId) throws Exception{
        
        AuthRoleReqDTO  reqDto = new AuthRoleReqDTO();
        reqDto.setId(Long.valueOf(roleId));
        AuthRoleResDTO resDto = roleManageRSV.findAuthRoleById(reqDto);
        model.addAttribute("roleInfo", resDto);
        return "/staff/authrole/role-update";
    }
    
    /**
     * 
     * updateRole:(修改角色). <br/> 
     * 
     * @author linby 
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/updaterole")
    @ResponseBody
    public EcpBaseResponseVO updateRole(@Valid SaveAuthRoleVO vo) throws Exception{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        AuthRoleReqDTO reqDto = new AuthRoleReqDTO();
        ObjectCopyUtil.copyObjValue(vo, reqDto, null, false);
        try {
            roleManageRSV.updateAuthRole(reqDto);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            res.setResultMsg(e.getMessage());
        }
        return res;
    }
    
    /**
     * 
     * enablerole:(启用角色). <br/> 
     * 
     * @author linby 
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/enablerole")
    @ResponseBody
    public EcpBaseResponseVO enableRole(AuthRoleVO vo) throws Exception{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        AuthRoleReqDTO reqDto = new AuthRoleReqDTO();
        if(vo.getId()==null){
        	res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE); 
        	res.setResultMsg("角色id不能为空");
        }
        reqDto.setId(vo.getId());
        reqDto.setSysCode(vo.getSysCode());
        reqDto.setStatus(StaffConstants.Role.ROLE_STATUS_ACTIVE);//生效
        try {
            roleManageRSV.deleteAuthRoleById(reqDto);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            res.setResultMsg(e.getMessage());
        }
        return res;
    }
    
    /**
     * 
     * deleteRole:(删除角色). <br/> 
     * 
     * @author linby 
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/deleterole")
    @ResponseBody
    public EcpBaseResponseVO deleteRole(@Valid AuthRoleVO vo) throws Exception{
        
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        AuthRoleReqDTO reqDto = new AuthRoleReqDTO();
        reqDto.setId(vo.getId());
        reqDto.setSysCode(vo.getSysCode());
        reqDto.setStatus(StaffConstants.Role.ROLE_STATUS_INVALID);
        try {
            roleManageRSV.deleteAuthRoleById(reqDto);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            res.setResultMsg(e.getMessage());
        }
        return res;
    }
    
    /**
     * 
     * generateMenuEntireTree:(得到菜单树). <br/> 
     * 
     * @author linby 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/generatemenu/entiretree")
    @ResponseBody
    public String generateMenuEntireTree(Model model, AuthMenuVO vo) throws Exception{
        AuthMenuReqDTO reqDto = new AuthMenuReqDTO();
//        reqDto.setSysCode(StaffConstants.PublicParam.SYS_CODE_MANAGE);
        if(StringUtil.isNotBlank(vo.getSysCode())){
            reqDto.setSysCode(vo.getSysCode());
        }else{
            reqDto.setSysCode(StaffConstants.PublicParam.SYS_CODE_MALL);
        }
        List<AuthMenuResDTO> listMenu = menuManageRSV.generateEntireTree(reqDto);
        String res = JSON.toJSONString(listMenu);
        return res;
    }
    
    /**
     * 
     * generateFuncEntireTree:(得到数据功能规则权限树). <br/> 
     * 
     * @author linby 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/generatefunc/entiretree")
    @ResponseBody
    public String generateFuncEntireTree(Model model, DataFuncVO vo) throws Exception{
    	if(vo.getzTreeIsLoad()!=null && vo.getzTreeIsLoad()){//如果已经初始化则返回“空”
    		return "[]";
    	}
        DataFuncReqDTO reqDto = new DataFuncReqDTO();
        if(StringUtil.isNotBlank(vo.getSysCode())){
            reqDto.setSysCode(vo.getSysCode());
        }else{
            reqDto.setSysCode(StaffConstants.PublicParam.SYS_CODE_MALL);
        }
        List<DataFuncZTreeNodeDTO> listDataAuth = dataAuthManageRSV.generateEntireTreeDataFunc(reqDto);
        String res = JSON.toJSONString(listDataAuth,SerializerFeature.DisableCircularReferenceDetect);
        return res;
    }
    
}

