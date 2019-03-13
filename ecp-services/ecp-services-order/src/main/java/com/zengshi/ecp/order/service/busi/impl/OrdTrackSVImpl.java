package com.zengshi.ecp.order.service.busi.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.busi.OrdTrackMapper;
import com.zengshi.ecp.order.dao.model.OrdTrack;
import com.zengshi.ecp.order.dao.model.OrdTrackCriteria;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsTrack;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdTrackSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffUnionRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.sequence.Sequence;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月17日下午4:27:03 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version
 * @since JDK 1.6
 */
public class OrdTrackSVImpl implements IOrdTrackSV {

    @Resource
    private OrdTrackMapper ordTrackMapper;
    
    @Resource
    private IStaffUnionRSV staffUnionRSV;

    @Resource(name = "seq_ord_track")
    private Sequence seqOrdTrack;
    
    private static final String MODULE = OrdTrackSVImpl.class.getName();
    @Override
    public void saveOrdTrack(OrdTrack ordTrack) {
        ordTrack.setId(this.seqOrdTrack.nextValue());
        this.ordTrackMapper.insert(ordTrack);
    }

    @Override
    public List<SOrderDetailsTrack> queryOrderDetailsTrack(String orderId) {
        OrdTrackCriteria otc = new OrdTrackCriteria();
        otc.createCriteria().andOrderIdEqualTo(orderId);
        otc.setOrderByClause("create_time , node asc");
        List<OrdTrack> ots = this.ordTrackMapper.selectByExample(otc);
        if(CollectionUtils.isEmpty(ots)){
            LogUtil.info(MODULE, "未找到["+orderId+"]该订单的跟踪记录信息");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312003);
        }
        List<SOrderDetailsTrack> sods = new ArrayList<SOrderDetailsTrack>();
        for(OrdTrack ot:ots){
            SOrderDetailsTrack sod = new SOrderDetailsTrack();
            ObjectCopyUtil.copyObjValue(ot, sod, null, false);
            sod.setCreateStaffName("");
            if(sod.getCreateStaff() > 0l){
                CustInfoResDTO custInfoResDTO = this.staffUnionRSV.findCustOrAdminByStaffId(sod.getCreateStaff());
                if(custInfoResDTO != null && custInfoResDTO.getStaffCode() != null){
                    sod.setCreateStaffName(custInfoResDTO.getStaffCode());
                }
            }
            
            LogUtil.info(MODULE, sod.toString());
            sods.add(sod);
        }
        return sods;
    }

}
