package com.zengshi.ecp.busi.seller.order.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.order.OrdConstant;
import com.zengshi.ecp.busi.seller.order.vo.RConfirmDeliveReqVO;
import com.zengshi.ecp.busi.seller.order.vo.RConfirmSubInfoVO;
import com.zengshi.ecp.busi.seller.order.vo.RDelyOrderReqVO;

import com.zengshi.ecp.order.dubbo.dto.RConfirmDeliveRequest;
import com.zengshi.ecp.order.dubbo.dto.RConfirmSubInfo;
import com.zengshi.ecp.order.dubbo.dto.RDeliveryPrintRequest;
import com.zengshi.ecp.order.dubbo.dto.RDeliveryPrintResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrderGiftsResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrderIdRequest;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.dto.RShopOrderResponse;
import com.zengshi.ecp.order.dubbo.dto.RShowOrdSubRequest;
import com.zengshi.ecp.order.dubbo.dto.RShowOrdSubResponse;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsMain;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdDeliveryRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdSubRSV;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016-4-7上午10:45:04  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6
 * 
 * 订单发货管理
 */
@Controller
@RequestMapping(value = "/seller/order/delivery")
public class OrdDeliveryController extends EcpBaseController{
    
    private static String MODULE = OrdDeliveryController.class.getName();
    @Resource(name = "ordMainRSV")
    private IOrdMainRSV ordMainRSV;
    
    @Resource
    private IOrdDeliveryRSV ordDeliveryRSV;
    
    @Resource
    private IShopInfoRSV shopInfoRSV;
    
    @Resource(name="ordSubRSV")
    private IOrdSubRSV ordSubRSV;
    
    private static final String SELLER_ORDER_DELIVERY_VM_PATH = "/seller/order";

    @RequestMapping(value="index")
    public String index(Model model, @RequestParam(value="shopId", required=false)Long shopId)
    {
        model.addAttribute("begDate", DateUtil.getOffsetDaysDate(DateUtil.getTheDayFirstSecond(DateUtil.getSysDate()), -90));
        model.addAttribute("endDate", DateUtil.getDate());
        model.addAttribute("shopId", shopId);
        return SELLER_ORDER_DELIVERY_VM_PATH + "/delivery-main";
    }
    
    /**
     * 
     * delyedList:(代发货). <br/> 
     * 
     * @author PJieWin 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/delyedlist")
    public String delyedList(Model model, RDelyOrderReqVO vo) throws Exception {

        String status = OrdConstants.ShopRequestStatus.REQUEST_STATUS_SEND;
        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = queryList(model,vo,status);
        model.addAttribute("delyedListPage", respVO);
        return SELLER_ORDER_DELIVERY_VM_PATH + "/tab/delyed-list";
    }
    /**
     *已发货
     * 
     * @param model
     * @param vo
     * @return
     * @throws Exception
     */
    @RequestMapping("/sendlist")
    public String sendList(Model model, RDelyOrderReqVO vo) throws Exception {

        String status = OrdConstants.ShopRequestStatus.REQUEST_STATUS_DELY;
        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = queryList(model,vo,status);

        model.addAttribute("sendListPage", respVO);
        return SELLER_ORDER_DELIVERY_VM_PATH + "/tab/sended-list";
    }
    public EcpBasePageRespVO<Map> queryList(Model model, RDelyOrderReqVO vo, String status) throws Exception {
        // 后场服务所需要的DTO；
        RQueryOrderRequest r = new RQueryOrderRequest();
        r = vo.toBaseInfo(RQueryOrderRequest.class);
        ObjectCopyUtil.copyObjValue(vo, r, "", false);
/*        r.setEndDate(new Timestamp(DateUtils.addDays(vo.getEndDate(), 1).getTime()));*/
//        r.setShopId(ParamsTool.getShopId());
        r.setSysType("00");
//        r.setCategoryCodes(ParamsTool.getCategoryCodes());
        r.setCategoryCodes(null);
        r.setStatus(status);
        // 其它的查询条件；
        LogUtil.debug(MODULE, vo.toString());

        PageResponseDTO<RShopOrderResponse> t = ordMainRSV.queryOrderByShopId(r);

        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = bulidPageResp(t);
        
        return respVO;
    }

    
    @RequestMapping("/dosend/{shopId}/{orderId}")
    public String dosend(Model model,@PathVariable("shopId")String shopId,@PathVariable("orderId")String orderId, EcpBasePageReqVO vo)throws Exception
    {


        if (StringUtil.isBlank(orderId)) {
            throw new BusinessException("order.orderid.null.error");
        }

        //获取赠品
        ROrderIdRequest orderIdRequest = new ROrderIdRequest();
        orderIdRequest.setOrderId(orderId);
        ROrderGiftsResponse gifts = ordMainRSV.queryOrderGift(orderIdRequest);

        //发票信息打印
        RDeliveryPrintRequest printRequest = new RDeliveryPrintRequest();
        printRequest.setOrderId(orderId);
        RDeliveryPrintResponse printResponse = ordDeliveryRSV.queryInvoiceInfo(printRequest);
        
        if(!shopId.equals(""+printResponse.getsOrderDetailsMain().getShopId())){
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311013);       
        }
        
