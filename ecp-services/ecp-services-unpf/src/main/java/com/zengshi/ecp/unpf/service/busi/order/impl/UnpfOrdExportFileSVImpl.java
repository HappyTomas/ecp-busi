package com.zengshi.ecp.unpf.service.busi.order.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dubbo.constants.GdsOption;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsSku2PropPropIdxReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.order.dubbo.dto.OrdFileImportDTO;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdFileImportRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.util.DataInoutUtil;
import com.zengshi.ecp.unpf.dao.model.UnpfOrdMain;
import com.zengshi.ecp.unpf.dubbo.dto.RExportExcleResponse;
import com.zengshi.ecp.unpf.dubbo.dto.RUnpfOrderDetailsResponse;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdDeliveryBatchResp;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdMainReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdMainResp;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdSubResp;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfQueryOrderReq;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfOrdDeliverBatchSV;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfOrdExportFileSV;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfOrdMainSV;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfOrdSubSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.MoneyUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;

public class UnpfOrdExportFileSVImpl implements IUnpfOrdExportFileSV {

	private static final String MODULE = UnpfOrdExportFileSVImpl.class.getName();

	@Resource
	private IUnpfOrdMainSV unpfOrdMainSV;

	@Resource
	private IOrdFileImportRSV ordFileImportRSV;

	@Resource
	private IUnpfOrdDeliverBatchSV iUnpfOrdDeliverBatchSV;

	@Resource
	private IUnpfOrdSubSV iUnpfOrdSubSV;
	
    @Resource
    private IGdsInfoQueryRSV gdsInfoQueryRSV;
    
    @Resource
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;


	@Override
	public RExportExcleResponse queryOrder2Excle(RUnpfQueryOrderReq rUnpfQueryOrderReq) throws BusinessException {
		LogUtil.info(MODULE, "=====导出订单入参=======" + JSON.toJSONString(rUnpfQueryOrderReq));
		Long oper = rUnpfQueryOrderReq.getStaff().getId();
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
		List<RUnpfOrderDetailsResponse> ruodrs = queryOrderExport(rUnpfQueryOrderReq);
		for (RUnpfOrderDetailsResponse ruodr : ruodrs) {
			List<Object> data = new ArrayList<Object>();
			data.add(ruodr.getUnpfOrdMainResp().getOuterId());
			data.add("");
			data.add("");
			data.add("");
			data.add("");
			data.add("");
			data.add("");
			data.add("");
			data.add("");
			//
			String contractName = ruodr.getUnpfOrdMainResp().getContractName();
			data.add(StringUtil.isBlank(contractName) ? "" : contractName);
			String contactPhone = ruodr.getUnpfOrdMainResp().getContractTel();
			data.add(StringUtil.isBlank(contactPhone) ? "" : contactPhone);
			String contactProvince = ruodr.getUnpfOrdMainResp().getContractProvince();
			data.add(StringUtil.isBlank(contactProvince) ? "" : contactProvince);
			String chnlAddress = ruodr.getUnpfOrdMainResp().getContractAddr();
			data.add(StringUtil.isBlank(chnlAddress) ? "" : chnlAddress);
			String contractZipcode = ruodr.getUnpfOrdMainResp().getContractZipcode();
			data.add(StringUtil.isBlank(contractZipcode) ? "" : contractZipcode);
			long expressFee = ruodr.getUnpfOrdMainResp().getRealExpressFee();
			data.add(Double.parseDouble(MoneyUtil.convertCentToYuan(expressFee)));
			data.add("");
			data.add(BaseParamUtil.fetchParamValue("STAFF_SHOP_DISTRIBUTION_WAY",
					ruodr.getUnpfOrdMainResp().getDispatchType()));
			if (ruodr.getUnpfOrdDeliveryBatchResp()!=null&&ruodr.getUnpfOrdDeliveryBatchResp().size() > 0) {
				RUnpfOrdDeliveryBatchResp delivery = ruodr.getUnpfOrdDeliveryBatchResp().get(0);
				String expressNo = delivery.getExpressNo();
				String expressName = delivery.getExpressName();
				expressName = (StringUtil.isBlank(expressName) ? "" : expressName + " ");
				data.add(StringUtil.isBlank(expressNo) ? "" : expressName + expressNo);
			} else {
				data.add("");
			}

			String orderId = ruodr.getUnpfOrdMainResp().getId();
			data.add(ruodr.getUnpfOrdMainResp().getId().substring(orderId.length() - 4));
			long realMoney = ruodr.getUnpfOrdMainResp().getRealMoney();
			data.add(Double.parseDouble(MoneyUtil.convertCentToYuan(realMoney)));
			data.add("");
			Timestamp payTime = ruodr.getUnpfOrdMainResp().getPayTime();
			String dateTime = "";
			if (payTime != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				dateTime = dateFormat.format(new Date(payTime.getTime()));
			}
			data.add(dateTime);
			data.add("");
			data.add("");
			datas.add(data);
		}
		String fileId = DataInoutUtil.exportExcel(datas, titles, "Order", fileType, moduleName, operator);
		LogUtil.info(MODULE, "======================" + fileId);
		RExportExcleResponse rer = new RExportExcleResponse();
		rer.setFileId(fileId);
		return rer;
	}

