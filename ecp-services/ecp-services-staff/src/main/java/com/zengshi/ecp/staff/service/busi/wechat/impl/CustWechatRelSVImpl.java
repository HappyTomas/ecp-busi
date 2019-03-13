package com.zengshi.ecp.staff.service.busi.wechat.impl;

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
import com.zengshi.ecp.staff.dao.mapper.busi.CustWechatRelMapper;
import com.zengshi.ecp.staff.dao.model.CustWechatRel;
import com.zengshi.ecp.staff.dao.model.CustWechatRelCriteria;
import com.zengshi.ecp.staff.dao.model.CustWechatRelCriteria.Criteria;
import com.zengshi.ecp.staff.dubbo.dto.CustWechatRelReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustWechatRelRespDTO;
import com.zengshi.ecp.staff.service.busi.wechat.interfaces.ICustWechatRelSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 商城用户与微信用户对接接口实现类<br>
 * Date:2016年7月21日上午10:38:54  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.6
 */
public class CustWechatRelSVImpl extends GeneralSQLSVImpl implements ICustWechatRelSV{

	@Resource
	public CustWechatRelMapper custWechatRelMapper;
	
	@Resource(name = "seq_cust_wechat_id")
    private PaasSequence seqCustWechatId;
	
	@Override
	public PageResponseDTO<CustWechatRelRespDTO> findCustWechatRel(CustWechatRelReqDTO req) throws BusinessException {
		PageResponseDTO<CustWechatRelRespDTO> pageInfo = new PageResponseDTO<CustWechatRelRespDTO>();
		CustWechatRelCriteria criteria = new CustWechatRelCriteria();
        Criteria sql = criteria.createCriteria();
        //用户id
        if (req.getStaffId() != null && req.getStaffId() != 0L) {
        	sql.andStaffIdEqualTo(req.getStaffId());
        }
        //用户名
        if (StringUtil.isNotBlank(req.getStaffCode())) {
        	sql.andStaffCodeLike("%" + req.getStaffCode() + "%");
        }
        //微信的wechatId
        if (StringUtil.isNotBlank(req.getWechatId())) {
        	sql.andWechatIdEqualTo(req.getWechatId());
        }
        pageInfo.setPageNo(req.getPageNo());
        pageInfo.setPageSize(req.getPageSize());
        //设置查询的开始及终止的rownum
        criteria.setLimitClauseStart(pageInfo.getStartRowIndex());
        criteria.setLimitClauseCount(pageInfo.getPageSize());
        criteria.setOrderByClause(" CREATE_TIME DESC");
        pageInfo = super.queryByPagination(req, criteria, true, new PaginationCallback<CustWechatRel, CustWechatRelRespDTO>() {

            @Override
            public List<CustWechatRel> queryDB(BaseCriteria bc) {
                return custWechatRelMapper.selectByExample((CustWechatRelCriteria)bc);
            }

            @Override
            public long queryTotal(BaseCriteria bc) {
                return custWechatRelMapper.countByExample((CustWechatRelCriteria)bc);
            }

            @Override
            public List<Comparator<CustWechatRel>> defineComparators() {
                List<Comparator<CustWechatRel>> ls=new ArrayList<Comparator<CustWechatRel>>();
                ls.add(new Comparator<CustWechatRel>(){

                    @Override
                    public int compare(CustWechatRel o1, CustWechatRel o2) {
                        return o1.getCreateTime().getTime()<o2.getCreateTime().getTime()?1:-1;
                    }
                    
                });
                return ls;
            }
            @Override
            public CustWechatRelRespDTO warpReturnObject(CustWechatRel custWechatRel) {
            	CustWechatRelRespDTO res = new CustWechatRelRespDTO();
                ObjectCopyUtil.copyObjValue(custWechatRel,res,null,false);
                
                return res;
            }
        });
        return pageInfo;
	}

	@Override
	public long saveCustWechatRel(CustWechatRelReqDTO req) throws BusinessException {
		CustWechatRel rel = new CustWechatRel();
		ObjectCopyUtil.copyObjValue(req, rel, null, false);
		rel.setId(seqCustWechatId.nextValue());
		rel.setCreateTime(DateUtil.getSysDate());
		custWechatRelMapper.insert(rel);
		return rel.getId();
	}

	@Override
	public long updateCustWechatRel(CustWechatRelReqDTO req) throws BusinessException {
		CustWechatRel rel = new CustWechatRel();
		ObjectCopyUtil.copyObjValue(req, rel, null, false);
		return custWechatRelMapper.updateByPrimaryKeySelective(rel);
	}

	@Override
	public CustWechatRelRespDTO findCustWechatRelByWechatId(String wechatId) throws BusinessException {
		CustWechatRelRespDTO resp = new CustWechatRelRespDTO();
		CustWechatRel rel = custWechatRelMapper.selectByPrimaryKey(wechatId);
		if (rel != null) {
			ObjectCopyUtil.copyObjValue(rel, resp, null, false);
		} else {
			resp = null;
		}
		return resp;
	}

	@Override
	public long deleteCustWechatRel(String wechatId) throws BusinessException {
		return custWechatRelMapper.deleteByPrimaryKey(wechatId);
	}

	@Override
	public long deleteCustWechatByRel(CustWechatRelReqDTO req) throws BusinessException {
		CustWechatRelCriteria criteria = new CustWechatRelCriteria();
		Criteria sql = criteria.createCriteria();
		//用户名
		if (StringUtil.isNotBlank(req.getStaffCode())) {
			sql.andStaffCodeEqualTo(req.getStaffCode());
		}
		//用户id
		if (req.getStaffId() != null && req.getStaffId() != 0L) {
			sql.andStaffIdEqualTo(req.getStaffId());
		}
		return custWechatRelMapper.deleteByExample(criteria);
	}

}

