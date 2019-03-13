package com.zengshi.ecp.demo.facade.impl.assured;

import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.demo.dao.model.User;
import com.zengshi.ecp.demo.facade.interfaces.assured.IAssuredTransactionSub1;
import com.zengshi.ecp.demo.service.common.interfaces.IUserSV;
import com.db.common.DistributedTransactionManager;
import com.distribute.tx.common.TransactionStatus;

import net.sf.json.JSONObject;

public class AssuredTransactionSub1 implements IAssuredTransactionSub1 {
	
	@Autowired
	private IUserSV userSV;
	
	@Override
	public Object participantTransaction(JSONObject paramJSONObject, TransactionStatus status) {
		DistributedTransactionManager.setLevel(0);
		
		System.out.println("sub1开始++++++++++++++++++++++++++++++++");
		User user = new User();
		user.setUserClass("09");
		user.setUserName("分布式事务99");
		
//		System.out.println("+++++++++++++++"+DistributedTransactionManager.getConnectionMap().size());
//		for(String key:DistributedTransactionManager.getConnectionMap().keySet()){
//			System.out.println(key);
//		}
		try {
			userSV.saveUser(user);
		} catch (Exception e) {
			status.setRollbackOnly();
			e.printStackTrace();
		}
		System.out.println("sub1结束++++++++++++++++++++++++++++++++");
		return false;
	}

}
