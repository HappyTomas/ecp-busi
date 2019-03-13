package com.zengshi.ecp.goods.dubbo.interfaces;

import com.zengshi.ecp.goods.dubbo.dto.GdsVerifyReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 单品管理dubbo服务接口<br>
 * Date:2015年8月30日下午4:37:30 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public interface IGdsSkuInfoManageRSV {
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
	 * @since JDK 1.6
	 */
	public void delGdsSkuInfo(GdsSkuInfoReqDTO gdsSkuInfoReqDTO)
			throws BusinessException;

	/**
	 * 
	 * doSkuShelves:(单品上架，下架). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @since JDK 1.6
	 */
	public void doSkuShelves(GdsSkuInfoReqDTO gdsSkuInfoReqDTO)
			throws BusinessException;
	/**
     * 
     * doGdsVerify:(进行单品上架、删除提交审核操作【适用于批量和单个】).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author gxq 
     * @param GdsVerifyReqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void doSkuVerify(GdsVerifyReqDTO gdsVerifyReqDTO) throws BusinessException;
    /**
     * 
     * editGdsVerify:(编辑单品操作审核信息). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author gxq 
     * @param GdsVerifyReqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void editSkuVerify(GdsVerifyReqDTO gdsVerifyReqDTO) throws BusinessException;
}
