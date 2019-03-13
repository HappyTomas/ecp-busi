package com.zengshi.ecp.demo.facade.interfaces.eventual;

import com.zengshi.ecp.demo.dao.model.DemoLog;
import com.distribute.tx.common.TransactionListener;


public interface IEventualTrasctionSV extends TransactionListener{
	
	public void execute(DemoLog demoLog);

}
