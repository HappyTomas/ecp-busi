package com.zengshi.ecp.prom.dao.mapper.busi;

import com.zengshi.ecp.prom.dao.model.PromOrdPresent;
import com.zengshi.ecp.prom.dao.model.PromOrdPresentCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PromOrdPresentMapper {
    Long countByExample(PromOrdPresentCriteria example) throws DataAccessException;

    int deleteByExample(PromOrdPresentCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(PromOrdPresent record) throws DataAccessException;

    int insertSelective(PromOrdPresent record) throws DataAccessException;

    List<PromOrdPresent> selectByExample(PromOrdPresentCriteria example) throws DataAccessException;

    PromOrdPresent selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PromOrdPresent record, @Param("example") PromOrdPresentCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PromOrdPresent record, @Param("example") PromOrdPresentCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PromOrdPresent record) throws DataAccessException;

    int updateByPrimaryKey(PromOrdPresent record) throws DataAccessException;
}