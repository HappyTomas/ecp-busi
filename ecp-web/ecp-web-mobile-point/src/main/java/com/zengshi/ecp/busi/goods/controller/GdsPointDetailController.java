package com.zengshi.ecp.busi.goods.controller;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.velocity.AiToolUtil;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.goods.vo.GdsDetailVO;
import com.zengshi.ecp.busi.goods.vo.GdsSkuMediaVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropRelationRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2MediaRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCollectRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.ScoreInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffUnionRSV;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 商品详情<br>
 * Date:2015年9月18日上午10:51:56  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@RequestMapping(value="/gdspointdetail")
@Controller
public class GdsPointDetailController extends EcpBaseController{
    private static String URL = "/goods/gdspointdetail";
    private static String MODULE = GdsPointDetailController.class.getName();
    @Resource
    private IGdsInfoQueryRSV iGdsInfoQueryRSV;
    @Resource
    private IShopInfoRSV iShopInfoRSV;
    @Resource
    private IPromRSV iPromRSV;
    @Resource
    private ICustInfoRSV iCustInfoRSV;
    @Resource
    private IShopManageRSV iShopManageRSV;
    @Resource
    private IGdsEvalRSV iGdsEvalRSV;
    @Resource
    private IGdsCategoryRSV igdsCategoryRSV;
    @Resource
    private IGdsSkuInfoQueryRSV iGdsSkuInfoQueryRSV;
    @Resource
    private IGdsCollectRSV iGdsCollectRSV;
    @Resource
    private IStaffUnionRSV iStaffUnionRSV;
    
