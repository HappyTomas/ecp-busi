package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsGds2Media;
import com.zengshi.ecp.goods.dao.model.GdsGds2MediaCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsGds2MediaMapper {
    Long countByExample(GdsGds2MediaCriteria example) throws DataAccessException;

    int deleteByExample(GdsGds2MediaCriteria example) throws DataAccessException;

    int insert(GdsGds2Media record) throws DataAccessException;

    int insertSelective(GdsGds2Media record) throws DataAccessException;

    List<GdsGds2Media> selectByExample(GdsGds2MediaCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsGds2Media record, @Param("example") GdsGds2MediaCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsGds2Media record, @Param("example") GdsGds2MediaCriteria example) throws DataAccessException;
}
