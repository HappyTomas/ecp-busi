package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Gds027Req;
import com.zengshi.ecp.app.req.Gds028Req;
import com.zengshi.ecp.app.resp.Gds027Resp;
import com.zengshi.ecp.app.resp.Gds028Resp;
import com.zengshi.ecp.busi.seller.goods.vo.category.CategoryTreeVO;
import com.zengshi.ecp.busi.seller.goods.vo.category.CategoryVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropRelationRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalogRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopCacheRSV;
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
 * @author xiaosm
 * @version
 * @since JDK 1.7
 */
@Service("gds028")
@Action(bizcode = "gds028", userCheck = false)
@Scope("prototype")
public class Gds028Action extends AppBaseAction<Gds028Req, Gds028Resp> {
    
    @Resource
    private IGdsCategoryRSV gdsCategoryRSV;
    
    @Resource
    private IShopCacheRSV shopCacheRSV;
    
    @Resource
    private IGdsCatalogRSV gdsCatalogRSV;

    private static final String MODULE = Gds028Action.class.getName();
    
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
    	Gds028Req gds028Req = this.request;
    	Gds028Resp gds028Resp = this.response;
        String catgCode = gds028Req.getCatgCode();
        
        List<GdsPropRespDTO> gdsPropRespDTOList = new ArrayList<GdsPropRespDTO>();
    	GdsCatg2PropReqDTO propReqDTO = new GdsCatg2PropReqDTO();
    	propReqDTO.setCatgCode(catgCode);
        GdsCatg2PropRelationRespDTO gdsCatg2PropRelationRespDTO = gdsCategoryRSV
                .queryCategoryPropsByCondition(propReqDTO);
        if (gdsCatg2PropRelationRespDTO != null) {
            gdsPropRespDTOList = gdsCatg2PropRelationRespDTO.getSearchProps();
        }
        gds028Resp.setGdsPropList(gdsPropRespDTOList);
    }
}
