package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsVerifyShopIdx;
import com.zengshi.ecp.goods.dao.model.GdsVerifyShopIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsVerifyShopIdxMapper {
    Long countByExample(GdsVerifyShopIdxCriteria example) throws DataAccessException;

    int deleteByExample(GdsVerifyShopIdxCriteria example) throws DataAccessException;

    int insert(GdsVerifyShopIdx record) throws DataAccessException;

    int insertSelective(GdsVerifyShopIdx record) throws DataAccessException;

    List<GdsVerifyShopIdx> selectByExample(GdsVerifyShopIdxCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsVerifyShopIdx record, @Param("example") GdsVerifyShopIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsVerifyShopIdx record, @Param("example") GdsVerifyShopIdxCriteria example) throws DataAccessException;
}
