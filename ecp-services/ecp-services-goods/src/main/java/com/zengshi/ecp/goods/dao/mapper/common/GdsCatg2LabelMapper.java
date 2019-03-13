package com.zengshi.ecp.goods.dao.mapper.common;

import com.zengshi.ecp.goods.dao.model.GdsCatg2Label;
import com.zengshi.ecp.goods.dao.model.GdsCatg2LabelCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsCatg2LabelMapper {
    Long countByExample(GdsCatg2LabelCriteria example) throws DataAccessException;

    int deleteByExample(GdsCatg2LabelCriteria example) throws DataAccessException;

    int insert(GdsCatg2Label record) throws DataAccessException;

    int insertSelective(GdsCatg2Label record) throws DataAccessException;

    List<GdsCatg2Label> selectByExample(GdsCatg2LabelCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsCatg2Label record, @Param("example") GdsCatg2LabelCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsCatg2Label record, @Param("example") GdsCatg2LabelCriteria example) throws DataAccessException;
}
