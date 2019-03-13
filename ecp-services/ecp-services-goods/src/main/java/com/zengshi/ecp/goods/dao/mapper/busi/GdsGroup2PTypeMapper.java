package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsGroup2PType;
import com.zengshi.ecp.goods.dao.model.GdsGroup2PTypeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsGroup2PTypeMapper {
    Long countByExample(GdsGroup2PTypeCriteria example) throws DataAccessException;

    int deleteByExample(GdsGroup2PTypeCriteria example) throws DataAccessException;

    int insert(GdsGroup2PType record) throws DataAccessException;

    int insertSelective(GdsGroup2PType record) throws DataAccessException;

    List<GdsGroup2PType> selectByExample(GdsGroup2PTypeCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsGroup2PType record, @Param("example") GdsGroup2PTypeCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsGroup2PType record, @Param("example") GdsGroup2PTypeCriteria example) throws DataAccessException;
}
