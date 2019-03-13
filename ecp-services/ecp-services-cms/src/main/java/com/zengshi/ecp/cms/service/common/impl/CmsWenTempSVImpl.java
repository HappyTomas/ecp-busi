package com.zengshi.ecp.cms.service.common.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsWenTempMapper;
import com.zengshi.ecp.cms.dao.model.CmsWenTempCriteria;
import com.zengshi.ecp.cms.dao.model.CmsWenTempWithBLOBs;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsWenTempSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: <br>
 * Date:2015年8月7日下午5:11:59 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version
 * @since JDK 1.6
 */
@Service("cmsWenTempSV")
public class CmsWenTempSVImpl extends GeneralSQLSVImpl implements ICmsWenTempSV {
    
    @Resource
    private CmsWenTempMapper cmsWenTempMapper;
    
    /**
     * TODO 查询楼层列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteSV#queryCmsSiteList(com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO)
     */
    @Override
    public List<CmsWenTempWithBLOBs> queryCmsWenTempList() throws BusinessException {

        /*1.组装查询条件*/
        CmsWenTempCriteria cmsSiteCriteria = new CmsWenTempCriteria();
        
        /*2.迭代查询结果*/
        List<CmsWenTempWithBLOBs> cmsSiteList = cmsWenTempMapper.selectByExampleWithBLOBs(cmsSiteCriteria);
        
        return cmsSiteList;

    }

}
