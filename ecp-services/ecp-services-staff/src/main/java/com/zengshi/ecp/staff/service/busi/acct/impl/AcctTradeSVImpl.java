package com.zengshi.ecp.staff.service.busi.acct.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dao.mapper.busi.AcctTradeMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.AcctTradeShopIDXMapper;
import com.zengshi.ecp.staff.dao.model.AcctInfo;
import com.zengshi.ecp.staff.dao.model.AcctInfoHis;
import com.zengshi.ecp.staff.dao.model.AcctInfoKey;
import com.zengshi.ecp.staff.dao.model.AcctTrade;
import com.zengshi.ecp.staff.dao.model.AcctTradeCriteria;
import com.zengshi.ecp.staff.dao.model.AcctTradeCriteria.Criteria;
import com.zengshi.ecp.staff.dao.model.AcctTradeShopIDX;
import com.zengshi.ecp.staff.dao.model.AcctTypeKey;
import com.zengshi.ecp.staff.dao.model.ShopInfo;
import com.zengshi.ecp.staff.dubbo.dto.AcctTradeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTradeResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTypeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTypeResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ListReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackSubReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.TransactionContentReqDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.acct.interfaces.IAcctInfoSV;
import com.zengshi.ecp.staff.service.busi.acct.interfaces.IAcctTradeSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IShopMgrSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月13日下午3:53:33  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public class AcctTradeSVImpl extends GeneralSQLSVImpl implements IAcctTradeSV {
    private static final String MODULE = AcctTradeSVImpl.class.getName();
    
    @Resource
    private AcctTradeMapper acctTradeMapper; //资金账户交易
    
    @Resource
    private AcctTradeShopIDXMapper acctTradeShopIDXMapper; //资金账户交易的店铺索引
    
    @Resource
    private IAcctInfoSV acctInfoService; //账户信息服务
    
    @Resource
    private IShopMgrSV shopMgrService; //店铺管理
    
    @Resource(name = "seq_acct_trade_id")
    private Sequence seqAcctTrade; //资金账户交易表sequence
    
    @Resource(name = "seq_acct_trade_shop_idx_id")
    private Sequence seqTradeShopIDX; //资金账户交易之店铺索引表sequence

    @Override
    public long saveAcctTrade(AcctTrade acctTrade) throws BusinessException{
        //TODO 入参有效性校验
        //1.入库
        acctTrade.setId(seqAcctTrade.nextValue());
        try {
            acctTradeMapper.insertSelective(acctTrade);
        } catch (DataAccessException e) {
            LogUtil.info(MODULE, "资金账户交易记录保存异常");
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR, new String[]{ "资金交易" });
        }
        //2.索引
        AcctTradeShopIDX tradeShopIDX = new AcctTradeShopIDX();
        tradeShopIDX.setId(seqTradeShopIDX.nextValue());
        tradeShopIDX.setStaffId(acctTrade.getStaffId());
        tradeShopIDX.setShopId(acctTrade.getShopId());
        tradeShopIDX.setAcctType(acctTrade.getAcctType());
        tradeShopIDX.setTradeType(acctTrade.getTradeType());
        tradeShopIDX.setDebitCredit(acctTrade.getDebitCredit());
        try {
            acctTradeShopIDXMapper.insert(tradeShopIDX);
        } catch (DataAccessException e) {
            LogUtil.info(MODULE, "资金账户交易之店铺索引保存异常");
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR, new String[]{ "资金交易之店铺索引" });
        }
        
        return acctTrade.getId();
    }

    @Override
    public void useShopCaptialTraded(TransactionContentReqDTO transactionContentDto) throws BusinessException {
        if(transactionContentDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[] { "交易不存在" });
        }
        //1.店铺下是否存在该用户账户
        AcctInfoKey acctInfoKey = new AcctInfoKey();
        acctInfoKey.setAcctType(transactionContentDto.getAcctType());
        acctInfoKey.setAdaptType(transactionContentDto.getAdaptType());
        acctInfoKey.setShopId(transactionContentDto.getShopId());
        acctInfoKey.setStaffId(transactionContentDto.getStaffId());
        AcctInfo ai = acctInfoService.findAcctInfoByKey(acctInfoKey);
        if(ai==null){
            throw new BusinessException(StaffConstants.STAFF_EXECUTE_ERROR, new String[] { "账户不存在" });
        }
        if(!StaffConstants.Acct.ACCT_STATUS_ENABLE.equals(ai.getStatus())){
            throw new BusinessException(StaffConstants.STAFF_EXECUTE_ERROR, new String[] { "账户冻结" });
        }
        if(!(ai.getBalance()>0)){
            throw new BusinessException(StaffConstants.STAFF_EXECUTE_ERROR, new String[] { "账户余额为0" });
        }
        //2.账户存在并有效，则判断余额是否满足交易金额
        //2.1得到订单金额抵扣比
        AcctTypeKey acctTypeKey = new AcctTypeKey(); 
        acctTypeKey.setAcctType(transactionContentDto.getAcctType());
        acctTypeKey.setAdaptType(transactionContentDto.getAdaptType());
        acctTypeKey.setShopId(transactionContentDto.getShopId());
        AcctTypeReqDTO atDTO = new AcctTypeReqDTO();
        ObjectCopyUtil.copyObjValue(acctTypeKey, atDTO, null, false);
        AcctTypeResDTO at = acctInfoService.findAcctTypeByKey(atDTO);
        BigDecimal deductOrderRatio = at.getDeductOrderRatio();
        //2.2得到该笔订单可使用的资金金额
        BigDecimal availableAmount = deductOrderRatio.multiply(new BigDecimal(transactionContentDto.getTradeMoney().toString()));
        //availableAmount = availableAmount.divide(new BigDecimal("100"),0,RoundingMode.DOWN);//分转元
        if(availableAmount.longValue()>ai.getBalance()){
            throw new BusinessException(StaffConstants.STAFF_EXECUTE_ERROR, new String[] { "账户余额不足" });
        }
        //3.账户余额满足条件，则进行支付
        AcctInfo aiUpdate = new AcctInfo();
        aiUpdate.setAcctType(transactionContentDto.getAcctType());
        aiUpdate.setAdaptType(transactionContentDto.getAdaptType());
        aiUpdate.setStaffId(transactionContentDto.getStaffId());
        aiUpdate.setShopId(transactionContentDto.getShopId());
      //  aiUpdate.setTotalMoney(ai.getTotalMoney()-transactionContentDto.getTradeMoney());
        aiUpdate.setTotalMoney(ai.getTotalMoney());
        aiUpdate.setBalance(ai.getBalance()-transactionContentDto.getTradeMoney());
        aiUpdate.setUpdateStaff(transactionContentDto.getStaff().getId());
        aiUpdate.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        
        acctInfoService.updateAcctInfo(aiUpdate);
        //4.新增交易明细
        AcctTrade atSave = new AcctTrade();
        atSave.setStaffId(transactionContentDto.getStaffId());
        atSave.setTradeType(StaffConstants.Trade.TRADE_TYPE_PAY);
        atSave.setDebitCredit(StaffConstants.Trade.ACCT_DC_SPEND);
        atSave.setOrderId(transactionContentDto.getOrderId());
        atSave.setAcctType(transactionContentDto.getAcctType());
        atSave.setAdaptType(transactionContentDto.getAdaptType());
        atSave.setShopId(transactionContentDto.getShopId());
        atSave.setTradeMoney(transactionContentDto.getTradeMoney());
        atSave.setTotalMoney(ai.getTotalMoney());
        atSave.setBalance(ai.getBalance());
        atSave.setFreezeMoney(ai.getFreezeMoney());
        atSave.setCreateStaff(transactionContentDto.getStaffId());
        atSave.setCreateTime(new Timestamp(System.currentTimeMillis()));
        atSave.setUpdateStaff(transactionContentDto.getStaffId());
        atSave.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        
        saveAcctTrade(atSave);
        //5.新增历史快照
        AcctInfoHis aihSave = new AcctInfoHis();
        aihSave.setAcctId(ai.getId());
        aihSave.setStaffId(ai.getStaffId());
        aihSave.setAcctType(ai.getAcctType());
        aihSave.setAdaptType(ai.getAdaptType());
        aihSave.setShopId(ai.getShopId());
        aihSave.setDebitCredit(StaffConstants.Acct.ACCT_DC_SPEND);
        aihSave.setTradeMoney(transactionContentDto.getTradeMoney());
        aihSave.setTotalMoney(ai.getTotalMoney());
        aihSave.setBalance(ai.getBalance());
        aihSave.setFreezeMoney(ai.getFreezeMoney());
        aihSave.setCreateStaff(transactionContentDto.getStaffId());
        aihSave.setCreateTime(new Timestamp(System.currentTimeMillis()));
        aihSave.setUpdateStaff(transactionContentDto.getStaffId());
        aihSave.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        
        acctInfoService.saveAcctInfoHis(aihSave);
        
    }

    @Override
    public void decreaseAcctBalance(TransactionContentReqDTO reqDto) throws BusinessException {
        try {
            updateAcctBalance(reqDto);
        } catch (Exception e) {
            LogUtil.info(MODULE, "资金账户变动执行异常");
            throw new BusinessException(StaffConstants.STAFF_EXECUTE_ERROR, new String[]{ "减少余额" });
        }
    }

    @Override
    public void increaseAcctBalance(TransactionContentReqDTO reqDto) throws BusinessException {
        try {
            updateAcctBalance(reqDto);
        } catch (Exception e) {
            LogUtil.info(MODULE, "资金账户变动执行异常");
            throw new BusinessException(StaffConstants.STAFF_EXECUTE_ERROR, new String[]{ "增加余额" });
        }
    }
    
    @Override
    public void updateAcctBalance(TransactionContentReqDTO reqDto) throws BusinessException {
        if(StringUtil.isBlank(reqDto.getAcctType())||StringUtil.isBlank(reqDto.getAdaptType())
                ||reqDto.getStaffId()==null||StringUtil.isBlank(reqDto.getTradeType())
                ||StringUtil.isBlank(reqDto.getDebitCredit())||reqDto.getTradeMoney()==null
                ||StringUtil.isBlank(reqDto.getOrderId())){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"参数异常"});
        }
        if(reqDto.getTradeMoney()==0){
        	LogUtil.info(MODULE, "交易资金为0，执行忽略 ");
        	return;
        }
        String notCopy = "id,updateTime,updateStaff";
        //1.更新账户信息
        AcctInfoKey aiKey = new AcctInfoKey();
        
        aiKey.setAcctType(reqDto.getAcctType());
        aiKey.setAdaptType(reqDto.getAdaptType());
        aiKey.setShopId(reqDto.getShopId());
        aiKey.setStaffId(reqDto.getStaffId());
        //1.1得到账户[获取余额]
        //TODO 账户有校性验证
        AcctInfo acctInfo = acctInfoService.findAcctInfoByKey(aiKey);
        Long optStaffId = 0L;//操作人
        if (reqDto.getStaff().getId() == 0L) {
        	optStaffId = acctInfo.getStaffId();
        } else {
        	optStaffId = reqDto.getStaff().getId();
        }
        if(acctInfo==null){
            LogUtil.info(MODULE, "资金账户变动执行异常，账户不存在");
            throw new BusinessException(StaffConstants.STAFF_EXECUTE_ERROR, new String[]{ "账户不存在" });
        }
        //1.2修改账户余额
        AcctInfo aiUpdate = new AcctInfo();
        ObjectCopyUtil.copyObjValue(acctInfo,aiUpdate,null,true);
        /*余额更改*/
        if(StaffConstants.Acct.ACCT_DC_INCOME.equals(reqDto.getDebitCredit())){
            aiUpdate.setBalance(acctInfo.getBalance()+reqDto.getTradeMoney());
            aiUpdate.setTotalMoney(acctInfo.getTotalMoney()+reqDto.getTradeMoney());
        }else if(StaffConstants.Acct.ACCT_DC_SPEND.equals(reqDto.getDebitCredit())){
            if(!StaffConstants.Acct.ACCT_STATUS_ENABLE.equals(acctInfo.getStatus())){
                throw new BusinessException(StaffConstants.STAFF_EXECUTE_ERROR, new String[] { "账户冻结" });
            }
            if(!(acctInfo.getBalance()>0)){
                throw new BusinessException(StaffConstants.STAFF_EXECUTE_ERROR, new String[] { "账户余额非正数" });
            }
            aiUpdate.setBalance(acctInfo.getBalance()-reqDto.getTradeMoney());
            aiUpdate.setTotalMoney(acctInfo.getTotalMoney()-reqDto.getTradeMoney());
        }else {
            LogUtil.info(MODULE, "非法借贷类型:"+reqDto.getDebitCredit());
            throw new BusinessException(StaffConstants.STAFF_EXECUTE_ERROR, new String[]{ "非法借贷类型" });
        }
        aiUpdate.setUpdateStaff(optStaffId);
        aiUpdate.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        acctInfoService.updateAcctInfo(aiUpdate);
        //2.新增账户快照
        AcctInfoHis acctInfoHis = new AcctInfoHis();
        ObjectCopyUtil.copyObjValue(acctInfo, acctInfoHis, notCopy, false);
        
        acctInfoHis.setDebitCredit(reqDto.getDebitCredit());
        acctInfoHis.setTradeMoney(reqDto.getTradeMoney());
        acctInfoHis.setAcctId(acctInfo.getId());
        acctInfoHis.setCreateStaff(optStaffId);
        acctInfoHis.setUpdateStaff(optStaffId);
        acctInfoHis.setCreateTime(new Timestamp(System.currentTimeMillis()));
        acctInfoHis.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        acctInfoService.saveAcctInfoHis(acctInfoHis);
        //3.增加交易记录
        AcctTrade acctTrade = new AcctTrade();
        ObjectCopyUtil.copyObjValue(acctInfo, acctTrade, notCopy, false);
        
        acctTrade.setOrderId(reqDto.getOrderId());
        acctTrade.setDebitCredit(reqDto.getDebitCredit());
        acctTrade.setTradeMoney(reqDto.getTradeMoney());
        acctTrade.setTradeType(reqDto.getTradeType());
        acctTrade.setCreateStaff(optStaffId);
        acctTrade.setUpdateStaff(optStaffId);
        acctTrade.setCreateTime(new Timestamp(System.currentTimeMillis()));
        acctTrade.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        saveAcctTrade(acctTrade);
    }

    @Override
    public void updateAcctBalance(ListReqDTO<TransactionContentReqDTO> listReqDto) throws BusinessException {
        if(listReqDto==null||CollectionUtils.isEmpty(listReqDto.getList())){
            LogUtil.info(MODULE, "资金变动批量操作,reqList is empty! return");
            return;
        }
        LogUtil.info(MODULE, "资金变动批量操作,开始执行");
        try {
            for (TransactionContentReqDTO transactionContentReqDTO : listReqDto.getList()) {
                updateAcctBalance(transactionContentReqDTO);
            }
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "资金变动批量操作异常", e);
            throw e;
        }
    }
    
    @Override
    public PageResponseDTO<AcctTradeResDTO> listAcctTradeByAcct(AcctTradeReqDTO reqDto)
            throws BusinessException {
        if(StringUtil.isBlank(reqDto.getAcctType())||StringUtil.isBlank(reqDto.getAdaptType())
            ||reqDto.getStaffId()==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        return listAcctTrade(reqDto);
    }

    @Override
    public PageResponseDTO<AcctTradeResDTO> listAcctTrade(AcctTradeReqDTO reqDto)
            throws BusinessException {
        AcctTradeCriteria atCriteria = new AcctTradeCriteria();
        Criteria sql = atCriteria.createCriteria();
        if(reqDto.getStartTime()!=null){
        	sql.andCreateTimeGreaterThanOrEqualTo(reqDto.getStartTime());
        }
        if(reqDto.getEndTime()!=null){
        	sql.andCreateTimeLessThanOrEqualTo(reqDto.getEndTime());
        }
        if(StringUtil.isNotBlank(reqDto.getDebitCredit())){
            sql.andDebitCreditEqualTo(reqDto.getDebitCredit());
        }
        if(StringUtil.isNotBlank(reqDto.getTradeType())){
            sql.andTradeTypeEqualTo(reqDto.getTradeType());
        }
        if(StringUtil.isNotBlank(reqDto.getAcctType())){
            sql.andAcctTypeEqualTo(reqDto.getAcctType());
        }
        if(StringUtil.isNotBlank(reqDto.getAdaptType())){
            sql.andAdaptTypeEqualTo(reqDto.getAdaptType());
        }
        if(reqDto.getShopId()!=null && reqDto.getShopId() != 0L){
            sql.andShopIdEqualTo(reqDto.getShopId());
        }
        if(StringUtil.isNotBlank(reqDto.getOrderId())){
            sql.andOrderIdEqualTo(reqDto.getOrderId());
        }
        if(reqDto.getStaffId()!=null){
            sql.andStaffIdEqualTo(reqDto.getStaffId());
        }
        
        atCriteria.setLimitClauseStart(reqDto.getStartRowIndex());
        atCriteria.setLimitClauseCount(reqDto.getPageSize());
        atCriteria.setOrderByClause("create_time desc");

        return super.queryByPagination(reqDto, atCriteria, true, new PaginationCallback<AcctTrade, AcctTradeResDTO>() {

            @Override
            public List<AcctTrade> queryDB(BaseCriteria criteria) {
                return acctTradeMapper.selectByExample((AcctTradeCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return acctTradeMapper.countByExample((AcctTradeCriteria)criteria);
            }

            @Override
            public List<Comparator<AcctTrade>> defineComparators(){
                List<Comparator<AcctTrade>> ls = new ArrayList<Comparator<AcctTrade>>();
                ls.add(new Comparator<AcctTrade>() {
                    public int compare(AcctTrade o1, AcctTrade o2) {
                        return o2.getCreateTime().getTime()>o1.getCreateTime().getTime()?1:-1;
                    }
                });
                return ls;
            }
            
            @Override
            public AcctTradeResDTO warpReturnObject(AcctTrade t) {
                AcctTradeResDTO dto = new AcctTradeResDTO();
                ObjectCopyUtil.copyObjValue(t,dto,null,true);
                //字段转义
                ShopInfo s =shopMgrService.findShopByShopID(t.getShopId());
                dto.setAcctName((s==null?"":s.getShopName())+BaseParamUtil.fetchParamValue(StaffConstants.Acct.STAFF_ACCT_TYPE_PARAMKEY, 
                                                                           t.getAcctType()));//资金类型名
                
                return dto;
            }
        });
    }

    @Override
    public void executeOrderRefund(TransactionContentReqDTO reqDto) throws BusinessException {
        if(reqDto==null||StringUtil.isBlank(reqDto.getOrderId())){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"executeOrderRefund"});
        }
        //1.查找订单交易过程[验证交易存在与否]
        AcctTradeReqDTO orderSpend = new AcctTradeReqDTO();
        orderSpend.setOrderId(reqDto.getOrderId());
        orderSpend.setStaffId(reqDto.getStaffId());
        orderSpend.setDebitCredit(StaffConstants.Trade.ACCT_DC_SPEND);
        orderSpend.setPageNo(0);//不分页查询
        PageResponseDTO<AcctTradeResDTO> listOrderSpend = listAcctTrade(orderSpend);
        Long orderSpendCount = listOrderSpend.getCount();
        if(orderSpendCount<=0){
            //throw new BusinessException(StaffConstants.STAFF_EXECUTE_ERROR,new String[]{"资金回退不成功，无此订单使用资金记录"});
        	LogUtil.info(MODULE, "资金回退无效，无此订单使用资金记录");
        	return;
        } 
        
        //1.1查询订单是否已经回退过
        AcctTradeReqDTO orderBack = new AcctTradeReqDTO();
        orderBack.setOrderId(reqDto.getOrderId());
        orderBack.setTradeType(StaffConstants.Trade.TRADE_TYPE_CANCEL);
        orderBack.setPageNo(0);//不分页查询
        PageResponseDTO<AcctTradeResDTO> listBack = listAcctTrade(orderBack);
        Long backCnt = listBack.getCount();
        if (backCnt >= 1) {
        	LogUtil.info(MODULE, "资金回退无效，该订单[" + reqDto.getOrderId() + "]已经进行过相关操作");
        	return;
        }
        
        //2.查到订单使用资金情况，进行资金回退
        for (AcctTradeResDTO order : listOrderSpend.getResult()) {
            TransactionContentReqDTO refund = new TransactionContentReqDTO();
            refund.setStaffId(order.getStaffId());
            refund.setShopId(order.getShopId());
            refund.setOrderId(order.getOrderId());
            refund.setAdaptType(order.getAdaptType());
            refund.setAcctType(order.getAcctType());
            refund.setDebitCredit(StaffConstants.Trade.ACCT_DC_INCOME);
            refund.setTradeType(reqDto.getTradeType());
            refund.setTradeMoney(order.getTradeMoney());
            updateAcctBalance(refund);
        }
    }
    @Override
    public void executeOrderBack(OrderBackSubReqDTO orderReq) throws BusinessException {
        if(orderReq == null || StringUtil.isBlank(orderReq.getOrderId())){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"executeOrderRefund"});
        }
        String tradeType = StaffConstants.Trade.TRADE_TYPE_BACK;
        //交易类型：0，对应退款；1，对应退货
        if ("0".equals(orderReq.getRefundOrBack())) {
        	tradeType = StaffConstants.Trade.TRADE_TYPE_REFUND;
        } else {
        	tradeType = StaffConstants.Trade.TRADE_TYPE_BACK;
        }
        /*1、查询平台资金是否有使用*/
        AcctTradeReqDTO orderSpend = new AcctTradeReqDTO();
        orderSpend.setOrderId(orderReq.getOrderId());
        orderSpend.setStaffId(orderReq.getStaffId());
        orderSpend.setAdaptType(StaffConstants.Acct.ADAPT_TYPE_PLATFORM);
        orderSpend.setDebitCredit(StaffConstants.Trade.ACCT_DC_SPEND);
        orderSpend.setPageNo(0);//不分页查询
        PageResponseDTO<AcctTradeResDTO> listOrderPlatform = listAcctTrade(orderSpend);
        Long orderSpendCount = listOrderPlatform.getCount();
        /*1-1、如果有使用，则进行回退计算*/
        if(orderSpendCount != 0){
            long tradeMoneyPlatform = 0L;//平台资金
            //2.查到订单使用资金情况，进行资金回退
            TransactionContentReqDTO refund = new TransactionContentReqDTO();
            for (AcctTradeResDTO order : listOrderPlatform.getResult()) {
                tradeMoneyPlatform += order.getTradeMoney();
                refund.setShopId(order.getShopId());
                refund.setAcctType(order.getAcctType());
            }
            refund.setStaffId(orderReq.getStaffId());
            refund.setOrderId(orderReq.getOrderId()); 
            refund.setDebitCredit(StaffConstants.Trade.ACCT_DC_INCOME);
            refund.setTradeType(tradeType);

            //如果是最后一笔退货的子订单，则需要用总的金额，减去已回退的金额，而不能直接用比例
            if (orderReq.isLastFlag()) {
                //查询已退的平台资金总额
                AcctTradeReqDTO sumTradePlatform = new AcctTradeReqDTO();
                sumTradePlatform.setOrderId(orderReq.getOrderId());
                sumTradePlatform.setStaffId(orderReq.getStaffId());
                sumTradePlatform.setTradeType(tradeType);//交易类型：订单退货
                sumTradePlatform.setAdaptType(StaffConstants.Acct.ADAPT_TYPE_PLATFORM);//平台资金
                sumTradePlatform.setPageNo(0);//不分页查询
                long sumAcctPlatform = this.sumAcctTrade(sumTradePlatform);//已经回退了多少
                tradeMoneyPlatform = tradeMoneyPlatform - sumAcctPlatform;
            } else {
                tradeMoneyPlatform = tradeMoneyPlatform * orderReq.getScale() / 1000000;//精度丢失，代替向下取整
            }
            refund.setAdaptType(StaffConstants.Acct.ADAPT_TYPE_PLATFORM);//适用类型：平台资金
            refund.setTradeMoney(tradeMoneyPlatform);
            refund.getStaff().setId(orderReq.getStaffId());
            this.updateAcctBalance(refund);
        }
        /*2、查询店铺资金使用情况*/
        List<BaseParamDTO> baseList = BaseParamUtil.fetchParamList("STAFF_ACCT_TYPE");
        if (CollectionUtils.isNotEmpty(baseList)) {
            /*2-1、分别查询不同的店铺资金类型，进行回退操作*/
            for (BaseParamDTO dto : baseList) {
                AcctTradeReqDTO shopAcct = new AcctTradeReqDTO();
                shopAcct.setOrderId(orderReq.getOrderId());
                shopAcct.setStaffId(orderReq.getStaffId());
                shopAcct.setAdaptType(StaffConstants.Acct.ADAPT_TYPE_SHOP);//适用类型
                shopAcct.setAcctType(dto.getSpCode());//资金类型
                shopAcct.setDebitCredit(StaffConstants.Trade.ACCT_DC_SPEND);
                shopAcct.setPageNo(0);//不分页查询
                PageResponseDTO<AcctTradeResDTO> listOrderShop = listAcctTrade(shopAcct);
                Long listOrderShopCount = listOrderShop.getCount();
                /*1-1、如果有使用，则进行回退计算*/
                if(listOrderShopCount != 0){
                    long tradeMoneyShop = 0L;//店铺资金
                    //2.查到订单使用资金情况，进行资金回退
                    TransactionContentReqDTO refundShop = new TransactionContentReqDTO();
                    for (AcctTradeResDTO order : listOrderShop.getResult()) {
                        tradeMoneyShop += order.getTradeMoney();
                        refundShop.setShopId(order.getShopId());
                        refundShop.setAcctType(order.getAcctType());
                    }
                    refundShop.setStaffId(orderReq.getStaffId());
                    refundShop.setOrderId(orderReq.getOrderId()); 
                    refundShop.setDebitCredit(StaffConstants.Trade.ACCT_DC_INCOME);
                    refundShop.setTradeType(tradeType);

                    //如果是最后一笔退货的子订单，则需要用总的金额，减去已回退的金额，而不能直接用比例
                    if (orderReq.isLastFlag()) {
                        //查询已退的平台资金总额
                        AcctTradeReqDTO sumTradePlatform = new AcctTradeReqDTO();
                        sumTradePlatform.setOrderId(orderReq.getOrderId());
                        sumTradePlatform.setStaffId(orderReq.getStaffId());
                        sumTradePlatform.setAcctType(dto.getSpCode());//账户类型
                        sumTradePlatform.setTradeType(tradeType);//交易类型：订单退货
                        sumTradePlatform.setAdaptType(StaffConstants.Acct.ADAPT_TYPE_SHOP);//店铺资金
                        sumTradePlatform.setPageNo(0);//不分页查询
                        long sumAcctPlatform = this.sumAcctTrade(sumTradePlatform);//已经回退了多少
                        tradeMoneyShop = tradeMoneyShop - sumAcctPlatform;
                    } else {
                        tradeMoneyShop = tradeMoneyShop * orderReq.getScale() / 1000000;//精度丢失，代替向下取整
                    }
                    refundShop.setAdaptType(StaffConstants.Acct.ADAPT_TYPE_SHOP);//适用类型：平台资金
                    refundShop.setTradeMoney(tradeMoneyShop);
                    refundShop.getStaff().setId(orderReq.getStaffId());
                    this.updateAcctBalance(refundShop);
                }
            }
        }
    }

    /**
     * 
     * sumAcctTrade:(统计交易金额之和). <br/> 
     * 
     * @author huangxl5
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long sumAcctTrade(AcctTradeReqDTO reqDto) throws BusinessException {
        AcctTradeCriteria atCriteria = new AcctTradeCriteria();
        Criteria sql = atCriteria.createCriteria();
        if(reqDto.getStartTime()!=null){
            sql.andCreateTimeGreaterThanOrEqualTo(reqDto.getStartTime());
        }
        if(reqDto.getEndTime()!=null){
            sql.andCreateTimeLessThanOrEqualTo(reqDto.getEndTime());
        }
        if(StringUtil.isNotBlank(reqDto.getDebitCredit())){
            sql.andDebitCreditEqualTo(reqDto.getDebitCredit());
        }
        if(StringUtil.isNotBlank(reqDto.getTradeType())){
            sql.andTradeTypeEqualTo(reqDto.getTradeType());
        }
        if(StringUtil.isNotBlank(reqDto.getAcctType())){
            sql.andAcctTypeEqualTo(reqDto.getAcctType());
        }
        if(StringUtil.isNotBlank(reqDto.getAdaptType())){
            sql.andAdaptTypeEqualTo(reqDto.getAdaptType());
        }
        if(StringUtil.isNotBlank(reqDto.getOrderId())){
            sql.andOrderIdEqualTo(reqDto.getOrderId());
        }
        if(reqDto.getStaffId()!=null){
            sql.andStaffIdEqualTo(reqDto.getStaffId());
        }
        atCriteria.setLimitClauseStart(reqDto.getStartRowIndex());
        atCriteria.setLimitClauseCount(reqDto.getPageSize());
        Long sum = acctTradeMapper.sumByExample(atCriteria);
        return sum == null? 0 : sum;
    }

}

