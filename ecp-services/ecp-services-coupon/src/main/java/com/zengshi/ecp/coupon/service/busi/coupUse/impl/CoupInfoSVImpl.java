package com.zengshi.ecp.coupon.service.busi.coupUse.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import com.zengshi.ecp.cms.dubbo.dto.CmsFloorCouponReqDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorCouponRSV;
import com.zengshi.ecp.coupon.dao.mapper.busi.CoupInfoMapper;
import com.zengshi.ecp.coupon.dao.model.CoupInfo;
import com.zengshi.ecp.coupon.dao.model.CoupInfoCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupInfoCriteria.Criteria;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupFullLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupInfoReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupFullLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupInfoRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupParamRespDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.Converter;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.coupon.service.busi.combination.interfaces.ICoupCreateInitSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupCatgLimitSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupFullLimitSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupInfoSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupRuleSV;
import com.zengshi.ecp.coupon.service.common.interfaces.ICoupParamSV;
import com.zengshi.ecp.coupon.service.util.CoupUtil;
import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;

import scala.math.Ordering.StringOrdering;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-coupon-server <br>
 * Description: <br>
 * Date:2015-10-9下午12:57:05  <br>
 * 
 * @author huanghe5
 * @version  
 * @since JDK 1.7
 */
public class CoupInfoSVImpl extends GeneralSQLSVImpl implements ICoupInfoSV {
	
	@Resource
    private ICoupCreateInitSV coupCreateInitSV;
	
	@Resource
    private ICoupParamSV coupParamSV;
	
	@Resource
    private CoupInfoMapper coupInfoMapper;
	
	@Resource
    private Converter<CoupInfo, CoupInfoRespDTO> coupInfoRespDTOConverter;
	
	@Resource
    private Converter<CoupInfoReqDTO,CoupInfo> coupInfoConverter;
	
	@Resource
    private ICoupCatgLimitSV coupCatgLimitSV;
	
	@Resource
    private ICoupFullLimitSV coupFullLimitSV;
	
	@Resource
    private IGdsCategoryRSV gdsCategoryRSV;
	
	@Resource
    private ICmsFloorCouponRSV cmsFloorCouponRSV;
	
