package com.zengshi.ecp.order.service.busi.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dao.model.OrdSub;
import com.zengshi.ecp.order.dubbo.dto.RConfirmDeliveRequest;
import com.zengshi.ecp.order.dubbo.dto.RConfirmSubInfo;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndSubInfo;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdDeliveryChkSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;

public class OrdDeliveryChkSVImpl implements IOrdDeliveryChkSV {
    
    @Resource
    private IOrdSubSV ordSubSV;
    
    @Resource
    private IOrdMainSV ordMainSV;
    
    private static final String MODULE = OrdDeliveryChkSVImpl.class.getName();
    
    private Long countDeliveryAmount(RConfirmDeliveRequest rConfirmDeliveRequest){
        Long allDeliveryAmount = 0l;
        for(RConfirmSubInfo cfsi:rConfirmDeliveRequest.getrConfirmSubInfo()){
            if(cfsi.getDeliveryAmount() < 1){
                LogUtil.info(MODULE, "子订单"+cfsi.getOrderSubId()+":发货数量小于1");
                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312008);
            }
            allDeliveryAmount += cfsi.getDeliveryAmount();
        }
        return allDeliveryAmount;
    }

    @Override
    public void chkDeliverBathcInput(RConfirmDeliveRequest rConfirmDeliveRequest) {
        /*----校验是否全部发货标识值是否正确--start-----*/
        
        OrdMain om = this.ordMainSV.queryOrderByOrderId(rConfirmDeliveRequest.getOrderId());
        if (om == null) {
            LogUtil.info(MODULE, "未找到[" + rConfirmDeliveRequest.getOrderId() + "]该订单的信息");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312001);
        }
        rConfirmDeliveRequest.setShopId(om.getShopId());
        rConfirmDeliveRequest.setStaffId(om.getStaffId());
        //统计总发货数量
        Long allDeliveryAmount = countDeliveryAmount(rConfirmDeliveRequest);
        //统计总剩余发货数量
        Long allRemainAmount = this.ordSubSV.queryCountRemainAmount(rConfirmDeliveRequest.getOrderId());
        
        //发货数量为0 或者剩余发货数量为0 抛异常
        if(allDeliveryAmount < 1l){
            LogUtil.info(MODULE, "订单"+rConfirmDeliveRequest.getOrderId()+":输入的总发货数量小于1");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312009);
        }
        if(allRemainAmount < 1l){
            LogUtil.info(MODULE, "订单"+rConfirmDeliveRequest.getOrderId()+":剩余发货数量小于1");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312010);
        }
        if(allDeliveryAmount > allRemainAmount){
            LogUtil.info(MODULE, "订单"+rConfirmDeliveRequest.getOrderId()+":发货的数量大于剩余发货数量");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312012);
        }
        //主订单是否全部发货
        rConfirmDeliveRequest.setIsSendAll(false);
        if(allDeliveryAmount.longValue() == allRemainAmount.longValue()){
            rConfirmDeliveRequest.setIsSendAll(true);
        }
//        //主订单为全部发货，子订单发货数量与剩余发货数量相等
//        if(rConfirmDeliveRequest.getIsSendAll()){
//            if(allDeliveryAmount != allRemainAmount){
//                LogUtil.info(MODULE, "订单"+rConfirmDeliveRequest.getOrderId()+":输入的是否全部发货标识异常");
//                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312011);
//            }
//        } else { //主订单为部分发货，子订单发货数量与剩余发货数量不相等
//            if(allDeliveryAmount == allRemainAmount){
//                LogUtil.info(MODULE, "订单"+rConfirmDeliveRequest.getOrderId()+":输入的是否全部发货标识异常");
//                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312011);
//            }
//        }
        //库存ID 组装
        //子订单是否全部发货
        for(int i = 0; i < rConfirmDeliveRequest.getrConfirmSubInfo().size(); i++){
            LogUtil.info(MODULE, rConfirmDeliveRequest.getrConfirmSubInfo().get(i).toString());
            SBaseAndSubInfo sba = new SBaseAndSubInfo();
            sba.setOrderId(rConfirmDeliveRequest.getOrderId());
            sba.setOrderSubId(rConfirmDeliveRequest.getrConfirmSubInfo().get(i).getOrderSubId());
            OrdSub os = this.ordSubSV.findByOrderSubId(sba);
            rConfirmDeliveRequest.getrConfirmSubInfo().get(i).setIsSendAll(false);
            if(os != null){
                rConfirmDeliveRequest.getrConfirmSubInfo().get(i).setStockId(os.getStockId());
                if(rConfirmDeliveRequest.getrConfirmSubInfo().get(i).getDeliveryAmount().longValue() == os.getRemainAmount().longValue()){
                    rConfirmDeliveRequest.getrConfirmSubInfo().get(i).setIsSendAll(true);
                }
            }
        }
        /*----校验是否全部发货标识值是否正确--end-----*/
        
        /*----校验发货录入实体编号是否有录入--start-----*/
        
        /*----校验发货录入实体编号是否有录入--end-----*/
    }

}

