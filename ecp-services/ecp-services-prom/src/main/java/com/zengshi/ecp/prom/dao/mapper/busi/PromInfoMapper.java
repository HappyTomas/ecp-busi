package com.zengshi.ecp.prom.dao.mapper.busi;

import com.zengshi.ecp.prom.dao.model.PromInfo;
import com.zengshi.ecp.prom.dao.model.PromInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PromInfoMapper {
    Long countByExample(PromInfoCriteria example) throws DataAccessException;

    int deleteByExample(PromInfoCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(PromInfo record) throws DataAccessException;

    int insertSelective(PromInfo record) throws DataAccessException;

    List<PromInfo> selectByExample(PromInfoCriteria example) throws DataAccessException;

    PromInfo selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PromInfo record, @Param("example") PromInfoCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PromInfo record, @Param("example") PromInfoCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PromInfo record) throws DataAccessException;

    int updateByPrimaryKey(PromInfo record) throws DataAccessException;
}