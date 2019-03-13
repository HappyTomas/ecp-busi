package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsGds2CatgCatgIdx;
import com.zengshi.ecp.goods.dao.model.GdsGds2CatgCatgIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsGds2CatgCatgIdxMapper {
    Long countByExample(GdsGds2CatgCatgIdxCriteria example) throws DataAccessException;

    int deleteByExample(GdsGds2CatgCatgIdxCriteria example) throws DataAccessException;

    int insert(GdsGds2CatgCatgIdx record) throws DataAccessException;

    int insertSelective(GdsGds2CatgCatgIdx record) throws DataAccessException;

    List<GdsGds2CatgCatgIdx> selectByExample(GdsGds2CatgCatgIdxCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsGds2CatgCatgIdx record, @Param("example") GdsGds2CatgCatgIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsGds2CatgCatgIdx record, @Param("example") GdsGds2CatgCatgIdxCriteria example) throws DataAccessException;
}
