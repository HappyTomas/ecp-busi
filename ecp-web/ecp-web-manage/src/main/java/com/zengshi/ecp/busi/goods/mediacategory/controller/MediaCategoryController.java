/** 
 * Project Name:ecp-web-manage 
 * File Name:GdsCategoryManageController.java 
 * Package Name:com.zengshi.ecp.busi.goods.category 
 * Date:2015年8月29日下午4:15:30 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.goods.mediacategory.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.mvc.annotation.NativeJson;
import com.zengshi.ecp.base.velocity.AiToolUtil;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.goods.common.GdsBaseController;
import com.zengshi.ecp.busi.goods.mediacategory.vo.MediaCategoryRespVO;
import com.zengshi.ecp.busi.goods.mediacategory.vo.MediaCategoryTreeVO;
import com.zengshi.ecp.busi.goods.mediacategory.vo.MediaCategoryVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.StringReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsMediaCategoryRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopCacheRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.ResourceMsgUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * 媒体分类管理Controller <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015年8月29日下午4:15:30  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
@Controller
@RequestMapping("/goods/mediacatg")
public class MediaCategoryController extends GdsBaseController{
	
	private static final String BASE_URL = "/goods/gdsmediacatg/mediacatg-";
	
	/**
	 * 分类树店铺ID前缀。
	 */
	public static final String SHOP_ID_PREFIX = "SHOP-";
	
	//private static final String BASE_OPEN_URL = "/goods/mediacatg/open/";

    @Resource(name="gdsMediaCategoryRSV")
    private IGdsMediaCategoryRSV gdsMediaCategoryRSV;
    
    @Resource(name="shopCacheRSV")
    private IShopCacheRSV shopCacheRSV;
    @Resource(name="gdsCategoryRSV")
    private IGdsCategoryRSV gdsCategoryRSV;
    
    /**
     * 
     * 导航至主体页面。 
     * 
     * @author liyong7
     * @param catgType
     * @param model
     * @param reqVO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/main")
    public String mainPage(Model model,MediaCategoryVO reqVO)throws Exception{
    	LogUtil.debug(MODULE, "导航到媒体分类主页面");
    	model.addAttribute("reqVO",reqVO);
    	// 查询最大层级配置.
    	BaseSysCfgRespDTO sysCfgRespDTO = SysCfgUtil.fetchSysCfg("GDS_CATEGORY_MAX_LEVEL");
    	if(null != sysCfgRespDTO){
    		model.addAttribute("maxLevel", sysCfgRespDTO.getParaValue());
    	}
    	return BASE_URL+"main";
    }
    
    /**
     * 
     * 执行分类信息更新。
     * 
     * @author liyong7
     * @param model
     * @param reqVO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
//    @RequestMapping("/mediacatgupdate")
//    @ResponseBody
    public MediaCategoryRespVO updateCategory(Model model,@Valid MediaCategoryVO reqVO)throws Exception{
    	LogUtil.info(MODULE, String.format("更新分类信息，参数：%s", ToStringBuilder.reflectionToString(reqVO)));
    	MediaCategoryRespVO respVO = new MediaCategoryRespVO();
    	try{
    		GdsMediaCategoryReqDTO reqDTO = new GdsMediaCategoryReqDTO();
        	ObjectCopyUtil.copyObjValue(reqVO, reqDTO, null, false);
        	gdsMediaCategoryRSV.editGdsMediaCategory(reqDTO);
        	respVO.setRespDTO(GdsUtils.doObjConvert(reqDTO, GdsMediaCategoryRespDTO.class, null, null));
    		respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
    		
    	}catch (BusinessException e) {
    		LogUtil.error(MODULE, String.format("编辑媒体分类信息异常，分类ID=%s", reqVO.getCatgCode()));
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            respVO.setResultMsg(e.getErrorMessage());
		}
    	return respVO;
    }
    
    
    
    /**
     * 
     * 分类树异步获取数据。
     * 
     * @author liyong7
     * @param model
     * @param reqVO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/asyncData/getNodes")
    @ResponseBody
    @NativeJson(true)
    public String getNodes(Model model,MediaCategoryVO reqVO)throws Exception{
    	LogUtil.info(MODULE,"获取媒体分类树型节点,参数"+ToStringBuilder.reflectionToString(reqVO));
    	List<MediaCategoryTreeVO> tree = new ArrayList<MediaCategoryTreeVO>();
    	
    	// 获取zTree自动参数.
    	String id = reqVO.getId();
    	if(StringUtil.isBlank(reqVO.getId())){
    		// 获取店铺信息.
			Map<Long, ShopInfoResDTO> map = shopCacheRSV.getCacheShop();
    		if(MapUtils.isNotEmpty(map)){
    			Set<Entry<Long, ShopInfoResDTO>> entrySet = map.entrySet();
    			for (Iterator<Entry<Long, ShopInfoResDTO>> iterator = entrySet.iterator(); iterator
						.hasNext();) {
					Entry<Long, ShopInfoResDTO> entry = (Entry<Long, ShopInfoResDTO>) iterator
							.next();
					ShopInfoResDTO dto = entry.getValue();
					if(null == dto || GdsUtils.isEqualsInvalid(dto.getShopStatus())){
						continue;
					}
					MediaCategoryTreeVO item = buildShopNodeItem(dto, null);
					tree.add(item);
				}
        	}
    	}else{
    		
    		List<GdsMediaCategoryRespDTO> catgLst = null;
    		
    		GdsMediaCategoryReqDTO reqDTO = new GdsMediaCategoryReqDTO();
    		// id为店铺ID特征前缀,则根据店铺ID查询出根分类.
    		if(id.startsWith(SHOP_ID_PREFIX)){
    			id = id.replace(SHOP_ID_PREFIX, "");
    			reqDTO.setShopId(Long.valueOf(id));
    			// 查询根分类。
        		catgLst = gdsMediaCategoryRSV.queryRootCategory(reqDTO);
        		
			}else{
				reqDTO.setCatgParent(id);
    			// 查询子分类。
        		catgLst = gdsMediaCategoryRSV.querySubCategory(reqDTO);
			}
    		convert2TreeList(tree,catgLst);
    	}
    	
    	String json = JSONObject.toJSONString(tree);
    	LogUtil.info(MODULE, String.format("返回JSON数据：%s", json));
    	return json;
    }
    
    
    @RequestMapping("/asyncData/selector/getNodes")
    @ResponseBody
    @NativeJson(true)
    public String dropDownSelector(Model model,MediaCategoryVO reqVO)throws Exception{
    	LogUtil.info(MODULE,"获取媒体分类树型节点,参数"+ToStringBuilder.reflectionToString(reqVO));
    	List<MediaCategoryTreeVO> tree = new ArrayList<MediaCategoryTreeVO>();
    	
    	// 获取zTree自动参数.
    	String id = reqVO.getId();
    	if(null != reqVO.getShopId() && StringUtil.isBlank(reqVO.getId())){
    		// 获取店铺信息.
			Map<Long, ShopInfoResDTO> map = shopCacheRSV.getCacheShop();
    		if(MapUtils.isNotEmpty(map)){
					ShopInfoResDTO dto = map.get(reqVO.getShopId());
					if(null != dto){
						MediaCategoryTreeVO item = buildShopNodeItem(dto, null);
						tree.add(item);
					}
        	}
    	}else if(StringUtil.isBlank(reqVO.getId())){
    		// 获取店铺信息.
			Map<Long, ShopInfoResDTO> map = shopCacheRSV.getCacheShop();
    		if(MapUtils.isNotEmpty(map)){
    			Set<Entry<Long, ShopInfoResDTO>> entrySet = map.entrySet();
    			for (Iterator<Entry<Long, ShopInfoResDTO>> iterator = entrySet.iterator(); iterator
						.hasNext();) {
					Entry<Long, ShopInfoResDTO> entry = (Entry<Long, ShopInfoResDTO>) iterator
							.next();
					ShopInfoResDTO dto = entry.getValue();
					if(null != dto){
						MediaCategoryTreeVO item = buildShopNodeItem(dto, null);
						tree.add(item);
					}
				}
        	}
    	}else{
    		
    		List<GdsMediaCategoryRespDTO> catgLst = null;
    		
    		GdsMediaCategoryReqDTO reqDTO = new GdsMediaCategoryReqDTO();
    		// id为店铺ID特征前缀,则根据店铺ID查询出根分类.
    		if(id.startsWith(SHOP_ID_PREFIX)){
    			id = id.replace(SHOP_ID_PREFIX, "");
    			reqDTO.setShopId(Long.valueOf(id));
    			// 查询根分类。
        		catgLst = gdsMediaCategoryRSV.queryRootCategory(reqDTO);
        		
			}else{
				reqDTO.setCatgParent(id);
    			// 查询子分类。
        		catgLst = gdsMediaCategoryRSV.querySubCategory(reqDTO);
			}
    		convert2TreeList(tree,catgLst);
    	}
    	
    	String json = JSONObject.toJSONString(tree);
    	LogUtil.info(MODULE, String.format("返回JSON数据：%s", json));
    	return json;
    }
    
    
    
    
    @RequestMapping("/asyncData/mediacatgsearch")
    @ResponseBody
    public String categorySearch(Model model,MediaCategoryVO reqVO)throws Exception{
    	LogUtil.info(MODULE,"搜索媒体分类节点,参数"+ToStringBuilder.reflectionToString(reqVO));
    	paramCheck(new Object[]{reqVO.getCatgName()}, new String[]{"catgName"});
    	GdsMediaCategoryReqDTO reqDTO = new GdsMediaCategoryReqDTO();
    	reqDTO.setCatgName(reqVO.getCatgName());
    	reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
    	reqDTO.setPageSize(Integer.MAX_VALUE);
    	
    	PageResponseDTO<GdsMediaCategoryRespDTO> page = gdsMediaCategoryRSV.queryGdsMediaCategoryPaging(reqDTO);
    	
    	List<GdsMediaCategoryRespDTO> nodes = page.getResult();
    	List<String> catgCodes = new ArrayList<String>();
    	List<MediaCategoryTreeVO> trees = new ArrayList<MediaCategoryTreeVO>();
    	if(CollectionUtils.isNotEmpty(nodes)){
    		Map<Long, ShopInfoResDTO> shopMap = null;
    		shopMap = shopCacheRSV.getCacheShop();
    		// 用于维持根节点ID.
    		List<String> rootIds = new ArrayList<String>();
    		String rootId = null;
    		
    		for(GdsMediaCategoryRespDTO respDTO : nodes){
    			
    			rootId = respDTO.getShopId().toString();
				if(!rootIds.contains(rootId)){
					if(null == shopMap){
						continue;
					}
					ShopInfoResDTO shop = shopMap.get(respDTO.getShopId());
					if(null == shop || GdsUtils.isEqualsInvalid(shop.getShopStatus())){
						continue;
					}
					trees.add(buildShopNodeItem(shop, true));
					rootIds.add(rootId);
				}
				 //////////////////////
                // 获取树轨迹－begin。
                GdsMediaCategoryReqDTO traceReqDTO = new GdsMediaCategoryReqDTO();
                traceReqDTO.setCatgCode(respDTO.getCatgCode());
                List<GdsMediaCategoryRespDTO> catgTraceLst = gdsMediaCategoryRSV.queryCategoryTraceUpon(traceReqDTO);
                if(CollectionUtils.isNotEmpty(catgTraceLst)){
                    for(GdsMediaCategoryRespDTO catgTrace : catgTraceLst){
                        if(catgCodes.contains(catgTrace.getCatgCode())){
                            continue;
                        }
                        catgCodes.add(catgTrace.getCatgCode());
                        // 创建分类树项.
                        MediaCategoryTreeVO vo = new MediaCategoryTreeVO();
                        vo.setId(catgTrace.getCatgCode());
                        vo.setName(catgTrace.getCatgName());
                        vo.setIsRoot(false);
                        vo.setShopId(catgTrace.getShopId().toString());
                        if (StringUtil.isBlank(catgTrace.getCatgParent())) {
                            vo.setpId(SHOP_ID_PREFIX + catgTrace.getShopId());
                        } else {
                            vo.setpId(catgTrace.getCatgParent());
                        }
                        vo.setCatgLevel(catgTrace.getCatgLevel());
                    
                        vo.setIsParent(GdsConstants.GdsCategory.IF_LEAF_0.equals(catgTrace.getIfLeaf()) ? true
                                : false);
                        vo.setOpen(true);
                        /*
                         * if(StringUtil.isNotBlank(respDTO.getMediaUuid())){
                         * vo.setIcon(new AiToolUtil().genImageUrl(respDTO.getMediaUuid(), "18x18")); }
                         */
                        trees.add(vo);
                    }
                }
                // 获取树轨迹－end。
                //////////////////////
                
