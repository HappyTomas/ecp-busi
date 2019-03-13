package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.PayShopCfg;
import com.zengshi.ecp.order.dao.model.PayShopCfgCriteria;
import com.zengshi.ecp.order.dao.model.PayShopCfgKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PayShopCfgMapper {
    Long countByExample(PayShopCfgCriteria example) throws DataAccessException;

    int deleteByExample(PayShopCfgCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(PayShopCfgKey key) throws DataAccessException;

    int insert(PayShopCfg record) throws DataAccessException;

    int insertSelective(PayShopCfg record) throws DataAccessException;

    List<PayShopCfg> selectByExample(PayShopCfgCriteria example) throws DataAccessException;

    PayShopCfg selectByPrimaryKey(PayShopCfgKey key) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PayShopCfg record, @Param("example") PayShopCfgCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PayShopCfg record, @Param("example") PayShopCfgCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PayShopCfg record) throws DataAccessException;

    int updateByPrimaryKey(PayShopCfg record) throws DataAccessException;
}