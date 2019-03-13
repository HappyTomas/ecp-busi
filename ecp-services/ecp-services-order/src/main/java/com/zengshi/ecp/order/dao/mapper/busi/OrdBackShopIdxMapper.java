package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdBackShopIdx;
import com.zengshi.ecp.order.dao.model.OrdBackShopIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdBackShopIdxMapper {
    Long countByExample(OrdBackShopIdxCriteria example) throws DataAccessException;

    int deleteByExample(OrdBackShopIdxCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdBackShopIdx record) throws DataAccessException;

    int insertSelective(OrdBackShopIdx record) throws DataAccessException;

    List<OrdBackShopIdx> selectByExample(OrdBackShopIdxCriteria example) throws DataAccessException;

    OrdBackShopIdx selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdBackShopIdx record, @Param("example") OrdBackShopIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdBackShopIdx record, @Param("example") OrdBackShopIdxCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdBackShopIdx record) throws DataAccessException;

    int updateByPrimaryKey(OrdBackShopIdx record) throws DataAccessException;
}