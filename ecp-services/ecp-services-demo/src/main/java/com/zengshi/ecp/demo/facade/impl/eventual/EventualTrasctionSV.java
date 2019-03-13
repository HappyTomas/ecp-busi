package com.zengshi.ecp.demo.facade.impl.eventual;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import com.zengshi.ecp.demo.dao.model.DemoLog;
import com.zengshi.ecp.demo.facade.interfaces.eventual.IEventualTrasctionSV;
import com.zengshi.ecp.demo.service.busi.interfaces.IDemoLogSV;
import com.distribute.tx.common.TransactionCallback;
import com.distribute.tx.common.TransactionContext;
import com.distribute.tx.common.TransactionStatus;
import com.distribute.tx.eventual.TransactionManager;

import net.sf.json.JSONObject;
/**
 * 最终一致性：子事务
 * @author jiangysh
 *
 */
@Service("eventualTrasctionSV")
public class EventualTrasctionSV implements IEventualTrasctionSV{
	@Resource(name="transactionManager1")
	private TransactionManager transactionManager;
	@Resource(name="transactionTemplate2")
	private TransactionTemplate transactionTemplate;
	
	@Autowired
	@Qualifier("demoLogSV")
	private IDemoLogSV demoLogSv;
	
	@Override
	public void execute(final DemoLog demoLog) {
		
		TransactionContext txMsg = new TransactionContext();
		txMsg.setContent(JSONObject.fromObject(demoLog).toString());
		txMsg.setName("business-topic");
		
		transactionManager.startTransaction(txMsg, new TransactionCallback() {
			
			@Override
			public Object doInTransaction(TransactionStatus status) {
//				Object ret = transactionTemplate.execute(new org.springframework.transaction.support.TransactionCallback() {
//
//					@Override
//					public Object doInTransaction(org.springframework.transaction.TransactionStatus status) {
//						System.out.println("子事务执行+++++++++++++++++++++++++++++++");
//						demoLogSv.saveLog(demoLog);
//						
//						 return null;
//					}
//				});
//				if (ret != null && ((boolean)ret)) {
//					System.out.println("succeed");
//					return true;
//				}else {
//					System.out.println("failed");
//					status.setRollbackOnly();
//					return false;
//				}
				System.out.println("子事务执行+++++++++++++++++++++++++++++++");
				boolean flag=demoLogSv.saveLog(demoLog);
				if(!flag){
					status.setRollbackOnly();
				}
				return flag;
			}
		});
	}

	@Override
	public void joinTransaction(JSONObject message, TransactionStatus status, String transactionName) {
		
		final DemoLog demoLog=(DemoLog) JSONObject.toBean(message, DemoLog.class);
		
//		@SuppressWarnings("unchecked")
//		Object ret = transactionTemplate.execute(new org.springframework.transaction.support.TransactionCallback() {
//
//			@Override
//			public Object doInTransaction(org.springframework.transaction.TransactionStatus status) {
//				System.out.println("+++++++++++++++++++++++++++++++++++监听保存");
//				demoLog.setLogInfo("监听保存："+demoLog.getLogInfo());
//				 return demoLogSv.saveLog(demoLog);
//			}
//		});
		System.out.println("+++++++++++++++++++++++++++++++++++监听保存");
		demoLog.setLogInfo("监听保存："+demoLog.getLogInfo());
		boolean ret =demoLogSv.saveLog(demoLog);
		System.out.println("监听ret："+ret+"    =======================================");
		if (ret) {
			System.out.println("succeed");
			return ;
		}else {
			System.out.println("failed");
			status.setRollbackOnly();
			return ;
		}
	}
	
	

}
