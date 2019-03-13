package com.zengshi.ecp.busi.order.pay.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.order.bill.vo.RQueryOrderReqVO;
import com.zengshi.ecp.busi.order.pay.vo.RAuditTradeCheckReqVO;
import com.zengshi.ecp.busi.order.pay.vo.ROfflineQueryReqVO;
import com.zengshi.ecp.busi.order.pay.vo.ROfflineReviewReqVO;
import com.zengshi.ecp.busi.order.pay.vo.ROrderBackReqVO;
import com.zengshi.ecp.busi.order.util.OrdConstant;
import com.zengshi.ecp.busi.order.util.ParamsTool;
import com.zengshi.ecp.busi.order.vo.RPayWayReqVo;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.order.dubbo.dto.RBackApplyResp;
import com.zengshi.ecp.order.dubbo.dto.ROfflineQueryRequest;
import com.zengshi.ecp.order.dubbo.dto.ROfflineQueryResponse;
import com.zengshi.ecp.order.dubbo.dto.ROfflineReviewRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdMainResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.PayWayRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.PayWayResponse;
import com.zengshi.ecp.order.dubbo.dto.pay.RAuditTradeCheckRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.RAuditTradeCheckResponse;
import com.zengshi.ecp.order.dubbo.dto.pay.RAuditTradeCheckTotalResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdBackGdsRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdDetailsRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdOfflineRSV;
import com.zengshi.ecp.order.dubbo.interfaces.pay.IAuditTradeCheckRSV;
import com.zengshi.ecp.order.dubbo.interfaces.pay.IPayWayRSV;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffUnionRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.MoneyUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

@Controller
@RequestMapping(value="/order/pay")
public class PayController extends EcpBaseController {

    private static final String MODULE = PayController.class.getName();
    
    @Resource
    private IOrdOfflineRSV ordOfflineRSV;
    
    @Resource
    private ICustInfoRSV custInfoRSV;
    
    @Resource
    private IOrdDetailsRSV ordDetailsRSV;
    
    @Resource
    private ICustInfoRSV custInfoSRV;

    @Resource
    private IOrdMainRSV ordMainRSV; 
    
    @Resource
    private IAuditTradeCheckRSV auditTradeCheckRSV; 
    
    @Resource
    private IOrdBackGdsRSV ordBackGdsRSV; 
    
    @Resource
    private IStaffUnionRSV staffUnionRSV;
    
    @Resource
    private IPayWayRSV paywayRSV;
    
    /**
     *  init:(默认进入银行支付账单和银行退款账单页面). <br/>
     * @author panjs
     * @param model
     * @return
     * @since JDK 1.6
     */
    @RequestMapping()
    public String init(Model model) {  
    	 model.addAttribute("begDate",new Timestamp(DateUtils.addYears(new Date(), -1).getTime()));
    	 model.addAttribute("endDate",new Timestamp(DateUtils.addDays(new Date(), -1).getTime()));  
        return "/order/pay/pay-grid";
    }
    
