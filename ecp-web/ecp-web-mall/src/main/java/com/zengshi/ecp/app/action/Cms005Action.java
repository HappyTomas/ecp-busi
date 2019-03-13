package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Cms005Req;
import com.zengshi.ecp.app.resp.Cms005Resp;
import com.zengshi.ecp.app.resp.gds.GdsBaseInfo;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.utils.CmsAnalUtil;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.utils.CmsGoodsDetailUtil;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorGdsRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTabRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 获取APP排行榜页签下商品服务<br>
 * Date:2016-2-22下午6:51:19  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version  
 * @since JDK 1.6 
 */

@Service("cms005")
@Action(bizcode="cms005", userCheck=false)
@Scope("prototype")
public class Cms005Action extends AppBaseAction<Cms005Req, Cms005Resp> {

    private static String MODULE = Cms005Action.class.getName();
    
    private final static Integer PAGENO= 1;
    
    private final static Integer PAGESIZE= 10;
    
    private final static String IMGSTANDARD= "320x320!";
    
    @Resource(name = "cmsFloorGdsRSV")
    private ICmsFloorGdsRSV cmsFloorGdsRSV;
    
    @Resource(name = "cmsFloorTabRSV")
    private ICmsFloorTabRSV cmsFloorTabRSV;

    @Resource(name = "gdsInfoQueryRSV")
    private IGdsInfoQueryRSV gdsInfoQueryRSV;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        LogUtil.info(MODULE, "==========开始进入cms005 app获取排行榜页签商品服务============"); 
        
        List<GdsBaseInfo> gdsInfoList = new ArrayList<GdsBaseInfo>();
        List<GdsInfoDetailRespDTO> gdsDetailInfoList = null;
        if(this.request == null){
            throw new Exception("请求参数为空");
        }
        //1 获取数据
        if(StringUtil.isEmpty(this.request.getTabId()) && StringUtil.isEmpty(this.request.getFloorId())){
            gdsDetailInfoList = new ArrayList<GdsInfoDetailRespDTO>();
        }else if(CmsConstants.DataSource.CMS_DATA_SOURCE_02.equalsIgnoreCase(this.request.getDataSource())){
            gdsDetailInfoList = this.getAnalRankGds();//行为分析
        }else{
            gdsDetailInfoList = this.getTabGdsData();//配置
        }
        //2 转化数据
        if(CollectionUtils.isNotEmpty(gdsDetailInfoList)){
            for(GdsInfoDetailRespDTO gdsDetail : gdsDetailInfoList){
                GdsBaseInfo gdsInfo = this.transformData(gdsDetail);
                if(null != gdsInfo && StringUtil.isNotEmpty(gdsInfo.getGdsId())){
                    gdsInfoList.add(gdsInfo);
                }
            }
        }
        
