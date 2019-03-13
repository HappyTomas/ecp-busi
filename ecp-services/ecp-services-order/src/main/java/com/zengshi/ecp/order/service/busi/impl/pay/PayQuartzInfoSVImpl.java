package com.zengshi.ecp.order.service.busi.impl.pay;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.order.dao.mapper.common.PayQuartzInfoLogMapper;
import com.zengshi.ecp.order.dao.mapper.common.PayQuartzInfoMapper;
import com.zengshi.ecp.order.dao.model.PayQuartzInfo;
import com.zengshi.ecp.order.dao.model.PayQuartzInfoCriteria;
import com.zengshi.ecp.order.dao.model.PayQuartzInfoLog;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayQuartzInfoRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayQuartzInfoResponse;
import com.zengshi.ecp.order.dubbo.util.MsgConstants.InputMsgCode;
import com.zengshi.ecp.order.dubbo.util.MsgConstants.PayInputMsgCode;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.PayStatus;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayQuartzInfoSV;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;

public class PayQuartzInfoSVImpl extends GeneralSQLSVImpl implements IPayQuartzInfoSV {

    @Resource
    private PayQuartzInfoMapper payQuartzInfoMapper;

    @Resource
    private PayQuartzInfoLogMapper payQuartzInfoLogMapper;

    @Resource(name = "seq_pay_quartz_info")
    private Sequence seqPayQuartzInfo;

    @Resource(name = "seq_pay_quartz_info_log")
    private Sequence seqPayQuartzInfoLog;

    @Override
    public void addScoreInfo(RPayQuartzInfoRequest payQuartzInfo) {
        PayQuartzInfo record = new PayQuartzInfo();
        ObjectCopyUtil.copyObjValue(payQuartzInfo, record, null, false);
        record.setId(seqPayQuartzInfo.nextValue());
        record.setTaskType(PayStatus.PAY_TASK_TYPE_01);
        record.setErrorTimes(0);
        record.setCreateTime(DateUtil.getSysDate());
        payQuartzInfoMapper.insert(record);
    }

    @Override
    public void addCoupInfo(RPayQuartzInfoRequest payQuartzInfo) {
        PayQuartzInfo record = new PayQuartzInfo();
        ObjectCopyUtil.copyObjValue(payQuartzInfo, record, null, false);
        record.setId(seqPayQuartzInfo.nextValue());
        record.setTaskType(PayStatus.PAY_TASK_TYPE_02);
        record.setErrorTimes(0);
        record.setCreateTime(DateUtil.getSysDate());
        payQuartzInfoMapper.insert(record);
    }
    

