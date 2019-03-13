package com.zengshi.ecp.goods.service.busi.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsCatgcustBlacklistMapper;
import com.zengshi.ecp.goods.dao.model.GdsCatgcustBlacklist;
import com.zengshi.ecp.goods.dao.model.GdsCatgcustBlacklistCriteria;
import com.zengshi.ecp.goods.dao.model.GdsCatgcustBlacklistCriteria.Criteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustBlacklistReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustBlacklistRespDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsCatgCustBlacklistSV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;

public class GdsCatgCustBlacklistSVImpl extends AbstractSVImpl implements IGdsCatgCustBlacklistSV {
//	private static final String MODULE = GdsCatgCustBlacklistSVImpl.class.getName();
    
    @Resource(name = "seq_gds_catgcust_blacklist")
	private Sequence seqGdsCatgCustBlacklist;

	@Resource
	private GdsCatgcustBlacklistMapper gdsCatgcustBlacklistMapper;
	/**
	 * 
	 * 支持批量添加  会合并gdsId与gdsIdList. 
	 * 添加前失效库中对应gdsId数据
	 */
	@Override
	public void addGdsCatgCustBlacklist(GdsCatgCustBlacklistReqDTO reqDTO)
			throws BusinessException {
	    List<Long> gdsIdList = new ArrayList<Long>();
	    if(null != reqDTO.getGdsIdList()){
	        gdsIdList.addAll(reqDTO.getGdsIdList());
        }
        if(null != reqDTO.getGdsId()){
            gdsIdList.add(reqDTO.getGdsId());
        }
        if(CollectionUtils.isNotEmpty(gdsIdList)){
            for (Long gdsId:gdsIdList) {
                GdsCatgCustBlacklistReqDTO addEntity=new GdsCatgCustBlacklistReqDTO();
                ObjectCopyUtil.copyObjValue(reqDTO, addEntity, null, false);
                addEntity.setGdsIdList(null);
                addEntity.setGdsId(gdsId);
                this.deleteGdsCatgCustBlacklistByGdsId(addEntity);
                this.addGdsCatgCustBlacklistByOne(addEntity);
            }
        }
	}
	/**
	 * 
	 * 根据id删除  只识别reqDTO中的id与idList字段  
	 * 支持批量删除   会合并id与idList
	 */
	@Override
	public int deleteGdsCatgCustBlacklistByPrimaryKey(GdsCatgCustBlacklistReqDTO reqDTO)
			throws BusinessException {
	    GdsCatgCustBlacklistReqDTO dto = new GdsCatgCustBlacklistReqDTO();
	    List<Long> idList = new ArrayList<Long>();
	    if(null != reqDTO.getIdList()){
	        idList.addAll(reqDTO.getIdList());
        }
        if(null != reqDTO.getId()){
            idList.add(reqDTO.getId());
        }
	    dto.setIdList(idList);
	    if(StringUtil.isBlank(reqDTO.getStatus())){
            dto.setStatus(GdsConstants.Commons.STATUS_VALID);  
        }
		return deleteGdsCatgCustBlacklistByGdsId(dto);
	}
	/**
	 * 
	 * 按条件删除    
	 * reqDTO字段之间是与  字段之内是或  如 id 与 idList  会是 id = 'id' and id in idList 
	 */
	@Override
	public int deleteGdsCatgCustBlacklistByGdsId(GdsCatgCustBlacklistReqDTO reqDTO)
			throws BusinessException {
		GdsCatgcustBlacklist record=new GdsCatgcustBlacklist();
		record.setUpdateStaff(reqDTO.getStaff().getId());
		record.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		record.setStatus(GdsConstants.Commons.STATUS_INVALID);

		GdsCatgcustBlacklistCriteria example = new GdsCatgcustBlacklistCriteria();
		if(StringUtil.isBlank(reqDTO.getStatus())){
		    reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);  
        }
		initExampleCriteria(example.createCriteria(),reqDTO);
		return gdsCatgcustBlacklistMapper.updateByExampleSelective(record, example);
	}
	/**
     * 
     * 按条件删除 
     * reqDTO字段之间是与  字段之内是或  如 id 与 idList  会是 id = 'id' and id in idList 
     */
	@Override
	public int deleteBatchGdsCatgCustBlacklistByGdsId(GdsCatgCustBlacklistReqDTO reqDTO)
			throws BusinessException {
		return deleteGdsCatgCustBlacklistByGdsId(reqDTO);
	}
	/**
	 * 
	 * 按条件查询 
	 * 只返回第一个数据    
	 * reqDTO字段之间是与  字段之内是或  如 id 与 idList  会是 id = 'id' and id in idList 
	 */
	@Override
	public GdsCatgCustBlacklistRespDTO queryGdsCatgcustBlacklistByExample(GdsCatgCustBlacklistReqDTO reqDTO) {
		
		GdsCatgcustBlacklistCriteria example = new GdsCatgcustBlacklistCriteria();
		initExampleCriteria(example.createCriteria(),reqDTO);
		example.setOrderByClause("CREATE_TIME DESC");
		
		List<GdsCatgcustBlacklist> result = gdsCatgcustBlacklistMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(result)){
			GdsCatgCustBlacklistRespDTO gdsCatgCustBlacklistRespDTO =  new GdsCatgCustBlacklistRespDTO();
			ObjectCopyUtil.copyObjValue(result.get(0), gdsCatgCustBlacklistRespDTO, null, false);
			return gdsCatgCustBlacklistRespDTO;
		}
		return null;
	}
	/**
     * 
     * 按条件查询  
     * 分页    
     * reqDTO字段之间是与  字段之内是或  如 id 与 idList  会是 id = 'id' and id in idList 
     */
    @Override
    public PageResponseDTO<GdsCatgCustBlacklistRespDTO> queryGdsCatgcustBlacklistPage(GdsCatgCustBlacklistReqDTO reqDTO) {
        GdsCatgcustBlacklistCriteria example = new GdsCatgcustBlacklistCriteria();
        initExampleCriteria(example.createCriteria(),reqDTO);
        example.setLimitClauseStart(reqDTO.getStartRowIndex());
        example.setLimitClauseCount(reqDTO.getPageSize());
        example.setOrderByClause("CREATE_TIME DESC");
        
        return super.queryByPagination(reqDTO, example, true, new PaginationCallback<GdsCatgcustBlacklist, GdsCatgCustBlacklistRespDTO>() {

            @Override
            public List<GdsCatgcustBlacklist> queryDB(BaseCriteria criteria) {
                return gdsCatgcustBlacklistMapper.selectByExample((GdsCatgcustBlacklistCriteria) criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return gdsCatgcustBlacklistMapper.countByExample((GdsCatgcustBlacklistCriteria) criteria);
            }

            @Override
            public GdsCatgCustBlacklistRespDTO warpReturnObject(GdsCatgcustBlacklist t) {
                GdsCatgCustBlacklistRespDTO gdsCatgCustBlacklistRespDTO =  new GdsCatgCustBlacklistRespDTO();
                ObjectCopyUtil.copyObjValue(t, gdsCatgCustBlacklistRespDTO, null, false);
                return gdsCatgCustBlacklistRespDTO;
            }
        });
    }
    /**
     * 
     * 按条件查询 
     * 列表    
     * reqDTO字段之间是与  字段之内是或  如 id 与 idList  会是 id = 'id' and id in idList 
     */
    @Override
    public List<GdsCatgCustBlacklistRespDTO> queryGdsCatgcustBlacklistList(GdsCatgCustBlacklistReqDTO reqDTO) throws BusinessException {
        GdsCatgcustBlacklistCriteria example = new GdsCatgcustBlacklistCriteria();
        initExampleCriteria(example.createCriteria(),reqDTO);
        example.setOrderByClause("CREATE_TIME DESC");
        
        List<GdsCatgcustBlacklist> result = gdsCatgcustBlacklistMapper.selectByExample(example);
        List<GdsCatgCustBlacklistRespDTO> respResult = new ArrayList<GdsCatgCustBlacklistRespDTO>();
        if(CollectionUtils.isNotEmpty(result)){
            for(GdsCatgcustBlacklist bo : result){
                GdsCatgCustBlacklistRespDTO gdsCatgCustBlacklistRespDTO =  new GdsCatgCustBlacklistRespDTO();
                ObjectCopyUtil.copyObjValue(bo, gdsCatgCustBlacklistRespDTO, null, false);
                respResult.add(gdsCatgCustBlacklistRespDTO);
            }
        }
        return respResult;
    }
    /**
     * 
     * initExampleCriteria:(通过条件查询的条件查询初始化方法). <br/> 
     * reqDTO字段之间是与  字段之内是或  如 id 与 idList  会是 id = 'id' and id in idList
     * @author zhanbh 
     * @param cr
     * @param dto
     * @return 
     * @since JDK 1.6
     */
    private Criteria initExampleCriteria(Criteria cr , GdsCatgCustBlacklistReqDTO dto){
        if(null != dto.getId()){
            cr.andIdEqualTo(dto.getId());
        }
        if(CollectionUtils.isNotEmpty(dto.getIdList())){
            cr.andIdIn(dto.getIdList());
        }
        if(null != dto.getShopId()){
            cr.andShopIdEqualTo(dto.getShopId());
        }
        if(null != dto.getGdsId()){
            cr.andGdsIdEqualTo(dto.getGdsId());
        }
        if(CollectionUtils.isNotEmpty(dto.getGdsIdList())){
            cr.andGdsIdIn(dto.getGdsIdList());
        }
        if(StringUtil.isNotBlank(dto.getStatus())){
            cr.andStatusEqualTo(dto.getStatus());
        }
        return cr;
    }
    /**
     * 
     * addGdsCatgCustBlacklistByOne:(添加单条数据). <br/> 
     * @author zhanbh 
     * @param gdsCatgCustBlacklistReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    private Long addGdsCatgCustBlacklistByOne(GdsCatgCustBlacklistReqDTO reqDTO)
            throws BusinessException {
        GdsCatgcustBlacklist gdsCatgcustBlacklist = new GdsCatgcustBlacklist();
        gdsCatgcustBlacklist.setId(seqGdsCatgCustBlacklist.nextValue());
        ObjectCopyUtil.copyObjValue(reqDTO, gdsCatgcustBlacklist, null, false);
        gdsCatgcustBlacklist.setStatus(GdsConstants.Commons.STATUS_VALID);
        gdsCatgcustBlacklist.setCreateTime(DateUtil.getSysDate());
        gdsCatgcustBlacklist.setCreateStaff(reqDTO.getStaff().getId());
        preInsert(reqDTO, gdsCatgcustBlacklist);
        gdsCatgcustBlacklistMapper.insert(gdsCatgcustBlacklist);
        return gdsCatgcustBlacklist.getId();
    }
}
