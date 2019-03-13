package com.zengshi.ecp.staff.dubbo.interfaces;

import com.zengshi.ecp.general.order.dto.PayQuartzInfoRequest;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreClearReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreDetailSelReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreDetailSelResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreExchangeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreExchangeResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreResultResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreSignCheckRespDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreSourceReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreSourceResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreTypeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreTypeResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description:dubbo层 积分信息服务接口 <br>
 * Date:2015-8-26下午8:32:23  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public interface IScoreInfoRSV {

    /**
     * 
     * scoreUse:(积分使用方法). <br/> 
     * 包括：1、插入积分消费记录
     *     2、插入积分操作日志记录
     *     3、更新用户积分账户
     * @author huangxl 
     * @param req
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void scoreUse(ScoreExchangeReqDTO req) throws BusinessException;
    
    
    
    
    /**
     * 
     * updateScoreInfo:(更新积分账户信息). <br/> 
     * 
     * @author huangxl 
     * @param req
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void updateScoreInfo(ScoreInfoReqDTO req) throws BusinessException;
    
    /**
     * 
     * listScoreInfo:(查询积分列表). <br/> 
     * 
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public PageResponseDTO<ScoreInfoResDTO> listScoreInfo(ScoreInfoReqDTO req) throws BusinessException;

    
    /**
     * 
     * saveScoreAdd:(积分增加的方法). <br/> 
     * 
     * @author huangxl 
     * @param req
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void saveScoreAdd(ScoreSourceReqDTO req) throws BusinessException;
    /**
     * 
     * caclScore:(积分计算接口). <br/> 
     * 
     * @author PJieWin 
     * @param pSourceType //积分来源行为类型
     * @param pCustInfo //客户信息
     * @param pOrderInfo//订单信息
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public ScoreResultResDTO caclScore(PayQuartzInfoRequest pOrderInfo)throws BusinessException;
    
    /**
     * 
     * findScoreInfoByStaffId:(根据用户ID，找出积分账户记录). <br/> 
     * 
     * @author PJieWin 
     * @param pStaffId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public ScoreInfoResDTO findScoreInfoByStaffId(Long pStaffId) throws BusinessException;
    
    /**
     * 
     * queryScoreTypeList:(查询积分类型列表). <br/> 
     * 
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public PageResponseDTO<ScoreTypeResDTO> queryScoreTypeList(ScoreTypeReqDTO req) throws BusinessException;

    /**
     * 
     * listScoreInfo:(查询积分来源明细列表). <br/> 
     * 
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public PageResponseDTO<ScoreSourceResDTO> listScoreSource(ScoreSourceReqDTO req) throws BusinessException;
    
    /**
     * 
     * listScoreInfo:(查询积分消费明细列表). <br/> 
     * 
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public PageResponseDTO<ScoreExchangeResDTO> listScoreExchange(ScoreExchangeReqDTO req) throws BusinessException;
    

    /**
     * 
     * sumScoreSourceExample:(根据条件，汇总积分). <br/> 
     * 
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public long sumScoreSourceExample(ScoreSourceReqDTO req) throws BusinessException;
    
    /**
     * 
     * sumScoreExchangeExample:(根据条件，汇总积分). <br/> 
     * 
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public long sumScoreExchangeExample(ScoreExchangeReqDTO req) throws BusinessException;
    
    /**
     * 
     * saveScoreClear:(失效积分定时清零方法). <br/> 
     * 参数：1、staff_id        用户id
     *     2、dead_line_time  到期时间（取的是小于该时间的数据）
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public boolean saveScoreClear(ScoreClearReqDTO req) throws BusinessException;
    
    /**
     * 
     * listScoreDetail:(查询积分明细列表). <br/> 
     * 包括积分来源+积分消费，混合在一起展现
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public PageResponseDTO<ScoreDetailSelResDTO> listScoreDetail(ScoreDetailSelReqDTO req) throws BusinessException;
    
    /**
     * 
     * sumScoreByExample:(根据条件统计积分). <br/> 
     * 
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public ScoreDetailSelResDTO sumScoreByExample(ScoreDetailSelReqDTO req) throws BusinessException;

    
    /**
     * 
     * listScoreDetailForApp:(查询积分明细列表). <br/> 
     * 包括积分来源+积分消费，混合在一起展现
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public PageResponseDTO<ScoreDetailSelResDTO> listScoreDetailForApp(ScoreDetailSelReqDTO req) throws BusinessException;
    
    /**
     * 
     * updateScoreInfo:(更新积分账户信息). <br/> 
     * 
     * @author PJieWin 
     * @param custInfo
     * @param scoreInfo
     * @param scoreResult
     * @param orderInfo
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void updateScoreInfo(CustInfoReqDTO custInfo, ScoreInfoReqDTO scoreInfo, ScoreResultResDTO scoreResult) throws BusinessException ;

    /**
     * 
     * findScoreInfoByStaffId:(根据用户ID，找出积分账户记录). <br/> 
     * 如果用户没有积分帐户，则创建一个
     * @author PJieWin 
     * @param pStaffId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public ScoreInfoResDTO findScoreInfoByCustAndCreate(CustInfoReqDTO req) throws BusinessException;
    
    /**
     * 
     * findScoreSignByStaffId:(查询用户连续签到记录). <br/> 
     * 
     * @author huangxl5 
     * @param staffId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public ScoreSignCheckRespDTO findScoreSignByStaffId(Long staffId) throws BusinessException;
    /**
     * 
     * signAlready:(判断是否已签到，并返回签到积分信息). <br/> 
     * 
     * @author PJieWin 
     * @param staffId
     * @return 
     * @since JDK 1.6
     */
    public ScoreSourceResDTO signAlready(Long staffId) ;
}

