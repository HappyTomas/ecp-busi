package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsCollectGdsIdx;
import com.zengshi.ecp.goods.dao.model.GdsCollectGdsIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsCollectGdsIdxMapper {
    Long countByExample(GdsCollectGdsIdxCriteria example) throws DataAccessException;

    int deleteByExample(GdsCollectGdsIdxCriteria example) throws DataAccessException;

    int insert(GdsCollectGdsIdx record) throws DataAccessException;

    int insertSelective(GdsCollectGdsIdx record) throws DataAccessException;

    List<GdsCollectGdsIdx> selectByExample(GdsCollectGdsIdxCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsCollectGdsIdx record, @Param("example") GdsCollectGdsIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsCollectGdsIdx record, @Param("example") GdsCollectGdsIdxCriteria example) throws DataAccessException;
}
