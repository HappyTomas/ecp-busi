package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdProm;
import com.zengshi.ecp.order.dao.model.OrdPromCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdPromMapper {
    Long countByExample(OrdPromCriteria example) throws DataAccessException;

    int deleteByExample(OrdPromCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdProm record) throws DataAccessException;

    int insertSelective(OrdProm record) throws DataAccessException;

    List<OrdProm> selectByExample(OrdPromCriteria example) throws DataAccessException;

    OrdProm selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdProm record, @Param("example") OrdPromCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdProm record, @Param("example") OrdPromCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdProm record) throws DataAccessException;

    int updateByPrimaryKey(OrdProm record) throws DataAccessException;
}