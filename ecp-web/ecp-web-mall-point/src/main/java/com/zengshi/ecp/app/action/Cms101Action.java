package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Cms101Req;
import com.zengshi.ecp.app.resp.Cms101Resp;
import com.zengshi.ecp.app.resp.Cms101Resp.Item;
import com.zengshi.ecp.app.resp.Cms101Resp.Model;
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
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsAdvertiseRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorDataRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsChannelRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorPlaceRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTemplateRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.prom.dubbo.dto.KillGdsInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.staff.dubbo.dto.ScoreInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IScoreInfoRSV;
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

@Service("cms101")
@Action(bizcode="cms101", userCheck=false)
@Scope("prototype")
public class Cms101Action extends AppBaseAction<Cms101Req, Cms101Resp> {

    private static String  MODULE =  Cms101Action.class.getName();
   
    private final Long  SITEID =  2l; //商城站点
    
    private final Long CHANNELPARENTCODE = 0l;//0代表根节点
    
    private final Integer CHANNELSIZE = 4;//首页栏目个数
    
    private final Integer CHANNELTYPE = 1;//栏目指定的模板编码
    
    private final Integer MYSCORETYPE = 610;//我的积分指定的模板编码

    private final Short INFOTYPE = 510;//信息指定的模板编码
    
    
    
    private final String[] COLORS= {"#2fe086","#ff8888","#fbd502","#33a1ff","#f178ff","#23d4e4"};//楼层颜色
    
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
    
    @Resource(name = "gdsInfoQueryRSV")
    private IGdsInfoQueryRSV gdsInfoQueryRSV;
    
    @Resource(name = "scoreInfoRSV")
    private IScoreInfoRSV scoreInfoRSV;
    
    @Resource(name = "cmsInfoRSV")
    private ICmsInfoRSV cmsInfoRSV;
    
    @Resource(name = "promQueryRSV")
    private IPromQueryRSV promQueryRSV;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        LogUtil.info(MODULE, "==========开始进入Cms101 app获取楼层服务============");
        
        if(this.request == null){
            this.request = new Cms101Req();
        }
        
        List<Cms101Resp.Model> datas = new ArrayList<Cms101Resp.Model>(); //首页数据
        
