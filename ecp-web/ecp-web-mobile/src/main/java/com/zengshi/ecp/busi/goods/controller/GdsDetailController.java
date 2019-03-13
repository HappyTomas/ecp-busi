package com.zengshi.ecp.busi.goods.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.velocity.AiToolUtil;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.goods.controller.utils.GdsDetailUtil;
import com.zengshi.ecp.busi.goods.vo.AddToCartButtonVO;
import com.zengshi.ecp.busi.goods.vo.GdsCatgCodeVO;
import com.zengshi.ecp.busi.goods.vo.GdsCommonCategoryVO;
import com.zengshi.ecp.busi.goods.vo.GdsDetailVO;
import com.zengshi.ecp.busi.goods.vo.GdsEvalVO;
import com.zengshi.ecp.busi.goods.vo.GdsParseISBNVO;
import com.zengshi.ecp.busi.goods.vo.GdsPromMatchSkuVO;
import com.zengshi.ecp.busi.goods.vo.GdsPromMatchVO;
import com.zengshi.ecp.busi.goods.vo.GdsSkuMediaVO;
import com.zengshi.ecp.busi.goods.vo.ScrollRespVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsBrowseHisReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReplyRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.GdsCategoryCompareReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.GdsCategoryCompareRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsSku2PropPropIdxReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2MediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.sharePoint.ShareMsgDto;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatgCustDiscRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCollectRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalReplyRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInterfaceGdsRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsPlatRecomRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsTypeRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsMessageUtil;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.order.dubbo.dto.ROrdSubStaffIdxReq;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdSubRSV;
import com.zengshi.ecp.order.dubbo.util.CommonConstants;
import com.zengshi.ecp.prom.dubbo.dto.GdsPromListDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromListRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.SeckillDiscountDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.StaffLocaleUtil;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopStaffGroupReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopCollectRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopManageRSV;
import com.zengshi.ecp.system.util.CookieUtil;
import com.zengshi.ecp.wxbase.util.WeixinUtil;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.CipherUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.MoneyUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-busi-ecp-web-mobile <br>
 * Description: 微商的商品详情页面<br>
 * Date:2016年6月20日下午2:16:19  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@RequestMapping(value="/gdsdetail")
@Controller
public class GdsDetailController extends EcpBaseController{
    private static String MODULE = GdsDetailController.class.getName();
    private String URL = "/goods/gdsdetail";
    private static String GDS_E_BOOK_CAT_CODE = "1200";
    private static String GDS_DIGITS_BOOK_CAT_CODE = "1201";
    private static int EVAL_PAGE_SIZE = 3;
    private static String GDS_PAPER_BOOK_CAT_CODE = "1115";
    private static Long PROP_ID_1032 = 1032l;
    private static int PAGE_SIZE_10 = 10;
    
    private static String KEY = "GDS_BROWSE_HIS";
    
    private static final String MASK_STRING = "***";
    
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
    private IGdsPlatRecomRSV iGdsPlatRecomRSV;

    @Resource
    private IGdsEvalReplyRSV iGdsEvalReplyRSV;

    @Resource
    private IGdsCatgCustDiscRSV gdsCatgCustDiscRSV;

    @Resource
    private IGdsTypeRSV gdsTypeRSV;

    @Resource
    private IGdsInterfaceGdsRSV iGdsInterfaceGdsRSV;

    @Resource
    private IPromQueryRSV promQueryRSV;

    @Resource
    private IShopCollectRSV iShopCollectRSV;

    @Resource
    private IGdsCollectRSV gdsCollectRSV;
    @Resource
    private IOrdSubRSV ordSubRSV;
    
    @Resource
    IGdsCategoryRSV gdsCategoryRSV;
    
