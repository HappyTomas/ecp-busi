package com.zengshi.ecp.busi.goods.gdsdetail.controller;

import java.io.IOException;
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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
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
import org.springframework.web.servlet.ModelAndView;

/*import com.zengshi.ecp.aip.dubbo.dto.AipLMNetValueAddedDetail;
import com.zengshi.ecp.aip.dubbo.dto.AipLMNetValueAddedRequest;
import com.zengshi.ecp.aip.dubbo.dto.AipLMNetValueAddedResponse;
import com.zengshi.ecp.aip.dubbo.dto.AipZYAuthRequest;
import com.zengshi.ecp.aip.dubbo.dto.AipZYAuthResponse;
import com.zengshi.ecp.aip.dubbo.interfaces.IAipLMNetValueAddedRSV;
import com.zengshi.ecp.aip.dubbo.interfaces.IAipZYAuthRSV;*/
import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.velocity.AiToolUtil;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.goods.gdsdetail.utils.GdsDetailUtil;
import com.zengshi.ecp.busi.goods.gdsdetail.vo.GdsCatgCodeVO;
import com.zengshi.ecp.busi.goods.gdsdetail.vo.GdsCommonAuthorVO;
import com.zengshi.ecp.busi.goods.gdsdetail.vo.GdsDetailVO;
import com.zengshi.ecp.busi.goods.gdsdetail.vo.GdsEvalVO;
import com.zengshi.ecp.busi.goods.gdsdetail.vo.GdsParseISBNVO;
import com.zengshi.ecp.busi.goods.gdsdetail.vo.GdsPdfVO;
import com.zengshi.ecp.busi.goods.gdsdetail.vo.GdsPromMatchSkuVO;
import com.zengshi.ecp.busi.goods.gdsdetail.vo.GdsPromMatchVO;
import com.zengshi.ecp.busi.goods.gdsdetail.vo.GdsRecentlyVO;
import com.zengshi.ecp.busi.goods.gdsdetail.vo.GdsSkuMediaVO;
import com.zengshi.ecp.busi.search.SearchUtil;
import com.zengshi.ecp.busi.search.vo.AddToCartButtonVO;
import com.zengshi.ecp.busi.search.vo.GoodSearchPageReqVO;
import com.zengshi.ecp.busi.search.vo.GoodSearchResult;
import com.zengshi.ecp.busi.shop.shopIndex.vo.ShopInfoVO;
import com.zengshi.ecp.busi.shop.shopSearch.controller.ShopSearchUtil;
import com.zengshi.ecp.busi.shop.shopSearch.vo.ShopSearchPageReqVO;
import com.zengshi.ecp.busi.shop.shopSearch.vo.ShopSearchResult;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsBrowseHisReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsBrowseHisRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReplyRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueRespDTO;
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
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsBrowseHisRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatgCustDiscRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCollectRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalReplyRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInterfaceGdsRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsPlatRecomRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsTypeRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsMessageUtil;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.order.dubbo.dto.RGoodSaleRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdSubStaffIdxReq;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdSubRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IReportGoodPayedRSV;
import com.zengshi.ecp.prom.dubbo.dto.GdsPromListDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromPostDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromPostRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.SeckillDiscountDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromShipRSV;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.front.util.StaffLocaleUtil;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopCollectReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopStaffGroupReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopCollectRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopManageRSV;
import com.zengshi.ecp.system.util.CookieUtil;
import com.zengshi.ecp.system.util.Escape;
import com.zengshi.ecp.system.util.GdsParamsTool;
import com.zengshi.ecp.system.util.ParamsTool;
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
 * Project Name:ecp-web-manage <br>
 * Description: 商品详情<br>
 * Date:2015年9月18日上午10:51:56 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author gxq
 * @version
 * @since JDK 1.6
 */
@RequestMapping(value = "/gdsdetail")
@Controller
public class GdsDetailController extends EcpBaseController {
	private static String URL = "/goods/gdsdetail";

	private static String MODULE = GdsDetailController.class.getName();

	private static int PAGE_SIZE = 5;

	private static int PAGE_SIZE_10 = 10;

	private static String KEY = "GDS_BROWSE_HIS";

	private static Long PROP_ID_1032 = 1032l;

	private static String WEB = "1";

	private static String GDS_E_BOOK_CAT_CODE = "1200";

	private static String GDS_PAPER_BOOK_CAT_CODE = "1115";

	private static String GDS_DIGITS_BOOK_CAT_CODE = "1201";

	private final static String ISLOGIN_VO_ATTR = "isLogin";

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
	private IGdsBrowseHisRSV iGdsBrowseHisRSV;

	@Resource
	private IGdsPlatRecomRSV iGdsPlatRecomRSV;

	@Resource
	private IGdsEvalReplyRSV iGdsEvalReplyRSV;

	@Resource
	private IGdsCatgCustDiscRSV gdsCatgCustDiscRSV;

	@Resource
	private IGdsTypeRSV gdsTypeRSV;
	@Resource
	private IGdsInfoExternalRSV gdsInfoExternalRSV;

	/*
	 * @Resource private IAipLMNetValueAddedRSV iAipLMNetValueAddedRSV;
	 * 
	 * @Resource private IAipZYAuthRSV iAipZYAuthRSV;
	 */

	@Resource
	private IGdsInterfaceGdsRSV iGdsInterfaceGdsRSV;

	@Resource
	private IPromQueryRSV promQueryRSV;

	@Resource
	private IShopCollectRSV iShopCollectRSV;

	@Resource
	private IReportGoodPayedRSV iReportGoodPayedRSV;

	@Resource
	private IOrdSubRSV ordSubRSV;

	@Resource
	private IPromShipRSV promShipRSV;

	private final static String SUFFIX_IMAGE_SIZE = "_100x100!";
	private static String mobileGateWayHeaders[]=new String[]{
    	    "ZXWAP",//中兴提供的wap网关的via信息，例如：Via=ZXWAP GateWayZTE Technologies，
    	    "chinamobile.com",//中国移动的诺基亚wap网关，例如：Via=WTP/1.1 GDSZ-PB-GW003-WAP07.gd.chinamobile.com (Nokia WAP Gateway 4.1 CD1/ECD13_D/4.1.04)
    	    "monternet.com",//移动梦网的网关，例如：Via=WTP/1.1 BJBJ-PS-WAP1-GW08.bj1.monternet.com. (Nokia WAP Gateway 4.1 CD1/ECD13_E/4.1.05)
    	    "infoX",//华为提供的wap网关，例如：Via=HTTP/1.1 GDGZ-PS-GW011-WAP2 (infoX-WISG Huawei Technologies)，或Via=infoX WAP Gateway V300R001 Huawei Technologies
    	    "XMS 724Solutions HTG",//国外电信运营商的wap网关，不知道是哪一家
    	    "wap.lizongbo.com",//自己测试时模拟的头信息
    	    "Bytemobile",//貌似是一个给移动互联网提供解决方案提高网络运行效率的，例如：Via=1.1 Bytemobile OSN WebProxy/5.1
    	    };
    	    /**电脑上的IE或Firefox浏览器等的User-Agent关键词*/
    	    private static String[] pcHeaders=new String[]{
    	    "Windows 98",
    	    "Windows ME",
    	    "Windows 2000",
    	    "Windows XP",
    	    "Windows NT",
    	    "Ubuntu"
    	    };
    	    /**手机浏览器的User-Agent里的关键词*/
    	    private static String[] mobileUserAgents=new String[]{
    	    "Nokia",//诺基亚，有山寨机也写这个的，总还算是手机，Mozilla/5.0 (Nokia5800 XpressMusic)UC AppleWebkit(like Gecko) Safari/530
    	    "SAMSUNG",//三星手机 SAMSUNG-GT-B7722/1.0+SHP/VPP/R5+Dolfin/1.5+Nextreaming+SMM-MMS/1.2.0+profile/MIDP-2.1+configuration/CLDC-1.1
    	    "MIDP-2",//j2me2.0，Mozilla/5.0 (SymbianOS/9.3; U; Series60/3.2 NokiaE75-1 /110.48.125 Profile/MIDP-2.1 Configuration/CLDC-1.1 ) AppleWebKit/413 (KHTML like Gecko) Safari/413
    	    "CLDC1.1",//M600/MIDP2.0/CLDC1.1/Screen-240X320
    	    "SymbianOS",//塞班系统的，
    	    "MAUI",//MTK山寨机默认ua
    	    "UNTRUSTED/1.0",//疑似山寨机的ua，基本可以确定还是手机
    	    "Windows CE",//Windows CE，Mozilla/4.0 (compatible; MSIE 6.0; Windows CE; IEMobile 7.11)
    	    "iPhone",//iPhone是否也转wap？不管它，先区分出来再说。Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_1 like Mac OS X; zh-cn) AppleWebKit/532.9 (KHTML like Gecko) Mobile/8B117
    	    "iPad",//iPad的ua，Mozilla/5.0 (iPad; U; CPU OS 3_2 like Mac OS X; zh-cn) AppleWebKit/531.21.10 (KHTML like Gecko) Version/4.0.4 Mobile/7B367 Safari/531.21.10
    	    "Android",//Android是否也转wap？Mozilla/5.0 (Linux; U; Android 2.1-update1; zh-cn; XT800 Build/TITA_M2_16.22.7) AppleWebKit/530.17 (KHTML like Gecko) Version/4.0 Mobile Safari/530.17
    	    "BlackBerry",//BlackBerry8310/2.7.0.106-4.5.0.182
    	    "UCWEB",//ucweb是否只给wap页面？ Nokia5800 XpressMusic/UCWEB7.5.0.66/50/999
    	    "ucweb",//小写的ucweb貌似是uc的代理服务器Mozilla/6.0 (compatible; MSIE 6.0;) Opera ucweb-squid
    	    "BREW",//很奇怪的ua，例如：REW-Applet/0x20068888 (BREW/3.1.5.20; DeviceId: 40105; Lang: zhcn) ucweb-squid
    	    "J2ME",//很奇怪的ua，只有J2ME四个字母
    	    "YULONG",//宇龙手机，YULONG-CoolpadN68/10.14 IPANEL/2.0 CTC/1.0
    	    "YuLong",//还是宇龙
    	    "COOLPAD",//宇龙酷派YL-COOLPADS100/08.10.S100 POLARIS/2.9 CTC/1.0
    	    "TIANYU",//天语手机TIANYU-KTOUCH/V209/MIDP2.0/CLDC1.1/Screen-240X320
    	    "TY-",//天语，TY-F6229/701116_6215_V0230 JUPITOR/2.2 CTC/1.0
    	    "K-Touch",//还是天语K-Touch_N2200_CMCC/TBG110022_1223_V0801 MTK/6223 Release/30.07.2008 Browser/WAP2.0
    	    "Haier",//海尔手机，Haier-HG-M217_CMCC/3.0 Release/12.1.2007 Browser/WAP2.0
    	    "DOPOD",//多普达手机
    	    "Lenovo",// 联想手机，Lenovo-P650WG/S100 LMP/LML Release/2010.02.22 Profile/MIDP2.0 Configuration/CLDC1.1
    	    "LENOVO",// 联想手机，比如：LENOVO-P780/176A
    	    "HUAQIN",//华勤手机
    	    "AIGO-",//爱国者居然也出过手机，AIGO-800C/2.04 TMSS-BROWSER/1.0.0 CTC/1.0
    	    "CTC/1.0",//含义不明
    	    "CTC/2.0",//含义不明
    	    "CMCC",//移动定制手机，K-Touch_N2200_CMCC/TBG110022_1223_V0801 MTK/6223 Release/30.07.2008 Browser/WAP2.0
    	    "DAXIAN",//大显手机DAXIAN X180 UP.Browser/6.2.3.2(GUI) MMP/2.0
    	    "MOT-",//摩托罗拉，MOT-MOTOROKRE6/1.0 LinuxOS/2.4.20 Release/8.4.2006 Browser/Opera8.00 Profile/MIDP2.0 Configuration/CLDC1.1 Software/R533_G_11.10.54R
    	    "SonyEricsson",// 索爱手机，SonyEricssonP990i/R100 Mozilla/4.0 (compatible; MSIE 6.0; Symbian OS; 405) Opera 8.65 [zh-CN]
    	    "GIONEE",//金立手机
    	    "HTC",//HTC手机
    	    "ZTE",//中兴手机，ZTE-A211/P109A2V1.0.0/WAP2.0 Profile
    	    "HUAWEI",//华为手机，
    	    "webOS",//palm手机，Mozilla/5.0 (webOS/1.4.5; U; zh-CN) AppleWebKit/532.2 (KHTML like Gecko) Version/1.0 Safari/532.2 Pre/1.0
    	    "GoBrowser",//3g GoBrowser.User-Agent=Nokia5230/GoBrowser/2.0.290 Safari
    	    "IEMobile",//Windows CE手机自带浏览器，
    	    "WAP2.0"//支持wap 2.0的
    	    };

