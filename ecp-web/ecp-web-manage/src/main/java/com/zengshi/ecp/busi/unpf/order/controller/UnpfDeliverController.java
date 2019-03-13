package com.zengshi.ecp.busi.unpf.order.controller;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.unpf.order.vo.UnpfOrdMainReqVO;
import com.zengshi.ecp.busi.unpf.order.vo.UnpfSendMainReqVO;
import com.zengshi.ecp.busi.unpf.order.vo.UnpfSendSubReqVO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.order.*;
import com.zengshi.ecp.unpf.dubbo.interfaces.order.IUnpfOrdDeliverRSV;
import com.zengshi.ecp.unpf.dubbo.interfaces.order.IUnpfOrdMainRSV;
import com.zengshi.ecp.unpf.dubbo.interfaces.order.IUnpfShopExpressRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: cbl
 * @date: 2016/11/8.
 */
@Controller
@RequestMapping(value = "/unpfdeliver")
public class UnpfDeliverController extends EcpBaseController {

    @Resource
    private IUnpfShopExpressRSV unpfShopExpressRSV;

    @Resource
    private IUnpfOrdMainRSV unpfOrdMainRSV;

    @Resource
    private IUnpfOrdDeliverRSV unpfOrdDeliverRSV;

    private static String MODULE = UnpfDeliverController.class.getName();

    @RequestMapping(value = "init")
    public String init(Model model) {
        model.addAttribute("begDate", DateUtil.getOffsetDaysDate(DateUtil.getTheDayFirstSecond(DateUtil.getSysDate()), -1));
        model.addAttribute("endDate", DateUtil.getDate());
        return "/unpf/order/deliver-grid";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Model queryOrdMainList(Model model, UnpfOrdMainReqVO unpfOrdMainReqVO) throws Exception {

        LogUtil.info(MODULE, JSON.toJSONString(unpfOrdMainReqVO));
        RUnpfOrdMainReq rUnpfOrdMainReq = new RUnpfOrdMainReq();
        rUnpfOrdMainReq = unpfOrdMainReqVO.toBaseInfo(RUnpfOrdMainReq.class);
        ObjectCopyUtil.copyObjValue(unpfOrdMainReqVO, rUnpfOrdMainReq, "", false);
        rUnpfOrdMainReq.setEndDate(new Timestamp(DateUtils.addDays(rUnpfOrdMainReq.getEndDate(), 1).getTime()));
        PageResponseDTO<RUnpfOrdMainResp> resp = this.unpfOrdDeliverRSV.queryUnpfOrdDeliverByPage(rUnpfOrdMainReq);
        EcpBasePageRespVO<Map> respVO = new EcpBasePageRespVO<Map>();
        if (resp != null) {
            respVO = EcpBasePageRespVO.buildByPageResponseDTO(resp);
        }
        return super.addPageToModel(model, respVO);
    }

    @RequestMapping(value = "/send")
    public String sendList(Model model,@RequestParam("orderId")String orderId){

        if (StringUtil.isBlank(orderId)) {
            throw new BusinessException("order.orderid.null.error");
        }

        RUnpfOrdMainReq rUnpfOrdMainReq = new RUnpfOrdMainReq();
        rUnpfOrdMainReq.setId(orderId);
        rUnpfOrdMainReq.setBuyerMsg("1");  //表示待发货查询-子订单状态过滤掉已发货的
        RUnpfOrdMainResp resp = this.unpfOrdMainRSV.queryUnpfOrdMain(rUnpfOrdMainReq);
        model.addAttribute("resp", resp);
        RUnpfShopExpressReq rUnpfShopExpressReq = new RUnpfShopExpressReq();
        rUnpfShopExpressReq.setShopId(resp.getShopId());
        rUnpfShopExpressReq.setPlatType(resp.getPlatType());
        List<RUnpfShopExpressResp> logistics = unpfShopExpressRSV.queryShopExpressResp(rUnpfShopExpressReq);
        LogUtil.info(MODULE, logistics + "");
        // 根据订单查店铺id
        model.addAttribute("logistics", logistics);

        return "/unpf/order/deliver-order";
    }

    /**
     * 确认发货
     * @param vo
     * @return
     */
    @RequestMapping(value="/confirmsend")
    @ResponseBody
    public EcpBaseResponseVO confirmSend(@Valid UnpfSendMainReqVO vo){
        EcpBaseResponseVO resp = new EcpBaseResponseVO();
        LogUtil.info(MODULE, JSON.toJSONString(vo));

        try{
            List<RUnpfSendSubReq> rUnpfSendSubReqs = new ArrayList<RUnpfSendSubReq>();
            RUnpfSendMainReq rdto = new RUnpfSendMainReq();
            ObjectCopyUtil.copyObjValue(vo, rdto, "", false);
            for(UnpfSendSubReqVO ordsubvo :  vo.getUnpfSendSubReqVOList()){
                RUnpfSendSubReq rsubdto = new RUnpfSendSubReq();
                ObjectCopyUtil.copyObjValue(ordsubvo, rsubdto, "", true);
                rUnpfSendSubReqs.add(rsubdto);
            }
            rdto.setUnpfSendSubReqList(rUnpfSendSubReqs);
            if("1".equals(vo.getIsSendAll()) ){
                rdto.setSendAll(true);
            } else {
                rdto.setSendAll(false);
            }
            unpfOrdDeliverRSV.orderDeliver(rdto);
            resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        }catch(Exception e){
            LogUtil.error(MODULE, e.getMessage());
            resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            resp.setResultMsg(e.getMessage()+",不允许发货");
        }
        return resp;
    }
}
