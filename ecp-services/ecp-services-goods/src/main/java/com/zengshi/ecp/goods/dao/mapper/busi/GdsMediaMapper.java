package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsMedia;
import com.zengshi.ecp.goods.dao.model.GdsMediaCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsMediaMapper {
    Long countByExample(GdsMediaCriteria example) throws DataAccessException;

    int deleteByExample(GdsMediaCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsMedia record) throws DataAccessException;

    int insertSelective(GdsMedia record) throws DataAccessException;

    List<GdsMedia> selectByExample(GdsMediaCriteria example) throws DataAccessException;

    GdsMedia selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsMedia record, @Param("example") GdsMediaCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsMedia record, @Param("example") GdsMediaCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsMedia record) throws DataAccessException;

    int updateByPrimaryKey(GdsMedia record) throws DataAccessException;
}
