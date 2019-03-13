package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.ShopInfo;
import com.zengshi.ecp.staff.dao.model.ShopInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ShopInfoMapper {
    Long countByExample(ShopInfoCriteria example) throws DataAccessException;

    int deleteByExample(ShopInfoCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(ShopInfo record) throws DataAccessException;

    int insertSelective(ShopInfo record) throws DataAccessException;

    List<ShopInfo> selectByExample(ShopInfoCriteria example) throws DataAccessException;

    ShopInfo selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") ShopInfo record, @Param("example") ShopInfoCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") ShopInfo record, @Param("example") ShopInfoCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(ShopInfo record) throws DataAccessException;

    int updateByPrimaryKey(ShopInfo record) throws DataAccessException;
}