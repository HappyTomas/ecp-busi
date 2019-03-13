package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsPlace;
import com.zengshi.ecp.cms.dao.model.CmsPlaceCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsPlaceMapper {
    Long countByExample(CmsPlaceCriteria example) throws DataAccessException;

    int deleteByExample(CmsPlaceCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsPlace record) throws DataAccessException;

    int insertSelective(CmsPlace record) throws DataAccessException;

    List<CmsPlace> selectByExample(CmsPlaceCriteria example) throws DataAccessException;

    CmsPlace selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsPlace record, @Param("example") CmsPlaceCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsPlace record, @Param("example") CmsPlaceCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsPlace record) throws DataAccessException;

    int updateByPrimaryKey(CmsPlace record) throws DataAccessException;
}
