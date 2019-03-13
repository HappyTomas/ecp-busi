/**
 * 
 */
package com.zengshi.ecp.busi.pageConfig.pageConfig.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorGdsRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutPreRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutTypeRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageAttrPreRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageConfigRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageTypeRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.prom.dubbo.dto.PromListRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * CMS模板化 - 页面预览
 * 
 * @author Terry
 *
 */
@Controller
@RequestMapping(value="/page-pre")
public class CmsPagePreController extends EcpBaseController {

    @Resource(name = "cmsPageInfoRSV")
    private ICmsPageInfoRSV cmsPageInfoRSV;
    
    @Resource(name = "cmsPageTypeRSV")
    private ICmsPageTypeRSV cmsPageTypeRSV;
    
    @Resource(name = "cmsLayoutPreRSV")
    private ICmsLayoutPreRSV cmsLayoutPreRSV;
    
    @Resource(name = "cmsLayoutTypeRSV")
    private ICmsLayoutTypeRSV cmsLayoutTypeRSV;
    
    @Resource(name = "cmsModularRSV")
    private ICmsModularRSV cmsModularRSV;
    
    @Resource(name = "cmsPageAttrPreRSV")
    private ICmsPageAttrPreRSV cmsPageAttrPreRSV;
    
    @Resource(name = "cmsPageConfigRSV")
    private ICmsPageConfigRSV cmsPageConfigRSV;
    
    @Resource(name = "cmsFloorGdsRSV")
    private ICmsFloorGdsRSV cmsFloorGdsRSV;
    
    @Resource(name = "gdsInfoQueryRSV")
    private IGdsInfoQueryRSV gdsInfoQueryRSV;
    
