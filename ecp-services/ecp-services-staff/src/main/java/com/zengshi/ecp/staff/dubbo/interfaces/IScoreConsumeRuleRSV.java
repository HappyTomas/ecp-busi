package com.zengshi.ecp.staff.dubbo.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ScoreConsumeRuleReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreConsumeRuleResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 积分消费规则rsv<br>
 * Date:2015-9-30下午2:57:05  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public interface IScoreConsumeRuleRSV {

    /**
     * 
     * findSpuScoreRule:(根据单品ID获取所需积分与金额). <br/> 
     * 
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public ScoreConsumeRuleResDTO findSkuScoreRule(ScoreConsumeRuleReqDTO req) throws BusinessException;
    
    /**
     * 
     * listGdsScoreRule:(查询积分商城列表). <br/> 
     * 
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public PageResponseDTO<ScoreConsumeRuleResDTO> listGdsScoreRule(ScoreConsumeRuleReqDTO req) throws BusinessException;
    
    /**
     * 
     * saceSkuScoreRule:(保存积分消费规则信息). <br/> 
     * 
     * @author PJieWin 
     * @param pDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int saceSkuScoreRule(ScoreConsumeRuleReqDTO pDto) throws BusinessException;
    
    /**
     * 
     * updateSkuStatus:(更新积分消费规则状态). <br/> 
     * 
     * @author PJieWin 
     * @param pRuleId
     * @param pStatus
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int updateSkuStatus(Long pRuleId, String pStatus) throws BusinessException;
}

