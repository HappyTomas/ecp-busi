package com.zengshi.ecp.unpf.dubbo.util;
/**
* @author  lisp: 
* @date 创建时间：2016年11月8日 上午10:56:17 
* @version 1.0 
* */
public class UnpfConstants {

	public static final String STATUS_99="99";//全部
	public static final int PAGESIZE = 10;//每页条数
	public static final int PAGENO = 1;//当前页数
	public static final String UNPF_PLAT_TYPE="UNPF_PLAT_TYPE";//平台类型
	
	public class PlatType4Shop {

        // 状态，0失效，1生效
        public static final String STATUS_0 = "0";
        public static final String STATUS_1 = "1";
        
        public static final String UNPF_PLAT_TYPE="UNPF_PLAT_TYPE";//平台类型 jd  taobao youzan 
    }
	public class MsgSYc {
		 // 状态，1处理成功 0处理失败 2没有配置授权信息 3签名不正确 4报文解码报错 5没有实现接口 6其他待定义
        public static final String STATUS_0 = "0";
        public static final String STATUS_1 = "1";
        public static final String STATUS_2 = "2";
        public static final String STATUS_3 = "3";
        public static final String STATUS_4 = "4";
        public static final String STATUS_5 = "5";
        public static final String STATUS_6 = "6";
	}
	public class ShopCfg {
		 // 状态，1有效 0无效
       public static final String STATUS_0 = "0";
       public static final String STATUS_1 = "1";
	}
	public class StockLimit{
		public static final String UNPF_TM_STOCK_LIMTI="UNPF_TM_STOCK_LIMTI";
		public static final String UNPF_TM_STOCK_SCALE="UNPF_TM_STOCK_SCALE";
	}
	
	public class ZpProp{
		public static final String PROP_ID = "1004";
	}
}


