package com.zengshi.ecp.prom.service.busi.sku.impl;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.general.prom.util.PromMsgUtil;
import com.zengshi.ecp.prom.dao.mapper.busi.PromSkuMapper;
import com.zengshi.ecp.prom.dao.mapper.busi.PromStockLimitMapper;
import com.zengshi.ecp.prom.dao.model.PromInfo;
import com.zengshi.ecp.prom.dao.model.PromSku;
import com.zengshi.ecp.prom.dao.model.PromSkuCriteria;
import com.zengshi.ecp.prom.dao.model.PromSkuCriteria.Criteria;
import com.zengshi.ecp.prom.dao.model.PromStockLimit;
import com.zengshi.ecp.prom.dao.model.PromStockLimitCriteria;
import com.zengshi.ecp.prom.dubbo.dto.KillGdsInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.Prom2SolrReqDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuRespDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.Converter;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromCmsSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromInfoSV;
import com.zengshi.ecp.prom.service.busi.prom2solr.interfaces.IProm2SolrSV;
import com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-12-11 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromSkuSVImpl extends GeneralSQLSVImpl  implements IPromSkuSV {

    private static final String MODULE = PromSkuSVImpl.class.getName();
    
    @Resource
    private PromSkuMapper promSkuMapper;
    
    @Resource
    private PromStockLimitMapper promStockLimitMapper;
    
    @Resource
    private IProm2SolrSV prom2SolrSV;
    
    @Resource
    private IPromInfoSV promInfoSV;
    
    @Resource(name = "seq_prom_sku_id")
    private PaasSequence seq_prom_sku_id;
    
    @Resource
    private Converter<PromSku,PromSkuRespDTO> promSkuRespDTOConverter;
    
    
    @Resource
    private Converter<PromSku,KillGdsInfoDTO> promKillGdsInfoDTOConverter;
    
    @Resource
    private Converter<PromSkuDTO,PromSku> promSkuConverter;
    
    @Resource
    private IPromCmsSV promCmsSV;
    
    //默认线程处理个数
    private static int nThreadsCount=20;
    /**
     * TODO 促销保存单品信息
     * @see com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuCheckSV#savePromSku(com.zengshi.ecp.prom.dao.model.PromSku)
     * @param promSku
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public int updatePromSku(PromSkuDTO promSkuDTO)
            throws BusinessException{
        
       int cnt= this.updatePromSkuInfo(promSkuDTO);
        //solr信息 记录
        PromInfoDTO promInfoDTO=promInfoSV.queryPromInfoDTOById(promSkuDTO.getPromId());
        
        if(promInfoDTO!=null){
            Prom2SolrReqDTO prom2SolrReqDTO=new Prom2SolrReqDTO();
            ObjectCopyUtil.copyObjValue(promInfoDTO, prom2SolrReqDTO, "", false);
            prom2SolrReqDTO.setPromId(promInfoDTO.getId());
            prom2SolrSV.save(prom2SolrReqDTO);
        }
        
        return cnt;
    }
    /**
     * TODO 促销保存单品信息
     * @see com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuCheckSV#savePromSku(com.zengshi.ecp.prom.dao.model.PromSku)
     * @param promSku
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    private int updatePromSkuInfo(PromSkuDTO promSkuDTO)
            throws BusinessException{
        PromSku promSku=new PromSku();
        promSku.setIfValid(PromConstants.PromSku.IF_VALID_1);
        if(!StringUtil.isEmpty(promSkuDTO.getIfValid())){
            promSku.setIfValid(promSkuDTO.getIfValid());
        }
        promSku.setUpdateTime(DateUtil.getSysDate());
        promSku.setUpdateStaff(promSkuDTO.getUpdateStaff());
        
        PromSkuCriteria example=new PromSkuCriteria();
        Criteria cr=example.createCriteria();
        cr.andIdEqualTo(promSkuDTO.getId());
        cr.andPromIdEqualTo(promSkuDTO.getPromId());
        
        int cnt=promSkuMapper.updateByExampleSelective(promSku, example);
        
        PromSku sku= promSkuMapper.selectByPrimaryKey(promSkuDTO.getId());
        
        //发送消息
        PromMsgUtil.sendPromIndexMsg(sku.getSiteId(), sku.getPromId(), sku.getSkuId(), PromConstants.PromInfo.STATUS_20, PromSkuSVImpl.class.toString());
        
        
        //失效 需要通知cms 把cms配置的相关数据作废掉
        if(PromConstants.PromSku.IF_VALID_0.equals(promSku.getIfValid())){
             promCmsSV.invalidCmsGds(sku.getPromId(), sku.getGdsId(), sku.getSkuId(),null);
        }
        
        return cnt;
    }
    /**
     * TODO 批量更新促销单品   目前页面操作数据量不大 不需要线程处理
     * @see com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuSV#updatePromSkuBatch(java.util.List)
     * @param promSkuDTOList
     * @throws BusinessException
     * @author huangjx
     */
    public void insertPromSkuBatch(List<PromSkuDTO> promSkuDTOList)
            throws BusinessException{
        
        if(CollectionUtils.isEmpty(promSkuDTOList)){
            return ;
        }
        //过滤数据
        HashSet h  =   new  HashSet(promSkuDTOList); 
        promSkuDTOList.clear(); 
        promSkuDTOList.addAll(h); 
        
        PromInfoDTO promInfoDTO=promInfoSV.queryPromInfoDTOById(promSkuDTOList.get(0).getPromId());
        
        //更新solr标记
        long cnt=0;
        for(PromSkuDTO promSkuDTO:promSkuDTOList){
            //insert 验证数据库是否存在 如果存在跳过
            PromSkuDTO dto=new PromSkuDTO();
            dto.setPromId(promSkuDTO.getPromId());
            dto.setSkuId(promSkuDTO.getSkuId());
            dto.setIfValid(PromConstants.PromSku.IF_VALID_1);
            PageResponseDTO<PromSkuRespDTO> page=this.querySkuPromPage(dto);
            if(page!=null && page.getCount()>0){
                continue;
            }
            cnt=cnt+1;
            PromSku promSku=promSkuConverter.convert(promSkuDTO);
            promSku.setCreateTime(DateUtil.getSysDate());
            promSku.setCreateStaff(promSkuDTO.getStaff().getId());
            promSku.setEndTime(promInfoDTO.getEndTime());
            promSku.setIfComposit(promInfoDTO.getIfComposit());
            promSku.setIfShow(promInfoDTO.getIfShow());
            promSku.setIfValid(PromConstants.PromSku.IF_VALID_1);
            promSku.setJoinType(PromConstants.PromSku.JOIN_TYPE_2);
            promSku.setMatchType(PromConstants.PromSku.MATCH_TYPE_1);
            promSku.setPriority(promInfoDTO.getPriority());
            promSku.setPromClass(promInfoDTO.getPromClass());
            promSku.setPromCnt(0l);
            promSku.setShopId(promInfoDTO.getShopId());
            promSku.setShowEndTime(promInfoDTO.getShowEndTime());
            promSku.setShowStartTime(promInfoDTO.getShowStartTime());
            promSku.setSiteId(promInfoDTO.getSiteId());
            promSku.setStartTime(promInfoDTO.getStartTime());
            promSku.setStatus(promInfoDTO.getStatus());
            
            this.savePromSku(promSku);
            
            //发送消息
            PromMsgUtil.sendPromIndexMsg(promSku.getSiteId(), promSku.getPromId(), promSku.getSkuId(), promSku.getStatus(), PromSkuSVImpl.class.toString());
        }
        if(cnt>0){
            //更新solr消息
            if(promInfoDTO!=null){
                Prom2SolrReqDTO prom2SolrReqDTO=new Prom2SolrReqDTO();
                ObjectCopyUtil.copyObjValue(promInfoDTO, prom2SolrReqDTO, "", false);
                prom2SolrReqDTO.setPromId(promInfoDTO.getId());
                prom2SolrSV.save(prom2SolrReqDTO);
            } 
        }
    }
    /**
     * TODO 批量更新促销单品   目前页面操作数据量不大 不需要线程处理
     * @see com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuSV#updatePromSkuBatch(java.util.List)
     * @param promSkuDTOList
     * @throws BusinessException
     * @author huangjx
     */
    public void updatePromSkuBatch(List<PromSkuDTO> promSkuDTOList)
            throws BusinessException{
        
        if(CollectionUtils.isEmpty(promSkuDTOList)){
            return ;
        }
        //过滤数据
        HashSet h  =   new  HashSet(promSkuDTOList); 
        promSkuDTOList.clear(); 
        promSkuDTOList.addAll(h); 
        
        //更新solr标记
        long cnt=0;
        for(PromSkuDTO promSkuDTO:promSkuDTOList){
            //取出数据
            PromSkuCriteria example=new PromSkuCriteria();
            this.initPromSkuParm(promSkuDTO,example);
            List<PromSku> skuList=promSkuMapper.selectByExample(example);
            //更新操作 
            for(PromSku promSku:skuList){
                cnt=cnt+1;
                promSkuDTO.setId(promSku.getId());
                promSkuDTO.setPromId(promSkuDTO.getPromId());
                promSkuDTO.setIfValid(PromConstants.PromSku.IF_VALID_0);
                promSkuDTO.setSkuId(promSku.getSkuId());
                promSkuDTO.setSiteId(promSku.getSiteId());
                promSkuDTO.setStatus(promSku.getStatus());
                
                this.updatePromSkuInfo(promSkuDTO);
            }
        }
        if(cnt>0){
            //更新solr消息
            PromInfoDTO promInfoDTO=promInfoSV.queryPromInfoDTOById(promSkuDTOList.get(0).getPromId());
            
            if(promInfoDTO!=null){
                Prom2SolrReqDTO prom2SolrReqDTO=new Prom2SolrReqDTO();
                ObjectCopyUtil.copyObjValue(promInfoDTO, prom2SolrReqDTO, "", false);
                prom2SolrReqDTO.setPromId(promInfoDTO.getId());
                prom2SolrSV.save(prom2SolrReqDTO);
            } 
        }
    }
    /**
     * TODO更新促销数量
     * @see com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuSV#updatePromSkuCnt(com.zengshi.ecp.prom.dubbo.dto.PromSkuDTO)
     * @param promSkuDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public void updatePromSkuCnt(PromSkuDTO promSkuDTO)
            throws BusinessException{
         
        if(promSkuDTO==null){
            return ;
        }
        //取出数据
        PromSkuCriteria example=new PromSkuCriteria();
        this.initPromSkuParm(promSkuDTO,example);
        List<PromSku> skuList=promSkuMapper.selectByExample(example);
        //更新操作 
        for(PromSku promSku:skuList){
            PromSku promSku1=new PromSku();
            promSku1.setUpdateTime(DateUtil.getSysDate());
            promSku1.setUpdateStaff(promSkuDTO.getUpdateStaff());
            promSku1.setPromCnt(promSkuDTO.getPromCnt());
            
            PromSkuCriteria example1=new PromSkuCriteria();
            Criteria cr=example1.createCriteria();
            cr.andIdEqualTo(promSku.getId());
            cr.andPromIdEqualTo(promSku.getPromId());
            
            promSkuMapper.updateByExampleSelective(promSku1, example1);
            
            //发送消息
            PromMsgUtil.sendPromIndexMsg(promSku.getSiteId(), promSku.getPromId(), promSku.getSkuId(), promSku.getStatus(), PromSkuSVImpl.class.toString());
            
        }
        
        //更新库存量
        this.updatePromStockSkuCnt(promSkuDTO);
    }
    /**
     * 促销数量变更 limit
     * @param promSkuDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void updatePromStockSkuCnt(PromSkuDTO promSkuDTO)
            throws BusinessException{
        
            if(promSkuDTO==null){
                return ;
            }
            PromStockLimitCriteria example1=new PromStockLimitCriteria();
            PromStockLimitCriteria.Criteria cr=example1.createCriteria();
            cr.andPromIdEqualTo(promSkuDTO.getPromId());
            cr.andSkuIdEqualTo(promSkuDTO.getSkuId());
            List<PromStockLimit>  l=promStockLimitMapper.selectByExample(example1);
            if(!CollectionUtils.isEmpty(l)){
                
                for(PromStockLimit promStockLimit:l){
                    //更新操作 
                    PromStockLimit promStockLimit1=new PromStockLimit();
                    promStockLimit1.setPromCnt(promSkuDTO.getPromCnt());
                    promStockLimit1.setRemaindCnt(promStockLimit1.getPromCnt().longValue()-promStockLimit.getBuyCnt().longValue());
                    promStockLimitMapper.updateByExampleSelective(promStockLimit1, example1);
                }
            }
    }
    /**
     * TODO 促销保存单品信息
     * @see com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuCheckSV#savePromSku(com.zengshi.ecp.prom.dao.model.PromSku)
     * @param promSku
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public int savePromSku(PromSku promSku)
            throws BusinessException{
        promSku.setId(seq_prom_sku_id.nextValue());
        return promSkuMapper.insert(promSku);
    }
    /**
     * TODO促销保存单品信息 库存量信息保存
     * @see com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuSV#savePromStockLimit(com.zengshi.ecp.prom.dao.model.PromStockLimit)
     * @param promStockLimit
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public int savePromStockLimit(PromStockLimit promStockLimit)
            throws BusinessException{
        
        if(promStockLimit==null){
            return 0;
        }
        int reInt=0;
        PromStockLimitCriteria example1=new PromStockLimitCriteria();
        PromStockLimitCriteria.Criteria cr=example1.createCriteria();
        cr.andPromIdEqualTo(promStockLimit.getPromId());
        cr.andSkuIdEqualTo(promStockLimit.getSkuId());
        List<PromStockLimit>  l=promStockLimitMapper.selectByExample(example1);
        if(!CollectionUtils.isEmpty(l)){
            
            for(PromStockLimit promStockLimit2:l){
                //更新操作 
                PromStockLimit promStockLimit1=new PromStockLimit();
                promStockLimit1.setPromCnt(promStockLimit.getPromCnt());
                promStockLimit1.setRemaindCnt(promStockLimit1.getPromCnt().longValue()-promStockLimit2.getBuyCnt().longValue());
                reInt=reInt+promStockLimitMapper.updateByExampleSelective(promStockLimit1, example1);
            }
        }else{
            reInt= promStockLimitMapper.insert(promStockLimit);
        }
        return reInt;
    }
    /**
     * TODO参加促销商品 
     * @see com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuSV#querySkuPromByPromId(java.lang.Long)
     * @param promId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromSku> querySkuPromByPromId(Long promId)
            throws BusinessException{
        PromSkuCriteria example = new PromSkuCriteria();
        example.setOrderByClause("ID DESC");
        PromSkuCriteria.Criteria  cr=example.createCriteria();
        cr.andPromIdEqualTo(promId);
        cr.andIfValidEqualTo(PromConstants.PromSku.IF_VALID_1);
        return promSkuMapper.selectByExample(example);
    }
    /**
     * TODO查询 商品列表
     * @see com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuSV#querySkuPromPage(com.zengshi.ecp.prom.dubbo.dto.PromSkuDTO)
     * @param promSkuDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromSkuRespDTO> querySkuPromPage(PromSkuDTO promSkuDTO)
            throws BusinessException{

        PromSkuCriteria example = new PromSkuCriteria();
        // 初始化查询页数 数量
        example.setLimitClauseCount(promSkuDTO.getPageSize());
        example.setLimitClauseStart(promSkuDTO.getStartRowIndex());
        // 排序
        example.setOrderByClause("id desc");
        // 初始化查询条件
        this.initPromSkuParm(promSkuDTO,example);

        // 返回查询分页结果集
        return super.queryByPagination(promSkuDTO, example, true,
                new PaginationCallback<PromSku, PromSkuRespDTO>() {
                    // 查询记录集
                    @Override
                    public List<PromSku> queryDB(BaseCriteria example) {

                        return promSkuMapper.selectByExample((PromSkuCriteria) example);
                    }

                    // 查询总记录数
                    @Override
                    public long queryTotal(BaseCriteria example) {
                        return promSkuMapper.countByExample((PromSkuCriteria) example);
                    }
                    // 查询结果转换
                    @Override
                    public PromSkuRespDTO warpReturnObject(PromSku t) {
                        return promSkuRespDTOConverter.convert(t);
                    }
                });
    }
    
    
    /**
     * 查询构造条件
     * @param promSku
     * @param date
     * @param example
     * @author huangjx
     */
    private void initPromSkuParm(PromSkuDTO promSkuDTO, PromSkuCriteria example) {

        Criteria cr = example.createCriteria();
        //促销编码
        if(!StringUtil.isEmpty(promSkuDTO.getPromId())){
            cr.andPromIdEqualTo(promSkuDTO.getPromId());
        }
       //skuId
        if (!StringUtil.isEmpty(promSkuDTO.getSkuId())) {
            cr.andSkuIdEqualTo(promSkuDTO.getSkuId());
        }
        //店铺编码
        if(!StringUtil.isEmpty(promSkuDTO.getShopId())){
            cr.andShopIdEqualTo(promSkuDTO.getShopId());
        }
        //促销分类
        if(!StringUtil.isEmpty(promSkuDTO.getPromClass())){
            cr.andPromClassEqualTo(promSkuDTO.getPromClass());
        }
        //商品编码
        if(!StringUtil.isEmpty(promSkuDTO.getGdsId())){
            cr.andGdsIdEqualTo(promSkuDTO.getGdsId());
        }
        //搭配类型
        if(!StringUtil.isEmpty(promSkuDTO.getMatchType())){
           cr.andMatchTypeEqualTo(promSkuDTO.getMatchType());
        }
        //站点
        if(!StringUtil.isEmpty(promSkuDTO.getSiteId())){
            cr.andSiteIdEqualTo(promSkuDTO.getSiteId());
         }
        //分类
        if(!StringUtil.isEmpty(promSkuDTO.getJoinType())){
            cr.andJoinTypeEqualTo(promSkuDTO.getJoinType());
         }
        //分类编码
        if(!StringUtil.isEmpty(promSkuDTO.getCatgCode())){
            cr.andCatgCodeEqualTo(promSkuDTO.getCatgCode());
         }
        //是否有效
        if(!StringUtil.isEmpty(promSkuDTO.getIfValid())){
            cr.andIfValidEqualTo(promSkuDTO.getIfValid());
         }else{
            cr.andIfValidEqualTo(PromConstants.PromSku.IF_VALID_1);
         }
        //排序
        example.setOrderByClause(" id asc ");
    }
    /**
     * 参加促销商品 (有效)
     * @param promId
     * @param ifValid
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromSku> querySkuPromByPromId(Long promId,String ifValid)
            throws BusinessException{
        PromSkuCriteria example = new PromSkuCriteria();
        PromSkuDTO promSkuDTO=new PromSkuDTO();
        promSkuDTO.setPromId(promId);
        promSkuDTO.setIfValid(ifValid);
        promSkuDTO.setJoinType(PromConstants.PromSku.JOIN_TYPE_2);
        this.initPromSkuParm(promSkuDTO,example);
        return promSkuMapper.selectByExample(example);
    }
    /**
     * TODO保存 促销单品信息 copy promId的信息到新的促销id
     * @see com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuSV#savePromSkuThread(java.lang.Long, com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO)
     * @param promIdOld
     * @param promInfoDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void savePromSkuThread(Long promIdOld,PromInfo promInfo)
            throws BusinessException{
        
        if(StringUtil.isEmpty(promIdOld)){
            LogUtil.error(MODULE, "输入参数为空哦");
            return ;
        }

        if(promInfo==null){
            LogUtil.error(MODULE, "输入参数为空哦");
            return ;
        }
        
        //查询promIdold t_prom_sku 数据  (可能几万笔数据哦 怎么处理呢 待定)
        List<PromSku> list=querySkuPromByPromId(promIdOld,PromConstants.PromSku.IF_VALID_1);
        
        if(CollectionUtils.isEmpty(list)){
            return ;
        }
        ExecutorService threadExecutor = Executors.newFixedThreadPool(nThreadsCount);
        
        //CountDownLatch countDownLatch  =  new  CountDownLatch (list.size()) ;
        
        try{
             for(PromSku promSku1:list){
                 PromSku promSku=new PromSku();
                 promSku.setCreateStaff(promInfo.getCreateStaff());
                 promSku.setCreateTime(DateUtil.getSysDate());
                 promSku.setEndTime(promInfo.getEndTime());
                 promSku.setGdsId(promSku1.getGdsId());
                 promSku.setIfComposit(promInfo.getIfComposit());
                 promSku.setIfShow(promInfo.getIfShow());
                 promSku.setJoinType(promSku1.getJoinType());
                 promSku.setMatchType(promSku1.getMatchType());
                 promSku.setPriority(promInfo.getPriority());
                 promSku.setPromClass(promInfo.getPromClass());
                 promSku.setPromCnt(promSku1.getPromCnt());
                 promSku.setPromId(promInfo.getId());
                 promSku.setShopId(promInfo.getShopId());
                 promSku.setShowEndTime(promInfo.getShowEndTime());
                 promSku.setShowStartTime(promInfo.getShowStartTime());
                 promSku.setSiteId(promInfo.getSiteId());
                 promSku.setSkuId(promSku1.getSkuId());
                 promSku.setStatus(promInfo.getStatus());
                 promSku.setStartTime(promInfo.getStartTime());
                 promSku.setIfValid(promSku1.getIfValid());
                 promSku.setCatgCode(promSku1.getCatgCode());
                 promSku.setPriceType(promSku1.getPriceType());
                 promSku.setPriceValue(promSku1.getPriceValue());
                 
                 PromStockLimit promStockLimit=new PromStockLimit();
                 
                 promStockLimit.setBuyCnt(new Long(0));
                 promStockLimit.setGdsId(promSku.getGdsId());
                 promStockLimit.setPromCnt(promSku.getPromCnt().longValue());
                 promStockLimit.setPromId(promSku.getPromId());
                 promStockLimit.setRemaindCnt(promStockLimit.getPromCnt());
                 promStockLimit.setSkuId(promSku.getSkuId());
                 promStockLimit.setCreateStaff(promSku.getCreateStaff());
                 promStockLimit.setCreateTime(new Timestamp(DateUtil.getSysDate().getTime()));
                 
                 
                 CopyPromSkuTaskSVImpl task1 = new CopyPromSkuTaskSVImpl(promSku,promStockLimit,null);  
                 threadExecutor.execute( task1 );  
             }
              //countDownLatch.await();
              
              threadExecutor.shutdown();   
             
        }catch(Exception ex){
            LogUtil.error(MODULE, "异常信息"+ex.toString());
        }finally{
            if(threadExecutor!=null && !threadExecutor.isShutdown()){
                threadExecutor.shutdown();  
            }
        }
    }
    @Override
    public long countNum(PromSkuDTO promSkuDTO) throws BusinessException {
        
        long count = 0;
        
        PromSkuCriteria criteria = new PromSkuCriteria();
        
        criteria.createCriteria().andPromIdEqualTo(promSkuDTO.getPromId()).andSkuIdEqualTo(promSkuDTO.getSkuId()).andIfValidEqualTo(promSkuDTO.getIfValid());
        
        try {
            count = promSkuMapper.countByExample(criteria);

        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, "异常信息"+e.toString());
            throw e;
        }
        
        return count;
    }
    
    
    
    /**
     * TODO查询 商品列表
     * @see com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuSV#querySkuPromforPage(com.zengshi.ecp.prom.dubbo.dto.PromSkuDTO)
     * @param promSkuDTO
     * @return
     * @throws BusinessException
     * @author lisp
     */
    @Override
    public PageResponseDTO<KillGdsInfoDTO> querySkuPromforPage(PromSkuDTO promSkuDTO)
            throws BusinessException{

        PromSkuCriteria example = new PromSkuCriteria();
        // 初始化查询页数 数量
        example.setLimitClauseCount(promSkuDTO.getPageSize());
        example.setLimitClauseStart(promSkuDTO.getStartRowIndex());
        // 排序
        example.setOrderByClause("id desc");
        // 初始化查询条件
        Criteria cr = example.createCriteria();
        //促销编码
        if(!StringUtil.isEmpty(promSkuDTO.getPromId())){
            cr.andPromIdEqualTo(promSkuDTO.getPromId());
        }
        cr.andStatusEqualTo(PromConstants.PromInfo.STATUS_10);
        cr.andIfValidEqualTo(PromConstants.PromSku.IF_VALID_1);
        // 返回查询分页结果集
        return super.queryByPagination(promSkuDTO, example, true,
                new PaginationCallback<PromSku, KillGdsInfoDTO>() {
                    // 查询记录集
                    @Override
                    public List<PromSku> queryDB(BaseCriteria example) {

                        return promSkuMapper.selectByExample((PromSkuCriteria) example);
                    }

                    // 查询总记录数
                    @Override
                    public long queryTotal(BaseCriteria example) {
                        return promSkuMapper.countByExample((PromSkuCriteria) example);
                    }
                    // 查询结果转换
                    @Override
                    public KillGdsInfoDTO warpReturnObject(PromSku t) {
                        return promKillGdsInfoDTOConverter.convert(t);
                    }
                });
    }
    
    
}
