package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsSku2MediaSnap;
import com.zengshi.ecp.goods.dao.model.GdsSku2MediaSnapCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsSku2MediaSnapMapper {
    Long countByExample(GdsSku2MediaSnapCriteria example) throws DataAccessException;

    int deleteByExample(GdsSku2MediaSnapCriteria example) throws DataAccessException;

    int insert(GdsSku2MediaSnap record) throws DataAccessException;

    int insertSelective(GdsSku2MediaSnap record) throws DataAccessException;

    List<GdsSku2MediaSnap> selectByExample(GdsSku2MediaSnapCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsSku2MediaSnap record, @Param("example") GdsSku2MediaSnapCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsSku2MediaSnap record, @Param("example") GdsSku2MediaSnapCriteria example) throws DataAccessException;
}
