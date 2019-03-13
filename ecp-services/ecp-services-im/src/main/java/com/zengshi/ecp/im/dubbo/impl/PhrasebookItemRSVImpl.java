package com.zengshi.ecp.im.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.im.dubbo.dto.ImPhrasebookItemReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImPhrasebookItemResDTO;
import com.zengshi.ecp.im.dubbo.interfaces.IPhrasebookItemRSV;
import com.zengshi.ecp.im.service.common.interfaces.IPhrasebookItemSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class PhrasebookItemRSVImpl implements IPhrasebookItemRSV {
	
	@Resource
	private IPhrasebookItemSV phrasebookItemSV;

	@Override
	public List<ImPhrasebookItemResDTO> findPhrasebookItemByGroupId(ImPhrasebookItemReqDTO reqDTO)
			throws BusinessException {
		return phrasebookItemSV.findPhrasebookItemByGroupId(reqDTO);
	}

	@Override
	public Long savePhrasebookItem(ImPhrasebookItemReqDTO reqDTO) throws BusinessException {
		return phrasebookItemSV.savePhrasebookItem(reqDTO);
	}

	@Override
	public int deletePhrasebookItemById(ImPhrasebookItemReqDTO reqDTO) throws BusinessException {
		return phrasebookItemSV.deletePhrasebookItemById(reqDTO);
	}

	@Override
	public int updatePhrasebookItemById(ImPhrasebookItemReqDTO reqDTO) throws BusinessException {
		return phrasebookItemSV.updatePhrasebookItemById(reqDTO);
	}

	@Override
	public PageResponseDTO<ImPhrasebookItemResDTO> queryPhrasebookItem(ImPhrasebookItemReqDTO reqDTO)
			throws BusinessException {
		return phrasebookItemSV.queryPhrasebookItem(reqDTO);
	}

	@Override
	public ImPhrasebookItemResDTO queryPhrasebookItemById(ImPhrasebookItemReqDTO reqDTO) throws BusinessException {
		return phrasebookItemSV.queryPhrasebookItemById(reqDTO);
	}

}
