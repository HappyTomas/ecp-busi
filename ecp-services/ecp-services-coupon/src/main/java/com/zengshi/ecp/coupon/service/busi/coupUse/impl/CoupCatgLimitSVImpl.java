package com.zengshi.ecp.coupon.service.busi.coupUse.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.coupon.dao.mapper.busi.CoupCatgLimitMapper;
import com.zengshi.ecp.coupon.dao.model.CoupCatgLimit;
import com.zengshi.ecp.coupon.dao.model.CoupCatgLimitCriteria;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupCatgLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupCheckParmReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupCatgLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupLimitCheckRespDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.Converter;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.coupon.service.busi.combination.interfaces.ICoupCreateInitSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupCatgLimitSV;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2CatgReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
/**
 * Title: ECP <br>
 * Project Name:ecp-services-coupon-server <br>
 * Description: <br>
 * Date:2015-10-16上午11:19:43  <br>
 * 
 * @author huanghe5
 * @version  
 * @since JDK 1.7
 */
public class CoupCatgLimitSVImpl extends GeneralSQLSVImpl implements ICoupCatgLimitSV{

	@Resource(name = "seq_coup_catg_limit_id")
    private PaasSequence seq_coup_catg_limit_id;
	@Resource
	private Converter<CoupCatgLimitReqDTO,CoupCatgLimit> coupCatgLimitConverter;
	@Resource
	private Converter<CoupCatgLimit, CoupCatgLimitRespDTO> coupCatgLimitRespDTOConverter;
	@Resource
	private CoupCatgLimitMapper coupCatgLimitMapper;
	@Resource
    private ICoupCreateInitSV coupCreateInitSV;
	@Resource
	private IGdsInfoQueryRSV gdsInfoQueryRSV;
	
	@Resource
	private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;
	
	/**
	 * 
	 * TODO 新增优惠券品类限制信息. 
	 * Date:2015-10-17下午2:21:04  <br>
	 * @author huanghe5
	 * @see com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupRuleSV#save(com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO)
	 */
	@Override
	public void save(CoupReqDTO coupReqDTO) throws BusinessException {
	
		List<CoupCatgLimitReqDTO> coupGatgLimitReqDTOs = coupReqDTO.getCoupCatgLimitReqDTOs();
		if(CollectionUtils.isNotEmpty(coupGatgLimitReqDTOs)){
			for (CoupCatgLimitReqDTO coupCatgLimitReqDTO : coupGatgLimitReqDTOs) {
				//优惠券品类限制信息入参
				coupCatgLimitReqDTO.setCoupId(coupReqDTO.getCoupInfoReqDTO().getId());//优惠券信息ID
				coupCatgLimitReqDTO.setId(seq_coup_catg_limit_id.nextValue());
				coupCatgLimitReqDTO.setCreateStaff(coupReqDTO.getStaff().getId());
				coupCatgLimitReqDTO.setCreateTime(DateUtil.getSysDate());
				//如果是 编辑
				if(CouponConstants.CoupSys.edit_EDIT.equals(coupReqDTO.getEdit())){
					coupCatgLimitReqDTO.setUpdateStaff(coupReqDTO.getStaff().getId());
					coupCatgLimitReqDTO.setUpdateTime(DateUtil.getSysDate());
				}
				if(StringUtil.isEmpty(coupCatgLimitReqDTO.getStatus())){
				    coupCatgLimitReqDTO.setStatus(CouponConstants.CoupSys.status_1);
	             }
				CoupCatgLimit coupCatgLimit = coupCatgLimitConverter.convert(coupCatgLimitReqDTO);
				coupCatgLimitMapper.insert(coupCatgLimit);
			}
		}
	}
	
	/**
	 * 
	 * TODO 修改优惠品类限制相关信息. 
	 * Date:2015-10-17下午2:32:39  <br>
	 * @author huanghe5
	 * @see com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupRuleSV#update(com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO)
	 */
	@Override
	public void update(CoupReqDTO coupReqDTO) throws BusinessException {
		
		//逻辑删除此coup_id的相关信息
		this.deleteStatus(coupReqDTO);
		//设置编辑
		coupReqDTO.setEdit(CouponConstants.CoupSys.edit_EDIT);
		//添加此coup_id的相关信息
		this.save(coupReqDTO);
	}
	
	/**
	 * 
	 * TODO 逻辑删除优惠券品类限制信息. 
	 * Date:2015-10-17下午2:22:13  <br>
	 * @author huanghe5
	 * @see com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupRuleSV#deleteStatus(com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO)
	 */
	@Override
	public void deleteStatus(CoupReqDTO coupReqDTO) throws BusinessException {
		
		CoupCatgLimit bean = new CoupCatgLimit();
		bean.setStatus(CouponConstants.CoupSys.status_0);//失效
		CoupCatgLimitCriteria example = new CoupCatgLimitCriteria();
		CoupCatgLimitCriteria.Criteria cr = example.createCriteria();
		if (coupReqDTO.getCoupInfoReqDTO().getId() != null && coupReqDTO.getCoupInfoReqDTO().getId() > 0) {
			cr.andCoupIdEqualTo(coupReqDTO.getCoupInfoReqDTO().getId());// 优惠券信息ID
			
			coupCatgLimitMapper.updateByExampleSelective(bean, example);
		}else if(coupReqDTO.getCoupCatgLimitReqDTO().getCoupId()!=null && coupReqDTO.getCoupCatgLimitReqDTO().getCoupId()>0){
			cr.andCoupIdEqualTo(coupReqDTO.getCoupCatgLimitReqDTO().getCoupId());// 优惠券信息ID
			
			coupCatgLimitMapper.updateByExampleSelective(bean, example);
		}
	
	}
	
