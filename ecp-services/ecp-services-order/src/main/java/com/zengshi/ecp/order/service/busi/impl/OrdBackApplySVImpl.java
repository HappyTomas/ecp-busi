package com.zengshi.ecp.order.service.busi.impl;

import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupOrdBackRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.OrdBackNumRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.OrdNumRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupDetailRSV;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.general.order.dto.CoupCheckBeanRespDTO;
import com.zengshi.ecp.general.order.dto.CoupDetailRespDTO;
import com.zengshi.ecp.order.dao.mapper.busi.OrdBackApplyMapper;
import com.zengshi.ecp.order.dao.model.*;
import com.zengshi.ecp.order.dubbo.dto.*;
import com.zengshi.ecp.order.dubbo.util.BackConstants;
import com.zengshi.ecp.order.dubbo.util.BackConstants.PayType;
import com.zengshi.ecp.order.dubbo.util.CommonConstants;
import com.zengshi.ecp.order.dubbo.util.LongUtils;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.PayStatus;
import com.zengshi.ecp.order.service.busi.interfaces.*;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dubbo.dto.*;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffUnionRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.db.sequence.Sequence;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrdBackApplySVImpl extends GeneralSQLSVImpl implements IOrdBackApplySV {
    
    @Resource
    private IThingLockSV thingLockSV;
    
    @Resource
    private IOrdMainSV ordMainSV;
    
    @Resource
    private IOrdSubSV ordSubSV;
    
    @Resource
    private IOrdTrackSV ordTrackSV;
    
    @Resource
    private IOrdBackGdsSV ordBackGdsSV;
    
    @Resource
    private IOrdBackPicSV ordBackPicSV;
    
    @Resource
    private IOrdBackCouponSV ordBackCouponSV;
    
    @Resource
    private IOrdBackDiscountSV ordBackDiscountSV;
    
    @Resource
    private IOrdBackGiftSV ordBackGiftSV;
    
    @Resource
    private IOrdBackTrackSV ordBackTrackSV;
    
    @Autowired(required = false)
    private IOrdBackShopIdxSV ordBackShopIdxSV;
    
    @Resource
    private IOrdBackStaffIdxSV ordBackStaffIdxSV;
    
    @Resource
    private IOrdCalculateSV ordCalculateSV;
    
    @Resource
    private IStaffUnionRSV staffUnionRSV;

    @Resource
    private ICoupDetailRSV coupDetailRSV;
    
    @Resource
    private IOrdPresentSV ordPresentSV;
    
    @Resource
    private OrdBackApplyMapper ordBackApplyMapper;
    
    @Resource
    private IOrdSubShopIdxSV ordSubShopIdxSV;
    
    @Resource(name = "seq_ord_back_apply")
    private Sequence seqOrdBackApply;

    private static final String MODULE = OrdBackApplySVImpl.class.getName();


    /** 
     * getBackGdsNum:计算退货的总数量. <br/> 
     * @author cbl 
     * @param rBackApplyReq
     * @return 
     * @since JDK 1.6 
     */ 
    private Long getBackGdsNum(RBackApplyReq rBackApplyReq){
        //计算退货总数量
        Long num = 0l;
        for(RBackOrdSubReq rBackOrdSubReq :rBackApplyReq.getrBackOrdSubReqs()){
            num += rBackOrdSubReq.getNum();
        }
        return num;
    }
    
    /** 
     * backReviewRefuse:拒绝退货. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    protected void updateBackReviewRefuse(RBackReviewReq rBackGdsReviewReq){
        OrdBackApply ordBackApply = new OrdBackApply();
        ordBackApply.setId(rBackGdsReviewReq.getBackId());
        ordBackApply.setOrderId(rBackGdsReviewReq.getOrderId());
        ordBackApply.setStatus(BackConstants.Status.REFUSE);
        ordBackApply.setUpdateStaff(rBackGdsReviewReq.getStaff().getId());
        ordBackApply.setUpdateTime(DateUtil.getSysDate());
        ordBackApply.setCheckDesc(rBackGdsReviewReq.getCheckDesc());
        updateBackApplyFromInput(ordBackApply);
        
        OrdBackTrack ordBackTrack = new OrdBackTrack();
        ordBackTrack.setBackId(rBackGdsReviewReq.getBackId());
        ordBackTrack.setOrderId(rBackGdsReviewReq.getOrderId());
        ordBackTrack.setNode(BackConstants.Status.REFUSE);
        if(BackConstants.ApplyType.BACK_GDS.equals(rBackGdsReviewReq.getApplyType().trim())){
            ordBackTrack.setNodeDesc(BackConstants.NodeDesc.GDS_REFUSE);
        } else if(BackConstants.ApplyType.REFUND.equals(rBackGdsReviewReq.getApplyType().trim())){
            ordBackTrack.setNodeDesc(BackConstants.NodeDesc.MONEY_REFUSE);
        }
        ordBackTrack.setRemark(rBackGdsReviewReq.getCheckDesc());
        ordBackTrack.setCreateStaff(rBackGdsReviewReq.getStaff().getId());
        ordBackTrack.setCreateTime(DateUtil.getSysDate());
        this.ordBackTrackSV.saveOrdBackTrack(ordBackTrack);
        
        //更新订单主表的二级状态为退货流程结束
        OrdMain om = this.ordMainSV.queryOrderByOrderId(rBackGdsReviewReq.getOrderId());
        if(om == null){
            LogUtil.debug(MODULE, "根据" + rBackGdsReviewReq.getOrderId() + "主订单号查询子订单数据为空");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312000);
        }
        OrdMain ordMain = new OrdMain();
        ordMain.setId(rBackGdsReviewReq.getOrderId());
        ordMain.setShopId(om.getShopId());
        ordMain.setStaffId(om.getStaffId());
        if(BackConstants.ApplyType.BACK_GDS.equals(rBackGdsReviewReq.getApplyType())){
            ordMain.setOrderTwoStatus(OrdConstants.OrderTwoStatus.STATUS_BACKGDS_NO);
        } else if(BackConstants.ApplyType.REFUND.equals(rBackGdsReviewReq.getApplyType())){
            ordMain.setOrderTwoStatus(OrdConstants.OrderTwoStatus.STATUS_REFUND_NO);
        }
        
        this.ordMainSV.updateOrderTwoStatusByOrderId(ordMain,rBackGdsReviewReq.getStaff().getId());
        ROrderBackReq rOrderBackReq = new ROrderBackReq();
        rOrderBackReq.setBackId(ordBackApply.getId());
        rOrderBackReq.setOrderId(ordBackApply.getOrderId());

        //解冻
        RBackApplyInfoResp rBackApplyInfoResp= queryBackShareInfo(rOrderBackReq);
        this.ordBackCouponSV.deleteOrdBackCouponByBackId(rOrderBackReq);
        this.ordBackDiscountSV.deleteOrdBackDiscountByBackId(rOrderBackReq);


        backRollback(rBackApplyInfoResp,"");
    }
    /** 
     * backReviewPass:同意退货\退款. <br/> 
     * @author cbl 
     * @param rBackGdsReviewReq 
     * @since JDK 1.6 
     */ 
    protected void updateBackReviewPass(RBackReviewReq rBackGdsReviewReq){
        
        //更新申请单审核通过状态
        OrdBackApply ordBackApply = new OrdBackApply();
        ordBackApply.setId(rBackGdsReviewReq.getBackId());
        ordBackApply.setOrderId(rBackGdsReviewReq.getOrderId());
        ordBackApply.setStatus(BackConstants.Status.REVIEW_PASS);
        ordBackApply.setPayType(rBackGdsReviewReq.getPayType());
        ordBackApply.setCheckDesc(rBackGdsReviewReq.getCheckDesc());
//        ordBackApply.setBackMoney(rBackGdsReviewReq.getrBackApplyInfoResp().getBackMoney());
        ordBackApply.setUpdateStaff(rBackGdsReviewReq.getStaff().getId());
        ordBackApply.setUpdateTime(DateUtil.getSysDate());
        updateBackApplyFromInput(ordBackApply);
        
        //保存分摊信息
//        saveShareInfo(rBackGdsReviewReq);
        
        OrdBackTrack ordBackTrack = new OrdBackTrack();
        ordBackTrack.setBackId(rBackGdsReviewReq.getBackId());
        ordBackTrack.setOrderId(rBackGdsReviewReq.getOrderId());
        ordBackTrack.setNode(BackConstants.Status.REVIEW_PASS);
        if(BackConstants.ApplyType.BACK_GDS.equals(rBackGdsReviewReq.getApplyType().trim())){
            ordBackTrack.setNodeDesc(BackConstants.NodeDesc.GDS_REVIEW_PASS);
        } else if(BackConstants.ApplyType.REFUND.equals(rBackGdsReviewReq.getApplyType().trim())){
            ordBackTrack.setNodeDesc(BackConstants.NodeDesc.MONEY_REVIEW_PASS);
        }
        ordBackTrack.setCreateStaff(rBackGdsReviewReq.getStaff().getId());
        ordBackTrack.setCreateTime(DateUtil.getSysDate());
        this.ordBackTrackSV.saveOrdBackTrack(ordBackTrack);
    }
    
    protected void saveShareInfo(OrdBackApply ordBackApply,RBackApplyInfoResp rBackApplyInfoResp){
//        保存退货退款金额
        OrdBackApply oba = new OrdBackApply();
        oba.setId(ordBackApply.getId());
        oba.setOrderId(ordBackApply.getOrderId());
        oba.setBackMoney(rBackApplyInfoResp.getBackMoney());
        oba.setUpdateStaff(ordBackApply.getUpdateStaff());
        oba.setUpdateTime(DateUtil.getSysDate());
        updateBackApplyFromInput(oba);

        OrdBackDiscount ordBackDiscount = new OrdBackDiscount();
        ordBackDiscount.setBackId(ordBackApply.getId());
        ordBackDiscount.setOrderId(ordBackApply.getOrderId());
        ordBackDiscount.setDiscountType(BackConstants.DiscountType.TYPE_MONEY_01);
        ordBackDiscount.setProcType(BackConstants.ProcType.USE_BACK_0);
        ordBackDiscount.setAmount(rBackApplyInfoResp.getBackMoney());
        this.ordBackDiscountSV.saveOrdBackDiscount(ordBackDiscount);
        
        //保存积分相关信息
        RBackApplyScoreResp rBackApplyScoreResp = rBackApplyInfoResp.getrBackApplyScoreResp();
        if(rBackApplyScoreResp != null){
            if(rBackApplyScoreResp.getSumUsedCulateScore().longValue() > 0){
                OrdBackDiscount ordBackDiscount1 = new OrdBackDiscount();
                ordBackDiscount1.setBackId(ordBackApply.getId());
                ordBackDiscount1.setOrderId(ordBackApply.getOrderId());
                ordBackDiscount1.setDiscountType(BackConstants.DiscountType.TYPE_SCORE_02);
                ordBackDiscount1.setProcType(BackConstants.ProcType.USE_BACK_0);
                ordBackDiscount1.setAmount(rBackApplyScoreResp.getSumUsedCulateScore());
                this.ordBackDiscountSV.saveOrdBackDiscount(ordBackDiscount1);
            }
            if(rBackApplyScoreResp.getSumReduCulateScore().longValue() > 0){
                OrdBackDiscount ordBackDiscount1 = new OrdBackDiscount();
                ordBackDiscount1.setBackId(ordBackApply.getId());
                ordBackDiscount1.setOrderId(ordBackApply.getOrderId());
                ordBackDiscount1.setDiscountType(BackConstants.DiscountType.TYPE_SCORE_02);
                ordBackDiscount1.setProcType(BackConstants.ProcType.GIVE_BAKC_1);
                ordBackDiscount1.setAmount(rBackApplyScoreResp.getSumReduCulateScore());
                this.ordBackDiscountSV.saveOrdBackDiscount(ordBackDiscount1);
            }
            
        }
        //保存资金帐户相关信息
        RBackApplyAccResp rBackApplyAccResp = rBackApplyInfoResp.getrBackApplyAccResp();
        if(rBackApplyAccResp != null && CollectionUtils.isNotEmpty(rBackApplyAccResp.getSumUsedCulateAccList())){
            for(OrderAcctSubResDTO orderAcctSubResDTO:rBackApplyAccResp.getSumUsedCulateAccList()){
                OrdBackDiscount ordBackDiscount1 = new OrdBackDiscount();
                ordBackDiscount1.setBackId(ordBackApply.getId());
                ordBackDiscount1.setOrderId(ordBackApply.getOrderId());
                ordBackDiscount1.setDiscountType(BackConstants.DiscountType.TYPE_ACCT_03);
                ordBackDiscount1.setProcType(BackConstants.ProcType.USE_BACK_0);
                ordBackDiscount1.setAcctType(orderAcctSubResDTO.getAcctType());
                ordBackDiscount1.setAmount(orderAcctSubResDTO.getBackMoney());
                this.ordBackDiscountSV.saveOrdBackDiscount(ordBackDiscount1);
            }
        }
        //保存优惠卷相关信息
        RBackApplyCoupResp rBackApplyCoupResp = rBackApplyInfoResp.getrBackApplyCoupResp();
        if(rBackApplyCoupResp != null && CollectionUtils.isNotEmpty(rBackApplyCoupResp.getBackApllyCoupList())){
            for(OrdNumRespDTO OrdNumRespDTO :rBackApplyCoupResp.getBackApllyCoupList()){
                if(OrdNumRespDTO.getCoupBackNum() == null || OrdNumRespDTO.getCoupBackNum() == 0){
                    continue;
                }
                OrdBackCoupon ordBackCoupon = new OrdBackCoupon();
                ordBackCoupon.setBackId(ordBackApply.getId());
                ordBackCoupon.setOrderId(ordBackApply.getOrderId());
                ordBackCoupon.setProcType(BackConstants.ProcType.GIVE_BAKC_1);
                ordBackCoupon.setCouponTypeId(OrdNumRespDTO.getCoupId());
                ordBackCoupon.setCouponTypeName(OrdNumRespDTO.getCoupName());
                ordBackCoupon.setCouponCnt(OrdNumRespDTO.getCoupBackNum());
                this.ordBackCouponSV.saveOrdBackCoupon(ordBackCoupon);
            }
        }
        //保存赠品相关信息
    }
    
    @Override
    public void updateBackApplyFromInput(OrdBackApply ordBackApply) throws BusinessException {
        OrdBackApplyCriteria omc = new OrdBackApplyCriteria();
        omc.createCriteria().andIdEqualTo(ordBackApply.getId()).andOrderIdEqualTo(ordBackApply.getOrderId());
        this.ordBackApplyMapper.updateByExampleSelective(ordBackApply, omc);
        this.ordBackShopIdxSV.updateBackApplyFromInput(ordBackApply);
        this.ordBackStaffIdxSV.updateBackApplyFromInput(ordBackApply);
        if(StringUtil.isNotBlank(ordBackApply.getStatus())){
            OrdBackGds ordBackGds = new OrdBackGds();
            ordBackGds.setBackId(ordBackApply.getId());
            ordBackGds.setOrderId(ordBackApply.getOrderId());
            ordBackGds.setStatus(ordBackApply.getStatus());
            this.ordBackGdsSV.updateOrdBackGdsFromInput(ordBackGds);
        }
    }
    
    @Override
    public Long saveOrdBackApply(OrdBackApply ordBackApply)  throws BusinessException{
        ordBackApply.setId(this.seqOrdBackApply.nextValue());
        this.ordBackApplyMapper.insert(ordBackApply);
        OrdBackStaffIdx ordBackStaffIdx = new OrdBackStaffIdx();
        ObjectCopyUtil.copyObjValue(ordBackApply, ordBackStaffIdx, null, false);
        ordBackStaffIdx.setBackId(ordBackApply.getId());
        ordBackStaffIdx.setPayTranNo(ordBackApply.getPayTranNo());
        this.ordBackStaffIdxSV.saveOrdBackStaffIdx(ordBackStaffIdx);
        
        OrdBackShopIdx ordBackShopIdx  = new OrdBackShopIdx();
        ObjectCopyUtil.copyObjValue(ordBackApply, ordBackShopIdx, null, false);
        ordBackShopIdx.setBackId(ordBackApply.getId());
        ordBackShopIdx.setPayTranNo(ordBackApply.getPayTranNo());
        this.ordBackShopIdxSV.saveOrdBackShopIdx(ordBackShopIdx);
        
        return ordBackApply.getId();
    }
    @Override
    public OrdBackApply queryOrdBackApplyByBackId(Long backId) throws BusinessException {
        OrdBackApplyCriteria obac = new OrdBackApplyCriteria();
        obac.createCriteria().andIdEqualTo(backId);
        List<OrdBackApply> obs = this.ordBackApplyMapper.selectByExample(obac);
        if(CollectionUtils.isEmpty(obs)){
            return null;
        }
        return obs.get(0);
    }
    @Override
    public RBackApplyResp queryOrdBackApply(ROrderBackReq rOrderBackReq)  throws BusinessException{
        OrdBackApplyCriteria obac = new OrdBackApplyCriteria();
        obac.createCriteria().andIdEqualTo(rOrderBackReq.getBackId()).andOrderIdEqualTo(rOrderBackReq.getOrderId());
        List<OrdBackApply> obs = this.ordBackApplyMapper.selectByExample(obac);
        if(CollectionUtils.isEmpty(obs)){
            return null;
        }
        RBackApplyResp rBackApplyResp = new RBackApplyResp();
        ObjectCopyUtil.copyObjValue(obs.get(0), rBackApplyResp, null, false);
        rBackApplyResp.setBackId(rOrderBackReq.getBackId());
        
        ROrdMainResponse rOrdMainResponse = this.ordMainSV.findOrdMianByOrderId(rOrderBackReq.getOrderId());
        if(StringUtil.isNotEmpty(rOrdMainResponse)){
            rBackApplyResp.setRealMoney(rOrdMainResponse.getRealMoney());
            rBackApplyResp.setPayTime(rOrdMainResponse.getPayTime());
        }
        List<RBackGdsResp> rBackGdsResps = this.ordBackGdsSV.queryBackGds(rOrderBackReq);
        Long orderScore = 0l;
        for(RBackGdsResp rBackGdsResp:rBackGdsResps){
            Long score =  0l;
            if(rBackGdsResp.getScore() != null){
                score = rBackGdsResp.getScore();
            }
            orderScore += score;
        }
        rBackApplyResp.setOrderScore(orderScore);
        return rBackApplyResp;
    }
    
   

    @Override
    public PageResponseDTO<ROrderBackResp> queryBackGdsByShop(ROrderBackReq rOrderBackReq)
            throws BusinessException {
        PageResponseDTO<ROrderBackResp> pageResponse = PageResponseDTO.buildByBaseInfo(rOrderBackReq, ROrderBackResp.class);
        
        PageResponseDTO<RBackApplyResp> psdoi = this.ordBackShopIdxSV.queryBackGdsByShop(rOrderBackReq);
        pageResponse.setCount(psdoi.getCount());
        pageResponse.setPageCount(psdoi.getPageCount());
        List<ROrderBackResp> rOrderBackResps = new ArrayList<ROrderBackResp>();
        
        if (CollectionUtils.isEmpty(psdoi.getResult())) {
            LogUtil.info(MODULE, "未找到订单数据 "+JSON.toJSONString(rOrderBackReq));
            pageResponse.setResult(null);
            return pageResponse;
        } else {
            for(RBackApplyResp rBackApplyResp: psdoi.getResult()){
                ROrderBackResp rOrderBackResp = new ROrderBackResp();
                ROrderBackReq rOrderBack = new ROrderBackReq();
                rOrderBack.setBackId(rBackApplyResp.getBackId());
                rOrderBack.setOrderId(rBackApplyResp.getOrderId());
                rOrderBackResp.setrBackApplyResp(this.queryOrdBackApply(rOrderBack));
                rOrderBackResp.setrBackGdsResps(this.ordBackGdsSV.queryBackGdsByBackId(rBackApplyResp));
                //查询主订单信息，获取支付方式和支付通道
                ROrdMainResponse orderMain = ordMainSV.findOrdMianByOrderId(rBackApplyResp.getOrderId());
                rOrderBackResp.setPayType(orderMain.getPayType());
                rOrderBackResp.setPayWay(orderMain.getPayWay());
                rOrderBackResps.add(rOrderBackResp);
            }
        }
        pageResponse.setResult(rOrderBackResps);
        return pageResponse;
    }

    @Override
    public PageResponseDTO<ROrderBackResp> queryBackGdsByStaff(ROrderBackReq rOrderBackReq)
            throws BusinessException {
        PageResponseDTO<ROrderBackResp> pageResponse = PageResponseDTO.buildByBaseInfo(rOrderBackReq, ROrderBackResp.class);
        
        PageResponseDTO<RBackApplyResp> psdoi = this.ordBackStaffIdxSV.queryBackGdsByStaff(rOrderBackReq);
        pageResponse.setCount(psdoi.getCount());
        pageResponse.setPageCount(psdoi.getPageCount());
        List<ROrderBackResp> rOrderBackResps = new ArrayList<ROrderBackResp>();
        
        if (CollectionUtils.isEmpty(psdoi.getResult())) {
            LogUtil.info(MODULE, "未找到订单数据 "+JSON.toJSONString(rOrderBackReq));
            pageResponse.setResult(null);
            return pageResponse;
        } else {
            for(RBackApplyResp rBackApplyResp: psdoi.getResult()){
                ROrderBackResp rOrderBackResp = new ROrderBackResp();
                ROrderBackReq rOrderBack = new ROrderBackReq();
                rOrderBack.setBackId(rBackApplyResp.getBackId());
                rOrderBack.setOrderId(rBackApplyResp.getOrderId());
                rOrderBackResp.setrBackApplyResp(this.queryOrdBackApply(rOrderBack));
                rOrderBackResp.setrBackGdsResps(this.ordBackGdsSV.queryBackGdsByBackId(rBackApplyResp));
                rOrderBackResps.add(rOrderBackResp);
            }
        }
        pageResponse.setResult(rOrderBackResps);
        return pageResponse;
    }

    @Override
    public RBackApplyOrdResp queryBackOrderSub(ROrderBackReq rOrderBackReq)
            throws BusinessException {
        RBackApplyOrdResp rBackApplyOrdResp = new RBackApplyOrdResp();
        
        SOrderDetailsMain sOrderDetailsMain = this.ordMainSV.queryOrderDetailsMain(rOrderBackReq.getOrderId());
        
        List<SOrderDetailsSub>  oss = this.ordSubSV.queryOrderDetailsSub(rOrderBackReq.getOrderId());
        if(CollectionUtils.isEmpty(oss)){
            LogUtil.debug(MODULE, "根据" + rOrderBackReq.getOrderId() + "主订单号查询子订单数据为空");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312000);
        }
        
        List<RBackApplyOrdSubResp> rBackApplyOrdSubResps = new ArrayList<RBackApplyOrdSubResp>();
        for(SOrderDetailsSub os :oss){
            RBackOrdSubReq rBackOrdSubReq = new RBackOrdSubReq();
            rBackOrdSubReq.setOrderId(rOrderBackReq.getOrderId());
            rBackOrdSubReq.setOrderSubId(os.getOrderSubId());
            
            RBackApplyOrdSubResp rbaos = new RBackApplyOrdSubResp();
            ObjectCopyUtil.copyObjValue(os, rbaos, null, false);
            rbaos.setBackStatus(BackConstants.IsProcess.NO);
            List<OrdBackGds> bgs =  this.ordBackGdsSV.queryBackGdsStatusByOrderSubId(rBackOrdSubReq);
            
           /* if(CollectionUtils.isNotEmpty(bgs)){
               rbaos.setBackStatus(BackConstants.IsProcess.YES);
            }*/
            
            //兼容部分退货改造
            rbaos.setBackStatus(BackConstants.IsProcess.NO);                       
            if(os.getGdsType()==2){
                rbaos.setBackStatus(BackConstants.IsProcess.OTH);
            }else if(bgs!=null){
                for(OrdBackGds ordBackGds:bgs){               
                    if(StringUtils.isBlank(ordBackGds.getStatus())
                            ||ordBackGds.getStatus().equals(BackConstants.Status.REFUNDED)
                            ||ordBackGds.getStatus().equals(BackConstants.Status.REFUSE)){
                       //不做处理
                    }else{
                        rbaos.setBackStatus(BackConstants.IsProcess.YES);
                        break;
                    }                      
                }
            }
           
            List<OrdBackGds> ordBackGdss =  this.ordBackGdsSV.queryHasBackBackGdsByOrderSubId(rBackOrdSubReq);
           
            Long hasBackNum = 0l;
            if(ordBackGdss!=null&&ordBackGdss.size()>0){
                for(OrdBackGds ordBackGds:ordBackGdss){
                    hasBackNum = hasBackNum+ordBackGds.getNum();
                }
            }
            rbaos.setNum(hasBackNum);
             
            rBackApplyOrdSubResps.add(rbaos);
        }
        
        rBackApplyOrdResp.setSOrderDetailsMain(sOrderDetailsMain);
        rBackApplyOrdResp.setrBackApplyOrdSubResps(rBackApplyOrdSubResps);
        
        return rBackApplyOrdResp;
    }

    protected OrdBackApply saveApplyDealOrderForMoney(RBackApplyReq rBackApplyReq){
        OrdMain om = this.ordMainSV.queryOrderByOrderId(rBackApplyReq.getOrderId());
        if(om == null){
            LogUtil.debug(MODULE, "根据" + rBackApplyReq.getOrderId() + "主订单号查询子订单数据为空");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312000);
        }
        OrdBackApply ordBackApply = new OrdBackApply();
        ObjectCopyUtil.copyObjValue(rBackApplyReq, ordBackApply, null, false);
        ordBackApply.setStaffId(om.getStaffId());
        ordBackApply.setShopId(om.getShopId());
        ordBackApply.setSiteId(om.getSiteId());
        ordBackApply.setOrderId(rBackApplyReq.getOrderId());
        ordBackApply.setApplyType(BackConstants.ApplyType.REFUND);
        ordBackApply.setStatus(BackConstants.Status.APPLY);
        ordBackApply.setApplyTime(DateUtil.getSysDate());
        ordBackApply.setIsEntire("1");  //最后一次退
        ordBackApply.setNum(getBackGdsNum(rBackApplyReq));
        ordBackApply.setCreateStaff(rBackApplyReq.getStaff().getId());
        ordBackApply.setCreateTime(DateUtil.getSysDate());
        ordBackApply.setUpdateStaff(null);
        ordBackApply.setUpdateTime(null);
        ordBackApply.setIsInAuditFile(PayStatus.ORD_MAIN_IS_IN_AUDIT_FILE_0);
        if(StringUtils.isNotBlank(om.getPayTranNo())){
        	ordBackApply.setPayTranNo(om.getPayTranNo());
        }
        LogUtil.debug(MODULE, JSON.toJSONString(ordBackApply));
        Long backId = this.saveOrdBackApply(ordBackApply);
        ordBackApply.setId(backId);
        //更新订单主表的二级状态为退款流程中
        OrdMain ordMain = new OrdMain();
        ordMain.setId(rBackApplyReq.getOrderId());
        ordMain.setShopId(om.getShopId());
        ordMain.setStaffId(om.getStaffId());
        ordMain.setOrderTwoStatus(OrdConstants.OrderTwoStatus.STATUS_REFUND_YES);
        this.ordMainSV.updateOrderTwoStatusByOrderId(ordMain,rBackApplyReq.getStaff().getId());

        List<OrdSub> oss = this.ordSubSV.queryOrderSubByOrderId(rBackApplyReq.getOrderId());
        if(CollectionUtils.isEmpty(oss)){
            LogUtil.debug(MODULE, "根据" + rBackApplyReq.getOrderId() + "主订单号查询子订单数据为空");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312000);
        }
        for(OrdSub os :oss){
            OrdBackGds ordBackGds = new OrdBackGds();
            ObjectCopyUtil.copyObjValue(os, ordBackGds, null, false);
            ordBackGds.setBackId(backId);
            ordBackGds.setOrderSubId(os.getId());
            ordBackGds.setNum(os.getOrderAmount());
            ordBackGds.setStatus(ordBackApply.getStatus());
            this.ordBackGdsSV.saveOrdBackGds(ordBackGds);
        }

        OrdBackTrack ordBackTrack = new OrdBackTrack();
        ordBackTrack.setBackId(backId);
        ordBackTrack.setOrderId(rBackApplyReq.getOrderId());
        ordBackTrack.setNode(BackConstants.Status.APPLY);
        ordBackTrack.setNodeDesc(BackConstants.NodeDesc.MONEY_APPLY);
        ordBackTrack.setCreateStaff(rBackApplyReq.getStaff().getId());
        ordBackTrack.setCreateTime(DateUtil.getSysDate());
        this.ordBackTrackSV.saveOrdBackTrack(ordBackTrack);
        return  ordBackApply;
    }

    protected OrdBackApply saveApplyDealOrderForGds(RBackApplyReq rBackApplyReq){
        OrdMain om = this.ordMainSV.queryOrderByOrderId(rBackApplyReq.getOrderId());
        if(om == null){
            LogUtil.debug(MODULE, "根据" + rBackApplyReq.getOrderId() + "主订单号查询子订单数据为空");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312000);
        }

        //判断是否最后一笔退货订单
        boolean lastFlag = ordCalculateSV.isLastBackBatch(rBackApplyReq);
        //保存申请表
        OrdBackApply ordBackApply = new OrdBackApply();
