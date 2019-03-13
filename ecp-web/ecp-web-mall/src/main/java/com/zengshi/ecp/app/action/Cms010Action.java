package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Cms010Req;
import com.zengshi.ecp.app.resp.Cms010Resp;
import com.zengshi.ecp.app.resp.Cms010Resp.Item;
import com.zengshi.ecp.app.resp.Cms010Resp.Model;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.utils.CmsAnalUtil;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.utils.CmsGoodsDetailUtil;
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
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorDataRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsChannelRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorPlaceRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTemplateRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsInfoRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.KillGdsInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
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
 * Date:2016-10-04下午6:51:19  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version  
 * @since JDK 1.6 
 */

@Service("cms010")
@Action(bizcode="cms010", userCheck=false)
@Scope("prototype")
public class Cms010Action extends AppBaseAction<Cms010Req, Cms010Resp> {

    private static String  MODULE =  Cms010Action.class.getName();
    
    private final Long  SITEID =  1l; //商城站点
    
    private final Long CHANNELPARENTCODE = 0l;//0代表根节点
    
    private final Integer CHANNELSIZE = 8;//首页栏目个数
    
    //private final Integer ADTYPE = 210;//轮播广告指定定的模板编码
    
    private final Short CHANNELTYPE = 310;//栏目指定的模板编码
    
    private final Short INFOTYPE = 510;//信息指定的模板编码
    
    private final Short GUESSTYPE = 410;//猜你喜欢指定的模板编码

    private final int GUESSPAGESIZE = 8;//猜你喜欢一页数据
    
    private final int GUESSPAGEONE = 1;//猜你喜欢默认取第一页
    
    private final int GUESSGDSNUM = 5000;//猜你喜欢总数
    
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
    
    @Resource(name = "cmsInfoRSV")
    private ICmsInfoRSV cmsInfoRSV;
    
    @Resource(name = "promQueryRSV")
    private IPromQueryRSV promQueryRSV;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        LogUtil.info(MODULE, "==========开始进入cms010 app获取楼层服务============");
        
        if(this.request == null){
            this.request = new Cms010Req();
        }
        
        List<Cms010Resp.Model> datas = new ArrayList<Cms010Resp.Model>(); //首页数据
        
        Long siteId = this.request.getSiteId();
        if(StringUtil.isEmpty(siteId) || siteId <=0){
            siteId = this.SITEID;
        }
        
        Integer guessPageNo = this.request.getGuessPageNo();
        if(StringUtil.isEmpty(guessPageNo) || guessPageNo <=0){
            guessPageNo = this.GUESSPAGEONE;
        }
        