	/**
	 * 
	 * init:(初始化进来的接口). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * 
	 * @author gxq
	 * @param gdsId
	 * @param skuId
	 * @param model
	 * @param gdsDetailVO
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping("/{gdsId}-{skuId}")
	public String init(@PathVariable("gdsId") String gdsId, @PathVariable("skuId") String skuId, Model model,
			GdsDetailVO gdsDetailVO) {
		GdsInfoReqDTO dto = new GdsInfoReqDTO();
		if (StringUtil.isNotEmpty(gdsDetailVO.getGdsId())) {
			dto.setId(gdsDetailVO.getGdsId());
		}
		if (StringUtil.isNotEmpty(gdsDetailVO.getSkuId())) {
			dto.setSkuId(gdsDetailVO.getSkuId());
		}
		
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
		List<String> imglist = new ArrayList<String>();//分享可以选择的图片
		if (StringUtil.isNotEmpty(resultDto)) {
			if (resultDto.getSkuInfo() != null) {
				stockStatus = GdsUtils.checkStcokStatus(resultDto.getSkuInfo().getRealAmount());
				stockStatusDesc = GdsUtils.checkStcokStatusDesc(resultDto.getSkuInfo().getRealAmount());
//				Map<String, GdsPropRespDTO> map = resultDto.getSkuInfo().getAllPropMaps();
				//富文本取数统一改成这样
				Map<String, GdsPropRespDTO> richTextMap = resultDto.getSkuInfo().getRichTextPropMap();
				int detailCount = 0;
				if (richTextMap != null) {
				    for (String propId : richTextMap.keySet()){
				        List<GdsPropValueRespDTO> richPropList = richTextMap.get(propId).getValues();
				        for (GdsPropValueRespDTO gdsPropValueRespDTO : richPropList) {
                            if (StringUtil.isNotBlank(gdsPropValueRespDTO.getPropValue())) {
                                gdsPropValueRespDTO.setPropValue(getHtmlUrl(gdsPropValueRespDTO.getPropValue()));
                                detailCount++;
                            }
                        }
				    }
                }
//				String[] strs = new String[] { "1020", "1021", "1022", "1023", "1024", "1025" };
//				int detailCount = 0;
//				if (map != null) {
//					for (int i = 0; i < strs.length; i++) {
//						GdsPropRespDTO gdsPropRespDTO = map.get(strs[i]);
//						if (gdsPropRespDTO != null && gdsPropRespDTO.getValues() != null) {
//							for (GdsPropValueRespDTO gdsPropValueRespDTO : gdsPropRespDTO.getValues()) {
//								if (StringUtil.isNotBlank(gdsPropValueRespDTO.getPropValue())) {
//									gdsPropValueRespDTO.setPropValue(getHtmlUrl(gdsPropValueRespDTO.getPropValue()));
//									detailCount++;
//								}
//							}
//						}
//					}
//				}
				// 根据录入的判断是否有目录等这些属性，如果这些都没有，则按照录入来说就会提供产品详情的录入，此时只展示产品详情
				if (detailCount == 0) {
					// 针对人卫，只显示产品详情
					if (StringUtil.isNotBlank(resultDto.getGdsDesc())) {
						resultDto.setGdsDesc(getHtmlUrl(resultDto.getGdsDesc()));
					}
					// resultDto.setGdsPartlist(getHtmlUrl(resultDto.getGdsPartlist()));
				}
				List<GdsSku2MediaRespDTO> skuMediaList = resultDto.getSkuInfo().getSku2MediaRespDTOs();
				
				for (GdsSku2MediaRespDTO gdsSku2MediaRespDTO : skuMediaList) {
					//过滤产品版权页图片和产品包装条形码特写 图片
					if(!gdsSku2MediaRespDTO.getSortNo().equals(6) && !gdsSku2MediaRespDTO.getSortNo().equals(7)){
						String imgUuid = gdsSku2MediaRespDTO.getMediaUuid();
						String imgUrl = ParamsTool.getImageUrl(imgUuid,"250x250!");//分享图片取正方型规格
						imglist.add(imgUrl);
					}
				}
			}
			if (GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES.equals(resultDto.getSkuInfo().getGdsStatus())
					|| GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES.equals(resultDto.getSkuInfo().getGdsStatus())) {
				// 获取相关分类
				getGdsDownCommonCat(resultDto, model);
			}
			ShopInfoResDTO shopInfo = iShopInfoRSV.findShopInfoByShopID(resultDto.getShopId());
			if (StringUtil.isNotEmpty(shopInfo)) {
				shopName = shopInfo.getShopName();
			} else {
				throw new BusinessException("web.gds.2000012");
			}
		}
		// 获取库存阈值的配置参数
		getStockParam(model);
		model.addAttribute("shopName", shopName);
		model.addAttribute("stockStatus", stockStatus);
		model.addAttribute("stockStatusDesc", stockStatusDesc);
		model.addAttribute("gdsDetailInfo", resultDto);
		
		AddToCartButtonVO btn = GdsDetailUtil.getDefaultAddToCartButton(stockStatus, GdsUtils.isEqualsValid(resultDto.getGdsTypeRespDTO().getIfNeedstock()));
		
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
                            ? (sdf.format(skuStock.getZeroStockStarttime())) : null,GdsUtils.isEqualsValid(resultDto.getGdsTypeRespDTO().getIfNeedstock()));
    		
		}catch(Exception e){
		    LogUtil.error(MODULE, "[商城商品域]商品详请设置加入购物车按钮遇到异常!",e);
		}
		
		model.addAttribute("addToCartPromp",btn.getAddToCartPromp());
        model.addAttribute("addToCartEnable",btn.isAddToCartEnable());
		
		
		model.addAttribute("imglist", imglist);
		model.addAttribute("staffId", CipherUtil.encrypt(String.valueOf(StaffLocaleUtil.getStaff().getId())));
//		LongReqDTO gdsTypeQuery = new LongReqDTO();
//		gdsTypeQuery.setId(resultDto.getGdsTypeId());
//		GdsTypeRespDTO gdsTypeRespDTO = gdsTypeRSV.queryGdsTypeByPK(gdsTypeQuery);
		model.addAttribute("gdsType", resultDto.getGdsTypeRespDTO());

		BaseSysCfgRespDTO baseSysCfgRespDTO = SysCfgUtil.fetchSysCfg("SHOP_SHOW_LOCK");
		model.addAttribute("shopShow", baseSysCfgRespDTO.getParaValue());
		
		try{
		    BaseSysCfgRespDTO unitCfg = SysCfgUtil.fetchSysCfg(GdsConstants.SysCfgConstants.MALL_GDS_DETAIL_UNIT);
		    // 设置商品信息单位.
		    model.addAttribute("gdsInfoUnit",unitCfg.getParaValue());
		}catch(Exception e){
		    LogUtil.error(MODULE, "获取键值为["+GdsConstants.SysCfgConstants.MALL_GDS_DETAIL_UNIT+"]的配置参数异常!");
		}
		
		
		
		return URL + "/gdsdetail";
	}

	@RequestMapping("/shopInfo")
	public String initShopInfo(Model model, GdsDetailVO vo) {

		BaseSysCfgRespDTO baseSysCfgRespDTO = SysCfgUtil.fetchSysCfg("SHOP_SHOW_LOCK");
		model.addAttribute("showShop", baseSysCfgRespDTO.getParaValue());
		if (GdsConstants.Commons.STATUS_VALID.equals(baseSysCfgRespDTO.getParaValue())) {
			/* 店铺信息 begin */
			ShopInfoVO shopInfoVO = new ShopInfoVO();
			ShopInfoResDTO shopInfoResDTO = iShopInfoRSV.findShopInfoByShopID(Long.valueOf(vo.getShopId()));
			ObjectCopyUtil.copyObjValue(shopInfoResDTO, shopInfoVO, null, false);

