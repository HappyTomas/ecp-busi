package com.zengshi.ecp.order.dubbo.impl.pay;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelResp;
import com.zengshi.ecp.order.dubbo.dto.pay.PaySuccInfo;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayQuartzInfoRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayQuartzInfoResponse;
import com.zengshi.ecp.order.dubbo.interfaces.pay.IPayQuartzInfoForOrderRSV;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.facade.interfaces.eventual.IPayCoupSV;
import com.zengshi.ecp.order.facade.interfaces.eventual.IPayScoreSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdPayRelSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayQuartzInfoSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

public class PayQuartzInfoForOrderRSVImpl implements IPayQuartzInfoForOrderRSV {

    private static final String MODULE = PayQuartzInfoForOrderRSVImpl.class.getName();

    @Resource
    private IPayQuartzInfoSV payQuartzInfoSV;
    
    @Resource
    private IPayScoreSV payScoreSV;
    
    @Resource
    private IPayCoupSV payCoupSV;

    @Resource
    private IOrdPayRelSV iOrdPayRelSV;
    
    

    @Override
    public List<RPayQuartzInfoResponse> queryNotDealScoreOrder(
            RPayQuartzInfoRequest rPayQuartzInfoRequest) {
        if(rPayQuartzInfoRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.PayInputMsgCode.PAY_INPUT_300000); 
        }
        LogUtil.info(MODULE, rPayQuartzInfoRequest.toString());
        try {
            return this.payQuartzInfoSV.queryNotDealScoreOrder(rPayQuartzInfoRequest);
            
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.PayServiceMsgCode.PAY_SERVER_310014);
        }
    }

    @Override
    public List<RPayQuartzInfoResponse> queryNotDealCoupOrder(
            RPayQuartzInfoRequest rPayQuartzInfoRequest) {
        if(rPayQuartzInfoRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.PayInputMsgCode.PAY_INPUT_300000); 
        }
        LogUtil.info(MODULE, rPayQuartzInfoRequest.toString());
        try {
            return this.payQuartzInfoSV.queryNotDealCoupOrder(rPayQuartzInfoRequest);
            
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.PayServiceMsgCode.PAY_SERVER_310014);
        }
    }

    @Override
    public void dealScoreOrder(RPayQuartzInfoRequest rPayQuartzInfoRequest) {
        if(rPayQuartzInfoRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.PayInputMsgCode.PAY_INPUT_300000); 
        }
        if(StringUtil.isBlank(rPayQuartzInfoRequest.getOrderId())){
            LogUtil.info(MODULE, "订单编码不能为空");
            throw new BusinessException(MsgConstants.PayInputMsgCode.PAY_INPUT_300001); 
        }
        if(rPayQuartzInfoRequest.getStaffId()<=0){
            LogUtil.info(MODULE, "买家ID不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003); 
        }
        LogUtil.info(MODULE, rPayQuartzInfoRequest.toString());
        try {

            /**
             * 拆分订单进行处理。。因为积分和优惠券都是具体到订单的。只有订单状态更新和支付结果 是不用拆分订单
             */
            ROrdPayRelReq rOrdPayRelReq = new ROrdPayRelReq();
            String joinOrderid = rPayQuartzInfoRequest.getOrderId();
            rOrdPayRelReq.setJoinOrderid(joinOrderid);
            List<ROrdPayRelResp> resultList = iOrdPayRelSV.queryOrdPayRelByOption(rOrdPayRelReq);

            if(resultList != null && resultList.size() >= 1){
                for(ROrdPayRelResp resp : resultList){
                    LogUtil.info(MODULE, "dealScoreOrder=============" + resp.toString());
                    PaySuccInfo paySuccInfo = new PaySuccInfo();
                    paySuccInfo.setOrderId(resp.getOrderId());
                    paySuccInfo.setStaffId(resp.getStaffId());
                    payScoreSV.dealScore(paySuccInfo);
                }
            }

        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.PayServiceMsgCode.PAY_SERVER_310011);
        }
    }

    @Override
    public void dealCoupOrder(RPayQuartzInfoRequest rPayQuartzInfoRequest) {
    	if(rPayQuartzInfoRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.PayInputMsgCode.PAY_INPUT_300000); 
        }
        if(StringUtil.isBlank(rPayQuartzInfoRequest.getOrderId())){
            LogUtil.info(MODULE, "订单编码不能为空");
            throw new BusinessException(MsgConstants.PayInputMsgCode.PAY_INPUT_300001); 
        }
        if(rPayQuartzInfoRequest.getStaffId()<=0){
            LogUtil.info(MODULE, "买家ID不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003); 
        }
        LogUtil.info(MODULE, rPayQuartzInfoRequest.toString());
        try {
            /**
             * 拆分订单进行处理。。因为积分和优惠券都是具体到订单的。只有订单状态更新和支付结果 是不用拆分订单
             */
            ROrdPayRelReq rOrdPayRelReq = new ROrdPayRelReq();
            String joinOrderid = rPayQuartzInfoRequest.getOrderId();
            rOrdPayRelReq.setJoinOrderid(joinOrderid);
            List<ROrdPayRelResp> resultList = iOrdPayRelSV.queryOrdPayRelByOption(rOrdPayRelReq);

            if(resultList != null && resultList.size() >= 1){
                for(ROrdPayRelResp resp : resultList){
                    LogUtil.info(MODULE, "dealCoupOrder=============" + resp.toString());
                    PaySuccInfo paySuccInfo = new PaySuccInfo();
                    paySuccInfo.setOrderId(resp.getOrderId());
                    paySuccInfo.setStaffId(resp.getStaffId());
                    payCoupSV.dealCoup(paySuccInfo);
                }
            }

        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.PayServiceMsgCode.PAY_SERVER_310011);
        }
    }


}
