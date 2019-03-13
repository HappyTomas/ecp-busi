package com.zengshi.ecp.busi.order.pay.controller;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.order.OrdConstant;
import com.zengshi.ecp.busi.order.pay.vo.PaymentVO;
import com.zengshi.ecp.busi.order.pay.vo.ROfflineApplyReqVO;
import com.zengshi.ecp.busi.order.pay.vo.SOfflinePicVO;
import com.zengshi.ecp.busi.order.pay.vo.WeiXinVO;
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.order.dubbo.dto.*;
import com.zengshi.ecp.order.dubbo.dto.pay.PayRequestData;
import com.zengshi.ecp.order.dubbo.dto.pay.PayWayRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.PayWayResponse;
import com.zengshi.ecp.order.dubbo.dto.pay.PaymentRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdDetailsRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdOfflineRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdPayRelRSV;
import com.zengshi.ecp.order.dubbo.interfaces.pay.IPayWayRSV;
import com.zengshi.ecp.order.dubbo.interfaces.pay.IPaymentRSV;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import scala.actors.threadpool.Arrays;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping(value="/pay")
public class PayController extends EcpBaseController {

    private static final String MODULE = PayController.class.getName();
    
    @Resource
    private IOrdOfflineRSV ordOfflineRSV;
    
    @Resource
    private IPayWayRSV payWayRSV;
    
    @Resource
    private IPaymentRSV paymentRSV; 
    
    @Resource 
    private IOrdMainRSV ordMainRSV;
    
    @Resource
    private IOrdDetailsRSV ordDetailsRSV;

    @Resource
    private IOrdPayRelRSV ordPayRelRSV;

    //先下支付页面
    /**
     * 先下支付页面
     * offline:(这里用一句话描述这个方法的作用). <br/> 
     * 
     * @author wang 
     * @param model
     * @param orderid
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/offline")
    public String offline(Model model,@RequestParam(value="orderId")String orderid){
        if(StringUtil.isBlank(orderid)){
            throw new BusinessException();
        }
        
//        Long staffId = ParamsTool.getStaffId();
        ROfflinePayRequest  offlinedto = new ROfflinePayRequest();
        Long staffId = offlinedto.getStaff().getId();
        
        offlinedto.setOrderId(orderid);
        offlinedto.setStaffId(staffId);
        ROfflinePayResponse resp = new ROfflinePayResponse();
        try{
            resp = ordOfflineRSV.queryOfflinePay(offlinedto);
        }catch(Exception e){
            LogUtil.error(MODULE, e.getMessage(),e);
        }
        
        model.addAttribute("orderId", orderid);
        model.addAttribute("offlineresp", resp);
        return "/order/pay/pay-offline";
    }
    
    //先下支付信息保存
    /**
     * 先下支付信息保存
     * saveInfo:(这里用一句话描述这个方法的作用). <br/> 
     * 
     * @author wang 
     * @param vo
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/offlineapply")
    @ResponseBody
    public EcpBaseResponseVO offlineApply(@Valid ROfflineApplyReqVO vo){
        EcpBaseResponseVO resp = new EcpBaseResponseVO();
        ROfflineApplyRequest offlinedto = new ROfflineApplyRequest();
        offlinedto.setOrderId(vo.getOrderId());
        offlinedto.setShopId(vo.getShopId());
        
        /**
         * 
         * 请补全接口
         * 
         */
        //offlinedto.setStaffId(ParamsTool.getStaffId());
        offlinedto.setStaffId(offlinedto.getStaff().getId());
        offlinedto.setRemark(vo.getRemark());
        //拼装图片信息
        List<SOfflinePic> pics = new ArrayList<SOfflinePic>();
        for(SOfflinePicVO svo : vo.getAnnex()){
            SOfflinePic pic = new SOfflinePic();
            pic.setPicName(svo.getPicName());
            pic.setVfsId(svo.getVfsId());
            pics.add(pic);
        }
        offlinedto.setAnnex(pics);
        try{
            ordOfflineRSV.offlineApply(offlinedto);
            resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        }catch(Exception e){
            resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            resp.setResultMsg(e.getMessage());
        }
        
