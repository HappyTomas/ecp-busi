package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsGds2PropSnap;
import com.zengshi.ecp.goods.dao.model.GdsGds2PropSnapCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsGds2PropSnapMapper {
    Long countByExample(GdsGds2PropSnapCriteria example) throws DataAccessException;

    int deleteByExample(GdsGds2PropSnapCriteria example) throws DataAccessException;

    int insert(GdsGds2PropSnap record) throws DataAccessException;

    int insertSelective(GdsGds2PropSnap record) throws DataAccessException;

    List<GdsGds2PropSnap> selectByExample(GdsGds2PropSnapCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsGds2PropSnap record, @Param("example") GdsGds2PropSnapCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsGds2PropSnap record, @Param("example") GdsGds2PropSnapCriteria example) throws DataAccessException;
}
