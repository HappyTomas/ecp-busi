package com.zengshi.ecp.order.service.busi.impl;

import com.zengshi.ecp.order.dao.mapper.busi.OrdPayRelMapper;
import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dao.model.OrdPayRel;
import com.zengshi.ecp.order.dao.model.OrdPayRelCriteria;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelResp;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.Common;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdPayRelSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdPayRelSVImpl implements IOrdPayRelSV{
    @Resource
    private OrdPayRelMapper ordPayRelMapper;
    @Resource
    private IOrdMainSV iOrdMainSV;
    
    @Resource(name = "seq_ord_pay_rel")
    private Sequence seqOrdPayRel;

    private static final String MODULE = OrdMainSVImpl.class.getName();
    
    
    @Override
    public ROrdPayRelResp saveOrdPayRel(ROrdPayRelReq rOrdPayRelReq) throws BusinessException {
        ROrdPayRelResp rOrdPayRelResp = new ROrdPayRelResp();
        String joinOrderId = "";
        long mergeTotalRealMoney = 0;
        //根据合并的子订单获取订单的对应信息
        if(StringUtil.isNotEmpty(rOrdPayRelReq.getOrderIdList())){
            OrdPayRel ordPayRel = null;
            Timestamp createTime = new Timestamp(System.currentTimeMillis());
            //如果是多个订单合并支付，则合并订单要生成新的。如果是一个订单合并支付。则合并支付的订单也是单个订单的orderId
//            if(rOrdPayRelReq.getOrderIdList().size() >= 2){
                joinOrderId = iOrdMainSV.createOrdMainId();
//            }else{
//                joinOrderId = rOrdPayRelReq.getOrderIdList().get(0);
//            }
            for(String orderId : rOrdPayRelReq.getOrderIdList()){
                OrdMain orderRsp = iOrdMainSV.queryOrderByOrderId(orderId);
                String id = createOrdPayRelId();
                if(orderRsp != null){
                    ordPayRel = new OrdPayRel();
                    ordPayRel.setId(id);
                    ordPayRel.setOrderId(orderRsp.getOrderCode());
                    ordPayRel.setRealMoney(orderRsp.getRealMoney());
                    ordPayRel.setStaffId(orderRsp.getStaffId());
                    ordPayRel.setShopId(orderRsp.getShopId());
                    ordPayRel.setOrderTime(orderRsp.getOrderTime());
                    ordPayRel.setPayFlag(orderRsp.getPayFlag());
                    ordPayRel.setPayWay(orderRsp.getPayWay());
                    ordPayRel.setJoinOrderid(joinOrderId);
                    ordPayRel.setCreateTime(createTime);
                    ordPayRel.setCreateStaff(rOrdPayRelReq.getStaff().getId());
                    mergeTotalRealMoney=mergeTotalRealMoney+orderRsp.getRealMoney();
                    ordPayRelMapper.insert(ordPayRel);
                    /*rOrdPayRelResp.setJoinOrderid(joinOrderId);*/
                }
            }
        }
        rOrdPayRelResp.setJoinOrderid(joinOrderId);
        rOrdPayRelResp.setRealMoney(mergeTotalRealMoney);
        return rOrdPayRelResp;
    }
    /*
     * 生成合并订单序列id的方法
     */
    public String createOrdPayRelId() {
        DateFormat dfmt = new SimpleDateFormat("yyMMdd");
        String nowDate = dfmt.format(new Date());
        return Common.MER_CODE + nowDate
                + StringUtil.lPad(seqOrdPayRel.nextValue().toString(), "0", 8);

    }
    @Override
    public List<ROrdPayRelResp> queryOrdPayRelByOption(ROrdPayRelReq rOrdPayRelReq)
            throws BusinessException {
        List<ROrdPayRelResp> resultList = new ArrayList<ROrdPayRelResp>();
        OrdPayRelCriteria ordPayRelCriteria = new OrdPayRelCriteria();
        OrdPayRelCriteria.Criteria criteria = ordPayRelCriteria.createCriteria();
        if(StringUtil.isNotBlank(rOrdPayRelReq.getJoinOrderid())){
            criteria.andJoinOrderidEqualTo(rOrdPayRelReq.getJoinOrderid());
        }
        if(StringUtil.isNotBlank(rOrdPayRelReq.getPayFlag())){
            criteria.andPayFlagEqualTo(rOrdPayRelReq.getPayFlag());
        }
        if(StringUtil.isNotBlank(rOrdPayRelReq.getOrderId())){
            criteria.andOrderIdEqualTo(rOrdPayRelReq.getOrderId());
        }
        ordPayRelCriteria.setOrderByClause(" create_time desc ");
        List<OrdPayRel> list = ordPayRelMapper.selectByExample(ordPayRelCriteria);
        if(list != null){
            ROrdPayRelResp rOrdPayRelResp = null;
            for(OrdPayRel ordPayRel : list){
                rOrdPayRelResp = new ROrdPayRelResp();
                ObjectCopyUtil.copyObjValue(ordPayRel, rOrdPayRelResp, null, true);
                resultList.add(rOrdPayRelResp);
            }
        }
        return resultList;
    }
    
    @Override
    public void updateOrdPayRel(ROrdPayRelReq rOrdPayRelReq) throws BusinessException {
        OrdPayRel ordPayRel = new OrdPayRel();
        OrdPayRelCriteria ordPayRelCriteria = new OrdPayRelCriteria();
        OrdPayRelCriteria.Criteria criteria = ordPayRelCriteria.createCriteria();
        if(!StringUtil.isBlank(rOrdPayRelReq.getJoinOrderid())){
            criteria.andJoinOrderidEqualTo(rOrdPayRelReq.getJoinOrderid());
        }
        ObjectCopyUtil.copyObjValue(rOrdPayRelReq, ordPayRel, null, false);
        ordPayRelMapper.updateByExampleSelective(ordPayRel, ordPayRelCriteria);
    }

}

