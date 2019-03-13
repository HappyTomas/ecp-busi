package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Ord019Req;
import com.zengshi.ecp.app.resp.Ord019Resp;
import com.zengshi.ecp.busi.order.OrdConstant;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupCodeRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupDetailRSV;
import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartItemResponse;
import com.zengshi.ecp.order.dubbo.dto.RPreOrdMainResponse;
import com.zengshi.ecp.order.dubbo.dto.RPreOrdMainsResponse;
import com.zengshi.ecp.order.dubbo.dto.RPreOrdSubResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartItemRSV;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopStaffGroupReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopManageRSV;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 优惠码查询<br>
 * Date:2016年10月21日上午11:03:42  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author lwy
 * @version  
 * @since JDK 1.6
 */
@Service("ord019")
@Action(bizcode = "ord019", userCheck = true)
@Scope("prototype")
public class Ord019Action  extends AppBaseAction<Ord019Req, Ord019Resp>{

    private static final String MODULE = Ord019Action.class.getName();
    
    @Resource
    private ICustInfoRSV  custInfoRSV;
    
    @Resource
    private IOrdCartItemRSV  ordCartItemRSV;
    
    @Resource
    private IShopManageRSV shopManageRSV;
    
    @Resource
    private ICoupDetailRSV coupDetailRSV;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        CoupCodeRespDTO coupCodeResp = new CoupCodeRespDTO();
        ROrdCartCommRequest rOrdCartCommRequest = new ROrdCartCommRequest();
       
        Long staffId = rOrdCartCommRequest.getStaff().getId();
      //获取缓存中保存购物车信息
        RPreOrdMainsResponse ordMains = (RPreOrdMainsResponse) CacheUtil.getItem(this.request.getRedisKey());
       
        //获取使用优惠码的订单
        RPreOrdMainResponse ordMain = null;
        for(int i=0;i<ordMains.getPreOrdMainList().size();i++){
            ordMain = ordMains.getPreOrdMainList().get(i);
            if(this.request.getShopId().longValue()==ordMain.getShopId().longValue()){
                break;
            }
        }
        rOrdCartCommRequest.setCoupCode(this.request.getCoupCode());
        rOrdCartCommRequest.setId(ordMain.getCartId());
        rOrdCartCommRequest.setSource(this.request.getSourceKey());
        rOrdCartCommRequest.setStaffId(staffId);
        rOrdCartCommRequest.setShopId(this.request.getShopId());
        // 补齐购物车信息
        CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
        custInfoReqDTO.setId(staffId);
        CustInfoResDTO custInfoResDTO = custInfoRSV.getCustInfoById(custInfoReqDTO);
        ShopStaffGroupReqDTO shopStaffGroupReqDTO = new ShopStaffGroupReqDTO();
        shopStaffGroupReqDTO.setStaffId(custInfoResDTO.getId());
        shopStaffGroupReqDTO.setShopId(this.request.getShopId());
        // 客户组id
        String custGroupValue = null;
        if (custInfoResDTO != null && custInfoResDTO.getId() != null
                && custInfoResDTO.getId() != 0) {
            custGroupValue = shopManageRSV.queryShopStaffGroup(shopStaffGroupReqDTO);
        }
        // 客户基本信息
        rOrdCartCommRequest.setCustGroupValue(custGroupValue);
        rOrdCartCommRequest.setCustLevelValue(custInfoResDTO.getCustLevelCode());
        rOrdCartCommRequest.setAreaValue(custInfoResDTO.getProvinceCode());
        //web  渠道
        rOrdCartCommRequest.setChannelValue(this.request.getSourceKey());  

