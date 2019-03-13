package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsSkuAddonSnap;
import com.zengshi.ecp.goods.dao.model.GdsSkuAddonSnapCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsSkuAddonSnapMapper {
    Long countByExample(GdsSkuAddonSnapCriteria example) throws DataAccessException;

    int deleteByExample(GdsSkuAddonSnapCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsSkuAddonSnap record) throws DataAccessException;

    int insertSelective(GdsSkuAddonSnap record) throws DataAccessException;

    List<GdsSkuAddonSnap> selectByExample(GdsSkuAddonSnapCriteria example) throws DataAccessException;

    GdsSkuAddonSnap selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsSkuAddonSnap record, @Param("example") GdsSkuAddonSnapCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsSkuAddonSnap record, @Param("example") GdsSkuAddonSnapCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsSkuAddonSnap record) throws DataAccessException;

    int updateByPrimaryKey(GdsSkuAddonSnap record) throws DataAccessException;
}
