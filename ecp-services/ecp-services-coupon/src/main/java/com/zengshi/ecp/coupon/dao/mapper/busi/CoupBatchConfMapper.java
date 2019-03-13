package com.zengshi.ecp.coupon.dao.mapper.busi;

import com.zengshi.ecp.coupon.dao.model.CoupBatchConf;
import com.zengshi.ecp.coupon.dao.model.CoupBatchConfCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CoupBatchConfMapper {
    Long countByExample(CoupBatchConfCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CoupBatchConf record) throws DataAccessException;

    int insertSelective(CoupBatchConf record) throws DataAccessException;

    List<CoupBatchConf> selectByExample(CoupBatchConfCriteria example) throws DataAccessException;

    CoupBatchConf selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CoupBatchConf record, @Param("example") CoupBatchConfCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CoupBatchConf record, @Param("example") CoupBatchConfCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CoupBatchConf record) throws DataAccessException;

    int updateByPrimaryKey(CoupBatchConf record) throws DataAccessException;
}
