package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.PayShopCfgLog;
import com.zengshi.ecp.order.dao.model.PayShopCfgLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PayShopCfgLogMapper {
    Long countByExample(PayShopCfgLogCriteria example) throws DataAccessException;

    int deleteByExample(PayShopCfgLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(PayShopCfgLog record) throws DataAccessException;

    int insertSelective(PayShopCfgLog record) throws DataAccessException;

    List<PayShopCfgLog> selectByExample(PayShopCfgLogCriteria example) throws DataAccessException;

    PayShopCfgLog selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PayShopCfgLog record, @Param("example") PayShopCfgLogCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PayShopCfgLog record, @Param("example") PayShopCfgLogCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PayShopCfgLog record) throws DataAccessException;

    int updateByPrimaryKey(PayShopCfgLog record) throws DataAccessException;
}