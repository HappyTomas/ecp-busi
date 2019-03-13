package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsCollect;
import com.zengshi.ecp.goods.dao.model.GdsCollectCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsCollectMapper {
    Long countByExample(GdsCollectCriteria example) throws DataAccessException;

    int deleteByExample(GdsCollectCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsCollect record) throws DataAccessException;

    int insertSelective(GdsCollect record) throws DataAccessException;

    List<GdsCollect> selectByExample(GdsCollectCriteria example) throws DataAccessException;

    GdsCollect selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsCollect record, @Param("example") GdsCollectCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsCollect record, @Param("example") GdsCollectCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsCollect record) throws DataAccessException;

    int updateByPrimaryKey(GdsCollect record) throws DataAccessException;
}
