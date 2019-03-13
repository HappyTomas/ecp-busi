package com.zengshi.ecp.order.dao.mapper.common;

import com.zengshi.ecp.order.dao.model.PayQuartzInfo;
import com.zengshi.ecp.order.dao.model.PayQuartzInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PayQuartzInfoMapper {
    Long countByExample(PayQuartzInfoCriteria example) throws DataAccessException;

    int deleteByExample(PayQuartzInfoCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(PayQuartzInfo record) throws DataAccessException;

    int insertSelective(PayQuartzInfo record) throws DataAccessException;

    List<PayQuartzInfo> selectByExample(PayQuartzInfoCriteria example) throws DataAccessException;

    PayQuartzInfo selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PayQuartzInfo record, @Param("example") PayQuartzInfoCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PayQuartzInfo record, @Param("example") PayQuartzInfoCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PayQuartzInfo record) throws DataAccessException;

    int updateByPrimaryKey(PayQuartzInfo record) throws DataAccessException;
}