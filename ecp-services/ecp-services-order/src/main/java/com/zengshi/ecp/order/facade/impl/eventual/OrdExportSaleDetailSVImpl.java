package com.zengshi.ecp.order.facade.impl.eventual;

import com.zengshi.ecp.order.dao.model.OrdFileImport;
import com.zengshi.ecp.order.dubbo.dto.*;
import com.zengshi.ecp.order.dubbo.util.OrderUtils;
import com.zengshi.ecp.order.facade.interfaces.eventual.IOrdExportSaleDetailSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdFileImportSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubSV;
import com.zengshi.ecp.server.util.DataInoutUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionStatus;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by cbl on 2016/12/7.
 */
public class OrdExportSaleDetailSVImpl implements IOrdExportSaleDetailSV {

    @Resource
    private IOrdFileImportSV ordFileImportSV;

    @Resource
    private IOrdSubSV ordSubSV;

    private static final String MODULE = OrdExportSaleDetailSVImpl.class.getName();

    @Override
    public void joinTransaction(JSONObject jsonObject, TransactionStatus transactionStatus, String s) {
        final RGoodSaleRequest rcdr= JSON.parseObject(jsonObject.toString(), RGoodSaleRequest.class);
        rcdr.setPageSize(Integer.MAX_VALUE);
        rcdr.setPageNo(1);
        LogUtil.info(MODULE,"销售明细导出子事务入参："+JSON.toJSONString(rcdr));
        List<RGoodSaleResponse> responseList = ordSubSV.queryGoodSaleExportNew(rcdr);
        LogUtil.info(MODULE,"销售明细开始写入文件");
        String fileId = writeFile(responseList,rcdr);
        LogUtil.info(MODULE,"销售明细结束写入文件");
        OrdFileImport ordFile = new OrdFileImport();
        ordFile.setId(rcdr.getKey());
        ordFile.setFileName(fileId);
        ordFile.setShopId(100L);
        ordFile.setStatus("1");
        ordFile.setImportTime(DateUtil.getSysDate());
        ordFileImportSV.updateByIdSelective(ordFile);

    }

    protected String writeFile(List<RGoodSaleResponse> rGoodSaleResponseList,RGoodSaleRequest rcdr){
        String fileName = "Sale"+"("+ DateUtil.getDateString(rcdr.getBegDate(), DateUtil.YYYYMMDD)+"-"+DateUtil.getDateString(DateUtil.getOffsetDaysDate(rcdr.getEndDate(),-1), DateUtil.YYYYMMDD)+")";
        String fileType = "xls";
        String moduleName = "GoodSaleForOrdSub";
        String operator = String.valueOf(rcdr.getStaff().getId()) ;
        List<String> titles = new ArrayList<String>();
        titles.add("订单编号");
        titles.add("商品名称");
        titles.add("产品一级分类");
        titles.add("产品二级分类");
        titles.add("产品三级分类");
        titles.add("产品四级分类");
        titles.add("购买单价");
        titles.add("购买数量");
        titles.add("购买总金额");
        titles.add("支付方式");
        titles.add("是否活动商品");
        titles.add("购买日期");
        titles.add("会员名");
        titles.add("联系人");
        titles.add("联系电话");
        titles.add("联系地址");
        List<List<Object>> datas = new ArrayList<List<Object>>();
        for (RGoodSaleResponse shor : rGoodSaleResponseList) {
            List<Object> data = new ArrayList<Object>();
            String orderId = shor.getOrderId();
            data.add(shor.getOrderId()==null?"":shor.getOrderId());
            data.add(shor.getGdsName()==null?"":shor.getGdsName());
            data.add(shor.getCategory01()==null?"":shor.getCategory01());
            data.add(shor.getCategory02()==null?"":shor.getCategory02());
            data.add(shor.getCategory03()==null?"":shor.getCategory03());
            data.add(shor.getCategory04()==null?"":shor.getCategory04());
            data.add(shor.getDiscountPrice() == null ? 0l : OrderUtils.doubleDiv(shor.getDiscountPrice()));
            data.add(shor.getOrderAmount()==null?0l:shor.getOrderAmount());
            data.add(shor.getRealMoney() == null ? 0l : OrderUtils.doubleDiv(shor.getRealMoney()));
            if(shor.getPayType()==null){
            	data.add("");
            }else if(shor.getPayType().equals("0")){
            	data.add("线上支付");
            }else if(shor.getPayType().equals("1")){
            	data.add("上门支付");
            }else if(shor.getPayType().equals("2")){
            	data.add("邮局汇款");
            }else if(shor.getPayType().equals("3")){
            	data.add("银行转账");
            }else{
            	data.add("");
            }
            
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
        LogUtil.info(MODULE, "======================"+fileId);
        return fileId;
    }

}
