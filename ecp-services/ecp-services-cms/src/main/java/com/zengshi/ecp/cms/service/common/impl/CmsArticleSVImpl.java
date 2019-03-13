package com.zengshi.ecp.cms.service.common.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsArticleMapper;
import com.zengshi.ecp.cms.dao.model.CmsArticle;
import com.zengshi.ecp.cms.dao.model.CmsArticleCriteria;
import com.zengshi.ecp.cms.dao.model.CmsArticleCriteria.Criteria;
import com.zengshi.ecp.cms.dao.model.CmsWenTempWithBLOBs;
import com.zengshi.ecp.cms.dubbo.dto.CmsArticleReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsArticleRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsChannelReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsChannelResDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsArticleSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsChannelSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsWenTempSV;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

@Service("cmsArticleSV")
public class CmsArticleSVImpl extends GeneralSQLSVImpl implements ICmsArticleSV{

	@Resource(name = "SEQ_CMS_ARTICLE")
    private PaasSequence seqCmsArticle;
    
    @Resource
    private CmsArticleMapper cmsArticleMapper;

    @Resource
    private ICmsSiteSV cmsSiteSV;
    
    @Resource
    private ICmsChannelSV cmsChannelSV;
    
    @Resource
    private ICmsWenTempSV cmsWenTempSV;
    
    
    /** 
     * inportOldWenToArticle:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(将旧官网数据导入到新官网文章表).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @throws BusinessException
     * @throws UnsupportedEncodingException 
     * @since JDK 1.6 
     */ 
    @Override
    public void inportOldWenToArticle() throws BusinessException, UnsupportedEncodingException{
        
        //1.先删除之前导入的旧数据
        CmsArticleCriteria example = new CmsArticleCriteria();
        Criteria criteria = example.createCriteria();
        criteria.andArticleRemarkLike("%旧官网数据导入%");
        cmsArticleMapper.deleteByExample(example);
        
        //2.查询旧数据
        List<CmsWenTempWithBLOBs> cmsWenTempList =  cmsWenTempSV.queryCmsWenTempList();
        
        //3.再将旧数据导入
        if(CollectionUtils.isNotEmpty(cmsWenTempList)){
            for(int i=0;i<cmsWenTempList.size();i++){
                CmsWenTempWithBLOBs wenTemp = cmsWenTempList.get(i);
                if(cmsWenTempList.get(i) != null){
                    CmsArticleReqDTO dto = new CmsArticleReqDTO();
                    Date date=new Date();
                    dto.setArticleTitle(wenTemp.getDoctitle());
                    dto.setSiteId(3L);//官网
                    dto.setChannelId(1003L);//新闻栏目
                    dto.setIstop("0");//否
                    dto.setHomepageIsShow("0");//否
                    dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                    dto.setPubTime(wenTemp.getCrtime());
                    dto.setLostTime(DateUtil.getTimestamp("2050-12-30"));
                    dto.setCreateTime(new Timestamp(date.getTime()));
                    dto.setCreateStaff(dto.getCreateStaff());  
                    dto.setArticleRemark("旧官网数据导入");
                    
                    String staticId = FileUtil.saveFile(wenTemp.getImporttext().getBytes("utf-8"),"oldWen", ".html");
                    dto.setStaticId(staticId);
                    
                    this.addCmsArticle(dto);
                }
            }
        }
    }
    
