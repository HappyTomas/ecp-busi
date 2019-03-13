package com.zengshi.ecp.prom.service.busi.sku.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.goods.dubbo.dto.category.GdsCategoryCompareReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.GdsCategoryCompareRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-9-23 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class GdsCatgSVImpl {

    private static final String MODULE = GdsCatgSVImpl.class.getName();
    
    @Resource
    private IGdsCategoryRSV  gdsCategoryRSV;
    
    /**
     * 比较分类
     * @param sourceCatg
     * @param targetCatg
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public Integer compareCatg(String sourceCatg,String targetCatg) throws BusinessException {
        
      if(StringUtil.isEmpty(sourceCatg) || StringUtil.isEmpty(targetCatg)) {
          return GdsCategoryCompareRespDTO.RESULT_ERROR;
      }
      GdsCategoryCompareReqDTO  compareReqDTO= new GdsCategoryCompareReqDTO();
        compareReqDTO.setSourceCode(sourceCatg);
        compareReqDTO.setTargetCode(targetCatg);
        GdsCategoryCompareRespDTO dto=gdsCategoryRSV.executeCompare(compareReqDTO);
        return dto.getResult();
    }
    
    /**
     * 源分类集合 和目标分类比较
     * @param sourceCatgs
     * @param targetCatg
     * @return
     * @author huangjx
     */
    public Integer compareCatg(List<String> sourceCatgs,String targetCatg){
        
        Integer result=GdsCategoryCompareRespDTO.RESULT_ERROR;
        if(CollectionUtils.isEmpty(sourceCatgs) || StringUtil.isEmpty(targetCatg)) {
            return result;
        }
       //验证比较
        for(String sourceCatg:sourceCatgs){
            result=this.compareCatg(sourceCatg, targetCatg);
            if(GdsCategoryCompareRespDTO.RESULT_EQUAL.equals(result) || GdsCategoryCompareRespDTO.RESULT_GREAT_THAN.equals(result) ){
                break;
            }
        }
        return result;
    }
}
