/** 
 * Project Name:ecp-web-demo 
 * File Name:DemoController.java 
 * Package Name:com.zengshi.ecp.busi.demo.controller 
 * Date:2015-8-5下午2:51:38 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.staff.buyer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.util.WebContextUtil;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupMeReqDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupDetailRSV;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCollectRSV;
import com.zengshi.ecp.order.dubbo.dto.RCustomerOrdResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdCountResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdReceiptRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdReceiptRSV;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.CustomerRequestStatus;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctSumResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAcctManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IScoreInfoRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 会员中心<br>
 * Date:2015-9-16上午11:01:35  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value="/buyercenter")
public class BuyerCenterController extends EcpBaseController {
    
    @Resource
    private IScoreInfoRSV scoreInfoRSV;
    
    @Resource
    private IAcctManageRSV acctManageRSV;
    
    @Resource
    private IOrdMainRSV orderMainRSV;
    
    @Resource
    private ICustInfoRSV custInfoRSV;
    
    @Resource
    private IGdsCollectRSV gdsCollectRSV;
    
    @Resource
    private IOrdReceiptRSV ordReceiptRSV;
    
    @Resource
    private ICoupDetailRSV coupDetailRSV;
    
    /**
     * 
     * buyerCenter:(跳转到买家中心首页). <br/> 
     * 
     * @author huangxl 
     * @return 
     * @since JDK 1.7
     */
    @RequestMapping(value="/index")
    public String init(Model model){
        //获取当前登录用户
        CustInfoReqDTO custReq = new CustInfoReqDTO();
        custReq.setId(custReq.getStaff().getId());
        //获取用户更多信息
        CustInfoResDTO custRes = custInfoRSV.getCustInfoById(custReq);
        model.addAttribute("nickname", custRes.getStaffCode());//会员名
        /*安全级别低：手机与邮箱都未绑；安全级别中：手机与邮箱绑定了其中之一；安全级别高：手机与邮箱都绑定了*/
        if (StringUtil.isNotBlank(custRes.getSerialNumber()) && StringUtil.isNotBlank(custRes.getEmail())) {
            model.addAttribute("securityLevel", "高");//安全级别
        } else if (StringUtil.isBlank(custRes.getSerialNumber()) && StringUtil.isBlank(custRes.getEmail())) {
            model.addAttribute("securityLevel", "低");//安全级别
        } else {
            model.addAttribute("securityLevel", "中");//安全级别
        }
        model.addAttribute("custLevelName", custRes.getCustLevelName());//会员等级名称
        model.addAttribute("custLevelCode", custRes.getCustLevelCode());//会员等级编码
        model.addAttribute("custPic",custRes.getCustPic());//会员头像
        //手机是否绑定，默认非绑定
        model.addAttribute("isPhoneBind", "false");
        if (StringUtil.isNotBlank(custRes.getSerialNumber())) {
            model.addAttribute("isPhoneBind", "true");
        } 
        //邮箱是否绑定
        model.addAttribute("isEmailBind", "false");
        if (StringUtil.isNotBlank(custRes.getEmail())) {
            model.addAttribute("isEmailBind", "true");
        }
        //默认图片地址
        String defaultImageUrl = ImageUtil.getImageUrl("_48x48!");
        model.addAttribute("defaultImageUrl", defaultImageUrl);
        return "/staff/buyer/buyer-center";
    }
    
    /**
     * 
     * buyerAcctInfo:(买家中心-》我的资产 相关数据获取). <br/> 
     * 
     * @author huangxl 
     * @param demoCfg
     * @return 
     * @since JDK 1.7
     */
    @RequestMapping(value="/acctinfo")
    @ResponseBody
    public Map<String,Object> acctInfo(){
        Map<String,Object> result = new HashMap<String,Object>();
        
        //获取用户资金账户信息
        AcctInfoReqDTO req = new AcctInfoReqDTO();
        req.setStaffId(req.getStaff().getId());
        req.setAdaptType(StaffConstants.Acct.ADAPT_TYPE_SHOP);//适用类型：店铺资金
        AcctSumResDTO res = acctManageRSV.queryAcctSumRelatedShops(req);
        
        //获取用户积分信息
        ScoreInfoResDTO dto = scoreInfoRSV.findScoreInfoByStaffId(req.getStaff().getId());
        
        //获取用户剩余的优惠券
        CoupMeReqDTO coupMeReq = new CoupMeReqDTO();
        coupMeReq.setStaffId(req.getStaff().getId());
        long coupCount = coupDetailRSV.queryCoupValidCount(coupMeReq);
        //获取优惠券信息
        result.put("coup", coupCount);//优惠券
        result.put("score", dto.getScoreBalance() == null ? 0L : dto.getScoreBalance());//积分
        result.put("acct", res.getBalance() == null ? 0L : res.getBalance());//资金账户可用金额
        
        //获取待付款、待收货、已收货订单数量
        RQueryOrderRequest orderReq = new RQueryOrderRequest();
        orderReq.setStaffId(orderReq.getStaff().getId());
        orderReq.setCurrentSiteId(orderReq.getCurrentSiteId());
        orderReq.setSiteId(orderReq.getCurrentSiteId());
        ROrdCountResponse ordCountRes = orderMainRSV.queryOrderCountByStaff(orderReq);
        if (ordCountRes != null) {
            result.put("orderSend", ordCountRes.getRequestStatusSend());//待发货数量
            result.put("orderPay", ordCountRes.getRequestStatusPay());//待付款订单数量
            result.put("orderRecept", ordCountRes.getRequestStatusRecept());//待收货订单数量
            result.put("orderRecepted", ordCountRes.getRequestStatusReceptde());//已收货订单数量
        }
        return result;
    }
    
    /**
     * 
     * orderList:(获取订单列表信息). <br/> 
     * 
     * @author huangxl 
     * @param staffId
     * @param orderStatus
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    @RequestMapping(value="/orderlist")
    @ResponseBody
    public Map<String,Object> orderList(@RequestParam("orderStatus")String orderStatus) throws BusinessException{
        Map<String,Object> result = new HashMap<String,Object>();
        /*获取用户的订单信息*/
        RQueryOrderRequest orderReq = new RQueryOrderRequest();
        orderReq.setStaffId(orderReq.getStaff().getId());
        orderReq.setSiteId(orderReq.getCurrentSiteId());
        orderReq.setCurrentSiteId(orderReq.getCurrentSiteId());
        //查询全部订单
        if ("all".equals(orderStatus)) {
            orderReq.setStatus(CustomerRequestStatus.REQUEST_STATUS_ALL);
        //查询待付款订单
        } else if ("pay".equals(orderStatus)) {
            orderReq.setStatus(CustomerRequestStatus.REQUEST_STATUS_PAY);
        //查询待收货订单
        } else if ("recept".equals(orderStatus)) {
            orderReq.setStatus(CustomerRequestStatus.REQUEST_STATUS_RECEPT);
        //查询已收货订单
        } else if ("recepted".equals(orderStatus)) {
            orderReq.setStatus(CustomerRequestStatus.REQUEST_STATUS_RECEPTED);
        //查询待发货订单
        } else if ("send".equals(orderStatus)) {
            orderReq.setStatus(CustomerRequestStatus.REQUEST_STATUS_SEND);
        }
        orderReq.setPageNo(1);
        orderReq.setPageSize(10);//只查前10条
        
        PageResponseDTO<RCustomerOrdResponse> resultPage = orderMainRSV.queryOrderByStaffId(orderReq);
        if (resultPage != null) {
            String orderList = JSON.toJSONString(resultPage.getResult());
            result.put("orderList", orderList);
        } 
        
        return result;
    }
    
    
    /**
     * 
     * orderList:(获取我关注的商品列表信息). <br/> 
     * 
     * @author huangxl 
     * @param staffId
     * @param orderStatus
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    @RequestMapping(value="/atteGdsList")
    @ResponseBody
    public Map<String,Object> atteGdsList() throws BusinessException{
        Map<String,Object> result = new HashMap<String,Object>();
        /*获取我关注的商品信息列表*/
        GdsCollectReqDTO req = new GdsCollectReqDTO();
        req.setStaffId(req.getStaff().getId());
        req.setStatus("1");
        req.setPageNo(1);
        req.setPageSize(5);//只查前10条
        PageResponseDTO<GdsCollectRespDTO> resultPage = gdsCollectRSV.queryGdsCollectRespDTOPagingByStaff(req);        
        if (resultPage != null && resultPage.getResult() != null) {
            List<GdsCollectRespDTO> collectGdsList = new ArrayList<GdsCollectRespDTO>();
            //把图片id转为图片url
            for (GdsCollectRespDTO resp : resultPage.getResult()) {
                if (StringUtil.isNotBlank(resp.getSkuMainPic())) {
                    resp.setSkuMainPic(ImageUtil.getImageUrl(resp.getSkuMainPic()));
                } else {
                    resp.setSkuMainPic(ImageUtil.getImageUrl(""));//取默认图片
                }
                collectGdsList.add(resp);
            }
            String atteGdsList = JSON.toJSONString(collectGdsList);
            result.put("atteGdsList", atteGdsList);
        } 
        return result;
    }
    
    /**
     * 
     * confirmOrd:(确认收货). <br/> 
     * 
     * @author huangxl 
     * @param orderId
     * @return 
     * @since JDK 1.7
     */
    @RequestMapping(value="/confirmord")
    @ResponseBody
    public EcpBaseResponseVO confirmOrd(@RequestParam(value="orderId")String orderId){
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        ROrdReceiptRequest rdto = new ROrdReceiptRequest();
        if (StringUtil.isBlank(orderId)) {
            throw new BusinessException("order.null.error");
        }
        rdto.setOrderId(orderId);
        rdto.setStaffId(rdto.getStaff().getId());
        //默认为出现异常，如果方法执行成功，则变更结果为成功。
        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
        vo.setResultMsg("确认收货失败。");
        ordReceiptRSV.orderReceipt(rdto);
        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        vo.setResultMsg("确认收货成功。");
        return vo;
    }
    
    @RequestMapping(value="/ordcancel")
    @ResponseBody
    public EcpBaseResponseVO cancelOrd(@RequestParam(value="orderId")String orderId){
        EcpBaseResponseVO resp = new EcpBaseResponseVO();
        ROrderDetailsRequest rdetail = new ROrderDetailsRequest();
        if (StringUtil.isBlank(orderId)) {
            throw new BusinessException("orderId不能为空");
        }
        rdetail.setOrderId(orderId);
        rdetail.setStaffId(rdetail.getStaff().getId());
        //默认为出现异常，如果方法执行成功，则变更结果为成功。
        resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
        resp.setResultMsg("订单取消失败。");
        orderMainRSV.removeOrd(rdetail);
        resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        resp.setResultMsg("订单取消成功。");
        return resp;
    }
    
    /**
     * 
     * payOrderCnt:(获取待付款订单数). <br/> 
     * 
     * @author huangxl 
     * @return 
     * @since JDK 1.7
     */
    @RequestMapping(value="/payordercnt")
    @ResponseBody
    public Map<String,Object> payOrderCnt(){
        Map<String,Object> result = new HashMap<String,Object>();
        //判断用户是否登录
        com.zengshi.ecp.server.front.security.AuthPrivilegeResDTO authPrivilegeResDTO = WebContextUtil.getCurrentUser();
        if (authPrivilegeResDTO == null) {
            result.put("payOrdCnt", 0);//待付款订单数量
            return result;
        }
        
        //获取待付款、待收货、已收货订单数量
        RQueryOrderRequest orderReq = new RQueryOrderRequest();
        orderReq.setStaffId(orderReq.getStaff().getId());
        orderReq.setCurrentSiteId(orderReq.getCurrentSiteId());
        orderReq.setSiteId(orderReq.getCurrentSiteId());
        ROrdCountResponse ordCountRes = orderMainRSV.queryOrderCountByStaff(orderReq);
        if (ordCountRes != null && ordCountRes.getRequestStatusPay()!= null) {
            result.put("payOrdCnt", ordCountRes.getRequestStatusPay());//待付款订单数量
        } else {
            result.put("payOrdCnt", 0);//待付款订单数量
        }
        return result;
    }
}


