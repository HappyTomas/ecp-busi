package com.zengshi.ecp.staff.dao.mapper.common;

import com.zengshi.ecp.staff.dao.model.ScoreCategoryRatio;
import com.zengshi.ecp.staff.dao.model.ScoreCategoryRatioCriteria;
import com.zengshi.ecp.staff.dao.model.ScoreCategoryRatioKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ScoreCategoryRatioMapper {
    Long countByExample(ScoreCategoryRatioCriteria example) throws DataAccessException;

    int deleteByExample(ScoreCategoryRatioCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(ScoreCategoryRatioKey key) throws DataAccessException;

    int insert(ScoreCategoryRatio record) throws DataAccessException;

    int insertSelective(ScoreCategoryRatio record) throws DataAccessException;

    List<ScoreCategoryRatio> selectByExample(ScoreCategoryRatioCriteria example) throws DataAccessException;

    ScoreCategoryRatio selectByPrimaryKey(ScoreCategoryRatioKey key) throws DataAccessException;

    int updateByExampleSelective(@Param("record") ScoreCategoryRatio record, @Param("example") ScoreCategoryRatioCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") ScoreCategoryRatio record, @Param("example") ScoreCategoryRatioCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(ScoreCategoryRatio record) throws DataAccessException;

    int updateByPrimaryKey(ScoreCategoryRatio record) throws DataAccessException;
}