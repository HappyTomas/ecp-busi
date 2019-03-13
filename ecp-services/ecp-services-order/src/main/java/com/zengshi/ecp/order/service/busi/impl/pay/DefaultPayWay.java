package com.zengshi.ecp.order.service.busi.impl.pay;

import com.zengshi.ecp.order.dao.model.*;
import com.zengshi.ecp.order.dubbo.dto.OrdSubShareReq;
import com.zengshi.ecp.order.dubbo.dto.OrdSubShareResp;
import com.zengshi.ecp.order.dubbo.dto.RBackConfirmReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelResp;
import com.zengshi.ecp.order.dubbo.dto.pay.*;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdPayRelRSV;
import com.zengshi.ecp.order.dubbo.util.MsgConstants.PayInputMsgCode;
import com.zengshi.ecp.order.dubbo.util.MsgConstants.PayServiceMsgCode;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.PayStatus;
import com.zengshi.ecp.order.facade.interfaces.eventual.IBackPayOrderSV;
import com.zengshi.ecp.order.facade.interfaces.eventual.IPaymentSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubShareSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.*;
import com.zengshi.ecp.order.service.busi.interfaces.pay.audit.IAuditDailySumSV;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 支付通道默认实现类<br>
 * Date:2015年10月10日上午10:36:48  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public class DefaultPayWay implements IPayWay {
    
	public static final String module = DefaultPayWay.class.getName();
	public final static String ORDER_PAIED="1";
	
	@Resource
	protected IPayRequestSV payRequestSV;
	
	@Resource
	protected IPayIntfReqLogSV payIntfReqLogSV;
	
    @Resource
    protected IPayIntfNotifyLogSV payIntfNotifyLogSV;
    
    @Resource
    protected IPaymentSV paymentSV;
    
    @Resource
    protected IOrdMainSV ordMainSV;

	@Autowired(required=false)
    protected IPayWaySV payWaySV;
    
    @Resource
    protected IPayShopCfgSV payShopCfgSV;
    
    @Resource
    protected IOrdSubSV ordSubSV;
    
    @Resource
    protected IPayResultSV payResultSV;
    
    @Resource
    protected IPayRepeatSV payRepeatSV;
    
    @Resource
    protected ICustInfoRSV custInfoRSV;
    
    @Resource
    protected IPayRefundResultSV payRefundResultSV;
    
    @Resource
    protected IAuditDailySumSV auditDailySumSV;
    
    @Resource
    protected IBackPayOrderSV backPayOrderSV;

	@Resource
	private IOrdPayRelRSV iOrdPayRelSV;
	
	@Autowired
	private IRefundCallbackSV refundCallbackSV;
	
	@Resource
	private IOrdSubShareSV ordSubShareSV;
	
	@Override
	public PayRequestData requestPayment(PaymentRequest request,Map<String,String> extendProps) throws Exception{
		return null;
		
	}

	@Override
	public Map<String, String> paymentCallback(Map<String, String> responseResultMap)throws Exception {
		return null;
	}

	@Override
	public Map<String, String> queryPaymentResult(String orderId)throws Exception {
		return null;
	}
	

	@Override
	public RPayRefundResponse refund(RPayRefundRequest request,Map<String,String> extendProps) throws Exception{
		return null;
	}

	@Override
	public Map<String, String> refundCallback(Map<String, String> resultData)throws Exception {
		return null;
	}
	//-------------------支付通道共享实现方法--------begin-------------------------
	/**
	 * 
	 * addPayRequest:记录订单支付请求信息. <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author weijq
	 * @param request 
	 * @since JDK 1.6
	 */
	public void addPayRequest(PayRequestDTO request) {
	    payRequestSV.addPayRequest(request);
	}
	
	/**
	 * 
	 * addPayIntfReqLog:记录请求报文日志. <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author weijq
	 * @param log
	 * @since JDK 1.6
	 */
	public void addPayLog(PayIntfReqLogDTO log) {
	    log.setTypeCode(PayStatus.PAY_LOG_TYPE_CODE_01);
        payIntfReqLogSV.addPayIntfReqLog(log);
    }
	
	/**
	 * 
	 * addPayNotifyLog:保存支付应答日志--支付通道共享实现方法. <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author weijq
	 * @param log 
	 * @since JDK 1.6
	 */
	public void addPayResponseLog(PayIntfNotifyLogDTO log) {
	    log.setTypeCode(PayStatus.PAY_NOTIFYLOG_TYPE_CODE_01);
	    payIntfNotifyLogSV.addPayNotifyLog(log);
	}

//	/**
//	 * 转换支付请求--支付通道共享实现方法
//	 * @param basePayRequest
//	 * @return
//	 */
	public String formatPayRequest(BasePayRequest basePayRequest){
		return format(basePayRequest.toMap());
	}
//	/**
//	 * 转换支付绑定请求--支付通道共享实现方法
//	 * @param basePayRequest
//	 * @return
//	 */
//	public String formatBindRequest(BaseBindRequest baseBindRequest){
//		return format(baseBindRequest.toMap());
//	}
//	public String formatBindResponse(BaseBindResponse baseBindResponse){
//		return format(baseBindResponse.toMap());
//	}
	public String format(Map<String,String > map){
		String result = "";
		if(map!=null){
			result = JSONObject.fromObject(map).toString();
		}
		LogUtil.info(module, "ret="+result);
		return result;
	}
	
	/**
	 * 
	 * getOrderMainBean:根据订单id获取订单实体. <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author weijq
	 * @param orderId
	 * @return
	 * @throws Exception 
	 * @since JDK 1.6
	 */
	public OrdMain getOrderMainBean(String orderId)throws Exception{
	    return ordMainSV.queryOrderByOrderId(orderId);
	}
	
	/**
	 * 
	 * getordSubByOrderId:根据订单编码获取所有子订单信息. <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author weijq
	 * @param orderId
	 * @return
	 * @throws Exception 
	 * @since JDK 1.6
	 */
	public List<OrdSub> getordSubByOrderId(String orderId)throws Exception{
        return ordSubSV.queryOrderSubByOrderId(orderId);
    }
	
	/**
	 * 
	 * buildPayParamVO:初始化支付所需的参数. <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author weijq
	 * @param orderId
	 * @param payWay
	 * @return
	 * @throws Exception 
	 * @since JDK 1.6
	 */
	public PayParamVO  buildPayParamVO(String orderId,String payWay) throws Exception{
	    if(StringUtil.isBlank(orderId)){
	        throw new BusinessException(PayInputMsgCode.PAY_INPUT_300001);
	    }
	    if(StringUtil.isBlank(payWay)){
	        throw new BusinessException(PayInputMsgCode.PAY_INPUT_300002);
        }
		ROrdPayRelReq rOrdPayRelReq = new ROrdPayRelReq();
		rOrdPayRelReq.setJoinOrderid(orderId);
		List<ROrdPayRelResp> orderList = iOrdPayRelSV.queryOrdPayRelByOption(rOrdPayRelReq);
		if(orderList == null || orderList.size() == 0){
			throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310003);
		}
		PayParamVO vo = new PayParamVO();
		vo.setOrderId(orderId);
		long totalRealMoney = 0;
		long shopId = 0l;
		for(int index = 0, size = orderList.size(); index < size; index++){
			ROrdPayRelResp ordPayRelResp = orderList.get(index);
			if(index == 0){
				shopId = ordPayRelResp.getShopId();
				vo.setPayFlag(ordPayRelResp.getPayFlag());
				vo.setStaffId(ordPayRelResp.getStaffId());
				vo.setShopId(shopId);
				OrdMain ordMain = new OrdMain();
				ordMain.setShopId(shopId);
				ordMain.setStaffId(ordPayRelResp.getStaffId());
				vo.setOrdMain(ordMain);
			}
			totalRealMoney += ordPayRelResp.getRealMoney();
		}
		PayWay payWayBean = getPayWayBean(payWay);
		if(payWayBean==null){
			throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310004);
		}
		// 获取合并支付的开关 1-表示开启  0-表示关闭
        BaseSysCfgRespDTO payMergeSysCfg = SysCfgUtil.fetchSysCfg(OrdConstants.OrdPayRel.SWITCH_PAY_MERGE);
        if(OrdConstants.OrdPayRel.PAY_MERGE_ENABLE.equals(payMergeSysCfg.getParaValue())){
        	shopId = OrdConstants.OrdPayRel.PAY_WAY_MERGE_CFG_ID;
        }
		PayShopCfg payShopCfg = getPayShopCfgBean(shopId, payWay);
		if(payShopCfg==null){
			throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310009);
		}
		vo.setPayment(totalRealMoney);
		vo.setPayWay(payWayBean);
		vo.setPayShopCfg(payShopCfg);
		return vo;
	}
	
	public PayWay getPayWayBean(String payWay)throws Exception{
	    PayWay payWayBean = payWaySV.getPayWayById(payWay);
	    return payWayBean;
	}
	public PayShopCfg getPayShopCfgBean(Long shopId,String payWay)throws Exception{
	    PayShopCfg payShopCfg = payShopCfgSV.getCfgByShopIdAndPayWay(shopId, payWay);
		return payShopCfg;
	}
	
	public CustInfoResDTO getStaffInfo(Long staffId)throws Exception{
	    CustInfoReqDTO custInfoReq = new CustInfoReqDTO();
        custInfoReq.setId(staffId);
        CustInfoResDTO custInfoResponse = custInfoRSV.getCustInfoById(custInfoReq);
        return custInfoResponse;
    }
	/**
	 * 
	 * handlePaySucc:公共的支付结果后续处理方法（此方法为一个转发）. <br/> 
	 * TODO 先过滤重复通知，不用把过滤重复通知放在一致性事务之后.<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author weijq
	 * @param paySuccInfo
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void saveHandlePaySucc(PaySuccInfo paySuccInfo) throws BusinessException{
	    // 查询是否已经处理成功
        List<PayResult> payResultList = payResultSV.getPayResultByOrderId(paySuccInfo.getOrderId());

        PayRepeat payRepeat = payRepeatSV.getPayRepeatByPayWayTransNo(paySuccInfo.getPayWay(),
                paySuccInfo.getPayTransNo());

        // 已经处理过的重复支付通知直接返回
        if (payRepeat != null) {
            return;
        }

        // 已经支付成功的直接返回，重复支付的插入重复支付表，并返回
        for (PayResult payResult : payResultList) {
            if (paySuccInfo.getPayWay().equals(payResult.getPayWay())
                    && paySuccInfo.getPayTransNo().equals(payResult.getPayTransNo())) {
                return;
            } else if (!paySuccInfo.getPayWay().equals(payResult.getPayWay())
                    || !paySuccInfo.getPayTransNo().equals(payResult.getPayTransNo())) {
                PayRepeatDTO payRepeatDTO = new PayRepeatDTO();
                payRepeatDTO.setOrderId(paySuccInfo.getOrderId());
                payRepeatDTO.setPayWay(paySuccInfo.getPayWay());
                payRepeatDTO.setPayWayName(paySuccInfo.getPayWayName());
                payRepeatDTO.setPayTransNo(paySuccInfo.getPayTransNo());
                payRepeatDTO.setPayment(paySuccInfo.getPayment());
                payRepeatDTO.setPayStatus("00");
                payRepeatDTO.setPayDesc("重复支付");
                payRepeatDTO.setPayTime(DateUtil.getSysDate());
                payRepeatDTO.setCreateTime(DateUtil.getSysDate());
                payRepeatDTO.setMerchAcct(paySuccInfo.getMerchAcct());
                payRepeatDTO.setPayeeName(paySuccInfo.getPayeeName());
                payRepeatDTO.setPayeeAcct(paySuccInfo.getPayeeAcct());
                payRepeatSV.addPayRepeat(payRepeatDTO);
                return;
            }
        }
        // 记录支付成功日志
        PayResultDTO payResult = new PayResultDTO();
        payResult.setOrderId(paySuccInfo.getOrderId());
        payResult.setPayWay(paySuccInfo.getPayWay());
        payResult.setPayWayName(paySuccInfo.getPayWayName());
        payResult.setPayTransNo(paySuccInfo.getPayTransNo());
        payResult.setPayment(paySuccInfo.getPayment());
        payResult.setPayStatus("00");
        payResult.setPayDesc("支付成功");
        payResult.setPayTime(DateUtil.getSysDate());
        payResult.setMerchAcct(paySuccInfo.getMerchAcct());
        payResult.setPayeeName(paySuccInfo.getPayeeName());
        payResult.setPayeeAcct(paySuccInfo.getPayeeAcct());
        payResultSV.addPayResult(payResult);
        ROrdPayRelReq rOrdPayRelReq = new ROrdPayRelReq();
        String joinOrderid = paySuccInfo.getOrderId();
        rOrdPayRelReq.setJoinOrderid(joinOrderid);
        List<ROrdPayRelResp> resultList = iOrdPayRelSV.queryOrdPayRelByOption(rOrdPayRelReq);
        
        if(resultList != null && resultList.size() >= 1){
            //更新ordpayrel 表
            rOrdPayRelReq.setRemark("支付成功");
            rOrdPayRelReq.setPayFlag(OrdConstants.Order.ORDER_PAY_FLAG_1);
            rOrdPayRelReq.setPayWay(paySuccInfo.getPayWay()); 
            Timestamp time = DateUtil.getSysDate();
            rOrdPayRelReq.setUpdateTime(time);
            rOrdPayRelReq.setUpdateStaff(OrdConstants.Common.DEFAULT_STAFFID.toString());
            iOrdPayRelSV.updateOrdPayRel(rOrdPayRelReq);
        	for(ROrdPayRelResp resp : resultList){
        		paySuccInfo.setOrderId(resp.getOrderId());
                rOrdPayRelReq.setOrderId(resp.getOrderId()); 
        		ordMainSV.updateOrderPayTranNo(rOrdPayRelReq); //更新订单主表及索引表的商户订单号
        	    paymentSV.savePaySucc(paySuccInfo); 	
        	}       	
        }  
        

	}
	
	/**
     * 
     * saveRefundSucc:公共的退款结果后续处理方法. <br/> 
     * TODO 先过滤重复通知，不用把过滤重复通知放在一致性事务之后.<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param paySuccInfo
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public void saveRefundCallback(PayRefundSuccInfo payRefundSuccInfo) throws Exception{
	    refundCallbackSV.saveRefundCallback(payRefundSuccInfo);       
	}
//	
//	//-------------------支付通道共享实现方法---------end--------------------------
//	public String toString() {
//		return JSONObject.fromObject(this).toString();
//	}
//
//	@Override
//	public Map<String, String> pagePaymentCallback(
//			Map<String, String> pageResultMap) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	/**
//	 * 新增支付结果查询日志
//	 * @param payQueryLogVO
//	 * @return
//	 */
//	public boolean addPayQueryLog(PayQueryLogVO payQueryLogVO){
//		try {
//			if(payQueryLogVO == null){
//				throw new BusinessException(Special.PARAM_IS_NULL, "payQueryLogVO");
//			}
//			if(StringUtils.isBlank(payQueryLogVO.getProvinceCode())){
//				throw new BusinessException(Special.PARAM_IS_NULL, "provinceCode");
//			}
//			ITPayQueryLogSV sv = ServiceUtil.getService(ITPayQueryLogSV.class,payQueryLogVO.getProvinceCode());
//			sv.addPayQueryLog(payQueryLogVO);
//			return true;
//		} catch (Exception e) {
//			Debug.logError(e, "严重错误：新增支付结果查询日志失败！",module);
//			return false;
//		}
//	}
//
	@Override
	public OrderPayStatusVO parsePayStatus(Map<String, String> params)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Map<String, String> check(Map<String, String> resultData) throws Exception {
	    return null;
	}
