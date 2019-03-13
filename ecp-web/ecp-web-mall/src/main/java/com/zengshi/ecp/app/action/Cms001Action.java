package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Cms001Req;
import com.zengshi.ecp.app.resp.Cms001Resp;
import com.zengshi.ecp.app.resp.cms.AdvertiseRespVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsAdvertiseRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.system.util.ParamsTool;
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
 * Description: <br>获取APP广告服务
 * Date:2016-2-22下午6:51:19  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */

@Service("cms001")
@Action(bizcode="cms001", userCheck=false)
@Scope("prototype")
public class Cms001Action extends AppBaseAction<Cms001Req, Cms001Resp> {

    private static String MODULE = Cms001Action.class.getName();
    private final Integer DEFAULTPAGESIZE= 1;
    private final Long  ADPLACEID = 1l;//店铺首页内容位置id
    private final Long  SITEID =  1l; //商城站点
    @Resource(name = "cmsAdvertiseRSV")
    private ICmsAdvertiseRSV cmsAdvertiseRSV;
    
    @Resource(name = "cmsPlaceRSV")
    private ICmsPlaceRSV cmsPlaceRSV;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        LogUtil.info(MODULE, "==========开始进入cms001 app获取广告服务============");
        //1. 获取首页轮播广告数据  
        this.response.setAdvertiseList(this.getAdData());
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
    private List<AdvertiseRespVO> getAdData() {
        List<AdvertiseRespVO> adData = null;
        
        Long adPlaceId = this.ADPLACEID;
        if(StringUtil.isNotEmpty(this.request.getPlaceId()) && this.request.getPlaceId() > 0){
            adPlaceId =  this.request.getPlaceId();
        }
        
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

        //2.查询广告
        CmsAdvertiseReqDTO reqDto = new CmsAdvertiseReqDTO();
        if(placeRespDto!=null && StringUtil.isNotEmpty(placeRespDto.getId())){
            
            reqDto.setPlaceId(adPlaceId);
            
            //设置店铺ID
            if(StringUtil.isNotEmpty(this.request.getShopId()) && this.request.getShopId() > 0){
                reqDto.setShopId(this.request.getShopId());
            }
            //设置数据状态
            reqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            reqDto.setPlatformType(CmsConstants.PlatformType.CMS_PLATFORMTYPE_02);
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
                        adData = this.transforAdData(respList,"");
                    }
                }
            } catch (Exception e) {
                LogUtil.error(MODULE, "==========查询cms008 app查询广告列表异常============");
                throw e;
            }
        }

        return adData;
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
    private List<AdvertiseRespVO> transforAdData(List<CmsAdvertiseRespDTO> respList, String standard) {
        List<AdvertiseRespVO> adData = new ArrayList<AdvertiseRespVO>();
        
        if(standard == null){
            standard = "";
        }
        
        if(CollectionUtils.isNotEmpty(respList)){
            for(CmsAdvertiseRespDTO dto : respList){
                if(StringUtil.isNotEmpty(dto.getVfsId())){//广告必须有图片
                    //转化为vo
                    AdvertiseRespVO adItem = new AdvertiseRespVO();
                    //调文件服务器，返回图片
                    adItem.setVfsUrl(ParamsTool.getImageUrl(dto.getVfsId(),standard));
                    //转化链接地址
                    if (StringUtil.isNotBlank(dto.getLinkType())) {
                        adItem.setLinkUrl(CmsCommonUtil.getClickUrl(this.SITEID,dto.getLinkUrl(),dto.getLinkType(),String.valueOf(dto.getId())));
                    }
                    adItem.setAdvertiseTitle(dto.getAdvertiseTitle());
                    if(StringUtil.isNotEmpty(adItem)){
                        adData.add(adItem);
                    }
                }
            }
        }
        return adData;
    }
}

