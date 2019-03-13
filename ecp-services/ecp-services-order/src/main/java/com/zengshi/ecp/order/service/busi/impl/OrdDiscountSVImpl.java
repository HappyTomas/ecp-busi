package com.zengshi.ecp.order.service.busi.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.busi.OrdDiscountMapper;
import com.zengshi.ecp.order.dao.mapper.busi.OrdPromMapper;
import com.zengshi.ecp.order.dao.mapper.busi.manual.OrdDiscountUalMapper;
import com.zengshi.ecp.order.dao.model.OrdDiscount;
import com.zengshi.ecp.order.dao.model.OrdDiscountCriteria;
import com.zengshi.ecp.order.dao.model.OrdProm;
import com.zengshi.ecp.order.dao.model.OrdPromCriteria;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsDiscount;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdDiscountSV;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.sequence.Sequence;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月17日下午4:27:03 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwei
 * @version
 * @since JDK 1.6
 */
public class OrdDiscountSVImpl implements IOrdDiscountSV {

    @Resource
    private OrdDiscountMapper ordDiscountMapper;
    
    @Resource
    private OrdDiscountUalMapper ordDisconutUalMapper;

    @Resource
    private OrdPromMapper ordPromMapper;

    @Resource(name = "seq_ord_discount")
    private Sequence seqOrdDiscount;

    @Override
    public void saveOrdDiscount(OrdDiscount info) {
        info.setId(this.seqOrdDiscount.nextValue());
        this.ordDiscountMapper.insert(info);
    }

    @Override
    public SOrderDetailsDiscount queryOrderDetailsDiscount(String orderId) {
        SOrderDetailsDiscount sod = new SOrderDetailsDiscount();

        //商品总优惠统计
        OrdDiscountCriteria all = new OrdDiscountCriteria();
        all.createCriteria().andOrderIdEqualTo(orderId);
        Long discountPrices = this.ordDisconutUalMapper.sumDiscountPriceByOrderId(all);
        if(discountPrices == null){
            discountPrices = 0l;
        }
        sod.setDiscountPriceSum(discountPrices);

        //商品优惠分类折扣，无promId
        OrdDiscountCriteria odc_gds_prom = new OrdDiscountCriteria();
        odc_gds_prom.createCriteria().andOrderIdEqualTo(orderId).andDiscountTypeEqualTo(OrdConstants.DiscountType.TYPE_GDS_CODE);
        Long discountGdsProm = this.ordDisconutUalMapper.sumDiscountPriceByOrderId(odc_gds_prom);
        if(discountGdsProm == null){
            discountGdsProm = 0l;
        }
        sod.setDiscountGdsProm(discountGdsProm);

        //商品优惠包括（分类折扣，有promId，子订单优惠）
        OrdDiscountCriteria odc_sub_prom = new OrdDiscountCriteria();
        odc_sub_prom.createCriteria().andOrderIdEqualTo(orderId).andDiscountTypeEqualTo(OrdConstants.DiscountType.TYPE_SUB_CODE);
        Long discountSubProm = this.ordDisconutUalMapper.sumDiscountPriceByOrderId(odc_sub_prom);
        if(discountSubProm == null){
            discountSubProm = 0l;
        }
        //由于主服务保存有误，特意获取此项数据
        OrdDiscountCriteria odc_sub_prom_01 = new OrdDiscountCriteria();
        odc_sub_prom_01.createCriteria().andOrderIdEqualTo(orderId).andDiscountTypeEqualTo(OrdConstants.DiscountType.TYPE_ORDER_CODE).andOrderSubIdIsNotNull();
        Long discountSubProm01 = this.ordDisconutUalMapper.sumDiscountPriceByOrderId(odc_sub_prom_01);
        if(discountSubProm01 == null){
            discountSubProm01 = 0l;
        }
        sod.setDiscountSubProm(discountSubProm01+discountSubProm);

        //订单级别优惠统计
        OrdDiscountCriteria odc_ord_prom = new OrdDiscountCriteria();
        odc_ord_prom.createCriteria().andOrderIdEqualTo(orderId).andDiscountTypeEqualTo(OrdConstants.DiscountType.TYPE_ORDER_CODE).andOrderSubIdIsNull();
        Long discountOrdProm = this.ordDisconutUalMapper.sumDiscountPriceByOrderId(odc_ord_prom);
        if(discountOrdProm == null){
            discountOrdProm = 0l;
        }
        sod.setDiscountOrderSum(discountOrdProm);

        //资金账户优惠
        OrdDiscountCriteria odc_acct_prom = new OrdDiscountCriteria();
        odc_acct_prom.createCriteria().andOrderIdEqualTo(orderId).andDiscountTypeEqualTo(OrdConstants.DiscountType.TYPE_CAPITAL_CODE);
        Long discountAcctProm = this.ordDisconutUalMapper.sumDiscountPriceByOrderId(odc_acct_prom);
        if(discountAcctProm == null){
            discountAcctProm = 0l;
        }
        sod.setDiscountAcctSum(discountAcctProm);

        //优惠券优惠
        OrdDiscountCriteria odc_coup_prom = new OrdDiscountCriteria();
        odc_coup_prom.createCriteria().andOrderIdEqualTo(orderId).andDiscountTypeEqualTo(OrdConstants.DiscountType.TYPE_COUP_CODE);
        Long discountCoupProm = this.ordDisconutUalMapper.sumDiscountPriceByOrderId(odc_coup_prom);
        if(discountCoupProm == null){
            discountCoupProm = 0l;
        }
        sod.setDiscountCoupSum(discountCoupProm);
        
        //优惠码优惠
        OrdDiscountCriteria odc_coupcode_prom = new OrdDiscountCriteria();
        odc_coupcode_prom.createCriteria().andOrderIdEqualTo(orderId).andDiscountTypeEqualTo(OrdConstants.DiscountType.TYPE_COUPCODE_CODE);
        Long discountCoupCodeProm = this.ordDisconutUalMapper.sumDiscountPriceByOrderId(odc_coupcode_prom);
        if(discountCoupCodeProm == null){
            discountCoupCodeProm = 0l;
        }
        sod.setDiscountCoupCodeSum(discountCoupCodeProm);

        return sod;
    }

