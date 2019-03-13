package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdGift;
import com.zengshi.ecp.order.dao.model.OrdGiftCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdGiftMapper {
    Long countByExample(OrdGiftCriteria example) throws DataAccessException;

    int deleteByExample(OrdGiftCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdGift record) throws DataAccessException;

    int insertSelective(OrdGift record) throws DataAccessException;

    List<OrdGift> selectByExample(OrdGiftCriteria example) throws DataAccessException;

    OrdGift selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdGift record, @Param("example") OrdGiftCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdGift record, @Param("example") OrdGiftCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdGift record) throws DataAccessException;

    int updateByPrimaryKey(OrdGift record) throws DataAccessException;
}