    /**
     * 
     * paywayGrid:进入支付方式管理页面<br/> 
     * @author lwy 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/paywaygrid")
    public String paywayGrid(Model model){
        model.addAllAttributes(ParamsTool.paramsToday());
        return "/order/pay/payway-grid";
    }
    
    /**
     * 
     * paywayList:(支付方式查询). <br/> 
     * @author lwy 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/paywayList")
    @ResponseBody
    public Model paywayList(Model model,RPayWayReqVo vo) throws Exception {
        PayWayRequest dto = new PayWayRequest();
        dto = vo.toBaseInfo(PayWayRequest.class);
        ObjectCopyUtil.copyObjValue(vo, dto, "", false);
        LogUtil.debug(MODULE, vo.toString()); 
        PageResponseDTO<PayWayResponse> t = paywayRSV.queryPayWayPage(dto);
        EcpBasePageRespVO<Map> respVO  = EcpBasePageRespVO.buildByPageResponseDTO(t);
        return super.addPageToModel(model, respVO);
    }
    
    /**
     * 
     * addPayWay:(进入添加支付方式页面). <br/> 
     * @author lwy 
     * @param model
     * @param vo
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping("/addPayWay")
    public String addPayWay(Model model,RPayWayReqVo vo){
        LogUtil.debug(MODULE,"参数为：" + vo.toString());
        return "/order/pay/payway/payway-add";
    }
    
    /**
     * 
     * editPayWay:(进入编辑支付方式页面). <br/> 
     * @author lwy 
     * @param model
     * @param vo
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping("/editPayWay")
    public String editPayWay(Model model,RPayWayReqVo vo){
        PayWayRequest dto = new PayWayRequest();
        dto = vo.toBaseInfo(PayWayRequest.class);
        ObjectCopyUtil.copyObjValue(vo, dto, "", false);
        LogUtil.debug(MODULE,"参数为：" + vo.toString());      
        PayWayResponse respdto = null;       
        try{
            respdto = this.paywayRSV.queryOnePayWay(dto);
        }catch(Exception e){
            e.printStackTrace();
            LogUtil.info(MODULE, "获取标签失败！", e);
        }
        model.addAttribute("payway",respdto);
        return "/order/pay/payway/payway-edit";
    }
    
    /**
     * 
     * paywaySave:(新增或者修改支付方式). <br/> 
     * 
     * @author lwy 
     * @param model
     * @param vo
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping("/paywayAdd")
    @ResponseBody
    public PayWayResponse paywayAdd(Model model,RPayWayReqVo vo)  {
        PayWayRequest dto = new PayWayRequest();
        dto = vo.toBaseInfo(PayWayRequest.class);
        ObjectCopyUtil.copyObjValue(vo, dto, "", false);
        PayWayResponse respdto = new PayWayResponse();
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
       
        //添加创建人信息和创建时间
        dto.setCreateTime(time);
        dto.setCreateStaff(dto.getStaff().getId());
       
        try{
            paywayRSV.addPayWay(dto);
            respdto.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS); 
        }catch(Exception e){
            LogUtil.error(MODULE, "============出错了============="+e.getMessage());
            respdto.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE); 
            respdto.setResultMsg(e.getMessage());
        }
        return respdto;
    }
    
    /**
     * 
     * paywaySave:(新增或者修改支付方式). <br/> 
     * 
     * @author lwy 
     * @param model
     * @param vo
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping("/paywayUpdate")
    @ResponseBody
    public PayWayResponse paywayUpdate(Model model,RPayWayReqVo vo)  {
        PayWayRequest dto = new PayWayRequest();
        dto = vo.toBaseInfo(PayWayRequest.class);
        ObjectCopyUtil.copyObjValue(vo, dto, "", false);
        PayWayResponse respdto = new PayWayResponse();
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        
        //添加修改人和修改时间
        dto.setUpdateTime(time);
        dto.setUpdateStaff(dto.getStaff().getId());
        
        try{
            paywayRSV.updatePayWay(dto);
            respdto.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS); 
        }catch(Exception e){
            LogUtil.error(MODULE, "============出错了============="+e.getMessage());
            respdto.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE); 
            respdto.setResultMsg(e.getMessage());
        }
        return respdto;
    }
    
    /**
     * paySumData:(获取对账汇总信息). <br/>
     * @author panjs
     * @param model
     * @return
     * @since JDK 1.6
     */
    @RequestMapping("/paySumData")
    @ResponseBody
    public Map<String,Object> paySumData(Model model, RAuditTradeCheckReqVO vo) throws Exception {
        // 后场服务所需要的DTO；
    	 Map<String, Object> map = new HashMap<String, Object>(); 
    	RAuditTradeCheckRequest r = new RAuditTradeCheckRequest();
        r = vo.toBaseInfo(RAuditTradeCheckRequest.class);
        ObjectCopyUtil.copyObjValue(vo, r, "", false); 
        // 其它的查询条件；
        LogUtil.debug(MODULE, vo.toString());
        RAuditTradeCheckTotalResponse  resp = auditTradeCheckRSV.getTAuditTradeChecksTotal(r);
        map.put("transAmounts", resp.getTransAmounts());
        map.put("successOrderCounts", resp.getSuccessOrderCounts());
        map.put("transOrderCounts", resp.getTransOrderCounts());
        map.put("successOrderAmounts", resp.getSuccessOrderAmounts());
        return map;
    }
     
    /**
     *  yhPayList:(银行支付账单查询). <br/>
     * @author panjs
     * @param model
     * @return
     * @since JDK 1.6
     */
    @RequestMapping("/yhPayList")
    @ResponseBody
    public Model yhPayList(Model model, RAuditTradeCheckReqVO vo) throws Exception {
        if(StringUtil.isNotBlank(vo.getStaffCode())) {
            CustInfoReqDTO CustInfoReqDTO = new CustInfoReqDTO();
            CustInfoReqDTO.setStaffCode(vo.getStaffCode());
            CustInfoResDTO custInfoResDTO = staffUnionRSV.findCustInfo(CustInfoReqDTO);
            if(custInfoResDTO!=null) vo.setStaffId(custInfoResDTO.getId());
            if (custInfoResDTO==null) {
                PageResponseDTO<RAuditTradeCheckResponse> t = new PageResponseDTO<RAuditTradeCheckResponse>();
                EcpBasePageRespVO<Map> respVO  = EcpBasePageRespVO.buildByPageResponseDTO(t);
                return super.addPageToModel(model, respVO);
            }
        }
    	String status = OrdConstants.ShopRequestStatus.REQUEST_STATUS_DELY; 
    	RAuditTradeCheckRequest dto = new RAuditTradeCheckRequest();
    	dto = vo.toBaseInfo(RAuditTradeCheckRequest.class);
        ObjectCopyUtil.copyObjValue(vo, dto, "", false); 
        LogUtil.debug(MODULE, vo.toString()); 
        PageResponseDTO<RAuditTradeCheckResponse> t = auditTradeCheckRSV.getTAuditTradeChecks(dto);
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
        return super.addPageToModel(model, ParamsTool.ordDetailSiteUrl(respVO));
    }
     
