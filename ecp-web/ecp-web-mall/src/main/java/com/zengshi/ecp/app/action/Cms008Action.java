package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Cms008Req;
import com.zengshi.ecp.app.resp.Cms008Resp;
import com.zengshi.ecp.app.resp.Cms008Resp.Item;
import com.zengshi.ecp.app.resp.Cms008Resp.Model;
import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorDataReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorDataRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsChannelReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsChannelResDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTemplateReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTemplateRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsAdvertiseRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorDataRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsChannelRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorPlaceRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTemplateRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 获取APP首页数据服务<br>
 * Date:2016-2-22下午6:51:19  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version  
 * @since JDK 1.6 
 */

@Service("cms008")
@Action(bizcode="cms008", userCheck=false)
@Scope("prototype")
public class Cms008Action extends AppBaseAction<Cms008Req, Cms008Resp> {

    private static String  MODULE =  Cms008Action.class.getName();
   
    private final Long  SITEID =  1l; //商城站点
    
    private final Long  ADPLACEID = 1l;//首页内容位置id
    
    private final Long CHANNELPARENTCODE = 0l;//0代表根节点
    
    private final Integer DEFAULTPAGESIZE= 1;//默认一页多少数据
    
    private final Integer CHANNELSIZE = 4;//首页栏目个数
    
    private final Integer ADTYPE = 0;//广告制定的模板编码
    
    private final Integer CHANNELTYPE = 1;//栏目指定的模板编码
    
    private final Integer INFOTYPE = 2;//信息指定的模板编码
    
    @Resource(name = "cmsChannelRSV")
    private ICmsChannelRSV cmsChannelRSV;
    
    @Resource(name = "cmsAppFloorRSV")
    private ICmsAppFloorRSV cmsAppFloorRSV;
    
    @Resource(name = "cmsFloorTemplateRSV")
    private ICmsFloorTemplateRSV cmsFloorTemplateRSV;
    
    @Resource(name = "cmsFloorPlaceRSV")
    private ICmsFloorPlaceRSV cmsFloorPlaceRSV;
    
    @Resource(name = "cmsAppFloorDataRSV")
    private ICmsAppFloorDataRSV cmsAppFloorDataRSV;
    
    @Resource(name = "cmsAdvertiseRSV")
    private ICmsAdvertiseRSV cmsAdvertiseRSV;
    
    @Resource(name = "cmsPlaceRSV")
    private ICmsPlaceRSV cmsPlaceRSV;
    
    @Resource(name = "cmsInfoRSV")
    private ICmsInfoRSV cmsInfoRSV;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        LogUtil.info(MODULE, "==========开始进入cms008 app获取楼层服务============");
        
        if(this.request == null){
            this.request = new Cms008Req();
        }
        
        List<Cms008Resp.Model> datas = new ArrayList<Cms008Resp.Model>(); //首页数据
        
        //1. 获取首页轮播广告数据  
        Long adPlaceId = this.ADPLACEID;
        if(StringUtil.isNotEmpty(this.request.getAdPlaceId()) && this.request.getAdPlaceId() > 0){
            adPlaceId =  this.request.getAdPlaceId();
        }
        Cms008Resp.Model adData = this.getAdData(adPlaceId);
        if(adData!=null && CollectionUtils.isNotEmpty(adData.getItemList())){
            datas.add(adData);
        }
        
        Long siteId = this.request.getSiteId();
        if(StringUtil.isEmpty(siteId) || siteId <=0){
            siteId = this.SITEID;
        }
        //2.获取首页栏目数据
        Cms008Resp.Model channelData = this.getChannelData(siteId);
        if(channelData != null && CollectionUtils.isNotEmpty(channelData.getItemList())){
            datas.add(channelData);
        }
        
        //3.获取信息
        Cms008Resp.Model infoData = this.getInfoData(siteId);
        if(infoData!=null && CollectionUtils.isNotEmpty(infoData.getItemList())){
            datas.add(infoData);
        }
        
        //4.获取APP楼层
        List<Cms008Resp.Model> floorDatas = this.getFloorDatas(siteId);
        if(CollectionUtils.isNotEmpty(floorDatas)){
            datas.addAll(floorDatas);
        }
        
