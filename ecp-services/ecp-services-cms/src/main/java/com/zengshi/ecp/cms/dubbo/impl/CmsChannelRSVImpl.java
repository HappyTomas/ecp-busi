package com.zengshi.ecp.cms.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.cms.dubbo.dto.CmsChannelReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsChannelResDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsChannelRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsChannelSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

public class CmsChannelRSVImpl implements ICmsChannelRSV {

    @Resource
    private ICmsChannelSV cmsChannelSV;
    
    private static final String MODULE = CmsChannelRSVImpl.class.getName(); 
    
    @Override
    public long insert(CmsChannelReqDTO record) throws BusinessException{
        
        if(record == null)
        {
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102, new String[]{"CmsChannelReqDTO record"});
        }
        /*if (StringUtil.isEmpty(record.getIsresiteinfo())) {
			LogUtil.error(MODULE, "入参isresiteinfo为空！");
			String[] keyInfos = { "isresiteinfo" };
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos);
		}*/
        if (StringUtil.isEmpty(record.getPlatformType())) {
			LogUtil.error(MODULE, "入参platformType为空！");
			String[] keyInfos = { "platformType" };
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos);
		}
        long channelId =  0;
        
        if(StringUtil.isNotBlank(record.getChannelUrl())){
            record.setChannelUrl(record.getChannelUrl().replaceAll("^(/)*",""));
        }
        channelId = cmsChannelSV.insert(record);
        
        return channelId;
    }

    @Override
    public int update(CmsChannelReqDTO record) throws BusinessException{
        if(record == null || record.getId() == null)
        {
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102, new String[]{"CmsChannelReqDTO record:id"});
        }
        
        int count = 0;
        //将地址的开头/去掉
        if(StringUtil.isNotBlank(record.getChannelUrl())){
            record.setChannelUrl(record.getChannelUrl().replaceAll("^(/)*",""));
        }
        count = cmsChannelSV.update(record);
        
        return count;
    }

    @Override
    public int delete(CmsChannelReqDTO record) throws BusinessException{
        if(record == null || record.getId() == null)
        {
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102, new String[]{"CmsChannelReqDTO record:id"});
        }
        int count = 0;
        
        count = cmsChannelSV.delete(record);
        
        return count;
    }

    @Override
    public List<CmsChannelResDTO> listChannel(CmsChannelReqDTO record) throws BusinessException {
        if(record == null)
        {
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102, new String[]{"CmsChannelReqDTO record"});
        }
        
        List<CmsChannelResDTO> list = cmsChannelSV.listChannel(record);
        if(list != null){
            for(CmsChannelResDTO dto : list){
                  //将地址的开头/去掉
                  if(StringUtil.isNotBlank(dto.getChannelUrl())){
                      dto.setChannelUrl(dto.getChannelUrl().replaceAll("^(/)*",""));
                  }
              }
        }
        
        return  list;
    }

    @Override
    public CmsChannelResDTO find(CmsChannelReqDTO record) throws BusinessException {
        
        if(record == null || record.getId() == null)
        {
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102, new String[]{"CmsChannelReqDTO record:id"});
        }
        CmsChannelResDTO dto = cmsChannelSV.find(record);
        //将地址的开头/去掉
        if(dto!=null && StringUtil.isNotBlank(dto.getChannelUrl())){
            dto.setChannelUrl(dto.getChannelUrl().replaceAll("^(/)*",""));
        }
        return dto;
    }

    
}

