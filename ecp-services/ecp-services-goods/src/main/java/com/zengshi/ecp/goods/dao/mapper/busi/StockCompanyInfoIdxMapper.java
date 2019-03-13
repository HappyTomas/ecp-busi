package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.StockCompanyInfoIdx;
import com.zengshi.ecp.goods.dao.model.StockCompanyInfoIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface StockCompanyInfoIdxMapper {
    Long countByExample(StockCompanyInfoIdxCriteria example) throws DataAccessException;

    int insert(StockCompanyInfoIdx record) throws DataAccessException;

    int insertSelective(StockCompanyInfoIdx record) throws DataAccessException;

    List<StockCompanyInfoIdx> selectByExample(StockCompanyInfoIdxCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") StockCompanyInfoIdx record, @Param("example") StockCompanyInfoIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") StockCompanyInfoIdx record, @Param("example") StockCompanyInfoIdxCriteria example) throws DataAccessException;
}
