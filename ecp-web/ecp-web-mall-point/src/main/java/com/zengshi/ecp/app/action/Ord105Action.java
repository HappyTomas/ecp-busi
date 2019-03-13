package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Ord105Req;
import com.zengshi.ecp.app.resp.Ord10501Resp;
import com.zengshi.ecp.app.resp.Ord105Resp;
import com.zengshi.ecp.base.velocity.ParamToolUtil;
import com.zengshi.ecp.busi.order.OrdConstant;
import com.zengshi.ecp.busi.order.vo.RCrePreOrdItemReqVO;
import com.zengshi.ecp.busi.order.vo.RCrePreOrdReqVO;
import com.zengshi.ecp.general.order.dto.CoupCheckBeanRespDTO;
import com.zengshi.ecp.order.dubbo.dto.RCrePreOrdItemRequest;
import com.zengshi.ecp.order.dubbo.dto.RCrePreOrdRequest;
import com.zengshi.ecp.order.dubbo.dto.RCrePreOrdsRequest;
import com.zengshi.ecp.order.dubbo.dto.RPreOrdMainResponse;
import com.zengshi.ecp.order.dubbo.dto.RPreOrdMainsResponse;
import com.zengshi.ecp.order.dubbo.dto.RPreOrdSubResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartRSV;
import com.zengshi.ecp.order.dubbo.util.CommonConstants;
import com.zengshi.ecp.prom.dubbo.dto.CartPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.CartPromItemDTO;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustAddrReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAddrResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustAddrRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;
import com.alibaba.fastjson.JSON;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 购物车去结算<br>
 * Date:2016年3月5日上午10:29:56 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version
 * @since JDK 1.6
 */
@Service("ord105")
@Action(bizcode = "ord105", userCheck = true)
@Scope("prototype")
public class Ord105Action extends AppBaseAction<Ord105Req, Ord105Resp> {

    @Resource
    private IOrdCartRSV ordCartRSV;

    @Resource
    private IShopInfoRSV shopInfoRSV;
    
    @Resource
    private ICustAddrRSV custaddrRSV;

