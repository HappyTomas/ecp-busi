package com.zengshi.ecp.goods.dubbo.interfaces;

import com.zengshi.ecp.goods.dubbo.dto.GdsMediaLibReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 媒体操作dubbo服务<br>
 * Date:2015年9月2日下午9:28:50 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public interface IGdsMediaRSV {
    
    /**
     * 创建店铺媒体库. <br/>
     * @param shopId
     * @param shopName
     * @return
     * @throws BusinessException
     */
    void saveGdsMediaLib(GdsMediaLibReqDTO gdsMediaLibReqDTO) throws BusinessException;
    
	/**
	 * 
	 * 保存媒体信息. <br/>
	 * 
	 * @author linwb3
	 * @param gdsMedia
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void saveGdsMedia(GdsMediaReqDTO gdsMediaReqDTO)
			throws BusinessException;

	/**
	 * 
	 * 根据主键ID查询媒体信息. <br/>
	 * 
	 * @author linwb3
	 * @param id
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public GdsMediaRespDTO queryGdsMediaById(GdsMediaReqDTO gdsMediaReqDTO)
			throws BusinessException;


	/**
	 * 
	 * 编辑媒体信息. <br/>
	 * 
	 * @author linwb3
	 * @param GdsProp
	 * @param updateStaff
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void editGdsMedia(GdsMediaReqDTO gdsMediaReqDTO)
			throws BusinessException;

	/**
	 * 
	 * 将指定媒体信息进行失效处理. <br/>
	 * 
	 * @author linwb3
	 * @param id
	 * @param updateStaff
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void disableGdsMedia(GdsMediaReqDTO gdsMediaReqDTO)
			throws BusinessException;

	/**
	 * 
	 * queryGdsInfoListPage:(查询媒体列表分页). <br/>
	 * 
	 * @author linwb3
	 * @param gdsMediaReqDTO
	 * @return
	 * @since JDK 1.6
	 */
	public PageResponseDTO<GdsMediaRespDTO> queryGdsInfoListPage(
			GdsMediaReqDTO gdsMediaReqDTO);

}
