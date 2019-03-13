package com.zengshi.ecp.goods.service.busi.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.goods.dao.mapper.busi.StockCompanyInfoIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.StockCompanyRepIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.StockInDetailMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.StockInfoAdaptMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.StockInfoHisMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.StockInfoMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.StockOptRecordMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.StockOutDetailMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.StockPreOccupyMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.StockRepAdaptHisMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.StockRepAdaptMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.StockRepHisMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.StockRepInfoIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.StockRepMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.StockShopInfoIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.StockShopRepIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.StockSkuEntityMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.manual.StockInfoExtraMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.manual.StockOptRecordExtraMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.manual.StockPreOccupyExtraMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.manual.StockRepExtraMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.manual.StockShopInfoIdxExtraMapper;
import com.zengshi.ecp.goods.dao.model.StockCompanyInfoIdx;
import com.zengshi.ecp.goods.dao.model.StockCompanyInfoIdxCriteria;
import com.zengshi.ecp.goods.dao.model.StockCompanyRepIdx;
import com.zengshi.ecp.goods.dao.model.StockCompanyRepIdxCriteria;
import com.zengshi.ecp.goods.dao.model.StockInDetail;
import com.zengshi.ecp.goods.dao.model.StockInfo;
import com.zengshi.ecp.goods.dao.model.StockInfoAdapt;
import com.zengshi.ecp.goods.dao.model.StockInfoAdaptCriteria;
import com.zengshi.ecp.goods.dao.model.StockInfoCriteria;
import com.zengshi.ecp.goods.dao.model.StockInfoHis;
import com.zengshi.ecp.goods.dao.model.StockInfoHisCriteria;
import com.zengshi.ecp.goods.dao.model.StockOptRecord;
import com.zengshi.ecp.goods.dao.model.StockOutDetail;
import com.zengshi.ecp.goods.dao.model.StockOutDetailCriteria;
import com.zengshi.ecp.goods.dao.model.StockPreOccupy;
import com.zengshi.ecp.goods.dao.model.StockPreOccupyCriteria;
import com.zengshi.ecp.goods.dao.model.StockRep;
import com.zengshi.ecp.goods.dao.model.StockRepAdapt;
import com.zengshi.ecp.goods.dao.model.StockRepAdaptCriteria;
import com.zengshi.ecp.goods.dao.model.StockRepAdaptHis;
import com.zengshi.ecp.goods.dao.model.StockRepCriteria;
import com.zengshi.ecp.goods.dao.model.StockRepHis;
import com.zengshi.ecp.goods.dao.model.StockRepInfoIdx;
import com.zengshi.ecp.goods.dao.model.StockRepInfoIdxCriteria;
import com.zengshi.ecp.goods.dao.model.StockShopInfoIdx;
import com.zengshi.ecp.goods.dao.model.StockShopInfoIdxCriteria;
import com.zengshi.ecp.goods.dao.model.StockShopRepIdx;
import com.zengshi.ecp.goods.dao.model.StockShopRepIdxCriteria;
import com.zengshi.ecp.goods.dao.model.StockSkuEntity;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.AffirmStockDTO;
import com.zengshi.ecp.goods.dubbo.dto.AffirmStockMainDTO;
import com.zengshi.ecp.goods.dubbo.dto.DeliverySkuStcokMainReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.DeliverySkuStcokReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.GdsStockInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.GdsStockRepRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoForGdsReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoPageRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockRepAdaptMainDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockRepAdaptReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockRepAdaptRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockRepMainReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockRepPageRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockRepReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockRepRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockReturnInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockReturnSubReqDTO;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoQuerySV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsTypeSV;
import com.zengshi.ecp.goods.service.thread.GdsInfoMessageDTO;
import com.zengshi.ecp.goods.service.thread.GdsInfoMessageTask;
import com.zengshi.ecp.goods.service.thread.ThreadPoolExecutorUtil;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015-8-7下午5:13:47 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author zjh
 * @version
 * @since JDK 1.6
 */
public class GdsStockSVImpl extends AbstractSVImpl implements IGdsStockSV {
	@Resource(name = "seq_stock_rep")
	private Sequence seqStockRep;

	@Resource(name = "seq_stock_rep_adapt")
	private Sequence seqStockRepAdapt;

	@Resource(name = "seq_stock_rep_adapt_his")
	private Sequence seqStockRepAdaptHis;

	@Resource(name = "seq_stock_rep_his")
	private Sequence seqStockRepHis;

	@Resource(name = "seq_stock_info_his")
	private Sequence seqStockInfoHis;

	@Resource(name = "seq_stock_info")
	private Sequence seqStockInfo;

	@Resource(name = "seq_stock_opt_record")
	private Sequence seqStockOptRecord;

	@Resource(name = "seq_stock_pre_occupy")
	private Sequence seqStockPreOccupy;

	@Resource(name = "seq_stock_sku_entity")
	private Sequence seqStockSkuEntity;

	@Resource(name = "seq_stock_out_detail")
	private Sequence seqStockOutDetail;

	@Resource(name = "seq_stock_in_detail")
	private Sequence seqStockInDetail;

	@Resource(name = "seq_stock_info_adapt")
	private Sequence seqStockInfoAdapt;

	@Resource
	private StockRepMapper repMapper;

	@Resource
	private StockShopRepIdxMapper shopRepIdxMapper;

	@Resource
	private StockCompanyRepIdxMapper companyRepIdxMapper;

	@Resource
	private StockRepInfoIdxMapper stockRepInfoIdxMapper;

	@Resource
	private StockRepExtraMapper stockRepExtraMapper;

	@Resource
	private StockRepAdaptMapper stockRepAdaptMapper;

	@Resource
	private StockRepAdaptHisMapper stockRepAdaptHisMapper;

	@Resource
	private StockRepHisMapper stockRepHisMapper;

	@Resource
	private StockInfoMapper stockInfoMapper;

	@Resource
	private StockInfoHisMapper stockInfoHisMapper;

	@Resource
	private StockShopInfoIdxMapper stockShopInfoIdxMapper;

	@Resource
	private StockInfoExtraMapper stockInfoExtraMapper;

	@Resource
	private StockOptRecordMapper stockOptRecordMapper;

	@Resource
	private StockPreOccupyMapper stockPreOccupyMapper;

	@Resource
	private StockPreOccupyExtraMapper stockPreOccupyExtraMapper;

	@Resource
	private StockSkuEntityMapper stockSkuEntityMapper;

	@Resource
	private StockOutDetailMapper stockOutDetailMapper;

	@Resource
	private StockCompanyInfoIdxMapper stockCompanyInfoIdxMapper;

	@Resource
	private StockInDetailMapper stockInDetailMapper;

	@Resource
	private StockInfoAdaptMapper stockInfoAdaptMapper;

	@Resource
	private IGdsInfoQuerySV gdsInfoQuerySV;

	@Resource
	private IGdsSkuInfoQuerySV skuInfoQuerySV;

	@Resource
	private IGdsCategorySV categorySV;

	@Resource
	private ICustInfoRSV custInfoRSV;

	@Resource
	private IGdsTypeSV gdsTypeSV;

	@Resource
	private StockOptRecordExtraMapper optRecordExtraMapper;
	@Resource
	private StockShopInfoIdxExtraMapper shopInfoIdxExtraMapper;

	/**
	 * 
	 * TODO 新增仓库.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#addStockRep(com.zengshi.ecp.goods.dao.model.StockRep)
	 */
	@Override
	public StockRep addStockRep(StockRepMainReqDTO stockRepMainDto) throws BusinessException {
		// 新增仓库记录
		StockRep stockRep = new StockRep();
		ObjectCopyUtil.copyObjValue(stockRepMainDto.getStockRepDTO(), stockRep, null, false);
		stockRep.setId(seqStockRep.nextValue());
		stockRep.setCreateStaff(stockRepMainDto.getStaffId());
		stockRep.setCreateTime(new Timestamp(System.currentTimeMillis()));
		stockRep.setUpdateStaff(stockRepMainDto.getStaffId());
		stockRep.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		stockRep.setStatus(GdsConstants.Commons.STATUS_VALID);
		repMapper.insertSelective(stockRep);
		// 新增店铺仓库索引记录
		if (stockRep.getShopId() != null && stockRep.getShopId() != 0L) {
			StockShopRepIdx shopRepIdx = new StockShopRepIdx();
			shopRepIdx.setRepCode(stockRep.getId());
			shopRepIdx.setRepName(stockRep.getRepName());
			shopRepIdx.setRepType(stockRep.getRepType());
			shopRepIdx.setShopId(stockRep.getShopId());
			shopRepIdx.setStatus(stockRep.getStatus());
			addStockShopRepIdx(shopRepIdx);

		}
		// 新增企业索引记录
		StockCompanyRepIdx companyRepIdx = new StockCompanyRepIdx();
		companyRepIdx.setRepCode(stockRep.getId());
		companyRepIdx.setRepName(stockRep.getRepName());
		companyRepIdx.setRepType(stockRep.getRepType());
		companyRepIdx.setStatus(stockRep.getStatus());
		companyRepIdx.setCompanyId(stockRep.getCompanyId());
		addStockCompanyRepIdx(companyRepIdx);
		// 新增仓库适用范围记录
		stockRepMainDto.getStockRepDTO().setId(stockRep.getId());
		// 如果是总仓模式，添加默认适用范围记录
		if (GdsConstants.GdsStock.STOCK_REP_TYPE_PUBLIC.equals(stockRep.getRepType())) {

			List<StockRepAdaptReqDTO> adaptReqDTOs = new ArrayList<StockRepAdaptReqDTO>();
			StockRepAdaptReqDTO adaptReqDTO = new StockRepAdaptReqDTO();
			adaptReqDTO.setRepCode(stockRep.getId());
			adaptReqDTO.setShopId(stockRep.getShopId());
			adaptReqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
			adaptReqDTOs.add(adaptReqDTO);
			stockRepMainDto.setNewRepAdaptList(adaptReqDTOs);
		}
		editStockRepAdaptInfo(stockRepMainDto);
		return stockRep;
	}

	/**
	 * 
	 * TODO 新增店铺仓库索引表.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#addStockShopRepIdx(com.zengshi.ecp.goods.dao.model.StockShopRepIdx)
	 */
	@Override
	public void addStockShopRepIdx(StockShopRepIdx shopRepIdx) throws BusinessException {
		shopRepIdxMapper.insertSelective(shopRepIdx);

	}

	/**
	 * 
	 * TODO 新增企业仓库索引表.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#addStockCompanyRepIdx(com.zengshi.ecp.goods.dao.model.StockCompanyRepIdx)
	 */
	@Override
	public void addStockCompanyRepIdx(StockCompanyRepIdx companyRepIdx) throws BusinessException {
		companyRepIdxMapper.insertSelective(companyRepIdx);
	}

	/**
	 * 
	 * TODO 新增仓库适用范围记录（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#addStockRepAdapt(com.zengshi.ecp.goods.dubbo.dto.stock.StockRepAdaptReqDTO)
	 */
	@Override
	public void addStockRepAdapt(StockRepAdaptReqDTO stockRepAdaptDTO) throws BusinessException {
		StockRepAdapt stockRepAdapt = new StockRepAdapt();
		ObjectCopyUtil.copyObjValue(stockRepAdaptDTO, stockRepAdapt, null, false);
		stockRepAdapt.setId(seqStockRepAdapt.nextValue());
		stockRepAdapt.setCreateTime(new Timestamp(System.currentTimeMillis()));
		stockRepAdapt.setCreateStaff(stockRepAdaptDTO.getStaffId());
		stockRepAdapt.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		stockRepAdapt.setUpdateStaff(stockRepAdaptDTO.getStaffId());
		stockRepAdapt.setStatus(GdsConstants.Commons.STATUS_VALID);
	}

	/**
	 * 
	 * TODO 分页查询店铺下仓库信息（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#queryStockRepInfoByShopId(com.zengshi.ecp.goods.dubbo.dto.stock.StockRepReqDTO)
	 */
	@Override
	public PageResponseDTO<StockRepPageRespDTO> queryStockRepInfoByShopId(StockRepReqDTO stockRepDTO) throws BusinessException {

		StockShopRepIdxCriteria stockShopRepIdxCriteria = new StockShopRepIdxCriteria();
		StockShopRepIdxCriteria.Criteria criteria = stockShopRepIdxCriteria.createCriteria();
		criteria.andShopIdEqualTo(stockRepDTO.getShopId());
		if (stockRepDTO.getStatus() != null && !"".equals(stockRepDTO.getStatus())) {
			criteria.andStatusEqualTo(stockRepDTO.getStatus());
		}
		if (stockRepDTO.getRepName() != null && !"".equals(stockRepDTO.getRepName())) {
			criteria.andRepNameLike("%" + stockRepDTO.getRepName() + "%");
		}
		stockShopRepIdxCriteria.setLimitClauseStart(stockRepDTO.getStartRowIndex());
		stockShopRepIdxCriteria.setLimitClauseCount(stockRepDTO.getPageSize());
		stockShopRepIdxCriteria.setOrderByClause("rep_code desc");
		return super.queryByPagination(stockRepDTO, stockShopRepIdxCriteria, true, new PaginationCallback<StockShopRepIdx, StockRepPageRespDTO>() {

			@Override
			public List<StockShopRepIdx> queryDB(BaseCriteria arg0) {
				return shopRepIdxMapper.selectByExample((StockShopRepIdxCriteria) arg0);

			}

			@Override
			public StockRepPageRespDTO warpReturnObject(StockShopRepIdx stockShopRepIdx) {
				StockRepPageRespDTO pageDTO = new StockRepPageRespDTO();

				// 获取仓库信息
				if (stockShopRepIdx.getRepCode() == null) {
					throw new BusinessException(GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235025);
				}
				StockRep stockRep = repMapper.selectByPrimaryKey(stockShopRepIdx.getRepCode());
				ObjectCopyUtil.copyObjValue(stockRep, pageDTO, null, false);
				// 获取仓库下的商品数量(接口有问题后续补)
				StockRepInfoIdxCriteria stockRepInfoIdxCriteria = new StockRepInfoIdxCriteria();
				StockRepInfoIdxCriteria.Criteria criteria3 = stockRepInfoIdxCriteria.createCriteria();
				criteria3.andRepCodeEqualTo(stockRep.getId());
				// int count =
				// repMapper.countByExample(stockRepCriteria);
				Long count = stockRepExtraMapper.countGroupByGdsId(stockRepInfoIdxCriteria);
				if (count == null) {
					pageDTO.setCount(0L);
				} else {
					pageDTO.setCount(count);
				}
				return pageDTO;
			}

			@Override
			public long queryTotal(BaseCriteria arg0) {

				return shopRepIdxMapper.countByExample((StockShopRepIdxCriteria) arg0);

			}
		});

	}

	/**
	 * 
	 * TODO 获取店铺仓库覆盖的省份列表（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#queryStockRepAdaptProvinceByShopId(com.zengshi.ecp.goods.dubbo.dto.stock.StockRepAdaptReqDTO)
	 */
	@Override
	public List<StockRepAdaptRespDTO> queryStockRepAdaptProvinceByShopId(StockRepAdaptReqDTO stockRepAdaptDTO) throws BusinessException {
		StockRepAdaptCriteria stockRepAdaptCriteria = new StockRepAdaptCriteria();
		StockRepAdaptCriteria.Criteria criteria = stockRepAdaptCriteria.createCriteria();
		criteria.andShopIdEqualTo(stockRepAdaptDTO.getShopId());
		if (stockRepAdaptDTO.getAdaptCountry() != null && !"".equals(stockRepAdaptDTO.getAdaptCountry())) {
			criteria.andAdaptCountryEqualTo(stockRepAdaptDTO.getAdaptCountry());
		}
		criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		// 过滤共仓适用范围记录
		criteria.andAdaptProvinceIsNotNull();
		List<StockRepAdapt> stockRepAdapts = stockRepExtraMapper.selectGroupByProvince(stockRepAdaptCriteria);
		List<StockRepAdaptRespDTO> stockRepAdaptDTOs = new ArrayList<StockRepAdaptRespDTO>();

		if (stockRepAdapts != null && stockRepAdapts.size() > 0) {
			for (StockRepAdapt stockRepAdapt : stockRepAdapts) {
				StockRepAdaptRespDTO repAdaptDTO = new StockRepAdaptRespDTO();
				ObjectCopyUtil.copyObjValue(stockRepAdapt, repAdaptDTO, null, false);
				stockRepAdaptDTOs.add(repAdaptDTO);

			}
		}
		return stockRepAdaptDTOs;
	}

	/**
	 * 
	 * TODO 获取店铺在某个省份下覆盖的地市列表（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#queryStockRepAdaptCityByProvince(com.zengshi.ecp.goods.dubbo.dto.stock.StockRepAdaptReqDTO)
	 */
	@Override
	public List<StockRepAdaptRespDTO> queryStockRepAdaptCityByProvince(StockRepAdaptReqDTO stockRepAdaptDTO) throws BusinessException {
		StockRepAdaptCriteria stockRepAdaptCriteria = new StockRepAdaptCriteria();
		StockRepAdaptCriteria.Criteria criteria = stockRepAdaptCriteria.createCriteria();
		criteria.andShopIdEqualTo(stockRepAdaptDTO.getShopId());
		criteria.andAdaptProvinceEqualTo(stockRepAdaptDTO.getAdaptProvince());
		criteria.andAdaptCityIsNotNull();
		criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		List<StockRepAdapt> stockRepAdapts = stockRepExtraMapper.selectGroupByCity(stockRepAdaptCriteria);
		List<StockRepAdaptRespDTO> repAdaptDTOs = new ArrayList<StockRepAdaptRespDTO>();
		if (stockRepAdapts != null && stockRepAdapts.size() > 0) {
			for (StockRepAdapt stockRepAdapt : stockRepAdapts) {
				StockRepAdaptRespDTO adaptDTO = new StockRepAdaptRespDTO();
				ObjectCopyUtil.copyObjValue(stockRepAdapt, adaptDTO, null, false);
				// 编辑时获取当前仓库是否在该市有覆盖
				if (stockRepAdaptDTO.getRepCode() != null && 0L != stockRepAdaptDTO.getRepCode()) {
					stockRepAdapt.setRepCode(stockRepAdaptDTO.getRepCode());
					stockRepAdapt.setShopId(stockRepAdaptDTO.getShopId());
					Boolean ifExistAdapt = isExistAdapt(stockRepAdapt);
					adaptDTO.setIfCurrentHas(ifExistAdapt);

				}
				repAdaptDTOs.add(adaptDTO);
			}
		}
		return repAdaptDTOs;
	}

