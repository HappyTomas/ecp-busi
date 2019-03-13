package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Gds027Req;
import com.zengshi.ecp.app.resp.Gds027Resp;
import com.zengshi.ecp.app.resp.gds.CategoryTree;
import com.zengshi.ecp.busi.seller.goods.vo.category.CategoryTreeVO;
import com.zengshi.ecp.busi.seller.goods.vo.category.CategoryVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalogRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopCacheRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;
import com.alibaba.fastjson.JSONObject;

/**
 * 获取商品分类信息 Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016年3月10日上午10:18:08 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author lincx
 * @version
 * @since JDK 1.7
 */
@Service("gds027")
@Action(bizcode = "gds027", userCheck = false)
@Scope("prototype")
public class Gds027Action extends AppBaseAction<Gds027Req, Gds027Resp> {
    
    @Resource
    private IGdsCategoryRSV gdsCategoryRSV;
    
    @Resource
    private IShopCacheRSV shopCacheRSV;
    
    @Resource
    private IGdsCatalogRSV gdsCatalogRSV;

    private static final String MODULE = Gds027Action.class.getName();
    
    /**
     * 分类树店铺ID前缀。
     */
    public static final String SHOP_ID_PREFIX = "SHOP-";
    
    /**
     * 分类树目录ID前缀.
     */
    public static final String CATLOG_ID_PREFIX = "CATLOG-";

    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
    	Gds027Req gds027Req = this.request;
        Gds027Resp gds027Resp = this.response;
        GdsCategoryReqDTO reqDTO;
//        List<GdsCatalogRespDTO> catlogs ;
        boolean isRoot = true;
        String id = gds027Req.getId();
        String catlogId = "1";
        if(StringUtil.isBlank(catlogId)){
        	catlogId="1";
        }
        if(StringUtil.isBlank(id)){
        	//返回根节点
//        	List<CategoryTreeVO> trees_root = new ArrayList<CategoryTreeVO>();
        	String catgType = GdsConstants.GdsCategory.CATG_TYPE_1;
//        	String catgLogType = GdsConstants.GdsCatlog.CATLOG_TYPE_1;
        	reqDTO = new GdsCategoryReqDTO();
        	reqDTO.setCatgType(catgType);
        	BaseSysCfgRespDTO sysCfg = SysCfgUtil.fetchSysCfg("MOBILE_SEARCH_BEGIN_CATEGORY");
        	String categoryIds = sysCfg.getParaValue();
        	String ids[] = categoryIds.split(",");
        	List<String> catgParents = new ArrayList<String>();
        	for(String id_root : ids){
        		catgParents.add(id_root);
        	}
        	reqDTO.setCatgParents(catgParents);
        	reqDTO.setPageSize(Integer.MAX_VALUE);
        	reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        	PageResponseDTO<GdsCategoryRespDTO> rootDto = gdsCategoryRSV.queryGdsCategoryPaging(reqDTO);
        	List<GdsCategoryRespDTO> gdsCategoryList = rootDto.getResult();
        	List<CategoryTree> trees = new ArrayList<CategoryTree>();
        	if(CollectionUtils.isNotEmpty(gdsCategoryList)){
        		convert2TreeList(trees, gdsCategoryList,reqDTO);
        	}
        	gds027Resp.setGdsCategoryList(trees);
        }else if("all".equalsIgnoreCase(id)){
            // 特殊请求参数，用于一次性获取分类。
          //返回根节点
            String catgType = GdsConstants.GdsCategory.CATG_TYPE_1;
            reqDTO = new GdsCategoryReqDTO();
            reqDTO.setCatgType(catgType);
            BaseSysCfgRespDTO sysCfg = SysCfgUtil.fetchSysCfg("MOBILE_SEARCH_BEGIN_CATEGORY");
            String categoryIds = sysCfg.getParaValue();
            String ids[] = categoryIds.split(",");
            reqDTO.setPageSize(Integer.MAX_VALUE);
            
            List<GdsCategoryRespDTO> gdsCategoryList = new ArrayList<>();
            
            
            if(ArrayUtils.isNotEmpty(ids)){
                for(String parentCode : ids ){
                    reqDTO.setCatgPath(parentCode);
                    List<String> catgLst = new ArrayList<>();
                    catgLst.add(parentCode);
                    reqDTO.setExcludeCatgCode(catgLst);
                    reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
                    reqDTO.setOrderBy("CATG_LEVEL DESC,CATG_CODE");
                    PageResponseDTO<GdsCategoryRespDTO> rootDto = gdsCategoryRSV.queryGdsCategoryPaging(reqDTO);
                    if(0 < rootDto.getCount()){
                        gdsCategoryList.addAll(rootDto.getResult());
                    }
                }
                
            }
            List<CategoryTree> trees = new ArrayList<CategoryTree>();
            if(CollectionUtils.isNotEmpty(gdsCategoryList)){
                convert2TreeList(trees, gdsCategoryList,reqDTO);
            }
            gds027Resp.setGdsCategoryList(trees);
        }else{
        	CategoryVO reqVO = new CategoryVO();

            if(StringUtil.isNotBlank(id)){
            	reqVO.setId(id);
            }
        	LogUtil.info(MODULE, "获取分类树型节点,参数" + ToStringBuilder.reflectionToString(reqVO));
//            List<CategoryTreeVO> trees = new ArrayList<CategoryTreeVO>();
            List<CategoryTree> trees = new ArrayList<CategoryTree>();
            // 判断是否有传分类类型参数
            if (StringUtil.isBlank(reqVO.getCatgType())) {
//                LogUtil.warn(MODULE, "catgType为空,返回空数据!");
                JSONObject.toJSONString(trees);
            }
            reqDTO = new GdsCategoryReqDTO();
            ObjectCopyUtil.copyObjValue(reqVO, reqDTO, null, true);
            id = reqVO.getId();
            
         // 目录ID不为空,查询分类信息.
            List<GdsCategoryRespDTO> catgLst = null;

            if (id.startsWith(CATLOG_ID_PREFIX)) {
                id = id.replace(CATLOG_ID_PREFIX, "");
                reqDTO.setCatlogId(Long.valueOf(id));
                // 查询根分类。
                catgLst = gdsCategoryRSV.queryRootCategory(reqDTO);

            } else if (id.startsWith(SHOP_ID_PREFIX)) {
                id = id.replace(SHOP_ID_PREFIX, "");
                reqDTO.setShopId(Long.valueOf(id));
                // 查询根分类。
                catgLst = gdsCategoryRSV.queryRootCategory(reqDTO);
            } else {
                // 否则加载子分类。
                reqDTO.setCatgParent(id);
                catgLst = gdsCategoryRSV.querySubCategory(reqDTO);
            }
            convert2TreeList(trees, catgLst,reqDTO);
            gds027Resp.setGdsCategoryList(trees);
        }
        
    }
    public CategoryTreeVO  buildCatalogNodeItem(GdsCatalogRespDTO catlogRespDTO, Boolean isOpen) {
        CategoryTreeVO vo = new CategoryTreeVO();
        vo.setId(CATLOG_ID_PREFIX + catlogRespDTO.getId());
        vo.setName(catlogRespDTO.getCatlogName());
        vo.setpId(null);
        vo.setCatgLevel(null);
        vo.setIsParent(true);
        if (null != catlogRespDTO.getId()) {
            vo.setCatlogId(catlogRespDTO.getId().toString());
        }
        vo.setIsRoot(true);
        vo.setClick(false);
        vo.setOpen(isOpen);
        return vo;
    }
