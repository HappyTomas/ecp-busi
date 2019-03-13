package com.zengshi.ecp.goods.dao.mapper.busi.manual;

import com.zengshi.ecp.goods.dao.model.GdsBrowseHis;
import com.zengshi.ecp.goods.dao.model.GdsBrowseHisCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsBrowseHisExtraMapper {
    Long countByExample(GdsBrowseHisCriteria example) throws DataAccessException;

    int deleteByExample(GdsBrowseHisCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsBrowseHis record) throws DataAccessException;

    int insertSelective(GdsBrowseHis record) throws DataAccessException;

    List<GdsBrowseHis> selectByExample(GdsBrowseHisCriteria example) throws DataAccessException;

    GdsBrowseHis selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsBrowseHis record, @Param("example") GdsBrowseHisCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsBrowseHis record, @Param("example") GdsBrowseHisCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsBrowseHis record) throws DataAccessException;

    int updateByPrimaryKey(GdsBrowseHis record) throws DataAccessException;
}
