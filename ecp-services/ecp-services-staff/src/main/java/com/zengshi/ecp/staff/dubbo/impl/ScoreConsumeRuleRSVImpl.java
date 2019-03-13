package com.zengshi.ecp.staff.dubbo.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ScoreConsumeRuleReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreConsumeRuleResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IScoreConsumeRuleRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.score.interfaces.IScoreConsumeRuleSV;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.paas.utils.ThreadId;

public class ScoreConsumeRuleRSVImpl implements IScoreConsumeRuleRSV{

    @Resource
    private IScoreConsumeRuleSV scoreConsumeRuleSV;
    
    private static Logger logger = LoggerFactory.getLogger(ScoreInfoRSVImpl.class);

    @Override
    public ScoreConsumeRuleResDTO findSkuScoreRule(ScoreConsumeRuleReqDTO req) throws BusinessException {
        ScoreConsumeRuleResDTO dto = new ScoreConsumeRuleResDTO();
        try {
            dto = scoreConsumeRuleSV.findSkuScoreRule(req);
        } catch (Exception e) {
            logger.error("ThreadId:["+ThreadId.getThreadId()+"];积分查询异常：", e);
            throw new BusinessException("积分查询异常",new String[]{"T_SCORE_CONSUME_RULE"});
        }
        return dto;
    }

    @Override
    public PageResponseDTO<ScoreConsumeRuleResDTO> listGdsScoreRule(ScoreConsumeRuleReqDTO req)
            throws BusinessException {
        PageResponseDTO<ScoreConsumeRuleResDTO> page = new PageResponseDTO<ScoreConsumeRuleResDTO>();
        try {
            page = scoreConsumeRuleSV.listGdsScoreRule(req);
        } catch (Exception e) {
            logger.error("ThreadId:["+ThreadId.getThreadId()+"];商品积分配置列表查询异常：", e);
            throw new BusinessException("商品积分配置列表查询异常",new String[]{"T_SCORE_CONSUME_RULE"});
        }
        return page;
    }

    @Override
    public int saceSkuScoreRule(ScoreConsumeRuleReqDTO pDto) throws BusinessException {
        
        try {
            scoreConsumeRuleSV.saveSkuScoreRule(pDto);
        } catch (Exception e) {
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR, new String[]{pDto.toString()});
        }
        return 1;
    }

    @Override
    public int updateSkuStatus(Long pRuleId, String pStatus) throws BusinessException {
        if (pRuleId == 0L) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"ID"});
        }
        if (StringUtil.isBlank(pStatus)) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"状态"});
        }
        try {
            scoreConsumeRuleSV.updateSkuStatus(pRuleId, pStatus);
        } catch (Exception e) {
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR, new String[]{pRuleId.toString()});
        }
        return 1;
    }
}

