package com.zengshi.ecp.order.facade.impl.eventual;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import com.zengshi.ecp.coupon.dubbo.dto.req.CouponDetailPreReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CouponPresentReqDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupDetailRSV;
import com.zengshi.ecp.order.dao.model.AuditAutoLog;
import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dao.model.OrdPresent;
import com.zengshi.ecp.order.dao.model.PayQuartzInfo;
import com.zengshi.ecp.order.dubbo.dto.pay.PaySuccInfo;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayQuartzInfoRequest;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.PayStatus;
import com.zengshi.ecp.order.dubbo.util.PayHelper;
import com.zengshi.ecp.order.facade.interfaces.eventual.IPayCoupSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdPresentSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayQuartzInfoSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.audit.IAuditAutoLogSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionStatus;

public class PayCoupSVImpl implements IPayCoupSV {
    private static final String MODULE = PayCoupSVImpl.class.getName();
    
    @Resource
    private IOrdPresentSV ordPresentSV;
    
    @Resource
    private IPayQuartzInfoSV payQuartzInfoSV;
    
    @Resource
    private ICoupDetailRSV coupDetailRSV;
    
    @Resource
    private IOrdMainSV ordMainSV;
    
    @Resource
    private IAuditAutoLogSV auditAutoLogSV;
    
    @Override
    public void joinTransaction(JSONObject message, TransactionStatus status, String transactionName) {
        try {
            final PaySuccInfo paySuccInfo = JSON.parseObject(message.toString(), PaySuccInfo.class);
            LogUtil.info(MODULE,"PayCoupSVImpl============="+paySuccInfo.toString());
            dealCoup(paySuccInfo);
            return;
        } catch (BusinessException be) {
        	LogUtil.error(MODULE, "支付回调优惠券接口处理异常", be);
            be.printStackTrace();
            status.setRollbackOnly();
            throw new BusinessException(be.getErrorCode());
        } catch (Exception e) {
        	LogUtil.error(MODULE, "支付回调优惠券接口处理异常", e);
            e.printStackTrace();
            status.setRollbackOnly();
            throw new BusinessException(MsgConstants.PayServiceMsgCode.PAY_SERVER_310021);
        }
        
    }
    
    @Override
    public void dealCoup(PaySuccInfo paySuccInfo) {
    	try{
            LogUtil.info(MODULE,"处理优惠券开始============="+paySuccInfo.toString());
            
            //获取定时任务表数据
            RPayQuartzInfoRequest payQuartzInfoRequest = new RPayQuartzInfoRequest();
            payQuartzInfoRequest.setOrderId(paySuccInfo.getOrderId());
            payQuartzInfoRequest.setTaskType(PayStatus.PAY_TASK_TYPE_02);
            payQuartzInfoRequest.setDealFlag(PayStatus.PAY_DEAL_FLAG_0);
            List<PayQuartzInfo> payQuartzInfoList= payQuartzInfoSV.getBeanByOrderIdTaskTypeDealFlag(payQuartzInfoRequest);
            PayQuartzInfo payQuartzInfo;
            //找不到就直接返回
            if(payQuartzInfoList==null||payQuartzInfoList.isEmpty()){
                return;
            }else{
            	payQuartzInfo = payQuartzInfoList.get(0);
            }
            OrdMain ordMain = ordMainSV.queryOrderByOrderId(paySuccInfo.getOrderId());
            
            if(ordMain==null){
            	throw new BusinessException(MsgConstants.PayServiceMsgCode.PAY_SERVER_310003);
            }
            //查询需要赠送的优惠券，没有就删除定时任务记录返回
            List<OrdPresent> ordPresentList = ordPresentSV.queryCoupByOrderId(paySuccInfo.getOrderId());
            
            //是否需要变更本条记录为为处理，只要更改状态为正在处理就一定要变为true
        	boolean updateToUndo = false;
        	
            if(!ordPresentList.isEmpty()){
            	//处理
            	RPayQuartzInfoRequest payQuartzInfoDTO = new RPayQuartzInfoRequest();
            	payQuartzInfoDTO.setTaskType(PayStatus.PAY_TASK_TYPE_02);
            	payQuartzInfoDTO.setId(payQuartzInfo.getId());
            	int i = payQuartzInfoSV.updateDealFlag(payQuartzInfoDTO, PayStatus.PAY_DEAL_FLAG_0, PayStatus.PAY_DEAL_FLAG_2);
            	
            	//更新状态成功
            	if(i==1){
            		//更新状态成功后设置为需要变更为未处理
            		updateToUndo = true;
            		try{
	            		CouponPresentReqDTO coupCallBackReqDTO = new CouponPresentReqDTO();
	                	coupCallBackReqDTO.setStaffId(ordMain.getStaffId());
	                	coupCallBackReqDTO.setShopId(ordMain.getShopId());
	                	coupCallBackReqDTO.setOrderId(paySuccInfo.getOrderId());
	                	List<CouponDetailPreReqDTO> couponDetailPreBeans = new ArrayList<CouponDetailPreReqDTO>();
	                	for(OrdPresent ordPresent:ordPresentList){
                			CouponDetailPreReqDTO couponDetail = new CouponDetailPreReqDTO();
                			couponDetail.setCoupId(Long.parseLong(ordPresent.getCouponTypeId()));
                			couponDetail.setCoupSum(ordPresent.getCouponCnt().intValue());
                			couponDetailPreBeans.add(couponDetail);
	                	}
	                	coupCallBackReqDTO.setCouponDetailPreBeans(couponDetailPreBeans);
	                	coupDetailRSV.presentCoupon(coupCallBackReqDTO);
	                	//成功后删除定时任务
                        payQuartzInfoSV.deletePayQuartzInfo(payQuartzInfo.getId());
            		}catch (Exception e) {
            			LogUtil.error(MODULE, "优惠券接口处理异常", e);
            			//记录日志
                        AuditAutoLog auditAutoLog = new AuditAutoLog();
                        auditAutoLog.setPayWay(paySuccInfo.getPayWay());
                        auditAutoLog.setExecMethods("dealCoup");
                        auditAutoLog.setExecuteTime(DateUtil.getSysDate());
                        auditAutoLog.setRemark(PayHelper.getExceptionStackTrace(e));
                        auditAutoLogSV.saveAuditAutoLog(auditAutoLog);
            		}
            	}
            }else{
            	//不需要赠送优惠券就直接删除
            	payQuartzInfoSV.deletePayQuartzInfo(payQuartzInfo.getId());
            	
            }
            
            if(updateToUndo){
                //把正在处理中的状态改到未处理
                RPayQuartzInfoRequest payQuartzInfoRequest2 = new RPayQuartzInfoRequest();
                payQuartzInfoRequest2.setTaskType(PayStatus.PAY_TASK_TYPE_02);
                payQuartzInfoRequest2.setId(payQuartzInfo.getId());
                payQuartzInfoSV.updateDealFlag(payQuartzInfoRequest2, PayStatus.PAY_DEAL_FLAG_2, PayStatus.PAY_DEAL_FLAG_0);
            }
            
            //增加错误次数
            payQuartzInfoSV.addErrorTimes(payQuartzInfo.getId());
            
            LogUtil.info(MODULE,"处理优惠券结束,订单号："+paySuccInfo.getOrderId());
                
        }catch(Exception e){
            LogUtil.error(MODULE, "优惠券接口处理异常,订单号："+paySuccInfo.getOrderId(), e);
            throw e;
        }
    }

}

