package com.zengshi.ecp.unpf.dao.mapper.busi;

import com.zengshi.ecp.unpf.dao.model.UnpfStockCatgLimit;
import com.zengshi.ecp.unpf.dao.model.UnpfStockCatgLimitCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface UnpfStockCatgLimitMapper {
    Long countByExample(UnpfStockCatgLimitCriteria example) throws DataAccessException;

    int deleteByExample(UnpfStockCatgLimitCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(UnpfStockCatgLimit record) throws DataAccessException;

    int insertSelective(UnpfStockCatgLimit record) throws DataAccessException;

    List<UnpfStockCatgLimit> selectByExample(UnpfStockCatgLimitCriteria example) throws DataAccessException;

    UnpfStockCatgLimit selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") UnpfStockCatgLimit record, @Param("example") UnpfStockCatgLimitCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") UnpfStockCatgLimit record, @Param("example") UnpfStockCatgLimitCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(UnpfStockCatgLimit record) throws DataAccessException;

    int updateByPrimaryKey(UnpfStockCatgLimit record) throws DataAccessException;
}