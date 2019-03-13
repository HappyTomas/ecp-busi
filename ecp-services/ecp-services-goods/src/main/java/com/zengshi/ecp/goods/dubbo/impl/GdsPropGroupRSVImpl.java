package com.zengshi.ecp.goods.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropGroupReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropGroupRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsPropGroupRSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropGroupSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class GdsPropGroupRSVImpl extends AbstractRSVImpl implements IGdsPropGroupRSV{

	@Resource
	private IGdsPropGroupSV gdsPropGroupSV;
	
	
	/**
	 * 新增属性组
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsPropGroupRSV#createGdsPropGroup(com.zengshi.ecp.goods.dubbo.dto.GdsPropGroupReqDTO)
	 */
	@Override
	public void createGdsPropGroup(GdsPropGroupReqDTO dto)
			throws BusinessException {
		paramNullCheck(dto, true,"reqDto");
		paramNullCheck(dto.getGroupName(),"reqDto.groupName");
		gdsPropGroupSV.createGdsPropGroup(dto);
	}

	/**
	 * 查询单个属性组以
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsPropGroupRSV#queryGdsPropGroup(com.zengshi.ecp.goods.dubbo.dto.GdsPropGroupReqDTO)
	 */
	@Override
	public GdsPropGroupRespDTO queryGdsPropGroup(GdsPropGroupReqDTO dto)
			throws BusinessException {
		paramNullCheck(dto, true,"reqDto");
		paramNullCheck(dto.getId(),"reqDto.id");
		return gdsPropGroupSV.queryGdsPropGroup(dto.getId(),GdsConstants.Commons.STATUS_VALID);
	}
	
	
	/**
	 * 查询单个属性组以及属性列表
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsPropGroupRSV#queryGdsPropGroup(com.zengshi.ecp.goods.dubbo.dto.GdsPropGroupReqDTO)
	 */
	@Override
	public GdsPropGroupRespDTO queryGdsPropGroupAndProps(GdsPropGroupReqDTO dto)
			throws BusinessException {
		paramNullCheck(dto, true,"reqDto");
		paramNullCheck(dto.getId(),"reqDto.id");
		return gdsPropGroupSV.queryGdsPropGroupAndProps(dto.getId());
	}
	
	
	

	/**
	 * 编辑属性组
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsPropGroupRSV#editGdsPropGroup(com.zengshi.ecp.goods.dubbo.dto.GdsPropGroupReqDTO)
	 */
	@Override
	public Integer editGdsPropGroup(GdsPropGroupReqDTO dto)
			throws BusinessException {
		paramNullCheck(dto, true,"reqDto");
		paramNullCheck(dto.getId(),"reqDto.id");
		return gdsPropGroupSV.editGdsPropGroup(dto);
	}

	/**
	 * 禁用属性组
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsPropGroupRSV#executeDisableGdsPropGroup(com.zengshi.ecp.goods.dubbo.dto.GdsPropGroupReqDTO)
	 */
	@Override
	public Integer executeDisableGdsPropGroup(GdsPropGroupReqDTO dto)
			throws BusinessException {
		paramNullCheck(dto, true,"reqDto");
		paramNullCheck(dto.getId(),"reqDto.id");
		return gdsPropGroupSV.executeDisableGdsPropGroup(dto.getId(),dto.getStaff().getId());

	}

	/**
	 * 启用属性组
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsPropGroupRSV#executeEnableGdsPropGroup(com.zengshi.ecp.goods.dubbo.dto.GdsPropGroupReqDTO)
	 */
	@Override
	public Integer executeEnableGdsPropGroup(GdsPropGroupReqDTO dto)
			throws BusinessException {
		paramNullCheck(dto, true,"reqDto");
		paramNullCheck(dto.getId(),"reqDto.id");
		return gdsPropGroupSV.executeEnableGdsPropGroup(dto.getId(),dto.getStaff().getId());
	}

	/**
	 * 分页查询属性组
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsPropGroupRSV#queryGdsPropGroupRespDTOPaging(com.zengshi.ecp.goods.dubbo.dto.GdsPropGroupReqDTO)
	 */
	@Override
	public PageResponseDTO<GdsPropGroupRespDTO> queryGdsPropGroupRespDTOPaging(
			GdsPropGroupReqDTO dto) throws BusinessException {
		paramNullCheck(dto, true,"reqDto");
		return gdsPropGroupSV.queryGdsPropGroupRespDTOPaging(dto);
	}

   
}

