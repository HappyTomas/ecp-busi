package com.zengshi.ecp.unpf.dao.mapper.common;

import com.zengshi.ecp.unpf.dao.model.UnpfPropRela;
import com.zengshi.ecp.unpf.dao.model.UnpfPropRelaCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface UnpfPropRelaMapper {
    Long countByExample(UnpfPropRelaCriteria example) throws DataAccessException;

    int deleteByExample(UnpfPropRelaCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(UnpfPropRela record) throws DataAccessException;

    int insertSelective(UnpfPropRela record) throws DataAccessException;

    List<UnpfPropRela> selectByExample(UnpfPropRelaCriteria example) throws DataAccessException;

    UnpfPropRela selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") UnpfPropRela record, @Param("example") UnpfPropRelaCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") UnpfPropRela record, @Param("example") UnpfPropRelaCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(UnpfPropRela record) throws DataAccessException;

    int updateByPrimaryKey(UnpfPropRela record) throws DataAccessException;
}