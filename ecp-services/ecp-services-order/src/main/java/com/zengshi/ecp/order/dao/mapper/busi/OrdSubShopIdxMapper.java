package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdSubShopIdx;
import com.zengshi.ecp.order.dao.model.OrdSubShopIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdSubShopIdxMapper {
    Long countByExample(OrdSubShopIdxCriteria example) throws DataAccessException;

    int deleteByExample(OrdSubShopIdxCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdSubShopIdx record) throws DataAccessException;

    int insertSelective(OrdSubShopIdx record) throws DataAccessException;

    List<OrdSubShopIdx> selectByExample(OrdSubShopIdxCriteria example) throws DataAccessException;

    OrdSubShopIdx selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdSubShopIdx record, @Param("example") OrdSubShopIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdSubShopIdx record, @Param("example") OrdSubShopIdxCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdSubShopIdx record) throws DataAccessException;

    int updateByPrimaryKey(OrdSubShopIdx record) throws DataAccessException;
}