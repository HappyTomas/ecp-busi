package com.zengshi.ecp.busi.sys.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoPageRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.net.ntp.TimeStamp;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.prom.myprom.vo.PromQueryVO;
import com.zengshi.ecp.busi.sys.vo.LogoImageVO;
import com.zengshi.ecp.busi.sys.vo.ReportItemRateVO;
import com.zengshi.ecp.busi.sys.vo.ReportItemStatisticsVO;
import com.zengshi.ecp.busi.sys.vo.ReportItemVO;
import com.zengshi.ecp.busi.sys.vo.ReportTupleItemVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsVerifyReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.order.dubbo.dto.REntityInputRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrderWorkPlatRSV;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.interfaces.ICompanyCheckRSV;
import com.zengshi.ecp.sys.dubbo.dto.ReportItemReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.ReportItemRespDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IReportItemRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping(value = "/reportitem")
public class ReportItemController extends EcpBaseController {

	private static final String MODULE = ReportItemController.class.getName();
	@Resource
	private IGdsInfoQueryRSV gdsInfoQueryRSV;
	@Resource
	private IGdsEvalRSV gdsEvalRSV;
	@Resource
	private IPromQueryRSV promQueryRSV;
	@Resource
	private ICompanyCheckRSV companyCheckRSV;
	@Resource
	private IReportItemRSV reportItemRSV;
	@Resource
	private IOrderWorkPlatRSV orderWorkPlatRSV;

	@Resource(name = "gdsStockRSV")
	private IGdsStockRSV gdsStockRSV;

	// 获取紧俏商品数量
	@RequestMapping("/hardtogetgdscount")
	@ResponseBody
	public Long getHardToGetGdsCount(ReportItemVO reportItemVO) throws Exception {
		Long count = 0l;
		StockInfoReqDTO stockInfoDTO=new StockInfoReqDTO();
		stockInfoDTO.setStockStatus(GdsConstants.GdsStock.STOCK_STATUS_HARDTOGET);
		count=gdsStockRSV.countSkus(stockInfoDTO);
		return count;
	}

	// 获取缺货商品数量
	@RequestMapping("/lackgdscount")
	@ResponseBody
	public Long getLackGdsCount(ReportItemVO reportItemVO) throws Exception {
		Long count = 0l;
		StockInfoReqDTO stockInfoDTO=new StockInfoReqDTO();
		stockInfoDTO.setStockStatus(GdsConstants.GdsStock.STOCK_STATUS_LACK);
		count=gdsStockRSV.countSkus(stockInfoDTO);
		return count;
	}

	// 获取待审核商品数量
	@RequestMapping("/verifygdscount")
	@ResponseBody
	public Long getVerifyGdsCount(ReportItemVO reportItemVO) throws Exception {
		Long count = 0l;
		GdsVerifyReqDTO gdsVerifyReqDTO = new GdsVerifyReqDTO();
		gdsVerifyReqDTO.setVerifyStatus(GdsConstants.GdsVerify.WAITE_VERIFY);
		// gdsVerifyReqDTO.setShopId(reportItemVO.getShopId());
		count = gdsInfoQueryRSV.queryGdsVerifyInfoCount(gdsVerifyReqDTO);
		return count;
	}

	// 获取待审核评价数量
	@RequestMapping("/auditevalcount")
	@ResponseBody
	public Long getVerifyEvalCount(ReportItemVO reportItemVO) throws Exception {
		Long count = 0l;
		GdsEvalReqDTO dto = new GdsEvalReqDTO();
		dto.setAuditStatus(GdsConstants.Commons.STATUS_PENDING_AUDIT);
		// dto.setShopId(reportItemVO.getShopId());
		count = gdsEvalRSV.statisticEvalByPass(dto);
		return count;
	}

	// 获取待审核入驻
	@RequestMapping("/auditshopcount")
	@ResponseBody
	public Long getAuditShopCount(ReportItemVO reportItemVO) throws Exception {
		Long count = 0l;

		// dto.setShopId(reportItemVO.getShopId());
		count = companyCheckRSV.getCheckCompanyCount();
		return count;
	}
	// 获取待审核促销总数
	// @RequestMapping("/auditpromcount")
	// @ResponseBody
	// public Long getAuditPromCount(ReportItemVO reportItemVO) throws Exception
	// {
	// Long count = 0l;
	// GdsEvalReqDTO dto = new GdsEvalReqDTO();
	// dto.setAuditStatus(reportItemVO.getAuditStatus());
	//// dto.setShopId(reportItemVO.getShopId());
	// count = gdsEvalRSV.statisticEvalByPass(dto);
	// return count;
	// }