	/**
     * 优惠券信息查询 分页
     * 
     * @param CoupInfoReqDTO
     * @return
     * @throws BusinessException
     * @author huanghe5
     */
	@Override
	public PageResponseDTO<CoupInfoRespDTO> queryCoupInfoPage(
			CoupInfoReqDTO coupInfoReqDTO) throws BusinessException {

		// 查询初始化查询条件
		CoupInfoCriteria example = new CoupInfoCriteria();
		example.setLimitClauseCount(coupInfoReqDTO.getPageSize());
		example.setLimitClauseStart(coupInfoReqDTO.getStartRowIndex());
		
		// 排序
		example.setOrderByClause("id desc");
		coupCreateInitSV.initCoupInfo(example, coupInfoReqDTO);

		// 返回查询分页结果集
		return super.queryByPagination(coupInfoReqDTO, example, true,
				new PaginationCallback<CoupInfo, CoupInfoRespDTO>() {
					// 查询记录集
					@Override
					public List<CoupInfo> queryDB(BaseCriteria example) {

						return coupInfoMapper
								.selectByExample((CoupInfoCriteria) example);
					}

					// 查询总记录数
					@Override
					public long queryTotal(BaseCriteria example) {

						return coupInfoMapper.countByExample(
								(CoupInfoCriteria) example);
					}

					// 查询结果转换
					@Override
					public CoupInfoRespDTO warpReturnObject(CoupInfo t) {
						return coupInfoRespDTOConverter.convert(t);
					}
				});
	}
    /**
     * 优惠券小类查询
     * @param coupInfoReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public CoupInfoRespDTO queryCoupInfoById(CoupInfoReqDTO coupInfoReqDTO) throws BusinessException{
        
    	return coupInfoRespDTOConverter.convert(coupInfoMapper.selectByPrimaryKey(coupInfoReqDTO.getId()));
    }
    
    /**
     * 
     * queryCoupById:查询有效的优惠券信息Bean. <br/> 
     * @author huanghe5
     * @param coupInfoReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public CoupInfoRespDTO queryCoupById(CoupInfoReqDTO coupInfoReqDTO) throws BusinessException{
        
        coupInfoReqDTO.setStatus(coupInfoReqDTO.getStatus());
        
        CoupInfoReqDTO coupInfoBean = new CoupInfoReqDTO();
        coupInfoBean.setId(coupInfoReqDTO.getId());
        coupInfoBean.setStatus(coupInfoReqDTO.getStatus());
        List<CoupInfoRespDTO> coupInfoBeans= this.queryCoupList(coupInfoBean);
        if(CollectionUtils.isNotEmpty(coupInfoBeans)){
            CoupInfoRespDTO bean = new CoupInfoRespDTO();
            for (CoupInfoRespDTO coupInfoRespDTO : coupInfoBeans) {
                return coupInfoRespDTO;
            }
        }
        
        return null;
    }
    

	/**
	 * 
	 * TODO 优惠券保存以及编辑. 
	 * Date:2015-10-26下午3:07:30  <br>
	 * @author huanghe5
	 * @return 0:表示优惠券  否则是 优惠码 
	 * @see com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupInfoSV#saveCoup(com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO)
	 */
	@Override
	public String saveCoup(CoupReqDTO coupReqDTO) throws BusinessException {
		if(coupReqDTO==null){
			return null;
		}
		CoupInfoReqDTO coupInfoReqDTO = coupReqDTO.getCoupInfoReqDTO();
	    String status = coupInfoReqDTO.getStatus();
		if (CouponConstants.CoupSys.edit_EDIT.equals(coupReqDTO.getEdit())) {
			//查询原来的优惠券名字和修改的优惠券名字是否一致
			CoupInfoReqDTO coupInfoReqDTO2 =  new CoupInfoReqDTO();
	        coupInfoReqDTO2.setId(coupInfoReqDTO.getId());
	        if(CouponConstants.CoupInfo.STATUS_1.equals(status)){
	        	//因为草稿状态的时候用户点提交传进来的状态可能是有效的
                coupInfoReqDTO2.setStatus(CouponConstants.CoupInfo.STATUS_2);
            }else{
                coupInfoReqDTO2.setStatus(status);
            }
	        CoupInfoRespDTO bean = this.queryCoupById(coupInfoReqDTO2);
	        if(bean == null){
	        	throw new BusinessException("coupon.error.450038");
	        }
	        //优惠券名字和原来名字不同则要验证优惠券名称
			if(coupInfoReqDTO!=null&&!coupInfoReqDTO.getCoupName().equals(bean.getCoupName())){
			    boolean s=this.getCoupName(coupInfoReqDTO);
		        if(!s){
		            throw new BusinessException("coupon.error.450033");
		        }
			}
			//优惠券名字和原来名字不同则要验证优惠券名称
			if(StringUtil.isNotBlank(coupInfoReqDTO.getCoupCode())){
				if(StringUtil.isNotBlank(bean.getCoupCode())){
					if(!coupInfoReqDTO.getCoupCode().equals(bean.getCoupCode())){
						boolean sCode=this.getCoupCode(coupInfoReqDTO);
						if(!sCode){
							throw new BusinessException("coupon.error.450042");
						}
					}
				}else{
					boolean sCode=this.getCoupCode(coupInfoReqDTO);
					if(!sCode){
						throw new BusinessException("coupon.error.450042");
					}
				}
			}
			//删除原来数据
			this.deleteBatchCoup(coupReqDTO);
			//删除优惠券对应的限制规则表信息
			this.dealCoupUseRuleInfo(coupInfoReqDTO, coupReqDTO, CouponConstants.CoupSys.edit_DELETE);
		}
		if(StringUtil.isBlank(coupInfoReqDTO.getIfBack())){
			coupInfoReqDTO.setIfBack(CouponConstants.CoupInfo.IF_BACK_0);
		}
		if (CouponConstants.CoupSys.edit_ADD.equals(coupReqDTO.getEdit())) {
		    boolean s=this.getCoupName(coupInfoReqDTO);
	        if(!s){
	            throw new BusinessException("coupon.error.450033");
	        }
			Boolean sCode =this.getCoupCode(coupInfoReqDTO);
				if(!sCode){
					throw new BusinessException("coupon.error.450042");
			}
		}
		
		
		// 1.添加到优惠券信息表
		String coupCode = coupCreateInitSV.saveCoupInfo(coupInfoReqDTO);
		String ifCode = coupInfoReqDTO.getIfCode();
		// 2.处理优惠券使用规则信息，新增/修改/删除
		this.dealCoupUseRuleInfo(coupInfoReqDTO,coupReqDTO,coupReqDTO.getEdit());
		
		if (!StringUtil.isEmpty(coupInfoReqDTO.getGainRuleCode())) {// 领取规则
			Map<String, Object> map = (Map<String, Object>) JSON.parse(coupInfoReqDTO.getGainRuleCode());
			
			if (map != null && map.entrySet().iterator() != null) {
				Iterator<Entry<String, Object>> beans = map.entrySet()
						.iterator();
				if(beans != null){
					String key1 = null;
					while (beans.hasNext()) {
						Entry<String, Object> ebean = beans.next();
						key1 = ebean.getKey().toString();
						if(beans.next().getKey() == null){
							continue;
						}
						//去掉多余的spring id(因为领取的spring id可能出现多个重复)
						String key2=beans.next().getKey().toString();
						if(CouponConstants.CoupSys.coupGetLimitSV.equals(key2)){
							if(key1.equals(key2)){
								continue;
							}
							key1 = key2;
						}
						// 获取spring id
						CoupParamRespDTO coupParamRespDTO = coupParamSV
								.queryCoupParamByCode(ebean.getKey().toString());
						if (coupParamRespDTO != null) {
							// 通过spring id找到该类的方法
							ICoupRuleSV coupRuleSV = EcpFrameContextHolder.getBean(
									coupParamRespDTO.getServiceId(),
									ICoupRuleSV.class);
							// 使用方法
							if (coupRuleSV != null) {
								if (CouponConstants.CoupSys.edit_ADD.equals(coupReqDTO.getEdit())) {// 新增
									coupRuleSV.save(coupReqDTO);
								} else if (CouponConstants.CoupSys.edit_EDIT.equals(coupReqDTO.getEdit())) {// 修改
									coupRuleSV.update(coupReqDTO);
								}
							}
						}
					}
				}
			}
		}
		if (CouponConstants.CoupInfo.IF_CODE_1.equals(ifCode)&&CouponConstants.CoupSys.status_1.equals(status)) {
            return coupCode;
        }
		return null;
	}
	
