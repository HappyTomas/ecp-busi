package com.zengshi.ecp.staff.service.busi.union.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.general.order.dto.PayQuartzInfoRequest;
import com.zengshi.ecp.general.order.interfaces.IPayQuartzInfoRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dao.model.AuthStaffAdmin;
import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dao.model.ScoreInfo;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ListReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderAcctMainResDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderAcctSubResDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackCheckReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackMainReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackSubReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreExchangeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreFrozenBackReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.TransactionContentReqDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.acct.interfaces.IAcctToOrderSV;
import com.zengshi.ecp.staff.service.busi.acct.interfaces.IAcctTradeSV;
import com.zengshi.ecp.staff.service.busi.custinfo.interfaces.ICustInfoSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IAdminManageSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.ICustManageSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IShopManageSV;
import com.zengshi.ecp.staff.service.busi.score.interfaces.IScoreCaclSV;
import com.zengshi.ecp.staff.service.busi.score.interfaces.IScoreInfoSV;
import com.zengshi.ecp.staff.service.busi.score.interfaces.IScoreToOrderOtherSV;
import com.zengshi.ecp.staff.service.busi.union.interfaces.IOrdBackCheckSV;
import com.zengshi.ecp.staff.service.busi.union.interfaces.IStaffUnionSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 用户域对外统一服务接口实现类<br>
 * Date:2015-9-30下午7:13:58 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version
 * @since JDK 1.7
 */
public class StaffUnionSVImpl implements IStaffUnionSV {

    @Resource
    private IScoreInfoSV scoreInfoSV;

    /*2016-08-08配合子订单可部分退货的改造，直接把这个指向给改了，接口实现也有相应调整*/
    @Resource
    private IScoreToOrderOtherSV scoreToOrderOtherSV;

    @Resource
    private IScoreCaclSV scoreCaclSV;

    @Resource
    private IAcctTradeSV acctTradeSV;

    @Resource
    private ICustInfoSV custInfoSV;
    
    @Resource
    private ICustManageSV custManageSV;

    @Resource
    private IPayQuartzInfoRSV payQuartzInfoRSV;
    
    @Resource
    private IAdminManageSV adminManageSV;
    
    @Resource
    private IAcctToOrderSV acctToOrderSV;
    
    @Resource
    private IShopManageSV shopManageSV;
    
    @Resource
    private IOrdBackCheckSV ordBackCheckSV;
    

    private static final String MODULE = StaffUnionSVImpl.class.getName();

    @Override
    public boolean saveStaffRelInfoForOrder(ListReqDTO<ScoreExchangeReqDTO> scoreReq,
            ListReqDTO<TransactionContentReqDTO> listReqDto) throws BusinessException {
        /* 下订单时，积分使用服务 */
        /* 入参对象可以为空，为空时，表示没有使用积分，不进行任何操作，直接跳过业务处理 */
        if (scoreReq != null && scoreReq.getList().size() != 0) {
            for (ScoreExchangeReqDTO scoreExchangeReq : scoreReq.getList()) {
                scoreExchangeReq.setCreateStaff(scoreExchangeReq.getStaffId());
                scoreExchangeReq.setOptType(StaffConstants.Score.SCORE_OPT_TYPE_2);
                scoreExchangeReq.setSiteId(scoreExchangeReq.getCurrentSiteId());
                scoreExchangeReq.setExchangeMode(StaffConstants.Score.SCORE_EXCHANGE_MODE_1);
                scoreExchangeReq.setRemark("订单【"+scoreExchangeReq.getOrderId()+"】兑换商品，使用积分。");
                scoreInfoSV.saveScoreUse(scoreExchangeReq); 
            }
        }
        /* 下订单时，资金账户使用服务 */
        /* 入参对象可以为空，为空时，表示没有使用资金账户，不进行任何操作，直接跳过业务处理 */
        if (listReqDto != null) {
            acctTradeSV.updateAcctBalance(listReqDto);
        }
        return true;
    }

