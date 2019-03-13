package com.zengshi.ecp.staff.service.cache.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dubbo.dto.AuthMenuResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.cache.interfaces.IMenuCacheSV;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IMenuManageSV;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 菜单缓存<br>
 * Date:2015年10月22日上午9:48:25  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public class MenuCacheSVImpl implements IMenuCacheSV {
    
    public static final String MODULE = MenuCacheSVImpl.class.getName();
    
    @Resource
    private IMenuManageSV menuManageSV;

    @Override
    public Long addRecord(AuthMenuResDTO cache) throws BusinessException {
        if(cache==null){
            return 0L;
        }
        String itemKey = StaffConstants.PublicParam.SYS_SUB_SYSTEM_PARAMKEY+"_"+cache.getSysCode();
        @SuppressWarnings("unchecked")
        List<AuthMenuResDTO> listMenuDto = (List<AuthMenuResDTO>) CacheUtil.getItem(itemKey);
        int sizeBefore = listMenuDto.size();
        if(CollectionUtils.isNotEmpty(listMenuDto)){
            listMenuDto.add(cache);
            CacheUtil.addItem(itemKey,listMenuDto);
            return (long) (listMenuDto.size()-sizeBefore);
        }else{
            List<String> sysCodes = new ArrayList<String>();
            sysCodes.add(cache.getSysCode());
            List<AuthMenuResDTO> listMenuDtoSub = menuManageSV.listAuthMenuFromDB(sysCodes);
            CacheUtil.addItem(itemKey, listMenuDtoSub);
        }
        return 0L;
    }

    @Override
    public Long removeRecord(AuthMenuResDTO cache) throws BusinessException {
        if(cache==null){
            return 0L;
        }
        String itemKey = StaffConstants.PublicParam.SYS_SUB_SYSTEM_PARAMKEY+"_"+cache.getSysCode();
        @SuppressWarnings("unchecked")
        List<AuthMenuResDTO> listMenuDto = (List<AuthMenuResDTO>) CacheUtil.getItem(itemKey);
        int sizeBefore = listMenuDto.size();
        if(CollectionUtils.isNotEmpty(listMenuDto)){
            for (AuthMenuResDTO authMenuResDTO : listMenuDto) {
                if(authMenuResDTO!=null&&authMenuResDTO.getId()!=null&&authMenuResDTO.getId().equals(cache.getId())){
                    listMenuDto.remove(authMenuResDTO);
                    break;
                }
            }
            CacheUtil.addItem(itemKey,listMenuDto);
            return (long) (sizeBefore-listMenuDto.size());
        }
        return 0L;
    }

    @Override
    public Long clear() throws BusinessException {
        LogUtil.info(MODULE, "============清理缓存 begin==============");
        Long i = new Long(0);
        List<BaseParamDTO> listSysSubSystemParam = BaseParamUtil.fetchParamList(StaffConstants.PublicParam.SYS_SUB_SYSTEM_PARAMKEY);
        if(CollectionUtils.isEmpty(listSysSubSystemParam)){
        	return i;
        }
        
        for (BaseParamDTO baseParamDTO : listSysSubSystemParam) {
            @SuppressWarnings("unchecked")
            List<AuthMenuResDTO> list = (List<AuthMenuResDTO>) CacheUtil.getItem(
                    StaffConstants.PublicParam.SYS_SUB_SYSTEM_PARAMKEY+"_"+baseParamDTO.getSpCode());
            i += list.size();
            CacheUtil.delItem(
                    StaffConstants.PublicParam.SYS_SUB_SYSTEM_PARAMKEY+"_"+baseParamDTO.getSpCode());
            LogUtil.info(MODULE, "============清理缓存 key:"+StaffConstants.PublicParam.SYS_SUB_SYSTEM_PARAMKEY
                    +"_"+baseParamDTO.getSpCode()+" ("+baseParamDTO.getSpValue()+")==============");
        }
        
        LogUtil.info(MODULE, "============清理缓存 end==============");
        return i;
    }

    @Override
    public Long init() throws BusinessException {
        LogUtil.info(MODULE, "============初始化缓存 begin==============");
        Long i = new Long(0);
        List<BaseParamDTO> listSysSubSystemParam = BaseParamUtil.fetchParamList(StaffConstants.PublicParam.SYS_SUB_SYSTEM_PARAMKEY);
        if(CollectionUtils.isEmpty(listSysSubSystemParam)){
        	return i;
        }
        
        for (BaseParamDTO baseParamDTO : listSysSubSystemParam) {
            List<String> sysCodes = new ArrayList<String>();
            sysCodes.add(baseParamDTO.getSpCode());
            List<AuthMenuResDTO> listMenuDto = menuManageSV.listAuthMenuFromDB(sysCodes);
            i = i.longValue()+listMenuDto.size();
            CacheUtil.addItem(StaffConstants.PublicParam.SYS_SUB_SYSTEM_PARAMKEY+"_"+baseParamDTO.getSpCode(), listMenuDto);
            LogUtil.info(MODULE, "============初始化缓存 key:"+StaffConstants.PublicParam.SYS_SUB_SYSTEM_PARAMKEY
                    +"_"+baseParamDTO.getSpCode()+" ("+baseParamDTO.getSpValue()+")==============");
        }
        
        LogUtil.info(MODULE, "============初始化缓存 end==============");
        return i;
    }

    @Override
    public Long updateRecord(AuthMenuResDTO cache) throws BusinessException {
        if(cache==null){
            return 0L;
        }
        String itemKey = StaffConstants.PublicParam.SYS_SUB_SYSTEM_PARAMKEY+"_"+cache.getSysCode();
        @SuppressWarnings("unchecked")
        List<AuthMenuResDTO> listMenuDto = (List<AuthMenuResDTO>) CacheUtil.getItem(itemKey);
        int beUpdated = 0;
        if(CollectionUtils.isNotEmpty(listMenuDto)){
            for (AuthMenuResDTO authMenuResDTO : listMenuDto) {
                if(authMenuResDTO!=null&&authMenuResDTO.getId()!=null&&authMenuResDTO.getId().equals(cache.getId())){
                    listMenuDto.remove(authMenuResDTO);
                    listMenuDto.add(cache);
                    beUpdated++;//更新记录
                    break;
                }
            }
            CacheUtil.addItem(itemKey,listMenuDto);
            return (long) beUpdated;
        }
        return 0L;
    }


}

