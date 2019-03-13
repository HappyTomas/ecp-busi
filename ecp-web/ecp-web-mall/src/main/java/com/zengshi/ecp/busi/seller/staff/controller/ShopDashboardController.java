package com.zengshi.ecp.busi.seller.staff.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.search.SearchUtil;
import com.zengshi.ecp.busi.search.vo.GoodSearchResult;
import com.zengshi.ecp.busi.seller.staff.vo.ReportItemStatisticsVO;
import com.zengshi.ecp.busi.seller.staff.vo.ReportItemStatisticsVO.DailyTrade;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.SellerReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.SellerResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffUtil;
import com.zengshi.ecp.sys.dubbo.dto.ReportItemReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.ReportItemRespDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IReportItemRSV;
import com.zengshi.ecp.system.filter.SellerLocaleUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 卖家店铺工作台<br>
 * Date:2016年5月9日下午3:09:37  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value="/seller/shopdashboard")
public class ShopDashboardController extends EcpBaseController {
	
	private static final String MODULE = ShopDashboardController.class.getName();
	
	@Resource
	private IShopInfoRSV shopInfoRSV; //店铺信息服务
	
	@Resource
	private IReportItemRSV reportItemRSV; 
	
	/**
	 * 
	 * index:(店铺工作台首页). <br/> 
	 * 
	 * @author linby
	 * @param model
	 * @return 
	 * @since JDK 1.7
	 */
	@RequestMapping(value="/index")
    public String index(Model model){
        return "/seller/staff/shopdashboard/index";
    }
	
