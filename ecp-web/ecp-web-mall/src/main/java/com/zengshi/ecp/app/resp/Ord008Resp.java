package com.zengshi.ecp.app.resp;

import java.util.List;
import java.util.Map;

import com.zengshi.ecp.general.order.dto.CoupCheckBeanRespDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAddrResDTO;
import com.zengshi.butterfly.app.model.IBody;

public class Ord008Resp extends IBody {

    /** 
     * redisKey:数据缓存中的Key. 
     * @since JDK 1.6 
     */ 
    private String redisKey;
    
    /** 
     * agentMoneys:提交订单时的合计价格. 
     * @since JDK 1.6 
     */ 
    private Long agentMoneys;
    
    /** 
     * orderMoneys:总金额. 
     * @since JDK 1.6 
     */ 
    private Long orderMoneys;
    
    /** 
     * realExpressFees:运费. 
     * @since JDK 1.6 
     */ 
    private Long realExpressFees;
    
    /** 
     * allMoney:应付总额. 
     * @since JDK 1.6 
     */ 
    private Long allMoney;
    
    /** 
     * orderAmounts:总数量. 
     * @since JDK 1.6 
     */ 
    private Long orderAmounts;
    
    /** 
     * discountMoneys:总优惠金额. 
     * @since JDK 1.6 
     */ 
    private Long discountMoneys;
    
    /** 
     * orderMoneyMap:订单金额. 
     * @since JDK 1.6 
     */ 
    private Map<Long,Long> orderMoneyMap;
    
    /** 
     * realExpressFeesMap:店铺运费. 
     * @since JDK 1.6 
     */ 
    private Map<Long,Long> realExpressFeesMap;
    
    /** 
     * discountPriceMoneyMap:购物车优惠金额. 
     * @since JDK 1.6 
     */ 
    private Map<Long,Long> discountPriceMoneyMap;
    
    /** 
     * deliverTypes:配送方式. 
     * @since JDK 1.6 
     */ 
    private Map<Long,String> deliverTypes;
    
    /** 
     * payList:支付方式. 
     * @since JDK 1.6 
     */ 
    private Map<String,String> payList;
    
    /** 
     * addrs:收货地址. 
     * @since JDK 1.6 
     */ 
    private List<CustAddrResDTO> addrs;
    
    /** 
     * Ord00801Resps:优惠券和现金账户相关信息. 
     * @since JDK 1.6 
     */ 
    private List<Ord00801Resp> Ord00801Resps;
    
    /**
     * 是否器用优惠码，1：启用，其他：不启用
     */
    private String ifcoupCodeOpen="0";
    
    /** 
     * invoceConList:发票内容. 
     * @since JDK 1.6 
     */ 
    private List<String> invoiceConList;    

    public Map<Long, Long> getOrderMoneyMap() {
        return orderMoneyMap;
    }

    public void setOrderMoneyMap(Map<Long, Long> orderMoneyMap) {
        this.orderMoneyMap = orderMoneyMap;
    }

    public List<Ord00801Resp> getOrd00801Resps() {
        return Ord00801Resps;
    }

    public void setOrd00801Resps(List<Ord00801Resp> ord00801Resps) {
        Ord00801Resps = ord00801Resps;
    }

    public Map<Long, Long> getRealExpressFeesMap() {
        return realExpressFeesMap;
    }

    public void setRealExpressFeesMap(Map<Long, Long> realExpressFeesMap) {
        this.realExpressFeesMap = realExpressFeesMap;
    }


    public List<CustAddrResDTO> getAddrs() {
        return addrs;
    }

    public void setAddrs(List<CustAddrResDTO> addrs) {
        this.addrs = addrs;
    }


    public Long getAgentMoneys() {
        return agentMoneys;
    }

    public void setAgentMoneys(Long agentMoneys) {
        this.agentMoneys = agentMoneys;
    }

    public Long getOrderMoneys() {
        return orderMoneys;
    }

    public void setOrderMoneys(Long orderMoneys) {
        this.orderMoneys = orderMoneys;
    }

    public Long getRealExpressFees() {
        return realExpressFees;
    }

    public void setRealExpressFees(Long realExpressFees) {
        this.realExpressFees = realExpressFees;
    }

    public Long getAllMoney() {
        return allMoney;
    }

    public void setAllMoney(Long allMoney) {
        this.allMoney = allMoney;
    }

    public Long getOrderAmounts() {
        return orderAmounts;
    }

    public void setOrderAmounts(Long orderAmounts) {
        this.orderAmounts = orderAmounts;
    }

    public Long getDiscountMoneys() {
        return discountMoneys;
    }

    public void setDiscountMoneys(Long discountMoneys) {
        this.discountMoneys = discountMoneys;
    }

    public Map<Long, Long> getDiscountPriceMoneyMap() {
        return discountPriceMoneyMap;
    }

    public void setDiscountPriceMoneyMap(Map<Long, Long> discountPriceMoneyMap) {
        this.discountPriceMoneyMap = discountPriceMoneyMap;
    }

    public Map<Long, String> getDeliverTypes() {
        return deliverTypes;
    }

    public void setDeliverTypes(Map<Long, String> deliverTypes) {
        this.deliverTypes = deliverTypes;
    }

    public Map<String, String> getPayList() {
        return payList;
    }

    public void setPayList(Map<String, String> payList) {
        this.payList = payList;
    }

    public String getRedisKey() {
        return redisKey;
    }

    public void setRedisKey(String redisKey) {
        this.redisKey = redisKey;
    }

	public List<String> getInvoiceConList() {
		return invoiceConList;
	}

	public void setInvoiceConList(List<String> invoiceConList) {
		this.invoiceConList = invoiceConList;
	}

	public String getIfcoupCodeOpen() {
		return ifcoupCodeOpen;
	}

	public void setIfcoupCodeOpen(String ifcoupCodeOpen) {
		this.ifcoupCodeOpen = ifcoupCodeOpen;
	}



    
}

