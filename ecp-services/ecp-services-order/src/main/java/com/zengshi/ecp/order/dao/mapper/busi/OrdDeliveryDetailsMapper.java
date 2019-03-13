package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdDeliveryDetails;
import com.zengshi.ecp.order.dao.model.OrdDeliveryDetailsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdDeliveryDetailsMapper {
    Long countByExample(OrdDeliveryDetailsCriteria example) throws DataAccessException;

    int deleteByExample(OrdDeliveryDetailsCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdDeliveryDetails record) throws DataAccessException;

    int insertSelective(OrdDeliveryDetails record) throws DataAccessException;

    List<OrdDeliveryDetails> selectByExample(OrdDeliveryDetailsCriteria example) throws DataAccessException;

    OrdDeliveryDetails selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdDeliveryDetails record, @Param("example") OrdDeliveryDetailsCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdDeliveryDetails record, @Param("example") OrdDeliveryDetailsCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdDeliveryDetails record) throws DataAccessException;

    int updateByPrimaryKey(OrdDeliveryDetails record) throws DataAccessException;
}