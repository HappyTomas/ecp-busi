package com.zengshi.ecp.goods.dao.mapper.busi.manual;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.goods.dao.model.GdsCollectCntCriteria;

public interface GdsCollectCntExtraMapper {

    int executeGdsCollectCntInc(@Param("example") GdsCollectCntCriteria example) throws DataAccessException;

    int executeGdsCollectCntDec(@Param("example") GdsCollectCntCriteria example) throws DataAccessException;

}
