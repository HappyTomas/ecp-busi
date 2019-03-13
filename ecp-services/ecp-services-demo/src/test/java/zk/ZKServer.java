package zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZKServer {

	public static void main(String[] args) throws Exception {
		
		ZooKeeper zk=new ZooKeeper("192.168.1.102:22181", 5000, new Watcher(){

			@Override
			public void process(WatchedEvent event) {
				
				if(Event.EventType.NodeDataChanged.equals(event.getType())){
					String path=event.getPath();
					System.out.println("触发数据变动++++++++++++");
				}
			}
			
		});
		
		Stat s=zk.exists("/com/zengshi/paas/db/oracle/sequence", false);
		System.out.println("/com/zengshi/paas/db/oracle/sequence  :"+new String(zk.getData("/com/zengshi/paas/db/oracle/sequence", false, s)));
		
//		s=zk.exists("/com/zengshi/paas/db/oracle/distributeTransaction/logic_transaction_db_01", false);
//        System.out.println("/com/zengshi/paas/db/oracle/distributeTransaction/logic_transaction_db_01  :"+new String(zk.getData("/com/zengshi/paas/db/oracle/distributeTransaction/logic_transaction_db_01", false, s)));
//
//        s=zk.exists("/com/zengshi/paas/db/oracle/distributeTransaction/logic_transaction_db_02", false);
//        System.out.println("/com/zengshi/paas/db/oracle/distributeTransaction/logic_transaction_db_02  :"+new String(zk.getData("/com/zengshi/paas/db/oracle/distributeTransaction/logic_transaction_db_02", false, s)));
//
//        s=zk.exists("/com/zengshi/paas/tx/transactionListenerManager", false);
//        System.out.println("/com/zengshi/paas/tx/transactionListenerManager  :"+new String(zk.getData("/com/zengshi/paas/tx/transactionListenerManager", false, s)));
//
//        s=zk.exists("/com/zengshi/paas/tx/transactionManager", false);
//        System.out.println("/com/zengshi/paas/tx/transactionManager  :"+new String(zk.getData("/com/zengshi/paas/tx/transactionManager", false, s)));
//
//        System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
//        s=zk.exists("/com/zengshi/paas/tx/transactionManagerServer", true);
//        System.out.println("/com/zengshi/paas/tx/transactionManagerServer  :"+new String(zk.getData("/com/zengshi/paas/tx/transactionManagerServer", false, s)));
//
//        String msg="{zookeeper.connect:'192.168.1.102:22181',group.id:'distribute_transaction_manager_topic_group',transaction.topic:'distribute_transaction_manager_topic',processor.num:'4',zk.server:'192.168.1.102:22181'}";
//
//        zk.setData("/com/zengshi/paas/tx/transactionManagerServer", msg.getBytes(), s.getVersion());
//        System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
//
//        s=zk.exists("/com/zengshi/paas/tx/transactionPublisher", false);
//        System.out.println("/com/zengshi/paas/tx/transactionPublisher  :"+new String(zk.getData("/com/zengshi/paas/tx/transactionPublisher", false, s)));
//
//
//        s=zk.exists("/com/zengshi/paas/message/messageSender", false);
//        System.out.println("/com/zengshi/paas/message/messageSender  :"+new String(zk.getData("/com/zengshi/paas/message/messageSender", false, s)));
//
//
//        s=zk.exists("/com/zengshi/paas/db/oracle/tran_manage_server", false);
//        System.out.println("/com/zengshi/paas/db/oracle/tran_manage_server  :"+new String(zk.getData("/com/zengshi/paas/db/oracle/tran_manage_server", false, s)));
        
//        s=zk.exists("/transactionManager/participants", false);
//        System.out.println("/transactionManager/participants  :"+new String(zk.getData("/transactionManager/participants", false, s)));
//
//        s=zk.exists("/transactionManager/leader", false);
//        System.out.println("/transactionManager/leader  :"+new String(zk.getData("/transactionManager/leader", false, s)));
        
//		
//		Thread.sleep(10000L);

////		String p=zk.create("/test", "test".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
////		System.out.println("创建路径："+p);
//		if(zk.exists("/test",false)!=null){
//			System.out.println("node1 exists now.");
//		}
//		Stat s=zk.exists("/test", true);
//		zk.setData("/test", "测试0988".getBytes(),s.getVersion());
//		System.out.println("wating>>>>>>>>>>>>>>>>>>>>>>>");
//		Thread.sleep(10000L);
////		zk.setData("/test", "测试0983".getBytes(),s.getVersion()+1);
//		System.out.println("测试0983++++++++++++");
//		System.out.println("wating2222>>>>>>>>>>>>>>>>>>>>>>>");
//		Thread.sleep(10000L);
//		Stat ss=zk.exists("/test", true);
//		zk.setData("/test", "测试0983".getBytes(),ss.getVersion());
//		System.in.read(); // 为保证服务一直开着，利用输入流的阻塞来模拟  
	}

}
