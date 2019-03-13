package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsScoreExt;
import com.zengshi.ecp.goods.dao.model.GdsScoreExtCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsScoreExtMapper {
    Long countByExample(GdsScoreExtCriteria example) throws DataAccessException;

    int deleteByExample(GdsScoreExtCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsScoreExt record) throws DataAccessException;

    int insertSelective(GdsScoreExt record) throws DataAccessException;

    List<GdsScoreExt> selectByExample(GdsScoreExtCriteria example) throws DataAccessException;

    GdsScoreExt selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsScoreExt record, @Param("example") GdsScoreExtCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsScoreExt record, @Param("example") GdsScoreExtCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsScoreExt record) throws DataAccessException;

    int updateByPrimaryKey(GdsScoreExt record) throws DataAccessException;
}
