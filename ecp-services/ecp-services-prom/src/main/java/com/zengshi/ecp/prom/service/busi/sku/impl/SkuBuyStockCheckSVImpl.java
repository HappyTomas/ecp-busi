package com.zengshi.ecp.prom.service.busi.sku.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.prom.dao.mapper.busi.PromStockLimitMapper;
import com.zengshi.ecp.prom.dao.model.PromStockLimit;
import com.zengshi.ecp.prom.dao.model.PromStockLimitKey;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class SkuBuyStockCheckSVImpl extends DefaultSkuCheckSVImpl {

    private static final String MODULE = SkuBuyStockCheckSVImpl.class.getName();

    @Resource
    private PromStockLimitMapper promStockLimitMapper;
   
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
     * TODO促销信息验证 是否超过购买量量限制
     * @see com.zengshi.ecp.prom.service.busi.sku.impl.DefaultSkuCheckSVImpl#check(com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO, com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO)
     * @param promInfoDTO
     * @param promRuleCheckDTO
     * @return
     * @author huangjx
     */
    @Override
    public boolean check(PromInfoDTO promInfoDTO,PromRuleCheckDTO promRuleCheckDTO) {
        // t_prom_stock_limit 限制
        PromStockLimitKey key=new PromStockLimitKey();
        key.setGdsId(promRuleCheckDTO.getGdsId());
        key.setPromId(promInfoDTO.getId());
        key.setSkuId(promRuleCheckDTO.getSkuId());//待定 某些情况下下 可为空 具体待定
        PromStockLimit promStockLimit=promStockLimitMapper.selectByPrimaryKey(key);
        //为空 没有限制
        if(promStockLimit==null){
            return Boolean.TRUE;
        }
        if(StringUtil.isEmpty(promRuleCheckDTO.getBuyCnt())){
            return promStockLimit.getRemaindCnt()>=0;
        }
        //剩余量 和购买量比较
        if(promStockLimit.getRemaindCnt().compareTo(new Long(promRuleCheckDTO.getBuyCnt()))>=0){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
