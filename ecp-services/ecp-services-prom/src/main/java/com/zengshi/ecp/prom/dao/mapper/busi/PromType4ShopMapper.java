package com.zengshi.ecp.prom.dao.mapper.busi;

import com.zengshi.ecp.prom.dao.model.PromType4Shop;
import com.zengshi.ecp.prom.dao.model.PromType4ShopCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PromType4ShopMapper {
    Long countByExample(PromType4ShopCriteria example) throws DataAccessException;

    int deleteByExample(PromType4ShopCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(PromType4Shop record) throws DataAccessException;

    int insertSelective(PromType4Shop record) throws DataAccessException;

    List<PromType4Shop> selectByExample(PromType4ShopCriteria example) throws DataAccessException;

    PromType4Shop selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PromType4Shop record, @Param("example") PromType4ShopCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PromType4Shop record, @Param("example") PromType4ShopCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PromType4Shop record) throws DataAccessException;

    int updateByPrimaryKey(PromType4Shop record) throws DataAccessException;
}