/** 
 * Project Name:ecp-services-staff-server 
 * File Name:ScoreToOrderSVImpl.java 
 * Package Name:com.zengshi.ecp.staff.service.busi.score.impl 
 * Date:2015年9月30日下午3:22:58 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.staff.service.busi.score.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.model.ScoreExchange;
import com.zengshi.ecp.staff.dao.model.ScoreInfo;
import com.zengshi.ecp.staff.dao.model.ScoreOptLog;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackMainReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreExchangeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreExchangeResDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackSubReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreFrozenBackReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreResultResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreSourceReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreSourceResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.score.interfaces.IScoreInfoSV;
import com.zengshi.ecp.staff.service.busi.score.interfaces.IScoreToOrderSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 积分针对订单的接口<br>
 * Date:2015年9月30日下午3:22:58  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6 
 */
public class ScoreToOrderSVImpl implements IScoreToOrderSV {
    
    private  static final String MODULE = ScoreToOrderSVImpl.class.getName();   
    
    @Resource
    private IScoreInfoSV scoreInfoSV;

    /** 
     * TODO 积分回滚. 
     * @see com.zengshi.ecp.staff.service.busi.score.interfaces.IScoreToOrderSV#saveScoreRoolBack() 
     */
    @Override
    public int saveScoreRollBack(CustInfoReqDTO custinfo, OrderInfoReqDTO orderinfo) {
        return 0;
    }

