package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Cms009Req;
import com.zengshi.ecp.app.resp.Cms009Resp;
import com.zengshi.ecp.app.resp.cms.InfoRespVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsInfoRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 获取APP信息列表服务<br>
 * Date:2016-2-22下午6:51:19  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */

@Service("cms009")
@Action(bizcode="cms009", userCheck=false)
@Scope("prototype")
public class Cms009Action extends AppBaseAction<Cms009Req, Cms009Resp> {

    private static String MODULE = Cms009Action.class.getName();
    
    private final Long  SITEID =  1l; //商城站点
   
    @Resource(name = "cmsInfoRSV")
    private ICmsInfoRSV cmsInfoRSV;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        LogUtil.info(MODULE, "==========开始进入cms009 app获取信息列表服务============");
        //1. 获取首页滚动信息数据  
        this.response.setInfoList(this.getInfoData());
        
    }
    
    /**
     * 
     * getInfoData:(获取信息列表). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param siteId
     * @return 
     * @since JDK 1.6
     */
    private List<InfoRespVO> getInfoData() {
        
        CmsInfoReqDTO reqDTO = new CmsInfoReqDTO();
        reqDTO.setPageNo(this.request.getPageNo());
        reqDTO.setPageSize(this.request.getPageSize());
        reqDTO.setSiteId(this.request.getSiteId());
        //设置有效
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        //设置发布截至时间
        reqDTO.setThisTime(DateUtil.getSysDate());
        //reqDTO.setThisTime(DateUtil.getTheDayFirstSecond(DateUtil.getSysDate()));
        List<InfoRespVO> infoList = new ArrayList<InfoRespVO>();
        try {
            PageResponseDTO<CmsInfoRespDTO> infoRespPage = cmsInfoRSV.queryCmsInfoPage(reqDTO);
            if(infoRespPage != null){
                List<CmsInfoRespDTO> infoRespList = infoRespPage.getResult();
                if(CollectionUtils.isNotEmpty(infoRespList)){
                    for(CmsInfoRespDTO dto : infoRespList){
                        if(dto!=null && StringUtil.isNotEmpty(dto.getId())){
                            InfoRespVO item = new InfoRespVO();
                            item.setInfoTitle(dto.getInfoTitle());
                            item.setTypeName(dto.getTypeName());
                            item.setPubTime(DateUtil.getDateString(dto.getPubTime(), DateUtil.DATE_FORMAT));
                            item.setSiteId(dto.getSiteId());
                            String mobileUrl = this.getMobileMallUrl();
                            if(StringUtil.isNotBlank(mobileUrl)){ //微信的促销页
                                item.setClickUrl("/pmph/webview/pageInit?url="+mobileUrl+"/info/infoDetail4App?id="+dto.getId());
                            }
                            infoList.add(item);
                        }
                    }
                }
            }
        } catch (Exception e) {
            LogUtil.info(MODULE, "==========cms009 app获取信息列表服务异常============");
            throw e;
        }
        
        return infoList;
    }
    /**
     * 
     * getMobileMallUrl:(从配置表获取微信端mall商城地址). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @return 
     * @since JDK 1.6
     */
    private String getMobileMallUrl(){
        String mobileMallUrl = "";
        try {
            mobileMallUrl = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_SITE_MAPPING,this.SITEID.toString());//微商地址
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取微信商城地址异常，读取配置表CMS_SITE_MAPPING！");
        }
        mobileMallUrl = mobileMallUrl == null ? "" : mobileMallUrl.replaceAll("(?:/|\\\\)+$", "");
        return mobileMallUrl;
    }

}