        // 单品购物车明细信息
        List<ROrdCartItemCommRequest> rOrdCartItemCommRequests = new ArrayList<ROrdCartItemCommRequest>();
        if(ordMain.getPreOrdSubList()!=null){
            for (RPreOrdSubResponse preOrdSubresp : ordMain.getPreOrdSubList()) {
                ROrdCartItemCommRequest rOrdCartItemCommRequest = new ROrdCartItemCommRequest();
                if(preOrdSubresp.getCartItemId()!=null){           
                    rOrdCartItemCommRequest.setId(preOrdSubresp.getCartItemId());            
                    ROrdCartItemResponse ordCartItem = ordCartItemRSV.queryCartItemByItemId(rOrdCartItemCommRequest);
                    ObjectCopyUtil.copyObjValue(ordCartItem, rOrdCartItemCommRequest, null, false);
                    rOrdCartItemCommRequest.setPromId(ordCartItem.getPromId());
                }else{
                    rOrdCartItemCommRequest.setGdsId(preOrdSubresp.getGdsId());
                    rOrdCartItemCommRequest.setGdsName(preOrdSubresp.getGdsName());
                    rOrdCartItemCommRequest.setGroupType(preOrdSubresp.getGroupType());
                    rOrdCartItemCommRequest.setGroupDetail(preOrdSubresp.getGroupDetail());
                    rOrdCartItemCommRequest.setGdsType(preOrdSubresp.getGdsType());
                    rOrdCartItemCommRequest.setShopId(this.request.getShopId());
                    rOrdCartItemCommRequest.setShopName(ordMain.getShopName());
                    rOrdCartItemCommRequest.setOrderAmount(preOrdSubresp.getOrderAmount());
                    rOrdCartItemCommRequest.setSiteId(rOrdCartItemCommRequest.getSiteId());
                    rOrdCartItemCommRequest.setSkuId(preOrdSubresp.getSkuId());
                    
                }          
                rOrdCartItemCommRequest.setDiscountMoney(preOrdSubresp.getBaseDiscountMoney());
                rOrdCartItemCommRequests.add(rOrdCartItemCommRequest);
            }
        }
        if(ordMain.getGroupLists()!=null){
            //组合套餐购物车明细信息
            for(List<RPreOrdSubResponse> preOrdSubResps:ordMain.getGroupLists()){
                for(RPreOrdSubResponse preOrdSubResp:preOrdSubResps){
                    ROrdCartItemCommRequest rOrdCartItemCommRequest = new ROrdCartItemCommRequest();
                    if(preOrdSubResp.getCartItemId()!=null){           
                        rOrdCartItemCommRequest.setId(preOrdSubResp.getCartItemId());            
                        ROrdCartItemResponse ordCartItem = ordCartItemRSV.queryCartItemByItemId(rOrdCartItemCommRequest);
                        ObjectCopyUtil.copyObjValue(ordCartItem, rOrdCartItemCommRequest, null, false);
                        rOrdCartItemCommRequest.setPromId(ordCartItem.getPromId());
                    }else{
                        rOrdCartItemCommRequest.setGdsId(preOrdSubResp.getGdsId());
                        rOrdCartItemCommRequest.setGdsName(preOrdSubResp.getGdsName());
                        rOrdCartItemCommRequest.setGroupType(preOrdSubResp.getGroupType());
                        rOrdCartItemCommRequest.setGroupDetail(preOrdSubResp.getGroupDetail());
                        rOrdCartItemCommRequest.setGdsType(preOrdSubResp.getGdsType());
                        rOrdCartItemCommRequest.setShopId(this.request.getShopId());
                        rOrdCartItemCommRequest.setShopName(ordMain.getShopName());
                        rOrdCartItemCommRequest.setOrderAmount(preOrdSubResp.getOrderAmount());
                        rOrdCartItemCommRequest.setSiteId(rOrdCartItemCommRequest.getSiteId());
                        rOrdCartItemCommRequest.setSkuId(preOrdSubResp.getSkuId());
                        
                    }          
                    rOrdCartItemCommRequest.setDiscountMoney(preOrdSubResp.getBaseDiscountMoney());
                    rOrdCartItemCommRequests.add(rOrdCartItemCommRequest);
                }
            }
        }
        // 每个店铺的明细列表
        rOrdCartCommRequest.setOrdCartItemCommList(rOrdCartItemCommRequests);
        coupCodeResp = coupDetailRSV.queryOrdCheckCoupByCode(rOrdCartCommRequest);
        String redisKey = OrdConstant.ORDER_SESSION_KEY_PREFIX +staffId+ DateUtil.getCurrentTimeMillis();//临时存储优惠码信息
        CacheUtil.addItem(redisKey, coupCodeResp,OrdConstant.APP_ORDER_SESSION_TIME);       
        coupCodeResp.setHashKey(redisKey);
        this.response.setCoupCode(coupCodeResp.getCoupCode());
        this.response.setCoupValue(coupCodeResp.getCoupValue());
        this.response.setHashKey(redisKey);
        this.response.setIfCanUse(coupCodeResp.getIfCanUse());
        this.response.setResultMsg(coupCodeResp.getResultMsg());
    }

}

