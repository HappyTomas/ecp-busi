package com.zengshi.ecp.goods.dao.mapper.common;

import com.zengshi.ecp.goods.dao.model.GdsProp2Group;
import com.zengshi.ecp.goods.dao.model.GdsProp2GroupCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsProp2GroupMapper {
    Long countByExample(GdsProp2GroupCriteria example) throws DataAccessException;

    int deleteByExample(GdsProp2GroupCriteria example) throws DataAccessException;

    int insert(GdsProp2Group record) throws DataAccessException;

    int insertSelective(GdsProp2Group record) throws DataAccessException;

    List<GdsProp2Group> selectByExample(GdsProp2GroupCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsProp2Group record, @Param("example") GdsProp2GroupCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsProp2Group record, @Param("example") GdsProp2GroupCriteria example) throws DataAccessException;
}
