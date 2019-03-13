package com.zengshi.ecp.unpf.dao.mapper.busi;

import com.zengshi.ecp.unpf.dao.model.UnpfGdsCatgLimit;
import com.zengshi.ecp.unpf.dao.model.UnpfGdsCatgLimitCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface UnpfGdsCatgLimitMapper {
    Long countByExample(UnpfGdsCatgLimitCriteria example) throws DataAccessException;

    int deleteByExample(UnpfGdsCatgLimitCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(UnpfGdsCatgLimit record) throws DataAccessException;

    int insertSelective(UnpfGdsCatgLimit record) throws DataAccessException;

    List<UnpfGdsCatgLimit> selectByExample(UnpfGdsCatgLimitCriteria example) throws DataAccessException;

    UnpfGdsCatgLimit selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") UnpfGdsCatgLimit record, @Param("example") UnpfGdsCatgLimitCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") UnpfGdsCatgLimit record, @Param("example") UnpfGdsCatgLimitCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(UnpfGdsCatgLimit record) throws DataAccessException;

    int updateByPrimaryKey(UnpfGdsCatgLimit record) throws DataAccessException;
}