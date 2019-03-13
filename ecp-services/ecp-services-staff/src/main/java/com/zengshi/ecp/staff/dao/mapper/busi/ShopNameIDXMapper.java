package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.ShopNameIDX;
import com.zengshi.ecp.staff.dao.model.ShopNameIDXCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ShopNameIDXMapper {
    Long countByExample(ShopNameIDXCriteria example) throws DataAccessException;

    int deleteByExample(ShopNameIDXCriteria example) throws DataAccessException;

    int insert(ShopNameIDX record) throws DataAccessException;

    int insertSelective(ShopNameIDX record) throws DataAccessException;

    List<ShopNameIDX> selectByExample(ShopNameIDXCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") ShopNameIDX record, @Param("example") ShopNameIDXCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") ShopNameIDX record, @Param("example") ShopNameIDXCriteria example) throws DataAccessException;
}