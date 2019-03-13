package com.zengshi.ecp.staff.dao.mapper.busi.manual;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.staff.dao.model.ScoreExchangeCriteria;

public interface ScoreExchangeSumMapper {
    
    Long sumByExample(ScoreExchangeCriteria example) throws DataAccessException;

}