package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsChannelMapper;
import com.zengshi.ecp.cms.dao.model.CmsChannel;
import com.zengshi.ecp.cms.dao.model.CmsChannelCriteria;
import com.zengshi.ecp.cms.dao.model.CmsChannelCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsChannelReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsChannelResDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteInfoRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsChannelSV;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: <br>
 * Date:2015-11-18上午10:27:49  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6
 * 
 * 栏目管理实现类
 */
@Service
public class CmsChannelSVImpl extends GeneralSQLSVImpl implements ICmsChannelSV{

    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsChannelSVImpl.class.getName();
    
    @Resource
    private CmsChannelMapper cmsChannelMapper;
    
    @Resource
    private ICmsSiteInfoRSV cmsSiteInfoRSV;
    
    @Resource(name="SEQ_CMS_CHANNEL")
    private PaasSequence seqCmsChannel;
    
    @Override
    public List<CmsChannelResDTO> listChannel(CmsChannelReqDTO record) throws BusinessException {
        
        List<CmsChannel> resultList = null;
        
        CmsChannelCriteria criteria = new CmsChannelCriteria();
        Criteria sql =  criteria.createCriteria();
        
        if(record.getId() != null)
        {
            sql.andIdEqualTo(record.getId());
        }
        if(record.getChannelParent() != null)
        {
            sql.andChannelParentEqualTo(record.getChannelParent());
        }
        if(StringUtil.isNotBlank(record.getStatus()))
        {
            sql.andStatusEqualTo(record.getStatus());
        }
        if(record.getSiteId()!= null)
        {
            sql.andSiteIdEqualTo(record.getSiteId());
        }
        if(StringUtil.isNotEmpty(record.getIsOutLink()))
        {
            sql.andIsOutLinkEqualTo(record.getIsOutLink());
        }
        if(StringUtil.isNotEmpty(record.getSiteinfoId()))
        {
            sql.andSiteinfoIdEqualTo(record.getSiteinfoId());
        }
        if(StringUtil.isNotEmpty(record.getChannelType()))
        {
            sql.andChannelTypeEqualTo(record.getChannelType());
        }
        if(StringUtil.isNotEmpty(record.getIsresiteinfo()))
        {
            sql.andIsresiteinfoEqualTo(record.getIsresiteinfo());
        }
        if(StringUtil.isNotEmpty(record.getPlatformType())){
        	sql.andPlatformTypeEqualTo(record.getPlatformType());
        }
        try {
        	criteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
            resultList = cmsChannelMapper.selectByExample(criteria);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询栏目列表出现异常:",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_CHANNEL_500103);
        }
        
        if(CollectionUtils.isNotEmpty(resultList))
        {
            List<CmsChannelResDTO> result = new ArrayList<CmsChannelResDTO>(resultList.size());
            for(CmsChannel one: resultList)
            {
                CmsChannelResDTO resDTO = new CmsChannelResDTO();
                ObjectCopyUtil.copyObjValue(one, resDTO, null, false);
                
                result.add(resDTO);
            }
            return result;
        }
        
        return null;
    }
    
    @Override
    public long insert(CmsChannelReqDTO record) {
        
        //long sortNum = 0;//排序号码
        
        CmsChannel channelModel = new CmsChannel();
        
        ObjectCopyUtil.copyObjValue(record, channelModel, null, false);
        channelModel.setId(seqCmsChannel.nextValue());
        channelModel.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        channelModel.setCreateTime(DateUtil.getSysDate());
        channelModel.setCreateStaff(record.getStaff().getStaffCode());
        
        /*if(channelModel.getChannelParent() != null)
        {
            CmsChannelCriteria criteria = new CmsChannelCriteria();
            criteria.createCriteria().andChannelParentEqualTo(channelModel.getChannelParent()).andStatusEqualTo(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            sortNum = cmsChannelMapper.countByExample(criteria);
        }
        channelModel.setSortNo(Integer.valueOf((int) (sortNum+1)));*/

        
        try {
            cmsChannelMapper.insertSelective(channelModel);
        } catch (Exception e) {
            LogUtil.error(MODULE, "新增栏目出现异常:",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_CHANNEL_500101);
        }
        
        return channelModel.getId();
    }

