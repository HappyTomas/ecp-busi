package com.zengshi.ecp.order.service.busi.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.model.OrdFileImport;
import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dao.model.OrdSub;
import com.zengshi.ecp.order.dubbo.dto.RExportExcleResponse;
import com.zengshi.ecp.order.dubbo.dto.RGoodSaleRequest;
import com.zengshi.ecp.order.dubbo.dto.RGoodSaleResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdBarCodeResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrderIdResponse;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsDelivery;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsSub;
import com.zengshi.ecp.order.dubbo.dto.SOrderIdx;
import com.zengshi.ecp.order.dubbo.util.OrderUtils;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdExportFileSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdFileImportSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.util.DataInoutUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.MoneyUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;

public class OrdExportFileSVImpl implements IOrdExportFileSV {
    
    @Resource
    private IOrdMainSV ordMainSV;
    
    @Resource
    private IOrdSubSV  ordSubSV;

    @Resource
    private IOrdFileImportSV ordFileImportSV;

    private static final String MODULE = OrdExportFileSVImpl.class.getName();




    /** 
     * queryOrderExportInfo:要用线程的方式来处理，不然游标资源不够. <br/> 
     * @author cbl 
     * @param sDelyOrderIdxs
     * @return 
     * @since JDK 1.6 
     */ 
    protected List<ROrderDetailsResponse> queryOrderExportInfo(List<SOrderIdx> sDelyOrderIdxs){
        final List<ROrderDetailsResponse> rOrderDetailsResponses = new ArrayList<ROrderDetailsResponse>();
        for(SOrderIdx sOrderIdx:sDelyOrderIdxs){
            final ROrderDetailsRequest rOrderDetailsRequest = new ROrderDetailsRequest();
            rOrderDetailsRequest.setOrderId(sOrderIdx.getOrderId());
            
            Thread thread=new Thread(new Runnable() {
                
                @Override
                public void run() {
                    ROrderDetailsResponse sOrderDetailsResponse = ordMainSV.queryOrderDetails(rOrderDetailsRequest);
                    rOrderDetailsResponses.add(sOrderDetailsResponse);
                }
            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
//                e.printStackTrace();
                LogUtil.info(MODULE, e.getMessage(),e);
            }
        }
        return rOrderDetailsResponses;
    }

    protected PageResponseDTO<ROrderDetailsResponse> queryOrderExport(RQueryOrderRequest rQueryOrderRequest) {
        PageResponseDTO<ROrderDetailsResponse> pageResponse = PageResponseDTO
                .buildByBaseInfo(rQueryOrderRequest, ROrderDetailsResponse.class);
        pageResponse.setResult(new ArrayList<ROrderDetailsResponse>());
        PageResponseDTO<ROrderIdResponse> psdoi = this.ordMainSV.queryOrderId(rQueryOrderRequest);
        pageResponse.setCount(psdoi.getCount());
        pageResponse.setPageCount(psdoi.getPageCount());
        if (CollectionUtils.isEmpty(psdoi.getResult())) {
            pageResponse.setResult(null);
            return pageResponse;
        }
        List<SOrderIdx> sdois = new ArrayList<SOrderIdx>();
        for (ROrderIdResponse roi : psdoi.getResult()) {
            SOrderIdx sdo = new SOrderIdx();
            ObjectCopyUtil.copyObjValue(roi, sdo, null, false);
            sdois.add(sdo);
        }

        List<ROrderDetailsResponse> sqors = queryOrderExportInfo(sdois);
        if (CollectionUtils.isEmpty(sqors)) {
        } else {
            pageResponse.getResult().addAll(sqors);
        }

        return pageResponse;
    }
    protected List<ROrderDetailsResponse>  queryOrderExportNew(RQueryOrderRequest rQueryOrderRequest) {

        List<OrdMain> ordMains = this.ordMainSV.queryOrderIdByThread(rQueryOrderRequest);

        LogUtil.info(MODULE,"订单导出sql查询结束："+ordMains.size());
        List<ROrderDetailsResponse>  rOrderDetailsResponseList = new ArrayList<>();
        if(CollectionUtils.isEmpty(ordMains)){
            return rOrderDetailsResponseList;
        }
        LogUtil.info(MODULE,"销售明细导出补全数据结束");
        rOrderDetailsResponseList = queryOrderExportInfoByThread(ordMains,rQueryOrderRequest);
        return rOrderDetailsResponseList;
    }
    
    /**
     * wangxq 准备条码导出数据
     * @param queryOrderRequest
     * @return
     */
    protected PageResponseDTO<ROrdBarCodeResponse> queryOrdBarCodeInfo(RQueryOrderRequest queryOrderRequest){
        PageResponseDTO<ROrdBarCodeResponse> pageResponse = PageResponseDTO
                .buildByBaseInfo(queryOrderRequest, ROrdBarCodeResponse.class);
        pageResponse.setResult(new ArrayList<ROrdBarCodeResponse>());
        
        PageResponseDTO<ROrderIdResponse> psdoi = this.ordMainSV.queryOrderId(queryOrderRequest);
        pageResponse.setCount(psdoi.getCount());
        pageResponse.setPageCount(psdoi.getPageCount());
        if (CollectionUtils.isEmpty(psdoi.getResult())) {
            pageResponse.setResult(null);
            return pageResponse;
        }
        List<SOrderIdx> sdois = new ArrayList<SOrderIdx>();
        for (ROrderIdResponse roi : psdoi.getResult()) {
            SOrderIdx sdo = new SOrderIdx();
            ObjectCopyUtil.copyObjValue(roi, sdo, null, false);
            sdois.add(sdo);
        }

        List<ROrdBarCodeResponse> sqors = queryOrderBarCodeInfo(sdois);
        if (CollectionUtils.isEmpty(sqors)) {
        } else {
            pageResponse.getResult().addAll(sqors);
        }

        return pageResponse;

    }
    protected List<ROrdBarCodeResponse> queryOrdBarCodeInfoNew (RQueryOrderRequest queryOrderRequest){
        List<OrdMain> ordMains = this.ordMainSV.queryOrderIdByThread(queryOrderRequest);
        LogUtil.info(MODULE,"订单导出sql查询结束："+ordMains.size());
        List<ROrdBarCodeResponse> sqors = new ArrayList<>();
        if (CollectionUtils.isEmpty(ordMains)) {
            return sqors;
        }
        sqors = queryOrderBarCodeInfoByThread(ordMains,queryOrderRequest);
        return sqors;
    }
    
    //调商品域获取ISBN
    protected String getBarCode(String isbn){
        String barCode = isbn;
        if(StringUtil.isBlank(isbn)){
            return barCode;
        }
        if(isbn.contains("/")){
            barCode = isbn.split("/")[0];
            barCode = barCode.replaceAll("-","");
        }
        if(isbn.contains("-")){
            barCode = isbn.replaceAll("-","");
        }
        return barCode;
    }

    
    //条码服务遍历子订单
    protected List<ROrdBarCodeResponse> getBarCodeOrdSubs(List<SOrderDetailsSub> detailsSubs,String orderId){

        List<ROrdBarCodeResponse> ods = new ArrayList<>();
        for(int dIndex = 0;dIndex < detailsSubs.size();dIndex++){
            ROrdBarCodeResponse sOrderDetailsResponse = new ROrdBarCodeResponse();
            SOrderDetailsSub detailsSub = detailsSubs.get(dIndex);

            ObjectCopyUtil.copyObjValue(detailsSub, sOrderDetailsResponse, "", false);
            sOrderDetailsResponse.setBasePrice(MoneyUtil.convertCentToYuan(detailsSub.getBasePrice()));
            if(detailsSubs.get(dIndex).getBasePrice()==null||detailsSubs.get(dIndex).getBasePrice()==0l){
                sOrderDetailsResponse.setDiscount(0d);
            }else{
                BigDecimal bigDecimal = new BigDecimal(detailsSub.getDiscountPrice()).divide(new BigDecimal(detailsSub.getBasePrice()),2,RoundingMode.HALF_UP);
                sOrderDetailsResponse.setDiscount(bigDecimal.doubleValue()*100);
            }
            sOrderDetailsResponse.setBarCode(getBarCode(detailsSubs.get(dIndex).getTxCode()));
            sOrderDetailsResponse.setOrderMoney(MoneyUtil.convertCentToYuan(detailsSubs.get(dIndex).getBasePrice()*detailsSubs.get(dIndex).getOrderAmount()));
            if(detailsSubs.get(dIndex).getBasePrice()!=null)sOrderDetailsResponse.setRealMoney(MoneyUtil.convertCentToYuan(detailsSubs.get(dIndex).getDiscountMoney()));
            sOrderDetailsResponse.setOrderId(orderId);
            ods.add(sOrderDetailsResponse);
        }
        return ods;
    }
    /**
     * 插叙条码 wangxq
     * @param sDelyOrderIdxs
     * @return
     */
    protected List<ROrdBarCodeResponse> queryOrderBarCodeInfo(List<SOrderIdx> sDelyOrderIdxs){
        final List<ROrdBarCodeResponse> rOrderDetailsResponses = new ArrayList<ROrdBarCodeResponse>();
        for(int i=0;i<sDelyOrderIdxs.size();i++){
            final ROrderDetailsRequest rOrderDetailsRequest = new ROrderDetailsRequest();
            rOrderDetailsRequest.setOrderId(sDelyOrderIdxs.get(i).getOrderId());
            final SOrderIdx fIdx = sDelyOrderIdxs.get(i);

            Thread thread=new Thread(new Runnable() {

                @Override
                public void run() {
                    ROrderDetailsResponse rOrderDetailsResponse =ordMainSV.queryOrderDetails(rOrderDetailsRequest);
                    List<SOrderDetailsSub> detailsSubs = rOrderDetailsResponse.getsOrderDetailsSubs();
                    rOrderDetailsResponses.addAll(getBarCodeOrdSubs(detailsSubs, fIdx.getOrderId()));
                }
            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
//                e.printStackTrace();
                LogUtil.info(MODULE, e.getMessage(),e);
            }


        }
        return rOrderDetailsResponses;
    }
    protected List<ROrdBarCodeResponse> queryOrderBarCodeInfoByThread(List<OrdMain> ordMains,RQueryOrderRequest rQueryOrderRequest){
        List<ROrdBarCodeResponse>  saleResponses = new ArrayList<>();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(16,32,5, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(64));
        Map<Integer, List<ROrdBarCodeResponse>> ordBarMap = new HashMap<Integer, List<ROrdBarCodeResponse>>();
        int group = 60;  //查数据点40%，剩余占用60%,分成60个线程
        int groupNum = ordMains.size() / group;
        int lastNum = ordMains.size() % group;
        if(ordMains.size() <= 60){
            group = ordMains.size();
            groupNum = 1;
        } else {
            group += 1;
        }
        List<Future> fus = new ArrayList<Future>();
        for (int i = 0; i < group; i++) {
            List<OrdMain> obs  = null;
            if(ordMains.size() <= 60){
                obs = new ArrayList<>();
                obs.add(ordMains.get(i));
            } else {
                if(i != group-1){
                    obs = ordMains.subList(i*groupNum, (i+1)*groupNum);
                } else {
                    obs = ordMains.subList(i*groupNum, i*groupNum+lastNum);
                }
            }
            Future f = executor.submit(new OrdExportFileSVImpl.DealOrdBarThread(obs, rQueryOrderRequest.getExportKey(),i,ordBarMap));
            fus.add(f);
        }

        for (Future f : fus) {
            try {
                f.get();
            } catch (InterruptedException e) {
                LogUtil.info(MODULE,"销售明细补全信息线程查询异常",e);
            } catch (ExecutionException e) {
                LogUtil.info(MODULE,"销售明细补全信息线程查询异常",e);
            }
        }
        executor.shutdown();
        for (List<ROrdBarCodeResponse> v : ordBarMap.values()) {
            LogUtil.info(MODULE,"补全信息子列表数量："+v.size());
            saleResponses.addAll(v);
        }

        return saleResponses;
    }
    
    @Override
    public PageResponseDTO<ROrderDetailsResponse> exportOrder2Print(
            RQueryOrderRequest rQueryOrderRequest) throws BusinessException {
        PageResponseDTO<ROrderDetailsResponse> rdor = queryOrderExport(rQueryOrderRequest);
        return rdor;
    }

    @Override
    public RExportExcleResponse queryOrder2Excle(RQueryOrderRequest rQueryOrderRequest)
            throws BusinessException {
        LogUtil.info(MODULE, "=====导出订单入参======="+JSON.toJSONString(rQueryOrderRequest));
        Long oper = rQueryOrderRequest.getStaff().getId();
        String fileName = oper.toString();
        String fileType = "xls";
        String moduleName = "First";
        String operator = oper.toString();
        List<String> titles = new ArrayList<String>();
        titles.add("单号");
//        titles.add("部门");
//        titles.add("操作人");
//        titles.add("操作日期");
//        titles.add("复核人");
//        titles.add("复核日期");
//        titles.add("客户名称");
//        titles.add("业务类型");
//        titles.add("收货单位");
        titles.add("联系人");
        titles.add("联系电话");
        titles.add("省份");
        titles.add("联系地址");
        titles.add("邮编");
        titles.add("运费");
        titles.add("其他费用");
        titles.add("发货方式");
        titles.add("发运单号");
        titles.add("汇款票号");
        titles.add("汇款金额");
        titles.add("汇款方式");
        titles.add("汇款日期");
        //退款 退货查询方法需要根据是否单独退货和多次退货进行设计
//        titles.add("退款方式");
//        titles.add("退款金额");

        List<List<Object>> datas = new ArrayList<List<Object>>();
        PageResponseDTO<ROrderDetailsResponse> rdor = queryOrderExport(rQueryOrderRequest);
        for (ROrderDetailsResponse shor : rdor.getResult()) {
            List<Object> data = new ArrayList<Object>();
            data.add(shor.getsOrderDetailsMain().getId());
//            data.add("");
//            data.add("");
//            data.add("");
//            data.add("");
//            data.add("");
//            data.add("");
//            data.add("");
//            data.add("");
            String contactName = shor.getsOrderDetailsMain().getContactName();
            data.add(StringUtil.isBlank(contactName) ? "" : contactName);
            String contactPhone = shor.getsOrderDetailsMain().getContactPhone();
            data.add(StringUtil.isBlank(contactPhone) ? "" : contactPhone);
            data.add("");
            String chnlAddress = shor.getsOrderDetailsMain().getChnlAddress();
            data.add(StringUtil.isBlank(chnlAddress) ? "" : chnlAddress);
            data.add("");
            long expressFee = shor.getsOrderDetailsMain().getRealExpressFee();
            data.add(Double.parseDouble(MoneyUtil.convertCentToYuan(expressFee)));
            data.add("");
            data.add(BaseParamUtil.fetchParamValue("STAFF_SHOP_DISTRIBUTION_WAY",
                    shor.getsOrderDetailsMain().getDispatchType()));
            if (shor.getsOrderDetailsDeliverys().size() > 0) {
                String detail = "";
                for(int i=0;i<shor.getsOrderDetailsDeliverys().size();i++){
                    SOrderDetailsDelivery delivery = shor.getsOrderDetailsDeliverys().get(i);
                    String expressNo = delivery.getExpressNo();
                    String expressName = delivery.getExpressName();
                    expressName = (StringUtil.isBlank(expressName)?"":expressName+" ");
                    if(detail.equals("")){
                        if(!StringUtil.isBlank(expressNo)){
                            detail=expressName + expressNo;
                        }                      
                    }else{
                        if(!StringUtil.isBlank(expressNo)){
                            detail=detail+"  "+expressName+expressNo;
                        } 
                       
                    }
                }
                //data.add(StringUtil.isBlank(expressNo) ? "" : expressName + expressNo);
                data.add(detail);
            } else {
                data.add("");
            }

            String orderId = shor.getsOrderDetailsMain().getId();
            data.add(shor.getsOrderDetailsMain().getId().substring(orderId.length() - 4));
            long realMoney = shor.getsOrderDetailsMain().getRealMoney();
            data.add(Double.parseDouble(MoneyUtil.convertCentToYuan(realMoney)));
            data.add(BaseParamUtil.fetchParamValue("ORD_PAY_TYPE",
                    shor.getsOrderDetailsMain().getPayType()));
            Timestamp payTime = shor.getsOrderDetailsMain().getPayTime();
            String dateTime = "";
            if(payTime!=null){
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                dateTime = dateFormat.format(new Date(payTime.getTime()));
            }
            data.add(dateTime);
            /**
             * 依据情况单独开发（退货退款）
             */
//            data.add("");
//            data.add("");
            datas.add(data);
        }
        String fileId = DataInoutUtil.exportExcel(datas, titles, "Order", fileType, moduleName, operator);
//        FileUtil.saveFile(fileName, fileType);
//        String localFileName = "E:\\1.xlsx";
//        FileUtil.readFile(fileId, localFileName);
        
        LogUtil.info(MODULE, "======================"+fileId);
        RExportExcleResponse rer = new RExportExcleResponse();
        rer.setFileId(fileId);
        return rer;
    }

    @Override
    public RExportExcleResponse queryOrder2ExcleNew(RQueryOrderRequest rQueryOrderRequest) throws BusinessException {
        LogUtil.info(MODULE, "=====导出订单入参======="+JSON.toJSONString(rQueryOrderRequest));
        Long oper = rQueryOrderRequest.getStaff().getId();
        String fileName = oper.toString();
        String fileType = "xls";
        String moduleName = "First";
        String operator = oper.toString();
        List<String> titles = new ArrayList<String>();
        titles.add("单号");
        titles.add("部门");
        titles.add("操作人");
        titles.add("操作日期");
        titles.add("复核人");
        titles.add("复核日期");
        titles.add("客户名称");
        titles.add("业务类型");
        titles.add("收货单位");
        titles.add("联系人");
        titles.add("联系电话");
        titles.add("省份");
        titles.add("联系地址");
        titles.add("邮编");
        titles.add("运费");
        titles.add("其他费用");
        titles.add("发货方式");
        titles.add("发运单号");
        titles.add("汇款票号");
        titles.add("汇款金额");
        titles.add("汇款方式");
        titles.add("汇款日期");
        titles.add("退款方式");
        titles.add("退款金额");

        List<List<Object>> datas = new ArrayList<List<Object>>();
        List<ROrderDetailsResponse> rdor = queryOrderExportNew(rQueryOrderRequest);
        for (ROrderDetailsResponse shor : rdor) {
            List<Object> data = new ArrayList<Object>();
            data.add(shor.getsOrderDetailsMain().getId());
            data.add("");
            data.add("");
            data.add("");
            data.add("");
            data.add("");
            data.add("");
            data.add("");
            data.add("");
            String contactName = shor.getsOrderDetailsMain().getContactName();
            data.add(StringUtil.isBlank(contactName) ? "" : contactName);
            String contactPhone = shor.getsOrderDetailsMain().getContactPhone();
            data.add(StringUtil.isBlank(contactPhone) ? "" : contactPhone);
            data.add("");
            String chnlAddress = shor.getsOrderDetailsMain().getChnlAddress();
            data.add(StringUtil.isBlank(chnlAddress) ? "" : chnlAddress);
            data.add("");
            long expressFee = shor.getsOrderDetailsMain().getRealExpressFee();
            data.add(Double.parseDouble(MoneyUtil.convertCentToYuan(expressFee)));
            data.add("");
            data.add(BaseParamUtil.fetchParamValue("STAFF_SHOP_DISTRIBUTION_WAY",
                    shor.getsOrderDetailsMain().getDispatchType()));
            if (shor.getsOrderDetailsDeliverys().size() > 0) {
                SOrderDetailsDelivery delivery = shor.getsOrderDetailsDeliverys().get(0);
                String expressNo = delivery.getExpressNo();
                String expressName = delivery.getExpressName();
                expressName = (StringUtil.isBlank(expressName)?"":expressName+" ");
                data.add(StringUtil.isBlank(expressNo) ? "" : expressName + expressNo);
            } else {
                data.add("");
            }

            String orderId = shor.getsOrderDetailsMain().getId();
            data.add(shor.getsOrderDetailsMain().getId().substring(orderId.length() - 4));
            long realMoney = shor.getsOrderDetailsMain().getRealMoney();
            data.add(Double.parseDouble(MoneyUtil.convertCentToYuan(realMoney)));
            data.add(BaseParamUtil.fetchParamValue("ORD_PAY_TYPE",
                    shor.getsOrderDetailsMain().getPayType()));
            Timestamp payTime = shor.getsOrderDetailsMain().getPayTime();
            String dateTime = "";
            if(payTime!=null){
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                dateTime = dateFormat.format(new Date(payTime.getTime()));
            }
            data.add(dateTime);
            data.add("");
            data.add("");
            datas.add(data);
        }
        String fileId = DataInoutUtil.exportExcel(datas, titles, "Order", fileType, moduleName, operator);
//        FileUtil.saveFile(fileName, fileType);
//        String localFileName = "E:\\1.xlsx";
//        FileUtil.readFile(fileId, localFileName);

        LogUtil.info(MODULE, "======================"+fileId);
        RExportExcleResponse rer = new RExportExcleResponse();
        rer.setFileId(fileId);
        return rer;
    }

    @Override
    public RExportExcleResponse queryOrderBarCode(RQueryOrderRequest queryOrderRequest)
            throws BusinessException {
        RExportExcleResponse response = new RExportExcleResponse();
        Long oper = queryOrderRequest.getStaff().getId();
        String fileName = oper.toString();
        String fileType = "xls";
        String moduleName = "BarCode";
        String operator = oper.toString();
        List<String> titles = new ArrayList<String>();
        titles.add("单号");
        titles.add("序号");
        titles.add("条码");
        titles.add("数量");
        titles.add("定价");
        titles.add("折扣");
        titles.add("订单应付");
        titles.add("订单实付");

        LogUtil.info(MODULE, "=====导出订单条码入参======="+JSON.toJSONString(queryOrderRequest));
        List<List<Object>> datas = new ArrayList<List<Object>>();
        PageResponseDTO<ROrdBarCodeResponse> rdor = queryOrdBarCodeInfo(queryOrderRequest);
        Long serial = 0l;
        for (ROrdBarCodeResponse barCode : rdor.getResult()) {
            List<Object> data = new ArrayList<Object>();
            data.add(barCode.getOrderId());
            serial ++;
            data.add(serial);
            data.add(barCode.getBarCode());
            data.add(barCode.getOrderAmount());
            data.add(Double.parseDouble(barCode.getBasePrice()));
            data.add(barCode.getDiscount());
            data.add(Double.parseDouble(barCode.getBasePrice())*barCode.getOrderAmount());
            data.add(Double.parseDouble(barCode.getRealMoney()));
            datas.add(data);
        }

        String fileId = DataInoutUtil.exportExcel(datas, titles, "0", fileType, moduleName, operator);

        LogUtil.info(MODULE, "======================条码文件Id"+fileId+"======================");
        response.setFileId(fileId);
        return response;
    }

    @Override
    public RExportExcleResponse queryOrderBarCodeNew(RQueryOrderRequest queryOrderRequest) throws BusinessException {
        RExportExcleResponse response = new RExportExcleResponse();
        Long oper = queryOrderRequest.getStaff().getId();
        String fileName = oper.toString();
        String fileType = "xls";
        String moduleName = "BarCode";
        String operator = oper.toString();
        List<String> titles = new ArrayList<String>();
        titles.add("单号");
        titles.add("序号");
        titles.add("条码");
        titles.add("数量");
        titles.add("定价");
        titles.add("折扣");
        titles.add("订单码洋");
        titles.add("订单实洋");

        LogUtil.info(MODULE, "=====导出订单条码入参======="+JSON.toJSONString(queryOrderRequest));
        List<List<Object>> datas = new ArrayList<List<Object>>();
        List<ROrdBarCodeResponse> rdor = queryOrdBarCodeInfoNew(queryOrderRequest);
        Long serial = 0l;
        for (ROrdBarCodeResponse barCode : rdor) {
            List<Object> data = new ArrayList<Object>();
            data.add(barCode.getOrderId());
            serial ++;
            data.add(serial);
            data.add(barCode.getBarCode());
            data.add(barCode.getOrderAmount());
            data.add(Double.parseDouble(barCode.getBasePrice()));
            data.add(barCode.getDiscount());
            data.add(Double.parseDouble(barCode.getBasePrice())*barCode.getOrderAmount());
            data.add(Double.parseDouble(barCode.getRealMoney()));
            datas.add(data);
        }

        String fileId = DataInoutUtil.exportExcel(datas, titles, "0", fileType, moduleName, operator);

        LogUtil.info(MODULE, "======================条码文件Id"+fileId+"======================");
        response.setFileId(fileId);
        return response;
    }

    @Override
    public RExportExcleResponse exportGoodSaleExcel(RGoodSaleRequest rGoodSaleRequest) {
        LogUtil.info(MODULE, "=====导出销售明细入参======="+JSON.toJSONString(rGoodSaleRequest));
        Long oper = rGoodSaleRequest.getStaff().getId();
        String fileName = "Sale"+"("+DateUtil.getDateString(rGoodSaleRequest.getBegDate(), DateUtil.YYYYMMDD)+"-"+DateUtil.getDateString(DateUtil.getOffsetDaysDate(rGoodSaleRequest.getEndDate(),-1), DateUtil.YYYYMMDD)+")";
        String fileType = "xls";
        String moduleName = "GoodSaleForOrdSub";
        String operator = oper.toString();
        List<String> titles = new ArrayList<String>();
        titles.add("订单编号");
        titles.add("商品名称");
//        titles.add("ISBN号");
//        titles.add("书号");
        titles.add("产品一级分类");
        titles.add("产品二级分类");
        titles.add("产品三级分类");
        titles.add("产品四级分类");
        titles.add("购买单价");
        titles.add("购买数量");
        titles.add("购买总金额");
        titles.add("是否活动商品");
        titles.add("购买日期");
        titles.add("会员名");
        titles.add("联系人");
        titles.add("联系电话");
        titles.add("联系地址");
        List<List<Object>> datas = new ArrayList<List<Object>>();
        List<RGoodSaleResponse> shors = ordSubSV.queryGoodSaleExport(rGoodSaleRequest);
        for (RGoodSaleResponse shor : shors) {
            List<Object> data = new ArrayList<Object>();
            String orderId = shor.getOrderId();

            data.add(shor.getOrderId()==null?"":shor.getOrderId());
            data.add(shor.getGdsName()==null?"":shor.getGdsName());
//            data.add(shor.getIsbn()==null?"":shor.getIsbn());
//           data.add(shor.getZsCode()==null?"":shor.getZsCode());
            data.add(shor.getCategory01()==null?"":shor.getCategory01());
            data.add(shor.getCategory02()==null?"":shor.getCategory02());
            data.add(shor.getCategory03()==null?"":shor.getCategory03());
            data.add(shor.getCategory04()==null?"":shor.getCategory04());
            data.add(shor.getDiscountPrice() == null ? 0l : OrderUtils.doubleDiv(shor.getDiscountPrice()));
            data.add(shor.getOrderAmount()==null?0l:shor.getOrderAmount());
            data.add(shor.getOrderMoney() == null ? 0l : OrderUtils.doubleDiv(shor.getOrderMoney()));
            data.add(shor.getIsProm() == null ? "" : shor.getIsProm()==true?"是":"否");

            Timestamp orderTime = shor.getOrderTime();
            String dateTime = "";
            if(orderTime!=null){
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                dateTime = dateFormat.format(new Date(orderTime.getTime()));
            }
            data.add(dateTime);

            data.add(shor.getStaffCode() == null ? "" : shor.getStaffCode());
            data.add(shor.getContactName() == null ? "" : shor.getContactName());
            data.add(shor.getContactPhone() == null ? "" : shor.getContactPhone());
            data.add(shor.getChnlAddress() == null ? "" : shor.getChnlAddress());

            datas.add(data);
        }
        String fileId = DataInoutUtil.exportExcel(datas, titles, fileName, fileType, moduleName, operator);
//        FileUtil.saveFile(fileName, fileType);
//        String localFileName = "E:\\1.xlsx";
//        FileUtil.readFile(fileId, localFileName);

        LogUtil.info(MODULE, "======================"+fileId);
        RExportExcleResponse rer = new RExportExcleResponse();
        rer.setFileId(fileId);
        return rer;

    }

    protected List<ROrderDetailsResponse> queryOrderExportInfoByThread(List<OrdMain> ordMains,RQueryOrderRequest rQueryOrderRequest) {
        List<ROrderDetailsResponse>  saleResponses = new ArrayList<>();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(16,32,5, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(64));
        Map<Integer, List<ROrderDetailsResponse>> ordDetailsMap = new HashMap<Integer, List<ROrderDetailsResponse>>();
        int group = 60;  //查数据点40%，剩余占用60%,分成60个线程
        int groupNum = ordMains.size() / group;
        int lastNum = ordMains.size() % group;
        if(ordMains.size() <= 60){
            group = ordMains.size();
            groupNum = 1;
        } else {
            group += 1;
        }
        List<Future> fus = new ArrayList<Future>();
        for (int i = 0; i < group; i++) {
            List<OrdMain> obs  = null;
            if(ordMains.size() <= 60){
                obs = new ArrayList<>();
                obs.add(ordMains.get(i));
            } else {
                if(i != group-1){
                    obs = ordMains.subList(i*groupNum, (i+1)*groupNum);
                } else {
                    obs = ordMains.subList(i*groupNum, i*groupNum+lastNum);
                }
            }
            Future f = executor.submit(new OrdExportFileSVImpl.DealOrdInfoThread(obs, rQueryOrderRequest.getExportKey(),i,ordDetailsMap));
            fus.add(f);
        }

        for (Future f : fus) {
            try {
                f.get();
            } catch (InterruptedException e) {
                LogUtil.info(MODULE,"销售明细补全信息线程查询异常",e);
            } catch (ExecutionException e) {
                LogUtil.info(MODULE,"销售明细补全信息线程查询异常",e);
            }
        }
        executor.shutdown();
        for (List<ROrderDetailsResponse> v : ordDetailsMap.values()) {
            LogUtil.info(MODULE,"补全信息子列表数量："+v.size());
            saleResponses.addAll(v);
        }

        return saleResponses;
    }

    public class DealOrdInfoThread extends Thread  {

        private List<OrdMain> ordMains;

        private Long key;

        private int group;

        private Map<Integer, List<ROrderDetailsResponse>> ordDetailsMap;

        public DealOrdInfoThread(List<OrdMain> ordMains,Long key,int group,Map<Integer, List<ROrderDetailsResponse>> ordDetailsMap){
            this.ordMains = ordMains;
            this.key = key;
            this.group = group;
            this.ordDetailsMap = ordDetailsMap;
        }

        @Override
        public void run() {
            LogUtil.info(MODULE,"销售明细补全线程开始处理");
            List<ROrderDetailsResponse> rOrderDetailsResponseList = new ArrayList<>();
            for(OrdMain ordMain:ordMains){
                final ROrderDetailsRequest rOrderDetailsRequest = new ROrderDetailsRequest();
                rOrderDetailsRequest.setOrderId(ordMain.getId());
                ROrderDetailsResponse sOrderDetailsResponse = ordMainSV.queryOrderDetails(rOrderDetailsRequest);
                rOrderDetailsResponseList.add(sOrderDetailsResponse);
            }
            ordDetailsMap.put(group,rOrderDetailsResponseList);
            OrdFileImport ordFile = new OrdFileImport();
            ordFile.setId(key);
            ordFile.setShopId(1L);  //文件进度
            ordFileImportSV.updateById(ordFile);
            LogUtil.info(MODULE,"销售明细补全线程结束处理");
        }
    }
    public class DealOrdBarThread extends Thread  {

        private List<OrdMain> ordMains;

        private Long key;

        private int group;

        private Map<Integer, List<ROrdBarCodeResponse>> ordBarMap;

        public DealOrdBarThread(List<OrdMain> ordMains,Long key,int group,Map<Integer, List<ROrdBarCodeResponse>> ordBarMap){
            this.ordMains = ordMains;
            this.key = key;
            this.group = group;
            this.ordBarMap = ordBarMap;
        }

        @Override
        public void run() {
            LogUtil.info(MODULE,"销售明细补全线程开始处理");
            List<ROrdBarCodeResponse> rOrdBarCodeResponseList = new ArrayList<>();
            for(OrdMain ordMain:ordMains){
                final ROrderDetailsRequest rOrderDetailsRequest = new ROrderDetailsRequest();
                rOrderDetailsRequest.setOrderId(ordMain.getId());
                ROrderDetailsResponse rOrderDetailsResponse =ordMainSV.queryOrderDetails(rOrderDetailsRequest);
                List<SOrderDetailsSub> detailsSubs = rOrderDetailsResponse.getsOrderDetailsSubs();
                rOrdBarCodeResponseList.addAll(getBarCodeOrdSubs(detailsSubs, ordMain.getId()));
            }
            ordBarMap.put(group,rOrdBarCodeResponseList);
            OrdFileImport ordFile = new OrdFileImport();
            ordFile.setId(key);
            ordFile.setShopId(1L);  //文件进度
            ordFileImportSV.updateById(ordFile);
            LogUtil.info(MODULE,"销售明细补全线程结束处理");
        }
    }
}

