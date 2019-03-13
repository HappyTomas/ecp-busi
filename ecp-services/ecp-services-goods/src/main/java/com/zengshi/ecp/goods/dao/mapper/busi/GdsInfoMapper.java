package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsInfo;
import com.zengshi.ecp.goods.dao.model.GdsInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsInfoMapper {
    Long countByExample(GdsInfoCriteria example) throws DataAccessException;

    int deleteByExample(GdsInfoCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsInfo record) throws DataAccessException;

    int insertSelective(GdsInfo record) throws DataAccessException;

    List<GdsInfo> selectByExample(GdsInfoCriteria example) throws DataAccessException;

    GdsInfo selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsInfo record, @Param("example") GdsInfoCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsInfo record, @Param("example") GdsInfoCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsInfo record) throws DataAccessException;

    int updateByPrimaryKey(GdsInfo record) throws DataAccessException;
}
