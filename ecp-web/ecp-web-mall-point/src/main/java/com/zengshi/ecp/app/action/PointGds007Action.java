package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Pointmgds007Req;
import com.zengshi.ecp.app.resp.Pointmgds007Resp;
import com.zengshi.ecp.cms.dubbo.dto.CmsHotSearchReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsHotSearchRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsHotSearchRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartRSV;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.util.SiteLocaleUtil;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 热词搜索
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016年3月10日上午11:47:48  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zjh
 * @version  
 * @since JDK 1.6
 */
@Service("pointmgds007")
@Action(bizcode = "pointmgds007", userCheck = false)
@Scope("prototype")
public class PointGds007Action extends AppBaseAction<Pointmgds007Req, Pointmgds007Resp> {
    
    @Resource
    private IOrdCartRSV ordCartRSV;
    
    @Resource
    private IShopInfoRSV shopInfoRSV;
    @Resource
    private ICmsHotSearchRSV cmsHotSearchRSV;
    
    private static final String MODULE = PointGds007Action.class.getName();
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
       
        Pointmgds007Req gds007Req = this.request;
        Pointmgds007Resp gds007Resp = this.response; 
        List<String> hotkeywords = new ArrayList<String>();

        CmsHotSearchReqDTO cmsHotSearchReqDTO = new CmsHotSearchReqDTO();
        cmsHotSearchReqDTO.setPageNo(1);
        cmsHotSearchReqDTO.setPageSize(5);
        cmsHotSearchReqDTO.setSiteId(SiteLocaleUtil.getSite());
        cmsHotSearchReqDTO.setStatus(SearchConstants.STATUS_1);
        PageResponseDTO<CmsHotSearchRespDTO> pageResp = cmsHotSearchRSV
                .queryCmsHotSearchPage(cmsHotSearchReqDTO);
        if (CollectionUtils.isNotEmpty(pageResp.getResult())) {
            for (CmsHotSearchRespDTO cmsHotSearchRespDTO : pageResp.getResult()) {
                hotkeywords.add(cmsHotSearchRespDTO.getHotSearchName());
            }
        }

        gds007Resp.setHotkeywords(hotkeywords);

    }

}
