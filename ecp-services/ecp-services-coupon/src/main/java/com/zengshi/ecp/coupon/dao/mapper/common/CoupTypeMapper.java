package com.zengshi.ecp.coupon.dao.mapper.common;

import com.zengshi.ecp.coupon.dao.model.CoupType;
import com.zengshi.ecp.coupon.dao.model.CoupTypeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CoupTypeMapper {
    Long countByExample(CoupTypeCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CoupType record) throws DataAccessException;

    int insertSelective(CoupType record) throws DataAccessException;

    List<CoupType> selectByExample(CoupTypeCriteria example) throws DataAccessException;

    CoupType selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CoupType record, @Param("example") CoupTypeCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CoupType record, @Param("example") CoupTypeCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CoupType record) throws DataAccessException;

    int updateByPrimaryKey(CoupType record) throws DataAccessException;
}
