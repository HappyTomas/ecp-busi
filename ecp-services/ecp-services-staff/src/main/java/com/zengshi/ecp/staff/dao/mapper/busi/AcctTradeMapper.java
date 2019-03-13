package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.AcctTrade;
import com.zengshi.ecp.staff.dao.model.AcctTradeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AcctTradeMapper {
    Long countByExample(AcctTradeCriteria example) throws DataAccessException;

    int deleteByExample(AcctTradeCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(AcctTrade record) throws DataAccessException;

    int insertSelective(AcctTrade record) throws DataAccessException;

    List<AcctTrade> selectByExample(AcctTradeCriteria example) throws DataAccessException;

    AcctTrade selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AcctTrade record, @Param("example") AcctTradeCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AcctTrade record, @Param("example") AcctTradeCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AcctTrade record) throws DataAccessException;

    int updateByPrimaryKey(AcctTrade record) throws DataAccessException;
    
    Long sumByExample(AcctTradeCriteria example) throws DataAccessException;
}