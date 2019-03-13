package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsAddon;
import com.zengshi.ecp.goods.dao.model.GdsAddonCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsAddonMapper {
    Long countByExample(GdsAddonCriteria example) throws DataAccessException;

    int deleteByExample(GdsAddonCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsAddon record) throws DataAccessException;

    int insertSelective(GdsAddon record) throws DataAccessException;

    List<GdsAddon> selectByExample(GdsAddonCriteria example) throws DataAccessException;

    GdsAddon selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsAddon record, @Param("example") GdsAddonCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsAddon record, @Param("example") GdsAddonCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsAddon record) throws DataAccessException;

    int updateByPrimaryKey(GdsAddon record) throws DataAccessException;
}