        if(this.GUESSPAGEONE == guessPageNo){//取第一页则取首页数据
            //获取APP楼层
            List<CmsAppFloorRespDTO> appFloorList = this.qryProAppFloor(siteId);
            List<Cms010Resp.Model> floorDatas = this.doAppFloor(appFloorList, siteId);
            if(CollectionUtils.isNotEmpty(floorDatas)){
                datas.addAll(floorDatas);
            }
        }else{//取猜你喜欢分页数据
            Cms010Resp.Model guessData = this.getGuessPageData(siteId);
            if(null != guessData){
                datas.add(guessData);
            }
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
            LogUtil.info(MODULE, "==========cms010 app获取楼层服务异常============");
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
        List<Model> floorDatas = new ArrayList<Cms010Resp.Model>();
        Model guessData = null;//猜你喜欢只会有一个且再最后面
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
                }else if(this.GUESSTYPE.shortValue() == tempNo.shortValue() && null == guessData){//取猜你喜欢数据源
                    try {
                        guessData = this.getGuessData(siteId); 
                    } catch (Exception e) {
                        LogUtil.error(MODULE, "调用行为分析获取猜你喜欢异常");
                    }
                    if(null != guessData && StringUtil.isNotBlank(Dto.getFloorName())){
                        guessData.setName(Dto.getFloorName()); 
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
        if(guessData != null && StringUtil.isNotEmpty(guessData.getType())){
            guessData.setColor(this.COLORS[colorIndex%6]);
            colorIndex ++ ;
            floorDatas.add(guessData);
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
        List<Item> itemList  = new ArrayList<Cms010Resp.Item>();
        
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
     * 
     * getGuessPageData:(获取猜你喜欢分页数据  只有再手机上拉加载更多是调用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @return 
     * @since JDK 1.6
     */
    private Model getGuessPageData(Long siteId){
        Model guessData = null;
        //判断是否有猜你喜欢楼层
        boolean checkGuessFloor = false;
        try {
            checkGuessFloor = this.checkGuessFloor(siteId);
        } catch (Exception e) {
            LogUtil.info(MODULE, "==========cms010 app检查首页是否有猜你喜欢楼层方法异常============");
        }
        if(checkGuessFloor){
            try {
                guessData = this.getGuessData(siteId);
            } catch (Exception e) {
                LogUtil.info(MODULE, "==========cms010 app首页获取猜你喜欢数据异常============");
            }
        }
        return guessData;
    }
    /**
     * 
     * getGuessData:(获取猜你喜欢服务). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param siteId2
     * @return 
     * @throws Exception 
     * @since JDK 1.6
     */
    @SuppressWarnings("unchecked")
    private Model getGuessData(Long siteId) throws Exception {
        Map<String,Object> guessResultMap = null;
        Integer pageSize = this.request.getGuessPageSize();
        if(StringUtil.isEmpty(pageSize) || 0 >= pageSize){
            pageSize = this.GUESSPAGESIZE;
        }
        Integer gdsNum = this.request.getGuessGdsNum();
        if(StringUtil.isEmpty(gdsNum) || 0 >= gdsNum){
            gdsNum = this.GUESSGDSNUM;
        }
        Integer guessPageNo = this.request.getGuessPageNo();
        if(StringUtil.isEmpty(guessPageNo) || guessPageNo <=0){
            guessPageNo = this.GUESSPAGEONE;
        }
        List<Long> propIds = new ArrayList<Long>();
        propIds.add(1001l);//作者
        //propIds.add(1005l);//出版日期
        //propIds.add(1006l);//出版社
        //propIds.add(1020l);//内容简介 
        //调用猜你喜欢服务获取数据
        List<GdsInfoDetailRespDTO> gdsList = null;
        try {
            guessResultMap = CmsAnalUtil.getAnalysGuessData(gdsNum,propIds,true,guessPageNo, pageSize);
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取猜你喜欢数据异常!");
            gdsList =null; //发生异常则不展示猜你喜欢楼层
        }
        if(null != guessResultMap && guessResultMap.containsKey("pageInfo") && null != guessResultMap.get("pageInfo")){
            EcpBasePageRespVO<GdsInfoDetailRespDTO> gdsPageInfo = null;
            try {
                gdsPageInfo = (EcpBasePageRespVO<GdsInfoDetailRespDTO>) guessResultMap.get("pageInfo");
            } catch (Exception e) {
                LogUtil.error(MODULE, "从行为分析数据map转化为分页数据异常！", e);
            }
            if(null != gdsPageInfo){
                gdsList =  gdsPageInfo.getList();
            }
        }
        if(CollectionUtils.isEmpty(gdsList)){
            return null;
        }
        
        //转化数据
        Model floorData = new Model();
        floorData.setType((int)(this.GUESSTYPE));
        floorData.setName("猜你喜欢");
        
        List<Item> itemList  = new ArrayList<Cms010Resp.Item>();
        for(GdsInfoDetailRespDTO gdsInfo : gdsList){
            if(null == gdsInfo || StringUtil.isEmpty(gdsInfo.getId())){
                continue;
            }
            //扩展商品数据
            CmsGoodsDetailUtil.extendGdsInfo(gdsInfo, propIds, false, true, "320x320!");
            //扩展价格
            CmsGoodsDetailUtil.getGdsPromPrice(gdsInfo, CmsConstants.PlatformType.CMS_PLATFORMTYPE_03, null);
            Item item = null;
            try {
                item = this.converGuessData(gdsInfo);
            } catch (Exception e) {
                LogUtil.error(MODULE, "转化猜你喜欢商品详情数据成item异常!",e);
            }
            if(null!=item){
                itemList.add(item);
            }
        }
        floorData.setItemList(itemList);
        return floorData;
    }
    /**
     * 
     * converGuessData:(转化猜你喜欢商品详情数据成item). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param gdsInfo
     * @return 
     * @since JDK 1.6
     */
    private Item converGuessData(GdsInfoDetailRespDTO gdsInfo){
        Item item = new Item();
        item.setGdsId(gdsInfo.getId());
        item.setName(gdsInfo.getGdsName());
        item.setImgUrl(gdsInfo.getMainPic().getURL());
        item.setGuidPrice(gdsInfo.getGuidePrice());
        if(null != gdsInfo.getSkuInfo()){
            item.setSkuId(gdsInfo.getSkuInfo().getId());
            item.setDiscountPrice(gdsInfo.getSkuInfo().getDiscountPrice());
        }
        item.setCatgCode(gdsInfo.getMainCatgs());
        String clickUrl = "/pmph/goodInfo/pageInit";
        if(StringUtil.isNotEmpty(item.getGdsId())){
            clickUrl +="?gdsId="+item.getGdsId();
        }
        if(StringUtil.isNotEmpty(item.getSkuId())){
            clickUrl +="&skuId="+item.getSkuId();
        };
        item.setClickUrl(clickUrl);
        
        GdsCollectRespDTO collectDto = null;
        try {
            collectDto = CmsGoodsDetailUtil.checkCollect(gdsInfo.getId());
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询商品是否被收藏异常!");
        }
        if(null != collectDto && StringUtil.isNotEmpty(collectDto.getId())){
            item.setCollectId(collectDto.getId());
        }
        return item;
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
        List<Item> itemList  = new ArrayList<Cms010Resp.Item>();
        
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
        List<Item> itemList  = new ArrayList<Cms010Resp.Item>();
    
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
            LogUtil.info(MODULE, "==========cms010 app获取首页数据服务==查询栏目记录 发生异常============");
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
        List<Item> itemList = new ArrayList<Cms010Resp.Item>();
        List<CmsInfoRespDTO> infoList = null;
        try {
            infoList = cmsInfoRSV.queryCmsInfoList(reqDTO);
        } catch (Exception e) {
            LogUtil.info(MODULE, "==========cms010 app获取信息服务异常============");
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
        Cms010Resp.Item item = null;
        
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
                LogUtil.info(MODULE, "==========cms010 app获取楼层数据服务异常============");
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
     * checkGuessFloor:(这里用一句话描述这个方法的作用). <br/> 
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
    private boolean checkGuessFloor(Long siteId) throws Exception{
        boolean checked = false;
        //调用app楼层服务。查询该站点下指定状态的app楼层
        List<CmsFloorTemplateRespDTO> tempList = null;
        try {
            tempList = this.qryFloorTemplate();
        } catch (Exception e) {
            LogUtil.info(MODULE, "==========cms011 app获取楼层模板服务异常============");
        }
        if(CollectionUtils.isNotEmpty(tempList)){
            for(CmsFloorTemplateRespDTO temp : tempList){
                List<CmsAppFloorRespDTO> appFloorList = null;
                //1.获取参数  并初始化app楼层请求参数
                CmsAppFloorReqDTO floorReqDto = new CmsAppFloorReqDTO();
                //设置楼层状态
                floorReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                //设置站点
                floorReqDto.setSiteId(siteId);
                floorReqDto.setFloorTemplateId(temp.getId());
                try {
                    appFloorList = cmsAppFloorRSV.queryCmsAppFloorList(floorReqDto);
                } catch (Exception e) {
                    LogUtil.info(MODULE, "==========cms011 app获取楼层服务异常============");
                    throw new Exception("获取楼层异常");
                } 
                if(CollectionUtils.isNotEmpty(appFloorList)){
                    checked = true;
                    break;
                }
            }
        }
        return checked;
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
    private List<CmsFloorTemplateRespDTO> qryFloorTemplate() {
        List<CmsFloorTemplateRespDTO> respDto = null;
        CmsFloorTemplateReqDTO reqDto = new CmsFloorTemplateReqDTO();
        reqDto.setTemplateNo(this.GUESSTYPE);
        reqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);;
        try {
            respDto = this.cmsFloorTemplateRSV.queryCmsFloorTemplateList(reqDto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询楼层模板出错",e);
            throw e;
        }
        return respDto;
    }
    
}

