package com.zengshi.ecp.demo.test.zk;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.ZooKeeper;

public class ZKServer2 {

	public static void main(String[] args) throws Exception {
		
		ZooKeeper zk=new ZooKeeper("192.168.1.102:22181", 5000, new Watcher(){

			@Override
			public void process(WatchedEvent event) {
				
				if(Event.EventType.NodeDataChanged.equals(event.getType())){
					String path=event.getPath();
					System.out.println("触发数据变动2******************");
				}
			}
			
		});
		int i=0;
//		while(true){
//			Stat s=zk.exists("/test", true);
////			zk.setData("/test", ("测试0999_"+i++).getBytes(),s.getVersion());
//			Thread.sleep(1000L);
//		}
		String node="/com/zengshi/paas/db/oracle/distributeTransaction/logic_transaction_db_01";
		Stat s=zk.exists(node, false);
		
		System.out.println("logic_transaction_db_01  :"+new String(zk.getData(node, false, s)));
//		zk.register(new Watcher(){
//
//			@Override
//			public void process(WatchedEvent event) {
//				
//				System.out.println(event.getPath()+"%%%%"+event.getType());
//			}
//			
//		});
//		if(zk.exists("/test/3",true)!=null){
//			System.out.println("testNode exists now.");
//		}
//		zk.create("/test/3", "test1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//		zk.delete("/test/1", -1);
		
//		System.in.read(); // 为保证服务一直开着，利用输入流的阻塞来模拟  
//		zk.create("/test", "test".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//		zk.setData("/test", "测试".getBytes(), -1);
	}

}
