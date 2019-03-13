package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsShiptempShopIdx;
import com.zengshi.ecp.goods.dao.model.GdsShiptempShopIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsShiptempShopIdxMapper {
    Long countByExample(GdsShiptempShopIdxCriteria example) throws DataAccessException;

    int deleteByExample(GdsShiptempShopIdxCriteria example) throws DataAccessException;

    int insert(GdsShiptempShopIdx record) throws DataAccessException;

    int insertSelective(GdsShiptempShopIdx record) throws DataAccessException;

    List<GdsShiptempShopIdx> selectByExample(GdsShiptempShopIdxCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsShiptempShopIdx record, @Param("example") GdsShiptempShopIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsShiptempShopIdx record, @Param("example") GdsShiptempShopIdxCriteria example) throws DataAccessException;
}
