package com.zengshi.ecp.prom.dao.mapper.common;

import com.zengshi.ecp.prom.dao.model.PromTypeMsg;
import com.zengshi.ecp.prom.dao.model.PromTypeMsgCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PromTypeMsgMapper {
    Long countByExample(PromTypeMsgCriteria example) throws DataAccessException;

    int deleteByExample(PromTypeMsgCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(PromTypeMsg record) throws DataAccessException;

    int insertSelective(PromTypeMsg record) throws DataAccessException;

    List<PromTypeMsg> selectByExample(PromTypeMsgCriteria example) throws DataAccessException;

    PromTypeMsg selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PromTypeMsg record, @Param("example") PromTypeMsgCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PromTypeMsg record, @Param("example") PromTypeMsgCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PromTypeMsg record) throws DataAccessException;

    int updateByPrimaryKey(PromTypeMsg record) throws DataAccessException;
}