package com.zengshi.ecp.prom.service.busi.constraint.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import com.zengshi.ecp.prom.dubbo.dto.PromConstraintDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.constraint.interfaces.IPromConstraintCheckSV;
import com.zengshi.ecp.prom.service.busi.constraint.interfaces.IPromConstraintSV;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromConstraintSVImpl extends GeneralSQLSVImpl implements IPromConstraintSV {

    private static final String MODULE = PromConstraintSVImpl.class.getName();

    @Resource
    private ArrayList<IPromConstraintCheckSV> iPromCheckSVList;
    
    @Resource
    private ArrayList<IPromConstraintCheckSV> iPromCheckSVSolrList;

    @Resource
    private IPromConstraintCheckSV channelCheckSV;
    
    @Resource(name = "defaultCheckSV")
    private IPromConstraintCheckSV defaultCheckSV;

    private boolean anonymousCheck(Long promId, PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException {
        boolean checkValue = false;
        //匿名用户
        if(StringUtil.isEmpty(promRuleCheckDTO.getStaffId()) || "0".equals(promRuleCheckDTO.getStaffId())){
            String param=null;
            BaseSysCfgRespDTO  sysDTO=SysCfgUtil.fetchSysCfg(PromConstants.PromSys.PROM_RULE_ANONYMOUS_CHECK);
            //无此配置 默认0
            if(sysDTO!=null && !StringUtil.isEmpty(sysDTO.getParaValue())){
                param=sysDTO.getParaValue();
            }
            //参数配置0 表示 不需要验证
            if("0".equals(param)){
                checkValue=false;
            }else{
                checkValue=true;
            }
        }else{
            //非匿名 必须验证
            checkValue=true;
        }
        return checkValue;
    }
    /**
     * TODO能否参与促销，验证
     * 
     * @see com.zengshi.ecp.prom.service.busi.constraint.interfaces.IPromConstraintSV#check(java.lang.String,
     *      com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO)
     * @param promId
     * @param promRuleCheckDTO
     * @return
     * @throws Exception
     * @author huangjx
     */
    public boolean check(Long promId, PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException {
        
        // 默认检核失败 false
        boolean checkValue = false;
        
        PromConstraintDTO promConstaintDTO = new PromConstraintDTO();
        promConstaintDTO = defaultCheckSV.getPromConstaintInfo(promId);// 促销规则
        
        //来源渠道验证
        if(channelCheckSV.isCheck(promConstaintDTO)){
            // 返回checkValue==true 表示满足规则 可以购买
            checkValue= channelCheckSV.check(promConstaintDTO, promRuleCheckDTO);
            
            if(!checkValue){
                return false;
            }
        }
        //匿名用户
        if (!anonymousCheck(promId,promRuleCheckDTO)){
            return true;
        }
     
        if (null != iPromCheckSVList) {
            
            // 获得促销配置信息
            if (promConstaintDTO != null) {
                
                    for (IPromConstraintCheckSV iPromCheckSV : iPromCheckSVList) {
                        
                        if (iPromCheckSV.isCheck(promConstaintDTO)) {
                            
                            checkValue = iPromCheckSV.check(promConstaintDTO, promRuleCheckDTO);
                            // 返回checkValue==true 表示满足规则 可以购买
                            if (!checkValue) {
                                break;
                            }
                            
                        } else {
                            // 不需要验证 表示没有限制
                            checkValue = true;
                        }
                        
                    }
                
            } else {
                // 促销配置规则为空 表示没有限制 可以购买
                checkValue = true;
            }
            
        }
        return checkValue;
    }
    
    /**
     * TODO能否参与促销，验证
     * @see com.zengshi.ecp.prom.service.busi.constraint.interfaces.IPromConstraintSV#checkSolr(java.lang.Long, com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO)
     * @param promId
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public boolean checkSolr(Long promId, PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException {
        
        // 默认检核失败 false
        boolean checkValue = false;
        
        PromConstraintDTO promConstaintDTO = new PromConstraintDTO();
        promConstaintDTO = defaultCheckSV.getPromConstaintInfo(promId);// 促销规则
        
        if (null != iPromCheckSVSolrList) {
            
            // 获得促销配置信息
            if (promConstaintDTO != null) {
                
                    for (IPromConstraintCheckSV iPromCheckSV : iPromCheckSVSolrList) {
                        
                        if (iPromCheckSV.isCheck(promConstaintDTO)) {
                            
                            checkValue = iPromCheckSV.check(promConstaintDTO, promRuleCheckDTO);
                            // 返回checkValue==true 表示满足规则 可以购买
                            if (!checkValue) {
                                break;
                            }
                            
                        } else {
                            // 不需要验证 表示没有限制
                            checkValue = true;
                        }
                        
                    }
                
            } else {
                // 促销配置规则为空 表示没有限制 可以购买
                checkValue = true;
            }
            
        }
        return checkValue;
    }
}
