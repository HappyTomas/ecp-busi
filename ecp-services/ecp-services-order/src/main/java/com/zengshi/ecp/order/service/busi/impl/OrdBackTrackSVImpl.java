package com.zengshi.ecp.order.service.busi.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.busi.OrdBackTrackMapper;
import com.zengshi.ecp.order.dao.model.OrdBackTrack;
import com.zengshi.ecp.order.dao.model.OrdBackTrackCriteria;
import com.zengshi.ecp.order.dubbo.dto.RBackTrackResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackTrackSV;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffUnionRSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.sequence.Sequence;

public class OrdBackTrackSVImpl implements IOrdBackTrackSV {
    
    @Resource
    private IStaffUnionRSV staffUnionRSV;
    
    @Resource
    private OrdBackTrackMapper ordBackTrackMapper;
    
    @Resource(name = "seq_ord_back_track")
    private Sequence seqOrdBackTrack;

    private static final String MODULE = OrdBackTrackSVImpl.class.getName();

    @Override
    public void saveOrdBackTrack(OrdBackTrack ordBackTrack) {
        ordBackTrack.setId(this.seqOrdBackTrack.nextValue());
        this.ordBackTrackMapper.insert(ordBackTrack);
    }

    @Override
    public List<RBackTrackResp> queryOrdBackTrack(ROrderBackReq rOrderBackReq) {
        OrdBackTrackCriteria obtc = new OrdBackTrackCriteria();
        obtc.createCriteria().andBackIdEqualTo(rOrderBackReq.getBackId()).andOrderIdEqualTo(rOrderBackReq.getOrderId());
        obtc.setOrderByClause("create_time asc");
        List<OrdBackTrack> obts = this.ordBackTrackMapper.selectByExample(obtc);
        if(CollectionUtils.isEmpty(obts)){
            return null;
        }
        List<RBackTrackResp> rbts = new ArrayList<RBackTrackResp>();
        for(OrdBackTrack obt:obts){
            RBackTrackResp rbt = new RBackTrackResp();
            ObjectCopyUtil.copyObjValue(obt, rbt, null, false);
            rbt.setStaffName("");
            if(rbt.getCreateStaff() > 0l){
                CustInfoResDTO custInfoResDTO = this.staffUnionRSV.findCustOrAdminByStaffId(rbt.getCreateStaff());
                if(custInfoResDTO != null && custInfoResDTO.getStaffCode() != null){
                    rbt.setStaffName(custInfoResDTO.getStaffCode());
                }
            }
            rbts.add(rbt);
        }
        return rbts;
    }

}

