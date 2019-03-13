package com.zengshi.ecp.goods.service.busi.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsArrmsgMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsArrmsgShopIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsArrmsgStaffIdxMapper;
import com.zengshi.ecp.goods.dao.model.GdsArrmsg;
import com.zengshi.ecp.goods.dao.model.GdsArrmsgCriteria;
import com.zengshi.ecp.goods.dao.model.GdsArrmsgShopIdx;
import com.zengshi.ecp.goods.dao.model.GdsArrmsgShopIdxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsArrmsgStaffIdx;
import com.zengshi.ecp.goods.dao.model.GdsArrmsgStaffIdxCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsArrmsgReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsArrmsgRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsArrmsgSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoQuerySV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;

public class GdsArrmsgSVImpl extends AbstractSVImpl implements IGdsArrmsgSV {
	@Resource
	private GdsArrmsgMapper gdsArrmsgMapper;

	@Resource
	private GdsArrmsgShopIdxMapper gdsArrmsgShopIdxMapper;

	@Resource
	private GdsArrmsgStaffIdxMapper gdsArrmsgStaffIdxMapper;

	@Resource(name = "seq_gds_arrmsg")
	private Sequence seqGdsArrmsg;

	@Resource(name = "gdsSkuInfoQuerySV")
	private  IGdsSkuInfoQuerySV gdsSkuInfoQuerySV;

	private static final String DEFAULT_ORDER_BY = "NOTICE_ID DESC";

	@Override
	public void saveGdsArrmsg(GdsArrmsgReqDTO reqDTO) throws BusinessException {
		long id = seqGdsArrmsg.nextValue();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if (StringUtil.isBlank(reqDTO.getStatus())) {
			reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
		}

		GdsSkuInfoReqDTO skuInfoReqDTO = new GdsSkuInfoReqDTO();
		skuInfoReqDTO.setId(reqDTO.getSkuId());
		GdsSkuInfoRespDTO sku = gdsSkuInfoQuerySV.querySkuInfoByOptions(
				skuInfoReqDTO, SkuQueryOption.BASIC);

		reqDTO.setNoticeType(GdsConstants.GdsArrMsg.ARRMSG_TYPE_ARR);
		// 插入商品到货通知主表（t_gds_arrmsg）
		GdsArrmsg gdsArrmsg = new GdsArrmsg();
		ObjectCopyUtil.copyObjValue(reqDTO, gdsArrmsg, null, true);
		ObjectCopyUtil.copyObjValue(sku, gdsArrmsg, null, true);
		gdsArrmsg.setStaffId(reqDTO.getStaff().getId());
		gdsArrmsg.setCreateTime(timestamp);
		gdsArrmsg.setId(id);
		gdsArrmsgMapper.insert(gdsArrmsg);
		// 插入到商品到货通知店铺维度索引表（t_gds_arrmsg_shop_idx）
		GdsArrmsgShopIdx gdsArrmsgShopIdx = new GdsArrmsgShopIdx();
		ObjectCopyUtil.copyObjValue(gdsArrmsg, gdsArrmsgShopIdx, null, true);
		gdsArrmsgShopIdx.setNoticeId(id);
		gdsArrmsgShopIdx.setCreateTime(timestamp);
		// 获取前店参数
		// getArrmsgShopIdxParam(gdsArrmsgShopIdx,reqDTO);
		preInsert(reqDTO, gdsArrmsgShopIdx);
		gdsArrmsgShopIdxMapper.insert(gdsArrmsgShopIdx);
		// 插入到商品到货通知用户维度索引表（t_gds_arrmsg_staff_idx）
		GdsArrmsgStaffIdx gdsArrmsgStaffIdx = new GdsArrmsgStaffIdx();
		ObjectCopyUtil.copyObjValue(gdsArrmsg, gdsArrmsgStaffIdx, null, true);
		// 获取前店参数
		// getGdsArrmsgStaffIdxParam(gdsArrmsgStaffIdx,reqDTO);
		preInsert(reqDTO, gdsArrmsgStaffIdx);
		gdsArrmsgStaffIdx.setNoticeId(id);
		gdsArrmsgStaffIdxMapper.insert(gdsArrmsgStaffIdx);
	}

