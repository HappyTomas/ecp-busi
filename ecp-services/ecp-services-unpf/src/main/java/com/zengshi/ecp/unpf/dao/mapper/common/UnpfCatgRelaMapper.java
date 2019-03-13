package com.zengshi.ecp.unpf.dao.mapper.common;

import com.zengshi.ecp.unpf.dao.model.UnpfCatgRela;
import com.zengshi.ecp.unpf.dao.model.UnpfCatgRelaCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface UnpfCatgRelaMapper {
    Long countByExample(UnpfCatgRelaCriteria example) throws DataAccessException;

    int deleteByExample(UnpfCatgRelaCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(UnpfCatgRela record) throws DataAccessException;

    int insertSelective(UnpfCatgRela record) throws DataAccessException;

    List<UnpfCatgRela> selectByExample(UnpfCatgRelaCriteria example) throws DataAccessException;

    UnpfCatgRela selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") UnpfCatgRela record, @Param("example") UnpfCatgRelaCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") UnpfCatgRela record, @Param("example") UnpfCatgRelaCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(UnpfCatgRela record) throws DataAccessException;

    int updateByPrimaryKey(UnpfCatgRela record) throws DataAccessException;
}