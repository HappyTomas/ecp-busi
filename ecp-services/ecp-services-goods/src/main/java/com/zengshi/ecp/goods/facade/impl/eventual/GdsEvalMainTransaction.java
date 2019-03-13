package com.zengshi.ecp.goods.facade.impl.eventual;

import javax.annotation.Resource;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalRespDTO;
import com.zengshi.ecp.goods.facade.interfaces.eventual.IGdsEvalMainTransaction;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsEvalSV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSONObject;
import com.distribute.tx.common.TransactionCallback;
import com.distribute.tx.common.TransactionContext;
import com.distribute.tx.common.TransactionStatus;
import com.distribute.tx.eventual.TransactionManager;
/**
 * 
 * Title: 商品评价主事务实现. <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2015-10-16上午9:57:20  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
public class GdsEvalMainTransaction extends AbstractSVImpl implements IGdsEvalMainTransaction {
	
	@Resource(name="transactionManagerEval")
	private TransactionManager transactionManager;
//	@Resource(name="transactionTemplate2")
//	private TransactionTemplate transactionTemplate;
	
	
	@Resource(name="gdsEvalSV")
	private IGdsEvalSV gdsEvalSV;
	
	

	@Override
	public GdsEvalRespDTO addEval(final GdsEvalReqDTO reqDTO) {
		
		LogUtil.info(MODULE, "添加评价,入参:"+ToStringBuilder.reflectionToString(reqDTO));
		final TransactionContext txMsg = new TransactionContext();
		txMsg.setName("business-topic-goods-eval");
		/*Map<String, Object> params = new HashMap<String, Object>();
	  	params.put("orderId", reqDTO.getOrderId());
	  	params.put("subId", reqDTO.getOrderSubId());*/
	  	txMsg.setContent(JSONObject.toJSONString(reqDTO));
		Object obj = transactionManager.startTransaction(txMsg, new TransactionCallback() {
			@Override
			public Object doInTransaction(TransactionStatus status) {
				try{
				  	GdsEvalRespDTO respDTO = gdsEvalSV.addGdsEval(reqDTO);
					return respDTO;
				}catch (Exception e) {
					LogUtil.error(MODULE, "增加商品评价遇到异常!",e);
					throw new BusinessException(GdsErrorConstants.GdsEval.ERROR_GOODS_EVAL_200555);
				}
			}
		});
		
		return (GdsEvalRespDTO) obj;
	}



	@Override
	public void executeEvalAudit(GdsEvalReqDTO reqDTOLst) {
		LogUtil.info(MODULE, "评价审核最终一致性事务,入参:"+ToStringBuilder.reflectionToString(reqDTOLst));
		final TransactionContext txMsg = new TransactionContext();
		txMsg.setName("business-topic-goods-eval");
		txMsg.setContent(JSONObject.toJSONString(reqDTOLst));
		transactionManager.startTransaction(txMsg, new TransactionCallback() {
			@Override
			public Object doInTransaction(TransactionStatus status) {
				return null;
			}
		});
		
	}
	
	
}

