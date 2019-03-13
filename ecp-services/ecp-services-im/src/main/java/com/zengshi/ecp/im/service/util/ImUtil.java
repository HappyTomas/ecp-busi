package com.zengshi.ecp.im.service.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration.Builder;
import org.jivesoftware.smackx.iqregister.AccountManager;

import com.zengshi.ecp.im.dubbo.util.ImConstants;
import com.zengshi.paas.utils.LogUtil;
import com.mongodb.DBObject;

public class ImUtil {

	public static AbstractXMPPConnection connection = null;

	public static String ServiceName = ImConstants.ServiceName;

	public  static String Host = ImConstants.Host;

	public  static int Port=ImConstants.Port;
	
	private static final String MODULE = ImUtil.class.getName();
	
	public static void init() throws XMPPException {

		Builder builder = XMPPTCPConnectionConfiguration.builder();
		builder.setSecurityMode(SecurityMode.disabled);
		builder.setCompressionEnabled(false);
		XMPPTCPConnectionConfiguration config = builder.setServiceName(ServiceName).setHost(Host).setPort(Port).build();
		connection = new XMPPTCPConnection(config);
		try {
			connection.connect();
			boolean target = connection.isConnected();
			if (target) {
				System.out.println("XMPP 服务器连接成功");
			} else {
				System.out.println("XMPP 服务器连接不成功");
			}
			//connection.login("admin", ImConstants.passWord);
		} catch (SmackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * <b>function:</b> 用户管理器 注册用户
	 * 
	 * @author wangbh
	 * @throws XMPPException
	 */

	public static void addAccountManager(String username, String password, Map<String, String> map)
			throws XMPPException {
		init();
		AccountManager accountManager = AccountManager.getInstance(connection);

		try {
			AccountManager.sensitiveOperationOverInsecureConnectionDefault(true);
			accountManager.createAccount(username, password, map);
		} catch (NoResponseException e) { // TODO Auto-generated catchblock
			LogUtil.error(MODULE, e.getMessage());
			disConnented();
		} catch (NotConnectedException e) {
			LogUtil.error(MODULE, e.getMessage());
			disConnented();
		}
		disConnented();
	}

	/**
	 * 删除当前连接用户
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws XMPPException
	 */
	public static boolean delAccountManager(String username, String password) throws XMPPException {
		init();
		try {
			connection.login(username, password);
			AccountManager accountManager = AccountManager.getInstance(connection);
			accountManager.deleteAccount();
			disConnented();
			return true;
		} catch (Exception e) {
			disConnented();
			return false;
		}

	}

	/**
	 * 修改openfire用户密码
	 * 
	 * @param username
	 * @param OldPassword
	 * @param NewPassword
	 * @return
	 * @throws XMPPException
	 */
	public static boolean changePassword(String username, String OldPassword, String NewPassword) throws XMPPException {
		init();
		try {
			connection.login(username, OldPassword);
			AccountManager accountManager = AccountManager.getInstance(connection);
			accountManager.changePassword(NewPassword);
			disConnented();
			return true;
		} catch (Exception e) {
			disConnented();
			return false;
		}

	}

	/**
	 * <b>function:</b> 消息发送
	 * 
	 * @author wangbh
	 * @throws XMPPException
	 */
	/*
	 * public void sendMessage() throws XMPPException { init();
	 * 
	 * ChatManager chatmanager = ChatManager.getInstanceFor(connection); Chat
	 * newChat = chatmanager.createChat("admin@xl-20140702qgsf"); try {
	 * newChat.sendMessage("Howdy!"); } catch (NotConnectedException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * disConnented(); }
	 */

	/**
	 * <b>function:</b> 用户私有分组（）
	 * 
	 * @author wangbh
	 * @throws XMPPException
	 */

	/*
	 * public void createGroup() throws XMPPException { init(); try {
	 * connection.login("abc", "123456"); Roster roster =
	 * Roster.getInstanceFor(connection); roster.createGroup("cs"); } catch
	 * (SmackException e) { e.printStackTrace(); disConnented(); } catch
	 * (IOException e) { e.printStackTrace(); disConnented(); } disConnented();
	 * }
	 */

	/**
	 * <b>function:</b> 断开连接
	 * 
	 * @author wangbh
	 * @throws XMPPException
	 */
	public static void disConnented() {
		if (connection != null) {
			connection.disconnect();
		}

	}

	/**
	 * 将DBObject转换成Bean对象
	 * 
	 */
	public static <T> T dbObjectToBean(DBObject dbObject, T bean) {

		try {
			if (bean == null) {
				return null;
			}
			Field[] fields = bean.getClass().getDeclaredFields();
			for (Field field : fields) {
				String varName = field.getName();
				Object object = dbObject.get(varName);
				if (object != null) {
					BeanUtils.setProperty(bean, varName, object);
				}

			}
			return bean;
		} catch (Exception e) {
		LogUtil.error(MODULE, e.getMessage(), e);
		}
		return null;
	}

}
