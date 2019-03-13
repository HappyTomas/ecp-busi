package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsArrmsgShopIdx;
import com.zengshi.ecp.goods.dao.model.GdsArrmsgShopIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsArrmsgShopIdxMapper {
    Long countByExample(GdsArrmsgShopIdxCriteria example) throws DataAccessException;

    int deleteByExample(GdsArrmsgShopIdxCriteria example) throws DataAccessException;

    int insert(GdsArrmsgShopIdx record) throws DataAccessException;

    int insertSelective(GdsArrmsgShopIdx record) throws DataAccessException;

    List<GdsArrmsgShopIdx> selectByExample(GdsArrmsgShopIdxCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsArrmsgShopIdx record, @Param("example") GdsArrmsgShopIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsArrmsgShopIdx record, @Param("example") GdsArrmsgShopIdxCriteria example) throws DataAccessException;
}
