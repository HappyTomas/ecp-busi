package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.StockCompanyRepIdx;
import com.zengshi.ecp.goods.dao.model.StockCompanyRepIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface StockCompanyRepIdxMapper {
    Long countByExample(StockCompanyRepIdxCriteria example) throws DataAccessException;

    int insert(StockCompanyRepIdx record) throws DataAccessException;

    int insertSelective(StockCompanyRepIdx record) throws DataAccessException;

    List<StockCompanyRepIdx> selectByExample(StockCompanyRepIdxCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") StockCompanyRepIdx record, @Param("example") StockCompanyRepIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") StockCompanyRepIdx record, @Param("example") StockCompanyRepIdxCriteria example) throws DataAccessException;
}
