package com.zengshi.ecp.order.service.busi.impl.pay;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.order.dao.mapper.common.PayWayMapper;
import com.zengshi.ecp.order.dao.model.GoodPayedReport;
import com.zengshi.ecp.order.dao.model.GoodStaffPayedReport;
import com.zengshi.ecp.order.dao.model.OrdSub;
import com.zengshi.ecp.order.dao.model.PayShopCfg;
import com.zengshi.ecp.order.dao.model.PayWay;
import com.zengshi.ecp.order.dao.model.PayWayCriteria;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelResp;
import com.zengshi.ecp.order.dubbo.dto.pay.PayResultDTO;
import com.zengshi.ecp.order.dubbo.dto.pay.PaySuccInfo;
import com.zengshi.ecp.order.dubbo.dto.pay.PayWayRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.PayWayResponse;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayQuartzInfoRequest;
import com.zengshi.ecp.order.dubbo.util.MsgConstants.PayInputMsgCode;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.PayStatus;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdPayRelSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayQuartzInfoSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayRepeatSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayResultSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayShopCfgSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayWaySV;
import com.zengshi.ecp.order.service.busi.interfaces.report.IGoodPayedSV;
import com.zengshi.ecp.order.service.busi.interfaces.report.IGoodStaffPayedSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 支付通道信息<br>
 * Date:2015年10月8日下午2:59:09 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version
 * @since JDK 1.6
 */
public class PayWaySVImpl extends GeneralSQLSVImpl implements IPayWaySV {

    @Resource
    private PayWayMapper payWayMapper;

    @Resource
    private IPayShopCfgSV payShopCfgSV;

    @Resource
    private IPayResultSV payResultSV;

    @Resource
    private IPayRepeatSV payRepeatSV;

    @Resource
    private IOrdMainSV ordMainSV;
    
    @Resource
    private IPayQuartzInfoSV payQuartzInfoSV;
    
    @Resource
    private IOrdSubSV ordSubSV;
    
    @Resource
    private IGoodPayedSV goodPayedSV;

    @Resource
    private IGoodStaffPayedSV goodStaffPayedSV;

    @Resource
    private IOrdPayRelSV iOrdPayRelSV;
    
    public static final String MODULE = PayWaySVImpl.class.getName();

    @Override
    public List<PayWay> getPayWay(PayWayRequest payWay) {
        PayWayCriteria payWayCriteria = new PayWayCriteria();
        PayWayCriteria.Criteria criteria = payWayCriteria.createCriteria();
        if (StringUtil.isNotBlank(payWay.getId())) {
            criteria.andIdEqualTo(payWay.getId());
        }
        if (StringUtil.isNotBlank(payWay.getPayWayType())) {
            criteria.andPayWayTypeEqualTo(payWay.getPayWayType());
        }
        if (StringUtil.isNotBlank(payWay.getPayAcctType())) {
            criteria.andPayAcctTypeEqualTo(payWay.getPayAcctType());
        }
        if (StringUtil.isNotBlank(payWay.getUseFlag())) {
            criteria.andUseFlagEqualTo(payWay.getUseFlag());
        }
        payWayCriteria.setOrderByClause("show_order asc");
        List<PayWay> payWays = payWayMapper.selectByExample(payWayCriteria);
        return payWays;
    }
    
    @Override
    public PayWay getPayWayById(String payWay) {
        PayWay payWayBean = payWayMapper.selectByPrimaryKey(payWay);
        return payWayBean;
    }

    @Override
    public List<PayWay> getPayWayByShopId(PayWayRequest payWay) {
        PayWayCriteria payWayCriteria = new PayWayCriteria();

        List<PayShopCfg> cfgList = payShopCfgSV.getCfgByShopId(payWay.getShopId());
        List<String> payWayList = new ArrayList<String>();
        if (cfgList != null && !cfgList.isEmpty()) {
            for (PayShopCfg cfg : cfgList) {
                payWayList.add(cfg.getPayWay());
            }
        } else {
            return new ArrayList<PayWay>();
        }

        payWayCriteria.createCriteria().andUseFlagEqualTo(PayStatus.PAY_WAY_OPEN)
                .andIdIn(payWayList);
        List<PayWay> payWays = payWayMapper.selectByExample(payWayCriteria);

        return payWays;
    }

    @Override
    public void addPayWay(PayWayRequest payWay) {
        PayWay bean = new PayWay();
        ObjectCopyUtil.copyObjValue(payWay, bean, null, false);
        bean.setCreateTime(DateUtil.getSysDate());
        payWayMapper.insert(bean);
    }

