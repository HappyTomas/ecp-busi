package com.zengshi.ecp.goods.dubbo.dto.category.dataimport;
/**
 * 
 * Title: ERP数据导入Map键名. <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2015-10-26上午9:41:15  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
public class DataImportConstants {
	
	public static class ERPDataMapKeys {
		/**
		 * 记录号.
		 */
		public static final String RECORD_ID = "record_id";
		/**
		 * 项目排序.(相当于主键)
		 */
		public static final String XIANGMUPAIXU = "xiangmupaixu";
		/**
		 * 项目名称.
		 */
		public static final String XINXIXIANGMU = "xinxixiangmu";
		/**
		 * 分类排序.(int)
		 */
		public static final String SORT_NO = "xinxipaixu";
		/**
		 * 分类ID.
		 */
		//public static final String XIANGMUBIANHAO = "xiangmubianhao";
		public static final String CATG_CODE = "xinxibianhao";
		/**
		 * 分类名称。
		 */
		//public static final String xinximingxi = "xinximingxi";
		public static final String CATG_NAME = "xinximingxi";
		/**
		 * 上级分类ID。
		 */
		//public static final String zhujianbianhao = "zhujianbianhao";
		public static final String CATG_PARENT = "zhujianbianhao";
		/**
		 * 上级项目。
		 */
		public static final String ZHUJIANXIANGMU = "zhujianxiangmu";
		/**
		 * 上级分类名称。
		 */
		public static final String CATG_PARENT_NAME = "zhujianxinxi";
		/**
		 * 操作.增加[1]删除[2]修改[3] 数据类型int
		 */
		public static final String XINXICAOZUO = "xinxicaozuo";
		/**
		 * 操作日期。
		 */
		public static final String CAOZUORIQI = "caozuoriqi";
	}
	
	/**
	 * 
	 * Title: ECP <br>
	 * Project Name:ecp-services-goods-server <br>
	 * Description:泽元分类信息数据键. <br>
	 * Date:2015-10-27下午5:38:11  <br>
	 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
	 * 
	 * @author liyong7
	 * @version DataImportConstants 
	 * @since JDK 1.6
	 */
	public static class ZYDataMapKeys {
		/**
		 * 分类ID.
		 */
		public static final String CATG_CODE = "ID";
		/**
		 * 父分类ID
		 */
		public static final String CATG_PARENT = "PARENTID";
		/**
		 * 排序.
		 */
		public static final String SORT_NO = "ORDERFLAG";
		/**
		 * 分类类型.(1=电子书，2=数字教材)
		 */
		public static final String TYPE = "TYPE";
		/**
		 * 分类名称.
		 */
		public static final String CATG_NAME = "NAME";
        /**
         * 数字教村分类.		
         */
		public static final Integer TYPE_ONE = 1;
		/**
		 * 电子书分类.
		 */
		public static final Integer TYPE_TWO = 2;
	
	}
	
	
	/**
	 * 
	 * Title: 商品分类更新需检测字段常量. <br>
	 * Project Name:ecp-services-goods-server <br>
	 * Description: <br>
	 * Date:2015-10-26下午5:29:59  <br>
	 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
	 * 
	 * @author liyong7
	 * @version ERPDataImportConstants 
	 * @since JDK 1.6
	 */
	public static class GdsCategoryUpdateChkFields{
		
		public static final String CATG_NAME = "catgName";
		
		public static final String SORT_NO = "sortNo";
		
	}
}

