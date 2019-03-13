package com.zengshi.ecp.unpf.dao.mapper.busi;

import com.zengshi.ecp.unpf.dao.model.UnpfStockLimit;
import com.zengshi.ecp.unpf.dao.model.UnpfStockLimitCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface UnpfStockLimitMapper {
    Long countByExample(UnpfStockLimitCriteria example) throws DataAccessException;

    int deleteByExample(UnpfStockLimitCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(UnpfStockLimit record) throws DataAccessException;

    int insertSelective(UnpfStockLimit record) throws DataAccessException;

    List<UnpfStockLimit> selectByExample(UnpfStockLimitCriteria example) throws DataAccessException;

    UnpfStockLimit selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") UnpfStockLimit record, @Param("example") UnpfStockLimitCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") UnpfStockLimit record, @Param("example") UnpfStockLimitCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(UnpfStockLimit record) throws DataAccessException;

    int updateByPrimaryKey(UnpfStockLimit record) throws DataAccessException;
}