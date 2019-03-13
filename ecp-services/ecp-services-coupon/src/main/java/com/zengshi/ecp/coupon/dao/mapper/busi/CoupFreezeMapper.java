package com.zengshi.ecp.coupon.dao.mapper.busi;

import com.zengshi.ecp.coupon.dao.model.CoupFreeze;
import com.zengshi.ecp.coupon.dao.model.CoupFreezeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CoupFreezeMapper {
    Long countByExample(CoupFreezeCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CoupFreeze record) throws DataAccessException;

    int insertSelective(CoupFreeze record) throws DataAccessException;

    List<CoupFreeze> selectByExample(CoupFreezeCriteria example) throws DataAccessException;

    CoupFreeze selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CoupFreeze record, @Param("example") CoupFreezeCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CoupFreeze record, @Param("example") CoupFreezeCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CoupFreeze record) throws DataAccessException;

    int updateByPrimaryKey(CoupFreeze record) throws DataAccessException;
}
