package com.zengshi.ecp.im.dao.mapper.common;

import com.zengshi.ecp.im.dao.model.ImHotlineInfo;
import com.zengshi.ecp.im.dao.model.ImHotlineInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ImHotlineInfoMapper {
    Long countByExample(ImHotlineInfoCriteria example) throws DataAccessException;

    int deleteByExample(ImHotlineInfoCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(ImHotlineInfo record) throws DataAccessException;

    int insertSelective(ImHotlineInfo record) throws DataAccessException;

    List<ImHotlineInfo> selectByExample(ImHotlineInfoCriteria example) throws DataAccessException;

    ImHotlineInfo selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") ImHotlineInfo record, @Param("example") ImHotlineInfoCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") ImHotlineInfo record, @Param("example") ImHotlineInfoCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(ImHotlineInfo record) throws DataAccessException;

    int updateByPrimaryKey(ImHotlineInfo record) throws DataAccessException;
}