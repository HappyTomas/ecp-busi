package com.zengshi.ecp.unpf.dubbo.impl.gdssend;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zengshi.ecp.unpf.dubbo.dto.gdssend.*;
import com.zengshi.ecp.unpf.service.busi.good.send.interfaces.IUnpfSendLogSV;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.beans.BeanMap;

import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.GdsSendThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.UploadThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.UploadThirdRespDTO;
import com.zengshi.ecp.aip.third.dubbo.interfaces.IGdsSendRSV;
import com.zengshi.ecp.aip.third.dubbo.interfaces.IThirdUploadRSV;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.GdsCategoryCompareReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.GdsCategoryCompareRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2MediaRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseAreaAdminUtil;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfCatgRelaReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfCatgRelaRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfGdsCatgLimitReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfGdsCatgLimitRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfGdsLimitReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropRelaReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropRelaRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropValueRelaReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropValueRelaRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.cfg.UnpfShopCfgReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.cfg.UnpfShopCfgRespDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.IUnpfShopLimitRSV;
import com.zengshi.ecp.unpf.dubbo.interfaces.gdssend.IUnpfGdsSendRSV;
import com.zengshi.ecp.unpf.dubbo.util.UnpfConstants;
import com.zengshi.ecp.unpf.service.busi.auth.interfaces.IUnpfShopAuthSV;
import com.zengshi.ecp.unpf.service.busi.good.send.interfaces.IUnpfGdsUnsendSV;
import com.zengshi.ecp.unpf.service.busi.good.send.interfaces.IUnpfGoodSendSV;
import com.zengshi.ecp.unpf.service.common.interfaces.IUnpfCategorySV;
import com.zengshi.ecp.unpf.service.common.interfaces.IUnpfCategroyRelSV;
import com.zengshi.ecp.unpf.service.common.interfaces.IUnpfShopCfgSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * @author huangjx:
 * @date 创建时间：2016年11月17日 上午9:10:38
 * @version 1.0 Copyright (c) 2016 AI <br>
 * */
public class UnpfGdsSendRSVImpl implements IUnpfGdsSendRSV {

	private final String MODULE = getClass().getName();

	@Resource
	private IGdsSendRSV gdsSendRSV;

	@Resource
	private IUnpfShopAuthSV unpfShopAuthSV;

	@Resource
	private IGdsInfoQueryRSV gdsInfoQueryRSV;

	@Resource
	private IUnpfGoodSendSV unpfGoodSendSV;

	@Resource
	private IUnpfGdsUnsendSV unpfGdsUnsendSV;

	@Resource
	private IUnpfCategorySV unpfCategorySV;

	@Resource
	private IUnpfCategroyRelSV unpfCategroyRelSV;

	@Resource
	private IUnpfShopLimitRSV unpfShopLimitRSV;

	@Resource
	private IUnpfShopCfgSV unpfShopCfgSV;

	@Resource
	private IThirdUploadRSV thirdUploadRSV;
	
	@Resource
	private IGdsCategoryRSV  gdsCategoryRSV;

	@Resource
	private IUnpfSendLogSV unpfSendLogSV;
	
	/**
	 * 推送
	 * 
	 * @author huangjx
	 * @param
	 * @return
	 * @throws
	 */
	@Override
	public GdsSendRespDTO send(GdsSendReqDTO gdsSendReqDTO)
			throws BusinessException {

		GdsSendRespDTO respDTO = this.send(gdsSendReqDTO, null);
		if("0".equals(respDTO.getIfResult())){
			GdsSendThirdReqDTO gdsSendThirdReqDTO = new GdsSendThirdReqDTO();
			convertTopDTO(gdsSendReqDTO,gdsSendThirdReqDTO);
			UnpfSendLogReqDTO unpfSendLogReqDTO=new UnpfSendLogReqDTO();
			ObjectCopyUtil.copyObjValue(respDTO, unpfSendLogReqDTO, "", false);
			unpfSendLogReqDTO.setShopAuthId(gdsSendThirdReqDTO.getAuthId());
			unpfSendLogReqDTO.setAction("1");
			unpfSendLogReqDTO.setErrors(respDTO.getMsg());
			unpfSendLogReqDTO.setCreateStaff(unpfSendLogReqDTO.getStaff().getId());
			unpfSendLogSV.saveGdsSendLog(unpfSendLogReqDTO);
		}
		if("2".equals(respDTO.getIfResult())){
			respDTO.setIfResult("0");
		}
		return respDTO;
	}

	// 转化 授权基本信息
	private void convertTopDTO(GdsSendReqDTO gdsSendReqDTO,
			GdsSendThirdReqDTO gdsSendThirdReqDTO) throws BusinessException {

		if (gdsSendThirdReqDTO == null) {
			gdsSendThirdReqDTO = new GdsSendThirdReqDTO();
		}
		UnpfShopAuthReqDTO req = new UnpfShopAuthReqDTO();
		req.setShopId(gdsSendReqDTO.getShopId());
		req.setPlatType(gdsSendReqDTO.getPlatType());
		req.setId(gdsSendReqDTO.getShopAuthId());
		req.setStatus("1");
		// 查询店铺授权基本信息
		PageResponseDTO<UnpfShopAuthRespDTO> p = unpfShopAuthSV
				.queryPlatType4ShopForPage(req);

		if (p == null) {
			throw new BusinessException("unpf.100008", new String[] {
					req.getShopId().toString(), req.getPlatType() });
		}

		if (CollectionUtils.isEmpty(p.getResult())) {
			throw new BusinessException("unpf.100008", new String[] {
					req.getShopId().toString(), req.getPlatType() });
		}

		BaseShopAuthReqDTO baseShopAuthReqDTO = new BaseShopAuthReqDTO();

		ObjectCopyUtil.copyObjValue(p.getResult().get(0), baseShopAuthReqDTO,
				null, false);

		baseShopAuthReqDTO.setAuthId(p.getResult().get(0).getId());

		ObjectCopyUtil.copyObjValue(baseShopAuthReqDTO, gdsSendThirdReqDTO,
				null, false);

	}

