/** 
 * Project Name:ecp-services-goods 
 * File Name:IGdsMediaSV.java 
 * Package Name:GdsMedia 
 * Date:2015年8月14日上午9:20:52 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.goods.service.busi.interfaces;

import com.zengshi.ecp.goods.dao.model.GdsMedia;
import com.zengshi.ecp.goods.dao.model.GdsMediaLib;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaLibReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Project Name:ecp-services-goods <br>
 * Description: 媒体信息服务接口<br>
 * Date:2015年8月14日上午9:20:52 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version
 * @since JDK 1.6
 */
public interface IGdsMediaSV {
    
    /**
     * 创建店铺媒体库. <br/>
     * @param gdsMediaLibReqDTO
     * @return
     * @throws BusinessException
     */
    void saveGdsMediaLib(GdsMediaLibReqDTO gdsMediaLibReqDTO) throws BusinessException;
    
    /**
     * 根据主键查询店铺媒体库. <br/>
     * @param gdsMediaLibReqDTO
     * @return
     * @throws BusinessException
     */
    GdsMediaLib selectGdsMediaLibByPk(GdsMediaLibReqDTO gdsMediaLibReqDTO) throws BusinessException;

	/**
	 * 
	 * 保存媒体信息. <br/>
	 * 
	 * @author liyong7
	 * @param gdsMedia
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	GdsMedia saveGdsMedia(GdsMedia gdsMedia) throws BusinessException;

	/**
	 * 
	 * 根据主键ID查询媒体信息. <br/>
	 * 
	 * @author liyong7
	 * @param id
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	GdsMedia queryGdsMediaById(Long id) throws BusinessException;

	/**
	 * 
	 * 编辑媒体信息. <br/>
	 * 
	 * @author liyong7
	 * @param GdsProp
	 * @param updateStaff
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	GdsMedia editGdsMedia(GdsMedia gdsMedia, Long updateStaff)
			throws BusinessException;

	/**
	 * 
	 * 将指定媒体信息进行失效处理. <br/>
	 * 
	 * @author liyong7
	 * @param id
	 * @param updateStaff
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	void executeDisableGdsMedia(long id, Long updateStaff)
			throws BusinessException;

	/**
	 * 
	 * 将指定媒体信息进行失效处理. <br/>
	 * 
	 * @author liyong7
	 * @param id
	 * @param updateStaff
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	void executeEnableGdsMedia(long id, Long updateStaff)
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
	public PageResponseDTO<GdsMediaRespDTO> queryGdsMediaListPage(
			GdsMediaReqDTO gdsMediaReqDTO) throws BusinessException;

}
