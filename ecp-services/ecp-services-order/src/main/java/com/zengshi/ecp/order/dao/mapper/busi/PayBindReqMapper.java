package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.PayBindReq;
import com.zengshi.ecp.order.dao.model.PayBindReqCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PayBindReqMapper {
    Long countByExample(PayBindReqCriteria example) throws DataAccessException;

    int deleteByExample(PayBindReqCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(PayBindReq record) throws DataAccessException;

    int insertSelective(PayBindReq record) throws DataAccessException;

    List<PayBindReq> selectByExample(PayBindReqCriteria example) throws DataAccessException;

    PayBindReq selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PayBindReq record, @Param("example") PayBindReqCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PayBindReq record, @Param("example") PayBindReqCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PayBindReq record) throws DataAccessException;

    int updateByPrimaryKey(PayBindReq record) throws DataAccessException;
}