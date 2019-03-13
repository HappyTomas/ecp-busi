package com.zengshi.ecp.coupon.dao.mapper.busi;

import com.zengshi.ecp.coupon.dao.model.CoupConsumeIdx;
import com.zengshi.ecp.coupon.dao.model.CoupConsumeIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CoupConsumeIdxMapper {
    Long countByExample(CoupConsumeIdxCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CoupConsumeIdx record) throws DataAccessException;

    int insertSelective(CoupConsumeIdx record) throws DataAccessException;

    List<CoupConsumeIdx> selectByExample(CoupConsumeIdxCriteria example) throws DataAccessException;

    CoupConsumeIdx selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CoupConsumeIdx record, @Param("example") CoupConsumeIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CoupConsumeIdx record, @Param("example") CoupConsumeIdxCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CoupConsumeIdx record) throws DataAccessException;

    int updateByPrimaryKey(CoupConsumeIdx record) throws DataAccessException;
}
