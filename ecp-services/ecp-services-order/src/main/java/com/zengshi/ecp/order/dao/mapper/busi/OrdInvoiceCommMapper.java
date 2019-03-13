package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdInvoiceComm;
import com.zengshi.ecp.order.dao.model.OrdInvoiceCommCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdInvoiceCommMapper {
    Long countByExample(OrdInvoiceCommCriteria example) throws DataAccessException;

    int deleteByExample(OrdInvoiceCommCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdInvoiceComm record) throws DataAccessException;

    int insertSelective(OrdInvoiceComm record) throws DataAccessException;

    List<OrdInvoiceComm> selectByExample(OrdInvoiceCommCriteria example) throws DataAccessException;

    OrdInvoiceComm selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdInvoiceComm record, @Param("example") OrdInvoiceCommCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdInvoiceComm record, @Param("example") OrdInvoiceCommCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdInvoiceComm record) throws DataAccessException;

    int updateByPrimaryKey(OrdInvoiceComm record) throws DataAccessException;
}