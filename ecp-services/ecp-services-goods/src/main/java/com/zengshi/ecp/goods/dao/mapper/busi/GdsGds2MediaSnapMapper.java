package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsGds2MediaSnap;
import com.zengshi.ecp.goods.dao.model.GdsGds2MediaSnapCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsGds2MediaSnapMapper {
    Long countByExample(GdsGds2MediaSnapCriteria example) throws DataAccessException;

    int deleteByExample(GdsGds2MediaSnapCriteria example) throws DataAccessException;

    int insert(GdsGds2MediaSnap record) throws DataAccessException;

    int insertSelective(GdsGds2MediaSnap record) throws DataAccessException;

    List<GdsGds2MediaSnap> selectByExample(GdsGds2MediaSnapCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsGds2MediaSnap record, @Param("example") GdsGds2MediaSnapCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsGds2MediaSnap record, @Param("example") GdsGds2MediaSnapCriteria example) throws DataAccessException;
}
