package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.AcctInfoHisShopIDX;
import com.zengshi.ecp.staff.dao.model.AcctInfoHisShopIDXCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AcctInfoHisShopIDXMapper {
    Long countByExample(AcctInfoHisShopIDXCriteria example) throws DataAccessException;

    int deleteByExample(AcctInfoHisShopIDXCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(AcctInfoHisShopIDX record) throws DataAccessException;

    int insertSelective(AcctInfoHisShopIDX record) throws DataAccessException;

    List<AcctInfoHisShopIDX> selectByExample(AcctInfoHisShopIDXCriteria example) throws DataAccessException;

    AcctInfoHisShopIDX selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AcctInfoHisShopIDX record, @Param("example") AcctInfoHisShopIDXCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AcctInfoHisShopIDX record, @Param("example") AcctInfoHisShopIDXCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AcctInfoHisShopIDX record) throws DataAccessException;

    int updateByPrimaryKey(AcctInfoHisShopIDX record) throws DataAccessException;
}