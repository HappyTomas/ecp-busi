package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsInterfaceGdsGidx;
import com.zengshi.ecp.goods.dao.model.GdsInterfaceGdsGidxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsInterfaceGdsGidxMapper {
    Long countByExample(GdsInterfaceGdsGidxCriteria example) throws DataAccessException;

    int deleteByExample(GdsInterfaceGdsGidxCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsInterfaceGdsGidx record) throws DataAccessException;

    int insertSelective(GdsInterfaceGdsGidx record) throws DataAccessException;

    List<GdsInterfaceGdsGidx> selectByExample(GdsInterfaceGdsGidxCriteria example) throws DataAccessException;

    GdsInterfaceGdsGidx selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsInterfaceGdsGidx record, @Param("example") GdsInterfaceGdsGidxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsInterfaceGdsGidx record, @Param("example") GdsInterfaceGdsGidxCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsInterfaceGdsGidx record) throws DataAccessException;

    int updateByPrimaryKey(GdsInterfaceGdsGidx record) throws DataAccessException;
}
