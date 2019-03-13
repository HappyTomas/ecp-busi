package com.zengshi.ecp.prom.dao.mapper.busi;

import com.zengshi.ecp.prom.dao.model.PromMatchSku;
import com.zengshi.ecp.prom.dao.model.PromMatchSkuCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PromMatchSkuMapper {
    Long countByExample(PromMatchSkuCriteria example) throws DataAccessException;

    int deleteByExample(PromMatchSkuCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(PromMatchSku record) throws DataAccessException;

    int insertSelective(PromMatchSku record) throws DataAccessException;

    List<PromMatchSku> selectByExample(PromMatchSkuCriteria example) throws DataAccessException;

    PromMatchSku selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PromMatchSku record, @Param("example") PromMatchSkuCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PromMatchSku record, @Param("example") PromMatchSkuCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PromMatchSku record) throws DataAccessException;

    int updateByPrimaryKey(PromMatchSku record) throws DataAccessException;
}