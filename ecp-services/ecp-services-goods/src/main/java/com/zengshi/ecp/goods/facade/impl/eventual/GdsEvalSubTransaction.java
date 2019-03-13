package com.zengshi.ecp.goods.facade.impl.eventual;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.facade.interfaces.eventual.IGdsEvalSubTransaction;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.order.dubbo.dto.ROrdEvaluatedRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdEvaluationRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.distribute.tx.common.TransactionStatus;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2015-10-16上午10:35:36  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
public class GdsEvalSubTransaction extends AbstractSVImpl implements IGdsEvalSubTransaction {
	
	@Resource(name="ordEvaluationRSV")
	private IOrdEvaluationRSV ordEvaluationRSV;
	
	@Override
	public void joinTransaction(JSONObject message, TransactionStatus status,
			String transctionName) {
		LogUtil.info(MODULE, "商品评价回调子事务接收到消息:"+message.toString());
		//final Map<String,Object> paramMap = (Map<String, Object>) JSONObject.toBean(message, Map.class);
		String msgString = message.toString();
		
		if(StringUtil.isEmpty(msgString)){
			return;
		}
		
		List<ROrdEvaluatedRequest> evalReqlst = new ArrayList<ROrdEvaluatedRequest>();
		/*if(msgString.startsWith("[") && msgString.endsWith("]")){
			// 多个对象转化。
			List<GdsEvalReqDTO> reqDTOLst = com.alibaba.fastjson.JSONObject.parseArray(message.toString(),GdsEvalReqDTO.class);
			
			if(CollectionUtils.isNotEmpty(reqDTOLst)){
				
				for (Iterator<GdsEvalReqDTO> iterator = reqDTOLst.iterator(); iterator.hasNext();) {
					GdsEvalReqDTO element = iterator.next();
					ROrdEvaluatedRequest req = createROrdEvaluatedRequest(element);
					evalReqlst.add(req);
				}
			}
		}else{*/
			// 单个对象处理。
			GdsEvalReqDTO evalReqDTO = com.alibaba.fastjson.JSONObject.parseObject(message.toString(),GdsEvalReqDTO.class);
			if(null != evalReqDTO){
				ROrdEvaluatedRequest req = createROrdEvaluatedRequest(evalReqDTO);
				evalReqlst.add(req);
			}
		//}
		if(CollectionUtils.isNotEmpty(evalReqlst)){
			ordEvaluationRSV.updateEvaluated(evalReqlst);
		}
	}

	/* 
	 * createROrdEvaluatedRequest:创建订单已评价请求. <br/> 
	 * 
	 * @author liyong7
	 * @param element
	 * @return 
	 * @since JDK 1.6 
	 */ 
	private ROrdEvaluatedRequest createROrdEvaluatedRequest(
			GdsEvalReqDTO element) {
		ROrdEvaluatedRequest req = new ROrdEvaluatedRequest();
		req.setOrderId(element.getOrderId());
		req.setSubId(element.getOrderSubId());
		return req;
	}
	
}

