package com.zengshi.ecp.busi.seller.order.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.order.vo.RBackConfirmReqVO;
import com.zengshi.ecp.busi.seller.order.vo.RBackGdsReviewReqVO;
import com.zengshi.ecp.busi.seller.order.vo.ROrdBackReqVO;
import com.zengshi.ecp.order.dubbo.dto.RBackApplyInfoResp;
import com.zengshi.ecp.order.dubbo.dto.RBackConfirmReq;
import com.zengshi.ecp.order.dubbo.dto.RBackPayInfoResp;
import com.zengshi.ecp.order.dubbo.dto.RBackReviewReq;
import com.zengshi.ecp.order.dubbo.dto.RBackReviewResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackResp;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayRefundRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayRefundResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdBackGdsRSV;
import com.zengshi.ecp.order.dubbo.interfaces.pay.IPaymentRSV;
import com.zengshi.ecp.order.dubbo.util.BackConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping(value = "/seller/order/backgds")
public class SellerBackgdsController extends EcpBaseController{
    
    private static String MODULE = SellerBackgdsController.class.getName();
    
    @Resource
    private IOrdBackGdsRSV ordBackGdsRSV;
    
    @Resource
    private IPaymentRSV paymentRSV;
    
    private static final String SELLER_ORDER_DELIVERY_VM_PATH = "/seller/order";

    @RequestMapping(value="index")
    public String index(Model model, @RequestParam(value="shopId", required=false)Long shopId) throws Exception
    {
        model.addAttribute("begDate", DateUtil.getOffsetDaysDate(DateUtil.getTheDayFirstSecond(DateUtil.getSysDate()), -90));
        model.addAttribute("endDate", DateUtil.getDate());
        model.addAttribute("shopId", shopId);
        return SELLER_ORDER_DELIVERY_VM_PATH + "/seller-backgds";
    }
    @RequestMapping("/querytodo")
    public String queryOrderTodo(Model model, ROrdBackReqVO rOrdBackReqVO) throws Exception {
        LogUtil.info(MODULE, JSON.toJSONString(rOrdBackReqVO).toString());
        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        ROrderBackReq rOrderBackReq = new ROrderBackReq();
        rOrderBackReq = rOrdBackReqVO.toBaseInfo(ROrderBackReq.class);
        ObjectCopyUtil.copyObjValue(rOrdBackReqVO, rOrderBackReq, "", false);
        rOrderBackReq.setEndDate(new Timestamp(DateUtils.addDays(rOrderBackReq.getEndDate(), 1).getTime()));
        rOrderBackReq.setTabFlag("00"); //待处理
        PageResponseDTO<ROrderBackResp> resp = this.ordBackGdsRSV.queryBackGdsByShop(rOrderBackReq);
        model.addAttribute("resp", resp);
        return SELLER_ORDER_DELIVERY_VM_PATH + "/backgdslist/seller-backgds-todo";
    }
    @RequestMapping("/queryhandled")
    public String queryOrderHandled(Model model, ROrdBackReqVO rOrdBackReqVO) throws Exception {
        LogUtil.info(MODULE, JSON.toJSONString(rOrdBackReqVO).toString());
        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        ROrderBackReq rOrderBackReq = new ROrderBackReq();
        rOrderBackReq = rOrdBackReqVO.toBaseInfo(ROrderBackReq.class);
        ObjectCopyUtil.copyObjValue(rOrdBackReqVO, rOrderBackReq, "", false);
        rOrderBackReq.setEndDate(new Timestamp(DateUtils.addDays(rOrderBackReq.getEndDate(), 1).getTime()));
        rOrderBackReq.setTabFlag("01"); //已处理
        PageResponseDTO<ROrderBackResp> resp = this.ordBackGdsRSV.queryBackGdsByShop(rOrderBackReq);
        model.addAttribute("resp", resp);
        return SELLER_ORDER_DELIVERY_VM_PATH + "/backgdslist/seller-backgds-handled";
    }
    
