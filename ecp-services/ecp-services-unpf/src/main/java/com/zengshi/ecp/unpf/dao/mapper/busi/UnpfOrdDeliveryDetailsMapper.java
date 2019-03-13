package com.zengshi.ecp.unpf.dao.mapper.busi;

import com.zengshi.ecp.unpf.dao.model.UnpfOrdDeliveryDetails;
import com.zengshi.ecp.unpf.dao.model.UnpfOrdDeliveryDetailsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface UnpfOrdDeliveryDetailsMapper {
    Long countByExample(UnpfOrdDeliveryDetailsCriteria example) throws DataAccessException;

    int deleteByExample(UnpfOrdDeliveryDetailsCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(UnpfOrdDeliveryDetails record) throws DataAccessException;

    int insertSelective(UnpfOrdDeliveryDetails record) throws DataAccessException;

    List<UnpfOrdDeliveryDetails> selectByExample(UnpfOrdDeliveryDetailsCriteria example) throws DataAccessException;

    UnpfOrdDeliveryDetails selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") UnpfOrdDeliveryDetails record, @Param("example") UnpfOrdDeliveryDetailsCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") UnpfOrdDeliveryDetails record, @Param("example") UnpfOrdDeliveryDetailsCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(UnpfOrdDeliveryDetails record) throws DataAccessException;

    int updateByPrimaryKey(UnpfOrdDeliveryDetails record) throws DataAccessException;
}