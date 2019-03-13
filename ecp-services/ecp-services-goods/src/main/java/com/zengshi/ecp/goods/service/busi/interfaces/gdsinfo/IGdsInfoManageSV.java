package com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo;

import java.util.List;

import com.zengshi.ecp.goods.dao.model.GdsInfo;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfo;
import com.zengshi.ecp.goods.dubbo.dto.GdsVerifyReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoAddReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2CatgReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2MediaReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2PropReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 商品管理接口，主要负责商品的添加，编辑，删除，上下架等影响数据的操作 <br>
 * Date:2015年8月22日下午6:03:36 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public interface IGdsInfoManageSV extends IGeneralSQLSV {

	/**
	 * 
	 * addGdsInfo:(添加商品). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @return
	 * @since JDK 1.6
	 */
	public GdsInfoRespDTO addGdsInfo(GdsInfoAddReqDTO gdsInfoAddReqDTO)
			throws BusinessException;


	/**
	 * 
	 * editGdsInfo:(编辑商品). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @return 
	 * @since JDK 1.6
	 */
	public List<Long> editGdsInfoAndReference(GdsInfoAddReqDTO gdsInfoAddReqDTO)
			throws BusinessException;

	/**
	 * 
	 * editGdsInfo:(编辑商品). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @return 
	 * @since JDK 1.6
	 */
	public GdsInfo editGdsInfo(GdsInfoReqDTO gdsInfoReqDTO)
			throws BusinessException;

	/**
	 * 
	 * delGdsInfo:(删除商品). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @return 
	 * @since JDK 1.6
	 */
	public List<Long> deleteGdsInfo(GdsInfoReqDTO gdsInfoReqDTO)
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
	 * doGdsShelves:(商品上架，下架).不发送增量索引 <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @return 
	 * @since JDK 1.6
	 */
	public List<Long> executeGdsShelves(GdsInfoReqDTO gdsInfoReqDTO)
			throws BusinessException;

	/**
	 * 
	 * doGdsShelves:(批量商品上架，下架). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @since JDK 1.6
	 */
	public void batchExecuteGdsShelves(GdsInfoReqDTO gdsInfoReqDTO)
			throws BusinessException;

	/**
	 * 
	 * editGdsShipTemplate:(新增，编辑商品运费模板). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @return 
	 * @since JDK 1.6
	 */
	public List<Long> editGdsShipTemplate(GdsInfoReqDTO gdsInfoReqDTO)
			throws BusinessException;


	/**
     * 
     * addGdsVerify:(添加商品操作审核信息). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author gxq 
     * @param GdsVerifyReqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void addGdsVerify(GdsVerifyReqDTO gdsVerifyReqDTO) throws BusinessException;
    
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
    public List<GdsSkuInfo> editGdsVerify(GdsVerifyReqDTO gdsVerifyReqDTO) throws BusinessException;

    /**
     * 
     * addGdsVerifyOffline:(添加商品操作审核信息-已上架商品的线下记录). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author cxq 
     * @param GdsVerifyReqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int addGdsVerifyOffline(GdsVerifyReqDTO gdsVerifyReqDTO) throws BusinessException;

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
    public List<GdsSkuInfo> editGdsVerifyShelved(GdsVerifyReqDTO gdsVerifyReqDTO) throws BusinessException;
    
    public void delAllCache(Long gdsId, List<Long> skuIds);
}
