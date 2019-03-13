package com.zengshi.ecp.busi.goods.discountBlacklist.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.goods.common.GdsBaseController;
import com.zengshi.ecp.busi.goods.discountBlacklist.vo.GdsCatgCustBlacklistVO;
import com.zengshi.ecp.busi.goods.discountBlacklist.vo.GdsQueryReqVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustBlacklistReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustBlacklistRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatgCustBlacklistRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 会员折扣价黑名单 <br>
 * Date:2017年7月10日上午10:45:00 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version
 * @since JDK 1.6
 */

@Controller
@RequestMapping(value = "/gdsdisctblacklist")
public class GdsDisctBlacklistController extends GdsBaseController {

	private String MODULE = GdsDisctBlacklistController.class.getName();
	private static String URL = "/goods/gdsDisctBlacklist";

	@Resource
	private IGdsCatgCustBlacklistRSV gdsCatgCustBlacklistRSV;
	
	@Resource
    private IGdsInfoQueryRSV gdsInfoQueryRSV;

	@Resource
    private IShopInfoRSV shopInfoRSV;
	
	@Resource
    private IGdsCategoryRSV gdsCategoryRSV;
	/**
	 * 
	 * init:(分类折扣黑名单列表页初始化). <br/> 
	 * 
	 * @author zhanbh 
	 * @return 
	 * @since JDK 1.6
	 */
	@RequestMapping()
	public String init() {
		return URL + "/blacklist-grid";
	}