    @Override
    public int update(CmsChannelReqDTO record) {
        
        int count = 0;
        
        CmsChannel channelModel = new CmsChannel();
        
        ObjectCopyUtil.copyObjValue(record, channelModel, null, false);
        channelModel.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        channelModel.setUpdateTime(DateUtil.getSysDate());
        channelModel.setUpdateStaff(record.getStaff().getStaffCode());
        
        try {
        	//@修改：在更新时创建时间值会被去除掉，应该保留。 @huangxm9
            //count = cmsChannelMapper.updateByPrimaryKey(channelModel);
            count = cmsChannelMapper.updateByPrimaryKeySelective(channelModel);
        } catch (Exception e) {
            LogUtil.error(MODULE, "更新栏目出现异常:",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_CHANNEL_500102);
            
        }
        
        return count;
    }

    @Override
    public int delete(CmsChannelReqDTO record) {
        
        int count = 0;
        
        CmsChannel delRecord = new CmsChannel();
        
        delRecord.setId(record.getId());
        delRecord.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);

        //判断该栏目下是否存在子栏目
        if(exitsSubChannel(delRecord.getId()))
        {
            throw new BusinessException(CmsConstants.MsgCode.CMS_CHANNEL_500105);
        }
        
        try {
            count = cmsChannelMapper.updateByPrimaryKeySelective(delRecord);
        } catch (Exception e) {
            LogUtil.error(MODULE, "删除栏目出现异常:",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_CHANNEL_500104);
        }
        try {
            delRecord = cmsChannelMapper.selectByPrimaryKey(record.getId());
        } catch (Exception e) {
            LogUtil.error(MODULE, "删除栏目出现异常:",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_CHANNEL_500103);
        }
        //删除一个栏目时重新对在同一父栏目下的栏目进行排序
        //sortChannelSortNo(delRecord);
        
        return count;
    }

    @Override
    public CmsChannelResDTO find(CmsChannelReqDTO record) throws BusinessException {
        
        CmsChannel resultChannel = null;
        try {
            resultChannel = cmsChannelMapper.selectByPrimaryKey(record.getId());
        } catch (Exception e) {
            LogUtil.error(MODULE, "查找栏目出现异常:",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_CHANNEL_500101);
        }
        if(resultChannel != null)
        {
            CmsChannelResDTO result = new CmsChannelResDTO();
            ObjectCopyUtil.copyObjValue(resultChannel, result, null, false);
            return result;
        }
        
        return null;
    }

    /**
     * 
     * sortChannelSortNo:(删除一个栏目时重新对在同一父栏目下的栏目进行排序). <br/> 
     * 
     * @author PJieWin 
     * @param record
     * @return 
     * @since JDK 1.6
     */
    @SuppressWarnings("unused")
    private int sortChannelSortNo(CmsChannel record)
    {
        //序列重新排序
        if(record.getChannelParent()!= null)
        {
            CmsChannelCriteria criteria2 = new CmsChannelCriteria();
            Criteria sql2 =  criteria2.createCriteria();
            sql2.andChannelParentEqualTo(record.getChannelParent()).andSortNoGreaterThan(record.getSortNo());
            List<CmsChannel> oldRecordList = cmsChannelMapper.selectByExample(criteria2);
            if(CollectionUtils.isNotEmpty(oldRecordList))
            {
                for(CmsChannel one:oldRecordList)
                {
                    one.setSortNo(one.getSortNo()-1);
                    try {
                        cmsChannelMapper.updateByPrimaryKeySelective(one);
                    } catch (Exception e) {
                        LogUtil.error(MODULE, "删除栏目排序出现异常:",e);
                        throw new BusinessException(CmsConstants.MsgCode.CMS_CHANNEL_500102);
                    }
                }
            }

        }
        return 0;
    }
    
    /**
     * 
     * haveSubChannel:(判断该该栏目下是否存在子栏目). <br/> 
     * 
     * @author PJieWin 
     * @param channelId
     * @return 
     * @since JDK 1.6
     */
    private boolean exitsSubChannel(Long channelId)
    {
        long count = 0;
        
        CmsChannelCriteria criteria = new CmsChannelCriteria();
        Criteria sql =  criteria.createCriteria();
        sql.andChannelParentEqualTo(channelId).andStatusEqualTo(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        try {
            count = cmsChannelMapper.countByExample(criteria);
        } catch (Exception e) {
            LogUtil.error(MODULE, "判断该栏目下是否有子栏目出现异常:",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_CHANNEL_500101);
        }
        
        return count>0?true:false;
    }
    
}

