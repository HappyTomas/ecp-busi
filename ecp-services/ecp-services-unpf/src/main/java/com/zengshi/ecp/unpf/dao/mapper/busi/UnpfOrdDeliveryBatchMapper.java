package com.zengshi.ecp.unpf.dao.mapper.busi;

import com.zengshi.ecp.unpf.dao.model.UnpfOrdDeliveryBatch;
import com.zengshi.ecp.unpf.dao.model.UnpfOrdDeliveryBatchCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface UnpfOrdDeliveryBatchMapper {
    Long countByExample(UnpfOrdDeliveryBatchCriteria example) throws DataAccessException;

    int deleteByExample(UnpfOrdDeliveryBatchCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(UnpfOrdDeliveryBatch record) throws DataAccessException;

    int insertSelective(UnpfOrdDeliveryBatch record) throws DataAccessException;

    List<UnpfOrdDeliveryBatch> selectByExample(UnpfOrdDeliveryBatchCriteria example) throws DataAccessException;

    UnpfOrdDeliveryBatch selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") UnpfOrdDeliveryBatch record, @Param("example") UnpfOrdDeliveryBatchCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") UnpfOrdDeliveryBatch record, @Param("example") UnpfOrdDeliveryBatchCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(UnpfOrdDeliveryBatch record) throws DataAccessException;

    int updateByPrimaryKey(UnpfOrdDeliveryBatch record) throws DataAccessException;
}