	// 发送
	private GdsSendRespDTO send(GdsSendReqDTO gdsSendReqDTO,
			GdsSendThirdReqDTO gdsSendThirdReqDTO) throws BusinessException {

		GdsSendRespDTO sendResp = new GdsSendRespDTO();
		sendResp.setGdsId(gdsSendReqDTO.getGdsId());
		sendResp.setPlatType(gdsSendReqDTO.getPlatType());
		sendResp.setShopId(gdsSendReqDTO.getShopId());
		sendResp.setShopAuthId(gdsSendReqDTO.getShopAuthId());

		int sendCntFlag = 3;// 默认推送3次

		// 取数据库设置推送次数
		try {
			BaseSysCfgRespDTO sysDTO = SysCfgUtil.fetchSysCfg(gdsSendReqDTO
					.getPlatType() + "_send_time");
			if (sysDTO != null) {
				if (StringUtils.isNotBlank(sysDTO.getParaValue())) {
					sendCntFlag = Integer.valueOf(sysDTO.getParaValue());
				}
			}
		} catch (Exception ex) {
			LogUtil.error(MODULE + gdsSendReqDTO.getPlatType()
					+ "_send_time       " + ex.getMessage(), "");
		}

		try {
			// 验证参数
			gdsSendReqDTO.check();

			// 授权基本信息设置
			if (gdsSendThirdReqDTO == null) {
				gdsSendThirdReqDTO = new GdsSendThirdReqDTO();
				convertTopDTO(gdsSendReqDTO, gdsSendThirdReqDTO);
			}

			// 已发送一次 不用发送
			UnpfGdsSendReqDTO unpfGdsSendReqDTO = new UnpfGdsSendReqDTO();
			unpfGdsSendReqDTO.setGdsId(gdsSendReqDTO.getGdsId());
			unpfGdsSendReqDTO.setPlatType(gdsSendReqDTO.getPlatType());
			unpfGdsSendReqDTO.setShopId(gdsSendReqDTO.getShopId());
			unpfGdsSendReqDTO.setShopAuthId(gdsSendThirdReqDTO.getAuthId());

			GdsSendBaseRespDTO gdsSendBaseRespDTO = unpfGoodSendSV
					.querySendResultForGds(unpfGdsSendReqDTO);

			// 获得当前推送表数据
			if (gdsSendBaseRespDTO != null) {
				unpfGdsSendReqDTO.setSendCnt(gdsSendBaseRespDTO.getSendCnt());
				unpfGdsSendReqDTO.setId(gdsSendBaseRespDTO.getId());
				unpfGdsSendReqDTO.setAction(gdsSendBaseRespDTO.getAction());
				unpfGdsSendReqDTO.setOutCatgCode(gdsSendBaseRespDTO
						.getOutCatgCode());
				unpfGdsSendReqDTO.setOutGdsId(gdsSendBaseRespDTO.getOutGdsId());
				unpfGdsSendReqDTO.setStatus(gdsSendBaseRespDTO.getStatus());
			}

			if(unpfGdsSendReqDTO.getSendCnt()==null){
				unpfGdsSendReqDTO.setSendCnt(BigDecimal.ZERO);
			}
			// 推送成功 或者失败次数达到规定次数都不能推送
			if (unpfGdsSendReqDTO.getId() != null
					&& ("1".equals(unpfGdsSendReqDTO.getStatus())
					|| unpfGdsSendReqDTO.getSendCnt().intValue() > sendCntFlag)) {
				// 已经存在相关数据 数据移除
				UnpfGdsUnsendReqDTO unpfGdsUnsendReqDTO = new UnpfGdsUnsendReqDTO();
				unpfGdsUnsendReqDTO.setGdsId(gdsSendReqDTO.getGdsId());
				unpfGdsUnsendSV.deleteGdsUnsendByGdsId(unpfGdsUnsendReqDTO);
				if("1".equals(unpfGdsSendReqDTO.getStatus())){
					sendResp.setIfResult("0");
					sendResp.setMsg("商品编码" + gdsSendReqDTO.getGdsId()
							+ "已经成功推送,不能再次推送。谢谢！");
				}else{
					sendResp.setIfResult("0");
					sendResp.setMsg("商品编码" + gdsSendReqDTO.getGdsId()
							+ "推送失败次数大于"+sendCntFlag+" 不能再次推送。谢谢！");
				}
				return sendResp;

			} else {

				// 商品数据获得
				GdsInfoReqDTO gdsInfo = new GdsInfoReqDTO();
				gdsInfo.setId(unpfGdsSendReqDTO.getGdsId());
				gdsInfo.setShopId(unpfGdsSendReqDTO.getShopId());
				GdsQueryOption[] gdsQueryOptions = new GdsQueryOption[1];
				SkuQueryOption[] skuQuerys = new SkuQueryOption[1];
				gdsQueryOptions[0] = GdsQueryOption.ALL;
				skuQuerys[0] = SkuQueryOption.ALL;
				gdsInfo.setGdsQueryOptions(gdsQueryOptions);
				gdsInfo.setSkuQuerys(skuQuerys);
				GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQueryRSV.queryGdsInfoByOption(gdsInfo);
				LogUtil.error(MODULE,"商品信息：" + JSON.toJSONString(gdsInfoRespDTO));

				if (gdsInfoRespDTO==null || gdsInfoRespDTO.getId()==null) {
					sendResp.setIfResult("0");
					sendResp.setMsg("商品编码" + gdsSendReqDTO.getGdsId()
							+ "找不到商品数据");
					return sendResp;
				}
				if (!unpfGdsSendReqDTO.getShopId().equals(gdsInfoRespDTO.getShopId())) {
					sendResp.setIfResult("0");
					sendResp.setMsg("商品编码" + gdsSendReqDTO.getGdsId()+" 店铺编码 ="+unpfGdsSendReqDTO.getShopId()
							+ "不是授权店铺的数据");
					return sendResp;
				}

				if (GdsConstants.GdsInfo.GDS_STATUS_DELETE.equals(gdsInfoRespDTO.getGdsStatus())) {
					sendResp.setIfResult("0");
					sendResp.setMsg("商品编码" + gdsSendReqDTO.getGdsId()+" 店铺编码 ="+unpfGdsSendReqDTO.getShopId()
							+ "已经删除 不能推送");
					return sendResp;
				}
				// 需要验证黑名单服务。如果当前商品或者商品分类属于设置的授权黑名单数据，不能发送，也不清理数据。
				if (isCatgLimit(gdsInfoRespDTO, unpfGdsSendReqDTO)) {
					sendResp.setIfResult("0");
					sendResp.setMsg("黑名单分类 不需要推送");
					return sendResp;
				}
				if (isGdsLimit(gdsInfoRespDTO, unpfGdsSendReqDTO)) {
					sendResp.setIfResult("0");
					sendResp.setMsg("黑名单商品 不需要推送");
					return sendResp;
				}
				
				//若平台为有赞，则gdsName中增加分类名
				if(gdsSendReqDTO.getPlatType().indexOf("youzan")>-1){
					LogUtil.info(MODULE, "----有赞平台添加catgNames---开始----");
					GdsCategoryReqDTO gdsCategoryReqDTOreqDTO = new GdsCategoryReqDTO();
					gdsCategoryReqDTOreqDTO.setCatgCode(gdsInfoRespDTO.getMainCategory().getCatgCode());
					List<GdsCategoryRespDTO> gdsCates = gdsCategoryRSV.queryCategoryTraceUpon(gdsCategoryReqDTOreqDTO);
					String catgNames = "";
					if(gdsCates!=null&&gdsCates.size()>0){
						for(GdsCategoryRespDTO resp:gdsCates){
							catgNames = catgNames+resp.getCatgName()+"/";
						}
						if(catgNames.length()>0){
							catgNames = catgNames.substring(0, catgNames.length()-1);
							gdsInfoRespDTO.setGdsName(gdsInfoRespDTO.getGdsName()+"("+catgNames+")");
						}						
					}
					LogUtil.info(MODULE, "----有赞平台添加catgNames---结束----");
				}
				/*
				 * // 单品数据获得以及属性 属性值转化
				 * convert2SkuInfo(thirdGdsInfo,unpfGdsSendReqDTO); // 分类数据转化
				 * convert2CatgInfo(thirdGdsInfo,unpfGdsSendReqDTO);
				 */

				// 如果 就没有商品属性和值呢 怎么办 没有就没有呗 为空推送
				// 属性?????????????????????????????????????????
				// 属性值???????????????????????????????????????
				Map<String, Object> mapAll = new HashMap<String, Object>();
				Map<String, Object> mapInfo = BeanMap.create(gdsInfoRespDTO);

				for (Map.Entry<String, Object> entry : mapInfo.entrySet()) {
					mapAll.put(entry.getKey(), entry.getValue());
				}
				
				Map<String, Object> map = gdsBaseInfoToMap(gdsInfoRespDTO, unpfGdsSendReqDTO,gdsSendThirdReqDTO,sendResp);
				if(map.get("sendResp") != null){
					LogUtil.error(MODULE,"产品版权页图片和产品包装条码特写信息为空");
					return (GdsSendRespDTO)map.get("sendResp");
				}
				
				mapAll.putAll(map);

				//分类转化
				convert2Catg(mapAll, gdsInfoRespDTO,unpfGdsSendReqDTO);
				// 不同平台 页面功能设置
				convert2Set(mapAll, unpfGdsSendReqDTO);
				// 不同平台 系统默认参数设置
				convert2Param(mapAll, unpfGdsSendReqDTO);


				Map<String, Object> mapAllNew =new HashMap<String, Object>();
				for (Map.Entry<String, Object> entry : mapAll.entrySet()) {
					//根据数据字典表 把相关的key转为外部key
					String keyId=null;
					try{
						keyId=BaseParamUtil.fetchParamValue("UNPF_KEY_RELA", entry.getKey());
						if(StringUtils.isNotBlank(keyId)){
							mapAllNew.put(keyId, entry.getValue());
						}else{
							mapAllNew.put(entry.getKey(), entry.getValue());
						}
					}catch(Exception ex){
						LogUtil.error(MODULE + "内部key 未找到对应的外部key--" + ex.getMessage(), "");
						mapAllNew.put(entry.getKey(), entry.getValue());
					}

				}
				gdsSendThirdReqDTO.setGdsInfoMap(mapAllNew);
				/*String jString=JSON.toJSONString(mapAllNew);
				System.out.println(jString);*/
				if (unpfGdsSendReqDTO.getId() == null) {
					// 初始化数据
					unpfGdsSendReqDTO.setStatus("9");// 初始化
					unpfGdsSendReqDTO.setAction("1");// 推送
					unpfGdsSendReqDTO.setOutCatgCode(gdsInfoRespDTO.getMainCatgs());// 外部分类代码???
					Long id = unpfGoodSendSV.save(unpfGdsSendReqDTO);
					unpfGdsSendReqDTO.setId(id);

				}
				// 第三方发送
				try {
					LogUtil.error(MODULE,"第三方发送信息："+JSON.toJSONString(gdsSendThirdReqDTO));
					HashMap resultMap = gdsSendRSV.send(gdsSendThirdReqDTO);
					if (resultMap != null) {
						if(resultMap.get("gdsId")!=null){
							unpfGdsSendReqDTO.setOutGdsId(resultMap.get("gdsId")
									.toString());
						}
					}

				} catch (Exception e) {

					LogUtil.error(MODULE + "商品推送失败--" + e.getMessage(), "");

					// 推送3次（3次或以上）失败 不能发送 需要把数据置为 status=2
					int countFlag = unpfGdsSendReqDTO.getSendCnt().intValue() + 1;
					if (sendCntFlag < countFlag) {
						// 推送失败
						unpfGdsSendReqDTO.setUpdateStaff(unpfGdsSendReqDTO
								.getStaff().getId());
						unpfGdsSendReqDTO.setCreateStaff(unpfGdsSendReqDTO
								.getStaff().getId());
						unpfGdsSendReqDTO.setUpdateTime(DateUtil.getSysDate());
						unpfGdsSendReqDTO.setErrors(e.toString());
						unpfGdsSendReqDTO.setStatus("0");
						unpfGdsSendReqDTO.getSendCnt().add(BigDecimal.ONE);
						unpfGoodSendSV.saveSendError(unpfGdsSendReqDTO, true);

					} else {
						unpfGdsSendReqDTO.setUpdateStaff(unpfGdsSendReqDTO
								.getStaff().getId());
						unpfGdsSendReqDTO.setCreateStaff(unpfGdsSendReqDTO
								.getStaff().getId());
						unpfGdsSendReqDTO.setUpdateTime(DateUtil.getSysDate());
						unpfGdsSendReqDTO.setErrors(e.toString());
						unpfGdsSendReqDTO.setStatus("2");
						unpfGdsSendReqDTO.getSendCnt().add(BigDecimal.ONE);
						unpfGoodSendSV.saveSendError(unpfGdsSendReqDTO, false);
					}
					sendResp.setIfResult("2");
					sendResp.setMsg(e.getMessage());
					return sendResp;
//					throw e;
				}

				// 推送成功
				unpfGoodSendSV.saveSendSucess(unpfGdsSendReqDTO);

			}


		} catch (BusinessException e) {
			LogUtil.error(MODULE + "商品推送失败,BusinessException原因---" + e.getMessage(), "");
			if ("1".equals(gdsSendReqDTO.getIfThrow())) {
				throw e;
			}
			sendResp.setIfResult("0");
			sendResp.setMsg(e.getErrorMessage());
		}catch (Exception e) {
			LogUtil.error(MODULE + "商品推送失败,Exception原因---" + e.getMessage(), "");
			if ("1".equals(gdsSendReqDTO.getIfThrow())) {
				throw e;
			}
			sendResp.setIfResult("0");
			sendResp.setMsg(e.getMessage());
		}
		return sendResp;
	}
	// 是否分类黑名单
	private boolean isCatgLimit(GdsInfoRespDTO gdsInfoRespDTO,
			UnpfGdsSendReqDTO unpfGdsSendReqDTO) {
		
		 boolean ifLimit=false;
		 
		UnpfGdsCatgLimitReqDTO catgLimitReqDTO = new UnpfGdsCatgLimitReqDTO();
		catgLimitReqDTO.setPlatType(unpfGdsSendReqDTO.getPlatType());
		catgLimitReqDTO.setShopAuthId(unpfGdsSendReqDTO.getShopAuthId());
		catgLimitReqDTO.setShopId(unpfGdsSendReqDTO.getShopId());
		catgLimitReqDTO.setStatus("1");
		catgLimitReqDTO.setPageSize(10);
		//取分页数值
		PageResponseDTO<UnpfGdsCatgLimitRespDTO>  page=unpfShopLimitRSV.queryCatgLimitPage(catgLimitReqDTO);
		
		if(page!=null && CollectionUtils.isNotEmpty(page.getResult())){

	        //调用接口 获得gds 当前分类 
	        GdsInfoReqDTO dto=new GdsInfoReqDTO();
	        dto.setId(gdsInfoRespDTO.getId());
	        GdsQueryOption[] gdsQuery=new GdsQueryOption[1];
	        gdsQuery[0]=GdsOption.GdsQueryOption.CATG;//分类
	        dto.setGdsQueryOptions(gdsQuery);
	        GdsInfoRespDTO gdsDTO= gdsInfoQueryRSV.queryGdsInfoByOption(dto);
	        
	        List<String> platformCatgList=new ArrayList<String>();
	       
	        if(gdsDTO!=null){
	        	//如果下架 删除等 gdsDTO为null
	            if(!CollectionUtils.isEmpty(gdsDTO.getPlatformCategory())){
	                  for(GdsCategoryRespDTO p:gdsDTO.getPlatformCategory()){
	                      platformCatgList.add(p.getCatgCode());
	                  }
	            }
	        }
	       
	        long count=page.getPageCount();
	       //获得每个黑名单分类和商品分类比较
	        for(int pNo=1;pNo<=count;pNo++){
	        	catgLimitReqDTO.setPageNo(pNo);
	        	PageResponseDTO<UnpfGdsCatgLimitRespDTO>  pageTemp=unpfShopLimitRSV.queryCatgLimitPage(catgLimitReqDTO);
	        	
	        	if(pageTemp!=null && CollectionUtils.isNotEmpty(pageTemp.getResult())){
	    	        for(UnpfGdsCatgLimitRespDTO r:pageTemp.getResult()){
	    	            //调用接口 获得 当前分类下子节点 
	    	            Integer result=  this.compareCatg(platformCatgList,r.getCatgCode());
	    	            if(GdsCategoryCompareRespDTO.RESULT_EQUAL.equals(result) || GdsCategoryCompareRespDTO.RESULT_GREAT_THAN.equals(result) ){
	    	              //返回结果关系
	    	            	ifLimit=true;
	    	            	break;
	    	            }
	    	        }
	        	}
	        }
	        
	        return ifLimit;
		
		} 
		return ifLimit;
	}

