package com.zengshi.ecp.unpf.dao.mapper.busi;

import com.zengshi.ecp.unpf.dao.model.UnpfGdsUnsend;
import com.zengshi.ecp.unpf.dao.model.UnpfGdsUnsendCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface UnpfGdsUnsendMapper {
    Long countByExample(UnpfGdsUnsendCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(UnpfGdsUnsend record) throws DataAccessException;

    int insertSelective(UnpfGdsUnsend record) throws DataAccessException;

    List<UnpfGdsUnsend> selectByExample(UnpfGdsUnsendCriteria example) throws DataAccessException;

    UnpfGdsUnsend selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") UnpfGdsUnsend record, @Param("example") UnpfGdsUnsendCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") UnpfGdsUnsend record, @Param("example") UnpfGdsUnsendCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(UnpfGdsUnsend record) throws DataAccessException;

    int updateByPrimaryKey(UnpfGdsUnsend record) throws DataAccessException;
}