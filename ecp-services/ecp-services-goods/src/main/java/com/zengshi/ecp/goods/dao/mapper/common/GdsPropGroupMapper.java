package com.zengshi.ecp.goods.dao.mapper.common;

import com.zengshi.ecp.goods.dao.model.GdsPropGroup;
import com.zengshi.ecp.goods.dao.model.GdsPropGroupCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsPropGroupMapper {
    Long countByExample(GdsPropGroupCriteria example) throws DataAccessException;

    int deleteByExample(GdsPropGroupCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsPropGroup record) throws DataAccessException;

    int insertSelective(GdsPropGroup record) throws DataAccessException;

    List<GdsPropGroup> selectByExample(GdsPropGroupCriteria example) throws DataAccessException;

    GdsPropGroup selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsPropGroup record, @Param("example") GdsPropGroupCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsPropGroup record, @Param("example") GdsPropGroupCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsPropGroup record) throws DataAccessException;

    int updateByPrimaryKey(GdsPropGroup record) throws DataAccessException;
}