    @Resource
    private IPromQueryRSV promQueryRSV;
    /**
     * 
     * 功能描述：初始化
     *
     * @author  曾海沥(Terry)
     * <p>创建日期 ：2016年5月13日 下午6:49:41</p>
     *
     * @param model
     * @param pageId
     * @return
     * @throws Exception
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/init")
    public String init(Model model, Long pageId) throws Exception{
    	CmsPageInfoReqDTO pinfo = new CmsPageInfoReqDTO();
    	pinfo.setId(pageId);
    	List<CmsLayoutPreRespDTO> pageConfig  = new ArrayList<CmsLayoutPreRespDTO>();
    	CmsPageInfoReqDTO arg0 = new CmsPageInfoReqDTO();
        arg0.setId(pageId);
        CmsPageInfoRespDTO pageInfo = cmsPageInfoRSV.queryCmsPageInfo(arg0);
        pageConfig = cmsPageConfigRSV.initPageConfigPre(pinfo); 
    	CmsPageAttrPreRespDTO pageAttr = this.qryPageAttrPreByPgId(pageId);
    	model.addAttribute("pc", true);
    	model.addAttribute("pageConfig", pageConfig);
    	model.addAttribute("pageId", pageId);
    	model.addAttribute("pageAttr",pageAttr);
    	model.addAttribute("pageTypeId",pageInfo.getPageTypeId()+"");
    	model.addAttribute("pageInfo", pageInfo);
    	// WEB端页面
		if (CmsConstants.PlatformType.CMS_PLATFORMTYPE_01.equals(pageInfo.getPlatformType())) {
			return "/pageConfig/pageConfig/preview/page-common-preview";
		} else {// 移动端页面
			return "/pageConfig/pageConfigWap/preview/page-common-preview-wap";
		}
    }
    /**
     * 
     * 功能描述：初始化
     *
     * @author  曾海沥(Terry)
     * <p>创建日期 ：2016年5月13日 下午6:49:41</p>
     *
     * @param model
     * @param pageId
     * @return
     * @throws Exception
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/withoutLogin")
    public String withoutLogin(Model model, Long pageId) throws Exception{
    	CmsPageInfoReqDTO pinfo = new CmsPageInfoReqDTO();
    	pinfo.setId(pageId);
    	List<CmsLayoutPreRespDTO> pageConfig  = new ArrayList<CmsLayoutPreRespDTO>();
    	CmsPageInfoReqDTO arg0 = new CmsPageInfoReqDTO();
    	arg0.setId(pageId);
    	CmsPageInfoRespDTO pageInfo = cmsPageInfoRSV.queryCmsPageInfo(arg0);
    	pageConfig = cmsPageConfigRSV.initPageConfigPre(pinfo); 
    	CmsPageAttrPreRespDTO pageAttr = this.qryPageAttrPreByPgId(pageId);
    	model.addAttribute("pc", false);
    	model.addAttribute("pageConfig", pageConfig);
    	model.addAttribute("pageId", pageId);
    	model.addAttribute("pageAttr",pageAttr);
    	model.addAttribute("pageTypeId",pageInfo.getPageTypeId()+"");
    	model.addAttribute("pageInfo", pageInfo);
    	// 移动端页面
		return "/pageConfig/pageConfigWap/preview/page-common-preview-wap";
    }
    
    /**
     * 
     * 功能描述：根据布局项目读取布局项详细配置内容
     *
     * @author  曾海沥(Terry)
     * <p>创建日期 ：2016年5月11日 上午9:39:01</p>
     *
     * @param model
     * @return
     * @throws Exception
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/loadLayoutItemProp")
    @ResponseBody
    public JSONObject loadLayoutItemProp(Model model,Long itemId) throws Exception{
    	CmsLayoutItemPreReqDTO req = new CmsLayoutItemPreReqDTO();
    	//req.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
    	req.setId(itemId);
    	CmsLayoutItemPreRespDTO resp = cmsPageConfigRSV.queryLayoutItemPre(req);
    	JSONObject obj = new JSONObject();
    	obj.put("itemProp", resp);
    	return obj;
    }
    
    /**
     * 
     * 功能描述：获得页面属性
     *
     * @author  曾海沥(Terry)
     * <p>创建日期 ：2016年5月13日 下午4:17:16</p>
     *
     * @param pageId
     * @return
     * @throws Exception
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    private CmsPageAttrPreRespDTO qryPageAttrPreByPgId(Long pageId) throws Exception{
        CmsPageAttrPreRespDTO pageAttrPre = null;
        if(StringUtil.isNotEmpty(pageId)){
            CmsPageAttrPreReqDTO reqDto = new CmsPageAttrPreReqDTO();
            reqDto.setPageId(pageId);
            
            List<CmsPageAttrPreRespDTO> pageAttrPres = null;
            pageAttrPres = this.cmsPageAttrPreRSV.queryCmsAttrPreList(reqDto);
            if(CollectionUtils.isNotEmpty(pageAttrPres)){
                pageAttrPre = pageAttrPres.get(0);
            }
            //扩展数据
            if(pageAttrPre !=null ){
                //扩展背景图展示方式
                if(StringUtil.isBlank(pageAttrPre.getBackgroupShowType())){//空则设置为不平铺
                    pageAttrPre.setBackgroupShowType(CmsConstants.BackgroupShowType.CMS_BACKGROUPSHOWTYPE_02);
                }
            }
        }
        return pageAttrPre;
    }
    
    /**
     * 
     * 功能描述：根据商品ID获得商品
     *
     * @author  曾海沥(Terry)
     * <p>创建日期 ：2016年5月13日 下午6:55:44</p>
     *
     * @param model
     * @param goodsIds 商品ID集，使用,隔开
     * @return
     * @throws Exception
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/loadGoodsInfo")
    @ResponseBody
    public JSONObject loadGoodsInfo(Model model,String ids) throws Exception{
    	if(StringUtils.isEmpty(ids) || ids.equals("null") || ids.equals("undefined")) return null;
    	List<GdsInfoDetailRespDTO> gdss = new ArrayList<GdsInfoDetailRespDTO>();
    	
    	String[] gdsIds = ids.split(",");
    	for (String id : gdsIds) {
    		GdsInfoReqDTO req = new GdsInfoReqDTO();
    		req.setId(new Long(id));
    		GdsInfoDetailRespDTO dto=getGdsDetail(req);
    		dto.setUrl(CmsCacheUtil.getCmsSiteCache(1L).getSiteUrl()+dto.getUrl());
    		gdss.add(dto);
		}
    	JSONObject obj = new JSONObject();
    	obj.put("goodslist", gdss);
    	return obj;
    }
    
    /**
     * 
     * 功能描述：获得商品明细
     *
     * @author  曾海沥(Terry)
     * <p>创建日期 ：2016年5月13日 下午7:12:46</p>
     *
     * @param req
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    private GdsInfoDetailRespDTO getGdsDetail(GdsInfoReqDTO req){
    	if(req ==  null) return null;
    	GdsQueryOption[] gdsOptions=new GdsQueryOption[]{
    		GdsQueryOption.BASIC,GdsQueryOption.MAINPIC
        };
    	req.setGdsQueryOptions(gdsOptions);
    	return gdsInfoQueryRSV.queryGdsInfoDetail(req);
    }
    
    /**
     * 
     * 功能描述：根据楼层ID获得楼层下的所有商品
     *
     * @author  曾海沥(Terry)
     * <p>创建日期 ：2016年5月13日 下午6:56:05</p>
     *
     * @return
     * @throws Exception
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/loadGoodsInfoByFloor")
    @ResponseBody
    public JSONObject loadGoodsInfoByFloor(Model model,String ids) throws Exception {
    	if(StringUtils.isEmpty(ids)) return null;
    	List<GdsInfoDetailRespDTO> gdss = new ArrayList<GdsInfoDetailRespDTO>();
    	String[] flrIds = ids.split(",");
    	
    	for (String id : flrIds) {
    		CmsFloorGdsReqDTO req = new CmsFloorGdsReqDTO();
        	req.setFloorId(new Long(id));
        	List<CmsFloorGdsRespDTO> floorGdsList = cmsFloorGdsRSV.queryCmsFloorGdsList(req);
        	for (CmsFloorGdsRespDTO floorgds : floorGdsList) {
        		GdsInfoReqDTO gdsreq = new GdsInfoReqDTO();
        		gdsreq.setId(floorgds.getGdsId());
        		GdsInfoDetailRespDTO dto=getGdsDetail(gdsreq);
        		queryGdsPromByID(dto, floorgds.getPromId());
        		dto.setUrl(CmsCacheUtil.getCmsSiteCache(1L).getSiteUrl()+dto.getUrl());
        		gdss.add(dto);
			}
		}
    	JSONObject obj = new JSONObject();
    	obj.put("goodslist", gdss);
    	return obj;
    }
    
    
    /**
     * 
     * queryGdsProm:(获取商品促销信息). <br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author zhanbh
     * @return
     * @since JDK 1.6
     */
    private GdsInfoDetailRespDTO queryGdsPromByID(GdsInfoDetailRespDTO gdsInfo,Long promId) {
        PromListRespDTO respDto = null;
        List<PromListRespDTO> list = null ;
        if(gdsInfo!=null && StringUtil.isNotEmpty(gdsInfo.getId())){
            if(gdsInfo.getSkuInfo() == null){
               return null;
            }
            try {
                //1.查询促销
                PromRuleCheckDTO promRuleCheckDTO = new PromRuleCheckDTO();
                CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
                promRuleCheckDTO.setSiteId(promRuleCheckDTO.getCurrentSiteId());
                promRuleCheckDTO.setStaffId(custInfoReqDTO.getStaff().getId() + "");
                promRuleCheckDTO.setGdsId(gdsInfo.getId());
                promRuleCheckDTO.setChannelValue("1");
                promRuleCheckDTO.setShopId(gdsInfo.getShopId());
                promRuleCheckDTO.setCalDate(new Date(System.currentTimeMillis()));
                promRuleCheckDTO.setSkuId(gdsInfo.getSkuInfo().getId());
                promRuleCheckDTO.setBasePrice(gdsInfo.getSkuInfo().getRealPrice());
                promRuleCheckDTO.setBuyPrice(gdsInfo.getSkuInfo().getDiscountPrice());
                promRuleCheckDTO.setGdsName(gdsInfo.getGdsName());
                //promRuleCheckDTO.setShopName(gdsPromReqVO.getShopName());
                if(StringUtil.isNotEmpty(promId)){
                    promRuleCheckDTO.setPromId(promId);
                }
                list = promQueryRSV.listProm(promRuleCheckDTO);
            } catch (BusinessException e) {
                LogUtil.error(this.getClass().getName(), "获取促销列表失败", e);
                list =null;
            }
            //2.价格数据修改
            if(CollectionUtils.isNotEmpty(list)){
                respDto = list.get(0);
                if(respDto.getPromSkuPriceRespDTO() != null){
                    if(StringUtil.isNotEmpty(respDto.getPromSkuPriceRespDTO().getDiscountCaclPrice())){
                        gdsInfo.setGuidePrice(respDto.getPromSkuPriceRespDTO().getDiscountCaclPrice().longValue());
                    }
                    if(StringUtil.isNotEmpty(respDto.getPromSkuPriceRespDTO().getDiscountFinalPrice())){
                        gdsInfo.getSkuInfo().setDiscountPrice(respDto.getPromSkuPriceRespDTO().getDiscountFinalPrice().longValue());
                    }
                }
            }
            
        }
        
        return gdsInfo;
    }

}