    @Override
    public boolean queryOrdSubIsDiscount(String orderId, String orderSubId) {
        boolean flag = false;
        OrdPromCriteria osc = new OrdPromCriteria();
        OrdPromCriteria.Criteria ca =osc.createCriteria();
        ca.andOrderIdEqualTo(orderId).andOrderSubIdEqualTo(orderSubId);

        List<OrdProm> ordProms = this.ordPromMapper.selectByExample(osc);
        OrdProm ordProm = null;
        if(CollectionUtils.isNotEmpty(ordProms)){
            ordProm = ordProms.get(0);
        }

        if(ordProm!=null && ordProm.getPromId()!= -1l && ordProm.getPromId()!= -2l){
            flag = true;
        }
        return flag;
    }

    @Override
    public List<OrdDiscount> queryOrdDiscountByOrderId(String orderId) {
        OrdDiscountCriteria odc = new OrdDiscountCriteria();
        odc.createCriteria().andOrderIdEqualTo(orderId);
        return this.ordDiscountMapper.selectByExample(odc);
    }
    @Override
    public List<OrdDiscount> queryOrdDiscount(String orderId,List<String> discountTypes) {
        OrdDiscountCriteria odc = new OrdDiscountCriteria();
        odc.createCriteria().andOrderIdEqualTo(orderId).andDiscountTypeIn(discountTypes);
        return this.ordDiscountMapper.selectByExample(odc);
    } 
    
    @Override
    public Long queryOrdDiscountMoney(String orderId,List<String> discountTypes) {
        OrdDiscountCriteria odc = new OrdDiscountCriteria();
        odc.createCriteria().andOrderIdEqualTo(orderId).andDiscountTypeIn(discountTypes);
        Long discountMoney = this.ordDisconutUalMapper.sumDiscountPriceByOrderId(odc);
        return discountMoney;
    }     

}
