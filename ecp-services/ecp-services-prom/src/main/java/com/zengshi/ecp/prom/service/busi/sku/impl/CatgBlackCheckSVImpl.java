package com.zengshi.ecp.prom.service.busi.sku.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.goods.dubbo.constants.GdsOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.GdsCategoryCompareRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuLimitDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromQuerySV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-9-15 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class CatgBlackCheckSVImpl extends DefaultSkuCheckSVImpl {

    private static final String MODULE = CatgBlackCheckSVImpl.class.getName();
    
    @Resource
    private IPromQuerySV promQuerySV;
    
    @Resource
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;
    
    @Resource
    private IGdsInfoQueryRSV  gdsInfoQueryRSV;
    
    @Resource
    private GdsCatgSVImpl  gdsCatgSV;

    private Boolean ifcheck;
    
    public Boolean getIfcheck() {
        return ifcheck;
    }

    public void setIfcheck(Boolean ifcheck) {
        this.ifcheck = ifcheck;
    }
    /**
     * TODO分类信息是否需要验证.
     * @see com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuCheckSV#isCheck(com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO, com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO)
     * @param promInfoDTO
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public boolean isCheck(PromInfoDTO promInfoDTO,PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException {
        return getIfcheck();
    }
    
    /**
     * TODO促销 分类信息是否黑名单.
     * @see com.zengshi.ecp.prom.service.busi.sku.impl.DefaultSkuCheckSVImpl#check(com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO, com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO)
     * @param promInfoDTO
     * @param promRuleCheckDTO
     * @return
     * @author huangjx
     */
    @Override
    public boolean check(PromInfoDTO promInfoDTO,PromRuleCheckDTO promRuleCheckDTO) {
        
        //获得促销id对应的分类
        PromSkuLimitDTO promSkuLimitDTO=new PromSkuLimitDTO();
        promSkuLimitDTO.setPromId(promInfoDTO.getId());
        promSkuLimitDTO.setJoinType(PromConstants.PromSku.JOIN_TYPE_1);
        List<PromSkuLimitDTO> limitList=promQuerySV.listPromotionSkuLimit(promSkuLimitDTO);
        if(CollectionUtils.isEmpty(limitList)){
            //没有数据 表示非黑名单
            return Boolean.FALSE;
        }
        
        //调用接口获得skuid+gdsId对应的分类 
        GdsInfoReqDTO dto=new GdsInfoReqDTO();
        dto.setId(promRuleCheckDTO.getGdsId());
        
        GdsQueryOption[] gdsQuery=new GdsQueryOption[1];
        gdsQuery[0]=GdsOption.GdsQueryOption.CATG;//分类
        dto.setGdsQueryOptions(gdsQuery);
        
        GdsInfoRespDTO gdsDTO= gdsInfoQueryRSV.queryGdsInfoByOption(dto);
        //分类代码
        List<String> platformCatgList=new ArrayList<String>();
        if(gdsDTO!=null){
        	//下架 删除 为null
	        if(!CollectionUtils.isEmpty(gdsDTO.getPlatformCategory())){
	              for(GdsCategoryRespDTO p:gdsDTO.getPlatformCategory()){
	                  platformCatgList.add(p.getCatgCode());
	              }
	        }
        }
        //单品对应的catgcode是否在列表中 或者为列表中的分类的子类
        for(PromSkuLimitDTO skuLimitDTO:limitList){
                if(!StringUtil.isEmpty(skuLimitDTO.getCatgCode())){
                    //获得分类对应子类 比较
                      Integer result=  gdsCatgSV.compareCatg(platformCatgList,skuLimitDTO.getCatgCode());
                      if(GdsCategoryCompareRespDTO.RESULT_EQUAL.equals(result) || GdsCategoryCompareRespDTO.RESULT_LESS_THAN.equals(result) ){
                          return Boolean.TRUE;
                        //返回结果关系
                      }
                }
        }
        return Boolean.FALSE;
    }
}
