package com.zengshi.ecp.unpf.dao.mapper.busi;

import com.zengshi.ecp.unpf.dao.model.UnpfShopExpress;
import com.zengshi.ecp.unpf.dao.model.UnpfShopExpressCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface UnpfShopExpressMapper {
    Long countByExample(UnpfShopExpressCriteria example) throws DataAccessException;

    int deleteByExample(UnpfShopExpressCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(UnpfShopExpress record) throws DataAccessException;

    int insertSelective(UnpfShopExpress record) throws DataAccessException;

    List<UnpfShopExpress> selectByExample(UnpfShopExpressCriteria example) throws DataAccessException;

    UnpfShopExpress selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") UnpfShopExpress record, @Param("example") UnpfShopExpressCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") UnpfShopExpress record, @Param("example") UnpfShopExpressCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(UnpfShopExpress record) throws DataAccessException;

    int updateByPrimaryKey(UnpfShopExpress record) throws DataAccessException;
}