    @Override
    public int updateDealFlag(RPayQuartzInfoRequest payQuartzInfo,String OldStatus,String newStatus) {
        if (payQuartzInfo == null) {
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300000);
        }
        if (StringUtil.isBlank(OldStatus)) {
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300007);
        }
        if (StringUtil.isBlank(newStatus)) {
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300008);
        }
        if (StringUtil.isBlank(payQuartzInfo.getTaskType())) {
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300010);
        }
        PayQuartzInfo record = new PayQuartzInfo();
        record.setId(payQuartzInfo.getId());
        record.setDealFlag(newStatus);
        record.setUpdateStaff(payQuartzInfo.getUpdateStaff());
        record.setUpdateTime(DateUtil.getSysDate());
        
        PayQuartzInfoCriteria criteria = new PayQuartzInfoCriteria();
        criteria.createCriteria().andIdEqualTo(payQuartzInfo.getId())
                .andDealFlagEqualTo(OldStatus).andTaskTypeEqualTo(payQuartzInfo.getTaskType());
        return payQuartzInfoMapper.updateByExampleSelective(record,criteria);
    }

    @Override
    public void deletePayQuartzInfo(long id) {
        PayQuartzInfo bean = payQuartzInfoMapper.selectByPrimaryKey(id);
        PayQuartzInfoLog logBean = new PayQuartzInfoLog();
        ObjectCopyUtil.copyObjValue(bean, logBean, null, false);
        logBean.setId(seqPayQuartzInfoLog.nextValue());
        logBean.setOriginalId(bean.getId());
        logBean.setLogTime(DateUtil.getSysDate());
        logBean.setDealFlag(PayStatus.PAY_DEAL_FLAG_1);

        payQuartzInfoLogMapper.insert(logBean);

        payQuartzInfoMapper.deleteByPrimaryKey(id);
    }
    
    @Override
    public List<PayQuartzInfo> getBeanByOrderIdTaskTypeDealFlag(RPayQuartzInfoRequest payQuartzInfo) {
        if(payQuartzInfo==null){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300000);
        }
        if(StringUtil.isBlank(payQuartzInfo.getOrderId())){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300001);
        }
        if(StringUtil.isBlank(payQuartzInfo.getTaskType())){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300010);
        }
        if(StringUtil.isBlank(payQuartzInfo.getDealFlag())){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300007);
        }
        
        PayQuartzInfoCriteria example = new PayQuartzInfoCriteria();
        PayQuartzInfoCriteria.Criteria  criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(payQuartzInfo.getOrderId())
        .andTaskTypeEqualTo(payQuartzInfo.getTaskType())
        .andDealFlagEqualTo(payQuartzInfo.getDealFlag());
        
        List<PayQuartzInfo> list = payQuartzInfoMapper.selectByExample(example);
        return list;
    }
    
    @Override
    public PayQuartzInfo getBeanBySubOrderIdTaskTypeDealFlag(
            RPayQuartzInfoRequest payQuartzInfo) {
        if(payQuartzInfo==null){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300000);
        }
        if(StringUtil.isBlank(payQuartzInfo.getSubOrder())){
            throw new BusinessException(InputMsgCode.ORD_INPUT_311001);
        }
        if(StringUtil.isBlank(payQuartzInfo.getTaskType())){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300010);
        }
        if(StringUtil.isBlank(payQuartzInfo.getDealFlag())){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300007);
        }
        PayQuartzInfoCriteria example = new PayQuartzInfoCriteria();
        PayQuartzInfoCriteria.Criteria  criteria = example.createCriteria();
        criteria.andSubOrderEqualTo(payQuartzInfo.getSubOrder())
        .andTaskTypeEqualTo(payQuartzInfo.getTaskType())
        .andDealFlagEqualTo(payQuartzInfo.getDealFlag());
        
        List<PayQuartzInfo> list = payQuartzInfoMapper.selectByExample(example);
        if(list!=null&&!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }
    
    @Override
    public void addErrorTimes(Long id) {
        PayQuartzInfo payQuartzInfo = payQuartzInfoMapper.selectByPrimaryKey(id);
        if(payQuartzInfo!=null){
            if(payQuartzInfo.getErrorTimes()!=null){
                int i = payQuartzInfo.getErrorTimes()+1;
                payQuartzInfo.setErrorTimes(i);
            }else{
                payQuartzInfo.setErrorTimes(1);
            }
            payQuartzInfo.setUpdateTime(DateUtil.getSysDate());
            payQuartzInfoMapper.updateByPrimaryKeySelective(payQuartzInfo);
            if(payQuartzInfo.getErrorTimes()>=10){
                deletePayQuartzInfo(id);
            }
        }
    }

    private PageResponseDTO<RPayQuartzInfoResponse> queryByPagination(BaseInfo baseInfo,BaseCriteria criteria){
        return super.queryByPagination(baseInfo, criteria, true, new PaginationCallback<PayQuartzInfo, RPayQuartzInfoResponse>() {

            @Override
            public List<PayQuartzInfo> queryDB(BaseCriteria bCriteria) {
                return payQuartzInfoMapper.selectByExample((PayQuartzInfoCriteria)bCriteria) ;
            }

            @Override
            public long queryTotal(BaseCriteria bCriteria) {
                return payQuartzInfoMapper.countByExample((PayQuartzInfoCriteria)bCriteria);
            }

            @Override
            public List<Comparator<PayQuartzInfo>> defineComparators() {
                List<Comparator<PayQuartzInfo>> ls = new ArrayList<Comparator<PayQuartzInfo>>();
                ls.add(new Comparator<PayQuartzInfo>(){

                    @Override
                    public int compare(PayQuartzInfo o1, PayQuartzInfo o2) {
                        return o2.getCreateTime().compareTo(o1.getCreateTime());
                    }
                    
                });
                return ls;
            }

            @Override
            public RPayQuartzInfoResponse warpReturnObject(PayQuartzInfo ordMain) {
                RPayQuartzInfoResponse sdoi = new RPayQuartzInfoResponse();
                BeanUtils.copyProperties(ordMain, sdoi);
                return sdoi;
            }

            
        });
      }
    
    @Override
    public List<RPayQuartzInfoResponse> queryNotDealScoreOrder(RPayQuartzInfoRequest rPayQuartzInfoRequest) {
    	//获取间隔时间
        String secondsStr = BaseParamUtil.fetchParamValue("PAY_QUARTZ_TIME", "0");
        int seconds = Integer.parseInt(secondsStr);
        PayQuartzInfoCriteria example = new PayQuartzInfoCriteria();
        PayQuartzInfoCriteria.Criteria criteria = example.createCriteria();
        criteria.andTaskTypeEqualTo(PayStatus.PAY_TASK_TYPE_01);
        criteria.andDealFlagEqualTo(PayStatus.PAY_DEAL_FLAG_0);
        if(seconds>0){
        	
        	Timestamp time = DateUtil.getSysDate();
        	Calendar calendar=Calendar.getInstance();   
        	calendar.setTime(time); 
        	calendar.add(Calendar.SECOND, -seconds);
        	time = new Timestamp(calendar.getTimeInMillis());
        	criteria.andCreateTimeLessThan(time);
        }
        criteria.andErrorTimesLessThan(10);
        example.setOrderByClause(" UPDATE_TIME,CREATE_TIME ");
        example.setLimitClauseStart(0);
        example.setLimitClauseCount(rPayQuartzInfoRequest.getCount());
        PageResponseDTO<RPayQuartzInfoResponse> pageResponseDTO = queryByPagination(rPayQuartzInfoRequest,example);
        return pageResponseDTO.getResult();
    }
    
    @Override
    public List<RPayQuartzInfoResponse> queryNotDealCoupOrder(
            RPayQuartzInfoRequest rPayQuartzInfoRequest) {
    	//获取间隔时间
        String secondsStr = BaseParamUtil.fetchParamValue("PAY_QUARTZ_TIME", "1");
        int seconds = Integer.parseInt(secondsStr);
        PayQuartzInfoCriteria example = new PayQuartzInfoCriteria();
        PayQuartzInfoCriteria.Criteria criteria = example.createCriteria();
        criteria.andTaskTypeEqualTo(PayStatus.PAY_TASK_TYPE_02);
        criteria.andDealFlagEqualTo(PayStatus.PAY_DEAL_FLAG_0);
        
        if(seconds>0){
        	Timestamp time = DateUtil.getSysDate();
        	Calendar calendar=Calendar.getInstance();   
        	calendar.setTime(time); 
        	calendar.add(Calendar.SECOND, -seconds);
        	time = new Timestamp(calendar.getTimeInMillis());
        	criteria.andCreateTimeLessThan(time);
        }
        criteria.andErrorTimesLessThan(10);
        example.setOrderByClause(" UPDATE_TIME,CREATE_TIME ");
        example.setLimitClauseStart(0);
        example.setLimitClauseCount(rPayQuartzInfoRequest.getCount());
        PageResponseDTO<RPayQuartzInfoResponse> pageResponseDTO = queryByPagination(rPayQuartzInfoRequest,example);
        return pageResponseDTO.getResult();
    }
    
}
