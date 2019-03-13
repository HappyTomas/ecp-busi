package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Cms004Req;
import com.zengshi.ecp.app.resp.Cms004Resp;
import com.zengshi.ecp.app.resp.cms.FloorTabRespVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTabRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
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
 * Description: 获取APP排行榜服务<br>
 * Date:2016-2-22下午6:51:19  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */

@Service("cms004")
@Action(bizcode="cms004", userCheck=false)
@Scope("prototype")
public class Cms004Action extends AppBaseAction<Cms004Req, Cms004Resp> {

    private static String MODULE = Cms004Action.class.getName();
    
    private final Long  SITEID =  1l; //商城站点
    
    private final Integer TABSIZE = 1000;//返回tab数
    
    private final Long PLACEID = 1702l;
    
    @Resource(name = "cmsFloorRSV")
    private ICmsFloorRSV cmsFloorRSV;
    
    
    @Resource(name = "cmsFloorTabRSV")
    private ICmsFloorTabRSV cmsFloorTabRSV;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        LogUtil.info(MODULE, "==========开始进入cms004 app获取排行榜服务============"); 
        
        if(this.request ==null){
            this.request = new Cms004Req();
        }
        
        //1. 获取参数
        CmsFloorReqDTO floorReqDto = new CmsFloorReqDTO();
        // 1.1设置内容位置id
        Long placeId = this.PLACEID;
        if(StringUtil.isNotEmpty(this.request.getPlaceId())){
            placeId = this.request.getPlaceId();
        }
        floorReqDto.setPlaceId(placeId);
        // 1.2 设置站点
        floorReqDto.setSiteId(SITEID);
        // 1.3 设置状态 默认有效
        floorReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);

        // 2. 调用服务
        CmsFloorRespDTO cmsFloorRespDTO = null;
        List<CmsFloorRespDTO> floorRespList = null;
        try {
            // 2.1 获取楼层
            floorRespList = cmsFloorRSV.queryCmsFloorList(floorReqDto);
        } catch (Exception e) {
            LogUtil.info(MODULE, "==========cms004  getResponse  app获取排行榜服务发生异常============");
        }
        if (CollectionUtils.isNotEmpty(floorRespList)) {
            cmsFloorRespDTO = floorRespList.get(0);
        }

        // 2.2 获取楼层页签 及楼层数据
        if (cmsFloorRespDTO != null && StringUtil.isNotEmpty(cmsFloorRespDTO.getId())) {
            // 转化数据
            ObjectCopyUtil.copyObjValue(cmsFloorRespDTO, this.response, null, false);

            // 2.2.1 获取楼层页签
            List<FloorTabRespVO> tabList = null;
            tabList = this.getTabList(cmsFloorRespDTO);
            this.response.setTabList(tabList);
        }
    }

    /**
     * 
     * getTabList:(根据楼层获取指定数量的页签  并转化为FloorTabRespVO). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh
     * @param floorId
     * @param tabSize
     * @return 
     * @since JDK 1.6
     */
    private List<FloorTabRespVO> getTabList(CmsFloorRespDTO floor) {
        List<FloorTabRespVO> tabRespVoList= new ArrayList<FloorTabRespVO>();
        List<CmsFloorTabRespDTO> floorTabRespList = null;
        
        CmsFloorTabReqDTO tabReqDto = new CmsFloorTabReqDTO();
        if(floor!=null && StringUtil.isNotEmpty(floor.getId())){
            //1.设置查询参数
            tabReqDto.setFloorId(floor.getId());
            tabReqDto.setPlaceId(floor.getPlaceId());
            tabReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            tabReqDto.setPageNo(1);
            //设置查询tab页数量
            int tabSize = this.TABSIZE;
            if(StringUtil.isNotEmpty(this.request.getTabSize()) && this.request.getTabSize() >= 0){
                tabSize = this.request.getTabSize();
            }
            tabReqDto.setPageSize(tabSize);
            PageResponseDTO<CmsFloorTabRespDTO> floorTabPageInfo = null;
            //2.调用服务
            try {
                floorTabPageInfo = cmsFloorTabRSV.queryCmsFloorTabPage(tabReqDto);
            } catch (Exception e) {
                LogUtil.info(MODULE, "==========cms004  getTabList  app获取排行榜页签服务发生异常============"); 
            }
            if(null != floorTabPageInfo){
                floorTabRespList = floorTabPageInfo.getResult();
            }
            //转化数据
            if(CollectionUtils.isNotEmpty(floorTabRespList)){
                for(CmsFloorTabRespDTO dto : floorTabRespList){
                    if(StringUtil.isNotEmpty(dto.getId())){
                        FloorTabRespVO respVo = new FloorTabRespVO();
                        ObjectCopyUtil.copyObjValue(dto, respVo, null, false);
                        if(CmsConstants.DataSource.CMS_DATA_SOURCE_02.equalsIgnoreCase(floor.getDataSource()) && StringUtil.isBlank(dto.getCatgCode()) && StringUtil.isNotBlank(floor.getCatgCode())){//取行为分析 且tab数据的商品分类为空
                            respVo.setCatgCode(floor.getCatgCode());
                        }
                        respVo.setDataSource(floor.getDataSource());
                        respVo.setCountType(floor.getCountType());
                        tabRespVoList.add(respVo);
                    }
                }
            }
        }
        return tabRespVoList;
    }

}

