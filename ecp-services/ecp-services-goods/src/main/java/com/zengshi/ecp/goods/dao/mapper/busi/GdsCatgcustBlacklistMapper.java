package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsCatgcustBlacklist;
import com.zengshi.ecp.goods.dao.model.GdsCatgcustBlacklistCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsCatgcustBlacklistMapper {
    Long countByExample(GdsCatgcustBlacklistCriteria example) throws DataAccessException;

    int deleteByExample(GdsCatgcustBlacklistCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsCatgcustBlacklist record) throws DataAccessException;

    int insertSelective(GdsCatgcustBlacklist record) throws DataAccessException;

    List<GdsCatgcustBlacklist> selectByExample(GdsCatgcustBlacklistCriteria example) throws DataAccessException;

    GdsCatgcustBlacklist selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsCatgcustBlacklist record, @Param("example") GdsCatgcustBlacklistCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsCatgcustBlacklist record, @Param("example") GdsCatgcustBlacklistCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsCatgcustBlacklist record) throws DataAccessException;

    int updateByPrimaryKey(GdsCatgcustBlacklist record) throws DataAccessException;
}