	/**
	 * 
	 * TODO 失效仓库适用范围（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#editStockRepAdaptInfo(com.zengshi.ecp.goods.dubbo.dto.stock.StockRepMainReqDTO)
	 */
	@Override
	public void editStockRepAdaptInfo(StockRepMainReqDTO stockRepMainDto) throws BusinessException {
		Long shopId = stockRepMainDto.getStockRepDTO().getShopId();
		Long repCode = stockRepMainDto.getStockRepDTO().getId();
		// 新增仓库适用范围记录
		List<StockRepAdaptReqDTO> newRepAdaptList = stockRepMainDto.getNewRepAdaptList();
		if (newRepAdaptList != null) {
			for (StockRepAdaptReqDTO repAdaptDTO : newRepAdaptList) {
				StockRepAdapt stockRepAdapt = new StockRepAdapt();
				ObjectCopyUtil.copyObjValue(repAdaptDTO, stockRepAdapt, null, false);
				stockRepAdapt.setId(seqStockRepAdapt.nextValue());
				stockRepAdapt.setCreateStaff(stockRepMainDto.getStaffId());
				stockRepAdapt.setUpdateStaff(stockRepMainDto.getStaffId());
				stockRepAdapt.setCreateTime(new Timestamp(System.currentTimeMillis()));
				stockRepAdapt.setUpdateTime(new Timestamp(System.currentTimeMillis()));
				stockRepAdapt.setStatus(GdsConstants.Commons.STATUS_VALID);
				stockRepAdapt.setRepCode(repCode);
				stockRepAdapt.setShopId(shopId);
				stockRepAdaptMapper.insertSelective(stockRepAdapt);
				// 如果是编辑，则需要补全已有单品的库存记录，默认库存数量为0
				if (stockRepMainDto.getIfNew() != null && !stockRepMainDto.getIfNew()) {
					// 新增库存适用范围记录 (先预留)
					// addStockInfoForInput(stockInfoDTO);
				}

			}
		}
		// 失效仓库适用范围记录
		List<StockRepAdaptReqDTO> delRepAdaptList = stockRepMainDto.getDelRepAdaptList();
		if (delRepAdaptList != null) {
			for (StockRepAdaptReqDTO repAdaptDTO : delRepAdaptList) {
				StockRepAdaptCriteria repAdaptCriteria = new StockRepAdaptCriteria();
				StockRepAdaptCriteria.Criteria criteria = repAdaptCriteria.createCriteria();
				if (repAdaptDTO.getAdaptCountry() != null && !"".equals(repAdaptDTO.getAdaptCountry())) {
					criteria.andAdaptCountryEqualTo(repAdaptDTO.getAdaptCountry());
				}
				if (repAdaptDTO.getAdaptProvince() != null && !"".equals(repAdaptDTO.getAdaptProvince())) {
					criteria.andAdaptProvinceEqualTo(repAdaptDTO.getAdaptProvince());
				}
				if (repAdaptDTO.getAdaptCity() != null && !"".equals(repAdaptDTO.getAdaptCity())) {
					criteria.andAdaptCityEqualTo(repAdaptDTO.getAdaptCity());
				} else {
					criteria.andAdaptCityIsNull();

				}
				criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
				criteria.andRepCodeEqualTo(repCode);
				criteria.andShopIdEqualTo(shopId);
				List<StockRepAdapt> adapts = stockRepAdaptMapper.selectByExample(repAdaptCriteria);
				if (adapts != null && adapts.size() > 0) {

					StockRepAdapt adapt = adapts.get(0);
					// 失效库存适用范围记录
					deleteStockRepAdapt(adapt);

				}
			}
		}
	}

