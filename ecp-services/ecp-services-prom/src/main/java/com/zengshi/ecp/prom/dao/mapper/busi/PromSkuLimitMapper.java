package com.zengshi.ecp.prom.dao.mapper.busi;

import com.zengshi.ecp.prom.dao.model.PromSkuLimit;
import com.zengshi.ecp.prom.dao.model.PromSkuLimitCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PromSkuLimitMapper {
    Long countByExample(PromSkuLimitCriteria example) throws DataAccessException;

    int deleteByExample(PromSkuLimitCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(PromSkuLimit record) throws DataAccessException;

    int insertSelective(PromSkuLimit record) throws DataAccessException;

    List<PromSkuLimit> selectByExample(PromSkuLimitCriteria example) throws DataAccessException;

    PromSkuLimit selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PromSkuLimit record, @Param("example") PromSkuLimitCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PromSkuLimit record, @Param("example") PromSkuLimitCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PromSkuLimit record) throws DataAccessException;

    int updateByPrimaryKey(PromSkuLimit record) throws DataAccessException;
}