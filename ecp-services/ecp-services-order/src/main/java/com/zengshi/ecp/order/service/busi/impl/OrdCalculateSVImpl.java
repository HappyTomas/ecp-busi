package com.zengshi.ecp.order.service.busi.impl;

import com.zengshi.ecp.coupon.dubbo.dto.req.CoupOrdBackReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupOrdNumBackRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupDetailRSV;
import com.zengshi.ecp.order.dao.mapper.busi.OrdBackApplyMapper;
import com.zengshi.ecp.order.dao.model.*;
import com.zengshi.ecp.order.dubbo.dto.*;
import com.zengshi.ecp.order.dubbo.util.BackConstants;
import com.zengshi.ecp.order.dubbo.util.LongUtils;
import com.zengshi.ecp.order.service.busi.interfaces.*;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.*;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffUnionRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffUnionRWRSV;
import com.zengshi.paas.utils.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrdCalculateSVImpl implements IOrdCalculateSV {

	@Resource
	private IOrdMainSV ordMainSV;

	@Resource
	private IOrdSubSV ordSubSV;

	@Resource
	private IOrdDiscountSV ordDiscountSV;

	@Resource
	private OrdBackApplyMapper ordBackApplyMapper;

	@Resource
	private IStaffUnionRSV staffUnionRSV;
	
	@Resource	
	private IOrdBackDiscountSV ordBackDiscountSV;
	
	@Autowired(required = false)
	private IOrdBackApplySV ordBackApplySV;
	
	@Resource	
	private IOrdBackGdsSV ordBackGdsSV;
	
    @Resource   
    private ICoupDetailRSV coupDetailRSV;	
    
    @Resource  
    private IOrderBackSV orderBackSV;
    
    @Resource
    private IStaffUnionRWRSV staffUnionRWESV;

	private static final String MODULE = OrdCalculateSVImpl.class.getName();

	@Override
	public RBackApplyInfoResp calCulateShare(RBackReviewReq info)
			throws BusinessException {
		String orderId = info.getOrderId();
		// 获取退货明细
		List<RBackGdsResp> backList=getBackList(info);
		// 判断是否最后一笔退货订单
		boolean lastFlag = isLastBackBatch(info,backList);		
		// 如果是最后一笔退货，走最后一笔计算逻辑，如果不是，走正常的分摊计算
		RBackApplyInfoResp rBackApplyInfoResp = new RBackApplyInfoResp();
		rBackApplyInfoResp.setOrderId(info.getOrderId());
		rBackApplyInfoResp.setBackId(info.getBackId());
		rBackApplyInfoResp.setLastFlag(lastFlag);
		List<OrdSub> ordSubs = new ArrayList<OrdSub>();
		// 查询主订单的信息，取出realMoney
		OrdMain ordMain = ordMainSV.queryOrderByOrderId(orderId);
		Long siteId = ordMain.getSiteId();
		Long staffId = ordMain.getStaffId();
		Long discountMoneys = 0l;
		Long sumUsedScore = 0l;
		// 封装总的明细金额，总的使用积分
		List<OrderBackSubReqDTO> OrderBackSubReqDTOzList = new ArrayList<OrderBackSubReqDTO>();
		if (!CollectionUtils.isEmpty(backList)) {
			for (RBackGdsResp rBackGdsResp : backList) {
				SBaseAndSubInfo sBaseAndSubInfo = new SBaseAndSubInfo();
				sBaseAndSubInfo.setOrderId(info.getOrderId());
				sBaseAndSubInfo.setOrderSubId(rBackGdsResp.getOrderSubId());
				OrdSub ordSub = ordSubSV.findByOrderSubId(sBaseAndSubInfo);
				discountMoneys = discountMoneys + ordSub.getDiscountMoney();
				sumUsedScore = sumUsedScore + ordSub.getOrderSubScore();
				ordSubs.add(ordSub);
				// 封装积分查询入参
				OrderBackSubReqDTO OrderBackSubReqDTO = new OrderBackSubReqDTO();
				OrderBackSubReqDTO.setStaffId(staffId);
				OrderBackSubReqDTO.setOrderId(orderId);
				OrderBackSubReqDTO
						.setSubOrderId(rBackGdsResp.getOrderSubId());
				OrderBackSubReqDTOzList.add(OrderBackSubReqDTO);
				
			}
		}
		// 获取分摊比例

		long scale = calCulateScale(info,lastFlag);   

		OrderBackMainReqDTO<OrderBackSubReqDTO> socreOrderBackMainReqDTO = new OrderBackMainReqDTO<OrderBackSubReqDTO>();
		socreOrderBackMainReqDTO.setList(OrderBackSubReqDTOzList);
		socreOrderBackMainReqDTO.setStaffId(staffId);
		socreOrderBackMainReqDTO.setOrderId(orderId);
		socreOrderBackMainReqDTO.setScale(scale);
		socreOrderBackMainReqDTO.setSiteId(siteId);
		socreOrderBackMainReqDTO.setLastFlag(lastFlag);
		// 计算退货申请的退款金额
		Long culateMoney = calCulateMoney(info, ordMain,scale, lastFlag);
		rBackApplyInfoResp.setBackMoney(culateMoney);
		// 积分信息封装开始
		// 分装退货积分信息
        RBackApplyScoreResp rBackApplyScoreResp = new RBackApplyScoreResp();
		// 设置订单退货扣减的积分----调用用户积分接口，查询用户需要扣减的积分--todo
		long sumReduCulateScore = staffUnionRSV
				.selTotalScoreByBackSubOrder(socreOrderBackMainReqDTO);
		if(sumUsedScore.longValue()!=0 || sumReduCulateScore!=0){
		      // 设置订单退货增加的积分
	        rBackApplyScoreResp.setSumUsedCulateScore(sumUsedScore);
	        rBackApplyScoreResp.setSumReduCulateScore(sumReduCulateScore);
		      // 设置订单退货用户剩余的积分，调用用户积分接口，查询用户剩余积分
	        ScoreInfoResDTO scoreInfoResDTO = staffUnionRSV
	                .findScoreInfoByStaffId(staffId);
	        if (!StringUtil.isEmpty(scoreInfoResDTO)) {
	            rBackApplyScoreResp
	                    .setStaffScore(scoreInfoResDTO.getScoreBalance());
	        }else{
	            rBackApplyScoreResp.setStaffScore(0l);
	        }
	        rBackApplyInfoResp.setrBackApplyScoreResp(rBackApplyScoreResp);
		}
		// 积分信息封装结束
		// 资金账户信息封装开始
        RBackApplyAccResp rBackApplyAccResp = new RBackApplyAccResp();
		OrderBackMainReqDTO<OrderBackSubReqDTO> accinfoOrderBackMainReqDTO = new OrderBackMainReqDTO<OrderBackSubReqDTO>();
		accinfoOrderBackMainReqDTO.setOrderId(orderId);
		accinfoOrderBackMainReqDTO.setStaffId(staffId);
		accinfoOrderBackMainReqDTO.setScale(scale);
		accinfoOrderBackMainReqDTO.setLastFlag(lastFlag);
		OrderAcctMainResDTO<OrderAcctSubResDTO> OrderAcctMainList=staffUnionRSV.selAcctByBackOrder(accinfoOrderBackMainReqDTO);
		if(!StringUtil.isEmpty(OrderAcctMainList) && !CollectionUtils.isEmpty(OrderAcctMainList.getList())){
		       rBackApplyAccResp.setSumUsedCulateAccList(OrderAcctMainList.getList());	    
		       rBackApplyInfoResp.setrBackApplyAccResp(rBackApplyAccResp);
		}
		// 优惠劵封装开始
		boolean firstFlag=isFirstBackBatch(info);
		if(firstFlag){
			CoupOrdBackReqDTO coupReq=new CoupOrdBackReqDTO();
			coupReq.setStaffId(staffId);
			coupReq.setOrderId(orderId);
			CoupOrdNumBackRespDTO coupRespList=coupDetailRSV.queryStaffCoup(coupReq);
			if(!StringUtil.isEmpty(coupRespList)){
			    RBackApplyCoupResp rBackApplyCoupResp=new RBackApplyCoupResp();
			    rBackApplyCoupResp.setBackApllyCoupList(coupRespList.getCoupNumBeans());
			    rBackApplyInfoResp.setrBackApplyCoupResp(rBackApplyCoupResp);
			}	
		}
		return rBackApplyInfoResp;

	}

	@Override
	public long calCulateScaleApply(RBackApplyReq rBackApplyReq, OrdBackApply ordBackApply) throws BusinessException {
		//String applyType=rBackApplyReq.getApplyType();
		//if(BackConstants.ApplyType.REFUND.equals(applyType) || "1".equals(ordBackApply.getIsEntire())){
		/*if(BackConstants.ApplyType.REFUND.equals(applyType)){
			return 100l;
		}
		*/
		//全部退货
		if(StringUtils.isNotBlank(rBackApplyReq.getCheckedAll())&&rBackApplyReq.getCheckedAll().equals("1")){
		    return 1000000l;
		}
		
		String orderId = ordBackApply.getOrderId();
		Long discountMoneys = 0l;
		String subOrderId = "";
		//非全部退款时只允许单个子订单商品退款，因而子订单编号只有一个
		if (!CollectionUtils.isEmpty(rBackApplyReq.getrBackOrdSubReqs())) {
			for (RBackOrdSubReq rBackOrdSubReq : rBackApplyReq.getrBackOrdSubReqs()) {
				SBaseAndSubInfo sBaseAndSubInfo = new SBaseAndSubInfo();
				sBaseAndSubInfo.setOrderId(orderId);
				sBaseAndSubInfo.setOrderSubId(rBackOrdSubReq.getOrderSubId());
				OrdSub ordSub = ordSubSV.findByOrderSubId(sBaseAndSubInfo);
				discountMoneys = discountMoneys + ordSub.getDiscountMoney();
				subOrderId = rBackOrdSubReq.getOrderSubId();
			}
		}
		
		Long sumDiscountMoney = ordSubSV.querySumDiscountMoney(orderId);
		SBaseAndSubInfo sOrderAOrderSubInfo = new SBaseAndSubInfo();
		sOrderAOrderSubInfo.setOrderSubId(subOrderId);
		sOrderAOrderSubInfo.setOrderId(orderId);
		
		Long subAmount = ordSubSV.findByOrderSubId(sOrderAOrderSubInfo).getOrderAmount();
		
		return (new BigDecimal(ordBackApply.getNum()*discountMoneys * 1000000).divide(new BigDecimal(sumDiscountMoney*subAmount),2)).longValue();

	}

	@Override
	public RBackApplyInfoResp calCulateShareApply(RBackApplyReq rBackApplyReq, OrdBackApply ordBackApply) throws BusinessException {
		String orderId = rBackApplyReq.getOrderId();
        // 如果是最后一笔退货，走最后一笔计算逻辑，如果不是，走正常的分摊计算
        RBackApplyInfoResp rBackApplyInfoResp = new RBackApplyInfoResp();
        rBackApplyInfoResp.setOrderId(ordBackApply.getOrderId());
        rBackApplyInfoResp.setBackId(ordBackApply.getId());
        rBackApplyInfoResp.setLastFlag("1".equals(ordBackApply.getIsEntire()));
        List<OrdSub> ordSubs = new ArrayList<OrdSub>();
        // 查询主订单的信息，取出realMoney
        OrdMain ordMain = ordMainSV.queryOrderByOrderId(orderId);
        Long siteId = ordMain.getSiteId();
        Long staffId = ordMain.getStaffId();
        
        Long discountMoneys = 0l;
        Long sumUsedScore = 0l;
        // 封装总的明细金额，总的使用积分
        List<OrderBackSubReqDTO> OrderBackSubReqDTOzList = new ArrayList<OrderBackSubReqDTO>();
        if (!CollectionUtils.isEmpty(rBackApplyReq.getrBackOrdSubReqs())) {
            for (RBackOrdSubReq rBackOrdSubReq:rBackApplyReq.getrBackOrdSubReqs()) {
                SBaseAndSubInfo sBaseAndSubInfo = new SBaseAndSubInfo();
                sBaseAndSubInfo.setOrderId(rBackApplyReq.getOrderId());
                sBaseAndSubInfo.setOrderSubId(rBackOrdSubReq.getOrderSubId());
                OrdSub ordSub = ordSubSV.findByOrderSubId(sBaseAndSubInfo);
                discountMoneys = discountMoneys + ordSub.getDiscountMoney();
                sumUsedScore = sumUsedScore + ordSub.getOrderSubScore();
                ordSubs.add(ordSub);
                // 封装积分查询入参
                OrderBackSubReqDTO OrderBackSubReqDTO = new OrderBackSubReqDTO();
                OrderBackSubReqDTO.setStaffId(staffId);
                OrderBackSubReqDTO.setOrderId(orderId);
                OrderBackSubReqDTO.setSubOrderId(rBackOrdSubReq.getOrderSubId());                
                SBaseAndSubInfo sOrderAOrderSubInfo = new SBaseAndSubInfo();
                sOrderAOrderSubInfo.setOrderSubId(rBackOrdSubReq.getOrderSubId());
                sOrderAOrderSubInfo.setOrderId(orderId);  
                OrderBackSubReqDTOzList.add(OrderBackSubReqDTO);                           
            }
        }       
        // 计算商品占总订单的分摊比例
        long scale = orderBackSV.calCulateScaleApply(rBackApplyReq,ordBackApply,ordMain);
        rBackApplyInfoResp.setScale(scale);
        OrderBackMainReqDTO<OrderBackSubReqDTO> socreOrderBackMainReqDTO = new OrderBackMainReqDTO<OrderBackSubReqDTO>();
        socreOrderBackMainReqDTO.setList(OrderBackSubReqDTOzList);
        socreOrderBackMainReqDTO.setStaffId(staffId);
        socreOrderBackMainReqDTO.setOrderId(orderId);
        socreOrderBackMainReqDTO.setScale(scale);
        socreOrderBackMainReqDTO.setSiteId(siteId);
        socreOrderBackMainReqDTO.setLastFlag(rBackApplyInfoResp.isLastFlag());
        // 计算退货申请的退款金额
        Long culateMoney = orderBackSV.calCulateMoneyApply(rBackApplyReq,ordBackApply,ordMain,scale,rBackApplyInfoResp.isLastFlag());
        rBackApplyInfoResp.setBackMoney(culateMoney);
        // 积分信息封装开始
        // 分装退货积分信息
        RBackApplyScoreResp rBackApplyScoreResp = new RBackApplyScoreResp();
    	// 设置订单退货扣减的积分----调用用户积分接口，查询用户需要扣减的积分--todo
		long sumReduCulateScore=staffUnionRWESV.selTotalScoreByBackOrderRW(socreOrderBackMainReqDTO);
        if(sumUsedScore.longValue()!=0 || sumReduCulateScore!=0){
            //设置订单退货增加的积分
            rBackApplyScoreResp.setSumUsedCulateScore(sumUsedScore);
            rBackApplyScoreResp.setSumReduCulateScore(sumReduCulateScore);
            //设置订单退货用户剩余的积分，调用用户积分接口，查询用户剩余积分
            ScoreInfoResDTO scoreInfoResDTO = staffUnionRSV.findScoreInfoByStaffId(staffId);
            if (!StringUtil.isEmpty(scoreInfoResDTO)) {
                rBackApplyScoreResp.setStaffScore(scoreInfoResDTO.getScoreBalance());
            }else{
                rBackApplyScoreResp.setStaffScore(0l);
            }
            rBackApplyInfoResp.setrBackApplyScoreResp(rBackApplyScoreResp);
        }
        // 积分信息封装结束
        // 资金账户信息封装开始
        RBackApplyAccResp rBackApplyAccResp = new RBackApplyAccResp();
        OrderBackMainReqDTO<OrderBackSubReqDTO> accinfoOrderBackMainReqDTO = new OrderBackMainReqDTO<OrderBackSubReqDTO>();
        accinfoOrderBackMainReqDTO.setOrderId(orderId);
        accinfoOrderBackMainReqDTO.setStaffId(staffId);
        accinfoOrderBackMainReqDTO.setScale(scale);
        accinfoOrderBackMainReqDTO.setLastFlag(rBackApplyInfoResp.isLastFlag());
        //查询资金账户
        OrderAcctMainResDTO<OrderAcctSubResDTO> OrderAcctMainList=staffUnionRSV.selAcctByBackOrder(accinfoOrderBackMainReqDTO);
        if(!StringUtil.isEmpty(OrderAcctMainList) && !CollectionUtils.isEmpty(OrderAcctMainList.getList())){
            rBackApplyAccResp.setSumUsedCulateAccList(OrderAcctMainList.getList());
            rBackApplyInfoResp.setrBackApplyAccResp(rBackApplyAccResp);
        }
        // 优惠劵封装开始
        boolean firstFlag=isFirstBackBatchApply(ordBackApply);
        if(firstFlag){
            CoupOrdBackReqDTO coupReq=new CoupOrdBackReqDTO();
            coupReq.setStaffId(staffId);
            coupReq.setOrderId(orderId);
            CoupOrdNumBackRespDTO coupRespList=coupDetailRSV.queryStaffCoup(coupReq);
            if(!StringUtil.isEmpty(coupRespList)){
                RBackApplyCoupResp rBackApplyCoupResp=new RBackApplyCoupResp();
                rBackApplyCoupResp.setBackApllyCoupList(coupRespList.getCoupNumBeans());
                rBackApplyInfoResp.setrBackApplyCoupResp(rBackApplyCoupResp);
            }
        }
        return rBackApplyInfoResp;
	}
	

			
	/**
	 * 计算是否最后第一次退款退货
	 *
	 * @param
	 * @param
	 * @return
	 */
	private boolean isFirstBackBatchApply(OrdBackApply ordBackApply) {
		// 返回1代表是最后一笔退款退货
		if (BackConstants.ApplyType.REFUND.equals(ordBackApply.getApplyType())) {
			return true;
		}
		String orderId = ordBackApply.getOrderId();
		// 查询已经退货的总数量
		OrdBackApplyCriteria ordBackApplyCriteria = new OrdBackApplyCriteria();
		ordBackApplyCriteria.createCriteria().andOrderIdEqualTo(orderId)
				.andStatusNotEqualTo(BackConstants.Status.REFUSE).andIdNotEqualTo(ordBackApply.getId());
		List<OrdBackApply> orderApplyList = ordBackApplyMapper
				.selectByExample(ordBackApplyCriteria);
		if(CollectionUtils.isEmpty(orderApplyList)){
			return true;
		}else{
			return false;
		}
	}


	private Long calCulateMoneyApply(RBackApplyReq rBackApplyReq, OrdBackApply ordBackApply, OrdMain ordMain,double scale) {
        Long expressFee=ordMain.getRealExpressFee();
        Long realMoney=ordMain.getRealMoney();
        String applyType=rBackApplyReq.getApplyType();
        if(BackConstants.ApplyType.REFUND.equals(applyType)){
            return realMoney;
        }else{
            //if ("1".equals(ordBackApply.getIsEntire())) {
            //全部退货
            if(StringUtil.isNotBlank(rBackApplyReq.getCheckedAll())&&rBackApplyReq.getCheckedAll().equals("1")){
                Long result=calCulateLastApply(ordBackApply,ordMain);
                return result;
            } else {                
                Long discountMoneys = 0l;
                  //子订单最后一笔
                boolean isSubLast = this.isSubLastBackBatch(rBackApplyReq);
                if(isSubLast){
                    Long subOrderMoney = 0l;
                    Long hasBackMoney = 0l;
                    for(RBackOrdSubReq rBackOrdSubReq: rBackApplyReq.getrBackOrdSubReqs()){
                  //  for(int i=0;i<rBackApplyReq.getrBackOrdSubReqs().size();i++){
                       // RBackOrdSubReq rBackOrdSubReq = rBackApplyReq.getrBackOrdSubReqs().get(i)
                        SBaseAndSubInfo sBaseAndSubInfo = new SBaseAndSubInfo();
                        sBaseAndSubInfo.setOrderId(rBackApplyReq.getOrderId());
                        sBaseAndSubInfo.setOrderSubId(rBackOrdSubReq.getOrderSubId());
                        OrdSub ordSub = ordSubSV.findByOrderSubId(sBaseAndSubInfo);                 
                        subOrderMoney = subOrderMoney+ordSub.getDiscountMoney();   
                        
                        //查询子订单退款明细
                        List<OrdBackGds> backgdss = ordBackGdsSV.queryHasBackBackGdsByOrderSubId(rBackOrdSubReq);
                        if(backgdss!=null&&backgdss.size()>0){
                            for(OrdBackGds backgds:backgdss){
                                OrdBackApply ordBackAppply = ordBackApplySV.queryOrdBackApplyByBackId(backgds.getBackId());
                                //状态为已退款的单据
                                if(ordBackAppply.getStatus().equals(BackConstants.Status.REFUNDED)&&ordBackAppply.getBackMoney()!=null){
                                    hasBackMoney = hasBackMoney +ordBackAppply.getBackMoney();
                                }
                            }
                        }                      
                    }                                    
                    //应退金额为:子订单总额-已退总额
                    discountMoneys = subOrderMoney-hasBackMoney;
                    return discountMoneys;
                }else{//非最后一笔按比例计算
                    
                    //避免被向上取整出现9000xxx/1000000=10的情况
                    double money = (realMoney-expressFee)*scale/1000000;                
                    return new BigDecimal(money).longValue();
                }
                
               /* Long money=realMoney.longValue()-expressFee.longValue();
                Long sumDiscountMoney = ordSubSV.querySumDiscountMoney(ordBackApply.getOrderId());
                if(sumDiscountMoney.longValue()==money){
                    String orderId = ordBackApply.getOrderId();
                    
                    if (!CollectionUtils.isEmpty(rBackApplyReq.getrBackOrdSubReqs())) {
                        for (RBackOrdSubReq rBackOrdSubReq:rBackApplyReq.getrBackOrdSubReqs()) {
                            SBaseAndSubInfo sBaseAndSubInfo = new SBaseAndSubInfo();
                            sBaseAndSubInfo.setOrderId(orderId);
                            sBaseAndSubInfo.setOrderSubId(rBackOrdSubReq.getOrderSubId());
                            OrdSub ordSub = ordSubSV.findByOrderSubId(sBaseAndSubInfo);
                            discountMoneys = discountMoneys + ordSub.getDiscountMoney();
                        }
                    }
                    return discountMoneys;
                }else{
                    return (new BigDecimal(money*scale).divide(new BigDecimal(
                            1000000),2)).longValue();
                }*/
            }
        }

    }


	private Long calCulateLastApply(OrdBackApply ordBackApply,OrdMain ordMain)
			throws BusinessException {
		Long realMoney = ordMain.getRealMoney();
		Long usedbackMoney=0l;
		// 最后一次退货，金额取实付金额-已退金额,返回积分取下单使用返回积分，扣减积分取下单赠送扣减积分，百分比，资金账户取使用回退资金账户，和百分比
		ROrderBackReq rOrderBackReq=new ROrderBackReq();
		rOrderBackReq.setOrderId(ordBackApply.getOrderId());
		List<RBackDiscountResp> rBackDiscountList=ordBackDiscountSV.queryOrdBackDiscountByOrderId(rOrderBackReq);
		if(!CollectionUtils.isEmpty(rBackDiscountList)){
			for(RBackDiscountResp rBackDiscountResp : rBackDiscountList){
				if(rBackDiscountResp.getDiscountType().equals("01")){
					if(!LongUtils.isEmpty(rBackDiscountResp.getAmount())){
						usedbackMoney=usedbackMoney+rBackDiscountResp.getAmount();
					}
				}
			}
		}
		Long lastMoney=realMoney.longValue()-ordMain.getRealExpressFee()-usedbackMoney.longValue();
		return lastMoney;

	}


	private List<RBackGdsResp> getBackList(RBackReviewReq info){
		String orderId = info.getOrderId();
		Long backId=info.getBackId();		
		RBackApplyResp rBackApplyResp=new RBackApplyResp();
		rBackApplyResp.setOrderId(orderId);
		rBackApplyResp.setBackId(backId);
		List<RBackGdsResp> rBackGdsList=ordBackGdsSV.queryBackGdsByBackId(rBackApplyResp);
		return rBackGdsList;
	}
	private Long calCulateLast(RBackReviewReq info,OrdMain ordMain)
			throws BusinessException {
		Long realMoney = ordMain.getRealMoney();
		Long usedbackMoney=0l;
		// 最后一次退货，金额取实付金额-已退金额,返回积分取下单使用返回积分，扣减积分取下单赠送扣减积分，百分比，资金账户取使用回退资金账户，和百分比
		ROrderBackReq rOrderBackReq=new ROrderBackReq();
		rOrderBackReq.setOrderId(info.getOrderId());
		List<RBackDiscountResp> rBackDiscountList=ordBackDiscountSV.queryOrdBackDiscountByOrderId(rOrderBackReq);
		if(!CollectionUtils.isEmpty(rBackDiscountList)){
			for(RBackDiscountResp rBackDiscountResp : rBackDiscountList){
				if(rBackDiscountResp.getDiscountType().equals("01")){
					if(!LongUtils.isEmpty(rBackDiscountResp.getAmount())){
						usedbackMoney=usedbackMoney+rBackDiscountResp.getAmount();
					}
				}
			}
		}
		Long lastMoney=realMoney.longValue()-ordMain.getRealExpressFee()-usedbackMoney.longValue();	
		return lastMoney;
		
	}
	
	public long calCulateScale(RBackReviewReq info,boolean lastFlag) {
	    //最后一笔
        if(lastFlag){
            return 1000000l;
        }
        String orderId = info.getOrderId();
        Long discountMoneys = 0l;
        String subOrderId = "";
        List<RBackGdsResp> RBackGdsList=getBackList(info);
        //非全部退款时只允许单个子订单商品退款，因而子订单编号只有一个
        Long num = 0l;
        if (!CollectionUtils.isEmpty(RBackGdsList)) {
            for (RBackGdsResp rBackGdsResp: RBackGdsList) {
                SBaseAndSubInfo sBaseAndSubInfo = new SBaseAndSubInfo();
                sBaseAndSubInfo.setOrderId(orderId);
                sBaseAndSubInfo.setOrderSubId(rBackGdsResp.getOrderSubId());
                OrdSub ordSub = ordSubSV.findByOrderSubId(sBaseAndSubInfo);
                discountMoneys = discountMoneys + ordSub.getDiscountMoney();
                subOrderId = rBackGdsResp.getOrderSubId();
                num = num + rBackGdsResp.getNum();
            }
        }
        
        Long sumDiscountMoney = ordSubSV.querySumDiscountMoney(orderId);
        SBaseAndSubInfo sOrderAOrderSubInfo = new SBaseAndSubInfo();
        sOrderAOrderSubInfo.setOrderSubId(subOrderId);
        sOrderAOrderSubInfo.setOrderId(orderId);
        
        Long subAmount = ordSubSV.findByOrderSubId(sOrderAOrderSubInfo).getOrderAmount();
        
        return (new BigDecimal(num*discountMoneys * 1000000).divide(new BigDecimal(sumDiscountMoney*subAmount),2)).longValue();

	}

	private Long calCulateMoney(RBackReviewReq info, OrdMain ordMain,double scale, boolean lastFlag) {
		Long expressFee=ordMain.getRealExpressFee();
		Long realMoney=ordMain.getRealMoney();
        String applyType=info.getApplyType();
		if(BackConstants.ApplyType.REFUND.equals(applyType)){
            return realMoney;
        }else{
            if (lastFlag) {
                Long result=calCulateLast(info,ordMain);
                return result;
            } else {
                Long money=realMoney.longValue()-expressFee.longValue();
                Long sumDiscountMoney = ordSubSV.querySumDiscountMoney(info.getOrderId());
                if(sumDiscountMoney.longValue()==money){
                    String orderId = info.getOrderId();
                    List<RBackGdsResp> RBackGdsList=getBackList(info);
                    Long discountMoneys = 0l;
                    if (!CollectionUtils.isEmpty(RBackGdsList)) {
                        for (RBackGdsResp rBackGdsResp : RBackGdsList) {
                            SBaseAndSubInfo sBaseAndSubInfo = new SBaseAndSubInfo();
                            sBaseAndSubInfo.setOrderId(orderId);
                            sBaseAndSubInfo.setOrderSubId(rBackGdsResp.getOrderSubId());
                            OrdSub ordSub = ordSubSV.findByOrderSubId(sBaseAndSubInfo);
                            discountMoneys = discountMoneys + ordSub.getDiscountMoney();
                        }
                    }
                    return discountMoneys;
                }else{
                    return (new BigDecimal(money*scale).divide(new BigDecimal(
                            100),2)).longValue();   
                }
            }   
        }

	}
	
	@Override
    public boolean isSubLastBackBatch(RBackApplyReq rBackApplyReq) throws BusinessException {
	  //判断是否子订单最后一笔
        boolean isLast = false;
        
        for(RBackOrdSubReq rBackOrdSubReq: rBackApplyReq.getrBackOrdSubReqs()){
            SBaseAndSubInfo sBaseAndSubInfo = new SBaseAndSubInfo();
            sBaseAndSubInfo.setOrderId(rBackApplyReq.getOrderId());
            sBaseAndSubInfo.setOrderSubId(rBackOrdSubReq.getOrderSubId());
            OrdSub ordSub = ordSubSV.findByOrderSubId(sBaseAndSubInfo);
            Long subOrderAmounts =ordSub.getOrderAmount();
            if(ordSub.getHasBackNum()!=null){
                subOrderAmounts=subOrderAmounts-ordSub.getHasBackNum();
            }                       
            if(subOrderAmounts == rBackOrdSubReq.getNum()){
                isLast = true;
                break;
            }
        }
        
        return isLast;
	}


	@Override
	public boolean isLastBackBatch(RBackApplyReq rBackApplyReq) throws BusinessException {
		// 返回1代表是最后一笔退款退货
		if (BackConstants.ApplyType.REFUND.equals(rBackApplyReq.getApplyType())) {
			return true;
		}
		
		//全部退货返回是最后一笔
		if(StringUtils.isNotBlank(rBackApplyReq.getCheckedAll())&&rBackApplyReq.getCheckedAll().equals("1")){
		    return true;
		}		
	      
        String orderId = rBackApplyReq.getOrderId();
		// 查询主订单的信息，取出总数量
        OrdMain ordMain = ordMainSV.queryOrderByOrderId(orderId);
        Long orderAmounts = ordMain.getOrderAmount();
        // 查询本次退货申请的总数量
        Long applyOrderAmounts = 0l;
        if (!CollectionUtils.isEmpty(rBackApplyReq.getrBackOrdSubReqs())) {
            for (RBackOrdSubReq rBackOrdSubReq : rBackApplyReq.getrBackOrdSubReqs()) {
                applyOrderAmounts = applyOrderAmounts + rBackOrdSubReq.getNum();
            }
        }
        //查询已经退货的总数量
        OrdBackApplyCriteria ordBackApplyCriteria = new OrdBackApplyCriteria();
        ordBackApplyCriteria.createCriteria().andOrderIdEqualTo(orderId)
                .andStatusEqualTo(BackConstants.Status.REFUNDED);
        List<OrdBackApply> orderApplyList = ordBackApplyMapper
                .selectByExample(ordBackApplyCriteria);
        Long backOrderAmounts = 0l;
        if (!CollectionUtils.isEmpty(orderApplyList)) {
            for (OrdBackApply ordBackApply : orderApplyList) {
                backOrderAmounts = backOrderAmounts + ordBackApply.getNum();
            }
        }
        // 合计总的退货数量
        Long sumBackOrderAmounts = applyOrderAmounts + backOrderAmounts;
        if (orderAmounts.longValue() == sumBackOrderAmounts.longValue()) {
            return true;
        }
        
		return false;
	}



	/**
	 * 计算是否最后一次退款退货
	 * 
	 * @param
	 * @param
	 * @return
	 */
	private boolean isLastBackBatch(RBackReviewReq info,List<RBackGdsResp> backList) {
	    // 返回1代表是最后一笔退款退货
		if (BackConstants.ApplyType.REFUND.equals(info.getApplyType())) {
			return true;
		}
		String orderId = info.getOrderId();
		// 查询主订单的信息，取出总数量
		OrdMain ordMain = ordMainSV.queryOrderByOrderId(orderId);
		Long orderAmounts = ordMain.getOrderAmount();
		// 查询本次退货申请的总数量
		Long applyOrderAmounts = 0l;
		if (!CollectionUtils.isEmpty(backList)) {
			for (RBackGdsResp rBackGdsResp : backList) {
				applyOrderAmounts = applyOrderAmounts + rBackGdsResp.getNum();
			}
		}
		// 查询已经退货的总数量
		OrdBackApplyCriteria ordBackApplyCriteria = new OrdBackApplyCriteria();
		ordBackApplyCriteria.createCriteria().andOrderIdEqualTo(orderId)
				.andStatusEqualTo(BackConstants.Status.REFUNDED);
		List<OrdBackApply> orderApplyList = ordBackApplyMapper
				.selectByExample(ordBackApplyCriteria);
		Long backOrderAmounts = 0l;
		if (!CollectionUtils.isEmpty(orderApplyList)) {
			for (OrdBackApply ordBackApply : orderApplyList) {
				backOrderAmounts = backOrderAmounts + ordBackApply.getNum();
			}
		}
		// 合计总的退货数量
		Long sumBackOrderAmounts = applyOrderAmounts + backOrderAmounts;
		if (orderAmounts.longValue() == sumBackOrderAmounts.longValue()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 计算是否最后第一次退款退货
	 * 
	 * @param
	 * @param
	 * @return
	 */
	private boolean isFirstBackBatch(RBackReviewReq info) {
	    // 返回1代表是最后一笔退款退货
		if (BackConstants.ApplyType.REFUND.equals(info.getApplyType())) {
			return true;
		}
		String orderId = info.getOrderId();
		// 查询已经退货的总数量
		OrdBackApplyCriteria ordBackApplyCriteria = new OrdBackApplyCriteria();
		ordBackApplyCriteria.createCriteria().andOrderIdEqualTo(orderId)
				.andStatusNotEqualTo(BackConstants.Status.REFUSE).andIdNotEqualTo(info.getBackId());
		List<OrdBackApply> orderApplyList = ordBackApplyMapper
				.selectByExample(ordBackApplyCriteria);
        if(CollectionUtils.isEmpty(orderApplyList)){
        	return true;
        }else{
        	return false;
        }
	}
	
}
