package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.ScoreDetailSel;
import com.zengshi.ecp.staff.dao.model.ScoreDetailSelCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ScoreDetailSelMapper {
    Long countByExample(ScoreDetailSelCriteria example) throws DataAccessException;

    int deleteByExample(ScoreDetailSelCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(ScoreDetailSel record) throws DataAccessException;

    int insertSelective(ScoreDetailSel record) throws DataAccessException;

    List<ScoreDetailSel> selectByExample(ScoreDetailSelCriteria example) throws DataAccessException;

    ScoreDetailSel selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") ScoreDetailSel record, @Param("example") ScoreDetailSelCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") ScoreDetailSel record, @Param("example") ScoreDetailSelCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(ScoreDetailSel record) throws DataAccessException;

    int updateByPrimaryKey(ScoreDetailSel record) throws DataAccessException;
    
    ScoreDetailSel sumByExample(ScoreDetailSelCriteria example) throws DataAccessException;
}