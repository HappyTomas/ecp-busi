package com.zengshi.ecp.demo.facade.impl.assured;

import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.demo.dao.model.User;
import com.zengshi.ecp.demo.facade.interfaces.assured.IAssuredTransactionSub2;
import com.zengshi.ecp.demo.service.common.interfaces.IUserSV;
import com.db.common.DistributedTransactionManager;
import com.distribute.tx.common.TransactionStatus;

import net.sf.json.JSONObject;

public class AssuredTransactionSub2 implements IAssuredTransactionSub2 {

	@Autowired
	private IUserSV userSV;
	
	@Override
	public Object participantTransaction(JSONObject paramJSONObject, TransactionStatus status) {
		//设置
		DistributedTransactionManager.setLevel(0);
		
		User user = new User();
		user.setUserClass("08");
		user.setUserName("分布式事务88");
		System.out.println("sub2开始++++++++++++++++++++++++++++++++");
//		System.out.println("+++++++++++++++"+DistributedTransactionManager.getConnectionMap().size());
//		for(String key:DistributedTransactionManager.getConnectionMap().keySet()){
//			System.out.println(key);
//		}
		userSV.saveUser(user);
		System.out.println("sub2结束++++++++++++++++++++++++++++++++");
//		status.setRollbackOnly();
		return false;
	}

}
