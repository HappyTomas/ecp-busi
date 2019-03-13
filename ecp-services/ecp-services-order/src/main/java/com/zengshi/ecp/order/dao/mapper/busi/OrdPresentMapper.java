package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdPresent;
import com.zengshi.ecp.order.dao.model.OrdPresentCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdPresentMapper {
    Long countByExample(OrdPresentCriteria example) throws DataAccessException;

    int deleteByExample(OrdPresentCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdPresent record) throws DataAccessException;

    int insertSelective(OrdPresent record) throws DataAccessException;

    List<OrdPresent> selectByExample(OrdPresentCriteria example) throws DataAccessException;

    OrdPresent selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdPresent record, @Param("example") OrdPresentCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdPresent record, @Param("example") OrdPresentCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdPresent record) throws DataAccessException;

    int updateByPrimaryKey(OrdPresent record) throws DataAccessException;
}