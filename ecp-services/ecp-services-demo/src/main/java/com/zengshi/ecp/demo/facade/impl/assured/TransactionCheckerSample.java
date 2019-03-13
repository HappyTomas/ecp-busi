package com.zengshi.ecp.demo.facade.impl.assured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.zengshi.ecp.demo.dao.model.DemoLog;
import com.zengshi.ecp.demo.service.busi.interfaces.IDemoLogSV;
import com.distribute.tx.common.TransactionChecker;
import com.distribute.tx.common.TransactionContext;
import com.distribute.tx.common.TransactionStatus;

import net.sf.json.JSONObject;

public class TransactionCheckerSample implements TransactionChecker {

	@Autowired
	@Qualifier("demoLogSV")
	private IDemoLogSV demoLogSv;
	
	@Override
	public void checkTransaction(TransactionContext paramTransactionContext, TransactionStatus status) {
		DemoLog log=(DemoLog) JSONObject.toBean(JSONObject.fromObject(paramTransactionContext.getContent()), DemoLog.class);
		DemoLog plog=this.demoLogSv.find(log.getLogId());
		if(plog == null || plog.getLogId() == null){
			status.setRollbackOnly();
			System.out.println("=====================================status.setRollbackOnly()");
		}else {
			System.out.println("===============================================commit");
		}
		System.out.println(log.getLogId()+":"+log.getLogInfo());
	}

}
