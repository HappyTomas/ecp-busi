package com.zengshi.ecp.busi.unpf.order.controller;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.order.util.ParamsTool;
import com.zengshi.ecp.busi.unpf.order.vo.UnpfOrdMainReqVO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdMainReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdMainResp;
import com.zengshi.ecp.unpf.dubbo.interfaces.order.IUnpfOrdMainRSV;
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
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: cbl
 * @date: 2016/11/8.
 */
@Controller
@RequestMapping(value = "/unpforder")
public class UnpfOrderController extends EcpBaseController {

    @Resource
    private IUnpfOrdMainRSV unpfOrdMainRSV;

    private static String MODULE = UnpfOrderController.class.getName();

    @RequestMapping(value = "init")
    public String init(Model model) {
        model.addAttribute("begDate", DateUtil.getOffsetDaysDate(DateUtil.getTheDayFirstSecond(DateUtil.getSysDate()), -90));
        model.addAttribute("endDate", DateUtil.getDate());
        return "/unpf/order/order-grid";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Model queryOrdMainList(Model model, UnpfOrdMainReqVO unpfOrdMainReqVO) throws Exception {
        LogUtil.info(MODULE, JSON.toJSONString(unpfOrdMainReqVO).toString());
        RUnpfOrdMainReq rUnpfOrdMainReq = new RUnpfOrdMainReq();
        rUnpfOrdMainReq = unpfOrdMainReqVO.toBaseInfo(RUnpfOrdMainReq.class);
        ObjectCopyUtil.copyObjValue(unpfOrdMainReqVO, rUnpfOrdMainReq, "", false);
        rUnpfOrdMainReq.setEndDate(new Timestamp(DateUtils.addDays(rUnpfOrdMainReq.getEndDate(), 1).getTime()));
        PageResponseDTO<RUnpfOrdMainResp> resp = this.unpfOrdMainRSV.queryUnpfOrdMainByPage(rUnpfOrdMainReq);
        EcpBasePageRespVO<Map> respVO = new EcpBasePageRespVO<Map>();
        if (resp != null) {
            respVO = EcpBasePageRespVO.buildByPageResponseDTO(resp);
        }
        return super.addPageToModel(model, respVO);
    }

    @RequestMapping(value = "/detail")
    public String queryOrderDetails(Model model, @RequestParam("orderId") String orderid) {

        RUnpfOrdMainReq rUnpfOrdMainReq = new RUnpfOrdMainReq();
        if (StringUtil.isBlank(orderid)) {
            throw new BusinessException("order.orderid.null.error");
        }
        rUnpfOrdMainReq.setId(orderid);

        RUnpfOrdMainResp resp = this.unpfOrdMainRSV.queryUnpfOrdMain(rUnpfOrdMainReq);

        // 订单id
        model.addAttribute("orderId", orderid);
        // 主订单相关字段
        model.addAttribute("ordMain", resp);
//        // 子订单相关字段
//        model.addAttribute("sOrderDetailsSubs", resp.getsOrderDetailsSubs());
//        // 订单优惠相关字段
//        model.addAttribute("sOrderDetailsDiscount", resp.getsOrderDetailsDiscount());
//        // 订单跟踪相关字段
//        model.addAttribute("sOrderDetailsTracks", resp.getsOrderDetailsTracks());
//        // 订单收货地址相关字段
//        model.addAttribute("sOrderDetailsAddr", resp.getsOrderDetailsAddr());
//        // 订单普通发票相关字段
//        model.addAttribute("sOrderDetailsComm", resp.getsOrderDetailsComm());
//        // 订单增值税发票相关字段
//        model.addAttribute("sOrderDetailsTax", resp.getsOrderDetailsTax());
//        //物流信息相关字段
//        model.addAttribute("sOrderDetailsDeliverys", resp.getsOrderDetailsDeliverys());

        Map<String, Integer> status = new HashMap<String, Integer>();
        status.put("01", 0);
        status.put("02", 1);
        status.put("04", 1);
        status.put("05", 1);
        status.put("06", 2);
        status.put("80", 3);
        List<String> statuslist = ParamsTool.getStatusList();
        model.addAttribute("status", status);
        model.addAttribute("statuslist", statuslist);

        return "/unpf/order/order-detail";
    }
}
