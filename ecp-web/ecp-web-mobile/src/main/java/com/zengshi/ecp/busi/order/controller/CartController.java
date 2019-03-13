package com.zengshi.ecp.busi.order.controller;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.order.vo.ROrdCartChgReqVO;
import com.zengshi.ecp.busi.order.vo.ROrdCartReqVO;
import com.zengshi.ecp.general.order.dto.ROrdCartChangeRequest;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.order.dubbo.dto.*;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartItemRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartRSV;
import com.zengshi.ecp.order.dubbo.util.CommonConstants;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.server.front.dto.BaseStaff;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustAddrRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author wangxq
 * @notice 功能 加入购物车= 加入购物车实现= 展示购物车= 购物车修改数量 购物车修改订单促销 购物车修改明细促销 删除购物车明细
 *
 */
@Controller
@RequestMapping(value = "/order")
public class CartController extends EcpBaseController {

	private static final String MODULE = CartController.class.getName();

	@Resource
	private IOrdCartRSV ordCartRSV;
	@Resource
	private IOrdCartItemRSV ordCartItemRSV;

	/**
	 * 单品查询服务
	 */
	@Resource
	private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;

	/**
	 * 店铺查询服务
	 */
	@Resource
	private IShopInfoRSV shopInfoRSV;

	/*
	 * 客户管理域服务
	 */
	@Resource
	private ICustAddrRSV custAddrRSV;

	/**
	 * 促销域
	 */
	@Resource
	private IPromRSV promRsv;

	// 加入购物车
	@RequestMapping(value = "/cart/add")
	public EcpBaseResponseVO addToCart(Model model, @Valid ROrdCartReqVO vo) {
		LogUtil.info(MODULE, "加入购物车开始");
		ROrdCartRequest rdto = new ROrdCartRequest();

		// 获取staffId
		// Long staffId = ParamsTool.getStaffId();
		Long staffId = rdto.getStaff().getId();
		rdto.setStaffId(staffId);

		EcpBaseResponseVO resp = new EcpBaseResponseVO();
		try {

			// ObjectCopyUtil.copyObjValue(vo, rdto, "ordCartItemList", false);
			String shopName="";
			if(vo.getShopId() !=null){
				ShopInfoResDTO shop = shopInfoRSV.findShopInfoByShopID(vo.getShopId());
				shopName=shop.getShopName();
			}
			
			rdto.setId(vo.getId());
			rdto.setCartType(vo.getCartType());
			rdto.setShopId(vo.getShopId());
			rdto.setShopName(shopName);
			rdto.setPromId(vo.getPromId());
			
		
		

			List<ROrdCartItemRequest> itList = new ArrayList<ROrdCartItemRequest>();

			JSONArray itemList = JSONArray.parseArray(vo.getOrdCartItemList());
			for (int i = 0; i < itemList.size(); i++) {
				JSONObject it = itemList.getJSONObject(i);
				ROrdCartItemRequest item = new ROrdCartItemRequest();

				if(StringUtil.isBlank(shopName) && it.getLong("shopId") !=null ){
					ShopInfoResDTO shop = shopInfoRSV.findShopInfoByShopID(it.getLong("shopId"));
					shopName=shop.getShopName();
					rdto.setShopName(shopName);
				}
				
				String ctype = it.getString("cartType");
				item.setCartType(StringUtil.isEmpty(ctype) ? "01" : ctype);
				item.setShopId(it.getLong("shopId"));
				item.setShopName(shopName);
				item.setStaffId(staffId);
				item.setGdsId(it.getLong("gdsId"));
				item.setGdsName(it.getString("gdsName"));
				item.setSkuId(it.getLong("skuId"));
				String skuInfo = it.getString("skuInfo");
				item.setSkuInfo(skuInfo);
				String groupType = it.getString("groupType");
				item.setGroupType(StringUtil.isEmpty(groupType) ? "0"
						: groupType);
				item.setGroupDetail(it.getString("groupDetail"));
				item.setGdsType(Long.valueOf(it.getString("gdsType")));
				if(StringUtil.isBlank(it.getString("orderAmount"))){
					item.setOrderAmount(1l);
				} else {
					item.setOrderAmount(Long.valueOf(it.getString("orderAmount")));
				}

				item.setCategoryCode(it.getString("mainCatgs"));
				if (!StringUtil.isBlank(it.getString("scoreTypeId")))
					item.setScoreTypeId(Long.valueOf(it
							.getString("scoreTypeId")));
				String prnFlag = it.getString("prnFlag");
				if(StringUtil.isBlank(prnFlag)) prnFlag = "0";
				item.setPrnFlag(prnFlag);
				if(!StringUtil.isBlank(it.getString("promId"))) {
					item.setPromId(Long.valueOf(it.getString("promId")));
				}
				
				itList.add(item);
			}
			rdto.setOrdCartItemList(itList);

			ordCartRSV.addToCart(rdto);

			resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
			resp.setResultMsg("添加成功");
		} catch (BusinessException bus) {
			resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
			resp.setResultMsg(bus.getErrorMessage());
			LogUtil.error(MODULE, bus.getErrorMessage());
		} catch (Exception e) {
			resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
			resp.setResultMsg(e.getMessage());
			LogUtil.error(MODULE, e.getMessage());
		}

		return resp;
	}

