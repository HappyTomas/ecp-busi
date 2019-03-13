package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.ShopShipper;
import com.zengshi.ecp.staff.dao.model.ShopShipperCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ShopShipperMapper {
    Long countByExample(ShopShipperCriteria example) throws DataAccessException;

    int deleteByExample(ShopShipperCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(ShopShipper record) throws DataAccessException;

    int insertSelective(ShopShipper record) throws DataAccessException;

    List<ShopShipper> selectByExample(ShopShipperCriteria example) throws DataAccessException;

    ShopShipper selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") ShopShipper record, @Param("example") ShopShipperCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") ShopShipper record, @Param("example") ShopShipperCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(ShopShipper record) throws DataAccessException;

    int updateByPrimaryKey(ShopShipper record) throws DataAccessException;
}