    @Override
    public boolean saveStaffRelInfoForOrderRollBack(ListReqDTO<ScoreExchangeReqDTO> scoreReq,
            ListReqDTO<TransactionContentReqDTO> listReqDto) throws BusinessException {
        /* 下订单时，积分使用服务，异常时，回退操作 */
        /* 入参对象可以为空，为空时，表示无需回退积分，不进行任何操作，直接跳过业务处理 */
        if (scoreReq != null && scoreReq.getList().size() != 0) {
            for (ScoreExchangeReqDTO scoreExchangeReq : scoreReq.getList()) {
            	scoreToOrderOtherSV.saveScoreUseRollBack(scoreExchangeReq);
            }
        }
        /* 下订单时，资金账户使用服务，异常时，回退操作 */
        /* 入参对象可以为空，为空时，表示无需回退资金账户数据，不进行任何操作，直接跳过业务处理 */
        if (listReqDto != null && listReqDto.getList().size() != 0) {
            for (TransactionContentReqDTO reqDto : listReqDto.getList()) {
                acctTradeSV.executeOrderRefund(reqDto);
            }
        }
        return true;
    }

    @Override
    public boolean saveStaffRelInfoForPay(PayQuartzInfoRequest dto) throws BusinessException {
            boolean flag = true;
            /* 订单支付完成后，支付回调：获取积分服务 */
            String pSourceType = dto.getSourceType();
            CustInfoReqDTO pCustInfo = new CustInfoReqDTO();
            pCustInfo.setId(dto.getStaffId());
            /* 订单支付完成后，支付回调：用户会员等级及成长值增加的服务 */
            try {
                scoreCaclSV.updateScore(pSourceType, pCustInfo, dto);
            } catch (Exception e) {
                LogUtil.error(MODULE, "更新会员积分失败", e);
                throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR);
            }
            try {
                custInfoSV.updateCustLevelCode(dto);
            } catch (Exception e) {
                LogUtil.error(MODULE, "更新会员成长值失败", e);
                throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR);
            }
            /*店铺会员成长值计算*/
            try {
            	shopManageSV.updateShopVipRealForPay(dto);
            } catch (Exception e) {
                LogUtil.error(MODULE, "更新店铺会员成长值失败", e);
                throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR);
            }
            LogUtil.info(MODULE, "=================客户域逻辑执行结束，开始调用定时任务=================");
        
            return flag;
    }

    @Override
    public CustInfoResDTO findCustOrAdminByStaffId(Long staffId) throws BusinessException {
        CustInfoResDTO res = new CustInfoResDTO();
        //判断参数是否有效
        if (staffId != null && staffId != 0L) {
            /*查询会员表*/
            CustInfo custInfo = custInfoSV.findCustInfoById(staffId);
            if (custInfo != null) {
                res.setCustName(custInfo.getCustName());
                res.setStaffCode(custInfo.getStaffCode());
                /*会员表查不到，则查询管理人员表，如果查到数据，统一叫：管理员*/
            } else {
                AuthStaffAdmin admin = adminManageSV.findAuthStaffAdminById(staffId);
                if (admin != null) {
                    res.setCustName(admin.getStaffName());
                    res.setStaffCode(admin.getStaffCode());
                }
            }
        }
        return res;
    }

    @Override
    public CustInfoResDTO findCustInfo(CustInfoReqDTO req) throws BusinessException {
        return custManageSV.findCustInfo(req);
    }

    @Override
    public boolean saveScoreAcctForOrderRefund(OrderBackSubReqDTO reqDto) throws BusinessException {
        boolean flag = true;
        //查询出账户是否可以扣为负的开关
        String zeroFlag = BaseParamUtil.fetchParamValue("SUBTRACT_ZERO_FLAG", "BELOW_ZERO");
        reqDto.setType(zeroFlag);//1：可扣为负；其他则不可扣为负
        /*1、返回已使用的积分*/
        flag = scoreToOrderOtherSV.saveScoreUseForOrderRefund(reqDto);
        if (flag) {
            /*2、已获得的积分进行扣减*/
            flag = scoreToOrderOtherSV.saveScoreAddForOrderRefund(reqDto);
            /*3、资金账户返还*/
            acctToOrderSV.saveAcctUseForOrderRefund(reqDto);
        }
        return flag;
    }

    @Override
    public Long saveScoreFrozenForOrderBack(OrderBackMainReqDTO<OrderBackSubReqDTO> req) throws BusinessException {
        long freezeScore = 0L;
        
        //查询出账户是否可以扣为负的开关
        String zeroFlag = BaseParamUtil.fetchParamValue("SUBTRACT_ZERO_FLAG", "BELOW_ZERO");
        req.setType(zeroFlag);//1：可扣为负；其他则不可扣为负
        if (req != null) {
            /*1、冻结子订单获得的积分*/
            for (OrderBackSubReqDTO orderBack : req.getList()) {
            	orderBack.setType(zeroFlag);
                freezeScore += scoreToOrderOtherSV.saveScoreFrozenForOrderBackSub(orderBack);
            }
            /*2、冻结子订单平摊主订单获得的积分*/
            freezeScore += scoreToOrderOtherSV.saveScoreFrozenForOrderBackMain(req);
        }
        return freezeScore;
    }

    @Override
    public boolean saveScoreAcctForOrderBack(OrderBackMainReqDTO<OrderBackSubReqDTO> req,OrderBackSubReqDTO orderReq) throws BusinessException {
        boolean isOpt = false;
        Long backId = 0L;
        //校验该笔退款退货订单是否已经操作过了。
        if (req.getBackId() != null && req.getBackId() != 0L) {
            backId = req.getBackId();
            isOpt = ordBackCheckSV.checkOrdBack(req.getBackId());
        } else if (orderReq.getBackId() != null && orderReq.getBackId() != 0L) {
            backId = orderReq.getBackId();
            isOpt = ordBackCheckSV.checkOrdBack(orderReq.getBackId());
        }
        //已经操作过，则直接返回true，记录一下日志
        if (isOpt) {
            LogUtil.info("StaffUnionSVImpl", "该笔退款/退货订单已经处理过了。backId为：" + backId);
            return true;
        }
        //查询出账户是否可以扣为负的开关
        String zeroFlag = BaseParamUtil.fetchParamValue("SUBTRACT_ZERO_FLAG", "BELOW_ZERO");
        req.setType(zeroFlag);//1：可扣为负；其他则不可扣为负
        if (req != null) {
            /*1、返还使用的积分*/
            for (OrderBackSubReqDTO orderBack : req.getList()) {
                scoreToOrderOtherSV.saveScoreUseForOrderRefund(orderBack);
            }
            /*2、解冻子订单获得的积分，并扣除*/
            for (OrderBackSubReqDTO orderBack : req.getList()) {
            	orderBack.setType(zeroFlag);
            	orderBack.setRefundOrBack(req.getRefundOrBack());//退款退货标识统一用主订单的，订单域不分别入值
                scoreToOrderOtherSV.saveScoreAddForOrderBackSub(orderBack);
            }
            /*3、解冻子订单平摊主订单获得的积分，并扣除*/
            scoreToOrderOtherSV.saveScoreAddForOrderBackMain(req);
        }
        if (orderReq != null) {
        	/*4、返还资金账户使用的金额*/
            acctToOrderSV.saveAcctUseForOrderBack(orderReq);
        }
        /*5、业务处理完成后，记录此次退款退货申请id，以免下次重复处理*/
        OrderBackCheckReqDTO checkReq = new OrderBackCheckReqDTO();
        checkReq.setBackId(backId);
        ordBackCheckSV.saveOraBackCheck(checkReq);
        return true;
    }



    @Override
    public long selTotalScoreByOrderId(String orderId) throws BusinessException {
        return scoreToOrderOtherSV.selTotalScoreByOrderId(orderId);
    }

    @Override
    public long selTotalScoreByBackSubOrder(OrderBackMainReqDTO<OrderBackSubReqDTO> req) throws BusinessException {
        long score = 0L;
        if (req != null) {
            /*1、子订单获得的积分*/
            for (OrderBackSubReqDTO orderBack : req.getList()) {
                score += scoreToOrderOtherSV.selTotalScoreByBackOrderSub(orderBack);
            }
            /*2、子订单平摊主订单获得的积分*/
            score += scoreToOrderOtherSV.selTotalScoreByBackOrderMain(req);
        }
        return score;
    }

    @Override
    public ScoreInfoResDTO findScoreInfoByStaffId(Long staffId) throws BusinessException {
        ScoreInfoResDTO res = new ScoreInfoResDTO();
        ScoreInfo scoreInfo = scoreInfoSV.findScoreInfoByStaffId(staffId);
        if (scoreInfo != null) {
            ObjectCopyUtil.copyObjValue(scoreInfo, res, null, false);
        }
        return res;
    }

    @Override
    public OrderAcctMainResDTO<OrderAcctSubResDTO> selAcctByBackOrder(
            OrderBackMainReqDTO<OrderBackSubReqDTO> req) throws BusinessException {
        return acctToOrderSV.selAcctByBackOrder(req);
    }

	@Override
	public boolean saveScoreFrozenBack(ScoreFrozenBackReqDTO req)
			throws BusinessException {
		return scoreToOrderOtherSV.saveScoreFrozenBack(req);
	}

}
