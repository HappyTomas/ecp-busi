package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsEvalShopIdx;
import com.zengshi.ecp.goods.dao.model.GdsEvalShopIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsEvalShopIdxMapper {
    Long countByExample(GdsEvalShopIdxCriteria example) throws DataAccessException;

    int deleteByExample(GdsEvalShopIdxCriteria example) throws DataAccessException;

    int insert(GdsEvalShopIdx record) throws DataAccessException;

    int insertSelective(GdsEvalShopIdx record) throws DataAccessException;

    List<GdsEvalShopIdx> selectByExample(GdsEvalShopIdxCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsEvalShopIdx record, @Param("example") GdsEvalShopIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsEvalShopIdx record, @Param("example") GdsEvalShopIdxCriteria example) throws DataAccessException;
}
