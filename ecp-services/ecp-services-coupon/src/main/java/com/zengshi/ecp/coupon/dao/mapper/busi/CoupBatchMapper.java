package com.zengshi.ecp.coupon.dao.mapper.busi;

import com.zengshi.ecp.coupon.dao.model.CoupBatch;
import com.zengshi.ecp.coupon.dao.model.CoupBatchCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CoupBatchMapper {
    Long countByExample(CoupBatchCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CoupBatch record) throws DataAccessException;

    int insertSelective(CoupBatch record) throws DataAccessException;

    List<CoupBatch> selectByExample(CoupBatchCriteria example) throws DataAccessException;

    CoupBatch selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CoupBatch record, @Param("example") CoupBatchCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CoupBatch record, @Param("example") CoupBatchCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CoupBatch record) throws DataAccessException;

    int updateByPrimaryKey(CoupBatch record) throws DataAccessException;
}
