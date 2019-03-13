package com.zengshi.ecp.app.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Gds004Req;
import com.zengshi.ecp.app.resp.Gds004Resp;
import com.zengshi.ecp.app.resp.gds.GdsPromMatchBaseInfo;
import com.zengshi.ecp.app.resp.gds.GdsPromMatchSkuBaseInfo;
import com.zengshi.ecp.app.resp.gds.GdsSkuBaseInfo;
import com.zengshi.ecp.busi.goods.gdsdetail.vo.GdsPromMatchSkuVO;
import com.zengshi.ecp.busi.goods.gdsdetail.vo.GdsPromMatchVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartRSV;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopStaffGroupReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopManageRSV;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 组合商品 Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016年3月10日上午11:15:08 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zjh
 * @version
 * @since JDK 1.6
 */
@Service("gds004")
@Action(bizcode = "gds004", userCheck = false)
@Scope("prototype")
public class Gds004Action extends AppBaseAction<Gds004Req, Gds004Resp> {

	@Resource
	private IOrdCartRSV ordCartRSV;

	@Resource
	private IShopInfoRSV shopInfoRSV;
	@Resource
	private IShopManageRSV iShopManageRSV;
	@Resource
	private IGdsSkuInfoQueryRSV iGdsSkuInfoQueryRSV;

	@Resource
	private ICustInfoRSV iCustInfoRSV;
	@Resource
	private IPromRSV iPromRSV;

	private static final String MODULE = Gds004Action.class.getName();

	private static String MOBILE = "2";

