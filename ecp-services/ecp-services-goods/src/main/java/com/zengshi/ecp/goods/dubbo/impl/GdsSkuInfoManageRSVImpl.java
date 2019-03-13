package com.zengshi.ecp.goods.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.general.prom.interfaces.IPromMsg2SolrRSV;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfo;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsVerifyReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoManageRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsCacheUtil;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoManageSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoManageSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoQuerySV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdslog.IGdsLogSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdslog.Operation;
import com.zengshi.ecp.goods.service.busi.interfaces.gdslog.OperationType;
import com.zengshi.ecp.goods.service.busi.interfaces.gdslog.ServiceEnum;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 单品管理Dubbo服务实现<br>
 * Date:2015年8月30日下午4:52:46 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsSkuInfoManageRSVImpl extends AbstractRSVImpl implements
		IGdsSkuInfoManageRSV {

	@Resource
	private IGdsSkuInfoManageSV gdsSkuInfoManageSV;
	
	@Autowired(required = false)
	private IGdsInfoManageSV gdsInfoManageSV;
	
	@Resource
	private IGdsSkuInfoQuerySV gdsSkuInfoQuerySV;
	
	@Resource
	private IPromMsg2SolrRSV promMsg2SolrRSV;

	@Operation(model=IGdsLogSV.GDS_MODEL,operType=OperationType.SKU_ADD,recordFields={"gdsName","shopId"})
	@Override
	public Long saveGdsSkuInfo(GdsSkuInfoReqDTO gdsSkuInfoReqDTO)
			throws BusinessException {
		paramNullCheck(gdsSkuInfoReqDTO, true, "gdsSkuInfoReqDTO");
		return gdsSkuInfoManageSV.saveGdsSkuInfo(gdsSkuInfoReqDTO);
	}

	@Operation(model=IGdsLogSV.GDS_MODEL,operType=OperationType.SKU_EDIT,recordFields={"gdsName","id","shopId"})
	@Override
	public Long editGdsSkuInfoAndReference(GdsSkuInfoReqDTO gdsSkuInfoReqDTO)
			throws BusinessException {
		paramNullCheck(gdsSkuInfoReqDTO, true, "gdsSkuInfoReqDTO");
		paramNullCheck(gdsSkuInfoReqDTO.getId(), "gdsSkuInfoReqDTO.id");
		Long skuId=gdsSkuInfoManageSV.editGdsSkuInfoAndReference(gdsSkuInfoReqDTO);
		GdsCacheUtil.delSkuInfoCache(gdsSkuInfoReqDTO.getId());
		GdsCacheUtil.delGdsPicCache(gdsSkuInfoReqDTO.getId());
		return skuId;
	}

	@Operation(model=IGdsLogSV.GDS_MODEL,operType=OperationType.SKU_EDIT,recordFields={"gdsName","id","shopId"})
	@Override
	public Long editGdsSkuInfo(GdsSkuInfoReqDTO gdsSkuInfoReqDTO)
			throws BusinessException {
		paramNullCheck(gdsSkuInfoReqDTO, true, "gdsSkuInfoReqDTO");
		paramNullCheck(gdsSkuInfoReqDTO.getId(), "gdsSkuInfoReqDTO.id");
		Long skuId=gdsSkuInfoManageSV.editGdsSkuInfo(gdsSkuInfoReqDTO);
		GdsCacheUtil.delSkuInfoCache(gdsSkuInfoReqDTO.getId());
		GdsCacheUtil.delGdsPicCache(gdsSkuInfoReqDTO.getId());
		return skuId;
	}

	@Operation(model=IGdsLogSV.GDS_MODEL,operType=OperationType.SKU_DELETE,recordFields={"id"})
	@Override
	public void delGdsSkuInfo(GdsSkuInfoReqDTO gdsSkuInfoReqDTO)
			throws BusinessException {
		paramNullCheck(gdsSkuInfoReqDTO, true, "gdsSkuInfoReqDTO");
		paramNullCheck(gdsSkuInfoReqDTO.getId(), "gdsSkuInfoReqDTO.id");
		GdsSkuInfo skuInfo=gdsSkuInfoManageSV.deleteGdsSkuInfo(gdsSkuInfoReqDTO);
		GdsCacheUtil.delSkuInfoCache(gdsSkuInfoReqDTO.getId());
		GdsCacheUtil.delGdsPicCache(gdsSkuInfoReqDTO.getId());
        GdsUtils.sendPromMsg(skuInfo.getShopId(),skuInfo.getGdsId(),skuInfo.getId(),GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES,promMsg2SolrRSV);
	}

	@Operation(model=IGdsLogSV.GDS_MODEL,service=ServiceEnum.GDS_SKU_INFO_OPE_TYPE_CAL_SV,recordFields={"id"})
	@Override
	public void doSkuShelves(GdsSkuInfoReqDTO gdsSkuInfoReqDTO)
			throws BusinessException {
		paramNullCheck(gdsSkuInfoReqDTO, true, "gdsSkuInfoReqDTO");
		paramNullCheck(gdsSkuInfoReqDTO.getId(), "gdsSkuInfoReqDTO.id");
		paramNullCheck(gdsSkuInfoReqDTO.getGdsStatus(),"gdsSkuInfoReqDTO.gdsStatus");
		GdsSkuInfo skuInfo=gdsSkuInfoManageSV.executeSkuShelves(gdsSkuInfoReqDTO);
		GdsCacheUtil.delSkuInfoCache(gdsSkuInfoReqDTO.getId());
		GdsCacheUtil.delGdsPicCache(gdsSkuInfoReqDTO.getId());
        GdsUtils.sendPromMsg(skuInfo.getShopId(),skuInfo.getGdsId(),skuInfo.getId(),gdsSkuInfoReqDTO.getGdsStatus(),promMsg2SolrRSV);
	}

	@Operation(model=IGdsLogSV.GDS_MODEL,service=ServiceEnum.GDS_SKU_VERIFY_OPE_TYPE_CAL_SV,recordFields={"shopId","ids"})
	@Override
    public void doSkuVerify(GdsVerifyReqDTO gdsVerifyReqDTO) throws BusinessException {
        paramNullCheck(gdsVerifyReqDTO, true, "gdsVerifyReqDTO");
        paramNullCheck(gdsVerifyReqDTO.getShopId(), "gdsVerifyReqDTO.shopId");
        Long[] ids = gdsVerifyReqDTO.getIds();
        if (!ArrayUtils.isEmpty(ids)) {
            for (Long id : ids) {
               gdsVerifyReqDTO.setGdsId(id);
               gdsInfoManageSV.addGdsVerify(gdsVerifyReqDTO);
            }
        }
    }
	
	@Operation(model = IGdsLogSV.GDS_MODEL,operType=OperationType.SKU_INFO_VERIFY,recordFields={"shopId","ids"})
    @Override
    public void editSkuVerify(GdsVerifyReqDTO gdsVerifyReqDTO) throws BusinessException {
        paramNullCheck(gdsVerifyReqDTO,true, "gdsVerifyReqDTO");
        paramNullCheck(gdsVerifyReqDTO.getShopId(), "gdsVerifyReqDTO.shopId");
        List<GdsSkuInfo> skus = gdsInfoManageSV.editGdsVerify(gdsVerifyReqDTO);
        if (CollectionUtils.isNotEmpty(skus)
                && GdsConstants.GdsVerify.VERIFY_APPROVED.equals(gdsVerifyReqDTO
                        .getVerifyStatus())) {
            List<Long> skuIds=new ArrayList<Long>();
            for (GdsSkuInfo gdsSkuInfo : skus) {
                skuIds.add(gdsSkuInfo.getId());
                GdsUtils.sendPromMsg(gdsSkuInfo.getShopId(), gdsSkuInfo.getGdsId(), gdsSkuInfo.getId(), gdsSkuInfo.getGdsStatus(), promMsg2SolrRSV);
            }
            // 表明进行审核通过，有商品进行更新
            delAllCache(gdsVerifyReqDTO.getGdsId(), skuIds);
        }
    }
    private void delAllCache(Long gdsId, List<Long> skuIds) {
        GdsCacheUtil.delGdsInfoCache(gdsId);
        GdsCacheUtil.delGdsPicCache(gdsId);
        if (CollectionUtils.isNotEmpty(skuIds)) {
            for (Long skuId : skuIds) {
                GdsCacheUtil.delSkuInfoCache(skuId);
                GdsCacheUtil.delSkuPicCache(skuId);
            }
        }
    }

}
