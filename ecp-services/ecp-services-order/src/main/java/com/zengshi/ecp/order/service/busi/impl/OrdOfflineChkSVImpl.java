package com.zengshi.ecp.order.service.busi.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.busi.OrdOfflineChkMapper;
import com.zengshi.ecp.order.dao.model.OrdOfflineChk;
import com.zengshi.ecp.order.dubbo.dto.ROfflineReviewRequest;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdOfflineChkSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdOfflineSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdTrackSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.fastjson.JSON;
import com.db.sequence.Sequence;

public class OrdOfflineChkSVImpl implements IOrdOfflineChkSV {
    
    @Resource
    private IOrdMainSV ordMainSV;
    
    @Resource
    private IOrdSubSV ordSubSV;
    
    @Resource
    private IOrdTrackSV ordTrackSV;
    
    @Resource
    private IOrdOfflineSV ordOfflineSV;
    
    @Resource
    private OrdOfflineChkMapper ordOfflineChkMapper;
    
    @Resource(name="seq_ord_offline_chk")
    private Sequence  seqOrdOfflineChk;
    
    private static final String MODULE = OrdOfflineChkSVImpl.class.getName();
    
    @Override
    public void saveOrdOfflineChk(OrdOfflineChk ordOfflineChk) {
        ordOfflineChk.setId(this.seqOrdOfflineChk.nextValue());
        this.ordOfflineChkMapper.insert(ordOfflineChk);
    }

    @Override
    public void saveOfflineReview(ROfflineReviewRequest rOfflineReviewRequest) {
        //保存审核信息
        OrdOfflineChk ooc = new OrdOfflineChk();
        ObjectCopyUtil.copyObjValue(rOfflineReviewRequest, ooc, null, false);
        ooc.setCreateStaff(rOfflineReviewRequest.getStaff().getId());
        ooc.setCreateTime(DateUtil.getSysDate());
        ooc.setUpdateStaff(ooc.getCreateStaff());
        ooc.setUpdateTime(ooc.getCreateTime());
        LogUtil.info(MODULE, ooc.toString());
        saveOrdOfflineChk(ooc);
        LogUtil.info(MODULE, "1111"+JSON.toJSONString(ooc).toString());
        //修改线下支付申请记录的状态
        this.ordOfflineSV.updateOrdOfflineStatus(rOfflineReviewRequest);
        /*
        //修改订单状态
        SBaseAndStatusInfo sosi = new SBaseAndStatusInfo();
        sosi.setOrderId(rOfflineReviewRequest.getOrderId());
        sosi.setShopId(rOfflineReviewRequest.getShopId());
        sosi.setStaffId(rOfflineReviewRequest.getStaffId());
        //判断审核状态
        if(OfflineConstants.Status.STATUS_SHOP_PASS.equals(rOfflineReviewRequest.getStatus())) {
            sosi.setStatus(OrdConstants.OrderStatus.ORDER_STATUS_PAID);  //审核通过--已支付
            sosi.setOrderTwoStatus(OrdConstants.OrderTwoStatus.STATUS_PAID_OFFLINE_VERIFY_PASS);
        } else {
            sosi.setStatus(OrdConstants.OrderStatus.ORDER_STATUS_CANCLE);  //审核不通过--已取消
            sosi.setOrderTwoStatus(OrdConstants.OrderTwoStatus.STATUS_PAID_OFFLINE_VERIFY_NOT);
        }
            
        //更新主订单状态,更新T_ORD_SUB-订单子表
        this.ordMainSV.updateOrderStatus(sosi);
        
        
        //增加跟踪表信息
        dealOrderTrack(rOfflineReviewRequest);*/
    }

    /**
     * delOrderTrack:生成订单跟踪表信息. <br/>
     * 
     * @author cbl
     * @since JDK 1.6
     */
    /*private void dealOrderTrack(ROfflineReviewRequest rOfflineReviewRequest) {
        OrdTrack ot = new OrdTrack();
        //判断审核状态
        if(OfflineConstants.Status.STATUS_SHOP_PASS.equals(rOfflineReviewRequest.getStatus())){
            ot.setNode(OrdConstants.OrderTwoStatus.STATUS_PAID_OFFLINE_VERIFY_PASS);
            ot.setNodeDesc(OrdConstants.NodeDesc.STATUS_PAID_OFFLINE_VERIFY_PASS);
        } else {
            ot.setNode(OrdConstants.OrderTwoStatus.STATUS_PAID_OFFLINE_VERIFY_NOT);
            ot.setNodeDesc(OrdConstants.OrderTwoStatus.STATUS_PAID_OFFLINE_VERIFY_NOT);
        }
        
        ot.setOrderId(rOfflineReviewRequest.getOrderId());
        ot.setCreateStaff(rOfflineReviewRequest.getStaffId());
        ot.setCreateTime(new Timestamp(System.currentTimeMillis()));
        this.ordTrackSV.saveOrdTrack(ot);
    }*/

}

