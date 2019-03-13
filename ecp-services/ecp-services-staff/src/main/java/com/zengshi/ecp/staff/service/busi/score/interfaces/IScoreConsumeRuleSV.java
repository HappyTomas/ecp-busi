package com.zengshi.ecp.staff.service.busi.score.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ScoreConsumeRuleReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreConsumeRuleResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 积分消费规则接口<br>
 * Date:2015-9-30下午2:47:13  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public interface IScoreConsumeRuleSV {

    /**
     * 
     * listGdsScoreRule:(获取积分商城商品列表). <br/> 
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
     * saveSkuScoreRule:(保存积分消费规则). <br/> 
     * 
     * @author PJieWin 
     * @param pRuleReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int saveSkuScoreRule(ScoreConsumeRuleReqDTO pRuleReqDTO)throws BusinessException;
    
    /**
     * 
     * updateSkuStatus:(更新积分消费规则状态). <br/> 
     * 
     * @author PJieWin 
     * @param pStatus
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int updateSkuStatus(Long pRuleId, String pStatus) throws BusinessException;
}

