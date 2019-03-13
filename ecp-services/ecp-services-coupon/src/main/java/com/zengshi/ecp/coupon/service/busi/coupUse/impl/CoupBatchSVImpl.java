package com.zengshi.ecp.coupon.service.busi.coupUse.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.coupon.dao.model.CoupBatchConf;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupBatchConfReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupBatchFieldReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CouponDetailPreReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CouponPresentReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupBatchConfRespDTO;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.coupon.service.busi.combination.interfaces.ICoupCreateInitSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupBatch;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupDetailSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class CoupBatchSVImpl extends GeneralSQLSVImpl implements ICoupBatch{

	@Resource
    private ICoupCreateInitSV coupCreateInitSV;
	
	@Resource
	private ICustManageRSV custManageRSV;
	
	@Resource
    private ICoupDetailSV coupDetailSV;
	//页数总数量
	private static final int n=999999999;
	
	private static final int staffSize=300;
	
	/**
	 * 
	 * saveCoupGain:新增优惠券批量查询配置表. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupCallBackReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public void saveCoupBatchConf(CoupBatchConfReqDTO coupBatchConfReqDTO) throws BusinessException {
		
		coupCreateInitSV.saveCoupBatchConf(coupBatchConfReqDTO);
	}
	
	
	/**
	 * 
	 * deleteCoupBatchConf:删除优惠券批量查询配置表. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupBatchConfReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public void deleteCoupBatchConf(CoupBatchConfReqDTO coupBatchConfReqDTO) throws BusinessException {
		CoupBatchConfReqDTO coupBatchConfReq = new CoupBatchConfReqDTO();
		coupBatchConfReq.setId(coupBatchConfReqDTO.getId());
		coupBatchConfReq.setStatus(CouponConstants.CoupSys.status_1);
		
		CoupBatchConf coupBatchConf = new CoupBatchConf();
		coupBatchConf.setStatus(CouponConstants.CoupSys.status_0);
		coupCreateInitSV.updateCoupBatchConf(coupBatchConfReqDTO, coupBatchConf);
	}

	/**
	 * 
	 * editCoupBatchConf:编辑(修改)优惠券批量查询配置表. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupCallBackReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public void editCoupBatchConf(CoupBatchConfReqDTO coupBatchConfReqDTO) throws BusinessException {
		//先删除信息
		this.deleteCoupBatchConf(coupBatchConfReqDTO);
		//新增
		this.saveCoupBatchConf(coupBatchConfReqDTO);
	}

	/**
	 * 
	 * TODO 优惠券批量配置查询. 
	 * Date:2016年3月19日下午5:34:48  <br>
	 * @author huanghe5
	 * @see com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupBatch#queryCoupBatchConf(com.zengshi.ecp.coupon.dubbo.dto.req.CoupBatchConfReqDTO)
	 */
	@Override
	public List<CoupBatchConfRespDTO> queryCoupBatchConf(CoupBatchConfReqDTO coupBatchConfReqDTO)
			throws BusinessException {
		if(coupBatchConfReqDTO == null){
			return null;
		}
		
		return coupCreateInitSV.queryCoupBatchConf(coupBatchConfReqDTO);
	}
	
	/**
	 * 
	 * giveBatchCoupon:根据用户等级获取用户. <br/> 
	 * 
	 * @author huanghe5
	 * @param info
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	private List<CustInfoResDTO> giveBatchStaffCoupon(CustInfoReqDTO info)throws BusinessException {
		
		
		PageResponseDTO<CustInfoResDTO> custBeanPageList = custManageRSV.listCustInfo(info);
		
		List<CustInfoResDTO> custInfoList= custBeanPageList.getResult();
		if(CollectionUtils.isEmpty(custInfoList)){
			return null;
		}
		return custInfoList;
		
	}

	/**
	 * 
	 * insertCoupon:根据配置批量赠送优惠券. <br/> 
	 * 
	 * @author huanghe5
	 * @throws BusinessException
	 * @since JDK 1.7
	 */
	@Override
	public CoupBatchConfRespDTO insertCoupon() throws BusinessException {
		
		CoupBatchConfReqDTO coupBatchConfReqDTO = new CoupBatchConfReqDTO();
		coupBatchConfReqDTO.setStatus(CouponConstants.CoupBatch.status_1);//已执行
		//查询未执行的配置参数
		List<CoupBatchConfRespDTO> coupBatchBeans= this.queryCoupBatchConf(coupBatchConfReqDTO);
		if(CollectionUtils.isEmpty(coupBatchBeans)){
			return null;
		}
		//1.先解析优惠券批量配置表
		for (CoupBatchConfRespDTO coupBatchConfRespDTO : coupBatchBeans) {
			long staffOnId = 0;
			JSONArray ja = JSON.parseArray(coupBatchConfRespDTO.getConfig());
			if (!CollectionUtils.isEmpty(ja)) {
				//2.解析配置表中config字段
				for (int i = 0; i < ja.size(); i++) {
					CoupBatchFieldReqDTO bfDto = JSON.parseObject(ja.getString(i), CoupBatchFieldReqDTO.class);
					//0:用户类型,1:金额类型(交易金额范围,交易次数)
					if(CouponConstants.CoupBatch.paraType_0.equals(bfDto.getParaType())){
						CustInfoReqDTO info = new CustInfoReqDTO();
						info.setCustLevelCode(bfDto.getCustLevel());
//						boolean b = true;
//						while (b) {
//							
//						}
						//j:页数
						for(int j=1; j<=n; j++){
							
							info.setPageSize(staffSize);//每页数量
							info.setPageNo(j);//页数
							
							List<CustInfoResDTO> custInfoBean = this
									.giveBatchStaffCoupon(info);
							if (CollectionUtils.isEmpty(custInfoBean)) {
								break;
							}
							for (CustInfoResDTO custInfoResDTO : custInfoBean) {
								
								if(staffOnId==0){
									staffOnId = custInfoResDTO.getId();
								}
								if(staffOnId==custInfoResDTO.getId()){
									continue;
								}
								
								try {
									//赠送优惠券步骤
									//1.设置参数
									CouponPresentReqDTO couponPresentReqDTO = new  CouponPresentReqDTO();
									couponPresentReqDTO.setStaffId(custInfoResDTO.getId());
									List<CouponDetailPreReqDTO> couponDetailPreBeans = new ArrayList<>();
									CouponDetailPreReqDTO couponDetailPreBean= new CouponDetailPreReqDTO();
									couponDetailPreBean.setCoupId(bfDto.getCoupId()); 
									couponDetailPreBean.setCoupSum(bfDto.getCoupNum());
									couponDetailPreBeans.add(couponDetailPreBean);
									couponPresentReqDTO.setCouponDetailPreBeans(couponDetailPreBeans);
									//2.赠送优惠券
									coupDetailSV.presentCoupon(couponPresentReqDTO);
								} catch (Exception e) {
									//抓到参数,写到日志表里
									e.printStackTrace();
								}
								staffOnId = custInfoResDTO.getId();
							}
							
						}
							
					}
					//1:金额类型(交易金额范围,交易次数) 订单组提供接口
					if(CouponConstants.CoupBatch.paraType_1.equals(bfDto.getParaType())){
						//交易次数
						if(!StringUtil.isBlank(bfDto.getDealNum())){
							
						}
					}
				}
			}
			
			coupBatchConfReqDTO.setStatus(CouponConstants.CoupBatch.status_2);//已执行
			this.editCoupBatchConf(coupBatchConfReqDTO);
		}
		return null;
	}
	
	
	
}

