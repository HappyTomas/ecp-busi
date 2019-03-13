package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.AcctInfoShopIDX;
import com.zengshi.ecp.staff.dao.model.AcctInfoShopIDXCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AcctInfoShopIDXMapper {
    Long countByExample(AcctInfoShopIDXCriteria example) throws DataAccessException;

    int deleteByExample(AcctInfoShopIDXCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(AcctInfoShopIDX record) throws DataAccessException;

    int insertSelective(AcctInfoShopIDX record) throws DataAccessException;

    List<AcctInfoShopIDX> selectByExample(AcctInfoShopIDXCriteria example) throws DataAccessException;

    AcctInfoShopIDX selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AcctInfoShopIDX record, @Param("example") AcctInfoShopIDXCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AcctInfoShopIDX record, @Param("example") AcctInfoShopIDXCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AcctInfoShopIDX record) throws DataAccessException;

    int updateByPrimaryKey(AcctInfoShopIDX record) throws DataAccessException;
}