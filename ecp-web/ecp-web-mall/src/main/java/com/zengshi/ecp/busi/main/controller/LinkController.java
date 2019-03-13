package com.zengshi.ecp.busi.main.controller;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.cms.dubbo.dto.CmsLinkReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLinkRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsLinkRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**商城-行业资讯
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015年8月17日下午6:54:49 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "/link")
public class LinkController extends EcpBaseController {

    private static String MODULE = LinkController.class.getName();
    
    private static final Long PARENTCODE = 0l;//根节点的父节点标识
    
    private static final String LINKTYPE_FRIEND = "01";
    @Resource(name = "cmsLinkRSV")
    private ICmsLinkRSV cmsLinkRSV;
    
    /**
     * 
     * gethlinkvm:(获取首页底部链接vm). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/gethlinkvm")
    public String getHLinkVM(Model model){
        List<CmsLinkRespDTO> links= null;
        String errMsg = null;
        try {
            links = this.qryTwoLLink(PARENTCODE);
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取首页底部链接vm异常");
            errMsg = "服务异常";
        }
        model.addAttribute("linkList", links);
        model.addAttribute("errMsg", errMsg);
        return "/main/child/home-links";
    }
    /**
     * 
     * qryTwoLLink:(获取两级链接). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param pLinkId
     * @return 
     * @throws Exception 
     * @since JDK 1.6
     */
    private List<CmsLinkRespDTO> qryTwoLLink(Long pLinkId) throws Exception{
        LogUtil.info(MODULE, "获取两级链接开始");
        List<CmsLinkRespDTO> links= null;
        if(StringUtil.isEmpty(pLinkId)){
            pLinkId = PARENTCODE;
        }
        try {
            links = this.qryCLinkList(pLinkId); 
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取两级链接第一级链接异常:链接id为："+pLinkId);
            throw e;
        }
        if(CollectionUtils.isNotEmpty(links)){
            for(CmsLinkRespDTO link : links){
                if(null != link && StringUtil.isNotEmpty(link.getId())){
                    try {
                        link.setChildList(this.qryCLinkList(link.getId())); 
                    } catch (Exception e) {
                        LogUtil.error(MODULE, "获取两级链接,第二级链接id为"+link.getId()+"异常");
                        throw e;
                    }        
                }
            }
        }
        LogUtil.info(MODULE, "获取两级链接结束：无异常");
        return links;
    }
   /**
     * 
     * qryCLinkList:(获取子链接). <br/> 
     * 
     * @author zhanbh 
     * @param pLinkId
     * @return 
     * @throws Exception 
     * @since JDK 1.6
     */
    private List<CmsLinkRespDTO> qryCLinkList(Long pLinkId) throws Exception{
        LogUtil.info(MODULE, "获取子链接信息开始");
        if(StringUtil.isEmpty(pLinkId)){
            pLinkId = PARENTCODE;
        }
        LogUtil.info(MODULE, "获取子链接父链接为:"+pLinkId);
        List<CmsLinkRespDTO> list = null;
        CmsLinkReqDTO reqDTO = new CmsLinkReqDTO();
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        reqDTO.setLinkParent(pLinkId);
        reqDTO.setSiteId(reqDTO.getCurrentSiteId());
        reqDTO.setLinkType(LINKTYPE_FRIEND);
        try {
            list = cmsLinkRSV.queryCmsLinkList(reqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取子链接信息异常：",e);
            throw new Exception("获取子链接信息异常");
        }
        LogUtil.info(MODULE, "获取子链接信息结束：成功");
        return list;
    }

}
