package com.zengshi.ecp.goods.service.busi.interfaces;

import com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 商品收藏操作接口<br>
 * Date:2015年9月4日上午11:39:32  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version  
 * @since JDK 1.6
 */
public interface IGdsCollectSV {

	/**
	 * 
	 * 增加收藏。<br/>
	 * 
	 * @author linwb3
	 * @param dto
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	Long addGdsCollect(GdsCollectReqDTO dto) throws BusinessException;
	
	/**
     * 
     * 店铺维度收藏信息查询统计。
     * 
     * @author linwb3
     * @param dto
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    long countGdsCollectByShop(GdsCollectReqDTO dto)
            throws BusinessException;

	/**
	 * 
	 * 店铺维度收藏信息分页查询。
	 * 
	 * @author linwb3
	 * @param dto
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	PageResponseDTO<GdsCollectRespDTO> queryGdsCollectRespDTOPagingByShop(GdsCollectReqDTO dto)
			throws BusinessException;
	
	/**
     * 
     * 用户维度收藏信息分页查询。
     * 
     * @author linwb3
     * @param dto
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    PageResponseDTO<GdsCollectRespDTO> queryGdsCollectRespDTOPagingByStaff(GdsCollectReqDTO dto)
            throws BusinessException;
    
    /**
     * 
     * 商品维度收藏信息分页查询。
     * 
     * @author linwb3
     * @param dto
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    PageResponseDTO<GdsCollectRespDTO> queryGdsCollectRespDTOPagingByGds(GdsCollectReqDTO dto)
            throws BusinessException;

	/**
	 * 
	 * 根据主键查询收藏信息.直接查主表，不走索引表。
	 * 
	 * @author linwb3
	 * @param reqDTO
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	GdsCollectRespDTO queryGdsCollectByPK(GdsCollectReqDTO reqDTO)
			throws BusinessException;

	/**
	 * 
	 * 根据主键删除收藏信息.
	 * 
	 * @author linwb3
	 * @param reqDTO
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	int deleteGdsCollectByPK(GdsCollectReqDTO reqDTO) throws BusinessException;

	/**
	 * 
	 * 编辑收藏。<br/>
	 * 
	 * @author linwb3
	 * @param reqDTO
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	int editGdsCollect(GdsCollectReqDTO reqDTO) throws BusinessException;

	/**
	 * 
	 * 根据主键查询收藏.直接查主表，不走索引表。
	 * 
	 * @author linwb3
	 * @param id
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	GdsCollectRespDTO queryGdsCollectByPK(Long id) throws BusinessException;

}
