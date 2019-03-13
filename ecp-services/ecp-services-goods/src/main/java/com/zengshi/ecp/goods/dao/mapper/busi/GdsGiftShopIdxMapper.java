package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsGiftShopIdx;
import com.zengshi.ecp.goods.dao.model.GdsGiftShopIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsGiftShopIdxMapper {
    Long countByExample(GdsGiftShopIdxCriteria example) throws DataAccessException;

    int deleteByExample(GdsGiftShopIdxCriteria example) throws DataAccessException;

    int insert(GdsGiftShopIdx record) throws DataAccessException;

    int insertSelective(GdsGiftShopIdx record) throws DataAccessException;

    List<GdsGiftShopIdx> selectByExample(GdsGiftShopIdxCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsGiftShopIdx record, @Param("example") GdsGiftShopIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsGiftShopIdx record, @Param("example") GdsGiftShopIdxCriteria example) throws DataAccessException;
}
