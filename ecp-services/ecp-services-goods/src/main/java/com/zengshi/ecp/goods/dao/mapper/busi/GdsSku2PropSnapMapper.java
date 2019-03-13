package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsSku2PropSnap;
import com.zengshi.ecp.goods.dao.model.GdsSku2PropSnapCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsSku2PropSnapMapper {
    Long countByExample(GdsSku2PropSnapCriteria example) throws DataAccessException;

    int deleteByExample(GdsSku2PropSnapCriteria example) throws DataAccessException;

    int insert(GdsSku2PropSnap record) throws DataAccessException;

    int insertSelective(GdsSku2PropSnap record) throws DataAccessException;

    List<GdsSku2PropSnap> selectByExample(GdsSku2PropSnapCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsSku2PropSnap record, @Param("example") GdsSku2PropSnapCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsSku2PropSnap record, @Param("example") GdsSku2PropSnapCriteria example) throws DataAccessException;
}
