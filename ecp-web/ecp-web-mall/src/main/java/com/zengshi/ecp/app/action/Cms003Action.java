package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Cms003Req;
import com.zengshi.ecp.app.resp.Cms003Resp;
import com.zengshi.ecp.app.resp.cms.AppFloorDataRespVO;
import com.zengshi.ecp.app.resp.cms.AppFloorRespVO;
import com.zengshi.ecp.app.resp.gds.GdsBaseInfo;
import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorDataReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorDataRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorDataRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorPlaceRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTemplateRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 获取APP楼层服务<br>
 * Date:2016-2-22下午6:51:19  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version  
 * @since JDK 1.6 
 */

@Service("cms003")
@Action(bizcode="cms003", userCheck=false)
@Scope("prototype")
public class Cms003Action extends AppBaseAction<Cms003Req, Cms003Resp> {

    private static String  MODULE =  Cms003Action.class.getName();
   
    private static Long  SITEID =  1l; //商城站点
    
    @Resource(name = "cmsAppFloorRSV")
    private ICmsAppFloorRSV cmsAppFloorRSV;
    
    @Resource(name = "cmsFloorTemplateRSV")
    private ICmsFloorTemplateRSV cmsFloorTemplateRSV;
    
    @Resource(name = "cmsFloorPlaceRSV")
    private ICmsFloorPlaceRSV cmsFloorPlaceRSV;
    
    @Resource(name = "cmsAppFloorDataRSV")
    private ICmsAppFloorDataRSV cmsAppFloorDataRSV;
    
    @Resource(name = "gdsInfoQueryRSV")
    private IGdsInfoQueryRSV gdsInfoQueryRSV;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        LogUtil.info(MODULE, "==========开始进入cms003 app获取楼层服务============");
        
        List<AppFloorRespVO> appFloorVOList = new ArrayList<AppFloorRespVO>();
        //1.获取参数  并初始化app楼层请求参数
        CmsAppFloorReqDTO floorReqDto = new CmsAppFloorReqDTO();
        
        //设置楼层状态
        if(StringUtil.isNotBlank(this.request.getStatus())){
            floorReqDto.setStatus(this.request.getStatus());
        }else{
            floorReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        }
        
        //设置站点
        floorReqDto.setSiteId(SITEID);
        
        //2.调用app楼层服务。查询该站点下指定状态的app楼层
        List<CmsAppFloorRespDTO> appFloorList = null;
        try {
            appFloorList = cmsAppFloorRSV.queryCmsAppFloorList(floorReqDto);
            if(CollectionUtils.isNotEmpty(appFloorList)){
                for(CmsAppFloorRespDTO Dto : appFloorList){
                    AppFloorRespVO appFloorVO = this.getAppFloorVO(Dto);
                    if(appFloorVO != null && StringUtil.isNotEmpty(appFloorVO.getId())){
                        appFloorVOList.add(appFloorVO);
                    }
                }
            }
        } catch (Exception e) {
            LogUtil.info(MODULE, "==========cms003 app获取楼层服务异常============");
            e.printStackTrace();
        }
        
