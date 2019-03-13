package com.zengshi.ecp.search.service.common.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.search.dao.mapper.common.SecHotKeywordMapper;
import com.zengshi.ecp.search.dao.model.SecHotKeyword;
import com.zengshi.ecp.search.dao.model.SecHotKeywordCriteria;
import com.zengshi.ecp.search.dubbo.dto.SecHotKeywordReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecHotKeywordRespDTO;
import com.zengshi.ecp.search.service.common.interfaces.ISecHotKeywordSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;

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
public class SecHotKeywordSVImpl extends GeneralSQLSVImpl implements ISecHotKeywordSV {

    @Autowired
    private SecHotKeywordMapper secHotKeywordMapper;

    @Override
    public PageResponseDTO<SecHotKeywordRespDTO> querySecHotKeywordPage(SecHotKeywordReqDTO secHotKeywordReqDTO) throws BusinessException{
        SecHotKeywordCriteria dcriteria=new SecHotKeywordCriteria();
        dcriteria.setLimitClauseCount(secHotKeywordReqDTO.getPageSize());
        dcriteria.setLimitClauseStart(secHotKeywordReqDTO.getStartRowIndex());
        dcriteria.setOrderByClause("HOTKEYWORD_RANK asc");
        return super.queryByPagination(secHotKeywordReqDTO, dcriteria,false, new PaginationCallback<SecHotKeyword, SecHotKeywordRespDTO>() {
            //查询记录集
            @Override
            public List<SecHotKeyword> queryDB(BaseCriteria criteria) {
                
                return secHotKeywordMapper.selectByExample((SecHotKeywordCriteria) criteria);
            }
            //查询总记录数
            @Override
            public long queryTotal(BaseCriteria criteria) {
                
                return secHotKeywordMapper.countByExample((SecHotKeywordCriteria)criteria);
            }
            //查询结果转换
            @Override
            public SecHotKeywordRespDTO warpReturnObject(SecHotKeyword t) {
                SecHotKeywordRespDTO dto=new SecHotKeywordRespDTO();
                BeanUtils.copyProperties(t, dto);
                return dto;
            }
        });
    }


}
