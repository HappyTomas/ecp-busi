package com.zengshi.ecp.order.service.busi.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.coupon.dubbo.dto.req.CoupConsumeReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupInfoReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupOrdBackReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupConsumeRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupInfoRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupOrdNumBackRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.OrdNumRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupDetailRSV;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupRSV;
import com.zengshi.ecp.order.dao.mapper.busi.OrdBackApplyMapper;
import com.zengshi.ecp.order.dao.mapper.busi.OrdBackDiscountMapper;
import com.zengshi.ecp.order.dao.mapper.busi.OrdBackGdsMapper;
import com.zengshi.ecp.order.dao.mapper.busi.OrdBackTrackMapper;
import com.zengshi.ecp.order.dao.mapper.busi.OrdSubMapper;
import com.zengshi.ecp.order.dao.model.OrdBackApply;
import com.zengshi.ecp.order.dao.model.OrdBackApplyCriteria;
import com.zengshi.ecp.order.dao.model.OrdBackDiscount;
import com.zengshi.ecp.order.dao.model.OrdBackDiscountCriteria;
import com.zengshi.ecp.order.dao.model.OrdBackGds;
import com.zengshi.ecp.order.dao.model.OrdBackGdsCriteria;
import com.zengshi.ecp.order.dao.model.OrdBackTrack;
import com.zengshi.ecp.order.dao.model.OrdBackTrackCriteria;
import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dao.model.OrdSub;
import com.zengshi.ecp.order.dubbo.dto.RBackApplyOrdReq;
import com.zengshi.ecp.order.dubbo.dto.RBackApplyReq;
import com.zengshi.ecp.order.dubbo.dto.RBackCouponResp;
import com.zengshi.ecp.order.dubbo.dto.RBackDiscountResp;
import com.zengshi.ecp.order.dubbo.dto.RBackOrdSubReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdReturnRefundReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdReturnRefundResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndSubInfo;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsMain;
import com.zengshi.ecp.order.dubbo.util.BackConstants;
import com.zengshi.ecp.order.dubbo.util.LongUtils;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackApplySV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackDiscountSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackTrackSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrderBackSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.OrderAcctMainResDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderAcctSubResDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackMainReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackSubReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffUnionRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffUnionRWRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

public class OrderBackSVImpl implements IOrderBackSV {

    @Resource
    private IOrdMainSV ordMainSV;
    
    @Resource
    private IOrdSubSV ordSubSV;
    
    @Resource   
    private IOrdBackDiscountSV ordBackDiscountSV;
    
    @Resource
    private IStaffUnionRSV staffUnionRSV;
    
    @Resource   
    private ICoupDetailRSV coupDetailRSV;   
    
    @Resource
    private IStaffUnionRWRSV staffUnionCoreRWRSV;
        
    @Resource
    private IOrdBackTrackSV ordBackTrackSV;
    
    @Resource
    private OrdBackTrackMapper ordBackTrackMapper;
    
    @Resource
    private OrdBackGdsMapper ordBackGdsMapper;
    
    @Resource
    private OrdSubMapper ordSubMapper;
    
    @Resource
    private OrdBackDiscountMapper ordBackDiscountMapper;
        
    @Resource
    private OrdBackApplyMapper ordBackApplyMapper;
    
    @Resource
    private ICoupRSV coupRSV;
    
    private static final String MODULE = OrderBackSVImpl.class.getName();
    
