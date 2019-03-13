package com.zengshi.ecp.goods.dao.mapper.common;

import com.zengshi.ecp.goods.dao.model.GdsInterfaceCatg;
import com.zengshi.ecp.goods.dao.model.GdsInterfaceCatgCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsInterfaceCatgMapper {
    Long countByExample(GdsInterfaceCatgCriteria example) throws DataAccessException;

    int deleteByExample(GdsInterfaceCatgCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsInterfaceCatg record) throws DataAccessException;

    int insertSelective(GdsInterfaceCatg record) throws DataAccessException;

    List<GdsInterfaceCatg> selectByExample(GdsInterfaceCatgCriteria example) throws DataAccessException;

    GdsInterfaceCatg selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsInterfaceCatg record, @Param("example") GdsInterfaceCatgCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsInterfaceCatg record, @Param("example") GdsInterfaceCatgCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsInterfaceCatg record) throws DataAccessException;

    int updateByPrimaryKey(GdsInterfaceCatg record) throws DataAccessException;
}