        Map<Long, String> logistics = shopInfoRSV.listExpress(Long.valueOf(printResponse.getsOrderDetailsMain().getShopId()));
        LogUtil.info(MODULE, logistics + "");
        // 根据订单查店铺id
        model.addAttribute("logistics", logistics);

        SOrderDetailsMain ordMain = printResponse.getsOrderDetailsMain();
        // ----这里应该把物流或者自提的文本对应信息传递回去
        model.addAttribute("orderId", orderId);
        model.addAttribute("dispatchType", ordMain.getDispatchType());
        model.addAttribute("contactName", ordMain.getContactName());
        model.addAttribute("contactPhone", ordMain.getContactPhone());
        model.addAttribute("chnlAddress", ordMain.getChnlAddress());
        model.addAttribute("invoiceType", ordMain.getInvoiceType());
        String invoiceTitle = "";
        if(ordMain.getInvoiceType().equals(OrdConstant.InvoiceType.COMM)) invoiceTitle = printResponse.getsOrderDetailsComm().getInvoiceTitle();
        if(ordMain.getInvoiceType().equals(OrdConstant.InvoiceType.TAX)) invoiceTitle = printResponse.getsOrderDetailsTax().getInvoiceTitle();

        model.addAttribute("invoiceTitle",invoiceTitle);

        String invoiceContent = "";
        if(ordMain.getInvoiceType().equals(OrdConstant.InvoiceType.COMM)) invoiceContent = printResponse.getsOrderDetailsComm().getInvoiceContent();

        String detailFlag = "0";
        if(ordMain.getInvoiceType().equals(OrdConstant.InvoiceType.COMM)) detailFlag = printResponse.getsOrderDetailsComm().getDetailFlag();
        model.addAttribute("invoiceContent",invoiceContent);
        model.addAttribute("detailFlag",detailFlag);
        //赠品信息
        model.addAttribute("gifts",gifts);
        model.addAttribute("shopId",shopId);
        queryOrder(model, orderId, vo);
        
        return SELLER_ORDER_DELIVERY_VM_PATH + "/delivery-dosend";
    }
    @RequestMapping(value="/ordersubs")
    public String queryOrder(Model model, @RequestParam(value="orderId") String orderId, EcpBasePageReqVO vo) throws Exception{
        RShowOrdSubRequest r = vo.toBaseInfo(RShowOrdSubRequest.class);
        //RShowOrdSubRequest r = new RShowOrdSubRequest();
        
        if(StringUtil.isBlank(orderId)){
            throw new BusinessException("order.orderid.null.error");
        }
        r.setOrderId(orderId);

        PageResponseDTO<RShowOrdSubResponse> t = ordSubRSV.queryOrderSub(r);
        
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
        
        model.addAttribute("subordersdetail", respVO);
        return "/seller/order/tab/order-subs-send";
    }
    @RequestMapping(value="/confirmsend")
    @ResponseBody
    public EcpBaseResponseVO confirmSend(@Valid RConfirmDeliveReqVO vo){
        EcpBaseResponseVO resp = new EcpBaseResponseVO();
        List<RConfirmSubInfo> rConfirmSubInfos = new ArrayList<RConfirmSubInfo>();
        RConfirmDeliveRequest rdto = new RConfirmDeliveRequest();
        try{
            
            RDeliveryPrintRequest printRequest = new RDeliveryPrintRequest();
            printRequest.setOrderId(vo.getOrderId());
            RDeliveryPrintResponse printResponse = ordDeliveryRSV.queryInvoiceInfo(printRequest);
            
            if(!(""+vo.getShopId()).equals(""+printResponse.getsOrderDetailsMain().getShopId())){
                throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311013);       
            }
            
            ObjectCopyUtil.copyObjValue(vo, rdto, "", false);
            for(RConfirmSubInfoVO ordsubvo :  vo.getrConfirmSubInfo()){
                RConfirmSubInfo rsubdto = new RConfirmSubInfo();
                ObjectCopyUtil.copyObjValue(ordsubvo, rsubdto, "", true);
                rConfirmSubInfos.add(rsubdto);
            }
            rdto.setrConfirmSubInfo(rConfirmSubInfos);
            ordDeliveryRSV.orderDelivery(rdto);
            resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        }catch(Exception e){
            LogUtil.error(MODULE, e.getMessage());
            resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            resp.setResultMsg(e.getMessage()+",不允许发货");
        }
        
        return resp;
    }
    // 避免空指针异常
    @SuppressWarnings("rawtypes")
    private EcpBasePageRespVO<Map> bulidPageResp(PageResponseDTO<RShopOrderResponse> t)
            throws Exception {
        EcpBasePageRespVO<Map> respVO = new EcpBasePageRespVO<Map>();
        if (t != null) {
            respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
        }
        return respVO;
    }
}

