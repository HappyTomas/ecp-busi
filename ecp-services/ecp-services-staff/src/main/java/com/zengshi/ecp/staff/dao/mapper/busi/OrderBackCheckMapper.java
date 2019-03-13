package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.OrderBackCheck;
import com.zengshi.ecp.staff.dao.model.OrderBackCheckCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrderBackCheckMapper {
    Long countByExample(OrderBackCheckCriteria example) throws DataAccessException;

    int deleteByExample(OrderBackCheckCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long backId) throws DataAccessException;

    int insert(OrderBackCheck record) throws DataAccessException;

    int insertSelective(OrderBackCheck record) throws DataAccessException;

    List<OrderBackCheck> selectByExample(OrderBackCheckCriteria example) throws DataAccessException;

    OrderBackCheck selectByPrimaryKey(Long backId) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrderBackCheck record, @Param("example") OrderBackCheckCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrderBackCheck record, @Param("example") OrderBackCheckCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrderBackCheck record) throws DataAccessException;

    int updateByPrimaryKey(OrderBackCheck record) throws DataAccessException;
}