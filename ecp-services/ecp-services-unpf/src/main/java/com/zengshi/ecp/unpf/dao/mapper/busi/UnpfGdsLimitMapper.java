package com.zengshi.ecp.unpf.dao.mapper.busi;

import com.zengshi.ecp.unpf.dao.model.UnpfGdsLimit;
import com.zengshi.ecp.unpf.dao.model.UnpfGdsLimitCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface UnpfGdsLimitMapper {
    Long countByExample(UnpfGdsLimitCriteria example) throws DataAccessException;

    int deleteByExample(UnpfGdsLimitCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(UnpfGdsLimit record) throws DataAccessException;

    int insertSelective(UnpfGdsLimit record) throws DataAccessException;

    List<UnpfGdsLimit> selectByExample(UnpfGdsLimitCriteria example) throws DataAccessException;

    UnpfGdsLimit selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") UnpfGdsLimit record, @Param("example") UnpfGdsLimitCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") UnpfGdsLimit record, @Param("example") UnpfGdsLimitCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(UnpfGdsLimit record) throws DataAccessException;

    int updateByPrimaryKey(UnpfGdsLimit record) throws DataAccessException;
}