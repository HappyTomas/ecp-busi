package com.zengshi.ecp.sys.dao.mapper.common;

import com.zengshi.ecp.sys.dao.model.BaseSmsCfg;
import com.zengshi.ecp.sys.dao.model.BaseSmsCfgCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface BaseSmsCfgMapper {
    Long countByExample(BaseSmsCfgCriteria example) throws DataAccessException;

    int deleteByExample(BaseSmsCfgCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String smsGateway) throws DataAccessException;

    int insert(BaseSmsCfg record) throws DataAccessException;

    int insertSelective(BaseSmsCfg record) throws DataAccessException;

    List<BaseSmsCfg> selectByExample(BaseSmsCfgCriteria example) throws DataAccessException;

    BaseSmsCfg selectByPrimaryKey(String smsGateway) throws DataAccessException;

    int updateByExampleSelective(@Param("record") BaseSmsCfg record, @Param("example") BaseSmsCfgCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") BaseSmsCfg record, @Param("example") BaseSmsCfgCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(BaseSmsCfg record) throws DataAccessException;

    int updateByPrimaryKey(BaseSmsCfg record) throws DataAccessException;
}