	/**
	 * 
	 * TODO 判断仓库适用范围是否到地市一级（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#isExistCityLevel(com.zengshi.ecp.goods.dao.model.StockRepAdapt)
	 */
	@Override
	public Boolean isExistCityLevel(StockRepAdaptRespDTO stockRepAdapt) throws BusinessException {

		StockRepAdaptCriteria stockRepAdaptCriteria = new StockRepAdaptCriteria();
		StockRepAdaptCriteria.Criteria criteria = stockRepAdaptCriteria.createCriteria();
		criteria.andAdaptProvinceEqualTo(stockRepAdapt.getAdaptProvince());
		if (stockRepAdapt.getRepCode() != null && 0L != stockRepAdapt.getRepCode()) {
			criteria.andRepCodeEqualTo(stockRepAdapt.getRepCode());
		}
		criteria.andShopIdEqualTo(stockRepAdapt.getShopId());
		criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		criteria.andAdaptCityIsNotNull();
		Long count = stockRepAdaptMapper.countByExample(stockRepAdaptCriteria);
		if (count == 0L) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 
	 * TODO 标记仓库在某个省份下的覆盖情况：1店铺仓库是否覆盖到地市一级2当前仓库是否有覆盖（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#tagProvinceLevel(com.zengshi.ecp.goods.dubbo.dto.stock.StockRepAdaptReqDTO)
	 */
	@Override
	public StockRepAdaptMainDTO tagProvinceLevel(StockRepAdaptReqDTO stockRepAdaptDTO) throws BusinessException {
		StockRepAdaptMainDTO adaptMainDTO = new StockRepAdaptMainDTO();

		List<StockRepAdaptRespDTO> tagAdaptDTOs = new ArrayList<StockRepAdaptRespDTO>();
		// 获取店铺覆盖省份列表
		List<StockRepAdaptRespDTO> stockRepAdapts = this.queryStockRepAdaptProvinceByShopId(stockRepAdaptDTO);

		if (stockRepAdapts != null && stockRepAdapts.size() > 0) {
			for (StockRepAdaptRespDTO adapt : stockRepAdapts) {
				// StockRepAdaptDTO repAdaptDTO = new StockRepAdaptDTO();
				// 判断当前省份是否到地市一级
				adapt.setShopId(stockRepAdaptDTO.getShopId());
				Boolean ifCity = this.isExistCityLevel(adapt);
				if (ifCity) {
					adapt.setIfCityLevel(true);
				} else {
					adapt.setIfCityLevel(false);
				}

				// 编辑仓库时，需要获取当前仓库是否在该省份有范围分布
				if (stockRepAdaptDTO.getRepCode() != null && stockRepAdaptDTO.getRepCode() != 0L) {
					StockRepAdapt stockRepAdapt = new StockRepAdapt();
					stockRepAdapt.setAdaptProvince(adapt.getAdaptProvince());
					stockRepAdapt.setRepCode(stockRepAdaptDTO.getRepCode());
					stockRepAdapt.setShopId(stockRepAdaptDTO.getShopId());
					Boolean ifExistsAdapt = isExistAdapt(stockRepAdapt);
					if (ifExistsAdapt) {
						adapt.setIfCurrentHas(true);
					} else {
						adapt.setIfCurrentHas(false);
					}

				}
				tagAdaptDTOs.add(adapt);
			}
		}

		adaptMainDTO.setStockRepAdaptDTOs(tagAdaptDTOs);
		return adaptMainDTO;
	}

	/**
	 * 
	 * TODO 判断仓库在该区域是否有覆盖（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#isExistAdapt(com.zengshi.ecp.goods.dao.model.StockRepAdapt)
	 */
	@Override
	public Boolean isExistAdapt(StockRepAdapt stockRepAdapt) throws BusinessException {
		StockRepAdaptCriteria stockRepAdaptCriteria = new StockRepAdaptCriteria();
		StockRepAdaptCriteria.Criteria criteria = stockRepAdaptCriteria.createCriteria();
		if (stockRepAdapt.getRepCode() != null && 0L != stockRepAdapt.getRepCode()) {
			criteria.andRepCodeEqualTo(stockRepAdapt.getRepCode());
		}
		criteria.andShopIdEqualTo(stockRepAdapt.getShopId());
		criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		if (stockRepAdapt.getAdaptCountry() != null && !"".equals(stockRepAdapt.getAdaptCountry())) {

			criteria.andAdaptCountryEqualTo(stockRepAdapt.getAdaptCountry());
		}
		if (stockRepAdapt.getAdaptProvince() != null && !"".equals(stockRepAdapt.getAdaptProvince())) {

			criteria.andAdaptProvinceEqualTo(stockRepAdapt.getAdaptProvince());
		}

		if (stockRepAdapt.getAdaptCity() != null && !"".equals(stockRepAdapt.getAdaptCity())) {

			criteria.andAdaptCityEqualTo(stockRepAdapt.getAdaptCity());
		}
		Long count = stockRepAdaptMapper.countByExample(stockRepAdaptCriteria);
		if (count == 0L) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 
	 * TODO 判断是否在某个省份下有其他的仓库覆盖（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#isExistAdaptOtherRep(com.zengshi.ecp.goods.dao.model.StockRepAdapt)
	 */
	@Override
	public Boolean isExistAdaptOtherRep(StockRepAdapt stockRepAdapt) throws BusinessException {
		StockRepAdaptCriteria stockRepAdaptCriteria = new StockRepAdaptCriteria();
		StockRepAdaptCriteria.Criteria criteria = stockRepAdaptCriteria.createCriteria();
		criteria.andRepCodeNotEqualTo(stockRepAdapt.getRepCode());
		criteria.andShopIdEqualTo(stockRepAdapt.getShopId());
		criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		criteria.andAdaptCityIsNotNull();
		if (stockRepAdapt.getAdaptCountry() != null && !"".equals(stockRepAdapt.getAdaptCountry())) {

			criteria.andAdaptCountryEqualTo(stockRepAdapt.getAdaptCountry());
		}
		if (stockRepAdapt.getAdaptProvince() != null && !"".equals(stockRepAdapt.getAdaptProvince())) {

			criteria.andAdaptProvinceEqualTo(stockRepAdapt.getAdaptProvince());
		}

		Long count = stockRepAdaptMapper.countByExample(stockRepAdaptCriteria);
		if (count == 0L) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 
	 * TODO 编辑仓库（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#editStockRep(com.zengshi.ecp.goods.dubbo.dto.stock.StockRepMainReqDTO)
	 */
	@Override
	public void editStockRep(StockRepMainReqDTO stockRepMainDto) throws BusinessException {
		StockRep stockRep = new StockRep();
		ObjectCopyUtil.copyObjValue(stockRepMainDto.getStockRepDTO(), stockRep, null, false);
		StockRep rep = repMapper.selectByPrimaryKey(stockRepMainDto.getStockRepDTO().getId());
		if (rep != null) {
			// 新增仓库历史记录
			addStockRepHis(rep);
		} else {

			throw new BusinessException(GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235001, new String[] { Long.toString(stockRep.getId()) });
		}
		// 编辑仓库记录

		StockRepCriteria stockRepCriteria = new StockRepCriteria();
		StockRepCriteria.Criteria repCrit = stockRepCriteria.createCriteria();
		repCrit.andShopIdEqualTo(stockRep.getShopId());
		repCrit.andIdEqualTo(stockRep.getId());
		repMapper.updateByExampleSelective(stockRep, stockRepCriteria);
		// 同步修改店铺、企业与仓库的索引表
		if (!rep.getRepName().equals(stockRepMainDto.getStockRepDTO().getRepName())) {
			StockShopRepIdx stockShopRepIdx = new StockShopRepIdx();
			stockShopRepIdx.setRepName(stockRepMainDto.getStockRepDTO().getRepName());
			StockShopRepIdxCriteria shopRepIdxExample = new StockShopRepIdxCriteria();
			StockShopRepIdxCriteria.Criteria criteria = shopRepIdxExample.createCriteria();
			criteria.andRepCodeEqualTo(rep.getId());
			criteria.andShopIdEqualTo(rep.getShopId());
			criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
			shopRepIdxMapper.updateByExampleSelective(stockShopRepIdx, shopRepIdxExample);

			StockCompanyRepIdx stockCompanyRepIdx = new StockCompanyRepIdx();
			stockCompanyRepIdx.setRepName(stockRepMainDto.getStockRepDTO().getRepName());
			StockCompanyRepIdxCriteria companyRepIdxExample = new StockCompanyRepIdxCriteria();
			StockCompanyRepIdxCriteria.Criteria criteriaCompany = companyRepIdxExample.createCriteria();
			criteriaCompany.andRepCodeEqualTo(rep.getId());
			criteriaCompany.andCompanyIdEqualTo(rep.getCompanyId());
			criteriaCompany.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
			companyRepIdxMapper.updateByExampleSelective(stockCompanyRepIdx, companyRepIdxExample);
		}
		// 编辑仓库适用范围
		editStockRepAdaptInfo(stockRepMainDto);
	}

	/**
	 * 
	 * TODO 删除仓库（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#deleteStockRep(com.zengshi.ecp.goods.dubbo.dto.stock.StockRepReqDTO)
	 */
	@Override
	public void deleteStockRep(StockRepReqDTO stockRepDTO) throws BusinessException {
		StockRep stockRep = new StockRep();
		stockRep.setStatus(GdsConstants.Commons.STATUS_INVALID);
		// 失效仓库
		StockRepCriteria stockRepCriteria = new StockRepCriteria();

		StockRepCriteria.Criteria repCrit = stockRepCriteria.createCriteria();
		repCrit.andIdEqualTo(stockRepDTO.getId());
		repMapper.updateByExampleSelective(stockRep, stockRepCriteria);
		StockRep rep = repMapper.selectByPrimaryKey(stockRepDTO.getId());
		// 新增仓库历史记录
		addStockRepHis(rep);
		// 失效仓库适用范围(店铺)
		if (stockRepDTO.getShopId() != null && stockRepDTO.getShopId() != 0L) {
			StockRepAdaptCriteria stockRepAdaptCriteria = new StockRepAdaptCriteria();
			StockRepAdaptCriteria.Criteria criteria = stockRepAdaptCriteria.createCriteria();
			criteria.andRepCodeEqualTo(stockRepDTO.getId());
			criteria.andShopIdEqualTo(stockRepDTO.getShopId());
			List<StockRepAdapt> stockRepAdapts = stockRepAdaptMapper.selectByExample(stockRepAdaptCriteria);
			if (stockRepAdapts != null) {
				for (StockRepAdapt stockRepAdapt : stockRepAdapts) {
					stockRepAdapt.setUpdateTime(new Timestamp(System.currentTimeMillis()));
					stockRepAdapt.setUpdateStaff(stockRepDTO.getStaffId());
					deleteStockRepAdapt(stockRepAdapt);
				}

			}
			// 失效仓库店铺索引表
			StockShopRepIdx record = new StockShopRepIdx();
			record.setStatus(GdsConstants.Commons.STATUS_INVALID);
			StockShopRepIdxCriteria shopRepIdxExample = new StockShopRepIdxCriteria();
			StockShopRepIdxCriteria.Criteria shopRepIdxCriteria = shopRepIdxExample.createCriteria();
			shopRepIdxCriteria.andShopIdEqualTo(stockRepDTO.getShopId());
			shopRepIdxCriteria.andRepCodeEqualTo(stockRepDTO.getId());
			shopRepIdxMapper.updateByExampleSelective(record, shopRepIdxExample);
		}
		// 失效仓库企业索引表
		StockCompanyRepIdx record = new StockCompanyRepIdx();
		record.setStatus(GdsConstants.Commons.STATUS_INVALID);
		StockCompanyRepIdxCriteria companyRepIdxExample = new StockCompanyRepIdxCriteria();
		StockCompanyRepIdxCriteria.Criteria CompanyRepIdxCriteria = companyRepIdxExample.createCriteria();
		CompanyRepIdxCriteria.andCompanyIdEqualTo(stockRepDTO.getCompanyId());
		CompanyRepIdxCriteria.andRepCodeEqualTo(stockRepDTO.getId());
		companyRepIdxMapper.updateByExampleSelective(record, companyRepIdxExample);

	}

	/**
	 * 
	 * TODO 新增仓库历史记录（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#addStockRepHis(com.zengshi.ecp.goods.dao.model.StockRep)
	 */
	@Override
	public void addStockRepHis(StockRep stockRep) throws BusinessException {
		StockRepHis stockRepHis = new StockRepHis();
		ObjectCopyUtil.copyObjValue(stockRep, stockRepHis, null, false);
		stockRepHis.setId(seqStockRepHis.nextValue());
		stockRepHis.setRepCode(stockRep.getId());
		stockRepHisMapper.insertSelective(stockRepHis);
	}

	/**
	 * 
	 * TODO 新增仓库适用范围历史记录（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#addStockRepAdaptHis(com.zengshi.ecp.goods.dao.model.StockRepAdapt)
	 */
	@Override
	public void addStockRepAdaptHis(StockRepAdapt stockRepAdapt) throws BusinessException {
		StockRepAdaptHis stockRepAdaptHis = new StockRepAdaptHis();
		ObjectCopyUtil.copyObjValue(stockRepAdapt, stockRepAdaptHis, null, false);
		stockRepAdaptHis.setId(seqStockRepAdaptHis.nextValue());
		stockRepAdaptHis.setAdaptId(stockRepAdapt.getId());
		stockRepAdaptHisMapper.insertSelective(stockRepAdaptHis);

	}

	/**
	 * 
	 * TODO 删除仓库适用范围（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#deleteStockRepAdapt(com.zengshi.ecp.goods.dao.model.StockRepAdapt)
	 */
	@Override
	public void deleteStockRepAdapt(StockRepAdapt stockRepAdapt) throws BusinessException {
		// 获取仓库信息

		StockRep stockRep = repMapper.selectByPrimaryKey(stockRepAdapt.getRepCode());
		// 新增仓库适用范围历史记录
		addStockRepAdaptHis(stockRepAdapt);
		// 失效仓库适用范围记录
		stockRepAdapt.setStatus(GdsConstants.Commons.STATUS_INVALID);
		StockRepAdaptCriteria stockRepAdaptCriteria = new StockRepAdaptCriteria();
		StockRepAdaptCriteria.Criteria adaptCrit = stockRepAdaptCriteria.createCriteria();
		adaptCrit.andShopIdEqualTo(stockRepAdapt.getShopId());
		adaptCrit.andIdEqualTo(stockRepAdapt.getId());
		stockRepAdaptMapper.updateByExampleSelective(stockRepAdapt, stockRepAdaptCriteria);
		StockInfoAdaptCriteria stockInfoAdaptCriteria = new StockInfoAdaptCriteria();
		StockInfoAdaptCriteria.Criteria criteria = stockInfoAdaptCriteria.createCriteria();
		criteria.andAdaptCountryEqualTo(stockRepAdapt.getAdaptCountry());
		criteria.andAdaptProvinceEqualTo(stockRepAdapt.getAdaptProvince());
		criteria.andShopIdEqualTo(stockRepAdapt.getShopId());
		criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		criteria.andRepCodeEqualTo(stockRepAdapt.getRepCode());
		if (stockRepAdapt.getAdaptCity() != null && !"".equals(stockRepAdapt.getAdaptCity())) {
			criteria.andAdaptCityEqualTo(stockRepAdapt.getAdaptCity());

		}
		List<StockInfoAdapt> stockInfoAdapts = stockInfoAdaptMapper.selectByExample(stockInfoAdaptCriteria);
		if (stockInfoAdapts != null && stockInfoAdapts.size() > 0) {
			// 失效仓库库存适用范围记录
			for (StockInfoAdapt stockInfoAdapt : stockInfoAdapts) {
				stockInfoAdapt.setStatus(GdsConstants.Commons.STATUS_INVALID);
				StockInfoAdaptCriteria infoAdaptCriteria = new StockInfoAdaptCriteria();
				StockInfoAdaptCriteria.Criteria adaptCriteria = infoAdaptCriteria.createCriteria();
				adaptCriteria.andShopIdEqualTo(stockInfoAdapt.getShopId());
				adaptCriteria.andIdEqualTo(stockInfoAdapt.getId());
				stockInfoAdaptMapper.updateByExampleSelective(stockInfoAdapt, infoAdaptCriteria);
				// 失效库存记录
				StockInfoReqDTO stockInfoDTO = new StockInfoReqDTO();
				stockInfoDTO.setId(stockInfoAdapt.getStockId());
				stockInfoDTO.setShopId(stockInfoAdapt.getShopId());
				stockInfoDTO.setCompanyId(stockRep.getCompanyId());
				this.deleteStockInfo(stockInfoDTO);

			}
		}

	}

	@Override
	public void deleteStockInfoBySkuInfo(StockInfoReqDTO stockInfoReqDTO) throws BusinessException {
		// 获取所有
		StockInfoAdaptCriteria stockInfoAdaptCriteria = new StockInfoAdaptCriteria();
		StockInfoAdaptCriteria.Criteria criteria = stockInfoAdaptCriteria.createCriteria();

		criteria.andSkuIdEqualTo(stockInfoReqDTO.getSkuId());
		criteria.andShopIdEqualTo(stockInfoReqDTO.getShopId());
		if(StringUtil.isNotBlank(stockInfoReqDTO.getExt1())){
			criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_OFFLINE);
		}else{
			criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		}
		List<StockInfoAdapt> stockInfoAdapts = stockInfoAdaptMapper.selectByExample(stockInfoAdaptCriteria);
		if (stockInfoAdapts != null) {
			// 遍历库存适用范围，失效适用范围和库存记录
			for (StockInfoAdapt stockInfoAdapt : stockInfoAdapts) {
				stockInfoAdapt.setStatus(GdsConstants.Commons.STATUS_INVALID);
				stockInfoAdaptMapper.updateByPrimaryKeySelective(stockInfoAdapt);
				stockInfoReqDTO.setId(stockInfoAdapt.getStockId());
				deleteStockInfo(stockInfoReqDTO);
			}

		}
	}

	/**
	 * 
	 * TODO 删除库存记录（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#deleteStockInfo(com.zengshi.ecp.goods.dao.model.StockInfo)
	 */
	@Override
	public void deleteStockInfo(StockInfoReqDTO stockInfoDTO) throws BusinessException {
		StockInfo stockInfo = stockInfoMapper.selectByPrimaryKey(stockInfoDTO.getId());
		// 新增库存历史记录
		StockInfoHis stockInfoHis = new StockInfoHis();
		ObjectCopyUtil.copyObjValue(stockInfo, stockInfoHis, null, false);
		stockInfoHis.setId(seqStockInfoHis.nextValue());
		stockInfoHis.setStockId(stockInfoDTO.getId());
		stockInfoHisMapper.insertSelective(stockInfoHis);
		// 更新库存信息
		stockInfo.setStatus(GdsConstants.Commons.STATUS_INVALID);
		// stockInfoMapper.updateByPrimaryKey(stockInfo);
		stockInfoMapper.updateByPrimaryKeySelective(stockInfo);

		// 更新店铺库存索引表
		StockShopInfoIdx stockShopInfoIdx = new StockShopInfoIdx();
		StockShopInfoIdxCriteria stockShopInfoIdxCriteria = new StockShopInfoIdxCriteria();
		StockShopInfoIdxCriteria.Criteria criteria = stockShopInfoIdxCriteria.createCriteria();
		criteria.andShopIdEqualTo(stockInfoDTO.getShopId());
		criteria.andStockIdEqualTo(stockInfoDTO.getId());
		stockShopInfoIdx.setStatus(GdsConstants.Commons.STATUS_INVALID);
		stockShopInfoIdxMapper.updateByExampleSelective(stockShopInfoIdx, stockShopInfoIdxCriteria);
		// 更新仓库库存索引表
		StockRepInfoIdx stockRepInfoIdx = new StockRepInfoIdx();
		StockRepInfoIdxCriteria stockRepInfoIdxCriteria = new StockRepInfoIdxCriteria();
		StockRepInfoIdxCriteria.Criteria repInfoCriteria = stockRepInfoIdxCriteria.createCriteria();
		repInfoCriteria.andRepCodeEqualTo(stockInfo.getRepCode());
		repInfoCriteria.andStockIdEqualTo(stockInfoDTO.getId());
		stockRepInfoIdx.setStatus(GdsConstants.Commons.STATUS_INVALID);
		stockRepInfoIdxMapper.updateByExampleSelective(stockRepInfoIdx, stockRepInfoIdxCriteria);
		// 更新企业库存索引表
		StockCompanyInfoIdx stockCompanyInfoIdx = new StockCompanyInfoIdx();
		StockCompanyInfoIdxCriteria companyInfoIdxCriteria = new StockCompanyInfoIdxCriteria();
		StockCompanyInfoIdxCriteria.Criteria companyCrit = companyInfoIdxCriteria.createCriteria();
		companyCrit.andStockIdEqualTo(stockInfoDTO.getId());
		companyCrit.andCompanyIdEqualTo(stockInfoDTO.getCompanyId());
		stockCompanyInfoIdx.setStatus(GdsConstants.Commons.STATUS_INVALID);
		stockCompanyInfoIdxMapper.updateByExampleSelective(stockCompanyInfoIdx, companyInfoIdxCriteria);
	}

	@Override
	public Long countSkus(StockInfoReqDTO stockInfoDto) throws BusinessException {
		StockShopInfoIdxCriteria stockShopInfoIdxCriteria = new StockShopInfoIdxCriteria();
		StockShopInfoIdxCriteria.Criteria criteria = stockShopInfoIdxCriteria.createCriteria();
		criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		if (stockInfoDto.getStockStatus() != null) {
			Long lackCount = Long.parseLong(SysCfgUtil.fetchSysCfg(GdsConstants.GdsStock.STOCK_LACK_THRESHOLD).getParaValue());
			Long hardGetCount = Long.parseLong(SysCfgUtil.fetchSysCfg(GdsConstants.GdsStock.STOCK_HARDTOGET_THRESHOLD).getParaValue());

			List<GdsTypeRespDTO> gdsTypeList=gdsTypeSV.queryGdsTypeList();
			List<Long> noStockGdsTypeIdList=new ArrayList<Long>();
			if(CollectionUtils.isNotEmpty(gdsTypeList)){
				for(GdsTypeRespDTO gdsTypeRespDTO:gdsTypeList){
					if(StringUtils.equals(GdsConstants.Commons.STATUS_INVALID,gdsTypeRespDTO.getIfNeedstock())){
						noStockGdsTypeIdList.add(gdsTypeRespDTO.getId());
					}
				}
			}

			if (GdsConstants.GdsStock.STOCK_STATUS_LACK.equals(stockInfoDto.getStockStatus())) {
				criteria.andAvailCountLessThanOrEqualTo(lackCount);
				//需要排除商品类型为无库存的商品
				if(CollectionUtils.isNotEmpty(noStockGdsTypeIdList)){
					criteria.andTypeIdNotIn(noStockGdsTypeIdList);
				}
			} else if (GdsConstants.GdsStock.STOCK_STATUS_HARDTOGET.equals(stockInfoDto.getStockStatus())) {
				criteria.andAvailCountLessThanOrEqualTo(hardGetCount);
				//需要排除商品类型为无库存的商品
				if(CollectionUtils.isNotEmpty(noStockGdsTypeIdList)){
					criteria.andTypeIdNotIn(noStockGdsTypeIdList);
				}
			} else if (GdsConstants.GdsStock.STOCK_STATUS_ENOUGH.equals(stockInfoDto.getStockStatus())){
				criteria.andAvailCountGreaterThan(hardGetCount);
				if(CollectionUtils.isNotEmpty(noStockGdsTypeIdList)){
					//需要包含商品类型为无库存的商品
					StockShopInfoIdxCriteria.Criteria criteria2 = stockShopInfoIdxCriteria.createCriteria();
					criteria2.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
					criteria2.andTypeIdIn(noStockGdsTypeIdList);
					stockShopInfoIdxCriteria.or(criteria2);
				}
			}

		}
		stockShopInfoIdxCriteria.setLimitClauseStart(stockInfoDto.getStartRowIndex());
		stockShopInfoIdxCriteria.setLimitClauseCount(stockInfoDto.getPageSize());
		return stockShopInfoIdxMapper.countByExample(stockShopInfoIdxCriteria);
	}

	/**
	 * 
	 * TODO 条件获取库存列表（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#queryStockPageInfo(com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoReqDTO)
	 */
	@Override
	public PageResponseDTO<StockInfoPageRespDTO> queryStockPageInfo(StockInfoReqDTO stockInfoDto) throws BusinessException {
		StockShopInfoIdxCriteria stockShopInfoIdxCriteria = new StockShopInfoIdxCriteria();
		StockShopInfoIdxCriteria.Criteria criteria = stockShopInfoIdxCriteria.createCriteria();

		criteria.andShopIdEqualTo(stockInfoDto.getShopId());
		criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		if (stockInfoDto.getTypeId() != null && 0L != stockInfoDto.getTypeId()) {
			criteria.andTypeIdEqualTo(stockInfoDto.getTypeId());

		}
		/*if (stockInfoDto.getCatgCode() != null && !"".equals(stockInfoDto.getCatgCode())) {
			GdsCategoryReqDTO reqDTO = new GdsCategoryReqDTO();
			reqDTO.setCatgCode(stockInfoDto.getCatgCode());
			reqDTO.setIsContainSub(true);
			GdsCategoryRespDTO categoryRespDTO = categorySV.queryGdsCategoryByPK(reqDTO);
			List<String> catgStrs = new ArrayList<String>();
			this.getAllSubCatgCode(catgStrs, categoryRespDTO);

			criteria.andCatgCodeIn(catgStrs);

		}*/
		// 商品名称。
		if(StringUtils.isNotBlank(stockInfoDto.getGdsName())){
		    criteria.andGdsNameLike("%" + stockInfoDto.getGdsName() + "%");
		}
		// 分类编码
		if(StringUtils.isNotBlank(stockInfoDto.getCatgCode())){
		    criteria.andCatgPathLike("%<" + stockInfoDto.getCatgCode() + ">%" );
		}
		
		// 产品编号
		if(StringUtils.isNotBlank(stockInfoDto.getIsbn())){
            criteria.andProductNoLike("%" + stockInfoDto.getIsbn() + "%" );
        }

		/*if (stockInfoDto.getGdsName() != null && !"".equals(stockInfoDto.getGdsName())) {

			GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
			gdsInfoReqDTO.setGdsName(stockInfoDto.getGdsName());
			gdsInfoReqDTO.setShopId(stockInfoDto.getShopId());
			List<GdsInfoRespDTO> gdsInfoRespDTOs = gdsInfoQuerySV.queryGdsInfoList(gdsInfoReqDTO);
			if (gdsInfoRespDTOs != null && gdsInfoRespDTOs.size() > 0) {
				List<Long> gdsIds = new ArrayList<Long>();
				for (GdsInfoRespDTO gdsInfoRespDTO : gdsInfoRespDTOs) {

					gdsIds.add(gdsInfoRespDTO.getId());

				}

				criteria.andGdsIdIn(gdsIds);
			} else {

				PageResponseDTO<StockInfoPageRespDTO> pageResponseDTO = new PageResponseDTO<StockInfoPageRespDTO>();
				pageResponseDTO.setPageNo(0);
				pageResponseDTO.setPageSize(10);
				pageResponseDTO.setResult(new ArrayList<StockInfoPageRespDTO>());
				pageResponseDTO.setPageCount(0);
				return pageResponseDTO;
			}

		}*/

		/*if (stockInfoDto.getIsbn() != null && !"".equals(stockInfoDto.getIsbn())) {

			GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
			gdsInfoReqDTO.setIsbn(stockInfoDto.getIsbn());
			gdsInfoReqDTO.setShopId(stockInfoDto.getShopId());
			List<GdsInfoRespDTO> gdsInfoRespDTOs = gdsInfoQuerySV.queryGdsInfoList(gdsInfoReqDTO);
			if (gdsInfoRespDTOs != null && gdsInfoRespDTOs.size() > 0) {
				List<Long> gdsIds = new ArrayList<Long>();
				for (GdsInfoRespDTO gdsInfoRespDTO : gdsInfoRespDTOs) {

					gdsIds.add(gdsInfoRespDTO.getId());

				}

				criteria.andGdsIdIn(gdsIds);
			} else {

				PageResponseDTO<StockInfoPageRespDTO> pageResponseDTO = new PageResponseDTO<StockInfoPageRespDTO>();
				pageResponseDTO.setPageNo(0);
				pageResponseDTO.setPageSize(10);
				pageResponseDTO.setResult(new ArrayList<StockInfoPageRespDTO>());
				pageResponseDTO.setPageCount(0);
				return pageResponseDTO;
			}

		}*/

		if (stockInfoDto.getSkuId() != null && 0L != stockInfoDto.getSkuId()) {
			criteria.andSkuIdEqualTo(stockInfoDto.getSkuId());

		}
		
		if (stockInfoDto.getGdsId() != null && 0L != stockInfoDto.getGdsId()) {
			criteria.andGdsIdEqualTo(stockInfoDto.getGdsId());

		}
		if (stockInfoDto.getRepType() != null && !"".equals(stockInfoDto.getRepType())) {
			criteria.andRepTypeEqualTo(stockInfoDto.getRepType());
		}

		if (stockInfoDto.getStockStatus() != null) {
			Long lackCount = Long.parseLong(SysCfgUtil.fetchSysCfg(GdsConstants.GdsStock.STOCK_LACK_THRESHOLD).getParaValue());
			Long hardGetCount = Long.parseLong(SysCfgUtil.fetchSysCfg(GdsConstants.GdsStock.STOCK_HARDTOGET_THRESHOLD).getParaValue());

			if (GdsConstants.GdsStock.STOCK_STATUS_LACK.equals(stockInfoDto.getStockStatus())) {
				criteria.andAvailCountLessThanOrEqualTo(lackCount);

			} else if (GdsConstants.GdsStock.STOCK_STATUS_HARDTOGET.equals(stockInfoDto.getStockStatus())) {

				criteria.andAvailCountLessThanOrEqualTo(hardGetCount);
				criteria.andAvailCountGreaterThan(lackCount);
			} else if (GdsConstants.GdsStock.STOCK_STATUS_ENOUGH.equals(stockInfoDto.getStockStatus()))
				criteria.andAvailCountGreaterThan(hardGetCount);
		}
		stockShopInfoIdxCriteria.setLimitClauseStart(stockInfoDto.getStartRowIndex());
		stockShopInfoIdxCriteria.setLimitClauseCount(stockInfoDto.getPageSize());
		return super.queryByPagination(stockInfoDto, stockShopInfoIdxCriteria, true, new PaginationCallback<StockShopInfoIdx, StockInfoPageRespDTO>() {

			@Override
			public List<StockShopInfoIdx> queryDB(BaseCriteria arg0) {
				return stockShopInfoIdxMapper.selectByExample((StockShopInfoIdxCriteria) arg0);
			}

			@Override
			public long queryTotal(BaseCriteria arg0) {
				return stockShopInfoIdxMapper.countByExample((StockShopInfoIdxCriteria) arg0);
			}

			@Override
			public StockInfoPageRespDTO warpReturnObject(StockShopInfoIdx stockShopInfoIdx) {
				StockInfoPageRespDTO pageDTO = new StockInfoPageRespDTO();
				// 根据索引表获取记录
				StockInfo stockInfo = stockInfoMapper.selectByPrimaryKey(stockShopInfoIdx.getStockId());
				if (stockInfo != null) {
					pageDTO=copyStockInfo2PageResp(stockInfo);
					//ObjectCopyUtil.copyObjValue(stockInfo, pageDTO, null, false);
				} else {
					throw new BusinessException(GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235002, new String[] { Long.toString(stockShopInfoIdx.getStockId()) });
				}
				GdsSkuInfoReqDTO skuInfoReqDTO = new GdsSkuInfoReqDTO();
				skuInfoReqDTO.setId(stockInfo.getSkuId());
				skuInfoReqDTO.setGdsId(stockInfo.getGdsId());
				GdsSkuInfoRespDTO gdsSkuInfoRespDTO = skuInfoQuerySV.querySkuInfoByOptions(skuInfoReqDTO, new SkuQueryOption[] { SkuQueryOption.BASIC });
				pageDTO.setGdsName(gdsSkuInfoRespDTO.getGdsName());
				pageDTO.setGdsId(gdsSkuInfoRespDTO.getGdsId());
				pageDTO.setSkuId(gdsSkuInfoRespDTO.getId());
				pageDTO.setShopId(gdsSkuInfoRespDTO.getShopId());
				pageDTO.setPropStr(gdsSkuInfoRespDTO.getSkuProps());
				pageDTO.setSkuStatus(gdsSkuInfoRespDTO.getGdsStatusName());
				pageDTO.setFacStock(stockInfo.getFacStock());
				// 增加ISBN号显示.
				pageDTO.setProductNo(gdsSkuInfoRespDTO.getIsbn());
				if (stockInfo.getTypeId() != null && stockInfo.getTypeId() != 0L) {
					pageDTO.setTypeName(gdsSkuInfoRespDTO.getGdsTypeName());
				} else {
					pageDTO.setTypeName("");
				}
				String repType = BaseParamUtil.fetchParamValue("GDS_REP_TYPE", stockInfo.getRepType());
				pageDTO.setRepType(repType);
				return pageDTO;
			}
		});
	}

	/**
	 * 
	 * TODO 更新库存（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#updateStockInfo(com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoReqDTO)
	 */
	@Override
	public Long updateStockInfo(StockInfoReqDTO stockInfoDTO) throws BusinessException {
		if (stockInfoDTO.getTurnType() != null && !"".equals(stockInfoDTO.getTurnType())) {
			StockInfoCriteria stockInfoCriteria = new StockInfoCriteria();
			StockInfoCriteria.Criteria criteria = stockInfoCriteria.createCriteria();
			criteria.andIdEqualTo(stockInfoDTO.getId());

			Long count = stockInfoDTO.getTurnCount();
			StockInfo stockInfo = stockInfoMapper.selectByPrimaryKey(stockInfoDTO.getId());
			if (stockInfo == null) {

				throw new BusinessException(GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235002, new String[] { Long.toString(stockInfoDTO.getId()) });
			}

			// 新增操作日志
			StockOptRecord stockOptRecord = new StockOptRecord();
			stockOptRecord.setCount(count);
			stockOptRecord.setId(seqStockOptRecord.nextValue());
			stockOptRecord.setOptRepCode(stockInfoDTO.getRepCode());
			stockOptRecord.setOptType(stockInfoDTO.getTurnType());
			stockOptRecord.setStockId(stockInfo.getId());
			// 如果是确认收货，则存订单号，退货存退货流水号，发货存发货批次号
			if (GdsConstants.GdsStock.STOCK_INFO_TURN_AFFIRM.equals(stockInfoDTO.getTurnType()) || GdsConstants.GdsStock.STOCK_INFO_TURN_SEND.equals(stockInfoDTO.getTurnType()) || GdsConstants.GdsStock.STOCK_INFO_TURN_RETURN.equals(stockInfoDTO.getTurnType())) {
				stockOptRecord.setOrdOptNo(stockInfoDTO.getOrdOptNo());
			}
			stockOptRecord.setSkuId(stockInfo.getSkuId());
			stockOptRecord.setCreateStaff(stockInfoDTO.getStaffId());
			// 如果是确认收货、退货、发货则判断是否已经存在对应的记录。存在返回optId 为null，以便区分是否继续
			if (GdsConstants.GdsStock.STOCK_INFO_TURN_AFFIRM.equals(stockOptRecord.getOptType()) || GdsConstants.GdsStock.STOCK_INFO_TURN_SEND.equals(stockOptRecord.getOptType()) || GdsConstants.GdsStock.STOCK_INFO_TURN_RETURN.equals(stockOptRecord.getOptType())) {
				stockOptRecord.setCreateTime(new Timestamp(System.currentTimeMillis()));
				// 判断库存操作日志是否插入成功
				int ifInsert = optRecordExtraMapper.insertStockOptRecordNotExists(stockOptRecord);
				// 不成功则返回null，并终止库存操作
				if (ifInsert == 0) {

					return null;
				}
			} else {

				this.addStockOptRecord(stockOptRecord);
			}
			// 新增库存历史记录
			this.addStockInfoHis(stockInfo);

			int key = 0;
			if (stockInfoDTO.getTurnType().equals(GdsConstants.GdsStock.STOCK_INFO_TURN_UP) || stockInfoDTO.getTurnType().equals(GdsConstants.GdsStock.STOCK_INFO_TURN_AFFIRM)) {

				key = stockInfoExtraMapper.updateStockInfoTurnUp(stockInfoDTO.getTurnCount(), stockInfoDTO.getStaffId(), stockInfoCriteria);
				if (key == 1) {
					sendGdsIndexMsg(stockInfo);
				}
			} else if (stockInfoDTO.getTurnType().equals(GdsConstants.GdsStock.STOCK_INFO_TURN_DOWN)) {
				criteria.andAvailCountGreaterThanOrEqualTo(count);
				key = stockInfoExtraMapper.updateStockInfoTurnDown(stockInfoDTO.getTurnCount(), stockInfoDTO.getStaffId(), stockInfoCriteria);
				if (key == 1) {
					sendGdsIndexMsg(stockInfo);
				} else {

					throw new BusinessException(GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235018);

				}

			} else if (stockInfoDTO.getTurnType().equals(GdsConstants.GdsStock.STOCK_INFO_TURN_SEND)) {
				criteria.andPreOccupyCountGreaterThanOrEqualTo(count);
				key = stockInfoExtraMapper.updateStockInfoTurnSend(stockInfoDTO.getTurnCount(), stockInfoDTO.getStaffId(), stockInfoCriteria);

			} else if (stockInfoDTO.getTurnType().equals(GdsConstants.GdsStock.STOCK_INFO_TURN_CANCEL_PRE)) {
				criteria.andPreOccupyCountGreaterThanOrEqualTo(stockInfoDTO.getTurnCount());

				key = stockInfoExtraMapper.updateStockInfoTurnCancelPre(stockInfoDTO.getTurnCount(), stockInfoDTO.getStaffId(), stockInfoCriteria);
				if (key == 1) {
					sendGdsIndexMsg(stockInfo);

				}

			} else if (stockInfoDTO.getTurnType().equals(GdsConstants.GdsStock.STOCK_INFO_TURN_ADD_PRE)) {
				GdsInfoReqDTO gdsInfo = new GdsInfoReqDTO();
				gdsInfo.setId(stockInfoDTO.getGdsId());
				GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQuerySV.queryGdsInfoByOption(gdsInfo, new GdsQueryOption[] { GdsQueryOption.BASIC });
				Long lackCount = 0L;
				// 判断商品是否是积分商品
				if (GdsUtils.isEqualsInvalidOrNULL(gdsInfoRespDTO.getIfScoreGds())) {

					lackCount = Long.parseLong(SysCfgUtil.fetchSysCfg(GdsConstants.GdsStock.STOCK_LACK_THRESHOLD).getParaValue());
				} else {

					lackCount = Long.parseLong(SysCfgUtil.fetchSysCfg(GdsConstants.GdsStock.STOCK_SCORE_LACK_THRESHOLD).getParaValue());
				}
				criteria.andAvailCountGreaterThanOrEqualTo(count + lackCount);
				key = stockInfoExtraMapper.updateStockInfoTurnAddPre(count, stockInfoDTO.getStaffId(), stockInfoCriteria);
				if (key == 1) {
					sendGdsIndexMsg(stockInfo);
				}
			} else if (stockInfoDTO.getTurnType().equals(GdsConstants.GdsStock.STOCK_INFO_TURN_RETURN)) {

			}

			if (key == 1 && !stockInfoDTO.getTurnType().equals(GdsConstants.GdsStock.STOCK_INFO_TURN_AFFIRM) && stockInfoDTO.getShopId() != null) {
			    
			    GdsInfoReqDTO gdsQuery = new GdsInfoReqDTO();
			    gdsQuery.setId(stockInfo.getGdsId());
			    GdsQueryOption[] gdsQueryOpt = new GdsQueryOption[]{GdsQueryOption.BASIC};
			    gdsQuery.setGdsQueryOptions(gdsQueryOpt);
			    GdsInfoRespDTO gdsInfo = gdsInfoQuerySV.queryGdsInfo(gdsQuery);
			    
			    if(null != gdsInfo){
			        StockInfo s = new StockInfo();
			        s.setId(stockInfo.getId());
			        s.setGdsName(gdsInfo.getGdsName());
			        s.setProductNo(gdsInfo.getIsbn());
			        s.setCatgPath(gdsInfo.getPlatCatgs());
			        
			        stockInfoMapper.updateByPrimaryKeySelective(s);
			    }
			    
			    
				// 同步修改店铺库存索引表的可用库存数据
				StockInfo stockInfoAft = stockInfoMapper.selectByPrimaryKey(stockInfoDTO.getId());
				StockShopInfoIdx shopInfoIdx = new StockShopInfoIdx();
				shopInfoIdx.setAvailCount(stockInfoAft.getAvailCount());
				if(null != gdsInfo){
				    shopInfoIdx.setGdsName(gdsInfo.getGdsName());
				    shopInfoIdx.setProductNo(gdsInfo.getIsbn());
				    shopInfoIdx.setCatgPath(gdsInfo.getPlatCatgs());
				}
				StockShopInfoIdxCriteria example = new StockShopInfoIdxCriteria();
				StockShopInfoIdxCriteria.Criteria criter = example.createCriteria();
				criter.andShopIdEqualTo(stockInfoAft.getShopId());
				criter.andStockIdEqualTo(stockInfoAft.getId());
				stockShopInfoIdxMapper.updateByExampleSelective(shopInfoIdx, example);
			}
			
			
			updateZeroStockInfo(stockInfoDTO.getId(), stockInfo);
			

			if (key == 0) {

				throw new BusinessException(GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235028);
			}
			
			return stockOptRecord.getId();
		} else {

			throw new BusinessException(GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235003);
		}
        
	}

    /** 
     * updateZeroStockInfo:更新零库存信息. <br/> 
     * 
     * @author liyong7
     * @param stockId
     * @param stockInfo 
     * @since JDK 1.6 
     */ 
    private void updateZeroStockInfo(Long stockId, StockInfo stockInfo) {
        // 查询更新操作后库存信息.
        StockInfo currentStock = stockInfoMapper.selectByPrimaryKey(stockId);
        // 更新后可用库存
        Long currentAvailCount = currentStock.getAvailCount();
        // 更新前可用库存.
        Long hisAvailCount = stockInfo.getAvailCount();
        Long zero = 0L;
        // 当前可用库存是否为零库存
        if(zero.equals(currentAvailCount)){
        	boolean updateZeroFlag = false;
            // 历史库存为非零可用库存,则设置持续零库存标识为1,并设置零库存起始时间为系统当前时间.
            if(!zero.equals(hisAvailCount)){
            	updateZeroFlag = true;
            }else{
            	if(GdsConstants.Commons.STATUS_INVALID.equals(stockInfo.getZeroStockFlag())){
            		updateZeroFlag = true;
            	}
            }
            if(updateZeroFlag){
            	currentStock.setZeroStockFlag(GdsConstants.Commons.STATUS_VALID);
                currentStock.setZeroStockStarttime(DateUtil.getSysDate());
                stockInfoMapper.updateByPrimaryKey(currentStock);
            }
        }else{
        	// 当前库存不为零，历史库存为零，则需要重置零库存标识为0，持续零库存起始时间为空。
            if(zero.equals(hisAvailCount)){
                currentStock.setZeroStockFlag(GdsConstants.Commons.STATUS_INVALID);
                currentStock.setZeroStockStarttime(null);
                stockInfoMapper.updateByPrimaryKey(currentStock);
            }
        }
        
        StockShopInfoIdxCriteria example = new StockShopInfoIdxCriteria();
        StockShopInfoIdxCriteria.Criteria criter = example.createCriteria();
        criter.andShopIdEqualTo(currentStock.getShopId());
        criter.andStockIdEqualTo(currentStock.getId());
        List<StockShopInfoIdx> idxs = stockShopInfoIdxMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(idxs)){
            StockShopInfoIdx idx = idxs.get(0);
            idx.setZeroStockFlag(currentStock.getZeroStockFlag());
            idx.setFacStock(currentStock.getFacStock());
            idx.setZeroStockStarttime(currentStock.getZeroStockStarttime());
            stockShopInfoIdxMapper.updateByExample(idx, example);
        }
    }

	private void sendGdsIndexMsg(StockInfo stockInfo) {
		/*try {
			GdsInfoReqDTO reqDTO = new GdsInfoReqDTO();
			reqDTO.setId(stockInfo.getGdsId());
			GdsInfoRespDTO gdsInfo = gdsInfoQuerySV.queryGdsInfoByOption(reqDTO);
			Long catlogId = gdsInfo.getCatlogId();
			GdsUtils.sendGdsIndexMsg(null, "T_GDS_INFO", MODULE, stockInfo.getGdsId(), catlogId);
		} catch (Exception e) {
			LogUtil.error(MODULE, "send goods DB deltaMessage failed! please check kafka's status! ");
		}*/
		
		GdsInfoReqDTO reqDTO = new GdsInfoReqDTO();
        reqDTO.setId(stockInfo.getGdsId());
        GdsInfoRespDTO gdsInfo = gdsInfoQuerySV.queryGdsInfoByOption(reqDTO);
		GdsInfoMessageDTO messageDTO = new GdsInfoMessageDTO();
        messageDTO.setGdsId(gdsInfo.getId());
        messageDTO.setShopId(gdsInfo.getShopId());
        messageDTO.setCatlogId(gdsInfo.getCatlogId());
        messageDTO.setSkuIds(gdsInfo.getSkuIds());
        messageDTO.setSendPromMsg(false);
        messageDTO.setGdsStatus(gdsInfo.getGdsStatus());
        ThreadPoolExecutorUtil.commitTask(new GdsInfoMessageTask(messageDTO));
		
	}

	/**
	 * 
	 * TODO 新增库存（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#addStockInfo(com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoReqDTO)
	 */
	@Override
	public StockInfoReqDTO addStockInfo(StockInfoReqDTO stockInfoDTO) throws BusinessException {
		// 新增库存信息
		StockInfo stockInfo = new StockInfo();
		ObjectCopyUtil.copyObjValue(stockInfoDTO, stockInfo, null, false);
		stockInfo.setCreateStaff(stockInfoDTO.getStaffId());
		stockInfo.setUpdateStaff(stockInfoDTO.getStaffId());
		stockInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
		stockInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		stockInfo.setAvailCount(stockInfoDTO.getTurnCount());
		stockInfo.setRealCount(stockInfoDTO.getTurnCount());
		stockInfo.setPreOccupyCount(0L);
		stockInfo.setSendCount(0L);
		
		if(null == stockInfo.getAvailCount()){
		    stockInfo.setFacStock(-1L);
		}
		
		if(stockInfo.getAvailCount().equals(0L)){
		    stockInfo.setZeroStockFlag(GdsConstants.Commons.STATUS_VALID);
		    stockInfo.setZeroStockStarttime(DateUtil.getSysDate());
		}
		
		
		if(StringUtils.isNotBlank(stockInfoDTO.getExt1())){
		    stockInfo.setStatus(GdsConstants.Commons.STATUS_OFFLINE);
		}else{
		    stockInfo.setStatus(GdsConstants.Commons.STATUS_VALID); 
		}
		stockInfo.setId(seqStockInfo.nextValue());
		stockInfoMapper.insertSelective(stockInfo);
		stockInfoDTO.setId(stockInfo.getId());
		// 新增库存操作记录
		StockOptRecord stockOptRecord = new StockOptRecord();
		stockOptRecord.setCount(stockInfoDTO.getTurnCount());
		stockOptRecord.setOptRepCode(stockInfoDTO.getRepCode());
		if (stockInfoDTO.getShopId() != null) {
			stockOptRecord.setOptType(GdsConstants.GdsStock.STOCK_INFO_TURN_NEW);
		} else {
			stockOptRecord.setOptType(GdsConstants.GdsStock.STOCK_INFO_TURN_AFFIRM);
		}
		stockOptRecord.setSkuId(stockInfoDTO.getSkuId());
		stockOptRecord.setCreateStaff(stockInfoDTO.getStaffId());
		stockOptRecord.setCreateTime(new Timestamp(System.currentTimeMillis()));
		stockOptRecord.setId(seqStockOptRecord.nextValue());
		stockOptRecord.setStockId(stockInfo.getId());
		addStockOptRecord(stockOptRecord);
		// 新增企业索引表
		StockCompanyInfoIdx companyInfoIdx = new StockCompanyInfoIdx();
		companyInfoIdx.setCompanyId(stockInfoDTO.getCompanyId());
		companyInfoIdx.setCatgCode(stockInfoDTO.getCatgCode());
		companyInfoIdx.setGdsId(stockInfoDTO.getGdsId());
		companyInfoIdx.setRepType(stockInfoDTO.getRepType());
		companyInfoIdx.setSkuId(stockInfoDTO.getSkuId());
		companyInfoIdx.setStockId(stockInfo.getId());
		if(StringUtils.isNotBlank(stockInfoDTO.getExt1())){
			companyInfoIdx.setStatus(GdsConstants.Commons.STATUS_OFFLINE);
		}else{
			companyInfoIdx.setStatus(GdsConstants.Commons.STATUS_VALID);
		}
		companyInfoIdx.setTypeId(stockInfoDTO.getTypeId());
		companyInfoIdx.setStockType(stockInfoDTO.getStockType());
		stockCompanyInfoIdxMapper.insertSelective(companyInfoIdx);
		// 新增仓库索引表
		StockRepInfoIdx stockRepInfoIdx = new StockRepInfoIdx();
		stockRepInfoIdx.setRepCode(stockInfoDTO.getRepCode());
		stockRepInfoIdx.setCatgCode(stockInfoDTO.getCatgCode());
		stockRepInfoIdx.setGdsId(stockInfoDTO.getGdsId());
		stockRepInfoIdx.setSkuId(stockInfoDTO.getSkuId());
		stockRepInfoIdx.setStockId(stockInfo.getId());
		if(StringUtils.isNotBlank(stockInfoDTO.getExt1())){
			stockRepInfoIdx.setStatus(GdsConstants.Commons.STATUS_OFFLINE);
		}else{
			stockRepInfoIdx.setStatus(GdsConstants.Commons.STATUS_VALID);
		}
		stockRepInfoIdx.setTypeId(stockInfoDTO.getTypeId());
		stockRepInfoIdxMapper.insertSelective(stockRepInfoIdx);

		if (!stockInfoDTO.getRepType().equals(GdsConstants.GdsStock.STOCK_REP_TYPE_BUYER)) {
			// 新增店铺索引表
			StockShopInfoIdx stockShopInfoIdx = new StockShopInfoIdx();
			stockShopInfoIdx.setShopId(stockInfoDTO.getShopId());
			stockShopInfoIdx.setCatgCode(stockInfoDTO.getCatgCode());
			stockShopInfoIdx.setGdsId(stockInfoDTO.getGdsId());
			stockShopInfoIdx.setSkuId(stockInfoDTO.getSkuId());
			stockShopInfoIdx.setStockId(stockInfo.getId());
			if(StringUtils.isNotBlank(stockInfoDTO.getExt1())){
				stockShopInfoIdx.setStatus(GdsConstants.Commons.STATUS_OFFLINE);
			}else{
				stockShopInfoIdx.setStatus(GdsConstants.Commons.STATUS_VALID);
			}
			
			stockShopInfoIdx.setTypeId(stockInfoDTO.getTypeId());
			stockShopInfoIdx.setRepType(stockInfoDTO.getRepType());
			stockShopInfoIdx.setStockType(stockInfoDTO.getStockType());
			stockShopInfoIdx.setAvailCount(stockInfoDTO.getTurnCount());
			stockShopInfoIdx.setGdsName(stockInfoDTO.getGdsName());
			stockShopInfoIdx.setProductNo(stockInfoDTO.getProductNo());
			stockShopInfoIdx.setCatgPath(stockInfoDTO.getCatgPath());
			stockShopInfoIdx.setFacStock(stockInfo.getFacStock());
			stockShopInfoIdx.setZeroStockFlag(stockInfo.getZeroStockFlag());
			stockShopInfoIdx.setZeroStockStarttime(stockInfo.getZeroStockStarttime());
			stockShopInfoIdxMapper.insertSelective(stockShopInfoIdx);

		}
		stockInfoDTO.setOptId(stockOptRecord.getId());
		return stockInfoDTO;
	}

	/**
	 * 
	 * TODO 新增库存历史记录（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#addStockInfoHis(com.zengshi.ecp.goods.dao.model.StockInfo)
	 */
	@Override
	public void addStockInfoHis(StockInfo stockInfo) throws BusinessException {
		StockInfoHis stockInfoHis = new StockInfoHis();
		ObjectCopyUtil.copyObjValue(stockInfo, stockInfoHis, null, false);
		stockInfoHis.setId(seqStockInfoHis.nextValue());
		stockInfoHis.setStockId(stockInfo.getId());
		stockInfoHisMapper.insertSelective(stockInfoHis);
	}

	/**
	 * 
	 * TODO 新增库存操作记录（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#addStockOptRecord(com.zengshi.ecp.goods.dao.model.StockOptRecord)
	 */
	@Override
	public void addStockOptRecord(StockOptRecord stockOptRecord) throws BusinessException {
		stockOptRecord.setCreateTime(new Timestamp(System.currentTimeMillis()));
		stockOptRecordMapper.insertSelective(stockOptRecord);

	}

	/**
	 * 
	 * addBatchStockPreOccupy:(新增库存预占). <br/>
	 * 
	 * @author zjh
	 * @param preOccupyBatchDTO
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	@Override
	public void addBatchStockPreOccupy(ROrdCartCommRequest cartCommRequest) throws BusinessException {
		for (ROrdCartItemCommRequest ordCartItemCommRequest : cartCommRequest.getOrdCartItemCommList()) {
			if (cartCommRequest.getStaffId() == null) {
				throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100, new String[] { "staffId" });
			}
			if (cartCommRequest.getShopId() == null) {
				throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100, new String[] { "shopId" });
			}
			ordCartItemCommRequest.setShopId(cartCommRequest.getShopId());
			ordCartItemCommRequest.setStaffId(cartCommRequest.getStaffId());
			this.addStockPreOccupy(ordCartItemCommRequest);

		}
	}

	/**
	 * 
	 * TODO 新增库存预占记录（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#addStockPreOccupy(com.zengshi.ecp.goods.dubbo.dto.StockPreOccupyReqDTO)
	 */
	@Override
	public void addStockPreOccupy(ROrdCartItemCommRequest cartItemCommRequest) throws BusinessException {

		if (cartItemCommRequest == null) {
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200097);
		}

		if (cartItemCommRequest.getShopId() == null) {
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100, new String[] { "shopId" });
		}
		if (cartItemCommRequest.getSkuId() == null) {
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100, new String[] { "skuId" });
		}
		if (cartItemCommRequest.getOrderAmount() == null) {
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100, new String[] { "count" });
		}
		if (cartItemCommRequest.getOrderId() == null) {
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100, new String[] { "orderId" });
		}
		if (cartItemCommRequest.getOrderSubId() == null) {
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100, new String[] { "subOrder" });
		}
		if (cartItemCommRequest.getRepCode() == null) {
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100, new String[] { "repCode" });
		}
		if (cartItemCommRequest.getStaffId() == null) {
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100, new String[] { "staffId" });
		}

		if (cartItemCommRequest.getStockId() == null) {
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100, new String[] { "stockId" });
		}

		// 新增预占
		StockPreOccupy stockPreOccupy = new StockPreOccupy();
		stockPreOccupy.setCount(cartItemCommRequest.getOrderAmount());
		stockPreOccupy.setOrderId(cartItemCommRequest.getOrderId());
		stockPreOccupy.setSubOrder(cartItemCommRequest.getOrderSubId());
		stockPreOccupy.setCreateStaff(cartItemCommRequest.getStaffId());
		stockPreOccupy.setCreateTime(new Timestamp(System.currentTimeMillis()));
		stockPreOccupy.setRepCode(cartItemCommRequest.getRepCode());
		stockPreOccupy.setShopId(cartItemCommRequest.getShopId());
		stockPreOccupy.setSkuId(cartItemCommRequest.getSkuId());
		stockPreOccupy.setStatus(GdsConstants.Commons.STATUS_VALID);
		stockPreOccupy.setStockId(cartItemCommRequest.getStockId());
		stockPreOccupy.setUpdateStaff(cartItemCommRequest.getStaffId());
		stockPreOccupy.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		stockPreOccupy.setId(seqStockPreOccupy.nextValue());
		int key = stockPreOccupyExtraMapper.insertPreOccupyNotExists(stockPreOccupy);
		// 如果已经存在，则不执行下面的步骤
		if (key == 0) {
			LogUtil.info(GdsStockSVImpl.class.getName(), "-----------------订单：" + cartItemCommRequest.getOrderSubId() + "已经新增预占！---------------------");
			return;

		}
		// 更新库存信息
		StockInfoReqDTO infoDTO = new StockInfoReqDTO();
		infoDTO.setTurnType(GdsConstants.GdsStock.STOCK_INFO_TURN_ADD_PRE);
		infoDTO.setId(cartItemCommRequest.getStockId());
		infoDTO.setTurnCount(cartItemCommRequest.getOrderAmount());
		infoDTO.setStaffId(cartItemCommRequest.getStaffId());
		infoDTO.setRepCode(cartItemCommRequest.getRepCode());
		infoDTO.setGdsId(cartItemCommRequest.getGdsId());

		updateStockInfo(infoDTO);
	}

	/**
	 * 
	 * TODO 取消库存预占记录（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#deleteStockPreOccupy(com.zengshi.ecp.goods.dubbo.dto.DelPreOccupyReqDTO)
	 */
	@Override
	public void deleteStockPreOccupy(ROrdCartCommRequest cartCommRequest) throws BusinessException {
		paramNullCheck(cartCommRequest, new String[] { "cartCommRequest" });
		paramNullCheck(cartCommRequest.getOrdCartItemCommList(), new String[] { "cartCommRequest.ordCartItemCommList" });
		paramNullCheck(cartCommRequest.getStaffId(), new String[] { "cartCommRequest.staffId" });
		// 遍历，以子订单级取消
		for (ROrdCartItemCommRequest cartItemCommRequest : cartCommRequest.getOrdCartItemCommList()) {
			cartItemCommRequest.setStaffId(cartCommRequest.getStaffId());
			deleteSubStockPreOccupy(cartItemCommRequest);
		}

	}

	/**
	 * 
	 * TODO 取消库存预占记录 明细级别.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#deleteSubStockPreOccupy(com.zengshi.ecp.goods.dubbo.dto.DelPreOccupyReqDTO)
	 */
	@Override
	public void deleteSubStockPreOccupy(ROrdCartItemCommRequest cartItemCommRequest) throws BusinessException {
		StockPreOccupy stockPreOccupy = new StockPreOccupy();
		stockPreOccupy.setStatus(GdsConstants.Commons.STATUS_INVALID);
		StockPreOccupyCriteria example = new StockPreOccupyCriteria();
		StockPreOccupyCriteria.Criteria criteria = example.createCriteria();
		criteria.andStockIdEqualTo(cartItemCommRequest.getStockId());
		criteria.andSubOrderEqualTo(cartItemCommRequest.getOrderSubId());
		criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		int key = stockPreOccupyMapper.updateByExampleSelective(stockPreOccupy, example);
		// 如果预占记录已经失效，则跳过，说明订单已经被取消
		if (key == 0) {
			return;
		}
		// 更新库存信息
		StockInfoReqDTO stockInfoDTO = new StockInfoReqDTO();
		stockInfoDTO.setId(cartItemCommRequest.getStockId());
		stockInfoDTO.setTurnCount(cartItemCommRequest.getOrderAmount());
		stockInfoDTO.setTurnType(GdsConstants.GdsStock.STOCK_INFO_TURN_CANCEL_PRE);
		stockInfoDTO.setStaffId(cartItemCommRequest.getStaffId());
		stockInfoDTO.setRepCode(cartItemCommRequest.getRepCode());
		stockInfoDTO.setGdsId(cartItemCommRequest.getGdsId());
		this.updateStockInfo(stockInfoDTO);
	}

	/**
	 * 
	 * TODO 子订单发货（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#updateDeliverySkuStcokForSub(com.zengshi.ecp.goods.dubbo.dto.DeliverySkuStcokReqDTO)
	 */
	@Override
	public void updateDeliverySkuStcokForSub(DeliverySkuStcokReqDTO deliverySkuStcokDTO) throws BusinessException {

		StockInfo stockInfo = stockInfoMapper.selectByPrimaryKey(deliverySkuStcokDTO.getStockId());
		if (stockInfo == null) {

			throw new BusinessException(GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235002, new String[] { Long.toString(deliverySkuStcokDTO.getStockId()) });
		}
		StockPreOccupyCriteria stockPreOccupyExample = new StockPreOccupyCriteria();
		StockPreOccupyCriteria.Criteria criteria = stockPreOccupyExample.createCriteria();
		criteria.andSubOrderEqualTo(deliverySkuStcokDTO.getSubOrder());
		criteria.andOrderIdEqualTo(deliverySkuStcokDTO.getOrderId());
		criteria.andStockIdEqualTo(deliverySkuStcokDTO.getStockId());

		List<StockPreOccupy> stockPreOccupies = stockPreOccupyMapper.selectByExample(stockPreOccupyExample);
		if (stockPreOccupies != null && stockPreOccupies.size() > 0) {
			StockPreOccupy stockPreOccupy = stockPreOccupies.get(0);
			// 如果已经发货则跳过
			if (stockPreOccupy.getStatus().equals(GdsConstants.Commons.STATUS_INVALID)) {
				return;
			}
			// 更新库存表
			StockInfoReqDTO infoDTO = new StockInfoReqDTO();
			infoDTO.setTurnCount(deliverySkuStcokDTO.getDeliveryCount());
			infoDTO.setId(stockPreOccupy.getStockId());
			infoDTO.setTurnType(GdsConstants.GdsStock.STOCK_INFO_TURN_SEND);
			infoDTO.setStaffId(deliverySkuStcokDTO.getStaffId());
			infoDTO.setRepCode(stockPreOccupy.getRepCode());
			infoDTO.setSkuId(stockPreOccupy.getSkuId());
			infoDTO.setOrdOptNo(deliverySkuStcokDTO.getDeliverySno());
			// infoDTO.setGdsId(stockPreOccupy.getGdsId());
			Long optId = this.updateStockInfo(infoDTO);
			if (optId == null) {
				return;
			}

			// 如果是全部发货
			if (deliverySkuStcokDTO.getIsDelivAll()) {
				// 失效库存预占

				stockPreOccupy.setStatus(GdsConstants.Commons.STATUS_INVALID);
				stockPreOccupy.setUpdateTime(new Timestamp(System.currentTimeMillis()));
				stockPreOccupy.setUpdateStaff(deliverySkuStcokDTO.getStaffId());
				StockPreOccupyCriteria stockPreOccupyCriteria = new StockPreOccupyCriteria();
				StockPreOccupyCriteria.Criteria crit = stockPreOccupyCriteria.createCriteria();
				crit.andIdEqualTo(stockPreOccupy.getId());
				crit.andStockIdEqualTo(stockPreOccupy.getStockId());
				stockPreOccupyMapper.updateByExampleSelective(stockPreOccupy, stockPreOccupyCriteria);
			} else {
				// 如果是部分发货
				StockPreOccupy preOccupy = new StockPreOccupy();
				preOccupy.setUpdateStaff(deliverySkuStcokDTO.getStaffId());
				preOccupy.setUpdateTime(new Timestamp(System.currentTimeMillis()));
				// 设置预占减少数
				preOccupy.setCount(deliverySkuStcokDTO.getDeliveryCount());
				StockPreOccupyCriteria preOccupyExample = new StockPreOccupyCriteria();
				StockPreOccupyCriteria.Criteria stockPreOccupyCriteria = preOccupyExample.createCriteria();
				stockPreOccupyCriteria.andIdEqualTo(stockPreOccupy.getId());
				stockPreOccupyCriteria.andStockIdEqualTo(stockPreOccupy.getStockId());
				stockPreOccupyCriteria.andCountGreaterThanOrEqualTo(deliverySkuStcokDTO.getDeliveryCount());
				stockPreOccupyExtraMapper.updatePreOccupyBySend(preOccupy, preOccupyExample);

			}

			// 判断是否有串号操作
			if (deliverySkuStcokDTO.getIsSerial()) {
				List<String> serialNos = deliverySkuStcokDTO.getSerialNoList();
				if (serialNos != null && serialNos.size() > 0) {
					for (String serialNo : serialNos) {
						StockSkuEntity stockSkuEntity = new StockSkuEntity();
						stockSkuEntity.setSkuId(deliverySkuStcokDTO.getSkuId());
						stockSkuEntity.setStockId(deliverySkuStcokDTO.getStockId());
						stockSkuEntity.setRepCode(stockPreOccupy.getRepCode());
						stockSkuEntity.setCodeType(GdsConstants.GdsStock.STOCK_CODE_TYPE_SELLER);
						stockSkuEntity.setSerialNo(serialNo);
						stockSkuEntity.setStatus(GdsConstants.GdsStock.STOCK_SKU_ENTITY_STATUS_SEND);
						stockSkuEntity.setCreateStaff(deliverySkuStcokDTO.getStaffId());
						stockSkuEntity.setUpdateStaff(deliverySkuStcokDTO.getStaffId());
						stockSkuEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
						stockSkuEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
						stockSkuEntity.setModifyTime(new Timestamp(System.currentTimeMillis()));
						stockSkuEntity.setId(seqStockSkuEntity.nextValue());
						stockSkuEntityMapper.insertSelective(stockSkuEntity);
						StockOutDetail stockOutDetail = new StockOutDetail();
						stockOutDetail.setEntityId(stockSkuEntity.getId());
						stockOutDetail.setGdsId(stockInfo.getGdsId());
						stockOutDetail.setOptType(GdsConstants.GdsStock.STOCK_SKU_ENTITY_STATUS_SEND);
						stockOutDetail.setOrderId(deliverySkuStcokDTO.getOrderId());
						stockOutDetail.setSubOrder(deliverySkuStcokDTO.getSubOrder());
						stockOutDetail.setRepCode(stockInfo.getRepCode());
						stockOutDetail.setSerialNo(serialNo);
						stockOutDetail.setSkuId(stockInfo.getSkuId());
						stockOutDetail.setStockId(stockInfo.getId());
						stockOutDetail.setStockOptId(optId);
						stockOutDetail.setCreateTime(new Timestamp(System.currentTimeMillis()));
						stockOutDetail.setCreateStaff(deliverySkuStcokDTO.getStaffId());
						stockOutDetail.setId(seqStockOutDetail.nextValue());

						stockOutDetailMapper.insertSelective(stockOutDetail);
					}
				} else {

					throw new BusinessException(GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235004);
				}

			}

		} else {
			throw new BusinessException(GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235005, new String[] { deliverySkuStcokDTO.getSubOrder() });

		}

	}

	/**
	 * 
	 * TODO 确认收货（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#updateAffirmReceiptStock(com.zengshi.ecp.goods.dubbo.dto.AffirmStockDTO)
	 */
	@Override
	public void updateAffirmReceiptStockForSub(AffirmStockDTO affirmStockDTO) throws BusinessException {

		// 判断当前买家是否有该单品的库存信息
		StockCompanyInfoIdxCriteria stockCompanyInfoIdxExample = new StockCompanyInfoIdxCriteria();
		StockCompanyInfoIdxCriteria.Criteria stockCompanyInfoIdxCriteria = stockCompanyInfoIdxExample.createCriteria();
		stockCompanyInfoIdxCriteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		stockCompanyInfoIdxCriteria.andCompanyIdEqualTo(affirmStockDTO.getCompanyId());
		stockCompanyInfoIdxCriteria.andRepTypeEqualTo(GdsConstants.GdsStock.STOCK_REP_TYPE_BUYER);
		stockCompanyInfoIdxCriteria.andSkuIdEqualTo(affirmStockDTO.getSkuId());
		List<StockCompanyInfoIdx> infoIdxs = stockCompanyInfoIdxMapper.selectByExample(stockCompanyInfoIdxExample);
		StockRepReqDTO repReqDTO = new StockRepReqDTO();
		repReqDTO.setCompanyId(affirmStockDTO.getCompanyId());
		repReqDTO.setStaffId(affirmStockDTO.getStaffId());
		// 获取买家企业的仓库
		StockRepRespDTO repRespDTO = this.dealStockRepForIfMakeUp(repReqDTO);
		Long repCode = repRespDTO.getId();
		affirmStockDTO.setRepCode(repCode);

		// 如果没有，则新增
		if (infoIdxs == null || infoIdxs.size() == 0) {
			StockInfoReqDTO stockInfoDTO = new StockInfoReqDTO();

			stockInfoDTO.setIsOver(GdsConstants.Commons.STATUS_INVALID);
			stockInfoDTO.setGdsId(affirmStockDTO.getGdsId());
			stockInfoDTO.setIsUsewarning(GdsConstants.Commons.STATUS_INVALID);
			stockInfoDTO.setCatgCode(affirmStockDTO.getCatgCode());
			stockInfoDTO.setStaffId(affirmStockDTO.getStaffId());
			stockInfoDTO.setSkuId(affirmStockDTO.getSkuId());
			stockInfoDTO.setTypeId(affirmStockDTO.getTypeId());
			stockInfoDTO.setCompanyId(affirmStockDTO.getCompanyId());
			stockInfoDTO.setRepCode(repCode);
			stockInfoDTO.setRepType(GdsConstants.GdsStock.STOCK_REP_TYPE_BUYER);
			stockInfoDTO.setTypeId(affirmStockDTO.getTypeId());
			stockInfoDTO.setTurnCount(affirmStockDTO.getAffirmCount());
			stockInfoDTO.setGdsId(affirmStockDTO.getGdsId());
			StockInfoReqDTO infoDTO = addStockInfo(stockInfoDTO);
			affirmStockDTO.setOptId(infoDTO.getOptId());
			affirmStockDTO.setBuyStockId(infoDTO.getId());

			StockOptRecord record = new StockOptRecord();
			record.setId(infoDTO.getOptId());
			record.setOrdOptNo(affirmStockDTO.getSubOrderId());
			stockOptRecordMapper.updateByPrimaryKeySelective(record);

		} else {
			StockCompanyInfoIdx companyInfoIdx = infoIdxs.get(0);
			// 如果是已经存在企业库存，则判断是否已经更新加入

			StockInfoReqDTO stockInfoDTO = new StockInfoReqDTO();
			stockInfoDTO.setId(companyInfoIdx.getStockId());
			stockInfoDTO.setTurnType(GdsConstants.GdsStock.STOCK_INFO_TURN_AFFIRM);
			stockInfoDTO.setTurnCount(affirmStockDTO.getAffirmCount());
			stockInfoDTO.setRepCode(repCode);
			stockInfoDTO.setSkuId(affirmStockDTO.getSkuId());
			stockInfoDTO.setGdsId(affirmStockDTO.getGdsId());
			stockInfoDTO.setStaffId(affirmStockDTO.getStaffId());
			stockInfoDTO.setOrdOptNo(affirmStockDTO.getSubOrderId());
			Long optId = updateStockInfo(stockInfoDTO);
			if (optId == null) {
				return;

			}
			affirmStockDTO.setOptId(optId);
			affirmStockDTO.setBuyStockId(companyInfoIdx.getStockId());
		}

		// 处理串号
		GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQuerySV.queryGdsInfoById(affirmStockDTO.getGdsId());
		if (gdsInfoRespDTO.getIfEntityCode() != null && !"".equals(gdsInfoRespDTO.getIfEntityCode())) {
			if (gdsInfoRespDTO.getIfEntityCode().equals(GdsConstants.GdsInfo.IF_ENTITY_CODE_DELIVER)) {
				updateEntityWhenAffirm(affirmStockDTO);
			}
		}
	}

	/**
	 * 
	 * updateEntityWhenAffirm:(确认收货更新串号). <br/>
	 * 
	 * @author zjh
	 * @param affirmStockDTO
	 * @throws BusinessException
	 * @since JDK 1.6
	 */

	public void updateEntityWhenAffirm(AffirmStockDTO affirmStockDTO) throws BusinessException {
		StockOutDetailCriteria stockOutDetailExample = new StockOutDetailCriteria();
		StockOutDetailCriteria.Criteria stockOutDetailCriteria = stockOutDetailExample.createCriteria();
		stockOutDetailCriteria.andStockIdEqualTo(affirmStockDTO.getStockId());
		stockOutDetailCriteria.andOrderIdEqualTo(affirmStockDTO.getOrderId());
		stockOutDetailCriteria.andSubOrderEqualTo(affirmStockDTO.getSubOrderId());

		List<StockOutDetail> stockOutDetails = stockOutDetailMapper.selectByExample(stockOutDetailExample);
		if (stockOutDetails == null || stockOutDetails.size() == 0) {
			throw new BusinessException(GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235007, new String[] { affirmStockDTO.getSubOrderId() });

		} else {

			if (affirmStockDTO.getBuyStockId() != null && affirmStockDTO.getBuyStockId() != 0L) {
				for (StockOutDetail stockOutDetail : stockOutDetails) {
					// 买家入库实体
					StockSkuEntity stockSkuEntity = new StockSkuEntity();
					stockSkuEntity.setCodeType(GdsConstants.GdsStock.STOCK_CODE_TYPE_BUYER);
					stockSkuEntity.setRepCode(affirmStockDTO.getRepCode());
					stockSkuEntity.setSerialNo(stockOutDetail.getSerialNo());
					stockSkuEntity.setSkuId(stockOutDetail.getSkuId());
					stockSkuEntity.setStockId(affirmStockDTO.getBuyStockId());
					stockSkuEntity.setStatus(GdsConstants.GdsStock.STOCK_SKU_ENTITY_STATUS_RECEIVE);
					stockSkuEntity.setCreateStaff(affirmStockDTO.getStaffId());
					stockSkuEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
					stockSkuEntity.setUpdateStaff(affirmStockDTO.getStaffId());
					stockSkuEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
					stockSkuEntity.setModifyTime(new Timestamp(System.currentTimeMillis()));
					stockSkuEntity.setId(seqStockSkuEntity.nextValue());
					stockSkuEntityMapper.insertSelective(stockSkuEntity);

					// 买家新增入库明细
					StockInDetail stockInDetail = new StockInDetail();
					stockInDetail.setEntityId(stockSkuEntity.getId());
					stockInDetail.setGdsId(affirmStockDTO.getGdsId());
					stockInDetail.setOptType(GdsConstants.GdsStock.STOCK_INFO_TURN_AFFIRM);
					stockInDetail.setOrderId(affirmStockDTO.getOrderId());
					stockInDetail.setSubOrder(affirmStockDTO.getSubOrderId());
					stockInDetail.setStockOptId(affirmStockDTO.getOptId());
					stockInDetail.setSkuId(affirmStockDTO.getSkuId());
					stockInDetail.setStockId(affirmStockDTO.getBuyStockId());
					stockInDetail.setCreateStaff(affirmStockDTO.getStaffId());
					stockInDetail.setCreateTime(new Timestamp(System.currentTimeMillis()));
					stockInDetail.setId(seqStockInDetail.nextValue());
					stockInDetailMapper.insertSelective(stockInDetail);
				}
			}

		}

	}

	/**
	 * 
	 * TODO 商品录入新增库存（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#addStockInfoForInput(com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoReqDTO)
	 */
	@Override
	public void addStockInfoForInput(StockInfoReqDTO stockInfoDTO) throws BusinessException {
		BaseSysCfgRespDTO baseSysCfgRespDTO = SysCfgUtil.fetchSysCfg("IF_OPEN_REGIONAL_STOCK");
		if (baseSysCfgRespDTO.getParaValue().equals(GdsConstants.Commons.STATUS_VALID) && GdsConstants.GdsStock.STOCK_INFO_TYPE_SEPERATE.equals(stockInfoDTO.getRepType())) {
			if (stockInfoDTO.getStockType().equals(GdsConstants.GdsStock.STOCK_INFO_TYPE_SEPERATE)) {
				for (int i = 0; i < stockInfoDTO.getStockRepAdapts().size(); i++) {
					// 依次新增库存量适用范围及范围下的库存量
					StockRepAdaptReqDTO repAdaptDTO = stockInfoDTO.getStockRepAdapts().get(i);
					// 新增库存量数据
					stockInfoDTO.setTurnCount(repAdaptDTO.getCount());
					StockInfoReqDTO stock = addStockInfo(stockInfoDTO);
					// 新增库存适用范围记录

					StockInfoAdapt stockInfoAdapt = new StockInfoAdapt();
					stockInfoAdapt.setTypeId(stockInfoDTO.getTypeId());
					stockInfoAdapt.setCatgCode(stockInfoDTO.getCatgCode());
					stockInfoAdapt.setAdaptCity(repAdaptDTO.getAdaptCity());
					stockInfoAdapt.setAdaptProvince(repAdaptDTO.getAdaptProvince());

					stockInfoAdapt.setGdsId(stockInfoDTO.getGdsId());
					stockInfoAdapt.setRepCode(stockInfoDTO.getRepCode());
					stockInfoAdapt.setRepType(GdsConstants.GdsStock.STOCK_REP_TYPE_SEPERATE);
					stockInfoAdapt.setSkuId(stockInfoDTO.getSkuId());
					stockInfoAdapt.setShopId(stockInfoDTO.getShopId());
					if(StringUtil.isNotBlank(stockInfoDTO.getExt1())){
						stockInfoAdapt.setStatus(GdsConstants.Commons.STATUS_OFFLINE);
					}else{
						stockInfoAdapt.setStatus(GdsConstants.Commons.STATUS_VALID);
					}
					stockInfoAdapt.setStockType(stockInfoDTO.getStockType());
					stockInfoAdapt.setStockId(stock.getId());
					stockInfoAdapt.setId(seqStockInfoAdapt.nextValue());

					stockInfoAdaptMapper.insertSelective(stockInfoAdapt);
				}
			} else {

				// 新增库存量数据
				StockInfoReqDTO stock = addStockInfo(stockInfoDTO);

				// 新增库存适用范围记录
				for (StockRepAdaptReqDTO repAdapt : stockInfoDTO.getStockRepAdapts()) {
					StockInfoAdapt stockInfoAdapt = new StockInfoAdapt();
					stockInfoAdapt.setTypeId(stockInfoDTO.getTypeId());
					stockInfoAdapt.setCatgCode(stockInfoDTO.getCatgCode());
					stockInfoAdapt.setAdaptCity(repAdapt.getAdaptCity());
					stockInfoAdapt.setShopId(stockInfoDTO.getShopId());
					stockInfoAdapt.setAdaptCountry(repAdapt.getAdaptCountry());
					stockInfoAdapt.setAdaptProvince(repAdapt.getAdaptProvince());
					stockInfoAdapt.setGdsId(stockInfoDTO.getGdsId());
					stockInfoAdapt.setRepCode(stockInfoDTO.getRepCode());
					stockInfoAdapt.setRepType(GdsConstants.GdsStock.STOCK_REP_TYPE_SEPERATE);
					stockInfoAdapt.setSkuId(stockInfoDTO.getSkuId());
					if(StringUtil.isNotBlank(stockInfoDTO.getExt1())){
						stockInfoAdapt.setStatus(GdsConstants.Commons.STATUS_OFFLINE);
					}else{
						stockInfoAdapt.setStatus(GdsConstants.Commons.STATUS_VALID);
					}
					stockInfoAdapt.setStockType(stockInfoDTO.getStockType());
					stockInfoAdapt.setStockId(stock.getId());
					stockInfoAdapt.setId(seqStockInfoAdapt.nextValue());
					stockInfoAdaptMapper.insertSelective(stockInfoAdapt);
				}
			}

		} else {
			StockRepReqDTO repReqDTO = new StockRepReqDTO();
			repReqDTO.setShopId(stockInfoDTO.getShopId());
			repReqDTO.setCompanyId(stockInfoDTO.getCompanyId());
			repReqDTO.setStaffId(stockInfoDTO.getStaffId());
			StockRepRespDTO repRespDTO = this.dealStockRepForIfMakeUp(repReqDTO);
			stockInfoDTO.setRepCode(repRespDTO.getId());
			StockInfoReqDTO stock = this.addStockInfo(stockInfoDTO);

			StockInfoAdapt stockInfoAdapt = new StockInfoAdapt();
			stockInfoAdapt.setTypeId(stockInfoDTO.getTypeId());
			stockInfoAdapt.setCatgCode(stockInfoDTO.getCatgCode());
			stockInfoAdapt.setShopId(stockInfoDTO.getShopId());
			stockInfoAdapt.setGdsId(stockInfoDTO.getGdsId());
			stockInfoAdapt.setRepCode(stockInfoDTO.getRepCode());
			stockInfoAdapt.setRepType(GdsConstants.GdsStock.STOCK_REP_TYPE_PUBLIC);
			stockInfoAdapt.setSkuId(stockInfoDTO.getSkuId());
			if(StringUtil.isNotBlank(stockInfoDTO.getExt1())){
				stockInfoAdapt.setStatus(GdsConstants.Commons.STATUS_OFFLINE);
			}else{
				stockInfoAdapt.setStatus(GdsConstants.Commons.STATUS_VALID);
			}
			stockInfoAdapt.setStockType(stockInfoDTO.getStockType());
			stockInfoAdapt.setStockId(stock.getId());
			stockInfoAdapt.setId(seqStockInfoAdapt.nextValue());
			stockInfoAdaptMapper.insertSelective(stockInfoAdapt);
		}

	}

	/**
	 * 
	 * TODO 获取单品库存信息（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#queryStockInfoByGds(com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoForGdsReqDTO)
	 */
	@Override
	public StockInfoRespDTO queryStockInfoByGds(StockInfoForGdsReqDTO stockInfoForGdsDTO) throws BusinessException {

		GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQuerySV.queryGdsInfoById(stockInfoForGdsDTO.getGdsId());

		if (GdsConstants.Commons.STATUS_INVALID.equals(gdsInfoRespDTO.getIfDisperseStock())) {
			StockInfoAdaptCriteria stockInfoAdaptExample = new StockInfoAdaptCriteria();
			StockInfoAdaptCriteria.Criteria stockInfoAdaptCrit = stockInfoAdaptExample.createCriteria();
			stockInfoAdaptCrit.andShopIdEqualTo(stockInfoForGdsDTO.getShopId());
			stockInfoAdaptCrit.andSkuIdEqualTo(stockInfoForGdsDTO.getSkuId());
			stockInfoAdaptCrit.andRepTypeEqualTo(GdsConstants.GdsStock.STOCK_REP_TYPE_PUBLIC);
			stockInfoAdaptCrit.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
			List<StockInfoAdapt> stockInfoAdapts = stockInfoAdaptMapper.selectByExample(stockInfoAdaptExample);
			if (stockInfoAdapts == null || stockInfoAdapts.size() == 0) {

				StockInfoRespDTO stockInfoRespDTO = new StockInfoRespDTO();
				stockInfoRespDTO.setAvailCount(0L);
				return stockInfoRespDTO;
			} else {
				StockInfoAdapt stockInfoAdapt = stockInfoAdapts.get(0);
				Long stockId = stockInfoAdapt.getStockId();
				StockInfo stockInfo = stockInfoMapper.selectByPrimaryKey(stockId);
				if (stockInfo != null) {
					StockInfoRespDTO stockInfoDTO = copyStockInfo2Resp(stockInfo);
					// ObjectCopyUtil.copyObjValue(stockInfo, stockInfoDTO,
					// null, false);
					return stockInfoDTO;
				} else {
					StockInfoRespDTO stockInfoRespDTO = new StockInfoRespDTO();
					stockInfoRespDTO.setAvailCount(0L);
					return stockInfoRespDTO;
				}
			}
		} else if (GdsConstants.Commons.STATUS_VALID.equals(gdsInfoRespDTO.getIfDisperseStock())) {
			StockInfoAdaptCriteria stockInfoAdaptExample = new StockInfoAdaptCriteria();
			StockInfoAdaptCriteria.Criteria stockInfoAdaptCrit = stockInfoAdaptExample.createCriteria();
			stockInfoAdaptCrit.andShopIdEqualTo(stockInfoForGdsDTO.getShopId());
			stockInfoAdaptCrit.andSkuIdEqualTo(stockInfoForGdsDTO.getSkuId());
			stockInfoAdaptCrit.andRepTypeEqualTo(GdsConstants.GdsStock.STOCK_REP_TYPE_SEPERATE);
			stockInfoAdaptCrit.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
			stockInfoAdaptCrit.andAdaptCountryEqualTo(stockInfoForGdsDTO.getAdaptCountry());
			stockInfoAdaptCrit.andAdaptProvinceEqualTo(stockInfoForGdsDTO.getAdaptProvince());
			if (stockInfoForGdsDTO.getAdaptCity() != null && !"".equals(stockInfoForGdsDTO.getAdaptCity())) {

				stockInfoAdaptCrit.andAdaptCityEqualTo(stockInfoForGdsDTO.getAdaptCity());

			}

			List<StockInfoAdapt> stockInfoAdapts = null;
			stockInfoAdapts = stockInfoAdaptMapper.selectByExample(stockInfoAdaptExample);

			if (stockInfoAdapts == null || stockInfoAdapts.size() == 0) {
				// 如果查询没有到地市，则说明没有库存记录
				if (stockInfoForGdsDTO.getAdaptCity() == null || "".equals(stockInfoForGdsDTO.getAdaptCity())) {
					StockInfoRespDTO stockInfoRespDTO = new StockInfoRespDTO();
					stockInfoRespDTO.setAvailCount(0L);
					return stockInfoRespDTO;

				} else {
					// 如果条件到地市，则说明地市级没有库存，再查询省份级别的库存
					StockInfoAdaptCriteria stockInfoAdaptExampleCityNull = new StockInfoAdaptCriteria();
					StockInfoAdaptCriteria.Criteria stockInfoAdaptCritCityNull = stockInfoAdaptExampleCityNull.createCriteria();
					stockInfoAdaptCritCityNull.andShopIdEqualTo(stockInfoForGdsDTO.getShopId());
					stockInfoAdaptCritCityNull.andSkuIdEqualTo(stockInfoForGdsDTO.getSkuId());
					stockInfoAdaptCritCityNull.andRepTypeEqualTo(GdsConstants.GdsStock.STOCK_REP_TYPE_SEPERATE);
					stockInfoAdaptCritCityNull.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
					stockInfoAdaptCritCityNull.andAdaptCountryEqualTo(stockInfoForGdsDTO.getAdaptCountry());
					stockInfoAdaptCritCityNull.andAdaptProvinceEqualTo(stockInfoForGdsDTO.getAdaptProvince());
					stockInfoAdaptCritCityNull.andAdaptCityIsNull();
					stockInfoAdapts = stockInfoAdaptMapper.selectByExample(stockInfoAdaptExample);
					if (stockInfoForGdsDTO.getAdaptCity() == null || "".equals(stockInfoForGdsDTO.getAdaptCity())) {

						StockInfoRespDTO stockInfoRespDTO = new StockInfoRespDTO();
						stockInfoRespDTO.setAvailCount(0L);
						return stockInfoRespDTO;
					}
				}
			}
			StockInfoAdapt stockInfoAdapt = stockInfoAdapts.get(0);
			Long stockId = stockInfoAdapt.getStockId();
			StockInfo stockInfo = stockInfoMapper.selectByPrimaryKey(stockId);
			if (stockInfo != null) {
				StockInfoRespDTO stockInfoDTO = copyStockInfo2Resp(stockInfo);
				if (stockInfo.getAvailCount() <= Long.parseLong(SysCfgUtil.fetchSysCfg(GdsConstants.GdsStock.STOCK_LACK_THRESHOLD).getParaValue())) {
					stockInfoDTO.setStockStatus(GdsConstants.GdsStock.STOCK_STATUS_LACK);
				} else if (stockInfo.getAvailCount() <= Long.parseLong(SysCfgUtil.fetchSysCfg(GdsConstants.GdsStock.STOCK_HARDTOGET_THRESHOLD).getParaValue())) {
					stockInfoDTO.setStockStatus(GdsConstants.GdsStock.STOCK_STATUS_HARDTOGET);

				} else {
					stockInfoDTO.setStockStatus(GdsConstants.GdsStock.STOCK_STATUS_ENOUGH);

				}
				return stockInfoDTO;
			} else {
				StockInfoRespDTO stockInfoRespDTO = new StockInfoRespDTO();
				stockInfoRespDTO.setAvailCount(0L);
				return stockInfoRespDTO;
			}

		} else {

			throw new BusinessException(GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235008);
		}
		// }
	}

	/**
	 * 
	 * TODO 根据id获取仓库信息（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#queryStockRepInfoById(com.zengshi.ecp.goods.dubbo.dto.stock.StockRepReqDTO)
	 */
	@Override
	public StockRepRespDTO queryStockRepInfoById(StockRepReqDTO stockRepDTO) throws BusinessException {
		StockRep stockRep = repMapper.selectByPrimaryKey(stockRepDTO.getId());
		StockRepRespDTO repDTO = new StockRepRespDTO();
		if (stockRep == null) {
			throw new BusinessException(GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235001, new String[] { Long.toString(stockRepDTO.getId()) });
		} else {
			ObjectCopyUtil.copyObjValue(stockRep, repDTO, null, false);
			return repDTO;
		}

	}

	/**
	 * 
	 * TODO 商品录入时，库存录入列表初始（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#queryShopRepInfoForGdsInput(com.zengshi.ecp.goods.dubbo.dto.stock.StockRepReqDTO)
	 */
	public List<StockRepRespDTO> queryShopRepInfoForGdsInput(StockRepReqDTO stockRepDTO) throws BusinessException {
		List<StockRepRespDTO> stockRepDTOs = new ArrayList<StockRepRespDTO>();
		if (stockRepDTO.getIfRegionalStock()) {
			StockShopRepIdxCriteria stockShopRepIdxCriteria = new StockShopRepIdxCriteria();
			StockShopRepIdxCriteria.Criteria criteria = stockShopRepIdxCriteria.createCriteria();
			criteria.andShopIdEqualTo(stockRepDTO.getShopId());
			criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
			criteria.andRepTypeEqualTo(GdsConstants.GdsStock.STOCK_REP_TYPE_SEPERATE);
			List<StockShopRepIdx> stockShopRepIdxs = shopRepIdxMapper.selectByExample(stockShopRepIdxCriteria);
			if (stockShopRepIdxs != null && stockShopRepIdxs.size() != 0) {

				for (StockShopRepIdx stockShopRepIdx : stockShopRepIdxs) {
					Long repCode = stockShopRepIdx.getRepCode();
					StockRepAdaptCriteria stockRepAdaptExample = new StockRepAdaptCriteria();
					StockRepAdaptCriteria.Criteria stockRepAdaptCriteria = stockRepAdaptExample.createCriteria();
					stockRepAdaptCriteria.andRepCodeEqualTo(repCode);
					stockRepAdaptCriteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
					List<StockRepAdapt> stockRepAdapts = stockRepAdaptMapper.selectByExample(stockRepAdaptExample);
					if (stockRepAdapts == null || stockRepAdapts.size() == 0) {
						// 跳过没有仓库适用范围的仓库
						continue;
					} else {
						// 设置仓库信息
						StockRepRespDTO repDTO = new StockRepRespDTO();
						repDTO.setId(repCode);
						repDTO.setRepName(stockShopRepIdx.getRepName());
						repDTO.setRepType(stockShopRepIdx.getRepType());
						// 设置仓库适用范围属性
						List<StockRepAdaptReqDTO> stockRepAdaptDTOs = new ArrayList<StockRepAdaptReqDTO>();
						for (StockRepAdapt stockRepAdapt : stockRepAdapts) {
							StockRepAdaptReqDTO stockRepAdaptDTO = new StockRepAdaptReqDTO();
							ObjectCopyUtil.copyObjValue(stockRepAdapt, stockRepAdaptDTO, null, false);
							stockRepAdaptDTOs.add(stockRepAdaptDTO);
						}
						repDTO.setStockRepAdaptDTOs(stockRepAdaptDTOs);
						stockRepDTOs.add(repDTO);
					}
				}

			}
		} else {
			StockShopRepIdxCriteria stockShopRepIdxCriteria = new StockShopRepIdxCriteria();
			StockShopRepIdxCriteria.Criteria criteria = stockShopRepIdxCriteria.createCriteria();
			criteria.andShopIdEqualTo(stockRepDTO.getShopId());
			criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
			criteria.andRepTypeEqualTo(GdsConstants.GdsStock.STOCK_REP_TYPE_PUBLIC);
			List<StockShopRepIdx> stockShopRepIdxs = shopRepIdxMapper.selectByExample(stockShopRepIdxCriteria);
			if (stockShopRepIdxs != null && stockShopRepIdxs.size() != 0) {

				// 设置仓库信息
				StockShopRepIdx stockShopRepIdx = stockShopRepIdxs.get(0);
				StockRepRespDTO repDTO = new StockRepRespDTO();
				repDTO.setId(stockShopRepIdx.getRepCode());
				repDTO.setRepName(stockShopRepIdx.getRepName());
				stockRepDTOs.add(repDTO);
			}
		}
		return stockRepDTOs;

	}

	// @Override
	// public Long queryShopGdsStockInfoForAct(StockInfoReqDTO stockInfoDTO)
	// throws BusinessException {
	// StockShopInfoIdxCriteria shopInfoIdxExample = new
	// StockShopInfoIdxCriteria();
	//
	// StockShopInfoIdxCriteria.Criteria criteria =
	// shopInfoIdxExample.createCriteria();
	// criteria.andShopIdEqualTo(stockInfoDTO.getShopId());
	// criteria.andGdsIdEqualTo(stockInfoDTO.getGdsId());
	// if (stockInfoDTO.getSkuId() != null && stockInfoDTO.getSkuId() == 0L) {
	// criteria.andSkuIdEqualTo(stockInfoDTO.getSkuId());
	// }
	// criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
	// List<StockShopInfoIdx> stockShopInfoIdxs = stockShopInfoIdxMapper
	// .selectByExample(shopInfoIdxExample);
	// // 库存总数
	// Long count = 100L;
	// if (stockShopInfoIdxs != null) {
	// for (StockShopInfoIdx stockShopInfoIdx : stockShopInfoIdxs) {
	// Long stockId = stockShopInfoIdx.getStockId();
	// StockInfo stockInfo = stockInfoMapper.selectByPrimaryKey(stockId);
	// count = count + stockInfo.getAvailCount();
	//
	// }
	//
	// }
	// return count;
	// }

	/**
	 * 
	 * TODO 发货库存处理（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#updateDeliverySkuStcok(com.zengshi.ecp.goods.dubbo.dto.DeliverySkuStcokMainReqDTO)
	 */
	@Override
	public void updateDeliverySkuStcok(DeliverySkuStcokMainReqDTO deliverySkuStcokMainDTO) throws BusinessException {
		for (DeliverySkuStcokReqDTO deliverySkuStcokDTO : deliverySkuStcokMainDTO.getDeliverySkuStcokDTOs()) {

			deliverySkuStcokDTO.setStaffId(deliverySkuStcokMainDTO.getStaffId());
			this.updateDeliverySkuStcokForSub(deliverySkuStcokDTO);

		}

	}

	/**
	 * 
	 * TODO 确认收货（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#updateAffirmReceiptStock(com.zengshi.ecp.goods.dubbo.dto.AffirmStockMainDTO)
	 */
	@Override
	public void updateAffirmReceiptStock(AffirmStockMainDTO affirmStockMainDTO) throws BusinessException {
		for (AffirmStockDTO affirmStockDTO : affirmStockMainDTO.getAffirmStockDTOs()) {
			affirmStockDTO.setStaffId(affirmStockMainDTO.getStaffId());
			affirmStockDTO.setShopId(affirmStockMainDTO.getShopId());
			affirmStockDTO.setCompanyId(affirmStockMainDTO.getCompanyId());
			this.updateAffirmReceiptStockForSub(affirmStockDTO);
		}
	}

	/**
	 * 
	 * TODO 分仓时，获取商品库存信息（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#queryGdsStockInfos(com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoReqDTO)
	 */
	@Override
	public List<GdsStockRepRespDTO> queryGdsStockInfos(StockInfoReqDTO stockInfoDTO) throws BusinessException {
		List<GdsStockRepRespDTO> gdsStockRepDTOs = new ArrayList<GdsStockRepRespDTO>();
		StockRepReqDTO stockRepDTO = new StockRepReqDTO();
		stockRepDTO.setShopId(stockInfoDTO.getShopId());
		stockRepDTO.setIfRegionalStock(true);
		// 获取店铺所有分仓的仓库
		List<StockRepRespDTO> stockRepDTOs = this.queryShopRepInfoForGdsInput(stockRepDTO);
		for (StockRepRespDTO repDTO : stockRepDTOs) {
			GdsStockRepRespDTO gdsStockRepDTO = new GdsStockRepRespDTO();
			ObjectCopyUtil.copyObjValue(repDTO, gdsStockRepDTO, null, false);
			List<GdsStockInfoRespDTO> gdsStockInfoReqDTOs = new ArrayList<GdsStockInfoRespDTO>();
			// 获取仓库下所有区域下的库存信息
			for (StockRepAdaptReqDTO stockRepAdaptDTO : repDTO.getStockRepAdaptDTOs()) {
				StockInfoForGdsReqDTO stockInfoForGdsDTO = new StockInfoForGdsReqDTO();
				stockInfoForGdsDTO.setAdaptCity(stockRepAdaptDTO.getAdaptCity());
				stockInfoForGdsDTO.setAdaptCountry(stockRepAdaptDTO.getAdaptCountry());
				stockInfoForGdsDTO.setAdaptProvince(stockRepAdaptDTO.getAdaptProvince());
				stockInfoForGdsDTO.setShopId(stockInfoDTO.getShopId());
				stockInfoForGdsDTO.setSkuId(stockInfoDTO.getSkuId());
				stockInfoForGdsDTO.setGdsId(stockInfoDTO.getGdsId());
				// stockInfoForGdsDTO.setRepType(GdsConstants.GdsStock.STOCK_INFO_TYPE_SEPERATE);
				StockInfoRespDTO infoDTO = this.queryStockInfoByGds(stockInfoForGdsDTO);
				GdsStockInfoRespDTO gdsStockInfoReqDTO = new GdsStockInfoRespDTO();
				ObjectCopyUtil.copyObjValue(infoDTO, gdsStockInfoReqDTO, null, false);
				gdsStockInfoReqDTO.setAdaptCity(stockRepAdaptDTO.getAdaptCity());
				gdsStockInfoReqDTO.setAdaptProvince(stockRepAdaptDTO.getAdaptProvince());
				gdsStockInfoReqDTO.setAdaptCountry(stockRepAdaptDTO.getAdaptCountry());
				if (infoDTO.getId() == null || infoDTO.getId() == 0L) {
					gdsStockInfoReqDTO.setStockId(null);
				} else {
					gdsStockInfoReqDTO.setStockId(infoDTO.getId());
				}
				gdsStockInfoReqDTOs.add(gdsStockInfoReqDTO);
				// 设置仓库下该商品的库存模式
				gdsStockRepDTO.setStockType(infoDTO.getStockType());
			}
			gdsStockRepDTO.setStockInfoDTOs(gdsStockInfoReqDTOs);
			gdsStockRepDTOs.add(gdsStockRepDTO);
		}
		return gdsStockRepDTOs;

	}

	/**
	 * 
	 * TODO 订单提交失败，库存预占回退服务（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#deleteBatchStockPreOccupy(com.zengshi.ecp.goods.dubbo.dto.DelPreOccupyBatchReqDTO)
	 */
	@Override
	public void deleteBatchStockPreOccupy(ROrdCartsCommRequest cartsCommRequest) throws BusinessException {
		for (ROrdCartCommRequest cartCommRequest : cartsCommRequest.getOrdCartsCommList()) {

			cartCommRequest.setStaffId(cartsCommRequest.getStaffId());
			this.deleteStockPreOccupy(cartCommRequest);

		}

	}

	/**
	 * 
	 * TODO 订单提交新增预占（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV#addBatchOrderStockPreOccupy(com.zengshi.ecp.goods.dubbo.dto.StockPreOccupyBatchOrderReqDTO)
	 */
	@Override
	public void addBatchOrderStockPreOccupy(ROrdCartsCommRequest cartsCommRequest) throws BusinessException {
		for (ROrdCartCommRequest cartCommRequest : cartsCommRequest.getOrdCartsCommList()) {
			cartCommRequest.setStaffId(cartsCommRequest.getStaffId());
			this.addBatchStockPreOccupy(cartCommRequest);

		}

	}

	public StockRepRespDTO dealStockRepForIfMakeUp(StockRepReqDTO repReqDTO) throws BusinessException {
		StockRepRespDTO repRespDTO = new StockRepRespDTO();
		if (repReqDTO.getShopId() != null && repReqDTO.getShopId() != 0L) {
			StockRepAdaptCriteria adaptCriteria = new StockRepAdaptCriteria();
			StockRepAdaptCriteria.Criteria criteria = adaptCriteria.createCriteria();
			criteria.andAdaptCityIsNull();
			criteria.andAdaptProvinceIsNull();
			criteria.andAdaptCountryIsNull();
			criteria.andShopIdEqualTo(repReqDTO.getShopId());
			criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
			List<StockRepAdapt> adapts = stockRepAdaptMapper.selectByExample(adaptCriteria);
			if (adapts != null && adapts.size() > 0) {
				repRespDTO.setId(adapts.get(0).getRepCode());
				return repRespDTO;
			}
		} else {
			StockCompanyRepIdxCriteria repIdxCriteria = new StockCompanyRepIdxCriteria();
			StockCompanyRepIdxCriteria.Criteria criteria = repIdxCriteria.createCriteria();
			criteria.andCompanyIdEqualTo(repReqDTO.getCompanyId());
			criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
			criteria.andRepTypeEqualTo(GdsConstants.GdsStock.STOCK_REP_TYPE_BUYER);
			List<StockCompanyRepIdx> companyRepIdxs = companyRepIdxMapper.selectByExample(repIdxCriteria);

			if (companyRepIdxs != null && companyRepIdxs.size() > 0) {
				repRespDTO.setId(companyRepIdxs.get(0).getRepCode());
				return repRespDTO;
			}
		}

		StockRepMainReqDTO stockRepMainDto = new StockRepMainReqDTO();
		stockRepMainDto.setStaffId(repReqDTO.getStaffId());
		stockRepMainDto.setIfNew(true);
		StockRepReqDTO stockRepDTO = new StockRepReqDTO();
		stockRepDTO.setCodeType(GdsConstants.GdsStock.STOCK_CODE_TYPE_SELLER);
		stockRepDTO.setCompanyId(repReqDTO.getCompanyId());
		if (repReqDTO.getShopId() != null && repReqDTO.getShopId() != 0L) {
			stockRepDTO.setRemark("录库存补录");
			stockRepDTO.setRepName(repReqDTO.getShopId() + "的总仓");
			stockRepDTO.setRepType(GdsConstants.GdsStock.STOCK_REP_TYPE_PUBLIC);
			stockRepDTO.setShopId(repReqDTO.getShopId());
		} else {
			stockRepDTO.setRemark("确认收货补录");
			stockRepDTO.setRepType(GdsConstants.GdsStock.STOCK_REP_TYPE_BUYER);
		}

		stockRepMainDto.setStockRepDTO(stockRepDTO);
		StockRep rep = addStockRep(stockRepMainDto);
		repRespDTO.setId(rep.getId());

		return repRespDTO;

	}

	@Override
	public List<StockInfoRespDTO> queryStockInfosBySkuId(StockInfoForGdsReqDTO stockInfoForGdsDTO) throws BusinessException {

		List<StockInfoRespDTO> infoRespDTOs = new ArrayList<StockInfoRespDTO>();
		StockShopInfoIdxCriteria example = new StockShopInfoIdxCriteria();
		StockShopInfoIdxCriteria.Criteria criteria = example.createCriteria();
		criteria.andShopIdEqualTo(stockInfoForGdsDTO.getShopId());
		criteria.andSkuIdEqualTo(stockInfoForGdsDTO.getSkuId());
		criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		criteria.andRepTypeEqualTo(GdsConstants.GdsStock.STOCK_REP_TYPE_SEPERATE);
		List<StockShopInfoIdx> shopInfoIdxs = stockShopInfoIdxMapper.selectByExample(example);
		if (shopInfoIdxs != null && shopInfoIdxs.size() > 0) {
			for (StockShopInfoIdx shopInfoIdx : shopInfoIdxs) {
				StockInfo stockInfo = stockInfoMapper.selectByPrimaryKey(shopInfoIdx.getStockId());
				StockInfoRespDTO infoRespDTO = new StockInfoRespDTO();

				ObjectCopyUtil.copyObjValue(stockInfo, infoRespDTO, null, false);
				infoRespDTO.setId(stockInfo.getId());
				infoRespDTOs.add(infoRespDTO);
			}

		}
		return infoRespDTOs;
	}

	@Override
	public void updateStockInfoByFixedVal(StockInfoReqDTO stockInfoReqDTO) throws Exception {
		if (stockInfoReqDTO.getTurnCount() == null) {

			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100, new String[] { "turnCount" });

		}
		if (stockInfoReqDTO.getSkuId() == null) {

			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100, new String[] { "SkuId" });

		}
		if (stockInfoReqDTO.getShopId() == null) {

			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100, new String[] { "shopId" });

		}
		StockInfoAdaptCriteria stockInfoAdaptCriteria = new StockInfoAdaptCriteria();
		StockInfoAdaptCriteria.Criteria criteria = stockInfoAdaptCriteria.createCriteria();
		criteria.andSkuIdEqualTo(stockInfoReqDTO.getSkuId());
		criteria.andShopIdEqualTo(stockInfoReqDTO.getShopId());
		criteria.andRepTypeEqualTo(GdsConstants.GdsStock.STOCK_REP_TYPE_PUBLIC);
		criteria.andStockTypeEqualTo(GdsConstants.GdsStock.STOCK_INFO_TYPE_PUBLIC);
		List<StockInfoAdapt> stockInfoAdapts = stockInfoAdaptMapper.selectByExample(stockInfoAdaptCriteria);
		if (stockInfoAdapts != null && stockInfoAdapts.size() > 0) {
			Long stockId = stockInfoAdapts.get(0).getStockId();
			// 查询当前库存.
			StockInfo stockInfo = stockInfoMapper.selectByPrimaryKey(stockId);
			if (stockInfo == null) {

				throw new BusinessException(GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235002, new String[] { Long.toString(stockId) });
			}

			// 新增库存历史记录
			this.addStockInfoHis(stockInfo);
			// 新增操作日志
			StockOptRecord stockOptRecord = new StockOptRecord();
			stockOptRecord.setCount(stockInfoReqDTO.getTurnCount());
			stockOptRecord.setId(seqStockOptRecord.nextValue());
			stockOptRecord.setOptRepCode(stockInfoReqDTO.getRepCode());
			stockOptRecord.setOptType(GdsConstants.GdsStock.STOCK_INFO_TURN_FIXEDVAL);
			stockOptRecord.setStockId(stockInfo.getId());
			stockOptRecord.setSkuId(stockInfo.getSkuId());
			this.addStockOptRecord(stockOptRecord);
			StockInfoCriteria stockInfoCriteria = new StockInfoCriteria();
			StockInfoCriteria.Criteria criteriaStock = stockInfoCriteria.createCriteria();
			criteriaStock.andIdEqualTo(stockId);
			int key = 0;
			GdsInfoReqDTO reqDTO = new GdsInfoReqDTO();
			reqDTO.setId(stockInfo.getGdsId());
			GdsInfoRespDTO gdsInfo = gdsInfoQuerySV.queryGdsInfoByOption(reqDTO);
			stockInfo.setGdsName(gdsInfo.getGdsName());
			stockInfo.setProductNo(gdsInfo.getIsbn());
			stockInfo.setCatgPath(gdsInfo.getPlatCatgs());
			// 工厂库存. 如果为空则设置工厂库存为-1.
			if(null != stockInfoReqDTO.getFacStock()){
			   stockInfo.setFacStock(stockInfoReqDTO.getFacStock());
			}else{
			    stockInfo.setFacStock(-1L);
			}
			stockInfoMapper.updateByPrimaryKeySelective(stockInfo);
			key = stockInfoExtraMapper.updateStockInfoTurnByErp(stockInfoReqDTO.getTurnCount(), stockInfoCriteria);
			
			
			updateZeroStockInfo(stockId, stockInfo);
			
			if (key == 1) {
				try {
					Long catlogId = gdsInfo.getCatlogId();
					GdsUtils.sendGdsIndexMsg(null, "T_GDS_INFO", MODULE, stockInfo.getGdsId(), catlogId);
					
				} catch (BusinessException e) {
					throw e;
				} catch (Exception e) {
					LogUtil.error(MODULE, "send goods DB deltaMessage failed! please check kafka's status! ");
				}
			} else {

				throw new BusinessException(GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235028);
			}
		} else {

			throw new BusinessException(GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235028);
		}

	}

	public void getAllSubCatgCode(List<String> list, GdsCategoryRespDTO categoryRespDTO) {
		if (categoryRespDTO != null) {
			list.add(categoryRespDTO.getCatgCode());
			if (categoryRespDTO.getChildren() != null && categoryRespDTO.getChildren().size() > 0) {
				for (GdsCategoryRespDTO categoryRespDTODto : categoryRespDTO.getChildren()) {
					this.getAllSubCatgCode(list, categoryRespDTODto);

				}
			}

		}
	}

	@Override
	public void syncStockCatgInfoByGdsTurn(StockInfoReqDTO infoReqDTO) throws Exception {
		// 更新店铺库存索引表
		StockShopInfoIdxCriteria exampleShopIdxUp = new StockShopInfoIdxCriteria();
		StockShopInfoIdxCriteria.Criteria criteriaShopIdxUp = exampleShopIdxUp.createCriteria();
		criteriaShopIdxUp.andShopIdEqualTo(infoReqDTO.getShopId());
		criteriaShopIdxUp.andGdsIdEqualTo(infoReqDTO.getGdsId());
		criteriaShopIdxUp.andSkuIdEqualTo(infoReqDTO.getSkuId());
		StockShopInfoIdx record = new StockShopInfoIdx();
		record.setCatgCode(infoReqDTO.getCatgCode());
		stockShopInfoIdxMapper.updateByExampleSelective(record, exampleShopIdxUp);

		// 获取仓库信息
		StockShopInfoIdxCriteria exampleShopIdx = new StockShopInfoIdxCriteria();
		StockShopInfoIdxCriteria.Criteria criteriaShopIdx = exampleShopIdx.createCriteria();
		criteriaShopIdx.andShopIdEqualTo(infoReqDTO.getShopId());
		criteriaShopIdx.andGdsIdEqualTo(infoReqDTO.getGdsId());
		criteriaShopIdx.andSkuIdEqualTo(infoReqDTO.getSkuId());
		criteriaShopIdx.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		List<StockShopInfoIdx> stockShopInfoIdxs = stockShopInfoIdxMapper.selectByExample(exampleShopIdx);
		if (stockShopInfoIdxs != null && stockShopInfoIdxs.size() > 0) {
			StockShopInfoIdx stockShopInfoIdx = stockShopInfoIdxs.get(0);
			Long stockId = stockShopInfoIdx.getStockId();
			// 更新库存信息表
			StockInfo recordStockInfoUp = new StockInfo();
			recordStockInfoUp.setId(stockId);
			recordStockInfoUp.setCatgCode(infoReqDTO.getCatgCode());
			stockInfoMapper.updateByPrimaryKeySelective(recordStockInfoUp);
			// 库存历史表
			StockInfoHisCriteria exampleHisUp = new StockInfoHisCriteria();
			StockInfoHisCriteria.Criteria criteriaHis = exampleHisUp.createCriteria();
			criteriaHis.andStockIdEqualTo(stockId);
			StockInfoHis stockInfoHisUp = new StockInfoHis();
			stockInfoHisUp.setCatgCode(infoReqDTO.getCatgCode());
			stockInfoHisMapper.updateByExampleSelective(stockInfoHisUp, exampleHisUp);

			// 更新库存适用范围信息表
			StockInfoAdaptCriteria examStockAdaptUp = new StockInfoAdaptCriteria();
			StockInfoAdaptCriteria.Criteria criteriaAdaptUp = examStockAdaptUp.createCriteria();
			criteriaAdaptUp.andStockIdEqualTo(stockId);
			StockInfoAdapt stockInfoAdaptUp = new StockInfoAdapt();
			stockInfoAdaptUp.setCatgCode(infoReqDTO.getCatgCode());
			stockInfoAdaptMapper.updateByExampleSelective(stockInfoAdaptUp, examStockAdaptUp);

			StockInfo stockInfo = stockInfoMapper.selectByPrimaryKey(stockId);
			if (stockInfo != null) {
				Long repCode = stockInfo.getRepCode();
				// 更新仓库库存索引表
				StockRepInfoIdxCriteria exampleRepIdxUp = new StockRepInfoIdxCriteria();
				StockRepInfoIdxCriteria.Criteria criteriaRepIdxUp = exampleRepIdxUp.createCriteria();
				criteriaRepIdxUp.andRepCodeEqualTo(repCode);
				criteriaRepIdxUp.andGdsIdEqualTo(infoReqDTO.getGdsId());
				criteriaRepIdxUp.andSkuIdEqualTo(infoReqDTO.getSkuId());
				StockRepInfoIdx recordRep = new StockRepInfoIdx();
				recordRep.setCatgCode(infoReqDTO.getCatgCode());
				stockRepInfoIdxMapper.updateByExampleSelective(recordRep, exampleRepIdxUp);
			} else {

				throw new BusinessException();
			}

		} else {

			throw new BusinessException();
		}

		// 更新企业库存索引表
		StockCompanyInfoIdxCriteria exampleCompanyIdxUp = new StockCompanyInfoIdxCriteria();
		StockCompanyInfoIdxCriteria.Criteria criteriaCompanyIdxUp = exampleCompanyIdxUp.createCriteria();
		criteriaCompanyIdxUp.andCompanyIdEqualTo(infoReqDTO.getCompanyId());
		criteriaCompanyIdxUp.andGdsIdEqualTo(infoReqDTO.getGdsId());
		criteriaCompanyIdxUp.andSkuIdEqualTo(infoReqDTO.getSkuId());
		StockCompanyInfoIdx recordCompIdx = new StockCompanyInfoIdx();
		recordCompIdx.setCatgCode(infoReqDTO.getCatgCode());
		stockCompanyInfoIdxMapper.updateByExampleSelective(recordCompIdx, exampleCompanyIdxUp);

	}

	@Override
	public void returnStockInfo(StockReturnInfoReqDTO returnInfoReqDTO) throws Exception {
		CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
		custInfoReqDTO.setId(returnInfoReqDTO.getStaffId());
		CustInfoResDTO custInfoResDTO = custInfoRSV.getCustInfoById(custInfoReqDTO);
		// 如果买家是个人用户
		if (StaffConstants.custInfo.CUST_TYPE_P.equals(custInfoResDTO.getCustType())) {
			return;
		} else if (StaffConstants.custInfo.CUST_TYPE_C.equals(custInfoResDTO.getCustType())) {
			for (StockReturnSubReqDTO returnSubReqDTO : returnInfoReqDTO.getReturnSubReqDTOs()) {
				StockCompanyInfoIdxCriteria companyInfoIdxCriteria = new StockCompanyInfoIdxCriteria();
				StockCompanyInfoIdxCriteria.Criteria criteria = companyInfoIdxCriteria.createCriteria();
				criteria.andCompanyIdEqualTo(custInfoResDTO.getCompanyId());
				criteria.andSkuIdEqualTo(returnSubReqDTO.getSkuId());
				criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
				criteria.andRepTypeEqualTo(GdsConstants.GdsStock.STOCK_REP_TYPE_BUYER);
				List<StockCompanyInfoIdx> companyInfoIdxs = stockCompanyInfoIdxMapper.selectByExample(companyInfoIdxCriteria);
				if (companyInfoIdxs != null && companyInfoIdxs.size() > 0) {
					Long stockId = companyInfoIdxs.get(0).getStockId();
					StockInfoReqDTO stockInfoReqDTO = new StockInfoReqDTO();
					stockInfoReqDTO.setId(stockId);
					stockInfoReqDTO.setTurnType(GdsConstants.GdsStock.STOCK_INFO_TURN_DOWN);
					stockInfoReqDTO.setTurnCount(returnSubReqDTO.getReturnNum());
					stockInfoReqDTO.setOrdOptNo(returnSubReqDTO.getBackId() + "");
					stockInfoReqDTO.setStaffId(returnInfoReqDTO.getStaffId());
					this.updateStockInfo(stockInfoReqDTO);
				} else {
					throw new BusinessException(GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235002);
				}

			}

		}
	}

	/**
	 * 
	 * copyStockInfo2Resp:(将库存info对象转换为resp对象). <br/>
	 * 
	 * @author linwb3
	 * @param stockInfo
	 * @return
	 * @since JDK 1.6
	 */
	private StockInfoRespDTO copyStockInfo2Resp(StockInfo stockInfo) {
		StockInfoRespDTO stockInfoDTO = new StockInfoRespDTO();
		stockInfoDTO.setId(stockInfo.getId());
		stockInfoDTO.setRepCode(stockInfo.getRepCode());
		stockInfoDTO.setCatgCode(stockInfo.getCatgCode());
		stockInfoDTO.setTypeId(stockInfo.getTypeId());
		stockInfoDTO.setGdsId(stockInfo.getGdsId());
		stockInfoDTO.setSkuId(stockInfo.getSkuId());
		stockInfoDTO.setShopId(stockInfo.getShopId());
		stockInfoDTO.setCompanyId(stockInfo.getCompanyId());
		stockInfoDTO.setRealCount(stockInfo.getRealCount());
		stockInfoDTO.setPreOccupyCount(stockInfo.getPreOccupyCount());
		stockInfoDTO.setAvailCount(stockInfo.getAvailCount());
		stockInfoDTO.setSendCount(stockInfo.getSendCount());
		stockInfoDTO.setLackCount(stockInfo.getLackCount());
		stockInfoDTO.setWarningCount(stockInfo.getWarningCount());
		stockInfoDTO.setIsUsewarning(stockInfo.getIsUsewarning());
		stockInfoDTO.setIsOver(stockInfo.getIsOver());
		stockInfoDTO.setRepType(stockInfo.getRepType());
		stockInfoDTO.setStockType(stockInfo.getStockType());
		stockInfoDTO.setStatus(stockInfo.getStatus());
		stockInfoDTO.setCreateStaff(stockInfo.getCreateStaff());
		stockInfoDTO.setCreateTime(stockInfo.getCreateTime());
		stockInfoDTO.setUpdateStaff(stockInfo.getUpdateStaff());
		stockInfoDTO.setUpdateTime(stockInfo.getUpdateTime());
		stockInfoDTO.setFacStock(stockInfo.getFacStock());
		stockInfoDTO.setZeroStockFlag(stockInfo.getZeroStockFlag());
		stockInfoDTO.setZeroStockStarttime(stockInfo.getZeroStockStarttime());
		return stockInfoDTO;
	}
	
	/**
	 * 
	 * copyStockInfo2Resp:(将库存info对象转换为resp对象). <br/>
	 * 
	 * @author linwb3
	 * @param stockInfo
	 * @return
	 * @since JDK 1.6
	 */
	private StockInfoPageRespDTO copyStockInfo2PageResp(StockInfo stockInfo) {
		StockInfoPageRespDTO stockInfoDTO = new StockInfoPageRespDTO();
		stockInfoDTO.setId(stockInfo.getId());
		stockInfoDTO.setRepCode(stockInfo.getRepCode());
		try {
			if(stockInfo.getCatgCode() != null){
				stockInfoDTO.setCatgCode(Long.parseLong(stockInfo.getCatgCode()));
			}
		} catch (Exception e) {
			LogUtil.info(MODULE, "convert failed");
		}
		stockInfoDTO.setTypeId(stockInfo.getTypeId());
		stockInfoDTO.setGdsId(stockInfo.getGdsId());
		stockInfoDTO.setSkuId(stockInfo.getSkuId());
		stockInfoDTO.setShopId(stockInfo.getShopId());
		stockInfoDTO.setCompanyId(stockInfo.getCompanyId());
		stockInfoDTO.setRealCount(stockInfo.getRealCount());
		stockInfoDTO.setPreOccupyCount(stockInfo.getPreOccupyCount());
		stockInfoDTO.setAvailCount(stockInfo.getAvailCount());
		stockInfoDTO.setSendCount(stockInfo.getSendCount());
		stockInfoDTO.setWarningCount(stockInfo.getWarningCount());
		stockInfoDTO.setRepType(stockInfo.getRepType());
		return stockInfoDTO;
	}
	

	@Override
	public Long statisticStockLack(StockInfoReqDTO stockInfoReqDTO) throws Exception {
		StockShopInfoIdxCriteria stockShopInfoIdxCriteria = new StockShopInfoIdxCriteria();
		StockShopInfoIdxCriteria.Criteria criteria = stockShopInfoIdxCriteria.createCriteria();
		criteria.andAvailCountLessThan(Long.parseLong(SysCfgUtil.fetchSysCfg("STOCK_LACK_THRESHOLD").getParaValue()));
		criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		if (stockInfoReqDTO.getShopId() != null) {

			criteria.andShopIdEqualTo(stockInfoReqDTO.getShopId());
		}
		Long count = shopInfoIdxExtraMapper.countGroupBySkuId(stockShopInfoIdxCriteria);
		return count;

	}

    @Override
    public StockInfoRespDTO queryStockInfoById(Long stockId) throws BusinessException {
        paramNullCheck(stockId, "stockId");
        StockInfoRespDTO resp = null;
        StockInfo realStock = stockInfoMapper.selectByPrimaryKey(stockId);
        if(null != realStock){
            resp = copyStockInfo2Resp(realStock);
        }
        return resp;
    }
	
}
