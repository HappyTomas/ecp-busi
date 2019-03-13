package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.ShopVipLevel;
import com.zengshi.ecp.staff.dao.model.ShopVipLevelCriteria;
import com.zengshi.ecp.staff.dao.model.ShopVipLevelKey;
import java.util.List;
import org.springframework.dao.DataAccessException;

public interface ShopVipLevelMapper {
    Long countByExample(ShopVipLevelCriteria example) throws DataAccessException;

    int deleteByLevelCode(ShopVipLevelKey key) throws DataAccessException;

    int insert(ShopVipLevel record) throws DataAccessException;

    int insertSelective(ShopVipLevel record) throws DataAccessException;

    List<ShopVipLevel> selectByExample(ShopVipLevelCriteria example) throws DataAccessException;

    ShopVipLevel selectByPrimaryKey(ShopVipLevelKey key) throws DataAccessException;

    int updateByPrimaryKeySelective(ShopVipLevel record) throws DataAccessException;

    int updateByPrimaryKey(ShopVipLevel record) throws DataAccessException;
    
    int deleteByShopId(ShopVipLevel record) throws DataAccessException;
}