package com.zengshi.ecp.order.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.general.order.interfaces.IOrdCartsChkRSV;
import com.zengshi.ecp.order.dubbo.dto.RBatchCartChgRequest;
import com.zengshi.ecp.order.dubbo.dto.RCrePreOrdsRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartChgRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartChgResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartRequest;
import com.zengshi.ecp.order.dubbo.dto.RPreOrdMainsResponse;
import com.zengshi.ecp.order.dubbo.dto.RQueryCartRequest;
import com.zengshi.ecp.order.dubbo.dto.RShowOrdCartsResponse;
import com.zengshi.ecp.order.dubbo.dto.cart.FastOrdRequest;
import com.zengshi.ecp.order.dubbo.dto.cart.FastPreOrdRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartRSV;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdCartItemSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdCartSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;


public class OrdCartRSVImpl implements IOrdCartRSV {
    
    private static final String MODULE = OrdCartRSVImpl.class.getName();
    
    @Resource
    private IOrdCartSV ordCartSV;
    
    @Resource
    private IOrdCartItemSV ordCartItemSV;
    
    private List<IOrdCartsChkRSV> ordCartsChkRSVs;
    
    @Resource(name="gdsOrdCartsChkRSV")
    private IOrdCartsChkRSV gdsOrdCartsChkRSV;
    @Resource(name="promCartChkRSV")
    private IOrdCartsChkRSV promOrdCartsChkRSV;
    @Resource(name="scoreOrdCartsChkRSV")
    private IOrdCartsChkRSV staffOrdCartsChkRSV;
    
  
    public List<IOrdCartsChkRSV> getOrdCartsChkRSVs() {
        return ordCartsChkRSVs;
    }
    
