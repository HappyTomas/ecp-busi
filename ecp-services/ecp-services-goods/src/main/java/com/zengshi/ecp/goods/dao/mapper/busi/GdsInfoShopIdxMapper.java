package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsInfoShopIdx;
import com.zengshi.ecp.goods.dao.model.GdsInfoShopIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsInfoShopIdxMapper {
    Long countByExample(GdsInfoShopIdxCriteria example) throws DataAccessException;

    int deleteByExample(GdsInfoShopIdxCriteria example) throws DataAccessException;

    int insert(GdsInfoShopIdx record) throws DataAccessException;

    int insertSelective(GdsInfoShopIdx record) throws DataAccessException;

    List<GdsInfoShopIdx> selectByExample(GdsInfoShopIdxCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsInfoShopIdx record, @Param("example") GdsInfoShopIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsInfoShopIdx record, @Param("example") GdsInfoShopIdxCriteria example) throws DataAccessException;
}
