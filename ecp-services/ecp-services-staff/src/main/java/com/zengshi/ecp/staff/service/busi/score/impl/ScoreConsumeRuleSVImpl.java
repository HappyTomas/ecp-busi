package com.zengshi.ecp.staff.service.busi.score.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dao.mapper.busi.ScoreConsumeRuleMapper;
import com.zengshi.ecp.staff.dao.model.ScoreConsumeRule;
import com.zengshi.ecp.staff.dao.model.ScoreConsumeRuleCriteria;
import com.zengshi.ecp.staff.dao.model.ScoreConsumeRuleCriteria.Criteria;
import com.zengshi.ecp.staff.dubbo.dto.ScoreConsumeRuleReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreConsumeRuleResDTO;
import com.zengshi.ecp.staff.service.busi.score.interfaces.IScoreConsumeRuleSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.db.sequence.Sequence;

public class ScoreConsumeRuleSVImpl extends GeneralSQLSVImpl implements IScoreConsumeRuleSV{

    
    @Resource
    private ScoreConsumeRuleMapper consumeRuleMapper;
    
    @Resource(name = "seq_score_consume_rule_id")
    private Sequence seq_score_consume_rule_id;
    
    @Override
    public PageResponseDTO<ScoreConsumeRuleResDTO> listGdsScoreRule(ScoreConsumeRuleReqDTO req)
            throws BusinessException {
        PageResponseDTO<ScoreConsumeRuleResDTO> pageInfo = new PageResponseDTO<ScoreConsumeRuleResDTO>();
        pageInfo.setPageNo(req.getPageNo());
        pageInfo.setPageSize(req.getPageSize());
        pageInfo.setCount(0);//初始化记录为0
        pageInfo.setResult(null);//初始传空值
        ScoreConsumeRuleCriteria criteria = new ScoreConsumeRuleCriteria();
        Criteria sql = criteria.createCriteria();
        //条件：所需积分区间
        if (req.getScoreNeedTo() != 0L) {
            sql.andScoreNeedBetween(req.getScoreNeedFrom(), req.getScoreNeedTo());
        }
        //条件：所需金额区间
        if (req.getMoneyNeedTo() != 0L) {
            sql.andMoneyNeedBetween(req.getMoneyNeedFrom(), req.getMoneyNeedTo());
        }
        //条件：商品ID
        if (req.getGdsId() != 0L) {
            sql.andGdsIdEqualTo(req.getGdsId());
        }
        //条件：单品ID
        if (req.getSkuId() != 0L) {
            sql.andSkuIdEqualTo(req.getSkuId());
        }
        //条件：状态为有效：1
        sql.andStatusEqualTo("1");
        criteria.setLimitClauseStart(pageInfo.getStartRowIndex());
        criteria.setLimitClauseCount(pageInfo.getPageSize());
        criteria.setOrderByClause(" MONEY_NEED asc ");//根据所需金额升序
        pageInfo = super.queryByPagination(req, criteria, true, new PaginationCallback<ScoreConsumeRule, ScoreConsumeRuleResDTO>() {

            @Override
            public List<ScoreConsumeRule> queryDB(BaseCriteria bc) {
                return consumeRuleMapper.selectByExample((ScoreConsumeRuleCriteria)bc);
            }

            @Override
            public long queryTotal(BaseCriteria bc) {
                return consumeRuleMapper.countByExample((ScoreConsumeRuleCriteria)bc);
            }
            
            @Override
            public List<Comparator<ScoreConsumeRule>> defineComparators() {
                List<Comparator<ScoreConsumeRule>> ls=new ArrayList<Comparator<ScoreConsumeRule>>();
                ls.add(new Comparator<ScoreConsumeRule>(){

                    @Override
                    public int compare(ScoreConsumeRule o1, ScoreConsumeRule o2) {
                        return o1.getCreateTime().getTime()<o2.getCreateTime().getTime()?1:-1;
                    }
                    
                });
                return ls;
            }

            @Override
            public ScoreConsumeRuleResDTO warpReturnObject(ScoreConsumeRule scr) {
                ScoreConsumeRuleResDTO dto = new ScoreConsumeRuleResDTO();
                ObjectCopyUtil.copyObjValue(scr, dto, null, true);
                return dto;
            }
        });
        return pageInfo;
    }

    @Override
    public ScoreConsumeRuleResDTO findSkuScoreRule(ScoreConsumeRuleReqDTO req) throws BusinessException {
        ScoreConsumeRuleCriteria criteria = new ScoreConsumeRuleCriteria();
        Criteria sql = criteria.createCriteria();
        sql.andSkuIdEqualTo(req.getSkuId());
        List<ScoreConsumeRule> resultList = consumeRuleMapper.selectByExample(criteria);
        if (!CollectionUtils.isEmpty(resultList)) {
            ScoreConsumeRuleResDTO dto = new ScoreConsumeRuleResDTO();
            ScoreConsumeRule rule = resultList.get(0);
            ObjectCopyUtil.copyObjValue(rule, dto, null, true);
            return dto;
        }
        return null;
    }

    @Override
    public int saveSkuScoreRule(ScoreConsumeRuleReqDTO pRuleReqDTO) throws BusinessException {
        if(pRuleReqDTO == null)
        {
            return -1;
        }
        ScoreConsumeRule record = new ScoreConsumeRule();
        
        ObjectCopyUtil.copyObjValue(pRuleReqDTO, record, null, false);
        record.setId(seq_score_consume_rule_id.nextValue());
        try {
            consumeRuleMapper.insertSelective(record);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        return 1;
    }

    @Override
    public int updateSkuStatus(Long pRuleId, String pStatus) throws BusinessException {
        if(pRuleId == null)
        {
            return -1;
        }
        ScoreConsumeRule rule = new ScoreConsumeRule();
        rule.setId(pRuleId);
        rule.setStatus(pStatus);
        try {
            consumeRuleMapper.updateByPrimaryKeySelective(rule);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        return 1;
    }
}

