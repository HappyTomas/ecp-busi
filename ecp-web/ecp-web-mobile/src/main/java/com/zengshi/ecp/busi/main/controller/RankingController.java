package com.zengshi.ecp.busi.main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.main.vo.CmsFloorReqVO;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.utils.CmsAnalUtil;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.utils.CmsGoodsDetailUtil;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorGdsRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTabRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**微信商城-排行榜
 * Title: ECP <br>
 * Project Name:ecp-web-mobile <br>
 * Description: <br>
 * Date:2016年8月16日下午5:31:28  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */  
@Controller
@RequestMapping(value = "/ranking")
public class RankingController extends EcpBaseController {

    private static String MODULE = RankingController.class.getName();
    
    private static String BASEURL = "/main/ranking";
    
    private final Integer TABSIZE = 1000;//返回tab数
    
    private final static Integer PAGENO= 1;
    
    private final static Integer PAGESIZE= 10;
    
    @Resource
    private ICmsFloorRSV cmsFloorRSV;
    @Resource
    private ICmsFloorGdsRSV cmsFloorGdsRSV;
    @Resource
    private ICmsFloorTabRSV cmsFloorTabRSV;

    /**
     * init:页面初始化，requestMapping如果不写的话，访问地址同：Controller类的 requestmapping的URL TODO(这里描述这个方法适用条件 –
     * 可选).<br/>
     * 
     * @author jiangzh
     * @return
     * @since JDK 1.6
     */
    @RequestMapping()
    public String init(Model model,HttpSession session) {

        String url = BASEURL + "/ranking";// 返回页面

        return url;
    }
    