        //3.返回数据
        this.response.setGdsList(gdsInfoList);
    }

    /**
     * 
     * getTabGdsData:(获取配置的页签商品). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param request
     * @return 
     * @throws Exception 
     * @since JDK 1.6
     */
    private List<GdsInfoDetailRespDTO> getTabGdsData() throws Exception {
        LogUtil.info(MODULE, "调用排行榜配置页签商品开始：");
        List<GdsInfoDetailRespDTO> gdsDetailInfoList = new ArrayList<GdsInfoDetailRespDTO>();
        // 1. 设置入参
        CmsFloorGdsReqDTO reqDto = new CmsFloorGdsReqDTO();
        
        reqDto.setTabId(this.request.getTabId());
        this.response.setTabId(this.request.getTabId());
        
        if(StringUtil.isNotEmpty(this.request.getFloorId())){
            reqDto.setFloorId(this.request.getFloorId());
        }
        
        //设置状态  默认有效
        reqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        
        //设置查询页数
        Integer pageNo = PAGENO;
        Integer pageSize = PAGESIZE;
        if(StringUtil.isNotEmpty(this.request.getPageNo())){
            pageNo = this.request.getPageNo();
        }
        if(StringUtil.isNotEmpty(this.request.getPageSize())){
            pageSize = this.request.getPageSize();
        }
        reqDto.setPageSize(pageSize);
        //2. 调用服务   只展示上架商品
        List<CmsFloorGdsRespDTO> respList = new ArrayList<CmsFloorGdsRespDTO>();
        PageResponseDTO<CmsFloorGdsRespDTO> pageInfo = null;
        List<CmsFloorGdsRespDTO> pageInfoGdsList = null;
        for(int respSize = respList.size();respSize < pageSize; pageNo++,respSize = respList.size()){
            pageInfo = null;
            pageInfoGdsList= null;
            reqDto.setPageNo(pageNo);
            try {
                pageInfo = cmsFloorGdsRSV.queryCmsFloorGdsPage(reqDto);
            } catch (Exception e) {
                LogUtil.error(MODULE, "==========cms005  app获取排行榜配置第"+pageNo+"页商品服务发生异常============",e); 
                throw new Exception("查询页签商品异常");
            }
            if(null != pageInfo){
                pageInfoGdsList = pageInfo.getResult();
            }
            if(CollectionUtils.isEmpty(pageInfoGdsList)){
                break;
            }
            for(CmsFloorGdsRespDTO floorGdsDto: pageInfoGdsList){
                if(CmsGoodsDetailUtil.isGdsOnShelves(floorGdsDto.getGdsId())){
                    respList.add(floorGdsDto);
                    if(pageSize <= respList.size()){
                        break;
                    }
                }
            }
        }
        
        //3. 扩展商品信息
        if(CollectionUtils.isNotEmpty(respList)){
            for(CmsFloorGdsRespDTO respDto : respList){
                GdsInfoDetailRespDTO gdsDetailInfo = null;
                gdsDetailInfo = this.getGdsInfoForTab(respDto);
                if(gdsDetailInfo != null && StringUtil.isNotEmpty(gdsDetailInfo.getId())){
                    gdsDetailInfoList.add(gdsDetailInfo);
                }
            }
        }
        LogUtil.info(MODULE, "调用排行榜配置页签商品结束：");
        return gdsDetailInfoList;
    }

    /**
     * 
     * getGdsInfoForTab:(获取配置的页签下商品信息). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh
     * @param respDto
     * @param standard
     * @return 
     * @since JDK 1.6
     */
    private GdsInfoDetailRespDTO getGdsInfoForTab(CmsFloorGdsRespDTO respDto) {
        GdsInfoDetailRespDTO gdsInfoDetailRespDTO = null;
        
        if(respDto != null && StringUtil.isNotEmpty(respDto.getGdsId())){
            //查询商品信息
            GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
            GdsQueryOption[] gdsOptions = new GdsQueryOption[] { GdsQueryOption.BASIC,
                    GdsQueryOption.MAINPIC };
            SkuQueryOption[] skuOptions = new SkuQueryOption[] { SkuQueryOption.BASIC,
                    SkuQueryOption.CAlDISCOUNT };
            List<Long> propIds = new ArrayList<Long>();
            propIds.add(1001l);//作者
            gdsInfoReqDTO.setGdsQueryOptions(gdsOptions);
            gdsInfoReqDTO.setSkuQuerys(skuOptions);
            gdsInfoReqDTO.setId(respDto.getGdsId());
            gdsInfoReqDTO.setPropIds(propIds);
            try {
                gdsInfoDetailRespDTO = CmsGoodsDetailUtil.getGdsDetailByGdsId(gdsInfoReqDTO);
                CmsGoodsDetailUtil.extendGdsInfo(gdsInfoDetailRespDTO, propIds, false, true, IMGSTANDARD);
                CmsGoodsDetailUtil.getGdsPromPrice(gdsInfoDetailRespDTO, CmsConstants.PlatformType.CMS_PLATFORMTYPE_02, null);
            } catch (Exception e) {
                LogUtil.error(MODULE, "====cms005  getGdsInfoForTab==方法===查询商品详情异常======");
                gdsInfoDetailRespDTO = null;
            }
        }
        return gdsInfoDetailRespDTO;
    }
    /**
     * 
     * getAnalRankData:(获取 行为分析数据). <br/> 
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
    private List<GdsInfoDetailRespDTO> getAnalRankGds() throws Exception {
        EcpBasePageRespVO<GdsInfoDetailRespDTO> gdsDetailInfoPage = null;
        List<GdsInfoDetailRespDTO> gdsDetailInfoList = null;
        
        Integer pageNo = PAGENO;
        Integer pageSize = PAGESIZE;
        String countType = null;
        String catgCode = null;
        Long shopId = null;
        
        if(StringUtil.isNotEmpty(this.request.getPageNo())){
            pageNo = this.request.getPageNo();
        }
        if(StringUtil.isNotEmpty(this.request.getPageSize())){
            pageSize = this.request.getPageSize();
        }
        if(StringUtil.isNotBlank(this.request.getCountType())){
            countType = this.request.getCountType();
        }
        if(StringUtil.isNotBlank(this.request.getCatgCode())){
            catgCode = this.request.getCatgCode();
        }
        List<Long> propIds = new ArrayList<Long>();
        propIds.add(1001l);//作者
        //propIds.add(1005l);//出版日期
        //propIds.add(1006l);//出版社
        //propIds.add(1020l);//内容简介 
        gdsDetailInfoPage = CmsAnalUtil.getAnalRankDataPage(pageNo, pageSize, countType, catgCode, shopId,propIds);
        if(null != gdsDetailInfoPage){
            gdsDetailInfoList = gdsDetailInfoPage.getList();
        }
        //扩展信息
        if(CollectionUtils.isNotEmpty(gdsDetailInfoList)){
            for(GdsInfoDetailRespDTO gdsInfo : gdsDetailInfoList ){
                CmsGoodsDetailUtil.extendGdsInfo(gdsInfo, propIds, false, true, IMGSTANDARD);
                CmsGoodsDetailUtil.getGdsPromPrice(gdsInfo, CmsConstants.PlatformType.CMS_PLATFORMTYPE_02, null);
            }
        }
        return gdsDetailInfoList;
    }
    /**
     * 
     * transformData:(将商品详情对象转化为app使用的GdsBaseInfo对象). <br/> 
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
    private GdsBaseInfo transformData(GdsInfoDetailRespDTO gdsDetailInfo){
        GdsBaseInfo gdsInfo = null;
        if (gdsDetailInfo != null && StringUtil.isNotEmpty(gdsDetailInfo.getId())) {
            gdsInfo = new GdsBaseInfo();

            gdsInfo.setGdsId(gdsDetailInfo.getId());
            gdsInfo.setGdsName(gdsDetailInfo.getGdsName());
            gdsInfo.setImgPath(gdsDetailInfo.getMainPic().getURL());
            gdsInfo.setShopId(gdsDetailInfo.getShopId());

            if (gdsDetailInfo.getSkuInfo() != null) {
                gdsInfo.setSkuId(gdsDetailInfo.getSkuInfo().getId());
                gdsInfo.setBuyPrice(gdsDetailInfo.getSkuInfo().getDiscountPrice());
                gdsInfo.setBasePrice(gdsDetailInfo.getSkuInfo().getGuidePrice());
            }

        }
        return gdsInfo;
    }
}

