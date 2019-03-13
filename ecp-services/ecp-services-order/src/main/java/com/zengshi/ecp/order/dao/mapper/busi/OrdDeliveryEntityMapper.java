package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdDeliveryEntity;
import com.zengshi.ecp.order.dao.model.OrdDeliveryEntityCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdDeliveryEntityMapper {
    Long countByExample(OrdDeliveryEntityCriteria example) throws DataAccessException;

    int deleteByExample(OrdDeliveryEntityCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdDeliveryEntity record) throws DataAccessException;

    int insertSelective(OrdDeliveryEntity record) throws DataAccessException;

    List<OrdDeliveryEntity> selectByExample(OrdDeliveryEntityCriteria example) throws DataAccessException;

    OrdDeliveryEntity selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdDeliveryEntity record, @Param("example") OrdDeliveryEntityCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdDeliveryEntity record, @Param("example") OrdDeliveryEntityCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdDeliveryEntity record) throws DataAccessException;

    int updateByPrimaryKey(OrdDeliveryEntity record) throws DataAccessException;
}