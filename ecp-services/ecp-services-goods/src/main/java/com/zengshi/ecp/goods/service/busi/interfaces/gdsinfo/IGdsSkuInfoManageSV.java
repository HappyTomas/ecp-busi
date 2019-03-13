package com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo;

import com.zengshi.ecp.goods.dao.model.GdsSkuInfo;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PropReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 单品管理接口，主要负责单品的添加，编辑，删除，上下架等影响数据的操作<br>
 * Date:2015年8月22日下午6:03:36 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public interface IGdsSkuInfoManageSV extends IGeneralSQLSV {

	/**
	 * 
	 * addGdsInfo:(添加单品). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @return
	 * @since JDK 1.6
	 */
	public Long saveGdsSkuInfo(GdsSkuInfoReqDTO gdsSkuInfoReqDTO)
			throws BusinessException;

	/**
	 * 
	 * addGdsInfo:(编辑单品以及单品附属信息). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @return
	 * @since JDK 1.6
	 */
	public Long editGdsSkuInfoAndReference(GdsSkuInfoReqDTO gdsSkuInfoReqDTO)
			throws BusinessException;

	/**
	 * 
	 * addGdsInfo:(添加单品). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @return
	 * @since JDK 1.6
	 */
	public Long editGdsSkuInfo(GdsSkuInfoReqDTO gdsSkuInfoReqDTO)
			throws BusinessException;

	/**
	 * 
	 * delGdsInfo:(删除单品). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @return 
	 * @since JDK 1.6
	 */
	public GdsSkuInfo deleteGdsSkuInfo(GdsSkuInfoReqDTO gdsSkuInfoReqDTO)
			throws BusinessException;

	/**
	 * 
	 * doSkuShelves:(单品上架，下架，不关联商品状态). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @return 
	 * @since JDK 1.6
	 */
	public GdsSkuInfo executeSkuShelvesOnly(GdsSkuInfoReqDTO gdsSkuInfoReqDTO)
			throws BusinessException;

	/**
	 * 
	 * doSkuShelves:(单品上架，下架). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @return 
	 * @since JDK 1.6
	 */
	public GdsSkuInfo executeSkuShelves(GdsSkuInfoReqDTO gdsSkuInfoReqDTO)
			throws BusinessException;

	/**
	 * 
	 * onShelvesCheck:(上架检查). <br/> 
	 * 
	 * @author linwb3
	 * @param gdsSkuInfoReqDTO
	 * @param before
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void onShelvesCheck(GdsSkuInfoReqDTO gdsSkuInfoReqDTO, GdsSkuInfo before)
			throws BusinessException;


}
