package com.zengshi.ecp.order.dao.mapper.common;

import com.zengshi.ecp.order.dao.model.ImOrdBelong;
import com.zengshi.ecp.order.dao.model.ImOrdBelongCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ImOrdBelongMapper {
    Long countByExample(ImOrdBelongCriteria example) throws DataAccessException;

    int deleteByExample(ImOrdBelongCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String ordId) throws DataAccessException;

    int insert(ImOrdBelong record) throws DataAccessException;

    int insertSelective(ImOrdBelong record) throws DataAccessException;

    List<ImOrdBelong> selectByExample(ImOrdBelongCriteria example) throws DataAccessException;

    ImOrdBelong selectByPrimaryKey(String ordId) throws DataAccessException;

    int updateByExampleSelective(@Param("record") ImOrdBelong record, @Param("example") ImOrdBelongCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") ImOrdBelong record, @Param("example") ImOrdBelongCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(ImOrdBelong record) throws DataAccessException;

    int updateByPrimaryKey(ImOrdBelong record) throws DataAccessException;
}