package com.zengshi.ecp.goods.dao.mapper.common;

import com.zengshi.ecp.goods.dao.model.GdsCatg2PGroup;
import com.zengshi.ecp.goods.dao.model.GdsCatg2PGroupCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsCatg2PGroupMapper {
    Long countByExample(GdsCatg2PGroupCriteria example) throws DataAccessException;

    int deleteByExample(GdsCatg2PGroupCriteria example) throws DataAccessException;

    int insert(GdsCatg2PGroup record) throws DataAccessException;

    int insertSelective(GdsCatg2PGroup record) throws DataAccessException;

    List<GdsCatg2PGroup> selectByExample(GdsCatg2PGroupCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsCatg2PGroup record, @Param("example") GdsCatg2PGroupCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsCatg2PGroup record, @Param("example") GdsCatg2PGroupCriteria example) throws DataAccessException;
}
