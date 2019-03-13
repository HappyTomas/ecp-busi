package com.zengshi.ecp.goods.dao.mapper.common;

import com.zengshi.ecp.goods.dao.model.GdsCatg2Prop;
import com.zengshi.ecp.goods.dao.model.GdsCatg2PropCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsCatg2PropMapper {
    Long countByExample(GdsCatg2PropCriteria example) throws DataAccessException;

    int deleteByExample(GdsCatg2PropCriteria example) throws DataAccessException;

    int insert(GdsCatg2Prop record) throws DataAccessException;

    int insertSelective(GdsCatg2Prop record) throws DataAccessException;

    List<GdsCatg2Prop> selectByExample(GdsCatg2PropCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsCatg2Prop record, @Param("example") GdsCatg2PropCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsCatg2Prop record, @Param("example") GdsCatg2PropCriteria example) throws DataAccessException;
}
