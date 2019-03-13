package com.zengshi.ecp.unpf.service.busi.auth.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.unpf.dao.mapper.busi.UnpfShopAuthMapper;
import com.zengshi.ecp.unpf.dao.model.UnpfShopAuth;
import com.zengshi.ecp.unpf.dao.model.UnpfShopAuthCriteria;
import com.zengshi.ecp.unpf.dao.model.UnpfShopAuthCriteria.Criteria;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthRespDTO;
import com.zengshi.ecp.unpf.dubbo.util.UnpfConstants;
import com.zengshi.ecp.unpf.service.busi.auth.interfaces.IUnpfShopAuthSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.json.ParseException;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
* @author  lisp: 
* @date 创建时间：2016年11月8日 上午9:30:20 
* @version 1.0 
* 
*  */
public class UnpfShopAuthSVImpl extends GeneralSQLSVImpl implements IUnpfShopAuthSV {

	@Resource
	private UnpfShopAuthMapper unpfShopAuthMapper;//
	
	
	@Resource(name = "seq_unpf_shop_auth_id")
    private PaasSequence seq_unpf_shop_auth_id;
	
	
	
	/**
	* 对接平台 删除店铺授权
	* 
	* @author lisp
	* @param UnpfShopAuthReqDTO
	* @return 
	* @throws 
	*/
	@Override
	public int deletePlatType4Shop(UnpfShopAuthReqDTO unpfShopAuthReqDTO) throws BusinessException{
		
		UnpfShopAuth unpfShopAuth =  new UnpfShopAuth();
		unpfShopAuth.setUpdateStaff(unpfShopAuthReqDTO.getStaff().getId());
		unpfShopAuth.setUpdateTime(DateUtil.getSysDate());
		unpfShopAuth.setStatus(UnpfConstants.PlatType4Shop.STATUS_0);
		UnpfShopAuthCriteria example = new UnpfShopAuthCriteria();
		Criteria cr = example.createCriteria();
		cr.andIdEqualTo(unpfShopAuthReqDTO.getId());
		cr.andStatusEqualTo(UnpfConstants.PlatType4Shop.STATUS_1);
		
		return unpfShopAuthMapper.updateByExampleSelective(unpfShopAuth, example);		
	}
	

	/**
	* 对接平台 删除店铺授权
	* 
	* @author lisp
	* @param UnpfShopAuthReqDTO
	* @return 
	* @throws 
	*/
	@Override
	public int updatePlatType4Shop(UnpfShopAuthReqDTO unpfShopAuthReqDTO) throws BusinessException{
		
		UnpfShopAuth unpfShopAuth =  new UnpfShopAuth();
		ObjectCopyUtil.copyObjValue(unpfShopAuthReqDTO, unpfShopAuth, null, false);
		unpfShopAuth.setUpdateStaff(unpfShopAuthReqDTO.getStaff().getId());
		unpfShopAuth.setUpdateTime(DateUtil.getSysDate());
		UnpfShopAuthCriteria example = new UnpfShopAuthCriteria();
		Criteria cr = example.createCriteria();
		cr.andIdEqualTo(unpfShopAuthReqDTO.getId());
		cr.andStatusEqualTo(UnpfConstants.PlatType4Shop.STATUS_1);
		
		return unpfShopAuthMapper.updateByExampleSelective(unpfShopAuth, example);		
	}
	
	
	/**
	* 对接平台 店铺授权详情
	* 
	* @author lisp
	* @param UnpfShopAuthReqDTO
	* @return 
	* @throws 
	*/
	@Override
	public UnpfShopAuthRespDTO queryPlatType4ShopById(UnpfShopAuthReqDTO unpfShopAuthReqDTO) throws BusinessException{
		if(unpfShopAuthReqDTO == null){
			return null;
		}
		if(StringUtil.isEmpty(unpfShopAuthReqDTO.getId())){
			return null;
		}
		UnpfShopAuth unpfShopAuth = unpfShopAuthMapper.selectByPrimaryKey(unpfShopAuthReqDTO.getId());
		UnpfShopAuthRespDTO unpfShopAuthRespDTO = new UnpfShopAuthRespDTO();
		ObjectCopyUtil.copyObjValue(unpfShopAuth, unpfShopAuthRespDTO, null, false);
		return unpfShopAuthRespDTO;
	}
	
	
	
