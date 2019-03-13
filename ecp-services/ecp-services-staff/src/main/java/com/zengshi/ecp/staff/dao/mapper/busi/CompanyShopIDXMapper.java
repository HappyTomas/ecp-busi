package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.CompanyShopIDX;
import com.zengshi.ecp.staff.dao.model.CompanyShopIDXCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CompanyShopIDXMapper {
    Long countByExample(CompanyShopIDXCriteria example) throws DataAccessException;

    int deleteByExample(CompanyShopIDXCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long shopId) throws DataAccessException;

    int insert(CompanyShopIDX record) throws DataAccessException;

    int insertSelective(CompanyShopIDX record) throws DataAccessException;

    List<CompanyShopIDX> selectByExample(CompanyShopIDXCriteria example) throws DataAccessException;

    CompanyShopIDX selectByPrimaryKey(Long shopId) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CompanyShopIDX record, @Param("example") CompanyShopIDXCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CompanyShopIDX record, @Param("example") CompanyShopIDXCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CompanyShopIDX record) throws DataAccessException;

    int updateByPrimaryKey(CompanyShopIDX record) throws DataAccessException;
}