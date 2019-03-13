package com.zengshi.ecp.order.facade.impl.eventual;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import com.zengshi.ecp.general.order.dto.PayQuartzInfoRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.order.dao.model.AuditAutoLog;
import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dao.model.OrdPresent;
import com.zengshi.ecp.order.dao.model.OrdSub;
import com.zengshi.ecp.order.dao.model.PayQuartzInfo;
import com.zengshi.ecp.order.dubbo.dto.pay.PaySuccInfo;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayQuartzInfoRequest;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.PayStatus;
import com.zengshi.ecp.order.dubbo.util.PayHelper;
import com.zengshi.ecp.order.facade.interfaces.eventual.IPayScoreSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdPresentSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayQuartzInfoSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.audit.IAuditAutoLogSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffUnionRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionStatus;

public class PayScoreSVImpl implements IPayScoreSV {
    
    @Resource
    private IStaffUnionRSV staffUnionRSV;
    
    @Resource
    private IPayQuartzInfoSV payQuartzInfoSV;
    
    @Resource
    private IOrdMainSV ordMainSV;
    
    @Resource
    private IOrdSubSV ordSubSV;
    
    @Resource
    private IOrdPresentSV ordPresentSV;
    
    @Resource
    private IAuditAutoLogSV auditAutoLogSV;
    
    private static final String MODULE = PayScoreSVImpl.class.getName();
    
