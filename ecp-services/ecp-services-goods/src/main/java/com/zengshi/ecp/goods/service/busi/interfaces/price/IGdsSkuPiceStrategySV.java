package com.zengshi.ecp.goods.service.busi.interfaces.price;

import java.util.Map;

import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.goods.dubbo.dto.price.GdsPriceInfoResp;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description:商品价格策略接口抽象 <br>
 * Date:2015年8月26日下午3:22:40 <br>
 * Copyright (c)throws BusinessException 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public interface IGdsSkuPiceStrategySV extends IGeneralSQLSV {

	/**
	 * 
	 * getPrice:(获取价格信息 ). <br/>
	 * 
	 * @author linwb3
	 * @param id
	 * @param params
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public GdsPriceInfoResp getPrice(Long id, Map<String, Object> params)
			throws BusinessException;

	/**
	 * 
	 * calculatePrice:(价格计算). <br/>
	 * 
	 * @author linwb3
	 * @param params
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public Long calculatePrice(Map<String, Object> params)
			throws BusinessException;

	/**
	 * 
	 * validatePrice:(价格校验). <br/>
	 * 
	 * @author linwb3
	 * @param reqDto
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public ROrdCartsChkResponse validatePrice(Map<String, Object> params)
			throws BusinessException;

	/**
	 * 
	 * savePrice:(保存价格). <br/>
	 * 
	 * @author linwb3
	 * @param baseInfo
	 * @param params
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public Long savePrice(BaseInfo baseInfo, Map<String, Object> params)
			throws BusinessException;

	/**
	 * 
	 * editPrice:(编辑价格). <br/>
	 * 
	 * @author linwb3
	 * @param baseInfo
	 * @param params
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void editPrice(BaseInfo baseInfo, Map<String, Object> params)
			throws BusinessException;

	/**
	 * 
	 * delPrice:(删除价格信息). <br/>
	 * 
	 * @author linwb3
	 * @param baseInfo
	 * @param params
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void delPrice(BaseInfo baseInfo, Map<String, Object> params)
			throws BusinessException;

}
