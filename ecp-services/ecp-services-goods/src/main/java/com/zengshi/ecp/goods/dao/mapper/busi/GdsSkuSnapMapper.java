package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsSkuSnap;
import com.zengshi.ecp.goods.dao.model.GdsSkuSnapCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsSkuSnapMapper {
    Long countByExample(GdsSkuSnapCriteria example) throws DataAccessException;

    int deleteByExample(GdsSkuSnapCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsSkuSnap record) throws DataAccessException;

    int insertSelective(GdsSkuSnap record) throws DataAccessException;

    List<GdsSkuSnap> selectByExample(GdsSkuSnapCriteria example) throws DataAccessException;

    GdsSkuSnap selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsSkuSnap record, @Param("example") GdsSkuSnapCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsSkuSnap record, @Param("example") GdsSkuSnapCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsSkuSnap record) throws DataAccessException;

    int updateByPrimaryKey(GdsSkuSnap record) throws DataAccessException;
}
