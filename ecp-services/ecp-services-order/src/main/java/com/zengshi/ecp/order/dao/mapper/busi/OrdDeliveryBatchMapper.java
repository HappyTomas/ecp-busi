package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdDeliveryBatch;
import com.zengshi.ecp.order.dao.model.OrdDeliveryBatchCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdDeliveryBatchMapper {
    Long countByExample(OrdDeliveryBatchCriteria example) throws DataAccessException;

    int deleteByExample(OrdDeliveryBatchCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdDeliveryBatch record) throws DataAccessException;

    int insertSelective(OrdDeliveryBatch record) throws DataAccessException;

    List<OrdDeliveryBatch> selectByExample(OrdDeliveryBatchCriteria example) throws DataAccessException;

    OrdDeliveryBatch selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdDeliveryBatch record, @Param("example") OrdDeliveryBatchCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdDeliveryBatch record, @Param("example") OrdDeliveryBatchCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdDeliveryBatch record) throws DataAccessException;

    int updateByPrimaryKey(OrdDeliveryBatch record) throws DataAccessException;
}