	 /**
     * 
     * TODO 计算退款和退货时应退回的积分、资金账户和. 
     * @see com.zengshi.ecp.pmph.service.busi.interfaces.IReturnBackSV#calCulateBackInfo(java.util.List)
     */
	@Override
	public ROrdReturnRefundResp calCulateBackInfo(RBackApplyOrdReq resp) {
		// TODO Auto-generated method stub
		 ROrdReturnRefundResp ordReturnRefundResp = new ROrdReturnRefundResp();
	        
	        if(resp.getApplyType().equals(BackConstants.ApplyType.BACK_GDS)){
	            SOrderDetailsMain sOrderDetailsMain = ordMainSV.queryOrderDetailsMain(resp.getSOrderDetailsMain().getId());
	            resp.setSOrderDetailsMain(sOrderDetailsMain);
	        }
	        boolean isLastFlag = isLastBackBatch(resp);
	        long scale = calCulateScaleApply(resp);
	        long backMoney = calCulateMoneyApply(resp, scale,isLastFlag);
	        long sumUsedScore = 0l;
	        long curScore = 0l;
	        ordReturnRefundResp.setScale(scale);
	        ordReturnRefundResp.setBackMoney(backMoney);
	       
	        ScoreInfoResDTO scoreInfo = staffUnionRSV.findScoreInfoByStaffId(resp.getSOrderDetailsMain().getCreateStaff());
	        if(scoreInfo!=null){
	        	curScore = scoreInfo.getScoreTotal()-scoreInfo.getScoreUsed()-scoreInfo.getScoreFrozen();
	        }
	        
	        ordReturnRefundResp.setCurScore(curScore);
	        //积分相关信息
	        OrderBackMainReqDTO<OrderBackSubReqDTO> socreOrderBackMainReqDTO = new OrderBackMainReqDTO<OrderBackSubReqDTO>();
	        socreOrderBackMainReqDTO.setStaffId(resp.getSOrderDetailsMain().getCreateStaff());
	        socreOrderBackMainReqDTO.setOrderId(resp.getSOrderDetailsMain().getId());
	        socreOrderBackMainReqDTO.setScale(scale);
	        socreOrderBackMainReqDTO.setSiteId(resp.getSOrderDetailsMain().getSiteId());
	        socreOrderBackMainReqDTO.setLastFlag(isLastFlag);
	        
	        long sumReduCulateScore = staffUnionCoreRWRSV.selTotalScoreByBackOrderAllRW(socreOrderBackMainReqDTO);
	
	        ordReturnRefundResp.setReduCulateScore(sumReduCulateScore);
	        List<RBackDiscountResp> rBackDiscountResps = new ArrayList<RBackDiscountResp>();   
	        List<RBackCouponResp> rBackCouponResps = new ArrayList<RBackCouponResp>();  
	        if(sumReduCulateScore>0l){
	            RBackDiscountResp ordBackDiscountResp = new RBackDiscountResp();
	            ordBackDiscountResp.setOrderId(resp.getSOrderDetailsMain().getId());
	            ordBackDiscountResp.setDiscountType(BackConstants.DiscountType.TYPE_SCORE_02);
	            ordBackDiscountResp.setProcType(BackConstants.ProcType.GIVE_BAKC_1);                
	            ordBackDiscountResp.setAmount(sumReduCulateScore);
	            rBackDiscountResps.add(ordBackDiscountResp);
	        }          
	        if(!CollectionUtils.isEmpty(resp.getrBackApplyOrdSubResps())) {
	            for (int i=0;i<resp.getrBackApplyOrdSubResps().size();i++) {
	                SBaseAndSubInfo sBaseAndSubInfo = new SBaseAndSubInfo();
	                sBaseAndSubInfo.setOrderId(resp.getSOrderDetailsMain().getId());
	                sBaseAndSubInfo.setOrderSubId(resp.getrBackApplyOrdSubResps().get(i).getOrderSubId());
	                OrdSub ordSub = ordSubSV.findByOrderSubId(sBaseAndSubInfo);
	                sumUsedScore = sumUsedScore + ordSub.getOrderSubScore();
	            }
	            if(sumUsedScore>0l){
	                RBackDiscountResp ordBackDiscountResp = new RBackDiscountResp();
	                ordBackDiscountResp.setOrderId(resp.getSOrderDetailsMain().getId());
	                ordBackDiscountResp.setDiscountType(BackConstants.DiscountType.TYPE_SCORE_02);
	                ordBackDiscountResp.setProcType(BackConstants.ProcType.USE_BACK_0);                
	                ordBackDiscountResp.setAmount(sumUsedScore);
	                rBackDiscountResps.add(ordBackDiscountResp);
	            }
	        }
	        
	        //保存资金帐户相关信息
	        OrderBackMainReqDTO<OrderBackSubReqDTO> accinfoOrderBackMainReqDTO = new OrderBackMainReqDTO<OrderBackSubReqDTO>();
	        accinfoOrderBackMainReqDTO.setOrderId(resp.getSOrderDetailsMain().getId());
	        accinfoOrderBackMainReqDTO.setStaffId(resp.getSOrderDetailsMain().getCreateStaff());
	        accinfoOrderBackMainReqDTO.setScale(scale);
	        accinfoOrderBackMainReqDTO.setLastFlag(isLastFlag);
	        OrderAcctMainResDTO<OrderAcctSubResDTO> OrderAcctMainList=staffUnionRSV.selAcctByBackOrder(accinfoOrderBackMainReqDTO);        
	        if(!StringUtil.isEmpty(OrderAcctMainList) && OrderAcctMainList!=null){
	            for(OrderAcctSubResDTO orderAcctSubResDTO:OrderAcctMainList.getList()){
	                RBackDiscountResp ordBackDiscountResp = new RBackDiscountResp();
	            
	                ordBackDiscountResp.setOrderId(resp.getSOrderDetailsMain().getId());
	                ordBackDiscountResp.setDiscountType(BackConstants.DiscountType.TYPE_ACCT_03);
	                ordBackDiscountResp.setProcType(BackConstants.ProcType.USE_BACK_0);
	                ordBackDiscountResp.setAcctType(orderAcctSubResDTO.getAcctType());
	                ordBackDiscountResp.setAmount(orderAcctSubResDTO.getBackMoney());
	                rBackDiscountResps.add(ordBackDiscountResp);
	            }
	        }  
	      
	        //保存优惠卷相关信息
	      
	        
	        // 查询已经退货的总数量
	        OrdBackApplyCriteria ordBackApplyCriteria = new OrdBackApplyCriteria();
	        ordBackApplyCriteria.createCriteria().andOrderIdEqualTo(resp.getSOrderDetailsMain().getId())
	                .andStatusNotEqualTo(BackConstants.Status.REFUSE);
	        long num = ordBackApplyMapper.countByExample(ordBackApplyCriteria);
	        if(num==0){
	            CoupOrdBackReqDTO coupReq=new CoupOrdBackReqDTO();
	            coupReq.setStaffId(resp.getSOrderDetailsMain().getCreateStaff());
	            coupReq.setOrderId(resp.getSOrderDetailsMain().getId());            
	            CoupOrdNumBackRespDTO coupRespList= coupDetailRSV.queryOrderCoup(coupReq);
	            if(coupRespList != null && CollectionUtils.isNotEmpty(coupRespList.getCoupNumBeans())){
	                for(OrdNumRespDTO ordNumRespDTO :coupRespList.getCoupNumBeans()){
	                    if(ordNumRespDTO.getCoupBackNum() == null || ordNumRespDTO.getCoupBackNum() == 0){
	                        continue;
	                    }
	                    RBackCouponResp ordBackCoupon = new RBackCouponResp();              
	                    ordBackCoupon.setProcType(BackConstants.ProcType.GIVE_BAKC_1);
	                    ordBackCoupon.setCouponTypeId(ordNumRespDTO.getCoupId());
	                    ordBackCoupon.setCouponTypeName(ordNumRespDTO.getCoupName());
	                    if(ordNumRespDTO.getCoupBackNum()>ordNumRespDTO.getCoupPresentNum()){
	                        ordBackCoupon.setCouponCnt(ordNumRespDTO.getCoupPresentNum());
	                    }else{
	                        ordBackCoupon.setCouponCnt(ordNumRespDTO.getCoupBackNum());
	                    }          
	                    rBackCouponResps.add(ordBackCoupon);
	                }
	            }
	        }
	        ordReturnRefundResp.setrBackCouponResps(rBackCouponResps);
	        ordReturnRefundResp.setrBackDiscountResps(rBackDiscountResps);
	        return ordReturnRefundResp;
	}
	
	 
    private boolean isLastBackBatch(RBackApplyOrdReq resp) throws BusinessException {
        // 返回1代表是最后一笔退款退货
        if (BackConstants.ApplyType.REFUND.equals(resp.getApplyType())) {
            return true;
        }                 
        String orderId = resp.getSOrderDetailsMain().getId();
        // 查询主订单的信息，取出总数量
        //OrdMain ordMain = ordMainSV.queryOrderByOrderId(orderId);
        Long orderAmounts = resp.getSOrderDetailsMain().getOrderAmount();
        // 查询本次退货申请的总数量
        Long applyOrderAmounts = 0l;
        if (!CollectionUtils.isEmpty(resp.getrBackApplyOrdSubResps())) {
            for (int i=0;i<resp.getrBackApplyOrdSubResps().size();i++) {
                applyOrderAmounts = applyOrderAmounts + resp.getrBackApplyOrdSubResps().get(i).getNum();
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
     * 计算退货总比例 
     * @param resp
     * @return
     * @throws BusinessException
     */
    @Override
    public long calCulateScaleApply(RBackApplyOrdReq resp) {        
        //退款
        if(BackConstants.ApplyType.REFUND.equals(resp.getApplyType())){
            return 1000000l;
        }    
        String orderId = resp.getSOrderDetailsMain().getId();
        Long discountMoneys = 0l;
        Long amontMoney = ordSubSV.querySumDiscountMoney(orderId);
        //非全部退款时只允许单个子订单商品退款，因而子订单编号只有一个
        if (!CollectionUtils.isEmpty(resp.getrBackApplyOrdSubResps())) {
            for (int i=0;i<resp.getrBackApplyOrdSubResps().size();i++) {
                SBaseAndSubInfo sBaseAndSubInfo = new SBaseAndSubInfo();
                sBaseAndSubInfo.setOrderId(orderId);
                sBaseAndSubInfo.setOrderSubId(resp.getrBackApplyOrdSubResps().get(i).getOrderSubId());
                OrdSub ordSub = ordSubSV.findByOrderSubId(sBaseAndSubInfo);
                discountMoneys = discountMoneys+ordSub.getDiscountMoney()*resp.getrBackApplyOrdSubResps().get(i).getNum()/ordSub.getOrderAmount();
            }
        }        
        return (new BigDecimal(discountMoneys*1000000).divide(new BigDecimal(amontMoney),2)).longValue();       
    }
    
    /**
     * 
     * TODO 计算退货总比例 
     * @see com.zengshi.ecp.pmph.service.busi.interfaces.IReturnBackSV#calCulateScaleApply(com.zengshi.ecp.order.dubbo.dto.RBackApplyReq, com.zengshi.ecp.order.dao.model.OrdBackApply, com.zengshi.ecp.order.dao.model.OrdMain)
     */
    @Override
    public long calCulateScaleApply(RBackApplyReq rBackApplyReq, OrdBackApply ordBackApply,OrdMain ordMain) throws BusinessException {

        //全部退货
     /*   if(StringUtil.isNotBlank(rBackApplyReq.getCheckedAll())&&rBackApplyReq.getCheckedAll().equals("1")){
            //查找子订单是否含有细腻商品
        	return 1000000l;
        }   */     
        String orderId = ordBackApply.getOrderId();
        Long discountMoneys = 0l;
        Long amontMoney = ordSubSV.querySumDiscountMoney(orderId);
        //非全部退款时只允许单个子订单商品退款，因而子订单编号只有一个
        if (!CollectionUtils.isEmpty(rBackApplyReq.getrBackOrdSubReqs())) {
            for (RBackOrdSubReq rBackOrdSubReq : rBackApplyReq.getrBackOrdSubReqs()) {
                SBaseAndSubInfo sBaseAndSubInfo = new SBaseAndSubInfo();
                sBaseAndSubInfo.setOrderId(orderId);
                sBaseAndSubInfo.setOrderSubId(rBackOrdSubReq.getOrderSubId());
                OrdSub ordSub = ordSubSV.findByOrderSubId(sBaseAndSubInfo);
                discountMoneys = discountMoneys+ordSub.getDiscountMoney()*rBackOrdSubReq.getNum()/ordSub.getOrderAmount();
            }
        }        
        return (new BigDecimal(discountMoneys*1000000).divide(new BigDecimal(amontMoney),2)).longValue();       
    }
    
    /**
     * 
     * calCulateBackInfo:(退货审核时查询当前退货积分和退款信息). <br/> 
     * 
     * @author lwy 
     * @param req
     * @return 
     * @since JDK 1.6
     */
    @Override
    public ROrdReturnRefundResp calCulateBackInfo (ROrderBackReq req){
        ROrdReturnRefundResp ordReturnRefundResp = new ROrdReturnRefundResp(); 
        SOrderDetailsMain sOrderDetailsMain = ordMainSV.queryOrderDetailsMain(req.getOrderId());
        OrdBackGdsCriteria ordBackGdsCriteria = new OrdBackGdsCriteria();
        ordBackGdsCriteria.createCriteria().andOrderIdEqualTo(req.getOrderId()).andBackIdEqualTo(req.getBackId());
        List<OrdBackGds> orderBackGdsList = ordBackGdsMapper.selectByExample(ordBackGdsCriteria);
        Long discountMoneys = 0l;
        for(OrdBackGds ordBackGds:orderBackGdsList){
            OrdSub ordsub = ordSubMapper.selectByPrimaryKey(ordBackGds.getOrderSubId());
            discountMoneys = discountMoneys+ordBackGds.getNum()*ordsub.getBasePrice();
        }
        boolean isLastFlag = isLastBackBatch(sOrderDetailsMain,orderBackGdsList);
        long scale = (new BigDecimal(discountMoneys*1000000).divide(new BigDecimal(sOrderDetailsMain.getBasicMoney()),2)).longValue();
        ordReturnRefundResp.setScale(scale); 
        
        long backMoney = new BigDecimal(sOrderDetailsMain.getRealMoney()*scale/1000000).longValue();    
        ordReturnRefundResp.setBackMoney(backMoney);
        long curScore = 0l;
        
        ScoreInfoResDTO scoreInfo = staffUnionRSV.findScoreInfoByStaffId(sOrderDetailsMain.getCreateStaff());
        if(scoreInfo!=null){
        	curScore = scoreInfo.getScoreTotal()-scoreInfo.getScoreUsed()-scoreInfo.getScoreFrozen();
        }
        ordReturnRefundResp.setCurScore(curScore);
        
        OrderBackMainReqDTO<OrderBackSubReqDTO> socreOrderBackMainReqDTO = new OrderBackMainReqDTO<OrderBackSubReqDTO>();
        socreOrderBackMainReqDTO.setStaffId(sOrderDetailsMain.getCreateStaff());
        socreOrderBackMainReqDTO.setOrderId(sOrderDetailsMain.getId());
        socreOrderBackMainReqDTO.setScale(scale);
        socreOrderBackMainReqDTO.setSiteId(sOrderDetailsMain.getSiteId());
       
        socreOrderBackMainReqDTO.setLastFlag(isLastFlag);
        long sumReduCulateScore=0l;
        if(req.getBackId()!=null&&req.getBackId()>0){
            //判断是否有过调整记录
            OrdBackTrackCriteria criteria = new OrdBackTrackCriteria();
            criteria.createCriteria().andBackIdEqualTo(req.getBackId()).andOrderIdEqualTo(req.getOrderId()).andNodeEqualTo("99");
            long num = ordBackTrackMapper.countByExample(criteria);
            if(num>0){
                //重新计算调整后应减扣积分
                OrderBackMainReqDTO<OrderBackSubReqDTO> socreOrderBackMain = new OrderBackMainReqDTO<OrderBackSubReqDTO>();
                socreOrderBackMain.setStaffId(sOrderDetailsMain.getCreateStaff());
                socreOrderBackMain.setOrderId(req.getOrderId());
                socreOrderBackMain.setScale(1000000l);
                socreOrderBackMain.setSiteId(sOrderDetailsMain.getSiteId());
                socreOrderBackMain.setLastFlag(true);
                
                //订单全部退货时减扣积分
                long totleScore=staffUnionCoreRWRSV.selTotalScoreByBackOrderAllRW(socreOrderBackMain);                    
                //实付金额
               // OrdMain ordMain = ordMainSV.queryOrderByOrderId(req.getOrderId());
                long realMoney = sOrderDetailsMain.getRealMoney();
                OrdBackApply ordBackApply  =ordBackApplyMapper.selectByPrimaryKey(req.getBackId());
                //计算新的冻结积分 
                sumReduCulateScore = (long) Math.floor(ordBackApply.getBackMoney()*totleScore*1.0/realMoney);
            }else{
                sumReduCulateScore=staffUnionCoreRWRSV.selTotalScoreByBackOrderAllRW(socreOrderBackMainReqDTO);
            }
        }else{
             sumReduCulateScore=staffUnionCoreRWRSV.selTotalScoreByBackOrderAllRW(socreOrderBackMainReqDTO);
        }
        ordReturnRefundResp.setReduCulateScore(sumReduCulateScore);
        ordReturnRefundResp.setLastFlag(isLastFlag);
        ordReturnRefundResp.setStaffId(sOrderDetailsMain.getCreateStaff());
        
        return ordReturnRefundResp;
    }
    
    
   
    /**
     * 
     * calCulateMoneyApply:(计算退款金额). <br/> 
     * 
     * @author lwy 
     * @param rBackApplyReq
     * @param ordBackApply
     * @param ordMain
     * @param scale
     * @return 
     * @since JDK 1.6
     */
    @Override
    public Long calCulateMoneyApply(RBackApplyReq rBackApplyReq, OrdBackApply ordBackApply, OrdMain ordMain,double scale,boolean isLastFlag) {
        Long realMoney=ordMain.getRealMoney();
        String applyType=rBackApplyReq.getApplyType();
        LogUtil.info(MODULE, "核心退货计价服务开始！");
        if(BackConstants.ApplyType.REFUND.equals(applyType)){
            return realMoney;
        }else{
            //全部退货
            if(StringUtil.isNotBlank(rBackApplyReq.getCheckedAll())&&rBackApplyReq.getCheckedAll().equals("1")||isLastFlag){
               if(scale==1000000L){
            	   Long result=calCulateLastApply(ordMain);
            	   LogUtil.info(MODULE, "核心6退货路线");
            	   return result;
               }else{
            	   double money = realMoney*scale/1000000;    //实付金额*比例        
            	   LogUtil.info(MODULE, "核心5退货单价:money"+money);
            	   return new BigDecimal(money).longValue();  
                   
               }
            } else {                       
        		//避免被向上取整出现9000xxx/1000000=10的情况
                double money = realMoney*scale/1000000;    //实付金额*比例
                LogUtil.info(MODULE, "核心3退货原来逻辑路线:scale"+scale);
                return  new BigDecimal(money).longValue();     
            	
            	
            }
        }
       
    }
    
    /**
     * 
     * calCulateMoneyApply:(计算退款金额). <br/> 
     * 
     * @author lwy 
     * @param
     * @param
     * @param
     * @param scale
     * @return 
     * @since JDK 1.6
     */
    @Override
    public Long calCulateMoneyApply(RBackApplyOrdReq resp,double scale,boolean isLastFlag) {
        Long realMoney=resp.getSOrderDetailsMain().getRealMoney();
        String applyType=resp.getApplyType();
        if(BackConstants.ApplyType.REFUND.equals(applyType)){
            return realMoney;
        }else{
            //全部退货
            if(isLastFlag){
                OrdMain ordMain = new OrdMain();
                ordMain.setId(resp.getSOrderDetailsMain().getId());
                ordMain.setRealMoney(resp.getSOrderDetailsMain().getRealMoney());
                Long result=calCulateLastApply(ordMain);
                return result;
            } else {             
            //避免被向上取整出现9000xxx/1000000=10的情况
            double money = realMoney*scale/1000000;    //实付金额*比例        
            return new BigDecimal(money).longValue();      
            }
        }
    }
    
    @Override
    public boolean modifyBackMoney(ROrdReturnRefundReq req) {

        long score = 0l;
        boolean isSuccess = false;
        try{                       
                OrderBackMainReqDTO<OrderBackSubReqDTO> socreOrderBackMainReq = new OrderBackMainReqDTO<OrderBackSubReqDTO>();
                socreOrderBackMainReq.setStaffId(req.getStaffId());
                socreOrderBackMainReq.setOrderId(req.getOrderId());
                socreOrderBackMainReq.setScale(1000000l);
                socreOrderBackMainReq.setSiteId(req.getCurrentSiteId());
                socreOrderBackMainReq.setLastFlag(false);
                
                //订单全部退货时减扣积分
                long sumReduCulateScore=staffUnionCoreRWRSV.selTotalScoreByBackOrderAllRW(socreOrderBackMainReq);                    
                //实付金额
                OrdMain ordMain = ordMainSV.queryOrderByOrderId(req.getOrderId());
                long realMoney = ordMain.getRealMoney();
               // Long amontMoney = ordSubSV.querySumDiscountMoney(req.getOrderId());
                //查询日志，判断是否首次调整
                //计算新的冻结积分   
                score = (long) Math.floor(req.getModifyBackMoney()*sumReduCulateScore*1.0/realMoney);
                long curScore = 0l;
                
                //查询用户当前积分               
                ScoreInfoResDTO scoreInfo = staffUnionRSV.findScoreInfoByStaffId(req.getStaffId());
                if(scoreInfo!=null){
                	curScore = scoreInfo.getScoreTotal()-scoreInfo.getScoreUsed()-scoreInfo.getScoreFrozen();
                }
                
                if(curScore+req.getReduCulateScore()<score){
                    score = curScore+req.getReduCulateScore();
                }
                
                //积分相关信息
                OrderBackMainReqDTO<OrderBackSubReqDTO> socreOrderBackMainReqDTO = new OrderBackMainReqDTO<OrderBackSubReqDTO>();              
                socreOrderBackMainReqDTO.setOrderId(req.getOrderId());//订单号
                socreOrderBackMainReqDTO.setBackId(req.getBackId());//退款退货编号
                socreOrderBackMainReqDTO.setBackScore(req.getReduCulateScore());//默认冻结积分
                socreOrderBackMainReqDTO.setModifyBackSocre(score);//调整后的冻结积分
                socreOrderBackMainReqDTO.setScale(req.getScale());//退货退款默认比例            
                socreOrderBackMainReqDTO.setStaffId(req.getStaffId());//买家
                socreOrderBackMainReqDTO.setLastFlag(req.isLastFlag());//是否最后一笔      
                //修改冻结积分
                Long ce = staffUnionCoreRWRSV.saveScoreFrozenModifyForOrderBackRW(socreOrderBackMainReqDTO);
                //差额为0，则未调整
                if(ce!=null&&ce.longValue()==0l){
                    score=req.getReduCulateScore();
                }
              
                isSuccess = true;
           // }
            if(isSuccess){
              
                //保存退款金额
                OrdBackApply record = new OrdBackApply();
                record.setBackMoney(req.getModifyBackMoney());
                record.setId(req.getBackId());
                record.setOrderId(req.getOrderId());
                record.setBackScore(score);
                
                
                OrdBackApplyCriteria criteria = new OrdBackApplyCriteria();
                criteria.createCriteria().andIdEqualTo(req.getBackId())
                .andOrderIdEqualTo(req.getOrderId());
                ordBackApplyMapper.updateByExampleSelective(record, criteria);
               
                
              //修改资金账户表中退款额信息
                OrdBackDiscount ordBackDiscount = new OrdBackDiscount();
                ordBackDiscount.setBackId(req.getBackId());
                ordBackDiscount.setOrderId(req.getOrderId());
                ordBackDiscount.setAmount(req.getModifyBackMoney());
                
                OrdBackDiscountCriteria discountCriteria = new OrdBackDiscountCriteria();
                discountCriteria.createCriteria().andBackIdEqualTo(req.getBackId()).andOrderIdEqualTo(req.getOrderId())
                .andDiscountTypeEqualTo(BackConstants.DiscountType.TYPE_MONEY_01);
                ordBackDiscountMapper.updateByExampleSelective(ordBackDiscount, discountCriteria);
                
               // if(!ordBackApply.getApplyType().equals(BackConstants.ApplyType.REFUND)){      
                    //修改积分信息
                    OrdBackDiscount ordBackDiscount1 = new OrdBackDiscount();
                    ordBackDiscount1.setBackId(req.getBackId());
                    ordBackDiscount1.setOrderId(req.getOrderId());
                    ordBackDiscount1.setAmount(score);
                    
                    OrdBackDiscountCriteria discountCriteria1 = new OrdBackDiscountCriteria();
                    discountCriteria1.createCriteria().andBackIdEqualTo(req.getBackId()).andOrderIdEqualTo(req.getOrderId())
                    .andDiscountTypeEqualTo(BackConstants.DiscountType.TYPE_SCORE_02);
                    ordBackDiscountMapper.updateByExampleSelective(ordBackDiscount1, discountCriteria1);
                }
                
                //保存操作信息
                OrdBackTrack ordBackTrack = new OrdBackTrack();
                ordBackTrack.setBackId(req.getBackId());
                ordBackTrack.setOrderId(req.getOrderId());
                ordBackTrack.setNode("99");
                ordBackTrack.setNodeDesc("调整退款金额"); 
                double backMoney = req.getBackMoney()/100.00;
                double modifyBackMoney = req.getModifyBackMoney()/100.00;
                //组装操作信息
                String memo = "退款金额由"+backMoney+"调整为"+modifyBackMoney+",调整原因:"+req.getMemo();
                ordBackTrack.setRemark(memo);
                
                ordBackTrack.setCreateStaff(req.getStaff().getId());
                ordBackTrack.setCreateTime(DateUtil.getSysDate());
                ordBackTrackSV.saveOrdBackTrack(ordBackTrack);
            //}
            return isSuccess;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    
    private boolean isLastBackBatch(SOrderDetailsMain sOrderDetailsMain,List<OrdBackGds> orderBackGdsList) throws BusinessException {                
        String orderId = sOrderDetailsMain.getId();
        // 查询主订单的信息，取出总数量
        //OrdMain ordMain = ordMainSV.queryOrderByOrderId(orderId);
        Long orderAmounts = sOrderDetailsMain.getOrderAmount();
        // 查询本次退货申请的总数量
        Long applyOrderAmounts = 0l;
        if (!CollectionUtils.isEmpty(orderBackGdsList)) {
            for (int i=0;i<orderBackGdsList.size();i++) {
                applyOrderAmounts = applyOrderAmounts + orderBackGdsList.get(i).getNum();
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
    
    private Long calCulateLastApply(OrdMain ordMain)
            throws BusinessException {
        Long realMoney = ordMain.getRealMoney();
        Long usedbackMoney=0l;
        // 最后一次退货，金额取实付金额-已退金额,返回积分取下单使用返回积分，扣减积分取下单赠送扣减积分，百分比，资金账户取使用回退资金账户，和百分比
        ROrderBackReq rOrderBackReq=new ROrderBackReq();
        rOrderBackReq.setOrderId(ordMain.getId());
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
        Long lastMoney=realMoney.longValue()-usedbackMoney.longValue();
        return lastMoney;
    }
    
    

	
}
