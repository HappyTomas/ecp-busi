package com.zengshi.ecp.coupon.dao.mapper.busi;

import com.zengshi.ecp.coupon.dao.model.CoupInfo;
import com.zengshi.ecp.coupon.dao.model.CoupInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CoupInfoMapper {
    Long countByExample(CoupInfoCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CoupInfo record) throws DataAccessException;

    int insertSelective(CoupInfo record) throws DataAccessException;

    List<CoupInfo> selectByExample(CoupInfoCriteria example) throws DataAccessException;

    CoupInfo selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CoupInfo record, @Param("example") CoupInfoCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CoupInfo record, @Param("example") CoupInfoCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CoupInfo record) throws DataAccessException;

    int updateByPrimaryKey(CoupInfo record) throws DataAccessException;
}