    @RequestMapping("/{gdsId}-{skuId}")
    public String init(@PathVariable("gdsId") String gdsId, @PathVariable("skuId") String skuId,
            Model model,GdsDetailVO gdsDetailVO,HttpServletRequest request,HttpServletResponse response){
 //   	CookieUtil.deleteCookie(request, response, "shareMsg");
    	String share_staffId_temp = request.getParameter("staffId");
    	String share_staffId;
    	if(StringUtil.isBlank(share_staffId_temp) || StringUtil.isEmpty(share_staffId_temp)){
    		share_staffId="0";
        }else{
        	share_staffId = CipherUtil.decrypt(share_staffId_temp);
        }
    	if(StringUtil.isNotBlank(share_staffId) && !share_staffId.equals("0")){//分享写入cookie,staffId为0不加入
    		ShareMsgDto shareDto = new ShareMsgDto();
    		shareDto.setSkuId(skuId);
    		shareDto.setGdsId(gdsId);
    		shareDto.setStaffId(share_staffId);
            String exitValue;
				try {
					String jsonString = JSONObject.toJSONString(shareDto);
					List<String> cookielist = new ArrayList<String>();
					exitValue = CookieUtil.getCookieValue(request, "shareMsg");
		            JSONArray ja=JSONArray.parseArray(exitValue);
		            boolean isEqualGdsId=false;
		            boolean isEqualStaffId=false;
		            if(exitValue!=null){
		            	for(int i=0;i<ja.size();i++){
		        			JSONObject jo= ja.getJSONObject(i); //转换成JSONObject对象
		                    ShareMsgDto shareDto_cookie=(ShareMsgDto)JSONObject.parseObject(jo.toJSONString(),ShareMsgDto.class);    //转换成JavaBean
		                    if(shareDto_cookie.getGdsId().equals(gdsId)){
		                    	isEqualGdsId = true;
		                    }
		                    if(shareDto_cookie.getStaffId().equals(share_staffId)){
		                    	isEqualStaffId = true;
		                    }
		                }
		            }
		            if(exitValue==null || exitValue.equals("")){
		            	cookielist.add(jsonString);
		            	CookieUtil.addCookie(response, "shareMsg", cookielist.toString(), 60*60*24);
		            }else{
		            	if(isEqualGdsId==false && isEqualStaffId == true){//同个分享者分享多个商品
		            		cookielist.clear();
		            		for(int i=0;i<ja.size();i++){
		            			JSONObject jo= ja.getJSONObject(i); //转换成JSONObject对象
		                        ShareMsgDto shareDto_new=(ShareMsgDto)JSONObject.parseObject(jo.toJSONString(),ShareMsgDto.class);    //转换成JavaBean
		                        String jsonString_new = JSONObject.toJSONString(shareDto_new);
		                        cookielist.add(jsonString_new);
		                    }
		            		cookielist.add(jsonString);
		            		CookieUtil.deleteCookie(request, response, "shareMsg");
		            		CookieUtil.addCookie(response, "shareMsg", cookielist.toString(), 60*60*24);
		            	}else if(isEqualGdsId==false && isEqualStaffId==false){//不同分享者分享不同商品
		            		cookielist.clear();
		            		for(int i=0;i<ja.size();i++){
		                        JSONObject jo= ja.getJSONObject(i); //转换成JSONObject对象
		                        ShareMsgDto shareDto_new=(ShareMsgDto)JSONObject.parseObject(jo.toJSONString(),ShareMsgDto.class);    //转换成JavaBean
		                        String jsonString_new = JSONObject.toJSONString(shareDto_new);
		                        cookielist.add(jsonString_new);
		                    }
		            		cookielist.add(jsonString);
		            		CookieUtil.deleteCookie(request, response, "shareMsg");
		            		CookieUtil.addCookie(response, "shareMsg", cookielist.toString(), 60*60*24);
		            	};
		            }
			} catch (UnsupportedEncodingException e) {
				//e.printStackTrace();
				LogUtil.error(MODULE, "获取分享cookie失败！", e);
			}
    	}
    	
    	//详情页
    	GdsInfoReqDTO dto = new GdsInfoReqDTO();
        if (StringUtil.isNotEmpty(gdsDetailVO.getGdsId())) {
            dto.setId(gdsDetailVO.getGdsId());
        }
        if (StringUtil.isNotEmpty(gdsDetailVO.getSkuId())) {
            dto.setSkuId(gdsDetailVO.getSkuId());
        }
        GdsQueryOption[] gdsQueryOptions = new GdsQueryOption[] { GdsQueryOption.BASIC,GdsQueryOption.MAINPIC, GdsQueryOption.GDSTYPE};
        SkuQueryOption[] skuQueryOptions = new SkuQueryOption[] { SkuQueryOption.BASIC, SkuQueryOption.PROP,SkuQueryOption.MEDIA,
                SkuQueryOption.SHOWSTOCK, SkuQueryOption.CAlDISCOUNT };
        dto.setGdsQueryOptions(gdsQueryOptions);
        dto.setSkuQuerys(skuQueryOptions);
        GdsInfoDetailRespDTO resultDto = null;
        try {
            resultDto = iGdsInfoQueryRSV.queryGdsInfoDetail(dto);
            if (resultDto != null && resultDto.getSkuInfo() != null) {
                
                sendRecentlyBrowMsg(resultDto);
                model.addAttribute("shopId", resultDto.getShopId());

            } else {
                resultDto = new GdsInfoDetailRespDTO();
                GdsSkuInfoRespDTO gdsSkuInfoRespDTO = new GdsSkuInfoRespDTO();
                gdsSkuInfoRespDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_DELETE);
                resultDto.setId(dto.getId());
                gdsSkuInfoRespDTO.setId(dto.getSkuId());
                resultDto.setSkuInfo(gdsSkuInfoRespDTO);
                model.addAttribute("gdsDetailInfo", resultDto);
                model.addAttribute("shopId", resultDto.getShopId());
                return URL + "/gdsdetail";
            }
        } catch (BusinessException e) {
            if (resultDto == null || GdsErrorConstants.GdsInfo.ERROR_GOODS_GDSINFO_210005.equals(e.getErrorCode())) {
                resultDto = new GdsInfoDetailRespDTO();
                GdsSkuInfoRespDTO gdsSkuInfoRespDTO = new GdsSkuInfoRespDTO();
                gdsSkuInfoRespDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_DELETE);
                resultDto.setId(dto.getId());
                gdsSkuInfoRespDTO.setId(dto.getSkuId());
                resultDto.setSkuInfo(gdsSkuInfoRespDTO);
                model.addAttribute("gdsDetailInfo", resultDto);
                return URL + "/gdsdetail";
            }
            LogUtil.error(MODULE, "无法获取商品详情信息！", e);
        }
        String shopName = "";
        String stockStatus = "";
        String stockStatusDesc = "";
        String propValues = "";
        String exitAuthor = "0";
        String exitOtherbook = "0";
        long exit_gdsId = 0;
        long exit_skuId = 0;
        String Isbn = "";
        String standardIsbn = "";
        String catgCode ="";
        String platCags ="";
        String exit_catName = "";
        String content = "";
        String date = "";
        boolean exitIsbn = false;
        boolean exitDate = false;
        boolean ebook = false;
        boolean dbook = false;
        boolean valueAdded = false;
        String firstImgUrl = "";
        if (StringUtil.isNotEmpty(resultDto)) {
            if (resultDto.getSkuInfo() != null) {
                stockStatus = GdsUtils.checkStcokStatus(resultDto.getSkuInfo().getRealAmount());
                stockStatusDesc = GdsUtils.checkStcokStatusDesc(resultDto.getSkuInfo().getRealAmount());
                Isbn = resultDto.getIsbn();
                if(StringUtil.isNotBlank(Isbn)){
                	exitIsbn=true;
                }
                //产品详情
                if (StringUtil.isNotBlank(resultDto.getGdsDesc())) {
                    resultDto.setGdsDesc(getHtmlUrl(resultDto.getGdsDesc()));
                }
                platCags = resultDto.getSkuInfo().getPlatCatgs();
                if(platCags.contains("<1200>")){
                	ebook = true;
            	}else if(platCags.contains("<1201>")){
            		dbook = true;
                }else{
                }
            }
            if (GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES.equals(resultDto.getSkuInfo().getGdsStatus())
                    || GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES.equals(resultDto.getSkuInfo().getGdsStatus())) {
            }
            ShopInfoResDTO shopInfo = iShopInfoRSV.findShopInfoByShopID(resultDto.getShopId());
            if (StringUtil.isNotEmpty(shopInfo)) {
                shopName = shopInfo.getShopName();
            } else {
                throw new BusinessException("web.gds.2000012");
            }
            if (CollectionUtils.isNotEmpty(resultDto.getSkuInfo().getGdsProps())){
            	List<GdsPropRespDTO> props_list = resultDto.getSkuInfo().getGdsProps();
                for(int i=0; i<props_list.size(); i++){
                	long propId = props_list.get(i).getId();
                	if(propId==1001){//作者
                		propValues = props_list.get(i).getValues().get(0).getPropValue();
                		if(StringUtil.isNotBlank(propValues)){
                			exitAuthor = "1";
                		}
                	}
//                	if(propId==1004){//五位ISBN
//                		Isbn = props_list.get(i).getValues().get(0).getPropValue();
//                		if(StringUtil.isNotBlank(Isbn)){
//                			exitIsbn=true;
//                		}
//                	}
                	if(propId==1032){//标准ISBN
                		standardIsbn = props_list.get(i).getValues().get(0).getPropValue();
                	}
                	if(propId==1020){//内容简介
                		String temp = props_list.get(i).getValues().get(0).getPropValue();
                		if(StringUtil.isNotBlank(temp)){
                			props_list.get(i).getValues().get(0).setPropValue(getHtmlUrl(temp));
                		}
                	}
                	if(propId==1005){//出版日期
                		String date_temp = props_list.get(i).getValues().get(0).getPropValue();
                		if(StringUtil.isNotBlank(date_temp)){
                			date = date_temp.substring(0, 10);
                			exitDate = true;
                		}
                	}
                	if(propId==1021){//目录
                		String temp = props_list.get(i).getValues().get(0).getPropValue();
                		if(StringUtil.isNotBlank(temp)){
                			props_list.get(i).getValues().get(0).setPropValue(getHtmlUrl(temp));
                		}
                	}
                	if(propId==1022){//作者介绍
                		String temp = props_list.get(i).getValues().get(0).getPropValue();
                		if(StringUtil.isNotBlank(temp)){
                			props_list.get(i).getValues().get(0).setPropValue(getHtmlUrl(temp));
                		}
                	}
                	if(propId==1023){//编辑推荐
                		String temp = props_list.get(i).getValues().get(0).getPropValue();
                		if(StringUtil.isNotBlank(temp)){
                			props_list.get(i).getValues().get(0).setPropValue(getHtmlUrl(temp));
                		}
                	}
                	if(propId==1024){//专家推荐
                		String temp = props_list.get(i).getValues().get(0).getPropValue();
                		if(StringUtil.isNotBlank(temp)){
                			props_list.get(i).getValues().get(0).setPropValue(getHtmlUrl(temp));
                		}
                	}
                	if(propId==1025){//章节节选
                		String temp = props_list.get(i).getValues().get(0).getPropValue();
                		if(StringUtil.isNotBlank(temp)){
                			props_list.get(i).getValues().get(0).setPropValue(getHtmlUrl(temp));
                		}
                	}
                	if(propId==1027){//增值服务
                	    Long temp = props_list.get(i).getValues().get(0).getId();
                        if(null!= temp && 308L == temp.longValue()){
                            valueAdded = true; 
                        }
                	}
                }
                
/*                if (StringUtil.isNotBlank(resultDto.getGdsDesc())) {
                	resultDto.setGdsDesc(getHtmlUrl(resultDto.getGdsDesc()));
                } else {
                    List<GdsPropRespDTO> props = resultDto.getProps();
                    if (CollectionUtils.isNotEmpty(props)) {
                        for (GdsPropRespDTO gdsPropRespDTO : props) {
                            if(GdsConstants.GdsProp.GDS_PROP_VALUE_INPUT_TYPE_RICHTXT.equals(gdsPropRespDTO.getPropInputType())){
                                List<GdsPropValueRespDTO> propValue=gdsPropRespDTO.getValues();
                                if (CollectionUtils.isNotEmpty(propValue)) {
                                	resultDto.setGdsDesc(getHtmlUrl(propValue.get(0).getPropValue()));
                                }
                            }
                        }
                    }
                }*/

            }
            if (StringUtil.isNotEmpty(resultDto.getSkuInfo().getStockInfoRespDTO())){
            	catgCode = resultDto.getSkuInfo().getStockInfoRespDTO().getCatgCode();
            }
            if (CollectionUtils.isNotEmpty(resultDto.getSkuInfo().getSku2MediaRespDTOs())){
            	firstImgUrl = ImageUtil.getImageUrl(resultDto.getSkuInfo().getSku2MediaRespDTOs().get(0).getMediaUuid());
            }else{
            	firstImgUrl = ImageUtil.getImageUrl("");
            }
        }
        
        String otherBP ="";
        if(StringUtil.isNotBlank(standardIsbn)){//获取对应的电子书或者纸质书
        	GdsParseISBNVO gdsParseISBNVO = new GdsParseISBNVO();
        	gdsParseISBNVO.setBiazhunisbn(standardIsbn);
        	gdsParseISBNVO.setSkuId(resultDto.getSkuInfo().getStockInfoRespDTO().getSkuId());
        	gdsParseISBNVO.setCatgCode(catgCode);
        	List<GdsCatgCodeVO> gdsCateCode = querygdsbyisbn(gdsParseISBNVO);
        	if(gdsCateCode.size()>1){
        		exitOtherbook="1";
        		for(int i=0;i<gdsCateCode.size();i++){
            		if(!catgCode.equals(gdsCateCode.get(i).getCatgCode())){
            			exit_gdsId = gdsCateCode.get(i).getGdsId();
            			//exit_gdsId = 0;
            			exit_skuId = gdsCateCode.get(i).getSkuId();
            			exit_catName = gdsCateCode.get(i).getCatgName();
            			break;
            		}
            	}
        		//获取对应的电子书或者纸质书的价格
        		if(exit_gdsId!=0){
        			otherBP = getOtherGdsPrice(exit_gdsId,exit_skuId);
        		}
        	}else{
        		exitOtherbook="0";
        	}
            
        }else{
        	exitOtherbook="0";
        }
        
        AddToCartButtonVO btn = GdsDetailUtil.getDefaultAddToCartButton(stockStatus, GdsUtils.isEqualsValid(resultDto.getGdsTypeRespDTO().getIfNeedstock()));
        
        // 添加工厂库存逻辑.
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            
            StockInfoRespDTO skuStock = resultDto.getSkuInfo().getStockInfoDTO();
            // 获取1005属性值.
            GdsPropRespDTO prop1005 = resultDto.getSkuInfo().getAllPropMaps().get("1005");
            String prop1005Value = null;
            if(null != prop1005){
                if(CollectionUtils.isNotEmpty(prop1005.getValues())){
                    prop1005Value =  prop1005.getValues().get(0).getPropValue();
                }
            }
            
            
            btn = GdsDetailUtil.getAddToCartButton(gdsId, skuId,
                    resultDto.getSkuInfo().getRealAmount(), skuStock.getFacStock(), stockStatus,
                    prop1005Value, skuStock.getZeroStockStarttime() != null
                            ? (sdf.format(skuStock.getZeroStockStarttime())) : null, GdsUtils.isEqualsValid(resultDto.getGdsTypeRespDTO().getIfNeedstock()));
            
        }catch(Exception e){
            LogUtil.error(MODULE, "[商城商品域]商品详请设置加入购物车按钮遇到异常!",e);
        }
        
        model.addAttribute("addToCartPromp",btn.getAddToCartPromp());
        model.addAttribute("addToCartEnable",btn.isAddToCartEnable());
        
        
        // 获取库存阈值的配置参数
        getStockParam(model);
        model.addAttribute("propValues", propValues);
        model.addAttribute("exitAuthor", exitAuthor);
        model.addAttribute("exitOtherbook", exitOtherbook);
        model.addAttribute("exit_gdsId", exit_gdsId);
        model.addAttribute("exit_skuId", exit_skuId);
        model.addAttribute("exit_catName", exit_catName);
        model.addAttribute("shopName", shopName);
        model.addAttribute("stockStatus", stockStatus);
        model.addAttribute("stockStatusDesc", stockStatusDesc);
        model.addAttribute("gdsDetailInfo", resultDto);
        model.addAttribute("otherBP", otherBP);
        model.addAttribute("content", content);
        model.addAttribute("exitIsbn", exitIsbn);
        model.addAttribute("exitDate", exitDate);
        model.addAttribute("ebook", ebook);
        model.addAttribute("dbook", dbook);
        model.addAttribute("valueAdded", valueAdded);
        model.addAttribute("isbn", Isbn);
        model.addAttribute("datetime", date);
        LongReqDTO gdsTypeQuery = new LongReqDTO();
        gdsTypeQuery.setId(resultDto.getGdsTypeId());
        GdsTypeRespDTO gdsTypeRespDTO = gdsTypeRSV.queryGdsTypeByPK(gdsTypeQuery);
        model.addAttribute("gdsType", gdsTypeRespDTO);

        BaseSysCfgRespDTO baseSysCfgRespDTO = SysCfgUtil.fetchSysCfg("SHOP_SHOW_LOCK");
        model.addAttribute("shopShow", baseSysCfgRespDTO.getParaValue());
        model.addAttribute("systime", DateUtil.getSysDate().getTime());
        model.addAttribute("firstImgUrl", firstImgUrl);
        model.addAttribute("staffId", CipherUtil.encrypt(String.valueOf(StaffLocaleUtil.getStaff().getId())));
        return URL + "/gdsdetail";
    }
    
    /**
     * 
     * getSkuDetailInfo:(切换选择属性，获取对应属性的单品信息). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * 
     * @author gxq
     * @param model
     * @param gdsDetailVO
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = "getskudetailinfo")
    @ResponseBody
    public Model getSkuDetailInfo(Model model, GdsDetailVO gdsDetailVO) {
        GdsInfoDetailRespDTO respDto = new GdsInfoDetailRespDTO();
        GdsInfoReqDTO dto = new GdsInfoReqDTO();
        if (StringUtil.isNotEmpty(gdsDetailVO.getGdsId())) {
            dto.setId(gdsDetailVO.getGdsId());
        }
        if (StringUtil.isNotEmpty(gdsDetailVO.getSkuId())) {
            dto.setSkuId(gdsDetailVO.getSkuId());
        }
        GdsQueryOption[] gdsQueryOptions = new GdsQueryOption[] {
                GdsQueryOption.BASIC};
        SkuQueryOption[] skuQueryOptions = new SkuQueryOption[] {
                SkuQueryOption.BASIC, SkuQueryOption.MEDIA,SkuQueryOption.MAINPIC,
                SkuQueryOption.PROP, SkuQueryOption.SHOWSTOCK,SkuQueryOption.CAlDISCOUNT };
        dto.setGdsQueryOptions(gdsQueryOptions);
        dto.setSkuQuerys(skuQueryOptions);
        parsetSkuPropParam(dto, gdsDetailVO);
        try {
            respDto = iGdsInfoQueryRSV.queryGdsInfoDetail(dto);
            String stockStatus = "";
            String princePrice = "";
            if (StringUtil.isEmpty(respDto)) {
                respDto = new GdsInfoDetailRespDTO();
            } else {
                if (respDto.getSkuInfo() != null) {
                    GdsMediaRespDTO gdsMediaRespDTO = respDto.getSkuInfo().getMainPic();
                    if(gdsMediaRespDTO != null){
                        gdsMediaRespDTO.setURL( new AiToolUtil().genImageUrl(gdsMediaRespDTO.getMediaUuid(),"74x74!"));
                    }
                    stockStatus = GdsUtils.checkStcokStatus(respDto.getSkuInfo()
                            .getRealAmount());
                    //数字印刷的价格
                    if(respDto.getSkuInfo().getAllPropMaps()!=null && respDto.getSkuInfo().getAllPropMaps().get("1029")!= null){
                        for(GdsPropValueRespDTO gdsPropValueRespDTO :respDto.getSkuInfo().getAllPropMaps().get("1029").getValues()){
                            if(!StringUtil.isBlank(gdsPropValueRespDTO.getPropValue())){
                                princePrice = MoneyUtil.convertCentToYuan(gdsPropValueRespDTO.getPropValue());
                            }else{
                                princePrice = "0";
                            }
                        }
                    }
                }
                
            };
            model.addAttribute("gdsType", respDto.getGdsTypeRespDTO());
            model.addAttribute("digitsPrinPrice", princePrice);
            model.addAttribute("respDto", respDto);
            model.addAttribute("stockStatus", stockStatus);
            //获取库存阈值的配置参数
            getStockParam(model);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "单品信息切换错误！", e);
        } catch (Exception e) {
            LogUtil.error(MODULE, "单品信息切换错误！", e);
        }
        return model;
    }
    
    private void parsetSkuPropParam(GdsInfoReqDTO dto, GdsDetailVO gdsDetailVO) {
        List<GdsSku2PropReqDTO> skuProps = new ArrayList<GdsSku2PropReqDTO>();
        JSONArray list = JSONArray.parseArray(gdsDetailVO.getSkuPropParam());
        int size = list.size();
        if (size >= 1) {
            GdsSku2PropReqDTO reqDto = null;
            for (int i = 0; i < size; i++) {
                reqDto = new GdsSku2PropReqDTO();
                JSONObject object = (JSONObject) list.get(i);
                String propId = object.getString("propId");
                String propName = object.getString("propName");
                String valueId = object.getString("valueId");
                String value = object.getString("value");
                if (StringUtil.isNotBlank(propId)) {
                    reqDto.setPropId(Long.parseLong(propId));
                }
                reqDto.setPropName(propName);
                if (StringUtil.isNotBlank(valueId)) {
                    reqDto.setPropValueId(Long.parseLong(valueId));
                }
                reqDto.setPropValue(value);
                skuProps.add(reqDto);
            }
        }
        dto.setSkuProps(skuProps);
    }
    
    /**
     * 
     * :(获取商品详情上的图片展示). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * 
     * @author gxq
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = "/querygdspictrues")
    public String queryGdsPictrue(Model model,@RequestParam("gdsId") String gdsId,
            @RequestParam("skuId") String skuId) {
        List<GdsSkuMediaVO> resultList = new ArrayList<GdsSkuMediaVO>();
        GdsSkuInfoReqDTO dto = new GdsSkuInfoReqDTO();
//      if (StringUtil.isNotBlank(gdsId)) {
//          dto.setId(Long.parseLong(gdsId));
//      }
        if (StringUtil.isNotBlank(skuId)) {
            dto.setId(Long.parseLong(skuId));
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
                    	//过滤产品版权页图片和产品包装条形码特写的图片
						if(!gdsSku2MediaRespDTO.getSortNo().equals(6) && !gdsSku2MediaRespDTO.getSortNo().equals(7)){
							gdsSkuMediaVO = new GdsSkuMediaVO();
							gdsSkuMediaVO.setUrl(new AiToolUtil().genImageUrl(gdsSku2MediaRespDTO.getMediaUuid(),
									"360x309!"));
							resultList.add(gdsSkuMediaVO);
						}
                    }
                }else{
                    gdsSkuMediaVO = new GdsSkuMediaVO();
                    gdsSkuMediaVO.setUrl(new AiToolUtil().genImageUrl("","360x309!"));
                    resultList.add(gdsSkuMediaVO);
                }
            }else{
                GdsSkuMediaVO gdsSkuMediaVO = new GdsSkuMediaVO();
                gdsSkuMediaVO.setUrl(new AiToolUtil().genImageUrl("","360x309!"));
                resultList.add(gdsSkuMediaVO);
            }
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "获取商品详情图片错误！", e);
        }
        model.addAttribute("result", resultList);
        return URL +"/list/gdsdetail-pictrues-list";
    }
    
    /**
     * 
     * getStockParam:(封装的从缓存获取库存的阈值). <br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author gxq
     * @param model
     * @since JDK 1.6
     */
    public void getStockParam(Model model) {
        try {
            model.addAttribute("STOCK_LACK_THRESHOLD", Integer
                    .parseInt(SysCfgUtil.fetchSysCfg(GdsConstants.GdsStock.STOCK_LACK_THRESHOLD).getParaValue()));
        } catch (Exception e) {
            model.addAttribute("STOCK_LACK_THRESHOLD", 0);
            LogUtil.error(MODULE, "缓存获取库存阈值配置参数失败！", e);
        }
        try {
            model.addAttribute("STOCK_HARDTOGET_THRESHOLD", Integer
                    .parseInt(SysCfgUtil.fetchSysCfg(GdsConstants.GdsStock.STOCK_HARDTOGET_THRESHOLD).getParaValue()));
        } catch (Exception e) {
            model.addAttribute("STOCK_HARDTOGET_THRESHOLD", 0);
            LogUtil.error(MODULE, "缓存获取库存阈值配置参数失败！", e);
        }
    }
    
    
    /**
     * 
     * queryGdsEval:(获取商品详情的商品评价). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * 
     * @author gxq
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping(value = "/querygdseval")
    public String queryGdsEval(Model model, GdsEvalVO gdsEvalVO){
        try {
            gdsEvalVO.setPageSize(EVAL_PAGE_SIZE);
            getGdsEval(model,gdsEvalVO);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "获取评价列表失败！", e);
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取评价列表失败！", e);
        }
        return URL + "/list/gdsdetail-gdseval-list";
    }
    
    /**
     * 
     * querygdsevalscroll:(下拉滚动加载评价列表). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param gdsEvalVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/querygdsevalscroll")
    @ResponseBody
    public ScrollRespVO querygdsevalscroll(Model model,GdsEvalVO gdsEvalVO){
        ScrollRespVO scrollRespVO = new ScrollRespVO();
        GdsEvalReqDTO gdsEvalReqDTO = gdsEvalVO.toBaseInfo(GdsEvalReqDTO.class);
        ObjectCopyUtil.copyObjValue(gdsEvalVO, gdsEvalReqDTO, "", false);
        gdsEvalReqDTO.setPageNo(gdsEvalVO.getPage());
        gdsEvalReqDTO.setPageSize(5);
        gdsEvalReqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        PageResponseDTO<GdsEvalRespDTO> pageInfo = null;
        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = null;
        try {
            if("0".equals(gdsEvalVO.getEvalLeval())){
                //全部
            }else if("1".equals(gdsEvalVO.getEvalLeval())){
                //好评
                gdsEvalReqDTO.setScore((short)5);
            }else if("2".equals(gdsEvalVO.getEvalLeval())){
                //中评
                gdsEvalReqDTO.setScoreFrom((short)2);
                gdsEvalReqDTO.setScoreTo((short)4);
            }else if("3".equals(gdsEvalVO.getEvalLeval())){
                //差评
                gdsEvalReqDTO.setScoreFrom((short)0);
                gdsEvalReqDTO.setScoreTo((short)1);
            }
            pageInfo =iGdsEvalRSV.queryGdsEvalRespDTOPagingForGdsDetail(gdsEvalReqDTO);
            if (StringUtil.isNotEmpty(pageInfo)) {
                List<GdsEvalRespDTO> resp = pageInfo.getResult();
                if (resp != null && resp.size() > 0) {
                    for (GdsEvalRespDTO gdsEvalRespDTO : resp) {
                        // 解析评价内容
                        try {
                            gdsEvalRespDTO.setDetail(FileUtil
                                    .readFile2Text(
                                            gdsEvalRespDTO.getContent(),
                                            "UTF-8"));
                        } catch (Exception e) {
                            continue;
                        }
                        //每条评价的对应回复的内容
                        if (gdsEvalRespDTO.getEvalReplyRespDTOs() != null
                                && gdsEvalRespDTO.getEvalReplyRespDTOs().size() > 0) {
                            for (GdsEvalReplyRespDTO subDto : gdsEvalRespDTO.getEvalReplyRespDTOs()) {
                                // 解析回复内容
                                try {
                                    subDto.setDetail(FileUtil.readFile2Text(
                                            subDto.getContent(), "UTF-8"));
                                    CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
									custInfoReqDTO.setId(subDto.getStaffId());
									CustInfoResDTO custInfoResDTO = iCustInfoRSV.getCustInfoById(custInfoReqDTO);
									subDto.setStaffName(maskStaffCode(custInfoResDTO.getStaffCode()));
                                } catch (Exception e) {
                                    continue;
                                }
                            }
                        }
                        // 获取评价客户的客户信息。
                        CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
                        custInfoReqDTO.setId(gdsEvalRespDTO.getStaffId());
                        CustInfoResDTO custInfoResDTO = iCustInfoRSV.getCustInfoById(custInfoReqDTO);
                        gdsEvalRespDTO.setStaffName(maskStaffCode(custInfoResDTO.getStaffCode()));
                        gdsEvalRespDTO.setCustPic(new AiToolUtil().genImageUrl( custInfoResDTO.getCustPic(),"41x41!"));
                        gdsEvalRespDTO.setStaffLevelName(custInfoResDTO.getCustLevelName());
                        gdsEvalRespDTO.setStaffLevelCode(custInfoResDTO.getCustLevelCode());
                    }
                }
            }
            if(pageInfo != null){
                if(pageInfo.getResult() != null){
                    scrollRespVO.setTotal(Long.valueOf(pageInfo.getResult().size()+""));
                }else{
                    scrollRespVO.setTotal(0l);
                }
                scrollRespVO.setDatas(pageInfo.getResult());
            }
        } catch (BusinessException e){
            LogUtil.error(MODULE, "获取评价列表失败！", e);
        }
        return scrollRespVO;
    }
    
    public void getGdsEval(Model model,GdsEvalVO gdsEvalVO) throws Exception {
        GdsEvalReqDTO gdsEvalReqDTO = gdsEvalVO.toBaseInfo(GdsEvalReqDTO.class);
        ObjectCopyUtil.copyObjValue(gdsEvalVO, gdsEvalReqDTO, "", false);
        gdsEvalReqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        PageResponseDTO<GdsEvalRespDTO> pageInfo = null;
        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = null;
        try {
            pageInfo =iGdsEvalRSV.queryGdsEvalRespDTOPagingForGdsDetail(gdsEvalReqDTO);

            if (StringUtil.isNotEmpty(pageInfo)) {
                List<GdsEvalRespDTO> resp = pageInfo.getResult();
                if (resp != null && resp.size() > 0) {
                    for (GdsEvalRespDTO gdsEvalRespDTO : resp) {
                        // 解析评价内容
                        try {
                            gdsEvalRespDTO.setDetail(FileUtil
                                    .readFile2Text(
                                            gdsEvalRespDTO.getContent(),
                                            "UTF-8"));
                        } catch (Exception e) {
                            continue;
                        }
                        //每条评价的对应回复的内容
                        if (gdsEvalRespDTO.getEvalReplyRespDTOs() != null
                                && gdsEvalRespDTO.getEvalReplyRespDTOs().size() > 0) {
                            for (GdsEvalReplyRespDTO subDto : gdsEvalRespDTO.getEvalReplyRespDTOs()) {
                                // 解析回复内容
                                try {
                                    subDto.setDetail(FileUtil.readFile2Text(
                                            subDto.getContent(), "UTF-8"));
                                    CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
									custInfoReqDTO.setId(subDto.getStaffId());
									CustInfoResDTO custInfoResDTO = iCustInfoRSV.getCustInfoById(custInfoReqDTO);
									subDto.setStaffName(maskStaffCode(custInfoResDTO.getStaffCode()));
                                } catch (Exception e) {
                                    continue;
                                }
                            }
                        }
                        // 获取评价客户的客户信息。
                        CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
                        custInfoReqDTO.setId(gdsEvalRespDTO.getStaffId());
                        CustInfoResDTO custInfoResDTO = iCustInfoRSV.getCustInfoById(custInfoReqDTO);
                        gdsEvalRespDTO.setStaffName(maskStaffCode(custInfoResDTO.getStaffCode()));
                        gdsEvalRespDTO.setCustPic(new AiToolUtil().genImageUrl( custInfoResDTO.getCustPic(),"41x41!"));
                        gdsEvalRespDTO.setStaffLevelName(custInfoResDTO.getCustLevelName());
                        gdsEvalRespDTO.setStaffLevelCode(custInfoResDTO.getCustLevelCode());
                    }
                }
                respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
            }
            model.addAttribute("list", respVO);
            model.addAttribute("gdsId", gdsEvalVO.getGdsId());
            model.addAttribute("skuId", gdsEvalVO.getSkuId());
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "获取评价列表失败！", e);
            model.addAttribute("list",EcpBasePageRespVO.buildByPageResponseDTO(pageInfo));
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取评价列表失败！", e);
            model.addAttribute("list",EcpBasePageRespVO.buildByPageResponseDTO(pageInfo));
        }

    }
    /**
     * 
     * add:(添加收藏). <br/> 
     * 
     * @author gxq 
     * @param gdsDetailVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public EcpBaseResponseVO add(GdsDetailVO gdsDetailVO) {
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        try {
            GdsCollectReqDTO dto = new GdsCollectReqDTO();
            if(StringUtil.isNotBlank(gdsDetailVO.getCollectId())){
                dto.setId(Long.valueOf(gdsDetailVO.getCollectId()));
                iGdsCollectRSV.deleteGdsCollectByPK(dto);
                vo.setResultMsg("");
            }else{
                dto.setSkuId(gdsDetailVO.getSkuId());
                Long result = iGdsCollectRSV.addGdsCollect(dto);
                if(result != null){
                    vo.setResultMsg(result+"");
                }
            }
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
     * queryCommondCat:(获取相同分类推荐). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param gdsCommonCategoryVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/querycommondcat")
    public String queryCommondCat(Model model,GdsCommonCategoryVO gdsCommonCategoryVO) {
		String url = getAnalysisUrl()+"/service/recommendRelatedGds";
		Long skuIdValue = gdsCommonCategoryVO.getSkuId();
		if(null == skuIdValue && null != gdsCommonCategoryVO.getGdsId()){
		    try {
		        skuIdValue = getSkuId(gdsCommonCategoryVO.getGdsId());
	        } catch (Exception e) {
	            LogUtil.error(MODULE, "查询商品对应 单品id异常", e);
	        }  
		}
		if(StringUtil.isEmpty(skuIdValue)){
		    return URL + "/list/gdsdetail-samecategory-list";
		}
		BasicNameValuePair base = new BasicNameValuePair("skuId",skuIdValue.toString());
//		BasicNameValuePair base = new BasicNameValuePair("skuId","277682");
//		String url ="http://shoptest1.pmph.com:19419/ecp_httpsv/service/recommendRelatedGds";
		List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
		formparams.add(base);
		String json = doRequest(url, formparams);
		com.alibaba.fastjson.JSONObject jsonObj = null;
		if(null != json){
		    jsonObj = com.alibaba.fastjson.JSONObject.parseObject(json);
		}
		String serviceState = null;
		if(null != jsonObj && jsonObj.containsKey("serviceState")){
		    serviceState= jsonObj.get("serviceState").toString();
		}
		List<GdsInfoDetailRespDTO> gdsInfo = new ArrayList<GdsInfoDetailRespDTO>();
		if(null != serviceState && "0000".equals(serviceState) && jsonObj.containsKey("goodsList")){
			String goodsList = jsonObj.get("goodsList").toString();
			JSONArray jsonArray = null;
            if(StringUtil.isNotBlank(goodsList)){
                jsonArray = JSONArray.parseArray(goodsList); 
            }
            if(null == jsonArray){
                jsonArray = new JSONArray();
            }  
			int length;
			if(jsonArray.size()<=5){
				length=jsonArray.size();
			}else{
				length=5;
			}
			for(int i=0;i<length;i++){
				JSONObject jo= jsonArray.getJSONObject(i); //转换成JSONObject对象
				String gdsName = jo.get("gdsName").toString();
				jo.get("gdsStatus");
				jo.get("mainPicId");
				String skuId = jo.get("skuId").toString();
				String gdsId = jo.get("gdsId").toString();
				String guidePrice = jo.get("guidePrice").toString();
				String skuProps = jo.get("skuProps").toString();
				JSONArray jy_skuProps = JSONArray.parseArray(skuProps);
				Map<String,GdsPropRespDTO> propMap=new HashMap<String,GdsPropRespDTO>();
				for(int j=0;j<jy_skuProps.size();j++){
					List<GdsPropValueRespDTO> propValueList = new ArrayList<GdsPropValueRespDTO>();
					GdsPropRespDTO propResp = new GdsPropRespDTO();
					GdsPropValueRespDTO propValue = new GdsPropValueRespDTO();
					JSONObject jo_prop= jy_skuProps.getJSONObject(j); //转换成JSONObject对象
					String prop_id =(String) jo_prop.get("prop_id");
					String prop_name =(String) jo_prop.get("prop_name");
					String prop_value =(String) jo_prop.get("prop_value");
					propValue.setPropValue(prop_value);
					propValueList.add(propValue);
					propResp.setId(Long.valueOf(prop_id));
					propResp.setPropName(prop_name);
					propResp.setValues(propValueList);
					propMap.put(prop_id, propResp);
				}
				GdsInfoDetailRespDTO detailResp = new GdsInfoDetailRespDTO();
				detailResp.setGdsName(gdsName);
				detailResp.setGuidePrice(Long.valueOf(guidePrice));
				GdsSkuInfoRespDTO GdsSkuInfo = new GdsSkuInfoRespDTO();
				GdsSkuInfo.setGdsId(Long.valueOf(gdsId));
				GdsSkuInfo.setId(Long.valueOf(skuId));
				GdsMediaRespDTO mainPic = new GdsMediaRespDTO();
				mainPic.setMediaUuid(jo.get("mainPicId").toString());
				GdsSkuInfo.setMainPic(mainPic);
				GdsSkuInfo.setGdsName(gdsName);
				GdsSkuInfo.setAllPropMaps(propMap);
				long discountPrice = getDiscountPrice(Long.valueOf(gdsId),Long.valueOf(skuId));
				GdsSkuInfo.setDiscountPrice(discountPrice);
				detailResp.setSkuInfo(GdsSkuInfo);
				gdsInfo.add(detailResp);
			} 
		}else{
			gdsInfo=null;
		}
    	
//        GdsInfoReqDTO reqDTO = gdsCommonCategoryVO.toBaseInfo(GdsInfoReqDTO.class);
        List<GdsInfoDetailRespDTO> gdsInfoDetailRespDTO = null;
//        reqDTO.setMainCatgs(gdsCommonCategoryVO.getCategCode());
//        if(StringUtil.isNotEmpty(gdsCommonCategoryVO.getGdsId())){
//            reqDTO.setId(gdsCommonCategoryVO.getGdsId());
//        }
//        if(gdsCommonCategoryVO.getPageNumber()==0 || gdsCommonCategoryVO.getPageSize() ==0){
//            reqDTO.setPageNo(1);
//            reqDTO.setPageSize(6);
//        }else{
//            reqDTO.setPageNo(gdsCommonCategoryVO.getPageNumber());
//            reqDTO.setPageSize(gdsCommonCategoryVO.getPageSize());
//        }
        try {
//            gdsInfoDetailRespDTO = iGdsInfoQueryRSV.queryGdsSkuInfoListByCatgRela(reqDTO);
        	gdsInfoDetailRespDTO = gdsInfo;
            if (gdsInfoDetailRespDTO == null) {
                gdsInfoDetailRespDTO = new ArrayList<GdsInfoDetailRespDTO>();
            }
            model.addAttribute("commonAuthorList", gdsInfoDetailRespDTO);
            model.addAttribute("gdsId", gdsCommonCategoryVO.getGdsId());
            //表示到最后一页了。要从头开始咯
            if(gdsInfoDetailRespDTO.size()<6){
                gdsCommonCategoryVO.setPageNumber(0);
                gdsCommonCategoryVO.setPageSize(6);
            }
            model.addAttribute("pageNo", gdsCommonCategoryVO.getPageNumber());
            model.addAttribute("pageSize", gdsCommonCategoryVO.getPageSize());
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "获取相同分类推荐失败！", e);
            model.addAttribute("commonAuthorList",
                    new ArrayList<GdsSkuInfoRespDTO>());
            return URL + "/list/gdsdetail-samecategory";
        }
        return URL + "/list/gdsdetail-samecategory-list";
    }

    /**
     * 
     * querySaleList:(获取促销列表). <br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author gxq
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = "/querysalelist")
    @ResponseBody
    public Map<String, Object> querySaleList(GdsDetailVO gdsDetailVO) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<PromListRespDTO> list = new ArrayList<PromListRespDTO>();
        GdsPromListDTO listDto ;
        SeckillDiscountDTO seckillDto =null;
        PromInfoDTO promInfoDTO = null;
        try {
            PromRuleCheckDTO promRuleCheckDTO = new PromRuleCheckDTO();
            CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
            
            CustInfoResDTO custInfoResDTO = null;
            ShopStaffGroupReqDTO shopStaffGroupReqDTO = new ShopStaffGroupReqDTO();
            if (custInfoReqDTO.getStaff().getId() == 0) {
            	promRuleCheckDTO.setCustLevelValue(custInfoReqDTO.getStaff().getStaffLevelCode());
            } else {
	            custInfoReqDTO.setId(custInfoReqDTO.getStaff().getId());
	            custInfoResDTO = iCustInfoRSV.getCustInfoById(custInfoReqDTO);
	            promRuleCheckDTO.setCustLevelValue(custInfoResDTO.getCustLevelCode());
	            promRuleCheckDTO.setAreaValue(custInfoResDTO.getProvinceCode());
	            promRuleCheckDTO.setStaffId(custInfoResDTO.getId() + "");
	            shopStaffGroupReqDTO.setStaffId(custInfoResDTO.getId());
            }
            
            promRuleCheckDTO.setSiteId(promRuleCheckDTO.getCurrentSiteId());
//            promRuleCheckDTO.setStaffId(custInfoReqDTO.getStaff().getId() + "");
            promRuleCheckDTO.setGdsId(gdsDetailVO.getGdsId());
            promRuleCheckDTO.setChannelValue(CommonConstants.SOURCE.SOURCE_OTH);
            promRuleCheckDTO.setShopId(gdsDetailVO.getShopId());
            promRuleCheckDTO.setCalDate(new Date(System.currentTimeMillis()));
            promRuleCheckDTO.setSkuId(gdsDetailVO.getSkuId());
            promRuleCheckDTO.setBasePrice(gdsDetailVO.getRealPrice());
            promRuleCheckDTO.setBuyPrice(gdsDetailVO.getDiscountPrice());
            promRuleCheckDTO.setGdsName(gdsDetailVO.getGdsName());
            promRuleCheckDTO.setShopName(promRuleCheckDTO.getShopName());
//            list = promQueryRSV.listProm(promRuleCheckDTO);
            listDto =promQueryRSV.listPromNew(promRuleCheckDTO);
            if (StringUtil.isNotEmpty(listDto)) {
            	list = listDto.getPromList();
            	seckillDto = listDto.getSeckill();
            	promInfoDTO = seckillDto.getSeckillProm();
            }
        } catch (BusinessException e) {
            map.put("saleList", new ArrayList<PromInfoDTO>());
            map.put("seckill", new SeckillDiscountDTO());
            map.put("promInfoDTO", new PromInfoDTO());
            LogUtil.error(MODULE, "获取促销列表失败", e);
        }
        map.put("saleList", list);
        map.put("seckill", seckillDto);
        map.put("promInfoDTO", promInfoDTO);
        //SeckillDiscountDTO 
//        list.get(0).getSeckill().getSeckillProm();
        return map;
    }
    
    /**
     * 
     * querycatgcodelist:(获取分类路径). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author gxq 
     * @param gdsDetailVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/querycatgcodelist")
    @ResponseBody
    public Map<String, Object> querycatgcodelist(GdsDetailVO gdsDetailVO){
        Map<String, Object> map = new HashMap<String, Object>();
        List<GdsCategoryRespDTO> cateList = new ArrayList<GdsCategoryRespDTO>();
        GdsCategoryReqDTO gdsCategoryReqDTO = new GdsCategoryReqDTO();
        gdsCategoryReqDTO.setCatgCode(gdsDetailVO.getMainCatgs());
        String ifReadOnline = "0";
        try {
            String gdsDigitsBookCatCode = "";
            if(StringUtil.isNotEmpty(SysCfgUtil.fetchSysCfg("GDS_DIGITS_BOOK_CAT_CODE"))){
                gdsDigitsBookCatCode = SysCfgUtil.fetchSysCfg("GDS_DIGITS_BOOK_CAT_CODE").getParaValue();
            }else{
                gdsDigitsBookCatCode = GDS_DIGITS_BOOK_CAT_CODE;
            }
            String gdsEbookCatCode = "";
            if(StringUtil.isNotEmpty(SysCfgUtil.fetchSysCfg("GDS_E_BOOK_CAT_CODE"))){
                gdsEbookCatCode = SysCfgUtil.fetchSysCfg("GDS_E_BOOK_CAT_CODE").getParaValue();
            }else {
                gdsEbookCatCode = GDS_E_BOOK_CAT_CODE;
            }
            cateList = igdsCategoryRSV.queryCategoryTraceUpon(gdsCategoryReqDTO);
            if(cateList == null ){
                cateList = new ArrayList<GdsCategoryRespDTO>();
            }else{
                for(GdsCategoryRespDTO gdsCategoryRespDTO : cateList){
                    if(gdsDigitsBookCatCode.equals(gdsCategoryRespDTO.getCatgCode())){
                        map.put("digitsBook", true);
                        map.put("catgCodeName", "数字教材"); 
                    }else if(gdsEbookCatCode.equals(gdsCategoryRespDTO.getCatgCode())){
                        map.put("ebook", true);
                        map.put("catgCodeName", "电子书");
                    }
                    if(gdsDigitsBookCatCode.equals(gdsCategoryRespDTO.getCatgCode())|| gdsEbookCatCode.equals(gdsCategoryRespDTO.getCatgCode())){
                        ifReadOnline = "1";
                    }
                }
            }
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "获取分类路径失败！", e);
        }
        map.put("ifReadOnline", ifReadOnline);
        map.put("list", cateList);
        return map;
    }

    /**
     * 
     * queryfixedcombine:(获取自由搭配-固定搭配). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * 
     * @author gxq
     * @param model
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = "/queryfixedcombine")
    public String queryFixedCombine(Model model, GdsDetailVO gdsDetailVO) {
        List<GdsPromMatchVO> fixedResultList = new ArrayList<GdsPromMatchVO>();
        try {
            PromRuleCheckDTO promRuleCheckDTO = new PromRuleCheckDTO();
            CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
            CustInfoResDTO custInfoResDTO = null;
            ShopStaffGroupReqDTO shopStaffGroupReqDTO = new ShopStaffGroupReqDTO();
            if(custInfoReqDTO.getStaff().getId()==0){
                promRuleCheckDTO.setCustLevelValue(custInfoReqDTO.getStaff().getStaffLevelCode());
            }else{
                custInfoReqDTO.setId(custInfoReqDTO.getStaff().getId());
                custInfoResDTO = iCustInfoRSV
                        .getCustInfoById(custInfoReqDTO);
                promRuleCheckDTO.setCustLevelValue(custInfoResDTO.getCustLevelCode());
                promRuleCheckDTO.setAreaValue(custInfoResDTO.getProvinceCode());
                promRuleCheckDTO.setStaffId(custInfoResDTO.getId() + "");
                shopStaffGroupReqDTO.setStaffId(custInfoResDTO.getId());
            }
            shopStaffGroupReqDTO.setShopId(gdsDetailVO.getShopId());
            // 客户组id
            String custGroupValue = null;
            if (custInfoResDTO != null && custInfoResDTO.getId() != null
                    && custInfoResDTO.getId() != 0) {
                custGroupValue = iShopManageRSV
                        .queryShopStaffGroup(shopStaffGroupReqDTO);
            }
            // 客户基本信息
            promRuleCheckDTO.setCustGroupValue(custGroupValue);
            
            promRuleCheckDTO.setSiteId(promRuleCheckDTO.getCurrentSiteId());
            
            promRuleCheckDTO.setGdsId(gdsDetailVO.getGdsId());
            promRuleCheckDTO.setSkuId(gdsDetailVO.getSkuId());
            promRuleCheckDTO.setShopId(gdsDetailVO.getShopId());
            promRuleCheckDTO.setChannelValue(CommonConstants.SOURCE.SOURCE_OTH);
            promRuleCheckDTO.setCalDate(new Date(System.currentTimeMillis()));
            // 固定搭配
            promRuleCheckDTO.setMatchType(PromConstants.PromMatchSku.MATCH_TYPE_2);
            List<PromMatchDTO> fixedlist = iPromRSV.queryMatchList(promRuleCheckDTO);
            if (fixedlist != null && fixedlist.size() > 0) {
                getAutoInfo(fixedlist, fixedResultList);
            }
            model.addAttribute("fixedCombineList", fixedResultList);
            model.addAttribute("skuId", gdsDetailVO.getSkuId());
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "获取组合搭配失败！", e);
            model.addAttribute("fixedCombineList", fixedResultList);
            return URL + "/list/gdsdetail-combine-list";
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取组合搭配失败！", e);
            model.addAttribute("fixedCombineList", fixedResultList);
            return URL + "/list/gdsdetail-combine-list";
        }
        return URL + "/list/gdsdetail-combine-list";
    }
    
    private void getAutoInfo(List<PromMatchDTO> list,
            List<GdsPromMatchVO> resultList) {
        GdsPromMatchVO gdsPromMatchVO = null;
        for (PromMatchDTO promMatchDTO : list) {
            gdsPromMatchVO = new GdsPromMatchVO();

            List<PromMatchSkuDTO> promMatchSkuDTOList = promMatchDTO
                    .getPromMatchSkuDTOList();
            List<GdsPromMatchSkuVO> gdsPromMatchSkuVOList = new ArrayList<GdsPromMatchSkuVO>();
            GdsPromMatchSkuVO gdsPromMatchSkuVO = null;
            if (promMatchSkuDTOList != null && promMatchSkuDTOList.size() > 0) {
                for (PromMatchSkuDTO skuDto : promMatchSkuDTOList) {
                    gdsPromMatchSkuVO = new GdsPromMatchSkuVO();
                    GdsSkuInfoRespDTO skuInfoRespDTO = new GdsSkuInfoRespDTO();
                    Map<String, GdsPropRespDTO> allPropMaps = new HashMap<String, GdsPropRespDTO>();
                    ObjectCopyUtil.copyObjValue(skuDto, gdsPromMatchSkuVO, "",
                            false);
                    GdsSkuInfoReqDTO skudto = new GdsSkuInfoReqDTO();
                    SkuQueryOption[] skuQueryOptions1 = new SkuQueryOption[] {SkuQueryOption.BASIC,SkuQueryOption.PROP, SkuQueryOption.MAINPIC, SkuQueryOption.SHOWPRICE};
                    skudto.setSkuQuery(skuQueryOptions1);
                    skudto.setId(skuDto.getSkuId());
                    GdsSkuInfoRespDTO gdsSkuInfoRespDTO = iGdsSkuInfoQueryRSV.querySkuInfoByOptions(skudto);
                    if (gdsSkuInfoRespDTO != null) {
                        skuInfoRespDTO.setGdsName(gdsSkuInfoRespDTO.getGdsName());
                        skuInfoRespDTO.setRealPrice(gdsSkuInfoRespDTO.getRealPrice());
                        
                        // 作者
                        if (gdsSkuInfoRespDTO.getAllPropMaps() != null) {
                            allPropMaps.put("1001", gdsSkuInfoRespDTO.getAllPropMaps().get("1001"));
                        }
                        skuInfoRespDTO.setAllPropMaps(allPropMaps);
                        skuInfoRespDTO.setId(gdsSkuInfoRespDTO.getId());
                        skuInfoRespDTO.setGdsId(gdsSkuInfoRespDTO.getGdsId());
                        skuInfoRespDTO.setMainCatgs(gdsSkuInfoRespDTO.getMainCatgs());
                        skuInfoRespDTO.setSkuProps(gdsSkuInfoRespDTO.getSkuProps());
                        skuInfoRespDTO.setGdsTypeId(gdsSkuInfoRespDTO.getGdsTypeId());
                        skuInfoRespDTO.setMainPic(gdsSkuInfoRespDTO.getMainPic());
                        skuInfoRespDTO.setGdsStatus(gdsSkuInfoRespDTO.getGdsStatus());
                    }
                    gdsPromMatchSkuVO.setSkuInfo(skuInfoRespDTO);
                    gdsPromMatchSkuVOList.add(gdsPromMatchSkuVO);
                }
            }
            gdsPromMatchVO.setGdsPromMatchSkuVOList(gdsPromMatchSkuVOList);
            resultList.add(gdsPromMatchVO);
        }
    }
    
    /**
     * 
     * ifEbookOrDigitsbook:(判断是否是电子书，或这数字教材，或这是其中的一个). <br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author gxq
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = "/ifebookordigitsbook")
    @ResponseBody
    public Map<String, Object> ifEbookOrDigitsbook(Model model, GdsDetailVO gdsDetailVO) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<GdsCategoryRespDTO> cateList = null;
        GdsCategoryReqDTO gdsCategoryReqDTO = new GdsCategoryReqDTO();
        gdsCategoryReqDTO.setCatgCode(gdsDetailVO.getMainCatgs());
        try {
            String gdsDigitsBookCatCode = "";
            if (StringUtil.isNotEmpty(SysCfgUtil.fetchSysCfg("GDS_DIGITS_BOOK_CAT_CODE"))) {
                gdsDigitsBookCatCode = SysCfgUtil.fetchSysCfg("GDS_DIGITS_BOOK_CAT_CODE").getParaValue();
            } else {
                gdsDigitsBookCatCode = GDS_DIGITS_BOOK_CAT_CODE;
            }
            String gdsEbookCatCode = "";
            if (StringUtil.isNotEmpty(SysCfgUtil.fetchSysCfg("GDS_E_BOOK_CAT_CODE"))) {
                gdsEbookCatCode = SysCfgUtil.fetchSysCfg("GDS_E_BOOK_CAT_CODE").getParaValue();
            } else {
                gdsEbookCatCode = GDS_E_BOOK_CAT_CODE;
            }
            cateList = igdsCategoryRSV.queryCategoryTraceUpon(gdsCategoryReqDTO);
            if (cateList != null) {
                for (GdsCategoryRespDTO gdsCategoryRespDTO : cateList) {
                    if (gdsDigitsBookCatCode.equals(gdsCategoryRespDTO.getCatgCode())) {
                        map.put("digitsBook", true);
                        map.put("catgCodeName", "数字教材");
                        break;
                    } else if (gdsEbookCatCode.equals(gdsCategoryRespDTO.getCatgCode())) {
                        map.put("ebook", true);
                        map.put("catgCodeName", "电子书");
                        break;
                    }
                }
            }
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "获取分类路径失败！", e);
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取分类路径失败！", e);
        }
        return map;
    }
    
    /**
     * 
     * wetherBuyed:(判断虚拟商品是否已经被购买过（虚拟商品只能购买一次）). <br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author gxq
     * @param gdsDetailVO
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = "/wetherbuyed")
    @ResponseBody
    public Map<String, Object> wetherBuyed(GdsDetailVO gdsDetailVO) {

        Map<String, Object> map = new HashMap<String, Object>();

        ROrdSubStaffIdxReq rordSubStaffIdxReq = new ROrdSubStaffIdxReq();
        rordSubStaffIdxReq.setStaffId(new BaseInfo().getStaff().getId());
        rordSubStaffIdxReq.setSkuId(gdsDetailVO.getSkuId());

        try {
            List<com.zengshi.ecp.order.dubbo.dto.ROrdSubStaffIdxResp> results = ordSubRSV.queryOrderSubByStaffIdAndSkuid(rordSubStaffIdxReq);
            if (CollectionUtils.isNotEmpty(results)) {
                map.put("buyedFlag", true);
            } else {
                map.put("buyedFlag", false);
            }
            map.put("resultFlag", "ok");
        } catch (BusinessException e) {
            map.put("resultFlag", "fail");
            map.put("buyedFlag", false);
            LogUtil.error(MODULE, "判断虚拟商品是否已经被购买过失败！", e);
        } catch (Exception e) {
            map.put("resultFlag", "fail");
            map.put("buyedFlag", false);
            LogUtil.error(MODULE, "判断虚拟商品是否已经被购买过失败！", e);
        }
        return map;
    }
    
    /**
     * 
     * toMoreDdsEval:(跳转到更多评论详情页面). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param gdsDetailVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/tomoregdseval")
    public String toMoreDdsEval(Model model,GdsEvalVO gdsEvalVO){
        try {
            getGdsEval(model,gdsEvalVO);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "获取评价列表失败！", e);
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取评价列表失败！", e);
        }
        return URL +"/gdsdetail-moregdseval";
    }
    
    /**
     * 
     * queryGdsevalLevel:(获取评价数量噢). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param gdsEvalVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/querygdsevallevel")
    @ResponseBody
    public Model queryGdsevalLevel(Model model,GdsEvalVO gdsEvalVO){
        GdsEvalReqDTO gdsEvalReqDTO = gdsEvalVO.toBaseInfo(GdsEvalReqDTO.class);
        ObjectCopyUtil.copyObjValue(gdsEvalVO, gdsEvalReqDTO, "", false);
        gdsEvalReqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        PageResponseDTO<GdsEvalRespDTO> pageInfo = null;
        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = null;
        try {
            //差评
            gdsEvalReqDTO.setScoreFrom((short)0);
            gdsEvalReqDTO.setScoreTo((short)1);
            pageInfo =iGdsEvalRSV.queryGdsEvalRespDTOPagingForGdsDetail(gdsEvalReqDTO);
            model.addAttribute("badPraise", pageInfo.getCount());
            //中评
            gdsEvalReqDTO.setScoreFrom((short)2);
            gdsEvalReqDTO.setScoreTo((short)4);
            pageInfo =iGdsEvalRSV.queryGdsEvalRespDTOPagingForGdsDetail(gdsEvalReqDTO);
            model.addAttribute("middlePraise", pageInfo.getCount());
            //好评
            gdsEvalReqDTO.setScoreFrom(null);
            gdsEvalReqDTO.setScoreTo(null);
            gdsEvalReqDTO.setScore((short)5);
            pageInfo =iGdsEvalRSV.queryGdsEvalRespDTOPagingForGdsDetail(gdsEvalReqDTO);
            model.addAttribute("highPraise", pageInfo.getCount());
        } catch (BusinessException e){
            LogUtil.error(MODULE, "获取评价数量失败！", e);
        }
        return model;
    }
    /**
     * 
     * queryGdsCollect:(判断该商品是否已经被收藏). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param gdsDetailVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/querygdscollect")
    public Model queryGdsCollect (Model model,GdsDetailVO gdsDetailVO){
        // 获取用户对商品的收藏信息
        GdsCollectReqDTO collectReqDTO = new GdsCollectReqDTO();
        collectReqDTO.setGdsId(gdsDetailVO.getGdsId());
        collectReqDTO.setStaffId(collectReqDTO.getStaff().getId());
        collectReqDTO.setPageNo(0);
        collectReqDTO.setPageSize(1);
        PageResponseDTO<GdsCollectRespDTO> pageResponseDTO = gdsCollectRSV.queryGdsCollectRespDTOPagingByStaff(collectReqDTO);
        if ( pageResponseDTO.getCount() > 0) {
            model.addAttribute("collectId", pageResponseDTO.getResult().get(0).getId());
            model.addAttribute("ifHavFav", "1");
        } else {
            model.addAttribute("ifHavFav", "0");
        }
        return model;
    }
    
    /**
     * 
     * queryBookOtherType:(判断该商品是否有其他类型书籍). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param gdsDetailVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/queryBookOtherType")
    public Model queryBookOtherType (Model model,GdsDetailVO gdsDetailVO){
    	
    	// 纸质书编码
		String paperBookCateCode = SysCfgUtil.fetchSysCfg("GDS_PAPER_BOOK_CAT_CODE").getParaValue();
		if (StringUtil.isBlank(paperBookCateCode)) {
			paperBookCateCode = GDS_PAPER_BOOK_CAT_CODE;
		}
		// 电子书编码
		String eBookCateCode = SysCfgUtil.fetchSysCfg("GDS_E_BOOK_CAT_CODE").getParaValue();
		if (StringUtil.isBlank(eBookCateCode)) {
			eBookCateCode = GDS_E_BOOK_CAT_CODE;
		}
		// 数字教材
		String gdsDigitsBookCatCode = SysCfgUtil.fetchSysCfg("GDS_DIGITS_BOOK_CAT_CODE").getParaValue();
		if (StringUtil.isBlank(gdsDigitsBookCatCode)) {
			gdsDigitsBookCatCode = GDS_DIGITS_BOOK_CAT_CODE;
		}
    	//1:纸质书  2:电子书 3:数字教材
        String bookOtherType = "";
        String bookGds2SkuId = "";
        // 查询单品信息
        GdsSkuInfoReqDTO skuInfoReqDTO = new GdsSkuInfoReqDTO();
        skuInfoReqDTO.setGdsId(gdsDetailVO.getGdsId());
        skuInfoReqDTO.setId(gdsDetailVO.getSkuId());
        SkuQueryOption[] skuQuery = new SkuQueryOption[] { SkuQueryOption.BASIC,
                SkuQueryOption.PROP };
        skuInfoReqDTO.setSkuQuery(skuQuery);
        GdsSkuInfoRespDTO gdsSkuInfoRespDTO = iGdsSkuInfoQueryRSV
                .querySkuInfoByOptions(skuInfoReqDTO);
        
       //获取当前商品本身类型
  		GdsCategoryReqDTO dto = new GdsCategoryReqDTO();
  		dto.setCatgCode(gdsSkuInfoRespDTO.getMainCatgs());
  		
  		List<GdsCategoryRespDTO> list = gdsCategoryRSV.queryCategoryTraceUpon(dto);
  		//获取主分类是否为(纸质书，电子书，数字教材)
  		String itselfType = "";
  		if (list != null && list.size() > 0) {
  			for (GdsCategoryRespDTO categoryRespDTO : list) {
  				if (paperBookCateCode.equals(categoryRespDTO.getCatgCode())) {
  					itselfType = paperBookCateCode;
  				}
  			}
  		}
  		
  		if(StringUtil.isNotBlank(itselfType)){
  			//标准ISBN 获取是否有 电子书，纸质书，数字材料
  			if (gdsSkuInfoRespDTO.getAllPropMaps() != null) {
  				Map<String, GdsPropRespDTO> propMaps = gdsSkuInfoRespDTO.getAllPropMaps();
  				GdsPropRespDTO  standIsbnProp = propMaps.get(String.valueOf(PROP_ID_1032));
  				if(null != standIsbnProp && CollectionUtils.isNotEmpty(standIsbnProp.getValues())){
  					String standIsbn = standIsbnProp.getValues().get(0).getPropValue();
  					if(StringUtils.isNotEmpty(standIsbn)){
  						GdsSku2PropPropIdxReqDTO reqDTO = new GdsSku2PropPropIdxReqDTO();
  						reqDTO.setPropId(PROP_ID_1032);
  						reqDTO.setPropValue(standIsbn);
  						reqDTO.setPageSize(PAGE_SIZE_10);
  						
  						// 只取当前商品的
  						PageResponseDTO<GdsSkuInfoRespDTO> rspDto = iGdsSkuInfoQueryRSV.queryGdsSkuInfoPaging(reqDTO);
  						
  						if (rspDto != null) {
  							if (rspDto.getResult() != null && rspDto.getResult().size() > 0) {
  								for (GdsSkuInfoRespDTO skuInfoRespDTO : rspDto.getResult()) {
  									if (!GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES.equals(skuInfoRespDTO.getGdsStatus())) {
  										continue;
  									}
  									if(itselfType != ""){
  										int index = skuInfoRespDTO.getPlatCatgs().indexOf(itselfType);
  										if(index == -1 ){
  											if(skuInfoRespDTO.getPlatCatgs().indexOf(eBookCateCode)!=-1){
  												bookOtherType = "2";
  												bookGds2SkuId = skuInfoRespDTO.getGdsId()+"-"+skuInfoRespDTO.getId();
  											}else if(skuInfoRespDTO.getPlatCatgs().indexOf(gdsDigitsBookCatCode)!=-1){
  												bookOtherType = "3";
  												bookGds2SkuId = skuInfoRespDTO.getGdsId()+"-"+skuInfoRespDTO.getId();
  											}
  										}
  									}
  								}
  							}
  						}
  					}
  				}
  			}
  		}
        model.addAttribute("bookOtherType", bookOtherType);
        model.addAttribute("bookGds2SkuId", bookGds2SkuId);
        
        return model;
    }
    private String maskStaffCode(String source){
        String s = "";
        if(StringUtil.isNotBlank(source)){
            if(1 == source.length()){
                s = source+MASK_STRING;
            }else{
                StringBuilder builder = new StringBuilder();
                if(2 == source.length()){
                    builder.append(source.substring(0, 1));
                    builder.append(MASK_STRING);
                }else{
                    builder.append(source);
                    builder.replace(1, source.length() - 1, MASK_STRING);
                }
                s = builder.toString();
            }
            
        }
        return s;
    }
    
    
    private String getHtmlUrl(String vfsId) {
        if (StringUtil.isBlank(vfsId)) {
            return "";
        }
        return ImageUtil.getStaticDocUrl(vfsId, "html");
    }
    
    /**
	 * 
	 * querygdsbyisbn:(根据isbn 查询除了当前商品以外是否还有纸质书或者电子书). <br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author lincx
	 * @param model
	 * @return
	 * @since JDK 1.6
	 */
    private List<GdsCatgCodeVO> querygdsbyisbn(GdsParseISBNVO gdsParseISBNVO) {

		GdsSku2PropPropIdxReqDTO reqDTO = new GdsSku2PropPropIdxReqDTO();
		List<GdsCatgCodeVO> resultList = new ArrayList<GdsCatgCodeVO>();
		try {
			// 纸质书编码
			String paperBookCateCode = SysCfgUtil.fetchSysCfg("GDS_PAPER_BOOK_CAT_CODE").getParaValue();
			if (StringUtil.isBlank(paperBookCateCode)) {
				paperBookCateCode = GDS_PAPER_BOOK_CAT_CODE;
			}
			// 电子书编码
			String eBookCateCode = SysCfgUtil.fetchSysCfg("GDS_E_BOOK_CAT_CODE").getParaValue();
			if (StringUtil.isBlank(eBookCateCode)) {
				eBookCateCode = GDS_E_BOOK_CAT_CODE;
			}
			// 数字教材
			String gdsDigitsBookCatCode = SysCfgUtil.fetchSysCfg("GDS_DIGITS_BOOK_CAT_CODE").getParaValue();
			reqDTO.setPropId(PROP_ID_1032);
			if (StringUtil.isNotBlank(gdsParseISBNVO.getBiazhunisbn())) {
				reqDTO.setPropValue(gdsParseISBNVO.getBiazhunisbn());
			}
			reqDTO.setPageSize(PAGE_SIZE_10);
			// 只取当前商品的
			Map<String, GdsCatgCodeVO> map = new HashMap<String, GdsCatgCodeVO>();
			PageResponseDTO<GdsSkuInfoRespDTO> rspDto = iGdsSkuInfoQueryRSV.queryGdsSkuInfoPaging(reqDTO);
			if (rspDto != null) {
				if (rspDto.getResult() != null && rspDto.getResult().size() > 0) {
					GdsCatgCodeVO gdsCatgCodeVO = null;
					for (GdsSkuInfoRespDTO gdsSkuInfoRespDTO : rspDto.getResult()) {
						if (!GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES.equals(gdsSkuInfoRespDTO.getGdsStatus())) {
							continue;
						}
						// 初始化回传到前店的vo,一个标准的isbn可能对应多个商品
						gdsCatgCodeVO = new GdsCatgCodeVO();

						GdsCategoryReqDTO dto = new GdsCategoryReqDTO();
						dto.setCatgCode(gdsSkuInfoRespDTO.getMainCatgs());

						List<GdsCategoryRespDTO> list = igdsCategoryRSV.queryCategoryTraceUpon(dto);
						if (list != null && list.size() > 0) {
							for (GdsCategoryRespDTO gdsCategoryRespDTO : list) {
								if (gdsSkuInfoRespDTO.getMainCatgs().equals(gdsCategoryRespDTO.getCatgCode())) {
									if (gdsParseISBNVO.getCatgCode().equals(gdsCategoryRespDTO.getCatgCode())
											&& gdsParseISBNVO.getSkuId().equals(gdsSkuInfoRespDTO.getId())) {
										map.put("checked", gdsCatgCodeVO);
										gdsCatgCodeVO.setChecked("1");
										break;
									} else {
										gdsCatgCodeVO.setChecked("0");
									}
								}
							}
							for (GdsCategoryRespDTO gdsCategoryRespDTO1 : list) {
								if (paperBookCateCode.equals(gdsCategoryRespDTO1.getCatgCode())) {
									// 纸质书
									matchMapCatgCode(map, paperBookCateCode, gdsCategoryRespDTO1, gdsCatgCodeVO,
											gdsSkuInfoRespDTO);
									break;
								} else if (eBookCateCode.equals(gdsCategoryRespDTO1.getCatgCode())) {
									// 电子书
									matchMapCatgCode(map, eBookCateCode, gdsCategoryRespDTO1, gdsCatgCodeVO,
											gdsSkuInfoRespDTO);
									break;
								} else if (gdsDigitsBookCatCode.equals(gdsCategoryRespDTO1.getCatgCode())) {
									// 数字教材
									matchMapCatgCode(map, gdsDigitsBookCatCode, gdsCategoryRespDTO1, gdsCatgCodeVO,
											gdsSkuInfoRespDTO);
									break;
								}
							}
						}
					}
					int hasPaper = 0;
					if (map.containsKey("checked")) {
						Iterator<String> it = map.keySet().iterator();
						while (it.hasNext()) {
							String key = (String) it.next();
							GdsCatgCodeVO value = map.get(key);
							if ("1".equals(value.getChecked()) && value.getSkuId() != null && !"checked".equals(key)) {
								if (paperBookCateCode.equals(key)) {
									hasPaper++;
								}
								resultList.add(value);
							}
						}
						if (hasPaper == 0) {
							// 如果当前选中不是纸质书，则取纸质书
							if (map.containsKey(paperBookCateCode)) {
								resultList.add(map.get(paperBookCateCode));
							}
						} else {
							Iterator<String> its = map.keySet().iterator();
							while (its.hasNext()) {
								String key = (String) its.next();
								GdsCatgCodeVO value = map.get(key);
								if (resultList.size() <= 1) {
									if (!"1".equals(value.getChecked())) {
										resultList.add(value);
									}
								} else {
									break;
								}
							}
						}

					}
				}
			}
//			model.addAttribute("list", resultList);
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "根据ISBN号获取商品信息失败！", e);
//			model.addAttribute("list", resultList);
		} catch (Exception e) {
			LogUtil.error(MODULE, "根据ISBN号获取商品信息失败！", e);
//			model.addAttribute("list", resultList);
		}
		return resultList;
	
    }
    
    private void matchMapCatgCode(Map<String, GdsCatgCodeVO> map, String catgCode,
			GdsCategoryRespDTO gdsCategoryRespDTO, GdsCatgCodeVO gdsCatgCodeVO, GdsSkuInfoRespDTO gdsSkuInfoRespDTO) {
		if ("1".equals(gdsCatgCodeVO.getChecked())) {
			gdsCatgCodeVO.setCatgName(gdsCategoryRespDTO.getCatgName());
			gdsCatgCodeVO.setCatgCode(gdsSkuInfoRespDTO.getMainCatgs());
			gdsCatgCodeVO.setGdsId(gdsSkuInfoRespDTO.getGdsId());
			gdsCatgCodeVO.setSkuId(gdsSkuInfoRespDTO.getId());
			map.put(catgCode, gdsCatgCodeVO);
		} else {
			if (!map.containsKey(catgCode)) {
				gdsCatgCodeVO.setCatgName(gdsCategoryRespDTO.getCatgName());
				gdsCatgCodeVO.setCatgCode(gdsSkuInfoRespDTO.getMainCatgs());
				gdsCatgCodeVO.setGdsId(gdsSkuInfoRespDTO.getGdsId());
				gdsCatgCodeVO.setSkuId(gdsSkuInfoRespDTO.getId());
				map.put(catgCode, gdsCatgCodeVO);
			}
		}
	}
    
    private String getOtherGdsPrice(long gdsId,long skuId) {
    	String otherBP="";
    	GdsInfoReqDTO dto = new GdsInfoReqDTO();
    	dto.setId(gdsId);
    	dto.setSkuId(skuId);
        GdsQueryOption[] gdsQueryOptions = new GdsQueryOption[] { GdsQueryOption.BASIC,GdsQueryOption.MAINPIC, GdsQueryOption.GDSTYPE};
        SkuQueryOption[] skuQueryOptions = new SkuQueryOption[] { SkuQueryOption.BASIC, SkuQueryOption.PROP,
                SkuQueryOption.SHOWSTOCK, SkuQueryOption.CAlDISCOUNT };
        dto.setGdsQueryOptions(gdsQueryOptions);
        dto.setSkuQuerys(skuQueryOptions);
        GdsInfoDetailRespDTO resultDto = null;
        try {
            resultDto = iGdsInfoQueryRSV.queryGdsInfoDetail(dto);
            if (resultDto != null && resultDto.getSkuInfo() != null) {

            } else {
                resultDto = new GdsInfoDetailRespDTO();
                GdsSkuInfoRespDTO gdsSkuInfoRespDTO = new GdsSkuInfoRespDTO();
                gdsSkuInfoRespDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_DELETE);
                resultDto.setSkuInfo(gdsSkuInfoRespDTO);
            }
        } catch (BusinessException e) {
            if (resultDto == null || GdsErrorConstants.GdsInfo.ERROR_GOODS_GDSINFO_210005.equals(e.getErrorCode())) {
                resultDto = new GdsInfoDetailRespDTO();
                GdsSkuInfoRespDTO gdsSkuInfoRespDTO = new GdsSkuInfoRespDTO();
                gdsSkuInfoRespDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_DELETE);
                resultDto.setSkuInfo(gdsSkuInfoRespDTO);
            }
            LogUtil.error(MODULE, "无法获取商品详情信息！", e);
        }
        if(resultDto.getSkuInfo().getAppSpecPrice()==null || resultDto.getSkuInfo().getAppSpecPrice()==0){
        	GdsPromListDTO listDto ;
        	try {
        		PromRuleCheckDTO promRuleCheckDTO = new PromRuleCheckDTO();
        		CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
        		promRuleCheckDTO.setSiteId(promRuleCheckDTO.getCurrentSiteId());
        		promRuleCheckDTO.setStaffId(custInfoReqDTO.getStaff().getId() + "");
        		promRuleCheckDTO.setGdsId(gdsId);
        		promRuleCheckDTO.setChannelValue(CommonConstants.SOURCE.SOURCE_OTH);
        		promRuleCheckDTO.setShopId(resultDto.getSkuInfo().getShopId());
        		promRuleCheckDTO.setCalDate(new Date(System.currentTimeMillis()));
        		promRuleCheckDTO.setSkuId(skuId);
        		promRuleCheckDTO.setBasePrice(resultDto.getSkuInfo().getRealPrice());
        		promRuleCheckDTO.setBuyPrice(resultDto.getSkuInfo().getDiscountPrice());
        		promRuleCheckDTO.setGdsName(resultDto.getSkuInfo().getGdsName());
        		promRuleCheckDTO.setShopName(promRuleCheckDTO.getShopName());
        		listDto =promQueryRSV.listPromNew(promRuleCheckDTO);
        		if (StringUtil.isNotEmpty(listDto)) {
        			otherBP=listDto.getPromList().get(0).getPromSkuPriceRespDTO().getDiscountFinalPrice().toString();
        		}else{
        			otherBP = resultDto.getSkuInfo().getDiscountPrice().toString();
        		}
        	} catch (BusinessException e) {
        		LogUtil.error(MODULE, "获取对应的电子书或者纸质书促销列表失败", e);
        	}
        }else{
        	otherBP=resultDto.getSkuInfo().getAppSpecPrice().toString();
        }
        return otherBP;
	}
    
    private GdsCategoryCompareRespDTO comapre(String sourceCode, String targetCode) {
        GdsCategoryCompareReqDTO compareReqDTO = new GdsCategoryCompareReqDTO();
        compareReqDTO.setSourceCode(sourceCode);
        compareReqDTO.setTargetCode(targetCode);
        return gdsCategoryRSV.executeCompare(compareReqDTO);
    }
    
    /**
     * 
     * :(获取微信分享相关参数). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * 
     * @author gxq
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = "/getSignature")
    public Map<String,String> getSignature(Model model,@RequestParam("url") String url,HttpServletRequest request) {
    	//获取微信分享相关参数
        //String urls = "http://192.168.1.118:8080/ecp-web-mobile/gdsdetail/21039-30062";
        // String urls = request.getScheme() +"://" + request.getServerName()  
         //	                        + ":" +request.getServerPort() 
        //	                        + request.getServletPath();
         Map<String,String> signatureMap = new HashMap<String,String>();
         try {
 			signatureMap = WeixinUtil.getAllBase(url);
 			String appid = WeixinUtil.getAppid();
 			signatureMap.put("appid", appid);
 		} catch (NoSuchAlgorithmException e) {
 			LogUtil.error(MODULE, "处理分享链接失败", e);
 		}
         return signatureMap;
    }
    
    /**
     * 
     * getAnalysisUrl:(获取行为分析地址). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author lincx
     * @return
     * @since JDK 1.6
     */
    private static String getAnalysisUrl() {
        LogUtil.info(MODULE, "获取行为分析地址开始");
        BaseSysCfgRespDTO XwAnaUrlDto = null;
        try {
            XwAnaUrlDto = SysCfgUtil.fetchSysCfg("XW_ANAL_SYS_URL");
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取用户行为分析地址结束：异常", e);
            return null;
        }

        if (XwAnaUrlDto == null || StringUtil.isBlank(XwAnaUrlDto.getParaValue())) {
            LogUtil.error(MODULE, "用户行为分析地址结束：未配置：常量库字段为XW_ANAL_SYS_URL");
            return null;
        }
        String url = XwAnaUrlDto.getParaValue();
        LogUtil.info(MODULE, "行为分析地址结束：成功 地址为" + url);

        return url;
    }
    
    /**
     * 
     * doRequest:(使用httpclient执行http请求). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author lincx
     * @param formparams
     * @return
     * @since JDK 1.6
     */
    public static String doRequest(String url, List<BasicNameValuePair> formparams) {
        LogUtil.info(MODULE, "httpclient请求开始");
        // 1 入参验证
        if (StringUtil.isBlank(url)) {
            LogUtil.error(MODULE, "httpclient请求结束：地址为空");
            return null;
        }
        if (CollectionUtils.isEmpty(formparams)) {
            formparams = new ArrayList<BasicNameValuePair>();
        }

        CloseableHttpClient httpclient = null;
        HttpPost httppost = null;
        CloseableHttpResponse response = null;
        String result = null;
        // 处理请求
        try {
            // 创建默认的httpClient实例
            httpclient = HttpClients.createDefault();
            // 创建httppost
            httppost = new HttpPost(url);
            // 设置请求实体
            httppost.setEntity(new UrlEncodedFormEntity(formparams, "UTF-8"));
            // 执行请求
            response = httpclient.execute(httppost);
            //处理结果
            if(response!=null && response.getStatusLine() !=null){//请求成功
               LogUtil.info(MODULE, "httpclient请求返回状态码："
                       + response.getStatusLine().getStatusCode()); 
               HttpEntity entity = response.getEntity();  
               result = EntityUtils.toString(entity);
            }
           
        } catch (ClientProtocolException e) {
            LogUtil.error(MODULE, "远程服务协议异常", e);
        } catch (IOException e) {
            LogUtil.error(MODULE, "远程服务连接异常", e);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, e.getMessage(), e);
        } catch (Exception e) {
            LogUtil.error(MODULE, "调用远程服务未知异常", e);
        } finally {
            // 关闭连接,释放资源
            try {
                if(response != null){
                    try { 
                        response.close();
                    }
                    catch (Exception e) {  
                        e.printStackTrace();
                    } 
                }
                if(httppost != null){
                    try { 
                        httppost.releaseConnection();
                    }
                    catch (Exception e) {  
                        e.printStackTrace();
                    } 
                }
                if(httppost!=null){
                    try { 
                        httppost.abort();
                    }
                    catch (Exception e) {  
                        e.printStackTrace();
                    } 
                }
                if(httpclient !=null){
                    try { 
                        httpclient.close(); 
                    }
                    catch (Exception e) {  
                        e.printStackTrace();
                    } 
                }  
            } catch (Exception e) {
                LogUtil.error(MODULE, "关闭httpclient连接异常", e);
            }
        }
        LogUtil.info(MODULE, "httpclient请求结束");
        return result;
    }
    
    public Long getDiscountPrice(long gdsId, long skuId) {
    	Long DiscountPrice = null ;
		GdsInfoReqDTO dto = new GdsInfoReqDTO();
		dto.setId(gdsId);
		dto.setSkuId(skuId);
		GdsQueryOption[] gdsQueryOptions = new GdsQueryOption[] { GdsQueryOption.BASIC, GdsQueryOption.GDSTYPE};
		SkuQueryOption[] skuQueryOptions = new SkuQueryOption[] { SkuQueryOption.BASIC, SkuQueryOption.PROP,SkuQueryOption.MEDIA,
				SkuQueryOption.SHOWSTOCK, SkuQueryOption.CAlDISCOUNT};
		dto.setGdsQueryOptions(gdsQueryOptions);
		dto.setSkuQuerys(skuQueryOptions);
		GdsInfoDetailRespDTO resultDto = null;
		try {
			resultDto = iGdsInfoQueryRSV.queryGdsInfoDetail(dto);
			if (resultDto != null && resultDto.getSkuInfo() != null) {
				// 发送消息

			} else {
				resultDto = new GdsInfoDetailRespDTO();
				GdsSkuInfoRespDTO gdsSkuInfoRespDTO = new GdsSkuInfoRespDTO();
				gdsSkuInfoRespDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_DELETE);
				resultDto.setSkuInfo(gdsSkuInfoRespDTO);
			}
		} catch (BusinessException e) {
			if (resultDto == null || GdsErrorConstants.GdsInfo.ERROR_GOODS_GDSINFO_210005.equals(e.getErrorCode())) {
				resultDto = new GdsInfoDetailRespDTO();
				GdsSkuInfoRespDTO gdsSkuInfoRespDTO = new GdsSkuInfoRespDTO();
				gdsSkuInfoRespDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_DELETE);
				resultDto.setSkuInfo(gdsSkuInfoRespDTO);
			}
			LogUtil.error(MODULE, "无法获取商品折后价！", e);
		}
		if (StringUtil.isNotEmpty(resultDto)) {
			DiscountPrice = resultDto.getSkuInfo().getDiscountPrice();
		}
		return DiscountPrice;
	}
    
    public Long getSkuId(long gdsId) {
    	Long skuId = null ;
		GdsInfoReqDTO dto = new GdsInfoReqDTO();
		dto.setId(gdsId);
		GdsQueryOption[] gdsQueryOptions = new GdsQueryOption[] { GdsQueryOption.BASIC, GdsQueryOption.GDSTYPE};
		SkuQueryOption[] skuQueryOptions = new SkuQueryOption[] { SkuQueryOption.BASIC, SkuQueryOption.PROP,SkuQueryOption.MEDIA,
				SkuQueryOption.SHOWSTOCK, SkuQueryOption.CAlDISCOUNT};
		dto.setGdsQueryOptions(gdsQueryOptions);
		dto.setSkuQuerys(skuQueryOptions);
		GdsInfoDetailRespDTO resultDto = null;
		try {
			resultDto = iGdsInfoQueryRSV.queryGdsInfoDetail(dto);
			if (resultDto != null && resultDto.getSkuInfo() != null) {
				// 发送消息

			} else {
				resultDto = new GdsInfoDetailRespDTO();
				GdsSkuInfoRespDTO gdsSkuInfoRespDTO = new GdsSkuInfoRespDTO();
				gdsSkuInfoRespDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_DELETE);
				resultDto.setSkuInfo(gdsSkuInfoRespDTO);
			}
		} catch (BusinessException e) {
			if (resultDto == null || GdsErrorConstants.GdsInfo.ERROR_GOODS_GDSINFO_210005.equals(e.getErrorCode())) {
				resultDto = new GdsInfoDetailRespDTO();
				GdsSkuInfoRespDTO gdsSkuInfoRespDTO = new GdsSkuInfoRespDTO();
				gdsSkuInfoRespDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_DELETE);
				resultDto.setSkuInfo(gdsSkuInfoRespDTO);
			}
			LogUtil.error(MODULE, "无法获取商品折后价！", e);
		}
		if (StringUtil.isNotEmpty(resultDto)) {
			skuId = resultDto.getSkuInfo().getId();
		}
		return skuId;
	}
    public boolean isNumeric(String str){
    	boolean isN=false;
    	if(StringUtil.isBlank(str)){
    		Pattern pattern = Pattern.compile("[0-9]*");
    		Matcher isNum = pattern.matcher(str);
    		isN=isNum.matches();
    	}
        return isN;
    }
    
    /**
     * 
     * sendRecentlyBrowMsg:(发送消息). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * 
     * @author gxq
     * @return
     * @since JDK 1.6
     */
    public void sendRecentlyBrowMsg(GdsInfoDetailRespDTO resultDto) {
        GdsBrowseHisReqDTO gdsBrowseHisReqDTO = new GdsBrowseHisReqDTO();
        try {
            // GDS_BROWSE_HIS_用户ID_单品ID
            String key = KEY + "_" + gdsBrowseHisReqDTO.getStaff().getId() + "_" + resultDto.getSkuInfo().getId();
            if (CacheUtil.getItem(key) == null) {
                // 不存在则发消息
                gdsBrowseHisReqDTO.setGdsId(resultDto.getSkuInfo().getGdsId());
                gdsBrowseHisReqDTO.setSkuId(resultDto.getSkuInfo().getId());
                gdsBrowseHisReqDTO.setShopId(resultDto.getShopId());
                gdsBrowseHisReqDTO.setStaffId(gdsBrowseHisReqDTO.getStaff().getId());
                gdsBrowseHisReqDTO.setBrowsePrice(resultDto.getSkuInfo().getRealPrice());
                GdsMessageUtil.sendGdsBrowseMessage(gdsBrowseHisReqDTO);
                // 缓存到redis
                CacheUtil.addItem(key, key, 1 * 60 * 60 * 6);
            }
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "发送消息失败！", e);
        } catch (Exception e) {
            LogUtil.error(MODULE, "发送消息失败！", e);
        }
    }

    
}

