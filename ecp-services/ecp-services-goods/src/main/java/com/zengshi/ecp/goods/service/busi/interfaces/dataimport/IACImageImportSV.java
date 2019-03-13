/** 
 * Project Name:ecp-services-goods-server 
 * File Name:IImageSampleChapterSVImpl.java 
 * Package Name:com.zengshi.ecp.goods.service.busi.interfaces.dataimport 
 * Date:2015-10-30下午3:10:55 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.busi.interfaces.dataimport;

import java.util.List;
import java.util.Map;

import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: 农商商品图片导入服务。<br>
 * Date:2015-10-30下午3:10:55  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public interface IACImageImportSV {

	/**
	 * 
	 * executeUpdate:更新图片样章。
	 * 
	 * @author liyong7
	 * @param map
	 * @param gdsName 商品名称
	 * @param sendIdxMsg 是否发送索引更新消息.仅在值有true时才会发送索引更新消息.
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> executeUpdate(Map<String, Object> map, Boolean sendIdxMsg)throws BusinessException;
	/**
	 * 
	 * executeUpdateCheck:更新前检查,用于通用图片上传组件在图片上传前的检测.检测系统中是否有商品与该图片匹配.
	 * 
	 * @author liyong7
	 * @param fileName
	 * @param gdsName 商品名称
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public boolean executeUpdateCheck(String fileName) throws BusinessException;

}

