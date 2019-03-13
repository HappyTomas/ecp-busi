package com.zengshi.ecp.goods.service.busi.impl.excelImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.goods.dao.model.GdsCategory;
import com.zengshi.ecp.goods.dao.model.GdsProp;
import com.zengshi.ecp.goods.dao.model.GdsPropValue;
import com.zengshi.ecp.goods.dao.model.GdsType;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.ExcelImportGdsModelDTO;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpLogReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpPropValueInfo;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImportResultDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.excelImp.IGdsExcelImpLogSV;
import com.zengshi.ecp.goods.service.busi.interfaces.excelImp.IGdsExcelImpToolSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropValueSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsTypeSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

public class GdsExcelImpToolSVImp implements IGdsExcelImpToolSV {

	private static final int threadNum = 4 * 8;

	// 正则表达式
	// 数字
	private static final String digitalReg = "[0-9]+";

	private static final String VALIDATE_ERROR_CATG_CODE = "分类编码不合法！";

	private static final String VALIDATE_ERROR_GDS_NAME = "分类编码不合法！";
	private static final String VALIDATE_ERROR_GDS_TYPE = "商品类型不合法！";
	private static final String VALIDATE_ERROR_GDS_PRICE = "商品价格不合法！";
	private static final String VALIDATE_ERROR_SHOP_ID = "店铺编码不合法！";

	private static final String VALIDATE_ERROR_CATG_CODE_NOT_EXISTS = "分类编码不存在！";
	private static final String VALIDATE_ERROR_GDS_NOT_EXISTS = "商品不存在或者已经上架！";
	private static final String VALIDATE_ERROR_GDS_NAME_lENGTH = "商品名称超过最大长度！";
	private static final String VALIDATE_ERROR_GDS_SUBHEAD_lENGTH = "产品副标题超过最大长度！";
	private static final String VALIDATE_ERROR_GDS_TYPE_NOT_EXISTS = "商品类型不存在！";
	private static final String VALIDATE_ERROR_SHOP_ID_NOT_EXISTS = "店铺编码不存在！";
	private static final String VALIDATE_ERROR_PROP = "属性不合法！";
	private static final String VALIDATE_ERROR_GDS_ID = "商品编码不合法！";
	private static final String VALIDATE_ERROR_STOCK = "商品库存不合法！";
	@Resource
	private IShopInfoRSV shopInfoRSV;
	@Resource
	private IGdsCategorySV gdsCategorySV;
	@Resource
	private IGdsTypeSV gdsTypeSV;
	@Resource
	private IGdsPropSV gdsPropSV;
	@Resource
	private IGdsPropValueSV gdsPropValueSV;
	@Resource
	private IGdsExcelImpLogSV gdsExcelImpLogSV;
	@Resource
	private IGdsInfoQuerySV gdsInfoQuerySV;

	@Override
	public GdsExcelImportResultDTO importTempGdsExcelData(
			GdsExcelImpLogReqDTO excelImpLogReqDTO) throws Exception {
		// GdsExcelImpLogReqDTO gdsExcelImpLogReqDTO =
		// this.exportDataFromFile(excelImpLogReqDTO);

		if (excelImpLogReqDTO.getExcelImportGdsModelDTOs() == null
				|| excelImpLogReqDTO.getExcelImportGdsModelDTOs().size() == 0) {

			throw new BusinessException();
		}

		if (StringUtil.isBlank(excelImpLogReqDTO.getOptType())) {
			throw new BusinessException();

		}
		ForkJoinPool forkJoinPool = new ForkJoinPool(threadNum);
		GdsExcelImportMainTask excelImportMainTask = new GdsExcelImportMainTask(
				excelImpLogReqDTO.getExcelImportGdsModelDTOs(),
				excelImpLogReqDTO.getOptType());

		Future<GdsExcelImportResultDTO> future = forkJoinPool
				.submit(excelImportMainTask);
		GdsExcelImportResultDTO excelImportResultDTO = future.get();
		// 收集导入中间表的结果参数，更新操作日志
		GdsExcelImpLogReqDTO impLogReqDTO = new GdsExcelImpLogReqDTO();
		impLogReqDTO.setFailCount(excelImportResultDTO.getErrorCount());
		impLogReqDTO.setImportCount(excelImportResultDTO.getTotalCount());
		impLogReqDTO.setVictoryCount(excelImportResultDTO.getSuccessCount());
		impLogReqDTO.setImportId(excelImpLogReqDTO.getImportId());
		gdsExcelImpLogSV.updateGdsExcelImpLogWhenTempDone(excelImpLogReqDTO);
		return excelImportResultDTO;
	}

