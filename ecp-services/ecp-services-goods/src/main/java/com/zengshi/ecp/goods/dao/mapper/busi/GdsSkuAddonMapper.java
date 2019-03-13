package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsSkuAddon;
import com.zengshi.ecp.goods.dao.model.GdsSkuAddonCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsSkuAddonMapper {
    Long countByExample(GdsSkuAddonCriteria example) throws DataAccessException;

    int deleteByExample(GdsSkuAddonCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsSkuAddon record) throws DataAccessException;

    int insertSelective(GdsSkuAddon record) throws DataAccessException;

    List<GdsSkuAddon> selectByExample(GdsSkuAddonCriteria example) throws DataAccessException;

    GdsSkuAddon selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsSkuAddon record, @Param("example") GdsSkuAddonCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsSkuAddon record, @Param("example") GdsSkuAddonCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsSkuAddon record) throws DataAccessException;

    int updateByPrimaryKey(GdsSkuAddon record) throws DataAccessException;
}