    @Override
    public void editPayWay(PayWayRequest payWay) {
        PayWayCriteria payWayCriteria = new PayWayCriteria();
        payWayCriteria.createCriteria().andIdEqualTo(payWay.getId());
        PayWay bean = new PayWay();
        ObjectCopyUtil.copyObjValue(payWay, bean, null, false);
        bean.setUpdateTime(DateUtil.getSysDate());
        payWayMapper.updateByExampleSelective(bean, payWayCriteria);
    }

    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.order.service.busi.interfaces.pay.IPayWaySV#savehandlePaySucc(com.zengshi.ecp.order.dubbo.dto.pay.PaySuccInfo)
     */
    @Override
    public void savehandlePaySucc(PaySuccInfo paySuccInfo) {
        if (paySuccInfo == null) {
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300000);
        }
        if (StringUtil.isBlank(paySuccInfo.getOrderId())) {
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300001);
        }
        if (StringUtil.isBlank(paySuccInfo.getPayWay())) {
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300002);
        }

        if (StringUtil.isBlank(paySuccInfo.getPayTransNo())) {
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300003);
        }

        if (StringUtil.isBlank(paySuccInfo.getPayType())) {
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300004);
        }

        payBackSuc(paySuccInfo);
    }

    /**
     * 
     * payBackSuc:处理支付成功逻辑.记录重复支付 <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author weijq
     * @param paySuccInfo
     * @since JDK 1.6
     */
    public void payBackSuc(PaySuccInfo paySuccInfo) {
/*        // 修改订单状态，记录订单日志
        ROrdPayRelReq rOrdPayRelReq = new ROrdPayRelReq();
        String joinOrderid = paySuccInfo.getOrderId();
        rOrdPayRelReq.setJoinOrderid(joinOrderid);
        List<ROrdPayRelResp> resultList = iOrdPayRelSV.queryOrdPayRelByOption(rOrdPayRelReq);
        if(resultList != null && resultList.size() >= 1){
            //更新ordpayrel 表
            rOrdPayRelReq.setRemark("支付成功");
            rOrdPayRelReq.setPayFlag(OrdConstants.Order.ORDER_PAY_FLAG_1);
            rOrdPayRelReq.setPayWay(paySuccInfo.getPayWay());
            Timestamp time = DateUtil.getSysDate();
            rOrdPayRelReq.setUpdateTime(time);
            iOrdPayRelSV.updateOrdPayRel(rOrdPayRelReq);
            for(ROrdPayRelResp resp : resultList){
                //使用拆分的订单来遍历更新订单信息
                paySuccInfo.setOrderId(resp.getOrderId());
                commonPayBack(paySuccInfo);
            }
            //重新赋值合并订单，用于后面积分计算
            paySuccInfo.setOrderId(joinOrderid);
        }else{
            commonPayBack(paySuccInfo);
        }*/
    	commonPayBack(paySuccInfo);

    }

    /**
     *
     * commonPayBack:(支付回调。线上合并支付 跟线下公用一套逻辑). <br/>
     *
     * @author gxq
     * @param paySuccInfo
     * @since JDK 1.6
     */
    public void commonPayBack(PaySuccInfo paySuccInfo){
        ordMainSV.savePaySucc(paySuccInfo);
        // 分业务插入定时任务表
        RPayQuartzInfoRequest payQuartzInfo = new RPayQuartzInfoRequest();
        payQuartzInfo.setOrderId(paySuccInfo.getOrderId());
        payQuartzInfo.setStaffId(paySuccInfo.getStaffId());
        payQuartzInfo.setDealFlag(PayStatus.PAY_DEAL_FLAG_0);
        payQuartzInfo.setCreateStaff(paySuccInfo.getStaffId());
        payQuartzInfoSV.addScoreInfo(payQuartzInfo);// 积分
        payQuartzInfoSV.addCoupInfo(payQuartzInfo);// 优惠券
        List<OrdSub> ordSubList = ordSubSV.queryOrderSubByOrderId(paySuccInfo.getOrderId());
        for(OrdSub ordSub :ordSubList){

            GoodPayedReport goodPayedReport = new GoodPayedReport();
            try{
                goodPayedReport.setGdsId(ordSub.getGdsId());
                goodPayedReport.setSkuId(ordSub.getSkuId());
                goodPayedReport.setBuyNum(ordSub.getOrderAmount());
                goodPayedReport.setCreateStaff(paySuccInfo.getStaffId());
                goodPayedReport.setUpdateStaff(paySuccInfo.getStaffId());
                goodPayedReport.setSiteId(ordSub.getSiteId());
                goodPayedSV.saveGoodPayedReport(goodPayedReport);

            }catch(Exception e){
                LogUtil.error(MODULE, "支付回调goodPayedSV.saveGoodPayedReport接口异常", e);
            }

            try{
                //针对用户的购买行为报表
                GoodStaffPayedReport goodStaffPayedReport = new GoodStaffPayedReport();
                ObjectCopyUtil.copyObjValue(goodPayedReport, goodStaffPayedReport, "", false);
                goodStaffPayedReport.setStaffId(paySuccInfo.getStaffId());
                goodStaffPayedSV.saveGoodStaffPayed(goodStaffPayedReport);
            }catch (Exception m){
                LogUtil.error(MODULE, "goodStaffPayedSV.saveGoodStaffPayed", m);
            }
        }
    }

    /**
     * 
     * TODO 分页查询支付方式（可选）. 
     * @see com.zengshi.ecp.order.service.busi.interfaces.pay.IPayWaySV#queryPayWayPage(com.zengshi.ecp.order.dubbo.dto.pay.PayWayRequest)
     */
    @Override
    public PageResponseDTO<PayWayResponse> queryPayWayPage(PayWayRequest payWayRequest) {
        PayWayCriteria pwc = new PayWayCriteria();
        pwc.setOrderByClause(" show_order asc");
        pwc.setLimitClauseCount(payWayRequest.getPageSize());
        pwc.setLimitClauseStart(payWayRequest.getStartRowIndex());
        return  super.queryByPagination(payWayRequest,pwc,true,new PaginationCallback<PayWay,PayWayResponse>(){

            @Override
            public List queryDB(BaseCriteria arg0) {
                return payWayMapper.selectByExample((PayWayCriteria)arg0);
            }

            @Override
            public long queryTotal(BaseCriteria arg0) {
                return payWayMapper.countByExample((PayWayCriteria)arg0);
            }

            @Override
            public PayWayResponse warpReturnObject(PayWay arg0) {
                PayWayResponse payWayResponse = new PayWayResponse();
                BeanUtils.copyProperties(arg0, payWayResponse);
                return payWayResponse;
            }
            
        });
    }
}
