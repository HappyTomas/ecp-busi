package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdOfflinePic;
import com.zengshi.ecp.order.dao.model.OrdOfflinePicCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdOfflinePicMapper {
    Long countByExample(OrdOfflinePicCriteria example) throws DataAccessException;

    int deleteByExample(OrdOfflinePicCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdOfflinePic record) throws DataAccessException;

    int insertSelective(OrdOfflinePic record) throws DataAccessException;

    List<OrdOfflinePic> selectByExample(OrdOfflinePicCriteria example) throws DataAccessException;

    OrdOfflinePic selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdOfflinePic record, @Param("example") OrdOfflinePicCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdOfflinePic record, @Param("example") OrdOfflinePicCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdOfflinePic record) throws DataAccessException;

    int updateByPrimaryKey(OrdOfflinePic record) throws DataAccessException;
}