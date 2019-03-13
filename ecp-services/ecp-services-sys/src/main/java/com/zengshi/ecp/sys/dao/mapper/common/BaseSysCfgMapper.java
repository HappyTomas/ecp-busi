package com.zengshi.ecp.sys.dao.mapper.common;

import com.zengshi.ecp.sys.dao.model.BaseSysCfg;
import com.zengshi.ecp.sys.dao.model.BaseSysCfgCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface BaseSysCfgMapper {
    Long countByExample(BaseSysCfgCriteria example) throws DataAccessException;

    int deleteByExample(BaseSysCfgCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String paraCode) throws DataAccessException;

    int insert(BaseSysCfg record) throws DataAccessException;

    int insertSelective(BaseSysCfg record) throws DataAccessException;

    List<BaseSysCfg> selectByExample(BaseSysCfgCriteria example) throws DataAccessException;

    BaseSysCfg selectByPrimaryKey(String paraCode) throws DataAccessException;

    int updateByExampleSelective(@Param("record") BaseSysCfg record, @Param("example") BaseSysCfgCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") BaseSysCfg record, @Param("example") BaseSysCfgCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(BaseSysCfg record) throws DataAccessException;

    int updateByPrimaryKey(BaseSysCfg record) throws DataAccessException;
}
