package com.zengshi.ecp.busi.cms.channel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.cms.channel.vo.CmsChannelNode;
import com.zengshi.ecp.busi.cms.channel.vo.CmsChannelVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsArticleReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsArticleRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsChannelReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsChannelResDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceChannelReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceChannelRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsArticleRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsChannelRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceChannelRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteInfoRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.system.util.ConstantTool;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(value="/cms/channel")
public class CmsChannelController extends EcpBaseController {

    private String URL = "/cms/channel";//返回页面的基本路径 
    private static String MODULE = CmsChannelController.class.getName();
    
    @Resource
    private ICmsChannelRSV cmsChannelRSV;
    @Resource
    private ICmsSiteInfoRSV cmsSiteInfoRSV;
    @Resource
    private ICmsPlaceChannelRSV cmsPlaceChannelRSV;
    @Resource
    private ICmsArticleRSV cmsArticleRSV;
    @RequestMapping()
    public String init(Model model){
        return URL+"/main-channel";
    }
    
    @RequestMapping(value="/loadnodes")
    @ResponseBody
    public String loadNodes(Model model, CmsChannelVO channelVO){
        LogUtil.info(MODULE, "==========channelVO:" + channelVO.toString() + ";");
        
        CmsChannelReqDTO channelReqDTO = new CmsChannelReqDTO();
        ObjectCopyUtil.copyObjValue(channelVO, channelReqDTO, null, false);
        
        channelReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        
        List<CmsChannelResDTO> channelResDTOList = cmsChannelRSV.listChannel(channelReqDTO);
        
        if(CollectionUtils.isNotEmpty(channelResDTOList))
        {
            List<CmsChannelNode> trees = new ArrayList<CmsChannelNode>(channelResDTOList.size());
            for (CmsChannelResDTO resDTO:channelResDTOList) 
            {
                CmsChannelNode node = new CmsChannelNode();
                node.setId(String.valueOf(resDTO.getId()));
                node.setName(resDTO.getChannelName());
                node.setpId(String.valueOf(resDTO.getChannelParent()));
                node.setIsParent(false);
                for (CmsChannelResDTO resDTO2:channelResDTOList){
                	if(node.getId().equals(String.valueOf(resDTO2.getChannelParent()))){
                		node.setIsParent(true);
                		break;
                	}
                } 
                node.setSiteId(String.valueOf(resDTO.getSiteId()));
                trees.add(node);
            }
            String json = JSONObject.toJSONString(trees);
            return json;
        }
        return null;
    }
    
