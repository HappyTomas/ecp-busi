package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsShiptempPrice;
import com.zengshi.ecp.goods.dao.model.GdsShiptempPriceCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsShiptempPriceMapper {
    Long countByExample(GdsShiptempPriceCriteria example) throws DataAccessException;

    int deleteByExample(GdsShiptempPriceCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long pricingListId) throws DataAccessException;

    int insert(GdsShiptempPrice record) throws DataAccessException;

    int insertSelective(GdsShiptempPrice record) throws DataAccessException;

    List<GdsShiptempPrice> selectByExample(GdsShiptempPriceCriteria example) throws DataAccessException;

    GdsShiptempPrice selectByPrimaryKey(Long pricingListId) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsShiptempPrice record, @Param("example") GdsShiptempPriceCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsShiptempPrice record, @Param("example") GdsShiptempPriceCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsShiptempPrice record) throws DataAccessException;

    int updateByPrimaryKey(GdsShiptempPrice record) throws DataAccessException;
}
