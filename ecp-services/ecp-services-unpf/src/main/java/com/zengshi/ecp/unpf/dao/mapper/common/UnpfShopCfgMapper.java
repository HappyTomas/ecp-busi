package com.zengshi.ecp.unpf.dao.mapper.common;

import com.zengshi.ecp.unpf.dao.model.UnpfShopCfg;
import com.zengshi.ecp.unpf.dao.model.UnpfShopCfgCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface UnpfShopCfgMapper {
    Long countByExample(UnpfShopCfgCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(UnpfShopCfg record) throws DataAccessException;

    int insertSelective(UnpfShopCfg record) throws DataAccessException;

    List<UnpfShopCfg> selectByExampleWithBLOBs(UnpfShopCfgCriteria example) throws DataAccessException;

    List<UnpfShopCfg> selectByExample(UnpfShopCfgCriteria example) throws DataAccessException;

    UnpfShopCfg selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") UnpfShopCfg record, @Param("example") UnpfShopCfgCriteria example) throws DataAccessException;

    int updateByExampleWithBLOBs(@Param("record") UnpfShopCfg record, @Param("example") UnpfShopCfgCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") UnpfShopCfg record, @Param("example") UnpfShopCfgCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(UnpfShopCfg record) throws DataAccessException;

    int updateByPrimaryKeyWithBLOBs(UnpfShopCfg record) throws DataAccessException;

    int updateByPrimaryKey(UnpfShopCfg record) throws DataAccessException;
}