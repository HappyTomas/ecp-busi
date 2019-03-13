package com.zengshi.ecp.coupon.service.busi.coupUse.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.coupon.dao.mapper.busi.CoupBlackLimitMapper;
import com.zengshi.ecp.coupon.dao.model.CoupBlackLimit;
import com.zengshi.ecp.coupon.dao.model.CoupBlackLimitCriteria;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupBlackLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupCheckParmReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupBlackLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupLimitCheckRespDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.Converter;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.coupon.service.busi.combination.interfaces.ICoupCreateInitSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupBlackLimitSV;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2CatgReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-coupon-server <br>
 * Description: <br>
 * Date:2015-10-16上午11:19:21  <br>
 * 
 * @author huanghe5
 * @version  
 * @since JDK 1.7
 */
public class CoupBlackLimitSVImpl extends GeneralSQLSVImpl implements ICoupBlackLimitSV{

	@Resource(name = "seq_coup_black_limit_id")
    private PaasSequence seq_coup_black_limit_id;
	@Resource
	private Converter<CoupBlackLimitReqDTO,CoupBlackLimit> coupBlackLimitConverter;
	@Resource
	private Converter<CoupBlackLimit, CoupBlackLimitRespDTO> coupBlackLimitRespDTOConverter;
	@Resource
	private CoupBlackLimitMapper coupBlackLimitMapper;
	@Resource
    private ICoupCreateInitSV coupCreateInitSV;
	@Resource
	private IGdsInfoQueryRSV gdsInfoQueryRSV;
	
	/**
	 * 
	 * TODO 保存黑名单表. 
	 * Date:2015-10-16下午9:24:45  <br>
	 * @author huanghe5
	 * @see com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupRuleSV#save(com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO)
	 */
	@Override
	public void save(CoupReqDTO coupReqDTO) throws BusinessException {
		List<CoupBlackLimitReqDTO> coupBlackLimitReqDTOs = coupReqDTO.getCoupBlackLimitReqDTOs();
		if(CollectionUtils.isNotEmpty(coupBlackLimitReqDTOs)){
			for (CoupBlackLimitReqDTO coupBlackLimitReqDTO : coupBlackLimitReqDTOs) {
				//优惠券黑名单入参信息
				coupBlackLimitReqDTO.setCoupId(coupReqDTO.getCoupInfoReqDTO().getId());//优惠券信息ID
				coupBlackLimitReqDTO.setId(seq_coup_black_limit_id.nextValue());
				coupBlackLimitReqDTO.setCreateStaff(coupReqDTO.getStaff().getId());
				coupBlackLimitReqDTO.setCreateTime(DateUtil.getSysDate());
				//如果是 编辑
				if(CouponConstants.CoupSys.edit_EDIT.equals(coupReqDTO.getEdit())){
					coupBlackLimitReqDTO.setUpdateStaff(coupReqDTO.getStaff().getId());
					coupBlackLimitReqDTO.setUpdateTime(DateUtil.getSysDate());
				}
			    if(StringUtil.isEmpty(coupBlackLimitReqDTO.getStatus())){
			        coupBlackLimitReqDTO.setStatus(CouponConstants.CoupSys.status_1);
                }
				CoupBlackLimit coupBlackLimit = coupBlackLimitConverter.convert(coupBlackLimitReqDTO);
				coupBlackLimitMapper.insert(coupBlackLimit);
			}
		}
	}
	
	/**
	 * 
	 * TODO 修改黑名单表关于此coup_id的相关信息. 
	 * Date:2015-10-16下午9:31:42  <br>
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
	 * TODO 逻辑删除此黑名单信息. 
	 * Date:2015-10-16下午9:25:27  <br>
	 * @author huanghe5
	 * @see com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupRuleSV#deleteStatus(com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO)
	 */
	@Override
	public void deleteStatus(CoupReqDTO coupReqDTO) throws BusinessException {
		
		CoupBlackLimit bean = new CoupBlackLimit();
		bean.setStatus(CouponConstants.CoupSys.status_0);//失效
		CoupBlackLimitCriteria example = new CoupBlackLimitCriteria();
		CoupBlackLimitCriteria.Criteria cr = example.createCriteria();
		if (coupReqDTO.getCoupInfoReqDTO().getId() != null && coupReqDTO.getCoupInfoReqDTO().getId() > 0) {
			cr.andCoupIdEqualTo(coupReqDTO.getCoupInfoReqDTO().getId());// 优惠券信息ID
			
			coupBlackLimitMapper.updateByExampleSelective(bean, example);
		}else if(coupReqDTO.getCoupBlackLimitReqDTO().getCoupId()!=null && coupReqDTO.getCoupBlackLimitReqDTO().getCoupId()>0){
			cr.andCoupIdEqualTo(coupReqDTO.getCoupBlackLimitReqDTO().getCoupId());// 优惠券信息ID
			
			coupBlackLimitMapper.updateByExampleSelective(bean, example);
		}
		
		
		
	}
	
