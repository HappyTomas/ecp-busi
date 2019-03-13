package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Ord106Req;
import com.zengshi.ecp.app.resp.Ord106Resp;
import com.zengshi.ecp.base.velocity.ParamToolUtil;
import com.zengshi.ecp.busi.order.OrdConstant;
import com.zengshi.ecp.busi.order.vo.RSumbitMainReqVO;
import com.zengshi.ecp.order.dubbo.dto.ROrdDeliveAddrRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdMainsResponse;
import com.zengshi.ecp.order.dubbo.dto.RPreOrdMainResponse;
import com.zengshi.ecp.order.dubbo.dto.RPreOrdMainsResponse;
import com.zengshi.ecp.order.dubbo.dto.RPreOrdSubResponse;
import com.zengshi.ecp.order.dubbo.dto.RSumbitMainRequest;
import com.zengshi.ecp.order.dubbo.dto.RSumbitMainsRequest;
import com.zengshi.ecp.order.dubbo.dto.RSumbitSubRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.util.CommonConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAddrReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAddrResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustAddrRSV;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;
import com.alibaba.fastjson.JSON;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 提交订单<br>
 * Date:2016年3月5日上午10:30:49 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version
 * @since JDK 1.6
 */
@Service("ord106")
@Action(bizcode = "ord106", userCheck = true)
@Scope("prototype")
public class Ord106Action extends AppBaseAction<Ord106Req, Ord106Resp> {

    @Resource
    private IOrdMainRSV ordMainRSV;

    @Resource
    private ICustAddrRSV custAddrRSV;

