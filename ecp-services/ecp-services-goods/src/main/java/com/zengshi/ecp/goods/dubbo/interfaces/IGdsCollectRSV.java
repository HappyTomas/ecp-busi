package com.zengshi.ecp.goods.dubbo.interfaces;

import com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IGdsCollectRSV {
	/**
	 * 
	 * 增加收藏。<br/>
	 * 
	 * @author linwb3
	 * @param dto
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	Long addGdsCollect(GdsCollectReqDTO reqDTO) throws BusinessException;

	/**
	 * 
	 * 收藏信息分页查询。
	 * 
	 * @author linwb3
	 * @param dto
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	PageResponseDTO<GdsCollectRespDTO> queryGdsCollectRespDTOPagingByGds(
			GdsCollectReqDTO reqDTO) throws BusinessException;
	
	/**
     * 
     * 店铺维度下，商品用户收藏数查询统计。店铺、商品、单品主键必传。
     * 
     * @author linwb3
     * @param dto
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    long countGdsCollectByShop(
            GdsCollectReqDTO reqDTO) throws BusinessException;
	
	/**
     * 
     * 收藏信息分页查询。includeStaffCount设置为true则GdsCollectRespDTO同时返回商品收藏用户数统计。
     * 
     * @author linwb3
     * @param dto
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    PageResponseDTO<GdsCollectRespDTO> queryGdsCollectRespDTOPagingByShop(
            GdsCollectReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * 收藏信息分页查询。
     * 
     * @author linwb3
     * @param dto
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    PageResponseDTO<GdsCollectRespDTO> queryGdsCollectRespDTOPagingByStaff(
            GdsCollectReqDTO reqDTO) throws BusinessException;


	/**
	 * 
	 * 根据主键删除收藏信息
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
	 * 根据主键查询收藏.
	 * 
	 * @author linwb3
	 * @param id
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	GdsCollectRespDTO queryGdsCollectByPK(GdsCollectReqDTO reqDTO) throws BusinessException;
}
