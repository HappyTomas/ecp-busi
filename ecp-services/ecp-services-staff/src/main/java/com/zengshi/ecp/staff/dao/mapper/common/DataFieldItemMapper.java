package com.zengshi.ecp.staff.dao.mapper.common;

import com.zengshi.ecp.staff.dao.model.DataFieldItem;
import com.zengshi.ecp.staff.dao.model.DataFieldItemCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface DataFieldItemMapper {
    Long countByExample(DataFieldItemCriteria example) throws DataAccessException;

    int deleteByExample(DataFieldItemCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(DataFieldItem record) throws DataAccessException;

    int insertSelective(DataFieldItem record) throws DataAccessException;

    List<DataFieldItem> selectByExample(DataFieldItemCriteria example) throws DataAccessException;

    DataFieldItem selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") DataFieldItem record, @Param("example") DataFieldItemCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") DataFieldItem record, @Param("example") DataFieldItemCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(DataFieldItem record) throws DataAccessException;

    int updateByPrimaryKey(DataFieldItem record) throws DataAccessException;
}