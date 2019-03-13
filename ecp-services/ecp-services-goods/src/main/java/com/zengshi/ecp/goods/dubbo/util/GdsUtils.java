package com.zengshi.ecp.goods.dubbo.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.general.prom.dto.GdsPromDTO;
import com.zengshi.ecp.general.prom.interfaces.IPromMsg2SolrRSV;
import com.zengshi.ecp.general.prom.util.PromMsgUtil;
import com.zengshi.ecp.general.solr.message.DeltaMessage;
import com.zengshi.ecp.general.solr.message.GdsDeltaMessage;
import com.zengshi.ecp.general.solr.util.DeltaUtils;
import com.zengshi.ecp.general.solr.util.DeltaUtils.EDeltaType;
import com.zengshi.ecp.general.solr.util.DeltaUtils.EOperType;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaLibReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV;
import com.zengshi.ecp.server.front.dto.BaseStaff;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 商品公共工具类 Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月28日上午11:11:43 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
@Service("gdsUtils")
public class GdsUtils {

	private static final String MODULE = GdsUtils.class.getName();

	/**
	 * 图片后缀正则表达式。
	 */
	private static final Pattern PATTERN_IMAGE_SUFFIX = Pattern.compile("^.*\\.*(jpg|JPG|jpeg|JPEG|png|PNG)$", Pattern.CASE_INSENSITIVE);

	
	private static IGdsInfoExternalRSV gdsInfoExternalRSV;
	
	
	
	@Resource(name="gdsInfoExternalRSV")
	public void setGdsInfoExternalSV(IGdsInfoExternalRSV gdsInfoExternalRSV) {
        GdsUtils.gdsInfoExternalRSV = gdsInfoExternalRSV;
    }

