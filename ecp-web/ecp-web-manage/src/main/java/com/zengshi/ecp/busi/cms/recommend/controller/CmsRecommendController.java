package com.zengshi.ecp.busi.cms.recommend.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.cms.recommend.vo.CmsRecommendVO;
import com.zengshi.ecp.busi.goods.vo.GdsManageVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsRecommendReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsRecommendRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsRecommendRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.system.util.ConstantTool;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015.09.28 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxm9
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "/recommend")
public class CmsRecommendController extends EcpBaseController {

    private static String MODULE = CmsRecommendController.class.getName();

    private String URL = "/cms/recommend/recommend";// 返回页面的基本路径

    private String URL_OPEN = "/cms/recommend/open/recommend";// 返回页面的弹出窗路径

    @Resource(name = "cmsRecommendRSV")
    private ICmsRecommendRSV cmsRecommendRSV;
    @Resource(name = "cmsInfoRSV")
    private ICmsInfoRSV cmsInfoRSV;
    @Resource(name = "gdsInfoQueryRSV")
    private IGdsInfoQueryRSV gdsInfoQueryRSV;
    @Resource(name = "shopInfoRSV")
    private IShopInfoRSV shopInfoRSV;

    /**
     * init:页面初始化，requestMapping如果不写的话，访问地址同：Controller类的 requestmapping的URL TODO(这里描述这个方法适用条件 –
     * 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author huangxm9
     * @return
     * @since JDK 1.6
     */
    @RequestMapping()
    public String init() {
        return URL + "-init";
    }