	/**
	 * 
	 * gridList:(列表获取). <br/>
	 * 
	 * @author zhanbh 
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "gridlist")
	public Model gridList(Model model,  GdsCatgCustBlacklistVO vo) {
	    GdsCatgCustBlacklistReqDTO dto = vo.toBaseInfo(GdsCatgCustBlacklistReqDTO.class);
		ObjectCopyUtil.copyObjValue(vo, dto, null, false);
		dto.setStatus(GdsConstants.Commons.STATUS_VALID);
		EcpBasePageRespVO<Map> respVO = null;
		// 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
		PageResponseDTO<GdsCatgCustBlacklistRespDTO> pageInfo = gdsCatgCustBlacklistRSV.queryGdsCatgcustBlacklistPage(dto);
		if(null == pageInfo){
		    pageInfo = new PageResponseDTO<GdsCatgCustBlacklistRespDTO>();
		}
		if (null == pageInfo.getResult()) {
            pageInfo.setResult(new ArrayList<GdsCatgCustBlacklistRespDTO>());
        }
        for (GdsCatgCustBlacklistRespDTO respDTO : pageInfo.getResult()) {
            extendRespDTO(respDTO);
        }
        try {
            respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        } catch (Exception e) {
            LogUtil.error(MODULE, "分类折扣列表数据转化成VO失败！", e);
        }
		return super.addPageToModel(model, respVO);
	}

	/**
	 * 
	 * gotoAdd:(跳到添加页面). <br/>
	 * 
	 * @author zhanbh
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "gotoAdd")
	private String gotoAdd(Model model,  GdsCatgCustBlacklistVO vo) {
	    ShopInfoResDTO shopInfo =  qryShopInfoById(vo.getShopId());
	    model.addAttribute("shopId", shopInfo.getId());
	    model.addAttribute("shopName", shopInfo.getShopName());
		return URL + "/gdsselect/gds-select-grid";
	}
	/** 
     * add:(这里用一句话描述这个方法的作用). <br/> 
     * 
     * @author zhanbh 
     * @param ids
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/add")
    @ResponseBody
    public EcpBaseResponseVO add(Model model,  GdsCatgCustBlacklistVO vo) throws Exception{
        GdsCatgCustBlacklistReqDTO dto = new  GdsCatgCustBlacklistReqDTO();
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        dto.setGdsId(vo.getGdsId());
        dto.setGdsIdList(changeStrToList(vo.getGdsIdsStr()));
        dto.setShopId(vo.getShopId());
        try {
            gdsCatgCustBlacklistRSV.addGdsCatgCustBlacklist(dto);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (Exception e) {
            LogUtil.error(MODULE, "添加分类折扣异常！", e);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return respVO;
    }
	/** 
     * remove:(这里用一句话描述这个方法的作用). <br/> 
     * 
     * @author zhanbh 
     * @param ids
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/remove")
    @ResponseBody
    public EcpBaseResponseVO delete(Model model,  GdsCatgCustBlacklistVO vo) throws Exception{
        GdsCatgCustBlacklistReqDTO dto = new  GdsCatgCustBlacklistReqDTO();
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        dto.setId(vo.getId());
        dto.setIdList(changeStrToList(vo.getIdsStr()));
        try {
            gdsCatgCustBlacklistRSV.deleteGdsCatgCustBlacklistByPrimaryKey(dto);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (Exception e) {
            LogUtil.error(MODULE, "删除分类折扣异常！", e);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return respVO;
    }
    /**
     * 商品选择列表 
     * 
     * @param model
     * @param vo
     * @param queryDTO
     * @return
     * @throws Exception
     * @author zhanbh
     */
    @RequestMapping("/gdsgrid")
    @ResponseBody
    public Model gdsgrid(Model model, GdsQueryReqVO queryGdsReqVO) throws Exception {
        if(StringUtil.isBlank(queryGdsReqVO.getId())){
            queryGdsReqVO.setId(null);
        }
        GdsInfoReqDTO queryDTO=queryGdsReqVO.toBaseInfo(GdsInfoReqDTO.class);
        ObjectCopyUtil.copyObjValue(queryGdsReqVO, queryDTO, "", false);
        
        if(StringUtil.isBlank(queryGdsReqVO.getGdsStatus())){
            //默认全部为待上架 和 已上架
            List<String> gdsStList=new ArrayList<String>();
            gdsStList.add(GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES);
            gdsStList.add(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
            queryDTO.setGdsStatusArr(gdsStList);
        }
        
        if(queryGdsReqVO.getSiteId()!=null){
              List<Long> siteIds=new ArrayList<Long>();
              siteIds.add(queryGdsReqVO.getSiteId());
              queryDTO.setSiteIds(siteIds);
        }
        GdsQueryOption[] gdsQuery={GdsQueryOption.BASIC,GdsQueryOption.CATG,GdsQueryOption.PRICE};
        queryDTO.setGdsQueryOptions(gdsQuery);
        SkuQueryOption[] skuQuerys={SkuQueryOption.PRICE};
        queryDTO.setSkuQuerys(skuQuerys);
        PageResponseDTO<GdsInfoRespDTO>  page=gdsInfoQueryRSV.queryGdsInfoListPage(queryDTO);
        if(null == page){
            page = new PageResponseDTO<GdsInfoRespDTO>();
        }
        //转化分类名称
        if(CollectionUtils.isNotEmpty(page.getResult())){
            for(GdsInfoRespDTO dto:page.getResult()){
                //非空 不需要查询
                if(StringUtil.isBlank(dto.getMainCatgName())){ 
                    dto.setMainCatgName(qryGdsCategoryById(dto.getMainCatgs()).getCatgName());
                }
            }
        }
        
        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(page);

        return super.addPageToModel(model, respVO);
    }
    /**
     * 
     * changeStrToList:(这里用一句话描述这个方法的作用). <br/> 
     * 
     * @author zhanbh 
     * @param s  以,隔开
     * @since JDK 1.6
     */
    private List<Long> changeStrToList(String s){
        List<Long> result = new ArrayList<Long>();;
        if(StringUtil.isBlank(s)){
            return result;
        }
        String [] strList = s.split(",");
        for(String str : strList){
            if(isNumeric(str)){
                result.add(Long.parseLong(str));
            }
        }
        return result;
    }
    /**
     * 
     * isNumeric:(判断字符串是否为纯数字). <br/> 
     * 
     * @author zhanbh
     * @param str
     * @return 
     * @since JDK 1.6
     */
    private static boolean isNumeric(String str){
        if(StringUtil.isNotEmpty(str)){
            Pattern pattern = Pattern.compile("[0-9]*"); 
            Matcher isNum = pattern.matcher(str);
            if( isNum.matches() ){
                return true; 
            }
        }
        return false; 
    }
	/**
	 * 
	 * extendRespDTO:(扩展黑名单数据). <br/> 
	 * 
	 * @author zhanbh 
	 * @param respDTO 
	 * @since JDK 1.6
	 */
	private void extendRespDTO(GdsCatgCustBlacklistRespDTO respDTO){
	    respDTO.setGdsName(qryGdsInfoById(respDTO.getGdsId()).getGdsName());
	    respDTO.setShopName(qryShopInfoById(respDTO.getShopId()).getShopName());
	}
	/**
	 * 
	 * qryGdsCategoryById:(查询分类信息). <br/> 
	 * 
	 * @author zhanbh 
	 * @param catg
	 * @return 
	 * @since JDK 1.6
	 */
	private GdsCategoryRespDTO qryGdsCategoryById(String catg){
	    GdsCategoryRespDTO respDTO = null;
	    if(StringUtil.isBlank(catg)){
	        return new GdsCategoryRespDTO();
        }
	    GdsCategoryReqDTO reqDTO=new GdsCategoryReqDTO();
        reqDTO.setCatgCode(catg);
        //分类id 查询
        try {
            respDTO= gdsCategoryRSV.queryGdsCategoryByPK(reqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取商品分类信息异常", e);
        }
        if(null == respDTO){
            respDTO = new GdsCategoryRespDTO();
        }
        return respDTO;
	}
	/**
	 * 
	 * qryGdsInfoById:(获取商品信息). <br/> 
	 * 
	 * @author zhanbh 
	 * @param gdsId
	 * @return 
	 * @since JDK 1.6
	 */
	private GdsInfoRespDTO qryGdsInfoById(Long gdsId){
	    if(null == gdsId){
	        return new GdsInfoRespDTO();
	    }
	    GdsInfoReqDTO req = new GdsInfoReqDTO();
	    req.setId(gdsId);
	    GdsInfoRespDTO gdsInfo = null;
	    try {
	        gdsInfo = gdsInfoQueryRSV.queryGdsInfo(req);
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取商品信息异常", e);
        }
	    if(null == gdsInfo){
	        gdsInfo = new GdsInfoRespDTO();
	    }
	    return gdsInfo;
	}
	/**
	 * 
	 * qryShopInfoById:(获取店铺信息). <br/> 
	 * 
	 * @author zhanbh 
	 * @param shopId
	 * @return 
	 * @since JDK 1.6
	 */
	private ShopInfoResDTO qryShopInfoById(Long shopId){
	    if(null == shopId){
            return new ShopInfoResDTO();
        }
	    ShopInfoResDTO shopInfo = null;
	    try {
	        shopInfo = shopInfoRSV.findShopInfoByShopID(shopId);
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取店铺信息异常", e);
        }
	    if(null == shopInfo){
	        shopInfo = new ShopInfoResDTO();
	    }
	    return shopInfo;
	}
}
