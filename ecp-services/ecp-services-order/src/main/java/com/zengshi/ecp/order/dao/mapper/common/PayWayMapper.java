package com.zengshi.ecp.order.dao.mapper.common;

import com.zengshi.ecp.order.dao.model.PayWay;
import com.zengshi.ecp.order.dao.model.PayWayCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PayWayMapper {
    Long countByExample(PayWayCriteria example) throws DataAccessException;

    int deleteByExample(PayWayCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String id) throws DataAccessException;

    int insert(PayWay record) throws DataAccessException;

    int insertSelective(PayWay record) throws DataAccessException;

    List<PayWay> selectByExample(PayWayCriteria example) throws DataAccessException;

    PayWay selectByPrimaryKey(String id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PayWay record, @Param("example") PayWayCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PayWay record, @Param("example") PayWayCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PayWay record) throws DataAccessException;

    int updateByPrimaryKey(PayWay record) throws DataAccessException;
}