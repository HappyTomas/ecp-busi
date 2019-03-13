/**
 * 
 */
package com.zengshi.ecp.demo.service.busi.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.zengshi.ecp.demo.dao.mapper.busi.DemoLogMapper;
import com.zengshi.ecp.demo.dao.model.DemoLog;
import com.zengshi.ecp.demo.dao.model.DemoLogCriteria;
import com.zengshi.ecp.demo.dubbo.dto.DemoLogDTO;
import com.zengshi.ecp.demo.service.busi.interfaces.IDemoLogSV;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.db.sequence.Sequence;

/**
 * @author yugn
 *
 */
public class DemoLogSVImpl extends GeneralSQLSVImpl implements IDemoLogSV {

	@Resource
	private DemoLogMapper demoLogMapper;
//	@Resource
//	private IDemoLogDAO demoLogDAO;
	
	@Resource(name="seq_demo_info_id")
//	@Resource(name="seq_demo_info")
	private Sequence sequence;
	/* (non-Javadoc)
	 * @see com.zengshi.ecp.busi.demo.log.service.interfaces.IDemoLogSV#saveLog(com.zengshi.ecp.busi.demo.log.dao.model.DemoLog)
	 */
	@Override
	public boolean saveLog(DemoLog log){
		log.setLogId(this.sequence.nextValue());
//		if(log.getLogId()%2==1){
//			return false; 
//		}
		this.demoLogMapper.insert(log);
		return true;
	}
	@Override
	public DemoLog find(long id){
		
		return this.demoLogMapper.selectByPrimaryKey(id);
	}
    @Override
    public DemoLog select(long id) {
        return this.demoLogMapper.selectByPrimaryKey(id);
    }
    @Override
    public DemoLog findByCodeAndId(long id, String dbCode)  throws Exception{
        DemoLogCriteria criteria = new DemoLogCriteria();
        criteria.createCriteria().andDbCodeEqualTo(dbCode).andLogIdEqualTo(id);
        List<DemoLog> logs = this.demoLogMapper.selectByExample(criteria);
        if(logs == null || logs.size() == 0){
            throw new Exception("没找到数据");
        }
        return logs.get(0);
    }
    @Override
    public List<DemoLog> findAll() {
        DemoLogCriteria criteria = new DemoLogCriteria();
        return this.demoLogMapper.selectByExample(criteria);
    }
    
    @Override
    public List<DemoLog> findAll(DemoLogCriteria criteria) {
        
        return this.demoLogMapper.selectByExample(criteria);
    }
    //    @Cacheable(key="#baseInfo.appName+'baseInfo.appName'",value ="redis")
//    @CacheEvict(value="redis",key="#baseInfo.appName+'baseInfo.appName'")
    @Override
    public PageResponseDTO<DemoLogDTO> findByPage(BaseInfo baseInfo){

        DemoLogCriteria dcriteria=new DemoLogCriteria();
        dcriteria.setLimitClauseCount(baseInfo.getPageSize());
        dcriteria.setLimitClauseStart(baseInfo.getStartRowIndex());
        dcriteria.setOrderByClause("log_id desc");
        dcriteria.createCriteria().andDbCodeLike("0%");
        return super.queryByPagination(baseInfo, dcriteria,true, new PaginationCallback<DemoLog, DemoLogDTO>() {
            //查询记录集
            @Override
            public List<DemoLog> queryDB(BaseCriteria criteria) {
                
                return demoLogMapper.selectByExample((DemoLogCriteria)criteria);
            }
            //查询总记录数
            @Override
            public long queryTotal(BaseCriteria criteria) {
                
                return demoLogMapper.countByExample((DemoLogCriteria)criteria);
            }
            //可以不定义
            @Override
            public List<Comparator<DemoLog>> defineComparators() {
                List<Comparator<DemoLog>> ls=new ArrayList<Comparator<DemoLog>>();
                ls.add(new Comparator<DemoLog>(){

                    @Override
                    public int compare(DemoLog o1, DemoLog o2) {
                        return o1.getLogId()>o2.getLogId()?1:-1;
                    }
                    
                });
                return ls;
            }
            //查询结果转换
            @Override
            public DemoLogDTO warpReturnObject(DemoLog t) {
                DemoLogDTO dto=new DemoLogDTO();
                BeanUtils.copyProperties(t, dto);
                return dto;
            }
        });
    }
	

}