	/**
	 * 
	 * queryCoupInfoPage:查询黑名单表. <br/> 
	 * 
	 * @author huanghe5 
	 * @param coupBlackLimitReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public List<CoupBlackLimitRespDTO> queryCoupBlackList(
			CoupBlackLimitReqDTO coupBlackLimitReqDTO) throws BusinessException {
		CoupBlackLimitCriteria example = new CoupBlackLimitCriteria();
		coupCreateInitSV.initCoupBlackLimit(coupBlackLimitReqDTO, example);
		if(StringUtil.isBlank(coupBlackLimitReqDTO.getStatus())){
			coupBlackLimitReqDTO.setStatus(CouponConstants.CoupSys.status_1);
		}
		
		List<CoupBlackLimit> beans = coupBlackLimitMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(beans)) {
            return null;
        }
		List<CoupBlackLimitRespDTO> reList = new ArrayList<CoupBlackLimitRespDTO>();
		for (CoupBlackLimit coupBlackLimit : beans) {
			reList.add(coupBlackLimitRespDTOConverter.convert(coupBlackLimit));
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
		coupLimitCheckRespDTO.setCoupId(coupCheckParmReqDTO.getCoupId());
		coupLimitCheckRespDTO.setGdsId(ordCartItemCommRequest.getGdsId());
		coupLimitCheckRespDTO.setSkuId(ordCartItemCommRequest.getSkuId());
		CoupBlackLimitReqDTO coupBlackLimitReqDTO = new CoupBlackLimitReqDTO();
		coupBlackLimitReqDTO.setCoupId(coupCheckParmReqDTO.getCoupId());
		coupBlackLimitReqDTO.setStatus(CouponConstants.CoupSys.status_1);
		//查询coupId黑名单规则信息
		List<CoupBlackLimitRespDTO> bean = this.queryCoupBlackList(coupBlackLimitReqDTO);
		if(CollectionUtils.isNotEmpty(bean)){
			//校验入参信息是否符合黑名单信息
			for (CoupBlackLimitRespDTO coupBlackLimitRespDTO : bean) {
				if(CouponConstants.CoupBlackLimit.CATEGORY_TYPE_0.equals(coupBlackLimitRespDTO.getCategoryType())){
					if(ordCartItemCommRequest.getSkuId().equals(coupBlackLimitRespDTO.getSkuId())){
						coupLimitCheckRespDTO.setJudge(CouponConstants.CoupSys.judge_0);
					}
				}else if(CouponConstants.CoupBlackLimit.CATEGORY_TYPE_1.equals(coupBlackLimitRespDTO.getCategoryType())){
					if(ordCartItemCommRequest.getGdsId().equals(coupBlackLimitRespDTO.getGdsId())){
						coupLimitCheckRespDTO.setJudge(CouponConstants.CoupSys.judge_0);
					}
				}else if(CouponConstants.CoupBlackLimit.CATEGORY_TYPE_2.equals(coupBlackLimitRespDTO.getCategoryType())){
					//判断这商品是否属于此分类
					GdsGds2CatgReqDTO catgReqDTO=new GdsGds2CatgReqDTO();
					catgReqDTO.setCatgCode(coupBlackLimitRespDTO.getCatgCode());
					catgReqDTO.setGdsId(ordCartItemCommRequest.getGdsId());
					boolean isBelong=gdsInfoQueryRSV.isBelongToCategory(catgReqDTO);
					if(isBelong){
						coupLimitCheckRespDTO.setJudge(CouponConstants.CoupSys.judge_0);
					}
				}
			}
			if(StringUtil.isBlank(coupLimitCheckRespDTO.getJudge())){
				coupLimitCheckRespDTO.setJudge(CouponConstants.CoupSys.judge_1);
			}
			return coupLimitCheckRespDTO;
		}
		coupLimitCheckRespDTO.setJudge(CouponConstants.CoupSys.judge_0);
		return coupLimitCheckRespDTO;
	}



}

