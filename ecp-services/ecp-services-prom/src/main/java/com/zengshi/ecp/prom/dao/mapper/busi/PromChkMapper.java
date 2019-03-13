package com.zengshi.ecp.prom.dao.mapper.busi;

import com.zengshi.ecp.prom.dao.model.PromChk;
import com.zengshi.ecp.prom.dao.model.PromChkCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PromChkMapper {
    Long countByExample(PromChkCriteria example) throws DataAccessException;

    int deleteByExample(PromChkCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(PromChk record) throws DataAccessException;

    int insertSelective(PromChk record) throws DataAccessException;

    List<PromChk> selectByExample(PromChkCriteria example) throws DataAccessException;

    PromChk selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PromChk record, @Param("example") PromChkCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PromChk record, @Param("example") PromChkCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PromChk record) throws DataAccessException;

    int updateByPrimaryKey(PromChk record) throws DataAccessException;
}