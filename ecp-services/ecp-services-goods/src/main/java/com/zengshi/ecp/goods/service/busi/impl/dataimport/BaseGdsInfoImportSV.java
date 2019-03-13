package com.zengshi.ecp.goods.service.busi.impl.dataimport;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.service.busi.interfaces.gdslog.OperationType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.goods.dao.model.GdsInterfaceGds;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfo;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsDataImportConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsDataImportErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsInterfaceGdsReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoAddReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PropReqDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsInterfaceGdsSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoManageSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoManageSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoQuerySV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySyncSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 数据更新封装<br>
 * Date:2015年10月27日下午2:30:25 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
public abstract class BaseGdsInfoImportSV {

	private final String MODULE="【商品数据导入接口："+getClass().getName()+"】";

	@Autowired(required = false)
	protected IGdsInfoManageSV gdsInfoManageSV;

	@Resource
	protected IGdsSkuInfoManageSV gdsSkuInfoManageSV;

	@Resource
	protected IGdsInfoQuerySV gdsInfoQuerySV;
	
	@Resource
    protected IGdsSkuInfoQuerySV gdsSkuInfoQuerySV;

	@Resource
	protected IGdsInterfaceGdsSV gdsInterfaceGdsSV;

//	@Resource(name = "erpGdsCategoryImportSV")
//	protected IERPGdsCategoryImportSV gdsCategoryImportSV;

//	@Resource
//	protected IGdsInterfaceCatgSV gdsInterfaceCatgSV;
	
	@Resource
	protected IGdsCategorySV gdsCategorySV;
	
	@Resource
	protected IGdsCategorySyncSV gdsCategorySyncSV;

	/**
	 * 增加基础校验
	 * 
	 * @param map
	 * @throws BusinessException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void addValidation(Map map) throws BusinessException {
		if (!this.isNotNull_(map,this.getPkKeyName())) {
			throw new BusinessException(
					GdsDataImportErrorConstants.Commons.ERROR_GOODS_DATAIMPORT_5,
					new String[] { map.toString() });
		}

		if (!this.isNotNull_(map,this.getGdsNameKeyName())) {

			// 商品名称为空，做填值处理。空格出现被trim情况。
		    map.put(this.getGdsNameKeyName(), "-");

			// throw new BusinessException(
			// GdsDataImportErrorConstants.Commons.ERROR_GOODS_DATAIMPORT_6,
			// new String[] { map.toString() });
		}
	}

	/**
	 * 更新基础校验
	 * 
	 * @param map
	 * @throws BusinessException
	 */
	@SuppressWarnings("rawtypes")
	protected void updateValidation(Map map) throws BusinessException {
		if (!this.isNotNull_(map,this.getPkKeyName())) {
			throw new BusinessException(
					GdsDataImportErrorConstants.Commons.ERROR_GOODS_DATAIMPORT_5,
					new String[] { map.toString() });
		}
	}

	/**
	 * 删除基础校验
	 * 
	 * @param map
	 * @throws BusinessException
	 */
	@SuppressWarnings("rawtypes")
	protected void deleteValidation(Map map) throws BusinessException {
		if (!this.isNotNull_(map,this.getPkKeyName())) {
			throw new BusinessException(
					GdsDataImportErrorConstants.Commons.ERROR_GOODS_DATAIMPORT_5,
					new String[] { map.toString() });
		}
	}

	/**
	 * 动作类型
	 * 
	 * @param map
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings("rawtypes")
	protected abstract EActionType doActionType(Map map)
			throws BusinessException;

	/**
	 * 数据来源类型
	 * 
	 * @return
	 * @throws BusinessException
	 */
	protected abstract String getOriginType() throws BusinessException;

	/**
	 * 主键键名
	 * 
	 * @return
	 * @throws BusinessException
	 */
	protected abstract String getPkKeyName() throws BusinessException;

	/**
	 * 商品名称键名
	 * 
	 * @return
	 * @throws BusinessException
	 */
	protected abstract String getGdsNameKeyName() throws BusinessException;

