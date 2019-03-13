package com.zengshi.ecp.busi.order.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.order.vo.RQueryOrderReqVO;
import com.zengshi.ecp.busi.order.vo.myorder.ROrdMainRespVO;
import com.zengshi.ecp.busi.order.vo.myorder.ROrdSubRespVO;
import com.zengshi.ecp.busi.order.vo.myorder.ROrderRespVO;
import com.zengshi.ecp.busi.order.vo.myorder.ROrdersRespVO;
import com.zengshi.ecp.order.dubbo.dto.RCustomerOrdResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdExpressDetailsResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsResponse;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsSub;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdDetailsRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrderExpressRSV;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Created by wang on 16/8/5.
 */
@Controller
@RequestMapping(value="/point/order")
public class PointOrdController extends EcpBaseController{

    private static final String MODULE = PointOrdController.class.getName();

    @Resource
    private IOrdMainRSV ordMainRSV;

    @Resource
    private IOrdDetailsRSV ordDetailsRSV;
    
    @Resource
    private IOrderExpressRSV orderExpressRSV;

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
        ROrderDetailsResponse resp = new ROrderDetailsResponse();

        if(!ordMainRSV.queryChkStatus(rdto).isStatus()){

            return "redirect:/homepage";
        }
        resp = ordDetailsRSV.queryOrderDetails(rdto);

        // 订单id
        model.addAttribute("orderId", orderid);
        // 主订单相关字段
        model.addAttribute("ordMain", resp.getsOrderDetailsMain());
        // 子订单相关字段
        model.addAttribute("ordSubs", resp.getsOrderDetailsSubs());
        // 订单优惠相关字段
        model.addAttribute("discount", resp.getsOrderDetailsDiscount());
        // 订单跟踪相关字段
        model.addAttribute("track", resp.getsOrderDetailsTracks());
        // 订单收货地址相关字段
        model.addAttribute("addr", resp.getsOrderDetailsAddr());
        // 订单普通发票相关字段
        model.addAttribute("invoiceComm", resp.getsOrderDetailsComm());
        // 订单增值税发票相关字段
        model.addAttribute("invoiceTax", resp.getsOrderDetailsTax());
        //物流信息相关字段
        model.addAttribute("deliverys", resp.getsOrderDetailsDeliverys());
        //赠品信息相关
        model.addAttribute("gift", resp.getsOrderDetailsGifts());

        if(OrdConstants.CustomerRequestStatus.REQUEST_STATUS_PAY.equals(resp.getsOrderDetailsMain().getStatus())){
            String b = BaseParamUtil.fetchParamValue("ORD_PAY_LIMIT","0");
            int limitHour = Integer.valueOf(b);
            long hour = limitHour * 3600000 -(DateUtil.getSysDate().getTime() - resp.getsOrderDetailsMain().getOrderTime().getTime());
            long hh = hour/3600000;  
            long minutes = hour/60000-hh*60;  
            model.addAttribute("hour",hh);
            model.addAttribute("minutes",minutes);
        }