    /**
     * yhBackList:(银行退款账单查询). <br/>
     * @author panjs
     * @param model
     * @return
     * @since JDK 1.6
     */
    @RequestMapping("/yhBackList")
    @ResponseBody
    public Model yhBackList(Model model, RAuditTradeCheckReqVO vo) throws Exception {  
        if(StringUtil.isNotBlank(vo.getStaffCode())) {
            CustInfoReqDTO CustInfoReqDTO = new CustInfoReqDTO();
            CustInfoReqDTO.setStaffCode(vo.getStaffCode());
            CustInfoResDTO custInfoResDTO = staffUnionRSV.findCustInfo(CustInfoReqDTO);
            if(custInfoResDTO!=null) vo.setStaffId(custInfoResDTO.getId());
            if (custInfoResDTO==null) {
                PageResponseDTO<RAuditTradeCheckResponse> t = new PageResponseDTO<RAuditTradeCheckResponse>();
                EcpBasePageRespVO<Map> respVO  = EcpBasePageRespVO.buildByPageResponseDTO(t);
                return super.addPageToModel(model, respVO);
            }
        }
    	RAuditTradeCheckRequest dto = new RAuditTradeCheckRequest();
    	dto = vo.toBaseInfo(RAuditTradeCheckRequest.class);
        ObjectCopyUtil.copyObjValue(vo, dto, "", false); 
        LogUtil.debug(MODULE, vo.toString()); 
        PageResponseDTO<RAuditTradeCheckResponse> t = auditTradeCheckRSV.getTAuditTradeChecks(dto);
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
    	return super.addPageToModel(model, ParamsTool.ordDetailSiteUrl(respVO));
    }
    
    /**
     * ptPayList:(平台支付对账单查询). <br/>
     * @author panjs
     * @param model
     * @return
     * @since JDK 1.6
     */
    @RequestMapping("/ptPayList")
    @ResponseBody
    public Model ptPayList(Model model, RQueryOrderReqVO vo) throws Exception {
    	RQueryOrderRequest dto = new RQueryOrderRequest();
    	dto = vo.toBaseInfo(RQueryOrderRequest.class);
        ObjectCopyUtil.copyObjValue(vo, dto, "", false); 
        LogUtil.debug(MODULE, vo.toString()); 
        PageResponseDTO<ROrdMainResponse> t = ordMainRSV.querynotInAuditTradeCheckOrders(dto);
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
    	return super.addPageToModel(model, ParamsTool.ordDetailSiteUrl(respVO)); 
         
    }
    
    /**
     * ptBackList:(平台退款对账单查询). <br/>
     * @author panjs
     * @param model
     * @return
     * @since JDK 1.6
     */
    @RequestMapping("/ptBackList")
    @ResponseBody
    public Model ptBackList(Model model, ROrderBackReqVO vo) throws Exception {
    	ROrderBackReq dto = new ROrderBackReq();
    	dto = vo.toBaseInfo(ROrderBackReq.class);
    	vo.setShopId(null);
        ObjectCopyUtil.copyObjValue(vo, dto, "", false); 
        LogUtil.debug(MODULE, vo.toString()); 
        PageResponseDTO<RBackApplyResp> t = ordBackGdsRSV.querynotInAuditTradeCheckOrders(dto);
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
    	return super.addPageToModel(model, ParamsTool.ordDetailSiteUrl(respVO)); 
    } 
    
