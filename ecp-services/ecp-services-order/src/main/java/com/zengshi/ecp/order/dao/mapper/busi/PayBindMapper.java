package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.PayBind;
import com.zengshi.ecp.order.dao.model.PayBindCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PayBindMapper {
    Long countByExample(PayBindCriteria example) throws DataAccessException;

    int deleteByExample(PayBindCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(PayBind record) throws DataAccessException;

    int insertSelective(PayBind record) throws DataAccessException;

    List<PayBind> selectByExample(PayBindCriteria example) throws DataAccessException;

    PayBind selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PayBind record, @Param("example") PayBindCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PayBind record, @Param("example") PayBindCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PayBind record) throws DataAccessException;

    int updateByPrimaryKey(PayBind record) throws DataAccessException;
}