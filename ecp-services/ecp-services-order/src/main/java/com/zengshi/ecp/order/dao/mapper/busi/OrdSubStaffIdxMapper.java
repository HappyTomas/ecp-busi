package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdSubStaffIdx;
import com.zengshi.ecp.order.dao.model.OrdSubStaffIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdSubStaffIdxMapper {
    Long countByExample(OrdSubStaffIdxCriteria example) throws DataAccessException;

    int deleteByExample(OrdSubStaffIdxCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdSubStaffIdx record) throws DataAccessException;

    int insertSelective(OrdSubStaffIdx record) throws DataAccessException;

    List<OrdSubStaffIdx> selectByExample(OrdSubStaffIdxCriteria example) throws DataAccessException;

    OrdSubStaffIdx selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdSubStaffIdx record, @Param("example") OrdSubStaffIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdSubStaffIdx record, @Param("example") OrdSubStaffIdxCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdSubStaffIdx record) throws DataAccessException;

    int updateByPrimaryKey(OrdSubStaffIdx record) throws DataAccessException;
}