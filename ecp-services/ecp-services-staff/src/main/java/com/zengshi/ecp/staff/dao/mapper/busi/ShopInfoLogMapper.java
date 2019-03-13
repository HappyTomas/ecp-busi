package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.ShopInfoLog;
import com.zengshi.ecp.staff.dao.model.ShopInfoLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ShopInfoLogMapper {
    Long countByExample(ShopInfoLogCriteria example) throws DataAccessException;

    int deleteByExample(ShopInfoLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(ShopInfoLog record) throws DataAccessException;

    int insertSelective(ShopInfoLog record) throws DataAccessException;

    List<ShopInfoLog> selectByExample(ShopInfoLogCriteria example) throws DataAccessException;

    ShopInfoLog selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") ShopInfoLog record, @Param("example") ShopInfoLogCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") ShopInfoLog record, @Param("example") ShopInfoLogCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(ShopInfoLog record) throws DataAccessException;

    int updateByPrimaryKey(ShopInfoLog record) throws DataAccessException;
}