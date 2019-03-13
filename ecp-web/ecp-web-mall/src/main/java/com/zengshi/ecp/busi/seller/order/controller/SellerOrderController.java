package com.zengshi.ecp.busi.seller.order.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.seller.order.vo.RDelyOrderReqVO;
import com.zengshi.ecp.busi.seller.order.vo.RExportExcelRespVO;
import com.zengshi.ecp.busi.seller.order.vo.ROrderSummaryResp;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.order.dubbo.dto.RExportExcleResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrderSummaryResponse;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.dto.RShopOrderResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdManageRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdSubRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffUnionRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

@Controller
@RequestMapping(value = "/seller/order/manage")
public class SellerOrderController extends EcpBaseController{
    
    private static String MODULE = SellerOrderController.class.getName();
    
    @Resource
    private IOrdMainRSV ordMainRSV;
    
    @Resource
    private IOrdManageRSV ordManageRSV;
    
    @Resource
    private IStaffUnionRSV staffUnionRSV;
    
    @Resource
    private IGdsCategoryRSV gdsCategoryRSV;
    
    @Resource
    private IGdsInfoQueryRSV gdsInfoQueryRSV;
    
    @Resource(name = "ordSubRSV")
    private IOrdSubRSV ordSubRSV;
    
    private static final String SELLER_ORDER_DELIVERY_VM_PATH = "/seller/order";

    @RequestMapping(value="index")
    public String index(Model model, @RequestParam(value="shopId", required=false)Long shopId, @RequestParam(value="status", required=false)String status) throws Exception
    {
        model.addAttribute("begDate", DateUtil.getOffsetDaysDate(DateUtil.getTheDayFirstSecond(DateUtil.getSysDate()), -90));
        model.addAttribute("endDate", DateUtil.getDate());
        model.addAttribute("shopId", shopId);
        model.addAttribute("status", status);
        return SELLER_ORDER_DELIVERY_VM_PATH + "/seller-order";
    }
    
    
    /** 
     * orderSummaryData:订单查询汇总信息查询. <br/> 
     * @author cbl 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping("/ordersum")
    @ResponseBody
    public ROrderSummaryResp orderSummaryData(Model model, RDelyOrderReqVO vo) throws Exception {
        // 后场服务所需要的DTO；
        RQueryOrderRequest rQueryOrderRequest = new RQueryOrderRequest();
        ObjectCopyUtil.copyObjValue(vo, rQueryOrderRequest, "", false);
/*        rQueryOrderRequest.setEndDate(new Timestamp(DateUtils.addDays(vo.getEndDate(), 1).getTime()));*/
        
        //搜索会员
        if(StringUtil.isNotBlank(vo.getStaffCode())) {
            CustInfoReqDTO CustInfoReqDTO = new CustInfoReqDTO();
            CustInfoReqDTO.setStaffCode(vo.getStaffCode());
            CustInfoResDTO custInfoResDTO = staffUnionRSV.findCustInfo(CustInfoReqDTO);
            if(custInfoResDTO!=null) rQueryOrderRequest.setStaffId(custInfoResDTO.getId());
        }
        rQueryOrderRequest.setSysType("00");
        rQueryOrderRequest.setCategoryCodes(null);
        // 其它的查询条件；
        LogUtil.debug(MODULE, vo.toString());
        ROrderSummaryResp rosr = new ROrderSummaryResp();
        if(StringUtil.isNotEmpty(vo.getStaffCode()) && StringUtil.isEmpty(rQueryOrderRequest.getStaffId())){
            rosr.setSumOrderMoney(0L);
            rosr.setSumRealMoney(0L);
            rosr.setOrderCount(0L);
            rosr.setPayedCount(0L);
            rosr.setPayedRate(0L);
        }else{
             ROrderSummaryResponse  t = ordManageRSV.queryOrderSummaryData(rQueryOrderRequest); 
             ObjectCopyUtil.copyObjValue(t, rosr, "", false);
        }
        return rosr;
       
    }
    /** 
     * gridList:订单查询. <br/> 
     * @author cbl 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping("/orderlist")
    public String gridList(Model model, RDelyOrderReqVO vo) throws Exception {
        // 后场服务所需要的DTO；
//        RQueryOrderRequest r = new RQueryOrderRequest();
        RQueryOrderRequest r = vo.toBaseInfo(RQueryOrderRequest.class);
        ObjectCopyUtil.copyObjValue(vo, r, "", false);
/*        r.setEndDate(new Timestamp(DateUtils.addDays(vo.getEndDate(), 1).getTime()));*/
        r.setSysType("00");
        PageResponseDTO<RShopOrderResponse> resp = null;
        if(StringUtil.isNotBlank(vo.getStaffCode())){
            CustInfoReqDTO   CustInfoReqDTO  = new CustInfoReqDTO();
            CustInfoReqDTO.setStaffCode(vo.getStaffCode());
            CustInfoResDTO  custInfoResDTO = staffUnionRSV.findCustInfo(CustInfoReqDTO);
            if(custInfoResDTO == null){
                return SELLER_ORDER_DELIVERY_VM_PATH + "/orderlist/seller-order-list";
            } else {
                r.setStaffId(custInfoResDTO.getId());
            }
        }
        resp = ordManageRSV.queryOrder(r);
        model.addAttribute("resp", resp);

        return SELLER_ORDER_DELIVERY_VM_PATH + "/orderlist/seller-order-list";
