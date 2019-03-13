package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsCollectShopIdx;
import com.zengshi.ecp.goods.dao.model.GdsCollectShopIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsCollectShopIdxMapper {
    Long countByExample(GdsCollectShopIdxCriteria example) throws DataAccessException;

    int deleteByExample(GdsCollectShopIdxCriteria example) throws DataAccessException;

    int insert(GdsCollectShopIdx record) throws DataAccessException;

    int insertSelective(GdsCollectShopIdx record) throws DataAccessException;

    List<GdsCollectShopIdx> selectByExample(GdsCollectShopIdxCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsCollectShopIdx record, @Param("example") GdsCollectShopIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsCollectShopIdx record, @Param("example") GdsCollectShopIdxCriteria example) throws DataAccessException;
}
