package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.ShopVipReal;
import com.zengshi.ecp.staff.dao.model.ShopVipRealCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ShopVipRealMapper {
    Long countByExample(ShopVipRealCriteria example) throws DataAccessException;

    int deleteByExample(ShopVipRealCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(ShopVipReal record) throws DataAccessException;

    int insertSelective(ShopVipReal record) throws DataAccessException;

    List<ShopVipReal> selectByExample(ShopVipRealCriteria example) throws DataAccessException;

    ShopVipReal selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") ShopVipReal record, @Param("example") ShopVipRealCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") ShopVipReal record, @Param("example") ShopVipRealCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(ShopVipReal record) throws DataAccessException;

    int updateByPrimaryKey(ShopVipReal record) throws DataAccessException;
}