package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsEval;
import com.zengshi.ecp.goods.dao.model.GdsEvalCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsEvalMapper {
    Long countByExample(GdsEvalCriteria example) throws DataAccessException;

    int deleteByExample(GdsEvalCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsEval record) throws DataAccessException;

    int insertSelective(GdsEval record) throws DataAccessException;

    List<GdsEval> selectByExample(GdsEvalCriteria example) throws DataAccessException;

    GdsEval selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsEval record, @Param("example") GdsEvalCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsEval record, @Param("example") GdsEvalCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsEval record) throws DataAccessException;

    int updateByPrimaryKey(GdsEval record) throws DataAccessException;
}