    /**
     * grid:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author huangxm9
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping(value = "/grid")
    public String grid(Model model,
            @ModelAttribute("searchParams") String searchParams) throws Exception {
        return URL + "-grid";
    }

    /**
     * edit:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author huangxm9
     * @param model
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = "/add")
    public String add(Model model,
            @ModelAttribute("searchParams") String searchParams) throws Exception {
        return URL + "-edit";
    }

    /**
     * edit:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author huangxm9
     * @param model
     * @param id
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping(value = "/edit")
    public String edit(Model model, 
            @RequestParam("id") String id,
            @ModelAttribute("searchParams") String searchParams) throws Exception {
        // 1 验证入参
        if (StringUtils.isBlank(id)) {
            String[] keyInfos = new String[1];
            keyInfos[0] = "id";
            throw new BusinessException("cms.common.param.null.error", keyInfos);
        }
        // 2. 组件入参
        CmsRecommendReqDTO reqDTO = new CmsRecommendReqDTO();
        reqDTO.setId(Long.valueOf(id));
        try {
            // 2.1调用后场服务
            CmsRecommendRespDTO respDTO = cmsRecommendRSV.queryCmsRecommend(reqDTO);
                
            // 2.2根据作者图片ID获取图片URL
            String imageUrl = ConstantTool.getImageUrl(respDTO.getAuthorImage(), "290x220!");
            respDTO.setAuthorImageUrl(imageUrl);
            // 2.3根据商品id查询对应的推荐商品
            if (respDTO.getRecommendGdsId() != null) {
                GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
                gdsInfoReqDTO.setId(respDTO.getRecommendGdsId());
                GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQueryRSV.queryGdsInfo(gdsInfoReqDTO);
                if (gdsInfoRespDTO != null) {
                    respDTO.setRecommendGdsName(gdsInfoRespDTO.getGdsName());
                }
            }
            // 2.4根据商品id查询对应的该作者其他的作品
            if (StringUtils.isNotBlank(respDTO.getOtherProduction())) {
                String[] otherProductionArray = respDTO.getOtherProduction().split("、");
                String otherProductionName = "";
                for(int i=0;i<otherProductionArray.length;i++){
                    if(StringUtils.isNotBlank(otherProductionArray[i])){
                        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
                        gdsInfoReqDTO.setId(Long.valueOf(otherProductionArray[i]));
                        GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQueryRSV.queryGdsInfo(gdsInfoReqDTO);
                        if (gdsInfoRespDTO != null) {
                            otherProductionName += gdsInfoRespDTO.getGdsName()+'、';
                        }  
                    }
                }
                respDTO.setOtherProductionName(otherProductionName);
            }
            // 2.5根据商品id查询对应的该作者推荐的作品
            if (StringUtils.isNotBlank(respDTO.getRecommendProduction())) {
                String[] recommendProductionArray = respDTO.getRecommendProduction().split("、");
                String recommendProductionName = "";
                for(int i=0;i<recommendProductionArray.length;i++){
                    if(StringUtils.isNotBlank(recommendProductionArray[i])){
                        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
                        gdsInfoReqDTO.setId(Long.valueOf(recommendProductionArray[i]));
                        GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQueryRSV.queryGdsInfo(gdsInfoReqDTO);
                        if (gdsInfoRespDTO != null) {
                            recommendProductionName += gdsInfoRespDTO.getGdsName()+'、';
                        }  
                    }
                }
                respDTO.setRecommendProductionName(recommendProductionName);
            }
            // 2.5根据商品id查询对应的喜欢该作者还喜欢
            if (StringUtils.isNotBlank(respDTO.getOtherLike())) {
                String[] otherLikeArray = respDTO.getOtherLike().split("、");
                String otherLikeName = "";
                for(int i=0;i<otherLikeArray.length;i++){
                    if(StringUtils.isNotBlank(otherLikeArray[i])){
                        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
                        gdsInfoReqDTO.setId(Long.valueOf(otherLikeArray[i]));
                        GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQueryRSV.queryGdsInfo(gdsInfoReqDTO);
                        if (gdsInfoRespDTO != null) {
                            otherLikeName += gdsInfoRespDTO.getGdsName()+'、';
                        }  
                    }
                }
                respDTO.setOtherLikeName(otherLikeName);
            }
            model.addAttribute("respVO", respDTO);
        } catch (BusinessException e) {
            // TODO: handle exception
            throw new BusinessException(e.getErrorCode());
        }

        return URL + "-edit";
    }

    /**
     * changestatus:(生效、失效). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author huangxm9
     * @param model
     * @param ids
     * @param status
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping(value = "/changestatus")
    @ResponseBody
    public EcpBaseResponseVO changestatus(Model model, @RequestParam("ids") String ids,
            @RequestParam("status") String status) throws Exception {
        LogUtil.info(MODULE, "==========ids:" + ids + ";");
        if (StringUtils.isBlank(ids)) {
            String[] keyInfos = new String[1];
            keyInfos[0] = "ids";
            throw new BusinessException("cms.common.param.null.error", keyInfos);
        }
        if (StringUtils.isBlank(status)) {
            String[] keyInfos = new String[1];
            keyInfos[0] = "status";
            throw new BusinessException("cms.common.param.null.error", keyInfos);
        }
        String[] idsArray = ids.split(",");
        List<String> list = Arrays.asList(idsArray);
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException("入参ids为空！");
        }
        cmsRecommendRSV.changeStatusCmsRecommendBatch(list, status);
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return vo;
    }

    /**
     * delete:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author huangxm9
     * @param ids
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public EcpBaseResponseVO delete(@RequestParam("ids") String ids) throws Exception {

        LogUtil.info(MODULE, "==========ids:" + ids + ";");
        if (StringUtils.isBlank(ids)) {
            String[] keyInfos = new String[1];
            keyInfos[0] = "ids";
            throw new BusinessException("cms.common.param.null.error", keyInfos);
        }
        String[] idsArray = ids.split(",");
        List<String> list = Arrays.asList(idsArray);
        if (CollectionUtils.isEmpty(list)) {
            String[] keyInfos = new String[1];
            keyInfos[0] = "ids";
            throw new BusinessException("cms.common.param.null.error", keyInfos);
        }
        cmsRecommendRSV.deleteCmsRecommendBatch(list);
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return respVO;
    }

    /** 
     * save:(新增/编辑 发布保存). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param cmsRecommendVO
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/pubsave")
    @ResponseBody
    public EcpBaseResponseVO pubsave(@Valid CmsRecommendVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsRecommendReqDTO reqDTO = new CmsRecommendReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);//发布
        //如果排序为空，则默认赋值为1.
        if(reqDTO.getSortNo() == null){
            reqDTO.setSortNo(1);
        }
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        if(reqDTO.getId() != null){
            cmsRecommendRSV.updateCmsRecommend(reqDTO);
        }else{
            cmsRecommendRSV.addCmsRecommend(reqDTO);
        }
        respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return respVO;
    }
    /**
     * save:(新增/编辑 保存). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author huangxm9
     * @param cmsExpertRecommendVO
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public EcpBaseResponseVO save(@Valid CmsRecommendVO VO) throws Exception {
        LogUtil.info(MODULE, "==========" + JSONObject.toJSONString(VO));
        CmsRecommendReqDTO reqDTO = new CmsRecommendReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);// 未发布

        //如果排序为空，则默认赋值为1.
        if(reqDTO.getSortNo() == null){
            reqDTO.setSortNo(1);
        }
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        if (VO.getId() != null) {
            cmsRecommendRSV.updateCmsRecommend(reqDTO);
        } else {
            cmsRecommendRSV.addCmsRecommend(reqDTO);
        }
        respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return respVO;
    }

    /**
     * gridList:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author huangxm9
     * @param model
     * @param cmsExpertRecommendSearchVO
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping("/gridlist")
    @ResponseBody
    public Model gridList(Model model, @ModelAttribute("searchVO") CmsRecommendVO searchVO)
            throws Exception {
        LogUtil.info(MODULE, "==========" + JSONObject.toJSONString(searchVO));
        // 1. 调用后场服务所需要的DTO；
        CmsRecommendReqDTO reqDTO = searchVO.toBaseInfo(CmsRecommendReqDTO.class);
        ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);

        // 2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        PageResponseDTO<CmsRecommendRespDTO> pageInfo = cmsRecommendRSV.queryCmsRecommendPage(reqDTO);

        // 3. 调文件服务器，返回图片，调用店铺，返回店铺信息
        List<CmsRecommendRespDTO> respList = pageInfo.getResult();
        if (CollectionUtils.isNotEmpty(respList)) {
            for (CmsRecommendRespDTO dto : respList) {
                // 3.1调文件服务器，返回图片
                if (StringUtils.isNotBlank(dto.getAuthorImage())) {
                    dto.setAuthorImageUrl(ImageUtil.getImageUrl(dto.getAuthorImage() + "_" + "120x50!"));
                }
                // 3.2根据商品id返回商品名称
                if (dto.getRecommendGdsId() != null) {
                    GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
                    gdsInfoReqDTO.setId(dto.getRecommendGdsId());
                    GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQueryRSV.queryGdsInfo(gdsInfoReqDTO);
                    if (gdsInfoRespDTO != null) {
                        dto.setRecommendGdsName(gdsInfoRespDTO.getGdsName());
                    }
                }
            }
        }

        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        return super.addPageToModel(model, respVO);
    }

    /** 
     * showdetail:(查看详情). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh
     * 2015.10.16 
     * @param cmsHotSearchVO
     * @return 
     * @since JDK 1.7
     */ 
    @RequestMapping(value="/view")
    public String view(Model model,
            @RequestParam("id")String id,
            @ModelAttribute("searchParams") String searchParams) throws Exception{
    	 // 1 验证入参
        if (StringUtils.isBlank(id)) {
            String[] keyInfos = new String[1];
            keyInfos[0] = "id";
            throw new BusinessException("cms.common.param.null.error", keyInfos);
        }
        // 2. 组件入参
        CmsRecommendReqDTO reqDTO = new CmsRecommendReqDTO();
        reqDTO.setId(Long.valueOf(id));
        try {
            // 2.1调用后场服务
            CmsRecommendRespDTO respDTO = cmsRecommendRSV.queryCmsRecommend(reqDTO);
                
            // 2.2根据作者图片ID获取图片URL
            String imageUrl = ConstantTool.getImageUrl(respDTO.getAuthorImage(), "290x220!");
            respDTO.setAuthorImageUrl(imageUrl);
            // 2.3根据商品id查询对应的推荐商品
            if (respDTO.getRecommendGdsId() != null) {
                GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
                gdsInfoReqDTO.setId(respDTO.getRecommendGdsId());
                GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQueryRSV.queryGdsInfo(gdsInfoReqDTO);
                if (gdsInfoRespDTO != null) {
                    respDTO.setRecommendGdsName(gdsInfoRespDTO.getGdsName());
                }
            }
            // 2.4根据商品id查询对应的该作者其他的作品
            if (StringUtils.isNotBlank(respDTO.getOtherProduction())) {
                String[] otherProductionArray = respDTO.getOtherProduction().split("、");
                String otherProductionName = "";
                for(int i=0;i<otherProductionArray.length;i++){
                    if(StringUtils.isNotBlank(otherProductionArray[i])){
                        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
                        gdsInfoReqDTO.setId(Long.valueOf(otherProductionArray[i]));
                        GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQueryRSV.queryGdsInfo(gdsInfoReqDTO);
                        if (gdsInfoRespDTO != null) {
                            otherProductionName += gdsInfoRespDTO.getGdsName()+'、';
                        }  
                    }
                }
                respDTO.setOtherProductionName(otherProductionName);
            }
            // 2.5根据商品id查询对应的该作者推荐的作品
            if (StringUtils.isNotBlank(respDTO.getRecommendProduction())) {
                String[] recommendProductionArray = respDTO.getRecommendProduction().split("、");
                String recommendProductionName = "";
                for(int i=0;i<recommendProductionArray.length;i++){
                    if(StringUtils.isNotBlank(recommendProductionArray[i])){
                        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
                        gdsInfoReqDTO.setId(Long.valueOf(recommendProductionArray[i]));
                        GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQueryRSV.queryGdsInfo(gdsInfoReqDTO);
                        if (gdsInfoRespDTO != null) {
                            recommendProductionName += gdsInfoRespDTO.getGdsName()+'、';
                        }  
                    }
                }
                respDTO.setRecommendProductionName(recommendProductionName);
            }
            // 2.5根据商品id查询对应的喜欢该作者还喜欢
            if (StringUtils.isNotBlank(respDTO.getOtherLike())) {
                String[] otherLikeArray = respDTO.getOtherLike().split("、");
                String otherLikeName = "";
                for(int i=0;i<otherLikeArray.length;i++){
                    if(StringUtils.isNotBlank(otherLikeArray[i])){
                        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
                        gdsInfoReqDTO.setId(Long.valueOf(otherLikeArray[i]));
                        GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQueryRSV.queryGdsInfo(gdsInfoReqDTO);
                        if (gdsInfoRespDTO != null) {
                            otherLikeName += gdsInfoRespDTO.getGdsName()+'、';
                        }  
                    }
                }
                respDTO.setOtherLikeName(otherLikeName);
            }
            model.addAttribute("respVO", respDTO);
        } catch (BusinessException e) {
            // TODO: handle exception
            throw new BusinessException(e.getErrorCode());
        }

        return URL+"-view";
    }
    
