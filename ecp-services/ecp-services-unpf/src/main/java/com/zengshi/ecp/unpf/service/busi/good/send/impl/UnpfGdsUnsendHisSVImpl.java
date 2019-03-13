package com.zengshi.ecp.unpf.service.busi.good.send.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dao.mapper.busi.UnpfGdsUnsendHisMapper;
import com.zengshi.ecp.unpf.dao.model.UnpfGdsUnsendHis;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsUnsendHisReqDTO;
import com.zengshi.ecp.unpf.service.busi.good.send.interfaces.IUnpfGdsUnsendHisSV;
import com.zengshi.paas.utils.ObjectCopyUtil;


/**
* @author  lisp: 
* @date 创建时间：2016年11月21日 下午8:09:50 
* @version 1.0 
* @parameter  
* @since  
* @return  */
public class UnpfGdsUnsendHisSVImpl implements IUnpfGdsUnsendHisSV {
	
	@Resource
	private UnpfGdsUnsendHisMapper unpfGdsUnsendHisMapper;
	
	@Resource(name="seq_unpf_gds_unsend_his_id")
	private PaasSequence seq_unpf_gds_unsend_his_id;

	@Override
	public void saveGdsUnsendHis(UnpfGdsUnsendHisReqDTO unpfGdsUnsendHisReqDTO) throws BusinessException {

		UnpfGdsUnsendHis unpfGdsUnsendHis = new UnpfGdsUnsendHis();
		ObjectCopyUtil.copyObjValue(unpfGdsUnsendHisReqDTO, unpfGdsUnsendHis, null, false);
		unpfGdsUnsendHis.setId(seq_unpf_gds_unsend_his_id.nextValue());
		if(unpfGdsUnsendHis.getCreateStaff()==null){
			unpfGdsUnsendHis.setCreateStaff(unpfGdsUnsendHisReqDTO.getStaff().getId());
		}
		unpfGdsUnsendHisMapper.insert(unpfGdsUnsendHis);
	}

}