	// 获取待审核主题促销总数
	@RequestMapping("/auditthemepromcount")
	@ResponseBody
	public Long getAuditThemePromCount(ReportItemVO reportItemVO) throws Exception {
		Long count = 0l;
		PromInfoDTO promInfoDTO = new PromInfoDTO();
		count = promQueryRSV.queryTotalByProm(promInfoDTO);
		return count;
	}

	// 获取待发货订单总数
	@RequestMapping("/waitdeliordercount")
	@ResponseBody
	public Long getWaitDeliveryOrderCount(ReportItemVO reportItemVO) throws Exception {
		Long count = 0l;
		count = orderWorkPlatRSV.catchPlatDeliverCount();
		return count;
	}

	// 待处理退款订单总数
	@RequestMapping("/waitretMordercount")
	@ResponseBody
	public Long getWaitRetMoneyOrderCount(ReportItemVO reportItemVO) throws Exception {
		Long count = 0l;

		count = orderWorkPlatRSV.catchPlatBackMoneyOrderCount();
		return count;
	}

	// 待处理退货订单总数
	@RequestMapping("/waitretordercount")
	@ResponseBody
	public Long getWaitReturnOrderCount(ReportItemVO reportItemVO) throws Exception {
		Long count = 0l;
		count = orderWorkPlatRSV.catchPlatBackGdsOrderCount();
		return count;
	}

	// 待审核线下支付订单总数
	@RequestMapping("/waitverifyordercount")
	@ResponseBody
	public Long getWaitVerifyOrderCount(ReportItemVO reportItemVO) throws Exception {
		Long count = 0l;
		count = orderWorkPlatRSV.catchPlatOfflineCheckCount();

		return count;
	}

	// 昨日订单额
	@RequestMapping("/yestordermoney")
	@ResponseBody
	public Long getYestOrderMoney(ReportItemVO reportItemVO) throws Exception {
		Long count = 0l;
		ReportItemReqDTO reportItemReqDTO = new ReportItemReqDTO();
		reportItemReqDTO.setItemCode("ITEM_ORD_SUM_MONEY");

		reportItemReqDTO.setItemSource(SysCfgUtil.fetchSysCfg("SYS_ADMIN_ITEM_SOURCE").getParaValue());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		Date date = calendar.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		date = simpleDateFormat.parse(simpleDateFormat.format(date));
		Timestamp timestamp = new Timestamp(date.getTime());
		reportItemReqDTO.setCalDate(timestamp);
		reportItemReqDTO.setShopId(-1l);
		List<ReportItemRespDTO> itemRespDTOs = reportItemRSV.queryReportItemByCondition(reportItemReqDTO);
		if (CollectionUtils.isNotEmpty(itemRespDTOs)) {

			ReportItemRespDTO itemRespDTO = itemRespDTOs.get(0);
			return Long.parseLong(itemRespDTO.getItemValue());
		}
		return count;
	}

	// 昨日销售额
	@RequestMapping("/yestsellmoney")
	@ResponseBody
	public Long getYestSellMoney(ReportItemVO reportItemVO) throws Exception {
		Long count = 0l;
		ReportItemReqDTO reportItemReqDTO = new ReportItemReqDTO();
		reportItemReqDTO.setItemCode("ITEM_ORD_SALE_MONEY");

		reportItemReqDTO.setItemSource(SysCfgUtil.fetchSysCfg("SYS_ADMIN_ITEM_SOURCE").getParaValue());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		Date date = calendar.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		date = simpleDateFormat.parse(simpleDateFormat.format(date));
		Timestamp timestamp = new Timestamp(date.getTime());
		reportItemReqDTO.setCalDate(timestamp);
		reportItemReqDTO.setShopId(-1l);
		List<ReportItemRespDTO> itemRespDTOs = reportItemRSV.queryReportItemByCondition(reportItemReqDTO);
		if (CollectionUtils.isNotEmpty(itemRespDTOs)) {

			ReportItemRespDTO itemRespDTO = itemRespDTOs.get(0);
			return Long.parseLong(itemRespDTO.getItemValue());
		}
		return count;
	}

