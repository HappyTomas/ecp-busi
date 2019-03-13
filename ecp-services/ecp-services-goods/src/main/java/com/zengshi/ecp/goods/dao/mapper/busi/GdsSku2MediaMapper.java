package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsSku2Media;
import com.zengshi.ecp.goods.dao.model.GdsSku2MediaCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsSku2MediaMapper {
    Long countByExample(GdsSku2MediaCriteria example) throws DataAccessException;

    int deleteByExample(GdsSku2MediaCriteria example) throws DataAccessException;

    int insert(GdsSku2Media record) throws DataAccessException;

    int insertSelective(GdsSku2Media record) throws DataAccessException;

    List<GdsSku2Media> selectByExample(GdsSku2MediaCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsSku2Media record, @Param("example") GdsSku2MediaCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsSku2Media record, @Param("example") GdsSku2MediaCriteria example) throws DataAccessException;
}
