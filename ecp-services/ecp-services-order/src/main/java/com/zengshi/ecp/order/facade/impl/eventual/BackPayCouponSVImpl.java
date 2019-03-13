package com.zengshi.ecp.order.facade.impl.eventual;

import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupOrdBackRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.OrdBackNumRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupDetailRSV;
import com.zengshi.ecp.order.dubbo.dto.RBackConfirmReq;
import com.zengshi.ecp.order.dubbo.dto.RBackCouponResp;
import com.zengshi.ecp.order.dubbo.dto.RBackReviewResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;
import com.zengshi.ecp.order.dubbo.util.BackConstants;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.facade.interfaces.eventual.IBackPayCouponSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackApplyEtSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackApplySV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackCouponSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionStatus;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class BackPayCouponSVImpl implements IBackPayCouponSV {
    
    @Resource
    private ICoupDetailRSV coupDetailRSV;
    
    @Resource
    private IOrdBackCouponSV ordBackCouponSV;
    
    @Resource
    private IOrdBackApplyEtSV ordBackApplyEtSV;

    private static final String MODULE = BackPayCouponSVImpl.class.getName();
    
    @Override
    public void joinTransaction(JSONObject message, TransactionStatus status, String transactionName) {
        try {
            final RBackConfirmReq rBackConfirmReq = JSON.parseObject(message.toString(), RBackConfirmReq.class);
            LogUtil.info(MODULE,"BackPayCouponSVImpl============="+rBackConfirmReq.toString());
            dealMethod(rBackConfirmReq);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "支付回调促销域接口处理异常", be);
            be.printStackTrace();
            status.setRollbackOnly();
            throw new BusinessException(be.getErrorCode());
        } catch (Exception e) {
            LogUtil.error(MODULE, "支付回调促销域接口处理异常", e);
            status.setRollbackOnly();
            throw new BusinessException(MsgConstants.OtherSysMsgCode.CALL_PROM_SERVER_341000);
        }
    }

    @Override
    public void dealMethod(RBackConfirmReq rBackConfirmReq) {
        ROrderBackReq rOrderBackReq  = new ROrderBackReq();
        rOrderBackReq.setOrderId(rBackConfirmReq.getOrderId());
        rOrderBackReq.setBackId(rBackConfirmReq.getBackId());
        RBackReviewResp rBackReviewResp = this.ordBackApplyEtSV.queryBackIdInfo(rOrderBackReq);
        if(BackConstants.ApplyType.REFUND.equals(rBackReviewResp.getrBackApplyResp().getApplyType().trim())){
            //退款
            this.coupDetailRSV.deductionCoup(rBackReviewResp.getrBackApplyResp().getOrderId(), 
                    rBackReviewResp.getrBackApplyResp().getStaffId());
        } else {  
            //退货
            CoupOrdBackRespDTO coupOrdBackRespDTO  = new CoupOrdBackRespDTO();
            coupOrdBackRespDTO.setOrderId(rBackReviewResp.getrBackApplyResp().getOrderId());
            coupOrdBackRespDTO.setStaffId(rBackReviewResp.getrBackApplyResp().getStaffId());
            List<OrdBackNumRespDTO> ordBackNumRespDTOs = new ArrayList<OrdBackNumRespDTO>();
            
            List<RBackCouponResp> rBackCouponResps = this.ordBackCouponSV.queryOrdBackCoupon(rOrderBackReq);
            if(CollectionUtils.isNotEmpty(rBackCouponResps)){
                for(RBackCouponResp rBackCouponResp:rBackCouponResps){
                    OrdBackNumRespDTO ordBackNumRespDTO = new OrdBackNumRespDTO();
                    ordBackNumRespDTO.setCoupId(rBackCouponResp.getCouponTypeId());
                    ordBackNumRespDTO.setCoupNum(rBackCouponResp.getCouponCnt());
                    ordBackNumRespDTOs.add(ordBackNumRespDTO);
                }
                coupOrdBackRespDTO.setCoupNumBeans(ordBackNumRespDTOs);
                this.coupDetailRSV.loseBlackCoup(coupOrdBackRespDTO);
            }
            
        }
    }

}

