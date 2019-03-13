package com.zengshi.ecp.prom.service.busi.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.prom.dao.mapper.busi.PromCalRuleMapper;
import com.zengshi.ecp.prom.dao.mapper.busi.PromConstraintMapper;
import com.zengshi.ecp.prom.dao.mapper.busi.PromGiftMapper;
import com.zengshi.ecp.prom.dao.mapper.busi.PromInfoMapper;
import com.zengshi.ecp.prom.dao.mapper.busi.PromMatchSkuMapper;
import com.zengshi.ecp.prom.dao.mapper.busi.PromSkuLimitMapper;
import com.zengshi.ecp.prom.dao.mapper.busi.PromSkuMapper;
import com.zengshi.ecp.prom.dao.mapper.busi.PromStockLimitMapper;
import com.zengshi.ecp.prom.dao.mapper.busi.manual.PromGiftManualMapper;
import com.zengshi.ecp.prom.dao.mapper.busi.manual.PromSkuManualMapper;
import com.zengshi.ecp.prom.dao.mapper.busi.manual.PromStockLimitManualMapper;
import com.zengshi.ecp.prom.dao.model.PromCalRule;
import com.zengshi.ecp.prom.dao.model.PromConstraint;
import com.zengshi.ecp.prom.dao.model.PromGift;
import com.zengshi.ecp.prom.dao.model.PromInfo;
import com.zengshi.ecp.prom.dao.model.PromInfoCriteria;
import com.zengshi.ecp.prom.dao.model.PromInfoCriteria.Criteria;
import com.zengshi.ecp.prom.dao.model.PromMatchSku;
import com.zengshi.ecp.prom.dao.model.PromMatchSkuCriteria;
import com.zengshi.ecp.prom.dao.model.PromSku;
import com.zengshi.ecp.prom.dao.model.PromSkuLimit;
import com.zengshi.ecp.prom.dao.model.PromStockLimit;
import com.zengshi.ecp.prom.dao.model.PromStockLimitCriteria;
import com.zengshi.ecp.prom.dubbo.dto.Prom2SolrReqDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromCalRuleDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromConstraintDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGiftDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGroupResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuLimitDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromStockLimitDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeResponseDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.Converter;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.group.interfaces.IPromGroupSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromCmsSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromInfoSV;
import com.zengshi.ecp.prom.service.busi.prom2solr.interfaces.IProm2SolrSV;
import com.zengshi.ecp.prom.service.common.interfaces.IPromTypeSV;
import com.zengshi.ecp.prom.service.common.msg.interfaces.IPromMsgSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import com.db.distribute.DistributeRuleAssist;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromInfoSVImpl extends GeneralSQLSVImpl implements IPromInfoSV {

    private static final String MODULE = PromInfoSVImpl.class.getName();

    // 促销提醒信息
    @Resource
    private IPromMsgSV promMsgSV;

    @Resource
    private PromInfoMapper promInfoMapper;

    @Resource
    private PromSkuMapper promSkuMapper;

    @Resource
    private PromSkuLimitMapper promSkuLimitMapper;

    @Resource
    private PromConstraintMapper promConstraintMapper;

    @Resource
    private PromCalRuleMapper promCalRuleMapper;

    @Resource
    private Converter<PromDTO, PromInfo> promInfoConverter;

    @Resource
    private Converter<PromInfo, PromInfoResponseDTO> promInfoResponseDTOConverter;

    @Resource
    private Converter<PromSkuDTO, PromSku> promSkuConverter;

    @Resource
    private Converter<PromSkuLimitDTO, PromSkuLimit> promSkuLimitConverter;

    @Resource
    private Converter<PromInfo, PromInfoDTO> promInfoDTOConverter;

    @Resource
    private Converter<PromConstraint, PromConstraintDTO> promConstraintDTOConverter;

    @Resource
    private Converter<PromCalRule, PromCalRuleDTO> promCalRuleDTOConverter;
    
    // 促销类型服务
    @Resource
    private IPromTypeSV promTypeSV;

    @Resource
    private PromSkuManualMapper promSkuManualMapper;
    
    @Resource
    private PromStockLimitMapper promStockLimitMapper;

    @Resource
    private Converter<PromStockLimitDTO, PromStockLimit> promStockLimitConverter;
    
    @Resource
    private PromStockLimitManualMapper promStockLimitManualMapper;
    
    @Resource
    private PromGiftMapper promGiftMapper;
    
    @Resource
    private Converter<PromGiftDTO, PromGift> promGiftConverter;
    
    @Resource
    private PromGiftManualMapper promGiftManualMapper;
    
    @Resource
    private IPromGroupSV promGroupSV;
    
    @Resource
    private PromMatchSkuMapper promMatchSkuMapper;
    
    @Resource
    private Converter<PromMatchSkuDTO, PromMatchSku> promMatchSkuConverter;
    
    @Resource
    private Converter<PromInfo, Prom2SolrReqDTO> prom2SolrReqDTOConverter;
    
    @Resource
    private IPromCmsSV promCmsSV;
    
    @Resource
    private IProm2SolrSV prom2SolrSV;
    
    @Resource(name = "seq_prom_info_id")
    private PaasSequence seq_prom_info_id;

    @Resource(name = "seq_prom_sku_id")
    private PaasSequence seq_prom_sku_id;

    @Resource(name = "seq_prom_sku_limit_id")
    private PaasSequence seq_prom_sku_limit_id;

    @Resource(name = "seq_prom_constraint_id")
    private PaasSequence seq_prom_constraint_id;

    @Resource(name = "seq_prom_cal_rule_id")
    private PaasSequence seq_prom_cal_rule_id;
    

    /**
     * 保存 提交 促销基本信息
     * 
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
    public PromInfo savePromInfo(PromDTO promDTO) throws BusinessException {

        if (!StringUtil.isEmpty(promDTO.getId())) {
            // 作废原来数据 后在新增
            this.editPromInfoStatusByPromId(promDTO.getId(), promDTO.getUpdateStaff(),
                    PromConstants.PromInfo.STATUS_60, promDTO.getUpdateTime());
        }
        // 促销基本表
        PromInfo promInfo = new PromInfo();
        
      //表冗余字段 建议不要从前端传入，而是根据promTypeCode获得 信息 。
        PromTypeResponseDTO promTypeResponseDTO =promTypeSV.queryPromTypeByCode(promDTO.getPromTypeCode());
        promDTO.setIfShow(promTypeResponseDTO.getIfShow());
        promDTO.setPromClass(promTypeResponseDTO.getPromClass());
        //promDTO.setSiteId(siteId);//前台传入站点数据
        promDTO.setIfComposit(promTypeResponseDTO.getIfComposit());
        
        //展示开始时间 和展示截止时间 补充数据 前台非必填 但是部分展示使用该值计算。
        if(StringUtil.isEmpty(promDTO.getShowStartTime())){
            promDTO.setShowStartTime(promDTO.getStartTime());
        }
        if(StringUtil.isEmpty(promDTO.getShowEndTime())){
            promDTO.setShowEndTime(promDTO.getEndTime());
        }
        // 促销id
        promDTO.setId(seq_prom_info_id.nextValue());
        promInfoConverter.convert(promDTO, promInfo);
        promInfoMapper.insert(promInfo);

        //insert到 t_prom_2_solr
        Prom2SolrReqDTO prom2SolrReq=new Prom2SolrReqDTO();
        prom2SolrReq=prom2SolrReqDTOConverter.convert(promInfo);
       // prom2SolrReq.setIfSendMsg("1");
        prom2SolrSV.save(prom2SolrReq);
        
        return promInfo;

    }

    /**
     * 编辑状态 促销基本信息 & 编辑商品表相关状态
     * 
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
    public PromInfo editPromInfoStatusByPromId(Long promId, Long updateStaff, String status,
            Date updateTime) throws BusinessException {

        // 更新促销主表数据
        PromInfo promInfo = new PromInfo();
        promInfo.setId(promId);
        promInfo.setUpdateStaff(updateStaff);
        if (updateTime == null) {
            promInfo.setUpdateTime(DateUtil.getSysDate());
        } else {
            promInfo.setUpdateTime(new Timestamp(updateTime.getTime()));
        }
        promInfo.setStatus(status);
        promInfoMapper.updateByPrimaryKeySelective(promInfo);

        // 更新促销商品表数据（因为有冗余状态等数据）
        // 需要重新写update t_prom_sku set ... where prom_id=? 目前自动生成代码不支持；
        PromSku promSku = new PromSku();
        promSku.setPromId(promId);
        promSku.setUpdateStaff(updateStaff);
        if (updateTime == null) {
            promSku.setUpdateTime(DateUtil.getSysDate());
        } else {
            promSku.setUpdateTime(new Timestamp(updateTime.getTime()));
        }
        promSku.setStatus(status);
        promSkuManualMapper.updateByPromIdSelective(promSku);
        //搭配表数据
        PromMatchSku promMatchSku=new PromMatchSku();
        promMatchSku.setPromId(promId);
        promMatchSku.setPromStatus(status);
        promMatchSku.setUpdateStaff(updateStaff);
        if (updateTime == null) {
            promMatchSku.setUpdateTime(DateUtil.getSysDate());
        } else {
            promMatchSku.setUpdateTime(new Timestamp(updateTime.getTime()));
        }
        PromMatchSkuCriteria example=new PromMatchSkuCriteria();
        PromMatchSkuCriteria.Criteria cr1=example.createCriteria();
        cr1.andPromIdEqualTo(promId);
        cr1.andStatusEqualTo(PromConstants.PromMatchSku.STATUS_1);
        promMatchSkuMapper.updateByExampleSelective(promMatchSku, example);
        
        //查询promInfo 促销提醒信息发送solr中间表
        PromInfo promInfo1=promInfoMapper.selectByPrimaryKey(promInfo.getId());
        promInfo1.setStatus(status);
        
        Prom2SolrReqDTO prom2SolrReq=new Prom2SolrReqDTO();
        prom2SolrReq=prom2SolrReqDTOConverter.convert(promInfo1);
        prom2SolrReq.setIfSendMsg("1");
        prom2SolrSV.save(prom2SolrReq);
        
        
        //失效 需要通知cms 把cms配置的相关数据作废掉
        if(PromConstants.PromInfo.STATUS_20.equals(status)){
             promCmsSV.invalidCmsGds(promId, null, null,null);
        }
        
        return promInfo;

    }

    /**
     * 保存 提交 单品
     * 
     * @param promDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public void savePromSku(PromDTO promDTO) throws BusinessException {
        
        //单品列表
        if (!CollectionUtils.isEmpty(promDTO.getSkuList())) {
            
            List<PromSkuDTO> promSkuDTOList = (List<PromSkuDTO>) promDTO.getSkuList();
            
            String matchType=PromConstants.PromSku.MATCH_TYPE_1;
            //如果有搭配商品 t_prom_info.if_match=1  && t_prom_sku.match_type =2
            if(!CollectionUtils.isEmpty(promDTO.getMatchSkuDTOList())){
                matchType=PromConstants.PromSku.MATCH_TYPE_2;
            } 
            // dto 转为 model
            PromSku promSku = new PromSku();
            for (PromSkuDTO promSkuDTO : promSkuDTOList) {
                promSkuDTO.setId(seq_prom_sku_id.nextValue());// 获得序列号
                promSkuDTO.setIfShow(promDTO.getIfShow());//冗余字段
                promSkuDTO.setEndTime(promDTO.getEndTime());//冗余截止时间
                promSkuDTO.setPriority(promDTO.getPriority());//冗余优先级
                promSkuDTO.setStartTime(promDTO.getStartTime());//冗余开始时间
                promSkuDTO.setStatus(promDTO.getStatus());//冗余状态
                promSkuDTO.setShopId(promDTO.getShopId());
                promSkuDTO.setPromClass(promDTO.getPromClass());
                promSkuDTO.setCreateStaff(promDTO.getCreateStaff());
                promSkuDTO.setCreateTime(promDTO.getCreateTime());
                promSkuDTO.setMatchType(matchType);
                promSkuDTO.setIfComposit(promDTO.getIfComposit());//是否叠加 冗余
                promSkuDTO.setSiteId(promDTO.getSiteId());//站点 冗余
                promSkuDTO.setJoinType(PromConstants.PromSku.JOIN_TYPE_2);//单品参与
                promSkuDTO.setShowStartTime(promDTO.getShowStartTime());
                promSkuDTO.setShowEndTime(promDTO.getShowEndTime());
                promSkuDTO.setIfValid(PromConstants.PromSku.IF_VALID_1);
                //分类编码商品编码 单品编码 前台传入
                promSku = promSkuConverter.convert(promSkuDTO);
                promSku.setPromId(promDTO.getId());
                promSkuMapper.insert(promSku);
            }
        }
      //分类列表
        this.savePromCatg(promDTO);
    }

    /**
     * 黑名单
     * 
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void savePromSkuLimt(PromDTO promDTO) throws BusinessException {
        // 黑名单表 T_PROM_SKU_LIMIT
        //商品 单品 黑名单
        if (!CollectionUtils.isEmpty(promDTO.getBlackSkuList())) {
            List<PromSkuLimitDTO> promSkuLimitDTOList = (List<PromSkuLimitDTO>) promDTO
                    .getBlackSkuList();
            // dto 转为 model
            PromSkuLimit promSkuLimit = new PromSkuLimit();
            for (PromSkuLimitDTO promSkuLimitDTO : promSkuLimitDTOList) {
                promSkuLimitDTO.setId(seq_prom_sku_limit_id.nextValue());
                promSkuLimitDTO.setCreateStaff(promDTO.getCreateStaff());
                promSkuLimitDTO.setCreateTime(promDTO.getCreateTime());
                promSkuLimitDTO.setJoinType(PromConstants.PromSku.JOIN_TYPE_2);//单品
                //商品 单品 前台传入
                promSkuLimit = promSkuLimitConverter.convert(promSkuLimitDTO);
                promSkuLimit.setPromId(promDTO.getId());// 促销ID
                promSkuLimitMapper.insert(promSkuLimit);
            }
        }
        //分类 黑名单
        if (!CollectionUtils.isEmpty(promDTO.getCatgLimitList())) {
            List<PromSkuLimitDTO> promSkuLimitDTOList = (List<PromSkuLimitDTO>) promDTO
                    .getCatgLimitList();
            // dto 转为 model
            PromSkuLimit promSkuLimit = new PromSkuLimit();
            for (PromSkuLimitDTO promSkuLimitDTO : promSkuLimitDTOList) {
                promSkuLimitDTO.setId(seq_prom_sku_limit_id.nextValue());
                promSkuLimitDTO.setCreateStaff(promDTO.getCreateStaff());
                promSkuLimitDTO.setCreateTime(promDTO.getCreateTime());
                promSkuLimitDTO.setJoinType(PromConstants.PromSku.JOIN_TYPE_1);//分类
                //分类 前台传入
                promSkuLimit = promSkuLimitConverter.convert(promSkuLimitDTO);
                promSkuLimit.setPromId(promDTO.getId());// 促销ID
                promSkuLimitMapper.insert(promSkuLimit);
            }
        }
    }

    /**
     * 保存 提交 促销规则
     * 
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void savePromConstraint(PromDTO promDTO) throws BusinessException {
        if (promDTO.getPromConstraintDTO() != null) {
            PromConstraint promConstraint = new PromConstraint();
            promConstraint.setConstraintStr(JSON.toJSONString(promDTO.getPromConstraintDTO()));
            promConstraint.setCreateStaff(promDTO.getCreateStaff());
            promConstraint.setCreateTime(new Timestamp(promDTO.getCreateTime().getTime()));
            promConstraint.setId(seq_prom_constraint_id.nextValue());
            promConstraint.setPromId(promDTO.getId());
            promConstraintMapper.insert(promConstraint);
        }

    }

    /**
     * 保存 提交 计算规则
     * 
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void savePromCalRule(PromDTO promDTO) throws BusinessException {

        // insert 基本数据
        PromCalRule promCalRule = new PromCalRule();
        promCalRule.setCalStr(promDTO.getDiscountRule());
        promCalRule.setCreateStaff(promDTO.getCreateStaff());
        promCalRule.setCreateTime(new Timestamp(promDTO.getCreateTime().getTime()));
        promCalRule.setId(seq_prom_cal_rule_id.nextValue());
        promCalRule.setPromId(promDTO.getId());
        promCalRuleMapper.insert(promCalRule);
    }

    /**
     * 促销列表 分页
     * 
     * @param promDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromInfoResponseDTO> queryPromInfoForPage(PromInfoDTO promInfoDTO,String queryFlag)
            throws BusinessException {

        PromInfoCriteria example = new PromInfoCriteria();
        // 初始化查询页数 数量
        example.setLimitClauseCount(promInfoDTO.getPageSize());
        example.setLimitClauseStart(promInfoDTO.getStartRowIndex());
        // 排序
        example.setOrderByClause("id desc");
        // 初始化查询条件
        if (StringUtil.isEmpty(queryFlag)){
            this.initPromInfoParm(promInfoDTO, null, null, example,"1");
        }else{
            this.initPromInfoParm(promInfoDTO, null, null, example,queryFlag);   
        }

        if(promInfoDTO.getTableIndex()!=null && promInfoDTO.getTableIndex()>0){
            DistributeRuleAssist.setTableIndex(promInfoDTO.getTableIndex());
        }
        // 返回查询分页结果集
        PageResponseDTO<PromInfoResponseDTO> pages= super.queryByPagination(promInfoDTO, example, true,
                new PaginationCallback<PromInfo, PromInfoResponseDTO>() {
                    // 查询记录集
                    @Override
                    public List<PromInfo> queryDB(BaseCriteria example) {

                        return promInfoMapper.selectByExample((PromInfoCriteria) example);
                    }

                    // 查询总记录数
                    @Override
                    public long queryTotal(BaseCriteria example) {

                        return promInfoMapper.countByExample((PromInfoCriteria) example);
                    }
                    // 查询结果转换
                    @Override
                    public PromInfoResponseDTO warpReturnObject(PromInfo t) {
                        return promInfoResponseDTOConverter.convert(t);
                    }
                });
        if(promInfoDTO.getTableIndex()!=null && promInfoDTO.getTableIndex()>0){
            DistributeRuleAssist.clearTableIndex();
        }
        return pages;
    }

    /**
     * 促销列表 分页
     * 
     * @param promInfoDTO
     * @param groupIdList
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromInfoResponseDTO> queryPromInfoForPage(PromInfoDTO promInfoDTO,
            List<Long> groupIds) throws BusinessException {

        // 促销主题列表为空
        if (CollectionUtils.isEmpty(groupIds)) {
            return this.listPromInfoForPage(promInfoDTO, null);
        }
        return listPromInfoForPage(promInfoDTO, groupIds);

    }

    /**
     * 促销列表
     * 
     * @param promDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromInfoResponseDTO> queryPromInfoList(PromDTO promDTO) throws BusinessException {

        PromInfoCriteria example = new PromInfoCriteria();
        // 初始化条件
        this.initPromParm(promDTO, example);
        // 查询
        List<PromInfo> promInfoList = promInfoMapper.selectByExample(example);
        if (promInfoList == null) {
            return null;
        }
        // 解析结果集并转化
        List<PromInfoResponseDTO> reList = new ArrayList<PromInfoResponseDTO>();
        for (PromInfo promInfo : promInfoList) {
            reList.add(promInfoResponseDTOConverter.convert(promInfo));
        }
        return reList;
    }

    /**
     * 促销信息
     * 
     * @param id
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromInfoResponseDTO queryPromInfoResponseDTOById(Long id) throws BusinessException {

        PromInfo promInfo = promInfoMapper.selectByPrimaryKey(id);

        if (promInfo == null) {
            return null;
        }
        return promInfoResponseDTOConverter.convert(promInfo);
    }

    
    /**
     * 促销信息
     * 
     * @param id
     * @return
     * @throws BusinessException
     * @author lisp
     */
    @Override
    public PromInfoResponseDTO selectPromInfoResponseDTOById(Long id) throws BusinessException {

        PromInfo promInfo = promInfoMapper.selectByPrimaryKey(id);

        if (promInfo == null) {
            return null;
        }
        PromInfoResponseDTO promInfoResponseDTO = promInfoResponseDTOConverter.convert(promInfo);
        Long times = DateUtil.getCurrentTimeMillis();
        if(promInfoResponseDTO.getStartTime().getTime()<times){
        	if(promInfoResponseDTO.getEndTime().getTime()>times){
        		promInfoResponseDTO.setIfStart("1");
        	}else{
        		promInfoResponseDTO.setIfStart("2");
        	}
        }
        return promInfoResponseDTO;
    }
    
    
    /**
     * 促销信息
     * 
     * @param id
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromInfoDTO queryPromInfoDTOById(Long id) throws BusinessException {
        
        PromInfoDTO dto=new PromInfoDTO();
        
        PromInfo promInfo=promInfoMapper.selectByPrimaryKey(id);
        
        dto=promInfoDTOConverter.convert(promInfo,dto);
        
        if(promInfo!=null && promInfo.getGroupId()!=null){
            PromGroupResponseDTO groupDto=promGroupSV.queryPromGroupById(promInfo.getGroupId());
            if(groupDto!=null){
                dto.setGroupName(groupDto.getPromTheme());
            }
          
        }
        
        return dto;
    }

    /**
     *  查询条件 组织
     * @param promInfoDTO
     * @param groupIds  主题id列表
     * @param statuss   状态列表
     * @param example   条件
     * @param flag      1 按照 开始 <= 日期<=结束  2  日期>=结束 and  日期>=开始
     * @author huangjx
     */
    private void initPromInfoParm(PromInfoDTO promInfoDTO, List<Long> groupIds,
            List<String> statuss, PromInfoCriteria example,String flag) {

        if (promInfoDTO == null) {
            return;
        }
        Criteria cr = example.createCriteria();

        // 站点编码
        if (!StringUtil.isEmpty(promInfoDTO.getSiteId())) {
            cr.andSiteIdEqualTo(promInfoDTO.getSiteId());
        }
        // 是否叠加
        if (!StringUtil.isEmpty(promInfoDTO.getIfComposit())) {
            cr.andIfCompositEqualTo(promInfoDTO.getIfComposit());
        }
        // 状态 60 默认排除
        cr.andStatusNotEqualTo(PromConstants.PromInfo.STATUS_60);

        // 促销ID
        if (promInfoDTO.getId() != null) {
            cr.andIdEqualTo(promInfoDTO.getId());
        }
        // 促销组ID
        if (promInfoDTO.getGroupId() != null) {
            cr.andGroupIdEqualTo(promInfoDTO.getGroupId());
        }
        
        //特别处理    原因 取有效值（传入参数有传入endtime 取大于等于endtime的数据）
        if(StringUtil.isEmpty(flag) || "2".equals(flag)){
            // 传入促销截止时间 大于等于 传入date
            if (promInfoDTO.getEndTime() != null) {
                cr.andEndTimeGreaterThanOrEqualTo(new Timestamp(promInfoDTO.getEndTime().getTime()));
            }
            // 促销开始时间 大于等于 传入date
            if (promInfoDTO.getStartTime() != null) {
                cr.andStartTimeLessThanOrEqualTo(new Timestamp(promInfoDTO.getStartTime().getTime()));
            }
            // 传入促销截止展示时间 大于等于 传入date
            if (promInfoDTO.getShowEndTime() != null) {
                cr.andShowEndTimeGreaterThanOrEqualTo(new Timestamp(promInfoDTO.getShowEndTime().getTime()));
            }
            // 展示促销开始时间 小于等于 传入date
            if (promInfoDTO.getShowStartTime() != null) {
                cr.andShowStartTimeLessThanOrEqualTo(new Timestamp(promInfoDTO.getShowStartTime()
                        .getTime()));
            }
        }
        //基本上为列表分页查询
        if( "1".equals(flag)){
            //开始时间>=date
            //截止时间<=date
            // 传入促销截止时间 小于等于 传入date
            if (promInfoDTO.getEndTime() != null) {
                cr.andEndTimeLessThanOrEqualTo(new Timestamp(promInfoDTO.getEndTime().getTime()));
            }
            // 促销开始时间 大于等于 传入date
            if (promInfoDTO.getStartTime() != null) {
                cr.andStartTimeGreaterThanOrEqualTo(new Timestamp(promInfoDTO.getStartTime().getTime()));
            }
            // 展示促销截止时间 小于等于 传入date
            if (promInfoDTO.getShowEndTime() != null) {
                cr.andShowEndTimeLessThanOrEqualTo(new Timestamp(promInfoDTO.getShowEndTime()
                        .getTime()));
            }
            // 展示促销开始时间 大于等于 传入date
            if (promInfoDTO.getShowStartTime() != null) {
                cr.andShowStartTimeGreaterThanOrEqualTo(new Timestamp(promInfoDTO.getShowStartTime()
                        .getTime()));
            }
        }
        if( "3".equals(flag)){
            // 传入促销截止时间 小于等于 传入date
            if (promInfoDTO.getEndTime() != null) {
                cr.andEndTimeLessThan(new Timestamp(promInfoDTO.getEndTime().getTime()));
            }
        }
     
        // 促销类型代码 PROM_TYPE_CODE
        if (!StringUtil.isEmpty(promInfoDTO.getPromTypeCode())) {
            cr.andPromTypeCodeEqualTo(promInfoDTO.getPromTypeCode());
        }
        // 是否展示
        if (!StringUtil.isEmpty(promInfoDTO.getIfShow())) {
            cr.andIfShowEqualTo(promInfoDTO.getIfShow());
        }
        // 促销分类
        if (!StringUtil.isEmpty(promInfoDTO.getPromClass())) {
            cr.andPromClassEqualTo(promInfoDTO.getPromClass());
        }
        // 促销主题 PROM_THEME
        if (!StringUtil.isEmpty(promInfoDTO.getPromTheme())) {
            cr.andPromThemeLike(PromConstants.PromSys.LIKE_PERCENT+promInfoDTO.getPromTheme()+PromConstants.PromSys.LIKE_PERCENT);
        }
        // 促销展示类型（默认和促销类型一致）
        if (!StringUtil.isEmpty(promInfoDTO.getPromTypeShow())) {
            cr.andPromTypeShowLike(PromConstants.PromSys.LIKE_PERCENT+promInfoDTO.getPromTypeShow()+PromConstants.PromSys.LIKE_PERCENT);
        }
        // 优先级 值越低优先级越高
        if (!StringUtil.isEmpty(promInfoDTO.getPriority())) {
            cr.andPriorityGreaterThanOrEqualTo(promInfoDTO.getPriority());
        }
        // 状态
        if (!StringUtil.isEmpty(promInfoDTO.getStatus())) {
            cr.andStatusEqualTo(promInfoDTO.getStatus());
        }

        // 店铺id
        if (!StringUtil.isEmpty(promInfoDTO.getShopId())) {
            cr.andShopIdEqualTo(promInfoDTO.getShopId());
        }
        // 参与促销商品范围 0 部分商品参加 1全部商品参加 产品促销类型 默认0 2 分类参与
        if (!StringUtil.isEmpty(promInfoDTO.getJoinRange())) {
            cr.andJoinRangeEqualTo(promInfoDTO.getJoinRange());
        }
        // 是否设置黑名单 0 无 1有
        if (!StringUtil.isEmpty(promInfoDTO.getIfBlacklist())) {
            cr.andIfBlacklistEqualTo(promInfoDTO.getIfBlacklist());
        }
        // 是否有搭配商品
        if (!StringUtil.isEmpty(promInfoDTO.getIfMatch())) {
            cr.andIfMatchEqualTo(promInfoDTO.getIfMatch());
        }
        // 促销组id
        if (!CollectionUtils.isEmpty(groupIds)) {
            cr.andGroupIdIn(groupIds);
        }
        // 促销状态(促销审核 加入)
        if (!CollectionUtils.isEmpty(statuss)) {
            cr.andStatusIn(statuss);
            cr.andGroupIdIsNotNull();// groupId 非空
        }
        // 是否免邮
        if (!StringUtil.isEmpty(promInfoDTO.getIfFreePost())) {
            cr.andIfFreePostEqualTo(promInfoDTO.getIfFreePost());
        }
        // 平台审核查询
        if (PromConstants.PromSys.IF_PLAT_QUERY.equals(promInfoDTO.getIfPlatQuery())) {

            cr.andGroupIdIsNull();

            // 促销审核 查询 如果状态为空表示 需要查询如下状态
            List<String> statusList = new ArrayList<String>();
            if (StringUtil.isEmpty(promInfoDTO.getStatus())) {
                statusList.add(PromConstants.PromInfo.STATUS_10);
                statusList.add(PromConstants.PromInfo.STATUS_20);
                statusList.add(PromConstants.PromInfo.STATUS_40);
                statusList.add(PromConstants.PromInfo.STATUS_50);
                cr.andStatusIn(statusList);
            }
        }
        //失效 包括 状态失效 和截止时间到期
        //只有单状态为无效查询时 才会返回结果
        Criteria cr1 =this.initPromInfoParmByStatusInvalid(promInfoDTO, groupIds, statuss);
        if(cr1!=null){
            example.or(cr1);
        }
    }
    /**
     * 查询条件 组织
     * 
     * @param promDTO
     * @param example
     * @author huangjx
     */
    private Criteria initPromInfoParmByStatusInvalid(PromInfoDTO promInfoDTO, List<Long> groupIds,
            List<String> statuss) {

        if (promInfoDTO == null) {
            return null;
        }
        
        if (StringUtil.isEmpty(promInfoDTO.getStatus())) {
            return null;
        }
        //失效
        if (!PromConstants.PromInfo.STATUS_20.equals(promInfoDTO.getStatus())) {
            return null;
        }
        PromInfoCriteria example = new PromInfoCriteria();
        Criteria cr = example.createCriteria();

        // 站点编码
        if (!StringUtil.isEmpty(promInfoDTO.getSiteId())) {
            cr.andSiteIdEqualTo(promInfoDTO.getSiteId());
        }
        // 是否叠加
        if (!StringUtil.isEmpty(promInfoDTO.getIfComposit())) {
            cr.andIfCompositEqualTo(promInfoDTO.getIfComposit());
        }
        // 状态 60 默认排除
        cr.andStatusNotEqualTo(PromConstants.PromInfo.STATUS_60);
        
        cr.andStatusNotEqualTo(PromConstants.PromInfo.STATUS_00);

        // 促销ID
        if (promInfoDTO.getId() != null) {
            cr.andIdEqualTo(promInfoDTO.getId());
        }
        // 促销组ID
        if (promInfoDTO.getGroupId() != null) {
            cr.andGroupIdEqualTo(promInfoDTO.getGroupId());
        }
        // 促销截止时间 小于等于 date
        if (promInfoDTO.getEndTime() != null) {
            cr.andEndTimeGreaterThanOrEqualTo(new Timestamp(promInfoDTO.getEndTime().getTime()));
        }
        // 促销开始时间 大于等于 date
        if (promInfoDTO.getStartTime() != null) {
            cr.andStartTimeGreaterThanOrEqualTo(new Timestamp(promInfoDTO.getStartTime().getTime()));
        }
        // 展示促销截止时间 大于等于 date
        if (promInfoDTO.getShowEndTime() != null) {
            cr.andShowEndTimeGreaterThanOrEqualTo(new Timestamp(promInfoDTO.getShowEndTime()
                    .getTime()));
        }
        // 展示促销开始时间 小于等于 date
        if (promInfoDTO.getShowStartTime() != null) {
            cr.andShowStartTimeGreaterThanOrEqualTo(new Timestamp(promInfoDTO.getShowStartTime()
                    .getTime()));
        }
        // 促销类型代码 PROM_TYPE_CODE
        if (!StringUtil.isEmpty(promInfoDTO.getPromTypeCode())) {
            cr.andPromTypeCodeEqualTo(promInfoDTO.getPromTypeCode());
        }
        // 是否展示
        if (!StringUtil.isEmpty(promInfoDTO.getIfShow())) {
            cr.andIfShowEqualTo(promInfoDTO.getIfShow());
        }
        // 促销分类
        if (!StringUtil.isEmpty(promInfoDTO.getPromClass())) {
            cr.andPromClassEqualTo(promInfoDTO.getPromClass());
        }
        // 促销主题 PROM_THEME
        if (!StringUtil.isEmpty(promInfoDTO.getPromTheme())) {
            cr.andPromThemeLike(promInfoDTO.getPromTheme());
        }
        // 促销展示类型（默认和促销类型一致）
        if (!StringUtil.isEmpty(promInfoDTO.getPromTypeShow())) {
            cr.andPromTypeShowLike(PromConstants.PromSys.LIKE_PERCENT+promInfoDTO.getPromTypeShow()+PromConstants.PromSys.LIKE_PERCENT);
        }
        // 优先级 值越低优先级越高
        if (!StringUtil.isEmpty(promInfoDTO.getPriority())) {
            cr.andPriorityGreaterThanOrEqualTo(promInfoDTO.getPriority());
        }
        // 状态
        if (!StringUtil.isEmpty(promInfoDTO.getStatus())) {
            //失效 包括 状态失效 和截止时间到期
            if(PromConstants.PromInfo.STATUS_20.equals(promInfoDTO.getStatus())){
                cr.andEndTimeLessThanOrEqualTo(DateUtil.getSysDate());
            }
        }

        // 店铺id
        if (!StringUtil.isEmpty(promInfoDTO.getShopId())) {
            cr.andShopIdEqualTo(promInfoDTO.getShopId());
        }
        // 参与促销商品范围 0 部分商品参加 1全部商品参加 产品促销类型 默认0 2 分类参与
        if (!StringUtil.isEmpty(promInfoDTO.getJoinRange())) {
            cr.andJoinRangeEqualTo(promInfoDTO.getJoinRange());
        }
        // 是否设置黑名单 0 无 1有
        if (!StringUtil.isEmpty(promInfoDTO.getIfBlacklist())) {
            cr.andIfBlacklistEqualTo(promInfoDTO.getIfBlacklist());
        }
        // 是否有搭配商品
        if (!StringUtil.isEmpty(promInfoDTO.getIfMatch())) {
            cr.andIfMatchEqualTo(promInfoDTO.getIfMatch());
        }
        // 促销组id
        if (!CollectionUtils.isEmpty(groupIds)) {
            cr.andGroupIdIn(groupIds);
        }
        // 促销状态(促销审核 加入)
        if (!CollectionUtils.isEmpty(statuss)) {
            cr.andStatusIn(statuss);
            cr.andGroupIdIsNotNull();// groupId 非空
        }
        // 是否免邮
        if (!StringUtil.isEmpty(promInfoDTO.getIfFreePost())) {
            cr.andIfFreePostEqualTo(promInfoDTO.getIfFreePost());
        }
        // 平台审核查询
        if (PromConstants.PromSys.IF_PLAT_QUERY.equals(promInfoDTO.getIfPlatQuery())) {

            cr.andGroupIdIsNull();

            // 促销审核 查询 如果状态为空表示 需要查询如下状态
            List<String> statusList = new ArrayList<String>();
            if (StringUtil.isEmpty(promInfoDTO.getStatus())) {
                statusList.add(PromConstants.PromInfo.STATUS_10);
                statusList.add(PromConstants.PromInfo.STATUS_20);
                statusList.add(PromConstants.PromInfo.STATUS_40);
                statusList.add(PromConstants.PromInfo.STATUS_50);
                cr.andStatusIn(statusList);
            }
        }
      return cr;
    }

    /**
     * 查询条件 组织
     * 
     * @param promDTO
     * @param example
     * @author huangjx
     */
    private void initPromParm(PromDTO promDTO, PromInfoCriteria example) {
        if (promDTO == null) {
            return;
        }
        Criteria cr = example.createCriteria();
        // 促销ID
        if (promDTO.getId() != null) {
            cr.andIdEqualTo(promDTO.getId());
        }
        // 站点编码
        if (!StringUtil.isEmpty(promDTO.getSiteId())) {
            cr.andSiteIdEqualTo(promDTO.getSiteId());
        }
        // 是否叠加
        if (!StringUtil.isEmpty(promDTO.getIfComposit())) {
            cr.andIfCompositEqualTo(promDTO.getIfComposit());
        }
        
        // 促销组ID
        if (promDTO.getGroupId() != null) {
            cr.andGroupIdEqualTo(promDTO.getGroupId());
        }
        // 促销截止时间 大于等于 date
        if (promDTO.getEndTime() != null) {
            cr.andEndTimeGreaterThanOrEqualTo(new Timestamp(promDTO.getEndTime().getTime()));
        }
        // 促销开始时间 小于等于 date
        if (promDTO.getStartTime() != null) {
            cr.andStartTimeLessThanOrEqualTo(new Timestamp(promDTO.getStartTime().getTime()));
        }
        // 展示促销截止时间 大于等于 date
        if (promDTO.getShowEndTime() != null) {
            cr.andShowEndTimeGreaterThanOrEqualTo(new Timestamp(promDTO.getShowEndTime().getTime()));
        }
        // 展示促销开始时间 小于等于 date
        if (promDTO.getShowStartTime() != null) {
            cr.andShowStartTimeLessThanOrEqualTo(new Timestamp(promDTO.getShowStartTime().getTime()));
        }
        // 促销类型代码 PROM_TYPE_CODE
        if (!StringUtil.isEmpty(promDTO.getPromTypeCode())) {
            cr.andPromTypeCodeEqualTo(promDTO.getPromTypeCode());
        }
        // 是否展示
        if (!StringUtil.isEmpty(promDTO.getIfShow())) {
            cr.andIfShowEqualTo(promDTO.getIfShow());
        }
        // 促销分类
        if (!StringUtil.isEmpty(promDTO.getPromClass())) {
            cr.andPromClassEqualTo(promDTO.getPromClass());
        }
        // 促销主题 PROM_THEME
        if (!StringUtil.isEmpty(promDTO.getPromTheme())) {
            cr.andPromThemeLike(PromConstants.PromSys.LIKE_PERCENT+promDTO.getPromTheme()+PromConstants.PromSys.LIKE_PERCENT);
        }
        // 促销展示类型（默认和促销类型一致）
        if (!StringUtil.isEmpty(promDTO.getPromTypeShow())) {
            cr.andPromTypeShowLike(PromConstants.PromSys.LIKE_PERCENT+promDTO.getPromTypeShow()+PromConstants.PromSys.LIKE_PERCENT);
        }
        // 优先级 值越低优先级越高
        if (!StringUtil.isEmpty(promDTO.getPriority())) {
            cr.andPriorityGreaterThanOrEqualTo(promDTO.getPriority());
        }
        // 状态
        if (!StringUtil.isEmpty(promDTO.getStatus())) {
            cr.andStatusEqualTo(promDTO.getStatus());
        }
        // 状态 60 默认排除
        cr.andStatusNotEqualTo(PromConstants.PromInfo.STATUS_60);
        // 店铺id
        if (!StringUtil.isEmpty(promDTO.getShopId())) {
            cr.andShopIdEqualTo(promDTO.getShopId());
        }
        // 参与促销商品范围 0 部分商品参加 1全部商品参加 产品促销类型 默认0
        if (!StringUtil.isEmpty(promDTO.getJoinRange())) {
            cr.andJoinRangeEqualTo(promDTO.getJoinRange());
        }
        // 是否设置黑名单 0 无 1有
        if (!StringUtil.isEmpty(promDTO.getIfBlacklist())) {
            cr.andIfBlacklistEqualTo(promDTO.getIfBlacklist());
        }
        // 是否免邮
        if (!StringUtil.isEmpty(promDTO.getIfFreePost())) {
            cr.andIfFreePostEqualTo(promDTO.getIfFreePost());
        }
        //秒杀促销特别处理 方法:com.zengshi.ecp.prom.dubbo.impl.PromQueryRSVImpl.KillPromGdsinfoList
        if(StringUtil.isNotBlank(promDTO.getGridQuerySortName())){
        	String str = promDTO.getGridQuerySortName();
        	if(StringUtil.isNotBlank(promDTO.getGridQuerySortOrder())){
        		 str = str+" "+promDTO.getGridQuerySortOrder();
        	}
        	example.setOrderByClause(str);
        }
    }

    /**
     * 促销列表  无分页
     * @param promInfoDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromInfoResponseDTO> listPromInfo(PromInfoDTO promInfoDTO)
            throws BusinessException {

        PromInfoCriteria example = new PromInfoCriteria();
        // 初始化查询条件
        this.initPromInfoParm(promInfoDTO, null, null, example,"2");
        
        example.setOrderByClause(" ID ASC ");
        // 查询
        List<PromInfo> promInfoList = promInfoMapper.selectByExample(example);
        
        if (CollectionUtils.isEmpty(promInfoList)) {
            return null;
        }
        // 解析结果集并转化
        List<PromInfoResponseDTO> reList = new ArrayList<PromInfoResponseDTO>();
        
        for (PromInfo promInfo : promInfoList) {
            reList.add(promInfoResponseDTOConverter.convert(promInfo));
        }
        
        return reList;
    }

    /* (non-Javadoc)
     * 平台待审核促销数据统计
     * @see com.zengshi.ecp.prom.service.busi.interfaces.IPromInfoSV#queryTotalByProm(com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO)
     */
    public Long queryTotalByProm(PromInfoDTO promInfoDTO) throws BusinessException {
    	
    	if(promInfoDTO==null){
    		promInfoDTO=new PromInfoDTO();
    	}
        List<String> statuss = new ArrayList<String>();
        //状态为空 查询待审核
        if (StringUtil.isEmpty(promInfoDTO.getStatus())) {
        	  statuss.add(PromConstants.PromInfo.STATUS_40);
        }else{
        	//查询传入状态数据
        	statuss.add(promInfoDTO.getStatus());
        }
        // 初始化查询条件
        PromInfoCriteria example = new PromInfoCriteria();
        this.initPromInfoParm(promInfoDTO, null, statuss, example,"1");
        return promInfoMapper.countByExample(example);
        
       }

    
    /**
     * 显示秒杀促销列表 分页
     * 
     * @param promInfoDTO
     * @param 
     * @return
     * @throws BusinessException
     * @author lisp
     */
    @Override
    public  PageResponseDTO<PromInfoResponseDTO> listSecondPromInfoForPage(PromInfoDTO promInfoDTO) throws BusinessException {
    	// 初始化查询条件
        PromInfoCriteria example = new PromInfoCriteria();
        example.setLimitClauseCount(promInfoDTO.getPageSize());
        example.setLimitClauseStart(promInfoDTO.getStartRowIndex());
        example.setOrderByClause("start_time ASC,id ASC");
        
        Criteria cr = example.createCriteria();
        if(StringUtil.isNotBlank(promInfoDTO.getStatus())){
        	cr.andStatusEqualTo(promInfoDTO.getStatus());
        }
        if(StringUtil.isNotBlank(promInfoDTO.getPromTypeCode())){
        	
        	cr.andPromTypeCodeEqualTo(promInfoDTO.getPromTypeCode());
        }
        if(!StringUtil.isEmpty(promInfoDTO.getSiteId())){
        	cr.andSiteIdEqualTo(promInfoDTO.getSiteId());
        }
        if(promInfoDTO.getShowStartTime()!=null){
        	cr.andShowStartTimeLessThanOrEqualTo(promInfoDTO.getShowStartTime());
        }
        if(promInfoDTO.getEndTime()!=null){
        	cr.andEndTimeGreaterThanOrEqualTo(promInfoDTO.getEndTime());
        }
     // 返回查询分页结果集
        return super.queryByPagination(promInfoDTO, example, true,
                new PaginationCallback<PromInfo, PromInfoResponseDTO>() {
                    // 查询记录集
                    @Override
                    public List<PromInfo> queryDB(BaseCriteria example) {

                        return promInfoMapper.selectByExample((PromInfoCriteria) example);
                    }
                    // 查询总记录数
                    @Override
                    public long queryTotal(BaseCriteria example) {

                        return promInfoMapper.countByExample((PromInfoCriteria) example);
                    }
                    // 查询结果转换
                    @Override
                    public PromInfoResponseDTO warpReturnObject(PromInfo t) {
                        return promInfoResponseDTOConverter.convert(t);
                    }
                 });
    }
    /**
     * 促销列表 分页
     * 
     * @param promInfoDTO
     * @param groupIds
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    private PageResponseDTO<PromInfoResponseDTO> listPromInfoForPage(PromInfoDTO promInfoDTO,
            List<Long> groupIds) throws BusinessException {

        // 促销审核 查询 如果状态为空表示 需要查询如下状态
        List<String> statuss = new ArrayList<String>();
        if (StringUtil.isEmpty(promInfoDTO.getStatus())) {
            statuss.add(PromConstants.PromInfo.STATUS_10);
            statuss.add(PromConstants.PromInfo.STATUS_20);
            statuss.add(PromConstants.PromInfo.STATUS_40);
            statuss.add(PromConstants.PromInfo.STATUS_50);
        }
        // 初始化查询条件
        PromInfoCriteria example1 = new PromInfoCriteria();

        example1.setLimitClauseCount(promInfoDTO.getPageSize());
        example1.setLimitClauseStart(promInfoDTO.getStartRowIndex());
        
        this.initPromInfoParm(promInfoDTO, groupIds, statuss, example1,"1");

        // 返回查询分页结果集
        return super.queryByPagination(promInfoDTO, example1, true,
                new PaginationCallback<PromInfo, PromInfoResponseDTO>() {
                    // 查询记录集
                    @Override
                    public List<PromInfo> queryDB(BaseCriteria example) {

                        return promInfoMapper.selectByExample((PromInfoCriteria) example);
                    }
                    // 查询总记录数
                    @Override
                    public long queryTotal(BaseCriteria example) {

                        return promInfoMapper.countByExample((PromInfoCriteria) example);
                    }
                    // 查询结果转换
                    @Override
                    public PromInfoResponseDTO warpReturnObject(PromInfo t) {
                        return promInfoResponseDTOConverter.convert(t);
                    }
                 });
       }
    /**
     * TODO 促销单品库存量 保存
     * @see com.zengshi.ecp.prom.service.busi.interfaces.IPromInfoSV#savePromSkuStockLimit(com.zengshi.ecp.prom.dubbo.dto.PromDTO)
     * @param promDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public void savePromSkuStockLimit(PromDTO promDTO) throws BusinessException {
        
        //单品库存量
        if (!CollectionUtils.isEmpty(promDTO.getSkuList())) {
            
            List<PromSkuDTO> promSkuDTOList = (List<PromSkuDTO>) promDTO.getSkuList();
            // dto 转为 model
            PromStockLimit promStockLimit = new PromStockLimit();
            for (PromSkuDTO promSkuDTO : promSkuDTOList) {
                promStockLimit.setBuyCnt(new Long(0));
                promStockLimit.setGdsId(promSkuDTO.getGdsId());
                promStockLimit.setPromCnt(promSkuDTO.getPromCnt().longValue());
                promStockLimit.setPromId(promDTO.getId());
                promStockLimit.setRemaindCnt(promStockLimit.getPromCnt());
                promStockLimit.setSkuId(promSkuDTO.getSkuId());
                promStockLimit.setCreateStaff(promDTO.getCreateStaff());
                promStockLimit.setCreateTime(new Timestamp(promDTO.getCreateTime().getTime()));
                promStockLimitMapper.insert(promStockLimit);
            }
        }
    }
    /**
     * TODO 促销搭配库存量 保存
     * @see com.zengshi.ecp.prom.service.busi.interfaces.IPromInfoSV#savePromMatchSkuStockLimit(com.zengshi.ecp.prom.dubbo.dto.PromDTO)
     * @param promDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public void savePromMatchStockLimit(PromDTO promDTO) throws BusinessException {
        
        //搭配山品库存量
    if (!CollectionUtils.isEmpty(promDTO.getMatchSkuDTOList())) {
            
            List<PromMatchSkuDTO> promMatchSkuDTOList = (List<PromMatchSkuDTO>) promDTO.getMatchSkuDTOList();
            // dto 转为 model
            PromStockLimit promStockLimit = new PromStockLimit();
            for (PromMatchSkuDTO promMatchSkuDTO : promMatchSkuDTOList) {
                promStockLimit.setBuyCnt(new Long(0));
                promStockLimit.setGdsId(promMatchSkuDTO.getGdsId());
                promStockLimit.setPromCnt(promMatchSkuDTO.getPromCnt().longValue());
                promStockLimit.setPromId(promDTO.getId());
                promStockLimit.setRemaindCnt(promStockLimit.getPromCnt());
                promStockLimit.setSkuId(promMatchSkuDTO.getSkuId());
                promStockLimit.setCreateStaff(promDTO.getCreateStaff());
                promStockLimit.setCreateTime(new Timestamp(promDTO.getCreateTime().getTime()));
                promStockLimitMapper.insert(promStockLimit);
            }
        }
    }
    /**
     * 促销库存量 更新
     * @param promStockLimitDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public int updatePromStockLimitDeduce(PromStockLimitDTO promStockLimitDTO) throws BusinessException{
        //主键需要 prom_id gds_id  sku_id 
        //编辑值 促销总量 购买量 剩余量
          if(promStockLimitDTO==null){
               //传入参数为空
              throw new BusinessException("prom.400086");
          }
          if(promStockLimitDTO.getGdsId()==null){
              //传入参数为空
             throw new BusinessException("prom.400089");
         }
          if(promStockLimitDTO.getSkuId()==null){
              //传入参数为空
             throw new BusinessException("prom.400090");
         }
          if(promStockLimitDTO.getPromId()==null){
              //传入参数为空
             throw new BusinessException("prom.400091");
         }
          if(promStockLimitDTO.getBuyCnt()==null){
              //传入参数为空
             throw new BusinessException("prom.400092");
         }
          //全场促销不需要设置库存信息
         PromInfoDTO dto=this.queryPromInfoDTOById(promStockLimitDTO.getPromId());
         if(dto==null){
             return 0;
         }
         if(PromConstants.PromInfo.JOIN_RANGE_1.equals(dto.getJoinRange())){
             return 0;
         }
         //如果 部分参与 并且没有设置库存 不需要验证
         PromStockLimitCriteria example=new PromStockLimitCriteria();
         PromStockLimitCriteria.Criteria cr=example.createCriteria();
         cr.andPromIdEqualTo(promStockLimitDTO.getPromId());
         cr.andGdsIdEqualTo(promStockLimitDTO.getGdsId());
         cr.andSkuIdEqualTo(promStockLimitDTO.getSkuId());
         long cnt= promStockLimitMapper.countByExample(example);
         
         if(cnt<=0){
             return 0;
         }
         int updateCnt= promStockLimitManualMapper.updateCntBySelective(promStockLimitConverter.convert(promStockLimitDTO));
         
         if(updateCnt<=0){
             //库存量  已经卖完了
             throw new BusinessException("prom.400127");
         }
         return updateCnt;
    }
    /**
     * 促销库存量 更新
     * @param promStockLimitDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public int updatePromStockLimitAdd(PromStockLimitDTO promStockLimitDTO) throws BusinessException{
        //主键需要 prom_id gds_id  sku_id 
        //编辑值 促销总量 购买量 剩余量
          if(promStockLimitDTO==null){
               //传入参数为空
              throw new BusinessException("prom.400086");
          }
          if(promStockLimitDTO.getGdsId()==null){
              //传入参数为空
             throw new BusinessException("prom.400089");
         }
          if(promStockLimitDTO.getSkuId()==null){
              //传入参数为空
             throw new BusinessException("prom.400090");
         }
          if(promStockLimitDTO.getPromId()==null){
              //传入参数为空
             throw new BusinessException("prom.400091");
         }
          if(promStockLimitDTO.getBuyCnt()==null){
              //传入参数为空
             throw new BusinessException("prom.400092");
         }
          
          //全场促销不需要设置库存信息
          PromInfoDTO dto=this.queryPromInfoDTOById(promStockLimitDTO.getPromId());
          if(dto==null){
              return 0;
          }
          if(PromConstants.PromInfo.JOIN_RANGE_1.equals(dto.getJoinRange())){
              return 0;
          }
          
          //如果 部分参与 并且没有设置库存 不需要验证
          PromStockLimitCriteria example=new PromStockLimitCriteria();
          PromStockLimitCriteria.Criteria cr=example.createCriteria();
          cr.andPromIdEqualTo(promStockLimitDTO.getPromId());
          cr.andGdsIdEqualTo(promStockLimitDTO.getGdsId());
          cr.andSkuIdEqualTo(promStockLimitDTO.getSkuId());
          long cnt= promStockLimitMapper.countByExample(example);
          
          if(cnt<=0){
              return 0;
          }
          
         int updateCnt= promStockLimitManualMapper.addCntBySelective(promStockLimitConverter.convert(promStockLimitDTO));
         
         if(updateCnt<=0){
             //补偿已购买量
             throw new BusinessException("prom.400181");
         }
         return updateCnt;
    }
    /**
     * 促销赠品 保存
     * @param promDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public void savePromGift(PromDTO promDTO) throws BusinessException{
        
    if (!CollectionUtils.isEmpty(promDTO.getGiftList())) {
            
            for (PromGiftDTO promGiftDTO : promDTO.getGiftList()) {
                //创建人 创建时间
                promGiftDTO.setCreateStaff(promDTO.getCreateStaff());
                promGiftDTO.setCreateTime(new Timestamp(promDTO.getCreateTime().getTime()));
                promGiftDTO.setUpdateStaff(promDTO.getCreateStaff());
                promGiftDTO.setUpdateTime(new Timestamp(promDTO.getCreateTime().getTime()));
                promGiftDTO.setPromId(promDTO.getId());
                promGiftDTO.setRemaindCnt(promGiftDTO.getPresentAllCnt());
                promGiftDTO.setPresentCnt(new Long(0));
                //为空 默认1
                if(promGiftDTO.getEveryTimeCnt()==null){
                    promGiftDTO.setEveryTimeCnt(new Long(1));
                }
                //promGiftDTO.setPresentAllCnt(presentAllCnt)//当前设置赠送数量
                //promGiftDTO.setPresentCnt(new Long(0));//需送出去的量
                
                promGiftMapper.insert(promGiftConverter.convert(promGiftDTO));
            }
        }
    }
    /**
     * 促销赠品库存量 更新
     * @param promGiftDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public int updatePromGiftDeduce(PromGiftDTO promGiftDTO) throws BusinessException{

          //主键需要 prom_id gift_id
          //编辑值 促销总量 购买量 剩余量
          if(promGiftDTO==null){
               //传入参数为空
              throw new BusinessException("prom.400086");
          }
          if(promGiftDTO.getGiftId()==null){
              //传入参数为空 赠品编码为空
             throw new BusinessException("prom.400124");
         }
          if(promGiftDTO.getPromId()==null){
              //传入参数为空
             throw new BusinessException("prom.400091");
         }
          if(promGiftDTO.getPresentCnt()==null){
              //传入参数为空
             throw new BusinessException("prom.400125");
         }
          if(promGiftDTO.getPresentCnt().compareTo(new Long(0))<=0){
              //赠送数量 大于0
             throw new BusinessException("prom.400126");
         }
          
          promGiftDTO.setUpdateTime(DateUtil.getSysDate());
         int updateCnt= promGiftManualMapper.updateCntBySelective(promGiftConverter.convert(promGiftDTO));
         
         if(updateCnt<=0){
             //赠品 已经送完了
             throw new BusinessException("prom.400129");
         }
         return updateCnt;
    }
    /**
     * 促销赠品库存量  补偿新增机制
     * @param promGiftDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public int updatePromGiftAdd(PromGiftDTO promGiftDTO) throws BusinessException{

        //主键需要 prom_id gift_id
        //编辑值 促销总量 购买量 剩余量
        if(promGiftDTO==null){
             //传入参数为空
            throw new BusinessException("prom.400086");
        }
        if(promGiftDTO.getGiftId()==null){
            //传入参数为空 赠品编码为空
           throw new BusinessException("prom.400124");
       }
        if(promGiftDTO.getPromId()==null){
            //传入参数为空
           throw new BusinessException("prom.400091");
       }
        if(promGiftDTO.getPresentCnt()==null){
            //传入参数为空
           throw new BusinessException("prom.400125");
       }
        if(promGiftDTO.getPresentCnt().compareTo(new Long(0))<=0){
            //赠送数量 大于0
           throw new BusinessException("prom.400126");
       }
        
        promGiftDTO.setUpdateTime(DateUtil.getSysDate());
        
         int updateCnt= promGiftManualMapper.addCntBySelective(promGiftConverter.convert(promGiftDTO));
         
         if(updateCnt<=0){
             //赠品 已经送完了
             throw new BusinessException("prom.400182");
         }
         return updateCnt;
    }
    /**
     * 
     * savePromCatg:(保存分类数据). <br/> 
     * 
     * @author PJieWin 
     * @param promDTO 
     * @since JDK 1.6
     */
    public void savePromCatg(PromDTO promDTO){
        //分类列表
       if (!CollectionUtils.isEmpty(promDTO.getCatgList())) {
              
              List<PromSkuDTO> promSkuDTOList = (List<PromSkuDTO>) promDTO.getCatgList();
              
              String matchType=PromConstants.PromSku.MATCH_TYPE_1;
              //如果有搭配商品 t_prom_info.if_match=1  && t_prom_sku.match_type =2
              if(!CollectionUtils.isEmpty(promDTO.getMatchSkuDTOList())){
                  matchType=PromConstants.PromSku.MATCH_TYPE_2;
              } 
              // dto 转为 model
              PromSku promSku = new PromSku();
              for (PromSkuDTO promSkuDTO : promSkuDTOList) {
                  promSkuDTO.setId(seq_prom_sku_id.nextValue());// 获得序列号
                  promSkuDTO.setIfShow(promDTO.getIfShow());//冗余字段
                  promSkuDTO.setEndTime(promDTO.getEndTime());//冗余截止时间
                  promSkuDTO.setPriority(promDTO.getPriority());//冗余优先级
                  promSkuDTO.setStartTime(promDTO.getStartTime());//冗余开始时间
                  promSkuDTO.setStatus(promDTO.getStatus());//冗余状态
                  promSkuDTO.setShopId(promDTO.getShopId());
                  promSkuDTO.setPromClass(promDTO.getPromClass());
                  promSkuDTO.setCreateStaff(promDTO.getCreateStaff());
                  promSkuDTO.setCreateTime(promDTO.getCreateTime());
                  promSkuDTO.setMatchType(matchType);
                  promSkuDTO.setIfComposit(promDTO.getIfComposit());//是否叠加 冗余
                  promSkuDTO.setSiteId(promDTO.getSiteId());//站点 冗余
                  promSkuDTO.setJoinType(PromConstants.PromSku.JOIN_TYPE_1);//分类
                  promSkuDTO.setShowStartTime(promDTO.getShowStartTime());
                  promSkuDTO.setShowEndTime(promDTO.getShowEndTime());
                  promSkuDTO.setIfValid(PromConstants.PromSku.IF_VALID_1);
                  //分类编码前台传入
                  promSku = promSkuConverter.convert(promSkuDTO);
                  promSku.setPromId(promDTO.getId());
                  promSkuMapper.insert(promSku);
              }
          }    
    }
}

