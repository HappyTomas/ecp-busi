package com.zengshi.ecp.goods.dubbo.interfaces;

import com.zengshi.ecp.goods.dubbo.dto.GdsPropGroupReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropGroupRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 属性组dubbo服务<br>
 * Date:2015年9月3日下午8:54:52 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public interface IGdsPropGroupRSV {

	/**
	 * 
	 * 添加属性组信息. <br/>
	 * 
	 * @author linwb3
	 * @param dto
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	void createGdsPropGroup(GdsPropGroupReqDTO dto) throws BusinessException;

	/**
	 * 
	 * 根据主键获取属性组信息.
	 * 
	 * @author linwb3
	 * @param id
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	GdsPropGroupRespDTO queryGdsPropGroup(GdsPropGroupReqDTO dto)
			throws BusinessException;

	/**
	 * 
	 * 根据主键获取属性组以及对应属性列表信息
	 * 
	 * @author linwb3
	 * @param id
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	GdsPropGroupRespDTO queryGdsPropGroupAndProps(GdsPropGroupReqDTO dto)
			throws BusinessException;

	/**
	 * 
	 * 编辑属性组信息.<br/>
	 * 该方法可以用于dubbo层,属性组ID,属性组名称允许编辑.
	 * 
	 * @author linwb3
	 * @param dto
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	Integer editGdsPropGroup(GdsPropGroupReqDTO dto) throws BusinessException;

	/**
	 * 
	 * 禁用商品属性组.<br/>
	 * 
	 * @author linwb3
	 * @param id
	 * @param updateStaff
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	Integer executeDisableGdsPropGroup(GdsPropGroupReqDTO dto)
			throws BusinessException;

	/**
	 * 
	 * 启用商品属性组.<br/>
	 * 
	 * @author linwb3
	 * @param id
	 * @param updateStaff
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	Integer executeEnableGdsPropGroup(GdsPropGroupReqDTO dto)
			throws BusinessException;

	/**
	 * 
	 * 分页查询属性组信息<br/>
	 * 
	 * @author linwb3
	 * @param dto
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	PageResponseDTO<GdsPropGroupRespDTO> queryGdsPropGroupRespDTOPaging(
			GdsPropGroupReqDTO dto) throws BusinessException;
}
