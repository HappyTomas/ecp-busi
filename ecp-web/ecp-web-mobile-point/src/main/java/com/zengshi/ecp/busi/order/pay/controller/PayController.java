package com.zengshi.ecp.busi.order.pay.controller;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.order.OrdConstant;
import com.zengshi.ecp.busi.order.pay.vo.PaymentVO;
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.order.dubbo.dto.*;
import com.zengshi.ecp.order.dubbo.dto.pay.*;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdDetailsRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdOfflineRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdPayRelRSV;
import com.zengshi.ecp.order.dubbo.interfaces.pay.IPayWayRSV;
import com.zengshi.ecp.order.dubbo.interfaces.pay.IPaymentRSV;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.StaffLocaleUtil;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.CustWechatRelReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustWechatRelRespDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustWechatRelRSV;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping(value="/pay")
public class PayController extends EcpBaseController {

    private static final String MODULE = PayController.class.getName();
    
    @Resource
    private ICustWechatRelRSV custWechatRelRSV;

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

    @Resource
    private IOrdPayRelRSV iOrdPayRelRSV;


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
            //针对微商手机web方式,屏蔽其他
            for(int i=0;i<wayList.size();i++){
                PayWayResponse payWays = wayList.get(i);
                for(int b=0;b<payWays.getPayWayList().size();){
                    PayWayItem item = payWays.getPayWayList().get(b);
                    if(!"9006".equals(item.getId())){
                        payWays.getPayWayList().remove(item);
                    }else{
                        b++;
                    }
                }
            }
        }catch(Exception e){
            LogUtil.error(MODULE, e.getMessage(), e);
        }
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
                    if(StringUtil.isBlank(orderIdArry[index])) continue;
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
            //针对微商手机web方式,屏蔽其他
            for(int i=0;i<wayList.size();i++){
                PayWayResponse payWays = wayList.get(i);
                for(int b=0;b<payWays.getPayWayList().size();){
                    PayWayItem item = payWays.getPayWayList().get(b);
                    if(!"9006".equals(item.getId())){
                        payWays.getPayWayList().remove(item);
                    }else{
                        b++;
                    }
                }
            }
            model.addAttribute("showOrderIds", showOrderIds);
            model.addAttribute("realMoney", mergeTotalRealMoney);
            Date now = new Date();
            long l = now.getTime() - orderTime;
            long day = l / (24 * 60 * 60 * 1000);
            long hour = 24 - (l / (60 * 60 * 1000) - day * 24);
            model.addAttribute("wayList", wayList);
            model.addAttribute("hour", hour);
        }
        return "/order/pay/pay-way";
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
        ROrdPayRelReq reqDTO = new ROrdPayRelReq();
        String orderIds = vo.getOrderIds();
        if(StringUtil.isBlank(orderIds)){
            LogUtil.error(MODULE, "[发起支付请求失败]异常信息: 订单ID参数为空");
            map.put("flag", false);
            map.put("msg", "[发起支付请求失败]异常信息: 订单ID参数为空");
            return map;
        }
        String[] orderIdArry = orderIds.split(",");
        reqDTO.setOrderIdList(scala.actors.threadpool.Arrays.asList(orderIdArry));
        ROrdPayRelResp respDto = ordPayRelRSV.saveOrdPayRel(reqDTO);
        if(respDto == null || StringUtil.isBlank(respDto.getJoinOrderid())){
            LogUtil.error(MODULE, "[发起支付请求失败]异常信息: 保存合并支付订单表失败");
            map.put("flag", false);
            map.put("msg", "[发起支付请求失败]异常信息: 保存合并支付订单表失败");
            return map;
        }

        ROrdCartsChkResponse resp = new ROrdCartsChkResponse();
        PaymentRequest paymentdto = new PaymentRequest();
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

                if(OrdConstants.PAY_WAY.WeChat.equals(vo.getPayWay())){
                    Map<String, String> extendProps = new HashMap<String, String>();
                    extendProps.put("clientIp", ParamsTool.getClientAddr(request));
                    LogUtil.info(MODULE, "本机IP地址：" + ParamsTool.getClientAddr(request));
//                    extendProps.put("openid","orEgPv2pA9TIGxuW8IpKQUhDxvS8");

                    CustWechatRelReqDTO wechatReq = new CustWechatRelReqDTO();
                    wechatReq.setStaffCode(wechatReq.getStaff().getStaffCode());
                    wechatReq.setStaffId(wechatReq.getStaff().getId());
                    wechatReq.setPageNo(1);
                    wechatReq.setPageSize(1);
                    PageResponseDTO<CustWechatRelRespDTO> page = custWechatRelRSV.findCustWechatRel(wechatReq);
                    String openid = page.getResult().get(0).getWechatId();
                    LogUtil.info(MODULE, "微信公众号：" + openid);
                    extendProps.put("openid", openid);

                    PayRequestData payRequestData = paymentRSV.requestPayment(paymentdto, extendProps);
                    LogUtil.info(MODULE, "后场与微信支付预订单回调结果：" + payRequestData.getFormData());
                    map.put("formData", payRequestData.getFormData());
                    map.put("flag", true);
                    map.put("msg", "发起支付请求成功!");

//                    String joinOrderId = respDto.getJoinOrderid();
//                    String realMoney = String.valueOf(respDto.getRealMoney());
//                    String orderTime = String.valueOf(respDto.getOrderTime().getTime());
//                    Map<String, String> weChatPre = new HashMap<>();
//                    weChatPre.put("orderId",joinOrderId);
//                    weChatPre.put("realMoney",realMoney);
//                    weChatPre.put("orderTime",orderTime);
//                    LogUtil.info(MODULE,"微信支付预处理requestPayment"+JSON.toJSONString(weChatPre));
                    CacheUtil.addItem(OrdConstants.PayWayWeChat.REDIS_PREFIX+reqDTO.getStaff().getId(), respDto);

                }else{
                    Map<String, String> extendProps = new HashMap<String, String>();
                    PayRequestData payRequestData = paymentRSV.requestPayment(paymentdto, extendProps);
                    map.put("actionUrl", payRequestData.getActionUrl());
                    map.put("method", payRequestData.getMethod());
                    map.put("charset", payRequestData.getCharset());
                    map.put("formData", payRequestData.getFormData());
                    map.put("flag", true);
                    map.put("msg", "发起支付请求成功!");
                }
            }
        }catch(Exception e){
            LogUtil.error(MODULE, "[发起支付请求失败]异常信息:" + e.getMessage(),e);
            map.put("flag", false);
            map.put("msg", "[发起支付请求失败]异常信息:" + e.getMessage());
        }
        return map;
    }

    /**
     * 线上支付_确认订单
     * @param model
     * @param request
     * @return 订单列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/orderList/{onlineKey}")
    public String orderList(Model model,@PathVariable String onlineKey,HttpServletRequest request){
       
        List<ROrdMainResponse> orderList = (List<ROrdMainResponse>)CacheUtil.getItem(OrdConstant.OnlineOrd.ORDER_ONLINE_KEY+onlineKey);      
        if(orderList == null || orderList.size() == 0){
            throw new BusinessException("session超时");
        }
        model.addAttribute("orderList",orderList);
        return "/order/pay/pay-orderList";
    }
}

