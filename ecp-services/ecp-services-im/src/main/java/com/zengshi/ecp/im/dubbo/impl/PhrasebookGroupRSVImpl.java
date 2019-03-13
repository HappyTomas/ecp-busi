package com.zengshi.ecp.im.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.im.dubbo.dto.ImPhrasebookGroupReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImPhrasebookGroupResDTO;
import com.zengshi.ecp.im.dubbo.interfaces.IPhrasebookGroupRSV;
import com.zengshi.ecp.im.service.common.interfaces.IPhrasebookGroupSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class PhrasebookGroupRSVImpl implements IPhrasebookGroupRSV {
	
	@Resource
	private IPhrasebookGroupSV phrasebookGroupSV;

	@Override
	public List<ImPhrasebookGroupResDTO> findPhrasebookGroupByShopId(ImPhrasebookGroupReqDTO reqDTO)
			throws BusinessException {
		return phrasebookGroupSV.findPhrasebookGroupByShopId(reqDTO);
	}

	@Override
	public PageResponseDTO<ImPhrasebookGroupResDTO> queryPhrasebookGroup(ImPhrasebookGroupReqDTO reqDTO)
			throws BusinessException {
		return phrasebookGroupSV.queryPhrasebookGroup(reqDTO);
	}

	@Override
	public int deletePhrasebookGroupById(ImPhrasebookGroupReqDTO reqDTO) throws BusinessException {
		return phrasebookGroupSV.deletePhrasebookGroupById(reqDTO);
	}

	@Override
	public Long savePhrasebookGroup(ImPhrasebookGroupReqDTO reqDTO) throws BusinessException {
		return phrasebookGroupSV.savePhrasebookGroup(reqDTO);
	}

	@Override
	public int updatePhrasebookGroupById(ImPhrasebookGroupReqDTO reqDTO) throws BusinessException {
		return phrasebookGroupSV.updatePhrasebookGroupById(reqDTO);
	}

	@Override
	public ImPhrasebookGroupResDTO queryPhrasebookGroupById(ImPhrasebookGroupReqDTO reqDTO) throws BusinessException {
		return phrasebookGroupSV.queryPhrasebookGroupById(reqDTO);
	}

}