			/* 是否收藏 begin */
			ShopCollectReqDTO shopCollectReqDTO = new ShopCollectReqDTO();
			shopCollectReqDTO.setShopId(String.valueOf(vo.getShopId()));
			shopCollectReqDTO.setStaffId(StaffLocaleUtil.getStaff().getId());
			// iShopCollectRSV.deleteShopBySel(shopCollectReqDTO);

			boolean isCollect = false;
			if (shopCollectReqDTO.getStaffId() != 0) {
				PageResponseDTO<ShopInfoResDTO> page = iShopCollectRSV.listShopCollect(shopCollectReqDTO);
				if (page != null && CollectionUtils.isNotEmpty(page.getResult())) {
					isCollect = true;
				}
			}
			model.addAttribute("isCollect", isCollect);
			/* 是否收藏 end */
			model.addAttribute(ISLOGIN_VO_ATTR, StaffLocaleUtil.getStaff().getId() != 0);

			// 获取好评率
			GdsEvalReqDTO gdsEvalReqDTO = new GdsEvalReqDTO();
			gdsEvalReqDTO.setShopId(Long.valueOf(vo.getShopId()));
			shopInfoVO.setEvalRate(iGdsEvalRSV.calCulateShopGoodEvalRate(gdsEvalReqDTO));

			// 获取宝贝数量
			GoodSearchPageReqVO goodSearchPageReqVO = new GoodSearchPageReqVO();
			goodSearchPageReqVO.setShopId(vo.getShopId().toString());
			SearchResult<GoodSearchResult> searchResult = SearchUtil.searchGood(goodSearchPageReqVO);
			shopInfoVO.setFavNum(searchResult.getNumFound());

			// 获取销售数量
			/*RGoodSaleRequest gGoodSaleRequest = new RGoodSaleRequest();
			gGoodSaleRequest.setShopId(Long.valueOf(vo.getShopId()));
			Long saleNum = iReportGoodPayedRSV.querySumBuyNumByShopId(gGoodSaleRequest);
			shopInfoVO.setSaleNum(saleNum);*/
			// 获取销售数量改成从solr获取。
			ShopSearchPageReqVO shopSearchVO = new ShopSearchPageReqVO();
            shopSearchVO.setId(String.valueOf(vo.getShopId()));
            shopSearchVO.setPageSize(1);
            shopSearchVO.setPageNumber(1);
            SearchResult<ShopSearchResult> shopSearchResult = ShopSearchUtil
                       .searchShop(shopSearchVO);
            
