package com.zengshi.ecp.unpf.dubbo.impl.gdssend;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dao.model.UnpfGdsUnsend;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsUnsendHisReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsUnsendReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsUnsendRespDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.gdssend.IUnpfGdsUnsendHisRSV;
import com.zengshi.ecp.unpf.dubbo.interfaces.gdssend.IUnpfGdsUnsendRSV;
import com.zengshi.ecp.unpf.dubbo.util.UnpfConstants;
import com.zengshi.ecp.unpf.service.busi.good.send.interfaces.IUnpfGdsUnsendSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
* @author  lisp: 
* @date 创建时间：2016年11月21日 下午8:04:38 
* @version 1.0 
**/
public class UnpfGdsUnsendRSVImpl implements IUnpfGdsUnsendRSV {
	
	@Resource
	private IUnpfGdsUnsendSV unpfGdsUnsendSV;
	
	@Resource
	private IUnpfGdsUnsendHisRSV unpfGdsUnsendHisRSV;

	@Override
	public void saveGdsUnsend(UnpfGdsUnsendReqDTO unpfGdsUnsendReqDTO) throws BusinessException {
		if(unpfGdsUnsendReqDTO==null){
			throw new BusinessException("unpf.100001");
		}
		if(StringUtil.isEmpty(unpfGdsUnsendReqDTO.getShopId())){
			throw new BusinessException("unpf.100003");
		}
		if(StringUtil.isEmpty(unpfGdsUnsendReqDTO.getGdsId())){
			throw new BusinessException("unpf.100020");
		}
		if(StringUtil.isEmpty(unpfGdsUnsendReqDTO.getStatus())){
			throw new BusinessException("unpf.100018");
		}
		if(StringUtil.isEmpty(unpfGdsUnsendReqDTO.getCreateTime())){
			unpfGdsUnsendReqDTO.setCreateTime(DateUtil.getSysDate());
		}
		if(StringUtil.isEmpty(unpfGdsUnsendReqDTO.getCreateStaff())){
			unpfGdsUnsendReqDTO.setCreateStaff(unpfGdsUnsendReqDTO.getStaff().getId());
		}
		unpfGdsUnsendSV.saveGdsUnsend(unpfGdsUnsendReqDTO);
	}

	@Override
	public void deleteGdsUnsendById(UnpfGdsUnsendReqDTO unpfGdsUnsendReqDTO) throws BusinessException {
		if(unpfGdsUnsendReqDTO==null){
			throw new BusinessException("unpf.100001");
		}
		if(StringUtil.isEmpty(unpfGdsUnsendReqDTO.getId())){
			throw new BusinessException("unpf.100002");
		}
		unpfGdsUnsendSV.deleteGdsUnsendById(unpfGdsUnsendReqDTO);
	}

	
	@Override
	public void deleteGdsUnsendByGdsId(UnpfGdsUnsendReqDTO unpfGdsUnsendReqDTO) throws BusinessException {
		if(unpfGdsUnsendReqDTO==null){
			throw new BusinessException("unpf.100001");
		}
		if(StringUtil.isEmpty(unpfGdsUnsendReqDTO.getGdsId())){
			throw new BusinessException("unpf.100020");
		}
		unpfGdsUnsendSV.deleteGdsUnsendByGdsId(unpfGdsUnsendReqDTO);
	}
	
	@Override
	public UnpfGdsUnsendRespDTO queryGdsUnsendById(UnpfGdsUnsendReqDTO unpfGdsUnsendReqDTO) throws BusinessException {
		if(unpfGdsUnsendReqDTO==null){
			throw new BusinessException("unpf.100001");
		}
		if(StringUtil.isEmpty(unpfGdsUnsendReqDTO.getId())){
			throw new BusinessException("unpf.100002");
		}
		return unpfGdsUnsendSV.queryGdsUnsendById(unpfGdsUnsendReqDTO);
	}

	@Override
	public PageResponseDTO<UnpfGdsUnsendRespDTO> queryGdsUnsendForPage(UnpfGdsUnsendReqDTO unpfGdsUnsendReqDTO)
			throws BusinessException {
		if(unpfGdsUnsendReqDTO==null){
			throw new BusinessException("unpf.100001");
		}
		if(StringUtil.isEmpty(unpfGdsUnsendReqDTO.getPageSize())){
			unpfGdsUnsendReqDTO.setPageSize(UnpfConstants.PAGESIZE);
		}
		if(StringUtil.isEmpty(unpfGdsUnsendReqDTO.getPageNo())){
			unpfGdsUnsendReqDTO.setPageNo(UnpfConstants.PAGENO);
		}
		return unpfGdsUnsendSV.queryGdsUnsendForPage(unpfGdsUnsendReqDTO);
	}

	@Override
	public void saveUnsendGds(UnpfGdsUnsendReqDTO unpfGdsUnsendReqDTO) throws BusinessException {

		if(StringUtil.isEmpty(unpfGdsUnsendReqDTO.getGdsId())){
			throw new BusinessException("unpf.100020");
		}
		List<UnpfGdsUnsend> gdsList = unpfGdsUnsendSV.queryGdsUnsendByGdsId(unpfGdsUnsendReqDTO);
		if(CollectionUtils.isEmpty(gdsList)||gdsList.size()<=0){
			this.saveGdsUnsend(unpfGdsUnsendReqDTO);
		}
	}



}