//    			MediaCategoryTreeVO vo = new MediaCategoryTreeVO();
//    			vo.setId(respDTO.getCatgCode());
//    			vo.setName(respDTO.getCatgName());
//    			vo.setShopId(respDTO.getShopId().toString());
//		    	vo.setpId(SHOP_ID_PREFIX+respDTO.getShopId());
//    			vo.setCatgLevel(respDTO.getCatgLevel());
//    			vo.setIsParent(false);
//    			trees.add(vo);
    		}
    	}
    	String json = JSONObject.toJSONString(trees);
    	LogUtil.info(MODULE, String.format("返回JSON数据：%s", json));
    	return json;
    }
    /**
     * 
     * 选定分类删除操作。 
     * 
     * @author liyong7
     * @param model
     * @param catgCode
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/mediacatgdel")
    @ResponseBody
    public EcpBaseResponseVO deleteCategory(Model model,MediaCategoryVO reqVO)throws Exception{
    	LogUtil.info(MODULE,String.format("删除媒体分类,分类ID=%s", reqVO.getCatgCode()));
    	EcpBaseResponseVO respVO = new EcpBaseResponseVO();
    	try{
    		if(StringUtil.isBlank(reqVO.getCatgCode())){
        		throw new BusinessException("web.gds.200006");
        	}
    		
    		GdsMediaCategoryReqDTO condition = new GdsMediaCategoryReqDTO();
    		condition.setCatgCode(reqVO.getCatgCode());
    		Long total = gdsMediaCategoryRSV.executeCountSubMediaCatg(condition);
    		if(0 < total){
    			respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        		respVO.setResultMsg(ResourceMsgUtil.getMessage(GdsErrorConstants.GdsMediaCategory.ERROR_GOODS_MEDIACATG_200203, new String[]{reqVO.getCatgCode()}));
        		return respVO;
    		}
        	StringReqDTO reqDTO = new StringReqDTO();
        	reqDTO.setValue(reqVO.getCatgCode());
    		gdsMediaCategoryRSV.deleteGdsMediaCategoryByPK(reqDTO);
    		respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
    	}catch (BusinessException e) {
    		LogUtil.error(MODULE, String.format("删除媒体分类异常，分类ID=%s", reqVO.getCatgCode()),e);
    		respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
    		respVO.setResultMsg(e.getErrorMessage());
		}
    	return respVO;
    }
    
    
    /**
     * 
     * 显示媒体分类信息。
     * 
     * @author liyong7
     * @param reqVO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/mediacatgview")
    @ResponseBody
    public MediaCategoryRespVO view(MediaCategoryVO reqVO) throws Exception{
        LogUtil.info(MODULE,String.format("显示媒体分类信息,参数：catgCode=%s",reqVO.getCatgCode()));
        MediaCategoryRespVO respVO = new MediaCategoryRespVO();
        try{
        	GdsMediaCategoryReqDTO reqDTO = new GdsMediaCategoryReqDTO();
        	reqDTO.setCatgCode(reqVO.getCatgCode());
        	GdsMediaCategoryRespDTO respDTO = gdsMediaCategoryRSV.queryGdsMediaCategoryByPK(reqDTO);
        	if(null == respDTO || GdsUtils.isEqualsInvalid(respDTO.getStatus())){
        		respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
        		respVO.setResultMsg(ResourceMsgUtil.getMessage(GdsErrorConstants.GdsMediaCategory.ERROR_GOODS_MEDIACATG_200201, new String[]{reqVO.getCatgCode()}));
        	}else{
        		if(StringUtil.isNotBlank(respDTO.getMediaId())){
        			respDTO.setMediaUrl(new AiToolUtil().genImageUrl(respDTO.getMediaId(),"300x200"));
        		}
        		respVO.setRespDTO(respDTO);
        		
	    		 if(StringUtil.isNotBlank(respDTO.getCatgParent())){
	    			 GdsMediaCategoryReqDTO parentReqDTO = new GdsMediaCategoryReqDTO();
	             	parentReqDTO.setCatgCode(respDTO.getCatgParent());
	                 GdsMediaCategoryRespDTO parentRespDTO = gdsMediaCategoryRSV.queryGdsMediaCategoryByPK(parentReqDTO);
	                 if(null != parentRespDTO){
	                 	respVO.setParentRespDTO(parentRespDTO);
	                 }
	             }
        		
        		
        	}
        }catch(BusinessException e){
            LogUtil.error(MODULE, "查询媒体分类遇到异常！", e);
            respVO.setResultMsg(e.getErrorMessage());
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            throw new BusinessException(e.getErrorCode());
        }
        
        return respVO;
    }
    
  
    
    /**
     * 
     * 保存分类信息。<br/>
     * 
     * @author liyong7
     * @param reqVO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/mediacatgsave")
    @ResponseBody
    public MediaCategoryRespVO save(@Valid MediaCategoryVO reqVO){
    	
        LogUtil.info(MODULE,String.format("新增媒体分类,参数：%s",ToStringBuilder.reflectionToString(reqVO)));
        
        MediaCategoryRespVO respVO = new MediaCategoryRespVO();
        try{
        	
        	if(StringUtil.isNotBlank(reqVO.getCatgParent())){
    	    	GdsMediaCategoryReqDTO parentQueryDTO = new GdsMediaCategoryReqDTO();
    	    	parentQueryDTO.setCatgCode(reqVO.getCatgParent());
    	        GdsMediaCategoryRespDTO parent = gdsMediaCategoryRSV.queryGdsMediaCategoryByPK(parentQueryDTO);
    	        if(null == parent || GdsUtils.isEqualsInvalid(parent.getStatus())){
    	        	throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200311);
    	        }
    	        
    	        // 查询最大层级配置.
	        	BaseSysCfgRespDTO sysCfgRespDTO = SysCfgUtil.fetchSysCfg(GdsConstants.SysCfgConstants.GDS_CATEGORY_MAX_LEVEL);
    	        
	        	short catgLevel = (short) (parent.getCatgLevel()+1);
    	        
    	        if(isLevelLimit(sysCfgRespDTO,catgLevel)){
    	        	respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
    	        	respVO.setResultMsg(ResourceMsgUtil.getMessage(GdsErrorConstants.GdsMediaCategory.ERROR_GOODS_MEDIACATG_200206,
    	        			new String[]{sysCfgRespDTO.getParaValue()}));
    	        	return respVO;
    	        }
    	    	
    	    }
    	
        	
        	
        	GdsMediaCategoryReqDTO reqDTO = new GdsMediaCategoryReqDTO();
        	ObjectCopyUtil.copyObjValue(reqVO, reqDTO, null, true);
        	    
            reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
            
            GdsMediaCategoryRespDTO respDTO = new GdsMediaCategoryRespDTO();
            
            // 设置默认排序
            if(null == reqVO.getSortNo()){
            	reqVO.setSortNo(1);
            }
            
            
            if(StringUtil.isNotBlank(reqDTO.getCatgCode())){
            	gdsMediaCategoryRSV.editGdsMediaCategory(reqDTO);
            	ObjectCopyUtil.copyObjValue(reqDTO, respDTO, null, true);
            	respDTO.setCatgParent(null);
            }else{
            	respDTO = gdsMediaCategoryRSV.saveGdsMediaCategory(reqDTO);
            }
            
            if(StringUtil.isNotBlank(respDTO.getMediaId())){
            	respDTO.setMediaUrl(new AiToolUtil().genImageUrl(respDTO.getMediaId(), "300x200"));
            }
            
            
            respVO.setRespDTO(respDTO);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
                
                
        }catch(BusinessException e){
            LogUtil.error(MODULE, "新增媒体分类异常", e);
            respVO.setResultMsg(e.getErrorMessage());
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        
        }
        
        return respVO;
    }
    
    /**
     * 
     * save:文彬数据修复. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author liyong7
     * @param reqVO
     * @return 
     * @since JDK 1.6
     */
    //@RequestMapping(value = "/abcdefghijklmn99aaaaaa")
   // @ResponseBody
  /*  public String repairGds2Catg(MediaCategoryVO reqVO){
        LogUtil.info(MODULE,"=============数据修复开始.");
        BaseInfo baseInfo = new BaseInfo();
        gdsCategoryRSV.repairGds2Catg(baseInfo);
        LogUtil.info(MODULE,"=============数据修复结束.");
        return "恭喜数据修复完成....";
    }
    */
    
    
    
    
    /*
     * 
     * 构建店铺节点.
     * 
     * @author liyong7
     * @param dto
     * @return 
     * @since JDK 1.6
     */
    private MediaCategoryTreeVO buildShopNodeItem(ShopInfoResDTO dto,Boolean isOpen) {
    	MediaCategoryTreeVO vo = new MediaCategoryTreeVO();
		vo.setId(SHOP_ID_PREFIX+dto.getId());
		vo.setName(dto.getShopName());
		vo.setpId(null);
		vo.setCatgLevel(null);
		vo.setIsParent(true);
		vo.setIsRoot(true);
		vo.setClick(false);
		vo.setOpen(isOpen);
		return vo;
	}
    
    
    /*
     * 
     * convert2TreeList:分类信息转换成分类树列表. <br/> 
     * 
     * @author liyong7
     * @param trees
     * @param catgLst 
     * @since JDK 1.6
     */
    private void convert2TreeList(List<MediaCategoryTreeVO> trees,
			List<GdsMediaCategoryRespDTO> catgLst) {
		if(CollectionUtils.isNotEmpty(catgLst)){
			for(GdsMediaCategoryRespDTO respDTO : catgLst){
				MediaCategoryTreeVO vo = new MediaCategoryTreeVO();
				vo.setId(respDTO.getCatgCode());
				vo.setName(respDTO.getCatgName());
			    vo.setIsRoot(false);
			    vo.setShopId(respDTO.getShopId().toString());
		    	if(StringUtil.isBlank(respDTO.getCatgParent())){
		    		vo.setpId(SHOP_ID_PREFIX+respDTO.getShopId());
		    	}else{
		    		vo.setpId(respDTO.getCatgParent());
		    	}
				vo.setCatgLevel(respDTO.getCatgLevel());
				vo.setIsParent(GdsConstants.GdsCategory.IF_LEAF_0.equals(respDTO.getIfLeaf()) ? true : false);
				trees.add(vo);
			}
		}
	}
    
    
    
   
     private boolean isLevelLimit(BaseSysCfgRespDTO sysCfgRespDTO, Short currentLevel){
    	
    	if(null != sysCfgRespDTO && StringUtil.isNotBlank(sysCfgRespDTO.getParaValue())){
    		if(null != currentLevel && Short.valueOf(sysCfgRespDTO.getParaValue()) < currentLevel){
    			return true;
    		}
    	}
    	return false;
    }
    
    
    
}

