package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.ShopExpressRel;
import com.zengshi.ecp.staff.dao.model.ShopExpressRelCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ShopExpressRelMapper {
    Long countByExample(ShopExpressRelCriteria example) throws DataAccessException;

    int deleteByExample(ShopExpressRelCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(ShopExpressRel record) throws DataAccessException;

    int insertSelective(ShopExpressRel record) throws DataAccessException;

    List<ShopExpressRel> selectByExample(ShopExpressRelCriteria example) throws DataAccessException;

    ShopExpressRel selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") ShopExpressRel record, @Param("example") ShopExpressRelCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") ShopExpressRel record, @Param("example") ShopExpressRelCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(ShopExpressRel record) throws DataAccessException;

    int updateByPrimaryKey(ShopExpressRel record) throws DataAccessException;
}