package com.zengshi.ecp.busi.order.controller;

import java.sql.Timestamp;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.order.vo.RQueryOrderReqVO;
import com.zengshi.ecp.order.dubbo.dto.RCustomerOrdResponse;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

@Controller
@RequestMapping(value="/order/recepted")
public class ReceptedOrdController extends EcpBaseController {

    private static final String MODULE = ReceptedOrdController.class.getName();
    
    @Resource 
    private IOrdMainRSV ordMainRSV;
    
    @RequestMapping()
    public String init(Model model,RQueryOrderReqVO vo) throws Exception{
        //后场服务所需要的DTO；
        LogUtil.debug(MODULE, vo.toString());
        //后场服务所需要的DTO；
        RQueryOrderRequest rdor = vo.toBaseInfo(RQueryOrderRequest.class);

        String status = OrdConstants.CustomerRequestStatus.REQUEST_STATUS_RECEPTED;
        //rdor.setStaffId(ParamsTool.getStaffId());
        rdor.setStaffId(rdor.getStaff().getId());
        rdor.setSiteId(1l);
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
    
}

