package com.zengshi.ecp.order.service.busi.workplat.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.order.dao.mapper.busi.OrdMainMapper;
import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dao.model.OrdMainCriteria;
import com.zengshi.ecp.order.dao.model.OrdMainCriteria.Criteria;
import com.zengshi.ecp.order.dubbo.dto.WOrderCountInfo;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.service.busi.workplat.interfaces.IOrderMainInfoSV;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public class OrderMainInfoSVImpl implements IOrderMainInfoSV{

    
    private final static String MODULE = OrderMainInfoSVImpl.class.getName();
    
    @Resource
    private OrdMainMapper orderMainMapper;
    
    @Override
    public WOrderCountInfo queryOrderFromDB(Long byShopId, Timestamp byStartTime, Timestamp byEndTime) {
        
        //方案1.
        //查索引表
        //根据索引订单id查订单信息
        
        //方案2.
        //查主表
        OrdMainCriteria criteria = new OrdMainCriteria();
        Criteria conditions =  criteria.createCriteria();
        if (StringUtil.isNotEmpty(byShopId)) {
            conditions.andShopIdEqualTo(byShopId);
        }
        if (StringUtil.isNotEmpty(byStartTime)&&StringUtil.isNotEmpty(byEndTime)) {
            conditions.andOrderTimeBetween(byStartTime, byEndTime);
        }
        List<OrdMain> results = null;
        try {
            results = orderMainMapper.selectByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return caclOrderCountInfoDB(results);
        
    }

    @Override
    public WOrderCountInfo queryOrderFromXW(Long byShopId, Timestamp byStartTime,
            Timestamp byEndTime) {
        
        WOrderCountInfo countInfo = new WOrderCountInfo();
        
        String urlPart = "/service/dailyTradeData";//交易分析
        //取行为分析系统的数据
        BaseSysCfgRespDTO sysconfig =  SysCfgUtil.fetchSysCfg("XW_ANAL_SYS_URL");
        if(sysconfig == null || StringUtil.isBlank(sysconfig.getParaValue())){
            LogUtil.error(MODULE, "行为分析系统URL未配置");
            return countInfo;
        }
        // 创建默认的httpClient实例
        CloseableHttpClient httpclient = HttpClients.createDefault(); 
        // 创建httppost 
        HttpPost httppost = new HttpPost(sysconfig.getParaValue()+urlPart);
        // 创建参数队列
        List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
        formparams.add(new BasicNameValuePair("shopId", String.valueOf(byShopId)));//店铺id
        formparams.add(new BasicNameValuePair("format","json"));//
        formparams.add(new BasicNameValuePair("startDate",DateUtil.getDateString(byStartTime, DateUtil.YYYYMMDD)));
        formparams.add(new BasicNameValuePair("endDate",DateUtil.getDateString(byEndTime, DateUtil.YYYYMMDD)));
        
        //发请求
        UrlEncodedFormEntity uefEntity; 
        String json = null;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
            httppost.setEntity(uefEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {  
                if(response.getStatusLine() !=null && response.getStatusLine().getStatusCode()==200){//请求成功
                    HttpEntity entity = response.getEntity();  
                    if (entity != null && entity.getContent() !=null) {  
                        // 处理响应内容    
                        json = EntityUtils.toString(entity);
                    }  
                }else{
                    throw new BusinessException(response.getStatusLine().toString());
                }
            } finally {  
                response.close();  
            }
        } catch (ClientProtocolException e) {
            LogUtil.error(MODULE, "远程服务协议异常", e);
        }catch (IOException e) {  
            LogUtil.error(MODULE, "远程服务连接异常", e);
        }catch (BusinessException e){
            LogUtil.error(MODULE, e.getMessage(), e);
        }catch(Exception e){
            LogUtil.error(MODULE, "调用远程交易分析服务未知异常", e);
        }
        finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        } 
        //解析数据
        if (StringUtil.isNotBlank(json)) {
            countInfo = caclOrderCountInfoXW(JSON.parseObject(json));
        }

        
        return countInfo;
    }
    private WOrderCountInfo caclOrderCountInfoDB(List<OrdMain> orderList)
    {
        WOrderCountInfo orderCountInfo = new WOrderCountInfo();
       
        //数据统计
        if (CollectionUtils.isNotEmpty(orderList)) {
            Long allOrderMoney = 0L;        //总订单额
            Long aLLOrderSellMoney = 0L;    //总销售额
            Long allOrderCount = 0L;        //总订单数
            Long allPayedCount = 0L;        //支付成功数量
            Double placePrecent = 0.0;      //支付成功率
            
            for(OrdMain record: orderList)
            {
                //如果订单已取消，过滤订单
                if (needFilt(record)) {
                    continue;
                }
                //订单计数
                ++allOrderCount;
                //如果支付成功，累计订单金额，与销售额
                if (payedSuccess(record)) {
                    //订单金额累计
                    if (record.getBasicMoney() != null) {
                        allOrderMoney += record.getBasicMoney();
                    }      
                    //销售额累计
                    if (record.getRealMoney() != null) {
                        aLLOrderSellMoney += record.getRealMoney();
                    }
                    
                    ++allPayedCount;
                }
            
            }
            placePrecent = (Double)(allPayedCount*1.00/allOrderCount);
            
            orderCountInfo.setAllOrderCount(allOrderCount);
            orderCountInfo.setAllOrderMoney(allOrderMoney);
            orderCountInfo.setAllPayedCount(allPayedCount);
            orderCountInfo.setAllOrderSellMoney(aLLOrderSellMoney);
            orderCountInfo.setPlacePrecent(placePrecent);
        }

        return orderCountInfo;
    }
    private WOrderCountInfo caclOrderCountInfoXW(JSONObject jsonObject)
    {
        WOrderCountInfo orderCountInfo = new WOrderCountInfo();
        /*
        {"serviceMsg":"正常",
            "shopId":"100",
            "tradeList":[{
                "dataDate":"20160515",
                "orderAmount":"347410",
                "paidAmount":"292510",
                "orderCount":22,
                "payCount":20,
                "ordSuccRate":"2.96"}],
            "itemCount":1,
            "serviceState":"0000"}
         */
        
        
        Long allOrderMoney = 0L;        //总订单额
        Long aLLOrderSellMoney = 0L;    //总销售额
        Long allOrderCount = 0L;        //总订单数
        Long allPayedCount = 0L;        //支付成功数量
        Double placePrecent = 0.0;      //支付成功率
        
        JSONArray jsonArray = jsonObject.getJSONArray("tradeList");
        
        for(int i=0; i<jsonArray.size(); i++)
        {
            WOrderCountInfo rowcountinfo = new WOrderCountInfo();
            
            JSONObject rowinfo = jsonArray.getJSONObject(i);
            rowcountinfo.setAllOrderMoney(rowinfo.getLong("orderAmount"));
            rowcountinfo.setAllOrderSellMoney(rowinfo.getLong("paidAmount"));
            rowcountinfo.setAllOrderCount(rowinfo.getLong("orderCount"));
            rowcountinfo.setAllPayedCount(rowinfo.getLong("payCount"));
            rowcountinfo.setPlacePrecent(rowinfo.getDouble("ordSuccRate"));
            
            orderCountInfo.addCountInfo(rowcountinfo);
        }
        
        return orderCountInfo;
    }
    private boolean needFilt(OrdMain record)
    {
        //订单取消
        if (OrdConstants.OrderStatus.ORDER_STATUS_CANCLE.equals(record.getStatus())) {
            return true;
        }
        return false;
    }
    private boolean payedSuccess(OrdMain record)
    {
        //如果已支付
        if (OrdConstants.Order.ORDER_PAY_FLAG_1.equals(record.getPayFlag())) {
            return true;
        }
        return false;
    }
    private boolean endTimeInToday(Timestamp checkTime)
    {
        if (DateUtil.getTimeDifference(checkTime, DateUtil.getSysDate()) == 0) {
            return true;
        }
        return false;
    }

}

