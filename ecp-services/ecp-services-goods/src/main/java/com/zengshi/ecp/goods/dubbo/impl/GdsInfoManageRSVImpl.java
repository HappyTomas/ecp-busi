package com.zengshi.ecp.goods.dubbo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.general.prom.interfaces.IPromMsg2SolrRSV;
import com.zengshi.ecp.goods.dao.model.GdsInfo;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfo;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsVerifyReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoAddReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoManageRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsCacheUtil;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoManageSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdslog.IGdsLogSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdslog.Operation;
import com.zengshi.ecp.goods.service.busi.interfaces.gdslog.OperationType;
import com.zengshi.ecp.goods.service.busi.interfaces.gdslog.ServiceEnum;
import com.zengshi.ecp.goods.service.thread.GdsInfoMessageDTO;
import com.zengshi.ecp.goods.service.thread.GdsInfoMessageTask;
import com.zengshi.ecp.goods.service.thread.PromMessageTask;
import com.zengshi.ecp.goods.service.thread.ThreadPoolExecutorUtil;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsUnsendReqDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.gdssend.IUnpfGdsUnsendRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.common.DistributedTransactionManager;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 商品管理Dubbo服务<br>
 * Date:2015年8月30日下午4:49:35 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsInfoManageRSVImpl extends AbstractRSVImpl implements IGdsInfoManageRSV {

	@Autowired(required = false)
	private IGdsInfoManageSV gdsInfoManageSV;

	@Resource
	private IGdsInfoQuerySV gdsInfoQuerySV;

	@Resource
	private IPromMsg2SolrRSV promMsg2SolrRSV;
	
	@Resource
	private IUnpfGdsUnsendRSV unpfGdsUnsendRSV;

	@Operation(model = IGdsLogSV.GDS_MODEL, operType = OperationType.GDS_ADD, recordFields = { "gdsInfoReqDTO.gdsName", "gdsInfoReqDTO.shopId" })
	@Override
	public Long addGdsInfo(GdsInfoAddReqDTO gdsInfoAddReqDTO) throws BusinessException {
		paramNullCheck(gdsInfoAddReqDTO, true, "gdsInfoAddReqDTO");
		paramNullCheck(gdsInfoAddReqDTO.getGdsInfoReqDTO(), "gdsInfoAddReqDTO.gdsInfoReqDTO");
		paramNullCheck(gdsInfoAddReqDTO.getGdsInfoReqDTO().getGdsName(), "gdsInfoAddReqDTO.gdsInfoReqDTO.gdsName");
		paramNullCheck(gdsInfoAddReqDTO.getGdsInfoReqDTO().getShopId(), "gdsInfoAddReqDTO.gdsInfoReqDTO.shopId");
		paramNullCheck(gdsInfoAddReqDTO.getCompanyId(), "gdsInfoAddReqDTO.companyId");
		GdsInfoRespDTO gdsInfo = gdsInfoManageSV.addGdsInfo(gdsInfoAddReqDTO);
		//加一层判断，如果是拷贝操作过来的，不刷新促销索引。
		String isCopy = gdsInfoAddReqDTO.getIfLocalEdit();
		if(isCopy==null){
			//待上架也要刷新促销的索引
			if (gdsInfo != null && CollectionUtils.isNotEmpty(gdsInfo.getSkuIds())) {
				for (Long skuId : gdsInfo.getSkuIds()) {
					GdsUtils.sendPromMsg(gdsInfo.getShopId(), gdsInfo.getId(), skuId, GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES, promMsg2SolrRSV);
				}
			}
		}
		
		// 增加第三方平台统一推送逻辑。
        BaseSysCfgRespDTO sysCfg = SysCfgUtil.fetchSysCfg("SYS_GDS_SENDUNPF_SWICH");
        if(sysCfg!=null && "1".equals(sysCfg.getParaValue()) && 
                StringUtil.isBlank(gdsInfoAddReqDTO.getGdsInfoReqDTO().getExt1())){
            UnpfGdsUnsendReqDTO unSendReqDTO = new UnpfGdsUnsendReqDTO();
            unSendReqDTO.setGdsId(gdsInfo.getId());
            unSendReqDTO.setShopId(gdsInfo.getShopId());
            unSendReqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
            unpfGdsUnsendRSV.saveUnsendGds(unSendReqDTO);
        }
		
		
		
		return gdsInfo.getId();
	}

	@Operation(model = IGdsLogSV.GDS_MODEL, operType = OperationType.GDS_EDIT, recordFields = { "gdsInfoReqDTO.id", "gdsInfoReqDTO.gdsName", "gdsInfoReqDTO.shopId" })
	@Override
	public void editGdsInfoAndReference(GdsInfoAddReqDTO gdsInfoAddReqDTO) throws BusinessException {
		paramNullCheck(gdsInfoAddReqDTO, true, "gdsInfoAddReqDTO");
		paramNullCheck(gdsInfoAddReqDTO.getGdsInfoReqDTO(), "gdsInfoAddReqDTO.gdsInfoReqDTO");
		paramNullCheck(gdsInfoAddReqDTO.getGdsInfoReqDTO().getId(), "gdsInfoAddReqDTO.gdsInfoReqDTO.id");
		paramNullCheck(gdsInfoAddReqDTO.getGdsInfoReqDTO().getShopId(), "gdsInfoAddReqDTO.gdsInfoReqDTO.shopId");
		//编辑产品
		List<Long> skuIds = gdsInfoManageSV.editGdsInfoAndReference(gdsInfoAddReqDTO);
		//删除缓存
		delAllCache(gdsInfoAddReqDTO.getGdsInfoReqDTO().getId(), skuIds);
	}

	@Operation(model = IGdsLogSV.GDS_MODEL, operType = OperationType.GDS_EDIT, recordFields = { "id", "gdsName", "shopId" })
	@Override
	public void editGdsInfo(GdsInfoReqDTO gdsInfoReqDTO) throws BusinessException {
		paramNullCheck(gdsInfoReqDTO, true, "gdsInfoReqDTO");
		paramNullCheck(gdsInfoReqDTO.getId(), "gdsInfoReqDTO.id");
		paramNullCheck(gdsInfoReqDTO.getShopId(), "gdsInfoReqDTO.shopId");
		gdsInfoManageSV.editGdsInfo(gdsInfoReqDTO);
		
		//删除缓存
		GdsCacheUtil.delGdsInfoCache(gdsInfoReqDTO.getId());
		GdsCacheUtil.delGdsPicCache(gdsInfoReqDTO.getId());
		
	}

	@Operation(model = IGdsLogSV.GDS_MODEL, operType = OperationType.GDS_DELETE, recordFields = { "id", "shopId" })
	@Override
	public void delGdsInfo(GdsInfoReqDTO gdsInfoReqDTO) throws BusinessException {
		paramNullCheck(gdsInfoReqDTO, true, "gdsInfoReqDTO");
		paramNullCheck(gdsInfoReqDTO.getId(), "gdsInfoReqDTO.id");
		paramNullCheck(gdsInfoReqDTO.getShopId(), "gdsInfoReqDTO.shopId");
		List<Long> skuIds = gdsInfoManageSV.deleteGdsInfo(gdsInfoReqDTO);
		delAllCache(gdsInfoReqDTO.getId(), skuIds);
		
		//查询当前商品信息缓存
		GdsInfoReqDTO temp = new GdsInfoReqDTO();
		temp.setId(gdsInfoReqDTO.getId());
		GdsInfo gdsInfo = gdsInfoQuerySV.queryGdsInfoModel(temp);
		//删除商品索引
		GdsUtils.sendGdsIndexMsg(GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES, "T_GDS_INFO", MODULE, gdsInfo.getId(), gdsInfo.getCatlogId());
		for (Long skuId : skuIds) {
			// 发送促销索引删除
			GdsUtils.sendPromMsg(gdsInfo.getShopId(), gdsInfoReqDTO.getId(), skuId, GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES, promMsg2SolrRSV);
		}
		
	}

	@Operation(model = IGdsLogSV.GDS_MODEL, operType = OperationType.GDS_BATCH_DELETE, recordFields = { "ids", "shopId" })
	@Override
	public void batchDelGdsInfo(GdsInfoReqDTO gdsInfoReqDTO) throws BusinessException {
		paramNullCheck(gdsInfoReqDTO, true, "gdsInfoReqDTO");
		Long[] ids = gdsInfoReqDTO.getIds();
		if (!ArrayUtils.isEmpty(ids)) {
			for (Long id : ids) {
				GdsInfoReqDTO temp = new GdsInfoReqDTO();
				temp.setId(id);
				GdsInfo gdsinfo = gdsInfoQuerySV.queryGdsInfoModel(temp);
				try {
					gdsInfoReqDTO.setId(id);
					List<Long> skuIds = gdsInfoManageSV.deleteGdsInfo(gdsInfoReqDTO);
					for (Long skuId : skuIds) {
						// 发送促销索引删除
						GdsUtils.sendPromMsg(gdsinfo.getShopId(), gdsinfo.getId(), skuId, GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES, promMsg2SolrRSV);
					}
					GdsUtils.sendGdsIndexMsg(GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES, "T_GDS_INFO", MODULE, gdsinfo.getId(), gdsinfo.getCatlogId());
					delAllCache(id, skuIds);
				} catch (Exception e) {
					LogUtil.error(MODULE, "excute delete  failed !  gdsId is" + id);
				}
			}
		}
	}

	@Operation(model = IGdsLogSV.GDS_MODEL, operType = OperationType.GDS_BATCH_OFF_SHELVES, recordFields = { "ids", "shopId" })
	@Override
	public void batchOffShelvesGdsInfoByShopId(GdsInfoReqDTO gdsInfoReqDTO) throws BusinessException {
		paramNullCheck(gdsInfoReqDTO, true, "gdsInfoReqDTO");
		paramNullCheck(gdsInfoReqDTO.getShopId(), "gdsInfoReqDTO.shopId");

		List<GdsInfoRespDTO> gdsInfoRespDTOs = gdsInfoQuerySV.queryGdsInfoList(gdsInfoReqDTO);
		if (CollectionUtils.isNotEmpty(gdsInfoRespDTOs)) {
			for (GdsInfoRespDTO gdsInfoRespDTO : gdsInfoRespDTOs) {
				if (!GdsConstants.GdsInfo.GDS_STATUS_DELETE.equals(gdsInfoRespDTO.getGdsStatus()) && !GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES.equals(gdsInfoRespDTO.getGdsStatus())) {
					GdsInfoReqDTO gdsShelves = new GdsInfoReqDTO();
					gdsShelves.setId(gdsInfoRespDTO.getId());
					gdsShelves.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES);
					List<Long> skuIds = gdsInfoManageSV.executeGdsShelves(gdsShelves);
					
					for (Long skuId : skuIds) {
						// 发送促销索引删除
						GdsUtils.sendPromMsg(gdsInfoRespDTO.getShopId(), gdsInfoRespDTO.getId(), skuId, GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES, promMsg2SolrRSV);
					}
					GdsUtils.sendGdsIndexMsg(GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES, "T_GDS_INFO", MODULE, gdsInfoRespDTO.getId(), gdsInfoRespDTO.getCatlogId());
					delAllCache(gdsInfoRespDTO.getId(), skuIds);
				}
			}
		}
	}

	@Operation(model = IGdsLogSV.GDS_MODEL, service = ServiceEnum.GDS_INFO_OPE_TYPE_CAL_SV, recordFields = { "ids", "shopId" })
	@Override
	public void batchDoGdsShelves(GdsInfoReqDTO gdsInfoReqDTO) throws BusinessException {
		paramNullCheck(gdsInfoReqDTO, true, "gdsInfoReqDTO");
		paramNullCheck(gdsInfoReqDTO.getShopId(), "gdsInfoReqDTO.shopId");
		paramNullCheck(gdsInfoReqDTO.getGdsStatus(), "gdsInfoReqDTO.gdsStatus");
		Long[] ids = gdsInfoReqDTO.getIds();
		
		List<GdsInfoMessageDTO> msgLst = new ArrayList<>(); 
		
		
		if (!ArrayUtils.isEmpty(ids)) {
			for (Long id : ids) {
				GdsInfoReqDTO temp = new GdsInfoReqDTO();
				temp.setId(id);
				GdsInfo gdsinfo = gdsInfoQuerySV.queryGdsInfoModel(temp);
				// 如果是非删除操作
				try {
					List<Long> skuIds = null;
					if (!GdsConstants.GdsInfo.GDS_STATUS_DELETE.equals(gdsInfoReqDTO.getGdsStatus())) {
						GdsInfoReqDTO gdsShelves = new GdsInfoReqDTO();
						gdsShelves.setId(gdsinfo.getId());
						gdsShelves.setGdsStatus(gdsInfoReqDTO.getGdsStatus());
						gdsShelves.setShopId(gdsInfoReqDTO.getShopId());
						skuIds = gdsInfoManageSV.executeGdsShelves(gdsShelves);
						
						
						 GdsInfoMessageDTO messageDTO = new GdsInfoMessageDTO();
	                     messageDTO.setGdsId(gdsinfo.getId());
	                     messageDTO.setShopId(gdsInfoReqDTO.getShopId());
	                     messageDTO.setCatlogId(gdsinfo.getCatlogId());
	                     messageDTO.setSkuIds(skuIds);
	                     messageDTO.setGdsStatus(gdsInfoReqDTO.getGdsStatus());
	                     messageDTO.setSendPromMsg(true);
						
	                     msgLst.add(messageDTO);
/*						for (Long skuId : skuIds) {
							// 发送促销索引删除
							GdsUtils.sendPromMsg(gdsinfo.getShopId(), gdsinfo.getId(), skuId, gdsInfoReqDTO.getGdsStatus(), promMsg2SolrRSV);
						}
						GdsUtils.sendGdsIndexMsg(gdsInfoReqDTO.getGdsStatus(), "T_GDS_INFO", MODULE, gdsinfo.getId(), gdsinfo.getCatlogId());*/
					} else {
						// 如果是删除操作
						GdsInfoReqDTO gdsShelves = new GdsInfoReqDTO();
						gdsShelves.setId(id);
						gdsShelves.setGdsStatus(gdsInfoReqDTO.getGdsStatus());
						gdsShelves.setShopId(gdsInfoReqDTO.getShopId());
						skuIds = gdsInfoManageSV.deleteGdsInfo(gdsInfoReqDTO);
					}
					delAllCache(id, skuIds);
				} catch (Exception e) {
					LogUtil.error(MODULE, "excute gdsStatus to " + gdsInfoReqDTO.getGdsStatus() + " failed!   gdsId is" + id);
				}
			}
			
			if(!msgLst.isEmpty()){
                for (Iterator<GdsInfoMessageDTO> iterator = msgLst.iterator(); iterator.hasNext();) {
                    GdsInfoMessageDTO msg = iterator.next();
                     ThreadPoolExecutorUtil.commitTask(new GdsInfoMessageTask(msg));
                }
             }
			
		}
	}

	@Operation(model = IGdsLogSV.GDS_MODEL, service = ServiceEnum.GDS_INFO_OPE_TYPE_CAL_SV, recordFields = { "id", "shopId" })
	@Override
	public void doGdsShelves(GdsInfoReqDTO gdsInfoReqDTO) throws BusinessException {
		paramNullCheck(gdsInfoReqDTO, true, "gdsInfoReqDTO");
		paramNullCheck(gdsInfoReqDTO.getId(), "gdsInfoReqDTO.id");
		paramNullCheck(gdsInfoReqDTO.getShopId(), "gdsInfoReqDTO.shopId");
		paramNullCheck(gdsInfoReqDTO.getGdsStatus(), "gdsInfoReqDTO.gdsStatus");
		
		List<Long> skuIds = gdsInfoManageSV.executeGdsShelves(gdsInfoReqDTO);
		delAllCache(gdsInfoReqDTO.getId(), skuIds);
		
		
		GdsInfoReqDTO temp = new GdsInfoReqDTO();
		temp.setId(gdsInfoReqDTO.getId());
		GdsInfo gdsinfo = gdsInfoQuerySV.queryGdsInfoModel(temp);
		
		/*for (Long skuId : skuIds) {
			GdsUtils.sendPromMsg(gdsinfo.getShopId(), gdsinfo.getId(), skuId, gdsInfoReqDTO.getGdsStatus(), promMsg2SolrRSV);
		}
		GdsUtils.sendGdsIndexMsg(gdsInfoReqDTO.getGdsStatus(), "T_GDS_INFO", MODULE, gdsinfo.getId(), gdsinfo.getCatlogId());
		*/
		GdsInfoMessageDTO messageDTO = new GdsInfoMessageDTO();
        messageDTO.setGdsId(gdsinfo.getId());
        messageDTO.setShopId(gdsInfoReqDTO.getShopId());
        messageDTO.setCatlogId(gdsinfo.getCatlogId());
        messageDTO.setSkuIds(skuIds);
        messageDTO.setSendPromMsg(true);
        messageDTO.setGdsStatus(gdsInfoReqDTO.getGdsStatus());
        ThreadPoolExecutorUtil.commitTask(new GdsInfoMessageTask(messageDTO));
        //ThreadPoolExecutorUtil.commitTask(new PromMessageTask(messageDTO));
	}

	@Override
	public void editGdsShipTemplate(GdsInfoReqDTO gdsInfoReqDTO) throws BusinessException {
		paramNullCheck(gdsInfoReqDTO, true, "gdsInfoReqDTO");
		paramNullCheck(gdsInfoReqDTO.getId(), "gdsInfoReqDTO.id");
		paramNullCheck(gdsInfoReqDTO.getShopId(), "gdsInfoReqDTO.shopId");
		List<Long> skuIds = gdsInfoManageSV.editGdsShipTemplate(gdsInfoReqDTO);
		delAllCache(gdsInfoReqDTO.getId(), skuIds);
	}

	@Override
	public void delAllCache(Long gdsId, List<Long> skuIds) {
		GdsCacheUtil.delGdsInfoCache(gdsId);
		GdsCacheUtil.delGdsPicCache(gdsId);
		if (CollectionUtils.isNotEmpty(skuIds)) {
			for (Long skuId : skuIds) {
				GdsCacheUtil.delSkuInfoCache(skuId);
				GdsCacheUtil.delSkuPicCache(skuId);
			}
		}
	}

	@Operation(model = IGdsLogSV.GDS_MODEL, service = ServiceEnum.GDS_VERIFY_OPE_TYPE_CAL_SV, recordFields = { "ids", "shopId" })
	@Override
	public void doGdsVerify(GdsVerifyReqDTO gdsVerifyReqDTO) throws BusinessException {
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

	@Operation(model = IGdsLogSV.GDS_MODEL, operType = OperationType.GDS_INFO_VERIFY, recordFields = { "ids", "shopId" })
	@Override
	public void editGdsVerify(GdsVerifyReqDTO gdsVerifyReqDTO) throws BusinessException {
		paramNullCheck(gdsVerifyReqDTO, true, "gdsVerifyReqDTO");
		paramNullCheck(gdsVerifyReqDTO.getShopId(), "gdsVerifyReqDTO.shopId");
		Long[] ids = gdsVerifyReqDTO.getIds();
		if (!ArrayUtils.isEmpty(ids)) {
			for (Long id : ids) {
				gdsVerifyReqDTO.setGdsId(id);
				List<GdsSkuInfo> skus = gdsInfoManageSV.editGdsVerify(gdsVerifyReqDTO);
				if (CollectionUtils.isNotEmpty(skus) && GdsConstants.GdsVerify.VERIFY_APPROVED.equals(gdsVerifyReqDTO.getVerifyStatus())) {
					List<Long> skuIds = new ArrayList<Long>();
					String status="";
					for (GdsSkuInfo gdsSkuInfo : skus) {
						skuIds.add(gdsSkuInfo.getId());
						GdsUtils.sendPromMsg(gdsSkuInfo.getShopId(), gdsSkuInfo.getGdsId(), gdsSkuInfo.getId(), gdsSkuInfo.getGdsStatus(), promMsg2SolrRSV);
						status=gdsSkuInfo.getGdsStatus();
					}
					GdsInfoReqDTO temp = new GdsInfoReqDTO();
					temp.setId(id);
					GdsInfo gdsinfo = gdsInfoQuerySV.queryGdsInfoModel(temp);
					if (StringUtil.isNotBlank(gdsinfo.getExt1())) {
						gdsinfo.setId(Long.valueOf(gdsinfo.getExt1()));
					}
					// 表明进行审核通过，有商品进行更新
					delAllCache(gdsVerifyReqDTO.getGdsId(), skuIds);
					GdsUtils.sendGdsIndexMsg(status, "T_GDS_INFO", MODULE, gdsinfo.getId(), gdsinfo.getCatlogId());
				}
				
			}
		}
	}
	//新增审核-已上架商品线下记录的审核
	@Operation(model = IGdsLogSV.GDS_MODEL, operType = OperationType.GDS_INFO_VERIFY, recordFields = { "ids", "shopId" })
	@Override
	public void editGdsVerifyShelved(GdsVerifyReqDTO gdsVerifyReqDTO) throws BusinessException {
		paramNullCheck(gdsVerifyReqDTO, true, "gdsVerifyReqDTO");
		paramNullCheck(gdsVerifyReqDTO.getShopId(), "gdsVerifyReqDTO.shopId");
		Long[] ids = gdsVerifyReqDTO.getIds();
		List<GdsSkuInfo> skuInfos = new ArrayList<GdsSkuInfo>();
		if (!ArrayUtils.isEmpty(ids)) {
			for (Long id : ids) {
				gdsVerifyReqDTO.setGdsId(id);
				skuInfos = gdsInfoManageSV.editGdsVerifyShelved(gdsVerifyReqDTO);
				/*if (CollectionUtils.isNotEmpty(skus) && GdsConstants.GdsVerify.VERIFY_APPROVED.equals(gdsVerifyReqDTO.getVerifyStatus())) {
					List<Long> skuIds = new ArrayList<Long>();
					String status="";
					for (GdsSkuInfo gdsSkuInfo : skus) {
						skuIds.add(gdsSkuInfo.getId());
						GdsUtils.sendPromMsg(gdsSkuInfo.getShopId(), gdsSkuInfo.getGdsId(), gdsSkuInfo.getId(), gdsSkuInfo.getGdsStatus(), promMsg2SolrRSV);
						status=gdsSkuInfo.getGdsStatus();
					}
					GdsInfoReqDTO temp = new GdsInfoReqDTO();
					temp.setId(id);
					GdsInfo gdsinfo = gdsInfoQuerySV.queryGdsInfoModel(temp);
					GdsUtils.sendGdsIndexMsg(status, "T_GDS_INFO", MODULE, gdsinfo.getId(), gdsinfo.getCatlogId());
					// 表明进行审核通过，有商品进行更新
					delAllCache(gdsVerifyReqDTO.getGdsId(), skuIds);
				}*/
			}
		}
	}
	

}
