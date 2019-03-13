package com.zengshi.ecp.prom.service.busi.sku.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.prom.dao.mapper.busi.PromSkuMapper;
import com.zengshi.ecp.prom.dao.model.PromSku;
import com.zengshi.ecp.prom.dao.model.PromSkuCriteria;
import com.zengshi.ecp.prom.dao.model.PromSkuCriteria.Criteria;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class SkuJoinCheckSVImpl extends DefaultSkuCheckSVImpl {

    private static final String MODULE = SkuJoinCheckSVImpl.class.getName();
    
    @Resource
    private PromSkuMapper promSkuMapper;
    
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
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public boolean check(PromInfoDTO promInfoDTO,PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException {
        return skuCheckJoin(promRuleCheckDTO.getShopId(),promRuleCheckDTO.getGdsId(),promRuleCheckDTO.getSkuId(),promInfoDTO);
    }
    
    /**
     * 是否参与促销单品
     * @param shopId
     * @param gdsId
     * @param skuId
     * @param promInfoDTO
     * @return
     * @author huangjx
     */
    private boolean skuCheckJoin(Long shopId, Long gdsId, Long skuId, PromInfoDTO promInfoDTO){
        //查找组织
        PromSkuCriteria example =new PromSkuCriteria();
        Criteria cr=example.createCriteria();
        cr.andGdsIdEqualTo(gdsId);
        cr.andPromIdEqualTo(promInfoDTO.getId());
        if(skuId!=null){
            cr.andSkuIdEqualTo(skuId);
        }
        //查询
        List<PromSku> l=promSkuMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(l)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