    /**
     * qryFloorVM:(根据内容位置获取楼层信息). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param reqVO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/getRankVM")
    public String qryFloorVM(Model model,CmsFloorReqVO reqVO) throws Exception {
        LogUtil.info(MODULE, "==========reqVO:" + reqVO.toString() + ";");
        String url = BASEURL + "/list/ranking-list";// 返回页面
        String errMsg = "";
        CmsFloorRespDTO floorRespDTO = null;
        List<CmsFloorTabRespDTO> floorTabList = null;
        if(StringUtil.isEmpty(reqVO.getPlaceId())){//内容位置
            LogUtil.error(MODULE, "入参placeId为空！");
            errMsg = "入参内容位置为空";
        }else{
            try{
                floorRespDTO = this.qryFloorByPlaceId(reqVO);
            }catch(Exception err){
                LogUtil.error(MODULE, "查询内容位置关联楼层出现异常:",err);
                errMsg = "获取楼层异常";
            }
        }
        
        //获取页签
        if(null != floorRespDTO && StringUtil.isNotEmpty(floorRespDTO.getId())){//具有有效楼层
            //1.设置查询参数
            CmsFloorTabReqDTO tabReqDto = new CmsFloorTabReqDTO();
            tabReqDto.setFloorId(floorRespDTO.getId());
            tabReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            tabReqDto.setPageNo(1);
            //设置查询tab页数量
            Integer tabSize = TABSIZE;
            if(StringUtil.isNotEmpty(reqVO.getTabSize())){
                tabSize = reqVO.getTabSize();
            }
            tabReqDto.setPageSize(tabSize);
            PageResponseDTO<CmsFloorTabRespDTO> floorTabPageInfo = null;
            //2.调用服务
            try {
                floorTabPageInfo = cmsFloorTabRSV.queryCmsFloorTabPage(tabReqDto);
            } catch (Exception e) {
                LogUtil.error(MODULE, "查询楼层页签异常",e); 
                errMsg = "获取楼层页签异常";
            }
                    
            if(null != floorTabPageInfo){
                floorTabList =  floorTabPageInfo.getResult();
            }
        }
        
        model.addAttribute("floorRespDto", floorRespDTO);
        model.addAttribute("floorTabList", floorTabList);
        model.addAttribute("errMsg", errMsg);
        
        return url;
    }
    /**
     * 
     * getFloorGdsVM:(根据页签ID获取页签下的商品). <br/> 
     * 
     * @author zhanbh 
     * @param model
     * @param reqVO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/getRankGdsVM")
    public String getRankGdsVM(Model model,CmsFloorReqVO reqVO) throws Exception {
        LogUtil.info(MODULE, "==========reqVO:" + reqVO.toString() + ";");
        String url = BASEURL + "/list/ranking-list-floorinfo";// 返回页面
        String errMsg = "";
        // 图片规格
        String standard = "";
        if (StringUtil.isNotEmpty(reqVO.getPlaceWidth())&& StringUtil.isNotEmpty(reqVO.getPlaceHeight())) {
            standard = reqVO.getPlaceWidth() + "x" + reqVO.getPlaceHeight() + "!";
        } 
        List<GdsInfoDetailRespDTO> gdsDetailInfoList = null;
        if(StringUtil.isEmpty(reqVO.getTabId()) && StringUtil.isEmpty(reqVO.getFloorId())){
            gdsDetailInfoList = new ArrayList<GdsInfoDetailRespDTO>();
        }else if(CmsConstants.DataSource.CMS_DATA_SOURCE_02.equalsIgnoreCase(reqVO.getDataSource())){
            try {
                gdsDetailInfoList = this.getAnalRankGds(reqVO,standard);//行为分析
            } catch (Exception e) {
                LogUtil.error(MODULE, "远程服务异常", e);
                errMsg = "远程服务异常";
            }
        }else{
            try {
                gdsDetailInfoList = this.getTabGdsData(reqVO,standard);//配置
            } catch (Exception e) {
                LogUtil.error(MODULE, "查询配置商品异常", e);
                errMsg = "获取配置商品异常";
            }
        }
        
        model.addAttribute("tabId", reqVO.getTabId());
        model.addAttribute("gdsList", gdsDetailInfoList);
        model.addAttribute("errMsg", errMsg);
        return url;
    }
    /**
     * 
     * getTabGdsData:(获取排行榜配置数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param reqVO
     * @return 
     * @throws Exception 
     * @since JDK 1.6
     */
    private List<GdsInfoDetailRespDTO> getTabGdsData(CmsFloorReqVO reqVO,String standard) throws Exception {
        LogUtil.info(MODULE, "调用排行榜配置页签商品开始：");
        List<GdsInfoDetailRespDTO> gdsDetailInfoList = new ArrayList<GdsInfoDetailRespDTO>();
        // 1. 设置入参
        CmsFloorGdsReqDTO reqDto = new CmsFloorGdsReqDTO();
        if(StringUtil.isNotEmpty(reqVO.getTabId())){
            reqDto.setTabId(reqVO.getTabId());
        }
        if(StringUtil.isNotEmpty(reqVO.getFloorId())){
            reqDto.setFloorId(reqVO.getFloorId());
        }
        //设置状态  默认有效
        reqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        
        //设置查询页数
        Integer pageNo = PAGENO;
        Integer pageSize = PAGESIZE;
        if(StringUtil.isNotEmpty(reqVO.getPageNumber())){
            pageNo = reqVO.getPageNumber();
        }
        if(StringUtil.isNotEmpty(reqVO.getPageSize())){
            pageSize = reqVO.getPageSize();
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
                LogUtil.error(MODULE, "获取排行榜配置第"+pageNo+"页商品服务发生异常",e); 
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
                gdsDetailInfo = this.getGdsInfoForTab(respDto, standard);
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
     * getAnalRankGds:(获取行为分析排行榜数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param reqVO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    private List<GdsInfoDetailRespDTO> getAnalRankGds(CmsFloorReqVO reqVO,String standard) throws Exception {
        EcpBasePageRespVO<GdsInfoDetailRespDTO> gdsDetailInfoPage = null;
        List<GdsInfoDetailRespDTO> gdsDetailInfoList = null;
        
        Integer pageNo = PAGENO;
        Integer pageSize = PAGESIZE;
        String countType = null;
        String catgCode = null;
        Long shopId = null;
        
        if(StringUtil.isNotEmpty(reqVO.getPageNumber())){
            pageNo = reqVO.getPageNumber();
        }
        if(StringUtil.isNotEmpty(reqVO.getPageSize())){
            pageSize = reqVO.getPageSize();
        }
        if(StringUtil.isNotBlank(reqVO.getCountType())){
            countType = reqVO.getCountType();
        }
        if(StringUtil.isNotBlank(reqVO.getCatgCode())){
            catgCode = reqVO.getCatgCode();
        }
        List<Long> propIds = new ArrayList<Long>();
        propIds.add(1001l);//作者
        //propIds.add(1005l);//出版日期
        //propIds.add(1006l);//出版社
        //propIds.add(1020l);//内容简介 
        gdsDetailInfoPage = CmsAnalUtil.getAnalRankDataPage(pageNo, pageSize, countType, catgCode, shopId, propIds);
        if(null != gdsDetailInfoPage){
            gdsDetailInfoList = gdsDetailInfoPage.getList();
        }
        //扩展信息
        if(CollectionUtils.isNotEmpty(gdsDetailInfoList)){
            for(GdsInfoDetailRespDTO gdsInfo : gdsDetailInfoList ){
                CmsGoodsDetailUtil.extendGdsInfo(gdsInfo, propIds, false, true, standard);
                CmsGoodsDetailUtil.getGdsPromPrice(gdsInfo, CmsConstants.PlatformType.CMS_PLATFORMTYPE_03, null);
            }
        }
        return gdsDetailInfoList;
    }

    /**
     * 
     * qryFloorByPlaceId:(根据内容位置获取楼层id). <br/> 
     * 
     * @author gxq 
     * @param reqVO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    private CmsFloorRespDTO qryFloorByPlaceId(CmsFloorReqVO reqVO) throws Exception{
        CmsFloorReqDTO cmsFloorReqDTO = new CmsFloorReqDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(reqVO.getPlaceId())){//内容位置
            LogUtil.error(MODULE, "入参placeId为空！");
            return null;
        }
        cmsFloorReqDTO.setPlaceId(reqVO.getPlaceId());
        if (StringUtil.isNotBlank(reqVO.getStatus())) {//状态
            cmsFloorReqDTO.setStatus(reqVO.getStatus());
        } else {//默认有效
            cmsFloorReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        }
        
        CmsFloorRespDTO cmsFloorRespDTO = null;
        List<CmsFloorRespDTO> floorRespList = cmsFloorRSV.queryCmsFloorList(cmsFloorReqDTO);
        if(!CollectionUtils.isEmpty(floorRespList)){
            cmsFloorRespDTO = floorRespList.get(0);
        }
        return cmsFloorRespDTO;
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
    private GdsInfoDetailRespDTO getGdsInfoForTab(CmsFloorGdsRespDTO respDto,String standard) {
        GdsInfoDetailRespDTO gdsInfoDetailRespDTO = null;
        
        if(respDto != null && StringUtil.isNotEmpty(respDto.getGdsId())){
            //查询商品信息
            GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
            GdsQueryOption[] gdsOptions = new GdsQueryOption[] { GdsQueryOption.BASIC,
                    GdsQueryOption.MAINPIC };
            SkuQueryOption[] skuOptions = new SkuQueryOption[] { SkuQueryOption.BASIC,
                    SkuQueryOption.CAlDISCOUNT };

            gdsInfoReqDTO.setGdsQueryOptions(gdsOptions);
            gdsInfoReqDTO.setSkuQuerys(skuOptions);
            gdsInfoReqDTO.setId(respDto.getGdsId());

            List<Long> propIds = new ArrayList<Long>();
            propIds.add(1001l);//作者
            gdsInfoReqDTO.setPropIds(propIds);
            try {
                gdsInfoDetailRespDTO = CmsGoodsDetailUtil.getGdsDetailByGdsId(gdsInfoReqDTO);
                CmsGoodsDetailUtil.extendGdsInfo(gdsInfoDetailRespDTO, propIds, false, true, standard);
                CmsGoodsDetailUtil.getGdsPromPrice(gdsInfoDetailRespDTO, CmsConstants.PlatformType.CMS_PLATFORMTYPE_03, null);
            } catch (Exception e) {
                LogUtil.error(MODULE, "getGdsInfoForTab==方法===查询商品详情异常");
            }
        }
        return gdsInfoDetailRespDTO;
    }
    
}