            if (shopSearchResult.isSuccess() && shopSearchResult.getNumFound() >0) {
                if(shopSearchResult.getResultList()!= null && shopSearchResult.getResultList().size()>=1){
                    Long saleNum= shopSearchResult.getResultList().get(0).getSaleCount();
                    shopInfoVO.setSaleNum(saleNum);
                }else{
                    shopInfoVO.setSaleNum(0l);
                }
		    }
		    else if(shopSearchResult.isSuccess() && shopSearchResult.getNumFound() == 0){
		        shopInfoVO.setSaleNum(0L);
		    }
		    else{
		        throw new BusinessException(shopSearchResult.getMessage());
		    }
			// 店铺图片
			shopInfoVO.setLogoPathURL(ImageUtil.getImageUrl(shopInfoResDTO.getLogoPath() + SUFFIX_IMAGE_SIZE));
			model.addAttribute("shopInfoResp", shopInfoVO);

		}
		/* 店铺信息 end */
		return URL + "/list/shop-info";
	}

	/**
	 * 
	 * collectShop:(收藏店铺). <br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author zqr
	 * @param count
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/collectShop/{shopId}")
	@ResponseBody
	public EcpBaseResponseVO collectShop(Model model, @PathVariable("shopId") Long shopId) throws BusinessException {
		EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
		try {
			ShopCollectReqDTO shopCollectReqDTO = new ShopCollectReqDTO();
			shopCollectReqDTO.setShopId(String.valueOf(shopId));
			shopCollectReqDTO.setStaffId(StaffLocaleUtil.getStaff().getId());

			iShopCollectRSV.insertShopCollect(shopCollectReqDTO);
			ecpBaseResponseVO.setResultMsg("收藏店铺成功");
			ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (BusinessException e) {
			// LogUtil.error(MODUAL, "收藏店铺失败", e);
			ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			ecpBaseResponseVO.setResultMsg("收藏店铺失败");
		} catch (Exception e) {
			// LogUtil.error(MODUAL, "收藏店铺失败", e);
			ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			ecpBaseResponseVO.setResultMsg("收藏店铺失败");
		}

		return ecpBaseResponseVO;
	}

	/**
	 * 
	 * deleteShop:(收藏店铺). <br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author zqr
	 * @param count
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/deleteShop/{shopId}")
	@ResponseBody
	public EcpBaseResponseVO deleteShop(Model model, @PathVariable("shopId") Long shopId) throws BusinessException {
		EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
		try {
			ShopCollectReqDTO shopCollectReqDTO = new ShopCollectReqDTO();
			shopCollectReqDTO.setShopId(String.valueOf(shopId));
			shopCollectReqDTO.setStaffId(StaffLocaleUtil.getStaff().getId());
			iShopCollectRSV.deleteShopBySel(shopCollectReqDTO);
			ecpBaseResponseVO.setResultMsg("取消收藏店铺成功");
			ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (BusinessException e) {
			// LogUtil.error(MODUAL, "取消收藏店铺失败", e);
			ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			ecpBaseResponseVO.setResultMsg("取消收藏店铺失败");
		} catch (Exception e) {
			// LogUtil.error(MODUAL, "取消收藏店铺失败", e);
			ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			ecpBaseResponseVO.setResultMsg("取消收藏店铺失败");
		}

		return ecpBaseResponseVO;
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
	 * getGdsDownCommonCat:(未上架的商品获取相关分类). <br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author gxq
	 * @since JDK 1.6
	 */
	public void getGdsDownCommonCat(GdsInfoDetailRespDTO resultDto, Model model) {
		GdsInfoReqDTO reqDTO = new GdsInfoReqDTO();
		List<GdsInfoDetailRespDTO> gdsInfoDetailRespDTO = null;
		reqDTO.setMainCatgs(resultDto.getMainCatgs());
		reqDTO.setId(resultDto.getSkuInfo().getGdsId());
		reqDTO.setPageNo(1);
		reqDTO.setPageSize(5);
		List<Long> propIds = new ArrayList<Long>();
		propIds.add(1001l);// 作者
		reqDTO.setPropIds(propIds);
		try {
			gdsInfoDetailRespDTO = iGdsInfoQueryRSV.queryGdsSkuInfoListByCatgRela(reqDTO);
			if (gdsInfoDetailRespDTO != null) {
				model.addAttribute("commondCatGds", gdsInfoDetailRespDTO);
			} else {
				model.addAttribute("commondCatGds", new ArrayList<GdsSkuInfoRespDTO>());
			}
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "获取相关分类推荐失败！", e);
			model.addAttribute("commondCatGds", new ArrayList<GdsSkuInfoRespDTO>());
		}
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
	@RequestMapping(value = "/querycatgcodelist")
	@ResponseBody
	public Map<String, Object> querycatgcodelist(GdsDetailVO gdsDetailVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<GdsCategoryRespDTO> cateList = new ArrayList<GdsCategoryRespDTO>();
		GdsCategoryReqDTO gdsCategoryReqDTO = new GdsCategoryReqDTO();
		gdsCategoryReqDTO.setCatgCode(gdsDetailVO.getMainCatgs());
		String ifReadOnline = "0";
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
			if (cateList == null) {
				cateList = new ArrayList<GdsCategoryRespDTO>();
			} else {
				for (GdsCategoryRespDTO gdsCategoryRespDTO : cateList) {
					if (gdsDigitsBookCatCode.equals(gdsCategoryRespDTO.getCatgCode())) {
						map.put("digitsBook", true);
						map.put("catgCodeName", "数字教材");
					} else if (gdsEbookCatCode.equals(gdsCategoryRespDTO.getCatgCode())) {
						map.put("ebook", true);
						map.put("catgCodeName", "电子书");
					}
					if (gdsDigitsBookCatCode.equals(gdsCategoryRespDTO.getCatgCode())
							|| gdsEbookCatCode.equals(gdsCategoryRespDTO.getCatgCode())) {
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
		GdsQueryOption[] gdsQueryOptions = new GdsQueryOption[] { GdsQueryOption.BASIC, GdsQueryOption.GDSTYPE };
		SkuQueryOption[] skuQueryOptions = new SkuQueryOption[] { SkuQueryOption.BASIC, SkuQueryOption.MEDIA,
				SkuQueryOption.PROP, SkuQueryOption.SHOWSTOCK, SkuQueryOption.CAlDISCOUNT };
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
					stockStatus = GdsUtils.checkStcokStatus(respDto.getSkuInfo().getRealAmount());
					// 数字印刷的价格
					if (respDto.getSkuInfo().getAllPropMaps() != null
							&& respDto.getSkuInfo().getAllPropMaps().get("1029") != null) {
						for (GdsPropValueRespDTO gdsPropValueRespDTO : respDto.getSkuInfo().getAllPropMaps().get("1029")
								.getValues()) {
							if (!StringUtil.isBlank(gdsPropValueRespDTO.getPropValue())) {
								princePrice = MoneyUtil.convertCentToYuan(gdsPropValueRespDTO.getPropValue());
							} else {
								princePrice = "0";
							}
						}
					}
				}

			}
			;
			model.addAttribute("digitsPrinPrice", princePrice);
			model.addAttribute("respDto", respDto);
			model.addAttribute("stockStatus", stockStatus);
			// 获取库存阈值的配置参数
			getStockParam(model);
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "单品信息切换错误！", e);
		} catch (Exception e) {
			LogUtil.error(MODULE, "单品信息切换错误！", e);
		}
		return model;
	}

	private void initCommonGdsInfo() {

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

	private String getHtmlUrl(String vfsId) {
		if (StringUtil.isBlank(vfsId)) {
			return "";
		}
		return ImageUtil.getStaticDocUrl(vfsId, "html");
	}

	/**
	 * 
	 * queryRecentlyProduct:(获取最近浏览的产品). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * 
	 * @author gxq
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/queryrecentlyproduct")
	@ResponseBody
	public Map<String, Object> queryRecentlyProduct(GdsRecentlyVO gdsRecentlyVO, Model model) {

		/* 店铺信息 begin */
		if (gdsRecentlyVO.getShopId() != null) {
			ShopInfoVO shopInfoVO = new ShopInfoVO();
			ShopInfoResDTO shopInfoResDTO = iShopInfoRSV.findShopInfoByShopID(Long.valueOf(gdsRecentlyVO.getShopId()));
			ObjectCopyUtil.copyObjValue(shopInfoResDTO, shopInfoVO, null, false);

			// 获取好评率
			GdsEvalReqDTO gdsEvalReqDTO = new GdsEvalReqDTO();
			gdsEvalReqDTO.setShopId(Long.valueOf(gdsRecentlyVO.getShopId()));
			shopInfoVO.setEvalRate(iGdsEvalRSV.calCulateShopGoodEvalRate(gdsEvalReqDTO));

			// 获取宝贝数量
			GoodSearchPageReqVO goodSearchPageReqVO = new GoodSearchPageReqVO();
			goodSearchPageReqVO.setShopId(gdsRecentlyVO.getShopId().toString());
			SearchResult<GoodSearchResult> searchResult = SearchUtil.searchGood(goodSearchPageReqVO);
			shopInfoVO.setFavNum(searchResult.getNumFound());

			// 获取销售数量
			RGoodSaleRequest gGoodSaleRequest = new RGoodSaleRequest();
			gGoodSaleRequest.setShopId(Long.valueOf(gdsRecentlyVO.getShopId()));
			/*
			 * Long saleNum=iReportGoodPayedRSV.querySumBuyNumByShopId(
			 * gGoodSaleRequest); shopInfoVO.setSaleNum(saleNum);
			 */
			model.addAttribute("shopInfoResp", shopInfoVO);
		}
		/* 店铺信息 end */

		Map<String, Object> map = new HashMap<String, Object>();
		GdsBrowseHisReqDTO gdsBrowseHisReqDTO = gdsRecentlyVO.toBaseInfo(GdsBrowseHisReqDTO.class);
		gdsBrowseHisReqDTO.setStaffId(gdsBrowseHisReqDTO.getStaff().getId());
		PageResponseDTO<GdsBrowseHisRespDTO> pageInfo = null;
		if (StringUtil.isNotBlank(gdsRecentlyVO.getGdsSize())) {
			gdsBrowseHisReqDTO.setPageSize(Integer.parseInt(gdsRecentlyVO.getGdsSize()));
		}
		// 过滤当前商品
		if (StringUtil.isNotEmpty(gdsRecentlyVO.getGdsId())) {
			gdsBrowseHisReqDTO.setGdsId(gdsRecentlyVO.getGdsId());
		}
		try {
			pageInfo = iGdsBrowseHisRSV.queryGdsBrowseHisByPage(gdsBrowseHisReqDTO);
			if (pageInfo != null) {
				if (pageInfo.getResult() != null) {
					for (GdsBrowseHisRespDTO gdsBrowseHisRespDTO : pageInfo.getResult()) {
						gdsBrowseHisRespDTO
								.setMediaId(new AiToolUtil().genImageUrl(gdsBrowseHisRespDTO.getMediaId(), "122x122!"));
					}
				}
				map.put("list", pageInfo.getResult());
			}
		} catch (BusinessException e) {
			map.put("list", new ArrayList<GdsBrowseHisRespDTO>());
			LogUtil.error(MODULE, "获取最近浏览商品报错！", e);
		}
		return map;
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
	@RequestMapping(value = "/querygdspictrue")
	@ResponseBody
	public Map<String, Object> queryGdsPictrue(@RequestParam("pictrueNum") String pictrueNum,
			@RequestParam("pictrueHeight") String height, @RequestParam("pictrueWidth") String width,
			@RequestParam("pictrueMoreHeight") String moreHeight, @RequestParam("pictrueMoreWidth") String moreWidth,
			@RequestParam("gdsId") String gdsId, @RequestParam("skuId") String skuId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<GdsSkuMediaVO> resultList = new ArrayList<GdsSkuMediaVO>();
		GdsSkuInfoReqDTO dto = new GdsSkuInfoReqDTO();
		// if (StringUtil.isNotBlank(gdsId)) {
		// dto.setId(Long.parseLong(gdsId));
		// }
		if (StringUtil.isNotBlank(skuId)) {
			dto.setId(Long.parseLong(skuId));
			dto.setGdsId(Long.parseLong(gdsId));
		}
		SkuQueryOption[] skuQueryOptions = new SkuQueryOption[] { SkuQueryOption.BASIC, SkuQueryOption.MEDIA };
		dto.setSkuQuery(skuQueryOptions);
		dto.setStatus("1");
		GdsSkuInfoRespDTO resultDto = null;
		try {
			resultDto = iGdsSkuInfoQueryRSV.querySkuInfoByOptions(dto);
			if (resultDto != null) {
				GdsSkuMediaVO gdsSkuMediaVO = null;
				List<GdsSku2MediaRespDTO> skuMediaList = resultDto.getSku2MediaRespDTOs();
				if (resultDto != null && CollectionUtils.isNotEmpty(skuMediaList)) {
					// 取单品的图片
					for (GdsSku2MediaRespDTO gdsSku2MediaRespDTO : skuMediaList) {
						//过滤产品版权页图片和产品包装条形码特写的图片
						if(!gdsSku2MediaRespDTO.getSortNo().equals(99) && !gdsSku2MediaRespDTO.getSortNo().equals(100)){
							gdsSkuMediaVO = new GdsSkuMediaVO();
							gdsSkuMediaVO.setBigUrl(new AiToolUtil().genImageUrl(gdsSku2MediaRespDTO.getMediaUuid(),
									moreHeight + "x" + moreWidth + "!"));
							gdsSkuMediaVO.setMiddleUrl(new AiToolUtil().genImageUrl(gdsSku2MediaRespDTO.getMediaUuid(),
									Integer.parseInt(moreHeight) / 2 + "x" + Integer.parseInt(moreWidth) / 2 + "!"));
							gdsSkuMediaVO.setUrl(new AiToolUtil().genImageUrl(gdsSku2MediaRespDTO.getMediaUuid(),
									height + "x" + width + "!"));
							resultList.add(gdsSkuMediaVO);
						}
					}
				} else {
					map.put("bigUrl", new AiToolUtil().genImageUrl("", moreHeight + "x" + moreWidth + "!"));
					map.put("middleUrl", new AiToolUtil().genImageUrl("",
							Integer.parseInt(moreHeight) / 2 + "x" + Integer.parseInt(moreWidth) / 2 + "!"));
				}
			} else {
				map.put("bigUrl", new AiToolUtil().genImageUrl("", moreHeight + "x" + moreWidth + "!"));
				map.put("middleUrl", new AiToolUtil().genImageUrl("",
						Integer.parseInt(moreHeight) / 2 + "x" + Integer.parseInt(moreWidth) / 2 + "!"));
			}
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "获取商品详情图片错误！", e);
		}
		map.put("result", resultList);
		map.put("pictrueHeight", height);
		map.put("pictrueWidth", width);
		map.put("pictrueMoreHeight", moreHeight);
		map.put("pictrueMoreWidth", moreWidth);
		map.put("pictrueNum", pictrueNum);
		return map;
	}

	/**
	 * 
	 * queryAutoCombine:(获取自由搭配-固定搭配). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * 
	 * @author gxq
	 * @param model
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/queryautocombine")
	public String queryAutoCombine(Model model, GdsDetailVO gdsDetailVO) {
		List<GdsPromMatchVO> autoResultList = new ArrayList<GdsPromMatchVO>();
		List<GdsPromMatchVO> fixedResultList = new ArrayList<GdsPromMatchVO>();
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
			shopStaffGroupReqDTO.setShopId(gdsDetailVO.getShopId());
			// 客户组id
			String custGroupValue = null;
			if (custInfoResDTO != null && custInfoResDTO.getId() != null && custInfoResDTO.getId() != 0) {
				custGroupValue = iShopManageRSV.queryShopStaffGroup(shopStaffGroupReqDTO);
			}
			// 客户基本信息
			promRuleCheckDTO.setCustGroupValue(custGroupValue);

			promRuleCheckDTO.setSiteId(promRuleCheckDTO.getCurrentSiteId());

			promRuleCheckDTO.setGdsId(gdsDetailVO.getGdsId());
			promRuleCheckDTO.setSkuId(gdsDetailVO.getSkuId());
			promRuleCheckDTO.setShopId(gdsDetailVO.getShopId());
			promRuleCheckDTO.setChannelValue(WEB);
			promRuleCheckDTO.setCalDate(new Date(System.currentTimeMillis()));

			// 自由搭配
			promRuleCheckDTO.setMatchType(PromConstants.PromMatchSku.MATCH_TYPE_1);
			List<PromMatchDTO> autolist = iPromRSV.queryMatchList(promRuleCheckDTO);
			if (autolist != null && autolist.size() > 0) {
				getAutoInfo(autolist, autoResultList);
			}
			// 固定搭配
			promRuleCheckDTO.setMatchType(PromConstants.PromMatchSku.MATCH_TYPE_2);
			List<PromMatchDTO> fixedlist = iPromRSV.queryMatchList(promRuleCheckDTO);
			if (fixedlist != null && fixedlist.size() > 0) {
				getAutoInfo(fixedlist, fixedResultList);
			}
			model.addAttribute("autoCombineList", autoResultList);
			model.addAttribute("fixedCombineList", fixedResultList);
			model.addAttribute("skuId", gdsDetailVO.getSkuId());
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "获取组合搭配失败！", e);
			model.addAttribute("autoCombineList", autoResultList);
			model.addAttribute("fixedCombineList", fixedResultList);
			return URL + "/list/auto-combine";
		} catch (Exception e) {
			LogUtil.error(MODULE, "获取组合搭配失败！", e);
			model.addAttribute("autoCombineList", autoResultList);
			model.addAttribute("fixedCombineList", fixedResultList);
			return URL + "/list/auto-combine";
		}
		return URL + "/list/auto-combine";
	}

	private void getAutoInfo(List<PromMatchDTO> list, List<GdsPromMatchVO> resultList) {
		GdsPromMatchVO gdsPromMatchVO = null;
		for (PromMatchDTO promMatchDTO : list) {
			gdsPromMatchVO = new GdsPromMatchVO();
			gdsPromMatchVO.setPromInfoDTO(promMatchDTO.getPromInfoDTO());
			String promThemeCut=promMatchDTO.getPromInfoDTO().getPromTheme();
			if(promThemeCut.length()>6){
				promThemeCut=promThemeCut.substring(0,6);
			}
			gdsPromMatchVO.setPromThemeCut(promThemeCut);

			//促销免邮信息
			PromPostDTO PromPostDTO=new PromPostDTO();
			List<Long> promIds=new ArrayList<Long>();
			promIds.add(promMatchDTO.getPromInfoDTO().getId());
			PromPostDTO.setPromIds(promIds);
			PromPostRespDTO promPostRespDTO=promShipRSV.checkIfFreePost(PromPostDTO);
			gdsPromMatchVO.setIfPromShipFree(promPostRespDTO.getIfFreePost());

			List<PromMatchSkuDTO> promMatchSkuDTOList = promMatchDTO.getPromMatchSkuDTOList();
			List<GdsPromMatchSkuVO> gdsPromMatchSkuVOList = new ArrayList<GdsPromMatchSkuVO>();
			GdsPromMatchSkuVO gdsPromMatchSkuVO = null;
			if (promMatchSkuDTOList != null && promMatchSkuDTOList.size() > 0) {
				for (PromMatchSkuDTO skuDto : promMatchSkuDTOList) {
					gdsPromMatchSkuVO = new GdsPromMatchSkuVO();
					GdsSkuInfoRespDTO skuInfoRespDTO = new GdsSkuInfoRespDTO();
					Map<String, GdsPropRespDTO> allPropMaps = new HashMap<String, GdsPropRespDTO>();
					ObjectCopyUtil.copyObjValue(skuDto, gdsPromMatchSkuVO, "", false);
					GdsSkuInfoReqDTO skudto = new GdsSkuInfoReqDTO();
					SkuQueryOption[] skuQueryOptions1 = new SkuQueryOption[] { SkuQueryOption.BASIC,
							SkuQueryOption.PROP, SkuQueryOption.MAINPIC, SkuQueryOption.SHOWPRICE };
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
	 * queryGdsEval:(获取商品详情的商品评价). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * 
	 * @author gxq
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/querygdseval")
	public String queryGdsEval(Model model, GdsEvalVO gdsEvalVO) throws Exception {
		// Map<String, Object> map = new HashMap<String, Object>();
		GdsEvalReqDTO gdsEvalReqDTO = gdsEvalVO.toBaseInfo(GdsEvalReqDTO.class);
		ObjectCopyUtil.copyObjValue(gdsEvalVO, gdsEvalReqDTO, "", false);
		gdsEvalReqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
		PageResponseDTO<GdsEvalRespDTO> pageInfo = null;
		// 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
		EcpBasePageRespVO<Map> respVO = null;
		try {
			pageInfo = iGdsEvalRSV.queryGdsEvalRespDTOPagingForGdsDetail(gdsEvalReqDTO);

			if (StringUtil.isNotEmpty(pageInfo)) {
				List<GdsEvalRespDTO> resp = pageInfo.getResult();
				if (resp != null && resp.size() > 0) {
					for (GdsEvalRespDTO gdsEvalRespDTO : resp) {
						// 解析评价内容
						try {
							gdsEvalRespDTO.setDetail(FileUtil.readFile2Text(gdsEvalRespDTO.getContent(), "UTF-8"));
						} catch (Exception e) {
							continue;
						}
						// 每条评价的对应回复的内容
						if (gdsEvalRespDTO.getEvalReplyRespDTOs() != null
								&& gdsEvalRespDTO.getEvalReplyRespDTOs().size() > 0) {
							for (GdsEvalReplyRespDTO subDto : gdsEvalRespDTO.getEvalReplyRespDTOs()) {
								// 解析回复内容
								try {
									subDto.setDetail(FileUtil.readFile2Text(subDto.getContent(), "UTF-8"));
									CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
									custInfoReqDTO.setId(subDto.getStaffId());
									CustInfoResDTO custInfoResDTO = iCustInfoRSV.getCustInfoById(custInfoReqDTO);
									subDto.setStaffName(GdsParamsTool.fuzzyStaffCode(custInfoResDTO.getStaffCode()));
								} catch (Exception e) {
									continue;
								}
							}
						}
						// 获取评价客户的客户信息。
						CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
						custInfoReqDTO.setId(gdsEvalRespDTO.getStaffId());
						CustInfoResDTO custInfoResDTO = iCustInfoRSV.getCustInfoById(custInfoReqDTO);
						// gdsEvalRespDTO.setStaffName(custInfoResDTO.getStaffCode());
						// 模糊化用户名,在商品详情中用星号替换.
						gdsEvalRespDTO.setStaffName(GdsParamsTool.fuzzyStaffCode(custInfoResDTO.getStaffCode()));
						gdsEvalRespDTO.setStaffLevelName(custInfoResDTO.getCustLevelName());
						gdsEvalRespDTO.setStaffLevelCode(custInfoResDTO.getCustLevelCode());
					}
				}
				respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
			}
			model.addAttribute("list", respVO);
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "获取评价列表失败！", e);
			model.addAttribute("list", EcpBasePageRespVO.buildByPageResponseDTO(pageInfo));
		} catch (Exception e) {
			LogUtil.error(MODULE, "获取评价列表失败！", e);
			model.addAttribute("list", EcpBasePageRespVO.buildByPageResponseDTO(pageInfo));
		}

		return URL + "/list/gds-evaluation-list";
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
	public EcpBaseResponseVO add(GdsDetailVO gdsDetailVO) {
		EcpBaseResponseVO vo = new EcpBaseResponseVO();
		try {
			GdsCollectReqDTO dto = new GdsCollectReqDTO();
			dto.setSkuId(gdsDetailVO.getSkuId());
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
	 * :(获取相同分类推荐). <br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author gxq
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/querycommondcat")
	public String queryCommondCat(Model model, GdsCommonAuthorVO gdsCommonAuthorVO) {
		String url = getAnalysisUrl()+"/service/recommendRelatedGds";
		if(null == gdsCommonAuthorVO || StringUtil.isEmpty(gdsCommonAuthorVO.getSkuId())){
		    return URL + "/list/gds-commondCat-list";
        }
		BasicNameValuePair base = new BasicNameValuePair("skuId",gdsCommonAuthorVO.getSkuId().toString());
		//BasicNameValuePair base = new BasicNameValuePair("skuId","277682");
//		String url ="http://shoptest1.pmph.com:19419/ecp_httpsv/service/recommendRelatedGds";
		List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
		formparams.add(base);
		String json = doRequest(url, formparams);
		JSONObject jsonObj = null;
		if(null != json){
            jsonObj = JSONObject.parseObject(json);
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
				GdsSkuInfo.setDiscountPrice(null);
				GdsSkuInfo.setGdsId(Long.valueOf(gdsId));
				GdsSkuInfo.setId(Long.valueOf(skuId));
				GdsMediaRespDTO mainPic = new GdsMediaRespDTO();
				mainPic.setMediaUuid(jo.get("mainPicId").toString());
				GdsSkuInfo.setMainPic(mainPic);
				GdsSkuInfo.setAllPropMaps(propMap);
				Long discountPrice = getDiscountPrice(Long.valueOf(gdsId),Long.valueOf(skuId));
				if(null != discountPrice && 0 < discountPrice){
				    GdsSkuInfo.setDiscountPrice(discountPrice);
				}
				detailResp.setSkuInfo(GdsSkuInfo);
				gdsInfo.add(detailResp);
			} 
		}else{
			gdsInfo=null;
		}
		
//		GdsInfoReqDTO reqDTO = gdsCommonAuthorVO.toBaseInfo(GdsInfoReqDTO.class);
//		List<GdsInfoDetailRespDTO> gdsInfoDetailRespDTO = null;
//		reqDTO.setMainCatgs(gdsCommonAuthorVO.getCategCode());
//		if (StringUtil.isNotEmpty(gdsCommonAuthorVO.getGdsId())) {
//			reqDTO.setId(gdsCommonAuthorVO.getGdsId());
//		}
//		List<Long> propIds = new ArrayList<Long>();
//		propIds.add(1001l);// 作者
//		reqDTO.setPropIds(propIds);
//		reqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
//		reqDTO.setPageNo(1);
//		reqDTO.setPageSize(5);
//		try {
//			gdsInfoDetailRespDTO = iGdsInfoQueryRSV.queryGdsSkuInfoListByCatgRela(reqDTO);
//			if (gdsInfoDetailRespDTO == null) {
//				gdsInfoDetailRespDTO = new ArrayList<GdsInfoDetailRespDTO>();
//			}
//			model.addAttribute("commonAuthorList", gdsInfo);
//		} catch (BusinessException e) {
//			LogUtil.error(MODULE, "获取相同分类推荐失败！", e);
//			model.addAttribute("commonAuthorList", new ArrayList<GdsSkuInfoRespDTO>());
//			return URL + "/list/gds-commondCat-list";
//		}
		model.addAttribute("commonAuthorList", gdsInfo);
		return URL + "/list/gds-commondCat-list";
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
		//List<PromListRespDTO> list = new ArrayList<PromListRespDTO>();
		GdsPromListDTO list = new GdsPromListDTO();
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
//			promRuleCheckDTO.setStaffId(custInfoReqDTO.getStaff().getId() + "");
			promRuleCheckDTO.setGdsId(gdsDetailVO.getGdsId());
			promRuleCheckDTO.setChannelValue(WEB);
			promRuleCheckDTO.setShopId(gdsDetailVO.getShopId());
			promRuleCheckDTO.setCalDate(new Date(System.currentTimeMillis()));
			promRuleCheckDTO.setSkuId(gdsDetailVO.getSkuId());
			promRuleCheckDTO.setBasePrice(gdsDetailVO.getRealPrice());
			promRuleCheckDTO.setBuyPrice(gdsDetailVO.getDiscountPrice());
			promRuleCheckDTO.setGdsName(gdsDetailVO.getGdsName());
			promRuleCheckDTO.setShopName(promRuleCheckDTO.getShopName());
			list = promQueryRSV.listPromNew(promRuleCheckDTO);
			if(null != list){
			    SeckillDiscountDTO  seckill = list.getSeckill();
			    if(null != seckill){
			        if(null != seckill.getSystemTime()){
			            map.put("sysdate",seckill.getSystemTime()); 
			        }else{
			            map.put("sysdate",DateUtil.getSysDate()); 
			        }
			        
			    }
			}
		} catch (BusinessException e) {
			map.put("saleList", new ArrayList<PromInfoDTO>());
			LogUtil.error(MODULE, "获取促销列表失败", e);
		}
		map.put("saleList", list);
		return map;
	}

	/*
	 * @RequestMapping(value = "/readpdf") public String readpdf() { return URL
	 * + "/pdf/gds-pdf"; }
	 */

	/**
	 * 
	 * parseIsbn:(解析isbn链接到商品详情). <br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author gxq
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/parseisbn")
	public String parseIsbn(Model model, GdsParseISBNVO gdsParseISBNVO) {
		/**
		 * type 类型。传1:表示 纸质书，0表示电子书
		 */
		GdsSku2PropPropIdxReqDTO reqDTO = new GdsSku2PropPropIdxReqDTO();

		Long gdsId = 0l;
		Long skuId = 0l;
		reqDTO.setPropId(PROP_ID_1032);
		if (StringUtil.isNotBlank(gdsParseISBNVO.getIsbn())) {
			String a = gdsParseISBNVO.getIsbn();
			String isbn = Escape.unescape(a);
			// 解析获取标准的ISBN号
			if (isbn.contains("ISBN")) {
				isbn = isbn.substring(4, isbn.length());
			}
			if (isbn.contains("/")) {
				isbn = isbn.substring(0, isbn.lastIndexOf('/'));
			}
			reqDTO.setPropValue(isbn);
		} else {
			return "redirect:/gdsdetail/" + gdsId + "-" + skuId + ".html";
		}
		reqDTO.setPageSize(PAGE_SIZE);

		try {
			// 纸质书编码
			BaseSysCfgRespDTO baseSysCfgRespDTO = SysCfgUtil.fetchSysCfg("GDS_PAPER_BOOK_CAT_CODE");
			String paperBookCateCode = "";
			if (baseSysCfgRespDTO != null) {
				paperBookCateCode = SysCfgUtil.fetchSysCfg("GDS_PAPER_BOOK_CAT_CODE").getParaValue();
			} else {
				paperBookCateCode = GDS_PAPER_BOOK_CAT_CODE;
			}
			// 电子书编码
			BaseSysCfgRespDTO baseSysCfgRespDTOEbook = SysCfgUtil.fetchSysCfg("GDS_E_BOOK_CAT_CODE");
			String eBookCateCode = "";
			if (baseSysCfgRespDTOEbook != null) {
				eBookCateCode = SysCfgUtil.fetchSysCfg("GDS_E_BOOK_CAT_CODE").getParaValue();
			} else {
				eBookCateCode = GDS_E_BOOK_CAT_CODE;
			}
			PageResponseDTO<GdsSkuInfoRespDTO> rspDto = iGdsSkuInfoQueryRSV.queryGdsSkuInfoPaging(reqDTO);
			if (rspDto != null) {
				if (rspDto.getResult() != null && rspDto.getResult().size() > 0) {
					for (GdsSkuInfoRespDTO gdsSkuInfoRespDTO : rspDto.getResult()) {
						// 根据入参的isbn查询要跳转的分类 来查找对应的分类书
						GdsCategoryReqDTO dto = new GdsCategoryReqDTO();
						dto.setCatgCode(gdsSkuInfoRespDTO.getMainCatgs());
						List<GdsCategoryRespDTO> list = igdsCategoryRSV.queryCategoryTraceUpon(dto);
						if (list != null && list.size() > 0) {
							if (getCateCodeInfo(list, gdsParseISBNVO.getType(), paperBookCateCode, eBookCateCode)) {
								gdsId = gdsSkuInfoRespDTO.getGdsId();
								skuId = gdsSkuInfoRespDTO.getId();
								break;
							}
						}
					}
				}
			}
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "根据ISBN号解析错误！", e);
			return "redirect:/gdsdetail/" + gdsId + "-" + skuId + ".html";
		} catch (Exception e) {
			LogUtil.error(MODULE, "根据ISBN号解析错误！", e);
			return "redirect:/gdsdetail/" + gdsId + "-" + skuId + ".html";
		}
		return "redirect:/gdsdetail/" + gdsId + "-" + skuId + ".html";

	}

	private boolean getCateCodeInfo(List<GdsCategoryRespDTO> list, String type, String paperBookCateCode,
			String eBookCateCode) {
		// type 类型。传1:表示 纸质书，0表示电子书
		for (GdsCategoryRespDTO gdsCategoryRespDTO : list) {
			if (paperBookCateCode.equals(gdsCategoryRespDTO.getCatgCode()) && "1".equals(type)) {
				// 纸质书
				return true;
			} else if (eBookCateCode.equals(gdsCategoryRespDTO.getCatgCode()) && "0".equals(type)) {
				// 电子书
				return true;
			} else {
				continue;
			}
		}
		return false;
	}

	/**
	 * 
	 * queryNetWordService:(获取网络增值服务). <br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author gxq
	 * @return
	 * @since JDK 1.6
	 */
	/*
	 * @RequestMapping(value = "/querynetwordservice") public String
	 * queryNetWordService(Model model, GdsNetWorkVO gdsNetWorkVO) {
	 * AipLMNetValueAddedRequest aipLMNetValueAddedRequest = new
	 * AipLMNetValueAddedRequest(); if
	 * (StringUtil.isNotBlank(gdsNetWorkVO.getIsbn())) {
	 * aipLMNetValueAddedRequest.setIsbn(gdsNetWorkVO.getIsbn()); }
	 * aipLMNetValueAddedRequest.setCnt(PAGE_SIZE_10);
	 * AipLMNetValueAddedResponse list = null; try { String GDS_NET_WORK_ADDRESS
	 * = SysCfgUtil.fetchSysCfg("GDS_NET_WORK_ADDRESS").getParaValue();
	 * aipLMNetValueAddedRequest.setReqUrl(GDS_NET_WORK_ADDRESS); list =
	 * iAipLMNetValueAddedRSV.requestResource(aipLMNetValueAddedRequest); if
	 * (list != null) { model.addAttribute("netWorkList", list.getResources());
	 * } else { model.addAttribute("netWorkList", new
	 * ArrayList<AipLMNetValueAddedDetail>()); }
	 * model.addAttribute("toMoreService",
	 * SysCfgUtil.fetchSysCfg("GDS_TO_NET_WORK_ADDRESS").getParaValue());
	 * model.addAttribute("isbn", gdsNetWorkVO.getIsbn()); } catch
	 * (BusinessException e) { LogUtil.error(MODULE, "获取网络增值服务错误！", e);
	 * model.addAttribute("netWorkList", new
	 * ArrayList<AipLMNetValueAddedDetail>()); } catch (Exception e) {
	 * LogUtil.error(MODULE, "获取网络增值服务错误！", e);
	 * model.addAttribute("netWorkList", new
	 * ArrayList<AipLMNetValueAddedDetail>()); } model.addAttribute("isbn",
	 * gdsNetWorkVO.getIsbn()); return URL + "/list/gds-netWorkService-list"; }
	 */

	/**
	 * 
	 * readOnline:(数字教材和电子书的授权阅读). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * 
	 * @author gxq
	 * @param gdsReadOnlineVO
	 * @return
	 * @since JDK 1.6
	 */
	/*
	 * @RequestMapping(value = "/readonline")
	 * 
	 * @ResponseBody public EcpBaseResponseVO readOnline(GdsReadOnlineVO
	 * gdsReadOnlineVO) { EcpBaseResponseVO vo = new EcpBaseResponseVO();
	 * CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO(); AipZYAuthRequest
	 * aipZYAuthRequest = new AipZYAuthRequest();
	 * aipZYAuthRequest.setUserName(custInfoReqDTO.getStaff().getStaffCode());
	 * // 阅读方式 1:正式，2：试用 aipZYAuthRequest.setReadType("2"); AipZYAuthResponse
	 * result = null; try { GdsInterfaceGdsGidxReqDTO gdsInterfaceGdsGidxReqDTO
	 * = new GdsInterfaceGdsGidxReqDTO();
	 * gdsInterfaceGdsGidxReqDTO.setGdsId(gdsReadOnlineVO.getGdsId()); // 08
	 * 是erp ,09 是泽云 gdsInterfaceGdsGidxReqDTO.setOrigin("09");
	 * GdsInterfaceGdsGidxRespDTO gdsInterfaceGdsGidxRespDTO =
	 * iGdsInterfaceGdsRSV.queryGdsInterfaceGdsGidxByEcpGdsId(
	 * gdsInterfaceGdsGidxReqDTO); if (gdsInterfaceGdsGidxRespDTO == null ||
	 * gdsInterfaceGdsGidxRespDTO.getOriginGdsId() == null) {
	 * vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
	 * vo.setResultMsg("亲，该商品暂时没有在线试读功能哦！"); return vo; }
	 * aipZYAuthRequest.setGoodsId(gdsInterfaceGdsGidxRespDTO.getOriginGdsId());
	 * String GDS_BOOK_SHOU_QUAN_ADDRESS =
	 * SysCfgUtil.fetchSysCfg("GDS_BOOK_SHOU_QUAN_ADDRESS").getParaValue();
	 * aipZYAuthRequest.setAuthUrl(GDS_BOOK_SHOU_QUAN_ADDRESS); result =
	 * iAipZYAuthRSV.sendAuthRequest(aipZYAuthRequest); if
	 * (aipZYAuthRequest._ZVING_STATUS_OK.equals(result.getStatus())) { String
	 * GDS_DIGITS_BOOK_CAT_CODE =
	 * SysCfgUtil.fetchSysCfg("GDS_DIGITS_BOOK_CAT_CODE").getParaValue(); String
	 * GDS_E_BOOK_CAT_CODE =
	 * SysCfgUtil.fetchSysCfg("GDS_E_BOOK_CAT_CODE").getParaValue();
	 * GdsCategoryReqDTO dto = new GdsCategoryReqDTO();
	 * dto.setCatgCode(gdsReadOnlineVO.getCatgCode()); List<GdsCategoryRespDTO>
	 * list = igdsCategoryRSV.queryCategoryTraceUpon(dto); if (list != null &&
	 * list.size() > 0) { for (GdsCategoryRespDTO gdsCategoryRespDTO : list) {
	 * if (GDS_DIGITS_BOOK_CAT_CODE.equals(gdsCategoryRespDTO.getCatgCode())) {
	 * vo.setResultMsg("人卫教材试读授权成功,请下载人卫教材APP试读"); break; } else if
	 * (GDS_E_BOOK_CAT_CODE.equals(gdsCategoryRespDTO.getCatgCode())) {
	 * vo.setResultMsg("人卫书城试读授权成功,请下载人卫书城APP试读"); break; } } }
	 * vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS); } else {
	 * vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
	 * vo.setResultMsg("在线试读授权失败！"); } } catch (BusinessException e) {
	 * LogUtil.error(MODULE, "获取数字教材授权阅读失败！", e);
	 * vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
	 * vo.setResultMsg("在线试读授权失败！原因是：" + e.getMessage()); } catch (Exception e)
	 * { LogUtil.error(MODULE, "获取数字教材授权阅读失败！", e);
	 * vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
	 * vo.setResultMsg("在线试读授权失败！原因是：" + e.getMessage()); } return vo; }
	 */

	/**
	 * 
	 * readPdfBuffer:(通过流方式读取pdf). <br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author gxq
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/readpdfbuffer")
	public void readPdfBuffer(HttpServletResponse response, GdsPdfVO gdsPdfVO) {
		response.setCharacterEncoding("UTF-8");
		ServletOutputStream outputStream = null;
		try {
			byte[] b = FileUtil.readFile(gdsPdfVO.getPdfId());
			outputStream = response.getOutputStream();
			if (b != null && b.length > 0) {
				outputStream.write(b);
			}
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * querygdsbyisbn:(根据isbn 查询除了当前商品以外是否还有纸质书或者电子书). <br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author gxq
	 * @param model
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/querygdsbyisbn")
	@ResponseBody
	public Model querygdsbyisbn(Model model, GdsParseISBNVO gdsParseISBNVO) {
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
			model.addAttribute("list", resultList);
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "根据ISBN号获取商品信息失败！", e);
			model.addAttribute("list", resultList);
		} catch (Exception e) {
			LogUtil.error(MODULE, "根据ISBN号获取商品信息失败！", e);
			model.addAttribute("list", resultList);
		}
		return model;
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
		    GdsSkuInfoReqDTO skuInfoQuery = new GdsSkuInfoReqDTO();
		    skuInfoQuery.setId(gdsDetailVO.getSkuId());
		    GdsSkuInfoRespDTO skuInfoRespDTO = iGdsSkuInfoQueryRSV.queryGdsSkuInfoResp(skuInfoQuery);
		    LongReqDTO longReqDTO = new LongReqDTO();
		    longReqDTO.setId(skuInfoRespDTO.getGdsTypeId());
		    if(gdsInfoExternalRSV.isGdsTypeBuyMore(longReqDTO)){
		        map.put("buyedFlag", false);
		    }else{
		        List<com.zengshi.ecp.order.dubbo.dto.ROrdSubStaffIdxResp> results = ordSubRSV
	                    .queryOrderSubByStaffIdAndSkuid(rordSubStaffIdxReq);
	            if (CollectionUtils.isNotEmpty(results)) {
	                map.put("buyedFlag", true);
	            } else {
	                map.put("buyedFlag", false);
	            } 
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

	@RequestMapping("/html/{fileId}")
	public String detail(@PathVariable("fileId") String fileId, HttpServletRequest req) {
		String content = FileUtil.readFile2Text(fileId, null);
		req.setAttribute("content", content);
		return URL + "/list/detail";
	}
	
	
	@RequestMapping("/html/baseinfo/{gdsId}-{skuId}")
    public String getBaseInfo(@PathVariable("gdsId") Long gdsId, @PathVariable("skuId") Long skuId, Model model) {
	    GdsInfoReqDTO dto = new GdsInfoReqDTO();
        if (null != gdsId) {
            dto.setId(gdsId);
        }
        if (null != skuId) {
            dto.setSkuId(skuId);
        }
        GdsQueryOption[] gdsQueryOptions = new GdsQueryOption[] { GdsQueryOption.BASIC, GdsQueryOption.GDSTYPE};
        SkuQueryOption[] skuQueryOptions = new SkuQueryOption[] { SkuQueryOption.BASIC, SkuQueryOption.PROP,
                SkuQueryOption.SHOWSTOCK, SkuQueryOption.CAlDISCOUNT };
        dto.setGdsQueryOptions(gdsQueryOptions);
        dto.setSkuQuerys(skuQueryOptions);
        GdsInfoDetailRespDTO resultDto = null;
        try {
            resultDto = iGdsInfoQueryRSV.queryGdsInfoDetail(dto);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "无法获取商品详情信息！", e);
        }
        model.addAttribute("gdsDetailInfo", resultDto);
        return URL + "/list/baseinfo";
    }
	
	@RequestMapping("/html/contentinfo/{gdsId}-{skuId}")
	 public String geTContentInfo(@PathVariable("gdsId") Long gdsId, @PathVariable("skuId") Long skuId, Model model) {
		    GdsInfoReqDTO dto = new GdsInfoReqDTO();
	        if (null != gdsId) {
	            dto.setId(gdsId);
	        }
	        if (null != skuId) {
	            dto.setSkuId(skuId);
	        }
	        GdsQueryOption[] gdsQueryOptions = new GdsQueryOption[] { GdsQueryOption.BASIC, GdsQueryOption.GDSTYPE};
	        SkuQueryOption[] skuQueryOptions = new SkuQueryOption[] { SkuQueryOption.BASIC, SkuQueryOption.PROP};
	        dto.setGdsQueryOptions(gdsQueryOptions);
	        dto.setSkuQuerys(skuQueryOptions);
	        GdsInfoDetailRespDTO resultDto = null;
	        try {
	            resultDto = iGdsInfoQueryRSV.queryGdsInfoDetail(dto);
	            if(resultDto!=null && resultDto.getSkuInfo()!=null){
	            	Map<String, GdsPropRespDTO> richTextMap = resultDto.getSkuInfo().getRichTextPropMap();
	            	if (richTextMap != null) {
	            		for (String propId : richTextMap.keySet()){
	            			List<GdsPropValueRespDTO> richPropList = richTextMap.get(propId).getValues();
	            			for (GdsPropValueRespDTO gdsPropValueRespDTO : richPropList) {
	            				if (StringUtil.isNotBlank(gdsPropValueRespDTO.getPropValue())) {
	            				//	gdsPropValueRespDTO.setPropValue(getHtmlUrl(gdsPropValueRespDTO.getPropValue()));
	            					gdsPropValueRespDTO.setPropValue(FileUtil.readFile2Text(gdsPropValueRespDTO.getPropValue(), "UTF-8"));
	            				}
	            			}
	            		}
	            	}
	            }
	            if(resultDto!=null && StringUtil.isNotBlank(resultDto.getGdsDesc())){
	                resultDto.setGdsDesc(FileUtil.readFile2Text(resultDto.getGdsDesc(), "UTF-8"));
	            }
	            
	        } catch (BusinessException e) {
	            LogUtil.error(MODULE, "无法获取商品详情信息！", e);
	        }
	        model.addAttribute("gdsDetailInfo", resultDto);
	        return URL + "/list/contentinfo";
	    }
		
	
	
	
	@RequestMapping("test/{gdsId}-{skuId}")
	public String test(@PathVariable("gdsId") String gdsId, @PathVariable("skuId") String skuId, Model model,
			GdsDetailVO gdsDetailVO) {
		GdsInfoReqDTO dto = new GdsInfoReqDTO();
		if (StringUtil.isNotEmpty(gdsDetailVO.getGdsId())) {
			dto.setId(gdsDetailVO.getGdsId());
		}
		if (StringUtil.isNotEmpty(gdsDetailVO.getSkuId())) {
			dto.setSkuId(gdsDetailVO.getSkuId());
		}
		GdsQueryOption[] gdsQueryOptions = new GdsQueryOption[] { GdsQueryOption.BASIC, GdsQueryOption.GDSTYPE};
		SkuQueryOption[] skuQueryOptions = new SkuQueryOption[] { SkuQueryOption.BASIC, SkuQueryOption.PROP,
				SkuQueryOption.SHOWSTOCK, SkuQueryOption.CAlDISCOUNT };
		dto.setGdsQueryOptions(gdsQueryOptions);
		dto.setSkuQuerys(skuQueryOptions);
		GdsInfoDetailRespDTO resultDto = null;
		try {
			resultDto = iGdsInfoQueryRSV.queryGdsInfoDetail(dto);
			if (resultDto != null && resultDto.getSkuInfo() != null) {
				// 发送消息
				sendRecentlyBrowMsg(resultDto);
				model.addAttribute("shopId", resultDto.getShopId());

			} else {
				resultDto = new GdsInfoDetailRespDTO();
				GdsSkuInfoRespDTO gdsSkuInfoRespDTO = new GdsSkuInfoRespDTO();
				gdsSkuInfoRespDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_DELETE);
				resultDto.setSkuInfo(gdsSkuInfoRespDTO);
				model.addAttribute("gdsDetailInfo", resultDto);
				model.addAttribute("shopId", resultDto.getShopId());
				return URL + "/gdsdetailClone";
			}
		} catch (BusinessException e) {
			if (resultDto == null || GdsErrorConstants.GdsInfo.ERROR_GOODS_GDSINFO_210005.equals(e.getErrorCode())) {
				resultDto = new GdsInfoDetailRespDTO();
				GdsSkuInfoRespDTO gdsSkuInfoRespDTO = new GdsSkuInfoRespDTO();
				gdsSkuInfoRespDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_DELETE);
				resultDto.setSkuInfo(gdsSkuInfoRespDTO);
				model.addAttribute("gdsDetailInfo", resultDto);
				return URL + "/gdsdetailClone";
			}
			LogUtil.error(MODULE, "无法获取商品详情信息！", e);
		}
		String shopName = "";
		String stockStatus = "";
		String stockStatusDesc = "";
		if (StringUtil.isNotEmpty(resultDto)) {
			if (resultDto.getSkuInfo() != null) {
				stockStatus = GdsUtils.checkStcokStatus(resultDto.getSkuInfo().getRealAmount());
				stockStatusDesc = GdsUtils.checkStcokStatusDesc(resultDto.getSkuInfo().getRealAmount());
//				Map<String, GdsPropRespDTO> map = resultDto.getSkuInfo().getAllPropMaps();
				//富文本取数统一改成这样
				Map<String, GdsPropRespDTO> richTextMap = resultDto.getSkuInfo().getRichTextPropMap();
				int detailCount = 0;
				if (richTextMap != null) {
				    for (String propId : richTextMap.keySet()){
				        List<GdsPropValueRespDTO> richPropList = richTextMap.get(propId).getValues();
				        for (GdsPropValueRespDTO gdsPropValueRespDTO : richPropList) {
                            if (StringUtil.isNotBlank(gdsPropValueRespDTO.getPropValue())) {
                                gdsPropValueRespDTO.setPropValue(getHtmlUrl(gdsPropValueRespDTO.getPropValue()));
                                detailCount++;
                            }
                        }
				    }
                }
//				String[] strs = new String[] { "1020", "1021", "1022", "1023", "1024", "1025" };
//				int detailCount = 0;
//				if (map != null) {
//					for (int i = 0; i < strs.length; i++) {
//						GdsPropRespDTO gdsPropRespDTO = map.get(strs[i]);
//						if (gdsPropRespDTO != null && gdsPropRespDTO.getValues() != null) {
//							for (GdsPropValueRespDTO gdsPropValueRespDTO : gdsPropRespDTO.getValues()) {
//								if (StringUtil.isNotBlank(gdsPropValueRespDTO.getPropValue())) {
//									gdsPropValueRespDTO.setPropValue(getHtmlUrl(gdsPropValueRespDTO.getPropValue()));
//									detailCount++;
//								}
//							}
//						}
//					}
//				}
				// 根据录入的判断是否有目录等这些属性，如果这些都没有，则按照录入来说就会提供产品详情的录入，此时只展示产品详情
				if (detailCount == 0) {
					// 针对人卫，只显示产品详情
					if (StringUtil.isNotBlank(resultDto.getGdsDesc())) {
						resultDto.setGdsDesc(getHtmlUrl(resultDto.getGdsDesc()));
					}
					// resultDto.setGdsPartlist(getHtmlUrl(resultDto.getGdsPartlist()));
				}
			}
			if (GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES.equals(resultDto.getSkuInfo().getGdsStatus())
					|| GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES.equals(resultDto.getSkuInfo().getGdsStatus())) {
				// 获取相关分类
				getGdsDownCommonCat(resultDto, model);
			}
			ShopInfoResDTO shopInfo = iShopInfoRSV.findShopInfoByShopID(resultDto.getShopId());
			if (StringUtil.isNotEmpty(shopInfo)) {
				shopName = shopInfo.getShopName();
			} else {
				throw new BusinessException("web.gds.2000012");
			}
		}
		// 获取库存阈值的配置参数
		getStockParam(model);
		model.addAttribute("shopName", shopName);
		model.addAttribute("stockStatus", stockStatus);
		model.addAttribute("stockStatusDesc", stockStatusDesc);
		model.addAttribute("gdsDetailInfo", resultDto);
//		LongReqDTO gdsTypeQuery = new LongReqDTO();
//		gdsTypeQuery.setId(resultDto.getGdsTypeId());
//		GdsTypeRespDTO gdsTypeRespDTO = gdsTypeRSV.queryGdsTypeByPK(gdsTypeQuery);
		model.addAttribute("gdsType", resultDto.getGdsTypeRespDTO());

		BaseSysCfgRespDTO baseSysCfgRespDTO = SysCfgUtil.fetchSysCfg("SHOP_SHOW_LOCK");
		model.addAttribute("shopShow", baseSysCfgRespDTO.getParaValue());
		
		try{
		    BaseSysCfgRespDTO unitCfg = SysCfgUtil.fetchSysCfg(GdsConstants.SysCfgConstants.MALL_GDS_DETAIL_UNIT);
		    // 设置商品信息单位.
		    model.addAttribute("gdsInfoUnit",unitCfg.getParaValue());
		}catch(Exception e){
		    LogUtil.error(MODULE, "获取键值为["+GdsConstants.SysCfgConstants.MALL_GDS_DETAIL_UNIT+"]的配置参数异常!");
		}
		
		
		
		return URL + "/gdsdetailClone";
	}
	
	@RequestMapping("/share")
    public ModelAndView share(Model model,HttpServletResponse response,HttpServletRequest request) throws IOException 
    { 
//		CookieUtil.deleteCookie(request, response, "shareMsg");
		boolean isphone = false;
        boolean pcFlag = false;
        boolean mobileFlag = false;
        String gdsId = request.getParameter("gdsId");
        String skuId = request.getParameter("skuId");
        //分享送积分写入cookie
        String staffId_temp = request.getParameter("staffId");
//        String staffId;
//        if(StringUtil.isBlank(staffId_temp) || StringUtil.isEmpty(staffId_temp) || !isNumeric(staffId_temp)){
//        	staffId="0";
//        }else{
//        }
        String staffId =CipherUtil.decrypt(staffId_temp);
//        String staffId ="12";
        ShareMsgDto shareDto = new ShareMsgDto();
        shareDto.setSkuId(skuId);
        shareDto.setGdsId(gdsId);
        shareDto.setStaffId(staffId);
        String jsonString="";
        if(!staffId.equals("0")){
        	jsonString = JSONObject.toJSONString(shareDto);
        }
        List<String> cookielist = new ArrayList<String>();
        try {
        	String exitValue = CookieUtil.getCookieValue(request, "shareMsg");
        	JSONArray ja=JSONArray.parseArray(exitValue);
        	boolean isEqualGdsId=false;
        	boolean isEqualStaffId=false;
        	if(exitValue!=null){
        		for(int i=0;i<ja.size();i++){
        			JSONObject jo= ja.getJSONObject(i); //转换成JSONObject对象
        			ShareMsgDto shareDto_cookie=JSONObject.parseObject(jo.toJSONString(),ShareMsgDto.class);    //转换成JavaBean
        			if(shareDto_cookie.getGdsId().equals(gdsId)){
        				isEqualGdsId = true;
        			}
        			if(shareDto_cookie.getStaffId().equals(staffId)){
        				isEqualStaffId = true;
        			}
        		}
        	}
        	if(exitValue==null || exitValue.equals("")){
        		if(!staffId.equals("0")){
        			cookielist.add(jsonString);
        			CookieUtil.addCookie(response, "shareMsg", cookielist.toString(), 60*60*24);
        		}
        	}else{
        		if(isEqualGdsId==false && isEqualStaffId == true){//同个分享者分享多个商品
        			cookielist.clear();
        			for(int i=0;i<ja.size();i++){
        				JSONObject jo= ja.getJSONObject(i); //转换成JSONObject对象
        				ShareMsgDto shareDto_new=JSONObject.parseObject(jo.toJSONString(),ShareMsgDto.class);    //转换成JavaBean
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
        				ShareMsgDto shareDto_new=JSONObject.parseObject(jo.toJSONString(),ShareMsgDto.class);    //转换成JavaBean
        				String jsonString_new = JSONObject.toJSONString(shareDto_new);
        				cookielist.add(jsonString_new);
        			}
        			cookielist.add(jsonString);
        			CookieUtil.deleteCookie(request, response, "shareMsg");
        			CookieUtil.addCookie(response, "shareMsg", cookielist.toString(), 60*60*24);
        		};
        	}
		} catch (Exception e) {
			LogUtil.info(MODULE, "获取cookie出错或者没有对应cookie!");
		}
        //判断是否为微信浏览器
        boolean isWX=false;
        String ua = request.getHeader("user-agent")  
                .toLowerCase();  
        if (ua.indexOf("micromessenger") > 0) {// 是微信浏览器  
            isWX = true;  
        }
        //判断PC 还是手机端
//        String via = request.getHeader("Via");
//        String userAgent = request.getHeader("user-agent");
//        for (int i = 0; via!=null && !via.trim().equals("") && i < mobileGateWayHeaders.length; i++) {
//            if(via.contains(mobileGateWayHeaders[i])){
//                mobileFlag = true;
//                break;
//            }
//        }
//        for (int i = 0;!mobileFlag && userAgent!=null && !userAgent.trim().equals("") && i < mobileUserAgents.length; i++) {
//            if(userAgent.contains(mobileUserAgents[i])){
//                mobileFlag = true;
//                break;
//            }
//        }
//        for (int i = 0; userAgent!=null && !userAgent.trim().equals("") && i < pcHeaders.length; i++) {
//            if(userAgent.contains(pcHeaders[i])){
//                pcFlag = true;
//                break;
//            }
//        }
//        if(mobileFlag==true && pcFlag==false){
//        	isphone=true;
//        }
        String weixinUrl= BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_SITE_MAPPING, String.valueOf(1L));  
        CmsSiteRespDTO cms = CmsCacheUtil.getCmsSiteCache(1L);
        String pcUrl = "";
        if (cms != null && StringUtil.isNotBlank(cms.getSiteUrl())) {
        	pcUrl = cms.getSiteUrl();
            boolean c = pcUrl.endsWith("/");
            if(c){
            	pcUrl = pcUrl.substring(0, pcUrl.length()-1)+"/gdsdetail/" + gdsId+"-"+skuId;
            }else{
            	pcUrl = pcUrl+"/gdsdetail/" + gdsId+"-"+skuId;
            	//pcUrl = "http://127.0.0.1:8280/ecp-web-mall/gdsdetail/"+ gdsId+"-"+skuId;
            }
//            return baseUrl + "/gdsdetail/html/" + gdsIdAndSkuId;
//            pcUrl = pcUrl.substring(0, pcUrl.length()-1)+"/gdsdetail/" + gdsId+"-"+skuId;
        }
        ModelAndView mv ;
        if(isWX){
        	 mv = new ModelAndView("redirect:"+weixinUrl+"/gdsdetail/" + gdsId+"-"+skuId+"?staffId="+staffId_temp);//redirect模式
        }else{
        	 mv = new ModelAndView("redirect:"+pcUrl);//redirect模式
        }
        return mv;
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
    public boolean isNumeric(String str){
    	boolean isN=false;
    	if(StringUtil.isBlank(str)){
    		Pattern pattern = Pattern.compile("[0-9]*");
    		Matcher isNum = pattern.matcher(str);
    		isN=isNum.matches();
    	}
        return isN;
    }

}
