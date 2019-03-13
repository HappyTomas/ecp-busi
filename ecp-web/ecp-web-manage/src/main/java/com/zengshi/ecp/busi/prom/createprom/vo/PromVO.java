package com.zengshi.ecp.busi.prom.createprom.vo;

import java.util.HashMap;
import javax.validation.Valid;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-21 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromVO extends EcpBaseResponseVO{


    private static final long serialVersionUID = 1L;
    //促销基本信息
    @Valid
    private PromInfoVO promInfoVO;
    //促销规则
    private PromRuleVO promRuleVO;
    //优惠规则
    private HashMap discountMap;
    
    //商品列表
    private HashMap gdsMap;
    
    //黑名单商品列表
    private HashMap gdsBlackMap;
    
    //赠品列表
    private HashMap  giftMap;// key:赠品编码,value:商品编码-单品编码-赠送数量
    
    //操作标记  edit view copy create
    private String doAction;
    
    //搭配商品列表
    private HashMap matchMap;
    
    //分类列表
    private HashMap catgMap;
    
    //分类列表黑名单
    private HashMap catgLimitMap;
    
    public PromInfoVO getPromInfoVO() {
        return promInfoVO;
    }
    public void setPromInfoVO(PromInfoVO promInfoVO) {
        this.promInfoVO = promInfoVO;
    }
    public PromRuleVO getPromRuleVO() {
        return promRuleVO;
    }
    public void setPromRuleVO(PromRuleVO promRuleVO) {
        this.promRuleVO = promRuleVO;
    }
    public HashMap getDiscountMap() {
        return discountMap;
    }
    public void setDiscountMap(HashMap discountMap) {
        this.discountMap = discountMap;
    }

    public HashMap getGdsMap() {
        return gdsMap;
    }
    public void setGdsMap(HashMap gdsMap) {
        this.gdsMap = gdsMap;
    }
    public HashMap getGdsBlackMap() {
        return gdsBlackMap;
    }
    public void setGdsBlackMap(HashMap gdsBlackMap) {
        this.gdsBlackMap = gdsBlackMap;
    }
    public HashMap getGiftMap() {
        return giftMap;
    }
    public void setGiftMap(HashMap giftMap) {
        this.giftMap = giftMap;
    }
    public String getDoAction() {
        return doAction;
    }
    public void setDoAction(String doAction) {
        this.doAction = doAction;
    }
    public HashMap getMatchMap() {
        return matchMap;
    }
    public void setMatchMap(HashMap matchMap) {
        this.matchMap = matchMap;
    }
    public HashMap getCatgMap() {
        return catgMap;
    }
    public void setCatgMap(HashMap catgMap) {
        this.catgMap = catgMap;
    }
    public HashMap getCatgLimitMap() {
        return catgLimitMap;
    }
    public void setCatgLimitMap(HashMap catgLimitMap) {
        this.catgLimitMap = catgLimitMap;
    }
}
