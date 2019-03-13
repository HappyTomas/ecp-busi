package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsGift;
import com.zengshi.ecp.goods.dao.model.GdsGiftCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsGiftMapper {
    Long countByExample(GdsGiftCriteria example) throws DataAccessException;

    int deleteByExample(GdsGiftCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsGift record) throws DataAccessException;

    int insertSelective(GdsGift record) throws DataAccessException;

    List<GdsGift> selectByExample(GdsGiftCriteria example) throws DataAccessException;

    GdsGift selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsGift record, @Param("example") GdsGiftCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsGift record, @Param("example") GdsGiftCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsGift record) throws DataAccessException;

    int updateByPrimaryKey(GdsGift record) throws DataAccessException;
}