	/** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsArticleSV#addCmsArticle(com.zengshi.ecp.cms.dubbo.dto.CmsArticleReqDTO) 
     */
    @Override
    public CmsArticleRespDTO addCmsArticle(CmsArticleReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsArticle bo = new CmsArticle();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsArticle.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsArticleMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsArticleRespDTO respDTO = new CmsArticleRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsArticleSV#updateCmsArticle(com.zengshi.ecp.cms.dao.model.CmsArticle)
     */
    @Override
    public CmsArticleRespDTO updateCmsArticle(CmsArticleReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsArticle bo = new CmsArticle();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新文章的原子方法*/
        return this.updateCmsArticle(bo);
    }
    
    /** 
     * updateCmsArticle:(更新文章的原子方法). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param bo
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public CmsArticleRespDTO updateCmsArticle(CmsArticle bo) throws BusinessException {
        cmsArticleMapper.updateByPrimaryKeySelective(bo);
        /*3.将结果返回*/
        CmsArticleRespDTO respDTO = new CmsArticleRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsArticleSV#deleteCmsArticle(java.lang.Long)
     */
    @Override
    public void deleteCmsArticle(String id) throws BusinessException {
        CmsArticle bo = new CmsArticle();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsArticle(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsArticleSV#deleteCmsArticleBatch(java.util.List)
     */
    @Override
    public void deleteCmsArticleBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsArticle(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsArticleSV#changeStatusCmsArticle(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsArticle(String id, String status) throws BusinessException {
        CmsArticle bo = new CmsArticle();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsArticle(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsArticleSV#changeStatusCmsArticleBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsArticleBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsArticle(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsArticleSV#queryCmsArticle(com.zengshi.ecp.cms.dubbo.dto.CmsArticleReqDTO)
     */
    @Override
    public CmsArticleRespDTO queryCmsArticle(CmsArticleReqDTO dto) throws BusinessException {
        CmsArticleRespDTO cmsArticleRespDTO = new CmsArticleRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsArticle bo = cmsArticleMapper.selectByPrimaryKey(dto.getId());
            cmsArticleRespDTO = conversionObject(bo);
        }
        
        return cmsArticleRespDTO;
    }
    
    /**
     * TODO 查询文章列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsArticleSV#queryCmsArticleList(com.zengshi.ecp.cms.dubbo.dto.CmsArticleReqDTO)
     */
    @Override
    public List<CmsArticleRespDTO> queryCmsArticleList(CmsArticleReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsArticleCriteria cmsArticleCriteria = new CmsArticleCriteria();
        Criteria criteria = cmsArticleCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotBlank(dto.getArticleTitle())) {
            criteria.andArticleTitleLike("%" + dto.getArticleTitle() + "%");
        }
        if (StringUtil.isNotBlank(dto.getAuthorName())) {
            criteria.andAuthorNameLike("%" + dto.getAuthorName() + "%");
        }
        if (StringUtil.isNotEmpty(dto.getSource())){//来源条件查询
        	criteria.andSourceEqualTo(dto.getSource());
        }
        if (StringUtil.isNotEmpty(dto.getIstop())){
        	criteria.andIstopEqualTo(dto.getIstop());
        }
        if (StringUtil.isNotEmpty(dto.getHomepageIsShow())){
            criteria.andHomepageIsShowEqualTo(dto.getHomepageIsShow());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        if (StringUtil.isNotEmpty(dto.getSiteId())) {
            criteria.andSiteIdEqualTo(dto.getSiteId());
        }
        if (StringUtil.isNotEmpty(dto.getChannelId())){//栏目查询
        	criteria.andChannelIdEqualTo(dto.getChannelId());
        }
        if (null!=dto.getChannelIds()&&dto.getChannelIds().size()>0){//栏目查询   in 操作
        	criteria.andChannelIdIn(dto.getChannelIds());
        }
        if(StringUtil.isNotEmpty(dto.getStartPubTime())&&StringUtil.isNotEmpty(dto.getEndPubTime())){
            criteria.andPubTimeBetween(dto.getStartPubTime(), dto.getEndPubTime());
        }else if(StringUtil.isNotEmpty(dto.getStartPubTime())){
            criteria.andPubTimeGreaterThanOrEqualTo(dto.getStartPubTime()); 
        }else if (StringUtil.isNotEmpty(dto.getEndPubTime())) {
            criteria.andPubTimeLessThanOrEqualTo(dto.getEndPubTime());
        }
        if(StringUtil.isNotEmpty(dto.getStartLostTime())&&StringUtil.isNotEmpty(dto.getEndLostTime())){
            criteria.andLostTimeBetween(dto.getStartLostTime(), dto.getEndLostTime());
        }else if (StringUtil.isNotEmpty(dto.getStartLostTime())) {
            criteria.andLostTimeGreaterThanOrEqualTo(dto.getStartLostTime());
        }else if (StringUtil.isNotEmpty(dto.getEndLostTime())) {
            criteria.andLostTimeLessThanOrEqualTo(dto.getEndLostTime());
        }
        if(StringUtil.isNotEmpty(dto.getThisTime())){
            criteria.andPubTimeLessThanOrEqualTo(dto.getThisTime()); 
            criteria.andLostTimeGreaterThanOrEqualTo(dto.getThisTime());
        }
        cmsArticleCriteria.setOrderByClause("ISTOP DESC, PUB_TIME DESC, CREATE_TIME DESC");
        
        /*2.迭代查询结果*/
        List<CmsArticleRespDTO> cmsArticleRespDTOList =  new ArrayList<CmsArticleRespDTO>();
        List<CmsArticle> cmsArticleList = cmsArticleMapper.selectByExample(cmsArticleCriteria);
        if(CollectionUtils.isNotEmpty(cmsArticleList)){
            for(CmsArticle bo:cmsArticleList){
                CmsArticleRespDTO cmsArticleRespDTO = conversionObject(bo);
                cmsArticleRespDTOList.add(cmsArticleRespDTO);
            }
        }
        return cmsArticleRespDTOList;
    }


    /** 
     * TODO 查询文章，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsArticleSV#queryCmsArticlePage(com.zengshi.ecp.cms.dubbo.dto.CmsArticleReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsArticleRespDTO> queryCmsArticlePage(CmsArticleReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索文章 */
        CmsArticleCriteria cmsArticleCriteria = new CmsArticleCriteria();
        Criteria criteria = cmsArticleCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotBlank(dto.getArticleTitle())) {
            criteria.andArticleTitleLike("%" + dto.getArticleTitle() + "%");
        }
        if (StringUtil.isNotBlank(dto.getAuthorName())) {
            criteria.andAuthorNameLike("%" + dto.getAuthorName() + "%");
        }
        if (StringUtil.isNotEmpty(dto.getSource())){//来源条件查询
        	criteria.andSourceEqualTo(dto.getSource());
        }
        if (StringUtil.isNotEmpty(dto.getIstop())){
        	criteria.andIstopEqualTo(dto.getIstop());
        }
        if (StringUtil.isNotEmpty(dto.getHomepageIsShow())){
            criteria.andHomepageIsShowEqualTo(dto.getHomepageIsShow());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        if (StringUtil.isNotEmpty(dto.getSiteId())) {
            criteria.andSiteIdEqualTo(dto.getSiteId());
        }
        if (StringUtil.isNotEmpty(dto.getChannelId())){//栏目查询
        	criteria.andChannelIdEqualTo(dto.getChannelId());
        }
        if (null!=dto.getChannelIds()&&dto.getChannelIds().size()>0){//栏目查询   in 操作
        	criteria.andChannelIdIn(dto.getChannelIds());
        }
        if(StringUtil.isNotEmpty(dto.getStartPubTime())&&StringUtil.isNotEmpty(dto.getEndPubTime())){
            criteria.andPubTimeBetween(dto.getStartPubTime(), dto.getEndPubTime());
        }else if(StringUtil.isNotEmpty(dto.getStartPubTime())){
            criteria.andPubTimeGreaterThanOrEqualTo(dto.getStartPubTime()); 
        }else if (StringUtil.isNotEmpty(dto.getEndPubTime())) {
            criteria.andPubTimeLessThanOrEqualTo(dto.getEndPubTime());
        }
        if(StringUtil.isNotEmpty(dto.getStartLostTime())&&StringUtil.isNotEmpty(dto.getEndLostTime())){
            criteria.andLostTimeBetween(dto.getStartLostTime(), dto.getEndLostTime());
        }else if (StringUtil.isNotEmpty(dto.getStartLostTime())) {
            criteria.andLostTimeGreaterThanOrEqualTo(dto.getStartLostTime());
        }else if (StringUtil.isNotEmpty(dto.getEndLostTime())) {
            criteria.andLostTimeLessThanOrEqualTo(dto.getEndLostTime());
        }
        if(StringUtil.isNotEmpty(dto.getThisTime())){
            criteria.andPubTimeLessThanOrEqualTo(dto.getThisTime()); 
            criteria.andLostTimeGreaterThanOrEqualTo(dto.getThisTime());
        }
        cmsArticleCriteria.setOrderByClause(" ISTOP DESC, SORT_NO ASC, PUB_TIME DESC, CREATE_TIME DESC");
        cmsArticleCriteria.setLimitClauseCount(dto.getPageSize());
        cmsArticleCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsArticleCriteria,false,new PaginationCallback<CmsArticle, CmsArticleRespDTO>(){

            @Override
            public List<CmsArticle> queryDB(BaseCriteria criteria) {
                return cmsArticleMapper.selectByExample((CmsArticleCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsArticleMapper.countByExample((CmsArticleCriteria)criteria);
            }

            @Override
            public CmsArticleRespDTO warpReturnObject(CmsArticle bo) {
                return  conversionObject(bo);
            }
        
        });

    }
    
    /** 
     * conversionObject:(将bo转换成DTO，同时翻译有关字段). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param bo 
     * @return 
     * @since JDK 1.6 
     */ 
    private CmsArticleRespDTO conversionObject(CmsArticle bo){
        CmsArticleRespDTO dto = new CmsArticleRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        // 1 查询站点服务，获取站点对应的名称
        if (StringUtil.isNotEmpty(dto.getSiteId())) {
            CmsSiteReqDTO reqDTO = new CmsSiteReqDTO();
            reqDTO.setId(bo.getSiteId());
            CmsSiteRespDTO respDTO = cmsSiteSV.queryCmsSite(reqDTO);
            if(respDTO != null){
                dto.setSiteZH(respDTO.getSiteName());
            }
        }
        
        // 2 查询栏目服务，获取栏目对应的名称
        if (StringUtil.isNotEmpty(dto.getChannelId())) {
            CmsChannelReqDTO reqDTO = new CmsChannelReqDTO();
            reqDTO.setId(bo.getChannelId());
            CmsChannelResDTO respDTO = cmsChannelSV.find(reqDTO);
            if(respDTO != null){
            	dto.setChannelZH(respDTO.getChannelName());
            }
        }
        
        //4.遍历将编码转中文 
        /*String sourceZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_SOURCE, dto.getSource());
        dto.setSourceZH(sourceZH);*/
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        String istopZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.PUBLIC_PARAM_ISNOT, dto.getIstop());
        dto.setIstopZH(istopZH);
        String homepageIsShowZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.PUBLIC_PARAM_ISNOT, dto.getHomepageIsShow());
        dto.setHomepageIsShowZH(homepageIsShowZH);
        
        return dto;
    }
}