    private static final String MODULE = Ord105Action.class.getName();

    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {

        if (CollectionUtils.isEmpty(this.request.getCarList())) {
            throw new BusinessException("购物车数据不能为空！");
        }
        
        List<RCrePreOrdReqVO> ordCarts = this.request.getCarList();
        // 放入预订单
        List<RCrePreOrdRequest> ordCartList = new ArrayList<RCrePreOrdRequest>();

        for (RCrePreOrdReqVO ordcart : ordCarts) {
            List<RCrePreOrdItemReqVO> cartItems = ordcart.getCartItemIdList();
            if (CollectionUtils.isEmpty(cartItems)) {
                throw new BusinessException("购物车明细不能为空！");
            }
            RCrePreOrdRequest rCrePreOrdRequest = new RCrePreOrdRequest();
            rCrePreOrdRequest.setCartId(ordcart.getCartId());
            List<RCrePreOrdItemRequest> ordCartItemList = new ArrayList<RCrePreOrdItemRequest>();
            for (RCrePreOrdItemReqVO cartItem : cartItems) {
                RCrePreOrdItemRequest rCrePreOrdItemRequest = new RCrePreOrdItemRequest();
                rCrePreOrdItemRequest.setCartItemId(cartItem.getCartItemId());
                ordCartItemList.add(rCrePreOrdItemRequest);
            }
            rCrePreOrdRequest.setCartItemIdList(ordCartItemList);
            ordCartList.add(rCrePreOrdRequest);
        }
        RCrePreOrdsRequest rOrdCartsRequest = new RCrePreOrdsRequest();
        rOrdCartsRequest.setStaffId(rOrdCartsRequest.getStaff().getId());
        // 数据来源
        
        rOrdCartsRequest.setSource(CommonConstants.SOURCE.SOURCE_APP);
        rOrdCartsRequest.setCarList(ordCartList);
        LogUtil.info(MODULE, "Ord004Action:" + JSON.toJSONString(rOrdCartsRequest));
        RPreOrdMainsResponse resp = ordCartRSV.createPreOrd(rOrdCartsRequest);
        String redisKey = OrdConstant.ORDER_SESSION_KEY_PREFIX + rOrdCartsRequest.getStaff().getId()
                + DateUtil.getCurrentTimeMillis();
        CacheUtil.addItem(redisKey, resp,OrdConstant.APP_ORDER_SESSION_TIME);
        this.response.setRedisKey(redisKey);

        // ===============出参===============================
        List<RPreOrdMainResponse> list = resp.getPreOrdMainList();
        LogUtil.info(MODULE, JSON.toJSONString(list));
        // 提交订单时的合计价格
        // model.addAttribute("cartProm", resp.getCartPromRespDTO());
        //资金账户
        //运费
        Map<Long,Long> realExpressFeesMap = new HashMap<>();
        Map<Long, Long> orderScoreMap = new HashMap<>();
        Long orderScore = 0l;
        List<Ord10501Resp> ord10501Resps = new ArrayList<Ord10501Resp>();
        for (RPreOrdMainResponse ordMain : list) {
            
            Ord10501Resp ord10501Resp = new Ord10501Resp();
            ord10501Resp.setShopId(ordMain.getShopId());
            ord10501Resps.add(ord10501Resp);
            realExpressFeesMap.put(ordMain.getShopId(), ordMain.getRealExpressFee());
            Long ordScore = 0l;
            for(RPreOrdSubResponse ordSub :ordMain.getPreOrdSubList()){
                ordScore += ordSub.getOrderScore();
            }
            orderScoreMap.put(ordMain.getShopId(), ordScore);
            orderScore += ordScore;
        }
        this.response.setOrderScoreMap(orderScoreMap);
        this.response.setOrderScores(orderScore);
        this.response.setRealExpressFeesMap(realExpressFeesMap);
        this.response.setOrd10501Resps(ord10501Resps);
        
        // model.addAttribute("preOrdMainList", list);
        // model.addAttribute("coupSizeMap",coupSizeMap);
        // 配送方式
        Map<Long, String> deliverTypes = new HashMap<Long, String>();
        LogUtil.info(MODULE, "======================获取配送方式=========================");
        for (RPreOrdMainResponse ordMain : list) {
            ShopInfoResDTO shopInfo = shopInfoRSV.findShopInfoByShopID(ordMain.getShopId());
            if (StringUtil.isBlank(shopInfo.getDistribution())) {
                deliverTypes.put(ordMain.getShopId(), OrdConstant.DeliverType.EXPERSS);
            } else {
                deliverTypes.put(ordMain.getShopId(), shopInfo.getDistribution());
            }
        }
        this.response.setDeliverTypes(deliverTypes);

        Long orderMoneys = 0l;// 总金额
        Long realExpressFees = 0l;// 运费
        Long allMoney = 0l;// 应付总额
        Long orderAmounts = 0l;// 总数量

        LogUtil.info(MODULE, "=======================计算金额===================================");
        Map<Long, Long> orderMoneyMap = new HashMap<>();

        for (int i = 0; i < list.size(); i++) {
            RPreOrdMainResponse ordMain = list.get(i);
            orderMoneys += ordMain.getOrderMoney();
            
            orderMoneyMap.put(ordMain.getShopId(), ordMain.getOrderMoney());


            if (deliverTypes.get(ordMain.getShopId()).contains(OrdConstant.DeliverType.EXPERSS)) {
                realExpressFees += ordMain.getRealExpressFee();// 有包含快递就使用快递
            }
            orderAmounts += ordMain.getOrderAmount();


        }
        this.response.setOrderMoneyMap(orderMoneyMap);
        allMoney = orderMoneys + realExpressFees ;
        this.response.setOrderMoneys(orderMoneys);
        this.response.setRealExpressFees(realExpressFees);
        this.response.setAllMoney(allMoney);
        this.response.setOrderAmounts(orderAmounts);
        LogUtil.info(MODULE, "=======================支付方式===================================");
        List<BaseParamDTO> payList = new ParamToolUtil().getParamDTOList("ORD_PAY_TYPE");
        Map<String, String> m = new HashMap<>();
        for (BaseParamDTO pay : payList) {
            m.put(pay.getSpCode(), pay.getSpValue());
        }
        this.response.setPayList(m);
        
        LogUtil.info(MODULE, "===================用户收货地址信息=========================");
        CustAddrReqDTO dto = new CustAddrReqDTO();
        dto.setStaffId(dto.getStaff().getId());
        List<CustAddrResDTO> addrs = custaddrRSV.listCustAddr(dto);
        this.response.setAddrs(addrs);

    }

}
