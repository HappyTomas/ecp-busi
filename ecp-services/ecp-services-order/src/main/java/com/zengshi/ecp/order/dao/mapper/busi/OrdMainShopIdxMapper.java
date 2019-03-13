package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdMainShopIdx;
import com.zengshi.ecp.order.dao.model.OrdMainShopIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdMainShopIdxMapper {
    Long countByExample(OrdMainShopIdxCriteria example) throws DataAccessException;

    int deleteByExample(OrdMainShopIdxCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdMainShopIdx record) throws DataAccessException;

    int insertSelective(OrdMainShopIdx record) throws DataAccessException;

    List<OrdMainShopIdx> selectByExample(OrdMainShopIdxCriteria example) throws DataAccessException;

    OrdMainShopIdx selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdMainShopIdx record, @Param("example") OrdMainShopIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdMainShopIdx record, @Param("example") OrdMainShopIdxCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdMainShopIdx record) throws DataAccessException;

    int updateByPrimaryKey(OrdMainShopIdx record) throws DataAccessException;
}