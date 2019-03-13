package com.zengshi.ecp.busi.order.controller;

import java.sql.Timestamp;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.order.vo.RQueryOrderReqVO;
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.order.dubbo.dto.RCustomerOrdResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdReceiptRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdReceiptRSV;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

@Controller
@RequestMapping(value="/order")
public class MyOrdController extends EcpBaseController {
    private static final String MODULE = MyOrdController.class.getName();
    
    @Resource 
    private IOrdMainRSV ordMainRSV;
    
    @Resource
    private IOrdReceiptRSV ordReceiptRSV;
    
    /**
     * 
     * init:待支付列表获取
     * 
     * @author wang 
     * @param model
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/pay")
    public String payList(Model model,RQueryOrderReqVO vo) throws Exception{
        LogUtil.debug(MODULE, vo.toString());
        //后场服务所需要的DTO；
        RQueryOrderRequest rdor = vo.toBaseInfo(RQueryOrderRequest.class);
        String status = OrdConstants.CustomerRequestStatus.REQUEST_STATUS_PAY;
        
//        rdor.setStaffId(ParamsTool.getStaffId());
        rdor.setStaffId(rdor.getStaff().getId());
        
        rdor.setSiteId(0l);
        rdor.setSysType("00");
        rdor.setStatus(status); // 
        
        ObjectCopyUtil.copyObjValue(vo, rdor, "", false);
        PageResponseDTO<RCustomerOrdResponse> rdors = this.ordMainRSV.queryOrderByStaffId(rdor);
        if(rdors==null){
            rdors = new PageResponseDTO<RCustomerOrdResponse>();
            rdors.setPageSize(1);
        }
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        model.addAttribute("status", status);
        
        //返回时间
        Map<String,Timestamp> dates = ParamsTool.params(vo);
        model.addAllAttributes(dates);
        model.addAttribute("resp",rdors);
        return "/order/pay-list";
    }
    
    @RequestMapping(value="/send")
    public String sendList(Model model,RQueryOrderReqVO vo) throws Exception{

        LogUtil.debug(MODULE, vo.toString());
        //后场服务所需要的DTO；
        RQueryOrderRequest rdor = vo.toBaseInfo(RQueryOrderRequest.class);

        String status = OrdConstants.CustomerRequestStatus.REQUEST_STATUS_SEND;
        //rdor.setStaffId(ParamsTool.getStaffId());
        rdor.setStaffId(rdor.getStaff().getId());
        rdor.setSiteId(2l);
        rdor.setSysType("00");
        rdor.setStatus(status); // 
        
        ObjectCopyUtil.copyObjValue(vo, rdor, "", false);
        PageResponseDTO<RCustomerOrdResponse> rdors = this.ordMainRSV.queryOrderByStaffId(rdor);
        if(rdors==null){
            rdors = new PageResponseDTO<RCustomerOrdResponse>();
            rdors.setPageSize(1);
        }
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        model.addAttribute("status", status);
        //返回时间
        Map<String,Timestamp> dates = ParamsTool.params(vo);
        model.addAllAttributes(dates);
        model.addAttribute("resp",rdors);
        return "/order/send-list";
    }
    
    @RequestMapping(value="/recept")
    public String recept(Model model,RQueryOrderReqVO vo) throws Exception{
        //后场服务所需要的DTO；
        LogUtil.debug(MODULE, vo.toString());
        //后场服务所需要的DTO；
        RQueryOrderRequest rdor = vo.toBaseInfo(RQueryOrderRequest.class);

        String status = OrdConstants.CustomerRequestStatus.REQUEST_STATUS_RECEPT;
        //rdor.setStaffId(ParamsTool.getStaffId());
        rdor.setStaffId(rdor.getStaff().getId());
        rdor.setSiteId(0l);
        rdor.setSysType("00");
        rdor.setStatus(status); // 
        
        ObjectCopyUtil.copyObjValue(vo, rdor, "", false);
        PageResponseDTO<RCustomerOrdResponse> rdors = this.ordMainRSV.queryOrderByStaffId(rdor);
        if(rdors==null){
            rdors = new PageResponseDTO<RCustomerOrdResponse>();
            rdors.setPageSize(1);
        }
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        model.addAttribute("status", status);
        //返回时间
        Map<String,Timestamp> dates = ParamsTool.params(vo);
        model.addAllAttributes(dates);
        model.addAttribute("resp",rdors);
        return "/order/recept-list";
    }
    
    //买家确认收货
    @RequestMapping(value="/confirmord")
    @ResponseBody
    public EcpBaseResponseVO  confirmOrd(@RequestParam(value="orderId")String orderId,@RequestParam(value="oper")String oper){
        ROrdCartsChkResponse resp = new ROrdCartsChkResponse();
        EcpBaseResponseVO ecpResp = new EcpBaseResponseVO();
        ROrdReceiptRequest rdto = new ROrdReceiptRequest();
        try {
            ROrderDetailsRequest rdor = new ROrderDetailsRequest();
            rdor.setOrderId(orderId);
            rdor.setOper(oper);
            resp = ordMainRSV.queryOrdOperChk(rdor); 
            ecpResp.setResultFlag(resp.isStatus()+"");
            ecpResp.setResultMsg(resp.getMsg());
            if(resp.isStatus()==true){
                if(StringUtil.isBlank(orderId)){
                    throw new BusinessException("order.null.error");
                }
                rdto.setOrderId(orderId); 
                rdto.setStaffId(rdto.getStaff().getId()); 
                ordReceiptRSV.orderReceipt(rdto);
            }
        } catch (Exception e) {
            ecpResp.setResultFlag("false");
            ecpResp.setResultMsg(e.getMessage());
        } 
        return ecpResp;
    }

    
    @RequestMapping(value="/recepted")
    public String recepted(Model model,RQueryOrderReqVO vo) throws Exception{
        //后场服务所需要的DTO；
        LogUtil.debug(MODULE, vo.toString());
        //后场服务所需要的DTO；
        RQueryOrderRequest rdor = vo.toBaseInfo(RQueryOrderRequest.class);

        String status = OrdConstants.CustomerRequestStatus.REQUEST_STATUS_RECEPTED;
        //rdor.setStaffId(ParamsTool.getStaffId());
        rdor.setStaffId(rdor.getStaff().getId());
        rdor.setSiteId(0l);
        rdor.setSysType("00");
        rdor.setStatus(status); // 
        
        ObjectCopyUtil.copyObjValue(vo, rdor, "", false);
        PageResponseDTO<RCustomerOrdResponse> rdors = this.ordMainRSV.queryOrderByStaffId(rdor);
        if(rdors==null){
            rdors = new PageResponseDTO<RCustomerOrdResponse>();
            rdors.setPageSize(1);
        }
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        model.addAttribute("status", status);
        //返回时间
        Map<String,Timestamp> dates = ParamsTool.params(vo);
        model.addAllAttributes(dates);
        model.addAttribute("resp",rdors);
        return "/order/recepted-list";
    }
    
    
    @RequestMapping(value="/cancel")
    public String cancel(Model model,RQueryOrderReqVO vo) throws Exception{
        //后场服务所需要的DTO；
        LogUtil.debug(MODULE, vo.toString());
        //后场服务所需要的DTO；
        RQueryOrderRequest rdor = vo.toBaseInfo(RQueryOrderRequest.class);

        String status = OrdConstants.CustomerRequestStatus.REQUEST_STATUS_CANCLE;
//        rdor.setStaffId(ParamsTool.getStaffId());
        rdor.setStaffId(rdor.getStaff().getId());
        rdor.setCurrentSiteId(rdor.getCurrentSiteId());
        rdor.setSysType("00");
        rdor.setStatus(status); // 
        
        ObjectCopyUtil.copyObjValue(vo, rdor, "", false);
        PageResponseDTO<RCustomerOrdResponse> rdors = this.ordMainRSV.queryOrderByStaffId(rdor);
        if(rdors==null){
            rdors = new PageResponseDTO<RCustomerOrdResponse>();
            rdors.setPageSize(1);
        }
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        model.addAttribute("status", status);
        //返回时间
        Map<String,Timestamp> dates = ParamsTool.params(vo);
        model.addAllAttributes(dates);
        model.addAttribute("resp",rdors);
        return "/order/cancel-list";
    }
}