	private List<RUnpfOrderDetailsResponse> queryOrderExport(RUnpfQueryOrderReq rUnpfQueryOrderReq) {
		List<UnpfOrdMain> unpfOrdMain = this.unpfOrdMainSV.queryUnpfOrdMainByThread(rUnpfQueryOrderReq);
		LogUtil.info(MODULE, "订单导出sql查询结束：" + unpfOrdMain.size());
		List<RUnpfOrderDetailsResponse> rUnpfOrderDetailsResponseList = new ArrayList<>();
		if (CollectionUtils.isEmpty(unpfOrdMain)) {
			return rUnpfOrderDetailsResponseList;
		}
		LogUtil.info(MODULE, "销售明细导出补全数据结束");
		rUnpfOrderDetailsResponseList = queryUnpfOrderExportInfoByThread(unpfOrdMain, rUnpfQueryOrderReq);
		return rUnpfOrderDetailsResponseList;
	}

	private List<RUnpfOrderDetailsResponse> queryUnpfOrderExportInfoByThread(List<UnpfOrdMain> unpfOrdMain,
			RUnpfQueryOrderReq rUnpfQueryOrderReq) {
		List<RUnpfOrderDetailsResponse> saleResponses = new ArrayList<>();
		ThreadPoolExecutor executor = new ThreadPoolExecutor(16, 32, 5, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(64));
		Map<Integer, List<RUnpfOrderDetailsResponse>> ordDetailsMap = new HashMap<Integer, List<RUnpfOrderDetailsResponse>>();
		int group = 60; // 查数据点40%，剩余占用60%,分成60个线程
		int groupNum = unpfOrdMain.size() / group;
		int lastNum = unpfOrdMain.size() % group;
		if (unpfOrdMain.size() <= 60) {
			group = unpfOrdMain.size();
			groupNum = 1;
		} else {
			group += 1;
		}
		List<Future> fus = new ArrayList<Future>();
		for (int i = 0; i < group; i++) {
			List<UnpfOrdMain> obs = null;
			if (unpfOrdMain.size() <= 60) {
				obs = new ArrayList<>();
				obs.add(unpfOrdMain.get(i));
			} else {
				if (i != group - 1) {
					obs = unpfOrdMain.subList(i * groupNum, (i + 1) * groupNum);
				} else {
					obs = unpfOrdMain.subList(i * groupNum, i * groupNum + lastNum);
				}
			}
			Future f = executor.submit(new UnpfOrdExportFileSVImpl.DealUnpfOrdInfoThread(obs,
					rUnpfQueryOrderReq.getExportKey(), i, ordDetailsMap));
			fus.add(f);
		}

		for (Future f : fus) {
			try {
				f.get();
			} catch (InterruptedException e) {
				LogUtil.info(MODULE, "销售明细补全信息线程查询异常", e);
			} catch (ExecutionException e) {
				LogUtil.info(MODULE, "销售明细补全信息线程查询异常", e);
			}
		}
		executor.shutdown();
		for (List<RUnpfOrderDetailsResponse> v : ordDetailsMap.values()) {
			LogUtil.info(MODULE, "补全信息子列表数量：" + v.size());
			saleResponses.addAll(v);
		}

		return saleResponses;
	}

	public class DealUnpfOrdInfoThread extends Thread {

		private List<UnpfOrdMain> unpfOrdMains;

		private Long key;

		private int group;