	// 是否商品黑名单
	private boolean isGdsLimit(GdsInfoRespDTO gdsInfoRespDTO,
			UnpfGdsSendReqDTO unpfGdsSendReqDTO) {

		UnpfGdsLimitReqDTO gdsLimitReqDTO = new UnpfGdsLimitReqDTO();
		gdsLimitReqDTO.setGdsId(unpfGdsSendReqDTO.getGdsId());
		gdsLimitReqDTO.setLimitType("2");
		gdsLimitReqDTO.setPlatType(unpfGdsSendReqDTO.getPlatType());
		gdsLimitReqDTO.setShopAuthId(unpfGdsSendReqDTO.getShopAuthId());
		gdsLimitReqDTO.setShopId(unpfGdsSendReqDTO.getShopId());
		gdsLimitReqDTO.setStatus("1");
		if (unpfShopLimitRSV.checkLimitExits(gdsLimitReqDTO)) {
			return true;
		}
		return false;

	}
	//外部分类设置
	private void convert2Catg(Map<String, Object> hashMap,GdsInfoRespDTO gdsInfoRespDTO,
			UnpfGdsSendReqDTO unpfGdsSendReqDTO) {
			UnpfCatgRelaReqDTO unpfCatgRelaReqDTO = new UnpfCatgRelaReqDTO();
			unpfCatgRelaReqDTO.setShopAuthId(unpfGdsSendReqDTO.getShopAuthId());
			unpfCatgRelaReqDTO.setPlatType(unpfGdsSendReqDTO.getPlatType());
			unpfCatgRelaReqDTO.setShopId(unpfGdsSendReqDTO.getShopId());
			unpfCatgRelaReqDTO.setStatus("1");
			unpfCatgRelaReqDTO.setCatgCode(gdsInfoRespDTO.getMainCatgs());
			List<UnpfCatgRelaRespDTO> list = unpfCategroyRelSV.selectCatgRelaList(unpfCatgRelaReqDTO);

			if (CollectionUtils.isNotEmpty(list)) {
				hashMap.put("outCatgCode", list.get(0).getOutCatgCode());
			}else{
				throw new BusinessException("unpf.100025",new String[]{gdsInfoRespDTO.getId().toString(),gdsInfoRespDTO.getMainCatgs()+gdsInfoRespDTO.getMainCatgName()});
			}
	}
	// 转化 其他默认值参数数据
	private void convert2Set(Map<String, Object> hashMap,
			UnpfGdsSendReqDTO unpfGdsSendReqDTO) {
		try {
			UnpfShopCfgReqDTO unpfShopCfgReqDTO = new UnpfShopCfgReqDTO();
			unpfShopCfgReqDTO.setShopAuthId(unpfGdsSendReqDTO.getShopAuthId());
			unpfShopCfgReqDTO.setShopId(unpfGdsSendReqDTO.getShopId());
			unpfShopCfgReqDTO.setStatus("1");
			List<UnpfShopCfgRespDTO> list = unpfShopCfgSV
					.queryShopCfgList(unpfShopCfgReqDTO);

			if (CollectionUtils.isNotEmpty(list)) {
				String inputValue = list.get(0).getInputValue().toString();
				HashMap<String, String> mm = JSONObject.parseObject(inputValue,
						HashMap.class);
				for (Map.Entry<String, String> entry : mm.entrySet()) {
					hashMap.put(entry.getKey(), entry.getValue());
				}
				//直辖市
				String spcity=null;
				// 如果省份 城市为空 需要设置
				if (hashMap.get("provinceName") == null || hashMap.get("provinceName")=="") {
					if (hashMap.get("provinceCode") != null && hashMap.get("provinceCode") != "") {
						 spcity=BaseParamUtil.fetchParamValue("UNPF_CITY", hashMap
								.get("provinceCode").toString());
						String name = BaseAreaAdminUtil.fetchAreaName(hashMap
								.get("provinceCode").toString());
						hashMap.put("prov", name);
					}else{
						hashMap.put("prov", "");
					}
				}
				if (hashMap.get("cityName") == null || hashMap.get("cityName") == "") {
					if (hashMap.get("cityCode") != null && hashMap.get("cityCode") != "" ) {
						String name = BaseAreaAdminUtil.fetchAreaName(hashMap
								.get("cityCode").toString());
						if(StringUtils.isNotEmpty(name)){
							name=StringUtils.removeEnd(name, "市");
						}
						hashMap.put("city", name);
					}else{
						hashMap.put("city", "");
					}
				}
				if(StringUtils.isNotEmpty(spcity)){
					hashMap.put("city", spcity);
				}
				  //所在地
		        Map<String, String> locationMap = new HashMap<String, String>();
		        locationMap.put("prov", hashMap.get("prov").toString());
		        locationMap.put("city", hashMap.get("city").toString());
		        hashMap.put("location", locationMap);
		        
		        
		      /*  //运费模板相关处理
		        //运费承担方式
		        gdsInfoMap.put("freight_payer", "1");
		        //买家承担运费
		        gdsInfoMap.put("freight_by_buyer", "freight_details");
		        //运费
		        Map<String, String> freightMap = new HashMap<String, String>();
		        freightMap.put("express_fee", "15");//快递
		        freightMap.put("post_fee", "10");//平邮
		        freightMap.put("ems_fee", "20");//EMS
		        gdsInfoMap.put("freight", freightMap);*/
		        
		        Map<String, String> freightMap = new HashMap<String, String>();
		        freightMap.put("express_fee", mm.get("express"));//快递
		        freightMap.put("post_fee", mm.get("post"));//平邮
		        freightMap.put("ems_fee", mm.get("ems"));//EMS
		        hashMap.put("freight", freightMap);
				hashMap.put("templateId",mm.get("templateId"));

			}
		} catch (Exception ex) {
			LogUtil.error(
					MODULE + "没有t_unpf_shop_cfg表设置默认值---" + ex.getMessage(), "");
		}
	}

