package com.zengshi.ecp.staff.service.busi.score.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dao.model.ScoreExchange;
import com.zengshi.ecp.staff.dao.model.ScoreInfo;
import com.zengshi.ecp.staff.dao.model.ScoreOptLog;
import com.zengshi.ecp.staff.dao.model.ScoreSource;
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

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015-8-6下午3:00:53  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.6
 * 
 * 积分信息相关操作服务类
 */
public interface IScoreInfoSV {

    /**
     * 
     * saveScoreInfo:(用户积分账户信息保存). <br/> 
     * @author huangxl 
     * @param scoreInfo 
     * @since JDK 1.7
     */
    public void saveScoreInfo (ScoreInfo scoreInfo) throws BusinessException;
    
    /**
     * 
     * findScoreInfoById:(根据积分信息id找出积分账户记录). <br/> 
     * @author Administrator 
     * @param id
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public ScoreInfo findScoreInfoById(Long id) throws BusinessException;
    
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
    public ScoreInfo findScoreInfoByStaffId(Long pStaffId) throws BusinessException;
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
     * updateScoreInfo:(更新积分信息记录). <br/> 
     * service服务
     * 
     * @author PJieWin 
     * @param pScoreInfo
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void updateScoreInfo(ScoreInfo pScoreInfo) throws BusinessException ;
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
    public void updateScoreInfo(CustInfo custInfo, ScoreInfo scoreInfo, ScoreResultResDTO scoreResult) throws BusinessException ;

    
    /**
     * 
     * findScoreInfoByCustInfo:(根据用户信息，找出用户积分账户记录). <br/> 
     * service服务
     * 
     * @author PJieWin 
     * @param pCustInfo
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public ScoreInfo findScoreInfoByCustInfo(CustInfo pCustInfo) throws BusinessException ;


    /**
     * 
     * saveScoreOptLog:(保存积分操作日志). <br/> 
     * 
     * @author huangxl 
     * @param log
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int saveScoreOptLog(ScoreOptLog log) throws BusinessException;
    
    /**
     * 
     * saveScoreOptLog:(保存积分操作日志). <br/> 
     * 
     * @author PJieWin 
     * @param custInfo
     * @param scoreInfo
     * @param scoreResult
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int saveScoreOptLog(CustInfo custInfo, ScoreInfo scoreInfo, ScoreResultResDTO scoreResult) throws BusinessException;

    /**
     * 
     * saveScoreSource:(保存积分来源明细表). <br/> 
     * 
     * @author PJieWin 
     * @param scoreSource
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void saveScoreSource(ScoreSource scoreSource) throws BusinessException;
    /**
     * 
     * saveScoreSource:(保存积分来源明细表). <br/> 
     * 
     * @author PJieWin 
     * @param custInfo
     * @param scoreInfo
     * @param scoreResult
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void saveScoreSource(CustInfo custInfo, ScoreInfo scoreInfo, ScoreResultResDTO scoreResult) throws BusinessException;


    /**
     * 
     * saveScoreExchange:(保存积分消费记录). <br/> 
     * 
     * @author huangxl 
     * @param exchange
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int saveScoreExchange(ScoreExchange exchange) throws BusinessException;
    
    /**
     * 
     * saveScoreUse:(积分消费方法). <br/> 
     * 包括：1、积分账户变更 
     *     2、积分消费记录新增
     *     3、积分操作日志新增
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void saveScoreUse(ScoreExchangeReqDTO req) throws BusinessException;
    
    
    
    /**
     * 
     * saveScoreAdd:(积分增加). <br/> 
     * 必要参数：staffId、sourceType、optType、score
     * 可选参数：orderId、subOrderId、remark
     * @author huangxl 
     * @param req
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void saveScoreAdd(ScoreSourceReqDTO req) throws BusinessException;
    
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
     * updateScoreSourceById:(更新积分来源表记录). <br/> 
     * 
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int updateScoreSourceById(ScoreSourceReqDTO req) throws BusinessException;
    
    /**
     * 
     * updateScoreExchangeById:(更新积分消费记录). <br/> 
     * 
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int updateScoreExchangeById(ScoreExchangeReqDTO req) throws BusinessException;
    
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
     * 与listScoreDetail的区别在于：这里要用原始的积分类型，而listScoreDetail方法取的是处理过的积分类型
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public PageResponseDTO<ScoreDetailSelResDTO> listScoreDetailForApp(ScoreDetailSelReqDTO req) throws BusinessException;
    
    /**
     * 
     * saveScoreSignCheck:(保存用户连续签到次数). <br/> 
     * 
     * @author huangxl5 
     * @param staffId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int saveScoreSignCheck(Long staffId) throws BusinessException;
    
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
    
    public ScoreSourceResDTO signAlready(Long staffId) ;
}