		private Map<Integer, List<RUnpfOrderDetailsResponse>> unpfOrdDetailsMap;

		public DealUnpfOrdInfoThread(List<UnpfOrdMain> unpfOrdMains, Long key, int group,
				Map<Integer, List<RUnpfOrderDetailsResponse>> unpfOrdDetailsMap) {
			this.unpfOrdMains = unpfOrdMains;
			this.key = key;
			this.group = group;
			this.unpfOrdDetailsMap = unpfOrdDetailsMap;
		}

		@Override
		public void run() {
			LogUtil.info(MODULE, "销售明细补全线程开始处理");
			List<RUnpfOrderDetailsResponse> rOrderDetailsResponseList = new ArrayList<>();
			for (UnpfOrdMain ordMain : unpfOrdMains) {
				RUnpfOrderDetailsResponse rUnpfOrderDetailsResponse = new RUnpfOrderDetailsResponse();
				RUnpfOrdMainResp rUnpfOrdMainResp = new RUnpfOrdMainResp();
				RUnpfOrdMainReq rUnpfOrdMainReq = new RUnpfOrdMainReq();
				rUnpfOrdMainReq.setOuterId(ordMain.getOuterId());
				rUnpfOrdMainReq.setId(ordMain.getId());
				rUnpfOrdMainReq.setPlatType(ordMain.getPlatType());
				List<RUnpfOrdDeliveryBatchResp> ruodbList = iUnpfOrdDeliverBatchSV
						.queryUnpfOrdDeliveryBatch(rUnpfOrdMainReq);
				ObjectCopyUtil.copyObjValue(ordMain, rUnpfOrdMainResp, "", false);
				rUnpfOrderDetailsResponse.setUnpfOrdDeliveryBatchResp(ruodbList);
				rUnpfOrderDetailsResponse.setUnpfOrdMainResp(rUnpfOrdMainResp);
				rOrderDetailsResponseList.add(rUnpfOrderDetailsResponse);
			}
			unpfOrdDetailsMap.put(group, rOrderDetailsResponseList);
			OrdFileImportDTO ordFile = new OrdFileImportDTO();
			ordFile.setId(key);
			ordFile.setShopId(1L); // 文件进度
			ordFileImportRSV.updateById(ordFile);
			LogUtil.info(MODULE, "销售明细补全线程结束处理");
		}
	}

	@Override
	public RExportExcleResponse queryOrderBarCode(RUnpfQueryOrderReq rUnpfQueryOrderReq) throws BusinessException {

		RExportExcleResponse response = new RExportExcleResponse();
		Long oper = rUnpfQueryOrderReq.getStaff().getId();
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

		LogUtil.info(MODULE, "=====导出订单条码入参=======" + JSON.toJSONString(rUnpfQueryOrderReq));
		List<List<Object>> datas = new ArrayList<List<Object>>();
		List<RUnpfOrdSubResp> ruosrs = queryOrdBarCodeInfoNew(rUnpfQueryOrderReq);
		Long serial = 0l;
		for (RUnpfOrdSubResp ruosr : ruosrs) {
			List<Object> data = new ArrayList<Object>();
			data.add(ruosr.getOuterId());
			serial++;
			data.add(serial);
			data.add(ruosr.getTxCode());
			data.add(ruosr.getOrderAmount());
			
			BigDecimal orderPrice = StringUtil.isEmpty(ruosr.getOrderPrice())?null:new BigDecimal(ruosr.getOrderPrice()).divide(new BigDecimal(100));
			data.add(orderPrice);
		   //天猫计算用order_money，有赞用real_money
			if("taobao".equals(ruosr.getPlatType().trim())){
				BigDecimal orderMoney = StringUtil.isEmpty(ruosr.getOrderMoney())?null:new BigDecimal(ruosr.getOrderMoney()).divide(new BigDecimal(100));
				BigDecimal allprice = null;
				if(orderPrice!=null&&orderMoney!=null&&ruosr.getOrderAmount()!=null){
					  allprice = orderPrice.multiply(new BigDecimal(ruosr.getOrderAmount()));
					  BigDecimal bigDecimal = orderMoney.divide(allprice,2,RoundingMode.HALF_UP);
					  data.add(bigDecimal.doubleValue()*100);
				}else{
					data.add(0);
				}
				data.add(allprice);
				data.add(orderMoney);
			}else{
				BigDecimal realMoney = StringUtil.isEmpty(ruosr.getRealMoney())?null:new BigDecimal(ruosr.getRealMoney()).divide(new BigDecimal(100));
				BigDecimal allprice = null;
				if(orderPrice!=null&&realMoney!=null&&ruosr.getOrderAmount()!=null){
					   allprice = orderPrice.multiply(new BigDecimal(ruosr.getOrderAmount()));
					   BigDecimal bigDecimal = realMoney.divide(allprice,2,RoundingMode.HALF_UP);
					   data.add(bigDecimal.doubleValue()*100);
				}else{
					data.add(0);
				}
				data.add(allprice);
				data.add(realMoney);
			}
			datas.add(data);
		}

		String fileId = DataInoutUtil.exportExcel(datas, titles, "0", fileType, moduleName, operator);

		LogUtil.info(MODULE, "======================条码文件Id" + fileId + "======================");
		response.setFileId(fileId);
		return response;

	}

