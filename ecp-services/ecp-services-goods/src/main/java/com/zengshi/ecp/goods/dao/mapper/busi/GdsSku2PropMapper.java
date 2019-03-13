package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsSku2Prop;
import com.zengshi.ecp.goods.dao.model.GdsSku2PropCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsSku2PropMapper {
    Long countByExample(GdsSku2PropCriteria example) throws DataAccessException;

    int deleteByExample(GdsSku2PropCriteria example) throws DataAccessException;

    int insert(GdsSku2Prop record) throws DataAccessException;

    int insertSelective(GdsSku2Prop record) throws DataAccessException;

    List<GdsSku2Prop> selectByExample(GdsSku2PropCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsSku2Prop record, @Param("example") GdsSku2PropCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsSku2Prop record, @Param("example") GdsSku2PropCriteria example) throws DataAccessException;
}
