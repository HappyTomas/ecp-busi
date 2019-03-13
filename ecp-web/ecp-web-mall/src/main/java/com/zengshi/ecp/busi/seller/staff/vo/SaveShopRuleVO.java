package com.zengshi.ecp.busi.seller.staff.vo;

import java.util.List;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016年4月27日下午5:53:39  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author chenyz
 * @version  
 * @since JDK 1.6
 */
public class SaveShopRuleVO extends EcpBasePageReqVO {
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;

    private Long shopId;
    
    private List<ShopVipRuleVO> ruleArr;//会员等级设置表格数据
    
    

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public List<ShopVipRuleVO> getRuleArr() {
        return ruleArr;
    }

    public void setRuleArr(List<ShopVipRuleVO> ruleArr) {
        this.ruleArr = ruleArr;
    }
    
}

