package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.ShopStaffGroup;
import com.zengshi.ecp.staff.dao.model.ShopStaffGroupCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ShopStaffGroupMapper {
    Long countByExample(ShopStaffGroupCriteria example) throws DataAccessException;

    int deleteByExample(ShopStaffGroupCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(ShopStaffGroup record) throws DataAccessException;

    int insertSelective(ShopStaffGroup record) throws DataAccessException;

    List<ShopStaffGroup> selectByExample(ShopStaffGroupCriteria example) throws DataAccessException;

    ShopStaffGroup selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") ShopStaffGroup record, @Param("example") ShopStaffGroupCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") ShopStaffGroup record, @Param("example") ShopStaffGroupCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(ShopStaffGroup record) throws DataAccessException;

    int updateByPrimaryKey(ShopStaffGroup record) throws DataAccessException;
}