package com.zengshi.ecp.sys.dao.mapper.common;

import com.zengshi.ecp.sys.dao.model.BaseAreaAdmin;
import com.zengshi.ecp.sys.dao.model.BaseAreaAdminCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface BaseAreaAdminMapper {
    Long countByExample(BaseAreaAdminCriteria example) throws DataAccessException;

    int deleteByExample(BaseAreaAdminCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String areaCode) throws DataAccessException;

    int insert(BaseAreaAdmin record) throws DataAccessException;

    int insertSelective(BaseAreaAdmin record) throws DataAccessException;

    List<BaseAreaAdmin> selectByExample(BaseAreaAdminCriteria example) throws DataAccessException;

    BaseAreaAdmin selectByPrimaryKey(String areaCode) throws DataAccessException;

    int updateByExampleSelective(@Param("record") BaseAreaAdmin record, @Param("example") BaseAreaAdminCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") BaseAreaAdmin record, @Param("example") BaseAreaAdminCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(BaseAreaAdmin record) throws DataAccessException;

    int updateByPrimaryKey(BaseAreaAdmin record) throws DataAccessException;
}
