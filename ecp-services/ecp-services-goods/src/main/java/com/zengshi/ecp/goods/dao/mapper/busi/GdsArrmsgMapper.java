package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsArrmsg;
import com.zengshi.ecp.goods.dao.model.GdsArrmsgCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsArrmsgMapper {
    Long countByExample(GdsArrmsgCriteria example) throws DataAccessException;

    int deleteByExample(GdsArrmsgCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsArrmsg record) throws DataAccessException;

    int insertSelective(GdsArrmsg record) throws DataAccessException;

    List<GdsArrmsg> selectByExample(GdsArrmsgCriteria example) throws DataAccessException;

    GdsArrmsg selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsArrmsg record, @Param("example") GdsArrmsgCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsArrmsg record, @Param("example") GdsArrmsgCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsArrmsg record) throws DataAccessException;

    int updateByPrimaryKey(GdsArrmsg record) throws DataAccessException;
}
