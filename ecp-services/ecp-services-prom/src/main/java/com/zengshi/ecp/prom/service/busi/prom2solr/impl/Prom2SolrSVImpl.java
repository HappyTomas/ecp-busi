package com.zengshi.ecp.prom.service.busi.prom2solr.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.general.prom.util.PromMsgUtil;
import com.zengshi.ecp.prom.dao.mapper.busi.Prom2SolrMapper;
import com.zengshi.ecp.prom.dao.model.Prom2Solr;
import com.zengshi.ecp.prom.dao.model.Prom2SolrCriteria;
import com.zengshi.ecp.prom.dao.model.Prom2SolrCriteria.Criteria;
import com.zengshi.ecp.prom.dubbo.dto.Prom2SolrReqDTO;
import com.zengshi.ecp.prom.dubbo.dto.Prom2SolrRespDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.Converter;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.prom2solr.interfaces.IProm2SolrSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-11-19 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class Prom2SolrSVImpl extends GeneralSQLSVImpl implements IProm2SolrSV {

    private static final String MODULE = Prom2SolrSVImpl.class.getName();
 
    @Resource
    private Prom2SolrMapper prom2SolrMapper;
    
    @Resource
    private  Converter<Prom2SolrReqDTO, Prom2Solr> prom2SolrConverter;
    
    @Resource
    private Converter<Prom2Solr, Prom2SolrRespDTO> prom2SolrRespDTOConverter;
    
    @Resource(name = "seq_prom_2_solr_id")
    private PaasSequence seq_prom_2_solr_id;
    
    /**
     * 消息中间表保存
     * @param prom2SolrReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void save( Prom2SolrReqDTO prom2SolrReqDTO) throws BusinessException{
        //只有生效 失效才insert
        if (PromConstants.PromInfo.STATUS_10.equals(prom2SolrReqDTO.getStatus())
                || PromConstants.PromInfo.STATUS_20.equals(prom2SolrReqDTO.getStatus())) {
            
            //如果消息列表存在未发布的 可以不用insert
            Prom2SolrReqDTO query =new Prom2SolrReqDTO();
           // query.setPromId(prom2SolrReqDTO.getPromId());
            query.setPromId(prom2SolrReqDTO.getId());
            query.setSendStatus(PromConstants.Prom2Solr.SEND_STATUS_0);
            query.setStatus(prom2SolrReqDTO.getStatus());
            //处理solr关键字数据
            query.setSolrType(PromConstants.Prom2Solr.SOLR_TYPE_1);
            PageResponseDTO<Prom2SolrRespDTO> page= this.queryProm2SolrForPage(query);
            if(page==null || page.getCount()<=0){
                prom2SolrReqDTO.setPromId(query.getPromId());
                prom2SolrReqDTO.setSendStatus(PromConstants.Prom2Solr.SEND_STATUS_0);
                prom2SolrReqDTO.setSendType(PromConstants.Prom2Solr.SEND_TYPE_1);
                prom2SolrReqDTO.setId(seq_prom_2_solr_id.nextValue());
                //刷新solr 关键字服务数据
                prom2SolrReqDTO.setSolrType(PromConstants.Prom2Solr.SOLR_TYPE_1);
                prom2SolrMapper.insert(prom2SolrConverter.convert(prom2SolrReqDTO));
            }
            //处理促销商品关系数据(新增促销、生效促销、失效促销)
            if("1".equals(prom2SolrReqDTO.getIfSendMsg())){
                query.setSolrType(PromConstants.Prom2Solr.SOLR_TYPE_2);
                PageResponseDTO<Prom2SolrRespDTO> page1= this.queryProm2SolrForPage(query);
                if(page1==null || page1.getCount()<=0){
                    prom2SolrReqDTO.setPromId(query.getPromId());
                    prom2SolrReqDTO.setSendStatus(PromConstants.Prom2Solr.SEND_STATUS_0);
                    prom2SolrReqDTO.setSendType(PromConstants.Prom2Solr.SEND_TYPE_1);
                    //刷新solr 促销和商品关系数据
                    prom2SolrReqDTO.setId(seq_prom_2_solr_id.nextValue());
                    prom2SolrReqDTO.setSolrType(PromConstants.Prom2Solr.SOLR_TYPE_2);
                    //发送消息
                    if(PromMsgUtil.sendPromIndexMsg(prom2SolrReqDTO.getSiteId(),query.getPromId(), null, prom2SolrReqDTO.getStatus(), Prom2SolrSVImpl.class.toString())){
                        prom2SolrReqDTO.setSendStatus(PromConstants.Prom2Solr.SEND_STATUS_1); 
                        prom2SolrReqDTO.setSendTime(DateUtil.getSysDate());
                     }
                  
                    prom2SolrMapper.insert(prom2SolrConverter.convert(prom2SolrReqDTO));
                }
            }
        }
    }
    
    /**
     * 更新
     * @param prom2SolrReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public long update(Prom2SolrReqDTO prom2SolrReqDTO) throws BusinessException{
        Prom2Solr s=prom2SolrConverter.convert(prom2SolrReqDTO);
        return prom2SolrMapper.updateByPrimaryKeySelective(s);
    }
    
 
    /**
     * 促销消息 数据  不分页
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<Prom2SolrRespDTO> queryProm2SolrList(Prom2SolrReqDTO prom2SolrReqDTO) throws BusinessException{

        Prom2SolrCriteria example = new Prom2SolrCriteria();
        // 初始化查询条件
        this.initParm(prom2SolrReqDTO,example);
        
        example.setOrderByClause(" ID ASC ");
        // 查询
        List<Prom2Solr> list = prom2SolrMapper.selectByExample(example);
        
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        // 解析结果集并转化
        List<Prom2SolrRespDTO> reList = new ArrayList<Prom2SolrRespDTO>();
        
        for (Prom2Solr prom2Solr : list) {
            reList.add(prom2SolrRespDTOConverter.convert(prom2Solr));
        }
        
        return reList;
    
    }
 
    /**
     * 列表 分页功能
     * @param prom2SolrReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<Prom2SolrRespDTO> queryProm2SolrForPage(Prom2SolrReqDTO prom2SolrReqDTO)
            throws BusinessException{

        Prom2SolrCriteria example = new Prom2SolrCriteria();
        // 初始化查询页数 数量
        example.setLimitClauseCount(prom2SolrReqDTO.getPageSize());
        example.setLimitClauseStart(prom2SolrReqDTO.getStartRowIndex());
        // 排序
        example.setOrderByClause("id desc");
        // 初始化查询条件
        this.initParm(prom2SolrReqDTO,example);

        // 返回查询分页结果集
        return super.queryByPagination(prom2SolrReqDTO, example, true,
                new PaginationCallback<Prom2Solr, Prom2SolrRespDTO>() {
                    // 查询记录集
                    @Override
                    public List<Prom2Solr> queryDB(BaseCriteria example) {

                        return prom2SolrMapper.selectByExample((Prom2SolrCriteria) example);
                    }

                    // 查询总记录数
                    @Override
                    public long queryTotal(BaseCriteria example) {

                        return prom2SolrMapper.countByExample((Prom2SolrCriteria) example);
                    }
                    // 查询结果转换
                    @Override
                    public Prom2SolrRespDTO warpReturnObject(Prom2Solr t) {
                        return prom2SolrRespDTOConverter.convert(t);
                    }
                });
    
    }
    
    /**
     * 初始化查询条件
     * @param reqDTO
     * @param example
     * @author huangjx
     */
    private void initParm(Prom2SolrReqDTO reqDTO,Prom2SolrCriteria example){
        
        if (reqDTO == null) {
            return;
        }
        Criteria cr = example.createCriteria();
        //solr_type
        if(StringUtil.isNotEmpty(reqDTO.getSolrType())){
            cr.andSolrTypeEqualTo(reqDTO.getSolrType());
        }
        //id 条件
        if(StringUtil.isNotEmpty(reqDTO.getId())){
            cr.andIdEqualTo(reqDTO.getId());
        }
        //截止时间
        if(StringUtil.isNotEmpty(reqDTO.getEndTime())){
            cr.andEndTimeGreaterThanOrEqualTo(new Timestamp(reqDTO.getEndTime().getTime()));
        }
        //开始时间
        if(StringUtil.isNotEmpty(reqDTO.getStartTime())){
            cr.andStartTimeLessThanOrEqualTo(new Timestamp(reqDTO.getStartTime().getTime()));
        }
        //展示开始时间
        if(StringUtil.isNotEmpty(reqDTO.getShowStartTime())){
            cr.andShowStartTimeLessThanOrEqualTo(new Timestamp(reqDTO.getShowStartTime().getTime()));
        }
        //展示截止时间
        if(StringUtil.isNotEmpty(reqDTO.getShowEndTime())){
            cr.andShowEndTimeGreaterThan(new Timestamp(reqDTO.getShowEndTime().getTime()));
        }
        //黑名单
        if(StringUtil.isNotEmpty(reqDTO.getIfBlacklist())){
            cr.andIfBlacklistEqualTo(reqDTO.getIfBlacklist());
        }
        //匹配
        if(StringUtil.isNotEmpty(reqDTO.getIfMatch())){
            cr.andIfMatchEqualTo(reqDTO.getIfMatch());
        }
        //是否展示
        if(StringUtil.isNotEmpty(reqDTO.getIfShow())){
            cr.andIfShowEqualTo(reqDTO.getIfShow());
        }
        //参与范围
        if(StringUtil.isNotEmpty(reqDTO.getJoinRange())){
            cr.andJoinRangeEqualTo(reqDTO.getJoinRange());
        }
        //促销编码
        if(StringUtil.isNotEmpty(reqDTO.getPromId())){
            cr.andPromIdEqualTo(reqDTO.getPromId());
        }
        //促销类型编码
        if(StringUtil.isNotEmpty(reqDTO.getPromTypeCode())){
            cr.andPromTypeCodeEqualTo(reqDTO.getPromTypeCode());
        }
        //发送状态
        if(StringUtil.isNotEmpty(reqDTO.getSendStatus())){
            cr.andSendStatusEqualTo(reqDTO.getSendStatus());
        }else{
            //待发送
            cr.andSendStatusEqualTo(PromConstants.Prom2Solr.SEND_STATUS_0);
        }
        //发送时间
        if(StringUtil.isNotEmpty(reqDTO.getSendTime())){
            cr.andSendTimeEqualTo(new Timestamp(reqDTO.getSendTime().getTime()));
        }
        //发送类型
        if(StringUtil.isNotEmpty(reqDTO.getSendType())){
            cr.andSendTypeEqualTo(reqDTO.getSendType());
        }
        //店铺编码
        if(StringUtil.isNotEmpty(reqDTO.getShopId())){
            cr.andShopIdEqualTo(reqDTO.getShopId());
        }
        //站点编码
        if(StringUtil.isNotEmpty(reqDTO.getSiteId())){
            cr.andSiteIdEqualTo(reqDTO.getSiteId());
        }
        //状态
        if(StringUtil.isNotEmpty(reqDTO.getStatus())){
            cr.andStatusEqualTo(reqDTO.getStatus());
        }else{
            //默认查询 有效和失效
            List<String> l=new ArrayList<String>();
            l.add(PromConstants.PromInfo.STATUS_10);
            l.add(PromConstants.PromInfo.STATUS_20);
            cr.andStatusIn(l);
        }
        //发送时间为空
        cr.andSendTimeIsNull();
    }
}
