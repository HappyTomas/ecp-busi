package com.zengshi.ecp.goods.dao.mapper.busi.manual;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.goods.dao.model.GdsCollectShopIdx;
import com.zengshi.ecp.goods.dao.model.GdsCollectShopIdxCriteria;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectRespDTO;

public interface GdsCollectShopIdxExtraMapper {

    List<GdsCollectShopIdx> selectByExampleDistinctSkuId(GdsCollectShopIdxCriteria example) throws DataAccessException;
    Long countByExampleDistinctSkuId(GdsCollectShopIdxCriteria example) throws DataAccessException;
    long countByExampleOrderByCollAmount(GdsCollectShopIdxCriteria example) throws DataAccessException;
    
    List<GdsCollectRespDTO> selectByExampleOrderByCollAmount(GdsCollectShopIdxCriteria example) throws DataAccessException;
}
