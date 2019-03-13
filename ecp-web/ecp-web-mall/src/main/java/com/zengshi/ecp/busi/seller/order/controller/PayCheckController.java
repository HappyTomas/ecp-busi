package com.zengshi.ecp.busi.seller.order.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.order.OrdConstant;
import com.zengshi.ecp.busi.order.pay.vo.ROfflineReviewReqVO;
import com.zengshi.ecp.busi.seller.order.vo.ROfflineQueryReqVO;

import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.order.dubbo.dto.ROfflineQueryRequest;
import com.zengshi.ecp.order.dubbo.dto.ROfflineQueryResponse;
import com.zengshi.ecp.order.dubbo.dto.ROfflineReviewRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdOfflineRSV;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

@RequestMapping(value="/seller/order/pay")
@Controller
public class PayCheckController  extends EcpBaseController {
    private static final String MODULE = PayCheckController.class.getName();
    @Resource
    private IOrdOfflineRSV ordOfflineRSV;
    @Resource
    private IOrdMainRSV ordMainRSV; 
    @Resource
    private ICustInfoRSV custInfoRSV;
    
    private static final String SELLER_ORDER_PAY_VM_PATH = "/seller/order";
    @RequestMapping(value="/check")
    public String check(Model model)
    {
        return SELLER_ORDER_PAY_VM_PATH + "/payoffline-check-main";
    }
    
    //线下支付列表
    @RequestMapping(value="/unchecklist")
    public String offlineList(Model model,ROfflineQueryReqVO vo) throws Exception{
        LogUtil.info(MODULE, "线下支付列表");

        ROfflineQueryRequest offquerydto = new ROfflineQueryRequest();
        
        offquerydto = vo.toBaseInfo(ROfflineQueryRequest.class);
        
//        offquerydto.setShopId(ParamsTool.getShopId());
        ObjectCopyUtil.copyObjValue(vo, offquerydto, "", false);
        //offquerydto.setStaff(ParamsTool.getStaff());
        
        // PageResponseDTO<DemoCfgRespDTO> t = this.initParam();
        PageResponseDTO<ROfflineQueryResponse> t = ordOfflineRSV.queryOfflineOrder(offquerydto);

        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
        model.addAttribute("unchecklistPage", respVO);
        
        return SELLER_ORDER_PAY_VM_PATH + "/tab/payoffline-uncheck-list";
    }

    //线下支付列表
    @RequestMapping(value="/checkedlist")
    public String checkedList(Model model,ROfflineQueryReqVO vo) throws Exception{
        LogUtil.info(MODULE, "线下支付列表");
        
        ROfflineQueryRequest offquerydto = new ROfflineQueryRequest();

        offquerydto = vo.toBaseInfo(ROfflineQueryRequest.class);

        ObjectCopyUtil.copyObjValue(vo, offquerydto, "", false);

        PageResponseDTO<ROfflineQueryResponse> t = ordOfflineRSV.queryCheckedOrder(offquerydto);

        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);

        model.addAttribute("checkedlistPage", respVO);
        
        return SELLER_ORDER_PAY_VM_PATH + "/tab/payoffline-checked-list";
    }
    @RequestMapping(value="/opencheck")
    public String opencheck(Model model, ROfflineQueryReqVO vo) throws Exception{
        LogUtil.info(MODULE, "线下支付页面");

        ROfflineQueryRequest offlinedto = new ROfflineQueryRequest();
        //验证信息
        if(StringUtil.isEmpty(vo.getShopId())){
            throw new BusinessException("店铺ID为空");
        }
        
        if(StringUtil.isBlank(vo.getOrderId())){
            throw new BusinessException("订单号为空");
        }
        
        offlinedto.setShopId(vo.getShopId());
        offlinedto.setOrderId(vo.getOrderId());
        ROfflineQueryResponse offlineResp = ordOfflineRSV.queryOfflineReview(offlinedto);
        model.addAttribute("offlineResp", offlineResp);
        model.addAttribute("orderId", vo.getOrderId());
        model.addAttribute("shopId", vo.getShopId());


        return SELLER_ORDER_PAY_VM_PATH + "/open/payoffline-open-check";
    }
    //线下支付审核
    @RequestMapping(value="/offlinesave")
    @ResponseBody
    public EcpBaseResponseVO offlineSave(@Valid ROfflineReviewReqVO vo){
        
        LogUtil.info(MODULE, "============审核方法开始=============");
        ROfflineReviewRequest rdto = new ROfflineReviewRequest();
        EcpBaseResponseVO resp = new EcpBaseResponseVO();
        ROrderDetailsRequest rdor = new ROrderDetailsRequest();
        ROrdCartsChkResponse checkResp = new ROrdCartsChkResponse();

        try {
            rdor.setOper(OrdConstant.ChkOrdStatus.CHECK);
            rdor.setShopId(vo.getShopId());
            rdor.setOrderId(vo.getOrderId());
            rdor.setStaffId(vo.getStaffId());
            //取消订单来源判断
            rdor.setDelFrom(OrdConstants.DealFrom.FROM_SHOP);

            //验证审核订单
            checkResp = ordMainRSV.queryOrdOperChk(rdor);
            if(!checkResp.isStatus()) throw new BusinessException(checkResp.getMsg());

            ObjectCopyUtil.copyObjValue(vo, rdto, "", false);

            LogUtil.info(MODULE, "============拷贝属性============");
            //店铺Id
            ordOfflineRSV.OfflineReview(rdto);

            resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);

        }catch(BusinessException bus){
            LogUtil.error(MODULE, "============出错了============="+bus.getMessage());
            resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            resp.setResultMsg(bus.getMessage());
        }catch(Exception e){
            LogUtil.error(MODULE, "============出错了============="+e.getMessage());
            resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            resp.setResultMsg(e.getMessage());
        }
        LogUtil.info(MODULE, "============审核方法结束=============");

        return resp;
    }
}

