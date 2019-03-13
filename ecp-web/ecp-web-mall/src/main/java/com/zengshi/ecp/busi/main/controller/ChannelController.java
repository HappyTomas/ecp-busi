package com.zengshi.ecp.busi.main.controller;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.main.vo.CmsChannelReqVO;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.utils.CmsGoodsDetailUtil;
import com.zengshi.ecp.cms.dubbo.dto.CmsArticleReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsArticleRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsChannelReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsChannelResDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsArticleRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsChannelRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**商城-栏目
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015年8月17日下午6:54:49 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "/channel")
public class ChannelController extends EcpBaseController {

    private static String MODULE = ChannelController.class.getName();
    
    @Resource(name="cmsChannelRSV")
    private ICmsChannelRSV cmsChannelRSV;
    
    @Resource(name = "cmsArticleRSV")
    private ICmsArticleRSV cmsArticleRSV;

    /**
     * 
     * qryChannel:(查询栏目). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 2015.11.21
     * @author zhanbh 
     * @return VM
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="qrychannel")
    public String qryChannel(Model model,CmsChannelReqVO reqVo) throws Exception{
    	if(reqVo == null){
    		reqVo = new CmsChannelReqVO();
    	}
        LogUtil.info(MODULE, "==========reqVO :" + reqVo.toString() + ";");
        //1.声明必须的变量
        String url = "/main/child/channel-";//VM地址
        //设置路径
        url = url + reqVo.getPath();
        CmsChannelReqDTO reqDto = new CmsChannelReqDTO();//查询条件
        List<CmsChannelResDTO> channelsList = null;//栏目数据
        Long channelParentCode = 0l;//0代表根节点
        
        //2.用VO初始化查询条件
        //设置站点
        reqDto.setSiteId(reqDto.getCurrentSiteId());
        //设置状态为有效
        if(StringUtil.isNotBlank(reqVo.getStatus())){
        	reqDto.setStatus(reqVo.getStatus());
        }else{
        	reqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        }
        //平台类型
        if(StringUtil.isNotBlank(reqVo.getPlatformType())){
        	reqDto.setPlatformType(reqVo.getPlatformType());//默认WEB
        }else{
        	reqDto.setPlatformType(CmsConstants.PlatformType.CMS_PLATFORMTYPE_01);//默认WEB
        }
        //导航栏目
        if(StringUtil.isNotBlank(reqVo.getChannelType())){
        	reqDto.setChannelType(reqVo.getChannelType());
        }else{
        	reqDto.setChannelType(CmsConstants.ChannelType.CMS_CHANNEL_TYPE_01);
        }
        
        //3.查询栏目
        try {
            //3.1 查询一级栏目
            reqDto.setChannelParent(channelParentCode);
            channelsList = cmsChannelRSV.listChannel(reqDto);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.info(MODULE, "查询栏目失败");
        }
        
        if(StringUtil.isNotBlank(reqVo.getPage())){//用于将当前页标记
        	model.addAttribute("page", reqVo.getPage());
        }
        model.addAttribute("siteId", reqDto.getSiteId());
        model.addAttribute("channelsList", channelsList);
        return url;
    }
    /**
     * getArticle:(查询文章). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author jiangzh
     * @param model
     * @param placeId
     * @param status
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping(value = "/article/{articleId}")
    public String getArticle(Model model,@PathVariable("articleId") String articleId) throws Exception {
        CmsArticleRespDTO respDTO = null;
        String errMsg = null;
        if(CmsGoodsDetailUtil.isNumeric(articleId)){
            try {
                respDTO = this.qryArticle(Long.parseLong(articleId));
            } catch (Exception e) {
                respDTO = null;
                errMsg = "查询异常";
            }
        }
        model.addAttribute("article", respDTO);
        model.addAttribute("errMsg", errMsg);
        return "/main/staticlink/common-article";
    }
    /**
     * 
     * qryArticle:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param articlaId
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    private CmsArticleRespDTO qryArticle(Long articlaId) throws Exception{
        LogUtil.info(MODULE, "查询文章开始：");
        if(StringUtil.isEmpty(articlaId)){
            LogUtil.error(MODULE, "查询文章结束：文章id为空");
            throw new Exception("文章id为空");
        }
        LogUtil.info(MODULE, "查询id为: "+articlaId + " 的文章");
        CmsArticleReqDTO reqDTO = new CmsArticleReqDTO();
        CmsArticleRespDTO respDto = null;
        reqDTO.setId(articlaId);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        reqDTO.setSiteId(reqDTO.getCurrentSiteId());
        try {
            respDto = cmsArticleRSV.queryCmsArticle(reqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询文章异常：",e);
            throw new Exception("查询文章异常");
        }
        if(null != respDto && StringUtil.isNotBlank(respDto.getStaticId())){
            respDto.setStaticUrl(ImageUtil.getStaticDocUrl(respDto.getStaticId(), "html"));
        }
        LogUtil.info(MODULE, "查询文章结束：成功");
        return respDto;
    }
}