	// 昨日订单总量
	@RequestMapping("/yestordercount")
	@ResponseBody
	public Long getYestOrderCount(ReportItemVO reportItemVO) throws Exception {
		Long count = 0l;
		ReportItemReqDTO reportItemReqDTO = new ReportItemReqDTO();
		reportItemReqDTO.setItemCode("ITEM_ORD_SUM_COUNT");

		reportItemReqDTO.setItemSource(SysCfgUtil.fetchSysCfg("SYS_ADMIN_ITEM_SOURCE").getParaValue());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		Date date = calendar.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		date = simpleDateFormat.parse(simpleDateFormat.format(date));
		Timestamp timestamp = new Timestamp(date.getTime());
		reportItemReqDTO.setCalDate(timestamp);
		reportItemReqDTO.setShopId(-1l);
		List<ReportItemRespDTO> itemRespDTOs = reportItemRSV.queryReportItemByCondition(reportItemReqDTO);
		if (CollectionUtils.isNotEmpty(itemRespDTOs)) {

			ReportItemRespDTO itemRespDTO = itemRespDTOs.get(0);
			return Long.parseLong(itemRespDTO.getItemValue());
		}
		return count;
	}

	// 昨日支付成功订单总数
	@RequestMapping("/yestpayedordercount")
	@ResponseBody
	public Long getYestPayedOrderCount(ReportItemVO reportItemVO) throws Exception {
		Long count = 0l;
		ReportItemReqDTO reportItemReqDTO = new ReportItemReqDTO();
		reportItemReqDTO.setItemCode("ITEM_ORD_PAY_COUNT");

		reportItemReqDTO.setItemSource(SysCfgUtil.fetchSysCfg("SYS_ADMIN_ITEM_SOURCE").getParaValue());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		Date date = calendar.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		date = simpleDateFormat.parse(simpleDateFormat.format(date));
		Timestamp timestamp = new Timestamp(date.getTime());
		reportItemReqDTO.setCalDate(timestamp);
		reportItemReqDTO.setShopId(-1l);
		List<ReportItemRespDTO> itemRespDTOs = reportItemRSV.queryReportItemByCondition(reportItemReqDTO);
		if (CollectionUtils.isNotEmpty(itemRespDTOs)) {

			ReportItemRespDTO itemRespDTO = itemRespDTOs.get(0);
			return Long.parseLong(itemRespDTO.getItemValue());
		}
		return count;
	}

	// 昨日下单成功率
	@RequestMapping("/yestsucceeorderrate")
	@ResponseBody
	public ReportItemRateVO getYestSuccessOrderRate(ReportItemVO reportItemVO) throws Exception {
		String count = "0.00%";
		ReportItemReqDTO reportItemReqDTO = new ReportItemReqDTO();
		reportItemReqDTO.setItemCode("ITEM_ORD_SUCESS_RATE");

		reportItemReqDTO.setItemSource(SysCfgUtil.fetchSysCfg("SYS_ADMIN_ITEM_SOURCE").getParaValue());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		Date date = calendar.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		date = simpleDateFormat.parse(simpleDateFormat.format(date));
		Timestamp timestamp = new Timestamp(date.getTime());
		reportItemReqDTO.setCalDate(timestamp);
		reportItemReqDTO.setShopId(-1l);
		List<ReportItemRespDTO> itemRespDTOs = reportItemRSV.queryReportItemByCondition(reportItemReqDTO);
		ReportItemRateVO itemRateVO = new ReportItemRateVO();
		if (CollectionUtils.isNotEmpty(itemRespDTOs)) {

			ReportItemRespDTO itemRespDTO = itemRespDTOs.get(0);
			Double result = Double.parseDouble(itemRespDTO.getItemValue());
			DecimalFormat df = new DecimalFormat("0.00%");
		    String r = df.format(result);
			itemRateVO.setRate(r);
			return itemRateVO;
		}
		itemRateVO.setRate(count);
		return itemRateVO;
	}

