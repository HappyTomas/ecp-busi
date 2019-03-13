package com.zengshi.ecp.goods.dubbo.constants;

import java.io.Serializable;

/**
 * 商品查询枚举
 * 	
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月22日下午3:20:03  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version  
 * @since JDK 1.6
 */

public final class GdsOption   implements Serializable{
	
	private static final long serialVersionUID = -6884128095310834943L;

	public enum GdsQueryOption  implements Serializable{
		/**
		 * 商品所有信息
		 */
		ALL("All"),
		/**
		 * 商品编辑信息
		 */
		EDIT("Edit"),
		/**
		 * 商品基本信息
		 */
		BASIC("Basic"),
		/**
		 * 商品价格信息(商品级别的价格信息)
		 */
		PRICE("Price"),
		/**
		 * 商品分类信息
		 */
		CATG("Catg"),
		/**
		 * 商品属性信息
		 */
		PROP("Prop"),
		/**
		 * 商品主图
		 */
		MAINPIC("MainPic"),
		/**
		 * 商品积分配置
		 */
		SCORE("Score"),
		/**
		 * 运费模板信息
		 */
		SHIPTEMPLATE("ShipTemplate"),
		/**
		 * 商品媒体信息
		 */
		MEDIA("Media"),
		/**
         * 商品类型信息
         */
        GDSTYPE("GdsType");
		/**
		 * 枚举编码值
		 */
		private final String code;
		
		/**
		 * 
		 * 构造函数 
		 * 
		 * @param code
		 */
		private GdsQueryOption(final String code){
			this.code=code.intern();
		}
		
		public String getCode(){
			return this.code;
		}
	}
	
	
	public static enum SkuQueryOption  implements Serializable{
		
		/**
		 * 单品所有信息
		 */
		ALL("All"),
		/**
		 * 单品编辑信息
		 */
		EDIT("Edit"),
		/**
		 * 单品基本信息
		 */
		BASIC("Basic"),
		/**
		 * 单品归属商品所有分类信息
		 */
		CATG("MainCatg"),
		/**
		 * 单品属性信息
		 */
		PROP("Prop"),
		/**
		 * 单品主图
		 */
		MAINPIC("MainPic"),
		/**
		 * 单品媒体信息
		 */
		MEDIA("Media"),
		/**
		 * 单品价格信息
		 */
		PRICE("Price"),
		/**
		 * 单品库存信息
		 */
		STOCK("Stock"),
		/**
		 * 单品展示价格
		 */
		SHOWPRICE("ShowPrice"),
		/**
		 * 单品展示库存信息
		 */
		SHOWSTOCK("ShowStock"),
		/**
		 * 计算单品折扣价格
		 */
		CAlDISCOUNT("CalDiscount");
		/**
		 * 枚举编码值
		 */
		private final String code;
		
		/**
		 * 
		 * 构造函数 
		 * 
		 * @param code
		 */
		private SkuQueryOption(final String code){
			this.code=code.intern();
		}
		
		public String getCode(){
			return this.code;
		}
	}
	
}