    /**
     * 
     * openLoadNodes:(加载位置关联栏目时的弹出框栏目树). <br/> 
     * TODO(位置关联栏目时，只选择链接类型为内链接 且关联的网站信息的类型为文章列表).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh
     * @param model
     * @param channelVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/openloadnodes")
    @ResponseBody
    public String openLoadNodes(Model model, CmsChannelVO channelVO){
        LogUtil.info(MODULE, "==========channelVO:" + channelVO.toString() + ";");
        
        CmsChannelReqDTO channelReqDTO = new CmsChannelReqDTO();
        ObjectCopyUtil.copyObjValue(channelVO, channelReqDTO, null, false);
        
        //设置状态为有效
        channelReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        //设置链接类型为内链接
        if(channelVO.getIsOutLink().equalsIgnoreCase("0")){
            channelReqDTO.setIsOutLink(CmsConstants.IsNot.CMS_ISNOT_0);
        }else if (channelVO.getIsOutLink().equalsIgnoreCase("1")){
            channelReqDTO.setIsOutLink(CmsConstants.IsNot.CMS_ISNOT_1);
        }
        
        //设置关联网站信息
        if(channelVO.getIsresiteinfo().equalsIgnoreCase("0")){
            channelReqDTO.setIsresiteinfo(CmsConstants.IsNot.CMS_ISNOT_0);
        }else if (channelVO.getIsresiteinfo().equalsIgnoreCase("1")){
            channelReqDTO.setIsresiteinfo(CmsConstants.IsNot.CMS_ISNOT_1);
        }
        
        List<CmsChannelResDTO> channelResDTOListOld = cmsChannelRSV.listChannel(channelReqDTO);
        List<CmsChannelResDTO> channelResDTOList = new ArrayList<CmsChannelResDTO>();
       
        //去除网站信息类型不为列表的
        if(CollectionUtils.isNotEmpty(channelResDTOListOld) && channelVO.getSiteInfoType().equalsIgnoreCase(CmsConstants.SiteInfoType.CMS_SITE_INFO_TYPE_02)){
            for (CmsChannelResDTO resDTO:channelResDTOListOld){
                if(resDTO.getSiteinfoId()!=null){
                    /*根据入参调用后场網站信息查询服务 */
                    CmsSiteInfoReqDTO cmsSiteInfoDTO = new CmsSiteInfoReqDTO();
                    cmsSiteInfoDTO.setId(resDTO.getSiteinfoId());
                    CmsSiteInfoRespDTO cmsSiteInfoRespDTO = cmsSiteInfoRSV.queryCmsSiteInfo(cmsSiteInfoDTO); 
                    if(cmsSiteInfoRespDTO.getSiteInfoType().equalsIgnoreCase(CmsConstants.SiteInfoType.CMS_SITE_INFO_TYPE_02)){
                        //如果网站信息是文章列表类型  则保留
                        channelResDTOList.add(resDTO);
                    }
                }
            }
        }else{
            channelResDTOList = channelResDTOListOld;
        }
        //释放 channelResDTOListOld
        channelResDTOListOld = null;
        if(CollectionUtils.isNotEmpty(channelResDTOList))
        {
            List<CmsChannelNode> trees = new ArrayList<CmsChannelNode>(channelResDTOList.size());
            for (CmsChannelResDTO resDTO:channelResDTOList) 
            {
                CmsChannelNode node = new CmsChannelNode();
                node.setId(String.valueOf(resDTO.getId()));
                node.setName(resDTO.getChannelName());
                node.setpId(String.valueOf(resDTO.getChannelParent()));
                node.setIsParent(true);
                node.setSiteId(String.valueOf(resDTO.getSiteId()));
                trees.add(node);
            }
            String json = JSONObject.toJSONString(trees);
            return json;
        }
        return null;
    }
    
    
    
    /** 
     * onenode:(加载栏目树). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @param channel_id
     * @param siteId
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/onenode")
    @ResponseBody
    public CmsChannelVO onenode(Model model, 
            @RequestParam(value="id",required=true)String channel_id){
        LogUtil.info(MODULE, "==========channel_id:" + channel_id + ";");
        
        CmsChannelVO channelVO = new CmsChannelVO();
        
        CmsChannelReqDTO arg0 = new CmsChannelReqDTO();
        arg0.setId(Long.valueOf(channel_id));
        
        CmsChannelResDTO res = cmsChannelRSV.find(arg0);
        
        if(res != null){
        	 String vfsUrl = ConstantTool.getImageUrl(res.getChannelIcon(),"120x130!");
             res.setVfsUrl(vfsUrl);
            ObjectCopyUtil.copyObjValue(res, channelVO, null, false);
        }
        
        return channelVO;
    }
    
    /** 
     * save:(保存栏目). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @param channelVO
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/save")
    @ResponseBody
    public CmsChannelNode save(Model model, CmsChannelVO channelVO){
        LogUtil.info(MODULE, "==========channelVO:" + channelVO + ";");
        
        CmsChannelNode node = new CmsChannelNode();
        CmsChannelReqDTO arg0 = new CmsChannelReqDTO();
        ObjectCopyUtil.copyObjValue(channelVO, arg0, null, false);
        if(StringUtil.isEmpty(arg0.getSortNo()))
        {
        	arg0.setSortNo(1);
        }
        if(channelVO.getId() == null || channelVO.getId() == 0){
            //新增栏目
            Long channelId = cmsChannelRSV.insert(arg0);
            channelVO.setId(channelId);
            channelVO.setNewCreate(true);
        }else {
            //更新栏目
            cmsChannelRSV.update(arg0);
            channelVO.setNewCreate(false);
        }
        node.setId(String.valueOf(channelVO.getId()));
        node.setName(channelVO.getChannelName());
        node.setpId(String.valueOf(channelVO.getChannelParent()));
        node.setIsParent(true);
        node.setNewCreate(channelVO.isNewCreate());
        
        return node;
    }
    
    /** 
     * delChannel:(删除栏目). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @param channel_id
     * @return 
     * @since JDK 1.6 
     *
     *@修改：huangxm9
     *@原因:若栏目被删除，则应该将管理的文章管理记录撤销，栏目位置管理关联的记录设置成已失效
     */ 
    @RequestMapping(value="/delete")
    @ResponseBody
    public EcpBaseResponseVO delChannel(Model model, 
            @RequestParam(value="id",required=true)String channel_id){
        LogUtil.info(MODULE, "==========channel_id:" + channel_id + ";");
        
        EcpBaseResponseVO res = new EcpBaseResponseVO();

        //调用删除函数
        CmsChannelReqDTO arg0 = new CmsChannelReqDTO();
        arg0.setId(Long.valueOf(channel_id));
        
        cmsChannelRSV.delete(arg0);
        //若栏目被删除，则应该将管理的文章管理记录撤销，栏目位置管理关联的记录设置成已失效
        CmsPlaceChannelReqDTO cmsPlaceChannelReqDTO = new CmsPlaceChannelReqDTO();
        List<CmsPlaceChannelRespDTO> list = null;
        cmsPlaceChannelReqDTO.setChannelId(Long.valueOf(channel_id));
        cmsPlaceChannelReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        list = cmsPlaceChannelRSV.queryCmsPlaceChannelList(cmsPlaceChannelReqDTO);
        for(int i=0;i<list.size();i++){
        	cmsPlaceChannelRSV.deleteCmsPlaceChannel(String.valueOf(list.get(i).getId()));
        }
        
        CmsArticleReqDTO cmsArticleReqDTO = new CmsArticleReqDTO();
        List<CmsArticleRespDTO> articleList = null;
        cmsArticleReqDTO.setChannelId(Long.valueOf(channel_id));
        cmsArticleReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        articleList = cmsArticleRSV.queryCmsArticleList(cmsArticleReqDTO);
        for(int k=0;k<articleList.size();k++){
        	cmsArticleRSV.deleteCmsArticle(String.valueOf(articleList.get(k).getId()));
        }
        //end
        res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);

        return res;
    }
    
    /** 
     * openChannel:(打开栏目树框). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @param siteId
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/openchannel")
    public String openChannel(Model model,CmsChannelVO channelVO){
        LogUtil.info(MODULE, "==========siteId:" + channelVO.toString() + ";");
        
        if(channelVO.getSiteId()!= null){
            model.addAttribute("siteId", channelVO.getSiteId());
            String siteUrl=CmsCacheUtil.getCmsSiteCache(channelVO.getSiteId()).getSiteUrl();
            model.addAttribute("siteUrl", siteUrl);
        }
    	if(StringUtil.isNotBlank(channelVO.getIsOutLink())){
    	    model.addAttribute("isOutLink", channelVO.getIsOutLink());
    	}
    	if(StringUtil.isNotBlank(channelVO.getIsresiteinfo())){
    	    model.addAttribute("isresiteinfo", channelVO.getIsresiteinfo());
        }
    	if(StringUtil.isNotBlank(channelVO.getSiteInfoType())){
    	    model.addAttribute("siteInfoType", channelVO.getSiteInfoType());
        }
    	if(StringUtil.isNotBlank(channelVO.getPlatformType())){
    		model.addAttribute("platformType", channelVO.getPlatformType());
    	}
    	if(StringUtil.isNotBlank(channelVO.getChannelType())){
    		model.addAttribute("channelType", channelVO.getChannelType());
    	}
        return URL+"/open/open-channel";
    }
    
    
    /** 
     * delChannel:(删除栏目). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param model
     * @param channel_id
     * @return 
     * @since JDK 1.6 
     *
     */ 
    @RequestMapping(value="/isexistarticle")
    @ResponseBody
    public EcpBaseResponseVO isExistArticle(Model model, 
            @RequestParam(value="id",required=true)String channel_id){
        LogUtil.info(MODULE, "==========channel_id:" + channel_id + ";");
        
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        //栏目下是否存在有效文章
        CmsArticleReqDTO articleReqDTO = new CmsArticleReqDTO();
        List<CmsArticleRespDTO> list = null;
        articleReqDTO.setChannelId(Long.valueOf(channel_id));
        articleReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        list = cmsArticleRSV.queryCmsArticleList(articleReqDTO);
        if(list.size()>0){
        	res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
        }else{
        	res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        }
        return res;
    }
}

