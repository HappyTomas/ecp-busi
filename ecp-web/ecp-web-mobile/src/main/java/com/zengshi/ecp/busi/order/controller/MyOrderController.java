package com.zengshi.ecp.busi.order.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.velocity.AiToolUtil;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.order.vo.RQueryOrderReqVO;
import com.zengshi.ecp.busi.order.vo.myorder.ROrdMainRespVO;
import com.zengshi.ecp.busi.order.vo.myorder.ROrdSubRespVO;
import com.zengshi.ecp.busi.order.vo.myorder.ROrderRespVO;
import com.zengshi.ecp.busi.order.vo.myorder.ROrdersRespVO;
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.order.dubbo.dto.RCustomerOrdResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdReceiptRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsSub;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdReceiptRSV;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SiteLocaleUtil;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Created by wang on 16/8/1.
 */
@Controller
@RequestMapping(value="/order/my")
public class MyOrderController extends EcpBaseController{

    private final String MODULE = MyOrderController.class.getName();
    @Resource
    private IOrdReceiptRSV ordReceiptRSV;

    @Resource
    private IOrdMainRSV ordMainRSV;

    private String URL = "/order/my";

    @RequestMapping(value = "/cancel")
    public String cancel(Model model,RQueryOrderReqVO vo) throws Exception{
        //后场服务所需要的DTO；
        LogUtil.debug(MODULE, vo.toString());

        String status = OrdConstants.CustomerRequestStatus.REQUEST_STATUS_CANCLE;
        setModelData(model, status, vo);

        return URL+"/scroll/body";
    }

    private void setModelData(Model model, String status, RQueryOrderReqVO vo) {

        PageResponseDTO<RCustomerOrdResponse> rdors = getPageResponseDTO(vo, status);

        if(CollectionUtils.isEmpty(rdors.getResult())){
            model.addAttribute("empty",true);
        }else{
            model.addAttribute("empty",false);
        }

        model.addAttribute("resp", rdors);

        model.addAttribute("status", status);
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

    @RequestMapping(value = "/recepted")
    public String recepted(Model model,RQueryOrderReqVO vo) throws Exception{
        //后场服务所需要的DTO；
        LogUtil.debug(MODULE, vo.toString());

        String status = OrdConstants.CustomerRequestStatus.REQUEST_STATUS_RECEPTED;
        setModelData(model, status, vo);

        return URL+"/scroll/body";
    }

    @RequestMapping(value = "recept")
    public String recept(Model model,RQueryOrderReqVO vo) throws Exception{
        //后场服务所需要的DTO；
        LogUtil.debug(MODULE, vo.toString());

        String status = OrdConstants.CustomerRequestStatus.REQUEST_STATUS_RECEPT;
        setModelData(model, status, vo);

        return URL+"/scroll/body";
    }


    //买家确认收货
    @RequestMapping(value="/recept/confirmord")
    @ResponseBody
    public EcpBaseResponseVO confirmOrd(@RequestParam(value="orderId")String orderId,@RequestParam(value="oper")String oper){
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

    /**
     *
     * cancelOrd:(取消订单). <br/>
     *
     * @author panjs
     * @param orderId
     * @param oper
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value="/cancel/confirmord")
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


    @RequestMapping(value = "/send")
    public String send(Model model,RQueryOrderReqVO vo) throws Exception{

        LogUtil.debug(MODULE, vo.toString());

        String status = OrdConstants.CustomerRequestStatus.REQUEST_STATUS_SEND;
        setModelData(model, status, vo);

        return URL+"/scroll/body";
    }

    @RequestMapping(value = "pay")
    public String pay(Model model,RQueryOrderReqVO vo) throws Exception{
        //后场服务所需要的DTO；
        LogUtil.debug(MODULE, vo.toString());

        String status = OrdConstants.CustomerRequestStatus.REQUEST_STATUS_PAY;
        setPayModelData(model, status, vo);
        // 获取合并支付的开关
        BaseSysCfgRespDTO payMergeSysCfg = SysCfgUtil.fetchSysCfg(OrdConstants.OrdPayRel.SWITCH_PAY_MERGE);
        model.addAttribute("switchPayMerge", payMergeSysCfg.getParaValue());
        return URL+"/scroll/body";
    }

    @RequestMapping()
    public String all(Model model,RQueryOrderReqVO vo) throws Exception{
        //后场服务所需要的DTO；
        LogUtil.debug(MODULE, vo.toString());

        String status = OrdConstants.CustomerRequestStatus.REQUEST_STATUS_ALL;
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        setPayModelData(model, status, vo);

        return URL+"/scroll/body";
    }


    @RequestMapping(value = "/scrollData")
    @ResponseBody
    public ROrdersRespVO scrollData(Model model,RQueryOrderReqVO vo,String status){
        PageResponseDTO<RCustomerOrdResponse> rdors = getPageResponseDTO(vo, status);

        ROrdersRespVO respVO = new ROrdersRespVO();
        respVO.setTotal(rdors.getCount());
        respVO.setPageCount(rdors.getPageCount());
        respVO.setPageNo(rdors.getPageNo());
        respVO.setPageSize(rdors.getPageSize());

        List<ROrderRespVO> orderRespVOs = new ArrayList<>();

        if(CollectionUtils.isEmpty(rdors.getResult())) {
            respVO.setDatas(orderRespVOs);
            return respVO;
        }
        //封装返回参数成前端所需
        if(rdors.getResult() != null && rdors.getResult().size() >= 1){
            for(RCustomerOrdResponse customerData:rdors.getResult()){
                ROrderRespVO orderRespVO = new ROrderRespVO();
                ROrdMainRespVO ordMain = new ROrdMainRespVO();
                ObjectCopyUtil.copyObjValue(customerData.getsCustomerOrdMain(), ordMain, "", false);
                ordMain.setStatusName(ParamsTool.translate("ORD_ORDER_STATUS", ordMain.getStatus()));
                ordMain.setShowMoney(ParamsTool.showMoneyByTwoDecimal(ordMain.getRealMoney().toString()));
    
                List<ROrdSubRespVO> ordsubs = new ArrayList<>();
                if(customerData.getsOrderDetailsSubs() != null && customerData.getsOrderDetailsSubs().size() >= 1){
                    for(SOrderDetailsSub detailsSub:customerData.getsOrderDetailsSubs()){
                        ROrdSubRespVO ordsub = new ROrdSubRespVO();
                        ObjectCopyUtil.copyObjValue(detailsSub,ordsub,"",false);
                        ordsubs.add(ordsub);
                    }
                }
                orderRespVO.setOrdMain(ordMain);
                orderRespVO.setOrdSubs(ordsubs);
                orderRespVOs.add(orderRespVO);
            }
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
        rdor.setSiteId(SiteLocaleUtil.getSite());
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
        }else{
            if(rdors.getResult() != null && rdors.getResult().size() >= 1){
                for(RCustomerOrdResponse respon : rdors.getResult()){
                    if(respon.getsOrderDetailsSubs() != null && respon.getsOrderDetailsSubs().size() >= 1){
                        for(SOrderDetailsSub sub : respon.getsOrderDetailsSubs()){
                            sub.setPicUrl(new AiToolUtil().genImageUrl(sub.getPicId(), "85x85!"));
                        }
                    }
                }
            }
        }
        return rdors;
    }
}