    /**
     * 
     * exportYhPayExcel:(导出银行支付账单). <br/> 
     * @author panjs 
     * @param response
     * @param vo
     * @since JDK 1.7
     */
    @RequestMapping(value = "/exportYhPayExcel", method = RequestMethod.POST)
    public void exportYhPayExcel(HttpServletResponse response, @ModelAttribute
    		RAuditTradeCheckReqVO vo) {

        try {
            ServletOutputStream outputStream = response.getOutputStream();
            String fileName = "支付交易查询.xls";
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            //设置响应头和下载保存的文件名      用关键字命名
            response.setHeader("content-disposition","attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            //response.setHeader("content-disposition","attachment;filename=" + fileName);
            String[] titles = {"支付通道名称","订单编号","支付平台流水号","清算金额","订单金额","支付时间","对账状态","清算日期","店铺名称","会员名","银行卡号","银行卡姓名"};
            RAuditTradeCheckRequest dto = new RAuditTradeCheckRequest();
        	dto = vo.toBaseInfo(RAuditTradeCheckRequest.class);
            ObjectCopyUtil.copyObjValue(vo, dto, "", false);
            dto.setPageSize(Integer.MAX_VALUE);
            PageResponseDTO<RAuditTradeCheckResponse> t = auditTradeCheckRSV.getTAuditTradeChecks(dto);
             
            List<RAuditTradeCheckResponse> list2 = t.getResult();
 
            this.yhPayCol(titles, response, list2);
        } catch (IOException e) {
            LogUtil.error(MODULE, "exportYhPayExcel", e);
        }

    }  
  
    //银行支付账单要导出的列
    private void yhPayCol(String[] titles, HttpServletResponse response,
            List<RAuditTradeCheckResponse> data) {
        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = workBook.createSheet("银行支付账单");
        XSSFCellStyle headStyle = this.getHeadStyle(workBook);
        XSSFCellStyle bodyStyle = this.getBodyStyle(workBook);
        // 构建表头
        XSSFRow headRow = sheet.createRow(0);
        XSSFCell cell = null;
        for (int i = 0; i < titles.length; i++) {
            cell = headRow.createCell(i);
            cell.setCellStyle(headStyle);
            cell.setCellValue(titles[i]);
        }
        // 构建表体数据
        if (data != null && data.size() > 0) {
            for (int j = 0; j < data.size(); j++) {
                XSSFRow bodyRow = sheet.createRow(j + 1);
                RAuditTradeCheckResponse resp = data.get(j); 
                
                cell = bodyRow.createCell(0);
                cell.setCellStyle(bodyStyle); 
                String payWay = resp.getPayWay();
                String payWayName = "-";
                if(StringUtil.isNotBlank(payWay) && !payWay.equals("null")){
                    payWayName = this.getPayWayName(payWay);
                }
                cell.setCellValue(payWayName);
                
                cell = bodyRow.createCell(1);
                cell.setCellStyle(bodyStyle); 
                cell.setCellValue(isNull(resp.getOrderId())); 
                 
                cell = bodyRow.createCell(2);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(isNull(resp.getTransNo()));
                
                cell = bodyRow.createCell(3);
                cell.setCellStyle(bodyStyle);
                String transAmount = "0.0";
                if(resp.getTransAmount()!=null){
                	transAmount = Double.parseDouble(MoneyUtil.convertCentToYuan(resp.getTransAmount()))+"";
                }
                cell.setCellValue(isNull(transAmount));
                
                cell = bodyRow.createCell(4);
                cell.setCellStyle(bodyStyle); 
                String orderAmount = "0.0";
                if(resp.getOrderAmount()!=null){
                	orderAmount = Double.parseDouble(MoneyUtil.convertCentToYuan(resp.getOrderAmount()))+"";
                }
                cell.setCellValue(isNull(orderAmount)); 
                
                cell = bodyRow.createCell(5);
                cell.setCellStyle(bodyStyle); 
                String dateTime = "";
                if(resp.getPayTime()!=null){
                	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                	dateTime = dateFormat.format(new Date(resp.getPayTime().getTime()));
                }
                cell.setCellValue(isNull(dateTime)); 
                
                cell = bodyRow.createCell(6);
                cell.setCellStyle(bodyStyle); 
                String auditStatus = resp.getAuditStatus();
                String auditStatusName = "-";
                if(StringUtil.isNotBlank(auditStatus) && !auditStatus.equals("null")){
	                if(auditStatus.equals("00")){
	                	auditStatusName = "正常";
	                }else if(auditStatus.equals("01")){
	                	auditStatusName = "长款";
	                }else if(auditStatus.equals("02")){
	                	auditStatusName = "短款";
	                }
                }
                cell.setCellValue(auditStatusName);  
                
                cell = bodyRow.createCell(7);
                cell.setCellStyle(bodyStyle); 
                dateTime = "";
                if(resp.getCheckDate()!=null){
                	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                	dateTime = dateFormat.format(new Date(resp.getCheckDate().getTime()));
                }
                cell.setCellValue(String.valueOf(dateTime==""?'-':dateTime)); 
                
                cell = bodyRow.createCell(8);
                cell.setCellStyle(bodyStyle); 
                cell.setCellValue(isNull(resp.getShopName())); 
                
                
                cell = bodyRow.createCell(9);
                cell.setCellStyle(bodyStyle); 
                cell.setCellValue(isNull(resp.getStaffName())); 
                
                cell = bodyRow.createCell(10);
                cell.setCellStyle(bodyStyle); 
                cell.setCellValue(isNull(resp.getCardNo())); 
                
                cell = bodyRow.createCell(11);
                cell.setCellStyle(bodyStyle); 
                cell.setCellValue(isNull(resp.getCardName()));  
                
            }
        }
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            workBook.write(outputStream);
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
                LogUtil.error(MODULE, "yhPayCol", e);
            }
        }

    } 
	
    /**
     * 
     * exportYhBackExcel:(导出银行退款账单). <br/> 
     * @author panjs 
     * @param response
     * @param vo
     * @since JDK 1.7
     */
    @RequestMapping(value = "/exportYhBackExcel", method = RequestMethod.POST)
    public void exportYhBackExcel(HttpServletResponse response, @ModelAttribute RAuditTradeCheckReqVO vo) {

        try {
            String fileName = "支付交易查询.xls";
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            //设置响应头和下载保存的文件名      用关键字命名
            response.setHeader("content-disposition","attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            //response.setHeader("content-disposition","attachment;filename=" + fileName);
            String[] titles = { "支付通道名称","订单编号","支付平台流水号","实际退款金额","应退金额","退款时间","对账状态","退款编号","店铺名称","会员名","银行卡号","银行卡姓名"};
            RAuditTradeCheckRequest dto = new RAuditTradeCheckRequest();
        	dto = vo.toBaseInfo(RAuditTradeCheckRequest.class);
            ObjectCopyUtil.copyObjValue(vo, dto, "", false);
            dto.setPageSize(Integer.MAX_VALUE);
            PageResponseDTO<RAuditTradeCheckResponse> t = auditTradeCheckRSV.getTAuditTradeChecks(dto);
             
            List<RAuditTradeCheckResponse> list2 = t.getResult();
 
            this.yhBackCol(titles, response, list2);
        } catch (Exception e) {
            LogUtil.error(MODULE, "exportYhBackExcel", e);
        }

    } 
    
    //银行退款账单 要导出的列
    private void yhBackCol(String[] titles, HttpServletResponse response,
            List<RAuditTradeCheckResponse> data) {
        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = workBook.createSheet("银行退款账单");
        XSSFCellStyle headStyle = this.getHeadStyle(workBook);
        XSSFCellStyle bodyStyle = this.getBodyStyle(workBook);
        // 构建表头
        XSSFRow headRow = sheet.createRow(0);
        XSSFCell cell = null;
        for (int i = 0; i < titles.length; i++) {
            cell = headRow.createCell(i);
            cell.setCellStyle(headStyle);
            cell.setCellValue(titles[i]);
        }
        // 构建表体数据
        if (data != null && data.size() > 0) {
            for (int j = 0; j < data.size(); j++) {
                XSSFRow bodyRow = sheet.createRow(j + 1);
                RAuditTradeCheckResponse resp = data.get(j); 
                
                cell = bodyRow.createCell(0);
                cell.setCellStyle(bodyStyle); 
                String payWay = resp.getPayWay();
                String payWayName = "-";
                if(StringUtil.isNotBlank(payWay) && !payWay.equals("null")){
                    payWayName = this.getPayWayName(payWay);
                }
                cell.setCellValue(payWayName);
                
                cell = bodyRow.createCell(1);
                cell.setCellStyle(bodyStyle); 
                cell.setCellValue(isNull(resp.getOrderId())); 
                 
                cell = bodyRow.createCell(2);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(isNull(resp.getTransNo()));
                
                cell = bodyRow.createCell(3);
                cell.setCellStyle(bodyStyle);
                String refundTransAmount = "0.0";
                if(resp.getRefundTransAmount()!=null){
                	refundTransAmount = Double.parseDouble(MoneyUtil.convertCentToYuan(resp.getRefundTransAmount()))+"";
                }
                cell.setCellValue(isNull(refundTransAmount));
                
                cell = bodyRow.createCell(4);
                cell.setCellStyle(bodyStyle);
                String refundOrderAmount = "0.0";
                if(resp.getRefundOrderAmount()!=null){
                	refundOrderAmount = Double.parseDouble(MoneyUtil.convertCentToYuan(resp.getRefundOrderAmount()))+"";
                }
                cell.setCellValue(isNull(refundOrderAmount)); 
                
                cell = bodyRow.createCell(5);
                cell.setCellStyle(bodyStyle); 
                String dateTime = "";
                if(resp.getPayTime()!=null){
                	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                	dateTime = dateFormat.format(new Date(resp.getPayTime().getTime()));
                }
                cell.setCellValue(String.valueOf(dateTime==""?'-':dateTime));  
                
                
                cell = bodyRow.createCell(6);
                cell.setCellStyle(bodyStyle); 
                String auditStatus = resp.getAuditStatus();
                String auditStatusName = "-";
                if(StringUtil.isNotBlank(auditStatus) && !auditStatus.equals("null")){
	                if(auditStatus.equals("00")){
	                	auditStatusName = "正常";
	                }else if(auditStatus.equals("01")){
	                	auditStatusName = "长款";
	                }else if(auditStatus.equals("02")){
	                	auditStatusName = "短款";
	                }
                }
                cell.setCellValue(auditStatusName);  
                
                cell = bodyRow.createCell(7);
                cell.setCellStyle(bodyStyle); 
                cell.setCellValue(isNull(resp.getBackId()+"")); 
                
                cell = bodyRow.createCell(8);
                cell.setCellStyle(bodyStyle); 
                cell.setCellValue(isNull(resp.getShopName())); 
                
                cell = bodyRow.createCell(9);
                cell.setCellStyle(bodyStyle); 
                cell.setCellValue(isNull(resp.getStaffName())); 
                
                cell = bodyRow.createCell(10);
                cell.setCellStyle(bodyStyle); 
                cell.setCellValue(isNull(resp.getCardNo())); 
                
                cell = bodyRow.createCell(11);
                cell.setCellStyle(bodyStyle); 
                cell.setCellValue(isNull(resp.getCardName()));  
                
            }
        }
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            workBook.write(outputStream);
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
                LogUtil.error(MODULE, "yhBackCol", e);
            }
        }

    } 
    
    /**
     * 
     * exportPtPayExcel:(导出支付差异对账单). <br/> 
     * @author panjs 
     * @param response
     * @param vo
     * @since JDK 1.7
     */
    @RequestMapping(value = "/exportPtPayExcel", method = RequestMethod.POST)
    public void exportPtPayExcel(HttpServletResponse response, @ModelAttribute RQueryOrderReqVO vo) {

        try {
            String fileName = "支付交易查询.xls";
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            //设置响应头和下载保存的文件名      用关键字命名
            response.setHeader("content-disposition","attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            //response.setHeader("content-disposition","attachment;filename=" + fileName);
            String[] titles = { "支付通道名称","订单编号","实付金额","订单状态","店铺名称","会员名","支付时间"};
            RQueryOrderRequest dto = new RQueryOrderRequest();
        	dto = vo.toBaseInfo(RQueryOrderRequest.class);
            ObjectCopyUtil.copyObjValue(vo, dto, "", false);
            dto.setPageSize(Integer.MAX_VALUE);
            PageResponseDTO<ROrdMainResponse> t = ordMainRSV.querynotInAuditTradeCheckOrders(dto);
             
            List<ROrdMainResponse> list2 = t.getResult();
 
            this.ptPayCol(titles, response, list2);
        } catch (Exception e) {
            LogUtil.error(MODULE, "exportPtPayExcel", e);
        }

    } 
    
    //支付差异对账单 要导出的列
    private void ptPayCol(String[] titles, HttpServletResponse response,
            List<ROrdMainResponse> data) {
        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = workBook.createSheet("支付差异对账单");
        XSSFCellStyle headStyle = this.getHeadStyle(workBook);
        XSSFCellStyle bodyStyle = this.getBodyStyle(workBook);
        // 构建表头
        XSSFRow headRow = sheet.createRow(0);
        XSSFCell cell = null;
        for (int i = 0; i < titles.length; i++) {
            cell = headRow.createCell(i);
            cell.setCellStyle(headStyle);
            cell.setCellValue(titles[i]);
        }
        // 构建表体数据
        if (data != null && data.size() > 0) {
            for (int j = 0; j < data.size(); j++) {
                XSSFRow bodyRow = sheet.createRow(j + 1);
                ROrdMainResponse resp = data.get(j);  
                
                cell = bodyRow.createCell(0);
                cell.setCellStyle(bodyStyle); 
                String payWay = resp.getPayWay();
                String payWayName = "-";
                if(StringUtil.isNotBlank(payWay) && !payWay.equals("null")){
                    payWayName = this.getPayWayName(payWay); 
                }
                cell.setCellValue(payWayName);
                
                cell = bodyRow.createCell(1);
                cell.setCellStyle(bodyStyle); 
                cell.setCellValue(isNull(resp.getId())); 
                 
                cell = bodyRow.createCell(2);
                cell.setCellStyle(bodyStyle);
                String realMoney = "0.0";
                if(resp.getRealMoney()!=null){
                	realMoney = Double.parseDouble(MoneyUtil.convertCentToYuan(resp.getRealMoney()))+"";
                }
                cell.setCellValue(isNull(realMoney));
                
                cell = bodyRow.createCell(3);
                cell.setCellStyle(bodyStyle); 
                String status = resp.getStatus();
                String statusName = "-";
                if(StringUtil.isNotBlank(statusName) && !statusName.equals("null")){
	                if(status.equals("01")){
	                	statusName = "待支付";
	                }else if(status.equals("02")){
	                	statusName = "已支付";
	                }else if(status.equals("03")){
	                	statusName = "待录入物流单";
	                }else if(status.equals("04")){
	                	statusName = "部分发货";
	                }else if(status.equals("05")){
	                	statusName = "已发货";
	                }else if(status.equals("06")){
	                	statusName = "已收货";
	                }else if(status.equals("08")){
	                	statusName = "已退款";
	                }else if(status.equals("80")){
	                	statusName = "关闭";
	                }else if(status.equals("99")){
	                	statusName = "已取消";
	                }
                }
                cell.setCellValue(statusName);
                
                cell = bodyRow.createCell(4);
                cell.setCellStyle(bodyStyle); 
                cell.setCellValue(isNull(resp.getShopName())); 
                
                cell = bodyRow.createCell(5);
                cell.setCellStyle(bodyStyle); 
                cell.setCellValue(isNull(resp.getStaffName()));  
                
                cell = bodyRow.createCell(6);
                cell.setCellStyle(bodyStyle); 
                String dateTime = "";
                if(resp.getPayTime()!=null){
                	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                	dateTime = dateFormat.format(new Date(resp.getPayTime().getTime()));
                }
                cell.setCellValue(String.valueOf(dateTime==""?'-':dateTime));    
                 
            }
        }
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            workBook.write(outputStream);
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
                LogUtil.error(MODULE, "ptPayCol", e);
            }
        }

    } 
    
    /**
     * 
     * exportPtPayExcel:(导出退款差异对账单). <br/> 
     * @author panjs 
     * @param response
     * @param vo
     * @since JDK 1.7
     */
    @RequestMapping(value = "/exportPtBackExcel", method = RequestMethod.POST)
    public void exportPtBackExcel(HttpServletResponse response, @ModelAttribute ROrderBackReqVO vo) {

        try {
            String fileName = "支付交易查询.xls";
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            //设置响应头和下载保存的文件名      用关键字命名
            response.setHeader("content-disposition","attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            //response.setHeader("content-disposition","attachment;filename=" + fileName);
            String[] titles = {"订单编号","退款编号","退款金额","退款时间","退款数量","退款类型名称","是否整个主订单退货","商城","退货原因","退货原因备注","供货商处理意见描述"};
            ROrderBackReq dto = new ROrderBackReq();
            vo.setShopId(null);
        	dto = vo.toBaseInfo(ROrderBackReq.class);
            ObjectCopyUtil.copyObjValue(vo, dto, "", false);
            dto.setPageSize(Integer.MAX_VALUE);
            PageResponseDTO<RBackApplyResp> t = ordBackGdsRSV.querynotInAuditTradeCheckOrders(dto);
             
            List<RBackApplyResp> list2 = t.getResult();
 
            this.ptBackCol(titles, response, list2);
        } catch (Exception e) {
            LogUtil.error(MODULE, "exportPtPayExcel", e);
        }

    } 
    
    //退款差异对账单 要导出的列
    private void ptBackCol(String[] titles, HttpServletResponse response,
            List<RBackApplyResp> data) {
        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = workBook.createSheet("退款差异对账单");
        XSSFCellStyle headStyle = this.getHeadStyle(workBook);
        XSSFCellStyle bodyStyle = this.getBodyStyle(workBook);
        // 构建表头
        XSSFRow headRow = sheet.createRow(0);
        XSSFCell cell = null;
        for (int i = 0; i < titles.length; i++) {
            cell = headRow.createCell(i);
            cell.setCellStyle(headStyle);
            cell.setCellValue(titles[i]);
        }
        // 构建表体数据
        if (data != null && data.size() > 0) {
            for (int j = 0; j < data.size(); j++) {
                XSSFRow bodyRow = sheet.createRow(j + 1);
                RBackApplyResp resp = data.get(j); 
                 
                cell = bodyRow.createCell(0);
                cell.setCellStyle(bodyStyle); 
                cell.setCellValue(isNull(resp.getOrderId())); 
                
                cell = bodyRow.createCell(1);
                cell.setCellStyle(bodyStyle); 
                cell.setCellValue(isNull(resp.getBackId()+"")); 
                
                cell = bodyRow.createCell(2);
                cell.setCellStyle(bodyStyle);
                String backMoney = "0.0";
                if(resp.getBackMoney()!=null){
                	backMoney = Double.parseDouble(MoneyUtil.convertCentToYuan(resp.getBackMoney()))+"";
                }
                cell.setCellValue(isNull(backMoney));
                 
                cell = bodyRow.createCell(3);
                cell.setCellStyle(bodyStyle); 
                String dateTime = "";
                if(resp.getApplyTime()!=null){
                	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                	dateTime = dateFormat.format(new Date(resp.getApplyTime().getTime()));
                }
                cell.setCellValue(String.valueOf(dateTime==""?'-':dateTime));  
                
                cell = bodyRow.createCell(4);
                cell.setCellStyle(bodyStyle); 
                cell.setCellValue(isNull(resp.getNum()+"")); 
                
                cell = bodyRow.createCell(5);
                cell.setCellStyle(bodyStyle); 
                cell.setCellValue(isNull(resp.getBackTypeName())); 
                
                cell = bodyRow.createCell(6);
                cell.setCellStyle(bodyStyle); 
                String isEntire = resp.getIsEntire();
                String isEntireName = "-";
                if(StringUtil.isNotBlank(isEntire) && !isEntire.equals("null")){
	                if(isEntire.equals("0")){
	                	isEntireName = "否";
	                }else if(isEntire.equals("1")){
	                	isEntireName = "是";
	                }
                }
                cell.setCellValue(isEntireName);  
                
                cell = bodyRow.createCell(7);
                cell.setCellStyle(bodyStyle); 
                String siteId = resp.getSiteId()+"";
                String siteIdName = "";
                if(StringUtil.isEmpty(siteId)){
                	CmsSiteRespDTO siteInfo=CmsCacheUtil.getCmsSiteCache(new Long(siteId));
                	siteIdName=siteInfo.getSiteName();
                }
                cell.setCellValue(isNull(siteIdName));  
                
                cell = bodyRow.createCell(8);
                cell.setCellStyle(bodyStyle); 
                String status = resp.getStatus();
                String statusName = "-";
                if(StringUtil.isNotBlank(statusName) && !statusName.equals("null")){
	                if(status.equals("01")){
	                	statusName = "收到商品破损";
	                }else if(status.equals("02")){
	                	statusName = "商品错发/漏发";
	                }else if(status.equals("03")){
	                	statusName = "收到商品与描述不符";
	                }else if(status.equals("04")){
	                	statusName = "商品质量问题";
	                }else if(status.equals("05")){
	                	statusName = "其他";
	                }
                }
                cell.setCellValue(statusName);  
                
                cell = bodyRow.createCell(9);
                cell.setCellStyle(bodyStyle); 
                cell.setCellValue(isNull(resp.getBackDesc()));
                
                cell = bodyRow.createCell(10);
                cell.setCellStyle(bodyStyle); 
                cell.setCellValue(isNull(resp.getCheckDesc()));
            }
        }
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            workBook.write(outputStream);
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
                LogUtil.error(MODULE, "ptBackCol", e);
            }
        }

    } 
    
    /**
     * 设置表头的单元格样式
     * 
     * @return
     */
    private XSSFCellStyle getHeadStyle(XSSFWorkbook wb) {
        // 创建单元格样式
        XSSFCellStyle cellStyle = wb.createCellStyle();
        // 设置单元格的背景颜色为淡蓝色
        cellStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
        cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        // 设置单元格居中对齐
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        // 设置单元格垂直居中对齐
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        // 创建单元格内容显示不下时自动换行
        cellStyle.setWrapText(true);
        // 设置单元格字体样式
        XSSFFont font = wb.createFont();
        // 设置字体加粗
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        cellStyle.setFont(font);
        // 设置单元格边框为细线条
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
        return cellStyle;
    }

    /**
     * 设置表体的单元格样式
     * 
     * @return
     */
    private XSSFCellStyle getBodyStyle(XSSFWorkbook wb) {
        // 创建单元格样式
        XSSFCellStyle cellStyle = wb.createCellStyle();
        // 设置单元格居中对齐
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        // 设置单元格垂直居中对齐
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        // 创建单元格内容显示不下时自动换行
        cellStyle.setWrapText(true);
        // 设置单元格字体样式
        XSSFFont font = wb.createFont();
        // 设置字体加粗
       // font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        cellStyle.setFont(font);
        // 设置单元格边框为细线条
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
        return cellStyle;
    }
    //空字符以-显示
    private String isNull(String str){ 
    	if(StringUtil.isBlank(str) || str.equals("null")){
    		str="-";
    	}
    	return str;
    }
    //支付通道名称
    private String getPayWayName(String payWay){
        String payWayName = "-";
        if(payWay.equals("9002")){
            payWayName = "鸿支付";
        }else if(payWay.equals("9003")){
            payWayName = "支付宝";
        }else if(payWay.equals("9004")){
            payWayName = "农行支付";
        }else if(payWay.equals("9006")){
            payWayName = "微信支付";
        }else if(payWay.equals("9007")){
            payWayName = "微信扫码支付";
        }
        return payWayName;
    }  
    //线下支付页面
    @RequestMapping(value="/offlinereivew2")
    public String queryOfflineReview(){
    	return "/order/pay/offline/offline-review2";
    }
    //线下支付页面
    @RequestMapping(value="/offlinereivew")
    public String queryOfflineReview(Model model,ROfflineQueryReqVO vo){
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
        List<String> picUrls = new ArrayList<String>();
//        for(SOfflinePic pic : offlineResp.getAnnex()){
//            picUrls.add(ParamsTool.getImageUrl(pic.getVfsId(),""));
//        }
        //用户名称
        CustInfoReqDTO custDto = new CustInfoReqDTO();
        custDto.setId(offlineResp.getStaffId());
        CustInfoResDTO custresp = custInfoRSV.getCustInfoById(custDto);
        model.addAttribute("picUrls", picUrls);
        model.addAttribute("custName", custresp.getCustName());
        model.addAttribute("offlineResp", offlineResp);

        String detailUrl = ParamsTool.getOrdDetailUrl(vo.getSiteId(), vo.getOrderId());
        
        model.addAttribute("detailUrl", detailUrl);
        return "/order/pay/offline/offline-review";
    }
    
    //线下支付页面
    @RequestMapping(value="/offlinegrid")
    public String offlineReview(Model model){
    	model.addAllAttributes(ParamsTool.params());
        return "/order/pay/offline-grid";
    }

    //线下支付页面
    @RequestMapping(value="/offlinegrid2")
    public String offlineReview2(Model model,@RequestParam("shopId")String shopId){
    	model.addAllAttributes(ParamsTool.params());
    	model.addAttribute("shopId",shopId);
        return "/order/pay/offline-grid";
    }


    //线下支付列表
    @RequestMapping(value="/unchecklist")
    public Model offlineList(Model model,ROfflineQueryReqVO vo) throws Exception{
        LogUtil.info(MODULE, "线下支付列表");
        
        //客户昵称
        //String staffName = vo.getStaffId();
        //调客户域查Id
        
        
        ROfflineQueryRequest offquerydto = new ROfflineQueryRequest();
        
        offquerydto = vo.toBaseInfo(ROfflineQueryRequest.class);
        
//        offquerydto.setShopId(ParamsTool.getShopId());
        ObjectCopyUtil.copyObjValue(vo, offquerydto, "", false);
        //offquerydto.setStaff(ParamsTool.getStaff());
        
        // PageResponseDTO<DemoCfgRespDTO> t = this.initParam();
        PageResponseDTO<ROfflineQueryResponse> t = ordOfflineRSV.queryOfflineOrder(offquerydto);

        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);

        return super.addPageToModel(model, ParamsTool.ordDetailSiteUrl(ParamsTool.setApplyType(respVO)));
    }

    //线下支付列表
    @RequestMapping(value="/checkedlist")
    public Model checkedList(Model model,ROfflineQueryReqVO vo) throws Exception{
        LogUtil.info(MODULE, "线下支付列表");

        //客户昵称
        //String staffName = vo.getStaffId();
        //调客户域查Id

        ROfflineQueryRequest offquerydto = new ROfflineQueryRequest();

        offquerydto = vo.toBaseInfo(ROfflineQueryRequest.class);

        ObjectCopyUtil.copyObjValue(vo, offquerydto, "", false);

        PageResponseDTO<ROfflineQueryResponse> t = ordOfflineRSV.queryCheckedOrder(offquerydto);

        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);

        return super.addPageToModel(model, ParamsTool.ordDetailSiteUrl(ParamsTool.setApplyType(respVO)));
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

