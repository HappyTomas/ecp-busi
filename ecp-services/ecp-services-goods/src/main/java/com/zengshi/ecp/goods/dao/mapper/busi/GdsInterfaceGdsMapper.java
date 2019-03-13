package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsInterfaceGds;
import com.zengshi.ecp.goods.dao.model.GdsInterfaceGdsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsInterfaceGdsMapper {
    Long countByExample(GdsInterfaceGdsCriteria example) throws DataAccessException;

    int deleteByExample(GdsInterfaceGdsCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsInterfaceGds record) throws DataAccessException;

    int insertSelective(GdsInterfaceGds record) throws DataAccessException;

    List<GdsInterfaceGds> selectByExample(GdsInterfaceGdsCriteria example) throws DataAccessException;

    GdsInterfaceGds selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsInterfaceGds record, @Param("example") GdsInterfaceGdsCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsInterfaceGds record, @Param("example") GdsInterfaceGdsCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsInterfaceGds record) throws DataAccessException;

    int updateByPrimaryKey(GdsInterfaceGds record) throws DataAccessException;
}