	@Override
	protected void getResponse() throws BusinessException, SystemException, Exception {

		Gds004Req gds004Req = this.request;
		Gds004Resp gds004Resp = this.response;

		List<GdsPromMatchVO> autoResultList = new ArrayList<GdsPromMatchVO>();
		List<GdsPromMatchVO> fixedResultList = new ArrayList<GdsPromMatchVO>();
		try {
			PromRuleCheckDTO promRuleCheckDTO = new PromRuleCheckDTO();
			CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
			CustInfoResDTO custInfoResDTO = null;
			ShopStaffGroupReqDTO shopStaffGroupReqDTO = new ShopStaffGroupReqDTO();
			if (custInfoReqDTO.getStaff().getId() == 0) {
				promRuleCheckDTO.setCustLevelValue(custInfoReqDTO.getStaff().getStaffLevelCode());
			} else {
				custInfoReqDTO.setId(custInfoReqDTO.getStaff().getId());
				custInfoResDTO = iCustInfoRSV.getCustInfoById(custInfoReqDTO);
				promRuleCheckDTO.setCustLevelValue(custInfoResDTO.getCustLevelCode());
				promRuleCheckDTO.setAreaValue(custInfoResDTO.getProvinceCode());
				promRuleCheckDTO.setStaffId(custInfoResDTO.getId() + "");
				shopStaffGroupReqDTO.setStaffId(custInfoResDTO.getId());
			}
			shopStaffGroupReqDTO.setShopId(gds004Req.getShopId());
			// 客户组id
			String custGroupValue = null;
			if (custInfoResDTO != null && custInfoResDTO.getId() != null && custInfoResDTO.getId() != 0) {
				custGroupValue = iShopManageRSV.queryShopStaffGroup(shopStaffGroupReqDTO);
			}
			// 客户基本信息
			promRuleCheckDTO.setCustGroupValue(custGroupValue);

			promRuleCheckDTO.setSiteId(promRuleCheckDTO.getCurrentSiteId());

			promRuleCheckDTO.setGdsId(gds004Req.getGdsId());
			promRuleCheckDTO.setShopId(gds004Req.getShopId());
			promRuleCheckDTO.setChannelValue(MOBILE);
			promRuleCheckDTO.setCalDate(new Date(System.currentTimeMillis()));

			// 自由搭配
//			promRuleCheckDTO.setMatchType(PromConstants.PromMatchSku.MATCH_TYPE_1);
//			List<PromMatchDTO> autolist = iPromRSV.queryMatchList(promRuleCheckDTO);
//			if (autolist != null && autolist.size() > 0) {
//				getAutoInfo(autolist, autoResultList);
//			}
			// 固定搭配
			promRuleCheckDTO.setMatchType(PromConstants.PromMatchSku.MATCH_TYPE_2);
			List<PromMatchDTO> fixedlist = iPromRSV.queryMatchList(promRuleCheckDTO);
			if (fixedlist != null && fixedlist.size() > 0) {
				getAutoInfo(fixedlist, fixedResultList);
			}

			gds004Resp.setSkuId(gds004Req.getSkuId());

		} catch (BusinessException e) {
			LogUtil.error(MODULE, "获取组合搭配失败！", e);

		} catch (Exception e) {
			LogUtil.error(MODULE, "获取组合搭配失败！", e);

		}
		List<GdsPromMatchBaseInfo> autoPromList = new ArrayList<GdsPromMatchBaseInfo>();
		List<GdsPromMatchBaseInfo> fixedPromList = new ArrayList<GdsPromMatchBaseInfo>();
		if (autoResultList != null) {
			for (GdsPromMatchVO promMatchDTO : autoResultList) {
				GdsPromMatchBaseInfo gdsPromMatchBaseInfo = new GdsPromMatchBaseInfo();
				ObjectCopyUtil.copyObjValue(promMatchDTO, gdsPromMatchBaseInfo, null, false);
				List<GdsPromMatchSkuVO> autoPromMatchSkuVOs = promMatchDTO.getGdsPromMatchSkuVOList();
				List<GdsPromMatchSkuBaseInfo> autoPromMatchSkuBaseInfos = new ArrayList<GdsPromMatchSkuBaseInfo>();
				for (GdsPromMatchSkuVO gdsPromMatchSkuVO : autoPromMatchSkuVOs) {
					GdsPromMatchSkuBaseInfo gdsPromMatchSkuBaseInfo = new GdsPromMatchSkuBaseInfo();
					ObjectCopyUtil.copyObjValue(gdsPromMatchSkuVO, gdsPromMatchSkuBaseInfo, null, false);
					GdsSkuBaseInfo gdsSkuBaseInfo = new GdsSkuBaseInfo();
					ObjectCopyUtil.copyObjValue(gdsPromMatchSkuVO.getSkuInfo(), gdsSkuBaseInfo, null, false);
					if (gdsPromMatchSkuVO.getSkuInfo().getMainPic() != null) {
						gdsSkuBaseInfo.setMainPicUrl(
								ImageUtil.getImageUrl(gdsPromMatchSkuVO.getSkuInfo().getMainPic().getMediaUuid()));
					} else {
						gdsSkuBaseInfo.setMainPicUrl(ImageUtil.getImageUrl((null)));

					}
					gdsPromMatchSkuBaseInfo.setSkuInfo(gdsSkuBaseInfo);
					autoPromMatchSkuBaseInfos.add(gdsPromMatchSkuBaseInfo);
				}
				gdsPromMatchBaseInfo.setGdsPromMatchSkuVOList(autoPromMatchSkuBaseInfos);
				autoPromList.add(gdsPromMatchBaseInfo);
			}

		}
		if (fixedResultList != null) {
			for (GdsPromMatchVO promMatchDTO : fixedResultList) {
				GdsPromMatchBaseInfo gdsPromMatchBaseInfo = new GdsPromMatchBaseInfo();
				ObjectCopyUtil.copyObjValue(promMatchDTO, gdsPromMatchBaseInfo, null, false);
				List<GdsPromMatchSkuVO> fixedPromMatchSkuVOs = promMatchDTO.getGdsPromMatchSkuVOList();
				List<GdsPromMatchSkuBaseInfo> fixedPromMatchSkuBaseInfos = new ArrayList<GdsPromMatchSkuBaseInfo>();
				for (GdsPromMatchSkuVO gdsPromMatchSkuVO : fixedPromMatchSkuVOs) {
					GdsPromMatchSkuBaseInfo gdsPromMatchSkuBaseInfo = new GdsPromMatchSkuBaseInfo();
					ObjectCopyUtil.copyObjValue(gdsPromMatchSkuVO, gdsPromMatchSkuBaseInfo, null, false);
					GdsSkuBaseInfo gdsSkuBaseInfo = new GdsSkuBaseInfo();
					ObjectCopyUtil.copyObjValue(gdsPromMatchSkuVO.getSkuInfo(), gdsSkuBaseInfo, null, false);
					if (gdsPromMatchSkuVO.getSkuInfo().getMainPic() != null) {
						gdsSkuBaseInfo.setMainPicUrl(
								ImageUtil.getImageUrl(gdsPromMatchSkuVO.getSkuInfo().getMainPic().getMediaUuid()));
					} else {
						gdsSkuBaseInfo.setMainPicUrl(ImageUtil.getImageUrl((null)));

					}
					gdsPromMatchSkuBaseInfo.setSkuInfo(gdsSkuBaseInfo);
					fixedPromMatchSkuBaseInfos.add(gdsPromMatchSkuBaseInfo);
				}
				gdsPromMatchBaseInfo.setGdsPromMatchSkuVOList(fixedPromMatchSkuBaseInfos);
				fixedPromList.add(gdsPromMatchBaseInfo);
			}

		}
		gds004Resp.setAutoCombineList(autoPromList);
		gds004Resp.setFixedCombineList(fixedPromList);
	}

