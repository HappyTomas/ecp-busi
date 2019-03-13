package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsGds2Catg;
import com.zengshi.ecp.goods.dao.model.GdsGds2CatgCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsGds2CatgMapper {
    Long countByExample(GdsGds2CatgCriteria example) throws DataAccessException;

    int deleteByExample(GdsGds2CatgCriteria example) throws DataAccessException;

    int insert(GdsGds2Catg record) throws DataAccessException;

    int insertSelective(GdsGds2Catg record) throws DataAccessException;

    List<GdsGds2Catg> selectByExample(GdsGds2CatgCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsGds2Catg record, @Param("example") GdsGds2CatgCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsGds2Catg record, @Param("example") GdsGds2CatgCriteria example) throws DataAccessException;
}
