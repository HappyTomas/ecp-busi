/** 
 * Project Name:ecp-services-aip-server 
 * File Name:AipConstants.java 
 * Package Name:com.zengshi.ecp.aip.dubbo.constants 
 * Date:2015-10-28下午5:12:31 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.aip.third.dubbo.constants;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-aip-server <br>
 * Description: Aip能力平台常量类。<br>
 * Date:2015-10-28下午5:12:31  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class AipConstants {
	/**
	 * 
	 * Title: ECP <br>
	 * Project Name:ecp-services-aip-server <br>
	 * Description: 通用常量。<br>
	 * Date:2015-10-29下午3:18:31  <br>
	 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
	 * 
	 * @author liyong7
	 * @version AipConstants 
	 * @since JDK 1.6
	 */
	public static final class Commons{
		
	}
	/**
	 * 
	 * Title: ECP <br>
	 * Project Name:ecp-services-aip-server <br>
	 * Description:远程服务地址。 <br>
	 * Date:2015-10-29下午3:18:40  <br>
	 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
	 * 
	 * @author liyong7
	 * @version AipConstants 
	 * @since JDK 1.6
	 */
	public static final class RemoteURL{

	}
	
	
	/**
	 * 
	 * Title: ECP <br>
	 * Project Name:ecp-services-aip-server <br>
	 * Description:异常码常量 <br>
	 * Date:2015-10-29下午3:35:36  <br>
	 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
	 * 
	 * @author liyong7
	 * @version AipConstants 
	 * @since JDK 1.6
	 */
	public static final class ErrorCode{

		/**
		 * PAY.SERVER.300001 = 农行对账异常
		 */
		public static final String PAY_SERVER_300001 = "PAY.SERVER.300001";
		
		/**
         * PAY.SERVER.300002 = 农行退款异常
         */
        public static final String PAY_SERVER_300002 = "PAY.SERVER.300002";
        
        /**
         * PAY.SERVER.300003 = 农行订单查询异常
         */
        public static final String PAY_SERVER_300003 = "PAY.SERVER.300003";
        
        /**
         * 入参对象不能为空
         */
        public static final String PAY_INPUT_300001 = "PAY.INPUT.300001";
        /**
         * 对账日期不能为空
         */
        public static final String PAY_INPUT_300002 = "PAY.INPUT.300002";
        /**
         * 对账是否压缩标识不能为空
         */
        public static final String PAY_INPUT_300003 = "PAY.INPUT.300003";
        /**
         * 订单日期不能为空
         */
        public static final String PAY_INPUT_300004 = "PAY.INPUT.300004";
        /**
         * 订单时间不能为空
         */
        public static final String PAY_INPUT_300005 = "PAY.INPUT.300005";
        /**
         * 原交易编号不能为空
         */
        public static final String PAY_INPUT_300006 = "PAY.INPUT.300006";
        /**
         * 交易编号不能为空
         */
        public static final String PAY_INPUT_300007 = "PAY.INPUT.300007";
        /**
         * 交易币种不能为空
         */
        public static final String PAY_INPUT_300008 = "PAY.INPUT.300008";
        
        /**
         * 退货金额 不能为空
         */
        public static final String PAY_INPUT_300009 = "PAY.INPUT.300009";
        
        /**
         * 交易类型不能为空
         */
        public static final String PAY_INPUT_300010 = "PAY.INPUT.300010";
        
        /**
         * 查询方式不能为空
         */
        public static final String PAY_INPUT_300011 = "PAY.INPUT.300011";
        
        /**
         * 商户号不能为空
         */
        public static final String PAY_INPUT_300012 = "PAY.INPUT.300012";
	}
	
	public static class PayServiceMsgCode{
	    /**
	     * 支付配置错误返回的异常
	     */
	    public static String PAY_SERVER_310002 = "PAY.SERVER.310002";
	    /**
         * 商户编号不存在
         */
	    public static String PAY_SERVER_310003 = "PAY.SERVER.310003";
	}

}

