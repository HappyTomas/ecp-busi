package com.zengshi.ecp.order.service.busi.impl.pay;

import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayWay;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 支付通道枚举类 <br>
 * Date:2015年10月15日上午10:59:53 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version
 * @since JDK 1.6
 */
public enum PayWayEnum {
    ABCPayWay("9004","abcPayWay",ABCPayWay.class),
    Alipay("9003","alipay",Alipay.class),
    Hongpay("9002","hongpayPlatform",HongpayPlatform.class),
    WeChatPay("9006","wechatpay",WeChatPay.class),
    WeiXinPay("9007","weixinpay",WeiXinPay.class),
    WeiXinAppPay("9008","weixinapppay",WeiXinAppPay.class);
    private PayWayEnum(String payWay, String beanName, Class<? extends IPayWay> payWayImplClass) {
        this.payWay = payWay;
        this.beanName = beanName;
//        this.payWayImplClassName = payWayImplClassName;
        this.payWayImplClass = payWayImplClass;
    }

    private String payWay;
    
    private String beanName;

    private Class<? extends IPayWay> payWayImplClass;

    public String getBeanName() {
        return beanName;
    }
    
    public String getPayWay() {
        return payWay;
    }
    
    public Class<? extends IPayWay> getPayWayImplClass() {
        return payWayImplClass;
    }

    public String toString() {
        return this.name() + ":" + this.getPayWay();
    }

    public static PayWayEnum getByPayWay(String payWay) {
        if (payWay == null) {
            throw new IllegalArgumentException("参数不能为空");
        }
        for (PayWayEnum e : values()) {
            if (e.getPayWay().equalsIgnoreCase(payWay)) {
                return e;
            }
        }
        throw new IllegalArgumentException("不存在的支付通道：" + payWay);
    }

    public static PayWayEnum getByImplClass(Class<? extends IPayWay> c) {
        if (c == null) {
            throw new IllegalArgumentException("参数不能为空");
        }
        for (PayWayEnum e : values()) {
            if (e.getPayWayImplClass()==c) {
                return e;
            }
        }
        throw new IllegalArgumentException("不存在的支付通道实现类：" + c);
    }

    public static String getPayWayByImplClass(Class<? extends IPayWay> c) {
        if (c == null) {
            throw new IllegalArgumentException("参数不能为空");
        }
        PayWayEnum p = getByImplClass(c);
        if (p == null) {
            throw new IllegalArgumentException("class不能解析");
        }
        return p.getPayWay();
    }

}