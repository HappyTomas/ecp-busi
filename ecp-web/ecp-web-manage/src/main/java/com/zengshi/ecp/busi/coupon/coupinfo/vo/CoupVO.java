package com.zengshi.ecp.busi.coupon.coupinfo.vo;

import java.util.HashMap;
import java.util.Map;

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
public class CoupVO extends EcpBaseResponseVO{


    private static final long serialVersionUID = 1L;
    
    //优惠券基本信息
    @Valid
    private CoupInfoVO coupInfoVO;
    
    //满减规则
    private CoupFullLimitVO fullLimitVO;
    
    //使用规则  key 110-a-b-c  110表示规则代码  a b c表示具体业务key  限制编码  value 具体值
    private Map<String,String> useMap;
    
    //使用黑名单规则  key 限制编码  value 具体值  分类黑名单  商品黑名单  单品黑名单
    private Map<String,String> useBlackMap;
    
    //领取规则
    //传入格式 会员等级 01：startTime-endTime;
    private Map<String,String> receiveMap;
    
    //中间状态字段 存储 页面选择的条件 110 规则 是否选择 如果选择为1 否则为空或0
    private Map<String,String> ruleCodeMap=new HashMap<String,String>();
    
    //操作标记  edit view copy create
    private String doAction;
    
    private String coupCodeFlag;//优惠码标识
    
    public CoupInfoVO getCoupInfoVO() {
        return coupInfoVO;
    }
    public void setCoupInfoVO(CoupInfoVO coupInfoVO) {
        this.coupInfoVO = coupInfoVO;
    }
    public Map<String,String> getReceiveMap() {
        return receiveMap;
    }
    public void setReceiveMap(Map<String,String> receiveMap) {
        this.receiveMap = receiveMap;
    }
    public Map<String,String> getUseMap() {
        return useMap;
    }
    public void setUseMap(Map<String,String> useMap) {
        this.useMap = useMap;
    }
    public Map<String,String> getUseBlackMap() {
        return useBlackMap;
    }
    public void setUseBlackMap(Map<String,String> useBlackMap) {
        this.useBlackMap = useBlackMap;
    }
    public CoupFullLimitVO getFullLimitVO() {
        return fullLimitVO;
    }
    public void setFullLimitVO(CoupFullLimitVO fullLimitVO) {
        this.fullLimitVO = fullLimitVO;
    }
    public Map<String,String> getRuleCodeMap() {
        return ruleCodeMap;
    }
    public void setRuleCodeMap(Map<String,String> ruleCodeMap) {
        this.ruleCodeMap = ruleCodeMap;
    }
    public String getDoAction() {
        return doAction;
    }
    public void setDoAction(String doAction) {
        this.doAction = doAction;
    }
	public String getCoupCodeFlag() {
		return coupCodeFlag;
	}
	public void setCoupCodeFlag(String coupCodeFlag) {
		this.coupCodeFlag = coupCodeFlag;
	}
}