	/**
	 * 查询品类限制表.  
	 * 
	 * @author huanghe5 
	 * @param CoupCatgLimitReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public List<CoupCatgLimitRespDTO> queryCoupCatgList(
			CoupCatgLimitReqDTO coupCatgLimitReqDTO) throws BusinessException {
		
		CoupCatgLimitCriteria example = new CoupCatgLimitCriteria();
		coupCreateInitSV.initCoupCatgLimit(coupCatgLimitReqDTO, example);
		if(StringUtil.isBlank(coupCatgLimitReqDTO.getStatus())){
			coupCatgLimitReqDTO.setStatus(CouponConstants.CoupSys.status_1);
		}
		List<CoupCatgLimit> beans = coupCatgLimitMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(beans)) {
            return null;
        }
		List<CoupCatgLimitRespDTO> reList = new ArrayList<CoupCatgLimitRespDTO>();
		for (CoupCatgLimit coupCatgLimit : beans) {
			reList.add(coupCatgLimitRespDTOConverter.convert(coupCatgLimit));
		}
		
		return reList;
	}

	/**
	 * 
	 * checkCoupLimit:下单时校验优惠券限制条件. <br/> 
	 * 
	 * @author huanghe5
	 * @param ordCartCommRequest
	 * @param coupId
	 * @return Judge : 1:通过 0:不通过  2:多个单品组合
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public CoupLimitCheckRespDTO checkCoupLimit(ROrdCartItemCommRequest ordCartItemCommRequest,
			CoupCheckParmReqDTO coupCheckParmReqDTO,CoupLimitCheckRespDTO coupLimitCheckRespDTO) throws BusinessException {
	    coupLimitCheckRespDTO.setJudge(CouponConstants.CoupSys.judge_0);
	    coupLimitCheckRespDTO.setCoupId(coupCheckParmReqDTO.getCoupId());
		coupLimitCheckRespDTO.setGdsId(ordCartItemCommRequest.getGdsId());
		coupLimitCheckRespDTO.setSkuId(ordCartItemCommRequest.getSkuId());
		CoupCatgLimitReqDTO coupCatgLimitReqDTO = new CoupCatgLimitReqDTO();
		coupCatgLimitReqDTO.setCoupId(coupCheckParmReqDTO.getCoupId());
		coupCatgLimitReqDTO.setStatus(CouponConstants.CoupSys.status_1);
		//根据coupId查询限制规则信息
		List<CoupCatgLimitRespDTO> bean = this.queryCoupCatgList(coupCatgLimitReqDTO);
		if(CollectionUtils.isNotEmpty(bean)){
			//校验入参信息是否符合黑名单信息
			for (CoupCatgLimitRespDTO coupCatgLimitRespDTO : bean) {
				if(CouponConstants.CoupCatgLimit.CATEGORY_TYPE_0.equals(coupCatgLimitRespDTO.getCategoryType())){
					if(ordCartItemCommRequest.getSkuId().equals(coupCatgLimitRespDTO.getSkuId())){
						coupLimitCheckRespDTO.setJudge(CouponConstants.CoupSys.judge_1);
					}
				}else if(CouponConstants.CoupCatgLimit.CATEGORY_TYPE_1.equals(coupCatgLimitRespDTO.getCategoryType())){
					if(ordCartItemCommRequest.getGdsId().equals(coupCatgLimitRespDTO.getGdsId())){
						coupLimitCheckRespDTO.setJudge(CouponConstants.CoupSys.judge_1);
					}
				}else if(CouponConstants.CoupCatgLimit.CATEGORY_TYPE_2.equals(coupCatgLimitRespDTO.getCategoryType())){
					//判断这商品是否属于此分类
					GdsGds2CatgReqDTO catgReqDTO=new GdsGds2CatgReqDTO();
					catgReqDTO.setCatgCode(coupCatgLimitRespDTO.getCatgCode());
					catgReqDTO.setGdsId(ordCartItemCommRequest.getGdsId());
					boolean isBelong=gdsInfoQueryRSV.isBelongToCategory(catgReqDTO);
					if(isBelong){
						coupLimitCheckRespDTO.setJudge(CouponConstants.CoupSys.judge_1);
					}
				}
			}
			if(StringUtil.isBlank(coupLimitCheckRespDTO.getJudge())){
				coupLimitCheckRespDTO.setJudge(CouponConstants.CoupSys.judge_0);
			}
			return coupLimitCheckRespDTO;
		}
		coupLimitCheckRespDTO.setJudge(CouponConstants.CoupSys.judge_0);
		return coupLimitCheckRespDTO;
	}
	
 

}

