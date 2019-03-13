package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsSku2PriceSnap;
import com.zengshi.ecp.goods.dao.model.GdsSku2PriceSnapCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsSku2PriceSnapMapper {
    Long countByExample(GdsSku2PriceSnapCriteria example) throws DataAccessException;

    int deleteByExample(GdsSku2PriceSnapCriteria example) throws DataAccessException;

    int insert(GdsSku2PriceSnap record) throws DataAccessException;

    int insertSelective(GdsSku2PriceSnap record) throws DataAccessException;

    List<GdsSku2PriceSnap> selectByExample(GdsSku2PriceSnapCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsSku2PriceSnap record, @Param("example") GdsSku2PriceSnapCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsSku2PriceSnap record, @Param("example") GdsSku2PriceSnapCriteria example) throws DataAccessException;
}