    private static final String MODULE = Ord106Action.class.getName();

    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {

        this.response.setExceptionFlag("0");
        this.response.setExceptionContent("");
        try {

            String redisKey = this.request.getRedisKey();


            List<RSumbitMainReqVO> ordMains = this.request.getSumbitMainList();

            RPreOrdMainsResponse rPreOrdMainsResp = (RPreOrdMainsResponse) CacheUtil.getItem(redisKey);
            if(rPreOrdMainsResp == null){
                LogUtil.error(MODULE, "提交订单异常");
                throw new BusinessException("提交订单异常");
            }
            LogUtil.info(MODULE, "去结算缓存数据：" + JSON.toJSONString(rPreOrdMainsResp));
            RSumbitMainsRequest sumbitMains = new RSumbitMainsRequest();
            List<RSumbitMainRequest> sumbitMainList = new ArrayList<RSumbitMainRequest>();

            for (RPreOrdMainResponse rPreOrdMainResp : rPreOrdMainsResp.getPreOrdMainList()) {

                // 缓存中的信息
                RSumbitMainRequest sumbitMain = new RSumbitMainRequest();
                sumbitMain.setStaffId(sumbitMain.getStaff().getId());
                sumbitMain.setShopId(rPreOrdMainResp.getShopId());
                sumbitMain.setShopName(rPreOrdMainResp.getShopName());
                sumbitMain.setBasicMoney(rPreOrdMainResp.getBasicMoney());
                sumbitMain.setOrderMoney(rPreOrdMainResp.getOrderMoney());
                sumbitMain.setOrderAmount(rPreOrdMainResp.getOrderAmount());
                sumbitMain.setCartId(rPreOrdMainResp.getCartId());

                // 复制子订单信息
                List<RSumbitSubRequest> preOrdSubList = new ArrayList<RSumbitSubRequest>();
                for (RPreOrdSubResponse ordSub : rPreOrdMainResp.getPreOrdSubList()) {
                    RSumbitSubRequest sumbitSub = new RSumbitSubRequest();
                    ObjectCopyUtil.copyObjValue(ordSub, sumbitSub, "", false);
                    sumbitSub.setStaffId(sumbitMain.getStaff().getId());
                    preOrdSubList.add(sumbitSub);
                }
                sumbitMain.setPreOrdSubList(preOrdSubList);

                // 处理优惠券，资金账户，积分账户的信息
                for (RSumbitMainReqVO ordMain : ordMains) {
                    // 匹配同一个cartId
                    if (rPreOrdMainResp.getCartId().equals(ordMain.getCartId())) {
                        // =====================订单金额校验=====================
                        // 页面金额重计
                        Long pageOrderMoney = ordMain.getRealMoney() // 最终金额
                                - ordMain.getRealExpressFee(); // 运费

                        Long cacheOrderMoney = rPreOrdMainResp.getOrderMoney();
                        Long cacheRealExpressFees = ordMain.getRealExpressFee();
                        LogUtil.error(MODULE, "购物车信息：" + JSON.toJSONString(ordMain));
                        LogUtil.info(MODULE,"订单金额" + pageOrderMoney + "||" + cacheOrderMoney);
                        LogUtil.info(MODULE,"运费金额" + ordMain.getRealExpressFee() + "||" + cacheRealExpressFees);

                        if (!pageOrderMoney.equals(cacheOrderMoney)) {
                            LogUtil.error(MODULE, "订单号：" + rPreOrdMainResp.getCartId() + "订单金额异常");
                            throw new BusinessException("订单号：" + rPreOrdMainResp.getCartId() + "订单金额异常,请重新检查");
                        }
                        sumbitMain.setDeliverType(ordMain.getDeliverType());
                        sumbitMain.setRealExpressFee(Long.valueOf(ordMain.getRealExpressFee()));
                        sumbitMain.setRealMoney(Long.valueOf(ordMain.getRealMoney()));

                    }
                }
                sumbitMainList.add(sumbitMain);
            }
            // 收货地址管理
            Long addrId = this.request.getAddrId();
            CustAddrReqDTO cust = new CustAddrReqDTO();
            cust.setStaffId(cust.getStaff().getId());
            cust.setId(addrId);

            CustAddrResDTO custresp = custAddrRSV.findAddr(cust);
            // 校验收货地址
            // if (gdsType.contains(OrdConstant.ORDER_ENTITY_TYPE))
            if (StringUtil.isEmpty(custresp)) {
                throw new BusinessException("收货地址不允许为空");
            }
            ROrdDeliveAddrRequest addrreq = new ROrdDeliveAddrRequest();
            ObjectCopyUtil.copyObjValue(custresp, addrreq, "", false);
            // 获取省市县具体位置
            ParamToolUtil area = new ParamToolUtil();
            String newchnlAddress = custresp.getChnlAddress();
            String newProvince = area.getAreaName(custresp.getProvince());
            String newCity = area.getAreaName(custresp.getCityCode());
            String newCounty = area.getAreaName(custresp.getCountyCode());
            if (StringUtil.isBlank(newProvince)) {
                newProvince = "";
            }
            if (StringUtil.isBlank(newProvince)) {
                newCity = "";
            }
            if (StringUtil.isBlank(newProvince)) {
                newCounty = "";
            }
            newchnlAddress = newProvince + newCity + newCounty + newchnlAddress;
            addrreq.setChnlAddress(newchnlAddress);
            LogUtil.info(MODULE, "======================收货地址管理=========================");

            // 设置地址信息
            sumbitMains.setrOrdDeliveAddrRequest(addrreq);
            // 购物车信息
            sumbitMains.setSumbitMainList(sumbitMainList);
            // 设置用户Id
            sumbitMains.setStaffId(sumbitMains.getStaff().getId());
            // 设置支付方式
            sumbitMains.setPayType(this.request.getPayType());

            sumbitMains.setSource(CommonConstants.SOURCE.SOURCE_APP);

            ROrdMainsResponse rOrdMainsResponse = ordMainRSV.sumbitOrd(sumbitMains);

            String redisKey1 = OrdConstant.ORDER_SESSION_KEY_PREFIX + sumbitMains.getStaff().getId()
                    + DateUtil.getCurrentTimeMillis();
            CacheUtil.addItem(redisKey1, rOrdMainsResponse,OrdConstant.APP_PAY_SESSION_TIME);
            this.response.setRedisKey(redisKey1);

        } catch (Exception e) {
            this.response.setExceptionFlag("1");
            this.response.setExceptionContent(e.getMessage());
        }
    }

}