        return resp;
    }

    /**
     * 线上支付
     * way:(订单提交成功页面进行该支付). <br/> 
     *
     * @author wangxq
     * @param vo
     * @return
     * @since JDK 1.6
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/way")
    public String way(Model model,HttpServletRequest request,PaymentVO vo){
        
        String showOrderId="";
        
        List<ROrdMainResponse> orderList = (List<ROrdMainResponse>)CacheUtil.getItem(OrdConstant.OnlineOrd.ORDER_ONLINE_KEY+vo.getOnlineKey());
       
        if(orderList == null || orderList.size() == 0){
            throw new BusinessException("session超时");
        }
        long mergeTotalRealMoney = 0;
        long shopId = 0;
        //是否有多个订单。如果只有一个订单不需要选择，会直接跳转到选择支付方式，因此在这里做一步判断
        //
        // 1、在orderList 多个店铺的订单需要支付时，会先选择支付方式 vo的orderIds有值
        if(!StringUtil.isBlank(vo.getOrderIds())){
            String[] orderIds = vo.getOrderIds().split(",");
            for(int i = 0; i<orderIds.length;i++){
                for(ROrdMainResponse resp : orderList){
                    if(orderIds[i].equals(resp.getId())){
                        mergeTotalRealMoney += resp.getRealMoney();
                        shopId = resp.getShopId();
                        if(i==0){
                            showOrderId = resp.getId();
                        }else{
                            showOrderId=showOrderId+","+resp.getId();	
                        }
                    }
                }
            }
        }else{

            //2、在预订单只有一个店铺的订单情况下回直接调用此方法 此时的vo当中的orderIds是没有值的
            for(int index = 0,size = orderList.size(); index < size; index++){
            	ROrdMainResponse ordMainResponse = orderList.get(index);
            	if(index == 0){
	            	shopId = ordMainResponse.getShopId();
	            	showOrderId = ordMainResponse.getId();
            	}
                mergeTotalRealMoney += ordMainResponse.getRealMoney();
            }
        }
        // 获取合并支付的开关 1-表示开启  0-表示关闭
        BaseSysCfgRespDTO payMergeSysCfg = SysCfgUtil.fetchSysCfg(OrdConstants.OrdPayRel.SWITCH_PAY_MERGE);
        if(OrdConstants.OrdPayRel.PAY_MERGE_ENABLE.equals(payMergeSysCfg.getParaValue())){
        	shopId = OrdConstants.OrdPayRel.PAY_WAY_MERGE_CFG_ID;
        }
        PayWayRequest waydto = new PayWayRequest();
        waydto.setShopId(shopId);
        List<PayWayResponse> wayList = new ArrayList<PayWayResponse>();
        try{
            wayList = payWayRSV.getPayWay(waydto);
        }catch(Exception e){
            LogUtil.error(MODULE, e.getMessage(), e);
        }
        CacheUtil.delItem(OrdConstant.OnlineOrd.ORDER_ONLINE_KEY+vo.getOnlineKey());
        model.addAttribute("showOrderIds", showOrderId);       
        model.addAttribute("realMoney", mergeTotalRealMoney);
        model.addAttribute("wayList", wayList);
        return "/order/pay/pay-way";
    }

    /**
     * 线上支付
     * way:(从我的订单进行支付). <br/> 
     *
     * @author panjs
     * @param model
     * @param orderIds
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value="/queryWay")
    public String queryWay(Model model, @RequestParam("orderIds") String orderIds){
        if(!StringUtil.isBlank(orderIds)){
        	ROrderDetailsRequest rdto = new ROrderDetailsRequest();
            List<PayWayResponse> wayList = new ArrayList<PayWayResponse>();
            long mergeTotalRealMoney = 0;
            long shopId = 0l;
            // 查询订单总金额
            String showOrderIds = "";
        	String[] orderIdArry = orderIds.split(",");
        	long orderTime = 0l;
        	try{
        		for(int index = 0; index < orderIdArry.length; index ++){
        			rdto.setOrderId(orderIdArry[index]);
        			ROrderDetailsResponse orderDetailsResponse = ordDetailsRSV.queryOrderDetails(rdto);
        			SOrderDetailsMain ordMain = orderDetailsResponse.getsOrderDetailsMain();
        			if(index == 0){
                    	shopId = ordMain.getShopId();
                    	showOrderIds = ordMain.getId();	
                    	orderTime = ordMain.getOrderTime().getTime();
                    }else{
                    	showOrderIds = showOrderIds + "," + ordMain.getId();	
                    }
        			mergeTotalRealMoney += ordMain.getRealMoney();
            	}
            }catch(Exception e){
                LogUtil.error(MODULE, e.getMessage(),e);
            }
        	// 获取合并支付的开关 1-表示开启  0-表示关闭
            BaseSysCfgRespDTO payMergeSysCfg = SysCfgUtil.fetchSysCfg(OrdConstants.OrdPayRel.SWITCH_PAY_MERGE);
            if(OrdConstants.OrdPayRel.PAY_MERGE_ENABLE.equals(payMergeSysCfg.getParaValue())){
            	shopId = OrdConstants.OrdPayRel.PAY_WAY_MERGE_CFG_ID;
            }
            PayWayRequest waydto = new PayWayRequest();
            waydto.setShopId(shopId);
            wayList = payWayRSV.getPayWay(waydto);
            model.addAttribute("showOrderIds", showOrderIds);       
            model.addAttribute("realMoney", mergeTotalRealMoney);
            java.util.Date now = new Date();
            long l = now.getTime() - orderTime;
            long day = l / (24 * 60 * 60 * 1000);
            long hour = 24 - (l / (60 * 60 * 1000) - day * 24);
            model.addAttribute("wayList", wayList);
            model.addAttribute("hour", hour);
        }
        return "/order/pay/pay-way3";
    }

    /**
     * 上门支付、邮局汇款、银行转账
     * way:(订单提交成功页面). <br/> 
     * 
     * @author panjs 
     * @param model
     * @param request
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value="/payWay/{payTypeKey}")
    public String payWay(Model model,@PathVariable String payTypeKey,HttpServletRequest request){
        String payType = "";
        try{         
            payType = (String)CacheUtil.getItem(OrdConstant.ORDER_PAY_TYPE+payTypeKey);
        }catch(Exception e){
            LogUtil.error(MODULE, e.getMessage(),e);
        } 
        model.addAttribute("payType", payType);
        CacheUtil.delItem(OrdConstant.ORDER_PAY_TYPE+payTypeKey);
        return "/order/pay/pay-way2";
    }
    
    /**
     * 线上支付
     * way:(订单列表). <br/> 
     * 
     * @author wangxq 
     * @param vo
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/orderList/{onlineKey}")
    public String orderList(Model model,@PathVariable String onlineKey,HttpServletRequest request){
  
        List<ROrdMainResponse> orderList = (List<ROrdMainResponse>)CacheUtil.getItem(OrdConstant.OnlineOrd.ORDER_ONLINE_KEY+onlineKey);      
        if(orderList == null || orderList.size() == 0){
            throw new BusinessException("session超时");
        }
        BaseSysCfgRespDTO payMergeSysCfg = SysCfgUtil.fetchSysCfg(OrdConstants.OrdPayRel.SWITCH_PAY_MERGE);
        model.addAttribute("switchPayMerge", payMergeSysCfg.getParaValue());
        model.addAttribute("orderList", orderList);
        CacheUtil.delItem(OrdConstant.OnlineOrd.ORDER_ONLINE_KEY+onlineKey);
        return "/order/pay/pay-orderList";
    }
    
    /**
     *  线上支付
     * requestPayment(返回表单URL、表单数据、字符集)
     * @author panjs 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/requestPayment")
    @ResponseBody
    public Map<String, Object> requestPayment(Model model, PaymentVO vo, HttpServletRequest request){
    	Map<String, Object> map = new HashMap<String, Object>(); 
    	// 多个订单合成一个订单去支付
    	PaymentRequest paymentdto = new PaymentRequest();
        Long session_staffId = paymentdto.getStaff().getId();
    	ROrdPayRelReq reqDTO = new ROrdPayRelReq();
    	String orderIds = vo.getOrderIds();
    	if(StringUtil.isBlank(orderIds)){
    		LogUtil.error(MODULE, "[发起支付请求失败]异常信息: 订单ID参数为空");
            map.put("flag", false);
            map.put("msg", "[发起支付请求失败]异常信息: 订单ID参数为空");
            return map;
    	}
    	String[] orderIdArry = orderIds.split(",");
    	ROrdMainResponse staffIdResp=new ROrdMainResponse();
    	ROrderDetailsRequest rOrderDetailsRequest = new ROrderDetailsRequest();
    	//判断当前orderId是否属于当前staffId
    	for(int i=0;i<orderIdArry.length;i++){
    		String orderId=orderIdArry[i];
    		rOrderDetailsRequest.setOrderId(orderId);
    		staffIdResp=ordMainRSV.queryStaffIdByOrderId(rOrderDetailsRequest);
    		long creat_staffId = staffIdResp.getStaffId();
    		if(creat_staffId!=session_staffId){
    			LogUtil.error(MODULE, "因订单号为["+orderId+"]的商品不属于用户["+paymentdto.getStaff().getStaffCode()+"],无法支付！");
    			map.put("flag", false);
                map.put("msg", "因订单号为["+orderId+"]的商品不属于用户["+paymentdto.getStaff().getStaffCode()+"],无法支付！");
                return map;
    		}
    		
    	}
        reqDTO.setOrderIdList(Arrays.asList(orderIdArry));
        ROrdPayRelResp respDto = ordPayRelRSV.saveOrdPayRel(reqDTO);
        if(respDto == null || StringUtil.isBlank(respDto.getJoinOrderid())){
        	LogUtil.error(MODULE, "[发起支付请求失败]异常信息: 保存合并支付订单表失败");
            map.put("flag", false);
            map.put("msg", "[发起支付请求失败]异常信息: 保存合并支付订单表失败");
            return map;
        }
        
        ROrdCartsChkResponse resp = new ROrdCartsChkResponse();
        
        paymentdto.setOrderId(respDto.getJoinOrderid());
        paymentdto.setPayWay(vo.getPayWay());
        try{
            ROrderDetailsRequest rdor = new ROrderDetailsRequest();
            rdor.setOrderId(respDto.getJoinOrderid());
            rdor.setOper(vo.getOper());
            resp = ordMainRSV.queryOrdOperChk(rdor); 
            map.put("flag",resp.isStatus());
            map.put("msg",resp.getMsg());
            if(resp.isStatus()){
                Map<String, String> extendProps = new HashMap<String, String>();
                extendProps.put("clientIp", ParamsTool.getClientAddr(request));
                PayRequestData payRequestData = paymentRSV.requestPayment(paymentdto, extendProps);
                map.put("actionUrl", payRequestData.getActionUrl());
                map.put("method", payRequestData.getMethod());
                map.put("charset", payRequestData.getCharset());
                if(vo.getPayWay().equals(OrdConstants.PAY_WAY.WeiXin)){
                    payRequestData.getFormData().put("orderIds", orderIds);
                    payRequestData.getFormData().put("mergeTotalRealMoney", vo.getMergeTotalRealMoney()+"");
                    payRequestData.getFormData().put("joinOrderId", respDto.getJoinOrderid());
                }
                map.put("formData", payRequestData.getFormData());
                map.put("flag", true);
                map.put("msg", "发起支付请求成功!");
            }
        }catch(Exception e){
            LogUtil.error(MODULE, "[发起支付请求失败]异常信息:" + e.getMessage(),e);
            map.put("flag", false);
            map.put("msg", "[发起支付请求失败]异常信息:" + e.getMessage());
        } 
        LogUtil.info(MODULE, "发起支付请求返回的数据:" + JSONObject.fromObject(map).toString());
        return map;
    } 
    /**
     *  线上支付---app专用
     * requestPayment(返回表单URL、表单数据、字符集)
     * @author linwei
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/requestPaymentApp")
    @ResponseBody
    public Map<String, Object> requestPaymentApp(Model model, PaymentVO vo, HttpServletRequest request){
    	Map<String, Object> map = new HashMap<String, Object>(); 
    	// 多个订单合成一个订单去支付
    	PaymentRequest paymentdto = new PaymentRequest();
    	ROrdPayRelReq reqDTO = new ROrdPayRelReq();
    	String orderIds = vo.getOrderIds();
    	if(StringUtil.isBlank(orderIds)){
    		LogUtil.error(MODULE, "[发起支付请求失败]异常信息: 订单ID参数为空");
            map.put("flag", false);
            map.put("msg", "[发起支付请求失败]异常信息: 订单ID参数为空");
            return map;
    	}
    	String[] orderIdArry = orderIds.split(",");
        reqDTO.setOrderIdList(Arrays.asList(orderIdArry));
        ROrdPayRelResp respDto = ordPayRelRSV.saveOrdPayRel(reqDTO);
        if(respDto == null || StringUtil.isBlank(respDto.getJoinOrderid())){
        	LogUtil.error(MODULE, "[发起支付请求失败]异常信息: 保存合并支付订单表失败");
            map.put("flag", false);
            map.put("msg", "[发起支付请求失败]异常信息: 保存合并支付订单表失败");
            return map;
        }
        
        ROrdCartsChkResponse resp = new ROrdCartsChkResponse();
        
        paymentdto.setOrderId(respDto.getJoinOrderid());
        paymentdto.setPayWay(vo.getPayWay());
        try{
            ROrderDetailsRequest rdor = new ROrderDetailsRequest();
            rdor.setOrderId(respDto.getJoinOrderid());
            rdor.setOper(vo.getOper());
            resp = ordMainRSV.queryOrdOperChk(rdor); 
            map.put("flag",resp.isStatus());
            map.put("msg",resp.getMsg());
            if(resp.isStatus()){
                Map<String, String> extendProps = new HashMap<String, String>();
                extendProps.put("clientIp", ParamsTool.getClientAddr(request));
                extendProps.put("appflag", "1");
                PayRequestData payRequestData = paymentRSV.requestPayment(paymentdto, extendProps);
                map.put("actionUrl", payRequestData.getActionUrl());
                map.put("method", payRequestData.getMethod());
                map.put("charset", payRequestData.getCharset());
                map.put("formData", payRequestData.getFormData());
                map.put("flag", true);
                map.put("msg", "发起支付请求成功!");
            }
        }catch(Exception e){
            LogUtil.error(MODULE, "[发起支付请求失败]异常信息:" + e.getMessage(),e);
            map.put("flag", false);
            map.put("msg", "[发起支付请求失败]异常信息:" + e.getMessage());
        } 
        return map;
    }    
    /** 
     * check:(验证付款). <br/> 
     * @author panjs 
     * @param model
     * @param orderid
     * @param oper
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/check")
    public Map<String, Object> check(Model model, @RequestParam("orderIds") String orderids) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        String[] ordStr = orderids.split(",");
        /**
         * 如果有多个订单合并支付，先判断这些订单是否都是线上支付
         */
        if(!verifySamePayType(ordStr)){
            //订单的支付方式不一样
            map.put("flag",false);
            map.put("msg","选中的订单存在支付方式不一致！请重新选择！");
            return map;
        }
        map.put("flag",true);
        map.put("orderIds", orderids);
        map.put("msg","success");
        return map;
    }

    /**
     *  微信支付页面
     * payWeiXin(返回二维码URL)
     * @author panjs 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/payWeiXin")
    public String payWeiXin(Model model, WeiXinVO vo){
        model.addAttribute("code_url", vo.getCode_url());
        ROrderDetailsRequest rdto = new ROrderDetailsRequest();
        String [] orderIds = vo.getOrderIds().split(",");
        rdto.setOrderId(orderIds[0]);
        ROrderDetailsResponse resp = new ROrderDetailsResponse();
        resp = ordDetailsRSV.queryOrderDetails(rdto);
        // 订单id
        model.addAttribute("orderIds", vo.getOrderIds());
        model.addAttribute("joinOrderId", vo.getJoinOrderId());
        model.addAttribute("realMoney", vo.getMergeTotalRealMoney());
        model.addAttribute("orderIdCount", orderIds.length);
        // 主订单相关字段
        model.addAttribute("sOrderDetailsMain", resp.getsOrderDetailsMain());
        // 子订单相关字段
        model.addAttribute("sOrderDetailsSubs", resp.getsOrderDetailsSubs());
        return "/order/pay/pay-weixin";
    } 
    
    public boolean verifySamePayType(String[] ords){
        StringBuffer str = new StringBuffer();
        Map<String,String> map = new HashMap<String,String>();
        int count = 0;
        String firstPayType = "";
        if(ords != null && ords.length >= 1){
            ROrderDetailsRequest req = new ROrderDetailsRequest();
            for(int j = 0;j < ords.length; j++){
                req.setOrderId(ords[j]);
                ROrderDetailsResponse respDto = ordDetailsRSV.queryOrderDetails(req);
                if(respDto != null){
                    //如果有支付过的订单
                    if(OrdConstants.OrderStatus.ORDER_STATUS_PAID.equals(respDto.getsOrderDetailsMain().getStatus())){
                        str.append(ords[j]);
                        count ++;
                    }
                    if(j == 0){
                        firstPayType = respDto.getsOrderDetailsMain().getPayType();
                    }
                    map.put(ords[j], respDto.getsOrderDetailsMain().getPayType());
                }
            }
            if(count >= 1){
                return false;
            }else{
                for(Map.Entry<String, String> mp : map.entrySet()){
                    if(!firstPayType.equals(mp.getValue())){
                        return false;
                    }
                }
            }
        }
        return true;
    }

}

