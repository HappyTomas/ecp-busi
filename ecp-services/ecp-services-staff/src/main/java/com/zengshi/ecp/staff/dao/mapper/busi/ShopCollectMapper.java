package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.ShopCollect;
import com.zengshi.ecp.staff.dao.model.ShopCollectCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ShopCollectMapper {
    Long countByExample(ShopCollectCriteria example) throws DataAccessException;

    int deleteByExample(ShopCollectCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(ShopCollect record) throws DataAccessException;

    int insertSelective(ShopCollect record) throws DataAccessException;

    List<ShopCollect> selectByExample(ShopCollectCriteria example) throws DataAccessException;

    ShopCollect selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") ShopCollect record, @Param("example") ShopCollectCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") ShopCollect record, @Param("example") ShopCollectCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(ShopCollect record) throws DataAccessException;

    int updateByPrimaryKey(ShopCollect record) throws DataAccessException;
    
    Long countByShopId(ShopCollect record) throws DataAccessException;
}