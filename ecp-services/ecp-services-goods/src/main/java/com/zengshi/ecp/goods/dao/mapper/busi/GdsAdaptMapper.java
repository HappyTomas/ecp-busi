package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsAdapt;
import com.zengshi.ecp.goods.dao.model.GdsAdaptCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsAdaptMapper {
    Long countByExample(GdsAdaptCriteria example) throws DataAccessException;

    int deleteByExample(GdsAdaptCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsAdapt record) throws DataAccessException;

    int insertSelective(GdsAdapt record) throws DataAccessException;

    List<GdsAdapt> selectByExample(GdsAdaptCriteria example) throws DataAccessException;

    GdsAdapt selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsAdapt record, @Param("example") GdsAdaptCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsAdapt record, @Param("example") GdsAdaptCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsAdapt record) throws DataAccessException;

    int updateByPrimaryKey(GdsAdapt record) throws DataAccessException;
}
