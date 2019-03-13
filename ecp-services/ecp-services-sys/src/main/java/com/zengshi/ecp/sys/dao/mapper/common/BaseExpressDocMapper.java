package com.zengshi.ecp.sys.dao.mapper.common;

import com.zengshi.ecp.sys.dao.model.BaseExpressDoc;
import com.zengshi.ecp.sys.dao.model.BaseExpressDocCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface BaseExpressDocMapper {
    Long countByExample(BaseExpressDocCriteria example) throws DataAccessException;

    int deleteByExample(BaseExpressDocCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(BaseExpressDoc record) throws DataAccessException;

    int insertSelective(BaseExpressDoc record) throws DataAccessException;

    List<BaseExpressDoc> selectByExample(BaseExpressDocCriteria example) throws DataAccessException;

    BaseExpressDoc selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") BaseExpressDoc record, @Param("example") BaseExpressDocCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") BaseExpressDoc record, @Param("example") BaseExpressDocCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(BaseExpressDoc record) throws DataAccessException;

    int updateByPrimaryKey(BaseExpressDoc record) throws DataAccessException;
}
