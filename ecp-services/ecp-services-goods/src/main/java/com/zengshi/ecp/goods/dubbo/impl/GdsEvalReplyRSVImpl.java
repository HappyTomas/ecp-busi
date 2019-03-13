package com.zengshi.ecp.goods.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReplyReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReplyRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalReplyRSV;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsEvalReplySV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2015-9-24上午10:16:57  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
public class GdsEvalReplyRSVImpl extends AbstractRSVImpl implements
		IGdsEvalReplyRSV {

	@Resource(name="gdsEvalReplySV")
	private IGdsEvalReplySV gdsEvalReplySV;
	
	
	@Override
	public GdsEvalReplyRespDTO addEvalReply(GdsEvalReplyReqDTO reqDTO)
			throws BusinessException {
          paramNullCheck(reqDTO, true);
          paramCheck(new Object[]{reqDTO.getEvalId(),reqDTO.getGdsId(),reqDTO.getOrderId(),reqDTO.getShopId(),reqDTO.getStaffId()}, 
        		  	 new String[]{"evalId","gdsId","orderId","shopId","staffId"});
          return gdsEvalReplySV.addEvalReply(reqDTO);
	}

	@Override
	public long executeCountReplyByEvalPK(GdsEvalReplyReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO);
		paramNullCheck(reqDTO.getEvalId(), "evalId");
		return gdsEvalReplySV.executeCountReplyByEvalPK(reqDTO.getEvalId(), GdsConstants.Commons.STATUS_VALID);
	}

	@Override
	public boolean queryExistReply(GdsEvalReplyReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO);
		paramNullCheck(reqDTO.getEvalId(), "evalId");
		return gdsEvalReplySV.queryExistReply(reqDTO.getEvalId(), GdsConstants.Commons.STATUS_VALID);
	}

	@Override
	public boolean queryExistSubReply(GdsEvalReplyReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO);
		paramNullCheck(reqDTO.getId(), "id");
		return gdsEvalReplySV.queryExistReply(reqDTO.getId(), GdsConstants.Commons.STATUS_VALID);
	}

	@Override
	public int deleteReplyByEvalId(GdsEvalReplyReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO);
		paramNullCheck(reqDTO.getEvalId(), "evalId");
		return gdsEvalReplySV.deleteReplyByEvalId(reqDTO);
	}

	@Override
	public void deleteEvalReplyByPK(GdsEvalReplyReqDTO reqDTO)
			throws BusinessException {
	    gdsEvalReplySV.deleteEvalReplyByPK(reqDTO);
	}

	@Override
	public PageResponseDTO<GdsEvalReplyRespDTO> queryGdsEvalReplyRespDTOPaging(
			GdsEvalReplyReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO);
		return gdsEvalReplySV.queryGdsEvalReplyRespDTOPaging(reqDTO);
	}

	@Override
	public List<GdsEvalReplyRespDTO> querySubReplyByReplyId(GdsEvalReplyReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO);
		paramNullCheck(reqDTO,"id");
		return gdsEvalReplySV.querySubReplyByReplyId(reqDTO.getId(), GdsConstants.Commons.STATUS_VALID);
	}

	@Override
	public void deleteSubReplyByReplyId(GdsEvalReplyReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO);
		paramNullCheck(reqDTO.getId(),"id");
		gdsEvalReplySV.deleteSubReplyByReplyId(reqDTO);
	}

	@Override
	public GdsEvalReplyRespDTO queryReplyByPK(GdsEvalReplyReqDTO reqDTO)
			throws BusinessException {
		return	gdsEvalReplySV.queryReplyByPK(reqDTO);
	}

}

