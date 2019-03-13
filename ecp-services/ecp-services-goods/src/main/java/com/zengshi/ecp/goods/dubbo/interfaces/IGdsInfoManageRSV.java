package com.zengshi.ecp.goods.dubbo.interfaces;


import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.goods.dubbo.dto.GdsVerifyReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoAddReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.util.GdsCacheUtil;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 商品管理dubbo服务查询接口<br>
 * Date:2015年8月30日下午4:37:17 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public interface IGdsInfoManageRSV {

	/**
	 * 
	 * addGdsInfo:(添加商品). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @return
	 * @since JDK 1.6
	 */
	public Long addGdsInfo(GdsInfoAddReqDTO gdsInfoAddReqDTO)
			throws BusinessException;

	/**
	 * 
	 * editGdsInfo:(编辑商品). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @since JDK 1.6
	 */
	public void editGdsInfoAndReference(GdsInfoAddReqDTO gdsInfoAddReqDTO)
			throws BusinessException;

	/**
	 * 
	 * editGdsInfo:(编辑商品). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @since JDK 1.6
	 */
	public void editGdsInfo(GdsInfoReqDTO gdsInfoReqDTO)
			throws BusinessException;

	/**
	 * 
	 * delGdsInfo:(删除商品). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @since JDK 1.6
	 */
	public void delGdsInfo(GdsInfoReqDTO gdsInfoReqDTO)
			throws BusinessException;

	/**
	 * 
	 * batchDelGdsInfo:(批量删除商品). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @since JDK 1.6
	 */
	public void batchDelGdsInfo(GdsInfoReqDTO gdsInfoReqDTO)
			throws BusinessException;

	/**
	 * 
	 * batchDelGdsInfo:(批量下架店铺商品). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @since JDK 1.6
	 */
	public void batchOffShelvesGdsInfoByShopId(GdsInfoReqDTO gdsInfoReqDTO)
			throws BusinessException;

	/**
	 * 
	 * batchDelGdsInfo:(批量上下架店铺商品). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @since JDK 1.6
	 */
	public void batchDoGdsShelves(GdsInfoReqDTO gdsInfoReqDTO)
			throws BusinessException;

	/**
	 * 
	 * doGdsShelves:(商品上架，下架). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @since JDK 1.6
	 */
	public void doGdsShelves(GdsInfoReqDTO gdsInfoReqDTO)
			throws BusinessException;

	/**
	 * 
	 * editGdsShipTemplate:(新增，编辑商品运费模板). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @since JDK 1.6
	 */
	public void editGdsShipTemplate(GdsInfoReqDTO gdsInfoReqDTO)
			throws BusinessException;

	
	/**
	 * 
	 * doGdsVerify:(进行商品上架、删除提交审核操作【适用于批量和单个】).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author gxq 
	 * @param GdsVerifyReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void doGdsVerify(GdsVerifyReqDTO gdsVerifyReqDTO) throws BusinessException;
	/**
	 * 
	 * editGdsVerify:(编辑商品操作审核信息). <br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author gxq 
	 * @param GdsVerifyReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void editGdsVerify(GdsVerifyReqDTO gdsVerifyReqDTO) throws BusinessException;
	/**
	 * 
	 * editGdsVerifyShelved:(编辑商品操作审核信息-已上架商品的线下记录). <br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author cxq 
	 * @param GdsVerifyReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void editGdsVerifyShelved(GdsVerifyReqDTO gdsVerifyReqDTO) throws BusinessException;
	
	public void delAllCache(Long gdsId, List<Long> skuIds) throws BusinessException;
}
