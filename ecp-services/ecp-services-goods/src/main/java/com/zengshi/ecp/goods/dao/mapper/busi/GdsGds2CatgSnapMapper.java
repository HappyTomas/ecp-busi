package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsGds2CatgSnap;
import com.zengshi.ecp.goods.dao.model.GdsGds2CatgSnapCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsGds2CatgSnapMapper {
    Long countByExample(GdsGds2CatgSnapCriteria example) throws DataAccessException;

    int deleteByExample(GdsGds2CatgSnapCriteria example) throws DataAccessException;

    int insert(GdsGds2CatgSnap record) throws DataAccessException;

    int insertSelective(GdsGds2CatgSnap record) throws DataAccessException;

    List<GdsGds2CatgSnap> selectByExample(GdsGds2CatgSnapCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsGds2CatgSnap record, @Param("example") GdsGds2CatgSnapCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsGds2CatgSnap record, @Param("example") GdsGds2CatgSnapCriteria example) throws DataAccessException;
}
