package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.PayRepeat;
import com.zengshi.ecp.order.dao.model.PayRepeatCriteria;
import com.zengshi.ecp.order.dao.model.PayRepeatKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PayRepeatMapper {
    Long countByExample(PayRepeatCriteria example) throws DataAccessException;

    int deleteByExample(PayRepeatCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(PayRepeatKey key) throws DataAccessException;

    int insert(PayRepeat record) throws DataAccessException;

    int insertSelective(PayRepeat record) throws DataAccessException;

    List<PayRepeat> selectByExample(PayRepeatCriteria example) throws DataAccessException;

    PayRepeat selectByPrimaryKey(PayRepeatKey key) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PayRepeat record, @Param("example") PayRepeatCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PayRepeat record, @Param("example") PayRepeatCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PayRepeat record) throws DataAccessException;

    int updateByPrimaryKey(PayRepeat record) throws DataAccessException;
}