	/**
	 * 获取商品上架状态
	 * // 新增商品默认是待上架状态
     * // 非待上架(上架或下架)，则需要执行上下架操作
	 * 
	 * @param map
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings("rawtypes")
	protected abstract String getGdsStatus(Map map, List<String> list)
			throws BusinessException;

	/**
	 * 获取商品编码映射前缀，因为不能排除多个第三方系统之间出现商品编码冲突的问题
	 * 
	 * @param map
	 * @return
	 * @throws BusinessException
	 */
	protected abstract String getSrcGdsCodePrefix() throws BusinessException;

	/**
	 * 获取客户Id
	 * @return
	 * @throws BusinessException
     */
	protected abstract Long getStaffId() throws BusinessException;

	/**
	 * 获取companyId
	 * @return
	 * @throws BusinessException
     */
	protected abstract Long getCompanyId() throws BusinessException;

//	/**
//	 * 获取商品分类编码映射前缀，因为不能排除多个第三方系统之间出现商品分类编码冲突的问题
//	 * 
//	 * @param map
//	 * @return
//	 * @throws BusinessException
//	 */
//	protected abstract String getSrcGdsCatgCodePrefix()
//			throws BusinessException;

	/**
	 * 保存商品信息
	 * 
	 * @param map
	 * @throws BusinessException
	 */
	@SuppressWarnings({ "rawtypes" })
	public GdsInfoRespDTO saveGdsInfo(Map map) throws BusinessException {

		// try{

		// 没有数据到达
		if (map == null || map.size() == 0) {
			return null;
		}

//		this.map = map;

		// 实际动作类型，在来源数据指定操作类型是增加的时候，若关系表关系已经存在则会采用更新操作。这时候的实际操作类型就是更新而不是添加.
		EActionType realActionType = this.doActionType(map);

		if (realActionType == null) {
			throw new BusinessException(
					GdsDataImportErrorConstants.Commons.ERROR_GOODS_DATAIMPORT_1,
					new String[] { map.toString() });
		}

		switch (realActionType.getActionType()) {
		case 1:
			// 添加校验必须放到添加内部根据实际操作类型选择判断流程
			return this.add(map);
		case 2:
			this.deleteValidation(map);
			return this.delete(map);
		case 3:
			this.updateValidation(map);
			return this.update(map);
		default:
			throw new BusinessException(
					GdsDataImportErrorConstants.Commons.ERROR_GOODS_DATAIMPORT_1,
					new String[] { map.toString() });
		}

		// }catch(Exception e){
		//
		// //记录错误信息
		// map.put(GdsDataImportConstants.Commons.KEY_FAIL_MESSAGE,
		// e.getMessage());
		//
		// throw e;
		// }

	}