//        ObjectCopyUtil.copyObjValue(om, ordBackApply, null, false);
        ObjectCopyUtil.copyObjValue(rBackApplyReq, ordBackApply, null, false);
        ordBackApply.setStaffId(om.getStaffId());
        ordBackApply.setShopId(om.getShopId());
        ordBackApply.setSiteId(om.getSiteId());
        ordBackApply.setOrderId(rBackApplyReq.getOrderId());
        ordBackApply.setApplyType(BackConstants.ApplyType.BACK_GDS);
        ordBackApply.setStatus(BackConstants.Status.APPLY);
        ordBackApply.setApplyTime(DateUtil.getSysDate());
        if(lastFlag){
            ordBackApply.setIsEntire("1");
        } else {
            ordBackApply.setIsEntire("0");
        }

        ordBackApply.setNum(getBackGdsNum(rBackApplyReq));
        ordBackApply.setCreateStaff(rBackApplyReq.getStaff().getId());
        ordBackApply.setCreateTime(DateUtil.getSysDate());
        ordBackApply.setUpdateStaff(null);
        ordBackApply.setUpdateTime(null);
        ordBackApply.setIsInAuditFile(PayStatus.ORD_MAIN_IS_IN_AUDIT_FILE_0);
        if(StringUtils.isNotBlank(om.getPayTranNo())){
        	ordBackApply.setPayTranNo(om.getPayTranNo());
        }
        LogUtil.debug(MODULE, JSON.toJSONString(ordBackApply));
        Long backId = this.saveOrdBackApply(ordBackApply);
        ordBackApply.setId(backId);

        //更新订单主表的二级状态为退货流程中
        OrdMain ordMain = new OrdMain();
        ordMain.setId(rBackApplyReq.getOrderId());
        ordMain.setShopId(om.getShopId());
        ordMain.setStaffId(om.getStaffId());
        ordMain.setOrderTwoStatus(OrdConstants.OrderTwoStatus.STATUS_BACKGDS_YES);
        this.ordMainSV.updateOrderTwoStatusByOrderId(ordMain,rBackApplyReq.getStaff().getId());

        for(RBackOrdSubReq rBackOrdSubReq: rBackApplyReq.getrBackOrdSubReqs()){
            SBaseAndSubInfo sOrderAOrderSubInfo = new SBaseAndSubInfo();
            sOrderAOrderSubInfo.setOrderId(rBackApplyReq.getOrderId());
            sOrderAOrderSubInfo.setOrderSubId(rBackOrdSubReq.getOrderSubId());
            OrdSub os = this.ordSubSV.findByOrderSubId(sOrderAOrderSubInfo);
            if(os == null){
                LogUtil.info(MODULE, "未找到["+rBackOrdSubReq.getOrderSubId()+"]该子订单信息");
                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312006);
            }
            OrdBackGds ordBackGds = new OrdBackGds();
            ObjectCopyUtil.copyObjValue(os, ordBackGds, null, false);
            ordBackGds.setBackId(backId);
            ordBackGds.setOrderSubId(rBackOrdSubReq.getOrderSubId());
            ordBackGds.setStatus(ordBackApply.getStatus());
            
            //取从前店转入数量，存在非全部退货情况 author：lwy
            ordBackGds.setNum(rBackOrdSubReq.getNum());
            
           // ordBackGds.setNum(os.getOrderAmount());目前不取前店转入的数量，取数据库的数量全部退货
            this.ordBackGdsSV.saveOrdBackGds(ordBackGds);
        }
        //保存申请凭证
        for(String id:rBackApplyReq.getBackPicList()){
            if(StringUtil.isBlank(id)){
                continue;
            }
            OrdBackPic ordBackPic = new OrdBackPic();
            ordBackPic.setBackId(backId);
            ordBackPic.setOrderId(rBackApplyReq.getOrderId());
            ordBackPic.setPicType(BackConstants.PicType.APPLY_GDS);
            ordBackPic.setVfsId(id);
            this.ordBackPicSV.saveOrdBackPic(ordBackPic);
        }
        //保存申请日志
        OrdBackTrack ordBackTrack = new OrdBackTrack();
        ordBackTrack.setBackId(backId);
        ordBackTrack.setOrderId(rBackApplyReq.getOrderId());
        ordBackTrack.setNode(BackConstants.Status.APPLY);
        ordBackTrack.setNodeDesc(BackConstants.NodeDesc.GDS_APPLY);
        ordBackTrack.setCreateStaff(rBackApplyReq.getStaff().getId());
        ordBackTrack.setCreateTime(DateUtil.getSysDate());
        this.ordBackTrackSV.saveOrdBackTrack(ordBackTrack);


        return  ordBackApply;
    }

    protected void saveApplyDealStaff(RBackApplyReq rBackApplyReq,OrdBackApply ordBackApply,RBackApplyInfoResp rBackApplyInfoResp){

        RBackApplyScoreResp rBackApplyScoreResp = rBackApplyInfoResp.getrBackApplyScoreResp();
        if(rBackApplyScoreResp != null){
            LogUtil.info(MODULE, "冻结积分开始,冻结积分为"+rBackApplyScoreResp.getSumReduCulateScore());
            OrderBackMainReqDTO<OrderBackSubReqDTO> orderBackMainReqDTO = new OrderBackMainReqDTO<OrderBackSubReqDTO>();
            orderBackMainReqDTO.setStaffId(ordBackApply.getStaffId());
            orderBackMainReqDTO.setOrderId(ordBackApply.getOrderId());
            RBackReviewReq rBackReviewReq= new RBackReviewReq();
            rBackReviewReq.setApplyType(ordBackApply.getApplyType());
            rBackReviewReq.setBackId(ordBackApply.getId());
            rBackReviewReq.setOrderId(ordBackApply.getOrderId());
            orderBackMainReqDTO.setScale(rBackApplyInfoResp.getScale());
            orderBackMainReqDTO.setSiteId(ordBackApply.getSiteId());
            orderBackMainReqDTO.setLastFlag("1".equals(ordBackApply.getIsEntire()));
            List<OrderBackSubReqDTO> orderBackSubReqDTOs = new ArrayList<OrderBackSubReqDTO>();
            for(RBackOrdSubReq rBackOrdSubReq: rBackApplyReq.getrBackOrdSubReqs()){
                OrderBackSubReqDTO orderBackSubReqDTO = new OrderBackSubReqDTO();
                orderBackSubReqDTO.setOrderId(ordBackApply.getOrderId());
                orderBackSubReqDTO.setStaffId(ordBackApply.getStaffId());
                orderBackSubReqDTO.setSubOrderId(rBackOrdSubReq.getOrderSubId());
                SBaseAndSubInfo sOrderAOrderSubInfo = new SBaseAndSubInfo();
                sOrderAOrderSubInfo.setOrderSubId(rBackOrdSubReq.getOrderSubId());
                sOrderAOrderSubInfo.setOrderId(ordBackApply.getOrderId());
               
                //计算子订单分摊比例
                Long subAmount = ordSubSV.findByOrderSubId(sOrderAOrderSubInfo).getOrderAmount();
                Long scale = new BigDecimal(rBackOrdSubReq.getNum() * 1000000).divide(new BigDecimal(subAmount),2).longValue();
                orderBackSubReqDTO.setScale(scale);
                orderBackSubReqDTOs.add(orderBackSubReqDTO);
            }
            orderBackMainReqDTO.setList(orderBackSubReqDTOs);
            this.staffUnionRSV.saveScoreFrozenForOrderBack(orderBackMainReqDTO);
            LogUtil.info(MODULE, "冻结积分结束");
        }
    }

    protected void saveAppyDealCoup(RBackApplyReq rBackApplyReq,OrdBackApply ordBackApply,RBackApplyInfoResp rBackApplyInfoResp){
        //冻结优惠卷相关信息
        RBackApplyCoupResp rBackApplyCoupResp = rBackApplyInfoResp.getrBackApplyCoupResp();
        if(rBackApplyCoupResp != null && CollectionUtils.isNotEmpty(rBackApplyCoupResp.getBackApllyCoupList())){
            LogUtil.info(MODULE, "冻结优惠劵开始,冻结的优惠劵为"+JSON.toJSONString(rBackApplyCoupResp));
            CoupOrdBackRespDTO coupOrdBackRespDTO  = new CoupOrdBackRespDTO();
            coupOrdBackRespDTO.setOrderId(ordBackApply.getOrderId());
            coupOrdBackRespDTO.setStaffId(ordBackApply.getStaffId());
            List<OrdBackNumRespDTO> coupNumBeans = new ArrayList<OrdBackNumRespDTO>();
            for(OrdNumRespDTO ordNumRespDTO :rBackApplyCoupResp.getBackApllyCoupList()){
                OrdBackNumRespDTO ordBackNumRespDTO = new OrdBackNumRespDTO();
                ordBackNumRespDTO.setCoupId(ordNumRespDTO.getCoupId());
                Long num = 0l;
                if(ordNumRespDTO.getCoupBackNum().longValue() > ordNumRespDTO.getCoupPresentNum().longValue()){
                    num = ordNumRespDTO.getCoupPresentNum();
                } else {
                    num = ordNumRespDTO.getCoupBackNum();
                }
                if(num <= 0){
                    continue;
                }
                ordBackNumRespDTO.setCoupNum(num);
                ordNumRespDTO.setCoupBackNum(num);

                coupNumBeans.add(ordBackNumRespDTO);
            }
            if(CollectionUtils.isNotEmpty(coupNumBeans)){
                coupOrdBackRespDTO.setCoupNumBeans(coupNumBeans);
                coupOrdBackRespDTO.setapplyId(ordBackApply.getId());
                this.coupDetailRSV.blockCoup(coupOrdBackRespDTO);
                
                LogUtil.info(MODULE, "冻结优惠券结束");
            } else {
                LogUtil.info(MODULE, "无优惠券可冻结");
            }

        }
    }
    
    @Override
    public void saveBackGdsApply(RBackApplyReq rBackApplyReq) throws BusinessException {
        
        ThingLock thingLock = new ThingLock();
        thingLock.setId(rBackApplyReq.getOrderId());
        thingLock.setType(CommonConstants.LockType.LOCK_BACK_GDS_APPLY);
        //加锁
        this.thingLockSV.addThingLock(thingLock);

        //处理订单域相关信息
        OrdBackApply ordBackApply = this.saveApplyDealOrderForGds(rBackApplyReq);

        //保存分摊信息
        RBackApplyInfoResp rBackApplyInfoResp = this.ordCalculateSV.calCulateShareApply(rBackApplyReq,ordBackApply);
        saveShareInfo(ordBackApply,rBackApplyInfoResp);

        //冻结积分
        saveApplyDealStaff(rBackApplyReq,ordBackApply,rBackApplyInfoResp);

        try{
            //冻结优惠券
            saveAppyDealCoup(rBackApplyReq,ordBackApply,rBackApplyInfoResp);
        } catch (BusinessException be) {
            //积分补尝
            backRollback(rBackApplyInfoResp,"0");
            throw be;
        } catch (Exception e) {
            //积分补尝
            backRollback(rBackApplyInfoResp,"0");
            throw e;
        }
        //解锁
        this.thingLockSV.removeThingLock(thingLock);
    }
  
    @Override
    public void backRollback(RBackApplyInfoResp rBackApplyInfoResp, String flag) throws BusinessException {

        OrdBackApply ordBackApply = queryOrdBackApplyByBackId(rBackApplyInfoResp.getBackId());
        if("0".equals(flag)){
            //积分补尝
            if(rBackApplyInfoResp.getrBackApplyScoreResp() != null && rBackApplyInfoResp.getrBackApplyScoreResp().getSumReduCulateScore().longValue() != 0){
                ScoreFrozenBackReqDTO scoreFrozen= new ScoreFrozenBackReqDTO();
                scoreFrozen.setBackScore(rBackApplyInfoResp.getrBackApplyScoreResp().getSumReduCulateScore());
                scoreFrozen.setOrderId(ordBackApply.getOrderId());
                scoreFrozen.setSiteId(ordBackApply.getSiteId());
                scoreFrozen.setStaffId(ordBackApply.getStaffId());
                this.staffUnionRSV.saveScoreFrozenBack(scoreFrozen);
            }
        } else if("1".equals(flag)){
            //优惠卷补尝
            if(rBackApplyInfoResp.getrBackApplyCoupResp() != null){
                CoupCheckBeanRespDTO coupCheckBeanRespDTO = new CoupCheckBeanRespDTO();
                List<CoupDetailRespDTO> coupDetailRespDTOs = new ArrayList<>();
                for(OrdNumRespDTO ordNumRespDTO :rBackApplyInfoResp.getrBackApplyCoupResp().getBackApllyCoupList()){
                    CoupDetailRespDTO coupDetailRespDTO= new CoupDetailRespDTO();
                    coupDetailRespDTO.setId(ordNumRespDTO.getCoupId());
                    coupDetailRespDTO.setStaffId(ordBackApply.getStaffId());
                    coupDetailRespDTOs.add(coupDetailRespDTO);
                }
                coupCheckBeanRespDTO.setCoupDetails(coupDetailRespDTOs);
                coupCheckBeanRespDTO.setApplyId(rBackApplyInfoResp.getBackId());
                this.coupDetailRSV.restorePayCoup(coupCheckBeanRespDTO);
            }
        } else {
            //积分补尝
            if(rBackApplyInfoResp.getrBackApplyScoreResp() != null && rBackApplyInfoResp.getrBackApplyScoreResp().getSumReduCulateScore().longValue() != 0){
                ScoreFrozenBackReqDTO scoreFrozen= new ScoreFrozenBackReqDTO();
                scoreFrozen.setBackScore(rBackApplyInfoResp.getrBackApplyScoreResp().getSumReduCulateScore());
                scoreFrozen.setOrderId(ordBackApply.getOrderId());
                scoreFrozen.setSiteId(ordBackApply.getSiteId());
                scoreFrozen.setStaffId(ordBackApply.getStaffId());
                this.staffUnionRSV.saveScoreFrozenBack(scoreFrozen);
            }
            //优惠卷补尝
            if(rBackApplyInfoResp.getrBackApplyCoupResp() != null){
                CoupCheckBeanRespDTO coupCheckBeanRespDTO = new CoupCheckBeanRespDTO();
                List<CoupDetailRespDTO> coupDetailRespDTOs = new ArrayList<>();
                for(OrdNumRespDTO ordNumRespDTO :rBackApplyInfoResp.getrBackApplyCoupResp().getBackApllyCoupList()){
                    CoupDetailRespDTO coupDetailRespDTO= new CoupDetailRespDTO();
                    coupDetailRespDTO.setId(ordNumRespDTO.getCoupId());
                    coupDetailRespDTO.setStaffId(ordBackApply.getStaffId());
                    coupDetailRespDTOs.add(coupDetailRespDTO);
                }
                coupCheckBeanRespDTO.setCoupDetails(coupDetailRespDTOs);
                coupCheckBeanRespDTO.setApplyId(rBackApplyInfoResp.getBackId());
                this.coupDetailRSV.restorePayCoup(coupCheckBeanRespDTO);
            }
        }
    }

    @Override
    public void saveBackReview(RBackReviewReq rBackGdsReviewReq) throws BusinessException {
        
        ThingLock thingLock = new ThingLock();
        thingLock.setId(rBackGdsReviewReq.getBackId().toString());
        thingLock.setType(CommonConstants.LockType.LOCK_BACK_REVIEW);
        //加锁
        this.thingLockSV.addThingLock(thingLock);
        
        ROrderBackReq rOrderBackReq = new ROrderBackReq();
        rOrderBackReq.setOrderId(rBackGdsReviewReq.getOrderId());
        rOrderBackReq.setBackId(rBackGdsReviewReq.getBackId());
        RBackApplyResp  chkResp =this.queryOrdBackApply(rOrderBackReq);
        if(chkResp == null || !(BackConstants.ChkStatus.CHK_BACKGDS_REVIEW.contains(chkResp.getStatus()))){
            LogUtil.info(MODULE, "申请单状态不对"+rBackGdsReviewReq.getBackId()+chkResp.getStatus()+BackConstants.ChkStatus.CHK_BACKGDS_REVIEW);
            throw new BusinessException(MsgConstants.ChkMsgCode.CHK_BACKGDS_REVIEW);
        }
        
        if(BackConstants.Status.REFUSE.equals(rBackGdsReviewReq.getStatus())){
            updateBackReviewRefuse(rBackGdsReviewReq);
        } else {
            updateBackReviewPass(rBackGdsReviewReq);
        }
        
        //解锁
        this.thingLockSV.removeThingLock(thingLock);
    }
    @Override
    public void saveBackGdsReceipt(RBackConfirmReq rBackConfirmReq) throws BusinessException {
        
        //校验申请状态是否正确
        ROrderBackReq rOrderBackReq = new ROrderBackReq();
        rOrderBackReq.setOrderId(rBackConfirmReq.getOrderId());
        rOrderBackReq.setBackId(rBackConfirmReq.getBackId());
        RBackApplyResp  chkResp =this.queryOrdBackApply(rOrderBackReq);
        if(chkResp != null && "01".equals(chkResp.getStatus())){
            LogUtil.info(MODULE, "买家未发货"+rBackConfirmReq.getBackId()+chkResp.getStatus());
            throw new BusinessException(MsgConstants.ChkMsgCode.CHK_BACKGDS_BUYER_SEND);
        }
        if(chkResp == null || !(BackConstants.ChkStatus.CHK_BACKGDS_RECEIPT.contains(chkResp.getStatus()))){
            LogUtil.info(MODULE, "申请单状态不对"+rBackConfirmReq.getBackId()+chkResp.getStatus()+BackConstants.ChkStatus.CHK_BACKGDS_RECEIPT);
            throw new BusinessException(MsgConstants.ChkMsgCode.CHK_BACKGDS_RECEIPT);
        }
        
        OrdBackApply ordBackApply = new OrdBackApply();
        ordBackApply.setId(rBackConfirmReq.getBackId());
        ordBackApply.setOrderId(rBackConfirmReq.getOrderId());
        ordBackApply.setStatus(BackConstants.Status.WAIT_REFUND);
        ordBackApply.setUpdateStaff(rBackConfirmReq.getStaff().getId());
        ordBackApply.setUpdateTime(DateUtil.getSysDate());
        updateBackApplyFromInput(ordBackApply);
        
        OrdBackTrack ordBackTrack = new OrdBackTrack();
        ordBackTrack.setBackId(rBackConfirmReq.getBackId());
        ordBackTrack.setOrderId(rBackConfirmReq.getOrderId());
        ordBackTrack.setNode(BackConstants.Status.WAIT_REFUND);
        ordBackTrack.setNodeDesc(BackConstants.NodeDesc.WAIT_REFUND);
        ordBackTrack.setCreateStaff(rBackConfirmReq.getStaff().getId());
        ordBackTrack.setCreateTime(DateUtil.getSysDate());
        this.ordBackTrackSV.saveOrdBackTrack(ordBackTrack);
    }
    @Override
    public void saveBackGdsSend(RBackConfirmReq rBackConfirmReq) throws BusinessException {
        
        OrdBackApply ordBackApply = new OrdBackApply();
        ObjectCopyUtil.copyObjValue(rBackConfirmReq, ordBackApply, null, false);
        ordBackApply.setId(rBackConfirmReq.getBackId());
        ordBackApply.setOrderId(rBackConfirmReq.getOrderId());
        ordBackApply.setStatus(BackConstants.Status.SEND);
        ordBackApply.setUpdateStaff(rBackConfirmReq.getStaff().getId());
        ordBackApply.setUpdateTime(DateUtil.getSysDate());
        updateBackApplyFromInput(ordBackApply);
        if(CollectionUtils.isNotEmpty(rBackConfirmReq.getBackPicList())){
            for(String id:rBackConfirmReq.getBackPicList()){
                OrdBackPic ordBackPic = new OrdBackPic();
                ordBackPic.setBackId(rBackConfirmReq.getBackId());
                ordBackPic.setOrderId(rBackConfirmReq.getOrderId());
                ordBackPic.setPicType(BackConstants.PicType.SEND_GDS);
                ordBackPic.setVfsId(id);
                this.ordBackPicSV.saveOrdBackPic(ordBackPic);
            }
        }
        OrdBackTrack ordBackTrack = new OrdBackTrack();
        ordBackTrack.setBackId(rBackConfirmReq.getBackId());
        ordBackTrack.setOrderId(rBackConfirmReq.getOrderId());
        ordBackTrack.setNode(BackConstants.Status.SEND);
        ordBackTrack.setNodeDesc(BackConstants.NodeDesc.SEND);
        ordBackTrack.setCreateStaff(rBackConfirmReq.getStaff().getId());
        ordBackTrack.setCreateTime(DateUtil.getSysDate());
        this.ordBackTrackSV.saveOrdBackTrack(ordBackTrack);
    }
    @Override
    public void saveBackGdsPayed(RBackConfirmReq rBackConfirmReq) throws BusinessException {
        ThingLock thingLock = new ThingLock();
        thingLock.setId(rBackConfirmReq.getBackId().toString());
        thingLock.setType(CommonConstants.LockType.LOCK_BACK_PAYED);
        //加锁
        this.thingLockSV.addThingLock(thingLock);
        //校验申请状态是否正确
        ROrderBackReq rOrderBackReq = new ROrderBackReq();
        rOrderBackReq.setOrderId(rBackConfirmReq.getOrderId());
        rOrderBackReq.setBackId(rBackConfirmReq.getBackId());
        RBackApplyResp  chkResp =this.queryOrdBackApply(rOrderBackReq);
        if(BackConstants.ApplyType.REFUND.equals(chkResp.getApplyType().trim())){
            if(chkResp == null || !(BackConstants.ChkStatus.CHK_REFUND_REFUND.contains(chkResp.getStatus()))){
                LogUtil.info(MODULE, "申请单状态不对"+rBackConfirmReq.getBackId()+chkResp.getStatus()+BackConstants.ChkStatus.CHK_BACKGDS_REFUND);
                throw new BusinessException(MsgConstants.ChkMsgCode.CHK_REFUND_REFUND);
            }
        } else {
            if(chkResp == null || !(BackConstants.ChkStatus.CHK_BACKGDS_REFUND.contains(chkResp.getStatus()))){
                LogUtil.info(MODULE, "申请单状态不对"+rBackConfirmReq.getBackId()+chkResp.getStatus()+BackConstants.ChkStatus.CHK_BACKGDS_REFUND);
                throw new BusinessException(MsgConstants.ChkMsgCode.CHK_BACKGDS_REFUND);
            }
        }
        
        
        
        OrdBackApply ordBackApply = new OrdBackApply();
        ObjectCopyUtil.copyObjValue(rBackConfirmReq, ordBackApply,null, false);
        ordBackApply.setId(rBackConfirmReq.getBackId());
        ordBackApply.setOrderId(rBackConfirmReq.getOrderId());
        ordBackApply.setRefundTime(DateUtil.getSysDate());
        ordBackApply.setStatus(BackConstants.Status.REFUNDED);
        ordBackApply.setUpdateStaff(rBackConfirmReq.getStaff().getId());
        ordBackApply.setUpdateTime(DateUtil.getSysDate());
        updateBackApplyFromInput(ordBackApply);
        if(CollectionUtils.isNotEmpty(rBackConfirmReq.getBackPicList())){
            for(String id:rBackConfirmReq.getBackPicList()){
                if(StringUtil.isBlank(id)){
                    continue;
                }
                OrdBackPic ordBackPic = new OrdBackPic();
                ordBackPic.setBackId(rBackConfirmReq.getBackId());
                ordBackPic.setOrderId(rBackConfirmReq.getOrderId());
                ordBackPic.setPicType(BackConstants.PicType.REFUND);
                ordBackPic.setVfsId(id);
                this.ordBackPicSV.saveOrdBackPic(ordBackPic);
            }
        }
        OrdBackTrack ordBackTrack = new OrdBackTrack();
        ordBackTrack.setBackId(rBackConfirmReq.getBackId());
        ordBackTrack.setOrderId(rBackConfirmReq.getOrderId());
        ordBackTrack.setNode(BackConstants.Status.REFUNDED);
        ordBackTrack.setNodeDesc(BackConstants.NodeDesc.REFUNDED);
        ordBackTrack.setCreateStaff(rBackConfirmReq.getStaff().getId());
        ordBackTrack.setCreateTime(DateUtil.getSysDate());
        this.ordBackTrackSV.saveOrdBackTrack(ordBackTrack);
        
        ROrderBackReq rOrderBack = new ROrderBackReq();
        rOrderBack.setBackId(rBackConfirmReq.getBackId());
        rOrderBack.setOrderId(rBackConfirmReq.getOrderId());
        RBackApplyResp  rBackApplyResp = this.queryOrdBackApply(rOrderBack);    
        
        if(BackConstants.IsEntire.YES.equals(rBackApplyResp.getIsEntire())){
            SBaseAndStatusInfo sosi = new SBaseAndStatusInfo();
            sosi.setOrderId(rBackApplyResp.getOrderId());
            sosi.setShopId(rBackApplyResp.getShopId());
            sosi.setStaffId(rBackApplyResp.getStaffId());
            if(BackConstants.ApplyType.BACK_GDS.equals(rBackApplyResp.getApplyType())){
                sosi.setStatus(OrdConstants.OrderStatus.ORDER_STATUS_BACKGDS); // 收货
                sosi.setOrderTwoStatus(OrdConstants.OrderTwoStatus.STATUS_CANCLE_BACKGDS);
            } else if(BackConstants.ApplyType.REFUND.equals(rBackApplyResp.getApplyType())){
                sosi.setStatus(OrdConstants.OrderStatus.ORDER_STATUS_REFUND); // 收货
                sosi.setOrderTwoStatus(OrdConstants.OrderTwoStatus.STATUS_CANCLE_REFUND);
            }
            
            sosi.setOperatorId(rBackConfirmReq.getStaff().getId());
            this.ordMainSV.updateOrderStatus(sosi);
            
            OrdTrack ot = new OrdTrack();
            
            if(BackConstants.ApplyType.BACK_GDS.equals(rBackApplyResp.getApplyType())){
                ot.setNode(OrdConstants.OrderTwoStatus.STATUS_CANCLE_BACKGDS);
                ot.setNodeDesc(OrdConstants.NodeDesc.STATUS_CANCLE_BACKGDS);
            } else if(BackConstants.ApplyType.REFUND.equals(rBackApplyResp.getApplyType())){
                ot.setNode(OrdConstants.OrderTwoStatus.STATUS_CANCLE_REFUND);
                ot.setNodeDesc(OrdConstants.NodeDesc.STATUS_CANCLE_REFUND);
            }
            ot.setOrderId(sosi.getOrderId());
            ot.setCreateStaff(sosi.getOperatorId());
            ot.setCreateTime(DateUtil.getSysDate());
            this.ordTrackSV.saveOrdTrack(ot);
        } else {
            //更新订单主表的二级状态为退货流程结束
            OrdMain ordMain = new OrdMain();
            ordMain.setId(rBackApplyResp.getOrderId());
            ordMain.setShopId(rBackApplyResp.getShopId());
            ordMain.setStaffId(rBackApplyResp.getStaffId());
           
            if(BackConstants.ApplyType.BACK_GDS.equals(rBackApplyResp.getApplyType())){
                ordMain.setOrderTwoStatus(OrdConstants.OrderTwoStatus.STATUS_BACKGDS_NO);
            } else if(BackConstants.ApplyType.REFUND.equals(rBackApplyResp.getApplyType())){
                ordMain.setOrderTwoStatus(OrdConstants.OrderTwoStatus.STATUS_REFUND_NO);
            }
            
            this.ordMainSV.updateOrderTwoStatusByOrderId(ordMain,rBackConfirmReq.getStaff().getId());
        }
        
        //获取该退货单下的所有子订单退货明细  author:lwy
        List<RBackGdsResp>  list = ordBackGdsSV.queryBackGds(rOrderBackReq);
        //查找主订单信息
        ROrdMainResponse ordermain = ordMainSV.findOrdMianByOrderId(rBackApplyResp.getOrderId());
        //回写子订单已退货数量          
        for(RBackGdsResp backGds:list){        
            SBaseAndSubInfo sOrderAOrderSubInfo = new SBaseAndSubInfo();
            sOrderAOrderSubInfo.setOrderSubId( backGds.getOrderSubId());
            sOrderAOrderSubInfo.setOrderId(rBackApplyResp.getOrderId());
            OrdSub ordsub = ordSubSV.findByOrderSubId(sOrderAOrderSubInfo);
            if(ordsub.getHasBackNum()==null){
                ordsub.setHasBackNum(backGds.getNum());
            }else{
                ordsub.setHasBackNum(ordsub.getHasBackNum()+backGds.getNum());
            }    
            try{
            	LogUtil.debug(MODULE, "------开始计算退款金额-----：子订单号为："+backGds.getOrderSubId());
            	 //计算当前商品退款金额（全部退货时，有某些商品可能存在误差，不过此处用于索引表部分退货统计，可以忽略这个问题）
                double hadBackScale = this.calCulateScaleApply(ordsub ,backGds.getNum());
                Long hadBackMoney = new BigDecimal(ordermain.getRealMoney()*hadBackScale/1000000).longValue();    
                LogUtil.debug(MODULE, "------计算退款金额结束-----：子订单号为："+ordsub.getId()+",退款金额为："+hadBackMoney);
                ordSubShopIdxSV.updateSubOrderBackInfo(ordsub.getId(), hadBackMoney,ordsub.getHasBackNum());
                LogUtil.debug(MODULE, "------保存退款金额和数量结束-----：子订单号为："+ordsub.getId()+",退款金额为："+hadBackMoney);
                ordSubSV.updateOrderSubHasBackNum(ordsub);
            }catch(Exception e){
               // e.printStackTrace();
            }
        }        
        
        //解锁
        this.thingLockSV.removeThingLock(thingLock);
    }
    
    
    @Override
    public void saveBackMoneyApply(RBackApplyReq rBackApplyReq) throws BusinessException {
        ThingLock thingLock = new ThingLock();
        thingLock.setId(rBackApplyReq.getOrderId());
        thingLock.setType(CommonConstants.LockType.LOCK_BACK_MONEY_APPLY);
        //加锁
        this.thingLockSV.addThingLock(thingLock);

        //处理订单域相关信息
        OrdBackApply ordBackApply = this.saveApplyDealOrderForMoney(rBackApplyReq);

        //保存分摊信息
        RBackApplyInfoResp rBackApplyInfoResp = this.ordCalculateSV.calCulateShareApply(rBackApplyReq,ordBackApply);
        saveShareInfo(ordBackApply,rBackApplyInfoResp);

        //冻结积分
        saveApplyDealStaff(rBackApplyReq,ordBackApply,rBackApplyInfoResp);

        try{
            //冻结优惠券
            saveAppyDealCoup(rBackApplyReq,ordBackApply,rBackApplyInfoResp);
        } catch (BusinessException be) {
            //积分补尝
            if(rBackApplyInfoResp.getrBackApplyScoreResp() != null && rBackApplyInfoResp.getrBackApplyScoreResp().getSumReduCulateScore().longValue() != 0){
                ScoreFrozenBackReqDTO scoreFrozen= new ScoreFrozenBackReqDTO();
                scoreFrozen.setBackScore(rBackApplyInfoResp.getrBackApplyScoreResp().getSumReduCulateScore());
                scoreFrozen.setOrderId(ordBackApply.getOrderId());
                scoreFrozen.setSiteId(ordBackApply.getSiteId());
                scoreFrozen.setStaffId(ordBackApply.getStaffId());
                this.staffUnionRSV.saveScoreFrozenBack(scoreFrozen);
            }
            throw be;
        } catch (Exception e) {
            //积分补尝
            if(rBackApplyInfoResp.getrBackApplyScoreResp() != null && rBackApplyInfoResp.getrBackApplyScoreResp().getSumReduCulateScore().longValue() != 0){
                ScoreFrozenBackReqDTO scoreFrozen= new ScoreFrozenBackReqDTO();
                scoreFrozen.setBackScore(rBackApplyInfoResp.getrBackApplyScoreResp().getSumReduCulateScore());
                scoreFrozen.setOrderId(ordBackApply.getOrderId());
                scoreFrozen.setSiteId(ordBackApply.getSiteId());
                scoreFrozen.setStaffId(ordBackApply.getStaffId());
                this.staffUnionRSV.saveScoreFrozenBack(scoreFrozen);
            }
            throw e;
        }
        
        //解锁
        this.thingLockSV.removeThingLock(thingLock);
    }

    @Override
    public ROrderBackDetailResp queryBackDetails(ROrderBackReq rOrderBackReq)
            throws BusinessException {
        ROrderBackDetailResp rOrderBackDetailResp = new ROrderBackDetailResp();
        RBackApplyResp rBackApplyResp = this.queryOrdBackApply(rOrderBackReq);
        List<RBackGdsResp> rBackGdsResps = this.ordBackGdsSV.queryBackGds(rOrderBackReq);
        Long orderScore = 0l;
        for(RBackGdsResp rBackGdsResp:rBackGdsResps){
            Long score =  0l;
            if(rBackGdsResp.getScore() != null){
                score = rBackGdsResp.getScore();
            }
            orderScore += score;
        }
        rBackApplyResp.setOrderScore(orderScore);
        rOrderBackDetailResp.setrBackApplyResp(rBackApplyResp);
        rOrderBackDetailResp.setrBackGdsResps(rBackGdsResps);
        
        List<RBackCouponResp> rBackCouponResps = this.ordBackCouponSV.queryOrdBackCoupon(rOrderBackReq);
        List<OrdPresent> ordPresents = ordPresentSV.queryOrdPresentByOrderId(rOrderBackReq.getOrderId());
        if(CollectionUtils.isNotEmpty(rBackCouponResps)){
            List<OrdNumRespDTO> backApllyCoupList = new ArrayList<>();
            for(RBackCouponResp rBackCouponResp:rBackCouponResps){
                if(BackConstants.ProcType.GIVE_BAKC_1.equals(rBackCouponResp.getProcType())){
                    OrdNumRespDTO ordNumRespDTO = new OrdNumRespDTO();
                    ordNumRespDTO.setCoupId(rBackCouponResp.getCouponTypeId());
                    ordNumRespDTO.setCoupName(rBackCouponResp.getCouponTypeName());
                    ordNumRespDTO.setCoupBackNum(rBackCouponResp.getCouponCnt());
                    long num = 0l;
                    if(CollectionUtils.isNotEmpty(ordPresents)){
	                    for(OrdPresent ordPresent : ordPresents){
	                        if(StringUtil.isNotBlank(ordPresent.getCouponTypeId())&&ordPresent.getCouponTypeId().equals(""+rBackCouponResp.getCouponTypeId())){
	                            num +=ordPresent.getCouponCnt();
	                        }
	                    }      
	                    ordNumRespDTO.setCoupPresentNum(num);
	                    backApllyCoupList.add(ordNumRespDTO);
                    }
                }
            }
            rOrderBackDetailResp.setBackApllyCoupList(backApllyCoupList);
        }
        
        rOrderBackDetailResp.setrBackPicResps(this.ordBackPicSV.queryOrdBackPic(rOrderBackReq));
        rOrderBackDetailResp.setrBackTrackResps(this.ordBackTrackSV.queryOrdBackTrack(rOrderBackReq));
        rOrderBackDetailResp.setrBackDiscountResps(this.ordBackDiscountSV.queryOrdBackDiscount(rOrderBackReq));
        rOrderBackDetailResp.setrBackCouponResps(rBackCouponResps);
        rOrderBackDetailResp.setrBackGiftResps(this.ordBackGiftSV.queryOrdBackGift(rOrderBackReq));
       
        return rOrderBackDetailResp;
    }

    @Override
    public RBackReviewResp queryBackIdInfo(ROrderBackReq rOrderBackReq)
            throws BusinessException {
        RBackReviewResp rBackReviewResp= new RBackReviewResp();
        RBackApplyResp rBackApplyResp  = this.queryOrdBackApply(rOrderBackReq);
        rBackReviewResp.setrBackApplyResp(rBackApplyResp);
        rBackReviewResp.setrBackGdsResps(this.ordBackGdsSV.queryBackGds(rOrderBackReq));
        return rBackReviewResp;
    }

    @Override
    public RBackApplyInfoResp queryBackShareInfo(ROrderBackReq rOrderBackReq) throws BusinessException {
        RBackApplyInfoResp rBackApplyInfoResp = new RBackApplyInfoResp();
        rBackApplyInfoResp.setBackId(rOrderBackReq.getBackId());
        rBackApplyInfoResp.setOrderId(rOrderBackReq.getOrderId());
        List<RBackDiscountResp> rBackDiscountResps = this.ordBackDiscountSV.queryOrdBackDiscount(rOrderBackReq);
        if(CollectionUtils.isNotEmpty(rBackDiscountResps)){
            boolean soreflag = false;
            RBackApplyScoreResp rBackApplyScoreResp = new RBackApplyScoreResp();
            RBackApplyAccResp rBackApplyAccResp = new RBackApplyAccResp();
            List<OrderAcctSubResDTO> orderAcctSubResDTOs = new ArrayList<>();
            for(RBackDiscountResp rBackDiscountResp:rBackDiscountResps){
                if(BackConstants.DiscountType.TYPE_MONEY_01.equals(rBackDiscountResp.getDiscountType())
                        && BackConstants.ProcType.USE_BACK_0.equals(rBackDiscountResp.getProcType())){
                    rBackApplyInfoResp.setBackMoney(rBackDiscountResp.getAmount());
                } else if(BackConstants.DiscountType.TYPE_SCORE_02.equals(rBackDiscountResp.getDiscountType())
                        && BackConstants.ProcType.USE_BACK_0.equals(rBackDiscountResp.getProcType())){
                    soreflag = true;
                    rBackApplyScoreResp.setSumUsedCulateScore(rBackDiscountResp.getAmount());
                } else if(BackConstants.DiscountType.TYPE_SCORE_02.equals(rBackDiscountResp.getDiscountType())
                        && BackConstants.ProcType.GIVE_BAKC_1.equals(rBackDiscountResp.getProcType())){
                    soreflag = true;
                    rBackApplyScoreResp.setSumReduCulateScore(rBackDiscountResp.getAmount());
                } else if(BackConstants.DiscountType.TYPE_ACCT_03.equals(rBackDiscountResp.getDiscountType())
                        && BackConstants.ProcType.USE_BACK_0.equals(rBackDiscountResp.getProcType())){
                    OrderAcctSubResDTO orderAcctSubResDTO = new OrderAcctSubResDTO();
                    orderAcctSubResDTO.setAcctType(rBackDiscountResp.getAcctType());
                    orderAcctSubResDTO.setAcctTypeName("");
                    orderAcctSubResDTO.setBackMoney(rBackDiscountResp.getAmount());
                    orderAcctSubResDTOs.add(orderAcctSubResDTO);
                }
            }
            if(soreflag){
                rBackApplyInfoResp.setrBackApplyScoreResp(rBackApplyScoreResp);
            }
            if(CollectionUtils.isNotEmpty(orderAcctSubResDTOs)){
                rBackApplyAccResp.setSumUsedCulateAccList(orderAcctSubResDTOs);
                rBackApplyInfoResp.setrBackApplyAccResp(rBackApplyAccResp);
            }
        }
        List<RBackCouponResp> rBackCouponResps = this.ordBackCouponSV.queryOrdBackCoupon(rOrderBackReq);
        List<OrdPresent> ordPresents = ordPresentSV.queryOrdPresentByOrderId(rOrderBackReq.getOrderId());
        if(CollectionUtils.isNotEmpty(rBackCouponResps)){
            List<OrdNumRespDTO> backApllyCoupList = new ArrayList<>();
            for(RBackCouponResp rBackCouponResp:rBackCouponResps){
                if(BackConstants.ProcType.GIVE_BAKC_1.equals(rBackCouponResp.getProcType())){
                    OrdNumRespDTO ordNumRespDTO = new OrdNumRespDTO();
                    ordNumRespDTO.setCoupId(rBackCouponResp.getCouponTypeId());
                    ordNumRespDTO.setCoupName(rBackCouponResp.getCouponTypeName());
                    ordNumRespDTO.setCoupBackNum(rBackCouponResp.getCouponCnt());
                    long num = 0l;
                    for(OrdPresent ordPresent : ordPresents){
                        if(StringUtil.isNotBlank(ordPresent.getCouponTypeId())&&ordPresent.getCouponTypeId().equals(""+rBackCouponResp.getCouponTypeId())){
                            num +=ordPresent.getCouponCnt();
                        }
                    }      
                    ordNumRespDTO.setCoupPresentNum(num);
                    backApllyCoupList.add(ordNumRespDTO);
                }
            }
            RBackApplyCoupResp rBackApplyCoupResp = new RBackApplyCoupResp();
            rBackApplyCoupResp.setBackApllyCoupList(backApllyCoupList);
            rBackApplyInfoResp.setrBackApplyCoupResp(rBackApplyCoupResp);
        }
       
        return rBackApplyInfoResp;
    }

    @Override
    public RBackReviewResp queryBackReview(ROrderBackReq rOrderBackReq) throws BusinessException {
        RBackReviewResp rBackReviewResp= new RBackReviewResp();
        RBackApplyResp rBackApplyResp  = this.queryOrdBackApply(rOrderBackReq);
        
        SOrderDetailsMain sOrderDetailsMain = this.ordMainSV.queryOrderDetailsMain(rOrderBackReq.getOrderId());
       /* if("0".equals(sOrderDetailsMain.getPayType())){
            rBackApplyResp.setPayType("1");  //线上退款
        } else {
            rBackApplyResp.setPayType("0");  //线下退款
        }*/
        //鸿支付升级为1级用户，可以线上退款
//        if("9002".equals(sOrderDetailsMain.getPayWay())){
//            rBackApplyResp.setPayType("0");  //线下退款
//        }
        rBackReviewResp.setOrderDetailsMain(sOrderDetailsMain);
        RBackReviewReq rBackReviewReq = new RBackReviewReq();
        rBackReviewReq.setApplyType(rBackApplyResp.getApplyType());
        rBackReviewReq.setBackId(rBackApplyResp.getBackId());
        rBackReviewReq.setOrderId(rBackApplyResp.getOrderId());
//        RBackApplyInfoResp rBackApplyInfoResp = this.ordCalculateSV.calCulateShare(rBackReviewReq);
        RBackApplyInfoResp rBackApplyInfoResp = queryBackShareInfo(rOrderBackReq);
        if(LongUtils.isEmpty(rBackApplyInfoResp.getBackMoney())){
            rBackApplyResp.setPayType("0");  //线下退款
        }
        rBackReviewResp.setrBackApplyInfoResp(rBackApplyInfoResp);
        rBackReviewResp.setrBackApplyResp(rBackApplyResp);
        rBackReviewResp.setrBackGdsResps(this.ordBackGdsSV.queryBackGds(rOrderBackReq));
        rBackReviewResp.setrBackPicResps(this.ordBackPicSV.queryOrdBackPic(rOrderBackReq));
        List<RBackTrackResp> rBackTrackResps =  this.ordBackTrackSV.queryOrdBackTrack(rOrderBackReq);
        List<RBackTrackResp> rBackReviews = new ArrayList<RBackTrackResp>();
        if(rBackTrackResps!=null){
            for(RBackTrackResp resp :rBackTrackResps){
                if(resp.getNode().contains("01")||resp.getNode().contains("99")){
                    rBackReviews.add(resp);
                }
            }
        }
        if(CollectionUtils.isNotEmpty(rBackReviews)){
            rBackReviewResp.setrBackTrackResps(rBackReviews);
        }
        
        return rBackReviewResp;
    }
    
    @Override
    public SRefundInfo queryRefundStatus(String orderId) {
        SRefundInfo  sRefundInfo = new SRefundInfo();
        OrdBackApplyCriteria obac = new OrdBackApplyCriteria();
        List<String> status = new ArrayList<String>();
        //status.add(BackConstants.Status.APPLY);
        //status.add(BackConstants.Status.REVIEW_PASS);
        
        status.add(BackConstants.Status.REFUNDED);
        status.add(BackConstants.Status.REFUSE);
        status.add(BackConstants.Status.SEND);
        status.add(BackConstants.Status.WAIT_REFUND);
       // obac.createCriteria().andOrderIdEqualTo(orderId).andStatusIn(status);
        obac.createCriteria().andOrderIdEqualTo(orderId).andStatusNotIn(status);
        List<OrdBackApply> obs = this.ordBackApplyMapper.selectByExample(obac);
        if(CollectionUtils.isNotEmpty(obs)){
            sRefundInfo.setRefundStatus("1");
            Collections.sort(obs, new Comparator<OrdBackApply>() {
                @Override
                public int compare(OrdBackApply arg0, OrdBackApply arg1) {
                    return arg1.getApplyTime().compareTo(arg0.getApplyTime());
                }
            });
            sRefundInfo.setRefundId(obs.get(0).getId());
            return sRefundInfo;
//            return "1";    //在退货退款流程中
        }
        //下单时间超过15天不允许退款
        
        List<OrdSub> oss = this.ordSubSV.queryOrderSubByOrderId(orderId);
        if(CollectionUtils.isEmpty(oss)){
            LogUtil.debug(MODULE, "根据" + orderId + "主订单号查询子订单数据为空");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312000);
        }
        for(OrdSub os :oss){
            if(os.getGdsType()==2 || os.getGdsType()==3){
                sRefundInfo.setRefundStatus("2");
                return sRefundInfo;    
            }
        }
        return null;
    }

    @Override
    public SRefundInfo queryBackGdsStatus(String orderId) {
        SRefundInfo  sRefundInfo = new SRefundInfo();
        
        List<OrdSub> oss = this.ordSubSV.queryOrderSubByOrderId(orderId);
        
        int virtualNum = 0;
        Long virtualAmontNum = 0l;
        for(OrdSub os :oss){      
            if(os.getGdsType()==2){
                virtualNum ++;
                virtualAmontNum = virtualAmontNum+os.getDeliverAmount();
            }
        }
        if(virtualNum==oss.size()){//若全部为虚拟商品，则不能退货
            sRefundInfo.setRefundStatus("2");   
            return sRefundInfo;
        }else if(virtualNum>0){//含有部分商品为虚拟商品的，判断除虚拟商品外是否退货完
        	//获取所有子订单商品，并取出虚拟商品数量和订单总商品数量
            OrdMain ordMain = ordMainSV.queryOrderByOrderId(orderId);
            
            //已退商品数量
            Long backOrderAmounts = 0l;
            //订单总商品数量 
            Long orderAmounts = ordMain.getOrderAmount();
            
            OrdBackApplyCriteria ordBackApplyCriteria = new OrdBackApplyCriteria();
            ordBackApplyCriteria.createCriteria().andOrderIdEqualTo(orderId)
                    .andStatusEqualTo(BackConstants.Status.REFUNDED);
            List<OrdBackApply> orderApplyList = ordBackApplyMapper
                    .selectByExample(ordBackApplyCriteria);
           
            if (!CollectionUtils.isEmpty(orderApplyList)) {
                for (OrdBackApply ordBackApply : orderApplyList) {
                    backOrderAmounts = backOrderAmounts + ordBackApply.getNum();
                }
            }
            //除虚拟商品外已经全部退货完成
            if(virtualAmontNum.longValue()+backOrderAmounts.longValue()==orderAmounts.longValue()){
                sRefundInfo.setRefundStatus("2");//则不能再退货
                return sRefundInfo;       
            }            
        }
        OrdBackApplyCriteria obac = new OrdBackApplyCriteria();
        List<String> status = new ArrayList<String>();
        status.add(BackConstants.Status.REFUNDED);
        status.add(BackConstants.Status.REFUSE);
        
        obac.createCriteria().andOrderIdEqualTo(orderId).andStatusNotIn(status);
        List<OrdBackApply> obs = this.ordBackApplyMapper.selectByExample(obac);
        if(CollectionUtils.isEmpty(obs)){
            return null;
        }
        Collections.sort(obs, new Comparator<OrdBackApply>() {
            @Override
            public int compare(OrdBackApply arg0, OrdBackApply arg1) {
                return arg1.getApplyTime().compareTo(arg0.getApplyTime());
            }
        });
        sRefundInfo.setRefundId(obs.get(0).getId());
        sRefundInfo.setRefundStatus("1");  //退货流程中
        
        return sRefundInfo;
    }

    @Override
    public RBackPayInfoResp queryBackPayInfo(ROrderBackReq rOrderBackReq) throws BusinessException {
        RBackPayInfoResp rBackPayInfoResp = new RBackPayInfoResp();
        rBackPayInfoResp.setrBackApplyResp(this.queryOrdBackApply(rOrderBackReq));
        rBackPayInfoResp.setsOrderDetailsMain(this.ordMainSV.queryOrderDetailsMain(rOrderBackReq.getOrderId()));
        return rBackPayInfoResp;
    }

    @Override
    public int updateIsInAuditFile(Long backId, String status) {
        OrdBackApply record = new OrdBackApply();
        record.setId(backId);
        record.setIsInAuditFile(status);
        return ordBackApplyMapper.updateByPrimaryKeySelective(record);
    }
    
    @Override
    public PageResponseDTO<RBackApplyResp> querynotInAuditTradeCheckOrders(
    		ROrderBackReq orderBackReq) {
    	
    	OrdBackApplyCriteria example = new OrdBackApplyCriteria();
    	OrdBackApplyCriteria.Criteria criteria = example.createCriteria();
        if(orderBackReq.getBegDate()!=null){
            criteria.andApplyTimeGreaterThanOrEqualTo(orderBackReq.getBegDate());
        }
        if(orderBackReq.getEndDate()!=null){
            criteria.andApplyTimeLessThanOrEqualTo(orderBackReq.getEndDate());
        }
        if(orderBackReq.getShopId()!=null){
            criteria.andShopIdEqualTo(orderBackReq.getShopId());
        }
        criteria.andStatusEqualTo(BackConstants.Status.REFUNDED);
        criteria.andPayTypeEqualTo(PayType.ORD_BACK_APPLY_PAY_TYPE_1);
        criteria.andIsInAuditFileEqualTo(PayStatus.ORD_MAIN_IS_IN_AUDIT_FILE_0);
        criteria.andBackMoneyNotEqualTo(0L); 
        example.setLimitClauseStart(orderBackReq.getStartRowIndex());
        example.setLimitClauseCount(orderBackReq.getPageSize());
        PageResponseDTO<RBackApplyResp> pageResponseDTO = queryByPagination(orderBackReq,example);
        if(CollectionUtils.isNotEmpty(pageResponseDTO.getResult())) {
        	for(RBackApplyResp backApplyResp:pageResponseDTO.getResult()){
        		if(backApplyResp.getStaffId()!=null){
        			CustInfoResDTO custInfoResDTO = this.staffUnionRSV.findCustOrAdminByStaffId(backApplyResp.getStaffId());
        			if(custInfoResDTO != null && custInfoResDTO.getStaffCode() != null){
        				backApplyResp.setStaffName(custInfoResDTO.getStaffCode());
        			} else {
        				backApplyResp.setStaffName(backApplyResp.getStaffId().toString());
        			}
        		}
        	}
        }
        return pageResponseDTO;
    }
    
    private PageResponseDTO<RBackApplyResp> queryByPagination(BaseInfo baseInfo,BaseCriteria criteria){
        return super.queryByPagination(baseInfo, criteria, true, new PaginationCallback<OrdBackApply, RBackApplyResp>() {

            @Override
            public List<OrdBackApply> queryDB(BaseCriteria bCriteria) {
                return ordBackApplyMapper.selectByExample((OrdBackApplyCriteria)bCriteria) ;
            }

            @Override
            public long queryTotal(BaseCriteria bCriteria) {
                return ordBackApplyMapper.countByExample((OrdBackApplyCriteria)bCriteria);
            }

            @Override
            public List<Comparator<OrdBackApply>> defineComparators() {
                List<Comparator<OrdBackApply>> ls = new ArrayList<Comparator<OrdBackApply>>();
                ls.add(new Comparator<OrdBackApply>(){

                    @Override
                    public int compare(OrdBackApply o1, OrdBackApply o2) {
                        return o2.getApplyTime().compareTo(o1.getApplyTime());
                    }
                    
                });
                return ls;
            }

            @Override
            public RBackApplyResp warpReturnObject(OrdBackApply ordBackApply) {
            	RBackApplyResp sdoi = new RBackApplyResp();
                    BeanUtils.copyProperties(ordBackApply, sdoi);
                    sdoi.setBackId(ordBackApply.getId());
                    return sdoi;
            }

            
        });
      }

    @Override
    public List<OrdBackApply> queryHasBackOrdBackApplyByOrderID(OrdBackApply ordBackApply) {
        OrdBackApplyCriteria ordBackApplyCriteria = new OrdBackApplyCriteria();
        ordBackApplyCriteria.createCriteria().andOrderIdEqualTo(ordBackApply.getOrderId()).andStatusEqualTo("04");
        List<OrdBackApply> list = ordBackApplyMapper.selectByExample(ordBackApplyCriteria);       
        return list;
    }
    
    /**
     * 计算非最后一次退货的退款比例
     * @return
     */
    public double calCulateScaleApply(OrdSub ordSub,long num)  throws BusinessException {
    	 
         Long discountMoneys = 0l;
         Long amontMoney = ordSubSV.querySumDiscountMoney(ordSub.getOrderId());
         //非全部退款时只允许单个子订单商品退款，因而子订单编号只有一个
         discountMoneys = ordSub.getDiscountMoney()*num/ordSub.getOrderAmount();
         return (new BigDecimal(discountMoneys*1000000).divide(new BigDecimal(amontMoney),2)).longValue();       
    }
    
    
}

