package com.zengshi.ecp.demo.facade.impl.eventual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.demo.facade.interfaces.eventual.ITransactionSV;
import com.distribute.tx.assured.AssuredTransactionManager;

import net.sf.json.JSONObject;

@Service
public class TransactionSV implements ITransactionSV {
	
	@Autowired
	@Qualifier("sampleTransaction")
	private AssuredTransactionManager assuredTransactionManager;
	
	@Override
	public void assuredTransaction(JSONObject paramJSONObject) {
		
		assuredTransactionManager.execute(paramJSONObject);
	}

}
