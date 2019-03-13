package com.zengshi.ecp.unpf.service.busi.good.send.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.unpf.dao.mapper.busi.UnpfSendLogMapper;
import com.zengshi.ecp.unpf.dao.model.UnpfSendLog;
import com.zengshi.ecp.unpf.dao.model.UnpfSendLogCriteria;
import com.zengshi.ecp.unpf.dao.model.UnpfSendLogCriteria.Criteria;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfSendLogReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfSendLogRespDTO;
import com.zengshi.ecp.unpf.service.busi.good.send.interfaces.IUnpfSendLogSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
* @author  lisp: 
* @date 创建时间：2016年11月19日 上午10:37:51 
* @version 1.0 
* @parameter  
* @since  
* @return  */
public class UnpfSendLogSVImpl extends GeneralSQLSVImpl implements IUnpfSendLogSV {
	
	@Resource
	private UnpfSendLogMapper unpfSendLogMapper;
	
	@Resource(name="seq_unpf_send_log_id")
	private PaasSequence seq_unpf_send_log_id;

	@Override
	public void saveGdsSendLog(UnpfSendLogReqDTO unpfSendLogReqDTO) throws BusinessException {

		UnpfSendLog unpfSendLog = new UnpfSendLog();
		ObjectCopyUtil.copyObjValue(unpfSendLogReqDTO, unpfSendLog, null, false);
		if(StringUtil.isEmpty(unpfSendLog.getCreateStaff())){
			unpfSendLog.setCreateStaff(unpfSendLogReqDTO.getStaff().getId());
		}
		if(unpfSendLog.getCreateStaff()<=0){
			unpfSendLog.setCreateStaff(unpfSendLogReqDTO.getStaff().getId());
		}
		if(StringUtil.isEmpty(unpfSendLogReqDTO.getCreateTime())){
			unpfSendLog.setCreateTime(DateUtil.getSysDate());
		}
		unpfSendLog.setId(seq_unpf_send_log_id.nextValue());
		unpfSendLogMapper.insert(unpfSendLog);
	}

	@Override
	public UnpfSendLogRespDTO queryGdsSendLogById(UnpfSendLogReqDTO unpfSendLogReqDTO) throws BusinessException {
		UnpfSendLog unpfSendLog = unpfSendLogMapper.selectByPrimaryKey(unpfSendLogReqDTO.getId());
		if(StringUtil.isEmpty(unpfSendLog)){
			return null;
		}
		UnpfSendLogRespDTO unpfSendLogRespDTO = new UnpfSendLogRespDTO();
		ObjectCopyUtil.copyObjValue(unpfSendLog, unpfSendLogRespDTO, null, false);
		return unpfSendLogRespDTO;
	}

	@Override
	public PageResponseDTO<UnpfSendLogRespDTO> queryGdsSendLogForPage(UnpfSendLogReqDTO unpfSendLogReqDTO)
			throws BusinessException {
		UnpfSendLogCriteria example = new UnpfSendLogCriteria();
		example.setLimitClauseCount(unpfSendLogReqDTO.getPageSize());
		example.setLimitClauseStart(unpfSendLogReqDTO.getStartRowIndex());
		example.setOrderByClause(" id DESC");
		this.initSendLog(example, unpfSendLogReqDTO);
		
		return super.queryByPagination(unpfSendLogReqDTO, example, true, 
				new PaginationCallback<UnpfSendLog,UnpfSendLogRespDTO>() {

					@Override
					public List<UnpfSendLog> queryDB(BaseCriteria example) {
						return unpfSendLogMapper.selectByExampleWithBLOBs((UnpfSendLogCriteria)example);
					}

					@Override
					public long queryTotal(BaseCriteria example) {
						return unpfSendLogMapper.countByExample((UnpfSendLogCriteria)example);
					}

					@Override
					public UnpfSendLogRespDTO warpReturnObject(UnpfSendLog t) {
						UnpfSendLogRespDTO unpfSendLogRespDTO =  new UnpfSendLogRespDTO();
						ObjectCopyUtil.copyObjValue(t, unpfSendLogRespDTO, null, false);
						return unpfSendLogRespDTO;
					}
					@Override
		            public List<Comparator<UnpfSendLog>> defineComparators() {
		                List<Comparator<UnpfSendLog>> lst = new ArrayList<Comparator<UnpfSendLog>>();
		                lst.add(new Comparator<UnpfSendLog>() {
		                    @Override
		                    public int compare(UnpfSendLog o1, UnpfSendLog o2) {
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

	private void initSendLog(UnpfSendLogCriteria example,UnpfSendLogReqDTO unpfSendLogReqDTO){
		
		Criteria cr = example.createCriteria(); 
		if(StringUtil.isNotEmpty(unpfSendLogReqDTO.getShopAuthId())){
			cr.andShopAuthIdEqualTo(unpfSendLogReqDTO.getShopAuthId());
		}
		if(StringUtil.isNotEmpty(unpfSendLogReqDTO.getSendId())){
			cr.andSendIdEqualTo(unpfSendLogReqDTO.getSendId());
		}
		if(StringUtil.isNotBlank(unpfSendLogReqDTO.getPlatType())){
			cr.andPlatTypeEqualTo(unpfSendLogReqDTO.getPlatType());
		}
		if(StringUtil.isNotEmpty(unpfSendLogReqDTO.getShopId())){
			cr.andShopIdEqualTo(unpfSendLogReqDTO.getShopId());
		}
		if(StringUtil.isNotEmpty(unpfSendLogReqDTO.getGdsId())){
			cr.andGdsIdEqualTo(unpfSendLogReqDTO.getGdsId());
		}
		if(StringUtil.isNotEmpty(unpfSendLogReqDTO.getSkuId())){
			cr.andSkuIdEqualTo(unpfSendLogReqDTO.getSkuId());
		}
		if(StringUtil.isNotBlank(unpfSendLogReqDTO.getAction())){
			cr.andActionEqualTo(unpfSendLogReqDTO.getAction());
		}
		if(StringUtil.isNotBlank(unpfSendLogReqDTO.getOutGdsId())){
			cr.andOutGdsIdEqualTo(unpfSendLogReqDTO.getOutGdsId());
		}
		if(StringUtil.isNotEmpty(unpfSendLogReqDTO.getCreateStaff())){
			cr.andCreateStaffEqualTo(unpfSendLogReqDTO.getCreateStaff());
		}
	}
	/**
	* 商品数量
	* 
	* @author huangjx
	* @param UnpfSendLogReqDTO
	* @return 
	* @throws 
	*/
	public Long countGds(UnpfSendLogReqDTO unpfSendLogReqDTO) throws BusinessException{
		UnpfSendLogCriteria example = new UnpfSendLogCriteria();
		this.initSendLog(example, unpfSendLogReqDTO);
		Long count=unpfSendLogMapper.countByExample(example);
		return count;
	}
}


