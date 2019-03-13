package com.zengshi.ecp.staff.dao.mapper.common;

import com.zengshi.ecp.staff.dao.model.PayWay;
import com.zengshi.ecp.staff.dao.model.PayWayCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ShopPayWayMapper {
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