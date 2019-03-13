package com.zengshi.ecp.busi.order.bill.controller;
 
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.zengshi.ecp.order.dubbo.interfaces.IOrdBackGdsRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdDetailsRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV; 

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.order.bill.vo.ROrdInvoiceReqVO;
import com.zengshi.ecp.busi.order.bill.vo.RQueryOrderReqVO; 
import com.zengshi.ecp.busi.order.util.ParamsTool; 
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.order.dubbo.dto.ROfflineReviewRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdInvoiceRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdInvoiceResponse; 
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsResponse;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest; 
import com.zengshi.ecp.server.front.dto.PageResponseDTO;  
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil; 
import com.zengshi.paas.utils.StringUtil;

@Controller
@RequestMapping(value="/order/bill")
public class BillController extends EcpBaseController {

    private static final String MODULE = BillController.class.getName();
    
    @Resource(name = "ordMainRSV")
    private IOrdMainRSV ordMainRSV;
    
    @Resource(name = "ordDetailsRSV")
    private IOrdDetailsRSV ordDetailsRSV;
    
    @Resource(name = "ordBackGdsRSV")
    private IOrdBackGdsRSV ordBackGdsRSV;
    
    /**
     * 
     * init:(发票查询页面). <br/> 
     * 
     * @author panjs 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping()
    public String init(Model model) {
        model.addAllAttributes(ParamsTool.params());
        return "/order/bill/bill-grid";
    }
    
    /**
     * 
     * offlineList:(发票列表). <br/> 
     * 
     * @author panjs 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/billlist")
    public Model billList(Model model,RQueryOrderReqVO vo) throws Exception{
        LogUtil.info(MODULE, "发票列表"); 
        RQueryOrderRequest rQueryOrderRequest = new RQueryOrderRequest();
        rQueryOrderRequest = vo.toBaseInfo(RQueryOrderRequest.class);
        ObjectCopyUtil.copyObjValue(vo, rQueryOrderRequest, "", false);
        PageResponseDTO<ROrdInvoiceResponse> dto =ordMainRSV.queryOrderInvoiceInfo(rQueryOrderRequest);
        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(dto); 
        return super.addPageToModel(model, ParamsTool.ordDetailSiteUrl(respVO)); 
    }  
    
    /**
     * 
     * review:(发票查询页面). <br/> 
     * 
     * @author panjs 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/review")
    public String review(Model model,@RequestParam("orderId") String orderId) {
        model.addAttribute("orderId", orderId);
        return "/order/bill/review/bill-review";
    }
    
    /**
     * 
     * offlineSave:(发票状态保存). <br/>  
     * 
     * @author panjs 
     * @param vo
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/billsave")
    @ResponseBody
    public EcpBaseResponseVO billSave(@Valid ROrdInvoiceReqVO vo){
    	LogUtil.info(MODULE, "============发票状态方法开始=============");
        ROrdInvoiceRequest rQueryOrderRequest = new ROrdInvoiceRequest();
        EcpBaseResponseVO resp = new EcpBaseResponseVO();  
        try { 
            ObjectCopyUtil.copyObjValue(vo, rQueryOrderRequest, "", false);
            ordMainRSV.updateOrderInvoiceInfo(rQueryOrderRequest);   
            resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS); 
        }catch(Exception e){
        	LogUtil.error(MODULE, "============出错了============="+e.getMessage());
            resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            resp.setResultMsg(e.getMessage());
        }
        LogUtil.info(MODULE, "============发票状态方法结束=============");

        return resp;
    }
    
    /**
     * 
     * printDetail:(销售明细页面). <br/> 
     * 
     * @author panjs 
     * @param orderId
     * @param res
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/printDetail")
    public String printDetail(Model model, @RequestParam("orderId") String orderId){
        ROrderDetailsRequest rdto = new ROrderDetailsRequest();
        if (StringUtil.isBlank(orderId)) {
            throw new BusinessException("order.orderid.null.error");
        }
        rdto.setOrderId(orderId);
        ROrderDetailsResponse resp = new ROrderDetailsResponse();
        resp = ordDetailsRSV.queryOrderDetails(rdto);

        // 主订单相关字段
        model.addAttribute("sOrderDetailsMain", resp.getsOrderDetailsMain());
        // 子订单相关字段
        model.addAttribute("sOrderDetailsSubs", resp.getsOrderDetailsSubs());
        // 订单优惠相关字段
        model.addAttribute("sOrderDetailsDiscount", resp.getsOrderDetailsDiscount());
        // 订单跟踪相关字段
        model.addAttribute("sOrderDetailsTracks", resp.getsOrderDetailsTracks());
        // 订单收货地址相关字段
        model.addAttribute("sOrderDetailsAddr", resp.getsOrderDetailsAddr());
        // 订单普通发票相关字段
        model.addAttribute("sOrderDetailsComm", resp.getsOrderDetailsComm());
        // 订单增值税发票相关字段
        model.addAttribute("sOrderDetailsTax", resp.getsOrderDetailsTax());

        return "/order/bill/print/print-detail"; 
    }
    
    /**
     * checkReturn:(验证是否发起退款退货). <br/> 
     * @param model
     * @param orderid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/checkReturn")
    public Map<String,Object> checkReturn(Model model,@RequestParam("orderId") String orderId,@RequestParam("oper") String oper) {
    	Map<String, Object> map = new HashMap<String, Object>(); 
        try {
        	ROrderDetailsRequest dto = new ROrderDetailsRequest(); 
            dto.setOrderId(orderId); 
            dto.setOper(oper); 
            ROrdCartsChkResponse  resp = new ROrdCartsChkResponse();
            resp = ordMainRSV.queryChkStatus(dto);
            map.put("flag",resp.isStatus());
            map.put("msg",resp.getMsg());
        } catch (Exception e) {
        	LogUtil.error(MODULE, e.getMessage(),e);
            map.put("flag", false);
            map.put("msg",e.getMessage());
        }
        return map; 
    } 
    
}