    /**
     * 
     * init:(初始化进来的接口). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * @author gxq 
     * @param gdsId
     * @param skuId
     * @param model
     * @param gdsDetailVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping("/{gdsId}-{skuId}")
    public String init(@PathVariable("gdsId") String gdsId,@PathVariable("skuId") String skuId,
            Model model,GdsDetailVO gdsDetailVO){
        GdsInfoReqDTO dto = new GdsInfoReqDTO();
        if(StringUtil.isNotEmpty(gdsDetailVO.getGdsId())){
            dto.setId(gdsDetailVO.getGdsId());
        }
        if(StringUtil.isNotEmpty(gdsDetailVO.getSkuId())){
            dto.setSkuId(gdsDetailVO.getSkuId());
        }
        GdsQueryOption[] gdsQueryOptions = new GdsQueryOption[] {
                GdsQueryOption.BASIC, GdsQueryOption.PROP,GdsQueryOption.PRICE,
                GdsQueryOption.SCORE};
        SkuQueryOption[] skuQueryOptions = new SkuQueryOption[] {
                SkuQueryOption.BASIC, SkuQueryOption.PROP,SkuQueryOption.SHOWSTOCK,SkuQueryOption.PRICE};
        dto.setGdsQueryOptions(gdsQueryOptions);
        dto.setSkuQuerys(skuQueryOptions);
        GdsInfoDetailRespDTO resultDto = null;
        try {
            resultDto = iGdsInfoQueryRSV.queryGdsInfoDetail(dto);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "无法获取商品详情信息！", e);
        }
        String shopName = "";
        String stockStatus = "";
        
        try{
            BaseSysCfgRespDTO unitCfg = SysCfgUtil.fetchSysCfg(GdsConstants.SysCfgConstants.MALL_GDS_DETAIL_UNIT);
            // 设置商品信息单位.
            model.addAttribute("gdsInfoUnit",unitCfg.getParaValue());
        }catch(Exception e){
            LogUtil.error(MODULE, "获取键值为["+GdsConstants.SysCfgConstants.MALL_GDS_DETAIL_UNIT+"]的配置参数异常!");
        }
        
        
        
        if(StringUtil.isNotEmpty(resultDto)){
            if (GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES.equals(resultDto
                    .getSkuInfo().getGdsStatus())
                    || GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES
                            .equals(resultDto.getSkuInfo().getGdsStatus())) {
                //获取相关分类
                getGdsDownCommonCat(resultDto,model);
            }
            //获取库存状态
            if(resultDto.getSkuInfo() != null){
                stockStatus =  GdsUtils.checkScoreStcokStatus(resultDto.getSkuInfo().getRealAmount());
            }
            GdsCatg2PropReqDTO reqDto = new GdsCatg2PropReqDTO();
            reqDto.setCatgCode(resultDto.getMainCatgs());
            //只获取基本属性，其他不要
            reqDto.setIfBasic(GdsConstants.GdsCatg2Prop.IS_BASIC_1);
            reqDto.setIfContainTopProp(true);
            GdsCatg2PropRelationRespDTO rspDto = igdsCategoryRSV.queryCategoryProps(reqDto);
            List<GdsPropRespDTO> basics = rspDto.getBasics();
            if(basics!=null && basics.size() >0){
                model.addAttribute("gdsBasicParam", basics);
            }
            resultDto.setGdsDesc(getHtmlUrl(resultDto.getGdsDesc()));
            resultDto.setGdsPartlist(getHtmlUrl(resultDto.getGdsPartlist()));
            ShopInfoResDTO shopInfo = iShopInfoRSV.findShopInfoByShopID(resultDto.getShopId());
            if(StringUtil.isNotEmpty(shopInfo)){
                shopName = shopInfo.getShopName();
            }else{
                throw new BusinessException("web.gds.2000012");
            }
        }else{
            resultDto = new GdsInfoDetailRespDTO();
            GdsSkuInfoRespDTO gdsSkuInfoRespDTO = new GdsSkuInfoRespDTO();
            gdsSkuInfoRespDTO
                    .setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_DELETE);
            resultDto.setSkuInfo(gdsSkuInfoRespDTO);
            model.addAttribute("gdsDetailInfo", resultDto);
            return URL + "/gdspointdetail";
        }
        model.addAttribute("STOCK_SCORE_LACK_THRESHOLD", Integer.parseInt(SysCfgUtil.fetchSysCfg(GdsConstants.GdsStock.STOCK_SCORE_LACK_THRESHOLD).getParaValue()));
        model.addAttribute("shopName", shopName);
        model.addAttribute("gdsDetailInfo", resultDto);
        model.addAttribute("stockStatus", stockStatus);
        model.addAttribute("scoreBalance", getScoreBalance());
        
        return URL + "/gdspointdetail";
    } 
    //获取当前登入用户的剩余积分
    private String getScoreBalance(){
    	String scoreBalance = "No";
    	GdsInfoReqDTO dto = new GdsInfoReqDTO();
    	Long staffId = dto.getStaff().getId();
    	if(staffId != 0){
    		ScoreInfoResDTO  siDto = iStaffUnionRSV.findScoreInfoByStaffId(staffId);
    		if(StringUtil.isNotEmpty(siDto) && siDto.getScoreBalance() != null){
    			scoreBalance = siDto.getScoreBalance().toString();
    		}
    	}
    	return scoreBalance;
    }
    private String getHtmlUrl(String vfsId){
        if(StringUtil.isBlank(vfsId)){
            return "";
        }
        return ImageUtil.getStaticDocUrl(vfsId, "html");
    }
    
    /**
     * 
     * queryRecentlyProduct:(获取兑换排行榜). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * @author gxq 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/queryrecentlyproduct")
    @ResponseBody
    public Map<String,Object> queryRecentlyProduct(@RequestParam("gdsSize")String gdsSize){
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "获取积分商品兑换排行榜报错！", e);
        }
        map.put("list", new Array[Integer.parseInt(gdsSize)]);
        return map;
    }
    
    /**
     * 
     * queryRecentlyProduct:(获取积分商城商品详情上的图片展示). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * @author gxq 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/querygdspictrues")
    public String queryGdsPictrues(Model model,
            @RequestParam("gdsId")String gdsId,
            @RequestParam("skuId")String skuId){
        List<GdsSkuMediaVO> resultList = new ArrayList<GdsSkuMediaVO>();
        GdsSkuInfoReqDTO dto = new GdsSkuInfoReqDTO();
        if (StringUtil.isNotBlank(skuId)) {
            dto.setId(Long.parseLong(skuId));
        }
        if (StringUtil.isNotBlank(gdsId)){
            dto.setGdsId(Long.parseLong(gdsId));
        }
        SkuQueryOption[] skuQueryOptions = new SkuQueryOption[] { SkuQueryOption.BASIC,SkuQueryOption.MEDIA };
        dto.setSkuQuery(skuQueryOptions);
        dto.setStatus("1");
        GdsSkuInfoRespDTO resultDto = null;
        try {
            resultDto = iGdsSkuInfoQueryRSV.querySkuInfoByOptions(dto);
            if (resultDto!=null) {
                GdsSkuMediaVO gdsSkuMediaVO = null;
                List<GdsSku2MediaRespDTO> skuMediaList = resultDto.getSku2MediaRespDTOs();
                if (resultDto!=null && CollectionUtils.isNotEmpty(skuMediaList)) {
                    // 取单品的图片
                    for (GdsSku2MediaRespDTO gdsSku2MediaRespDTO : skuMediaList) {
                        gdsSkuMediaVO = new GdsSkuMediaVO();
                        gdsSkuMediaVO.setUrl(new AiToolUtil().genImageUrl(gdsSku2MediaRespDTO.getMediaUuid(),
                                "414x191!"));
                        resultList.add(gdsSkuMediaVO);
                    }
                }else{
                    gdsSkuMediaVO = new GdsSkuMediaVO();
                    gdsSkuMediaVO.setUrl(new AiToolUtil().genImageUrl("","414x191!"));
                    resultList.add(gdsSkuMediaVO);
                }
            }else{
                GdsSkuMediaVO gdsSkuMediaVO = new GdsSkuMediaVO();
                gdsSkuMediaVO.setUrl(new AiToolUtil().genImageUrl("","414x191!"));
                resultList.add(gdsSkuMediaVO);
            }
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "获取商品详情图片错误！", e);
        }
        model.addAttribute("result", resultList);
        return URL +"/list/gdspoint-pictrues-list";
    }
    /**
     * 
     * add:(添加收藏). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * 
     * @author gxq 
     * @param skuId
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public EcpBaseResponseVO add(@RequestParam("skuId")String skuId) {
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        try {
            GdsCollectReqDTO dto = new GdsCollectReqDTO();
            if(!StringUtil.isBlank(skuId)){
                dto.setSkuId(Long.parseLong(skuId));
            }
            iGdsCollectRSV.addGdsCollect(dto);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "无法添加收藏！", e);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            vo.setResultMsg(e.getErrorMessage());
        }
        return vo;
    }
    
    /**
     * 
     * getGdsDownCommonCat:(未上架的商品获取相关分类). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author gxq  
     * @since JDK 1.6
     */
    public void getGdsDownCommonCat(GdsInfoDetailRespDTO resultDto,Model model){
        GdsInfoReqDTO reqDTO = new GdsInfoReqDTO();
        List<GdsInfoDetailRespDTO> gdsInfoDetailRespDTO = null;
        reqDTO.setMainCatgs(resultDto.getMainCatgs());
        reqDTO.setIfScoreGds(GdsConstants.Commons.STATUS_VALID);
        reqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
        reqDTO.setPageNo(1);
        reqDTO.setPageSize(4);
        try {
            gdsInfoDetailRespDTO = iGdsInfoQueryRSV
                    .queryGdsSkuInfoListByCatgRela(reqDTO);
            if (gdsInfoDetailRespDTO != null) {
                model.addAttribute("commondCatGds",
                        gdsInfoDetailRespDTO);
            } else {
                model.addAttribute("commondCatGds",
                        new ArrayList<GdsSkuInfoRespDTO>());
            }
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "获取相关分类推荐失败！", e);
            model.addAttribute("commondCatGds",
                    new ArrayList<GdsSkuInfoRespDTO>());
        }
    }
    
    
    @RequestMapping("/html/{fileId}")
    public String detail(@PathVariable("fileId") String fileId, HttpServletRequest req) {
        String content=FileUtil.readFile2Text(fileId, null);
        req.setAttribute("content", content);
        return URL + "/list/detail";
    }
}

