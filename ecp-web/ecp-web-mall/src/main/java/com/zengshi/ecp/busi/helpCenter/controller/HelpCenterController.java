package com.zengshi.ecp.busi.helpCenter.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.helpCenter.vo.CmsArticleVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsArticleReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsArticleRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsChannelReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsChannelResDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsArticleRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsChannelRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * Created by zhanbh on 16/12/14.
 */
@Controller
@RequestMapping(value = "/helpcenter")
public class HelpCenterController extends EcpBaseController{
    
    private static String MODULE = HelpCenterController.class.getName();
    private static final String BASEURL = "/helpcenter";
    private static final Long PARENTCODE = 0l;
    @Resource(name = "cmsChannelRSV")
    private ICmsChannelRSV cmsChannelRSV;
    @Resource(name = "cmsArticleRSV")
    private ICmsArticleRSV cmsArticleRSV;
    
    /**
     * 
     * helpcenter:(进入帮助中心主页). <br/> 
     * 
     * @author jiangzh 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping()
    public String helpcenter(Model model){
        String url = "/index";
        return BASEURL + url;
    }
    /**
     * 
     * helpcenter:(进入帮助中心主页，选定一个栏目). <br/> 
     * 
     * @author jiangzh 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/channel/{channelId}")
    public String helpChannel(Model model,@PathVariable("channelId") String channelId){
        String url = "/index";
        CmsChannelResDTO channel = null;
        String errMsg = null;
        
        if(this.isNumeric(channelId)){
            try {
                channel = this.qryHelpChanById(Long.parseLong(channelId));
            } catch (Exception e) {
                errMsg = "查询错误";
            }
            if(null != channel && StringUtil.isNotEmpty(channel.getId())){
                model.addAttribute("channelId", channel.getId());
                model.addAttribute("channelName", channel.getChannelName());
            }else if(null == channel && null == errMsg){
                errMsg = "无效信息";
            }
        }else if(StringUtil.isNotBlank(channelId)){
            errMsg = "参数错误";
        }
        model.addAttribute("errMsg", errMsg);
        return BASEURL + url;
    }
    /**
     * 
     * helpArticle:(进入帮助中心主页,选定一篇文章). <br/> 
     * 
     * @author jiangzh 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/article/{articleId}")
    public String helpArticle(Model model,@PathVariable("articleId") String articleId){
        String url = "/index";
        CmsArticleRespDTO respDTO = null;
        CmsChannelResDTO channel = null;
        String errMsg = null;
        if(this.isNumeric(articleId)){
            try {
                respDTO = this.qryArticle(Long.parseLong(articleId));
            } catch (Exception e) {
                respDTO = null;
                errMsg = "查询异常";
            }
            if(null != respDTO && StringUtil.isNotEmpty(respDTO.getId())){
                try {
                    channel = this.qryHelpChanById(respDTO.getChannelId());
                } catch (Exception e) {
                    respDTO = null;
                    errMsg = "查询异常";
                }
            }
            if((null == channel || StringUtil.isEmpty(channel.getId()))&& null == errMsg){
                respDTO = null;
                errMsg =  "无效信息";
            }
        }else{
            errMsg = "参数错误";
        }
        
        if(null != respDTO){
            model.addAttribute("articleId", articleId);
            model.addAttribute("articleName", respDTO.getArticleTitle());
        }
        if(null != channel){
            model.addAttribute("channelId", channel.getId());
            model.addAttribute("channelName", channel.getChannelName());
        }
        model.addAttribute("errMsg", errMsg);
        return BASEURL + url;
    }
    /**
     * 
     * getChanBar:(获取两级栏目). <br/> 
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
    @RequestMapping(value = "/getchanbar")
    public String getChanBar(Model model){
        String url = "/child/help-bar";
        List<CmsChannelResDTO> channels= new ArrayList<CmsChannelResDTO>();
        String errMsg = null;
        try {
            List<CmsChannelResDTO> channels1 = this.qryHelpChanList(PARENTCODE,CmsConstants.ChannelType.CMS_CHANNEL_TYPE_04);//帮助中心栏目 
            List<CmsChannelResDTO> channels2 = this.qryHelpChanList(PARENTCODE,CmsConstants.ChannelType.CMS_CHANNEL_TYPE_05);//客服中心栏目
            if(CollectionUtils.isNotEmpty(channels1)){
                channels.addAll(channels1);
            }
            if(CollectionUtils.isNotEmpty(channels2)){
                channels.addAll(channels2);
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取帮助、客服栏目异常",e);
            errMsg = "服务异常";
        }
        if(CollectionUtils.isNotEmpty(channels)){
            for(CmsChannelResDTO chan : channels){
                if(null != chan && StringUtil.isNotEmpty(chan.getId())){
                    try {
                        chan.setChildList(this.qryHelpChanList(chan.getId(),chan.getChannelType())); 
                    } catch (Exception e) {
                        LogUtil.error(MODULE, "获取帮助中心,栏目id为"+chan.getId()+"的二级栏目异常",e);
                        errMsg = "服务异常";
                    }        
                }
            }
        }
        model.addAttribute("channelList", channels);
        model.addAttribute("errMsg", errMsg);
        return BASEURL + url;
    }
    /**
     * getArtiPage:(查询文章列表). <br/>
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
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/getartipage")
    public String getArtiPage(Model model,CmsArticleVO articleVO) throws Exception {
        PageResponseDTO<CmsArticleRespDTO> respDTO = null;
        CmsChannelResDTO channel = null;
        String errMsg = null;
        if(StringUtil.isNotEmpty(articleVO.getChannelId())){
            try {
                channel = this.qryHelpChanById(articleVO.getChannelId());
            } catch (Exception e) {
                channel = null;
                errMsg = "查询异常";
            }
            if(null != channel){
                try {
                    respDTO = this.qryArticleOfChan(articleVO.getChannelId(), articleVO.getPageNumber(), articleVO.getPageSize());
                } catch (Exception e) {
                    errMsg = "查询异常";
                } 
            }else{
                if(StringUtil.isBlank(errMsg)){
                    errMsg = "无效信息";
                }
            }
        }
        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        if(null == respDTO){
            respDTO = new PageResponseDTO<CmsArticleRespDTO>();
        }
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(respDTO);
        model.addAttribute("respVO", respVO);
        model.addAttribute("channelId", articleVO.getChannelId());
        model.addAttribute("errMsg", errMsg);
        return BASEURL + "/child/artiPage";
    }
    /**
     * getArti:(查询文章). <br/>
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
    @RequestMapping(value = "/getarti")
    public String getArti(Model model,CmsArticleVO articleVO) throws Exception {
        CmsArticleRespDTO respDTO = null;
        CmsChannelResDTO channel = null;
        String errMsg = null;
        if(StringUtil.isNotEmpty(articleVO.getId())){
            try {
                respDTO = this.qryArticle(articleVO.getId());
            } catch (Exception e) {
                respDTO = null;
                errMsg = "查询异常";
            }
            if(null != respDTO){
                try {
                    channel = this.qryHelpChanById(respDTO.getChannelId());
                } catch (Exception e) {
                    respDTO = null;
                    errMsg = "查询异常";
                }
                if(null == channel && StringUtil.isBlank(BASEURL)){
                    errMsg =  "无效信息";
                }
            }
        }
        model.addAttribute("article", respDTO);
        model.addAttribute("channelId", articleVO.getChannelId());
        model.addAttribute("errMsg", errMsg);
        return BASEURL + "/child/article";
    }
    /**
     * 
     * getFaqList:(获取常见问题列表). <br/> 
     * 
     * @author yedw 
     * @param model
     * @param siteId 站点id
     * @param channelType 栏目类型  01导航栏目，02信息栏目，03投票栏目，04帮助中心，05客服中心
     * @param getNum 取数数量  可不填，默认10条
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/getFaqList")
    @ResponseBody
    public List<CmsArticleRespDTO> getFaqList(Model model,Long siteId,String channelType,Integer getNum) throws Exception {
        List<CmsArticleRespDTO> resultList= null;
        if(null==getNum){
            getNum=10;
        }
        resultList = this.qryFagList(siteId,channelType,getNum);
        return resultList;
    }
    /**
     * 
     * qryHelpChanList:(获取帮助中心子栏目). <br/> 
     * 
     * @author jiangzh 
     * @param channelId
     * @return 
     * @throws Exception 
     * @since JDK 1.6
     */
    private List<CmsChannelResDTO> qryHelpChanList(Long pChanId,String channelType) throws Exception{
        LogUtil.info(MODULE, "查询帮助中心子栏目信息开始");
        if(StringUtil.isEmpty(pChanId)){
            pChanId = PARENTCODE;
        }
        LogUtil.info(MODULE, "查询帮助中心子栏目父栏目为:"+pChanId);
        List<CmsChannelResDTO> list = null;
        CmsChannelReqDTO reqDTO = new CmsChannelReqDTO();
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        reqDTO.setChannelType(channelType);//栏目类型：帮助中心或者客服中心
        reqDTO.setChannelParent(pChanId);
        reqDTO.setPlatformType(CmsConstants.PlatformType.CMS_PLATFORMTYPE_01);
        reqDTO.setSiteId(reqDTO.getCurrentSiteId());
        try {
            list = cmsChannelRSV.listChannel(reqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询帮助中心子栏目信息异常：",e);
            throw new Exception("查询帮助中心子栏目信息异常");
        }
        LogUtil.info(MODULE, "查询帮助中心子栏目信息结束：成功");
        return list;
    }
    /**
     * 
     * qryHelpChanById:(查询帮助中心栏目信息). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param chanId
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    private CmsChannelResDTO qryHelpChanById(Long chanId) throws Exception{
        LogUtil.info(MODULE, "查询帮助中心栏目信息开始");
        if(StringUtil.isEmpty(chanId)){
            LogUtil.error(MODULE, "查询帮助中心栏目信息结束：栏目id为空");
            throw new Exception("栏目id为空"); 
        }
        LogUtil.info(MODULE, "查询帮助中心栏目id为:"+chanId);
        List<CmsChannelResDTO> resultList = null;
        CmsChannelResDTO result = null;
        CmsChannelReqDTO reqDTO = new CmsChannelReqDTO();
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        reqDTO.setPlatformType(CmsConstants.PlatformType.CMS_PLATFORMTYPE_01);
        reqDTO.setId(chanId);
        reqDTO.setSiteId(reqDTO.getCurrentSiteId());
        try {
            resultList = cmsChannelRSV.listChannel(reqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询帮助中心栏目信息异常：",e);
            throw new Exception("查询帮助中心栏目信息异常");
        }
        if(CollectionUtils.isNotEmpty(resultList)){
            result = resultList.get(0);
        }
        LogUtil.info(MODULE, "查询帮助中心栏目信息结束：成功");
        return result;
    }
    /**
     * 
     * qryArticleOfChan:(根据栏目ID获取文章列表). <br/> 
     * 
     * @author jiangzh 
     * @param channelId
     * @return 
     * @throws Exception 
     * @since JDK 1.6
     */
    private PageResponseDTO<CmsArticleRespDTO> qryArticleOfChan(Long channelId,Integer pageNo,Integer pageSize) throws Exception{
        LogUtil.info(MODULE, "查询栏目文章开始：");
        
        PageResponseDTO<CmsArticleRespDTO> result = null;
        if(StringUtil.isEmpty(channelId)){
            LogUtil.error(MODULE, "查询栏目文章结束：栏目id为空");
            throw new Exception("栏目id为空");
        }
        LogUtil.info(MODULE, "查询栏目id为: "+channelId + " 的文章");
        CmsArticleReqDTO reqDTO = new CmsArticleReqDTO();
        if(null != pageNo && 0 < pageNo){
            reqDTO.setPageNo(pageNo);  
        }
        if(null != pageSize && 0 < pageSize){
            reqDTO.setPageSize(pageSize); 
        }
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        reqDTO.setThisTime(DateUtil.getSysDate());
        reqDTO.setChannelId(channelId);
        reqDTO.setSiteId(reqDTO.getCurrentSiteId());
        try {
            result = cmsArticleRSV.queryCmsArticlePage(reqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询栏目文章异常：",e);
            throw new Exception("查询栏目文章异常");
        }
        LogUtil.info(MODULE, "查询栏目文章结束：成功");
        return result;
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
    /**
     * 
     * qryFagList:(获取常见问题列表). <br/> 
     * TODO(提供给客服中心常见问题服务。).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param siteId 站点id
     * @param channelType 栏目类型  01导航栏目，02信息栏目，03投票栏目，04帮助中心，05客服中心
     * @param getNum 取数数量  可不填，默认10条
     * @return 
     * @since JDK 1.6
     */
    private List<CmsArticleRespDTO> qryFagList(Long siteId,String channelType,Integer getNum){
        LogUtil.info(MODULE, "查询常见问题列表开始：");
        
        List<CmsArticleRespDTO> resultList=new ArrayList<CmsArticleRespDTO>();
        PageResponseDTO<CmsArticleRespDTO> resultPage = null;
        if(null==getNum){
            getNum=10;
        }
        //栏目
        CmsChannelReqDTO channelReqDTO=new CmsChannelReqDTO();
        channelReqDTO.setSiteId(siteId);
        channelReqDTO.setChannelType(channelType);
        channelReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        channelReqDTO.setPlatformType(CmsConstants.PlatformType.CMS_PLATFORMTYPE_01);
        List<CmsChannelResDTO> channelList=null;
        try {
            channelList = this.cmsChannelRSV.listChannel(channelReqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询常见问题列表异常：查询帮助中心栏目异常", e);
            throw e;
        }
        List<Long> channelIds=new ArrayList<Long>();
        if(null != channelList && channelList.size()>0){
            for (CmsChannelResDTO channelResDTO: channelList) {
                channelIds.add(channelResDTO.getId());
            }
            //文章
            CmsArticleReqDTO articleReqDTO=new CmsArticleReqDTO();
            articleReqDTO.setSiteId(siteId);
            articleReqDTO.setChannelIds(channelIds);
            articleReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            articleReqDTO.setThisTime(DateUtil.getSysDate());// 设置当前时间
            articleReqDTO.setPageNo(1);
            articleReqDTO.setPageSize(getNum);
            try {
                resultPage =  this.cmsArticleRSV.queryCmsArticlePage(articleReqDTO);
            } catch (Exception e) {
                LogUtil.error(MODULE, "查询常见问题列表异常：查询帮助中心文章异常", e);
                throw e;
            }
            if(null != resultPage){
                resultList = resultPage.getResult();
            }
        }
        LogUtil.info(MODULE, "查询常见问题列表结束：正常");
        return resultList;
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
