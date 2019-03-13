package com.zengshi.ecp.prom.dao.mapper.busi;

import com.zengshi.ecp.prom.dao.model.PromOrdRel;
import com.zengshi.ecp.prom.dao.model.PromOrdRelCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PromOrdRelMapper {
    Long countByExample(PromOrdRelCriteria example) throws DataAccessException;

    int deleteByExample(PromOrdRelCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(PromOrdRel record) throws DataAccessException;

    int insertSelective(PromOrdRel record) throws DataAccessException;

    List<PromOrdRel> selectByExample(PromOrdRelCriteria example) throws DataAccessException;

    PromOrdRel selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PromOrdRel record, @Param("example") PromOrdRelCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PromOrdRel record, @Param("example") PromOrdRelCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PromOrdRel record) throws DataAccessException;

    int updateByPrimaryKey(PromOrdRel record) throws DataAccessException;
}