/** 
 * Project Name:ecp-services-goods-server 
 * File Name:IImgSampleChapterRSV.java 
 * Package Name:com.zengshi.ecp.goods.dubbo.interfaces.dataimport 
 * Date:2015-10-30上午11:08:24 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.dubbo.interfaces.dataimport;

import java.util.Map;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.kettle.IReceiveData;
import com.zengshi.ecp.server.front.kettle.IRemoteLogic;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description:农商商品图片导入。 <br>
 * Date:2015-10-30上午11:08:24  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public interface IACImageImportRSV extends IReceiveData,IRemoteLogic{
	/**
	 * 
	 * executeUpdate:更新图片样章。
	 * 
	 * @author liyong7
	 * @param map
	 * @param catgCode
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void executeUpdate(Map<String, Object> map)throws BusinessException;

}

