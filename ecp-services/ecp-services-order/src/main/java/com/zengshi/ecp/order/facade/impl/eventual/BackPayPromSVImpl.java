package com.zengshi.ecp.order.facade.impl.eventual;

import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dao.model.OrdProm;
import com.zengshi.ecp.order.dao.model.OrdSub;
import com.zengshi.ecp.order.dubbo.dto.RBackConfirmReq;
import com.zengshi.ecp.order.dubbo.dto.RBackReviewResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndSubInfo;
import com.zengshi.ecp.order.dubbo.util.BackConstants;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.facade.interfaces.eventual.IBackPayCouponSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackApplySV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdPromSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionStatus;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class BackPayPromSVImpl implements IBackPayCouponSV {

    @Resource
    private IOrdMainSV ordMainSV;

    @Resource
    private IOrdSubSV ordSubSV;

    @Resource
    private IOrdPromSV ordPromSV;

    @Resource
    private IPromRSV promRSV;
    
    @Autowired(required = false)
    private IOrdBackApplySV ordBackApplySV;

    private static final String MODULE = BackPayPromSVImpl.class.getName();

    @Override
    public void joinTransaction(JSONObject message, TransactionStatus status,
            String transactionName) {
        try {
            final RBackConfirmReq rBackConfirmReq = JSON.parseObject(message.toString(),
                    RBackConfirmReq.class);
            LogUtil.info(MODULE, "BackPayPromSVImpl=============" + rBackConfirmReq.toString());
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
        ROrderBackReq rOrderBackReq = new ROrderBackReq();
        rOrderBackReq.setOrderId(rBackConfirmReq.getOrderId());
        rOrderBackReq.setBackId(rBackConfirmReq.getBackId());
        RBackReviewResp rBackReviewResp = this.ordBackApplySV.queryBackIdInfo(rOrderBackReq);
        if (BackConstants.ApplyType.REFUND
                .equals(rBackReviewResp.getrBackApplyResp().getApplyType().trim())) {
            // 退款
            boolean isProm = false;
            // 0 进行接口入参的封装
            ROrdCartsCommRequest rOrdCartsCommRequest = new ROrdCartsCommRequest();
            List<ROrdCartCommRequest> rOrdCartCommRequests = new ArrayList<ROrdCartCommRequest>();

            OrdMain ordMain = this.ordMainSV.queryOrderByOrderId(rBackConfirmReq.getOrderId());
            if (ordMain == null) {
                LogUtil.info(MODULE, "未找到[" + rBackConfirmReq.getOrderId() + "]该订单信息");
                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312001);
            }
            ROrdCartCommRequest rOrdCartCommRequest = new ROrdCartCommRequest();
            // 补齐订单信息
            ObjectCopyUtil.copyObjValue(ordMain, rOrdCartCommRequest, null, false);
            rOrdCartCommRequest.setOrderId(ordMain.getId());

            // 补齐订单促销
            SBaseAndSubInfo sBaseAndSubInfo = new SBaseAndSubInfo();
            sBaseAndSubInfo.setOrderId(rBackConfirmReq.getOrderId());
            OrdProm op = this.ordPromSV.queryOrdProm(sBaseAndSubInfo);
            if (op != null) {
                isProm = true;
                rOrdCartCommRequest.setPromId(op.getPromId());
            }
            List<OrdSub> ordSubs = this.ordSubSV
                    .queryOrderSubByOrderId(rBackConfirmReq.getOrderId());

            if (CollectionUtils.isEmpty(ordSubs)) {
                LogUtil.info(MODULE, "未找到[" + rBackConfirmReq.getOrderId() + "]该订单的子订单信息");
                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312002);
            }
            // 补齐明细信息
            List<ROrdCartItemCommRequest> rOrdCartItemCommRequests = new ArrayList<ROrdCartItemCommRequest>();
            for (OrdSub ordSub : ordSubs) {
                ROrdCartItemCommRequest rOrdCartItemCommRequest = new ROrdCartItemCommRequest();
                ObjectCopyUtil.copyObjValue(ordSub, rOrdCartItemCommRequest, null, false);
                rOrdCartItemCommRequest.setOrderSubId(ordSub.getId());

                // 补齐promId
                SBaseAndSubInfo sBaseAndSubInfo1 = new SBaseAndSubInfo();
                sBaseAndSubInfo1.setOrderId(rBackConfirmReq.getOrderId());
                sBaseAndSubInfo1.setOrderSubId(rOrdCartItemCommRequest.getOrderSubId());
                OrdProm opsub = this.ordPromSV.queryOrdProm(sBaseAndSubInfo1);
                if (opsub != null) {
                    isProm = true;
                    rOrdCartItemCommRequest.setPromId(opsub.getPromId());
                    rOrdCartItemCommRequest.setOrdPromId(opsub.getRelaMainPromId());
                }

                rOrdCartItemCommRequests.add(rOrdCartItemCommRequest);
            }
            rOrdCartCommRequest.setOrdCartItemCommList(rOrdCartItemCommRequests);
            rOrdCartCommRequests.add(rOrdCartCommRequest);
            rOrdCartsCommRequest.setOrdCartsCommList(rOrdCartCommRequests);
            rOrdCartsCommRequest.setStaffId(ordMain.getStaffId());

            // 活动补尝
            if (isProm) {
                LogUtil.info(MODULE, "调用促销域入参" + JSON.toJSONString(rOrdCartsCommRequest));
                this.promRSV.promOrdSaveRollBack(rOrdCartsCommRequest);
            } else {
                LogUtil.info(MODULE, "调用促销域入参:无促销域的下单信息不调用促销域");
            }
        } else {
            // 退货
        }
    }

}