    /**
	 * 
	 * isEqualsValid:(判断状态是否等于有效状态). <br/>
	 * 
	 * @author linwb3
	 * @param status
	 * @return
	 * @since JDK 1.6
	 */
	public static boolean isEqualsValid(String status) {

		if (GdsConstants.Commons.STATUS_VALID.equals(status)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * isEqualsValid:(判断状态是否等于无效状态). <br/>
	 * 
	 * @author linwb3
	 * @param status
	 * @return
	 * @since JDK 1.6
	 */
	public static boolean isEqualsInvalid(String status) {

		if (GdsConstants.Commons.STATUS_INVALID.equals(status)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * isEqualsValid:(判断状态是否等于无效状态). <br/>
	 * 
	 * @author linwb3
	 * @param status
	 * @return
	 * @since JDK 1.6
	 */
	public static boolean isEqualsInvalidOrNULL(String status) {

		if (status == null || GdsConstants.Commons.STATUS_INVALID.equals(status)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * isEqualsValid:(判断商品是否是上架状态). <br/>
	 * 
	 * @author linwb3
	 * @param status
	 * @return
	 * @since JDK 1.6
	 */
	public static boolean isOnShelves(String status) {

		if (GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES.equals(status)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * isEqualsValid:(判断商品是否是下架状态). <br/>
	 * 
	 * @author linwb3
	 * @param status
	 * @return
	 * @since JDK 1.6
	 */
	public static boolean isOffShelves(String status) {

		if (GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES.equals(status)) {
			return true;
		}
		return false;
	}

	/**
	 * sendGdsIndexMsg:(搜索增量消息服务). <br/>
	 * 
	 * @author linwb3
	 * @param gdsStatus
	 * @param tableName
	 * @param className
	 * @param Id
	 * @param catlogId
	 * @since JDK 1.6
	 */
	public static void sendGdsIndexMsg(String gdsStatus, String tableName, String className, Long Id, Long catlogId) {
		// 消息增量
		GdsDeltaMessage  msg=new GdsDeltaMessage();
		msg.setDeltaType_(EDeltaType.DBOBJECT);

		if (GdsUtils.isOnShelves(gdsStatus)) {
			try {
				
				msg.setOperType_(EOperType.UPDATE);
				msg.setOperType(EOperType.UPDATE.getType());
				msg.setCatlogIds(catlogId + "");
				List<String> ids=new ArrayList<String>();
                ids.add(Id + "");
                msg.setIds(ids);
				msg.setObjectNameEn(tableName);
				msg.setNeedRetry(true);
				DeltaUtils.sendDeltaMessage(msg);
				LogUtil.info(MODULE, "Send gdsInfo index create/update Message,Id is" + Id);
			} catch (Exception e) {
				LogUtil.error(className, "GdsInfo index create/update failed! please check you solr server Or check you MQ server!", e);
			}
		} else if (GdsUtils.isOffShelves(gdsStatus)) {
			try {
				
				msg.setOperType_(EOperType.DELETE);
				msg.setOperType(EOperType.DELETE.getType());
				msg.setCatlogIds(catlogId + "");
				List<String> ids=new ArrayList<String>();
                ids.add(Id + "");
                msg.setIds(ids);
				msg.setObjectNameEn(tableName);
				
				DeltaUtils.sendDeltaMessage(msg);
				LogUtil.info(MODULE, "Send gdsInfo index delete Message,Id is" + Id);
			} catch (Exception e) {
				LogUtil.error(className, "GdsInfo index delete failed! please check you solr server Or check you MQ server!", e);
			}
		} else if (StringUtils.isEmpty(gdsStatus)) {
			try {
				msg.setOperType_(EOperType.UPDATE);
				msg.setOperType(EOperType.UPDATE.getType());
				msg.setCatlogIds(catlogId + "");
				List<String> ids=new ArrayList<String>();
                ids.add(Id + "");
                msg.setIds(ids);
				msg.setObjectNameEn(tableName);
				
				DeltaUtils.sendDeltaMessage(msg);
				LogUtil.info(MODULE, "Send gdsInfo index create/update Message,Id is" + Id);
			} catch (Exception e) {
				LogUtil.error(className, "GdsInfo index update failed! please check you solr server Or check you MQ server!", e);
			}
		}
	}
	
	/**
	 * sendGdsIndexMsg:(搜索增量消息服务). <br/>
	 * 
	 * @author linwb3
	 * @param siteId
	 * @param catgCode 
	 * @since JDK 1.6
	 */
	public static void sendGdsIndexMsg(String siteId, String catgCode,String className) {
		GdsDeltaMessage  msg=new GdsDeltaMessage();
		msg.setDeltaType_(EDeltaType.DBOBJECT);
		
		// 消息增量
		if (StringUtil.isNotBlank(siteId)) {
			try {
				msg.setOperType_(EOperType.REFULL);
				msg.setSiteId(siteId);
				msg.setObjectNameEn("T_GDS_INFO");
				
				DeltaUtils.sendDeltaMessage(msg);
				LogUtil.info(MODULE, "Send gdsInfo index refull Message,siteId is" + siteId);
			} catch (Exception e) {
				LogUtil.error(className, "GdsInfo index refull failed! please check you solr server Or check you MQ server!", e);
			}
		} else if (StringUtil.isNotBlank(catgCode)) {
			try {
				msg.setOperType_(EOperType.CATG);
				msg.setOperType(EOperType.CATG.getType());
				msg.setCatgCodes(catgCode);
				msg.setObjectNameEn("T_GDS_INFO");
				
				DeltaUtils.sendDeltaMessage(msg);
				LogUtil.info(MODULE, "Send gdsInfo index  catg refull Message,catgCode is" + catgCode);
			} catch (Exception e) {
				LogUtil.error(className, "GdsInfo index catg refull failed! please check you solr server Or check you MQ server!", e);
			}
		}
	}

	
	public static void sendShopIndexMsg(Long shopId){
		try {
		DeltaMessage deltaMessage = new DeltaMessage();
		deltaMessage.setDeltaType_(EDeltaType.DBOBJECT);
		deltaMessage.setOperType_(EOperType.UPDATE);
		List<String> ids = new ArrayList<String>();
		ids.add(shopId.toString());
		deltaMessage.setIds(ids);
		deltaMessage.setObjectNameEn("T_SHOP_INFO");
		DeltaUtils.sendDeltaMessage(deltaMessage);
		LogUtil.info(MODULE, "Send gdsInfo index  update Message,shopId is" + shopId);

		}catch(Exception e){
		LogUtil.error(MODULE, "Send gdsInfo index  update Message fail, shopId is" + shopId + "error is" + e.toString());			
		}
	}
	
	/**
	 * 
	 * isEqualsValid:(判断商品是否是删除状态). <br/>
	 * 
	 * @author linwb3
	 * @param status
	 * @return
	 * @since JDK 1.6
	 */
	public static boolean isDelete(String status) {

		if (GdsConstants.GdsInfo.GDS_STATUS_DELETE.equals(status)) {
			return true;
		}
		return false;
	}

	/**
	 * 媒体库容量校验，新增
	 * 
	 * @param gdsMediaReqDTO
	 * @param gdsMediaLib
	 * @return
	 */
	public static void mediaCapacityValidate(GdsMediaReqDTO gdsMediaReqDTO, GdsMediaLibReqDTO gdsMediaLib) throws BusinessException {

		long surplus = 0;

		if (StringUtils.equals(gdsMediaReqDTO.getMediaType(), GdsConstants.GdsMedia.MEDIA_TYPE_AUDIO)) {
			surplus = gdsMediaLib.getaLimit() - (gdsMediaReqDTO.getMediaSize() + gdsMediaLib.getaNow());
		} else if (StringUtils.equals(gdsMediaReqDTO.getMediaType(), GdsConstants.GdsMedia.MEDIA_TYPE_PIC)) {
			surplus = gdsMediaLib.getpLimit() - (gdsMediaReqDTO.getMediaSize() + gdsMediaLib.getpNow());
		} else if (StringUtils.equals(gdsMediaReqDTO.getMediaType(), GdsConstants.GdsMedia.MEDIA_TYPE_VEDIO)) {
			surplus = gdsMediaLib.getvLimit() - (gdsMediaReqDTO.getMediaSize() + gdsMediaLib.getvNow());
		} else {
			throw new BusinessException(GdsErrorConstants.GdsMedia.ERROR_GOODS_MEDIA_200252);
		}

		if (surplus < 0) {
			throw new BusinessException(GdsErrorConstants.GdsMedia.ERROR_GOODS_MEDIA_200253);
		}

	}

	/**
	 * 媒体库容量校验，修改
	 * 
	 * @param gdsMediaReqDTO
	 * @param gdsMediaLib
	 * @return
	 */
	public static void mediaCapacityValidate(GdsMediaReqDTO originalGdsMedia, GdsMediaReqDTO gdsMediaReqDTO, GdsMediaLibReqDTO gdsMediaLib) throws BusinessException {

		long surplus = 0;

		if (StringUtils.equals(gdsMediaReqDTO.getMediaType(), GdsConstants.GdsMedia.MEDIA_TYPE_AUDIO)) {
			surplus = (gdsMediaLib.getaLimit() + originalGdsMedia.getMediaSize()) - (gdsMediaReqDTO.getMediaSize() + gdsMediaLib.getaNow());
		} else if (StringUtils.equals(gdsMediaReqDTO.getMediaType(), GdsConstants.GdsMedia.MEDIA_TYPE_PIC)) {
			surplus = (gdsMediaLib.getpLimit() + originalGdsMedia.getMediaSize()) - (gdsMediaReqDTO.getMediaSize() + gdsMediaLib.getpNow());
		} else if (StringUtils.equals(gdsMediaReqDTO.getMediaType(), GdsConstants.GdsMedia.MEDIA_TYPE_VEDIO)) {
			surplus = (gdsMediaLib.getvLimit() + originalGdsMedia.getMediaSize()) - (gdsMediaReqDTO.getMediaSize() + gdsMediaLib.getvNow());
		} else {
			throw new BusinessException(GdsErrorConstants.GdsMedia.ERROR_GOODS_MEDIA_200252);
		}

		if (surplus < 0) {
			throw new BusinessException(GdsErrorConstants.GdsMedia.ERROR_GOODS_MEDIA_200253);
		}

	}

	/**
	 * 
	 * 执行对像序列到目标对象序列的转换.
	 * 
	 * @author liyong7
	 * @param sources
	 * @param clazz
	 * @return
	 * @since JDK 1.6
	 */
	public static <SOURCE, TARGET> List<TARGET> doConvert(List<SOURCE> sources, Class<TARGET> clazz) {
		if (!CollectionUtils.isEmpty(sources)) {
			List<TARGET> result = new ArrayList<>();
			try {
				for (SOURCE source : sources) {
					TARGET t = clazz.newInstance();
					ObjectCopyUtil.copyObjValue(source, t, null, false);
					result.add(t);
				}
				return result;
			} catch (Exception e) {
				throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099, new String[] { Thread.currentThread().getStackTrace()[1].getMethodName() });
			}
		}
		return null;
	}

	/**
	 * 
	 * 执行对像序列到目标对象序列的转换.
	 * 
	 * @author linwb3
	 * @param sources
	 * @param clazz
	 * @param sourceField
	 *            额外字段的拷贝，名称相等字段（例如从source的id字段copy到target的skuId字段，
	 *            则sourceField为id， targetField为skuId）
	 * @param targetField
	 *            额外字段的拷贝，名称相等字段（例如从source的id字段copy到target的skuId字段，
	 *            则sourceField为id， targetField为skuId）
	 * @return
	 * @since JDK 1.6
	 */
	public static <SOURCE, TARGET> List<TARGET> doConvert(List<SOURCE> sources, Class<TARGET> clazz, String[] sourceField, String[] targetField) {
		if (!CollectionUtils.isEmpty(sources)) {
			List<TARGET> result = new ArrayList<>();
			try {
				for (SOURCE source : sources) {
					TARGET t = clazz.newInstance();
					ObjectCopyUtil.copyObjValue(source, t, null, false);

					if (sourceField != null && targetField != null) {
						if (sourceField.length == targetField.length) {
							for (int i = 0; i < targetField.length; i++) {
								String sname = sourceField[i];
								BeanUtils.setProperty(t, targetField[i], MethodUtils.invokeMethod(source, "get" + sname.substring(0, 1).toUpperCase() + sname.substring(1), null));
							}
						} else {
							throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099, new String[] { Thread.currentThread().getStackTrace()[1].getMethodName() });
						}
					}

					result.add(t);
				}
				return result;
			} catch (Exception e) {
				throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099, new String[] { Thread.currentThread().getStackTrace()[1].getMethodName() });
			}
		}
		return null;
	}

	/**
	 * 
	 * 执行对像序列到目标对象序列的转换.
	 * 
	 * @author linwb3
	 * @param sources
	 * @param clazz
	 * @param sourceField
	 *            额外字段的拷贝，名称相等字段（例如从source的id字段copy到target的skuId字段，
	 *            则sourceField为id， targetField为skuId）
	 * @param targetField
	 *            额外字段的拷贝，名称相等字段（例如从source的id字段copy到target的skuId字段，
	 *            则sourceField为id， targetField为skuId）
	 * @return
	 * @since JDK 1.6
	 */
	public static <SOURCE, TARGET> TARGET doObjConvert(SOURCE source, Class<TARGET> clazz, String[] sourceField, String[] targetField) {
		try {
			TARGET t = clazz.newInstance();
			ObjectCopyUtil.copyObjValue(source, t, null, false);

			if (sourceField != null && targetField != null) {
				if (sourceField.length == targetField.length) {
					for (int i = 0; i < targetField.length; i++) {
						String sname = sourceField[i];
						BeanUtils.setProperty(t, targetField[i], MethodUtils.invokeMethod(source, "get" + sname.substring(0, 1).toUpperCase() + sname.substring(1), null));
					}
				} else {
					throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099, new String[] { Thread.currentThread().getStackTrace()[1].getMethodName() });
				}
			}
			return t;
		} catch (Exception e) {
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099, new String[] { Thread.currentThread().getStackTrace()[1].getMethodName() });
		}
	}

	/**
	 * 
	 * convertMapToList:(map转换List). <br/>
	 * 
	 * @author linwb3
	 * @param maps
	 * @return
	 * @since JDK 1.6
	 */
	public static <KEY, VALUE> List<VALUE> convertMapToList(Map<KEY, VALUE> maps) {

		if (org.springframework.util.CollectionUtils.isEmpty(maps)) {
			return null;
		}
		Set<KEY> keys = maps.keySet();
		List<VALUE> values = new ArrayList<VALUE>();
		for (KEY key : keys) {
			values.add(maps.get(key));
		}
		return values;
	}

	/**
	 * 
	 * convertListToMapList:(将List<Object>转换为 List<Map<String,Object>>数组). <br/>
	 * 
	 * @author linwb3
	 * @param maps
	 * @return
	 * @since JDK 1.6
	 */
	@SuppressWarnings("rawtypes")
	public static <TARGET> List<Map<String, Object>> convertListToMapList(List<TARGET> list, Class cls) {
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		if (CollectionUtils.isEmpty(list)) {
			return maps;
		}
		for (TARGET target : list) {
			maps.add(ConvertObjToMap(target, cls));
		}
		return maps;
	}

	@SuppressWarnings("rawtypes")
	public static <TARGET> Map<String, Object> ConvertObjToMap(TARGET target, Class cls) {
		Map<String, Object> map = new HashMap<String, Object>();
		String sname = "";
		Field[] fields = cls.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			try {
				sname = fields[i].getName();
				if (sname.equals("serialVersionUID")) {
					continue;
				}
				// 检查vo 是否有该属性的 PropertyDescriptor ;如果没有，或者读取方法为空，那么则继续；
				PropertyDescriptor descriptor = PropertyUtils.getPropertyDescriptor(target, sname);
				if (descriptor == null || descriptor.getReadMethod() == null) {
					continue;
				}
				// 获取属性值
				Object obj = MethodUtils.invokeMethod(target, "get" + sname.substring(0, 1).toUpperCase() + sname.substring(1), null);
				map.put(sname, obj);
			} catch (Exception e) {

			}
		}

		return map;
	}

	/**
	 * 
	 * convertArrToList:(数组转List). <br/>
	 * 
	 * @author linwb3
	 * @param obj
	 * @param ta
	 * @return
	 * @since JDK 1.6
	 */
	public static <TARGET> List<TARGET> convertArrToList(TARGET[] obj) {
		List<TARGET> list = new ArrayList<TARGET>();
		if (!ArrayUtils.isEmpty(obj)) {
			for (TARGET target : obj) {
				list.add(target);
			}
		}
		return list;
	}

	/**
	 * 
	 * convertListToArr:(List转数组). <br/>
	 * 
	 * @author linwb3
	 * @param obj
	 * @param ta
	 * @return
	 * @since JDK 1.6
	 */
	public static String[] convertListToArr(List<String> list) {
		String[] arr = null;
		if (CollectionUtils.isNotEmpty(list)) {
			arr = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				String obj = list.get(i);
				arr[i] = obj;
			}
		}
		return arr;
	}

	public static List<String> getNoDeleteStatusList() {
		List<String> status = new ArrayList<String>();
		status.add(GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES);
		status.add(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
		status.add(GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES);
		return status;
	}

	public static String[] getNoDeleteStatusArr() {
		String[] status = new String[] { GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES, GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES, GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES, GdsConstants.GdsInfo.GDS_STATUS_OFFLINE };
		return status;
	}

	public static List<String> getDeleteStatusList() {
		List<String> status = new ArrayList<String>();
		status.add(GdsConstants.GdsInfo.GDS_STATUS_DELETE);
		return status;
	}

	public static String[] getDeleteStatusArr() {
		String[] status = new String[] { GdsConstants.GdsInfo.GDS_STATUS_DELETE };
		return status;
	}

	public static String getGdsUrl(Long gdsId, Long skuId, Long catLogId) {
		String url = "";
		if (catLogId != null && catLogId == 2) {

			url = "/gdspointdetail/gdsId-skuId";
		} else {

			url = "/gdsdetail/gdsId-skuId";
		}
		if (gdsId != null) {
			url = url.replace("gdsId", gdsId.toString());
		}
		if (skuId != null) {
			url = url.replace("skuId", skuId.toString());
		} else {
			url = url.replace("skuId", "");
		}
		return url;
	}

	/**
	 * 
	 * FormetFileSize:(格式化文件大小显示). <br/>
	 * 
	 * @author linwb3
	 * @param fileS
	 * @return
	 * @since JDK 1.6
	 */
	public static String FormatFileSize(long fileS) {// 转换文件大小
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

	/**
	 * 
	 * 必传参数检测，仅对普通参数进行判空处理，抛出异常信息为:必传参数{0}缺失!<br/>
	 * 会对嵌套对象，如果外围对外为空，一并传入判断那么该方法会报空。<br/>
	 * 举例：当外围对象为reqDTO为空时如果按以下格式调用则会报空: 其中两个参数的params与msgs的数组长度要一致。
	 * 
	 * @author liyong7
	 * @param params
	 * @param msgs
	 * @since JDK 1.6
	 */
	public static void paramCheck(Object[] params, String[] msgs) {
		if (null != params && null != msgs && params.length == msgs.length) {
			StringBuffer errorMsg = new StringBuffer();
			for (int i = 0; i < params.length; ++i) {
				Object obj = params[i];
				if (obj instanceof String) {
					if (StringUtil.isBlank((String) obj)) {
						errorMsg.append(msgs[i]);
						errorMsg.append(",");
					}
				} else if (obj instanceof Object[]) {
					if (obj == null || ((Object[]) obj).length == 0) {
						errorMsg.append(msgs[i]);
						errorMsg.append(",");
					}
				} else if (obj instanceof Collection<?>) {
					if (obj == null || CollectionUtils.isEmpty((Collection<?>) obj)) {
						errorMsg.append(msgs[i]);
						errorMsg.append(",");
					}
				} else {
					if (null == obj) {
						errorMsg.append(msgs[i]);
						errorMsg.append(",");
					}
				}
			}
			if (0 < errorMsg.length()) {
				errorMsg.deleteCharAt(errorMsg.length() - 1);
				throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100, new String[] { errorMsg.toString() });
			}
		} else {
			LogUtil.error(MODULE, "参数检测方法执行异常！请保证params与msgs不为空，并且两个参数数组长度一致");
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099, new String[] { Thread.currentThread().getStackTrace()[1].getMethodName() });
		}
	}

	/**
	 *
	 * checkStcokStatus:(获取库存状态). <br/>
	 * 
	 * @author linwb3
	 * @param count
	 * @return
	 * @since JDK 1.6
	 */
	public static String checkStcokStatus(Long count) {
	    if(count == null){
	        count = 0L;
	    }
		if (count <= Long.parseLong(SysCfgUtil.fetchSysCfg(GdsConstants.GdsStock.STOCK_LACK_THRESHOLD).getParaValue())) {
			return GdsConstants.GdsStock.STOCK_STATUS_LACK;
		} else if (count <= Long.parseLong(SysCfgUtil.fetchSysCfg(GdsConstants.GdsStock.STOCK_HARDTOGET_THRESHOLD).getParaValue())) {
			return GdsConstants.GdsStock.STOCK_STATUS_HARDTOGET;
		} else {
			return GdsConstants.GdsStock.STOCK_STATUS_ENOUGH;
		}
	}

	/**
	 * 
	 * checkScoreStcokStatus:(获取积分商品库存状态). <br/>
	 * 
	 * @author zjh
	 * @param count
	 * @return
	 * @since JDK 1.6
	 */
	public static String checkScoreStcokStatus(Long count) {
	    if(count == null){
            count = 0L;
        }
		if (count <= Long.parseLong(SysCfgUtil.fetchSysCfg(GdsConstants.GdsStock.STOCK_SCORE_LACK_THRESHOLD).getParaValue())) {
			return GdsConstants.GdsStock.STOCK_STATUS_LACK;
		} else if (count <= Long.parseLong(SysCfgUtil.fetchSysCfg(GdsConstants.GdsStock.STOCK_SCORE_HARDTOGET_THRESHOLD).getParaValue())) {
			return GdsConstants.GdsStock.STOCK_STATUS_HARDTOGET;
		} else {
			return GdsConstants.GdsStock.STOCK_STATUS_ENOUGH;
		}
	}

	/**
	 * 
	 * checkStcokStatusDesc:(获取库存描述). <br/>
	 * 
	 * @author linwb3
	 * @param count
	 * @return
	 * @since JDK 1.6
	 */
	public static String checkStcokStatusDesc(Long count) {
	    if(count == null){
            count = 0L;
        }
		if (count <= Long.parseLong(SysCfgUtil.fetchSysCfg(GdsConstants.GdsStock.STOCK_LACK_THRESHOLD).getParaValue())) {
			return "无货";
		} else if (count <= Long.parseLong(SysCfgUtil.fetchSysCfg(GdsConstants.GdsStock.STOCK_HARDTOGET_THRESHOLD).getParaValue())) {
			return "紧张";
		} else {
			return "充足";
		}
	}

	/**
	 * 
	 * checkStcokStatusDesc:(获取库存描述). <br/>
	 * 
	 * @author linwb3
	 * @param count
	 * @return
	 * @since JDK 1.6
	 */
	public static String checkStcokStatusDesc(Long count, long typeId) {
	    LongReqDTO longReq = new LongReqDTO();
	    longReq.setId(typeId);
	    if(!gdsInfoExternalRSV.isNeedStockAmount(longReq)){
	        return "充足";
	    }else {
			return checkStcokStatusDesc(count);
		}
	}

	public static boolean isPdf(String suffix) {
		if (StringUtil.isNotBlank(suffix)) {
			return ".pdf".equalsIgnoreCase(suffix) || "pdf".equalsIgnoreCase(suffix);
		}
		return false;
	}

	public static boolean isImage(String suffix) {
		if (StringUtil.isNotBlank(suffix)) {
			return PATTERN_IMAGE_SUFFIX.matcher(suffix).matches();
		}
		return false;
	}

	public static Long getDiscountPrice(Long price, BigDecimal discount) {
	    if(price == null ){
	        price=0L;
	    }
		if (discount.doubleValue() == 1d) {
			return price;
		}
        Long discountPrice = discount.divide(new BigDecimal(100d)).setScale(4,BigDecimal.ROUND_UP).multiply(new BigDecimal(price)).setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
        if(discountPrice.longValue() <= 0){
            discountPrice=1L;
        }
		return discountPrice;
	}
	
	/**
	 *
	 * getCatgs:(处理商品归属分类规则，取单品/商品的platcatgs字段传入). <br/> 
	 * 
	 * @author linwb3
	 * @param catg
	 * @return 
	 * @since JDK 1.6
	 */
	public static String[] getCatgs(String catg) {
        if (StringUtils.isNotBlank(catg) && catg.length() > 2) {
            catg = catg.substring(1, catg.length());
            catg = catg.substring(0, catg.length() - 1);
            return catg.split("><");
        }
        return null;
    }
	
	public static void sendPromMsg(Long shopId,Long gdsId,Long skuId,String gdsStatus,IPromMsg2SolrRSV promMsg2SolrRSV) {
	    try {
	        if (GdsUtils.isOffShelves(gdsStatus)) {
	            PromMsgUtil.sendPromIndexMsg(null, null, skuId, "20",MODULE);
	        } else if (GdsUtils.isOnShelves(gdsStatus)) {
	            //新增
	        	GdsPromDTO queryProm=new GdsPromDTO();
	            queryProm.setGdsId(gdsId);
	            queryProm.setShopId(shopId);
	            queryProm.setSkuId(skuId);
	            promMsg2SolrRSV.sendMsg2SolrByNewSku(queryProm);
	        }
	        LogUtil.debug(MODULE, "Send gdsInfo index  catg refull Message,skuId is" + skuId);
        } catch (Exception e) {
            LogUtil.error(MODULE, "GdsInfo index catg refull failed! please check you solr server Or check you MQ server!skuId is "+skuId, e);
        }
    }
	
	public static BaseStaff getStaff(Long staffId){
	    BaseStaff staff=new BaseStaff();
	    staff.setId(staffId);
	    return staff;
	}
	
	
	/**
     * 
     * isSupportFacStock:是否支持工厂库存. <br/> 
     * 
     * @author liyong7
     * @return 
     * @since JDK 1.6
     */
    public static boolean isSupportFacStock(){
        boolean result = false;
        try{
            BaseSysCfgRespDTO sysCfg = SysCfgUtil.fetchSysCfg(GdsConstants.GdsStock.FLAG_SUPPORT_FACSTOCK);
            if(GdsConstants.Commons.STATUS_VALID.equals(sysCfg.getParaValue())){
                result = true;
            }
        }catch(Exception e){
            
        }
        return result;
    }

}