	// 加入购物车
	@RequestMapping(value = "/cart/mini/add")
	public EcpBaseResponseVO addToCartMini(Model model, @Valid ROrdCartReqVO vo) {
		LogUtil.info(MODULE, "加入购物车开始");
		ROrdCartRequest rdto = new ROrdCartRequest();
		EcpBaseResponseVO resp = new EcpBaseResponseVO();

		try {

			// 获取单品信息
			GdsSkuInfoReqDTO reqDto = new GdsSkuInfoReqDTO();
			reqDto.setId(vo.getSkuId());
			GdsSkuInfoRespDTO skuInfo = gdsSkuInfoQueryRSV
					.querySkuInfoByOptions(reqDto);

			if (skuInfo.getId() == null || skuInfo.getId() == 0) {
				resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
				resp.setResultMsg("非法商品，请联系管理员！");
				return resp;
			}

			// 单品参数获取
			Long skuId = skuInfo.getId();
			Long gdsId = skuInfo.getGdsId();
			Long shopId = skuInfo.getShopId();
			Long staffId = rdto.getStaff().getId();
			Long gdstypeId = skuInfo.getGdsTypeId();
			String gdsName = skuInfo.getGdsName();
			String categoryCode = skuInfo.getMainCatgs();
			String cartType = StringUtil.isEmpty(vo.getCartType()) ? "01" : vo
					.getCartType();
			ShopInfoResDTO shop = shopInfoRSV.findShopInfoByShopID(shopId);
			String shopName = shop.getShopName();
			String skuProp = StringUtil.isEmpty(skuInfo.getSkuProps()) ? ""
					: skuInfo.getSkuProps();
			Long scoreTypeId = vo.getScoreTypeId();

			// 数量默认
			Long amount = 1l;
			if (null != vo.getAmount()) {
				amount = vo.getAmount();
			}

			rdto.setId(vo.getId());
			rdto.setPromId(vo.getPromId());
			rdto.setCartType(cartType);
			rdto.setShopId(shopId);
			rdto.setStaffId(staffId);
			rdto.setShopName(shopName);

			List<ROrdCartItemRequest> itList = new ArrayList<ROrdCartItemRequest>();

			ROrdCartItemRequest item = new ROrdCartItemRequest();
			item.setCartType(cartType);
			item.setShopId(shopId);
			item.setShopName(shopName);
			item.setStaffId(staffId);
			item.setGdsId(gdsId);
			item.setGdsName(gdsName);
			item.setSkuId(skuId);
			item.setSkuInfo(skuProp);
			item.setGroupType("0");
			item.setGroupDetail(null);
			item.setGdsType(gdstypeId);
			item.setOrderAmount(amount);
			item.setGroupDetail(skuId + "");
			item.setCategoryCode(categoryCode);
			item.setScoreTypeId(scoreTypeId);
			item.setPrnFlag("0");
			itList.add(item);
			rdto.setOrdCartItemList(itList);
			ordCartRSV.addToCart(rdto);

			resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
			resp.setResultMsg("添加成功");
		} catch (BusinessException bus) {
			resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
			resp.setResultMsg(bus.getErrorMessage());
			LogUtil.error(MODULE, bus.getErrorMessage());
		} catch (Exception e) {
			resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
			resp.setResultMsg(e.getMessage());
			LogUtil.error(MODULE, e.getMessage());
		}

		return resp;
	}

	@RequestMapping
	public String init(Model model) {
		return "/order/cart/init";
	}

