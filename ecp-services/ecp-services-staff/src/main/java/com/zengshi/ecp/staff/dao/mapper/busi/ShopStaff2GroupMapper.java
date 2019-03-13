package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.ShopStaff2Group;
import com.zengshi.ecp.staff.dao.model.ShopStaff2GroupCriteria;
import com.zengshi.ecp.staff.dao.model.ShopStaff2GroupKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ShopStaff2GroupMapper {
    Long countByExample(ShopStaff2GroupCriteria example) throws DataAccessException;

    int deleteByExample(ShopStaff2GroupCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(ShopStaff2GroupKey key) throws DataAccessException;

    int insert(ShopStaff2Group record) throws DataAccessException;

    int insertSelective(ShopStaff2Group record) throws DataAccessException;

    List<ShopStaff2Group> selectByExample(ShopStaff2GroupCriteria example) throws DataAccessException;

    ShopStaff2Group selectByPrimaryKey(ShopStaff2GroupKey key) throws DataAccessException;

    int updateByExampleSelective(@Param("record") ShopStaff2Group record, @Param("example") ShopStaff2GroupCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") ShopStaff2Group record, @Param("example") ShopStaff2GroupCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(ShopStaff2Group record) throws DataAccessException;

    int updateByPrimaryKey(ShopStaff2Group record) throws DataAccessException;
}