package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.ShopHotlineInfo;
import com.zengshi.ecp.staff.dao.model.ShopHotlineInfoCriteria;
import java.util.List;
import org.springframework.dao.DataAccessException;

public interface ShopHotlineInfoMapper {
    Long countByExample(ShopHotlineInfoCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(ShopHotlineInfo record) throws DataAccessException;

    int insertSelective(ShopHotlineInfo record) throws DataAccessException;

    List<ShopHotlineInfo> selectByExample(ShopHotlineInfoCriteria example) throws DataAccessException;

    ShopHotlineInfo selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByPrimaryKeySelective(ShopHotlineInfo record) throws DataAccessException;

    int updateByPrimaryKey(ShopHotlineInfo record) throws DataAccessException;
}