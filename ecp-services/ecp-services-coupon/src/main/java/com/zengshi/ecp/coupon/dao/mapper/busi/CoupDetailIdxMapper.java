package com.zengshi.ecp.coupon.dao.mapper.busi;

import com.zengshi.ecp.coupon.dao.model.CoupDetailIdx;
import com.zengshi.ecp.coupon.dao.model.CoupDetailIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CoupDetailIdxMapper {
    Long countByExample(CoupDetailIdxCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CoupDetailIdx record) throws DataAccessException;

    int insertSelective(CoupDetailIdx record) throws DataAccessException;

    List<CoupDetailIdx> selectByExample(CoupDetailIdxCriteria example) throws DataAccessException;

    CoupDetailIdx selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CoupDetailIdx record, @Param("example") CoupDetailIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CoupDetailIdx record, @Param("example") CoupDetailIdxCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CoupDetailIdx record) throws DataAccessException;

    int updateByPrimaryKey(CoupDetailIdx record) throws DataAccessException;
}
