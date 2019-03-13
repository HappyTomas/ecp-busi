package com.zengshi.ecp.busi.order.point.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.order.vo.ROrdAddressReqVO;
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdAddressRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdAddressResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrderIdRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdDetailsRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdReceiptRSV;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

@Controller
@RequestMapping(value = "/ord/point")
public class PointOrdController extends EcpBaseController {

    private static final String MODULE = PointOrdController.class.getName();

    @Resource
    private IOrdMainRSV ordMainRSV;

    @Resource
    private IOrdReceiptRSV ordReceiptRSV;

    @Resource
    private IOrdDetailsRSV ordDetailsRSV; 
    
    // 订单详情
    /**
     * 
     * @param model
     * @param orderid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/detail")
    public String otherDomainQueryOrderDetails(Model model, @RequestParam("orderId") String orderid) {

        ROrderDetailsRequest rdto = new ROrderDetailsRequest();
        if (StringUtil.isBlank(orderid)) {
            throw new BusinessException("order.orderid.null.error");
        }
        rdto.setOrderId(orderid);
        rdto.setOper("01");
        if(!ordMainRSV.queryChkStatus(rdto).isStatus()){

            return "redirect:/homepage";
        }
        ROrderDetailsResponse resp = new ROrderDetailsResponse();
        resp = ordDetailsRSV.queryOrderDetails(rdto);

        // 订单id
        model.addAttribute("orderId", orderid);
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
        // 订单增值税发票相关字段
        model.addAttribute("sOrderDetailsTax", resp.getsOrderDetailsTax());
      //物流信息相关字段
        model.addAttribute("ordExpressDetailsResps",resp.getOrdExpressDetailsResps());
        // 进度条相关
        Map<String, Integer> status = ParamsTool.getStatusMap();
        List<String> statuslist = ParamsTool.getStatusList();
        model.addAttribute("status", status);
        model.addAttribute("statuslist", statuslist);

        return "/order/point/detail/otherDomain/order-detail";
    }

    // 订单详情
    /**
     * 
     * @param model
     * @param orderid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/detail/{orderId}")
    public String queryOrderDetails(Model model, @PathVariable("orderId") String orderid) {

        ROrderDetailsRequest rdto = new ROrderDetailsRequest();
        if (StringUtil.isBlank(orderid)) {
            throw new BusinessException("order.orderid.null.error");
        }
        rdto.setOrderId(orderid);
        rdto.setOper("01");
        if(!ordMainRSV.queryChkStatus(rdto).isStatus()){

            return "redirect:/homepage";
        }
        ROrderDetailsResponse resp = new ROrderDetailsResponse();
        resp = ordDetailsRSV.queryOrderDetails(rdto);

        // 订单id
        model.addAttribute("orderId", orderid);
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
        //物流信息相关字段
        model.addAttribute("ordExpressDetailsResps",resp.getOrdExpressDetailsResps());
        // 进度条相关
        Map<String, Integer> status = ParamsTool.getStatusMap();
        List<String> statuslist = ParamsTool.getStatusList();
        model.addAttribute("status", status);
        model.addAttribute("statuslist", statuslist);

        return "/order/point/detail/order-detail";
    }
    
    @RequestMapping(value="/myorder/cancel")
    @ResponseBody
    public EcpBaseResponseVO cancelOrd(@RequestParam(value="orderId")String orderId,@RequestParam(value="oper")String oper){
        ROrdCartsChkResponse resp = new ROrdCartsChkResponse();
        EcpBaseResponseVO ecpResp = new EcpBaseResponseVO(); 
        try {
            ROrderDetailsRequest rdor = new ROrderDetailsRequest();
            rdor.setOrderId(orderId);
            rdor.setOper(oper);
            //取消订单来源判断
            rdor.setDelFrom(OrdConstants.DealFrom.FROM_BUYER);
            resp = ordMainRSV.queryOrdOperChk(rdor); 
            ecpResp.setResultFlag(resp.isStatus()+"");
            ecpResp.setResultMsg(resp.getMsg());
            if(resp.isStatus()==true){
                ROrderDetailsRequest rdetail = new ROrderDetailsRequest();
                if(StringUtil.isBlank(orderId)){
                    ecpResp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
                    ecpResp.setResultMsg("orderId不能为空");
                    throw new BusinessException("orderId不能为空");
                }
                rdetail.setOrderId(orderId);
                rdetail.setStaffId(rdetail.getStaff().getId());
                ordMainRSV.removeOrd(rdetail);
            }
        } catch (Exception e) {
            ecpResp.setResultFlag("false");
            ecpResp.setResultMsg(e.getMessage());
        }  
        return ecpResp;
    }
    
    /**
     * 
     * buyerAddrUpdate:(收货地址编辑弹出窗口). <br/> 
     * 
     * @author panjs 
     * @param model
     * @param id
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/buyeraddrupdate")
    public String buyerAddrUpdate(Model model,@RequestParam(value="orderId")String orderId){
        //根据订单编号，查找地址信息 
        ROrderIdRequest rOrderIdRequest = new ROrderIdRequest(); 
        rOrderIdRequest.setOrderId(orderId);
        ROrdAddressResponse addr = ordMainRSV.queryOrderAddress(rOrderIdRequest);
        model.addAttribute("orderId", orderId);
        model.addAttribute("buyerAddr", addr);
        return "/order/point/openaddress/buyer-addressupdate";
    }
    
    /**
     * 
     * saveAddr:(收货地址保存). <br/> 
     * 
     * @author panjs 
     * @param custaddr
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/saveaddr")
    @ResponseBody
    public Map<String,Object> saveAddr(@ModelAttribute ROrdAddressReqVO addrReq){
        Map<String, Object> cust = new HashMap<String, Object>();
        LogUtil.info(MODULE, "============== 保存店铺收货地址    开始  =============");
        
        ROrdAddressRequest addrDTO = new ROrdAddressRequest();
        ObjectCopyUtil.copyObjValue(addrReq, addrDTO, null, false);  
        try{
            ordMainRSV.updateOrderAddress(addrDTO);
            cust.put("resultFlag","ok");
        }catch(Exception e){
            LogUtil.error(MODULE, "保存地址出错");
            cust.put("resultFlag","expt");
            cust.put("msg",e.getMessage());
        } 
        return cust;
    }
}
