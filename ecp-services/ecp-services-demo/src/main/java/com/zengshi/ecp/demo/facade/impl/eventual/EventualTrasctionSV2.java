package com.zengshi.ecp.demo.facade.impl.eventual;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import com.zengshi.ecp.demo.dao.model.DemoLog;
import com.zengshi.ecp.demo.facade.interfaces.eventual.IEventualTrasctionSV2;
import com.zengshi.ecp.demo.service.busi.interfaces.IDemoLogSV;
import com.distribute.tx.common.TransactionCallback;
import com.distribute.tx.common.TransactionContext;
import com.distribute.tx.common.TransactionStatus;
import com.distribute.tx.eventual.TransactionManager;

import net.sf.json.JSONObject;
/**
 * 最终一致性：主事务
 * @author jiangysh
 *
 */
@Service("eventualTrasctionSV2")
public class EventualTrasctionSV2 implements IEventualTrasctionSV2{
	@Resource(name="transactionManager1")
	private TransactionManager transactionManager;
	@Resource(name="transactionTemplate2")
	private TransactionTemplate transactionTemplate;
	
	@Resource(name="demoLogSV")
	private IDemoLogSV demoLogSv;
	
	@Override
	public void execute(final DemoLog demoLog) {
		
		TransactionContext txMsg = new TransactionContext();
		txMsg.setContent(JSONObject.fromObject(demoLog).toString());
		txMsg.setName("business-topic");
		
		transactionManager.startTransaction(txMsg, new TransactionCallback() {
			@Override
			public Object doInTransaction(TransactionStatus status) {
				
//				@SuppressWarnings("unchecked")
//                Object ret = transactionTemplate.execute(new org.springframework.transaction.support.TransactionCallback() {
//
//					@Override
//					public Object doInTransaction(org.springframework.transaction.TransactionStatus status) {
//						 demoLogSv.saveLog(demoLog);
//						System.out.println("主事务！！！！==============");
//						 return true;
//					}
//				});
//				if(status!=null){
//					throw new RuntimeException();
//				}
//				if (ret != null && ((boolean)ret)) {
//					System.out.println("succeed");
//					return true;
//				}else {
//					System.out.println("failed");
//					status.setRollbackOnly();
//					return false;
//				}
				System.out.println("主事务！！！！风动旛动==============");
				boolean flag=demoLogSv.saveLog(demoLog);
				if(!flag){
					status.setRollbackOnly();
				}
				return flag;
			}
		});
	}
	
	

}
