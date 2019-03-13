package com.zengshi.ecp.staff.dao.mapper.common;

import com.zengshi.ecp.staff.dao.model.DataRuleItem;
import com.zengshi.ecp.staff.dao.model.DataRuleItemCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface DataRuleItemMapper {
    Long countByExample(DataRuleItemCriteria example) throws DataAccessException;

    int deleteByExample(DataRuleItemCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(DataRuleItem record) throws DataAccessException;

    int insertSelective(DataRuleItem record) throws DataAccessException;

    List<DataRuleItem> selectByExample(DataRuleItemCriteria example) throws DataAccessException;

    DataRuleItem selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") DataRuleItem record, @Param("example") DataRuleItemCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") DataRuleItem record, @Param("example") DataRuleItemCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(DataRuleItem record) throws DataAccessException;

    int updateByPrimaryKey(DataRuleItem record) throws DataAccessException;
}