//        return SELLER_ORDER_DELIVERY_VM_PATH + "/seller-order";
    }
    
    @RequestMapping(value="/printList")
    public String printList(Model model, RDelyOrderReqVO vo){
        // 后场服务所需要的DTO；
//        RQueryOrderRequest r = new RQueryOrderRequest();
        RQueryOrderRequest r = vo.toBaseInfo(RQueryOrderRequest.class);
        ObjectCopyUtil.copyObjValue(vo, r, "", false);
/*        r.setEndDate(new Timestamp(DateUtils.addDays(vo.getEndDate(), 1).getTime()));*/
        r.setSysType("00");
        if(StringUtil.isNotBlank(vo.getStaffCode())){
            CustInfoReqDTO   custInfoReqDTO  = new CustInfoReqDTO();
            custInfoReqDTO.setStaffCode(vo.getStaffCode());
            CustInfoResDTO  custInfoResDTO = staffUnionRSV.findCustInfo(custInfoReqDTO);
            if(custInfoResDTO != null){
                r.setStaffId(custInfoResDTO.getId());
            }  
        }
        PageResponseDTO<ROrderDetailsResponse> dto = ordMainRSV.exportOrder2Print(r);
        List<ROrderDetailsResponse> orders = dto.getResult();
        List<Map<String,Object>> orderList = new ArrayList<Map<String,Object>>();
        if(StringUtil.isNotEmpty(orders)){
            for(int i = 0; i < orders.size(); i++){
                Map<String,Object> map = new HashMap<String,Object>();
                ROrderDetailsResponse order = orders.get(i); 
                // 主订单相关字段
                map.put("sOrderDetailsMain", order.getsOrderDetailsMain());
                // 子订单相关字段
                map.put("sOrderDetailsSubs", order.getsOrderDetailsSubs());
                // 订单优惠相关字段
                map.put("sOrderDetailsDiscount", order.getsOrderDetailsDiscount());
                // 订单跟踪相关字段
                map.put("sOrderDetailsTracks", order.getsOrderDetailsTracks());
                // 订单收货地址相关字段
                map.put("sOrderDetailsAddr", order.getsOrderDetailsAddr());
                // 订单普通发票相关字段
                map.put("sOrderDetailsComm", order.getsOrderDetailsComm());
                // 订单增值税发票相关字段
                map.put("sOrderDetailsTax", order.getsOrderDetailsTax());
                orderList.add(map);
            }
        }
        model.addAttribute("orderList", orderList); 
        return SELLER_ORDER_DELIVERY_VM_PATH + "/orderlist/order-print"; 
    }
    
    @RequestMapping(value="/exportDetail")
    public String exportDetail(@RequestParam("exportInfo")String exportInfo,@RequestParam("exportType")String exportType,Model model){
        model.addAttribute("exportInfo",exportInfo);
        model.addAttribute("exportType",exportType);
        return SELLER_ORDER_DELIVERY_VM_PATH + "/export/order-export";
    }
    
    @RequestMapping(value = "/export/{fileId}")
    public void exportExcel(@PathVariable("fileId") String fileId, HttpServletResponse response){
        ServletOutputStream outputStream = null;
        try{
            byte[] bytes=FileUtil.readFile(fileId);
            String fileName = FileUtil.getFileName(fileId) + ".xls";
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            //设置响应头和下载保存的文件名      用关键字命名
//            response.setHeader("content-disposition","attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            response.setHeader("content-disposition","attachment;filename=" + fileName);
            outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @RequestMapping(value = "/getFileId")
    @ResponseBody
    public RExportExcelRespVO getFileId(RDelyOrderReqVO vo){
        RExportExcelRespVO resp = new RExportExcelRespVO();

        // 后场服务所需要的DTO；
//        RQueryOrderRequest r = new RQueryOrderRequest();
        RQueryOrderRequest r = vo.toBaseInfo(RQueryOrderRequest.class);
        ObjectCopyUtil.copyObjValue(vo, r, "", false);
/*        r.setEndDate(new Timestamp(DateUtils.addDays(vo.getEndDate(), 1).getTime()));*/
        r.setSysType("00");

        //搜索会员
        if(StringUtil.isNotBlank(vo.getStaffCode())) {
            CustInfoReqDTO CustInfoReqDTO = new CustInfoReqDTO();
            CustInfoReqDTO.setStaffCode(vo.getStaffCode());
            CustInfoResDTO custInfoResDTO = staffUnionRSV.findCustInfo(CustInfoReqDTO);
            if(custInfoResDTO!=null) r.setStaffId(custInfoResDTO.getId());
        }

        r.setCategoryCodes(null);
        // 其它的查询条件；
        LogUtil.debug(MODULE, vo.toString());

        try{
            RExportExcleResponse excel = ordMainRSV.exportOrder2Excle(r);
            if(StringUtil.isBlank(excel.getFileId())){
                resp.setFileId(excel.getFileId());
                resp.setResultFlag(resp.RESULT_FLAG_FAILURE);
            }else{
                resp.setFileId(excel.getFileId());
                resp.setResultFlag(resp.RESULT_FLAG_SUCCESS);
            }

        }catch (BusinessException e){
            LogUtil.error(MODULE,"============订单模板导出异常==========");
            resp.setResultFlag(resp.RESULT_FLAG_EXCEPTION);
        }

        return resp;
    }

    @RequestMapping(value = "/getBarCodeFileId")
    @ResponseBody
    public RExportExcelRespVO getBarCodeFileId(RDelyOrderReqVO vo){
        RExportExcelRespVO resp = new RExportExcelRespVO();

        // 后场服务所需要的DTO；
//        RQueryOrderRequest r = new RQueryOrderRequest();
        RQueryOrderRequest r = vo.toBaseInfo(RQueryOrderRequest.class);
        ObjectCopyUtil.copyObjValue(vo, r, "", false);
/*        r.setEndDate(new Timestamp(DateUtils.addDays(vo.getEndDate(), 1).getTime()));*/
        r.setSysType("00");

        //搜索会员
        if(StringUtil.isNotBlank(vo.getStaffCode())) {
            CustInfoReqDTO CustInfoReqDTO = new CustInfoReqDTO();
            CustInfoReqDTO.setStaffCode(vo.getStaffCode());
            CustInfoResDTO custInfoResDTO = staffUnionRSV.findCustInfo(CustInfoReqDTO);
            if(custInfoResDTO!=null) r.setStaffId(custInfoResDTO.getId());
        }

        r.setCategoryCodes(null);
        // 其它的查询条件；
        LogUtil.debug(MODULE, vo.toString());

        try{
            RExportExcleResponse excel = ordMainRSV.exportOrderBarCode(r);
            if(StringUtil.isBlank(excel.getFileId())){
                resp.setFileId(excel.getFileId());
                resp.setResultFlag(resp.RESULT_FLAG_FAILURE);
            }else{
                resp.setFileId(excel.getFileId());
                resp.setResultFlag(resp.RESULT_FLAG_SUCCESS);
            }

        }catch (BusinessException e){
            LogUtil.error(MODULE, "============订单条码导出异常==========");
            resp.setResultFlag(resp.RESULT_FLAG_EXCEPTION);
        }

        return resp;
    }
}