//	
//	public void addPayShiftLog(TPayShiftLogVO payShiftLogVO) throws Exception {
//		try {
//			if(payShiftLogVO == null){
//				throw new BusinessException(Special.PARAM_IS_NULL, "payShiftLogVO");
//			}
//			if(StringUtils.isBlank(payShiftLogVO.getProvinceCode())){
//				throw new BusinessException(Special.PARAM_IS_NULL, "provinceCode");
//			}
//			ITPayShiftLogSV sv = ServiceUtil.getService(ITPayShiftLogSV.class,payShiftLogVO.getProvinceCode());
//			sv.addPayShiftLog(payShiftLogVO);
//		} catch (Exception e) {
//			Debug.logError(e, "严重错误：新增绑卡转移日志失败！",module);
//			throw e;
//		}
//		
//	}
//	
//	public void addPayRefundRequestLog(TPayRefundRequestLogVO payRefundRequestLog) throws Exception {
//		try {
//			if(payRefundRequestLog == null){
//				throw new BusinessException(Special.PARAM_IS_NULL, "payRefundRequestLog");
//			}
//			if(StringUtils.isBlank(payRefundRequestLog.getProvinceCode())){
//				throw new BusinessException(Special.PARAM_IS_NULL, "provinceCode");
//			}
//			ITPayRefundRequestLogSV sv = ServiceUtil.getService(ITPayRefundRequestLogSV.class,payRefundRequestLog.getProvinceCode());
//			sv.addPayRefundRequestLog(payRefundRequestLog);
//		} catch (Exception e) {
//			Debug.logError(e, "严重错误：新增退款请求日志失败！",module);
//			throw e;
//		}
//		
//	}
//	
//	public void addPayRefundResponseLog(TPayRefundResponseLogVO payRefundResponseLogVO) throws Exception {
//		try {
//			if(payRefundResponseLogVO == null){
//				throw new BusinessException(Special.PARAM_IS_NULL, "payRefundResponseLogVO");
//			}
//			if(StringUtils.isBlank(payRefundResponseLogVO.getProvinceCode())){
//				throw new BusinessException(Special.PARAM_IS_NULL, "provinceCode");
//			}
//			ITPayRefundResponseLogSV sv = ServiceUtil.getService(ITPayRefundResponseLogSV.class,payRefundResponseLogVO.getProvinceCode());
//			sv.addPayRefundResponseLog(payRefundResponseLogVO);
//		} catch (Exception e) {
//			Debug.logError(e, "严重错误：新增退款响应日志失败！",module);
//			throw e;
//		}
//		
//	}
}