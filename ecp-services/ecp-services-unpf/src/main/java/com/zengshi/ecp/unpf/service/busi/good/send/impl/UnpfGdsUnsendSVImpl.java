package com.zengshi.ecp.unpf.service.busi.good.send.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.unpf.dao.mapper.busi.UnpfGdsUnsendMapper;
import com.zengshi.ecp.unpf.dao.model.UnpfGdsUnsend;
import com.zengshi.ecp.unpf.dao.model.UnpfGdsUnsendCriteria;
import com.zengshi.ecp.unpf.dao.model.UnpfGdsUnsendCriteria.Criteria;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsUnsendHisReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsUnsendReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsUnsendRespDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.gdssend.IUnpfGdsUnsendHisRSV;
import com.zengshi.ecp.unpf.service.busi.good.send.interfaces.IUnpfGdsUnsendHisSV;
import com.zengshi.ecp.unpf.service.busi.good.send.interfaces.IUnpfGdsUnsendSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
* @author  lisp: 
* @date 创建时间：2016年11月21日 下午8:03:01 
* @version 1.0 
**/
public class UnpfGdsUnsendSVImpl extends GeneralSQLSVImpl implements IUnpfGdsUnsendSV {

	@Resource
	private IUnpfGdsUnsendHisRSV unpfGdsUnsendHisRSV;
	
	@Resource
	private IUnpfGdsUnsendHisSV unpfGdsUnsendHisSV;
	
	@Resource
	private UnpfGdsUnsendMapper unpfGdsUnsendMapper;
	
	@Resource(name="seq_unpf_gds_unsend_id")
	private PaasSequence seq_unpf_gds_unsend_id;
	
	@Override
	public void saveGdsUnsend(UnpfGdsUnsendReqDTO unpfGdsUnsendReqDTO) throws BusinessException {
		UnpfGdsUnsend unpfGdsUnsend = new UnpfGdsUnsend();
		ObjectCopyUtil.copyObjValue(unpfGdsUnsendReqDTO, unpfGdsUnsend, null, false);
		unpfGdsUnsend.setId(seq_unpf_gds_unsend_id.nextValue());
		unpfGdsUnsendMapper.insert(unpfGdsUnsend);
	}

	@Override
	public void deleteGdsUnsendById(UnpfGdsUnsendReqDTO unpfGdsUnsendReqDTO) throws BusinessException {

		UnpfGdsUnsendRespDTO unpfGdsUnsendRespDTO = this.queryGdsUnsendById(unpfGdsUnsendReqDTO);
		if(unpfGdsUnsendRespDTO == null){
			throw new BusinessException("unpf.100023");
		}
		UnpfGdsUnsendHisReqDTO unpfGdsUnsendHisReqDTO = new UnpfGdsUnsendHisReqDTO();
		unpfGdsUnsendHisReqDTO.setShopId(unpfGdsUnsendRespDTO.getShopId());
		unpfGdsUnsendHisReqDTO.setGdsId(unpfGdsUnsendRespDTO.getGdsId());
		unpfGdsUnsendHisReqDTO.setStatus(unpfGdsUnsendRespDTO.getStatus());
		unpfGdsUnsendHisReqDTO.setCreateTimeOld(unpfGdsUnsendRespDTO.getCreateTime());
		unpfGdsUnsendHisReqDTO.setCreateStaffOld(unpfGdsUnsendRespDTO.getCreateStaff());
		unpfGdsUnsendHisReqDTO.setCreateTime(DateUtil.getSysDate());
		unpfGdsUnsendHisReqDTO.setCreateStaff(unpfGdsUnsendHisReqDTO.getStaff().getId());
		unpfGdsUnsendHisSV.saveGdsUnsendHis(unpfGdsUnsendHisReqDTO);
		unpfGdsUnsendMapper.deleteByPrimaryKey(unpfGdsUnsendReqDTO.getId());
	}

	
	@Override
	public void deleteGdsUnsendByGdsId(UnpfGdsUnsendReqDTO unpfGdsUnsendReqDTO) throws BusinessException {

		UnpfGdsUnsendCriteria example = new UnpfGdsUnsendCriteria();
		Criteria cr = example.createCriteria();
		cr.andGdsIdEqualTo(unpfGdsUnsendReqDTO.getGdsId());
		List<UnpfGdsUnsend> list = unpfGdsUnsendMapper.selectByExample(example);
		if(!CollectionUtils.isEmpty(list)&&list.size()>0){
			for (UnpfGdsUnsend unpfGdsUnsend : list) {
				unpfGdsUnsendReqDTO.setId(unpfGdsUnsend.getId());
				this.deleteGdsUnsendById(unpfGdsUnsendReqDTO);
			}
		}
	}
	
