package com.zengshi.ecp.prom.dao.mapper.busi;

import com.zengshi.ecp.prom.dao.model.PromSku;
import com.zengshi.ecp.prom.dao.model.PromSkuCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PromSkuMapper {
    Long countByExample(PromSkuCriteria example) throws DataAccessException;

    int deleteByExample(PromSkuCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(PromSku record) throws DataAccessException;

    int insertSelective(PromSku record) throws DataAccessException;

    List<PromSku> selectByExample(PromSkuCriteria example) throws DataAccessException;

    PromSku selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PromSku record, @Param("example") PromSkuCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PromSku record, @Param("example") PromSkuCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PromSku record) throws DataAccessException;

    int updateByPrimaryKey(PromSku record) throws DataAccessException;
}