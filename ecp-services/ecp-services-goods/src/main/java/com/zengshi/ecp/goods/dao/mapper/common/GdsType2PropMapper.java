package com.zengshi.ecp.goods.dao.mapper.common;

import com.zengshi.ecp.goods.dao.model.GdsType2Prop;
import com.zengshi.ecp.goods.dao.model.GdsType2PropCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsType2PropMapper {
    Long countByExample(GdsType2PropCriteria example) throws DataAccessException;

    int deleteByExample(GdsType2PropCriteria example) throws DataAccessException;

    int insert(GdsType2Prop record) throws DataAccessException;

    int insertSelective(GdsType2Prop record) throws DataAccessException;

    List<GdsType2Prop> selectByExample(GdsType2PropCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsType2Prop record, @Param("example") GdsType2PropCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsType2Prop record, @Param("example") GdsType2PropCriteria example) throws DataAccessException;
}
