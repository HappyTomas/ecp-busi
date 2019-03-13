package com.zengshi.ecp.unpf.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.unpf.dao.mapper.common.UnpfShopCfgMapper;
import com.zengshi.ecp.unpf.dao.model.UnpfShopCfg;
import com.zengshi.ecp.unpf.dao.model.UnpfShopCfgCriteria;
import com.zengshi.ecp.unpf.dao.model.UnpfShopCfgCriteria.Criteria;
import com.zengshi.ecp.unpf.dubbo.dto.cfg.UnpfShopCfgReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.cfg.UnpfShopCfgRespDTO;
import com.zengshi.ecp.unpf.service.common.interfaces.IUnpfShopCfgSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
* @author  lisp: 
* @date 创建时间：2016年11月25日 上午10:31:40 
* @version 1.0 
**/
public class UnpfShopCfgSVImpl extends GeneralSQLSVImpl implements IUnpfShopCfgSV {
	
	@Resource
	private UnpfShopCfgMapper unpfShopCfgMapper;
	
	@Resource(name="seq_unpf_shop_cfg_id")
	private PaasSequence seq_unpf_shop_cfg_id;
	
	@Override
	public void saveShopCfg(UnpfShopCfgReqDTO unpfShopCfgReqDTO) throws BusinessException {
		UnpfShopCfg unpfShopCfg = new UnpfShopCfg();
		ObjectCopyUtil.copyObjValue(unpfShopCfgReqDTO, unpfShopCfg, null, false);
		unpfShopCfg.setId(seq_unpf_shop_cfg_id.nextValue());
		unpfShopCfgMapper.insert(unpfShopCfg);
	}

	@Override
	public void updateShopCfg(UnpfShopCfgReqDTO unpfShopCfgReqDTO) throws BusinessException {

		UnpfShopCfgCriteria example = new UnpfShopCfgCriteria();
		Criteria cr = example.createCriteria();
		cr.andIdEqualTo(unpfShopCfgReqDTO.getId());
		UnpfShopCfg unpfShopCfg = new UnpfShopCfg();
		unpfShopCfg.setInputValue(unpfShopCfgReqDTO.getInputValue());
		unpfShopCfg.setUpdateStaff(unpfShopCfgReqDTO.getUpdateStaff());
		unpfShopCfg.setUpdateTime(unpfShopCfgReqDTO.getUpdateTime());
		unpfShopCfgMapper.updateByExampleSelective(unpfShopCfg, example);
	}

	@Override
	public UnpfShopCfgRespDTO queryShopCfgById(UnpfShopCfgReqDTO unpfShopCfgReqDTO) throws BusinessException {
		UnpfShopCfgRespDTO unpfShopCfgRespDTO = new UnpfShopCfgRespDTO();
		UnpfShopCfg unpfShopCfg = unpfShopCfgMapper.selectByPrimaryKey(unpfShopCfgReqDTO.getId());
		ObjectCopyUtil.copyObjValue(unpfShopCfg, unpfShopCfgRespDTO, null, false);
		return unpfShopCfgRespDTO;
	}

	@Override
	public List<UnpfShopCfgRespDTO> queryShopCfgList(UnpfShopCfgReqDTO unpfShopCfgReqDTO) throws BusinessException {
		
		List<UnpfShopCfgRespDTO> list = new ArrayList<UnpfShopCfgRespDTO>();
		UnpfShopCfgCriteria example = new UnpfShopCfgCriteria();
		this.initShopCfg(unpfShopCfgReqDTO, example);
		List<UnpfShopCfg> unpfShopCfgs = unpfShopCfgMapper.selectByExampleWithBLOBs(example);
		if(!CollectionUtils.isEmpty(unpfShopCfgs)){
			UnpfShopCfgRespDTO unpfShopCfgRespDTO = new UnpfShopCfgRespDTO();
			for (UnpfShopCfg unpfShopCfg : unpfShopCfgs) {
				ObjectCopyUtil.copyObjValue(unpfShopCfg, unpfShopCfgRespDTO, null, false);
				list.add(unpfShopCfgRespDTO);
			}
		}
		return list;
	}

	@Override
	public PageResponseDTO<UnpfShopCfgRespDTO> queryShopCfgForPage(UnpfShopCfgReqDTO unpfShopCfgReqDTO)
			throws BusinessException {
		UnpfShopCfgCriteria example = new UnpfShopCfgCriteria();
		example.setLimitClauseCount(unpfShopCfgReqDTO.getPageSize());
		example.setLimitClauseStart(unpfShopCfgReqDTO.getStartRowIndex());
		example.setOrderByClause("id desc");
		this.initShopCfg(unpfShopCfgReqDTO, example);
		return super.queryByPagination(unpfShopCfgReqDTO, example, true, 
				new PaginationCallback<UnpfShopCfg, UnpfShopCfgRespDTO>() {

					@Override
					public List<UnpfShopCfg> queryDB(BaseCriteria example) {
						return unpfShopCfgMapper.selectByExampleWithBLOBs((UnpfShopCfgCriteria)example);
					}

					@Override
					public long queryTotal(BaseCriteria example) {
						return unpfShopCfgMapper.countByExample((UnpfShopCfgCriteria)example);
					}

					@Override
					public UnpfShopCfgRespDTO warpReturnObject(UnpfShopCfg t) {
						UnpfShopCfgRespDTO unpfShopCfgRespDTO = new UnpfShopCfgRespDTO();
						ObjectCopyUtil.copyObjValue(t, unpfShopCfgRespDTO, null, false);
						return unpfShopCfgRespDTO;
					}
		});
	}

	private void initShopCfg(UnpfShopCfgReqDTO unpfShopCfgReqDTO,UnpfShopCfgCriteria example){
		
		Criteria cr = example.createCriteria();
		if(StringUtil.isNotBlank(unpfShopCfgReqDTO.getPlatType())){
			cr.andPlatTypeEqualTo(unpfShopCfgReqDTO.getPlatType());
		}
		if(StringUtil.isNotEmpty(unpfShopCfgReqDTO.getShopAuthId())){
			cr.andShopIdEqualTo(unpfShopCfgReqDTO.getShopId());
		}
		if(StringUtil.isNotEmpty(unpfShopCfgReqDTO.getShopAuthId())){
			cr.andShopAuthIdEqualTo(unpfShopCfgReqDTO.getShopAuthId());
		}
		if(StringUtil.isNotBlank(unpfShopCfgReqDTO.getStatus())){
			cr.andStatusEqualTo(unpfShopCfgReqDTO.getStatus());
		}
		if(StringUtil.isNotEmpty(unpfShopCfgReqDTO.getCreateStaff())){
			cr.andCreateStaffEqualTo(unpfShopCfgReqDTO.getCreateStaff());
		}
		if(StringUtil.isNotEmpty(unpfShopCfgReqDTO.getUpdateStaff())){
			cr.andUpdateStaffEqualTo(unpfShopCfgReqDTO.getUpdateStaff());
		}
	}
}


