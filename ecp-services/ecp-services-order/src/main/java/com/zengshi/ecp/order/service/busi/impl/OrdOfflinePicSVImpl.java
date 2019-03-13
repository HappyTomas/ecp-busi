package com.zengshi.ecp.order.service.busi.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.busi.OrdOfflinePicMapper;
import com.zengshi.ecp.order.dao.model.OrdOfflinePic;
import com.zengshi.ecp.order.dao.model.OrdOfflinePicCriteria;
import com.zengshi.ecp.order.dubbo.dto.ROfflineApplyRequest;
import com.zengshi.ecp.order.dubbo.dto.SOfflineCondition;
import com.zengshi.ecp.order.dubbo.dto.SOfflinePic;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdOfflinePicSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.sequence.Sequence;

public class OrdOfflinePicSVImpl implements IOrdOfflinePicSV {
    
    @Resource
    private OrdOfflinePicMapper ordOfflinePicMapper;
    
    @Resource(name="seq_ord_offline_pic")
    private Sequence seqOrdOfflinePic;
    
    private static final String MODULE = OrdOfflinePicSVImpl.class.getName();

    @Override
    public void saveOfflineApply(ROfflineApplyRequest rOfflineApplyRequest,Long offlineNo) {
        for(SOfflinePic sop :rOfflineApplyRequest.getAnnex()){
            OrdOfflinePic oop = new OrdOfflinePic();
            ObjectCopyUtil.copyObjValue(sop, oop, null, false);
            ObjectCopyUtil.copyObjValue(rOfflineApplyRequest, oop, null, false);
            oop.setOfflineNo(offlineNo);
            oop.setId(this.seqOrdOfflinePic.nextValue());
            LogUtil.info(MODULE, oop.toString());
            oop.setIsValid(OrdConstants.Common.COMMON_VALID);
            oop.setCreateStaff(rOfflineApplyRequest.getStaff().getId());
            oop.setCreateTime(DateUtil.getSysDate());
            this.ordOfflinePicMapper.insert(oop);
        }
    }

    @Override
    public List<SOfflinePic> queryByOfflineNo(SOfflineCondition sOfflineCondition) {
        
        OrdOfflinePicCriteria oc = new OrdOfflinePicCriteria();
        oc.createCriteria().andOfflineNoEqualTo(sOfflineCondition.getOfflineNo())
                           .andOrderIdEqualTo(sOfflineCondition.getOrderId());
        List<OrdOfflinePic> oop = this.ordOfflinePicMapper.selectByExample(oc);
        List<SOfflinePic> sops = null;
        if(CollectionUtils.isEmpty(oop)){
//                LogUtil.info(MODULE, "根据" + sOfflineCondition.getOfflineNo() + "线下支付流水号查询凭证数据为空");
//                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312005);
        } else {
            sops = new ArrayList<SOfflinePic>();
            for(OrdOfflinePic oo:oop){
                SOfflinePic sop = new SOfflinePic();
                ObjectCopyUtil.copyObjValue(oo, sop, null, false);
                LogUtil.info(MODULE,sop.toString());
                sops.add(sop);
            }
        }
        
        return sops;
    }

}

