package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdMainStaffIdx;
import com.zengshi.ecp.order.dao.model.OrdMainStaffIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdMainStaffIdxMapper {
    Long countByExample(OrdMainStaffIdxCriteria example) throws DataAccessException;

    int deleteByExample(OrdMainStaffIdxCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdMainStaffIdx record) throws DataAccessException;

    int insertSelective(OrdMainStaffIdx record) throws DataAccessException;

    List<OrdMainStaffIdx> selectByExample(OrdMainStaffIdxCriteria example) throws DataAccessException;

    OrdMainStaffIdx selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdMainStaffIdx record, @Param("example") OrdMainStaffIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdMainStaffIdx record, @Param("example") OrdMainStaffIdxCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdMainStaffIdx record) throws DataAccessException;

    int updateByPrimaryKey(OrdMainStaffIdx record) throws DataAccessException;
}