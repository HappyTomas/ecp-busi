package com.zengshi.ecp.unpf.service.busi.msgSyc.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.unpf.dao.mapper.busi.UnpfMsgSycHisMapper;
import com.zengshi.ecp.unpf.dao.mapper.busi.UnpfMsgSycMapper;
import com.zengshi.ecp.unpf.dao.model.UnpfMsgSycCriteria;
import com.zengshi.ecp.unpf.dao.model.UnpfMsgSycCriteria.Criteria;
import com.zengshi.ecp.unpf.dao.model.UnpfMsgSycHisWithBLOBs;
import com.zengshi.ecp.unpf.dao.model.UnpfMsgSycWithBLOBs;
import com.zengshi.ecp.unpf.dubbo.dto.msgSyc.UnpfMsgSycReqDTO;
import com.zengshi.ecp.unpf.service.busi.msgSyc.interfaces.IUnpfMsgSycSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;

/**
* @author  lisp: 
* @date 创建时间：2016年11月15日 上午11:03:40 
* @version 1.0 
* @since  1.0
**/
public class UnpfMsgSycSVImpl extends GeneralSQLSVImpl implements IUnpfMsgSycSV {

	 @Resource
     private UnpfMsgSycMapper unpfMsgSycMapper;
	
	 @Resource
     private UnpfMsgSycHisMapper unpfMsgSycHisMapper;
	
	 @Resource(name = "seq_unpf_msg_syc_id")
	 private Sequence seq_unpf_msg_syc_id;
	 
	 @Resource(name = "seq_unpf_msg_syc_his_id")
	 private Sequence seq_unpf_msg_syc_his_id;

	 /**
		* 保存同步信息
		* 
		* @author lisp
		* @param UnpfMsgSycReqDTO
		* @return 
		* @throws 
		*/
	@Override
	public void saveUnpfMsgSyc(UnpfMsgSycReqDTO unpfMsgSycReqDTO) throws BusinessException {
		UnpfMsgSycWithBLOBs unpfMsgSyc = new UnpfMsgSycWithBLOBs();
		ObjectCopyUtil.copyObjValue(unpfMsgSycReqDTO, unpfMsgSyc, null, false);
		if(StringUtil.isEmpty(unpfMsgSycReqDTO.getId())){
			unpfMsgSyc.setId(seq_unpf_msg_syc_id.nextValue());
		}else{
			unpfMsgSyc.setId(unpfMsgSycReqDTO.getId());
		}
		unpfMsgSycMapper.insert(unpfMsgSyc);
	}

	/**
	* 更新同步信息
	* 
	* @author lisp
	* @param UnpfMsgSycReqDTO
	* @return 
	* @throws 
	*/
	@Override
	public void updateUnpfMsgSyc(UnpfMsgSycReqDTO unpfMsgSycReqDTO) throws BusinessException {

		UnpfMsgSycCriteria example = new UnpfMsgSycCriteria();
		UnpfMsgSycWithBLOBs unpfMsgSyc = new UnpfMsgSycWithBLOBs();
		unpfMsgSyc.setErrors(unpfMsgSycReqDTO.getErrors());
		unpfMsgSyc.setStatus(unpfMsgSycReqDTO.getStatus());
		unpfMsgSyc.setShopId(unpfMsgSycReqDTO.getShopId());
		unpfMsgSyc.setTradeType(unpfMsgSycReqDTO.getTradeType());
		unpfMsgSyc.setPlatType(unpfMsgSycReqDTO.getPlatType());
		unpfMsgSyc.setMsg(unpfMsgSycReqDTO.getMsg());
		unpfMsgSyc.setUpdateStaff(unpfMsgSycReqDTO.getUpdateStaff());
		unpfMsgSyc.setUpdateTime(DateUtil.getSysDate());
		Criteria cr  = example.createCriteria();
		cr.andIdEqualTo(unpfMsgSycReqDTO.getId());
		unpfMsgSycMapper.updateByExampleSelective(unpfMsgSyc, example);
	}

	/**
	* 删除同步信息，并保存到同步信息历史表中去
	* 
	* @author lisp
	* @param UnpfMsgSycReqDTO
	* @return 
	* @throws 
	*/
	@Override
	public void deleteUnpfMsgSyc(UnpfMsgSycReqDTO unpfMsgSycReqDTO) throws BusinessException {
		UnpfMsgSycWithBLOBs unpfMsgSyc = unpfMsgSycMapper.selectByPrimaryKey(unpfMsgSycReqDTO.getId());
		UnpfMsgSycHisWithBLOBs unpfMsgSycHis  = new UnpfMsgSycHisWithBLOBs();
		ObjectCopyUtil.copyObjValue(unpfMsgSyc, unpfMsgSycHis, null, false);
		unpfMsgSycHis.setId(seq_unpf_msg_syc_his_id.nextValue());
		unpfMsgSycHis.setMsgId(unpfMsgSyc.getId());
		unpfMsgSycHisMapper.insert(unpfMsgSycHis);
		unpfMsgSycMapper.deleteByPrimaryKey(unpfMsgSycReqDTO.getId());
	}

	@Override
	public Long getMsgSycId() throws BusinessException {
		return seq_unpf_msg_syc_id.nextValue();
	}

}


