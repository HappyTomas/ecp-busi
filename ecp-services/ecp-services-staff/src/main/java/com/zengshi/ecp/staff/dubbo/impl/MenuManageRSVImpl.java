package com.zengshi.ecp.staff.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.model.AuthMenu;
import com.zengshi.ecp.staff.dao.model.AuthPrivilege2Menu;
import com.zengshi.ecp.staff.dubbo.dto.AuthManageReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthMenuReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthMenuResDTO;
import com.zengshi.ecp.staff.dubbo.dto.PrivilegeMenuRelReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IMenuManageRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.cache.interfaces.IMenuCacheSV;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IMenuManageSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月29日下午2:17:58  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public class MenuManageRSVImpl implements IMenuManageRSV {

    private static final String MODULE = MenuManageRSVImpl.class.getName();
    
    @Resource
    private IMenuManageSV menuManageSV;
    
    @Resource
    private IMenuCacheSV menuCacheSV;

    
    @Override
    public List<AuthMenuResDTO> listAuthMenu(AuthManageReqDTO reqDto) throws BusinessException {
        return menuManageSV.listAuthMenu(reqDto);
    }
    
    @Override
    public long saveAuthMenu(AuthMenuReqDTO reqDto) throws BusinessException {
        AuthMenu am = new AuthMenu();
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        //同一个系统，同一个层级下，菜单名不能重复
        AuthMenuReqDTO menuTitle = new AuthMenuReqDTO();
        menuTitle.setMenuTitle(reqDto.getMenuTitle());
        menuTitle.setSysCode(reqDto.getSysCode());
        menuTitle.setMenuTitleFull("FULL");//菜单全匹配查询
        menuTitle.setParentMenuId(reqDto.getParentMenuId());
        menuTitle.setPageSize(2);
        PageResponseDTO<AuthMenuResDTO> res = menuManageSV.listAuthMenu(menuTitle);
        if (res != null && CollectionUtils.isNotEmpty(res.getResult())) {
            throw new BusinessException("同一个系统，同一个层级中菜单名不能复重，菜单名称：[" + reqDto.getMenuTitle() + "]");
        }
        
        ObjectCopyUtil.copyObjValue(reqDto, am, null, false);
        am.setCreateStaff(reqDto.getStaff().getId());
        am.setCreateTime(DateUtil.getSysDate());
        am.setUpdateStaff(reqDto.getStaff().getId());
        am.setUpdateTime(DateUtil.getSysDate());
        long result = menuManageSV.saveAuthMenu(am);
        this.clearMenuCache();//清空下缓存，否则新增的数据，不会立刻生效
        return result;
    }

    @Override
    public void savePrivilegeMenuRel(PrivilegeMenuRelReqDTO reqDto) throws BusinessException {
        AuthPrivilege2Menu ap2m = new AuthPrivilege2Menu();
        ObjectCopyUtil.copyObjValue(reqDto, ap2m, null, false);
        menuManageSV.savePrivilegeMenuRel(ap2m);
    }

    @Override
    public PageResponseDTO<AuthMenuResDTO> listAuthMenu(AuthMenuReqDTO reqDto)
            throws BusinessException {
        return menuManageSV.listAuthMenu(reqDto);
    }

    @Override
    public AuthMenuResDTO findAuthMenuById(AuthMenuReqDTO reqDto) throws BusinessException {
        if(reqDto==null||reqDto.getId()==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        AuthMenuResDTO res = menuManageSV.findAuthMenuById(reqDto);
        return res;
    }

    @Override
    public void updateAuthMenuById(AuthMenuReqDTO reqDto) throws BusinessException {
        if(reqDto==null||reqDto.getId()==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        //同一个系统，同一个层级下，菜单名不能重复
        AuthMenuReqDTO menuTitle = new AuthMenuReqDTO();
        menuTitle.setMenuTitle(reqDto.getMenuTitle());
        menuTitle.setSysCode(reqDto.getSysCode());
        menuTitle.setMenuTitleFull("FULL");//菜单全匹配查询
        menuTitle.setParentMenuId(reqDto.getParentMenuId());
        menuTitle.setPageSize(2);
        PageResponseDTO<AuthMenuResDTO> res = menuManageSV.listAuthMenu(menuTitle);
        if (res != null && CollectionUtils.isNotEmpty(res.getResult())) {
        	if (res.getResult().size() == 1) {
        		AuthMenuResDTO menuResDto = res.getResult().get(0);
        		if (menuResDto.getId().longValue() != reqDto.getId().longValue()) {
        			throw new BusinessException("同一个系统，同一个层级中菜单名不能复重，菜单名称：[" + reqDto.getMenuTitle() + "]");
        		}
        	} else {
        		throw new BusinessException("同一个系统，同一个层级中菜单名不能复重，菜单名称：[" + reqDto.getMenuTitle() + "]");
        	}
            
        }
        
        AuthMenu update = new AuthMenu();
        ObjectCopyUtil.copyObjValue(reqDto, update, null, false);
        menuManageSV.updateAuthMenuById(update);
        this.clearMenuCache();//清空下缓存，否则更改的数据，不会立刻生效
    }

    @Override
    public void deleteAuthMenuById(AuthMenuReqDTO reqDto) throws BusinessException {
        if(reqDto==null||reqDto.getId()==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        menuManageSV.deleteAuthMenuById(reqDto);
        this.clearMenuCache();//清空下缓存，否则不会立刻生效
    }

    @Override
    public List<AuthMenuResDTO> generateEntireTree(AuthMenuReqDTO reqDto) throws BusinessException {
        List<AuthMenuResDTO> res = new ArrayList<AuthMenuResDTO>();
        try {
            res = menuManageSV.generateEntireTree(reqDto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "异常服务，dobbo调用失败", e);
            throw e;
        }
        return res;
    }

    @Override
    public List<AuthMenuResDTO> listAuthMenuByPrivList(AuthManageReqDTO reqDto)
            throws BusinessException {
        return menuManageSV.listAuthMenuByPrivList(reqDto);
    }

    @Override
    public Long clearMenuCache() throws BusinessException {
        return menuCacheSV.init();
    }

    
    
}