        //3. 返回数据
        this.response.setAppFloorList(appFloorVOList);
    }

    /**
     * 
     * getAppFloorVO:(将CmsAppFloorRespDTO 转化为AppFloorRespVO). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param foorRespDto
     * @return 
     * @since JDK 1.6
     */
    private AppFloorRespVO getAppFloorVO (CmsAppFloorRespDTO foorRespDto){
        AppFloorRespVO appFloorVO = null;
        List<AppFloorDataRespVO> appFloorDataVOList = new ArrayList<AppFloorDataRespVO>();
        
        if(foorRespDto!=null && StringUtil.isNotEmpty(foorRespDto.getId())){
            appFloorVO = new AppFloorRespVO();
            ObjectCopyUtil.copyObjValue(foorRespDto, appFloorVO, null, false);
            
            //根据模板id查询相关内容位置
            if(StringUtil.isNotEmpty(foorRespDto.getFloorTemplateId())){
                List<CmsFloorPlaceRespDTO> floorPlaceRespList =null;
                CmsFloorPlaceReqDTO floorPlaceReqDto = new CmsFloorPlaceReqDTO();
                floorPlaceReqDto.setFloorTemplateId(foorRespDto.getFloorTemplateId());
                floorPlaceReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                floorPlaceRespList = cmsFloorPlaceRSV.queryCmsFloorPlaceList(floorPlaceReqDto);
                
                if(CollectionUtils.isNotEmpty(floorPlaceRespList)){
                    for(CmsFloorPlaceRespDTO Dto : floorPlaceRespList){
                        AppFloorDataRespVO floorDataVo = this.getAppFloorDataVO(foorRespDto,Dto);
                        if(floorDataVo != null && StringUtil.isNotEmpty(floorDataVo.getId())){
                            appFloorDataVOList.add(floorDataVo);
                        }
                    }
                }
            }
          //返回数据
            appFloorVO.setFloorDataList(appFloorDataVOList);
        }
        return appFloorVO;
    }

    /**
     * 
     * getAppFloorDataVO:(通过app楼层数据，及模板内容位置数据，查询对应的app楼层数据，并转化为AppFloorDataRespVO). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param foorRespDto
     * @param floorPlaceDto
     * @return 
     * @since JDK 1.6
     */
    private AppFloorDataRespVO getAppFloorDataVO(CmsAppFloorRespDTO foorRespDto,CmsFloorPlaceRespDTO floorPlaceDto) {
        AppFloorDataRespVO floorDataRespVO = null;
        
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
                LogUtil.info(MODULE, "==========cms003 app获取楼层数据服务异常============");
                appFloorDataRespDto = null;
                e.printStackTrace();
            }
        
            //2.扩展app楼层数据的数据  
            if(appFloorDataRespDto != null && StringUtil.isNotEmpty(appFloorDataRespDto.getId())){
                floorDataRespVO = new AppFloorDataRespVO();
                //2.1.获取图片地址
                String standard = null;
                if(StringUtil.isNotEmpty(floorPlaceDto.getPlaceHeight()) && StringUtil.isNotEmpty(floorPlaceDto.getPlaceWidth())){
                    standard =  floorPlaceDto.getPlaceWidth() + "x" + floorPlaceDto.getPlaceHeight() + "!";
                }else{
                    standard = "";
                }
                appFloorDataRespDto.setVfsUrl(ParamsTool.getImageUrl(appFloorDataRespDto.getVfsId(),standard));
            
                //2.2. 转化数据
                ObjectCopyUtil.copyObjValue(appFloorDataRespDto, floorDataRespVO, null, false);
                floorDataRespVO.setSortNo(floorPlaceDto.getSortNo());
                
                //2.3.如果是商品类型  获取商品数据
                if(CmsConstants.LinkType.CMS_LINKTYPE_01.equalsIgnoreCase(floorDataRespVO.getLinkType())){
                    GdsBaseInfo gdsInfo = null;
                    gdsInfo = this.getGdsInfo(appFloorDataRespDto,standard);
                    if(gdsInfo != null && StringUtil.isNotEmpty(gdsInfo.getGdsId())){
                        floorDataRespVO.setGdsInfo(gdsInfo);
                        floorDataRespVO.setLinkUrl("?goPage=goodDetail"+"?gdsId="+gdsInfo.getGdsId()+"?skuId="+gdsInfo.getSkuId()+"?shopId="+gdsInfo.getShopId());
                    }
                }
            }
        }
        
        return floorDataRespVO;
    }

    /**
     * 
     * getGdsInfo:(根据楼层数据信息，获取商品信息). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param appFloorDataRespDto
     * @return 
     * @since JDK 1.6
     */
    private GdsBaseInfo getGdsInfo(CmsAppFloorDataRespDTO appFloorDataRespDto,String standard) {
        GdsBaseInfo gdsInfo = null;
        if(appFloorDataRespDto != null && CmsConstants.LinkType.CMS_LINKTYPE_01.equalsIgnoreCase(appFloorDataRespDto.getLinkType()) && StringUtil.isNotBlank(appFloorDataRespDto.getLinkUrl())){
            Long gdsId = null;
            try {
                gdsId = Long.parseLong(appFloorDataRespDto.getLinkUrl());
            } catch (Exception e) {
                LogUtil.error(MODULE, "===getGdsInfo==方法===商品id转化为Long类型失败======");
                e.printStackTrace();
                gdsId =null;
            }
            
            //查询商品信息
            if(StringUtil.isNotEmpty(gdsId)){
                GdsInfoDetailRespDTO gdsInfoDetailRespDTO = null;
                GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
                GdsQueryOption[] gdsOptions=new GdsQueryOption[]{
                        GdsQueryOption.BASIC,GdsQueryOption.MAINPIC
                };
                SkuQueryOption[] skuOptions=new SkuQueryOption[]{
                        SkuQueryOption.BASIC,SkuQueryOption.CAlDISCOUNT
                };
                
                gdsInfoReqDTO.setGdsQueryOptions(gdsOptions);
                gdsInfoReqDTO.setSkuQuerys(skuOptions);
                gdsInfoReqDTO.setId(gdsId);
                
                try {
                    gdsInfoDetailRespDTO = gdsInfoQueryRSV.queryGdsInfoDetail(gdsInfoReqDTO);
                } catch (Exception e) {
                    LogUtil.error(MODULE, "===getGdsInfo==方法===查询商品详情异常======");
                    e.printStackTrace();
                    gdsInfoDetailRespDTO =null;
                }
                
                if(gdsInfoDetailRespDTO != null && StringUtil.isNotEmpty(gdsInfoDetailRespDTO.getId())){
                    //商品图片
                    GdsMediaRespDTO gdsMediaRespDTO = gdsInfoDetailRespDTO.getMainPic();
                    if(gdsMediaRespDTO == null){
                        gdsMediaRespDTO = new GdsMediaRespDTO();
                        gdsInfoDetailRespDTO.setMainPic(gdsMediaRespDTO);
                    }
                    gdsMediaRespDTO.setURL(ParamsTool.getImageUrl(gdsMediaRespDTO.getMediaUuid(), standard));
                    
                    //转化数据
                    gdsInfo = new GdsBaseInfo();
                    
                    gdsInfo.setGdsId(gdsInfoDetailRespDTO.getId());
                    gdsInfo.setGdsName(gdsInfoDetailRespDTO.getGdsName());
                    gdsInfo.setImgPath(gdsInfoDetailRespDTO.getMainPic().getURL());
                    gdsInfo.setShopId(gdsInfoDetailRespDTO.getShopId());
                    
                    if(gdsInfoDetailRespDTO.getSkuInfo() != null){
                        gdsInfo.setSkuId(gdsInfoDetailRespDTO.getSkuInfo().getId());
                        gdsInfo.setBuyPrice(gdsInfoDetailRespDTO.getSkuInfo().getDiscountPrice());
                        gdsInfo.setBasePrice(gdsInfoDetailRespDTO.getSkuInfo().getRealPrice());
                    }
                    
                }  
            }
        }
        return gdsInfo;
    }

}

