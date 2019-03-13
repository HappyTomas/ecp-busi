package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsSkuInfoShopIdx;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfoShopIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsSkuInfoShopIdxMapper {
    Long countByExample(GdsSkuInfoShopIdxCriteria example) throws DataAccessException;

    int deleteByExample(GdsSkuInfoShopIdxCriteria example) throws DataAccessException;

    int insert(GdsSkuInfoShopIdx record) throws DataAccessException;

    int insertSelective(GdsSkuInfoShopIdx record) throws DataAccessException;

    List<GdsSkuInfoShopIdx> selectByExample(GdsSkuInfoShopIdxCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsSkuInfoShopIdx record, @Param("example") GdsSkuInfoShopIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsSkuInfoShopIdx record, @Param("example") GdsSkuInfoShopIdxCriteria example) throws DataAccessException;
}
