package com.zengshi.ecp.order.facade.impl.eventual;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV;
import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dao.model.OrdProm;
import com.zengshi.ecp.order.dao.model.OrdSub;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndSubInfo;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.facade.interfaces.eventual.IOrdRemoveGoodsSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdPromSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionStatus;

public class OrdRemoveGoodsSVImpl implements IOrdRemoveGoodsSV {
    
    @Resource
    private IOrdMainSV ordMainSV;
    
    @Resource
    private IOrdSubSV ordSubSV;
    
    @Resource
    private IOrdPromSV  ordPromSV;
    
    @Resource
    private IGdsInfoExternalRSV gdsInfoExternalRSV;

    private static final String MODULE = OrdRemoveGoodsSVImpl.class.getName();

    @Override
    public void joinTransaction(JSONObject message, TransactionStatus status,String transactionName) {
        try {

            final ROrderDetailsRequest rOrderDetailsRequest = JSON.parseObject(message.toString(),ROrderDetailsRequest.class);
            LogUtil.info(MODULE, "取消订单入参OrdRemoveGoodsSVImpl=============" + message.toString());

            dealMethod(rOrderDetailsRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "取消订单商品域接口处理业务异常", be);
            status.setRollbackOnly();
            throw new BusinessException(be.getErrorCode());
        } catch (Exception e) {
            LogUtil.error(MODULE, "取消订单商品域接口处理系统异常", e);
            status.setRollbackOnly();
            throw new BusinessException(MsgConstants.OtherSysMsgCode.CALL_GOODS_SERVER_340000);
        }
    }

    @Override
    public void dealMethod(ROrderDetailsRequest rOrderDetailsRequest) {
        
        //0 进行接口入参的封装
        ROrdCartsCommRequest rOrdCartsCommRequest = new ROrdCartsCommRequest();
        List<ROrdCartCommRequest> rOrdCartCommRequests = new ArrayList<ROrdCartCommRequest>();
        
        OrdMain ordMain = this.ordMainSV.queryOrderByOrderId(rOrderDetailsRequest.getOrderId());
        if(ordMain == null){
            LogUtil.info(MODULE, "未找到[" + rOrderDetailsRequest.getOrderId() + "]该订单信息");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312001);
        }
        ROrdCartCommRequest  rOrdCartCommRequest = new ROrdCartCommRequest();
        //补齐订单信息
        ObjectCopyUtil.copyObjValue(ordMain, rOrdCartCommRequest, null,false);
        rOrdCartCommRequest.setOrderId(ordMain.getId());

        //补齐订单促销
        SBaseAndSubInfo sBaseAndSubInfo = new SBaseAndSubInfo();
        sBaseAndSubInfo.setOrderId(rOrderDetailsRequest.getOrderId());
        OrdProm op = this.ordPromSV.queryOrdProm(sBaseAndSubInfo);
        if(op != null){
            rOrdCartCommRequest.setPromId(op.getPromId());
        }
        List<OrdSub> ordSubs = this.ordSubSV.queryOrderSubByOrderId(rOrderDetailsRequest.getOrderId());
        
        if (CollectionUtils.isEmpty(ordSubs)) {
            LogUtil.info(MODULE, "未找到[" + rOrderDetailsRequest.getOrderId() + "]该订单的子订单信息");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312002);
        }
        //补齐明细信息
        List<ROrdCartItemCommRequest> rOrdCartItemCommRequests = new ArrayList<ROrdCartItemCommRequest>();
        for(OrdSub ordSub: ordSubs){
            ROrdCartItemCommRequest rOrdCartItemCommRequest = new ROrdCartItemCommRequest();
            ObjectCopyUtil.copyObjValue(ordSub, rOrdCartItemCommRequest, null, false);
            rOrdCartItemCommRequest.setOrderSubId(ordSub.getId());
            
            //补齐promId
            SBaseAndSubInfo sBaseAndSubInfo1 = new SBaseAndSubInfo();
            sBaseAndSubInfo1.setOrderId(rOrderDetailsRequest.getOrderId());
            sBaseAndSubInfo1.setOrderSubId(rOrdCartItemCommRequest.getOrderSubId());
            OrdProm opsub = this.ordPromSV.queryOrdProm(sBaseAndSubInfo1);
            if(opsub != null){
                rOrdCartItemCommRequest.setPromId(opsub.getPromId());
                rOrdCartItemCommRequest.setOrdPromId(opsub.getRelaMainPromId());
            }
            
            rOrdCartItemCommRequests.add(rOrdCartItemCommRequest);
        }
        rOrdCartCommRequest.setOrdCartItemCommList(rOrdCartItemCommRequests);
        rOrdCartCommRequests.add(rOrdCartCommRequest);
        rOrdCartsCommRequest.setOrdCartsCommList(rOrdCartCommRequests);
        rOrdCartsCommRequest.setStaffId(ordMain.getStaffId());
        //库存补尝
        this.gdsInfoExternalRSV.batchCancleStockPreOccupy(rOrdCartsCommRequest);
    }

}
