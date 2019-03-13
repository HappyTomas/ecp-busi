package com.zengshi.ecp.demo.dao.mapper.common.manual;

import com.zengshi.ecp.demo.dao.model.DemoCfg;
import com.zengshi.ecp.demo.dao.model.DemoCfgCriteria;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.dao.DataAccessException;

public interface DemoCfgGroupMapper {

    ///查询结果转换为List<Map<>>
    @Select("SELECT * FROM t_demo_cfg WHERE id > #{id}")
    List<Map<String,Object>> selectByExample(int id) throws DataAccessException;
    
    ///查询结果转换为对象；
    @Select("SELECT * FROM t_demo_cfg WHERE id > #{id}")
    List<DemoCfg> select2DemoCfg(int id) throws DataAccessException;
    
    ///查询一条结果转换为对象
    @Select("SELECT * FROM t_demo_cfg WHERE id = #{id}")
    DemoCfg selectOne2DemoCfg(int id) throws DataAccessException;
    
    ////更新语句
    @Update("update t_demo_cfg set code=#{code} where id=#{id}")
    int updateValue(DemoCfg demoCfg) throws DataAccessException;
    
    
    @Select("SELECT count(*) as cnt,code FROM t_demo_cfg group by code")
    List<Map<String,Object>> groupBy() throws DataAccessException;
    
    ///查询技术，使用
    long countCountByExample(DemoCfgCriteria criteria) throws DataAccessException;

}
