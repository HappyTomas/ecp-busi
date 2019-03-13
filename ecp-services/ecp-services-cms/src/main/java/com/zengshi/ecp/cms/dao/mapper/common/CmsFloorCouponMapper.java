package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsFloorCoupon;
import com.zengshi.ecp.cms.dao.model.CmsFloorCouponCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsFloorCouponMapper {
    Long countByExample(CmsFloorCouponCriteria example) throws DataAccessException;

    int deleteByExample(CmsFloorCouponCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsFloorCoupon record) throws DataAccessException;

    int insertSelective(CmsFloorCoupon record) throws DataAccessException;

    List<CmsFloorCoupon> selectByExample(CmsFloorCouponCriteria example) throws DataAccessException;

    CmsFloorCoupon selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsFloorCoupon record, @Param("example") CmsFloorCouponCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsFloorCoupon record, @Param("example") CmsFloorCouponCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsFloorCoupon record) throws DataAccessException;

    int updateByPrimaryKey(CmsFloorCoupon record) throws DataAccessException;
}
