package com.zengshi.ecp.staff.service.common.score.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dao.mapper.common.ScoreTypeMapper;
import com.zengshi.ecp.staff.dao.model.ScoreType;
import com.zengshi.ecp.staff.dao.model.ScoreTypeCriteria;
import com.zengshi.ecp.staff.dubbo.dto.ScoreTypeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreTypeResDTO;
import com.zengshi.ecp.staff.service.common.score.interfaces.IScoreTypeSV;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 积分类型sv接口实现类<br>
 * Date:2015-10-14下午9:40:12  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public class ScoreTypeSVImpl extends GeneralSQLSVImpl implements IScoreTypeSV{

    @Resource
    private ScoreTypeMapper scoreTypeMapper;
    
    @Override
    public PageResponseDTO<ScoreTypeResDTO> queryScoreTypeList(ScoreTypeReqDTO req) throws BusinessException {
        PageResponseDTO<ScoreTypeResDTO> pageInfo = new PageResponseDTO<ScoreTypeResDTO>();
        ScoreTypeCriteria criteria = new ScoreTypeCriteria();
        pageInfo.setPageNo(1);
        pageInfo.setPageSize(9999);
        //设置查询的开始及终止的rownum
        criteria.setLimitClauseStart(pageInfo.getStartRowIndex());
        criteria.setLimitClauseCount(pageInfo.getPageSize());
        pageInfo = super.queryByPagination(req, criteria, true, new PaginationCallback<ScoreType, ScoreTypeResDTO>() {

            @Override
            public List<ScoreType> queryDB(BaseCriteria bc) {
                return scoreTypeMapper.selectByExample((ScoreTypeCriteria)bc);
            }

            @Override
            public long queryTotal(BaseCriteria bc) {
                return scoreTypeMapper.countByExample((ScoreTypeCriteria)bc);
            }

            @Override
            public List<Comparator<ScoreType>> defineComparators() {
                List<Comparator<ScoreType>> ls=new ArrayList<Comparator<ScoreType>>();
                ls.add(new Comparator<ScoreType>(){

                    @Override
                    public int compare(ScoreType o1, ScoreType o2) {
                        return o1.getCreateTime().getTime()<o2.getCreateTime().getTime()?1:-1;
                    }
                    
                });
                return ls;
            }
            @Override
            public ScoreTypeResDTO warpReturnObject(ScoreType scoreType) {
                ScoreTypeResDTO res = new ScoreTypeResDTO();
                if (scoreType != null) {
                    ObjectCopyUtil.copyObjValue(scoreType, res, null, true);
                }
                
                return res;
            }
        });
        
        return pageInfo;
    }

    @Override
    public ScoreType findScoreTypeByKey(String scoreType) throws BusinessException {
        
        return scoreTypeMapper.selectByPrimaryKey(scoreType);
    }

}