	/**
	* 对接平台授权店铺 保存验证 如果验证不通过 返回有值 并且包含不通过原因说明
	* 
	* @author lisp
	* @param UnpfShopAuthReqDTO
	* @return 
	* @throws 
	*/
	@Override
	public UnpfShopAuthReqDTO validPlatType4Shop(UnpfShopAuthReqDTO unpfShopAuthReqDTO) throws BusinessException{
		UnpfShopAuthCriteria example = new UnpfShopAuthCriteria();
		Criteria cr = example.createCriteria();
		cr.andPlatTypeEqualTo(unpfShopAuthReqDTO.getPlatType());
		cr.andShopIdEqualTo(unpfShopAuthReqDTO.getShopId());
		cr.andStatusEqualTo(UnpfConstants.PlatType4Shop.STATUS_1);
		//cr.andStatusEqualTo(UnpfConstants.PlatType4Shop.STATUS_1);
		List<UnpfShopAuth> l = unpfShopAuthMapper.selectByExample(example);
		if(!CollectionUtils.isEmpty(l)){
			return unpfShopAuthReqDTO;
			/*
			for (UnpfShopAuth unpfShopAuth : l) {
				if(unpfShopAuth.getExpiredTime().getTime()>=unpfShopAuthReqDTO.getExpiredTime().getTime()){
				}else{
					UnpfShopAuthReqDTO unpf = new UnpfShopAuthReqDTO();
					unpf.setId(unpfShopAuth.getId());
					this.deletePlatType4Shop(unpf);
				}
			}
		*/}
		
		return null;
	}
	/**
	* 对接平台 添加店铺授权
	* 
	* @author lisp
	* @param UnpfShopAuthReqDTO
	* @return 
	* @throws 
	*/
	@Override
	public void savePlatType4Shop(UnpfShopAuthReqDTO unpfShopAuthReqDTO) throws BusinessException{
		
			if(StringUtil.isBlank(unpfShopAuthReqDTO.getStatus())){
				unpfShopAuthReqDTO.setStatus(UnpfConstants.PlatType4Shop.STATUS_1);
			}
			if(StringUtil.isEmpty(unpfShopAuthReqDTO.getCreateStaff())){
				unpfShopAuthReqDTO.setCreateStaff(unpfShopAuthReqDTO.getStaff().getId());
			}
			if(StringUtil.isEmpty(unpfShopAuthReqDTO.getCreateTime())){
				unpfShopAuthReqDTO.setCreateTime(DateUtil.getSysDate());
			}
			unpfShopAuthReqDTO.setId(seq_unpf_shop_auth_id.nextValue());
			unpfShopAuthReqDTO.setStatus(UnpfConstants.PlatType4Shop.STATUS_1);
			/*unpfShopAuthReqDTO.setCreateStaff(unpfShopAuthReqDTO.getStaff().getId());
			unpfShopAuthReqDTO.setCreateTime(DateUtil.getSysDate());*/
			UnpfShopAuth record =new UnpfShopAuth();
			ObjectCopyUtil.copyObjValue(unpfShopAuthReqDTO, record, null, false);
			unpfShopAuthMapper.insertSelective(record);
	}
	/**
	* 对接平台 店铺授权列表 分页
	* 
	* @author lisp
	* @param UnpfShopAuthReqDTO
	* @return PageResponseDTO<UnpfShopAuthRespDTO>
	* @throws ParseException
	*/
	@Override
 	public PageResponseDTO<UnpfShopAuthRespDTO> queryPlatType4ShopForPage(UnpfShopAuthReqDTO unpfShopAuthReqDTO)
			throws BusinessException {
		
		UnpfShopAuthCriteria example = new UnpfShopAuthCriteria();
		//初始化查询页数 数量
		example.setLimitClauseCount(unpfShopAuthReqDTO.getPageSize());
		example.setLimitClauseStart(unpfShopAuthReqDTO.getStartRowIndex());
		//排序
		example.setOrderByClause("id DESC");
		//初始化查询条件
		this.initPlatType4ShopParm(unpfShopAuthReqDTO, example);
		return super.queryByPagination(unpfShopAuthReqDTO, example, true,
				new PaginationCallback<UnpfShopAuth, UnpfShopAuthRespDTO>() {
					//查询记录集
					@Override
					public List<UnpfShopAuth> queryDB(BaseCriteria example) {
						return unpfShopAuthMapper.selectByExample((UnpfShopAuthCriteria) example);
					}
					//查询总记录数
					@Override
					public long queryTotal(BaseCriteria example) {
						return unpfShopAuthMapper.countByExample((UnpfShopAuthCriteria) example);
					}
					//查询结果转换
					@Override
					public UnpfShopAuthRespDTO warpReturnObject(UnpfShopAuth unpfShopAuth) {
						UnpfShopAuthRespDTO unpfShopAuthRespDTO = new UnpfShopAuthRespDTO();
						ObjectCopyUtil.copyObjValue(unpfShopAuth, unpfShopAuthRespDTO, null, false);
						return unpfShopAuthRespDTO;
					}
					@Override
		            public List<Comparator<UnpfShopAuth>> defineComparators() {
		                List<Comparator<UnpfShopAuth>> lst = new ArrayList<Comparator<UnpfShopAuth>>();
		                lst.add(new Comparator<UnpfShopAuth>() {
		                    @Override
		                    public int compare(UnpfShopAuth o1, UnpfShopAuth o2) {
		                        if (o1.getId() > o2.getId()) {
		                            return -1;
		                        } else if (o1.getId().equals(o2.getId())) {
		                            return 0;
		                        } else {
		                            return 1;
		                        }
		                    }
		                });
		                return lst;
		            }

					
					
		});
	}
	