	// 转化 系统设置参数值
	private void convert2Param(Map<String, Object> hashMap,
			UnpfGdsSendReqDTO unpfGdsSendReqDTO) {
		List<BaseParamDTO> list = null;
		try {
			list = BaseParamUtil.fetchParamList(unpfGdsSendReqDTO.getPlatType()
					.toUpperCase() + "_SEND_DEFAULT_VALUE");
		} catch (Exception ex) {
			LogUtil.error(MODULE + "没有基本参数表设置默认值---" + ex.getMessage(), "");
		}
		if (CollectionUtils.isNotEmpty(list)) {
			for (BaseParamDTO p : list) {
				hashMap.put(p.getSpCode(), p.getSpValue());
			}
		}

	}

	private Map<String, Object> gdsBaseInfoToMap(GdsInfoRespDTO gdsInfo, UnpfGdsSendReqDTO unpfGdsSendReqDTO  ,GdsSendThirdReqDTO gdsSendThirdReqDTO,GdsSendRespDTO sendResp) {
        if (gdsInfo == null) {
            return null;
        }     
        
        Map<String, Object> gdsInfoMap = new HashMap<String, Object>();
        
        //商品标题
        gdsInfoMap.put("title", gdsInfo.getGdsName());
        //发布类型
        //gdsInfoMap.put("item_type", "b");
        //宝贝类型
       // gdsInfoMap.put("stuff_status", "5");
//		<option displayName="出售中" value="0"/>
//		<option displayName="定时上架" value="1"/>
//		<option displayName="仓库中" value="2"/>
		//商品状态  //我们系统是上架的并且库存大于0是 推出售中，其它都是仓库中
		gdsInfoMap.put("item_status", "2");
        //商品价格
        if(gdsInfo.getGuidePrice()!=null){
        	gdsInfoMap.put("guidePrice", String.valueOf(Double.valueOf(gdsInfo.getGuidePrice())/100));
        }else{
        	gdsInfoMap.put("guidePrice", gdsInfo.getGuidePrice());
        }
        if(gdsInfo.getGuidePrice()!=null){
        	gdsInfoMap.put("price", String.valueOf(Double.valueOf(gdsInfo.getGuidePrice())/100));
        }else{
        	gdsInfoMap.put("price", gdsInfo.getGuidePrice());
        }
        
        //价格为0的上传到厂库中  状态为2
        if(gdsInfo.getGuidePrice()==null || Long.compare(gdsInfo.getGuidePrice(),0)<=0){
        	   gdsInfoMap.put("item_status", "2");
        }
        //单品列表
        long quantity = 0;
        if (CollectionUtils.isNotEmpty(gdsInfo.getSkus())) {
            List<Map<String, String>> skus = new ArrayList<Map<String, String>>();
            for(GdsSkuInfoRespDTO sku : gdsInfo.getSkus()){
                Map<String, String> info = new HashMap<String, String>();
                //info.put("sku_id", sku.getId().toString());
                info.put("sku_outerId", sku.getId().toString()); 
                info.put("sku_price", String.valueOf(Double.valueOf(sku.getGuidePrice())/100));
                // quantity += sku.getRealAmount();
                Long realAmount = getResultStock(sku.getRealAmount());
                quantity += realAmount;
                info.put("sku_quantity", realAmount.toString());
                if (CollectionUtils.isNotEmpty(sku.getProps())) {
                    for(GdsPropRespDTO prop : sku.getProps()){
                        //内部属性转成外部属性     内部属性值转外部属性
                        String outPropId = convertToOutProp(prop.getId(), unpfGdsSendReqDTO);
                        if (StringUtil.isNotBlank(outPropId)) {
                            GdsPropValueRespDTO propValue = prop.getValues().get(0);
                            if (propValue != null) {
                                String outPropValueId = convertToOutPropValue(prop.getId(), outPropId, propValue.getId(), unpfGdsSendReqDTO);
                                if (StringUtil.isNotBlank(outPropValueId)) {
                                    info.put("prop_"+outPropId, outPropValueId);
                                }else {
                                    LogUtil.info(MODULE, "店铺["+unpfGdsSendReqDTO.getShopId()+"]的内部属性["+prop.getPropName()+"]的属性值["+propValue.getPropValue()+"]没有对应的["+unpfGdsSendReqDTO.getPlatType()+"]平台商品属性映射关系");
                                }
                            }
                        }else {
                            LogUtil.info(MODULE, "店铺["+unpfGdsSendReqDTO.getShopId()+"]的内部属性["+prop.getPropName()+"]没有对应的["+unpfGdsSendReqDTO.getPlatType()+"]平台商品属性映射关系");
                        }
                    }   
                    skus.add(info);
                }
            }
            gdsInfoMap.put("sku", skus);
        }

		//我们系统是上架的并且库存大于0是 推出售中，其它都是仓库中
		if(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES.equals(gdsInfo.getGdsStatus()) && quantity > 0){
			gdsInfoMap.put("item_status", "0");
		}
        //商品数量
        gdsInfoMap.put("quantity", quantity+"");      
        //提取方式
        //gdsInfoMap.put("delivery_way", "2");
        //码商
        //过期退款
        Map<String, Object> expired_refundMap = new HashMap<String, Object>();
        expired_refundMap.put("support_expired_refund_rate", "false");
        expired_refundMap.put("expired_refund_rate", "100");
        gdsInfoMap.put("expired_refund", expired_refundMap);
        //所在地
       /* Map<String, String> locationMap = new HashMap<String, String>();
        locationMap.put("prov", "福建");
        locationMap.put("city", "福州");
        gdsInfoMap.put("location", locationMap);*/
      /*  //运费承担方式
        gdsInfoMap.put("freight_payer", "1");
        //买家承担运费
        gdsInfoMap.put("freight_by_buyer", "freight_details");
        //运费
        Map<String, String> freightMap = new HashMap<String, String>();
        freightMap.put("express_fee", "15");//快递
        freightMap.put("post_fee", "10");//平邮
        freightMap.put("ems_fee", "20");//EMS
        gdsInfoMap.put("freight", freightMap);
        //运费模板ID
        gdsInfoMap.put("postage_id", "");*/
        //保修
        //gdsInfoMap.put("has_warranty", "false");
        //发票
        //gdsInfoMap.put("has_invoice", "true");
        
        //获取制品编号
        String zpCode = "";
        if(gdsInfo.getSkus()!=null&&gdsInfo.getSkus().size()>0){
        	if(gdsInfo.getSkus().get(0).getAllPropMaps()!=null){
        		GdsPropRespDTO propDto = gdsInfo.getSkus().get(0).getAllPropMaps().get(UnpfConstants.ZpProp.PROP_ID);
        		if(propDto!=null){
        			if( propDto.getValues()!=null&&propDto.getValues().size()>0){
        				zpCode = propDto.getValues().get(0).getPropValue();
        			}
        		}
        	}
        }
        
        //判断是否存在制品编号
        if(StringUtil.isBlank(zpCode)){
        	zpCode = String.valueOf(gdsInfo.getId());
        }else{
        	zpCode = "P"+zpCode;
        }
      
        //商家外部编码
        gdsInfoMap.put("outer_id", zpCode);
        //是否新品
        //gdsInfoMap.put("is_xinpin", gdsInfo.getIfNew());
        //商品描述
        Map<String, Object> descModMap = new HashMap<String, Object>();
        if (gdsInfo.getRichTextPropMap() != null) {
            
            //自定义描述模块
            List<Map<String, String>> itemUserDescList = new ArrayList<Map<String, String>>() ;
            
            Map<String, GdsPropRespDTO> richDescMap = gdsInfo.getRichTextPropMap();
            int itemDescOrder = 0;
            for(String descKey : richDescMap.keySet()){
                GdsPropRespDTO propContent = richDescMap.get(descKey);
                GdsPropValueRespDTO propValue = propContent.getValues().get(0);
                if (propValue != null) {
                    BaseParamDTO itemMod = convertToDescModule(propContent.getId());
                    if (StringUtil.isNotEmpty(itemMod)) {
                        String c=FileUtil.readFile2Text(propValue.getPropValue(), "UTF-8");
                        if(StringUtils.isBlank(c) && !(propContent.getId() != null && 1021 == propContent.getId().longValue())){
                        	continue;
                        }
						if(propContent.getId() != null && 1021== propContent.getId().longValue()){
                        	if(StringUtils.isBlank(c)) {//当商城目录信息为空时，补充标题样式和默认目录内容
								String tt = "<div style=\"margin: 0px; padding: 0px 0px0px 17px; list-style-type: none; border: 0px; background-color: rgb(245, 245, 245); font-weight: bold; height: 31px; line-height: 31px; color: rgb(0, 0, 0);\">\n" +
										"&nbsp; &nbsp;目录\n" +
										"</div>\n" +
										"<div style=\"margin: 0px; padding: 25px 29px 15px; list-style-type: none; border: 0px; position: relative; overflow: hidden; line-height: 24px;\">\n" +
										"<span style=\"color: rgb(0, 0, 0); font-family: SimSun, Arial;\">\n" +
										"<p style=\"list-style-type: none; border: 0px;\">本书暂无目录</p>\n" +
										"</span>\n" +
										"</div>";
								c = tt;
							} else { //当商城目录信息不为空时，补充标题样式
								String bt = "<div style=\"margin: 0px; padding: 0px 0px0px 17px; list-style-type: none; border: 0px; background-color: rgb(245, 245, 245); font-weight: bold; height: 31px; line-height: 31px; color: rgb(0, 0, 0);\">\n" +
										"&nbsp; &nbsp;目录\n" +
										"</div>";
								c = bt + c;
							}

						}
						if(propContent.getId() != null && 1020==propContent.getId().longValue()){ //内容简介自动补齐标题样式
							String bt = "<div style=\"margin: 0px; padding: 0px 0px0px 17px; list-style-type: none; border: 0px; background-color: rgb(245, 245, 245); font-weight: bold; height: 31px; line-height: 31px; color: rgb(0, 0, 0);\">\n" +
									"&nbsp; &nbsp;内容简介\n" +
									"</div>";
							c = bt + c;
						}
                        Map<String, String> itemDescContent = new HashMap<String, String>();
                        String itemModId = itemMod.getSpValue();
                        int    itemOrder = itemMod.getSpOrder();
                        itemDescContent.put(itemModId+"_content",c);
                        itemDescContent.put(itemModId+"_order", String.valueOf(itemOrder));
                        descModMap.put(itemModId, itemDescContent);
                    }else {   
                        String c=FileUtil.readFile2Text(propValue.getPropValue(), "UTF-8");
                        if(StringUtils.isBlank(c)){
                        	continue;
                        }
                        Map<String, String> itemUserDescContent = new HashMap<String, String>();
                        itemUserDescContent.put("desc_module_user_mod_name", propContent.getPropName());
                        itemUserDescContent.put("desc_module_user_mod_content", c);
                        itemUserDescContent.put("desc_module_user_mod_order", String.valueOf(itemDescOrder));        
                        itemUserDescList.add(itemUserDescContent);
                        LogUtil.info(MODULE, "店铺["+unpfGdsSendReqDTO.getShopId()+"]的商品描述["+propContent.getPropName()+"]模块没有对应的["+unpfGdsSendReqDTO.getPlatType()+"]平台商品描述映射关系,已自定义模块");
                    }
                    itemDescOrder++;
                }
            }
            if (CollectionUtils.isNotEmpty(itemUserDescList)) {
                //自定义商品描述模块
                descModMap.put("desc_module_user_mods", itemUserDescList);
            }
        }
		BaseParamDTO itemMod = convertToDescModule(1021L);
		if (StringUtil.isNotEmpty(itemMod)) {
			if(descModMap.get(itemMod.getSpValue()) == null){
				String tt = "<div style=\"margin: 0px; padding: 0px 0px0px 17px; list-style-type: none; border: 0px; background-color: rgb(245, 245, 245); font-weight: bold; height: 31px; line-height: 31px; color: rgb(0, 0, 0);\">\n" +
						"&nbsp; &nbsp;目录\n" +
						"</div>\n" +
						"<div style=\"margin: 0px; padding: 25px 29px 15px; list-style-type: none; border: 0px; position: relative; overflow: hidden; line-height: 24px;\">\n" +
						"<span style=\"color: rgb(0, 0, 0); font-family: SimSun, Arial;\">\n" +
						"<p style=\"list-style-type: none; border: 0px;\">本书暂无目录</p>\n" +
						"</span>\n" +
						"</div>";
				Map<String, String> itemDescContent = new HashMap<String, String>();
				String itemModId = itemMod.getSpValue();
				int    itemOrder = itemMod.getSpOrder();
				itemDescContent.put(itemModId+"_content",tt);
				itemDescContent.put(itemModId+"_order", String.valueOf(itemOrder));
				descModMap.put(itemModId, itemDescContent);
			}
		}
        gdsInfoMap.put("description", descModMap);
        
        //产品图片
        Map<String, String> pimagesMap = new HashMap<String, String>();
        //商品图片
        Map<String, String> imagesMap = new HashMap<String, String>();

        Map<String, String> imagesUuidMap = new HashMap<String, String>();

        if (CollectionUtils.isNotEmpty(gdsInfo.getMedias())) {
            int count = 0;
            for(GdsGds2MediaRespDTO image : gdsInfo.getMedias())
            {
            	UploadThirdReqDTO uploadThirdReqDTO=new UploadThirdReqDTO();
            	ObjectCopyUtil.copyObjValue(gdsSendThirdReqDTO.findBaseShopAuth(), uploadThirdReqDTO,
				null, false);
            	String url=ImageUtil.getImageUrl(StringUtils.isBlank(image.getMediaUuid())?ImageUtil.getDefaultImageId():image.getMediaUuid());
               	uploadThirdReqDTO.setImageInputTitle(image.getMediaUuid());
            	//uploadThirdReqDTO.setImgUrl(url);
            	uploadThirdReqDTO.setFileId(StringUtils.isBlank(image.getMediaUuid())?ImageUtil.getDefaultImageId():image.getMediaUuid());//该字段为空 取imgUrl路径处理图片
            	uploadThirdReqDTO.setPictureCategoryId("0");
            	uploadThirdReqDTO.setTitle(image.getMediaUuid());
            	UploadThirdRespDTO respUpload=null;
				imagesUuidMap.put("image_uuid_"+count,StringUtils.isBlank(image.getMediaUuid())?ImageUtil.getDefaultImageId():image.getMediaUuid());
            	try{
            	      respUpload=thirdUploadRSV.upload(uploadThirdReqDTO);
            	}catch(Exception ex){
            		 LogUtil.info(MODULE, "上传图片报错啦");
            	}
            	
            	if(respUpload!=null){
            		url=respUpload.getPicturePath();
            	}
            	pimagesMap.put("product_image_"+count, url);
            	imagesMap.put("item_image_"+count, url);
                count++;
            } 
        }
        gdsInfoMap.put("product_images", pimagesMap);
        gdsInfoMap.put("item_images", imagesMap);
        gdsInfoMap.put("images_uuids", imagesUuidMap);

        //有效期
        //gdsInfoMap.put("valid_thru", "17");
        
        //相关属性处理 转成key
        Map<String, GdsPropRespDTO>  mapPropMap=gdsInfo.getAllPropMaps();
        if(mapPropMap!=null){
        	 for (Map.Entry<String, GdsPropRespDTO> entry : mapPropMap.entrySet()) {
        		 if("1002".equals(entry.getKey())){
        			//特别处理isbn  以下格式不能识别/*ISBN：ISBN 978-7-117-23354-5/R·23355  ISBN：ISBN 978-7-117-23354-5*/
        			//正确格式978-7-117-23354-5 或者9787117233545
        			 Object isbnObject= feathPropValue(entry.getKey(),entry.getValue().getValues(),gdsInfo.getRichTextPropMap());
        			 if(isbnObject!=null){
        				 String isbn=isbnObject.toString();
        				 String[] s=isbn.split("/");
        				 if(s!=null && s.length>0){
        					 isbn=s[0];
        					 isbn=isbn.replaceAll("ISBN", "").replaceAll("-", "").replaceAll(" ", "");
        					 gdsInfoMap.put(convertKey("prop_",entry.getKey()), isbn);
        				 }
        				 
        			 }
        		 }else{
        			 gdsInfoMap.put(convertKey("prop_",entry.getKey()), feathPropValue(entry.getKey(),entry.getValue().getValues(),gdsInfo.getRichTextPropMap()));
        		 }
        		 
        	}
			gdsInfoMap.put("prop_2043189","无");
        }
        
        //产品规格组装
        List<Map<String, String>> cspuList=new ArrayList<Map<String, String>>();
        Map<String, String> productMap = new HashMap<String, String>();
        cspuList.add(productMap);
        
        if(pimagesMap!=null){
	        for (Map.Entry<String, String> entry : pimagesMap.entrySet()) {
	        	String key=convertKey("",entry.getKey());
	        	if(StringUtils.isBlank(key)){
	        		productMap.put(entry.getKey(), entry.getValue());
	        	}else{
	        		productMap.put(key, entry.getValue());
	        	}
	        } 
        }
        //条形码设置
        if(gdsInfoMap.get("barcode")!=null){
        	 productMap.put("barcode", gdsInfoMap.get("barcode").toString());
        }
        
        //产品包装正面图
        if(productMap.get("attach_1")==null || "".equals(productMap.get("attach_1"))){
        	productMap.put("attach_1", productMap.get("product_image_0"));
        }
        String attach_5 = null;
        String attach_8 = null;
		GdsPropRespDTO attach5DTO = mapPropMap.get("4000");
		GdsPropRespDTO attach8DTO = mapPropMap.get("4001");
		if(attach5DTO != null){
			if(CollectionUtils.isNotEmpty(attach5DTO.getValues()) && StringUtil.isNotBlank(attach5DTO.getValues().get(0).getPropValue())){
				attach_5 = ImageUtil.getImageUrl(attach5DTO.getValues().get(0).getPropValue());
			} else {
				LogUtil.info(MODULE, "==1:"+"该商品没有产品版权页图片和产品包装条码特写信息，请到商品管理中进行商品编辑，添加维护产品版权页图片和产品包装条码特写信息！");
			}
		}
		if(attach8DTO != null){
			if(CollectionUtils.isNotEmpty(attach8DTO.getValues()) && StringUtil.isNotBlank(attach8DTO.getValues().get(0).getPropValue())){
				attach_8 = ImageUtil.getImageUrl(attach8DTO.getValues().get(0).getPropValue());
			} else {
				LogUtil.info(MODULE, "==2:"+"该商品没有产品版权页图片和产品包装条码特写信息，请到商品管理中进行商品编辑，添加维护产品版权页图片和产品包装条码特写信息！");
			}
		}

		if("taobao".equals(unpfGdsSendReqDTO.getPlatType()) && (attach_5 == null || attach_8 == null) ){
			sendResp.setIfResult("0");
			sendResp.setMsg("该商品没有产品版权页图片和产品包装条码特写信息，请到商品管理中进行商品编辑，添加维护产品版权页图片和产品包装条码特写信息！");
			gdsInfoMap.put("sendResp",sendResp);
			return gdsInfoMap;
		}
        
        //产品包装条形码特写
        if(productMap.get("attach_5")==null || "".equals(productMap.get("attach_5"))){
        	productMap.put("attach_5", attach_5);
        }
        
        //产品版权页图片
        if(productMap.get("attach_8")==null || "".equals(productMap.get("attach_8"))){
        	productMap.put("attach_8", attach_8);
        }
        
        gdsInfoMap.put("cspuList", cspuList);
        
        return gdsInfoMap;
    }

