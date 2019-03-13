package com.zengshi.ecp.demo.facade.interfaces.eventual;

import com.zengshi.ecp.demo.dao.model.DemoLog;

public interface IEventualTrasctionSV2 {
	
	public void execute(DemoLog demoLog);

}