	/**
	 * 
	 * myShops:(得到店铺列表). <br/> 
	 * 
	 * @author linby
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@RequestMapping(value="/myshops")
	@ResponseBody
	public List<ShopInfoResDTO> myShops() throws BusinessException {
		
		SellerResDTO sellerResDTO = SellerLocaleUtil.getSeller();
        SellerReqDTO reqDTO = new SellerReqDTO();
        ObjectCopyUtil.copyObjValue(sellerResDTO, reqDTO, null, false);
        List<ShopInfoResDTO> listShop = StaffUtil.getSellerShopList(reqDTO);
		for (ShopInfoResDTO shopInfoResDTO : listShop) {
			shopInfoResDTO.setLogoPathURL(ImageUtil.getImageUrl(shopInfoResDTO.getLogoPath()));
		}
		
		//TODO 没有店铺
		
		return listShop;
	}
	
	/**
	 * 
	 * shopHotSales:(热卖排行). <br/> 
	 * 
	 * @author linby
	 * @param shopId
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@RequestMapping(value="/shophotsales")
	@ResponseBody
	public List<Map<String, String>> shopHotSales(Long shopId) throws BusinessException {
		
		List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
		
		List<GoodSearchResult> list = SearchUtil.shopHotSales(5, shopId);//店铺下头5个商品
		if(CollectionUtils.isNotEmpty(list)){
			for (GoodSearchResult goodSearchResult : list) {
				String gdsUrl = GdsUtils.getGdsUrl(Long.valueOf(goodSearchResult.getId()),
						goodSearchResult.getFirstSkuId(), null);
				Map<String, String> map = new HashMap<String, String>();
				map.put("gdsName", goodSearchResult.getGdsName());
				map.put("gdsSubHead", goodSearchResult.getGdsSubHead());
				map.put("imageUrl", goodSearchResult.getImageUrl());
				map.put("gdsUrl", gdsUrl);
				
				listMap.add(map);
			}
			
		}
		
		return listMap;
	}
	
	/**
	 * 
	 * dailyTradeData:(交易分析). <br/> 
	 * 
	 * @author linby
	 * @param shopId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@RequestMapping(value="/dailyTradeData")
	@ResponseBody
	public String dailyTradeData(String shopId,String startDate,String endDate) throws BusinessException {
		
		String urlPart = "/service/dailyTradeData";//交易分析
		
		CmsSiteRespDTO anaSiteDto = null;
		Map<String,String> map = new HashMap<String,String>();
       // anaSiteDto = CmsCacheUtil.getCmsSiteCache(Long.valueOf("6"));//站点：用户分析
		BaseSysCfgRespDTO sysconfig =  SysCfgUtil.fetchSysCfg("XW_ANAL_SYS_URL");
        if(sysconfig == null || StringUtil.isBlank(sysconfig.getParaValue())){
        	map.put("serviceState", "2001");
        	map.put("serviceMsg", "站点未配置");
        	return JSON.toJSONString(map);
        }
		// 创建默认的httpClient实例
        CloseableHttpClient httpclient = HttpClients.createDefault(); 
        // 创建httppost 
        HttpPost httppost = new HttpPost(sysconfig.getParaValue()+urlPart);
        // 创建参数队列
        List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
        formparams.add(new BasicNameValuePair("shopId", shopId));//店铺id
        formparams.add(new BasicNameValuePair("format","json"));//
        java.sql.Date date = new java.sql.Date(new Date().getTime());//当前时间
        formparams.add(new BasicNameValuePair("startDate",startDate==null?DateUtil.getDateString(DateUtil.getOffsetDaysDate(date, -29), "yyyyMMdd"):null));//开始时间  29天前
        formparams.add(new BasicNameValuePair("endDate",endDate));//结束时间
        
		//发请求
        UrlEncodedFormEntity uefEntity; 
        try {  
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
            httppost.setEntity(uefEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {  
                if(response.getStatusLine() !=null && response.getStatusLine().getStatusCode()==200){//请求成功
                    HttpEntity entity = response.getEntity();  
                    if (entity != null && entity.getContent() !=null) {  
                        // 处理响应内容    
                        String strResult = EntityUtils.toString(entity);
                        return strResult;
                    }  
                }else{
                    throw new BusinessException(response.getStatusLine().toString());
                }
            } finally {  
                response.close();  
            }
        }catch (ClientProtocolException e) {
            LogUtil.error(MODULE, "远程服务协议异常", e);
            map.put("serviceState", "2001");
        	map.put("serviceMsg", "远程服务协议异常");
        }catch (IOException e) {  
            LogUtil.error(MODULE, "远程服务连接异常", e);
            map.put("serviceState", "2001");
        	map.put("serviceMsg", "远程服务协议异常");
        }catch (BusinessException e){
            LogUtil.error(MODULE, e.getMessage(), e);
            map.put("serviceState", "2001");
        	map.put("serviceMsg", e.getMessage());
        }catch(Exception e){
            LogUtil.error(MODULE, "调用远程交易分析服务未知异常", e);
            map.put("serviceState", "2001");
        	map.put("serviceMsg", "调用远程交易分析服务未知异常");
        }
        finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        } 
        return JSON.toJSONString(map);
	}
	
	/**
	 * 
	 * dailyTradeDataWrapped:(交易分析统一出口). <br/> 
	 * 默认为[当前时间]到[29天前]合计30天的区间查询
	 * 原则上时间参数都不能大于当前时间
	 * 
	 * @author linby
	 * @param shopId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws BusinessException
	 * @throws ParseException 
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/dailyTradeDataWrapped")
	@ResponseBody
	public String dailyTradeDataWrapped(String shopId,String startDate,String endDate)
			throws BusinessException, ParseException {
		ReportItemStatisticsVO itemStatisticsVO = new ReportItemStatisticsVO();
		
		// 查询数据元素
		ReportItemReqDTO reportItemReqDTO = new ReportItemReqDTO();
		reportItemReqDTO.setItemCode("ITEM_ORD_SUM_MONEY");// 订单额
		reportItemReqDTO.setItemSource(SysCfgUtil.fetchSysCfg("SYS_ADMIN_ITEM_SOURCE").getParaValue());
		reportItemReqDTO.setShopId(Long.valueOf(shopId));
		Timestamp sysEndDate = endDate==null?DateUtil.getSysDate():DateUtil.getTimestamp(endDate); //当前时间
		Timestamp sysStartDate = startDate==null?DateUtil.getOffsetDaysTime(sysEndDate, -29):DateUtil.getTimestamp(startDate); //29天前
		reportItemReqDTO.setCalDateStart(sysStartDate);//29天前
		reportItemReqDTO.setCalDateEnd(sysEndDate);
		//1.订单额
		List<ReportItemRespDTO> orderMoneyList = reportItemRSV.queryReportItemByCondition(reportItemReqDTO);

		reportItemReqDTO.setItemCode("ITEM_ORD_SALE_MONEY"); //销售额
		//2.销售额
		List<ReportItemRespDTO> saleMoneyList = reportItemRSV.queryReportItemByCondition(reportItemReqDTO);

		List<DailyTrade> tradeList = new ArrayList<DailyTrade>();//结果数据

		//得到时段区间每天的第一秒时间集合
		List<Timestamp> listTime = this.dailyTradeTimeZone(reportItemReqDTO.getCalDateStart(),reportItemReqDTO.getCalDateEnd());
		
		for (Timestamp dayTime : listTime) {
			DailyTrade dailyTrade = itemStatisticsVO.newDailyTradeInst(DateUtil.getDateString(dayTime, "yyyyMMdd"),
					"0", "0");//当天统计数据
			for (ReportItemRespDTO orderAmount : orderMoneyList) {
				if(DateUtil.getDateString(orderAmount.getCalDate(), "yyyyMMdd")
						.equals(DateUtil.getDateString(dayTime, "yyyyMMdd"))){
					
					dailyTrade.setOrderAmount(orderAmount.getItemValue());
					break;
				}
			}
			for(ReportItemRespDTO paidAmount : saleMoneyList) {
				if(DateUtil.getDateString(paidAmount.getCalDate(), "yyyyMMdd")
						.equals(DateUtil.getDateString(dayTime, "yyyyMMdd"))){
					
					dailyTrade.setPaidAmount(paidAmount.getItemValue());
					break;
				}
			}
			tradeList.add(dailyTrade);
		}
		
		itemStatisticsVO.setServiceMsg("success");
		itemStatisticsVO.setServiceState("0000");
		itemStatisticsVO.setItemCount(tradeList.size());
		itemStatisticsVO.setTradeList(tradeList);
		itemStatisticsVO.setShopId(Long.valueOf(shopId));
		return JSON.toJSONString(itemStatisticsVO);

	}
	
	/**
	 * 
	 * dailyTradeTimeZone:(得到时段区间每天的第一秒时间集合). <br/> 
	 * 
	 * @author linby
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	private List<Timestamp> dailyTradeTimeZone(Timestamp startTime, Timestamp endTime) throws BusinessException{
		List<Timestamp> listTimestamp = new ArrayList<Timestamp>();
		int counts = (int) ((endTime.getTime()-startTime.getTime())/(3600*24*1000));//区间相差天数
		counts++;//加一天
		Timestamp firstDayTime = DateUtil.getTheDayFirstSecond(startTime);//第一天
		for (int i = 0; i < counts; i++) {//准备时间数据
			listTimestamp.add(DateUtil.getOffsetDaysTime(firstDayTime, i));
		}
		
		return listTimestamp;
	}
	
	
}
