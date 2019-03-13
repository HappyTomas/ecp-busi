package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsAddonSnap;
import com.zengshi.ecp.goods.dao.model.GdsAddonSnapCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsAddonSnapMapper {
    Long countByExample(GdsAddonSnapCriteria example) throws DataAccessException;

    int deleteByExample(GdsAddonSnapCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsAddonSnap record) throws DataAccessException;

    int insertSelective(GdsAddonSnap record) throws DataAccessException;

    List<GdsAddonSnap> selectByExample(GdsAddonSnapCriteria example) throws DataAccessException;

    GdsAddonSnap selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsAddonSnap record, @Param("example") GdsAddonSnapCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsAddonSnap record, @Param("example") GdsAddonSnapCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsAddonSnap record) throws DataAccessException;

    int updateByPrimaryKey(GdsAddonSnap record) throws DataAccessException;
}
