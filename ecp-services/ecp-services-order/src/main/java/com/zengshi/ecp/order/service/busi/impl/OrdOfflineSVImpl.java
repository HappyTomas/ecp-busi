package com.zengshi.ecp.order.service.busi.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.order.dao.mapper.busi.OrdOfflineChkMapper;
import com.zengshi.ecp.order.dao.mapper.busi.OrdOfflineMapper;
import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dao.model.OrdOffline;
import com.zengshi.ecp.order.dao.model.OrdOfflineChk;
import com.zengshi.ecp.order.dao.model.OrdOfflineChkCriteria;
import com.zengshi.ecp.order.dao.model.OrdOfflineCriteria;
import com.zengshi.ecp.order.dao.model.OrdTrack;
import com.zengshi.ecp.order.dubbo.dto.ROfflineApplyRequest;
import com.zengshi.ecp.order.dubbo.dto.ROfflineQueryRequest;
import com.zengshi.ecp.order.dubbo.dto.ROfflineQueryResponse;
import com.zengshi.ecp.order.dubbo.dto.ROfflineReviewRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.SOfflineCondition;
import com.zengshi.ecp.order.dubbo.dto.SOfflinePic;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.dubbo.util.OfflineConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdOfflinePicSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdOfflineSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdTrackSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffUnionRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.sequence.Sequence;

public class OrdOfflineSVImpl extends GeneralSQLSVImpl implements IOrdOfflineSV {

    @Resource
    private IOrdOfflinePicSV ordOfflinePicSV;

    @Resource
    private IOrdTrackSV ordTrackSV;
    
    @Resource
    private IOrdMainSV ordMainSV;
    
    @Resource
    private OrdOfflineMapper ordOfflineMapper;

    @Resource
    private OrdOfflineChkMapper ordOfflineChkMapper;
    
    @Resource
    private IStaffUnionRSV staffUnionRSV;

    @Resource(name = "seq_ord_offline")
    private Sequence seqOrdOffline;

    private static final String MODULE = OrdOfflineSVImpl.class.getName();

    @Override
    public void saveOrdOffline(OrdOffline ordOffline) {
        ordOffline.setId(this.seqOrdOffline.nextValue());
        this.ordOfflineMapper.insert(ordOffline);

    }

    /**
     * delOrderTrack:生成订单跟踪表信息. <br/>
     * 
     * @author cbl
     * @since JDK 1.6
     */
    private void dealOrderTrack(ROfflineApplyRequest rOfflineApplyRequest) {
        OrdTrack ot = new OrdTrack();
        ot.setNode(OrdConstants.OrderTwoStatus.STATUS_PAID_OFFLINE_APPLY);
        ot.setNodeDesc(OrdConstants.NodeDesc.STATUS_PAID_OFFLINE_APPLY);
        ot.setOrderId(rOfflineApplyRequest.getOrderId());
        ot.setCreateStaff(rOfflineApplyRequest.getStaff().getId());
        ot.setCreateTime(DateUtil.getSysDate());
        this.ordTrackSV.saveOrdTrack(ot);
    }

    @Override
    public void saveOfflineApply(ROfflineApplyRequest rOfflineApplyRequest) {
        // 保存申请信息
        OrdOffline oo = new OrdOffline();
        ObjectCopyUtil.copyObjValue(rOfflineApplyRequest, oo, null, false);
        oo.setCreateStaff(rOfflineApplyRequest.getStaff().getId());
        oo.setCreateTime(DateUtil.getSysDate());
        oo.setStatus(OfflineConstants.Status.STATUS_SHOP_WAIT);
        oo.setUpdateStaff(rOfflineApplyRequest.getStaff().getId());
        oo.setUpdateTime(oo.getCreateTime());
        oo.setId(this.seqOrdOffline.nextValue());
        oo.setIsValid(OrdConstants.Common.COMMON_VALID);
        saveOrdOffline(oo);
        if(CollectionUtils.isNotEmpty(rOfflineApplyRequest.getAnnex())){
            this.ordOfflinePicSV.saveOfflineApply(rOfflineApplyRequest, oo.getId());
        }
        // 保存跟踪表信息
        dealOrderTrack(rOfflineApplyRequest);
    }

