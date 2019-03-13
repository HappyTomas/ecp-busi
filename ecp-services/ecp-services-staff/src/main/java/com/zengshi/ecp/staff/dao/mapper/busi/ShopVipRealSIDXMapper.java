package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.ShopVipRealSIDX;
import com.zengshi.ecp.staff.dao.model.ShopVipRealSIDXCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ShopVipRealSIDXMapper {
    Long countByExample(ShopVipRealSIDXCriteria example) throws DataAccessException;

    int deleteByExample(ShopVipRealSIDXCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(ShopVipRealSIDX record) throws DataAccessException;

    int insertSelective(ShopVipRealSIDX record) throws DataAccessException;

    List<ShopVipRealSIDX> selectByExample(ShopVipRealSIDXCriteria example) throws DataAccessException;

    ShopVipRealSIDX selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") ShopVipRealSIDX record, @Param("example") ShopVipRealSIDXCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") ShopVipRealSIDX record, @Param("example") ShopVipRealSIDXCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(ShopVipRealSIDX record) throws DataAccessException;

    int updateByPrimaryKey(ShopVipRealSIDX record) throws DataAccessException;
}