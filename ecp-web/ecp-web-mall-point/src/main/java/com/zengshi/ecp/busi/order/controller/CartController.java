package com.zengshi.ecp.busi.order.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.order.vo.ROrdCartChgReqVO;
import com.zengshi.ecp.busi.order.vo.ROrdCartReqVO;
import com.zengshi.ecp.general.order.dto.ROrdCartChangeRequest;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartChgRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartChgResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartItemRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartItemResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartResponse;
import com.zengshi.ecp.order.dubbo.dto.RQueryCartRequest;
import com.zengshi.ecp.order.dubbo.dto.RShowOrdCartsResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartItemRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartRSV;
import com.zengshi.ecp.order.dubbo.util.CommonConstants;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustAddrRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

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

            rdto.setId(vo.getId());
            rdto.setCartType(vo.getCartType());
            rdto.setShopId(vo.getShopId());
            rdto.setShopName(vo.getShopName());
            rdto.setPromId(vo.getPromId());

            List<ROrdCartItemRequest> itList = new ArrayList<ROrdCartItemRequest>();

            JSONArray itemList = JSONArray.parseArray(vo.getOrdCartItemList());
            for (int i = 0; i < itemList.size(); i++) {
                JSONObject it = itemList.getJSONObject(i);
                ROrdCartItemRequest item = new ROrdCartItemRequest();
                String ctype = it.getString("cartType");
                item.setCartType(StringUtil.isEmpty(ctype) ? "01" : ctype);
                item.setShopId(it.getLong("shopId"));
                item.setShopName(it.getString("shopName"));
                item.setStaffId(staffId);
                item.setGdsId(it.getLong("gdsId"));
                item.setGdsName(it.getString("gdsName"));
                item.setSkuId(it.getLong("skuId"));
                String skuInfo = it.getString("skuInfo");
                item.setSkuInfo(skuInfo);
                String groupType = it.getString("groupType");
                item.setGroupType(StringUtil.isEmpty(groupType) ? "01"
                        : groupType);
                item.setGroupDetail(it.getString("groupDetail"));
                item.setGdsType(Long.valueOf(it.getString("gdsType")));
                item.setOrderAmount(Long.valueOf(it.getString("orderAmount")));
                item.setCategoryCode(it.getString("mainCatgs"));
                if (!StringUtil.isBlank(it.getString("scoreTypeId")))
                    item.setScoreTypeId(Long.valueOf(it
                            .getString("scoreTypeId")));
                item.setPrnFlag("0");
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

            //单品参数获取
            Long skuId = skuInfo.getId();
            Long gdsId = skuInfo.getGdsId();
            Long shopId = skuInfo.getShopId();
            Long staffId = rdto.getStaff().getId();
            Long gdstypeId = skuInfo.getGdsTypeId();
            String gdsName = skuInfo.getGdsName();
            String categoryCode = skuInfo.getMainCatgs();
            String cartType = StringUtil.isEmpty(vo.getCartType()) ? "01" : vo.getCartType();
            ShopInfoResDTO shop = shopInfoRSV.findShopInfoByShopID(shopId);
            String shopName = shop.getShopName();
            String skuProp = StringUtil.isEmpty(skuInfo.getSkuProps()) ? "00": skuInfo.getSkuProps();
            Long scoreTypeId=vo.getScoreTypeId();
            
            //数量默认
            Long amount=1l;
            if(null != vo.getAmount()){
                amount=vo.getAmount();
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
            item.setGroupType("01");
            item.setGroupDetail(null);
            item.setGdsType(gdstypeId);
            item.setOrderAmount(amount);
            item.setGroupDetail(skuId+"");
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
		info.setSource(CommonConstants.SOURCE.SOURCE_WEB);
        RShowOrdCartsResponse cartList = ordCartRSV.queryCart(info);
        List<ROrdCartResponse> respList = cartList.getOrdCartList();
        int num = 0;
        int itemCount = 0;
        int carNum = 0;
        if(!CollectionUtils.isEmpty(respList)){
            for(ROrdCartResponse resp : respList){
                List<ROrdCartItemResponse> list = resp.getOrdCartItemList();
                for(int j=0;j<list.size();j++){
                    ROrdCartItemResponse cartresp = list.get(j);
                    if(!cartresp.isGdsStatus()){
                        cartresp.setScore(0L);
                        cartresp.setBasePrice(0L);
                        num++;
                    }
                }
                itemCount +=list.size();
            }
            if(itemCount>0){
                if(num == itemCount){
                    carNum = 1;
                }
            }
        }
        model.addAttribute("carNum", carNum);
        model.addAttribute("cartList", cartList); 
        return "/order/cart/cart-list";
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
        ROrdCartChgResponse out = ordCartItemRSV.updateOrdCartItemAmount(r);

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

        ROrdCartChgResponse out = ordCartRSV.assembleParamForProm(chg);

        return selectProm(out.getrOrdCartChangeRequest());
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
     * @param vo
     * @return
     * @throws Exception
     *
     *             <p>
     *             修改历史 ：(修改人，修改时间，修改原因/内容)
     *             </p>
     */
    @RequestMapping(value = "/deleteCartItem")
    @ResponseBody
    public JSONObject deleteCartItem(Model model, ROrdCartChgReqVO vo)
            throws Exception {
        ROrdCartChgResponse r = delCartItem(vo);
        JSONObject returnProm = new JSONObject();
        if(r!=null && r.getrOrdCartChangeRequest()!=null){
            returnProm = selectProm(r.getrOrdCartChangeRequest());
        }else{
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
     *
     *            <p>
     *            修改历史 ：(修改人，修改时间，修改原因/内容)
     *            </p>
     * @return
     */
    private ROrdCartChgResponse delCartItem(ROrdCartChgReqVO vo) {
        ROrdCartChgRequest r = new ROrdCartChgRequest();
        r.setrOrdCartItemRequest(vo.getOrdCartItem());
        if(vo.getOrdCartChg().getrOrdCartCommRequest().getOrdCartItemCommList() != null && 
                vo.getOrdCartChg().getrOrdCartCommRequest().getOrdCartItemCommList().size()>0){
            r.setrOrdCartChangeRequest(vo.getOrdCartChg());
        }
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
        obj.put("data", promRsv.selectProm(r));
        return obj;
    }
    
    
    @RequestMapping(value = "/getcartcount")
    @ResponseBody
    public Long getCartCount() throws BusinessException{
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
