package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdBackStaffIdx;
import com.zengshi.ecp.order.dao.model.OrdBackStaffIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdBackStaffIdxMapper {
    Long countByExample(OrdBackStaffIdxCriteria example) throws DataAccessException;

    int deleteByExample(OrdBackStaffIdxCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdBackStaffIdx record) throws DataAccessException;

    int insertSelective(OrdBackStaffIdx record) throws DataAccessException;

    List<OrdBackStaffIdx> selectByExample(OrdBackStaffIdxCriteria example) throws DataAccessException;

    OrdBackStaffIdx selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdBackStaffIdx record, @Param("example") OrdBackStaffIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdBackStaffIdx record, @Param("example") OrdBackStaffIdxCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdBackStaffIdx record) throws DataAccessException;

    int updateByPrimaryKey(OrdBackStaffIdx record) throws DataAccessException;
}