        this.response.setDatas(datas);
    }

    /**
     * 
     * getAdData:(获取广告数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param adplaceId
     * @return 
     * @since JDK 1.6
     */
    private Model getAdData(Long adPlaceId) {
        Model adData = new Model();
        List<Item> itemList  = null;
        
        if(StringUtil.isNotEmpty(adPlaceId)){
            // 1.查找内容位置
            CmsPlaceRespDTO placeRespDto = null;
            CmsPlaceReqDTO placeReqDto = new CmsPlaceReqDTO();
            placeReqDto.setId(adPlaceId);
            placeReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            try {
                placeRespDto = cmsPlaceRSV.queryCmsPlace(placeReqDto);
            } catch (Exception e) {
                LogUtil.error(MODULE, "==========查询cms008 app获取内容位置服务异常============");
                throw e;
            }

            if(placeRespDto!=null && StringUtil.isNotEmpty(placeRespDto.getId())){
                //2.查询广告
                CmsAdvertiseReqDTO reqDto = new CmsAdvertiseReqDTO();
                reqDto.setPlaceId(adPlaceId);
                //设置平台类型
                reqDto.setPlatformType(CmsConstants.PlatformType.CMS_PLATFORMTYPE_02);
                //设置数据状态
                reqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                //设置当前时间  只查当前时间有效的数据
                reqDto.setThisTime(DateUtil.getSysDate());
                reqDto.setPageNo(1);
                
                if(placeRespDto!=null && StringUtil.isNotEmpty(placeRespDto.getPlaceCount())){
                    reqDto.setPageSize(placeRespDto.getPlaceCount());
                }else{
                    reqDto.setPageSize(this.DEFAULTPAGESIZE);
                }
                
                //调用服务，获取数据
                List<CmsAdvertiseRespDTO> respList = null;
                PageResponseDTO<CmsAdvertiseRespDTO> pageInfo = null;
                try {
                    pageInfo = this.cmsAdvertiseRSV.queryCmsAdvertisePage(reqDto);
                    if (pageInfo != null) {
                        respList = pageInfo.getResult();
                        //转化数据
                        if(CollectionUtils.isNotEmpty(respList)){
                            itemList = this.transforAdData(respList,"");
                        }
                    }
                } catch (Exception e) {
                    LogUtil.error(MODULE, "==========查询cms008 app查询广告列表异常============");
                    throw e;
                }
            }
        }
        
        //返回数据
        adData.setType(this.ADTYPE);
        if(CollectionUtils.isNotEmpty(itemList)){
            adData.setItemList(itemList);
        }
        return adData;
    }
    
    /**
     * 
     * getCatgData:(获取分类数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @return 
     * @since JDK 1.6
     */
    private Model getChannelData(Long siteId) {
        if(StringUtil.isEmpty(siteId)){
            return null;
        }
        
        Model channelData = new Model();
        List<Item> itemList  = new ArrayList<Cms008Resp.Item>();
    
        //1.根据内容位置获取根节点
        CmsChannelReqDTO channelReqDto = new CmsChannelReqDTO();
        
        //1.1 设置查询条件
        channelReqDto.setChannelParent(this.CHANNELPARENTCODE);
        channelReqDto.setPlatformType(CmsConstants.PlatformType.CMS_PLATFORMTYPE_02);//app端
        channelReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);//只查询有效的
        channelReqDto.setChannelType(CmsConstants.ChannelType.CMS_CHANNEL_TYPE_01);//导航类型
        channelReqDto.setSiteId(siteId);
        //1.2 调用服务，查询内容位置栏目关系记录
        List<CmsChannelResDTO> channelsList = null;//栏目数据 
        try {
            channelsList = cmsChannelRSV.listChannel(channelReqDto);
        } catch (Exception e) {
            LogUtil.info(MODULE, "==========cms101 app获取首页数据服务==查询栏目记录 发生异常============");
            throw e;
        }
        
        //1.3 转化数据
        if(CollectionUtils.isNotEmpty(channelsList)){
            int count = 0;
            Integer channelSize = this.request.getChannelSize();
            if(StringUtil.isEmpty(channelSize) || channelSize <=0){
                channelSize=this.CHANNELSIZE;
            }
            for(CmsChannelResDTO respDto : channelsList){
                if(count >= channelSize){
                    break;
                }
                if(respDto!=null && StringUtil.isNotEmpty(respDto.getId())){
                    Item item = new Item();
                    item.setName(respDto.getChannelName());
                    item.setClickUrl(respDto.getChannelUrl());
                    item.setImgUrl(ImageUtil.getImageUrl(respDto.getChannelIcon()));;
                    itemList.add(item);
                    count ++;
                }
            }
        }
        //返回数据
        channelData.setType(this.CHANNELTYPE);
        if(CollectionUtils.isNotEmpty(itemList)){
            channelData.setItemList(itemList);
        }else{
            return null;
        }
        return channelData;
    }
    /**
     * 
     * getFloorDatas:(获取app楼层). <br/> 
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
    private List<Model> getFloorDatas(Long siteId) {
        List<Model> floorDatas = new ArrayList<Cms008Resp.Model>();

        //1.获取参数  并初始化app楼层请求参数
        CmsAppFloorReqDTO floorReqDto = new CmsAppFloorReqDTO();
        
        //设置楼层状态
        floorReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        
        //设置站点
        floorReqDto.setSiteId(siteId);
        
        //2.调用app楼层服务。查询该站点下指定状态的app楼层
        List<CmsAppFloorRespDTO> appFloorList = null;
        try {
            appFloorList = cmsAppFloorRSV.queryCmsAppFloorList(floorReqDto);
            if(CollectionUtils.isNotEmpty(appFloorList)){
                for(CmsAppFloorRespDTO Dto : appFloorList){
                    Model floorData = this.getFloorData(Dto);
                    if(floorData != null && StringUtil.isNotEmpty(floorData.getType())){
                        floorDatas.add(floorData);
                    }
                }
            }
        } catch (Exception e) {
            LogUtil.info(MODULE, "==========cms008 app获取楼层服务异常============");
            throw e;
        }
        
        return floorDatas;
    }
    
    /**
     * 
     * getInfoData:(获取信息). <br/> 
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
    private Model getInfoData(Long siteId) {

        CmsInfoReqDTO reqDTO = new CmsInfoReqDTO();
        reqDTO.setSiteId(siteId);
        //设置有效
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        //设置发布截至时间
        reqDTO.setThisTime(DateUtil.getSysDate());
        //reqDTO.setThisTime(DateUtil.getTheDayFirstSecond(DateUtil.getSysDate()));
        
        Model infoData = new Model();
        List<Item> itemList = new ArrayList<Cms008Resp.Item>();
        try {
            List<CmsInfoRespDTO> infoList = cmsInfoRSV.queryCmsInfoList(reqDTO);
            if(CollectionUtils.isNotEmpty(infoList)){
                for(CmsInfoRespDTO dto : infoList){
                    if(dto!=null && StringUtil.isNotEmpty(dto.getId())){
                        Item item = new Item();
                        item.setName(dto.getInfoTitle());
                        item.setTypeName(dto.getTypeName());
                        item.setClickUrl("/pmph/info/infoDetail?infoId="+dto.getId());
                        itemList.add(item);
                    }
                }
            }
            infoData.setItemList(itemList);
        } catch (Exception e) {
            LogUtil.info(MODULE, "==========cms008 app获取信息服务异常============");
            throw e;
        }
        
        infoData.setType(this.INFOTYPE);
        infoData.setClickUrl("/pmph/info/infoList");
        
        return infoData;
    }
    
    /**
     * getFloorData:(获取app楼层内容位置数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param dto
     * @return 
     * @since JDK 1.6
     */
    private Model getFloorData(CmsAppFloorRespDTO foorRespDto) {
        Model floorData = new Model();
        List<Item> itemList  = new ArrayList<Cms008Resp.Item>();
        
        if(foorRespDto!=null && StringUtil.isNotEmpty(foorRespDto.getId()) && StringUtil.isNotEmpty(foorRespDto.getFloorTemplateId())){
            //转化楼层数据
            floorData.setClickUrl(CmsCommonUtil.getClickUrl(this.SITEID, foorRespDto.getLinkUrl(), "", ""));
            floorData.setName(foorRespDto.getFloorName());
            //根据楼层模板id查询楼层模板id
            CmsFloorTemplateRespDTO TemplateRespDto = this.qryFloorTemplateById(foorRespDto.getFloorTemplateId());
            if(TemplateRespDto==null || StringUtil.isEmpty(TemplateRespDto.getTemplateNo())){
                return null;
            }
            floorData.setType((int)(TemplateRespDto.getTemplateNo()));
            
            //根据模板id查询相关内容位置
            List<CmsFloorPlaceRespDTO> floorPlaceRespList = null;
            CmsFloorPlaceReqDTO floorPlaceReqDto = new CmsFloorPlaceReqDTO();
            floorPlaceReqDto.setFloorTemplateId(foorRespDto.getFloorTemplateId());
            floorPlaceReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            try {
                floorPlaceRespList = cmsFloorPlaceRSV.queryCmsFloorPlaceList(floorPlaceReqDto);
            } catch (Exception e) {
                LogUtil.error(MODULE, "查询楼层内容位置出错",e);
                throw e;
            }

            if (CollectionUtils.isNotEmpty(floorPlaceRespList)) {
                for (CmsFloorPlaceRespDTO Dto : floorPlaceRespList) {
                    Item item = this.getFloorDataItem(foorRespDto, Dto);
                    if (StringUtil.isNotEmpty(item)) {
                        itemList.add(item);
                    }
                }
            }
        }
        
        //返回数据
        if(CollectionUtils.isNotEmpty(itemList)){
            floorData.setItemList(itemList);
        }
        return floorData;
    }

    /**
     * 
     * qryFloorTemplateById:(根据楼层模板id查询有效的楼层模板). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh
     * @param floorTemplateId
     * @return 
     * @since JDK 1.6
     */
    private CmsFloorTemplateRespDTO qryFloorTemplateById(Long floorTemplateId) {
        CmsFloorTemplateRespDTO respDto = null;
        if(StringUtil.isNotEmpty(floorTemplateId)){
            CmsFloorTemplateReqDTO reqDto = new CmsFloorTemplateReqDTO();
            reqDto.setId(floorTemplateId);
            reqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);;
            try {
                respDto = this.cmsFloorTemplateRSV.queryCmsFloorTemplate(reqDto);
            } catch (Exception e) {
                LogUtil.error(MODULE, "查询楼层模板出错",e);
                throw e;
            }
        }
        return respDto;
    }

    /**
     * 
     * transforAdData:(转化广告数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param respList
     * @param standard
     * @return 
     * @since JDK 1.6
     */
    private List<Cms008Resp.Item> transforAdData(List<CmsAdvertiseRespDTO> respList, String standard) {
        List<Cms008Resp.Item> itemList  = new ArrayList<Cms008Resp.Item>();
        
        if(standard == null){
            standard = "";
        }
        
        if(CollectionUtils.isNotEmpty(respList)){
            for(CmsAdvertiseRespDTO dto : respList){
                if(StringUtil.isNotEmpty(dto.getVfsId())){//广告必须有图片
                    //转化为vo
                    Cms008Resp.Item adItem = new Cms008Resp.Item();
                    //调文件服务器，返回图片
                    adItem.setImgUrl(ParamsTool.getImageUrl(dto.getVfsId(),standard));
                   
                    //转化链接地址
                    if (StringUtil.isNotBlank(dto.getLinkType())) {
                        adItem.setClickUrl(CmsCommonUtil.getClickUrl(this.SITEID,dto.getLinkUrl(),dto.getLinkType(),String.valueOf(dto.getId())));
                    }
                    adItem.setName(dto.getAdvertiseTitle());
                    if(StringUtil.isNotEmpty(adItem)){
                        itemList.add(adItem);
                    }
                }
            }
        }
        return itemList;
    }
    
    /**
     * 
     * getFloorDataItem:(获取一个app楼层内容数据 并转化为Item). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh
     * @param foorRespDto
     * @param dto
     * @return 
     * @since JDK 1.6
     */
    private Item getFloorDataItem(CmsAppFloorRespDTO foorRespDto, CmsFloorPlaceRespDTO floorPlaceDto) {
        Cms008Resp.Item item = null;
        
        if(foorRespDto!=null && floorPlaceDto!=null && StringUtil.isNotEmpty(foorRespDto.getId()) && StringUtil.isNotEmpty(floorPlaceDto.getId())){
            //1.获取app楼层数据
            CmsAppFloorDataReqDTO appFloorDataReqDto = new CmsAppFloorDataReqDTO();
            
            
            appFloorDataReqDto.setAppFloorId(foorRespDto.getId());
            appFloorDataReqDto.setFloorPlaceId(floorPlaceDto.getId());
            appFloorDataReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            //调用服务
            List<CmsAppFloorDataRespDTO> appFloorDataRespDTOList = null;
            CmsAppFloorDataRespDTO appFloorDataRespDto = null;
            try {
                appFloorDataRespDTOList = cmsAppFloorDataRSV.queryCmsAppFloorDataList(appFloorDataReqDto);
                if(CollectionUtils.isNotEmpty(appFloorDataRespDTOList)){
                    appFloorDataRespDto = appFloorDataRespDTOList.get(0);
                }
            } catch (Exception e) {
                LogUtil.info(MODULE, "==========cms008 app获取楼层数据服务异常============");
                throw e;
            }
        
            //2. 转化数据  
            if(appFloorDataRespDto != null && StringUtil.isNotEmpty(appFloorDataRespDto.getId())){
                item = new Item();
                
                item.setName(appFloorDataRespDto.getName());
                
                //2.1.获取图片地址
                String standard = null;
                if(StringUtil.isNotEmpty(floorPlaceDto.getPlaceHeight()) && StringUtil.isNotEmpty(floorPlaceDto.getPlaceWidth())){
                    standard =  floorPlaceDto.getPlaceWidth() + "x" + floorPlaceDto.getPlaceHeight() + "!";
                }else{
                    standard = "";
                }
                item.setImgUrl(ParamsTool.getImageUrl(appFloorDataRespDto.getVfsId(),standard));
            
                //获取点击地址
                if(StringUtil.isBlank(appFloorDataRespDto.getLinkUrl())){
                    appFloorDataRespDto.setLinkUrl(appFloorDataRespDto.getName());
                }
                item.setClickUrl(CmsCommonUtil.getClickUrl(this.SITEID,appFloorDataRespDto.getLinkUrl(), appFloorDataRespDto.getLinkType(),""));
            }
        }
        
        return item;
    }
}