	private List<RUnpfOrdSubResp> queryOrdBarCodeInfoNew(RUnpfQueryOrderReq rUnpfQueryOrderReq) {
		List<UnpfOrdMain> unpfOrdMain = this.unpfOrdMainSV.queryUnpfOrdMainByThread(rUnpfQueryOrderReq);
		LogUtil.info(MODULE, "订单导出sql查询结束：" + unpfOrdMain.size());
		List<RUnpfOrdSubResp> sqors = new ArrayList<>();
		if (CollectionUtils.isEmpty(unpfOrdMain)) {
			return sqors;
		}
		sqors = queryOrderBarCodeInfoByThread(unpfOrdMain, rUnpfQueryOrderReq);
		return sqors;
	}

	private List<RUnpfOrdSubResp> queryOrderBarCodeInfoByThread(List<UnpfOrdMain> unpfOrdMain,
			RUnpfQueryOrderReq rUnpfQueryOrderReq) {
		List<RUnpfOrdSubResp> rUnpfOrdSubResps = new ArrayList<>();
		ThreadPoolExecutor executor = new ThreadPoolExecutor(16, 32, 5, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(64));
		Map<Integer, List<RUnpfOrdSubResp>> rUnpfOrdSubRespMap = new HashMap<Integer, List<RUnpfOrdSubResp>>();
		int group = 60; // 查数据点40%，剩余占用60%,分成60个线程
		int groupNum = unpfOrdMain.size() / group;
		int lastNum = unpfOrdMain.size() % group;
		if (unpfOrdMain.size() <= 60) {
			group = unpfOrdMain.size();
			groupNum = 1;
		} else {
			group += 1;
		}
		List<Future> fus = new ArrayList<Future>();
		for (int i = 0; i < group; i++) {
			List<UnpfOrdMain> obs = null;
			if (unpfOrdMain.size() <= 60) {
				obs = new ArrayList<>();
				obs.add(unpfOrdMain.get(i));
			} else {
				if (i != group - 1) {
					obs = unpfOrdMain.subList(i * groupNum, (i + 1) * groupNum);
				} else {
					obs = unpfOrdMain.subList(i * groupNum, i * groupNum + lastNum);
				}
			}
			Future f = executor.submit(new UnpfOrdExportFileSVImpl.DealUnpfOrdBarThread(obs,
					rUnpfQueryOrderReq.getExportKey(), i, rUnpfOrdSubRespMap));
			fus.add(f);
		}

		for (Future f : fus) {
			try {
				f.get();
			} catch (InterruptedException e) {
				LogUtil.info(MODULE, "销售明细补全信息线程查询异常", e);
			} catch (ExecutionException e) {
				LogUtil.info(MODULE, "销售明细补全信息线程查询异常", e);
			}
		}
		executor.shutdown();
		for (List<RUnpfOrdSubResp> v : rUnpfOrdSubRespMap.values()) {
			LogUtil.info(MODULE, "补全信息子列表数量：" + v.size());
			rUnpfOrdSubResps.addAll(v);
		}
		return rUnpfOrdSubResps;

	}

	public class DealUnpfOrdBarThread extends Thread {

		private List<UnpfOrdMain> unpfOrdMains;

		private Long key;

		private int group;

		private Map<Integer, List<RUnpfOrdSubResp>> rUnpfOrdSubRespMap;

