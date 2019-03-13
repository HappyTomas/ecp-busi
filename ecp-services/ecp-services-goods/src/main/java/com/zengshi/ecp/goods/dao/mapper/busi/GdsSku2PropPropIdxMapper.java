package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsSku2PropPropIdx;
import com.zengshi.ecp.goods.dao.model.GdsSku2PropPropIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsSku2PropPropIdxMapper {
    Long countByExample(GdsSku2PropPropIdxCriteria example) throws DataAccessException;

    int deleteByExample(GdsSku2PropPropIdxCriteria example) throws DataAccessException;

    int insert(GdsSku2PropPropIdx record) throws DataAccessException;

    int insertSelective(GdsSku2PropPropIdx record) throws DataAccessException;

    List<GdsSku2PropPropIdx> selectByExample(GdsSku2PropPropIdxCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsSku2PropPropIdx record, @Param("example") GdsSku2PropPropIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsSku2PropPropIdx record, @Param("example") GdsSku2PropPropIdxCriteria example) throws DataAccessException;
}