	// 昨日注册会员总数
	@RequestMapping("/yestregisterstaffcount")
	@ResponseBody
	public Long getYestRegisterStaffCount(ReportItemVO reportItemVO) throws Exception {
		Long count = 0l;
		ReportItemReqDTO reportItemReqDTO = new ReportItemReqDTO();
		reportItemReqDTO.setItemCode("ITEM_ORD_NEW_MEMBER");

		reportItemReqDTO.setItemSource(SysCfgUtil.fetchSysCfg("SYS_ADMIN_ITEM_SOURCE").getParaValue());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		Date date = calendar.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		date = simpleDateFormat.parse(simpleDateFormat.format(date));
		Timestamp timestamp = new Timestamp(date.getTime());
		reportItemReqDTO.setCalDate(timestamp);
		reportItemReqDTO.setShopId(-1l);
		List<ReportItemRespDTO> itemRespDTOs = reportItemRSV.queryReportItemByCondition(reportItemReqDTO);
		if (CollectionUtils.isNotEmpty(itemRespDTOs)) {

			ReportItemRespDTO itemRespDTO = itemRespDTOs.get(0);
			return Long.parseLong(itemRespDTO.getItemValue());
		}
		return count;
	}

	// 昨日注册会员总数
	@RequestMapping("/managelogo")
	@ResponseBody
	public LogoImageVO getManageLogo(ReportItemVO reportItemVO) throws Exception {
		String logoSerial = SysCfgUtil.fetchSysCfg("MANAGE_LOGO_FILE_SERIAL").getParaValue();
		String imageUrl = ImageUtil.getImageUrl(logoSerial);
		LogoImageVO imageVO = new LogoImageVO();
		imageVO.setImageUrl(imageUrl);
		return imageVO;
	}
	
	// 昨日注册会员总数
	@RequestMapping("/saleAnalyze")
	@ResponseBody
	public LogoImageVO getSaleAnalyze(ReportItemVO reportItemVO) throws Exception {
		String saleAnalyzeUrl = CmsCacheUtil.getCmsSiteCache(6l).getSiteUrl();

		LogoImageVO imageVO = new LogoImageVO();
		imageVO.setImageUrl(saleAnalyzeUrl);
		return imageVO;
	}
	