    @Resource
    public void setOrdCartsChkRSVs(List<IOrdCartsChkRSV> ordCartsChkRSVs) {
        this.ordCartsChkRSVs = ordCartsChkRSVs;
    }
    /**
     * 
     * 加入购物车dubbo服务. 
     * @see com.zengshi.ecp.order.dubbo.interfaces.IOrdCartRSV#addToCart(com.zengshi.ecp.order.dubbo.dto.ROrdCartRequest)
     */
    @Override
    public void addToCart(ROrdCartRequest info) throws BusinessException {
        if(info==null){
            LogUtil.info(MODULE, "入参不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(StringUtil.isBlank(info.getCartType())){
            LogUtil.info(MODULE, "购物车类型不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_351000);            
        }
        if(info.getStaffId()==null){
            LogUtil.info(MODULE, "买家id不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);            
        }
        if(info.getStaffId()==0){
            LogUtil.info(MODULE, "买家未登陆成功，请先登陆再进行操作");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300003);            
        }
        if(info.getShopId()==null){
            LogUtil.info(MODULE, "店铺id不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311002);            
        }
        if(StringUtil.isBlank(info.getShopName())){
            LogUtil.info(MODULE, "店铺名称不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311005);            
        }
        if(CollectionUtils.isEmpty(info.getOrdCartItemList())){
            LogUtil.info(MODULE, "购物侧列表不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_351001);            
        }
        try {
            LogUtil.info(MODULE, "加入购物车开始");
            this.ordCartSV.addToCart(info);
        } catch (BusinessException be) {
            
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            LogUtil.error(MODULE, "加入购物车异常", e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_350000);
        }
    }
    /**
     * 
     * 展示购物车的dubbo服务. 
     * @see com.zengshi.ecp.order.dubbo.interfaces.IOrdCartRSV#showCart(com.zengshi.ecp.order.dubbo.dto.ROrdCartRequest)
     */
    @Override
    public RShowOrdCartsResponse queryCart(ROrdCartRequest info)  throws BusinessException{
        if(info==null){
            LogUtil.info(MODULE, "入参不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(info.getStaffId()==null){
            LogUtil.info(MODULE, "买家id不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);            
        }        
        try {
           return this.ordCartSV.queryCart(info);
        } catch (BusinessException be) {
            
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_350001);
        }
    }

    /**
     *
     * 展示购物车的dubbo服务.
     * @see com.zengshi.ecp.order.dubbo.interfaces.IOrdCartRSV#fastOrdCart(com.zengshi.ecp.order.dubbo.dto.FastOrdRequest)
     */
    @Override
    public RShowOrdCartsResponse fastOrdCart(FastOrdRequest info)  throws BusinessException{
        if(info==null){
            LogUtil.info(MODULE, "入参不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000);
        }
        if(info.getStaffId()==null){
            LogUtil.info(MODULE, "买家id不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);
        }
        if(CollectionUtils.isEmpty(info.getOrdCartItemList())){
            LogUtil.info(MODULE, "立即购买购物车明细不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_351001);
        }
        try {
            return this.ordCartSV.fastOrdCart(info);
        } catch (BusinessException be) {

            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_350001);
        }
    }

    /**
     * 
     * 购物车生成预订单. 
     * @see com.zengshi.ecp.order.dubbo.interfaces.IOrdCartRSV#carToPreOrd(com.zengshi.ecp.order.dubbo.dto.ROrdCartsRequest)
     */
    @Override
    public RPreOrdMainsResponse createPreOrd(RCrePreOrdsRequest info) throws BusinessException {
        if(info==null){
            LogUtil.info(MODULE, "入参不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(info.getStaffId()==null){
            LogUtil.info(MODULE, "买家id不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);            
        }
        if(CollectionUtils.isEmpty(info.getCarList())){
            LogUtil.info(MODULE, "购物侧列表不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_351001);            
        }
        RPreOrdMainsResponse  rPreOrdMainsResponse  = null;
        try {
            LogUtil.info(MODULE, "========校验开始============");
            ROrdCartsCommRequest rOrdCartsCommRequest = this.ordCartSV.queryAssembleParamForAll(info);
            LogUtil.info(MODULE, "========入参============"+JSON.toJSONString(rOrdCartsCommRequest).toString());
            ROrdCartsChkResponse rOrdCartsChkResponse = createOrderChk(rOrdCartsCommRequest);
            
            if(rOrdCartsChkResponse != null){
                LogUtil.info(MODULE, "========异常============"+rOrdCartsChkResponse.getMsg());
                if(!rOrdCartsChkResponse.isStatus()){
                    throw new BusinessException(rOrdCartsChkResponse.getMsg());
                }
            }
            rPreOrdMainsResponse = this.ordCartSV.queryPreOrd(info);
            
         } catch (BusinessException be) {
             
             LogUtil.error(MODULE, "===业务异常==="+be);
             throw be;
         } catch (Exception e) {
             LogUtil.error(MODULE, "===系统异常==="+e);
             throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_350002);
         }
        return rPreOrdMainsResponse;
    }

    /**
     *
     * 购物车生成预订单.
     * @see com.zengshi.ecp.order.dubbo.interfaces.IOrdCartRSV#createFastPreOrd(com.zengshi.ecp.order.dubbo.dto.ROrdCartsRequest)
     */
    @Override
    public RPreOrdMainsResponse createFastPreOrd(FastPreOrdRequest info) throws BusinessException {
        if(info==null){
            LogUtil.info(MODULE, "入参不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000);
        }
        if(info.getStaffId()==null){
            LogUtil.info(MODULE, "买家id不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);
        }
        if(CollectionUtils.isEmpty(info.getCarList())){
            LogUtil.info(MODULE, "购物侧列表不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_351001);
        }
        RPreOrdMainsResponse  rPreOrdMainsResponse  = null;
        try {
            LogUtil.info(MODULE, "========校验开始============");
            ROrdCartsCommRequest rOrdCartsCommRequest = this.ordCartSV.queryFastAssembleParamForAll(info);
            LogUtil.info(MODULE, "========入参============"+JSON.toJSONString(rOrdCartsCommRequest).toString());
            ROrdCartsChkResponse rOrdCartsChkResponse = createOrderChk(rOrdCartsCommRequest);

            if(rOrdCartsChkResponse != null){
                LogUtil.info(MODULE, "========异常============"+rOrdCartsChkResponse.getMsg());
                if(!rOrdCartsChkResponse.isStatus()){
                    throw new BusinessException(rOrdCartsChkResponse.getMsg());
                }
            }
            rPreOrdMainsResponse = this.ordCartSV.queryFastPreOrd(info);

        } catch (BusinessException be) {

            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_350002);
        }
        return rPreOrdMainsResponse;
    }

    @Override
    public ROrdCartChgResponse updateOrdCartProm(ROrdCartChgRequest info) throws BusinessException {
        if(info==null){
            LogUtil.info(MODULE, "入参不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(info.getrOrdCartItemRequest().getId()==null){
            LogUtil.info(MODULE, "购物车实例id不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_351002);            
        }
        if(info.getrOrdCartItemRequest().getStaffId()==null){
            LogUtil.info(MODULE, "买家id不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);            
        }        
        ROrdCartChgResponse rpor = null;
        try {
            this.ordCartSV.updateOrdCartProm(info.getrOrdCartItemRequest());
            rpor = this.ordCartSV.queryAssembleParamForProm(info);
         } catch (BusinessException be) {
             
             LogUtil.error(MODULE, "===业务异常==="+be);
             throw be;
         } catch (Exception e) {
             LogUtil.error(MODULE, "===系统异常==="+e);
             throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_350004);
         }    
        return rpor;
    
    }

    @Override
    public ROrdCartsChkResponse createOrderChk(ROrdCartsCommRequest rOrdCartsCommRequest)
            throws BusinessException {
        ROrdCartsChkResponse roc =null;
        LogUtil.info(MODULE, "====调用商品域开始======");
        roc = this.gdsOrdCartsChkRSV.checkOrdCart(rOrdCartsCommRequest);
        if(roc != null && roc.isStatus() ){
            LogUtil.info(MODULE, "====调用商品域成功======"+roc.getMsg());
        } else {
            LogUtil.info(MODULE, "====调用商品域异常======"+roc.getMsg());
            return roc;
        }
        
        LogUtil.info(MODULE, "====调用促销域开始======");
        roc = this.promOrdCartsChkRSV.checkOrdCart(rOrdCartsCommRequest);
        
        if(roc != null && roc.isStatus() ){
            LogUtil.info(MODULE, "====调用促销域成功======"+roc.getMsg());
        } else {
            LogUtil.info(MODULE, "====调用促销域异常======"+roc.getMsg());
            return roc;
        }
        
        
        LogUtil.info(MODULE, "====调用客户域开始======");
        roc = this.staffOrdCartsChkRSV.checkOrdCart(rOrdCartsCommRequest);
        
        if(roc != null && roc.isStatus() ){
            LogUtil.info(MODULE, "====调用客户域成功======"+roc.getMsg());
        } else {
            LogUtil.info(MODULE, "====调用客户域异常======"+roc.getMsg());
            return roc;
        }
        
        /*
        ROrdCartsChkResponse roc = null;
        for(IOrdCartsChkRSV occr :ordCartsChkRSVs ){
            LogUtil.info(MODULE, "===================="+occr.getClass().getName());
            roc = occr.checkOrdCart(rOrdCartsCommRequest);
            
            if(!(roc.isStatus())){
                LogUtil.info(MODULE, "===================="+roc.getMsg());
                break;
            }
            LogUtil.info(MODULE, "222222222222222222"+roc.getMsg());
                    
        }*/
        
        return roc;
    }

    @Override
    public Long querySumAmountByStaffId(RQueryCartRequest rQueryCartRequest)
            throws BusinessException {
        if(rQueryCartRequest==null){
            LogUtil.info(MODULE, "入参不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(rQueryCartRequest.getStaffId()==null){
            LogUtil.info(MODULE, "购物车实例id不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_351002);            
        }
        Long sumNum = 0l;
        try {
            sumNum = this.ordCartItemSV.querySumAmountByStaffId(rQueryCartRequest);
        } catch (BusinessException be) {             
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_350010);
        }
        return sumNum;
    }
    @Override
    public ROrdCartChgResponse assembleParamForProm(ROrdCartChgRequest rOrdCartChgRequest)
            throws BusinessException {
        
        return this.ordCartSV.queryAssembleParamForProm(rOrdCartChgRequest);
    }
    @Override
    public ROrdCartsCommRequest deleteBatchCartItems(RBatchCartChgRequest rBatchCartChgRequest)
            throws BusinessException {
        if(rBatchCartChgRequest==null){
            LogUtil.info(MODULE, "入参不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(CollectionUtils.isEmpty(rBatchCartChgRequest.getrOrdCartItemRequests())){
            LogUtil.info(MODULE, "删除购物车明细列表不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_351001);            
        }
        if(CollectionUtils.isNotEmpty(rBatchCartChgRequest.getrBatchCartsRequests())){
            if(CollectionUtils.isEmpty(rBatchCartChgRequest.getrBatchCartItemsRequests())){
                LogUtil.info(MODULE, "勾选的购物车明细为空");
                throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);
            }
        }  
        if(CollectionUtils.isNotEmpty(rBatchCartChgRequest.getrBatchCartItemsRequests())){
            if(CollectionUtils.isEmpty(rBatchCartChgRequest.getrBatchCartsRequests())){
                LogUtil.info(MODULE, "勾选的店铺为空");
                throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);
            }
        }
        ROrdCartsCommRequest rpor = null;
        try {
            this.ordCartItemSV.deleteCartItemsBatch(rBatchCartChgRequest);
//            if(CollectionUtils.isNotEmpty(rBatchCartChgRequest.getrBatchCartsRequests()) &&
//                    CollectionUtils.isNotEmpty(rBatchCartChgRequest.getrBatchCartItemsRequests())){
//                rpor = this.ordCartSV.queryBatchDeleteForProm(rBatchCartChgRequest);
//            }
         } catch (BusinessException be) {
             
             LogUtil.error(MODULE, "===业务异常==="+be);
             throw be;
         } catch (Exception e) {
             LogUtil.error(MODULE, "===系统异常==="+e);
             throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_350004);
         }    
        return rpor;
    }
}