	@Override
	public GdsExcelImpReqDTO validateGdsExcelImpData(
			ExcelImportGdsModelDTO excelImportGdsModelDTO, String optType)
			throws Exception {
		GdsExcelImpReqDTO gdsExcelImpReqDTO = new GdsExcelImpReqDTO();
		// 先把数据copy到请求参数中
		ObjectCopyUtil.copyObjValue(excelImportGdsModelDTO, gdsExcelImpReqDTO,
				null, false);
		validate(excelImportGdsModelDTO, gdsExcelImpReqDTO, optType);
		return gdsExcelImpReqDTO;
	}

	private void validate(ExcelImportGdsModelDTO excelImportGdsModelDTO,
			GdsExcelImpReqDTO gdsExcelImpReqDTO, String optType)
			throws Exception {
		if (GdsConstants.GdsExcelImpLog.GDS_EXCEL_IMP_NEW.equals(optType)) {
			// 校验非空
			if (StringUtil.isBlank(excelImportGdsModelDTO.getCatgCode())) {

				gdsExcelImpReqDTO.setGdsPropStr(excelImportGdsModelDTO
						.getPropInfos().toString());
				gdsExcelImpReqDTO.setFailReason(VALIDATE_ERROR_CATG_CODE);
				return;
			}

			if (excelImportGdsModelDTO.getGdsType() == null) {
				gdsExcelImpReqDTO.setGdsPropStr(excelImportGdsModelDTO
						.getPropInfos().toString());
				gdsExcelImpReqDTO.setFailReason(VALIDATE_ERROR_GDS_TYPE);
				return;
			}
			if (excelImportGdsModelDTO.getGdsPrice() == null) {
				gdsExcelImpReqDTO.setGdsPropStr(excelImportGdsModelDTO
						.getPropInfos().toString());
				gdsExcelImpReqDTO.setFailReason(VALIDATE_ERROR_GDS_PRICE);
				return;
			}
			if (excelImportGdsModelDTO.getShopId() == null) {
				gdsExcelImpReqDTO.setGdsPropStr(excelImportGdsModelDTO
						.getPropInfos().toString());
				gdsExcelImpReqDTO.setFailReason(VALIDATE_ERROR_SHOP_ID);
				return;
			}

			if (excelImportGdsModelDTO.getGdsStock() == null) {

				gdsExcelImpReqDTO.setGdsPropStr(excelImportGdsModelDTO
						.getPropInfos().toString());
				gdsExcelImpReqDTO.setFailReason(VALIDATE_ERROR_STOCK);
				return;
			}

			// 校验录入数据在数据库是否有效
			ShopInfoResDTO shopInfoResDTO = this
					.validateShopInfo(excelImportGdsModelDTO.getShopId());
			if (shopInfoResDTO == null) {
				gdsExcelImpReqDTO.setGdsPropStr(excelImportGdsModelDTO
						.getPropInfos().toString());
				gdsExcelImpReqDTO
						.setFailReason(VALIDATE_ERROR_SHOP_ID_NOT_EXISTS);
				return;

			}

			GdsType gdsType = this.validateGdsType(excelImportGdsModelDTO
					.getGdsType());
			if (gdsType == null) {
				gdsExcelImpReqDTO.setGdsPropStr(excelImportGdsModelDTO
						.getPropInfos().toString());
				gdsExcelImpReqDTO
						.setFailReason(VALIDATE_ERROR_GDS_TYPE_NOT_EXISTS);
				return;

			}

			GdsCategory gdsCategory = this
					.validateGdsCategory(excelImportGdsModelDTO.getCatgCode());
			if (gdsCategory == null) {
				gdsExcelImpReqDTO.setGdsPropStr(excelImportGdsModelDTO
						.getPropInfos().toString());
				gdsExcelImpReqDTO
						.setFailReason(VALIDATE_ERROR_CATG_CODE_NOT_EXISTS);
				return;
			}
		} else {

			if (excelImportGdsModelDTO.getGdsId() == null) {
				gdsExcelImpReqDTO.setGdsPropStr(excelImportGdsModelDTO
						.getPropInfos().toString());
				gdsExcelImpReqDTO.setFailReason(VALIDATE_ERROR_GDS_ID);
				return;
			}
			GdsInfoRespDTO gdsInfoRespDTO = this
					.validateGdsId(excelImportGdsModelDTO.getGdsId());

			if (gdsInfoRespDTO == null) {
				gdsExcelImpReqDTO.setGdsPropStr(excelImportGdsModelDTO
						.getPropInfos().toString());
				gdsExcelImpReqDTO.setFailReason(VALIDATE_ERROR_GDS_NOT_EXISTS);
				return;
			} else {
				excelImportGdsModelDTO.setShopId(gdsInfoRespDTO.getShopId());
				if (!GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES
						.equals(gdsInfoRespDTO.getGdsStatus())
						&& !GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES
								.equals(gdsInfoRespDTO.getGdsStatus())) {
					gdsExcelImpReqDTO.setGdsPropStr(excelImportGdsModelDTO
							.getPropInfos().toString());
					gdsExcelImpReqDTO
							.setFailReason(VALIDATE_ERROR_GDS_NOT_EXISTS);
					return;
				}
				if(null!=excelImportGdsModelDTO.getGdsName()&&excelImportGdsModelDTO.getGdsName().trim().length()>128){
					gdsExcelImpReqDTO.setGdsPropStr(excelImportGdsModelDTO
							.getPropInfos().toString());
					gdsExcelImpReqDTO
							.setFailReason(VALIDATE_ERROR_GDS_NAME_lENGTH);
					return;
				}
				if(null!=excelImportGdsModelDTO.getGdsTitle()&&excelImportGdsModelDTO.getGdsTitle().trim().length()>100){
					gdsExcelImpReqDTO.setGdsPropStr(excelImportGdsModelDTO
							.getPropInfos().toString());
					gdsExcelImpReqDTO
							.setFailReason(VALIDATE_ERROR_GDS_SUBHEAD_lENGTH);
					return;
				}
			}
		}

		// if (StringUtil.isBlank(excelImportGdsModelDTO.getGdsName())) {
		// gdsExcelImpReqDTO.setGdsPropStr(excelImportGdsModelDTO.getPropInfos().toString());
		// gdsExcelImpReqDTO.setFailReason(VALIDATE_ERROR_GDS_NAME);
		// return;
		// }

		// 核对属性数据

		String[] propStrs = propStrValidatePropInfos(excelImportGdsModelDTO);
		if ("false".equals(propStrs[0])) {
			gdsExcelImpReqDTO.setFailReason(VALIDATE_ERROR_PROP);
		} else {
			gdsExcelImpReqDTO.setGdsPropStr(propStrs[1]);
		}

	}

