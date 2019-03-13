package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsGds2PropPropIdx;
import com.zengshi.ecp.goods.dao.model.GdsGds2PropPropIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsGds2PropPropIdxMapper {
    Long countByExample(GdsGds2PropPropIdxCriteria example) throws DataAccessException;

    int deleteByExample(GdsGds2PropPropIdxCriteria example) throws DataAccessException;

    int insert(GdsGds2PropPropIdx record) throws DataAccessException;

    int insertSelective(GdsGds2PropPropIdx record) throws DataAccessException;

    List<GdsGds2PropPropIdx> selectByExample(GdsGds2PropPropIdxCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsGds2PropPropIdx record, @Param("example") GdsGds2PropPropIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsGds2PropPropIdx record, @Param("example") GdsGds2PropPropIdxCriteria example) throws DataAccessException;
}
