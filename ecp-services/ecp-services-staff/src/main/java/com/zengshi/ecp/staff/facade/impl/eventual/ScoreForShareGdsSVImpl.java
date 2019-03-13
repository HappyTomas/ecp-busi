package com.zengshi.ecp.staff.facade.impl.eventual;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.general.order.dto.PayQuartzInfoRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.order.dubbo.dto.OrdSubShareReq;
import com.zengshi.ecp.order.dubbo.dto.OrdSubShareResp;
import com.zengshi.ecp.order.dubbo.dto.pay.PaySuccInfo;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdSubShareRSV;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IScoreCaclRSV;
import com.zengshi.ecp.staff.facade.interfaces.eventual.IScoreForShareGdsSV;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionStatus;

import net.sf.json.JSONObject;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 商品分享购买送积分子事务<br>
 * Date:2017年2月17日下午4:41:54  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
@Service("scoreForShareGdsSV")
public class ScoreForShareGdsSVImpl implements IScoreForShareGdsSV{
	
	private static final String MODULE = ScoreForShareGdsSVImpl.class.getName();
	
	@Resource
    private IOrdSubShareRSV ordSubShareRSV;
	
	@Resource
	private IScoreCaclRSV scoreCaclRSV;

	@Override
	public void joinTransaction(JSONObject message, TransactionStatus status, String transctionName) {
		final PaySuccInfo paySuccInfo = JSON.parseObject(message.toString(), PaySuccInfo.class);
		
		//判断是否有分享送积分
        OrdSubShareReq ordSubShareReq = new OrdSubShareReq();
        ordSubShareReq.setOrderId(paySuccInfo.getOrderId());
        ordSubShareReq.setStatus(OrdConstants.ShareStatus.NODO);
        List<OrdSubShareResp> ordSubShareResps = ordSubShareRSV.queryOrdSubShareList(ordSubShareReq);
        /*1、有分享送积分*/
        if(ordSubShareResps != null && ordSubShareResps.size() > 0){
        	LogUtil.info(MODULE, "=========商品分享购买赠送积分子事务开始START========");
        	CustInfoReqDTO cust = new CustInfoReqDTO();
    		
			PayQuartzInfoRequest payInfo = new PayQuartzInfoRequest();
			List<ROrdCartItemCommRequest> cartList = new ArrayList<ROrdCartItemCommRequest>();
			ROrdCartItemCommRequest cart = new ROrdCartItemCommRequest();
			cart.setOrderId(paySuccInfo.getOrderId());
			for (OrdSubShareResp sub : ordSubShareResps) {
				cart.setOrderSubId(sub.getSubOrdId());
				cust.setId(sub.getShareStaffId());
				cartList.add(cart);
				payInfo.setOrdCartItemCommList(cartList);
				payInfo.setPayment(sub.getRealMoney());//此次分享商品的金额，作为后续赠送积分的计算基础
				payInfo.setOrderId(paySuccInfo.getOrderId());
				payInfo.setSiteId(paySuccInfo.getCurrentSiteId());
				scoreCaclRSV.updateScore("08", cust, payInfo);
			}
			
			ordSubShareReq.setStatus(OrdConstants.ShareStatus.DONE);
			ordSubShareRSV.updateOrdSubShareStatus(ordSubShareReq);
        	
        	LogUtil.info(MODULE, "=========商品分享购买赠送积分子事务开始END========");
        	/*2、无分享送积分，不做处理*/
        }else{
        	LogUtil.info(MODULE, "=========该订单不需要分享送积分========");
        }
	}

}

