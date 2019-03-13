package com.zengshi.ecp.goods.dao.mapper.common;

import com.zengshi.ecp.goods.dao.model.GdsLabel;
import com.zengshi.ecp.goods.dao.model.GdsLabelCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsLabelMapper {
    Long countByExample(GdsLabelCriteria example) throws DataAccessException;

    int deleteByExample(GdsLabelCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsLabel record) throws DataAccessException;

    int insertSelective(GdsLabel record) throws DataAccessException;

    List<GdsLabel> selectByExample(GdsLabelCriteria example) throws DataAccessException;

    GdsLabel selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsLabel record, @Param("example") GdsLabelCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsLabel record, @Param("example") GdsLabelCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsLabel record) throws DataAccessException;

    int updateByPrimaryKey(GdsLabel record) throws DataAccessException;
}
