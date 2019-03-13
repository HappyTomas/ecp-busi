package com.zengshi.ecp.im.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration.Builder;
import org.jivesoftware.smackx.iqregister.AccountManager;
import org.junit.Test;

public class TestSmack {

	AbstractXMPPConnection connection = null;

	public void init() throws XMPPException {

		Builder builder = XMPPTCPConnectionConfiguration.builder();
		builder.setSecurityMode(SecurityMode.disabled);
		builder.setCompressionEnabled(false);
		XMPPTCPConnectionConfiguration config = builder
				.setServiceName("imserver").setHost("10.8.0.213")
				.setPort(5222).build();
		connection = new XMPPTCPConnection(config);
		try {
			connection.connect();
			boolean target = connection.isConnected();
			if (target) {
				System.out.println("XMPP 服务器连接成功");
			} else {
				System.out.println("XMPP 服务器连接不成功");
			}
			connection.login("admin", "123456");
		} catch (SmackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * <b>function:</b> 用户管理器
	 * 
	 * @author wangbh
	 * @throws XMPPException
	 */

	public void testAccountManager() throws XMPPException {
		init();
		AccountManager accountManager = AccountManager.getInstance(connection);

		Map<String, String> map = new HashMap<String, String>();

		try {
			AccountManager
					.sensitiveOperationOverInsecureConnectionDefault(true);
			map.put("name", "黄先龙3");
			accountManager.createAccount("hxl3", "123456", map);
		} catch (NoResponseException e) { // TODO Auto-generated catchblock
			e.printStackTrace();
			disConnented();
		} catch (NotConnectedException e) {
			// TODO Auto-generated catch block e.printStackTrace();
			disConnented();
		}
		disConnented();
	}

	/**
	 * <b>function:</b> 消息发送
	 * 
	 * @author wangbh
	 * @throws XMPPException
	 */
	@Test
	public void sendMessage() throws XMPPException {
		init();
		
		ChatManager chatmanager = ChatManager.getInstanceFor(connection);
		Chat newChat = chatmanager.createChat("admin@xl-20140702qgsf");
		try {
			newChat.sendMessage("Howdy!");
		} catch (NotConnectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		disConnented();
	}

	/**
	 * <b>function:</b> 用户私有分组（）
	 * 
	 * @author wangbh
	 * @throws XMPPException
	 */

/*	@Test
	public void createGroup() throws XMPPException {
		init();
		try {
			connection.login("abc", "123456");
			Roster roster = Roster.getInstanceFor(connection);
			roster.createGroup("cs");
		} catch (SmackException e) {
			e.printStackTrace();
			disConnented();
		} catch (IOException e) {
			e.printStackTrace();
			disConnented();
		}
		disConnented();
	}*/

	/**
	 * <b>function:</b> 断开连接
	 * 
	 * @author wangbh
	 * @throws XMPPException
	 */
	public void disConnented() {
		if (connection != null) {
			connection.disconnect();
		}

	}
	
	public static void main(String[] args) {
		try {
			new TestSmack().testAccountManager();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}

}