		public DealUnpfOrdBarThread(List<UnpfOrdMain> unpfOrdMains, Long key, int group,
				Map<Integer, List<RUnpfOrdSubResp>> rUnpfOrdSubRespMap) {
			this.unpfOrdMains = unpfOrdMains;
			this.key = key;
			this.group = group;
			this.rUnpfOrdSubRespMap = rUnpfOrdSubRespMap;
		}

		@Override
		public void run() {
			LogUtil.info(MODULE, "销售明细补全线程开始处理");
			List<RUnpfOrdSubResp> rUnpfOrdSubRespList = new ArrayList<>();
			for (UnpfOrdMain unpfOrdMain : unpfOrdMains) {
				RUnpfOrdMainReq rUnpfOrdMainReq = new RUnpfOrdMainReq();
				rUnpfOrdMainReq.setOuterId(unpfOrdMain.getOuterId());
				rUnpfOrdMainReq.setId(unpfOrdMain.getId());
				rUnpfOrdMainReq.setPlatType(unpfOrdMain.getPlatType());
				List<RUnpfOrdSubResp> list = iUnpfOrdSubSV.queryUnpfOrdSub(rUnpfOrdMainReq);
				setTxCode(list);
				rUnpfOrdSubRespList.addAll(list);
			}
			rUnpfOrdSubRespMap.put(group, rUnpfOrdSubRespList);
			OrdFileImportDTO ordFile = new OrdFileImportDTO();
			ordFile.setId(key);
			ordFile.setShopId(1L); // 文件进度
			ordFileImportRSV.updateById(ordFile);
			LogUtil.info(MODULE, "销售明细补全线程结束处理");
		}
		private void setTxCode(List<RUnpfOrdSubResp> list){
			if(!list.isEmpty()){
				for(RUnpfOrdSubResp uosr:list){
					try{
						String txCode = "";
						if(StringUtil.isNotEmpty(uosr.getGdsId())){
							if(!uosr.getGdsId().startsWith("p")){
								GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
								gdsInfoReqDTO.setId(Long.parseLong(uosr.getGdsId()));
								List<Long> propIds = new ArrayList<Long>();
						        propIds.add(1050L);//ISBN
						        gdsInfoReqDTO.setPropIds(propIds);

						        gdsInfoReqDTO.setSkuQuerys(new GdsOption.SkuQueryOption[] {GdsOption.SkuQueryOption.PROP});
						        GdsInfoDetailRespDTO gdsinfo = gdsInfoQueryRSV.queryGdsInfoDetail(gdsInfoReqDTO);
								if(gdsinfo!=null&&gdsinfo.getSkuInfo()!=null&&gdsinfo.getSkuInfo().getAllPropMaps()!=null){
									if(gdsinfo.getSkuInfo().getAllPropMaps().get("1050").getValues()!=null){
										txCode = gdsinfo.getSkuInfo().getAllPropMaps().get("1050").getValues().get(0).getPropValue();
									}
								}
							}else{
								GdsSku2PropPropIdxReqDTO reqDTO = new GdsSku2PropPropIdxReqDTO();
								String propValue = uosr.getGdsId();
								if(StringUtil.isEmpty(propValue)||propValue.length()<6){
									uosr.setTxCode(txCode); //条形码
									continue;
								}
								propValue = propValue.substring(1, 6);
								reqDTO.setPropValue(propValue);
								reqDTO.setPropId(1004l);
								reqDTO.setOptions(new GdsOption.SkuQueryOption[] {GdsOption.SkuQueryOption.PROP});
								PageResponseDTO<GdsSkuInfoRespDTO> pages = gdsSkuInfoQueryRSV.queryGdsSkuInfoPaging(reqDTO);
								if(pages!=null&&pages.getResult()!=null&&!pages.getResult().isEmpty()){
									if(pages.getResult().get(0)!=null&&pages.getResult().get(0).getAllPropMaps()!=null){
										if(pages.getResult().get(0).getAllPropMaps().get("1050")!=null){
											txCode = pages.getResult().get(0).getAllPropMaps().get("1050").getValues().get(0).getPropValue();
										}
									}
								}
								
							}
						}
						uosr.setTxCode(txCode); //条形码
					}catch (Exception e) {
						LogUtil.info(MODULE, "单号:"+uosr.getOuterId()+"补全条码错误");
						uosr.setTxCode(""); //条形码
						continue;
					}
					
				}
			}
		}
	}
}
