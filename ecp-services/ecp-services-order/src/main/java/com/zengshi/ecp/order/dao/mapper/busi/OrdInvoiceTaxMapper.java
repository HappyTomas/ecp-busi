package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdInvoiceTax;
import com.zengshi.ecp.order.dao.model.OrdInvoiceTaxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdInvoiceTaxMapper {
    Long countByExample(OrdInvoiceTaxCriteria example) throws DataAccessException;

    int deleteByExample(OrdInvoiceTaxCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdInvoiceTax record) throws DataAccessException;

    int insertSelective(OrdInvoiceTax record) throws DataAccessException;

    List<OrdInvoiceTax> selectByExample(OrdInvoiceTaxCriteria example) throws DataAccessException;

    OrdInvoiceTax selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdInvoiceTax record, @Param("example") OrdInvoiceTaxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdInvoiceTax record, @Param("example") OrdInvoiceTaxCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdInvoiceTax record) throws DataAccessException;

    int updateByPrimaryKey(OrdInvoiceTax record) throws DataAccessException;
}