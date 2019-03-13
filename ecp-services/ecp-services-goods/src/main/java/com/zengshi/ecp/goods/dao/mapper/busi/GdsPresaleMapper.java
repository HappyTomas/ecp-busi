package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsPresale;
import com.zengshi.ecp.goods.dao.model.GdsPresaleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsPresaleMapper {
    Long countByExample(GdsPresaleCriteria example) throws DataAccessException;

    int deleteByExample(GdsPresaleCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsPresale record) throws DataAccessException;

    int insertSelective(GdsPresale record) throws DataAccessException;

    List<GdsPresale> selectByExample(GdsPresaleCriteria example) throws DataAccessException;

    GdsPresale selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsPresale record, @Param("example") GdsPresaleCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsPresale record, @Param("example") GdsPresaleCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsPresale record) throws DataAccessException;

    int updateByPrimaryKey(GdsPresale record) throws DataAccessException;
}
