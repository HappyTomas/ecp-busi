package com.zengshi.ecp.staff.service.cache.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dao.mapper.busi.CompanyInfoMapper;
import com.zengshi.ecp.staff.dao.model.CompanyInfo;
import com.zengshi.ecp.staff.dao.model.CompanyInfoCriteria;
import com.zengshi.ecp.staff.dubbo.dto.CompanyInfoResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.cache.interfaces.ICompanyCacheSV;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月31日下午7:25:11  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version  
 * @since JDK 1.7
 */
public class CompanyCacheSVImpl implements ICompanyCacheSV{

    @Resource
    private CompanyInfoMapper companyInfoMapper;
    
    public static final String MODULE = CompanyCacheSVImpl.class.getName();
    public void addCacheCompany(){
        Map<Long,CompanyInfoResDTO> map = new HashMap<Long, CompanyInfoResDTO>();
        CompanyInfoCriteria c =new CompanyInfoCriteria();
        List<CompanyInfo> list =  companyInfoMapper.selectByExample(c);
        for (CompanyInfo companyInfo : list) {
            CompanyInfoResDTO dto = new CompanyInfoResDTO();
            ObjectCopyUtil.copyObjValue(companyInfo, dto, null, false);
            map.put(companyInfo.getId(), dto);
        }
        CacheUtil.addItem(StaffConstants.companyInfo.COMPANY_CACHE_KEY, map);
    }


    @Override
    public void updateCacheCompany(Long id) throws BusinessException {
        CompanyInfo info  = new CompanyInfo();
        try {
           info =companyInfoMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CompanyInfoResDTO dto =new CompanyInfoResDTO();
        ObjectCopyUtil.copyObjValue(info, dto, null, false);
        HashMap<Long,CompanyInfoResDTO> map = (HashMap<Long, CompanyInfoResDTO>) CacheUtil.getItem(StaffConstants.companyInfo.COMPANY_CACHE_KEY);
        if (map == null) {
        	map = new HashMap<Long,CompanyInfoResDTO>();
        }
        map.put(id, dto);
        CacheUtil.addItem(StaffConstants.companyInfo.COMPANY_CACHE_KEY, map);
    }


    @Override
    public Map<Long, CompanyInfoResDTO> getCacheCompany() throws BusinessException {
        
        Map<Long, CompanyInfoResDTO> map = new HashMap<Long, CompanyInfoResDTO>();
        map =  (Map<Long, CompanyInfoResDTO>) CacheUtil.getItem(StaffConstants.companyInfo.COMPANY_CACHE_KEY);
        if(map==null||map.isEmpty()){
            map = new HashMap<Long, CompanyInfoResDTO>(); 
            LogUtil.info(MODULE, "=========为从缓存中取到企业信息，转数据库中获取==========");
            CompanyInfoCriteria c =new CompanyInfoCriteria();
            List<CompanyInfo> list =  companyInfoMapper.selectByExample(c);
            for (CompanyInfo companyInfo : list) {
                CompanyInfoResDTO dto = new CompanyInfoResDTO();
                ObjectCopyUtil.copyObjValue(companyInfo, dto, null, false);
                map.put(companyInfo.getId(), dto);
            }
            CacheUtil.addItem(StaffConstants.companyInfo.COMPANY_CACHE_KEY, map);
        }
        return map;
    }

    public CompanyInfoResDTO find(Long pCompanyId) throws BusinessException
    {
        @SuppressWarnings("unchecked")
        Map<Long, CompanyInfoResDTO> map = (Map<Long, CompanyInfoResDTO>) CacheUtil.getItem(StaffConstants.companyInfo.COMPANY_CACHE_KEY);
        
        if(!CollectionUtils.isEmpty(map))
        {
            return map.get(pCompanyId);
        }
        return null;
    }
    
}