	@Override
	public UnpfGdsUnsendRespDTO queryGdsUnsendById(UnpfGdsUnsendReqDTO unpfGdsUnsendReqDTO) throws BusinessException {
		UnpfGdsUnsend unpfGdsUnsend = unpfGdsUnsendMapper.selectByPrimaryKey(unpfGdsUnsendReqDTO.getId());
		if(unpfGdsUnsend==null){
			return null;
		}
		UnpfGdsUnsendRespDTO unpfGdsUnsendRespDTO = new UnpfGdsUnsendRespDTO();
		ObjectCopyUtil.copyObjValue(unpfGdsUnsend, unpfGdsUnsendRespDTO, null, false);
		return unpfGdsUnsendRespDTO;
	}

	@Override
	public PageResponseDTO<UnpfGdsUnsendRespDTO> queryGdsUnsendForPage(UnpfGdsUnsendReqDTO unpfGdsUnsendReqDTO)
			throws BusinessException {
		UnpfGdsUnsendCriteria example = new UnpfGdsUnsendCriteria();
		example.setLimitClauseCount(unpfGdsUnsendReqDTO.getPageSize());
		example.setLimitClauseStart(unpfGdsUnsendReqDTO.getStartRowIndex());
		example.setOrderByClause(" id desc");
		this.initGdsUnsend(unpfGdsUnsendReqDTO, example);
		return super.queryByPagination(unpfGdsUnsendReqDTO, example, true,
				new PaginationCallback<UnpfGdsUnsend, UnpfGdsUnsendRespDTO>() {

					@Override
					public List<UnpfGdsUnsend> queryDB(BaseCriteria example) {
						return unpfGdsUnsendMapper.selectByExample((UnpfGdsUnsendCriteria)example);
					}

					@Override
					public long queryTotal(BaseCriteria example) {
						return unpfGdsUnsendMapper.countByExample((UnpfGdsUnsendCriteria)example);
					}

					@Override
					public UnpfGdsUnsendRespDTO warpReturnObject(UnpfGdsUnsend t) {
						UnpfGdsUnsendRespDTO unpfGdsUnsendRespDTO = new UnpfGdsUnsendRespDTO();
						ObjectCopyUtil.copyObjValue(t, unpfGdsUnsendRespDTO, null, false);
						return unpfGdsUnsendRespDTO;
					}
					@Override
		            public List<Comparator<UnpfGdsUnsend>> defineComparators() {
		                List<Comparator<UnpfGdsUnsend>> lst = new ArrayList<Comparator<UnpfGdsUnsend>>();
		                lst.add(new Comparator<UnpfGdsUnsend>() {
		                    @Override
		                    public int compare(UnpfGdsUnsend o1, UnpfGdsUnsend o2) {
		                        if (o1.getId() > o2.getId()) {
		                            return -1;
		                        } else if (o1.getId().equals(o2.getId())) {
		                            return 0;
		                        } else {
		                            return 1;
		                        }
		                    }
		                });
		                return lst;
		            }
		});
	}

	private void initGdsUnsend(UnpfGdsUnsendReqDTO unpfGdsUnsendReqDTO,UnpfGdsUnsendCriteria example) throws BusinessException{
		Criteria cr =  example.createCriteria();
		if(StringUtil.isNotEmpty(unpfGdsUnsendReqDTO.getShopId())){
			cr.andShopIdEqualTo(unpfGdsUnsendReqDTO.getShopId());
		}
		if(StringUtil.isNotEmpty(unpfGdsUnsendReqDTO.getGdsId())){
			cr.andGdsIdEqualTo(unpfGdsUnsendReqDTO.getGdsId());
		}
		if(StringUtil.isNotEmpty(unpfGdsUnsendReqDTO.getStatus())){
			cr.andStatusEqualTo(unpfGdsUnsendReqDTO.getStatus());
		}
	}

	
	@Override
	public List<UnpfGdsUnsend> queryGdsUnsendByGdsId(UnpfGdsUnsendReqDTO unpfGdsUnsendReqDTO)
			throws BusinessException {
		UnpfGdsUnsendCriteria example = new UnpfGdsUnsendCriteria();
		Criteria cr = example.createCriteria();
		cr.andGdsIdEqualTo(unpfGdsUnsendReqDTO.getGdsId());
		
		return unpfGdsUnsendMapper.selectByExample(example);
	}


}


