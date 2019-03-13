package com.zengshi.ecp.prom.service.busi.sku.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuLimitDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromQuerySV;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class SkuBlackCheckSVImpl extends DefaultSkuCheckSVImpl {

    private static final String MODULE = SkuBlackCheckSVImpl.class.getName();

    @Resource
    private IPromQuerySV promQuerySV;
 
    private Boolean ifcheck;
    
    public Boolean getIfcheck() {
        return ifcheck;
    }

    public void setIfcheck(Boolean ifcheck) {
        this.ifcheck = ifcheck;
    }
    /**
     * TODO销 商品信息是否需要验证.
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
     * TODO促销 商品信息是否需要验证.
     * @see com.zengshi.ecp.prom.service.busi.sku.impl.DefaultSkuCheckSVImpl#check(com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO, com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO)
     * @param promInfoDTO
     * @param promRuleCheckDTO
     * @return
     * @author huangjx
     */
    @Override
    public boolean check(PromInfoDTO promInfoDTO,PromRuleCheckDTO promRuleCheckDTO) {
        // 如果skuId 为空 判断过滤到gdsId，如果skuId非空 需要gdsid+skuId一起判断
        return checkBlack(promRuleCheckDTO.getShopId(),promRuleCheckDTO.getGdsId(),promRuleCheckDTO.getSkuId(),promInfoDTO);
    }

    /**
     * 验证黑名单
     * 
     * @param shopId
     * @param gdsId
     * @param skuId
     * @param promInfoDTO
     * @return
     * @author huangjx
     */
    private boolean checkBlack(Long shopId, Long gdsId, Long skuId,
            PromInfoDTO promInfoDTO) {

        //获得促销id对应的分类
        PromSkuLimitDTO promSkuLimitDTO=new PromSkuLimitDTO();
        promSkuLimitDTO.setPromId(promInfoDTO.getId());
        promSkuLimitDTO.setGdsId(gdsId);
        promSkuLimitDTO.setSkuId(skuId);
        promSkuLimitDTO.setJoinType(PromConstants.PromSku.JOIN_TYPE_2);
        List<PromSkuLimitDTO> limitList=promQuerySV.listPromotionSkuLimit(promSkuLimitDTO);
        if(CollectionUtils.isEmpty(limitList)){
            //没有数据 表示非黑名单
            return Boolean.FALSE;
        }
        
        /*
        PromSkuLimitCriteria example = new PromSkuLimitCriteria();
        Criteria cr = example.createCriteria();
        cr.andGdsIdEqualTo(gdsId);
        //sku 非空 加入
        if (skuId != null) {
            cr.andSkuIdEqualTo(skuId);
        }
        cr.andPromIdEqualTo(promInfoDTO.getId());
        //查询
        List<PromSkuLimit> l=promSkuLimitMapper.selectByExample(example);
        
        if(CollectionUtils.isEmpty(l)){
            //没有数据 表示非黑名单
            return Boolean.FALSE;
        }*/
        return Boolean.TRUE;
    }

}