	// 展示购物车
	@RequestMapping(value = "/cart/list")
	public String showCarList(Model model) {
		ROrdCartRequest info = new ROrdCartRequest();
		info.setStaffId(info.getStaff().getId());
		//暂时固定设置为WEB访问来源
		info.setSource(CommonConstants.SOURCE.SOURCE_APP);
		RShowOrdCartsResponse cartList = ordCartRSV.queryCart(info);
		LogUtil.info(MODULE,"展示购物车:"+JSON.toJSONString(cartList));
		//测试商品失效
		//cartList.getOrdCartList().get(0).getOrdCartItemList().get(0).setGdsStatus(false);
		//cartList.getOrdCartList().get(0).getOrdCartItemList().get(0).setGdsType(2L);
		//cartList.getOrdCartList().get(0).getOrdCartItemList().get(1).setGdsType(1L);
		//cartList.getOrdCartList().get(0).getOrdCartItemList().get(2).setGdsType(2L);
		//测试组合商品失效
		//cartList.getOrdCartList().get(0).getGroupLists().get(0).get(0).setGdsStatus(false);

		// List<ROrdCartResponse> order = cartList.getOrdCartList();
		// order.get(0).getOrdCartItemList();
		// ROrdCartItemResponse
		// cartList.setCartPromRespDTO(null);
		model.addAttribute("cartList", cartList);

		return "/order/cart/list/cart-list";
	}
	/**
	 * 
	 * 功能描述：全选商品，刷新所有店铺、明细优惠信息
	 *
	 * @author  曾海沥(zenghl)
	 * <p>创建日期 ：2015-11-13 下午4:16:45</p>
	 *
	 * @param model
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping(value = "/cart/selectAll")
	@ResponseBody
	public JSONObject selectAllItem(Model model) {
		ROrdCartRequest info = new ROrdCartRequest();
		info.setStaffId(info.getStaff().getId());
		//暂时固定设置为WEB访问来源
		info.setSource(CommonConstants.SOURCE.SOURCE_APP);
		RShowOrdCartsResponse cartList = ordCartRSV.queryCart(info);

		JSONObject obj = new JSONObject();
		obj.put("prom", cartList.getCartPromRespDTO());

		return obj;
	}

	/**
	 * 
	 * 功能描述：更新购物车数量
	 *
	 * @author 曾海沥(Terry)
	 *         <p>
	 *         创建日期 ：2015-9-30 上午11:46:00
	 *         </p>
	 *
	 * @return
	 *
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@RequestMapping(value = "/updateCartItemNumber")
	@ResponseBody
	public JSONObject updateCartItemNumber(Model model, ROrdCartChgReqVO vo)
			throws Exception {
		ROrdCartChgRequest r = new ROrdCartChgRequest();
		r.setrOrdCartItemRequest(vo.getOrdCartItem());
		r.setrOrdCartChangeRequest(vo.getOrdCartChg());
		r.setSource(CommonConstants.SOURCE.SOURCE_APP);
		ROrdCartChgResponse out = ordCartItemRSV.updateOrdCartItemAmount(r);

		return selectProm(out.getrOrdCartChangeRequest());
	}

	/**
	 * 
	 * 功能描述：更新组合商品数量
	 *
	 * @author 曾海沥(zenghl)
	 *         <p>
	 *         创建日期 ：2015-11-7 下午5:11:55
	 *         </p>
	 *
	 * @param model
	 * @param vo
	 * @return
	 *
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@RequestMapping(value = "/updateGroupNumber")
	@ResponseBody
	public JSONObject updateGroupNumber(Model model, ROrdCartChgReqVO vo) {
		RGroupChgRequest r = new RGroupChgRequest();
		r.setrOrdCartItemRequests(vo.getOrdCartItems());
		r.setSource(CommonConstants.SOURCE.SOURCE_APP);
		ordCartItemRSV.updateGroupAmount(r);
		JSONObject obj = new JSONObject();
		obj.put("success", true);
		return obj;
	}

	/**
	 * 
	 * 功能描述：删除组合商品
	 *
	 * @author 曾海沥(zenghl)
	 *         <p>
	 *         创建日期 ：2015-11-7 下午5:13:18
	 *         </p>
	 *
	 * @param model
	 * @param vo
	 * @return
	 *
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@RequestMapping(value = "/deleteGroupItems")
	@ResponseBody
	public JSONObject deleteGroupItems(Model model, ROrdCartChgReqVO vo) {
		RGroupChgRequest r = new RGroupChgRequest();
		r.setrOrdCartItemRequests(vo.getOrdCartItems());
		ordCartItemRSV.deleteGroup(r);
		JSONObject obj = new JSONObject();
		obj.put("success", true);
		return obj;
	}

	/**
	 * 
	 * 功能描述：修改店铺促销信息
	 *
	 * @author 曾海沥(zenghl)
	 *         <p>
	 *         创建日期 ：2015-10-28 上午11:36:27
	 *         </p>
	 *
	 * @param model
	 * @param vo
	 * @return
	 * @throws Exception
	 *
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	@RequestMapping(value = "/modifyShopProm")
	@ResponseBody
	public JSONObject modifyShopProm(Model model, ROrdCartChgReqVO vo)
			throws Exception {
		ROrdCartChgRequest chg = new ROrdCartChgRequest();
		chg.setrOrdCartItemRequest(vo.getOrdCartItem());
		chg.setrOrdCartChangeRequest(vo.getOrdCartChg());
		chg.setSource(CommonConstants.SOURCE.SOURCE_APP);

		ROrdCartChgResponse out = ordCartRSV.updateOrdCartProm(chg);

		return selectProm(out.getrOrdCartChangeRequest());
	}

	/**
	 * 
	 * 功能描述：修改明细信息的促销
	 *
	 * @author 曾海沥(zenghl)
	 *         <p>
	 *         创建日期 ：2015-10-20 上午9:59:00
	 *         </p>
	 *
	 * @param model
	 * @param
	 * @param
	 * @param
	 * @return
	 * @throws Exception
	 *
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	@RequestMapping(value = "/modifyShopItemProm")
	@ResponseBody
	public JSONObject modifyShopItemProm(Model model, ROrdCartChgReqVO vo)
			throws Exception {
		ROrdCartChgRequest chg = new ROrdCartChgRequest();
		chg.setrOrdCartItemRequest(vo.getOrdCartItem());
		chg.setrOrdCartChangeRequest(vo.getOrdCartChg());
		chg.setSource(CommonConstants.SOURCE.SOURCE_APP);

		ROrdCartChgResponse out = ordCartItemRSV.updateOrdCartItemProm(chg);

		return selectProm(out.getrOrdCartChangeRequest());
	}

	/**
	 * 
	 * 功能描述：选择/不选择商品
	 *
	 * @author 曾海沥(zenghl)
	 *         <p>
	 *         创建日期 ：2015-10-23 下午9:26:10
	 *         </p>
	 *
	 * @param model
	 * @param vo
	 * @return
	 *
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@RequestMapping(value = "/checkShopItem")
	@ResponseBody
	public JSONObject checkShopItem(Model model, ROrdCartChgReqVO vo) {
		ROrdCartChgRequest chg = new ROrdCartChgRequest();
		chg.setrOrdCartItemRequest(vo.getOrdCartItem());
		chg.setrOrdCartChangeRequest(vo.getOrdCartChg());
		chg.setSource(CommonConstants.SOURCE.SOURCE_APP);

		ROrdCartChgResponse out = ordCartRSV.assembleParamForProm(chg);
		

		return selectProm(out.getrOrdCartChangeRequest());
	}

	/**
	 * 
	 * 功能描述：获得购物车内容，并生成预订单内容
	 *
	 * @author 曾海沥(zenghl)
	 *         <p>
	 *         创建日期 ：2015-10-3 下午5:25:31
	 *         </p>
	 *
	 * @return
	 *
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	// @RequestMapping(value="/cart/create-pre-order")
	// public String createPreOrder(Model model,RCrePreOrdsRequestVO vo){
	// RCrePreOrdsRequest pre = new RCrePreOrdsRequest();
	// //设置预订单基础信息
	// pre.setStaffId(vo.getStaffId());
	// pre.setSource(vo.getSource());
	//
	// List<RCrePreOrdRequest> carList = new ArrayList<RCrePreOrdRequest>();
	// for(RCrePreOrdReqVO car : vo.getCarList()){
	// //设置购物车
	// RCrePreOrdRequest c = new RCrePreOrdRequest();
	// c.setStaffId(car.getStaffId());
	// c.setCartId(car.getCartId());
	// c.setPromId(car.getPromId());
	// //设置购物车明细项目
	// List<RCrePreOrdItemRequest> itemList = new
	// ArrayList<RCrePreOrdItemRequest>();
	// for(RCrePreOrdItemReqVO it : vo.getCartItemList()){
	// if(it.getCartId().equals(car.getCartId())){
	// RCrePreOrdItemRequest item = new RCrePreOrdItemRequest();
	// item.setCartItemId(it.getCartItemId());
	// item.setPromId(it.getPromId());
	// itemList.add(item);
	// }
	// }
	// c.setCartItemIdList(itemList);
	// carList.add(c);
	// }
	// pre.setCarList(carList);
	//
	// RPreOrdMainsResponse preOrd = ordCartRSV.createPreOrd(pre);
	//
	// model.addAttribute("preOrd", preOrd);
	//
	// return "/order/build/build-create";
	// }

	/**
	 * 
	 * 功能描述：批量删除购物车明细内容
	 *
	 * @author 曾海沥(zenghl)
	 *         <p>
	 *         创建日期 ：2015-10-8 上午9:46:03
	 *         </p>
	 *
	 * @return
	 *
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@RequestMapping(value = "/batchDeleteItem")
	@ResponseBody
	public JSONObject batchDeleteCartItem(Model model, ROrdCartChgReqVO vo) throws Exception {
		RBatchCartChgRequest params = new RBatchCartChgRequest();
		params.setrOrdCartItemRequests(vo.getOrdCartItems());
		//ROrdCartsCommRequest b = ordCartRSV.deleteBatchCartItems(a);
		ordCartRSV.deleteBatchCartItems(params);
		//CartPromRespDTO c = promRsv.initCartPromMap(b);
		JSONObject obj = new JSONObject();
		obj.put("success", true);
		//obj.put("data", c);
		return obj;
	}

	/**
	 * 
	 * 功能描述：删除购物车明细
	 *
	 * @author 曾海沥(zenghl)
	 *         <p>
	 *         创建日期 ：2015-9-30 下午8:38:57
	 *         </p>
	 *
	 * @param model
	 * @param
	 * @param
	 * @param
	 * @return
	 * @throws Exception
	 *
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	@RequestMapping(value = "/deleteCartItem")
	@ResponseBody
	public JSONObject deleteCartItem(Model model, ROrdCartChgReqVO vo) throws Exception {
		ROrdCartChgResponse r = delCartItem(vo);
		JSONObject returnProm = new JSONObject();
		if (r != null && r.getrOrdCartChangeRequest() != null) {
			returnProm = selectProm(r.getrOrdCartChangeRequest());
		} else {
			returnProm.put("success", true);
			returnProm.put("data", null);
		}
		return returnProm;
	}

	/**
	 * 
	 * 功能描述：删除购物车明细
	 *
	 * @author 曾海沥(zenghl)
	 *         <p>
	 *         创建日期 ：2015-9-30 下午9:01:12
	 *         </p>
	 *
	 * @param
	 * @param
	 * @param
	 *
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 * @return
	 */
	private ROrdCartChgResponse delCartItem(ROrdCartChgReqVO vo) {
		ROrdCartChgRequest r = new ROrdCartChgRequest();
		r.setrOrdCartItemRequest(vo.getOrdCartItem());
		if (vo.getOrdCartChg().getrOrdCartCommRequest()
				.getOrdCartItemCommList() != null
				&& vo.getOrdCartChg().getrOrdCartCommRequest()
						.getOrdCartItemCommList().size() > 0) {
			r.setrOrdCartChangeRequest(vo.getOrdCartChg());
		}
		r.setSource(CommonConstants.SOURCE.SOURCE_APP);
		return ordCartItemRSV.delOrdCartItem(r);
	}

	/**
	 * 
	 * 功能描述：请求促销域
	 *
	 * @author 曾海沥(zenghl)
	 *         <p>
	 *         创建日期 ：2015-10-21 下午4:37:37
	 *         </p>
	 *
	 * @param r
	 * @return
	 *
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	private JSONObject selectProm(ROrdCartChangeRequest r) {
		JSONObject obj = new JSONObject();
		obj.put("success", true);
		LogUtil.info(MODULE,"变更数量入参:"+JSON.toJSONString(r));
		obj.put("data", promRsv.selectProm(r));
		LogUtil.info(MODULE,"变更数量:"+JSON.toJSONString(obj));
		return obj;
	}

	@RequestMapping(value = "/getcartcount")
	@ResponseBody
	public Long getCartCount() throws BusinessException {
		RQueryCartRequest q = new RQueryCartRequest();
		q.setStaffId(q.getStaff().getId());
		long a = 0;
		try {
			a = ordCartRSV.querySumAmountByStaffId(q);
		} catch (Exception e) {
			LogUtil.error(MODULE, "当前会员未登录", e);
			return new Long(0);
		}

		return a;
	}

}
