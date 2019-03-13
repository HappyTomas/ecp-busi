package com.zengshi.ecp.prom.service.busi.sku.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuCheckSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class SkuCheckSVImpl {

    private static final String MODULE = SkuCheckSVImpl.class.getName();

    @Resource(name = "skuJoinCheckSV")
    private IPromSkuCheckSV skuJoinCheckSV;

    @Resource(name = "skuBlackCheckSV")
    private IPromSkuCheckSV skuBlackCheckSV;
    
    @Resource(name = "catgBlackCheckSV")
    private IPromSkuCheckSV catgBlackCheckSV;

    @Resource(name = "skuBuyStockCheckSV")
    private IPromSkuCheckSV skuBuyStockCheckSV;

    /**
     * 促销商品验证
     * @param promInfoDTO
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public boolean gdsCheck(PromInfoDTO promInfoDTO,PromRuleCheckDTO promRuleCheckDTO) throws BusinessException {
        
        boolean chkValue = false;
     /*   // 是否展示 0 不展示
        if (PromConstants.PromInfo.IF_SHOW_0.equals(promInfoDTO.getIfShow())) {
            
            if(PromConstants.PromSys.IF_THROWS.equals(promRuleCheckDTO.getIfThrows())){
                //促销已过期
                throw new BusinessException("prom.400099");
            }
            return chkValue;
        }*/
        //非该店铺商品
        if(!promInfoDTO.getShopId().equals(promRuleCheckDTO.getShopId())){
            
            if(PromConstants.PromSys.IF_THROWS.equals(promRuleCheckDTO.getIfThrows())){
                //非该店铺商品
                throw new BusinessException("prom.400100");
            }
            return chkValue;
        }
        // 2 判断1全场 参与 还是0部分参与  
        if (PromConstants.PromInfo.JOIN_RANGE_1.equals(promInfoDTO.getJoinRange())) {
            if (PromConstants.PromInfo.IF_BLACKLIST_1.equals(promInfoDTO.getIfBlacklist())) {
                // 3 全场参与 判断是否商品黑名单
                if (skuBlackCheckSV.isCheck(promInfoDTO,promRuleCheckDTO)) {
                    chkValue = skuBlackCheckSV.check(promInfoDTO,promRuleCheckDTO);
                    // 4是否黑名单
                    if (chkValue) {
                        if(PromConstants.PromSys.IF_THROWS.equals(promRuleCheckDTO.getIfThrows())){
                            // 黑名单商品
                            String[] key=new String[1];
                            key[0]=promRuleCheckDTO.getGdsName();
                            throw new BusinessException("prom.400101",key);
                        }
                        return Boolean.FALSE;
                    }
                }
            }
            if (PromConstants.PromInfo.IF_BLACKLIST_1.equals(promInfoDTO.getIfBlacklist())) {
                // 3 全场参与 判断是否分类黑名单
                if (catgBlackCheckSV.isCheck(promInfoDTO,promRuleCheckDTO)) {
                    
                    chkValue = catgBlackCheckSV.check(promInfoDTO,promRuleCheckDTO);
                    // 4是否黑名单
                    if (chkValue) {
                        if(PromConstants.PromSys.IF_THROWS.equals(promRuleCheckDTO.getIfThrows())){
                            // 黑名单商品
                            String[] key=new String[1];
                            key[0]=promRuleCheckDTO.getGdsName();
                            throw new BusinessException("prom.400101",key);
                        }
                        return Boolean.FALSE;
                    }
                }
            }
        

        } else {
            // 5 部分参与
            //可以不用验证 原因 调用此代码 已经跑过类似服务
       /*     if (skuJoinCheckSV.isCheck(promInfoDTO,promRuleCheckDTO)) {
                chkValue = skuJoinCheckSV.check(promInfoDTO,promRuleCheckDTO);
                if (!chkValue) {
                      return Boolean.FALSE;
                }
            }*/

        }
        // 6购买量
        if (skuBuyStockCheckSV.isCheck(promInfoDTO,promRuleCheckDTO)) {
            chkValue = skuBuyStockCheckSV.check(promInfoDTO,promRuleCheckDTO);
            // 返回true表示超过库存 不能参加活动
            if (!chkValue) {
                if(PromConstants.PromSys.IF_THROWS.equals(promRuleCheckDTO.getIfThrows())){
                    // 超过库存 不能参加活动
                    String[] key=new String[1];
                    key[0]=promRuleCheckDTO.getGdsName();
                    throw new BusinessException("prom.400102",key);
                }
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;

    }

}
