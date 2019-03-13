package com.zengshi.ecp.order.facade.impl.eventual;

import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dubbo.dto.*;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackMainReqDTO;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.facade.interfaces.eventual.IBackPayStaffSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackApplyEtSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackApplySV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackDiscountSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdCalculateSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrderBackSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackSubReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffUnionRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffUnionRWRSV;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionStatus;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class BackPayStaffSVImpl implements IBackPayStaffSV {
    
    @Resource
    private IOrdCalculateSV ordCalculateSV;
    
    @Resource
    private IStaffUnionRSV staffUnionRSV;

    @Resource
    private IOrdBackApplyEtSV ordBackApplyEtSV;

    @Resource
    private IOrdSubSV ordSubSV;
    
    @Resource
    private IOrdBackDiscountSV ordBackDiscountSV;

    @Resource
    private IOrdMainSV  ordMainSV;
    
    @Resource
    private IOrdBackApplySV ordBackApplySV;
    
    @Resource
    private IStaffUnionRWRSV staffUnionCoreRWRSV;
    
    @Resource  
    private IOrderBackSV orderBackSV;
    
    private static final String MODULE = BackPayStaffSVImpl.class.getName();

    @Override
    public void joinTransaction(JSONObject message, TransactionStatus status, String transactionName) {
        try {
            final RBackConfirmReq rBackConfirmReq = JSON.parseObject(message.toString(), RBackConfirmReq.class);
            LogUtil.info(MODULE,"BackPayStaffSVImpl============="+rBackConfirmReq.toString());
            dealMethod(rBackConfirmReq);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "支付回调客户域接口处理异常", be);
            be.printStackTrace();
            status.setRollbackOnly();
            throw new BusinessException(be.getErrorCode());
        } catch (Exception e) {
            LogUtil.error(MODULE, "支付回调客户域接口处理异常", e);
            status.setRollbackOnly();
            throw new BusinessException(MsgConstants.OtherSysMsgCode.CALL_STAFF_SERVER_342000);
        }
    }

    @Override
    public void dealMethod(RBackConfirmReq rBackConfirmReq) {
    	
    	LogUtil.error(MODULE, "人卫子事物开始");
        ROrderBackReq rOrderBackReq  = new ROrderBackReq();
        rOrderBackReq.setOrderId(rBackConfirmReq.getOrderId());
        rOrderBackReq.setBackId(rBackConfirmReq.getBackId());
        RBackReviewResp rBackReviewResp = this.ordBackApplySV.queryBackIdInfo(rOrderBackReq);
        
        List<OrderBackSubReqDTO> orderBackSubReqDTOs = new ArrayList<OrderBackSubReqDTO>();
        for(RBackGdsResp rBackGdsResp: rBackReviewResp.getrBackGdsResps()){
            OrderBackSubReqDTO orderBackSubReqDTO = new OrderBackSubReqDTO();
            orderBackSubReqDTO.setOrderId(rBackConfirmReq.getOrderId());
            orderBackSubReqDTO.setStaffId(rBackReviewResp.getrBackApplyResp().getStaffId());
            orderBackSubReqDTO.setSubOrderId(rBackGdsResp.getOrderSubId());
            SBaseAndSubInfo sOrderAOrderSubInfo = new SBaseAndSubInfo();
            sOrderAOrderSubInfo.setOrderSubId(rBackGdsResp.getOrderSubId());
            sOrderAOrderSubInfo.setOrderId(rBackGdsResp.getOrderId());            
            orderBackSubReqDTOs.add(orderBackSubReqDTO);
        }
        
        //积分相关信息
        OrderBackMainReqDTO<OrderBackSubReqDTO> socreOrderBackMainReqDTO = new OrderBackMainReqDTO<OrderBackSubReqDTO>();
        socreOrderBackMainReqDTO.setOrderId(rBackReviewResp.getrBackApplyResp().getOrderId());//订单号
        socreOrderBackMainReqDTO.setBackId(rBackReviewResp.getrBackApplyResp().getBackId());//退款退货编号
        socreOrderBackMainReqDTO.setList(orderBackSubReqDTOs);
        RBackApplyOrdReq rBackApplyOrdReq = new RBackApplyOrdReq();
        rBackApplyOrdReq.setApplyType(rBackReviewResp.getrBackApplyResp().getApplyType());

        OrdMain ordMain =this.ordMainSV.queryOrderByOrderId(socreOrderBackMainReqDTO.getOrderId());
        SOrderDetailsMain  sOrderDetailsMain = new SOrderDetailsMain();
        sOrderDetailsMain.setId(rBackConfirmReq.getOrderId());
        sOrderDetailsMain.setBasicMoney(ordMain.getBasicMoney());
        rBackApplyOrdReq.setSOrderDetailsMain(sOrderDetailsMain);
        List<RBackApplyOrdSubResp> rBackApplyOrdSubRespList = new ArrayList<>();
        for(RBackGdsResp rBackGdsResp: rBackReviewResp.getrBackGdsResps()){
            RBackApplyOrdSubResp rBackApplyOrdSubResp = new RBackApplyOrdSubResp();
            rBackApplyOrdSubResp.setOrderSubId(rBackGdsResp.getOrderSubId());
            rBackApplyOrdSubResp.setNum(rBackGdsResp.getNum());
            rBackApplyOrdSubRespList.add(rBackApplyOrdSubResp);
        }
        rBackApplyOrdReq.setrBackApplyOrdSubResps(rBackApplyOrdSubRespList);
        socreOrderBackMainReqDTO.setScale(orderBackSV.calCulateScaleApply(rBackApplyOrdReq));
        ROrderBackReq orderBackReq = new ROrderBackReq();
        orderBackReq.setBackId(rBackReviewResp.getrBackApplyResp().getBackId());
        orderBackReq.setOrderId(rBackReviewResp.getrBackApplyResp().getOrderId());
        List<RBackDiscountResp> backDiscountResps = ordBackDiscountSV.queryOrdBackDiscount(orderBackReq);
        long backScore = 0l;
        if(backDiscountResps!=null&&backDiscountResps.size()>0){
            for(RBackDiscountResp backDiscountResp:backDiscountResps){
                if(backDiscountResp.getDiscountType().equals("02")){
                    backScore=backScore+backDiscountResp.getAmount();
                }
            }
        }
        socreOrderBackMainReqDTO.setBackScore(backScore);
        socreOrderBackMainReqDTO.setModifyBackSocre(backScore);
        socreOrderBackMainReqDTO.setStaffId(rBackReviewResp.getrBackApplyResp().getStaffId());//买家
        socreOrderBackMainReqDTO.setLastFlag("1".equals(rBackReviewResp.getrBackApplyResp().getIsEntire()));//是否最后一笔      
        socreOrderBackMainReqDTO.setRefundOrBack(rBackReviewResp.getrBackApplyResp().getApplyType());
        
        OrderBackSubReqDTO orderBackSubReqDTO = new OrderBackSubReqDTO();
        orderBackSubReqDTO.setStaffId(rBackReviewResp.getrBackApplyResp().getStaffId());
        orderBackSubReqDTO.setOrderId(rBackReviewResp.getrBackApplyResp().getOrderId());
        orderBackSubReqDTO.setLastFlag(socreOrderBackMainReqDTO.isLastFlag());
        orderBackSubReqDTO.setScale(socreOrderBackMainReqDTO.getScale());
        orderBackSubReqDTO.setRefundOrBack(rBackReviewResp.getrBackApplyResp().getApplyType());
        orderBackSubReqDTO.setBackId(rBackReviewResp.getrBackApplyResp().getBackId());
        staffUnionCoreRWRSV.saveScoreAcctForOrderBackRW(socreOrderBackMainReqDTO,orderBackSubReqDTO); 
        
        LogUtil.error(MODULE, "调用客户域入参："+JSON.toJSONString(socreOrderBackMainReqDTO)+"===="+JSON.toJSONString(orderBackSubReqDTO));
      
    }

}