    @Override
    public void joinTransaction(JSONObject message, TransactionStatus status, String transactionName) {
        try {
            final PaySuccInfo paySuccInfo = JSON.parseObject(message.toString(), PaySuccInfo.class);
            LogUtil.info(MODULE,"PayScoreSVImpl============="+paySuccInfo.toString());
            dealScore(paySuccInfo);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "支付回调积分接口处理异常", be);
            be.printStackTrace();
            status.setRollbackOnly();
            throw new BusinessException(be.getErrorCode());
        } catch (Exception e) {
            LogUtil.error(MODULE, "支付回调积分接口处理异常", e);
            status.setRollbackOnly();
            throw new BusinessException(MsgConstants.PayServiceMsgCode.PAY_SERVER_310011);
        }
        
    }
    
    @Override
    public void dealScore(PaySuccInfo paySuccInfo) {
        LogUtil.info(MODULE,"积分处理开始============="+paySuccInfo.toString());
        RPayQuartzInfoRequest payQuartzInfoRequest = new RPayQuartzInfoRequest();
        payQuartzInfoRequest.setOrderId(paySuccInfo.getOrderId());
        payQuartzInfoRequest.setTaskType(PayStatus.PAY_TASK_TYPE_01);
        payQuartzInfoRequest.setDealFlag(PayStatus.PAY_DEAL_FLAG_0);
        List<PayQuartzInfo> payQuartzInfoList = payQuartzInfoSV.getBeanByOrderIdTaskTypeDealFlag(payQuartzInfoRequest);
        OrdMain ordMain = ordMainSV.queryOrderByOrderId(paySuccInfo.getOrderId());
        if(ordMain==null){
            throw new BusinessException(MsgConstants.PayServiceMsgCode.PAY_SERVER_310003);
        }
        
        PayQuartzInfo payQuartzInfo;
        //已经处理的就直接返回
        if(payQuartzInfoList==null||payQuartzInfoList.isEmpty()){
            return;
        }else{
        	payQuartzInfo = payQuartzInfoList.get(0);
        }
        
        //是否需要变更本条记录为为处理，只要更改状态为正在处理就一定要变为true
    	boolean updateToUndo = false;
        
        //处理
    	RPayQuartzInfoRequest payQuartzInfoDTO = new RPayQuartzInfoRequest();
    	payQuartzInfoDTO.setTaskType(PayStatus.PAY_TASK_TYPE_01);
    	payQuartzInfoDTO.setId(payQuartzInfo.getId());
    	int i = payQuartzInfoSV.updateDealFlag(payQuartzInfoDTO, PayStatus.PAY_DEAL_FLAG_0, PayStatus.PAY_DEAL_FLAG_2);
    	
    	//更新状态成功
    	if(i==1){
    		//更新状态成功后设置为需要变更为未处理
    		updateToUndo = true;
    		try{
    			PayQuartzInfoRequest request = new PayQuartzInfoRequest();
    	        request.setPayQuartzInfoId(payQuartzInfo.getId());
    	        request.setOrderId(paySuccInfo.getOrderId());
    	        request.setPayment(ordMain.getRealMoney());
    	        request.setStaffId(ordMain.getStaffId());
    	        request.setSourceType(StaffConstants.SHOPING_TYPE);
    	        request.setSiteId(ordMain.getSiteId());
    	        List<ROrdCartItemCommRequest> ordCartItemCommList = new ArrayList<ROrdCartItemCommRequest>();
    	        List<OrdSub> ordSubList = ordSubSV.queryOrderSubByOrderId(paySuccInfo.getOrderId());
    	        List<OrdPresent> ordPresentList = ordPresentSV.queryOrdPresentByOrderId(paySuccInfo.getOrderId());
    	        for(OrdPresent ordPresent:ordPresentList){
    	            if(StringUtil.isBlank(ordPresent.getSubOrder())){
    	                request.setSendOrderMainScore(ordPresent.getCredits());
    	                if(ordPresent.getCreditTimes()!=null){
    	                    request.setSendOrderMainScoreTimes(ordPresent.getCreditTimes().doubleValue());
    	                }
    	                
    	            }
    	        }
    	        for(OrdSub ordSub:ordSubList){
    	            ROrdCartItemCommRequest rOrdCartItemCommRequest=new ROrdCartItemCommRequest();
    	            ObjectCopyUtil.copyObjValue(ordSub, rOrdCartItemCommRequest, null, false);
    	            rOrdCartItemCommRequest.setOrderSubId(ordSub.getId());
    	            
    	            
    	            for(OrdPresent ordPresent:ordPresentList){
    	                if(rOrdCartItemCommRequest.getOrderSubId().equals(ordPresent.getSubOrder())){
    	                    rOrdCartItemCommRequest.setSendOrderSubScore(ordPresent.getCredits());
    	                    if(ordPresent.getCreditTimes()!=null){
    	                        rOrdCartItemCommRequest.setSendOrderSubScoreTimes(ordPresent.getCreditTimes().doubleValue());
    	                    }
    	                }
    	            }
    	            
    	            ordCartItemCommList.add(rOrdCartItemCommRequest);
    	        }
    	        request.setOrdCartItemCommList(ordCartItemCommList);
    	        staffUnionRSV.saveStaffRelInfoForPay(request);
    	        //成功后删除定时任务
                payQuartzInfoSV.deletePayQuartzInfo(payQuartzInfo.getId());
    		}catch (Exception e) {
    			LogUtil.error(MODULE, "积分接口处理异常", e);
    			//记录日志
                AuditAutoLog auditAutoLog = new AuditAutoLog();
                auditAutoLog.setPayWay(paySuccInfo.getPayWay());
                auditAutoLog.setExecMethods("dealScore");
                auditAutoLog.setExecuteTime(DateUtil.getSysDate());
                auditAutoLog.setRemark(PayHelper.getExceptionStackTrace(e));
                auditAutoLogSV.saveAuditAutoLog(auditAutoLog);
    		}
    	}
    	
    	if(updateToUndo){
            //把正在处理中的状态改到未处理
            RPayQuartzInfoRequest payQuartzInfoRequest2 = new RPayQuartzInfoRequest();
            payQuartzInfoRequest2.setTaskType(PayStatus.PAY_TASK_TYPE_01);
            payQuartzInfoRequest2.setId(payQuartzInfo.getId());
            payQuartzInfoSV.updateDealFlag(payQuartzInfoRequest2, PayStatus.PAY_DEAL_FLAG_2, PayStatus.PAY_DEAL_FLAG_0);
        }
    	
    	 //增加错误次数
        payQuartzInfoSV.addErrorTimes(payQuartzInfo.getId());
        LogUtil.info(MODULE,"积分处理结束============="+paySuccInfo.toString());
    }

}