	/**
	 * 下架
	 * 
	 * @param map
	 * @throws BusinessException
	 */
	@SuppressWarnings("rawtypes")
	public GdsInfoRespDTO offShelves(Map map) throws BusinessException {

		// 没有数据到达
		if (map == null || map.size() == 0) {
			return null;
		}

		if (!this.isNotNull_(map,getPkKeyName())) {
			throw new BusinessException(
					GdsDataImportErrorConstants.Commons.ERROR_GOODS_DATAIMPORT_5,
					new String[] { map.toString() });
		}

		String benbanbianhao = this.getValue_(map,getPkKeyName());

		// =================根据中间映射表判断商品是否存在，不存在直接抛出异常=================
		GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO = new GdsInterfaceGdsReqDTO();
		gdsInterfaceGdsReqDTO.setOriginGdsId(this.getSrcGdsCodePrefix()
				+ benbanbianhao);
		gdsInterfaceGdsReqDTO.setOrigin(this.getOriginType());
		GdsInterfaceGds gdsInterfaceGds = this.gdsInterfaceGdsSV
				.queryGdsInterfaceGdsByOriginGdsId(gdsInterfaceGdsReqDTO);
		if (gdsInterfaceGds == null) {

		    // 不存在直接抛出异常
			throw new BusinessException(
					GdsDataImportErrorConstants.Commons.ERROR_GOODS_DATAIMPORT_4,
					new String[] { map.toString() });
		}

		// ===============================下架================================
		List<Long> skuIds=this.executeGdsShelves(GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES,
				gdsInterfaceGds.getGdsId(), gdsInterfaceGds.getShopId());
		GdsInfoRespDTO gdsInfo=new GdsInfoRespDTO();
		gdsInfo.setId(gdsInterfaceGds.getGdsId());
		gdsInfo.setSkuIds(skuIds);
		gdsInfo.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES);
		return gdsInfo;
	}

	/**
	 * 添加商品
	 * 
	 * @param map
	 * @throws BusinessException
	 */
	@SuppressWarnings("rawtypes")
	protected GdsInfoRespDTO add(Map map) throws BusinessException {

		if (!this.isNotNull_(map,this.getPkKeyName())) {
			throw new BusinessException(
					GdsDataImportErrorConstants.Commons.ERROR_GOODS_DATAIMPORT_5,
					new String[] { map.toString() });
		}

		String benbanbianhao = this.getValue_(map,this.getPkKeyName());

		// =================根据中间映射表判断商品是否存在，若已经存在采用更新处理，否则无法保证商品映射一对一关系=================
		GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO = new GdsInterfaceGdsReqDTO();
		gdsInterfaceGdsReqDTO.setOriginGdsId(this.getSrcGdsCodePrefix()
				+ benbanbianhao);
		gdsInterfaceGdsReqDTO.setOrigin(this.getOriginType());
		GdsInterfaceGds gdsInterfaceGds = this.gdsInterfaceGdsSV
				.queryGdsInterfaceGdsByOriginGdsId(gdsInterfaceGdsReqDTO);

		// 若已经存在采用更新处理，否则无法保证商品映射一对一关系
		if (gdsInterfaceGds != null) {

			// 若关系表关系已经存在则会采用更新操作。这时候的实际操作类型就是更新而不是添加.
//			this.realActionType = EActionType.UPDATE;

			this.updateValidation(map);

			// 更新结束
			return this.update(map);
		}

		this.addValidation(map);

		// ===============================添加/保存商品================================
		GdsInfoAddReqDTO gdsInfoAddReqDTO = this.createAddGdsInfoParam(map);
		
		//设置查配置表，解决ifbasic属性为空问题。
		gdsInfoAddReqDTO.getGdsInfoReqDTO().setCopyPropFromConfiged(true);

		//不维护商品编码映射表
		gdsInfoAddReqDTO.setIfMaintainGdsInterfaceGds(false);
		
		GdsInfoRespDTO gdsInfo = this.gdsInfoManageSV
				.addGdsInfo(gdsInfoAddReqDTO);

		// ======================================执行上/下架===============================================
		// 避免空指针
		List<String> list = new ArrayList<String>();

		String gdsStatus = this.getGdsStatus(map, list);
		
		// 新增商品默认是待上架状态
		// 非待上架(上架或下架)，则需要执行上下架操作
		if (!GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES.equals(gdsStatus)) {
			GdsSkuInfoReqDTO gdsSkuInfoReqDTO=gdsInfoAddReqDTO.getSkuInfoReqDTOs().get(0);
			if (gdsSkuInfoReqDTO!=null && gdsSkuInfoReqDTO.getCommonPrice()!=null && gdsSkuInfoReqDTO.getCommonPrice()!= 0L) {
				this.executeGdsShelves(gdsStatus,
						gdsInfo.getId(), gdsInfo.getShopId());
			}
		}

		// ======================================商品编码关系表写入===============================================
		Boolean ignoreWriteRel = Boolean.valueOf(String.valueOf(map.get(GdsDataImportConstants.Commons.IS_IGNORE_WRITE_REL)));
		gdsInterfaceGdsReqDTO = new GdsInterfaceGdsReqDTO();
		gdsInterfaceGdsReqDTO.setGdsId(gdsInfo.getId());
		gdsInterfaceGdsReqDTO.setSkuId(gdsInfo.getSkuIds().get(0));// 商品、单品一对一关系
		gdsInterfaceGdsReqDTO.setShopId(gdsInfo.getShopId());
		gdsInterfaceGdsReqDTO.setOriginGdsId(this.getSrcGdsCodePrefix()
				+ benbanbianhao);
		gdsInterfaceGdsReqDTO.setOriginSkuId(this.getSrcGdsCodePrefix()
				+ benbanbianhao);
		gdsInterfaceGdsReqDTO.setOrigin(this.getOriginType());
		gdsInterfaceGdsReqDTO
				.setCreateStaff(this.getStaffId());
		
		if(!ignoreWriteRel){
		    this.gdsInterfaceGdsSV.saveGdsInterfaceGds(gdsInterfaceGdsReqDTO);
		}
		
		gdsInfo.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);

		//返回操作类型
		gdsInfo.setOperType(OperationType.GDS_ADD.getName());
		return gdsInfo;
	}

	/**
	 * 创建添加商品的全部信息，不包含主键信息
	 * 
	 * @param map
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings("rawtypes")
	protected abstract GdsInfoAddReqDTO createAddGdsInfoParam(Map map)
			throws BusinessException;

	/**
	 * 上、下架
	 * 
	 * @param gdsStatus
	 * @param gdsId
	 * @param shopId
	 * @throws BusinessException
	 */
	private List<Long> executeGdsShelves(String gdsStatus, Long gdsId, Long shopId)
			throws BusinessException {
		GdsInfoReqDTO req = new GdsInfoReqDTO();
		req.setGdsStatus(gdsStatus);
		req.setId(gdsId);
		req.setShopId(shopId);

		//不维护商品编码映射表
		req.setIfMaintainGdsInterfaceGds(false);

		return this.gdsInfoManageSV.executeGdsShelves(req);
	}

	/**
	 * 值不为空,代码中的key全部默认使用小写方式
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("rawtypes")
    protected boolean isNotNull_(Map map,String key) {
		return isNotNull(map, key);
	}

	/**
	 * 值不为空,代码中的key全部默认使用小写方式
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNotNull(Map map, String key) {
		if (!map.containsKey(key) && !map.containsKey(key.toUpperCase())) {
			return false;
		}

		if (null == map.get(key)&&null == map.get(key.toUpperCase())) {
			return false;
		}

		// String.valueOf(null)-"null"
		boolean lowerCaseFlag = StringUtils.isNotBlank(String.valueOf(map
				.get(key)))
				&& !StringUtils.equals("null", String.valueOf(map.get(key)))
				&& !StringUtils.equals("NULL", String.valueOf(map.get(key)));

		String upperCaseKey = key.toUpperCase();
		boolean upperCaseFlag = StringUtils.isNotBlank(String.valueOf(map
				.get(upperCaseKey)))
				&& !StringUtils.equals("null",
						String.valueOf(map.get(upperCaseKey)))
				&& !StringUtils.equals("NULL",
						String.valueOf(map.get(upperCaseKey)));

		return lowerCaseFlag || upperCaseFlag;
	}

	/**
	 * 代码中的key全部默认使用小写方式
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("rawtypes")
    protected String getValue_(Map map,String key) {
		return getValue(map, key);
	}

	/**
	 * 代码中的key全部默认使用小写方式
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getValue(Map map, String key) {

		Object o = map.get(key);

		// String.valueOf(null)-"null"
		if (null == o) {
			o = map.get(key.toUpperCase());
			if(o==null){
				return "";
			}
		}

		String value = String.valueOf(o);
		if (StringUtils.isBlank(value) || StringUtils.equals("null", value)
				|| StringUtils.equals("NULL", value)) {

			return "";
		}

		return value;
	}

	/**
	 * 添加MongoDB内容存储
	 * 
	 * @param content
	 * @param fileName
	 * @return
	 */
	protected String saveToMongoDB(String content, String fileName) {
		try {
			return FileUtil.saveFile(content.getBytes("utf-8"), fileName,
					".html");
		} catch (BusinessException e) {
			LogUtil.error(MODULE+"保存富文本出错,原因---" + e.getMessage(), "", e);
		} catch (UnsupportedEncodingException e) {
			LogUtil.error(MODULE+"保存富文本出错,原因---" + e.getMessage(), "", e);
		}
		return null;
	}

	/**
	 * 更新MongoDB内容存储
	 * 
	 * @param content
	 * @param fileId
	 * @param fileName
	 * @return
	 */
	protected String updateMongoDB(String content, String fileId,
			String fileName) {
		try {
			return FileUtil.updateFile(content.getBytes("utf-8"), fileId,
					fileName, ".html");
		} catch (BusinessException e) {
			LogUtil.error(MODULE+"更新富文本出错,原因---" + e.getMessage(), "", e);
		} catch (UnsupportedEncodingException e) {
			LogUtil.error(MODULE+"更新富文本出错,原因---" + e.getMessage(), "", e);
		} catch (Exception e) {
			LogUtil.error(MODULE+"更新富文本出错,原因---" + e.getMessage(), "", e);
		}
		return null;
	}

	/**
	 * 根据属性列表和更新规则(属性、字段)列表创建商品属性创建/更新参数对象
	 * 
	 * @param propInfoList
	 * @param list
	 * @return
	 * @throws BusinessException
	 */
	protected List<GdsGds2PropReqDTO> createGdsGds2PropReqDTOList(
			List<String[]> propInfoList, List<String> list)
			throws BusinessException {

		// 属性类型：1为规格属性 2为参数属性 3为普通属性
		// 属性值类型（冗余）：1为手工输入 2单选多值 3位多选多值
		// 属性值手工输入类型：1为文本（数字或者字符） 2为日期 3为富文本编辑器 （当前仅当值为手工录入时才会有值）
		// 属性定义类型：属性定义类型　１为系统自带属性　　２为自定义属性

		List<GdsGds2PropReqDTO> gdsGds2PropReqDTOList = new ArrayList<GdsGds2PropReqDTO>();

		if (CollectionUtils.isEmpty(propInfoList)) {
			return gdsGds2PropReqDTOList;
		}

		if (CollectionUtils.isNotEmpty(list)) {
			for (String[] propInfo : propInfoList) {

				// 已经被编辑属性，直接跳过
				if (list.contains(propInfo[0])) {
					continue;
				}

				gdsGds2PropReqDTOList.add(this
						.createGdsGds2PropReqDTO(propInfo));
			}
		} else {
			for (String[] propInfo : propInfoList) {
				gdsGds2PropReqDTOList.add(this
						.createGdsGds2PropReqDTO(propInfo));
			}
		}

		return gdsGds2PropReqDTOList;
	}

	/**
	 * 创建商品属性列表
	 * 
	 * @param propInfo
	 * @return
	 * @throws BusinessException
	 */
	protected GdsGds2PropReqDTO createGdsGds2PropReqDTO(String propInfo[])
			throws BusinessException {

		Long propId = Long.parseLong(propInfo[0]);
		String propValue = propInfo[1];

		GdsGds2PropReqDTO gdsGds2PropReqDTO = new GdsGds2PropReqDTO();
		gdsGds2PropReqDTO.setPropId(propId);

		// ==============单选、多选属性值需要特殊处理=================
		if (StringUtils.equals(propInfo[0], "1027")) {// 1027是否有网络增值【单选】【是：308，否：309】

			if (StringUtils.equals("否", propValue)) {

				gdsGds2PropReqDTO.setPropValue("否");
				gdsGds2PropReqDTO.setPropValueId(309L);

			} else if (StringUtils.equals("是", propValue)) {

				gdsGds2PropReqDTO.setPropValue("是");
				gdsGds2PropReqDTO.setPropValueId(308L);

			} else {

			    // 单选类型值不正确或为空，置为否
                gdsGds2PropReqDTO.setPropValue("否");
                gdsGds2PropReqDTO.setPropValueId(309L);

            }

		} else if (StringUtils.equals(propInfo[0], "1028")) {// 1028是否有数字印刷【单选】【是：306，否：307】

			// TODO 接口数据参数没传，不知解析格式

		} else if (StringUtils.equals(propInfo[0], "1031")) {// 1031是否提供试读【单选】【是：310，否：311】

			if (StringUtils.equals("0", propValue)) {

				gdsGds2PropReqDTO.setPropValue("否");
				gdsGds2PropReqDTO.setPropValueId(311L);

			} else if (StringUtils.equals("1", propValue)) {

				gdsGds2PropReqDTO.setPropValue("是");
				gdsGds2PropReqDTO.setPropValueId(310L);

			} else {

			    // 单选类型值不正确不处理
                gdsGds2PropReqDTO.setPropValue("否");
                gdsGds2PropReqDTO.setPropValueId(311L);

            }

		} else {

			gdsGds2PropReqDTO.setPropValue(propValue);

		}

		return gdsGds2PropReqDTO;
	}

	/**
	 * 根据商品属性列表创建单品属性列表
	 * 
	 * @param gds2PropReqDTOs
	 * @return
	 * @throws BusinessException
	 */
	protected List<GdsSku2PropReqDTO> createGdsSku2PropReqDTOList(
			List<GdsGds2PropReqDTO> gds2PropReqDTOs) throws BusinessException {

		List<GdsSku2PropReqDTO> gdsSku2PropReqDTOList = new ArrayList<GdsSku2PropReqDTO>();

		for (GdsGds2PropReqDTO gdsGds2PropReqDTO : gds2PropReqDTOs) {
			GdsSku2PropReqDTO gdsSku2PropReqDTO = new GdsSku2PropReqDTO();
			gdsSku2PropReqDTO.setPropId(gdsGds2PropReqDTO.getPropId());
			gdsSku2PropReqDTO.setPropValue(gdsGds2PropReqDTO.getPropValue());
			gdsSku2PropReqDTO
					.setPropValueId(gdsGds2PropReqDTO.getPropValueId());
			gdsSku2PropReqDTOList.add(gdsSku2PropReqDTO);
		}

		return gdsSku2PropReqDTOList;

	}

	/**
	 * 更新商品
	 * 
	 * @param map
	 * @throws BusinessException
	 */
	@SuppressWarnings("rawtypes")
	protected GdsInfoRespDTO update(Map map) throws BusinessException {

		String benbanbianhao = this.getValue_(map,this.getPkKeyName());

		// =================根据中间映射表判断商品是否存在，不存在直接抛出异常=================
		GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO = new GdsInterfaceGdsReqDTO();
		gdsInterfaceGdsReqDTO.setOriginGdsId(this.getSrcGdsCodePrefix()
				+ benbanbianhao);
		gdsInterfaceGdsReqDTO.setOrigin(this.getOriginType());
		GdsInterfaceGds gdsInterfaceGds = this.gdsInterfaceGdsSV
				.queryGdsInterfaceGdsByOriginGdsId(gdsInterfaceGdsReqDTO);
		if (gdsInterfaceGds == null) {
			throw new BusinessException(
					GdsDataImportErrorConstants.Commons.ERROR_GOODS_DATAIMPORT_3,
					new String[] { map.toString() });
		}

		GdsInfoRespDTO gdsInfoRespDTO = this.gdsInfoQuerySV.queryGdsInfoById(
				gdsInterfaceGds.getGdsId());
		
		//TODO 通过映射表找到的商品已经在ECP侧删除了，但是映射关系还在（如是在管理平台删除的商品）。可以采取的操作：
		//1、被删除的商品不允许再被新增进来。映射关系不删除。
		//2、被删除的商品允许再被新增进来。更新映射关系中的ECP侧最新商品编码。
		if (gdsInfoRespDTO == null||StringUtils.equals(GdsConstants.GdsInfo.GDS_STATUS_DELETE,gdsInfoRespDTO.getGdsStatus())) {

			// 商品不存在或已被删除
			// 不抛出异常，直接忽略
			LogUtil.error(MODULE,"商品已在ECP侧被删除（商品处于被删除状态）！不允许再创建。");
			return null;
		}

		// ===============================执行下架操作================================
		if(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES.equals(gdsInfoRespDTO.getGdsStatus())){
		    GdsInfoReqDTO req = new GdsInfoReqDTO();
	        req.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES);
	        req.setId(gdsInterfaceGds.getGdsId());
	        req.setShopId(gdsInterfaceGds.getShopId());

	        //不维护商品编码映射表
	        req.setIfMaintainGdsInterfaceGds(false);

	        this.gdsInfoManageSV.executeGdsShelves(req);
		}
		// ===============================修改商品================================

		// ☆☆☆☆更新的属性编码或商品属性名☆☆☆☆
		// String updateRule = gdsInterfaceGds.getUpdateRule();

		// 更新规则字段
		String updateRule = gdsInfoRespDTO.getChangePropStr();
		boolean neverUpdate = false;
		List<String> list = null;
		if (StringUtils.isBlank(updateRule)) {
			neverUpdate = true;
		} else {
			list = new ArrayList<String>(Arrays.asList(updateRule.split(",")));
			if (CollectionUtils.isEmpty(list)) {
				neverUpdate = true;
			}
		}

		// 避免空指针
		if (list == null) {
			list = new ArrayList<String>();
		}

		GdsInfoAddReqDTO gdsInfoAddReqDTO = null;
		if (neverUpdate) {
		    
		    //商品信息没有在ECP侧修改过，则直接采用create创建参数的方式。不过需要略过空值，否则无法updateSelective。
			gdsInfoAddReqDTO = this.createAddGdsInfoParam(map);
		} else {

			gdsInfoAddReqDTO = this.createUpdateGdsInfoParam(map, list,
					gdsInterfaceGds.getGdsId(), gdsInterfaceGds.getSkuId());
		}

		// 设置要覆盖的商品编码
		gdsInfoAddReqDTO.getGdsInfoReqDTO().setId(gdsInterfaceGds.getGdsId());
		gdsInfoAddReqDTO.getGdsInfoReqDTO()
				.setSkuId(gdsInterfaceGds.getSkuId());

		// 设置要覆盖的单品编码
		for (GdsSkuInfoReqDTO gdsSkuInfoReqDTO : gdsInfoAddReqDTO
				.getSkuInfoReqDTOs()) {
			gdsSkuInfoReqDTO.setId(gdsInterfaceGds.getSkuId());
			gdsSkuInfoReqDTO.setGdsId(gdsInterfaceGds.getGdsId());
		}
		
		//设置不更新图片，避免出现更新商品后图片信息丢失问题
		//gdsInfoAddReqDTO.getGdsInfoReqDTO().setUpdatePic(false);
		Boolean allowUpdatePic = Boolean.valueOf(String.valueOf(map.get("$IF_ALLOW_UPDATE_IMAGE$")));
		gdsInfoAddReqDTO.getGdsInfoReqDTO().setUpdatePic(allowUpdatePic);
		//设置查配置表，解决ifbasic属性为空问题。
        gdsInfoAddReqDTO.getGdsInfoReqDTO().setCopyPropFromConfiged(true);

		//不维护商品编码映射表
		gdsInfoAddReqDTO.setIfMaintainGdsInterfaceGds(false);
		
		List<Long> skuIds=this.gdsInfoManageSV.editGdsInfoAndReference(gdsInfoAddReqDTO);
		gdsInfoRespDTO.setSkuIds(skuIds);
		// ===============================是否上架================================
		// TODO 由于修改之前执行了下架操作，当上架标志位为待上架的时候需要还原回去，而不是跳过。
		if (!GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES.equals(this.getGdsStatus(map, list))) {
            GdsSkuInfoReqDTO gdsSkuInfoReqDTO=gdsInfoAddReqDTO.getSkuInfoReqDTOs().get(0);
		    if(gdsSkuInfoReqDTO.getCommonPrice() !=null && gdsSkuInfoReqDTO.getCommonPrice()!= 0L){
		        this.executeGdsShelves(this.getGdsStatus(map, list),
                        gdsInterfaceGds.getGdsId(), gdsInterfaceGds.getShopId());
		    }else{
		          if(CollectionUtils.isNotEmpty(skuIds)){
		                GdsSkuInfoReqDTO skuReq=new GdsSkuInfoReqDTO();
		                skuReq.setId(skuIds.get(0));
		                GdsSkuInfo sku=gdsSkuInfoQuerySV.querySkuInfoFromDB(skuReq);
		                if (sku!=null && sku.getCommonPrice()!=null && sku.getCommonPrice()!= 0L) {
		                    this.executeGdsShelves(this.getGdsStatus(map, list),
		                            gdsInterfaceGds.getGdsId(), gdsInterfaceGds.getShopId());
		                }
		            }
		    }
		}

		// ======================================编码关系表更新，主要是更新更新时间字段===============================================
		gdsInterfaceGdsReqDTO.setGdsId(gdsInterfaceGds.getGdsId());
		gdsInterfaceGdsReqDTO
				.setUpdateStaff(this.getStaffId());
		this.gdsInterfaceGdsSV
				.updateGdsInterfaceGdsByGdsInfoAndOriginGdsInfo(gdsInterfaceGdsReqDTO);

		//发送搜索引擎增量消息是根据稿件状态设置增量类型的，不能强制设置状态（除非是新增稿件）
		//gdsInfoRespDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES);

		//返回操作类型
		gdsInfoRespDTO.setOperType(OperationType.GDS_EDIT.getName());

		return gdsInfoRespDTO;

	}

	/**
	 * 获取需要更新的商品信息，不包含主键信息
	 * 
	 * @param map
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings("rawtypes")
	protected abstract GdsInfoAddReqDTO createUpdateGdsInfoParam(Map map,
			List<String> list, Long gdsId, Long skuId) throws BusinessException;

	/**
	 * 删除商品
	 * 
	 * @param map
	 * @throws BusinessException
	 */
	@SuppressWarnings("rawtypes")
	protected GdsInfoRespDTO delete(Map map) throws BusinessException {
	    GdsInfoRespDTO gdsInfo=new GdsInfoRespDTO();
		String benbanbianhao = this.getValue_(map,this.getPkKeyName());

		// =================根据中间映射表判断商品是否存在，不存在直接抛出异常=================
		GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO = new GdsInterfaceGdsReqDTO();
		gdsInterfaceGdsReqDTO.setOriginGdsId(this.getSrcGdsCodePrefix() + benbanbianhao);
		gdsInterfaceGdsReqDTO.setOrigin(this.getOriginType());
		GdsInterfaceGds gdsInterfaceGds = this.gdsInterfaceGdsSV.queryGdsInterfaceGdsByOriginGdsId(gdsInterfaceGdsReqDTO);
		if (gdsInterfaceGds == null) {
			// 不抛出异常，直接忽略
			// throw new BusinessException("操作模式是【删除】，但是商品编码映射表中找不到对应的原始记录：" +
			// map);
			return null;
		}

		gdsInfo.setId(gdsInterfaceGds.getGdsId());
		// ===============================删除商品================================
		GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
		gdsInfoReqDTO.setId(gdsInterfaceGds.getGdsId());
		gdsInfoReqDTO.setCompanyId(this.getCompanyId());
		gdsInfoReqDTO.setShopId(gdsInterfaceGds.getShopId());

		//不维护商品编码映射表
		gdsInfoReqDTO.setIfMaintainGdsInterfaceGds(false);

		// 删除服务内已经包含下架操作
		List<Long>skuIds=this.gdsInfoManageSV.deleteGdsInfo(gdsInfoReqDTO);
		gdsInfo.setSkuIds(skuIds);
		// =====================================商品编码关系表关系删除，包含更新人和更新时间字段===============================================
		gdsInterfaceGdsReqDTO.setGdsId(gdsInterfaceGds.getGdsId());
		gdsInterfaceGdsReqDTO
				.setUpdateStaff(this.getStaffId());
		this.gdsInterfaceGdsSV.deleteGdsInterfaceGdsByGdsInfoAndOriginGdsInfo(gdsInterfaceGdsReqDTO);
		gdsInfo.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES);

		gdsInfo.setShopId(gdsInterfaceGds.getShopId());

		//返回操作类型
		gdsInfo.setOperType(OperationType.GDS_DELETE.getName());

		return gdsInfo;

	}

	/**
	 * Title: ECP <br>
	 * Project Name:ecp-services-goods <br>
	 * Description: 操作类型定义<br>
	 * Date:2015年10月27日下午5:13:07 <br>
	 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
	 * 
	 * @author huangdf
	 * @version BaseGdsInfoImportSV
	 * @since JDK 1.6
	 */
	public enum EActionType {

		ADD(1),

		DELETE(2),

		UPDATE(3);

		private int actionType;

		public int getActionType() {
			return actionType;
		}

		EActionType(int actionType) {
			this.actionType = actionType;
		}

	}
}
