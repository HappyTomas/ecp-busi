package com.zengshi.ecp.goods.service.busi.interfaces.price;

import java.util.List;
import java.util.Map;

import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.goods.dao.model.GdsSku2Price;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PriceReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PriceRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.price.CartItemPriceInfo;
import com.zengshi.ecp.goods.dubbo.dto.price.GdsPriceCalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.price.GdsPriceCalRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.price.GdsPriceInfoResp;
import com.zengshi.ecp.goods.dubbo.dto.price.GdsPriceTypeRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 商品价格统一对外服务接口<br>
 * Date:2015年8月30日下午3:25:04 <br>
 * Copyright (c)throws BusinessException 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public interface IGdsPriceSV extends IGeneralSQLSV {

	/**
	 * 
	 * saveGdsSkuPrice:(保存商品/单品价格关系). <br/> 
	 * 
	 * @author linwb3
	 * @param sku2PriceReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void saveGdsSkuPrice(GdsSku2PriceReqDTO sku2PriceReqDTO)
			throws BusinessException;
	
	
	/**
	 * 
	 * caculatePrice:(价格计算). <br/> 
	 * 
	 * @author linwb3
	 * @param reqDto
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public Map<Long,CartItemPriceInfo> caculatePrice(ROrdCartsCommRequest reqDtos) throws BusinessException;
	
	
	/**
	 * 
	 * caculatePrice:(价格计算). <br/> 
	 * 
	 * @author linwb3
	 * @param reqDto
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public Long caculatePrice(Map<String,Object> params) throws BusinessException;

	/**
	 * 
	 * validatePrice:(价格校验). <br/> 
	 * 
	 * @author linwb3
	 * @param reqDto
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public ROrdCartsChkResponse validatePrice(ROrdCartsCommRequest reqDtos) throws BusinessException;

	
	/**
	 * 
	 * editGdsSkuPrice:(编辑商品/单品价格). <br/> 
	 * 
	 * @author linwb3
	 * @param sku2PriceReqDTOs
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void editGdsSkuPrice(GdsSku2PriceReqDTO sku2PriceReqDTOs)
			throws BusinessException;
	
	/**
	 * 
	 * queryGdsSkuPriceByPK:(获取价格主键获取价格信息). <br/> 
	 * 
	 * @author linwb3
	 * @param sku2PriceReqDTOs
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public GdsPriceInfoResp queryGdsSkuPriceByPK(GdsSku2PriceReqDTO sku2PriceReqDTOs)
			throws BusinessException;

	/**
	 * 
	 * delGdsSkuPrice:(删除单品商品价格关系). <br/> 
	 * 
	 * @author linwb3
	 * @param sku2PriceReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void delGdsSkuPrice(GdsSku2PriceReqDTO sku2PriceReqDTO)
			throws BusinessException;

	/**
	 * 
	 * delAllPrice:(删除单品/商品  所有价格关系以及价格). <br/> 
	 * 
	 * @author linwb3
	 * @param sku2PriceReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void delAllPrice(GdsSku2PriceReqDTO sku2PriceReqDTO)
			throws BusinessException;


	/**
	 * 
	 * queryAllPriceType:(获取所有价格类型). <br/> 
	 * 
	 * @author linwb3
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public List<GdsPriceTypeRespDTO> queryAllPriceType()
			throws BusinessException;

	/**
	 * 
	 * queryGdsSkuPriceList:(查询单品/商品 价格关系列表). <br/> 
	 * 
	 * @author linwb3
	 * @param sku2PriceReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public List<GdsSku2PriceRespDTO> queryGdsSkuPriceList(
			GdsSku2PriceReqDTO sku2PriceReqDTO) throws BusinessException;


	/**
	 * 
	 * getDeaultPrice:(获取商品默认价格). <br/> 
	 * 
	 * @author linwb3
	 * @param skuInfoReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public Long getDeaultPrice(GdsSkuInfoReqDTO skuInfoReqDTO)
			throws BusinessException;
	
	/**
	 * 
	 * caculate:(单品价格计算). <br/> 
	 * 
	 * @author linwb3
	 * @param reqDto
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public GdsPriceCalRespDTO caculatePrice(GdsPriceCalReqDTO reqDto) throws BusinessException;


	public List<GdsSku2Price> queryGdsSkuPriceModelList(GdsSku2PriceReqDTO sku2PriceReqDTO);

}
