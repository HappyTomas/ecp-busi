package com.zengshi.ecp.unpf.dao.mapper.common;

import com.zengshi.ecp.unpf.dao.model.UnpfPropValueRela;
import com.zengshi.ecp.unpf.dao.model.UnpfPropValueRelaCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface UnpfPropValueRelaMapper {
    Long countByExample(UnpfPropValueRelaCriteria example) throws DataAccessException;

    int deleteByExample(UnpfPropValueRelaCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(UnpfPropValueRela record) throws DataAccessException;

    int insertSelective(UnpfPropValueRela record) throws DataAccessException;

    List<UnpfPropValueRela> selectByExample(UnpfPropValueRelaCriteria example) throws DataAccessException;

    UnpfPropValueRela selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") UnpfPropValueRela record, @Param("example") UnpfPropValueRelaCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") UnpfPropValueRela record, @Param("example") UnpfPropValueRelaCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(UnpfPropValueRela record) throws DataAccessException;

    int updateByPrimaryKey(UnpfPropValueRela record) throws DataAccessException;
}