	//处理优惠券使用规则信息
	private void dealCoupUseRuleInfo(CoupInfoReqDTO coupInfoReqDTO, CoupReqDTO coupReqDTO,String actionType)
	{
	       // 根据查询到参数添加限制规则数据
        if (!StringUtil.isEmpty(coupInfoReqDTO.getUseRuleCode())) {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) JSON
                    .parse(coupInfoReqDTO.getUseRuleCode());
            if (map != null && map.entrySet().iterator() != null) {
                Iterator<Entry<String, Object>> beans = map.entrySet()
                        .iterator();
                Set<String> usedbean = new HashSet<String>();
                while (beans.hasNext()) {
                    Entry<String, Object> ebean = beans.next();
                    // 获取spring id
                    CoupParamRespDTO coupParamRespDTO = coupParamSV
                            .queryCoupParamByCode(ebean.getKey().toString());
                    if (coupParamRespDTO != null) {
                        //过滤重复的bean
                        if (StringUtil.isBlank(coupParamRespDTO.getServiceId())||usedbean.contains(coupParamRespDTO.getServiceId())) {
                            continue;
                        }
                        else{
                            usedbean.add(coupParamRespDTO.getServiceId());
                        }
                        // 通过spring id找到该类的方法
                        ICoupRuleSV coupRuleSV = EcpFrameContextHolder.getBean(
                                coupParamRespDTO.getServiceId(),
                                ICoupRuleSV.class);
                        // 使用方法
                        if (coupRuleSV != null) {
                            switch (actionType) {
                            case CouponConstants.CoupSys.edit_ADD:
                                coupRuleSV.save(coupReqDTO);
                                break;
                            case CouponConstants.CoupSys.edit_EDIT:
                                coupRuleSV.update(coupReqDTO);
                                break;
                            case CouponConstants.CoupSys.edit_DELETE:
                                coupRuleSV.deleteStatus(coupReqDTO);
                                break;
                            default:
                                break;
                            }
                        }
                    }
                }
            }
        } 
	}
	/**
	 * 
	 * getCoupName:校验小类名称是否重复. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupInfoReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	private boolean getCoupName(CoupInfoReqDTO coupInfoReqDTO)throws BusinessException {
		
		CoupInfoReqDTO coupInfoBean = new CoupInfoReqDTO();
		coupInfoBean.setCoupName(coupInfoReqDTO.getCoupName().trim());
		coupInfoBean.setStatus(CouponConstants.CoupInfo.STATUS_1);
		List<CoupInfoRespDTO> coupInfoBeans= this.queryCoupList(coupInfoBean);
		
		if(CollectionUtils.isNotEmpty(coupInfoBeans)){
			return false;
		}
		coupInfoBean.setStatus(CouponConstants.CoupInfo.STATUS_2);
        coupInfoBeans= this.queryCoupList(coupInfoBean);
        if(CollectionUtils.isNotEmpty(coupInfoBeans)){
            return false;
        }
		
		return true;
	}
	
	
	/**
	 * 
	 * getCoupCode:校验优惠码是否重复. <br/> 
	 * 
	 * @author lisp
	 * @param coupInfoReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public boolean getCoupCode(CoupInfoReqDTO coupInfoReqDTO)throws BusinessException {
		
		CoupInfoCriteria example = new CoupInfoCriteria();
        Criteria cr = example.createCriteria();
        if(StringUtil.isNotBlank(coupInfoReqDTO.getCoupCode())){
        	cr.andCoupCodeEqualTo(coupInfoReqDTO.getCoupCode());
        }else{
        	return true;
        }
        List<CoupInfo> coupInfoList = coupInfoMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(coupInfoList)&&coupInfoList.size()>0){
        	return false;
        }
        return true;
		
	}
	
	
    /**
     * 
     * deleteCoup:优惠券信息批量删除. <br/> 
     * @author huanghe5
     * @param coupReqDTO
     * @throws BusinessException 
     * @since JDK 1.7
     */
	@Override
	public void deleteBatchCoup(CoupReqDTO coupReqDTO) throws BusinessException {
		
		List<CoupInfoReqDTO> coupInfoReqDTOs = coupReqDTO.getCoupInfoReqDTOs();
		if(CollectionUtils.isNotEmpty(coupInfoReqDTOs)){
			for (CoupInfoReqDTO coupInfoReqDTO : coupInfoReqDTOs) {
			    CoupInfoReqDTO coupInfoReqDTO2= new CoupInfoReqDTO();
			    coupInfoReqDTO2.setId(coupInfoReqDTO.getId());
				if(coupInfoReqDTO != null){
					if(CouponConstants.CoupSys.edit_EDIT.equals(coupReqDTO.getEdit())){
					    coupInfoReqDTO2.setStatus(CouponConstants.CoupInfo.STATUS_4);
					}else{
					    coupInfoReqDTO2.setStatus(CouponConstants.CoupInfo.STATUS_3);
					}
					coupInfoReqDTO2.getStaff().setId(coupReqDTO.getStaff().getId());
					coupCreateInitSV.deleteCoup(coupInfoReqDTO2);
				}
			}
		}
	}
	
	/**
	 * 
	 * TODO 批量生失效接口. 
	 * Date:2015-10-26下午8:08:04  <br>
	 * @author huanghe5
	 * @see com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupInfoSV#ifInvalidCoup(com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO)
	 */
	@Override
	public void ifInvalidCoup(CoupReqDTO coupReqDTO) throws BusinessException {
		
		List<CoupInfoReqDTO> coupInfoReqDTOs = coupReqDTO.getCoupInfoReqDTOs();
		if(CollectionUtils.isNotEmpty(coupInfoReqDTOs)){
			for (CoupInfoReqDTO coupInfoReqDTO : coupInfoReqDTOs) {
				if(coupInfoReqDTO != null){
					Long id = coupInfoReqDTO.getId();
 					//0 失效 1 生效
					if(CouponConstants.CoupSys.status_1.equals(coupReqDTO.getEdit())){
					
						coupInfoReqDTO.setStatus(CouponConstants.CoupInfo.STATUS_1);//生效
						
					}else if(CouponConstants.CoupSys.status_0.equals(coupReqDTO.getEdit())){
						
						coupInfoReqDTO.setStatus(CouponConstants.CoupInfo.STATUS_0);//失效
					}
					coupCreateInitSV.deleteCoup(coupInfoReqDTO);
					if(CouponConstants.CoupSys.status_0.equals(coupReqDTO.getEdit())){
						CmsFloorCouponReqDTO cmsFloorCouponBean  = new CmsFloorCouponReqDTO();
						cmsFloorCouponBean.setCouponId(id);
						cmsFloorCouponRSV.deleteCmsFloorCoupon(cmsFloorCouponBean);
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * queryCoupInfoPage:优惠券小类查询. <br/> 
	 * 
	 * @author huanghe5 
	 * @param coupInfoReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public List<CoupInfoRespDTO> queryCoupInfoList(CoupInfoReqDTO coupInfoReqDTO)
			throws BusinessException {
		CoupInfoCriteria example = new CoupInfoCriteria();
		coupCreateInitSV.initCoupInfo(example, coupInfoReqDTO);
		if(StringUtil.isBlank(coupInfoReqDTO.getStatus())){
			coupInfoReqDTO.setStatus(CouponConstants.CoupSys.status_1);
		}
		List<CoupInfo> beans = coupInfoMapper.selectByExample(example);
		
		if (CollectionUtils.isEmpty(beans)) {
            return null;
        }
		
		List<CoupInfoRespDTO> reList = new ArrayList<CoupInfoRespDTO>();
		for (CoupInfo coupInfo : beans) {
			reList.add(coupInfoRespDTOConverter.convert(coupInfo));
		}
		
		return reList;
	}
	
	/**
     * 
     * queryCoupInfoPage:查询优惠券名称. <br/> 
     * 
     * @author lisp 
     * @param queryCoupInfoByCoupCode
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
	@Override
	public CoupInfoRespDTO queryCoupInfoByCoupCode(CoupInfoReqDTO coupInfoReqDTO)
			throws BusinessException {
		CoupInfoRespDTO coupInfoRespDTO  = null;
		CoupInfoCriteria example = new CoupInfoCriteria();
        Criteria cr = example.createCriteria();
        if(StringUtil.isNotBlank(coupInfoReqDTO.getCoupCode())){
        	cr.andCoupCodeEqualTo(coupInfoReqDTO.getCoupCode());
        }else{
        	return null;
        }
        cr.andStatusEqualTo(CouponConstants.CoupInfo.STATUS_1);
        List<CoupInfo> coupInfoList = coupInfoMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(coupInfoList)&&coupInfoList.size()>0){
        	CoupInfo coupInfo =coupInfoList.get(0); 
        	//固定时间类型的优惠券
        	if(CouponConstants.CoupInfo.EFF_TYPE_1.equals(coupInfo.getEffType())){
        		if (CoupUtil.timestampToDate(DateUtil.getSysDate()).getTime() < coupInfo.getInactiveTime().getTime()
        				&& CoupUtil.timestampToDate(DateUtil.getSysDate()).getTime() > coupInfo.getActiveTime().getTime()) {
                		coupInfoRespDTO = coupInfoRespDTOConverter.convert(coupInfo);
        			}
        	}else if(CouponConstants.CoupInfo.EFF_TYPE_0.equals(coupInfo.getEffType())){//浮动时间类型的优惠券
        		//直接使用优惠码,该优惠券未领取或者派送
        		if(StringUtil.isEmpty(coupInfo.getInactiveTime())||StringUtil.isEmpty(coupInfo.getActiveTime())){
        			coupInfoRespDTO = coupInfoRespDTOConverter.convert(coupInfo);
        		}else{//使用领取或者是派送的优惠券
        			if (CoupUtil.timestampToDate(DateUtil.getSysDate()).getTime() < coupInfo.getInactiveTime().getTime()
            				&& CoupUtil.timestampToDate(DateUtil.getSysDate()).getTime() > coupInfo.getActiveTime().getTime()) {
                		coupInfoRespDTO = coupInfoRespDTOConverter.convert(coupInfo);
        			}
        		}
        	}
        	
        }
		return coupInfoRespDTO;
	}
	/**
     * 
     * queryCoupInfoPage:查询优惠券名称. <br/> 
     * 
     * @author huanghe5 
     * @param coupInfoReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    private List<CoupInfoRespDTO> queryCoupList(CoupInfoReqDTO coupInfoReqDTO)
            throws BusinessException {
        CoupInfoCriteria example = new CoupInfoCriteria();
        Criteria cr = example.createCriteria();
        // 优惠券名称 (精确查询)
        if (!StringUtil.isEmpty(coupInfoReqDTO.getCoupName())) {
            cr.andCoupNameEqualTo(coupInfoReqDTO.getCoupName());

        }
        if (coupInfoReqDTO.getId()!=null) {
            cr.andIdEqualTo(coupInfoReqDTO.getId());

        }
        if(StringUtil.isBlank(coupInfoReqDTO.getStatus())){
            cr.andStatusEqualTo(CouponConstants.CoupSys.status_1);
            coupInfoReqDTO.setStatus(CouponConstants.CoupSys.status_1);
        }else{
            cr.andStatusEqualTo(coupInfoReqDTO.getStatus());
        }
        List<CoupInfo> beans = coupInfoMapper.selectByExample(example);
        
        if (CollectionUtils.isEmpty(beans)) {
            return null;
        }
        
        List<CoupInfoRespDTO> reList = new ArrayList<CoupInfoRespDTO>();
        for (CoupInfo coupInfo : beans) {
            reList.add(coupInfoRespDTOConverter.convert(coupInfo));
        }
        
        return reList;
    }
	
	/**
	 * 
	 * queryCoupInfoList:根据ID 查询到小类信息. <br/> 
	 * 
	 * @author huanghe5 
	 * @param coupInfoReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public List<CoupInfoRespDTO> queryCoupInfoList(List<Long> coupInfoReqBeans) throws BusinessException {
		List<CoupInfoRespDTO> coupInfoBeans = new ArrayList<CoupInfoRespDTO>();
		for (Long long1 : coupInfoReqBeans) {
			CoupInfoReqDTO coupInfoReqDTO = new CoupInfoReqDTO();
			coupInfoReqDTO.setId(long1);
			coupInfoReqDTO.setStatus(CouponConstants.CoupSys.status_1);
			List<CoupInfoRespDTO> beans = this.queryCoupInfoList(coupInfoReqDTO);
			if(CollectionUtils.isEmpty(beans)){
				continue;
			}
			if (beans.size() == 1) {
				CoupInfoRespDTO bean = beans.get(0);
				if(CouponConstants.CoupInfo.EFF_TYPE_1.equals(bean.getEffType())){
					if (CoupUtil.timestampToDate(DateUtil.getSysDate()).getTime() > bean.getInactiveTime().getTime()
							|| CoupUtil.timestampToDate(DateUtil.getSysDate()).getTime() < bean.getActiveTime()
									.getTime()) {
						continue;
					}
				}
				// 满减
				if(StringUtil.isNotBlank(bean.getUseRuleCode())){
					if (bean.getUseRuleCode().contains(CouponConstants.CoupPara.RULE_CODE_140)) {
						String s = "";
						CoupFullLimitReqDTO coupFullLimitReqDTO = new CoupFullLimitReqDTO();
						coupFullLimitReqDTO.setCoupId(bean.getId());
						List<CoupFullLimitRespDTO> fullBean = coupFullLimitSV.queryCoupFullList(coupFullLimitReqDTO);
						if (CollectionUtils.isNotEmpty(fullBean)) {
							for (CoupFullLimitRespDTO coupFullLimitRespDTO2 : fullBean) {
								if (CouponConstants.CoupFullLimit.TYPE_1.equals(coupFullLimitRespDTO2.getType())) {
									if (StringUtil.isNotEmpty(bean.getCoupValue()) && bean.getCoupValue() > 0) {
//										long coupValue = bean.getCoupValue() / 100;
//										s = coupFullLimitRespDTO2.getSumLimit() / 100 + "-" + coupValue;
										s = "满"+coupFullLimitRespDTO2.getSumLimit() / 100+"元使用";
										bean.setConditionsShow(s);
									}
								}else{
									s = "满"+coupFullLimitRespDTO2.getAmount()+"件使用";
									bean.setConditionsShow(s);
								}
							}
						}
					}else if (bean.getUseRuleCode().contains(CouponConstants.CoupPara.RULE_CODE_110)||bean.getUseRuleCode().contains(CouponConstants.CoupPara.RULE_CODE_130)) {
						bean.setConditionsShow(CouponConstants.CoupElse.e4);
					}else {
						bean.setConditionsShow(CouponConstants.CoupElse.e3);
					}
				}else {
					bean.setConditionsShow(CouponConstants.CoupElse.e3);
				}
				coupInfoBeans.add(bean);
			}else {
				continue;
			}
		}
		return coupInfoBeans;
	}
}
