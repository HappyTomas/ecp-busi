package com.zengshi.ecp.goods.service.busi.interfaces.gdsinfoidx;

import java.util.List;

import com.zengshi.ecp.goods.dao.model.GdsGds2PropPropIdx;
import com.zengshi.ecp.goods.dao.model.GdsInfo;
import com.zengshi.ecp.goods.dao.model.GdsMedia;
import com.zengshi.ecp.goods.dao.model.GdsSku2PropPropIdx;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfo;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsGds2CatgCatgIdxReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2CatgReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PropReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 商品，单品索引表操作接口<br>
 * Date:2015年8月25日下午7:30:05 <br>
 * Copyright (c)throws BusinessException 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public interface IGdsInfoIDXSV {

	/**
	 * addGdsInfoIDX:(添加商品索引表信息)throws BusinessException. <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfo
	 * @param catgs
	 * @since JDK 1.6
	 */
	public void addGdsInfoIDX(GdsInfo gdsInfo, List<GdsGds2CatgReqDTO> catgs,List<GdsGds2PropReqDTO> props)
			throws BusinessException;

	/**
	 * 
	 * delGdsInfoIDX:(删除商品索引表信息)throws BusinessException. <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfo
	 * @param catgs
	 * @since JDK 1.6
	 */
	public void delGdsInfoIDX(GdsInfo gdsInfo, List<GdsGds2CatgReqDTO> catgs,List<GdsGds2PropReqDTO> props)
			throws BusinessException;

	/**
	 * 
	 * editGdsInfoIDX:(编辑商品索引表信息)throws BusinessException. <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfo
	 * @param catgs
	 * @since JDK 1.6
	 */
	public void editGdsInfoIDX(GdsInfo gdsInfo, List<GdsGds2CatgReqDTO> catgs,List<GdsGds2PropReqDTO> props)
			throws BusinessException;

	/**
	 * 
	 * addSkuInfoIDX:(添加单品索引表信息)throws BusinessException. <br/>
	 * 
	 * @author linwb3
	 * @param skuInfo
	 * @since JDK 1.6
	 */
	public void addSkuInfoIDX(GdsSkuInfo skuInfo,List<GdsSku2PropReqDTO> props) throws BusinessException;

	/**
	 * 
	 * delSkuInfoIDX:(删除单品索引表信息)throws BusinessException. <br/>
	 * 
	 * @author linwb3
	 * @param skuInfo
	 * @since JDK 1.6
	 */
	public void delSkuInfoIDX(GdsSkuInfo skuInfo,List<GdsSku2PropReqDTO> props) throws BusinessException;

	/**
	 * 
	 * editSkuInfoIDX:(边界单品索引表信息)throws BusinessException. <br/>
	 * 
	 * @author linwb3
	 * @param skuInfo
	 * @since JDK 1.6
	 */
	public void editSkuInfoIDX(GdsSkuInfo skuInfo,List<GdsSku2PropReqDTO> props) throws BusinessException;
	
	/**
	 * 
	 * addGdsMediaIDX:(添加媒体索引表信息)throws BusinessException. <br/>
	 * 
	 * @author linwb3
	 * @param gdsMedia
	 * @since JDK 1.6
	 */
	public void addGdsMediaIDX(GdsMedia gdsMedia) throws BusinessException;

	/**
	 * 
	 * editGdsMediaIDX:(编辑媒体索引表信息)throws BusinessException. <br/>
	 * 
	 * @author linwb3
	 * @param gdsMedia
	 * @since JDK 1.6
	 */
	public void editGdsMediaIDX(GdsMedia gdsMedia) throws BusinessException;

	/**
	 * 
	 * delGdsMediaIDX:(删除媒体索引表信息)throws BusinessException. <br/>
	 * 
	 * @author linwb3
	 * @param gdsMedia
	 * @since JDK 1.6
	 */
	public void delGdsMediaIDX(GdsMedia gdsMedia) throws BusinessException;
	/**
	 * 
	 * queryGds2PropPropIdx:根据gdsId,propId查询有效状态索引. <br/> 
	 * 
	 * @author liyong7
	 * @param gds2PropPropIdx
	 * @return 
	 * @since JDK 1.6
	 */
	public GdsGds2PropPropIdx queryGds2PropPropIdx(GdsGds2PropPropIdx gds2PropPropIdx);
	/**
	 * 
	 * queryGdsSku2PropPropIdx:根据skuId,propId查询出单品与商品索引。
	 * 
	 * @author liyong7
	 * @param gdsSku2PropPropIdx
	 * @return 
	 * @since JDK 1.6
	 */
	public GdsSku2PropPropIdx queryGdsSku2PropPropIdx(GdsSku2PropPropIdx gdsSku2PropPropIdx);
	
	/**
	 * 
	 * updateGds2PropPropIdx:更新商品属性关联索引表。<br/>
	 * 
	 * @author liyong7
	 * @param gds2PropPropIdx 
	 * @since JDK 1.6
	 */
	public void updateGds2PropPropIdx(GdsGds2PropPropIdx gds2PropPropIdx);
	/**
	 * 
	 * updateGdsSku2PropPropIdx:更新单品属性关联索引表。<br/>
	 * 
	 * @author liyong7
	 * @param gdsSku2PropPropIdx 
	 * @since JDK 1.6
	 */
	public void updateGdsSku2PropPropIdx(GdsSku2PropPropIdx gdsSku2PropPropIdx);

	public void delGds2CatgCatgIDX(GdsGds2CatgCatgIdxReqDTO reqDTO);

}
