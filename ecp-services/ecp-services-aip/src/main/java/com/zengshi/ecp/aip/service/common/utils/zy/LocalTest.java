package com.zengshi.ecp.aip.service.common.utils.zy;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-aip-server <br>
 * Description: 与泽元协定数据传输加密类。需要增加泽元提供依赖包。<br>
 * Date:2015-11-4下午3:19:34  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
public class LocalTest {

	public static void main(String[] args) {
		String a=CryptoUtil.encrypt(CryptoUtil.DEFAULT_KEY, "ipmph");
		System.out.println( a);
		System.out.println(CryptoUtil.decrypt(CryptoUtil.DEFAULT_KEY, a));
	}

}
