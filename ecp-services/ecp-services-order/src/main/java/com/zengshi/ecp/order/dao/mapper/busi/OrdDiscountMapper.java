package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdDiscount;
import com.zengshi.ecp.order.dao.model.OrdDiscountCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdDiscountMapper {
    Long countByExample(OrdDiscountCriteria example) throws DataAccessException;

    int deleteByExample(OrdDiscountCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdDiscount record) throws DataAccessException;

    int insertSelective(OrdDiscount record) throws DataAccessException;

    List<OrdDiscount> selectByExample(OrdDiscountCriteria example) throws DataAccessException;

    OrdDiscount selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdDiscount record, @Param("example") OrdDiscountCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdDiscount record, @Param("example") OrdDiscountCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdDiscount record) throws DataAccessException;

    int updateByPrimaryKey(OrdDiscount record) throws DataAccessException;
}