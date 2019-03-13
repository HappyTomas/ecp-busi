package com.zengshi.ecp.goods.service.busi.impl.gdslog;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsLogMapper;
import com.zengshi.ecp.goods.dao.model.GdsLog;
import com.zengshi.ecp.goods.dao.model.GdsLogCriteria;
import com.zengshi.ecp.goods.dao.model.GdsLogCriteria.Criteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.gdslog.GdsLogReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdslog.GdsLogRespDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.gdslog.IGdsLogSV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;

public class GdsLogSVImpl extends AbstractSVImpl implements IGdsLogSV {
   
    @Resource(name = "seq_gds_log")
    private Sequence seqGdsLog;
    @Resource
    private GdsLogMapper gdsLogMapper;

    @Override
    public void addGdsLog(GdsLogReqDTO logReqDTO) throws Exception {
       GdsLog record = new GdsLog();
       record.setLogId(seqGdsLog.nextValue());
       ObjectCopyUtil.copyObjValue(logReqDTO, record, null, false);
       record.setStatus(GdsConstants.Commons.STATUS_VALID);
       preInsert(logReqDTO, record);
       gdsLogMapper.insert(record);
    }

    @Override
    public void updateGdsLogByPKSelective(GdsLogReqDTO logReqDTO) throws Exception {
        GdsLog record = new GdsLog();
        ObjectCopyUtil.copyObjValue(logReqDTO, record, null, false);
        preUpdate(logReqDTO, record);
        gdsLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageResponseDTO<GdsLogRespDTO> queryGdsLogRespDTOPaging(GdsLogReqDTO dto) throws Exception {
        GdsLogCriteria gdsLogCriteria = new GdsLogCriteria();
        gdsLogCriteria.setLimitClauseStart(dto.getStartRowIndex());
        gdsLogCriteria.setLimitClauseCount(dto.getPageSize());

        if(CollectionUtils.isNotEmpty(dto.getSortRule())){
            String orderByClause="";
            for(String s:dto.getSortRule()){
                String rules[]=s.split(",");
                orderByClause+=rules[0];
                orderByClause+=" ";
                orderByClause+=rules[1];
                orderByClause+=" ";
            }
            gdsLogCriteria.setOrderByClause(orderByClause);
        }else{
            gdsLogCriteria.setOrderByClause("log_id asc");
        }

        initGdsLogCriteria(gdsLogCriteria.createCriteria(), dto);

        return super.queryByPagination(dto, gdsLogCriteria, true,
                new GdsLogRespDTOPaginationOrderByLogIdAscCallback());
    }
    
    
    protected class GdsLogRespDTOPaginationCallback extends
            PaginationCallback<GdsLog, GdsLogRespDTO> {

        @Override
        public List<GdsLog> queryDB(BaseCriteria criteria) {
            return gdsLogMapper
                    .selectByExample((GdsLogCriteria) criteria);
        }

        @Override
        public long queryTotal(BaseCriteria criteria) {
            return gdsLogMapper.countByExample((GdsLogCriteria) criteria);
        }

        @Override
        public GdsLogRespDTO warpReturnObject(GdsLog t) {
            GdsLogRespDTO dto = new GdsLogRespDTO();
            ObjectCopyUtil.copyObjValue(t, dto, null, true);
            return dto;
        }

    }
    
    
    
    protected class GdsLogRespDTOPaginationOrderByLogIdAscCallback extends
            PaginationCallback<GdsLog, GdsLogRespDTO> {

            @Override
            public List<GdsLog> queryDB(BaseCriteria criteria) {
                return gdsLogMapper
                        .selectByExample((GdsLogCriteria) criteria);
            }
            
            @Override
            public long queryTotal(BaseCriteria criteria) {
                return gdsLogMapper.countByExample((GdsLogCriteria) criteria);
            }
            
            
            
            @Override
            public List<Comparator<GdsLog>> defineComparators() {
                List<Comparator<GdsLog>> lst = new ArrayList<Comparator<GdsLog>>();
                lst.add(new Comparator<GdsLog>() {
                    @Override
                    public int compare(GdsLog o1, GdsLog o2) {
                        if (o1.getLogId() > o2.getLogId()) {
                            return -1;
                        } else if (o1.getLogId().equals(o2.getLogId())) {
                            return 0;
                        } else {
                            return 1;
                        }
                    }
                });
                return lst;
            }

            @Override
            public GdsLogRespDTO warpReturnObject(GdsLog t) {
                GdsLogRespDTO dto = new GdsLogRespDTO();
                ObjectCopyUtil.copyObjValue(t, dto, null, true);
                return dto;
            }

        }
    
    

    private GdsLogCriteria.Criteria initGdsLogCriteria(GdsLogCriteria.Criteria criteria,
                                                       GdsLogReqDTO dto) {
        criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);

        if (dto.getBegUpdateTime() != null) {
            criteria.andUpdateTimeGreaterThanOrEqualTo(dto.getBegUpdateTime());
        }

        return criteria;
    }
    @Override
	public List<GdsLogRespDTO> queryGdsLogRespDTO(GdsLogReqDTO dto) throws Exception {
		GdsLogCriteria gdsLog = new GdsLogCriteria();
		Criteria gdsLogCriteria = gdsLog.createCriteria();
		gdsLogCriteria.andUpdateTimeGreaterThanOrEqualTo(dto.getBegUpdateTime());
		gdsLogCriteria.andUpdateTimeLessThanOrEqualTo(dto.getEndUpdateTime());
		
		if(StringUtil.isNotBlank(dto.getOperType())){
		    gdsLogCriteria.andOperTypeEqualTo(dto.getOperType());
		}
		if(null != dto.getOperResult()){
		    gdsLogCriteria.andOperResultEqualTo(dto.getOperResult());
		}
		if(StringUtil.isNotBlank(dto.getStatus())){
		    gdsLogCriteria.andStatusEqualTo(dto.getStatus()); 
		}
		
		if(CollectionUtils.isNotEmpty(dto.getOperTypes())){
		    gdsLogCriteria.andOperTypeIn(dto.getOperTypes());
		}
		
        List<GdsLogRespDTO> respList = new ArrayList<GdsLogRespDTO>();
        List<GdsLog> logList = gdsLogMapper.selectByExample(gdsLog);
        if(CollectionUtils.isNotEmpty(logList)){
        	for(int i=0;i<logList.size();i++){
        		GdsLogRespDTO logResp = new GdsLogRespDTO();
        		ObjectCopyUtil.copyObjValue(logList.get(i), logResp, null, true);
        		respList.add(logResp);
        	}
        }
        return respList;
	}
}