	private void getAutoInfo(List<PromMatchDTO> list, List<GdsPromMatchVO> resultList) {
		GdsPromMatchVO gdsPromMatchVO = null;
		for (PromMatchDTO promMatchDTO : list) {
			gdsPromMatchVO = new GdsPromMatchVO();

			List<PromMatchSkuDTO> promMatchSkuDTOList = promMatchDTO.getPromMatchSkuDTOList();
			List<GdsPromMatchSkuVO> gdsPromMatchSkuVOList = new ArrayList<GdsPromMatchSkuVO>();
			GdsPromMatchSkuVO gdsPromMatchSkuVO = null;
			if (promMatchSkuDTOList != null && promMatchSkuDTOList.size() > 0) {
				for (PromMatchSkuDTO skuDto : promMatchSkuDTOList) {
					gdsPromMatchSkuVO = new GdsPromMatchSkuVO();
					GdsSkuInfoRespDTO skuInfoRespDTO = new GdsSkuInfoRespDTO();
					Map<String, GdsPropRespDTO> allPropMaps = new HashMap<String, GdsPropRespDTO>();
					ObjectCopyUtil.copyObjValue(skuDto, gdsPromMatchSkuVO, "", false);
					GdsSkuInfoReqDTO skudto = new GdsSkuInfoReqDTO();
					SkuQueryOption[] skuQueryOptions1 = new SkuQueryOption[] { SkuQueryOption.BASIC,
							SkuQueryOption.PROP, SkuQueryOption.MAINPIC, SkuQueryOption.SHOWPRICE };
					skudto.setSkuQuery(skuQueryOptions1);
					skudto.setId(skuDto.getSkuId());
					GdsSkuInfoRespDTO gdsSkuInfoRespDTO = iGdsSkuInfoQueryRSV.querySkuInfoByOptions(skudto);
					if (gdsSkuInfoRespDTO != null) {
						skuInfoRespDTO.setGdsName(gdsSkuInfoRespDTO.getGdsName());
						skuInfoRespDTO.setRealPrice(gdsSkuInfoRespDTO.getRealPrice());

						// 作者
						if (gdsSkuInfoRespDTO.getAllPropMaps() != null) {
							allPropMaps.put("1001", gdsSkuInfoRespDTO.getAllPropMaps().get("1001"));
						}
						skuInfoRespDTO.setAllPropMaps(allPropMaps);
						skuInfoRespDTO.setId(gdsSkuInfoRespDTO.getId());
						skuInfoRespDTO.setGdsId(gdsSkuInfoRespDTO.getGdsId());
						skuInfoRespDTO.setMainCatgs(gdsSkuInfoRespDTO.getMainCatgs());
						skuInfoRespDTO.setSkuProps(gdsSkuInfoRespDTO.getSkuProps());
						skuInfoRespDTO.setGdsTypeId(gdsSkuInfoRespDTO.getGdsTypeId());
						skuInfoRespDTO.setMainPic(gdsSkuInfoRespDTO.getMainPic());
						skuInfoRespDTO.setGdsStatus(gdsSkuInfoRespDTO.getGdsStatus());

					}
					gdsPromMatchSkuVO.setSkuInfo(skuInfoRespDTO);
					gdsPromMatchSkuVOList.add(gdsPromMatchSkuVO);
				}
			}
			gdsPromMatchVO.setGdsPromMatchSkuVOList(gdsPromMatchSkuVOList);
			resultList.add(gdsPromMatchVO);
		}
	}

}
