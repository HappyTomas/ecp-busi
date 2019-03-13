package com.zengshi.ecp.unpf.dao.mapper.busi;

import com.zengshi.ecp.unpf.dao.model.UnpfGdsUnsendHis;
import com.zengshi.ecp.unpf.dao.model.UnpfGdsUnsendHisCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface UnpfGdsUnsendHisMapper {
    Long countByExample(UnpfGdsUnsendHisCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(UnpfGdsUnsendHis record) throws DataAccessException;

    int insertSelective(UnpfGdsUnsendHis record) throws DataAccessException;

    List<UnpfGdsUnsendHis> selectByExample(UnpfGdsUnsendHisCriteria example) throws DataAccessException;

    UnpfGdsUnsendHis selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") UnpfGdsUnsendHis record, @Param("example") UnpfGdsUnsendHisCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") UnpfGdsUnsendHis record, @Param("example") UnpfGdsUnsendHisCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(UnpfGdsUnsendHis record) throws DataAccessException;

    int updateByPrimaryKey(UnpfGdsUnsendHis record) throws DataAccessException;
}