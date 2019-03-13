package com.zengshi.ecp.busi.main.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.main.vo.ComponentReqVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsAdvertiseRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**积分商城-广告
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
@RequestMapping(value = "/leaflet")
public class LeafletController extends EcpBaseController {

    private static String MODULE = LeafletController.class.getName();
    
    @Resource(name = "cmsAdvertiseRSV")
    private ICmsAdvertiseRSV cmsAdvertiseRSV;
    
    @Resource(name = "cmsPlaceRSV")
    private ICmsPlaceRSV cmsPlaceRSV;
    @Resource(name = "cmsPageInfoRSV")
    private ICmsPageInfoRSV cmsPageInfoRSV;
    /**
     * qryLeafletList:(查询广告列表). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author jiangzh
     * @param model
     * @param placeId
     * @param linkType
     * @param status
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping(value = "/qryLeafletList")
    @ResponseBody
    public Map<String,Object> qryLeafletList(Model model,ComponentReqVO reqVO,HttpServletRequest request) throws Exception {
        LogUtil.info(MODULE, "==========reqVO:" + reqVO.toString() + ";");
        Map<String,Object> resultMap = new HashMap<String,Object>();
        List<CmsAdvertiseRespDTO> respList = null;
        
        // 1. 入参加工
        if(StringUtil.isNotEmpty(reqVO.getPlaceId())){//内容位置
            //1.1 查询内容位置
            CmsPlaceRespDTO placeRespDto = null;
            placeRespDto = this.qryPlaceByID(reqVO.getPlaceId(), reqVO.getStatus());
            if(placeRespDto != null && StringUtil.isNotEmpty(placeRespDto.getId())){
                if(StringUtil.isNotEmpty(reqVO.getPlaceHeight()) && StringUtil.isNotEmpty(placeRespDto.getPlaceHeight())){
                    reqVO.setPlaceHeight(placeRespDto.getPlaceHeight());
                }
                if(StringUtil.isNotEmpty(reqVO.getPlaceWidth()) && StringUtil.isNotEmpty(placeRespDto.getPlaceWidth())){
                    reqVO.setPlaceWidth(placeRespDto.getPlaceWidth());
                }
                if(StringUtil.isNotEmpty(reqVO.getPlaceSize()) && StringUtil.isNotEmpty(placeRespDto.getPlaceCount())){
                    reqVO.setPlaceSize(placeRespDto.getPlaceCount());
                }
                
                //1.2 初始化查询参数
                CmsAdvertiseReqDTO reqDTO = new CmsAdvertiseReqDTO();
                reqDTO.setPlaceId(reqVO.getPlaceId());
                if (StringUtil.isNotEmpty(reqVO.getStatus())) {//状态
                    reqDTO.setStatus(reqVO.getStatus());
                }else{
                    reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                }
                if (StringUtil.isNotEmpty(reqVO.getPlaceSize())) {//广告数量
                    reqDTO.setPageNo(1);
                    reqDTO.setPageSize(reqVO.getPlaceSize());
                }
                String standard = "";// 规格
                if (StringUtil.isNotEmpty(reqVO.getPlaceWidth()) && StringUtil.isNotEmpty(reqVO.getPlaceHeight())) {
                    standard = reqVO.getPlaceWidth() + "x" + reqVO.getPlaceHeight() + "!";
                }
                reqDTO.setPlatformType(CmsConstants.PlatformType.CMS_PLATFORMTYPE_01);
                // 设置当前时间  查询当前时间有效的广告
                reqDTO.setThisTime(DateUtil.getSysDate());
                //reqDTO.setThisTime(DateUtil.getTheDayFirstSecond(DateUtil.getSysDate()));
               
                // 2. 调用广告服务，无分页
                respList = new ArrayList<CmsAdvertiseRespDTO>();
                try{
                    PageResponseDTO<CmsAdvertiseRespDTO> pageInfo = cmsAdvertiseRSV.queryCmsAdvertisePage(reqDTO);
                    if (pageInfo != null) {
                        respList = pageInfo.getResult();
                    }
                    // 3. 返回图片URL 及链接地址
                    String baseUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
                    if (!CollectionUtils.isEmpty(respList)) {
                        for (CmsAdvertiseRespDTO dto : respList) {
                            // 3.1调文件服务器，返回图片
                            if (StringUtil.isNotBlank(dto.getVfsId())) {
                                dto.setVfsUrl(ParamsTool.getImageUrl(dto.getVfsId(),standard));
                            }
                            this.getAdLinkUrl(dto, baseUrl);
                        }
                    }
                }catch(Exception err){
                    LogUtil.error(MODULE, "查询广告出现异常:",err);
                }
            }
        }
        // 4. 返回结果
        resultMap.put("respList", respList);
        return resultMap;
    }
    /**
     * 
     * getLinkUrl:(获取广告的链接地址). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param dto
     * @param baseUrl 
     * @since JDK 1.6
     */
    private void getAdLinkUrl(CmsAdvertiseRespDTO dto,String baseUrl){
        if(StringUtil.isBlank(baseUrl)){
            baseUrl = "";
        }
        CmsSiteRespDTO mallSiteDto = CmsCacheUtil.getCmsSiteCache(1l);
        String mallSiteUrl = "";
        if(mallSiteDto != null && StringUtil.isNotBlank(mallSiteDto.getSiteUrl())){
            mallSiteUrl = mallSiteDto.getSiteUrl();
        }
        if (dto != null && StringUtil.isNotBlank(dto.getLinkUrl())) {
            String linkUrl = dto.getLinkUrl();
            
            if(CmsConstants.LinkType.CMS_LINKTYPE_01.equalsIgnoreCase(dto.getLinkType())){//商品
                if(this.isNumeric(linkUrl)){
                    linkUrl = "/gdspointdetail/"+linkUrl+"-";
                }
                linkUrl = baseUrl + linkUrl;
            }else if(CmsConstants.LinkType.CMS_LINKTYPE_02.equalsIgnoreCase(dto.getLinkType())){//公告
                if(this.isNumeric(linkUrl)){
                    linkUrl = "/info/infodetail?id="+ linkUrl;
                }
                linkUrl = mallSiteUrl+linkUrl;
            }else if(CmsConstants.LinkType.CMS_LINKTYPE_03.equalsIgnoreCase(dto.getLinkType())){
                if(this.isNumeric(linkUrl)){//页面
                    CmsPageInfoReqDTO pageInfoReqDTO = new CmsPageInfoReqDTO();
                    pageInfoReqDTO.setId(Long.valueOf(linkUrl.trim()));
                    CmsPageInfoRespDTO cmsPageInfoRespDTO = cmsPageInfoRSV.queryCmsPageInfo(pageInfoReqDTO);
                    if (cmsPageInfoRespDTO != null && StringUtil.isNotEmpty(cmsPageInfoRespDTO.getSiteUrl())) {
                        linkUrl = cmsPageInfoRespDTO.getSiteUrl();
                    } else {
                        linkUrl = "/modularcommon?pageId=" + linkUrl;
                    }
                }
                linkUrl = mallSiteUrl+linkUrl;
            }else if(CmsConstants.LinkType.CMS_LINKTYPE_04.equalsIgnoreCase(dto.getLinkType())){//其他
                String regex = "^([a-zA-Z]+:(/|\\\\){2})|([a-z0-9A-Z]+(?:-[a-z0-9A-Z]+)*\\.){2,}";//常见的 绝对url格式  并非全部
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(linkUrl);
                if(m.lookingAt()){//绝对地址
                    if(linkUrl.indexOf("://") < 0){
                        linkUrl = "http://"+linkUrl; 
                    }
                }else{//相对地址
                    if(!linkUrl.startsWith("/"));{
                        linkUrl = "/"+linkUrl;
                    }
                    linkUrl = baseUrl+linkUrl;
                }
            }
            
            //加adid  用于广告行为分析   add by zhanbh  2016.9.5
            String linkUrlPro = linkUrl;
            if(StringUtil.isNotEmpty(dto.getId())){
                String [] urlParts = linkUrlPro.split("\\?");
                linkUrlPro = "";
                if(urlParts != null && urlParts.length > 0){
                    int len = urlParts.length;
                    String adidStr = "?adid=";
                    adidStr += String.valueOf(dto.getId());
                    if(len > 1){
                        adidStr += "&"; 
                    }
                    
                    for (int i = 0; i < len ; i++){
                        linkUrlPro += urlParts[i];
                        if(i==0){
                            linkUrlPro += adidStr;
                        }else if(i < len - 1){
                            linkUrlPro += "?";
                        }
                    }
                }
                if(StringUtil.isNotBlank(linkUrlPro)){
                    linkUrl =   linkUrlPro;
                }
            }
            
            dto.setLinkUrl(linkUrl);
        }
    }
    /**
     * 
     * qryPlaceByID:(查询内容位置). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param placeId
     * @param status
     * @return 
     * @since JDK 1.6
     */
    private CmsPlaceRespDTO  qryPlaceByID(Long placeId,String status){
        CmsPlaceRespDTO respDto = null;
        if(StringUtil.isNotEmpty(placeId)){
            if(StringUtil.isBlank(status)){//默认查询有效的广告位置
                status = CmsConstants.ParamStatus.CMS_PARAMSTATUS_1;
            }
            
            CmsPlaceReqDTO reqDto= new CmsPlaceReqDTO();
            reqDto.setId(placeId);
            reqDto.setStatus(status);
            try {
                respDto = cmsPlaceRSV.queryCmsPlace(reqDto);
            } catch (Exception e) {
                LogUtil.error(MODULE, "广告查询中查询内容位置出错！");
                respDto = null;
            }
            
        }
        
        return respDto;
    }
    /**
     * 
     * isNumeric:(判断字符串是否为纯数字). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh
     * @param str
     * @return 
     * @since JDK 1.6
     */
    private boolean isNumeric(String str){
        if(StringUtil.isNotEmpty(str)){
            Pattern pattern = Pattern.compile("[0-9]*"); 
            Matcher isNum = pattern.matcher(str);
            if( isNum.matches() ){
                return true; 
            }
        }
         
        return false; 
     }
}
