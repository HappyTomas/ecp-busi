package com.zengshi.ecp.prom.service.busi.valid.impl;

import java.sql.Timestamp;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.valid.interfaces.IPromValidSV;
import com.zengshi.ecp.prom.service.util.PromUtil;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromInfoValidSVImpl extends GeneralSQLSVImpl implements IPromValidSV {

    private static final String MODULE = PromInfoValidSVImpl.class.getName();

    private boolean needToVerified;

    public boolean isNeedToVerified() {
        return needToVerified;
    }

    /**
     * xml 配置设置值为true false
     * 
     * @param needToVerified
     * @author huangjx
     */
    public void setNeedToVerified(boolean needToVerified) {
        this.needToVerified = needToVerified;
    }

    /**
     * TODO 促销信息录入-促销基本信息，是否需要验证
     * 
     * @see com.zengshi.ecp.prom.service.busi.valid.interfaces.IPromValidSV#needToVerified(com.zengshi.ecp.prom.dubbo.dto.PromDTO)
     * @param promDTO
     * @return
     * @throws Exception
     * @author huangjx
     */
    @Override
    public boolean needToVerified(PromDTO promDTO) throws BusinessException {
        return needToVerified;
    }

    /**
     * TODO 促销基本信息保存--验证
     * 
     * @see com.zengshi.ecp.prom.service.busi.valid.interfaces.IPromValidSV#valid(com.zengshi.ecp.prom.dubbo.dto.PromDTO)
     * @param promDTO
     * @throws Exception
     * @author huangjx
     */
    @Override
    public void valid(PromDTO promDTO) throws BusinessException {
        // 验证前台传入参数正确性
        if (StringUtils.isEmpty(promDTO.getPromTheme())) {
            // throw new Exception("促销主题不能为空！");
            throw new BusinessException("prom.400034");
        }
        if (promDTO.getPromTheme().length() > 60) {
            // throw new Exception("促销主题不能超过60个字符！");
            throw new BusinessException("prom.400033");
        }
        if (StringUtils.isEmpty(promDTO.getPromTypeShow())) {
            // throw new Exception("展示类型不能为空！");
            throw new BusinessException("prom.400032");
        }
        if (promDTO.getPriority() != null && promDTO.getPriority().compareTo(new Long(0)) < 0) {
            // throw new Exception("优先级不能为空！");
            throw new BusinessException("prom.400031");
        }
        if (promDTO.getStartTime() == null) {
            // throw new Exception("开始时间不能为空！");
            throw new BusinessException("prom.400030");
        }
      
        if (promDTO.getEndTime() == null) { // throw new Exception("结束时间不能为空！");
            throw new BusinessException("prom.400029");
        }

        if (new Timestamp(promDTO.getEndTime().getTime()).compareTo(new Timestamp(promDTO
                .getStartTime().getTime())) <= 0) { // throw new Exception("结束时间早于开始时间！");
            throw new BusinessException("prom.400028");
        }
        //验证必填项目
        if (PromUtil.matchSysCfgValue(PromConstants.PromSys.PROM_TYPE_HIDE_SHOW_TIME,promDTO.getPromTypeCode())!=true){
            if (promDTO.getShowStartTime() == null) { // throw new Exception("展示开始时间不能为空！");
                throw new BusinessException("prom.400027");
            }
            if (promDTO.getShowEndTime() == null) { // throw new Exception("展示结束时间不能为空！");
                throw new BusinessException("prom.400026");
            }
            if (new Timestamp(promDTO.getShowStartTime().getTime()).compareTo(new Timestamp(promDTO
                    .getStartTime().getTime())) > 0) {
                // throw new Exception("展示开始时间晚于促销开始时间！");
                throw new BusinessException("prom.400024");
            }
            if (new Timestamp(promDTO.getShowEndTime().getTime()).compareTo(new Timestamp(promDTO
                    .getEndTime().getTime())) < 0) {
                // throw new Exception("展示结束时间早于促销结束时间！");
                throw new BusinessException("prom.400023");
            }
            if (new Timestamp(promDTO.getShowEndTime().getTime()).compareTo(new Timestamp(promDTO
                    .getShowStartTime().getTime())) <= 0) { // throw new Exception("展示结束时间早于展示开始时间！");
                throw new BusinessException("prom.400025");
            }

        }
        if (StringUtils.isEmpty(promDTO.getPromContent())) {
            // throw new Exception("促销内容不能为空！");
            throw new BusinessException("prom.400022");
        }
        // 页面信息验证（单品必需选择）
        if (StringUtils.isNotEmpty(promDTO.getJoinRange()) && promDTO.getJoinRange().equals(PromConstants.PromInfo.JOIN_RANGE_0)) {
            // 部分商品参与（单品选择默认部分商品参与 jsonRange==0,订单类型人工页面选择）
            validGds(promDTO);
        }
        // 订单类型促销--商品范围 全场参与
        if (StringUtils.isNotEmpty(promDTO.getIfBlacklist())
                && promDTO.getIfBlacklist().equals(PromConstants.PromInfo.IF_BLACKLIST_1)) {
            // 选择黑名单
            validBlackList(promDTO);
        }
    }

    /**
     * 促销基本信息验证 --是否选择商品 分类
     * 
     * @param promDTO
     * @throws Exception
     * @author huangjx
     */
    private void validGds(PromDTO promDTO) throws BusinessException {
        
        //例外排除
   /*     if(PromConstants.PromType.PROM_TYPE_CODE_1000000013.equals(promDTO.getPromTypeCode())){
            return ;
        }
        if(PromConstants.PromType.PROM_TYPE_CODE_1000000014.equals(promDTO.getPromTypeCode())){
            return ;
        }*/
        if (PromUtil.matchSysCfgValue(PromConstants.PromSys.PROM_TYPE_SYS_CFG_KEY,promDTO.getPromTypeCode())){
            return ;
        }
        //其他类型必填
    /*    if (CollectionUtils.isEmpty(promDTO.getSkuList()) && CollectionUtils.isEmpty(promDTO.getCatgList()) ) {
            throw new BusinessException("prom.400152");
        }*/
       /* if (CollectionUtils.isEmpty(promDTO.getSkuList())) {
            // throw new Exception("请选择参与促销商品！");
            throw new BusinessException("prom.400021");
        }*/
    }

    /**
     * 促销基本信息验证 --是否设置黑名单 商品 或者分类
     * 
     * @param promDTO
     * @throws Exception
     * @author huangjx
     */
    private void validBlackList(PromDTO promDTO) throws BusinessException {
        
        if (CollectionUtils.isEmpty(promDTO.getBlackSkuList()) && CollectionUtils.isEmpty(promDTO.getCatgLimitList())) {
            throw new BusinessException("prom.400153");
        }
       /* if (CollectionUtils.isEmpty(promDTO.getBlackSkuList())) {
            // throw new Exception("请设置黑名单商品！");
            throw new BusinessException("prom.400020");
        }*/
    }
}
