package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsShop2Catgrate;
import com.zengshi.ecp.goods.dao.model.GdsShop2CatgrateCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsShop2CatgrateMapper {
    Long countByExample(GdsShop2CatgrateCriteria example) throws DataAccessException;

    int deleteByExample(GdsShop2CatgrateCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String id) throws DataAccessException;

    int insert(GdsShop2Catgrate record) throws DataAccessException;

    int insertSelective(GdsShop2Catgrate record) throws DataAccessException;

    List<GdsShop2Catgrate> selectByExample(GdsShop2CatgrateCriteria example) throws DataAccessException;

    GdsShop2Catgrate selectByPrimaryKey(String id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsShop2Catgrate record, @Param("example") GdsShop2CatgrateCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsShop2Catgrate record, @Param("example") GdsShop2CatgrateCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsShop2Catgrate record) throws DataAccessException;

    int updateByPrimaryKey(GdsShop2Catgrate record) throws DataAccessException;
}
