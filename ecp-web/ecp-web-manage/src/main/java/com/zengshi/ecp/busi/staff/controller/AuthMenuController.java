package com.zengshi.ecp.busi.staff.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.staff.vo.AuthMenuVO;
import com.zengshi.ecp.busi.staff.vo.SaveAuthMenuVO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AuthMenuReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthMenuResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IMenuManageRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 菜单管理<br>
 * Date:2015年9月15日下午6:16:40  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value="/menu")
public class AuthMenuController extends EcpBaseController {
    
    private static String MODULE = AuthMenuController.class.getName();
    
    @Resource
    private IMenuManageRSV menuManageRSV; //菜单服务
   
    
    /**
     * 
     * grid:(跳转到菜单管理列表). <br/> 
     * 
     * @author linby 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/grid")
    public String grid(Model model){
        
        return "/staff/menu/menu-grid";
    }
    
    @RequestMapping(value="nodedatas")
    @ResponseBody
    public String loadMenuData(Model model, AuthMenuVO menudata)
    {
        
        System.out.println("=====从ajax获取到的节点参数："+menudata.toString());
        
        AuthMenuReqDTO dto = new AuthMenuReqDTO();
        dto.setPageNo(0);
        //根据系统编码查找菜单列表
        if(!StringUtil.isBlank(menudata.getSysCode()))
        {
            dto.setSysCode(menudata.getSysCode());
        }
        //根据父菜单ID，查找菜单列表，作为父菜单ID即为一个目录
        if(menudata.getParentMenuId() != null)
        {
            dto.setParentMenuId(Long.valueOf(menudata.getParentMenuId()));
        }
        dto.setStatus(StaffConstants.Role.ROLE_STATUS_ACTIVE);
        
        PageResponseDTO<AuthMenuResDTO> menuPage = menuManageRSV.listAuthMenu(dto);
        
        List<AuthMenuResDTO> menuList = menuPage.getResult();
        List<AuthMenuVO> trees = new ArrayList<AuthMenuVO>();
        if(CollectionUtils.isNotEmpty(menuList)){
            for(AuthMenuResDTO respDTO : menuList){
                AuthMenuVO vo = DtoToVo(respDTO);
                trees.add(vo);
            }
        }
        String json = JSONObject.toJSONString(trees);
        LogUtil.info(MODULE, String.format("返回JSON数据：%s", json));
        return json;
    }
    @RequestMapping(value="/onenode")
    public AuthMenuVO onenode(@RequestParam(value="menu_id",required=true)String menu_id) throws Exception
    {
        AuthMenuReqDTO dto = new AuthMenuReqDTO();
        dto.setId(Long.valueOf(menu_id));
        dto.setStatus(StaffConstants.Role.ROLE_STATUS_ACTIVE);

        AuthMenuResDTO resdto = menuManageRSV.findAuthMenuById(dto);
        if(resdto != null)
        {
            AuthMenuVO vo = DtoToVo(resdto);
            vo.setMenuPicURL(ImageUtil.getImageUrl(resdto.getMenuPic()));
            return vo;
        }
        return null;
    }

    
    /**
     * 
     * add:(跳转到菜单添加页面). <br/> 
     * 
     * @author linby 
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/add")
    public String addmenu() throws Exception{
        return "/staff/menu/menuadd/menu-add";
    }
    
    /**
     * 
     * addMenu:(添加菜单). <br/> 
     * 
     * @author linby 
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/addmenudir")
    @ResponseBody
    public SaveAuthMenuVO addMenudir(@Valid SaveAuthMenuVO vo) throws Exception{
        AuthMenuReqDTO reqDto = VoToDto(vo);
        reqDto.setStatus("1");
        reqDto.setMenuType("0");
//        System.out.println("=======新增菜单获取到的参数："+vo.toString());
        //将vo转换为dto
        Long id = menuManageRSV.saveAuthMenu(reqDto);
        
        vo.setId(String.valueOf(id));
        vo.setpId(String.valueOf(reqDto.getParentMenuId()));
        vo.setName(reqDto.getMenuTitle());
        vo.setMenuType(reqDto.getMenuType());
        if("0".equals(reqDto.getMenuType()))
        {
            vo.setIsParent(true);
        }
        else {
            vo.setIsParent(false);
        }
        return vo;
    }
    @RequestMapping(value="/addsubmenu")
    @ResponseBody
    public SaveAuthMenuVO addsubmenu(@Valid SaveAuthMenuVO vo) throws Exception{
        //将vo转换为dto
        AuthMenuReqDTO reqDto = VoToDto(vo);
        reqDto.setStatus("1");
        reqDto.setMenuType("1");
        
        Long id = menuManageRSV.saveAuthMenu(reqDto);
        
        vo.setId(String.valueOf(id));
        vo.setpId(String.valueOf(reqDto.getParentMenuId()));
        vo.setName(reqDto.getMenuTitle());
        vo.setMenuType(reqDto.getMenuType());
        if("0".equals(reqDto.getMenuType()))
        {
            vo.setIsParent(true);
        }
        else {
            vo.setIsParent(false);
        }
        return vo;
    }
    
    @RequestMapping(value="/savemenu")
    @ResponseBody
    public SaveAuthMenuVO savemenu(@Valid SaveAuthMenuVO vo) throws Exception{
        AuthMenuVO res = new AuthMenuVO();
        //将vo转换为dto
        AuthMenuReqDTO reqDto = VoToDto(vo);
              
        if(reqDto.getId() == null)
        {
            reqDto.setStatus("1");
            Long id = menuManageRSV.saveAuthMenu(reqDto);
            vo.setId(String.valueOf(id));
            vo.setpId(String.valueOf(reqDto.getParentMenuId()));
            vo.setName(reqDto.getMenuTitle());
            vo.setIsNewCreate(true);
        }
        else {
            menuManageRSV.updateAuthMenuById(reqDto);
            vo.setpId(String.valueOf(reqDto.getParentMenuId()));
            vo.setName(reqDto.getMenuTitle());
            vo.setIsNewCreate(false);

        }

        if("0".equals(reqDto.getMenuType()))
        {
            vo.setIsParent(true);
        }
        else {
            vo.setIsParent(false);
        }
        return vo;
    }
    /**
     * 
     * update:(跳转到菜单修改页面). <br/> 
     * 
     * @author linby 
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/update")
    public String update(Model model, @Valid AuthMenuVO vo) throws Exception{
        AuthMenuReqDTO  reqDto = new AuthMenuReqDTO();
        ObjectCopyUtil.copyObjValue(vo, reqDto, null, false);
//        AuthRoleResDTO resDto = roleManageRSV.findAuthRoleById(reqDto);
//        model.addAttribute("menuInfo", resDto);
        return "/staff/menu/menu-update";
    }
    
    /**
     * 
     * updateMenu:(修改菜单). <br/> 
     * 
     * @author linby 
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/updatemenu")
    @ResponseBody
    public SaveAuthMenuVO updateMenu(@Valid SaveAuthMenuVO vo) throws Exception{
        
        AuthMenuReqDTO reqDto = VoToDto(vo);
        vo.setpId(String.valueOf(reqDto.getParentMenuId()));
        vo.setName(reqDto.getMenuTitle());
        if("0".equals(reqDto.getMenuType()))
        {
            vo.setIsParent(true);
        }
        else {
            vo.setIsParent(false);
        }

        menuManageRSV.updateAuthMenuById(reqDto);
        
        return vo;
    }
    
    /**
     * 
     * deleteMenu:(删除菜单). <br/> 
     * 
     * @author linby 
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/menudelete")
    @ResponseBody
    public EcpBaseResponseVO menudelete(@Valid SaveAuthMenuVO vo) throws Exception{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        AuthMenuReqDTO reqDto = VoToDto(vo);

        menuManageRSV.deleteAuthMenuById(reqDto);

         res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);

        return res;
    }
    
    
    @SuppressWarnings("unused")
    private AuthMenuReqDTO VoToDto(SaveAuthMenuVO vo)
    {
        AuthMenuReqDTO dto = new AuthMenuReqDTO();
        if(!StringUtil.isBlank(vo.getId()))
        {
            dto.setId(Long.valueOf(vo.getId()));
        }
        if(!StringUtil.isBlank(vo.getpId()))
        {
            dto.setParentMenuId(Long.valueOf(vo.getpId()));
        }
        if(!StringUtil.isBlank(vo.getParentMenuId()))
        {
            dto.setParentMenuId(Long.valueOf(vo.getParentMenuId()));
        }
        if(!StringUtil.isBlank(vo.getMenuTitle()))
        {
            dto.setMenuTitle(vo.getMenuTitle());
        }
        if(!StringUtil.isBlank(vo.getMenuPic()))
        {
            dto.setMenuPic(vo.getMenuPic());
        }
        if(!StringUtil.isBlank(vo.getMenuType()))
        {
            dto.setMenuType(vo.getMenuType());
        }
        if(!StringUtil.isBlank(vo.getMenuUrl()))
        {
            dto.setMenuUrl(vo.getMenuUrl());
        }
        if(!StringUtil.isBlank(vo.getMenuDesc()))
        {
            dto.setMenuDesc(vo.getMenuDesc());
        }
        if(!StringUtil.isBlank(vo.getSysCode()))
        {
            dto.setSysCode(vo.getSysCode());
        }
        if(!StringUtil.isBlank(String.valueOf(vo.getSortOrder())))
        {
            dto.setSortOrder(vo.getSortOrder());
        }
        
        return dto;
    }
    
    @SuppressWarnings("unused")
    private AuthMenuVO DtoToVo(AuthMenuResDTO dto)
    {

        AuthMenuVO vo = new AuthMenuVO();
        if(!StringUtil.isBlank(String.valueOf(dto.getId())))
        {
            vo.setId(String.valueOf(dto.getId()));
        }
        if(!StringUtil.isBlank(String.valueOf(dto.getParentMenuId())))
        {
            vo.setParentMenuId(String.valueOf(dto.getParentMenuId()));
            vo.setpId(String.valueOf(dto.getParentMenuId()));
        }
        if(!StringUtil.isBlank(dto.getMenuTitle()))
        {
            vo.setMenuTitle(dto.getMenuTitle());
            vo.setName(dto.getMenuTitle());
        }
        if(!StringUtil.isBlank(dto.getMenuType()))
        {
            vo.setMenuType(dto.getMenuType());
        }
        if(!StringUtil.isBlank(dto.getMenuPic()))
        {
            vo.setMenuPic(dto.getMenuPic());
        }
        if(!StringUtil.isBlank(dto.getMenuUrl()))
        {
            vo.setMenuUrl(dto.getMenuUrl());
        }
        if(!StringUtil.isBlank(dto.getMenuDesc()))
        {
            vo.setMenuDesc(dto.getMenuDesc());
        }
        if(!StringUtil.isBlank(dto.getSysCode()))
        {
            vo.setSysCode(dto.getSysCode());
        }
        if(!StringUtil.isBlank(String.valueOf(dto.getSortOrder())))
        {
            vo.setSortOrder(dto.getSortOrder());
        }
        if("0".equals(dto.getMenuType()))
        {
            vo.setIsParent(true);
        }
        else {
            vo.setIsParent(false);
        }
        return vo;
    
    }
    
}

