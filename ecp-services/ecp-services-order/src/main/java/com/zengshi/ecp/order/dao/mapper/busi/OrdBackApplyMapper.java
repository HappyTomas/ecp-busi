package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdBackApply;
import com.zengshi.ecp.order.dao.model.OrdBackApplyCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdBackApplyMapper {
    Long countByExample(OrdBackApplyCriteria example) throws DataAccessException;

    int deleteByExample(OrdBackApplyCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdBackApply record) throws DataAccessException;

    int insertSelective(OrdBackApply record) throws DataAccessException;

    List<OrdBackApply> selectByExample(OrdBackApplyCriteria example) throws DataAccessException;

    OrdBackApply selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdBackApply record, @Param("example") OrdBackApplyCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdBackApply record, @Param("example") OrdBackApplyCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdBackApply record) throws DataAccessException;

    int updateByPrimaryKey(OrdBackApply record) throws DataAccessException;
}