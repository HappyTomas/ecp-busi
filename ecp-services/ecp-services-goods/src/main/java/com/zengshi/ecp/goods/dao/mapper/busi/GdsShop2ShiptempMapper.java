package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsShop2Shiptemp;
import com.zengshi.ecp.goods.dao.model.GdsShop2ShiptempCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsShop2ShiptempMapper {
    Long countByExample(GdsShop2ShiptempCriteria example) throws DataAccessException;

    int deleteByExample(GdsShop2ShiptempCriteria example) throws DataAccessException;

    int insert(GdsShop2Shiptemp record) throws DataAccessException;

    int insertSelective(GdsShop2Shiptemp record) throws DataAccessException;

    List<GdsShop2Shiptemp> selectByExample(GdsShop2ShiptempCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsShop2Shiptemp record, @Param("example") GdsShop2ShiptempCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsShop2Shiptemp record, @Param("example") GdsShop2ShiptempCriteria example) throws DataAccessException;
}
