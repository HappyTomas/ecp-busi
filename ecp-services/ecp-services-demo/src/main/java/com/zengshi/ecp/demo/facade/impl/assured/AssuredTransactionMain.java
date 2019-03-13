package com.zengshi.ecp.demo.facade.impl.assured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.zengshi.ecp.demo.dao.model.DemoLog;
import com.zengshi.ecp.demo.facade.interfaces.assured.IAssuredTransactionMain;
import com.zengshi.ecp.demo.service.busi.interfaces.IDemoLogSV;
import com.db.common.DistributedTransactionManager;
import com.distribute.tx.common.TransactionStatus;

import net.sf.json.JSONObject;

public class AssuredTransactionMain implements IAssuredTransactionMain {
	
	@Autowired
	@Qualifier("demoLogSV")
	private IDemoLogSV demoLogSv;
	@Override
	public Object participantTransaction(JSONObject paramJSONObject, TransactionStatus status) {
		DemoLog log=(DemoLog) JSONObject.toBean(paramJSONObject, DemoLog.class);
		try {
			demoLogSv.saveLog(log);
			System.out.println("+++++++++++++++"+DistributedTransactionManager.getConnectionMap().size());
			for(String key:DistributedTransactionManager.getConnectionMap().keySet()){
				System.out.println(key);
			}
		} catch (Exception e) {
			status.setRollbackOnly();
			e.printStackTrace();
		}
		
		return null;
	}

}