        return "/order/detail/order-detail";
    }

    @RequestMapping(value = "/record")
    public String all(Model model,RQueryOrderReqVO vo) throws Exception{
        //后场服务所需要的DTO；
        LogUtil.debug(MODULE, vo.toString());

        String status = OrdConstants.CustomerRequestStatus.REQUEST_STATUS_RECEPTED;
        vo.setSiteId(2l);
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        setPayModelData(model, status, vo);

        return "/order/record/scroll/body";
    }
    private void setPayModelData(Model model, String status, RQueryOrderReqVO vo) {

        PageResponseDTO<RCustomerOrdResponse> rdors = getPageResponseDTO(vo, status);

        model.addAttribute("status", status);

        model.addAttribute("resp",rdors);

        if(CollectionUtils.isEmpty(rdors.getResult())){
            model.addAttribute("empty",true);
        }else{
            model.addAttribute("empty",false);
        }

        // 获取合并支付的开关
        BaseSysCfgRespDTO payMergeSysCfg = SysCfgUtil.fetchSysCfg(OrdConstants.OrdPayRel.SWITCH_PAY_MERGE);
        model.addAttribute("switchPayMerge", payMergeSysCfg.getParaValue());
    }

    @RequestMapping(value = "/scrollData")
    @ResponseBody
    public ROrdersRespVO scrollData(Model model, RQueryOrderReqVO vo, String status){
        PageResponseDTO<RCustomerOrdResponse> rdors = getPageResponseDTO(vo, status);

        ROrdersRespVO respVO = new ROrdersRespVO();
        respVO.setTotal(rdors.getCount());
        respVO.setPageCount(rdors.getPageCount());
        respVO.setPageNo(rdors.getPageNo());
        respVO.setPageSize(rdors.getPageSize());

        List<ROrderRespVO> orderRespVOs = new ArrayList<>();

        //封装返回参数成前端所需
        for(RCustomerOrdResponse customerData:rdors.getResult()){
            ROrderRespVO orderRespVO = new ROrderRespVO();
            ROrdMainRespVO ordMain = new ROrdMainRespVO();
            ObjectCopyUtil.copyObjValue(customerData.getsCustomerOrdMain(), ordMain, "", false);
            ordMain.setStatusName(ParamsTool.translate("ORD_ORDER_STATUS", ordMain.getStatus()));
            ordMain.setShowMoney(ParamsTool.showMoneyByTwoDecimal(ordMain.getRealMoney().toString()));

            //积分
            Long scores = 0l;
            List<ROrdSubRespVO> ordsubs = new ArrayList<>();
            for(SOrderDetailsSub detailsSub:customerData.getsOrderDetailsSubs()){
                ROrdSubRespVO ordsub = new ROrdSubRespVO();
                ObjectCopyUtil.copyObjValue(detailsSub,ordsub,"",false);
                ordsubs.add(ordsub);

                scores+=detailsSub.getScore();
            }
            ordMain.setScores(scores);

            orderRespVO.setOrdMain(ordMain);
            orderRespVO.setOrdSubs(ordsubs);
            orderRespVOs.add(orderRespVO);
        }

        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        respVO.setDatas(orderRespVOs);

        return respVO;
    }

    private PageResponseDTO<RCustomerOrdResponse> getPageResponseDTO(RQueryOrderReqVO vo, String status) {
        //后场服务所需要的DTO；
        RQueryOrderRequest rdor = vo.toBaseInfo(RQueryOrderRequest.class);
        ObjectCopyUtil.copyObjValue(vo, rdor, "", false);
        //构造参数
        int pageSize = 10;

        rdor.setStaffId(rdor.getStaff().getId());
        rdor.setSiteId(2l);
        rdor.setSysType("00");
        rdor.setStatus(status); //
        rdor.setPageNo(vo.getPage()<=0?1:vo.getPage());


        //匹配后场，加一天
        if(vo.getEndDate()!=null){
            rdor.setEndDate(new Timestamp(DateUtils.addDays(vo.getEndDate(), 1).getTime()));
        }
        PageResponseDTO<RCustomerOrdResponse> rdors = this.ordMainRSV.queryOrderByStaffId(rdor);
        if(rdors==null){
            rdors = new PageResponseDTO<RCustomerOrdResponse>();
            rdors.setPageSize(pageSize);
        }
        return rdors;
    }
    
    /**
     * 查询订单物流信息
     * @param orderId
     * @return
     */
    @RequestMapping(value="/queryExpress/{orderId}")
    public String queryOrderExpress(Model model,@PathVariable String orderId){
    	 List<ROrdExpressDetailsResp> list  = orderExpressRSV.queryOrderExpressDetailList(orderId);        
    	 model.addAttribute("ordExpressDetailsResp", list);
    	 return "/order/detail/order-express";
    }

}