    /**
     * 选择商品内容弹出框 opengds:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author huangxm9
     * @return
     * @since JDK 1.6
     */
    @RequestMapping("/opengds")
    public String opengds(Model model,@RequestParam("type") String type) {
        model.addAttribute("type", type);
        return URL_OPEN + "-gds";
    }

    /**
     * querygds:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author huangxm9
     * @param model
     * @param ids
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping("/querygds")
    public Model querygds(Model model, @ModelAttribute("searchVO") GdsManageVO searchVO)
            throws Exception {
        LogUtil.info(MODULE, "==========" + JSONObject.toJSONString(searchVO));
        // 1. 调用后场服务所需要的DTO；
        GdsInfoReqDTO reqDTO = searchVO.toBaseInfo(GdsInfoReqDTO.class);
        ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);
        reqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);// 已上架

        PageResponseDTO<GdsInfoRespDTO> pageInfo = gdsInfoQueryRSV.queryGdsInfoListPage(reqDTO);
        List<GdsInfoRespDTO> respList = null;
        if(pageInfo != null){
            respList = pageInfo.getResult();
        } 
        if(!CollectionUtils.isEmpty(respList)){
            for(GdsInfoRespDTO gdsInfoRespDTO:respList){
                if(gdsInfoRespDTO != null && StringUtil.isNotEmpty(gdsInfoRespDTO.getShopId())){
                    //3.2调用店铺，返回店铺信息
                    ShopInfoResDTO shopInfoRespDTO = shopInfoRSV.findShopInfoByShopID(gdsInfoRespDTO.getShopId());
                    if(shopInfoRespDTO != null){
                        gdsInfoRespDTO.setGdsSubHead(shopInfoRespDTO.getShopName());//将商品副标题，当成店铺名称用。
                    } 
                }
            }
        }
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        // 2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        return super.addPageToModel(model, respVO);
    }

}
