package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsSkuInfoSnap;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfoSnapCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsSkuInfoSnapMapper {
    Long countByExample(GdsSkuInfoSnapCriteria example) throws DataAccessException;

    int deleteByExample(GdsSkuInfoSnapCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsSkuInfoSnap record) throws DataAccessException;

    int insertSelective(GdsSkuInfoSnap record) throws DataAccessException;

    List<GdsSkuInfoSnap> selectByExample(GdsSkuInfoSnapCriteria example) throws DataAccessException;

    GdsSkuInfoSnap selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsSkuInfoSnap record, @Param("example") GdsSkuInfoSnapCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsSkuInfoSnap record, @Param("example") GdsSkuInfoSnapCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsSkuInfoSnap record) throws DataAccessException;

    int updateByPrimaryKey(GdsSkuInfoSnap record) throws DataAccessException;
}