    @Override
    public boolean checkOrdCart(ROrdCartsCommRequest ordCarts) throws BusinessException {
        //入参对象为空
        if (ordCarts == null) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"入参对象"});
        }
        //用户ID为空
        if (ordCarts.getStaffId() == null || ordCarts.getStaffId() == 0L) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"用户ID"});
        }
        Long scoreTotal = 0L;
        List<ROrdCartCommRequest> cartList = ordCarts.getOrdCartsCommList();
        for (ROrdCartCommRequest cartComm : cartList) {
            List<ROrdCartItemCommRequest> cartItemList= cartComm.getOrdCartItemCommList();
            for (ROrdCartItemCommRequest item : cartItemList) {
                if (item.getScore() != null && item.getScore() != 0) {
                    scoreTotal += item.getScore();
                }
            }
        }
        //该订单所需积分，如果为0，则不用计算，直接返回true
        if (scoreTotal == 0L) {
            return true;
        }
        //根据用户ID，查询积分账户
        ScoreInfo scoreInfo = scoreInfoSV.findScoreInfoByStaffId(ordCarts.getStaffId());
        //如果查不到积分账户，或者积分不足，则抛出业务异常：您的积分不足。
        if (scoreInfo == null || scoreInfo.getScoreBalance() < scoreTotal) {
            throw new BusinessException(StaffConstants.Score.SCORE_NOT_ENOUGH);
        }
        //如果积分帐户状态异常，则抛出业务异常：您的积分账户状态异常
        if (StaffConstants.Score.SCORE_STATUS_FROZEN.equals(scoreInfo.getStatus()) || StaffConstants.Score.SCORE_STATUS_INVALID.equals(scoreInfo.getStatus())) {
            throw new BusinessException(StaffConstants.Score.SCORE_STATUS_ERROR);
        }
        return true;
    }

    @Override
    public int saveScoreUseRollBack(ScoreExchangeReqDTO exchangeReq) throws BusinessException {
        LogUtil.info(MODULE, "=========开始进行积分回滚计算==========");
        /*0、先查询订单是否已经进行过回退操作，包括取消订单*/
    	ScoreSourceReqDTO sourceReq = new ScoreSourceReqDTO();
    	sourceReq.setOrderId(exchangeReq.getOrderId());
    	sourceReq.setSourceType(StaffConstants.Score.SCORE_ROLLBACK_TYPE);
    	PageResponseDTO<ScoreSourceResDTO> page = scoreInfoSV.listScoreSource(sourceReq);
    	if (page != null && CollectionUtils.isNotEmpty(page.getResult())) {
    		LogUtil.info(MODULE, "=========该订单[" + exchangeReq.getOrderId() + "]已经进行过相应操作==========");
    		return 1;
    	}
        
        /*1.根据用户id与订单id获取该订单所使用的积分*/
        ScoreExchangeReqDTO sedto = new ScoreExchangeReqDTO();
        sedto.setStaffId(exchangeReq.getStaffId());//用户id
        sedto.setOrderId(exchangeReq.getOrderId());//订单id
        sedto.setSubOrderId(exchangeReq.getSubOrderId());//子订单id
        PageResponseDTO<ScoreExchangeResDTO> sepage =  null;
        //如果订单不为空，则查询消费记录
        if (StringUtil.isNotBlank(exchangeReq.getOrderId())) {
            sepage = scoreInfoSV.listScoreExchange(sedto);
            //查不到消费记录，则直接返回，不做处理。查不到有两种情况：1、订单与用户id参数给错；2、这笔订单确实没有使用积分。
            if(CollectionUtils.isEmpty(sepage.getResult())) {
                LogUtil.info(MODULE, "根据用户以及订单信息：["+sedto.toString()+"]没有查询到对应的积分消费记录，无需回退。");
                return -1; 
            }
        }
        List<ScoreExchangeResDTO> scoreExList = sepage.getResult();
        ScoreExchangeResDTO scoreEx = (ScoreExchangeResDTO)scoreExList.get(0);
        ScoreResultResDTO result = new ScoreResultResDTO();
        result.setScore(scoreEx.getScoreUsing());
        result.setScoreType(StaffConstants.Score.SCORE_ROLLBACK_TYPE);//积分补偿类型
        
        /*2、补偿该用户积分账户，积分账户必定存在
                                     填写积分来源明细记录，积分来源类型为积分补偿类型
                                     填写操作日志，操纵类型为积分补偿类型*/
        ScoreSourceReqDTO req = new ScoreSourceReqDTO();
        req.setStaffId(exchangeReq.getStaffId());//用户ID
        req.setScore(result.getScore());
        req.setSourceType(StaffConstants.Score.SCORE_ROLLBACK_TYPE);
        req.setOptType(StaffConstants.Score.SCORE_OPT_TYPE_7);
        req.setCreateStaff(exchangeReq.getStaff().getId());
        req.setRemark("订单回滚，返还使用的积分");
        req.setOrderId(exchangeReq.getOrderId());
        req.setSubOrderId(exchangeReq.getSubOrderId());
        req.setSiteId(exchangeReq.getCurrentSiteId());//站点ID
        scoreInfoSV.saveScoreAdd(req); 
        LogUtil.info(MODULE, "=========结束积分回滚计算==========");
        return 1;
    }

    @Override
    public boolean saveScoreUseForOrderRefund(OrderBackSubReqDTO reqDto) throws BusinessException {
        LogUtil.info(MODULE, "=========订单退款时，把已使用的积分返还操作  START==========");
        String scoreType = StaffConstants.Score.SCORE_ORDER_REFUND_TYPE;
        String remark = "订单退款，返还已使用的积分";
        //订单入参 ： 1，退货  0,退款
        if ("1".equals(reqDto.getRefundOrBack())) {
        	scoreType = StaffConstants.Score.SCORE_ORDER_BACK_TYPE;
        	remark = "订单退货，返还已使用的积分";
        }
        LogUtil.info(MODULE, "传入的退款退货标识：["+reqDto.getRefundOrBack()+"]");

        /*1.根据用户id与订单id获取该订单所使用的积分*/
        ScoreExchangeReqDTO sedto = new ScoreExchangeReqDTO();
        sedto.setStaffId(reqDto.getStaffId());//用户id
        sedto.setOrderId(reqDto.getOrderId());//订单id
        sedto.setSubOrderId(reqDto.getSubOrderId());//子订单id（如果不传，则表示查的是所有主订单下的子订单）
        sedto.setPageNo(0);
        PageResponseDTO<ScoreExchangeResDTO> sepage =  null;
        //如果订单不为空，则查询消费记录
        if (StringUtil.isNotBlank(reqDto.getOrderId())) {
            sepage = scoreInfoSV.listScoreExchange(sedto);
            //查不到消费记录，则直接返回，不做处理。查不到有两种情况：1、订单与用户id参数给错；2、这笔订单确实没有使用积分。
            if(sepage == null || CollectionUtils.isEmpty(sepage.getResult())) {
                LogUtil.info(MODULE, "根据用户以及订单信息：["+sedto.toString()+"]没有查询到对应的积分消费记录，无需进行业务操作。");
                return true; 
            }
        } else {
            return false;
        }
        
        List<ScoreExchangeResDTO> scoreExList = sepage.getResult();
        for (ScoreExchangeResDTO scoreEx: scoreExList) {
            //如果已进行过回退操作或者该记录为回退操作，则跳过继续下面的订单
            if (StaffConstants.Score.SCORE_BACK_FLAG_1.equals(scoreEx.getBackFlag()) ||
                    StaffConstants.Score.SCORE_BACK_FLAG_2.equals(scoreEx.getBackFlag())) {
                continue;
            }
            /*2、返还该用户积分账户，积分账户必定存在
                                         填写积分来源明细记录，积分来源类型为订单退款返还积分
                                         填写操作日志，操纵类型为订单退款返还积分类型*/
            ScoreSourceReqDTO req = new ScoreSourceReqDTO();
            req.setStaffId(reqDto.getStaffId());//用户ID
            req.setScore(scoreEx.getScoreUsing());
            req.setSourceType(scoreType);
            req.setOptType(StaffConstants.Score.SCORE_OPT_TYPE_7);
            req.setCreateStaff(reqDto.getStaffId());
            req.setRemark(remark);
            req.setOrderId(reqDto.getOrderId());
            req.setSubOrderId(reqDto.getSubOrderId());
            req.setSiteId(reqDto.getCurrentSiteId());//站点ID
            req.setBackFlag(StaffConstants.Score.SCORE_BACK_FLAG_2);//新增的记录为：该条记录为回退记录
            scoreInfoSV.saveScoreAdd(req); 
            
            /*3、更新该条记录的back_flag状态为1：已进行了回退操作*/
            ScoreExchangeReqDTO exchangeReq = new ScoreExchangeReqDTO();
            exchangeReq.setId(scoreEx.getId());
            exchangeReq.setBackFlag(StaffConstants.Score.SCORE_BACK_FLAG_1);
            scoreInfoSV.updateScoreExchangeById(exchangeReq);
            
        }
        LogUtil.info(MODULE, "=========订单退款时，把已使用的积分返还操作  END==========");
        return true;
    }

    @Override
    public boolean saveScoreAddForOrderRefund(OrderBackSubReqDTO req) throws BusinessException {
        LogUtil.info(MODULE, "=========订单退款时，把所获得的积分，做相应的扣减操作  START==========");
        /*1、根据主订单id，查询出所有的积分来源记录*/
        PageResponseDTO<ScoreSourceResDTO> spage =  null;
        if (StringUtil.isNotBlank(req.getOrderId())) {
            ScoreSourceReqDTO sourceReq = new ScoreSourceReqDTO();
            sourceReq.setStaffId(req.getStaffId());
            sourceReq.setOrderId(req.getOrderId());
            sourceReq.setPageNo(0);
            spage = scoreInfoSV.listScoreSource(sourceReq);
            if(spage == null || CollectionUtils.isEmpty(spage.getResult())) {
                LogUtil.info(MODULE, "根据用户以及订单信息：["+req.toString()+"]没有查询到对应的积分来源记录，无需进行业务操作。");
                return true; 
            }
        } else {
            return false;
        }
        /*2、根据查出的记录，逐一进行扣减*/
        for (ScoreSourceResDTO sourceRes : spage.getResult()) {
           //如果已进行过回退操作或者该记录为回退操作，则跳过继续下面的订单
           if (StaffConstants.Score.SCORE_BACK_FLAG_1.equals(sourceRes.getBackFlag()) ||
                   StaffConstants.Score.SCORE_BACK_FLAG_2.equals(sourceRes.getBackFlag())) {
               continue;
           }
           ScoreExchangeReqDTO exchangeReq = new ScoreExchangeReqDTO();
           exchangeReq.setStaffId(req.getStaffId());
           exchangeReq.setCreateStaff(req.getStaffId());
           exchangeReq.setOrderId(sourceRes.getOrderId());
           exchangeReq.setSubOrderId(sourceRes.getSubOrderId());
           exchangeReq.setScoreUsing(sourceRes.getScore());//积分
           exchangeReq.setSiteId(sourceRes.getSiteId());//站点id
           exchangeReq.setFlag(req.getType());//是否可扣为负的标识
           exchangeReq.setRemark("订单退款，扣减该订单获得的积分。");
           exchangeReq.setBackFlag(StaffConstants.Score.SCORE_BACK_FLAG_2);
           exchangeReq.setExchangeMode(StaffConstants.Score.SCORE_EXCHANGE_MODE_2);//退货，退款
           exchangeReq.setOptType(StaffConstants.Score.SCORE_OPT_TYPE_8);//积分扣减：取消订单，退款，退货等
           scoreInfoSV.saveScoreUse(exchangeReq);
           
           /*3、更新该条记录bacl_flag状态为1：已进行回退操作*/
           ScoreSourceReqDTO sourceReq = new ScoreSourceReqDTO();
           sourceReq.setBackFlag(StaffConstants.Score.SCORE_BACK_FLAG_1);
           sourceReq.setId(sourceRes.getId());
           scoreInfoSV.updateScoreSourceById(sourceReq);
        }
        
        LogUtil.info(MODULE, "=========订单退款时，把所获得的积分，做相应的扣减操作  END==========");
        return true;
    }

    @Override
    public Long saveScoreFrozenForOrderBackSub(OrderBackSubReqDTO req) throws BusinessException {
        LogUtil.info(MODULE, "=========订单同意退货时，把子订单所获得的积分（含促销），先冻结起来  START==========");
        long subOrderScore = 0L;//子订单获得的积分
        /*1、根据主订单id，用户staffId，查询出所有的积分来源记录*/
        PageResponseDTO<ScoreSourceResDTO> spage =  null;
        if (StringUtil.isNotBlank(req.getOrderId())) {
            ScoreSourceReqDTO sourceReq = new ScoreSourceReqDTO();
            sourceReq.setStaffId(req.getStaffId());
            sourceReq.setOrderId(req.getOrderId());
            sourceReq.setPageNo(0);
            spage = scoreInfoSV.listScoreSource(sourceReq);
            if(spage == null || CollectionUtils.isEmpty(spage.getResult())) {
                LogUtil.info(MODULE, "根据用户以及订单信息：["+req.toString()+"]没有查询到对应的积分来源记录，无需进行业务操作。");
                return 0L; 
            }
        } else {
            return null;
        }
        /*2、循环结果记录：当记录有子订单id时，说明这是以子订单方式获得的积分，需要判断传入的子订单id*/
        for (ScoreSourceResDTO sourceRes : spage.getResult()) {
           //如果已进行过回退操作或者该记录为回退操作，则跳过继续下面的订单
           if (StaffConstants.Score.SCORE_BACK_FLAG_1.equals(sourceRes.getBackFlag()) ||
                   StaffConstants.Score.SCORE_BACK_FLAG_2.equals(sourceRes.getBackFlag())) {
               continue;
           }
           //子订单获得的积分
           if (StringUtil.isNotBlank(sourceRes.getSubOrderId()) && sourceRes.getSubOrderId().equals(req.getSubOrderId())) {
               subOrderScore += sourceRes.getScore();
           }
        }
        
        
        ScoreInfo scoreInfo = scoreInfoSV.findScoreInfoByStaffId(req.getStaffId());
        /*3-4、记录积分操作日志*/
        ScoreOptLog log = new ScoreOptLog();
        log.setTotalScore(scoreInfo.getScoreTotal());//操作前，总积分
        log.setBalanceScore(scoreInfo.getScoreBalance());//操作前，可用积分
        log.setFreezeScore(scoreInfo.getScoreFrozen());//操作前，冻结积分
        log.setUsedScore(scoreInfo.getScoreUsed());//操作前，已使用积分
        log.setScore(subOrderScore);//本次操作的积分
        log.setStaffId(req.getStaffId());//用户ID
        log.setRemark("订单退货，冻结子订单已获得的积分，不含平摊主订单所获得的积分。");//备注
        log.setOrderId(req.getOrderId());//订单编码
        log.setSubOrderId(req.getSubOrderId());//子订单编码
        log.setOptType(StaffConstants.Score.SCORE_OPT_TYPE_4);//操作类型
        log.setCreateStaff(String.valueOf(req.getStaffId()));//操作人
        log.setSiteId(req.getSiteId());//站点id
        scoreInfoSV.saveScoreOptLog(log);
        
        /*3-5、更新用户的积分账户，把积分冻结*/
        
        if (scoreInfo != null) {
            BigDecimal balance = new BigDecimal(scoreInfo.getScoreBalance());
            BigDecimal freeze = new BigDecimal(scoreInfo.getScoreFrozen());
            balance = balance.subtract(new BigDecimal(subOrderScore));//可用积分减少
            //可用积分不足，且积分不能扣为负时
            if (balance.compareTo(new BigDecimal(0)) == -1 && !StaffConstants.Score.SUBTRACT_BELOW_ZERO.equals(req.getType())) {
                throw new BusinessException(StaffConstants.Score.SCORE_NOT_ENOUGH, new String[]{"可用"});
            }
            freeze = freeze.add(new BigDecimal(subOrderScore));//冻结积分增加
            scoreInfo.setScoreBalance(balance.longValue());
            scoreInfo.setScoreFrozen(freeze.longValue());
            scoreInfoSV.updateScoreInfo(scoreInfo);
        }
        
        LogUtil.info(MODULE, "=========订单同意退货时，把子订单所获得的积分（含促销），先冻结起来  END==========");
        return subOrderScore;
    }
    
    @Override
    public Long saveScoreFrozenForOrderBackMain(OrderBackMainReqDTO<OrderBackSubReqDTO> req) throws BusinessException {
        LogUtil.info(MODULE, "=========订单同意退货时，把子订单所获得的积分（主订单上平摊获得的积分），先冻结起来  START==========");
        long mainOrderScore = 0L;//主订单获得的积分
        /*1、根据主订单id，用户staffId，查询出所有的积分来源记录*/
        PageResponseDTO<ScoreSourceResDTO> spage =  null;
        if (StringUtil.isNotBlank(req.getOrderId())) {
            ScoreSourceReqDTO sourceReq = new ScoreSourceReqDTO();
            sourceReq.setStaffId(req.getStaffId());
            sourceReq.setOrderId(req.getOrderId());
            sourceReq.setPageNo(0);
            spage = scoreInfoSV.listScoreSource(sourceReq);
            if(spage == null || CollectionUtils.isEmpty(spage.getResult())) {
                LogUtil.info(MODULE, "根据用户以及订单信息：["+req.toString()+"]没有查询到对应的积分来源记录，无需进行业务操作。");
                return 0L; 
            }
        } else {
            return null;
        }
        /*2、如果记录没有子订单id时，说明这是以主订单方式获得的积分，需要乘以平摊比例*/
        for (ScoreSourceResDTO sourceRes : spage.getResult()) {
           //如果已进行过回退操作或者该记录为回退操作，则跳过继续下面的订单
           if (StaffConstants.Score.SCORE_BACK_FLAG_1.equals(sourceRes.getBackFlag()) ||
                   StaffConstants.Score.SCORE_BACK_FLAG_2.equals(sourceRes.getBackFlag())) {
               continue;
           }
           //如果子订单等于主订单，则表示是以主订单方式获得的积分
           if (StringUtil.isNotBlank(sourceRes.getSubOrderId()) && req.getOrderId().equals(sourceRes.getSubOrderId())) {
               mainOrderScore += sourceRes.getScore();
           } 
        }
        /*3、计算此次需要冻结的积分，主要是计算主订单平摊的积分*/
        /*3-1、如果该退货订单是最后一笔，则需要查出主订单已退了多少积分，然后用总的主订单获得的积分，减去已退的积分*/
        if (req.isLastFlag()) {
            ScoreExchangeReqDTO backReq = new ScoreExchangeReqDTO();
            backReq.setStaffId(req.getStaffId());
            backReq.setOrderId(req.getOrderId());
            backReq.setPageNo(0);
            backReq.setBackFlag(StaffConstants.Score.SCORE_BACK_FLAG_2);
            PageResponseDTO<ScoreExchangeResDTO> exchangePage = scoreInfoSV.listScoreExchange(backReq);
            long backScore = 0L;
            //如果有退过，则计算出主订单上一共退了多少积分
            if(exchangePage != null && CollectionUtils.isNotEmpty(exchangePage.getResult())) {
                for (ScoreExchangeResDTO sourceRes : exchangePage.getResult()) {
                	 //主订单等于子订单，表示是主订单上获得的积分
                     if (StringUtil.isNotBlank(sourceRes.getSubOrderId()) && req.getOrderId().equals(sourceRes.getSubOrderId())) {
                         backScore += sourceRes.getScoreUsing();
                     }
                }
            }
            mainOrderScore = mainOrderScore - backScore;
        /*3-2、不是最后一笔，则用主订单获得的积分乘以比例即可。*/
        } else {
            mainOrderScore = mainOrderScore * req.getScale() / 1000000;//精度丢失，相当于向下取整
        }
        //积分为0，则直接返回
        if (mainOrderScore == 0L) {
        	return mainOrderScore;
        }
        
        ScoreInfo scoreInfo = scoreInfoSV.findScoreInfoByStaffId(req.getStaffId());
        /*3-3、记录积分操作日志*/
        ScoreOptLog log = new ScoreOptLog();
        log.setTotalScore(scoreInfo.getScoreTotal());//操作前，总积分
        log.setBalanceScore(scoreInfo.getScoreBalance());//操作前，可用积分
        log.setFreezeScore(scoreInfo.getScoreFrozen());//操作前，冻结积分
        log.setUsedScore(scoreInfo.getScoreUsed());//操作前，已使用积分
        log.setScore(mainOrderScore);//本次操作的积分
        log.setStaffId(req.getStaffId());//用户ID
        log.setRemark("订单退货，冻结已获得的积分");//备注
        log.setOrderId(req.getOrderId());//订单编码
        log.setOptType(StaffConstants.Score.SCORE_OPT_TYPE_4);//操作类型
        log.setCreateStaff(String.valueOf(req.getStaffId()));//操作人
        log.setSiteId(req.getSiteId());//站点id
        scoreInfoSV.saveScoreOptLog(log);
        
        /*3-5、更新用户的积分账户，把积分冻结*/
        
        if (scoreInfo != null) {
            BigDecimal balance = new BigDecimal(scoreInfo.getScoreBalance());
            BigDecimal freeze = new BigDecimal(scoreInfo.getScoreFrozen());
            balance = balance.subtract(new BigDecimal(mainOrderScore));//可用积分减少
            //可用积分不足，且积分不能扣为负时
            if (balance.compareTo(new BigDecimal(0)) == -1 && !StaffConstants.Score.SUBTRACT_BELOW_ZERO.equals(req.getType())) {
                throw new BusinessException(StaffConstants.Score.SCORE_NOT_ENOUGH, new String[]{"可用"});
            }
            freeze = freeze.add(new BigDecimal(mainOrderScore));//冻结积分增加
            scoreInfo.setScoreBalance(balance.longValue());
            scoreInfo.setScoreFrozen(freeze.longValue());
            scoreInfoSV.updateScoreInfo(scoreInfo);
        }
        
        LogUtil.info(MODULE, "=========订单同意退货时，把子订单所获得的积分（主订单上平摊获得的积分），先冻结起来  END==========");
        return mainOrderScore;
    }

    @Override
    public boolean saveScoreAddForOrderBackSub(OrderBackSubReqDTO req) throws BusinessException {
        LogUtil.info(MODULE, "=========订单同意退货时，把冻结的子订单的积分解冻，并做扣除  START==========");
        long subOrderScore = 0L;//子订单获得的积分
        ScoreInfo scoreInfo = scoreInfoSV.findScoreInfoByStaffId(req.getStaffId());
        /*1、根据主订单id，用户staffId，查询出所有的积分来源记录*/
        PageResponseDTO<ScoreSourceResDTO> spage =  null;
        if (StringUtil.isNotBlank(req.getOrderId())) {
            ScoreSourceReqDTO sourceReq = new ScoreSourceReqDTO();
            sourceReq.setStaffId(req.getStaffId());
            sourceReq.setOrderId(req.getOrderId());
            sourceReq.setPageNo(0);
            spage = scoreInfoSV.listScoreSource(sourceReq);
            if(spage == null || CollectionUtils.isEmpty(spage.getResult())) {
                LogUtil.info(MODULE, "根据用户以及订单信息：["+req.toString()+"]没有查询到对应的积分来源记录，无需进行业务操作。");
                return true; 
            }
        } else {
            return false;
        }
        /*2、循环结果记录：当记录有子订单id时，说明这是以子订单方式获得的积分，需要判断传入的子订单id
         * 如果记录没有子订单id时，说明这是以主订单方式获得的积分，需要乘以平摊比例*/
        for (ScoreSourceResDTO sourceRes : spage.getResult()) {
           //如果已进行过回退操作或者该记录为回退操作，则跳过继续下面的订单
           if (StaffConstants.Score.SCORE_BACK_FLAG_1.equals(sourceRes.getBackFlag()) ||
                   StaffConstants.Score.SCORE_BACK_FLAG_2.equals(sourceRes.getBackFlag())) {
               continue;
           }
           //子订单获得的积分
           if (StringUtil.isNotBlank(sourceRes.getSubOrderId()) && sourceRes.getSubOrderId().equals(req.getSubOrderId())) {
               subOrderScore += sourceRes.getScore();
               
               //增加一条积分扣减记录
               ScoreExchange exchangeReq = new ScoreExchange();
               exchangeReq.setStaffId(req.getStaffId());
               exchangeReq.setOrderId(sourceRes.getOrderId());
               exchangeReq.setSubOrderId(sourceRes.getSubOrderId());
               exchangeReq.setScoreUsing(sourceRes.getScore());//积分
               exchangeReq.setSiteId(sourceRes.getSiteId());//站点id
               exchangeReq.setCreateStaff(req.getStaffId());
               //订单入参：0，退款   1，退货
               if ("0".equals(req.getRefundOrBack())) {
            	   exchangeReq.setRemark("订单退款，扣减该子订单获得的积分。");
               } else {
            	   exchangeReq.setRemark("订单退货，扣减该子订单获得的积分。");
               }
               LogUtil.info(MODULE, "传入的退款退货标识：["+req.getRefundOrBack()+"]");
               exchangeReq.setBackFlag(StaffConstants.Score.SCORE_BACK_FLAG_2);
               exchangeReq.setExchangeMode(StaffConstants.Score.SCORE_EXCHANGE_MODE_2);//退货，退款
               scoreInfoSV.saveScoreExchange(exchangeReq);
               
               //增加一条操作日志 
               ScoreOptLog log = new ScoreOptLog();
               log.setTotalScore(scoreInfo.getScoreTotal());//操作前，总积分
               log.setBalanceScore(scoreInfo.getScoreBalance());//操作前，可用积分
               log.setFreezeScore(scoreInfo.getScoreFrozen());//操作前，冻结积分
               log.setUsedScore(scoreInfo.getScoreUsed());//操作前，已使用积分
               log.setScore(sourceRes.getScore());//本次操作的积分
               log.setStaffId(req.getStaffId());//用户ID
               //订单入参：0，退款   1，退货
               if ("0".equals(req.getRefundOrBack())) {
            	   log.setRemark("订单退款，解冻之前冻结的积分，并扣除。");//备注
               } else {
            	   log.setRemark("订单退货，解冻之前冻结的积分，并扣除。");//备注
               }
               log.setOptType(StaffConstants.Score.SCORE_OPT_TYPE_3);
               log.setOrderId(req.getOrderId());//订单编码
               log.setSubOrderId(req.getSubOrderId());//子订单编码
               log.setCreateStaff(String.valueOf(req.getStaffId()));//操作人
               log.setSiteId(req.getSiteId());//站点id
               scoreInfoSV.saveScoreOptLog(log);
               
               //更新该条记录bacl_flag状态为1：已进行回退操作*/
               ScoreSourceReqDTO sourceReq = new ScoreSourceReqDTO();
               sourceReq.setBackFlag(StaffConstants.Score.SCORE_BACK_FLAG_1);
               sourceReq.setId(sourceRes.getId());
               scoreInfoSV.updateScoreSourceById(sourceReq);
           }
        }
       
        /*3、更新用户的积分账户，把积分解冻，并作扣除，当成已使用
         * 冻结积分减少，已使用积分增加*/
        if (scoreInfo != null) {
            BigDecimal use = new BigDecimal(scoreInfo.getScoreUsed());
            BigDecimal freeze = new BigDecimal(scoreInfo.getScoreFrozen());
            freeze = freeze.subtract(new BigDecimal(subOrderScore));//冻结积分减少
            use = use.add(new BigDecimal(subOrderScore));//已使用积分增加
            scoreInfo.setScoreUsed(use.longValue());
            scoreInfo.setScoreFrozen(freeze.longValue());
            scoreInfoSV.updateScoreInfo(scoreInfo);
        }
        LogUtil.info(MODULE, "=========订单同意退货时，把冻结的积分解冻，并做扣除  END==========");
        return true;
    }
    
    @Override
    public boolean saveScoreAddForOrderBackMain(OrderBackMainReqDTO<OrderBackSubReqDTO> req) throws BusinessException {
        LogUtil.info(MODULE, "=========订单同意退货时，把冻结的子订单平摊主订单所获得的积分解冻，并做扣除  START==========");
        long mainOrderScore = 0L;//主订单获得的积分
        ScoreInfo scoreInfo = scoreInfoSV.findScoreInfoByStaffId(req.getStaffId());
        /*1、根据主订单id，用户staffId，查询出所有的积分来源记录*/
        PageResponseDTO<ScoreSourceResDTO> spage =  null;
        if (StringUtil.isNotBlank(req.getOrderId())) {
            ScoreSourceReqDTO sourceReq = new ScoreSourceReqDTO();
            sourceReq.setStaffId(req.getStaffId());
            sourceReq.setOrderId(req.getOrderId());
            sourceReq.setPageNo(0);
            spage = scoreInfoSV.listScoreSource(sourceReq);
            if(spage == null || CollectionUtils.isEmpty(spage.getResult())) {
                LogUtil.info(MODULE, "根据用户以及订单信息：["+req.toString()+"]没有查询到对应的积分来源记录，无需进行业务操作。");
                return true; 
            }
        } else {
            return false;
        }
        /*2、如果记录没有子订单id时，说明这是以主订单方式获得的积分，需要乘以平摊比例*/
        for (ScoreSourceResDTO sourceRes : spage.getResult()) {
           //如果已进行过回退操作或者该记录为回退操作，则跳过继续下面的订单
           if (StaffConstants.Score.SCORE_BACK_FLAG_1.equals(sourceRes.getBackFlag()) ||
                   StaffConstants.Score.SCORE_BACK_FLAG_2.equals(sourceRes.getBackFlag())) {
               continue;
           }
           //如果子订单等于主订单，则表示是以主订单方式获得的积分
           if (StringUtil.isNotBlank(sourceRes.getSubOrderId()) && req.getOrderId().equals(sourceRes.getSubOrderId())) {
               mainOrderScore += sourceRes.getScore();
               //<这里需要特别注意>如果是最后一笔，则把主订单的记录，状态变更为已进行回退操作。如果是最后一笔，不更新主订单记录的状态。
               if (req.isLastFlag()) {
                   //更新该条记录bacl_flag状态为1：已进行回退操作*/
                   ScoreSourceReqDTO sourceReq = new ScoreSourceReqDTO();
                   sourceReq.setBackFlag(StaffConstants.Score.SCORE_BACK_FLAG_1);
                   sourceReq.setId(sourceRes.getId());
                   scoreInfoSV.updateScoreSourceById(sourceReq);
               }
           } 
        }
        /*3、计算此次需要解冻的积分，主要是计算主订单平摊的积分*/
        /*3-1、如果该退货订单是最后一笔，则需要查出主订单已退了多少积分，然后用总的主订单获得的积分，减去已退的积分*/
        if (req.isLastFlag()) {
            ScoreExchangeReqDTO backReq = new ScoreExchangeReqDTO();
            backReq.setStaffId(req.getStaffId());
            backReq.setOrderId(req.getOrderId());
            backReq.setPageNo(0);
            backReq.setBackFlag(StaffConstants.Score.SCORE_BACK_FLAG_2);
            PageResponseDTO<ScoreExchangeResDTO> exchangePage = scoreInfoSV.listScoreExchange(backReq);
            long backScore = 0L;
            //如果有退过，则计算出主订单上一共退了多少积分
            if(exchangePage != null && CollectionUtils.isNotEmpty(exchangePage.getResult())) {
                 for (ScoreExchangeResDTO sourceRes : exchangePage.getResult()) {
                     if (StringUtil.isNotBlank(sourceRes.getSubOrderId()) && req.getOrderId().equals(sourceRes.getSubOrderId())) {
                         backScore += sourceRes.getScoreUsing();
                     }
                 }
            }
            mainOrderScore = mainOrderScore - backScore;
        /*3-2、不是最后一笔，则用主订单获得的积分乘以比例即可。*/
        } else {
            mainOrderScore = mainOrderScore * req.getScale() / 1000000;//精度丢失，相当于向下取整
        }
        if (mainOrderScore == 0L) {
        	return true;
        }
        /*3-4、记录一条主订单的积分消费记录*/
        ScoreExchange mainExchange = new ScoreExchange();
        mainExchange.setStaffId(req.getStaffId());
        mainExchange.setCreateStaff(req.getStaffId());
        mainExchange.setOrderId(req.getOrderId());
        mainExchange.setScoreUsing(mainOrderScore);//主订单上需要扣除的积分
        mainExchange.setSiteId(req.getSiteId());//站点id
        if ("0".equals(req.getRefundOrBack())) {
        	mainExchange.setRemark("订单退款，扣减该主订单获得的积分。扣减比例为：" + req.getScale() + "%");
        } else {
        	mainExchange.setRemark("订单退货，扣减该主订单获得的积分。扣减比例为：" + req.getScale() + "%");
        }
        
        mainExchange.setBackFlag(StaffConstants.Score.SCORE_BACK_FLAG_2);
        mainExchange.setExchangeMode(StaffConstants.Score.SCORE_EXCHANGE_MODE_2);//退货，退款
        scoreInfoSV.saveScoreExchange(mainExchange);
        
        /*3-5、记录一条主订单积分操作日志*/
        ScoreOptLog log = new ScoreOptLog();
        log.setTotalScore(scoreInfo.getScoreTotal());//操作前，总积分
        log.setBalanceScore(scoreInfo.getScoreBalance());//操作前，可用积分
        log.setFreezeScore(scoreInfo.getScoreFrozen());//操作前，冻结积分
        log.setUsedScore(scoreInfo.getScoreUsed());//操作前，已使用积分
        log.setScore(mainOrderScore);//本次操作的积分
        log.setStaffId(req.getStaffId());//用户ID
        if ("0".equals(req.getRefundOrBack())) {
        	log.setRemark("订单退款，主订单上之前冻结的积分解冻，并扣除。扣减比例为：" + req.getScale() + "%");//备注
        } else {
        	log.setRemark("订单退货，主订单上之前冻结的积分解冻，并扣除。扣减比例为：" + req.getScale() + "%");//备注
        }
        log.setOrderId(req.getOrderId());//订单编码
        log.setOptType(StaffConstants.Score.SCORE_OPT_TYPE_3);//操作类型
        log.setCreateStaff(String.valueOf(req.getStaffId()));//操作人
        log.setSiteId(req.getSiteId());//站点id
        scoreInfoSV.saveScoreOptLog(log);
        
        /*3-6、更新用户的积分账户，把积分解冻，并作扣除，当成已使用
         * 冻结积分减少，已使用积分增加*/
        if (scoreInfo != null) {
            BigDecimal use = new BigDecimal(scoreInfo.getScoreUsed());
            BigDecimal freeze = new BigDecimal(scoreInfo.getScoreFrozen());
            freeze = freeze.subtract(new BigDecimal(mainOrderScore));//冻结积分减少
            use = use.add(new BigDecimal(mainOrderScore));//已使用积分增加
            scoreInfo.setScoreUsed(use.longValue());
            scoreInfo.setScoreFrozen(freeze.longValue());
            scoreInfoSV.updateScoreInfo(scoreInfo);
        }
        LogUtil.info(MODULE, "========订单同意退货时，把冻结的子订单平摊主订单所获得的积分解冻，并做扣除  END==========");
        return true;
    }

    @Override
    public long selTotalScoreByOrderId(String orderId) throws BusinessException {
        ScoreSourceReqDTO req = new ScoreSourceReqDTO();
        req.setOrderId(orderId);
        req.setPageNo(0);
        PageResponseDTO<ScoreSourceResDTO> spage = scoreInfoSV.listScoreSource(req);
        long score = 0L;
        if (spage != null && CollectionUtils.isNotEmpty(spage.getResult())) {
            for (ScoreSourceResDTO source : spage.getResult()) {
                //排除标识为回退的数据
                if (!StaffConstants.Score.SCORE_BACK_FLAG_2.equals(source.getBackFlag())) {
                    score += source.getScore();
                }
            }
        }
        return score;
    }

    @Override
    public long selTotalScoreByBackOrderSub(OrderBackSubReqDTO req) throws BusinessException {
        long subOrderScore = 0L;//子订单获得的积分
        /*1、根据主订单id，用户staffId，查询出所有的积分来源记录*/
        PageResponseDTO<ScoreSourceResDTO> spage =  null;
        if (StringUtil.isNotBlank(req.getOrderId())) {
            ScoreSourceReqDTO sourceReq = new ScoreSourceReqDTO();
            sourceReq.setStaffId(req.getStaffId());
            sourceReq.setOrderId(req.getOrderId());
            sourceReq.setPageNo(0);
            spage = scoreInfoSV.listScoreSource(sourceReq);
            if(spage == null || CollectionUtils.isEmpty(spage.getResult())) {
                LogUtil.info(MODULE, "根据用户以及订单信息：["+req.toString()+"]没有查询到对应的积分来源记录，无需进行业务操作。");
                return 0L; 
            }
        } else {
            return 0L;
        }
        /*2、循环结果记录：当记录有子订单id时，说明这是以子订单方式获得的积分，需要判断传入的子订单id
         * 如果记录没有子订单id时，说明这是以主订单方式获得的积分，需要乘以平摊比例*/
        for (ScoreSourceResDTO sourceRes : spage.getResult()) {
           //该记录为回退操作，则跳过继续下面的订单
           if (StaffConstants.Score.SCORE_BACK_FLAG_2.equals(sourceRes.getBackFlag())) {
               continue;
           }
           //子订单获得的积分
           if (StringUtil.isNotBlank(sourceRes.getSubOrderId()) && sourceRes.getSubOrderId().equals(req.getSubOrderId())) {
               subOrderScore += sourceRes.getScore();
           }
        }
        
        return subOrderScore;
    }
    @Override
    public long selTotalScoreByBackOrderMain(OrderBackMainReqDTO<OrderBackSubReqDTO> req) throws BusinessException {
        long mainOrderScore = 0L;//主订单获得的积分
        /*1、根据主订单id，用户staffId，查询出所有的积分来源记录*/
        PageResponseDTO<ScoreSourceResDTO> spage =  null;
        if (StringUtil.isNotBlank(req.getOrderId())) {
            ScoreSourceReqDTO sourceReq = new ScoreSourceReqDTO();
            sourceReq.setStaffId(req.getStaffId());
            sourceReq.setOrderId(req.getOrderId());
            sourceReq.setPageNo(0);
            spage = scoreInfoSV.listScoreSource(sourceReq);
            if(spage == null || CollectionUtils.isEmpty(spage.getResult())) {
                LogUtil.info(MODULE, "根据用户以及订单信息：["+req.toString()+"]没有查询到对应的积分来源记录，无需进行业务操作。");
                return 0L; 
            }
        } else {
            return 0L;
        }
        /*2、如果记录没有子订单id时，说明这是以主订单方式获得的积分，需要乘以平摊比例*/
        for (ScoreSourceResDTO sourceRes : spage.getResult()) {
           //该记录为回退操作，则跳过继续下面的订单
           if (StaffConstants.Score.SCORE_BACK_FLAG_2.equals(sourceRes.getBackFlag())) {
               continue;
           }
           //如果子订单等于订订单，则表示是以主订单方式获得的积分
           if (StringUtil.isNotBlank(sourceRes.getSubOrderId()) && req.getOrderId().equals(sourceRes.getSubOrderId())) {
               mainOrderScore += sourceRes.getScore();
           } 
        }
        /*3、计算此次需要冻结的积分，主要是计算主订单平摊的积分*/
        /*3-1、如果该退货订单是最后一笔，则需要查出主订单已退了多少积分，然后用总的主订单获得的积分，减去已退的积分*/
        if (req.isLastFlag()) {
            ScoreExchangeReqDTO backReq = new ScoreExchangeReqDTO();
            backReq.setStaffId(req.getStaffId());
            backReq.setOrderId(req.getOrderId());
            backReq.setPageNo(0);
            backReq.setBackFlag(StaffConstants.Score.SCORE_BACK_FLAG_2);
            PageResponseDTO<ScoreExchangeResDTO> exchangePage = scoreInfoSV.listScoreExchange(backReq);
            long backScore = 0L;
            //如果有退过，则计算出主订单上一共退了多少积分
            if(exchangePage != null && CollectionUtils.isNotEmpty(exchangePage.getResult())) {
                for (ScoreExchangeResDTO sourceRes : exchangePage.getResult()) {
                     if (StringUtil.isNotBlank(sourceRes.getSubOrderId()) && req.getOrderId().equals(sourceRes.getSubOrderId())) {
                         backScore += sourceRes.getScoreUsing();
                     }
                }
            }
            mainOrderScore = mainOrderScore - backScore;
        /*3-2、不是最后一笔，则用主订单获得的积分乘以比例即可。*/
        } else {
            mainOrderScore = mainOrderScore * req.getScale() / 1000000;//精度丢失，相当于向下取整
        }
        
        return mainOrderScore;
    }

	@Override
	public boolean saveScoreFrozenBack(ScoreFrozenBackReqDTO req)
			throws BusinessException {
		ScoreInfo scoreInfo = scoreInfoSV.findScoreInfoByStaffId(req.getStaffId());
        /*1、记录积分操作日志*/
        ScoreOptLog log = new ScoreOptLog();
        log.setTotalScore(scoreInfo.getScoreTotal());//操作前，总积分
        log.setBalanceScore(scoreInfo.getScoreBalance());//操作前，可用积分
        log.setFreezeScore(scoreInfo.getScoreFrozen());//操作前，冻结积分
        log.setUsedScore(scoreInfo.getScoreUsed());//操作前，已使用积分
        log.setScore(req.getBackScore());//本次操作的积分
        log.setStaffId(req.getStaffId());//用户ID
        log.setRemark("订单退货操作回退，解冻之前冻结的积分");//备注
        log.setOrderId(req.getOrderId());//订单编码
        log.setOptType(StaffConstants.Score.SCORE_OPT_TYPE_3);//操作类型
        log.setCreateStaff(String.valueOf(req.getStaffId()));//操作人
        log.setSiteId(req.getSiteId());//站点id
        scoreInfoSV.saveScoreOptLog(log);
        
        /*2、更新用户的积分账户，把积分解冻*/
        
        if (scoreInfo != null) {
            BigDecimal balance = new BigDecimal(scoreInfo.getScoreBalance());
            BigDecimal freeze = new BigDecimal(scoreInfo.getScoreFrozen());
            freeze = freeze.subtract(new BigDecimal(req.getBackScore()));//冻结积分减少
            balance = balance.add(new BigDecimal(req.getBackScore()));//可用积分增加
            scoreInfo.setScoreBalance(balance.longValue());
            scoreInfo.setScoreFrozen(freeze.longValue());
            scoreInfoSV.updateScoreInfo(scoreInfo);
        }
		return true;
	}
}

