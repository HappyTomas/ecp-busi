package com.zengshi.ecp.sys.dao.mapper.common;

import com.zengshi.ecp.sys.dao.model.BaseEmail;
import com.zengshi.ecp.sys.dao.model.BaseEmailCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface BaseEmailMapper {
    Long countByExample(BaseEmailCriteria example) throws DataAccessException;

    int deleteByExample(BaseEmailCriteria example) throws DataAccessException;

    int insert(BaseEmail record) throws DataAccessException;

    int insertSelective(BaseEmail record) throws DataAccessException;

    List<BaseEmail> selectByExample(BaseEmailCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") BaseEmail record, @Param("example") BaseEmailCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") BaseEmail record, @Param("example") BaseEmailCriteria example) throws DataAccessException;
}