/*    private CategoryTreeVO buildShopNodeItem(ShopInfoResDTO dto, Boolean isOpen) {
        CategoryTreeVO vo = new CategoryTreeVO();
        vo.setId(SHOP_ID_PREFIX + dto.getId());
        vo.setName(dto.getShopName());
        vo.setpId(null);
        vo.setCatgLevel(null);
        vo.setIsParent(true);
        vo.setIsRoot(true);
        vo.setClick(false);
        vo.setOpen(isOpen);
        return vo;
    }*/
    private void convert2TreeList(List<CategoryTree> trees, List<GdsCategoryRespDTO> catgLst,
            GdsCategoryReqDTO categoryReqDTO) {
        if (CollectionUtils.isNotEmpty(catgLst)) {
            for (GdsCategoryRespDTO respDTO : catgLst) {
//            	CategoryTreeVO vo = new CategoryTreeVO();
            	CategoryTree vo = new CategoryTree();
                vo.setId(respDTO.getCatgCode());
                vo.setName(respDTO.getCatgName());
                vo.setIsRoot(false);
                if (null != respDTO.getCatlogId()) {
//                    vo.setCatlogId(respDTO.getCatlogId().toString());
                    if (StringUtil.isBlank(respDTO.getCatgParent())) {
                        vo.setPid(CATLOG_ID_PREFIX + respDTO.getCatlogId());
                    } else {
                        vo.setPid(respDTO.getCatgParent());
                    }
                } else if (null != respDTO.getShopId()) {
//                    vo.setShopId(respDTO.getShopId().toString());
                    if (StringUtil.isBlank(respDTO.getCatgParent())) {
                        vo.setPid(SHOP_ID_PREFIX + respDTO.getShopId());
                    } else {
                        vo.setPid(respDTO.getCatgParent());
                    }
                }
//                vo.setCatgLevel(respDTO.getCatgLevel());
                //如果指定显示最大节点
                if (categoryReqDTO.getMaxShowNode() != null || !"".equals(categoryReqDTO.getMaxShowNode())) {
                    if (respDTO.getCatgLevel().toString().equals(categoryReqDTO.getMaxShowNode())) {
                        vo.setIsRoot(false);
                    }else{
                        vo.setIsRoot(GdsConstants.GdsCategory.IF_LEAF_0.equals(respDTO.getIfLeaf()) ? true
                                : false);
                    }
                } else {
                    vo.setIsRoot(GdsConstants.GdsCategory.IF_LEAF_0.equals(respDTO.getIfLeaf()) ? true
                            : false);
                }
                /*
                 * if(StringUtil.isNotBlank(respDTO.getMediaUuid())){
                 * vo.setIcon(new AiToolUtil().genImageUrl(respDTO.getMediaUuid(), "18x18")); }
                 */
                trees.add(vo);
            }
        }
    }
    private List<GdsCatalogRespDTO> loadCatalogRespDTO(CategoryVO reqVO) {
        GdsCatalogReqDTO reqDTO = new GdsCatalogReqDTO();
        reqDTO.setPageSize(Integer.MAX_VALUE);
        reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        reqDTO.setId(reqVO.getCatlogId());
        PageResponseDTO<GdsCatalogRespDTO> page = gdsCatalogRSV
                .queryGdsCatalogRespDTOPaging(reqDTO);
        return page.getResult();
    }

    
}