	/**
	 * 
	 * saleTradeData:(销售趋势). <br/>
	 * 
	 * @author zhangjh6
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws BusinessException
	 * @throws ParseException
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/saleTradeData")
	@ResponseBody
	public ReportItemStatisticsVO saleTradeData(String startDate, String endDate)
			throws BusinessException, ParseException {
		ReportItemStatisticsVO itemStatisticsVO = new ReportItemStatisticsVO();
		// 查询数据元素
		// 订单额
		ReportItemReqDTO reportItemReqDTO = new ReportItemReqDTO();
		reportItemReqDTO.setItemCode("ITEM_ORD_SUM_MONEY");
		reportItemReqDTO.setItemSource(SysCfgUtil.fetchSysCfg("SYS_ADMIN_ITEM_SOURCE").getParaValue());
		reportItemReqDTO.setShopId(-1L);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.WEEK_OF_MONTH, -1);
		Date date = calendar.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		date = simpleDateFormat.parse(simpleDateFormat.format(date));
		Timestamp timestamp = new Timestamp(date.getTime());
		reportItemReqDTO.setCalDateStart(timestamp);
		reportItemReqDTO
				.setCalDateEnd(new Timestamp(simpleDateFormat.parse(simpleDateFormat.format(new Date())).getTime()));
		List<ReportItemRespDTO> orderMoneyList = reportItemRSV.queryReportItemByCondition(reportItemReqDTO);

		// 销售额
		reportItemReqDTO.setItemCode("ITEM_ORD_SALE_MONEY");
		List<ReportItemRespDTO> saleMoneyList = reportItemRSV.queryReportItemByCondition(reportItemReqDTO);

		List<ReportTupleItemVO> tupleItemVOs = new ArrayList<ReportTupleItemVO>();

		for (ReportItemRespDTO orderItemRespDTO : orderMoneyList) {
			ReportTupleItemVO reportTupleItemVO = new ReportTupleItemVO();
			// Date calDate = new Date();
			reportTupleItemVO.setDataDate(DateUtil.getDateString(orderItemRespDTO.getCalDate(), "yyyy-MM-dd"));

			if (orderItemRespDTO == null) {
				reportTupleItemVO.setCompareItem1(0l);
			} else {
				try {
					reportTupleItemVO.setCompareItem1(Long.parseLong(orderItemRespDTO.getItemValue()));

				} catch (Exception e) {
					reportTupleItemVO.setCompareItem1(0l);

				}

			}
			for (ReportItemRespDTO saleItemRespDTO : saleMoneyList) {

				if (saleItemRespDTO.getCalDate() != null
						&& (saleItemRespDTO.getCalDate().equals(orderItemRespDTO.getCalDate()))) {

					try {
						reportTupleItemVO.setCompareItem2(Long.parseLong(saleItemRespDTO.getItemValue()));

					} catch (Exception e) {
						reportTupleItemVO.setCompareItem2(0l);

					}

				} else {

					continue;
				}

			}

			tupleItemVOs.add(reportTupleItemVO);
		}
		itemStatisticsVO.setServiceMsg("success");
		itemStatisticsVO.setItemCount(tupleItemVOs.size());
		itemStatisticsVO.setReportItemRespDTOs(tupleItemVOs);
		itemStatisticsVO.setServiceState("0000");
		return itemStatisticsVO;

	}

	/**
	 * 
	 * orderTradeData:(订单趋势). <br/>
	 * 
	 * @author zhangjh6
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws BusinessException
	 * @throws ParseException
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/orderTradeData")
	@ResponseBody
	public ReportItemStatisticsVO orderTradeData(String startDate, String endDate)
			throws BusinessException, ParseException {
		ReportItemStatisticsVO itemStatisticsVO = new ReportItemStatisticsVO();
		// 查询数据元素
		// 订单总量
		ReportItemReqDTO reportItemReqDTO = new ReportItemReqDTO();
		reportItemReqDTO.setItemCode("ITEM_ORD_SUM_COUNT");
		reportItemReqDTO.setItemSource(SysCfgUtil.fetchSysCfg("SYS_ADMIN_ITEM_SOURCE").getParaValue());
		reportItemReqDTO.setShopId(-1L);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.WEEK_OF_MONTH, -1);
		Date date = calendar.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		date = simpleDateFormat.parse(simpleDateFormat.format(date));
		Timestamp timestamp = new Timestamp(date.getTime());
		reportItemReqDTO.setCalDateStart(timestamp);
		reportItemReqDTO
				.setCalDateEnd(new Timestamp(simpleDateFormat.parse(simpleDateFormat.format(new Date())).getTime()));
		List<ReportItemRespDTO> orderMoneyList = reportItemRSV.queryReportItemByCondition(reportItemReqDTO);

		// 支付订单总量
		reportItemReqDTO.setItemCode("ITEM_ORD_PAY_COUNT");
		List<ReportItemRespDTO> saleMoneyList = reportItemRSV.queryReportItemByCondition(reportItemReqDTO);

		List<ReportTupleItemVO> tupleItemVOs = new ArrayList<ReportTupleItemVO>();

		for (ReportItemRespDTO orderItemRespDTO : orderMoneyList) {
			ReportTupleItemVO reportTupleItemVO = new ReportTupleItemVO();
			// Date calDate = new Date();
			reportTupleItemVO.setDataDate(DateUtil.getDateString(orderItemRespDTO.getCalDate(), "yyyy-MM-dd"));

			if (orderItemRespDTO == null) {
				reportTupleItemVO.setCompareItem1(0l);
			} else {
				try {
					reportTupleItemVO.setCompareItem1(Long.parseLong(orderItemRespDTO.getItemValue()));

				} catch (Exception e) {
					reportTupleItemVO.setCompareItem1(0l);

				}

			}
			for (ReportItemRespDTO saleItemRespDTO : saleMoneyList) {

				if (saleItemRespDTO.getCalDate() != null
						&& (saleItemRespDTO.getCalDate().equals(orderItemRespDTO.getCalDate()))) {

					try {
						reportTupleItemVO.setCompareItem2(Long.parseLong(saleItemRespDTO.getItemValue()));

					} catch (Exception e) {
						reportTupleItemVO.setCompareItem2(0l);

					}

				} else {

					continue;
				}

			}

			tupleItemVOs.add(reportTupleItemVO);
		}
		itemStatisticsVO.setServiceMsg("success");
		itemStatisticsVO.setItemCount(tupleItemVOs.size());
		itemStatisticsVO.setReportItemRespDTOs(tupleItemVOs);
		itemStatisticsVO.setServiceState("0000");
		return itemStatisticsVO;

	}

}
