package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.AcctTradeShopIDX;
import com.zengshi.ecp.staff.dao.model.AcctTradeShopIDXCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AcctTradeShopIDXMapper {
    Long countByExample(AcctTradeShopIDXCriteria example) throws DataAccessException;

    int deleteByExample(AcctTradeShopIDXCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(AcctTradeShopIDX record) throws DataAccessException;

    int insertSelective(AcctTradeShopIDX record) throws DataAccessException;

    List<AcctTradeShopIDX> selectByExample(AcctTradeShopIDXCriteria example) throws DataAccessException;

    AcctTradeShopIDX selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AcctTradeShopIDX record, @Param("example") AcctTradeShopIDXCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AcctTradeShopIDX record, @Param("example") AcctTradeShopIDXCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AcctTradeShopIDX record) throws DataAccessException;

    int updateByPrimaryKey(AcctTradeShopIDX record) throws DataAccessException;
}