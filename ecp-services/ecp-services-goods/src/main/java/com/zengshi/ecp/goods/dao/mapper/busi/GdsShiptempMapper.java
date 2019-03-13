package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsShiptemp;
import com.zengshi.ecp.goods.dao.model.GdsShiptempCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsShiptempMapper {
    Long countByExample(GdsShiptempCriteria example) throws DataAccessException;

    int deleteByExample(GdsShiptempCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsShiptemp record) throws DataAccessException;

    int insertSelective(GdsShiptemp record) throws DataAccessException;

    List<GdsShiptemp> selectByExample(GdsShiptempCriteria example) throws DataAccessException;

    GdsShiptemp selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsShiptemp record, @Param("example") GdsShiptempCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsShiptemp record, @Param("example") GdsShiptempCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsShiptemp record) throws DataAccessException;

    int updateByPrimaryKey(GdsShiptemp record) throws DataAccessException;
}
