package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsInfoSnap;
import com.zengshi.ecp.goods.dao.model.GdsInfoSnapCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsInfoSnapMapper {
    Long countByExample(GdsInfoSnapCriteria example) throws DataAccessException;

    int deleteByExample(GdsInfoSnapCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long snapId) throws DataAccessException;

    int insert(GdsInfoSnap record) throws DataAccessException;

    int insertSelective(GdsInfoSnap record) throws DataAccessException;

    List<GdsInfoSnap> selectByExample(GdsInfoSnapCriteria example) throws DataAccessException;

    GdsInfoSnap selectByPrimaryKey(Long snapId) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsInfoSnap record, @Param("example") GdsInfoSnapCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsInfoSnap record, @Param("example") GdsInfoSnapCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsInfoSnap record) throws DataAccessException;

    int updateByPrimaryKey(GdsInfoSnap record) throws DataAccessException;
}
