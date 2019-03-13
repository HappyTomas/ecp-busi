package com.zengshi.ecp.search.dao.mapper.common;

import com.zengshi.ecp.search.dao.model.SecConfigPlan;
import com.zengshi.ecp.search.dao.model.SecConfigPlanCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface SecConfigPlanMapper {
    Long countByExample(SecConfigPlanCriteria example) throws DataAccessException;

    int deleteByExample(SecConfigPlanCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(SecConfigPlan record) throws DataAccessException;

    int insertSelective(SecConfigPlan record) throws DataAccessException;

    List<SecConfigPlan> selectByExample(SecConfigPlanCriteria example) throws DataAccessException;

    SecConfigPlan selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") SecConfigPlan record, @Param("example") SecConfigPlanCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") SecConfigPlan record, @Param("example") SecConfigPlanCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(SecConfigPlan record) throws DataAccessException;

    int updateByPrimaryKey(SecConfigPlan record) throws DataAccessException;
}