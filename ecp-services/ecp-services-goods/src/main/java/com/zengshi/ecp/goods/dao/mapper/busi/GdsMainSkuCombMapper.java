package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsMainSkuComb;
import com.zengshi.ecp.goods.dao.model.GdsMainSkuCombCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsMainSkuCombMapper {
    Long countByExample(GdsMainSkuCombCriteria example) throws DataAccessException;

    int deleteByExample(GdsMainSkuCombCriteria example) throws DataAccessException;

    int insert(GdsMainSkuComb record) throws DataAccessException;

    int insertSelective(GdsMainSkuComb record) throws DataAccessException;

    List<GdsMainSkuComb> selectByExample(GdsMainSkuCombCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsMainSkuComb record, @Param("example") GdsMainSkuCombCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsMainSkuComb record, @Param("example") GdsMainSkuCombCriteria example) throws DataAccessException;
}
