package com.zengshi.ecp.sys.dao.mapper.common;

import com.zengshi.ecp.sys.dao.model.WeixMenu;
import com.zengshi.ecp.sys.dao.model.WeixMenuCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface WeixMenuMapper {
    Long countByExample(WeixMenuCriteria example) throws DataAccessException;

    int deleteByExample(WeixMenuCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(WeixMenu record) throws DataAccessException;

    int insertSelective(WeixMenu record) throws DataAccessException;

    List<WeixMenu> selectByExample(WeixMenuCriteria example) throws DataAccessException;

    WeixMenu selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") WeixMenu record, @Param("example") WeixMenuCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") WeixMenu record, @Param("example") WeixMenuCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(WeixMenu record) throws DataAccessException;

    int updateByPrimaryKey(WeixMenu record) throws DataAccessException;
}
