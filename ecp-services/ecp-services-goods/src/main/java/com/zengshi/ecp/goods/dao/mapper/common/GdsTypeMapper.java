package com.zengshi.ecp.goods.dao.mapper.common;

import com.zengshi.ecp.goods.dao.model.GdsType;
import com.zengshi.ecp.goods.dao.model.GdsTypeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsTypeMapper {
    Long countByExample(GdsTypeCriteria example) throws DataAccessException;

    int deleteByExample(GdsTypeCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsType record) throws DataAccessException;

    int insertSelective(GdsType record) throws DataAccessException;

    List<GdsType> selectByExample(GdsTypeCriteria example) throws DataAccessException;

    GdsType selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsType record, @Param("example") GdsTypeCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsType record, @Param("example") GdsTypeCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsType record) throws DataAccessException;

    int updateByPrimaryKey(GdsType record) throws DataAccessException;
}