	//根据内部key 获得外部key
	private String convertKey(String prix,String innerId){
		String key=StringUtils.isBlank(prix)?"":prix+innerId;
		String re= BaseParamUtil.fetchParamValue("UNPF_KEY_RELA", StringUtils.isBlank(prix)?"":prix+innerId);
		return StringUtils.isBlank(re)?key:re;
	}

	//根据属性id或者属性值
	private Object feathPropValue(String propId,List<GdsPropValueRespDTO> values,Map<String, GdsPropRespDTO> richTextPropMap){
		//values 为空  返回null  只有一笔数据 返回对应的propValue  否则返回List<Map>
		if(CollectionUtils.isEmpty(values)){
			return null;
		}
		boolean isRichText = richTextPropMap.get(propId) != null ? true :false;
		if(values.size()<=1){
			if(isRichText){
				return FileUtil.readFile2Text(values.get(0).getPropValue(), "UTF-8");
			} else {
				return values.get(0).getPropValue();
			}
		}else{
			List<Map<String, Object>> maps=new ArrayList<Map<String, Object>>();
			for(GdsPropValueRespDTO v:values){
				Map<String, Object> map=new HashMap<String, Object>();
				if(isRichText){
					map.put(v.getId().toString(), FileUtil.readFile2Text(v.getPropValue(), "UTF-8"));
				} else {
					map.put(v.getId().toString(), v.getPropValue());
				}
				maps.add(map);
			}
			return maps;
		}
	}

   
   private BaseParamDTO convertToDescModule(Long innerId){
       //商品描述富文本属性 ： 1020内容简介 1021目录 1022作者介绍 1023编辑推荐 1024专家推荐 1025章节节选
	   return BaseParamUtil.fetchParamDTO("UNPF_KEY_RELA", "desc_module_"+innerId+"_cat_mod");
   }
   private String convertToOutProp(Long innerPropId, UnpfGdsSendReqDTO unpfGdsSendReqDTO){
       UnpfPropRelaReqDTO propRelReqDTO = new UnpfPropRelaReqDTO();
       propRelReqDTO.setPropId(innerPropId);
       propRelReqDTO.setShopId(unpfGdsSendReqDTO.getShopId());
       propRelReqDTO.setShopAuthId(unpfGdsSendReqDTO.getShopAuthId());
       propRelReqDTO.setPlatType(unpfGdsSendReqDTO.getPlatType());
       propRelReqDTO.setStatus("1");
       
       List<UnpfPropRelaRespDTO> propList = unpfCategroyRelSV.selectPropRelaList(propRelReqDTO);
       
       if (CollectionUtils.isNotEmpty(propList)) {
           return propList.get(0).getOutPropId();
       }
       return null;
   }
   private String convertToOutPropValue(Long innerPropId, String outPropId,Long innerPropValueId, UnpfGdsSendReqDTO unpfGdsSendReqDTO){
       
       UnpfPropValueRelaReqDTO propValueRelaReqDTO = new UnpfPropValueRelaReqDTO();
       propValueRelaReqDTO.setOutPropId(outPropId);
       propValueRelaReqDTO.setPropId(String.valueOf(innerPropId));
       propValueRelaReqDTO.setVid(String.valueOf(innerPropValueId));
       propValueRelaReqDTO.setShopId(unpfGdsSendReqDTO.getShopId());
       propValueRelaReqDTO.setShopAuthId(unpfGdsSendReqDTO.getShopAuthId());
       propValueRelaReqDTO.setPlatType(unpfGdsSendReqDTO.getPlatType());
       propValueRelaReqDTO.setStatus("1");
       
       List<UnpfPropValueRelaRespDTO> propValuesList = unpfCategroyRelSV.selectPropValueRelaList(propValueRelaReqDTO);
       
       if (CollectionUtils.isNotEmpty(propValuesList)) {
           return propValuesList.get(0).getOutVid();
       }
       
       return null;
   }

