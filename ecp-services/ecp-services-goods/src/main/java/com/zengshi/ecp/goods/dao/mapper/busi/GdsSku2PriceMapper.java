package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsSku2Price;
import com.zengshi.ecp.goods.dao.model.GdsSku2PriceCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsSku2PriceMapper {
    Long countByExample(GdsSku2PriceCriteria example) throws DataAccessException;

    int deleteByExample(GdsSku2PriceCriteria example) throws DataAccessException;

    int insert(GdsSku2Price record) throws DataAccessException;

    int insertSelective(GdsSku2Price record) throws DataAccessException;

    List<GdsSku2Price> selectByExample(GdsSku2PriceCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsSku2Price record, @Param("example") GdsSku2PriceCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsSku2Price record, @Param("example") GdsSku2PriceCriteria example) throws DataAccessException;
}
