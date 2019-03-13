package com.zengshi.ecp.coupon.dao.mapper.busi;

import com.zengshi.ecp.coupon.dao.model.CoupDetail;
import com.zengshi.ecp.coupon.dao.model.CoupDetailCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CoupDetailMapper {
    Long countByExample(CoupDetailCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CoupDetail record) throws DataAccessException;

    int insertSelective(CoupDetail record) throws DataAccessException;

    List<CoupDetail> selectByExample(CoupDetailCriteria example) throws DataAccessException;

    CoupDetail selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CoupDetail record, @Param("example") CoupDetailCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CoupDetail record, @Param("example") CoupDetailCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CoupDetail record) throws DataAccessException;

    int updateByPrimaryKey(CoupDetail record) throws DataAccessException;
}