        Long siteId = this.request.getSiteId();
        if(StringUtil.isEmpty(siteId) || siteId <=0){
            siteId = this.SITEID;
        }
        
        
        //获取APP楼层
        List<CmsAppFloorRespDTO> appFloorList = this.qryProAppFloor(siteId);
        List<Cms101Resp.Model> floorDatas = this.doAppFloor(appFloorList, siteId);
        if(CollectionUtils.isNotEmpty(floorDatas)){
        	datas.addAll(floorDatas);
        }
        this.response.setDatas(datas);
    }
    /**
     * 
     * qryProAppFloor:(查询数据库，获取app楼层记录). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param siteId
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    private List<CmsAppFloorRespDTO> qryProAppFloor(Long siteId) throws Exception{
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
        } catch (Exception e) {
            LogUtil.info(MODULE, "==========Cms101 app获取楼层服务异常============");
            throw new Exception("获取楼层异常");
        } 
        return  appFloorList;
    }
    /**
     * 
     * doAppAppFloor:(处理楼层记录). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @return 
     * @throws Exception 
     * @since JDK 1.6
     */
    private List<Model> doAppFloor(List<CmsAppFloorRespDTO> appFloorList,Long siteId) throws Exception{
        if(CollectionUtils.isEmpty(appFloorList)){
            return null;
        }
        
        //转化数据
        List<Model> floorDatas = new ArrayList<Cms101Resp.Model>();
        int colorIndex = 0;
        for(CmsAppFloorRespDTO Dto : appFloorList){
            //根据不同的模板类型去不同数据源取数据
            CmsFloorTemplateRespDTO TemplateRespDto = null;
            if(Dto!=null && StringUtil.isNotEmpty(Dto.getId()) && StringUtil.isNotEmpty(Dto.getFloorTemplateId())){
               //根据楼层模板id查询楼层模板id
               TemplateRespDto   = this.qryFloorTemplateById(Dto.getFloorTemplateId());
            }
            if(TemplateRespDto!=null && StringUtil.isNotEmpty(TemplateRespDto.getTemplateNo())){
                Model floorData = null;
                Short tempNo = TemplateRespDto.getTemplateNo();
                
                if(this.CHANNELTYPE.shortValue() == tempNo.shortValue()){//取栏目数据源
                    floorData = this.getChannelData(siteId);
                }else if(this.INFOTYPE.shortValue() == tempNo.shortValue()){//取信息数据源
                	floorData = this.getInfoData(siteId);
                }else if(this.MYSCORETYPE.shortValue() == tempNo.shortValue()){//取信息数据源 610
					Cms101Resp.Model userScorData = this.getUserScoreData();
					if (userScorData != null && CollectionUtils.isNotEmpty(userScorData.getItemList())) {
						floorDatas.add(userScorData);
					}
                }else if(100 <= tempNo.shortValue() && 200 > tempNo.shortValue()){//秒杀
                    floorData = this.getSecondKillData(Dto,tempNo);
                }else{//取楼层数据 数据源
                    floorData = this.getFloorData(Dto,tempNo);
                }
                
                if(floorData != null && StringUtil.isNotEmpty(floorData.getType())){
                    if(tempNo < 200){//楼层类型与秒杀类型设置楼层名颜色
                        floorData.setColor(this.COLORS[colorIndex%6]);
                        colorIndex ++ ; 
                    }
                    floorDatas.add(floorData);
                }
            }
        }
        return floorDatas;
    }
    /**
     * 
     * getSecondKillData:(获取秒杀信息). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param dto
     * @param tempNo 
     * @return 
     * @since JDK 1.6
     */
    private Model getSecondKillData(CmsAppFloorRespDTO dto, Short tempNo) {
        LogUtil.info(MODULE, "开始查询秒杀数据");
        PromDTO promReqDto = new PromDTO();
        promReqDto.setPageSize(3);
        promReqDto.setSiteId(this.SITEID);
        List<KillGdsInfoDTO> resultList = null;
        try {
            resultList = promQueryRSV.killPromGdsinfoList(promReqDto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询秒杀数据异常！", e);
        }
        if(CollectionUtils.isEmpty(resultList)){
            return null;
        }
        Model model = new Model();
        List<Item> itemList  = new ArrayList<Cms101Resp.Item>();
        
        for(KillGdsInfoDTO gdsInfo : resultList){
            if(null == gdsInfo || StringUtil.isEmpty(gdsInfo.getGdsId())){
                continue;
            }
            Item item = new Item();
            item.setGdsId(gdsInfo.getGdsId());
            item.setName(gdsInfo.getGdsName());
            item.setImgUrl(ParamsTool.getImageUrl(gdsInfo.getMediaUuid(), "160x160!"));
            item.setGuidPrice(gdsInfo.getBasePrice());
            item.setSkuId(gdsInfo.getSkuId());
            item.setDiscountPrice(gdsInfo.getKillPrice());
            String clickUrl = "/pmph/goodInfo/pageInit";
            if(StringUtil.isNotEmpty(item.getGdsId())){
                clickUrl +="?gdsId="+item.getGdsId();
            }
            if(StringUtil.isNotEmpty(item.getSkuId())){
                clickUrl +="&skuId="+item.getSkuId();
            };
            item.setClickUrl(clickUrl);
            itemList.add(item);
        }
        if(CollectionUtils.isEmpty(itemList)){
            return null;
        }
        
        model.setItemList(itemList);
        model.setType((int)tempNo);
        if(StringUtil.isNotBlank(dto.getLinkUrl())){
            model.setClickUrl(dto.getLinkUrl()); 
        }else{
            model.setClickUrl("/pmph/seckill/pageInit");
        }
        if(StringUtil.isNotBlank(dto.getFloorName())){
            model.setName(dto.getFloorName());
        }else{
            model.setName("限时秒杀");
        }
        return model;
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
     * @throws Exception 
     * @since JDK 1.6
     */
    private Model getFloorData(CmsAppFloorRespDTO foorRespDto,Short tempNo) throws Exception {
        Model floorData = null;
        List<Item> itemList  = new ArrayList<Cms101Resp.Item>();
        
        if(foorRespDto!=null && StringUtil.isNotEmpty(foorRespDto.getId()) && StringUtil.isNotEmpty(foorRespDto.getFloorTemplateId())){
            floorData = new Model();
            //转化楼层数据
            floorData.setClickUrl(CmsCommonUtil.getClickUrl(this.SITEID, foorRespDto.getLinkUrl(),"", ""));
            floorData.setName(foorRespDto.getFloorName());
            floorData.setType((int)tempNo);
            
            //根据模板id查询相关内容位置
            List<CmsFloorPlaceRespDTO> floorPlaceRespList = null;
            CmsFloorPlaceReqDTO floorPlaceReqDto = new CmsFloorPlaceReqDTO();
            floorPlaceReqDto.setFloorTemplateId(foorRespDto.getFloorTemplateId());
            floorPlaceReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            try {
                floorPlaceRespList = cmsFloorPlaceRSV.queryCmsFloorPlaceList(floorPlaceReqDto);
            } catch (Exception e) {
                LogUtil.error(MODULE, "查询楼层内容位置出错",e);
                throw new Exception("查询楼层内容位置出错");
            }

            if (CollectionUtils.isNotEmpty(floorPlaceRespList)) {
                
                //标记是否为广告模板
                boolean isAd = false;
                if( 200 <= tempNo && 300 > tempNo){
                    isAd = true;
                }
                
                for (CmsFloorPlaceRespDTO Dto : floorPlaceRespList) {
                    Item item = this.getFloorDataItem(foorRespDto, Dto,isAd);
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
     * getChannelData:(获取栏目数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @return 
     * @throws Exception 
     * @since JDK 1.6
     */
    private Model getChannelData(Long siteId) throws Exception {
        if(StringUtil.isEmpty(siteId)){
            return null;
        }
        
        Model channelData = new Model();
        List<Item> itemList  = new ArrayList<Cms101Resp.Item>();
    
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
            LogUtil.info(MODULE, "==========Cms101 app获取首页数据服务==查询栏目记录 发生异常============");
            throw new Exception("获取栏目异常！");
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
                    item.setClickUrl(CmsCommonUtil.getClickUrl(this.SITEID,respDto.getChannelUrl(), null, null));
                    item.setImgUrl(ImageUtil.getImageUrl(respDto.getChannelIcon()));;
                    itemList.add(item);
                    count ++;
                }
            }
        }
        //返回数据
        channelData.setType((int)this.CHANNELTYPE);
        if(CollectionUtils.isNotEmpty(itemList)){
            channelData.setItemList(itemList);
        }else{
            return null;
        }
        return channelData;
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
     * @throws Exception 
     * @since JDK 1.6
     */
    private Model getInfoData(Long siteId) throws Exception {

        CmsInfoReqDTO reqDTO = new CmsInfoReqDTO();
        reqDTO.setSiteId(siteId);
        //设置有效
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        //设置发布截至时间
        reqDTO.setThisTime(DateUtil.getSysDate());
        //reqDTO.setThisTime(DateUtil.getTheDayFirstSecond(DateUtil.getSysDate()));
        
        Model infoData = new Model();
        List<Item> itemList = new ArrayList<Cms101Resp.Item>();
        List<CmsInfoRespDTO> infoList = null;
        try {
            infoList = cmsInfoRSV.queryCmsInfoList(reqDTO);
        } catch (Exception e) {
            LogUtil.info(MODULE, "==========Cms101 app获取信息服务异常============");
            throw new Exception("获取信息异常！");
        }
        if(CollectionUtils.isNotEmpty(infoList)){
            String mobileUrl = CmsCommonUtil.getMobileMallUrl(this.SITEID);
            for(CmsInfoRespDTO dto : infoList){
                if(dto!=null && StringUtil.isNotEmpty(dto.getId())){
                    Item item = new Item();
                    item.setName(dto.getInfoTitle());
                    item.setTypeName(dto.getTypeName());
                    if(StringUtil.isNotBlank(mobileUrl)){ //微信的促销页
                        item.setClickUrl("/pmph/webview/pageInit?url="+mobileUrl+"/info/infoDetail4App?id="+dto.getId());
                    }
                    itemList.add(item);
                }
            }
        }
        infoData.setItemList(itemList);
        infoData.setType((int)this.INFOTYPE);
        infoData.setClickUrl("/pmph/infoList/pageInit");
        
        return infoData;
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
     * @throws Exception 
     * @since JDK 1.6
     */
    private Item getFloorDataItem(CmsAppFloorRespDTO foorRespDto, CmsFloorPlaceRespDTO floorPlaceDto,boolean isAd) throws Exception {
        Cms101Resp.Item item = null;
        
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
                LogUtil.info(MODULE, "==========Cms101 app获取楼层数据服务异常============");
                throw new Exception("获取楼层数据异常！");
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
                
                String adid = "";//标记是否为广告模板
                if(isAd){
                    adid = appFloorDataRespDto.getId().toString();
                }
                item.setClickUrl(CmsCommonUtil.getClickUrl(this.SITEID,appFloorDataRespDto.getLinkUrl(), appFloorDataRespDto.getLinkType(),adid));
            }
        }
        
        return item;
    }
    
    /**
     * 
     * getUserScoreData:(获取用户积分信息). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @return 
     * @since JDK 1.6
     */
    private Model getUserScoreData() {
        CmsPlaceReqDTO placeReqDto = new CmsPlaceReqDTO();
        if(StringUtil.isEmpty(placeReqDto.getStaff().getId()) || placeReqDto.getStaff().getId() <=0){//没登录
           return null; 
        }
        Model myScoreData = new Model();
        List<Item> itemList  = new ArrayList<Cms101Resp.Item>();
        
        
        //1.我的积分
        ScoreInfoResDTO scoreInfo = null;
        try {
            scoreInfo = scoreInfoRSV.findScoreInfoByStaffId(placeReqDto.getStaff().getId());
        } catch (Exception e) {
            LogUtil.error(MODULE, "==========查询cms101 app查询用户积分服务异常============");
            throw e;
        }
        Item scoreItem = new Cms101Resp.Item();
        scoreItem.setName("我的积分");
        scoreItem.setScore(scoreInfo.getScoreBalance());
        itemList.add(scoreItem);
        
        //2.兑换记录
        Item recordItem = new Cms101Resp.Item();
        recordItem.setName("兑换记录");
        recordItem.setClickUrl("/pmph/jfConvertRec/pageInit");
        itemList.add(recordItem);
        
        myScoreData.setItemList(itemList);
        myScoreData.setType(this.MYSCORETYPE);
        
        return  myScoreData;
    }
    
}

