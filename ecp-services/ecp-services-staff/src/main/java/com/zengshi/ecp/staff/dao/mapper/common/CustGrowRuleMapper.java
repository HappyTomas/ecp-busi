package com.zengshi.ecp.staff.dao.mapper.common;

import com.zengshi.ecp.staff.dao.model.CustGrowRule;
import com.zengshi.ecp.staff.dao.model.CustGrowRuleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CustGrowRuleMapper {
    Long countByExample(CustGrowRuleCriteria example) throws DataAccessException;

    int deleteByExample(CustGrowRuleCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CustGrowRule record) throws DataAccessException;

    int insertSelective(CustGrowRule record) throws DataAccessException;

    List<CustGrowRule> selectByExample(CustGrowRuleCriteria example) throws DataAccessException;

    CustGrowRule selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CustGrowRule record, @Param("example") CustGrowRuleCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CustGrowRule record, @Param("example") CustGrowRuleCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CustGrowRule record) throws DataAccessException;

    int updateByPrimaryKey(CustGrowRule record) throws DataAccessException;
}