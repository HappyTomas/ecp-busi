package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.ShopVipRule;
import com.zengshi.ecp.staff.dao.model.ShopVipRuleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ShopVipRuleMapper {
    Long countByExample(ShopVipRuleCriteria example) throws DataAccessException;

    int deleteByExample(ShopVipRuleCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(ShopVipRule record) throws DataAccessException;

    int insertSelective(ShopVipRule record) throws DataAccessException;

    List<ShopVipRule> selectByExample(ShopVipRuleCriteria example) throws DataAccessException;

    ShopVipRule selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") ShopVipRule record, @Param("example") ShopVipRuleCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") ShopVipRule record, @Param("example") ShopVipRuleCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(ShopVipRule record) throws DataAccessException;

    int updateByPrimaryKey(ShopVipRule record) throws DataAccessException;
}