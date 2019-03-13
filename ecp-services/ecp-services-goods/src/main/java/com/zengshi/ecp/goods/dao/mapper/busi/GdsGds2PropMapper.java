package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsGds2Prop;
import com.zengshi.ecp.goods.dao.model.GdsGds2PropCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsGds2PropMapper {
    Long countByExample(GdsGds2PropCriteria example) throws DataAccessException;

    int deleteByExample(GdsGds2PropCriteria example) throws DataAccessException;

    int insert(GdsGds2Prop record) throws DataAccessException;

    int insertSelective(GdsGds2Prop record) throws DataAccessException;

    List<GdsGds2Prop> selectByExample(GdsGds2PropCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsGds2Prop record, @Param("example") GdsGds2PropCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsGds2Prop record, @Param("example") GdsGds2PropCriteria example) throws DataAccessException;
}