    /** 
     * backGdsReview:退货审核信息查询. <br/> 
     * @author cbl 
     * @param model
     * @param vo
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/review")
    public String backGdsReview(Model model, @Valid RBackGdsReviewReqVO vo) {
        LogUtil.info(MODULE, JSON.toJSONString(vo).toString());
        ROrderBackReq rOrderBackReq = new ROrderBackReq();
        rOrderBackReq = vo.toBaseInfo(ROrderBackReq.class);
        ObjectCopyUtil.copyObjValue(vo, rOrderBackReq, "", false);
        RBackReviewResp resp = this.ordBackGdsRSV.queryBackGdsReview(rOrderBackReq);
        model.addAttribute("rBackApplyResp", resp.getrBackApplyResp());
        model.addAttribute("rBackGdsResps", resp.getrBackGdsResps());
        model.addAttribute("rBackApplyInfoResp", resp.getrBackApplyInfoResp());
        model.addAttribute("rBackPicResps", resp.getrBackPicResps());
        model.addAttribute("shareInfo", JSON.toJSONString(resp.getrBackApplyInfoResp()).toString());
        return SELLER_ORDER_DELIVERY_VM_PATH + "/seller-backgds-review";
    }
    
    /** 
     * confirmReview:退货提交审核. <br/> 
     * @author cbl 
     * @param vo
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/confirmReview")
    @ResponseBody
    public EcpBaseResponseVO confirmgReview(@Valid RBackGdsReviewReqVO vo) {
        LogUtil.info(MODULE, "---------------"+JSON.toJSONString(vo).toString());
        EcpBaseResponseVO resp = new EcpBaseResponseVO();  
        try { 
            RBackReviewReq rBackReviewReq = new RBackReviewReq();
            rBackReviewReq = vo.toBaseInfo(RBackReviewReq.class);
            ObjectCopyUtil.copyObjValue(vo, rBackReviewReq, "", false);
            RBackApplyInfoResp rBackApplyInfoResp = null;
            if(vo.getStatus().equals("1")) {
                rBackReviewReq.setStatus(BackConstants.Status.REVIEW_PASS);
                rBackApplyInfoResp = JSON.parseObject(vo.getShareInfo(), RBackApplyInfoResp.class);
            } else if(vo.getStatus().equals("0")){
                rBackReviewReq.setStatus(BackConstants.Status.REFUSE);
            } 
            rBackReviewReq.setApplyType(BackConstants.ApplyType.BACK_GDS);
            rBackReviewReq.setrBackApplyInfoResp(rBackApplyInfoResp);
            this.ordBackGdsRSV.saveBackGdsReview(rBackReviewReq);
            resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS); 
        }catch(Exception e){
            LogUtil.error(MODULE, "============出错了============="+e.getMessage());
            resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            resp.setResultMsg(e.getMessage());
        }
        return resp;
    }
    
    /** 
     * confirmReceipt:确认收货. <br/> 
     * @author cbl 
     * @param vo
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/receipt")
    @ResponseBody
    public EcpBaseResponseVO confirmReceipt(@Valid RBackGdsReviewReqVO vo) {
        LogUtil.info(MODULE, "---------------"+JSON.toJSONString(vo).toString());
        EcpBaseResponseVO resp = new EcpBaseResponseVO();  
        try { 
            RBackConfirmReq rBackConfirmReq = new RBackConfirmReq();
            rBackConfirmReq = vo.toBaseInfo(RBackConfirmReq.class);
            ObjectCopyUtil.copyObjValue(vo, rBackConfirmReq, "", false);
            this.ordBackGdsRSV.saveBackGdsReceipt(rBackConfirmReq);
            resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS); 
        }catch(Exception e){
            LogUtil.error(MODULE, "============出错了============="+e.getMessage());
            resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            resp.setResultMsg(e.getMessage());
        }
        return resp;
    }
    /** 
     * queryRefund:确认退款信息查询. <br/> 
     * @author cbl 
     * @param model
     * @param vo
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/queryRefund")
    public String queryRefund(Model model,@Valid RBackConfirmReqVO vo) {
        model.addAttribute("vo", vo);
        model.addAttribute("applyType", "1");
        return SELLER_ORDER_DELIVERY_VM_PATH + "/confirmrefund/confirmrefund";
    }
    /** 
     * confirmRefund:确认退款. <br/> 
     * @author cbl 
     * @param model
     * @param vo
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/confirmRefund")
    @ResponseBody
    public EcpBaseResponseVO confirmRefund(Model model, @Valid RBackConfirmReqVO vo) {
        LogUtil.info(MODULE, JSON.toJSONString(vo).toString());
        EcpBaseResponseVO resp = new EcpBaseResponseVO();  
        try { 
            RBackConfirmReq rBackConfirmReq = new RBackConfirmReq();
            rBackConfirmReq = vo.toBaseInfo(RBackConfirmReq.class);
            ObjectCopyUtil.copyObjValue(vo, rBackConfirmReq, "", false);
            this.ordBackGdsRSV.saveBackGdsPayed(rBackConfirmReq);
            resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS); 
        }catch(Exception e){
            LogUtil.error(MODULE, "============出错了============="+e.getMessage());
            resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            resp.setResultMsg(e.getMessage());
        }
        return resp;
    }
    /** 
     * onlineRefund:线上退款. <br/> 
     * @author cbl 
     * @param model
     * @param vo
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/onlineRefund")
    @ResponseBody
    public RPayRefundResponse onlineRefund(Model model, @Valid RBackConfirmReqVO vo) {
        LogUtil.info(MODULE, JSON.toJSONString(vo).toString());
        RPayRefundResponse rPayRefundResponse = null;
        try { 
            RBackConfirmReq rBackConfirmReq = new RBackConfirmReq();
            rBackConfirmReq = vo.toBaseInfo(RBackConfirmReq.class);
            ObjectCopyUtil.copyObjValue(vo, rBackConfirmReq, "", false);
            ROrderBackReq rOrderBackReq = new ROrderBackReq();
            rOrderBackReq.setBackId(rBackConfirmReq.getBackId());
            rOrderBackReq.setOrderId(rBackConfirmReq.getOrderId());
            RBackPayInfoResp  rBackPayInfoResp  = this.ordBackGdsRSV.queryBackPayInfo(rOrderBackReq);
            RPayRefundRequest rPayRefundRequest = new RPayRefundRequest();
            rPayRefundRequest.setBackId(rBackPayInfoResp.getrBackApplyResp().getBackId());
            rPayRefundRequest.setOrderId(rBackPayInfoResp.getrBackApplyResp().getOrderId());
            rPayRefundRequest.setPayWay(rBackPayInfoResp.getsOrderDetailsMain().getPayWay());
            rPayRefundRequest.setRefundAmount(rBackPayInfoResp.getrBackApplyResp().getBackMoney());
            Map<String,String> val = new HashMap<String, String>();
            rPayRefundResponse  =  this.paymentRSV.refund(rPayRefundRequest, val);
            if(rPayRefundResponse == null ){
                rPayRefundResponse = new RPayRefundResponse();
                rPayRefundResponse.setRefundMethod(OrdConstants.PayStatus.PAY_REFUND_METHOD_01);
                rPayRefundResponse.setFlag(false);
                rPayRefundResponse.setMessage("支付报错,请联系管理员");
            }
            
        }catch(Exception e){
            LogUtil.error(MODULE, "============出错了=============",e);
            rPayRefundResponse = new RPayRefundResponse();
            rPayRefundResponse.setRefundMethod(OrdConstants.PayStatus.PAY_REFUND_METHOD_01);
            rPayRefundResponse.setFlag(false);
            rPayRefundResponse.setMessage("支付报错,请联系管理员");
        }
        return rPayRefundResponse;
    }
}