	// 校验店铺
	private ShopInfoResDTO validateShopInfo(Long shopId) {
		try {
			ShopInfoResDTO shopInfoResDTO = shopInfoRSV
					.findShopInfoByShopID(shopId);
			return shopInfoResDTO;
		} catch (Exception e) {

			return null;
		}
	}

	// 校验商品编码
	private GdsInfoRespDTO validateGdsId(Long gdsId) {
		try {
			GdsInfoReqDTO gdsInfo = new GdsInfoReqDTO();
			gdsInfo.setId(gdsId);
			GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQuerySV
					.queryGdsInfo(gdsInfo);
			return gdsInfoRespDTO;
		} catch (Exception e) {

			return null;
		}
	}

	// 校验商品类型
	private GdsType validateGdsType(Long gdsTypeId) {
		try {
			GdsType gdsType = gdsTypeSV.queryGdsTypeByPK(gdsTypeId);
			return gdsType;
		} catch (Exception e) {

			return null;
		}
	}

	// 校验商品分类
	private GdsCategory validateGdsCategory(String catgCode) {
		try {
			GdsCategory gdsCategory = gdsCategorySV
					.queryGdsCategoryById(catgCode);
			return gdsCategory;
		} catch (Exception e) {

			return null;
		}

	}

	// 校验属性
	private String[] propStrValidatePropInfos(
			ExcelImportGdsModelDTO excelImportGdsModelDTO) {
		String propStr = "";
		Map<String, String> propInfo = excelImportGdsModelDTO.getPropInfos();
		boolean flag = true;
		// 是否所有属性都是空的
		boolean isNull = false;
		if (!MapUtils.isEmpty(propInfo)) {
			List<GdsExcelImpPropValueInfo> excelImpPropValueInfos = new ArrayList<GdsExcelImpPropValueInfo>();
			for (String key : propInfo.keySet()) {
				if (key == null && propInfo.get(key) == null) {
					isNull = true;

				} else {

					isNull = false;
				}

				GdsExcelImpPropValueInfo excelImpPropValueInfo = new GdsExcelImpPropValueInfo();
				if (StringUtil.isNotBlank(key)) {
					key = StringUtils.trim(key);
					String value = StringUtils.trim(propInfo.get(key));
					try {
						Long propId = Long.parseLong(key);
						GdsProp gdsProp = gdsPropSV.queryGdsPropByPK(propId,
								true);
						String propValType = gdsProp.getPropValueType();
						String inputType = gdsProp.getPropInputType();
						// 多选和富文本不进行处理
						if (GdsConstants.GdsProp.PROP_VALUE_TYPE_3
								.equals(propValType)
								|| GdsConstants.GdsProp.GDS_PROP_VALUE_INPUT_TYPE_RICHTXT
										.equals(inputType)) {
							flag = false;
							break;
						} else {

							// 如果是手工输入
							if (GdsConstants.GdsProp.PROP_VALUE_TYPE_1
									.equals(propValType)) {
								// 如果不是字符数字的也不处理
								if (!GdsConstants.GdsProp.GDS_PROP_VALUE_INPUT_TYPE_ZIFU
										.equals(inputType)) {
									flag = false;
									break;
								} else {

									ObjectCopyUtil.copyObjValue(gdsProp,
											excelImpPropValueInfo, null, false);
									excelImpPropValueInfo.setPropId(propId);
									excelImpPropValueInfo.setPropValue(value);
								}

							} else if (GdsConstants.GdsProp.PROP_VALUE_TYPE_2
									.equals(propValType)) {
								// 如果是单选
								// 校验并获取对应的propValue记录
								if (StringUtil.isBlank(propInfo.get(key))) {
									flag = false;
									break;
								} else {

									GdsPropValue gdsPropValue = gdsPropValueSV
											.queryPropValueInfoByVal(
													StringUtils.trim(propInfo
															.get(key)),
													propId,
													GdsConstants.Commons.STATUS_VALID);
									if (gdsPropValue != null) {
										ObjectCopyUtil.copyObjValue(gdsProp,
												excelImpPropValueInfo, null,
												false);
										excelImpPropValueInfo.setPropId(propId);
										excelImpPropValueInfo
												.setPropValue(value);
										excelImpPropValueInfo
												.setPropValueId(gdsPropValue
														.getId());
									} else {

										flag = false;
										break;
									}
								}
							} else {
								flag = false;
								break;
							}

						}
					} catch (Exception e) {

						flag = false;
						break;
					}

				} else {
					//如果当前值和id都是空，则继续遍历
					if(isNull==true){
						continue;
					}else{
					//如果id为空，值不为空，则提示属性异常	
					flag = false;
					break;
					}
				}
				excelImpPropValueInfo.setShopId(excelImportGdsModelDTO
						.getShopId());
				excelImpPropValueInfos.add(excelImpPropValueInfo);
			}
			if (flag == true && isNull == false) {
				JSONArray array = JSONArray.fromObject(excelImpPropValueInfos);
				propStr = array.toString();
			} else if (flag == false && isNull == true) {
				propStr = null;
			}
		}
		String[] res = new String[2];
		if (isNull || flag) {
			res[0] = "true";
			res[1] = propStr;
		} else {
			res[0] = "false";
			res[1] = propStr;
		}
		return res;

	}

}
