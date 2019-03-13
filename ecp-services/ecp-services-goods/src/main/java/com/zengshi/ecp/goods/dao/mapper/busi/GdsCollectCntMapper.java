package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsCollectCnt;
import com.zengshi.ecp.goods.dao.model.GdsCollectCntCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsCollectCntMapper {
    Long countByExample(GdsCollectCntCriteria example) throws DataAccessException;

    int deleteByExample(GdsCollectCntCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long skuId) throws DataAccessException;

    int insert(GdsCollectCnt record) throws DataAccessException;

    int insertSelective(GdsCollectCnt record) throws DataAccessException;

    List<GdsCollectCnt> selectByExample(GdsCollectCntCriteria example) throws DataAccessException;

    GdsCollectCnt selectByPrimaryKey(Long skuId) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsCollectCnt record, @Param("example") GdsCollectCntCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsCollectCnt record, @Param("example") GdsCollectCntCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsCollectCnt record) throws DataAccessException;

    int updateByPrimaryKey(GdsCollectCnt record) throws DataAccessException;
}
