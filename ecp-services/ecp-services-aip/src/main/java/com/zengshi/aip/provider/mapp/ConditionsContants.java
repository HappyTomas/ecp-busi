package com.zengshi.aip.provider.mapp;

/**
 * 商品搜索条件大类常亮
 */
public class ConditionsContants {
	/**最新上架数目*/
	public static final int LASTEST_ONLINE_GDS = 5;
	
	/** 
	 * 全部类型ID
	 */
	public static final String ALL = "0";
	
	/**
	 * 渲染状态
	 */
	public static class Status{
		/**选中*/
		public static final String CHECKED = "1";
		/**未选中*/
		public static final String UN_CHECKED = "0";
	}
	/**
	 * 手机终端
	 */
	public static class MobileCategory{
		public static final String PRICE_ITEM = "";
		public static final String SCREEN_ITEM = "MOBILE_PRICE";
	}
	
	
	/**
	 * 目录常量---T_GDS_SPU_CATEGORY
	 */
	public static class CategoryConts{
		/**
		 * 手机终端
		 */
		public static final String MOBILE = "100";
		/**
		 * 移动业务卡包
		 */
		public static final String MOBILE_BUSI_PACKAGE = "";
		/**
		 * 无线上网卡
		 */
		public static final String WIRELESS_CARD = "300";
		/**
		 * 手机配件
		 */
		public static final String MOBILE_ATTACHMENT = "200";
		/**
		 * 手机套包
		 */
		public static final String MOBILE_PACKAGE = "400";
	}
	
	// 手机
		public static class ZDGds {
			// 价格
			public static final long PRICE_PROP_ID = 34;
			// 屏幕尺寸
			public static final long SCREEN_PROP_ID = 12;

			// id
			public static final long CAT_ID = 100;
		}

		// 上网卡
		public static class SWKGds {
			// 价格范围
			public static final long PRICE_PROP_ID = 3;
			// 套餐
			public static final long PACKAGE_PROP_ID = 2;
			// 特点
			public static final long SPECIAL_PROP_ID = 4;

			public static final long CAT_ID = 300;

		}

		// 手机配件
		public static class PJGds {
			// 细粒度价格
			public static final long TINY_PRICE_PROP_ID = 32;

			// 配件类型
			public static final long ATTACHMENT_PROP_ID = 33;

			// 适用机型

			public static final long PROPMOBI_PROP_ID = 1;

			public static final long CAT_ID = 200;

		}

		// 手机套包
		public static class TBGds {
			// 细粒度价格
			public static final long TINY_PRICE_PROP_ID = 34;

			// 套餐类型
			public static final long PACKAGE_PROP_ID = 5;

			public static final long CAT_ID = 400;

		}
}