   /**
    * 比较分类
    * @param sourceCatg
    * @param targetCatg
    * @return
    * @throws BusinessException
    * @author huangjx
    */
   private Integer compareCatg(String sourceCatg,String targetCatg) throws BusinessException {
       
     if(StringUtil.isEmpty(sourceCatg) || StringUtil.isEmpty(targetCatg)) {
         return GdsCategoryCompareRespDTO.RESULT_ERROR;
     }
     GdsCategoryCompareReqDTO  compareReqDTO= new GdsCategoryCompareReqDTO();
       compareReqDTO.setSourceCode(sourceCatg);
       compareReqDTO.setTargetCode(targetCatg);
       GdsCategoryCompareRespDTO dto=gdsCategoryRSV.executeCompare(compareReqDTO);
       return dto.getResult();
   }
   
   /**
    * 源分类集合 和目标分类比较
    * @param sourceCatgs
    * @param targetCatg
    * @return
    * @author huangjx
    */
   private Integer compareCatg(List<String> sourceCatgs,String targetCatg){
       
       Integer result=GdsCategoryCompareRespDTO.RESULT_ERROR;
       if(CollectionUtils.isEmpty(sourceCatgs) || StringUtil.isEmpty(targetCatg)) {
           return result;
       }
      //验证比较
       for(String sourceCatg:sourceCatgs){
           result=this.compareCatg(sourceCatg, targetCatg);
           if(GdsCategoryCompareRespDTO.RESULT_EQUAL.equals(result) || GdsCategoryCompareRespDTO.RESULT_GREAT_THAN.equals(result) ){
               break;
           }
       }
       return result;
   }
   
