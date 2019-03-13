package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsSkuInfo;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsSkuInfoMapper {
    Long countByExample(GdsSkuInfoCriteria example) throws DataAccessException;

    int deleteByExample(GdsSkuInfoCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsSkuInfo record) throws DataAccessException;

    int insertSelective(GdsSkuInfo record) throws DataAccessException;

    List<GdsSkuInfo> selectByExample(GdsSkuInfoCriteria example) throws DataAccessException;

    GdsSkuInfo selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsSkuInfo record, @Param("example") GdsSkuInfoCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsSkuInfo record, @Param("example") GdsSkuInfoCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsSkuInfo record) throws DataAccessException;

    int updateByPrimaryKey(GdsSkuInfo record) throws DataAccessException;
}
