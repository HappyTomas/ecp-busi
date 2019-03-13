package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.ShopToStaffIDX;
import com.zengshi.ecp.staff.dao.model.ShopToStaffIDXCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ShopToStaffIDXMapper {
    Long countByExample(ShopToStaffIDXCriteria example) throws DataAccessException;

    int deleteByExample(ShopToStaffIDXCriteria example) throws DataAccessException;

    int insert(ShopToStaffIDX record) throws DataAccessException;

    int insertSelective(ShopToStaffIDX record) throws DataAccessException;

    List<ShopToStaffIDX> selectByExample(ShopToStaffIDXCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") ShopToStaffIDX record, @Param("example") ShopToStaffIDXCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") ShopToStaffIDX record, @Param("example") ShopToStaffIDXCriteria example) throws DataAccessException;
}