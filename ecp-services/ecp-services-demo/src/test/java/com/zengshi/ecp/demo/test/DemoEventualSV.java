package com.zengshi.ecp.demo.test;

import com.zengshi.ecp.demo.dao.model.DemoLog;
import com.zengshi.ecp.demo.facade.interfaces.eventual.IEventualTrasctionSV;
import com.zengshi.ecp.demo.facade.interfaces.eventual.IEventualTrasctionSV2;
import com.zengshi.ecp.demo.service.busi.interfaces.IDemoLogSV;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.distribute.tx.common.TransactionContext;
import com.distribute.tx.server.dao.TransactionContextDAO;
import org.junit.Test;

import javax.annotation.Resource;


public class DemoEventualSV extends EcpServicesTest {
	
	@Resource(name="eventualTrasctionSV")
	private IEventualTrasctionSV eventualTrasctionSV;
	
	@Resource(name="eventualTrasctionSV2")
	private IEventualTrasctionSV2 eventualTrasctionSV2;
	
//	@Resource(name="transactionContextDAO")
	private TransactionContextDAO tcDAO;
	
	@Resource
	private IDemoLogSV demoLogSV;
	
//	@Test
	public void insertLog(){
	    DemoLog log=demoLogSV.find(32L);//new DemoLog();
	    log.setDbCode("905");
	    log.setLogInfo("测试测试fff");
	    System.out.println("start================");
//	    demoLogSV.saveLog(log);
	    System.out.println(log.getLogInfo());
	    System.out.println("end================");
	}
	
//	@Test
	public void insertTc(){
	    TransactionContext tc=new TransactionContext();
	    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	    tc.setTransactionId(1L);
	    tc.setContent("测试测试+++++++++");
	    tcDAO.insert(tc);
	}
	
	@Test
	public void doTrasaction(){
		final DemoLog log = new DemoLog();
		final int code = 88;
		log.setDbCode("998"+code);
		log.setLogId(Long.valueOf(code));
		log.setLogInfo("wwwwww最终一致性xxxxxxxxx" + code);
		final DemoLog log2 = new DemoLog();
		log2.setDbCode("5"+code);
		log2.setLogId(Long.valueOf(code));
		log2.setLogInfo("wwww最终一致性监听xxxxx" + code);
		
//		eventualTrasctionSV.execute(log);
//		eventualTrasctionSV2.execute(log);
		
		Thread thread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				int i=0;
//				while(i++<5000){
					try {
//						log2.setLogInfo("12最终一致性监听" + i);
					    System.out.println("***********************************");
						eventualTrasctionSV2.execute(log2);
						Thread.sleep(60000L*5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
//			}
		});
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
