package com.zengshi.ecp.search.service.common.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.search.dao.mapper.common.SecRedirectMapper;
import com.zengshi.ecp.search.dao.model.SecRedirect;
import com.zengshi.ecp.search.dao.model.SecRedirectCriteria;
import com.zengshi.ecp.search.dao.model.SecRedirectCriteria.Criteria;
import com.zengshi.ecp.search.dubbo.dto.SecRedirectReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecRedirectRespDTO;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.dubbo.util.BeanTransfermationUtils;
import com.zengshi.ecp.search.dubbo.util.SearchMessageKeyContants;
import com.zengshi.ecp.search.service.common.interfaces.ISecRedirectSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.DateUtil;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月11日下午5:01:57 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
public class SecRedirectSVImpl extends GeneralSQLSVImpl implements ISecRedirectSV {

    @Autowired
    private SecRedirectMapper secRedirectMapper;

    @Resource(name = "seq_sec_redirect")
    private Sequence seqSecRedirect;

    @Override
    public List<SecRedirect> listSecRedirect() throws BusinessException {
        SecRedirectCriteria criteria = new SecRedirectCriteria();
        Criteria cr = criteria.createCriteria();

        // 过滤无效数据
        cr.andStatusEqualTo(SearchConstants.STATUS_VALID);

        List<SecRedirect> secRedirectList = this.secRedirectMapper.selectByExample(criteria);
        return secRedirectList;
    }

//    @Override
//    public List<SecRedirect> querySecRedirectPage(SecRedirectReqDTO secRedirectReqDTO)
//            throws BusinessException {
//        SecRedirectCriteria criteria = new SecRedirectCriteria();
//        Criteria cr = criteria.createCriteria();
//
//        // 过滤无效数据
//        cr.andStatusEqualTo(SearchConstants.STATUS_VALID);
//
//        // TODO 界面支持的查询条件
//
//        criteria.setLimitClauseStart(secRedirectReqDTO.getStartRowIndex());
//        criteria.setLimitClauseCount(secRedirectReqDTO.getPageSize());
//
//        List<SecRedirect> secRedirectList = this.secRedirectMapper.selectByExample(criteria);
//        return secRedirectList;
//    }
    
    @Override
    public PageResponseDTO<SecRedirectRespDTO> querySecRedirectPage(
            SecRedirectReqDTO secRedirectReqDTO) throws BusinessException {
        SecRedirectCriteria dcriteria=new SecRedirectCriteria();
        dcriteria.setLimitClauseCount(secRedirectReqDTO.getPageSize());
        dcriteria.setLimitClauseStart(secRedirectReqDTO.getStartRowIndex());
//        dcriteria.setOrderByClause("log_id desc");
        return super.queryByPagination(secRedirectReqDTO, dcriteria,false, new PaginationCallback<SecRedirect, SecRedirectRespDTO>() {
            //查询记录集
            @Override
            public List<SecRedirect> queryDB(BaseCriteria criteria) {
                
                return secRedirectMapper.selectByExample((SecRedirectCriteria) criteria);
            }
            //查询总记录数
            @Override
            public long queryTotal(BaseCriteria criteria) {
                
                return secRedirectMapper.countByExample((SecRedirectCriteria)criteria);
            }
            //查询结果转换
            @Override
            public SecRedirectRespDTO warpReturnObject(SecRedirect t) {
                SecRedirectRespDTO dto=new SecRedirectRespDTO();
                BeanUtils.copyProperties(t, dto);
                return dto;
            }
        });
    }


    @Override
    public long saveSecRedirect(SecRedirectReqDTO secRedirectReqDTO) throws BusinessException {

        // DTO转BO,同名属性自动赋值
        SecRedirect secRedirect = new SecRedirect();
        BeanTransfermationUtils.dto2bo(secRedirect, secRedirectReqDTO);

        // 补值操作
        long id = this.seqSecRedirect.nextValue();
        secRedirect.setId(id);
        secRedirect.setStatus(SearchConstants.STATUS_VALID);

        // 记录创建和修改信息的补值操作
        Timestamp t=DateUtil.getSysDate();
        secRedirect.setCreateStaff(secRedirectReqDTO.getStaff().getId());
        secRedirect.setCreateTime(t);
        secRedirect.setUpdateStaff(secRedirectReqDTO.getStaff().getId());
        secRedirect.setUpdateTime(t);

        this.secRedirectMapper.insert(secRedirect);
        
        return id;

    }

    @Override
    public void deleteSecRedirect(SecRedirectReqDTO secRedirectReqDTO) throws BusinessException {

        // 逻辑删除
        SecRedirect secRedirect = new SecRedirect();
        secRedirect.setId(secRedirectReqDTO.getId());
        secRedirect.setStatus(SearchConstants.STATUS_INVALID);

        // 记录创建和修改信息的补值操作
        secRedirect.setUpdateStaff(secRedirectReqDTO.getStaff().getId());
        secRedirect.setUpdateTime(DateUtil.getSysDate());

        this.secRedirectMapper.updateByPrimaryKeySelective(secRedirect);
        
    }

    @Override
    public void updateSecRedirect(SecRedirectReqDTO secRedirectReqDTO) throws BusinessException {

        // DTO转BO,同名属性自动赋值
        SecRedirect secRedirect = new SecRedirect();
        BeanTransfermationUtils.dto2bo(secRedirect, secRedirectReqDTO);

        // 记录创建和修改信息的补值操作
        secRedirect.setUpdateStaff(secRedirectReqDTO.getStaff().getId());
        secRedirect.setUpdateTime(DateUtil.getSysDate());

        this.secRedirectMapper.updateByPrimaryKey(secRedirect);
        
    }

    @Override
    public SecRedirect querySecRedirectById(long id) throws BusinessException {
       
        SecRedirect secRedirect = this.secRedirectMapper.selectByPrimaryKey(id);
        if (secRedirect == null) {
            throw new BusinessException(SearchMessageKeyContants.Info.KEY_INFO_ROW_NOTFOUND,new String[]{""+id});
        } else {
            return secRedirect;
        }
        
    }

}
