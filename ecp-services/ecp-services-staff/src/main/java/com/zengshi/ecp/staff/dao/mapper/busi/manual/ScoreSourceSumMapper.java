package com.zengshi.ecp.staff.dao.mapper.busi.manual;

import com.zengshi.ecp.staff.dao.model.ScoreSourceCriteria;
import org.springframework.dao.DataAccessException;

public interface ScoreSourceSumMapper {
    
    Long sumByExample(ScoreSourceCriteria example) throws DataAccessException;

}