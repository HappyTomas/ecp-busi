package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsPriceLadder;
import com.zengshi.ecp.goods.dao.model.GdsPriceLadderCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsPriceLadderMapper {
    Long countByExample(GdsPriceLadderCriteria example) throws DataAccessException;

    int deleteByExample(GdsPriceLadderCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsPriceLadder record) throws DataAccessException;

    int insertSelective(GdsPriceLadder record) throws DataAccessException;

    List<GdsPriceLadder> selectByExample(GdsPriceLadderCriteria example) throws DataAccessException;

    GdsPriceLadder selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsPriceLadder record, @Param("example") GdsPriceLadderCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsPriceLadder record, @Param("example") GdsPriceLadderCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsPriceLadder record) throws DataAccessException;

    int updateByPrimaryKey(GdsPriceLadder record) throws DataAccessException;
}
