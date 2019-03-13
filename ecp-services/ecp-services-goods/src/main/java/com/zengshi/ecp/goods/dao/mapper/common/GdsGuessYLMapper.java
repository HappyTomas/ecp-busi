package com.zengshi.ecp.goods.dao.mapper.common;

import com.zengshi.ecp.goods.dao.model.GdsGuessYL;
import com.zengshi.ecp.goods.dao.model.GdsGuessYLCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsGuessYLMapper {
    Long countByExample(GdsGuessYLCriteria example) throws DataAccessException;

    int deleteByExample(GdsGuessYLCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsGuessYL record) throws DataAccessException;

    int insertSelective(GdsGuessYL record) throws DataAccessException;

    List<GdsGuessYL> selectByExample(GdsGuessYLCriteria example) throws DataAccessException;

    GdsGuessYL selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsGuessYL record, @Param("example") GdsGuessYLCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsGuessYL record, @Param("example") GdsGuessYLCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsGuessYL record) throws DataAccessException;

    int updateByPrimaryKey(GdsGuessYL record) throws DataAccessException;
}
