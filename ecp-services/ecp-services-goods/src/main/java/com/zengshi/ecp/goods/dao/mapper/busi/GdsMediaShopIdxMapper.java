package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsMediaShopIdx;
import com.zengshi.ecp.goods.dao.model.GdsMediaShopIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsMediaShopIdxMapper {
    Long countByExample(GdsMediaShopIdxCriteria example) throws DataAccessException;

    int deleteByExample(GdsMediaShopIdxCriteria example) throws DataAccessException;

    int insert(GdsMediaShopIdx record) throws DataAccessException;

    int insertSelective(GdsMediaShopIdx record) throws DataAccessException;

    List<GdsMediaShopIdx> selectByExample(GdsMediaShopIdxCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsMediaShopIdx record, @Param("example") GdsMediaShopIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsMediaShopIdx record, @Param("example") GdsMediaShopIdxCriteria example) throws DataAccessException;
}