    /**
     * queryByOrderId:根据订单号查询线下支付待审核信息. <br/>
     * 
     * @author cbl
     * @param orderId
     * @return
     * @since JDK 1.6
     */
    private OrdOffline queryByOrderId(String orderId) {
        OrdOfflineCriteria oocc = new OrdOfflineCriteria();
        oocc.createCriteria().andOrderIdEqualTo(orderId);
        List<OrdOffline> oos = this.ordOfflineMapper.selectByExample(oocc);
        if (CollectionUtils.isEmpty(oos))
            return null;
        else
            return oos.get(0);
    }

    @Override
    public ROfflineQueryResponse queryOfflineReview(ROfflineQueryRequest rOfflineQueryRequest) {
        OrdOffline oo = queryByOrderId(rOfflineQueryRequest.getOrderId());
        if (oo == null) {
            LogUtil.info(MODULE, "根据" + rOfflineQueryRequest.getOrderId() + "订单号查询待审核数据为空");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312004);
        }
        ROfflineQueryResponse roqr = new ROfflineQueryResponse();
        ObjectCopyUtil.copyObjValue(oo, roqr, null, false);
        roqr.setOfflineNo(oo.getId());
        SOfflineCondition soc = new SOfflineCondition();
        soc.setOfflineNo(oo.getId());
        soc.setOrderId(oo.getOrderId());
        List<SOfflinePic> sops = this.ordOfflinePicSV.queryByOfflineNo(soc);
        roqr.setAnnex(sops);
        return roqr;
    }

    
    /** 
     * queryOffline:线下支付和上门支付待审核订单. <br/> 
     * @author cbl 
     * @param rOfflineQueryRequest
     * @return 
     * @since JDK 1.6 
     */ 
    private  PageResponseDTO<ROfflineQueryResponse> queryOffline(ROfflineQueryRequest rOfflineQueryRequest){
        OrdOfflineCriteria oocc = new OrdOfflineCriteria();
        oocc.setLimitClauseCount(rOfflineQueryRequest.getPageSize());
        oocc.setLimitClauseStart(rOfflineQueryRequest.getStartRowIndex());
        oocc.setOrderByClause("id desc");
        OrdOfflineCriteria.Criteria ca = oocc.createCriteria();
        ca.andShopIdEqualTo(rOfflineQueryRequest.getShopId())
                .andStatusEqualTo(OfflineConstants.Status.STATUS_SHOP_WAIT);
        if(StringUtil.isNotBlank(rOfflineQueryRequest.getApplyType())){
            ca.andApplyTypeEqualTo(rOfflineQueryRequest.getApplyType());
        }

        return super.queryByPagination(rOfflineQueryRequest, oocc, true,
                new PaginationCallback<OrdOffline, ROfflineQueryResponse>() {

                    @Override
                    public List<OrdOffline> queryDB(BaseCriteria bCriteria) {
                        return ordOfflineMapper.selectByExample((OrdOfflineCriteria) bCriteria);
                    }

                    @Override
                    public long queryTotal(BaseCriteria bCriteria) {
                        return ordOfflineMapper.countByExample((OrdOfflineCriteria) bCriteria);
                    }

                    @Override
                    public List<Comparator<OrdOffline>> defineComparators() {
                        List<Comparator<OrdOffline>> ls = new ArrayList<Comparator<OrdOffline>>();
                        ls.add(new Comparator<OrdOffline>(){

                            @Override
                            public int compare(OrdOffline o1, OrdOffline o2) {
                                return o2.getId().compareTo(o1.getId());
                            }
                            
                        });
                        return ls;
                    }

                    @Override
                    public ROfflineQueryResponse warpReturnObject(OrdOffline ordOffline) {
                        ROfflineQueryResponse sdoi = new ROfflineQueryResponse();
                        BeanUtils.copyProperties(ordOffline, sdoi);
                        sdoi.setOfflineNo(ordOffline.getId());
                        return sdoi;
                    }
                });
    }

    /**
     * queryChecked:线下支付和上门支付已审核订单. <br/>
     * @author cbl
     * @param rOfflineQueryRequest
     * @return
     * @since JDK 1.6
     */
    private  PageResponseDTO<ROfflineQueryResponse> queryChecked(ROfflineQueryRequest rOfflineQueryRequest){
        OrdOfflineCriteria oocc = new OrdOfflineCriteria();
        oocc.setLimitClauseCount(rOfflineQueryRequest.getPageSize());
        oocc.setLimitClauseStart(rOfflineQueryRequest.getStartRowIndex());
        oocc.setOrderByClause("id desc");

        OrdOfflineCriteria.Criteria ca = oocc.createCriteria();
        ca.andShopIdEqualTo(rOfflineQueryRequest.getShopId());

        if(StringUtil.isNotBlank(rOfflineQueryRequest.getStatus())){
            ca.andStatusEqualTo(rOfflineQueryRequest.getStatus());
        }else {
            List<String> status = new ArrayList<>();
            status.add(OfflineConstants.Status.STATUS_SHOP_PASS);
            status.add(OfflineConstants.Status.STATUS_SHOP_NOT);
            ca.andStatusIn(status);
        }

        if(StringUtil.isNotBlank(rOfflineQueryRequest.getApplyType())){
            ca.andApplyTypeEqualTo(rOfflineQueryRequest.getApplyType());
        }
        return super.queryByPagination(rOfflineQueryRequest, oocc, true,
                new PaginationCallback<OrdOffline, ROfflineQueryResponse>() {

                    @Override
                    public List<OrdOffline> queryDB(BaseCriteria bCriteria) {
                        return ordOfflineMapper.selectByExample((OrdOfflineCriteria) bCriteria);
                    }

                    @Override
                    public long queryTotal(BaseCriteria bCriteria) {
                        return ordOfflineMapper.countByExample((OrdOfflineCriteria) bCriteria);
                    }

                    @Override
                    public List<Comparator<OrdOffline>> defineComparators() {
                        List<Comparator<OrdOffline>> ls = new ArrayList<Comparator<OrdOffline>>();
                        ls.add(new Comparator<OrdOffline>(){

                            @Override
                            public int compare(OrdOffline o1, OrdOffline o2) {
                                return o2.getId().compareTo(o1.getId());
                            }

                        });
                        return ls;
                    }

                    @Override
                    public ROfflineQueryResponse warpReturnObject(OrdOffline ordOffline) {
                        ROfflineQueryResponse sdoi = new ROfflineQueryResponse();
                        BeanUtils.copyProperties(ordOffline, sdoi);
                        sdoi.setOfflineNo(ordOffline.getId());
                        return sdoi;
                    }
                });
    }

    @Override
    public PageResponseDTO<ROfflineQueryResponse> queryOfflineOrder(
            ROfflineQueryRequest rOfflineQueryRequest) {
        PageResponseDTO<ROfflineQueryResponse> roq = queryOffline(rOfflineQueryRequest);
        if(CollectionUtils.isNotEmpty(roq.getResult())) {
            for(int i = 0; i < roq.getResult().size(); i++){
                OrdMain om = this.ordMainSV.queryOrderByOrderId(roq.getResult().get(i).getOrderId());
                if(om!= null){
                    roq.getResult().get(i).setOrderTime(om.getOrderTime());
                    roq.getResult().get(i).setOrderType(om.getOrderType());
                    roq.getResult().get(i).setRealMoney(om.getRealMoney());
                    roq.getResult().get(i).setSiteId(om.getSiteId());
//                    CustInfoResDTO custInfoResDTO = this.custManageRSV.findCustInfoById(roq.getResult().get(i).getStaffId());
//                    if(custInfoResDTO != null){
//                        roq.getResult().get(i).setStaffName(custInfoResDTO.getCustName());
//                    } else {
//                        roq.getResult().get(i).setStaffName(roq.getResult().get(i).getStaffId().toString());
//                    }
                    CustInfoResDTO custInfoResDTO = this.staffUnionRSV.findCustOrAdminByStaffId(roq.getResult().get(i).getStaffId());
                    if(custInfoResDTO != null && custInfoResDTO.getStaffCode() != null){
                        roq.getResult().get(i).setStaffName(custInfoResDTO.getStaffCode());
                    } else {
                        roq.getResult().get(i).setStaffName(roq.getResult().get(i).getStaffId().toString());
                    }
                }
            }
        }
        return roq;
    }

    /**
     * queryOrdOfflineChk <br/>
     *
     * @author wxq
     * @param orderId,offlineNo
     * @return
     * @since JDK 1.6
     */
    private OrdOfflineChk queryOrdOfflineChk(String orderId, Long offlineNo){
        OrdOfflineChkCriteria criteria = new OrdOfflineChkCriteria();
        criteria.createCriteria().andOrderIdEqualTo(orderId)
        .andOfflineNoEqualTo(offlineNo);
        List<OrdOfflineChk> list = ordOfflineChkMapper.selectByExample(criteria);
        if(list!=null){
            return list.get(0);
        }
        return null;
    }

    @Override
    public PageResponseDTO<ROfflineQueryResponse> queryCheckedOrder(
            ROfflineQueryRequest rOfflineQueryRequest) {
        PageResponseDTO<ROfflineQueryResponse> roq = queryChecked(rOfflineQueryRequest);
        if(CollectionUtils.isNotEmpty(roq.getResult())) {
            for(int i = 0; i < roq.getResult().size(); i++){
                OrdMain om = this.ordMainSV.queryOrderByOrderId(roq.getResult().get(i).getOrderId());
                if(om!= null){
                    roq.getResult().get(i).setOrderTime(om.getOrderTime());
                    roq.getResult().get(i).setOrderType(om.getOrderType());
                    roq.getResult().get(i).setRealMoney(om.getRealMoney());
                    roq.getResult().get(i).setSiteId(om.getSiteId());
//                    CustInfoResDTO custInfoResDTO = this.custManageRSV.findCustInfoById(roq.getResult().get(i).getStaffId());
//                    if(custInfoResDTO != null){
//                        roq.getResult().get(i).setStaffName(custInfoResDTO.getCustName());
//                    } else {
//                        roq.getResult().get(i).setStaffName(roq.getResult().get(i).getStaffId().toString());
//                    }
                    CustInfoResDTO custInfoResDTO = this.staffUnionRSV.findCustOrAdminByStaffId(roq.getResult().get(i).getStaffId());
                    if(custInfoResDTO != null && custInfoResDTO.getStaffCode() != null){
                        roq.getResult().get(i).setStaffName(custInfoResDTO.getStaffCode());
                    } else {
                        roq.getResult().get(i).setStaffName(roq.getResult().get(i).getStaffId().toString());
                    }

                    OrdOfflineChk offlineChk = queryOrdOfflineChk(roq.getResult().get(i).getOrderId(), roq.getResult().get(i).getOfflineNo());
                    if(offlineChk!=null){
                        roq.getResult().get(i).setCheckCont(offlineChk.getCheckCont());

                        CustInfoResDTO admin = this.staffUnionRSV.findCustOrAdminByStaffId(offlineChk.getCreateStaff());
                        if(admin != null && admin.getStaffCode() != null){
                            roq.getResult().get(i).setAdminName(admin.getStaffCode());
                        } else {
                            roq.getResult().get(i).setAdminName(roq.getResult().get(i).getUpdateStaff().toString());
                        }
                    }


                }
            }
        }
        return roq;
    }

    @Override
    /**
     * @author wxq
     * 添加审核内容保存
     */
    public void updateOrdOfflineStatus(ROfflineReviewRequest rOfflineReviewRequest) {
        
        final OrdOfflineCriteria ooc = new OrdOfflineCriteria();
        ooc.createCriteria().andIdEqualTo(rOfflineReviewRequest.getOfflineNo())
                            .andOrderIdEqualTo(rOfflineReviewRequest.getOrderId());
        OrdOffline oo = new OrdOffline();
        oo.setStatus(rOfflineReviewRequest.getStatus());
        oo.setRemark(rOfflineReviewRequest.getCheckCont());
        oo.setUpdateStaff(rOfflineReviewRequest.getStaff().getId());
        oo.setUpdateTime(DateUtil.getSysDate());
        this.ordOfflineMapper.updateByExampleSelective(oo, ooc);
    }

    @Override
    public void deleteOrdOffline(ROrderDetailsRequest rOrderDetailsRequest) {
        final OrdOfflineCriteria ooc = new OrdOfflineCriteria();
        ooc.createCriteria().andOrderIdEqualTo(rOrderDetailsRequest.getOrderId());
        OrdOffline oo = new OrdOffline();
        oo.setStatus(OfflineConstants.Status.STATUS_SHOP_DEL);
        oo.setUpdateStaff(rOrderDetailsRequest.getStaff().getId());
        oo.setUpdateTime(DateUtil.getSysDate());
        this.ordOfflineMapper.updateByExampleSelective(oo, ooc);
    }

}
