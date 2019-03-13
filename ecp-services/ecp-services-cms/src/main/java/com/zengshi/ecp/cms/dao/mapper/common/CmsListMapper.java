package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsList;
import com.zengshi.ecp.cms.dao.model.CmsListCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsListMapper {
    Long countByExample(CmsListCriteria example) throws DataAccessException;

    int deleteByExample(CmsListCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsList record) throws DataAccessException;

    int insertSelective(CmsList record) throws DataAccessException;

    List<CmsList> selectByExample(CmsListCriteria example) throws DataAccessException;

    CmsList selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsList record, @Param("example") CmsListCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsList record, @Param("example") CmsListCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsList record) throws DataAccessException;

    int updateByPrimaryKey(CmsList record) throws DataAccessException;
}
