package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdBackGift;
import com.zengshi.ecp.order.dao.model.OrdBackGiftCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdBackGiftMapper {
    Long countByExample(OrdBackGiftCriteria example) throws DataAccessException;

    int deleteByExample(OrdBackGiftCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdBackGift record) throws DataAccessException;

    int insertSelective(OrdBackGift record) throws DataAccessException;

    List<OrdBackGift> selectByExample(OrdBackGiftCriteria example) throws DataAccessException;

    OrdBackGift selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdBackGift record, @Param("example") OrdBackGiftCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdBackGift record, @Param("example") OrdBackGiftCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdBackGift record) throws DataAccessException;

    int updateByPrimaryKey(OrdBackGift record) throws DataAccessException;
}