	/**
	 * 
	 * queryGdsArrMsgById:(查询单个到货通知). <br/> 
	 * 
	 * @author linwb3
	 * @param id
	 * @param stauts
	 * @return 
	 * @since JDK 1.6
	 */
	private GdsArrmsg queryGdsArrMsgById(Long id, String... stauts) {
		GdsArrmsgCriteria example = new GdsArrmsgCriteria();
		GdsArrmsgCriteria.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		if (!ArrayUtils.isEmpty(stauts) && StringUtil.isNotBlank(stauts[0])) {
			criteria.andStatusEqualTo(stauts[0]);
		}
		List<GdsArrmsg> arrs = gdsArrmsgMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(arrs)) {
			return arrs.get(0);
		}
		return null;
	}

	@Override
	public void deleteGdsArrmsg(GdsArrmsgReqDTO reqDTO)
			throws BusinessException {
		GdsArrmsg arrmsg=queryGdsArrMsgById(reqDTO.getId());

		Long updateStaff = reqDTO.getUpdateStaff();
		Timestamp updateTime = new Timestamp(System.currentTimeMillis());
		// 失效商品到货通知主表（t_gds_arrmsg）
		GdsArrmsg gdsArrmsg = new GdsArrmsg();
		gdsArrmsg.setStatus(GdsConstants.Commons.STATUS_INVALID);
		gdsArrmsg.setId(reqDTO.getId());
		gdsArrmsg.setUpdateStaff(updateStaff);
		gdsArrmsg.setUpdateTime(updateTime);
		gdsArrmsgMapper.updateByPrimaryKeySelective(gdsArrmsg);
		// 失效商品到货通知店铺维度索引表（t_gds_arrmsg_shop_idx）
		GdsArrmsgShopIdx gdsArrmsgShopIdx = new GdsArrmsgShopIdx();
		gdsArrmsgShopIdx.setStatus(GdsConstants.Commons.STATUS_INVALID);
		gdsArrmsgShopIdx.setUpdateStaff(updateStaff);
		gdsArrmsgShopIdx.setUpdateTime(updateTime);
		GdsArrmsgShopIdxCriteria gdsArrmsgShopIdxCriteria = new GdsArrmsgShopIdxCriteria();
		GdsArrmsgShopIdxCriteria.Criteria idxCriteria = gdsArrmsgShopIdxCriteria
				.createCriteria();
		idxCriteria.andShopIdEqualTo(arrmsg.getShopId());
		idxCriteria.andNoticeIdEqualTo(reqDTO.getId());
		idxCriteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		gdsArrmsgShopIdxMapper.updateByExampleSelective(gdsArrmsgShopIdx,
				gdsArrmsgShopIdxCriteria);

		// 失效商品到货通知用户维度索引表（t_gds_arrmsg_staff_idx）
		GdsArrmsgStaffIdx gdsArrmsgStaffIdx = new GdsArrmsgStaffIdx();
		gdsArrmsgStaffIdx.setStatus(GdsConstants.Commons.STATUS_INVALID);
		gdsArrmsgStaffIdx.setUpdateStaff(updateStaff);
		gdsArrmsgStaffIdx.setUpdateTime(updateTime);
		GdsArrmsgStaffIdxCriteria gdsArrmsgStaffIdxCriteria = new GdsArrmsgStaffIdxCriteria();
		GdsArrmsgStaffIdxCriteria.Criteria staffIdxCriteria = gdsArrmsgStaffIdxCriteria
				.createCriteria();
		staffIdxCriteria.andShopIdEqualTo(arrmsg.getShopId());
		staffIdxCriteria.andNoticeIdEqualTo(reqDTO.getId());
		staffIdxCriteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		gdsArrmsgStaffIdxMapper.updateByExampleSelective(gdsArrmsgStaffIdx,
				gdsArrmsgStaffIdxCriteria);
	}

	@Override
	public void sendNotice(GdsArrmsgReqDTO reqDTO) throws BusinessException {

	}

	@Override
	public PageResponseDTO<GdsArrmsgRespDTO> queryGdsArrmsg(
			GdsArrmsgReqDTO reqDTO) throws BusinessException {
		GdsArrmsgShopIdxCriteria gdsArrmsgShopIdxCriteria = new GdsArrmsgShopIdxCriteria();
		gdsArrmsgShopIdxCriteria.setOrderByClause(DEFAULT_ORDER_BY);
		// 获取查询条件
		getReuestCondition(reqDTO, gdsArrmsgShopIdxCriteria);
		return super.queryByPagination(reqDTO, gdsArrmsgShopIdxCriteria, true,
				new PaginationCallback<GdsArrmsgShopIdx, GdsArrmsgRespDTO>() {
					// 查询记录集合
					@Override
					public List<GdsArrmsgShopIdx> queryDB(BaseCriteria criteria) {
						return gdsArrmsgShopIdxMapper
								.selectByExample((GdsArrmsgShopIdxCriteria) criteria);
					}

					// 查询记录总数
					@Override
					public long queryTotal(BaseCriteria criteria) {
						return gdsArrmsgShopIdxMapper
								.countByExample((GdsArrmsgShopIdxCriteria) criteria);
					}

					@Override
					public GdsArrmsgRespDTO warpReturnObject(
							GdsArrmsgShopIdx record) {
						GdsArrmsgRespDTO respDTO = new GdsArrmsgRespDTO();
						ObjectCopyUtil
								.copyObjValue(record, respDTO, null, true);
						respDTO.setId(record.getNoticeId());

						BaseParamDTO paramDTO = BaseParamUtil
								.fetchParamDTO(
										GdsConstants.BaseParamConstants.GDS_ARRMSG_STATUS,
										record.getStatus());
						if (null != paramDTO) {
							respDTO.setStatusName(paramDTO.getSpValue());
						}
					    
						stuffStockStatus(record, respDTO);
						
						return respDTO;
					}

					private void stuffStockStatus(GdsArrmsgShopIdx record,
							GdsArrmsgRespDTO respDTO) {
						GdsSkuInfoReqDTO condition = new GdsSkuInfoReqDTO();
						condition.setStatus(GdsConstants.Commons.STATUS_VALID);
						condition.setGdsId(record.getGdsId());
						condition.setId(record.getSkuId());
						
						GdsSkuInfoRespDTO skuInfoRespDTO = gdsSkuInfoQuerySV.querySkuInfoByOptions(condition, SkuQueryOption.STOCK);
						
						if(null != skuInfoRespDTO){
							respDTO.setStockStatus(skuInfoRespDTO.calcAvalidStocks().toString());
						}else{
							respDTO.setStockStatus("0");
						}
					}

				});
	}

	@Override
	public PageResponseDTO<GdsArrmsgRespDTO> queryGdsArrmsgFromStaff(
			GdsArrmsgReqDTO reqDTO) throws BusinessException {
		GdsArrmsgStaffIdxCriteria gdsArrmsgStaffIdxCriteria = new GdsArrmsgStaffIdxCriteria();
		gdsArrmsgStaffIdxCriteria.setOrderByClause(DEFAULT_ORDER_BY);
		// 获取查询条件
		getReuestCondition(reqDTO, gdsArrmsgStaffIdxCriteria);
		return super.queryByPagination(reqDTO, gdsArrmsgStaffIdxCriteria, true,
				new PaginationCallback<GdsArrmsgStaffIdx, GdsArrmsgRespDTO>() {
					// 查询记录集合
					@Override
					public List<GdsArrmsgStaffIdx> queryDB(BaseCriteria criteria) {
						return gdsArrmsgStaffIdxMapper
								.selectByExample((GdsArrmsgStaffIdxCriteria) criteria);
					}

					// 查询记录总数
					@Override
					public long queryTotal(BaseCriteria criteria) {
						return gdsArrmsgStaffIdxMapper
								.countByExample((GdsArrmsgStaffIdxCriteria) criteria);
					}

					@Override
					public GdsArrmsgRespDTO warpReturnObject(
							GdsArrmsgStaffIdx record) {
						GdsArrmsgRespDTO respDTO = new GdsArrmsgRespDTO();
						ObjectCopyUtil
								.copyObjValue(record, respDTO, null, true);
						stuffStockStatus(record, respDTO);
						return respDTO;
					}

					private void stuffStockStatus(GdsArrmsgStaffIdx record,
							GdsArrmsgRespDTO respDTO) {
						GdsSkuInfoReqDTO condition = new GdsSkuInfoReqDTO();
						condition.setStatus(GdsConstants.Commons.STATUS_VALID);
						condition.setGdsId(record.getGdsId());
						condition.setId(record.getSkuId());
						
						GdsSkuInfoRespDTO skuInfoRespDTO = gdsSkuInfoQuerySV.querySkuInfoByOptions(condition, SkuQueryOption.STOCK);
						
						if(null != skuInfoRespDTO){
							respDTO.setStockStatus(skuInfoRespDTO.calcAvalidStocks().toString());
						}else{
							respDTO.setStockStatus("0");
						}
					}

				});
	}

	private void getReuestCondition(GdsArrmsgReqDTO reqDTO,
			GdsArrmsgStaffIdxCriteria gdsArrmsgStaffIdxCriteria) {
		GdsArrmsgStaffIdxCriteria.Criteria idxCriteria = gdsArrmsgStaffIdxCriteria
				.createCriteria();
		Long gdsId = reqDTO.getGdsId();
		if (null != gdsId) {
			idxCriteria.andGdsIdEqualTo(gdsId);
		}
		String gdsName = reqDTO.getGdsName();
		if (StringUtil.isNotBlank(reqDTO.getStatus())) {
			idxCriteria.andStatusEqualTo(reqDTO.getStatus());
		}
		if (null != reqDTO.getStaffId()) {
			idxCriteria.andStaffIdEqualTo(reqDTO.getStaffId());
		}
		if (StringUtil.isNotBlank(reqDTO.getGdsName())) {
			idxCriteria.andGdsNameLike("%" + reqDTO.getGdsName() + "%");
		}

		gdsArrmsgStaffIdxCriteria
				.setLimitClauseStart(reqDTO.getStartRowIndex());
		gdsArrmsgStaffIdxCriteria.setLimitClauseCount(reqDTO.getPageSize());
	}

	private void getReuestCondition(GdsArrmsgReqDTO reqDTO,
			GdsArrmsgShopIdxCriteria gdsArrmsgShopIdxCriteria) {
		GdsArrmsgShopIdxCriteria.Criteria idxCriteria = gdsArrmsgShopIdxCriteria
				.createCriteria();
		Long gdsId = reqDTO.getGdsId();
		if (null != gdsId) {
			idxCriteria.andGdsIdEqualTo(gdsId);
		}
		if (null != reqDTO.getShopId()) {
			idxCriteria.andShopIdEqualTo(reqDTO.getShopId());
		}
		if (StringUtil.isNotBlank(reqDTO.getStatus())) {
			idxCriteria.andStatusEqualTo(reqDTO.getStatus());
		}
		if (StringUtil.isNotBlank(reqDTO.getGdsName())) {
			idxCriteria.andGdsNameLike("%" + reqDTO.getGdsName() + "%");
		}
		gdsArrmsgShopIdxCriteria.setLimitClauseStart(reqDTO.getStartRowIndex());
		gdsArrmsgShopIdxCriteria.setLimitClauseCount(reqDTO.getPageSize());
	}

}
