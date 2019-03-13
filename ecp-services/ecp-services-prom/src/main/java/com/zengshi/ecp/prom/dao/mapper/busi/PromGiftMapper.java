package com.zengshi.ecp.prom.dao.mapper.busi;

import com.zengshi.ecp.prom.dao.model.PromGift;
import com.zengshi.ecp.prom.dao.model.PromGiftCriteria;
import com.zengshi.ecp.prom.dao.model.PromGiftKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PromGiftMapper {
    Long countByExample(PromGiftCriteria example) throws DataAccessException;

    int deleteByExample(PromGiftCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(PromGiftKey key) throws DataAccessException;

    int insert(PromGift record) throws DataAccessException;

    int insertSelective(PromGift record) throws DataAccessException;

    List<PromGift> selectByExample(PromGiftCriteria example) throws DataAccessException;

    PromGift selectByPrimaryKey(PromGiftKey key) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PromGift record, @Param("example") PromGiftCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PromGift record, @Param("example") PromGiftCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PromGift record) throws DataAccessException;

    int updateByPrimaryKey(PromGift record) throws DataAccessException;
}