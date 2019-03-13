package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsSkuInfoGdsIdx;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfoGdsIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsSkuInfoGdsIdxMapper {
    Long countByExample(GdsSkuInfoGdsIdxCriteria example) throws DataAccessException;

    int deleteByExample(GdsSkuInfoGdsIdxCriteria example) throws DataAccessException;

    int insert(GdsSkuInfoGdsIdx record) throws DataAccessException;

    int insertSelective(GdsSkuInfoGdsIdx record) throws DataAccessException;

    List<GdsSkuInfoGdsIdx> selectByExample(GdsSkuInfoGdsIdxCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsSkuInfoGdsIdx record, @Param("example") GdsSkuInfoGdsIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsSkuInfoGdsIdx record, @Param("example") GdsSkuInfoGdsIdxCriteria example) throws DataAccessException;
}