	private void initPlatType4ShopParm(UnpfShopAuthReqDTO unpfShopAuthReqDTO,UnpfShopAuthCriteria example){
		
		if(unpfShopAuthReqDTO == null){
			return;
		}
		Criteria cr = example.createCriteria();
		if(StringUtil.isNotBlank(unpfShopAuthReqDTO.getAppkey())){
			cr.andAppkeyEqualTo(unpfShopAuthReqDTO.getAppkey());
		}
		if(StringUtil.isNotBlank(unpfShopAuthReqDTO.getPlatType())){
			cr.andPlatTypeEqualTo(unpfShopAuthReqDTO.getPlatType());
		}
		if(StringUtil.isNotBlank(unpfShopAuthReqDTO.getStatus())){
			cr.andStatusEqualTo(unpfShopAuthReqDTO.getStatus());
		}
		if(!StringUtil.isEmpty(unpfShopAuthReqDTO.getShopId())){
			cr.andShopIdEqualTo(unpfShopAuthReqDTO.getShopId());
		}
		if(!StringUtil.isEmpty(unpfShopAuthReqDTO.getExpiredTime())){
			cr.andExpiredTimeGreaterThanOrEqualTo(unpfShopAuthReqDTO.getExpiredTime());
		}else{
			if(UnpfConstants.PlatType4Shop.STATUS_1.equals(unpfShopAuthReqDTO.getStatus())){
				cr.andExpiredTimeGreaterThanOrEqualTo(DateUtil.getSysDate());
			}
		}
		if(unpfShopAuthReqDTO.getId()!=null){
			cr.andIdEqualTo(unpfShopAuthReqDTO.getId());
		}
		
	}


	@Override
	public List<String> queryPlatType4ShopByExample(UnpfShopAuthReqDTO unpfShopAuthReqDTO)
			throws BusinessException {
		List<String> platTypes = new ArrayList<String>();
		UnpfShopAuthCriteria example = new UnpfShopAuthCriteria();
		example.setOrderByClause("plat_type");
		//初始化查询条件
		this.initPlatType4ShopParm(unpfShopAuthReqDTO, example);
		List<UnpfShopAuth> result = unpfShopAuthMapper.selectByExample(example);
		if(!CollectionUtils.isEmpty(result)){
			for (UnpfShopAuth unpfShopAuth : result) {
				if(StringUtil.isNotBlank(unpfShopAuth.getPlatType())&&!platTypes.contains(unpfShopAuth.getPlatType())){
					platTypes.add(unpfShopAuth.getPlatType());
				}
			}
		}
		return platTypes;
	}


}