   /**
    * 获取最终推送库存量
    * @param baseQuanties
    * @return
    */
	private Long getResultStock(Long baseQuanties){
		//如果库存为0或者空，直接返回0
		if(baseQuanties==null || baseQuanties==0){
			return 0l;
		}
       // 获取第三方平台天猫库存推送比例
       BaseSysCfgRespDTO unpfTMStockScale = SysCfgUtil.fetchSysCfg(UnpfConstants.StockLimit.UNPF_TM_STOCK_SCALE);
       // 获取第三方平台天猫库存推送上限
       BaseSysCfgRespDTO unpfTMStockLimit = SysCfgUtil.fetchSysCfg(UnpfConstants.StockLimit.UNPF_TM_STOCK_LIMTI);
       // 如果比例和上限都没配置或者为0，直接返回库存数
       if(StringUtil.isEmpty(unpfTMStockScale) && StringUtil.isEmpty(unpfTMStockLimit)){
			return baseQuanties;
		}		
       BigDecimal result = new BigDecimal(baseQuanties);
       if(StringUtil.isNotEmpty(unpfTMStockScale)){
       	result=result.multiply(new BigDecimal(unpfTMStockScale.getParaValue())).setScale(1, BigDecimal.ROUND_DOWN);
		}
       if(StringUtil.isNotEmpty(unpfTMStockLimit)){
       	if(result.compareTo(new BigDecimal(unpfTMStockLimit.getParaValue()))==1){
       		return new Long(unpfTMStockLimit.getParaValue());
       	}
       }
       return result.longValue();
	}
}
