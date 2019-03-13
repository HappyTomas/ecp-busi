package com.zengshi.ecp.goods.service.busi.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsCatgCustDiscMapper;
import com.zengshi.ecp.goods.dao.model.GdsCategory;
import com.zengshi.ecp.goods.dao.model.GdsCatgCustDisc;
import com.zengshi.ecp.goods.dao.model.GdsCatgCustDiscCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.CalCatgCustDiscReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustBlacklistReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustBlacklistRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustDiscBatchDelReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustDiscListReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustDiscReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustDiscRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsCatgCustBlacklistSV;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsCatgCustDiscSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySV;
import com.zengshi.ecp.server.front.dto.BaseStaff;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;

public class GdsCatgCustDiscSVImpl extends AbstractSVImpl implements IGdsCatgCustDiscSV {
	private static final String MODULE = GdsCatgCustDiscSVImpl.class.getName();
    
    @Resource(name = "seq_gds_catgcust_disc")
	private Sequence seqGdsCatgCustDisc;

	@Resource
	private GdsCatgCustDiscMapper gdsCatgCustDiscMapper;

	@Resource
	private IGdsInfoQuerySV gdsInfoQuerySV;

	@Resource
	private ICustInfoRSV custInfoRSV;

	@Resource
	private IGdsCategorySV gdsCategorySV;

	@Resource
	private IGdsCatgCustBlacklistSV gdsCatgCustBlacklistSV;
	public void init() {

	}

	@Override
	public void addGdsCatgCustDisc(GdsCatgCustDiscListReqDTO catgCustDiscListReqDTO) throws BusinessException {
		GdsCatgCustDiscCriteria example = new GdsCatgCustDiscCriteria();
		GdsCatgCustDiscCriteria.Criteria criteria = example.createCriteria();
		criteria.andCatgCodeEqualTo(catgCustDiscListReqDTO.getCatgCustDiscReqDTOs().get(0).getCatgCode());
		criteria.andShopIdEqualTo(catgCustDiscListReqDTO.getCatgCustDiscReqDTOs().get(0).getShopId());
		criteria.andCustLevelCodeEqualTo(catgCustDiscListReqDTO.getCatgCustDiscReqDTOs().get(0).getCustLevelCode());
		criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		long count = gdsCatgCustDiscMapper.countByExample(example);
		if (count > 0) {
			throw new BusinessException(GdsErrorConstants.GdsCatgCustDisc.ERROR_GOODS_CATG_CUST_DISC_240301);
		} else {
			for (GdsCatgCustDiscReqDTO catgCustDiscReqDTO : catgCustDiscListReqDTO.getCatgCustDiscReqDTOs()) {
				GdsCatgCustDisc catgCustDisc = new GdsCatgCustDisc();
				ObjectCopyUtil.copyObjValue(catgCustDiscReqDTO, catgCustDisc, null, false);

				catgCustDisc.setStatus(GdsConstants.Commons.STATUS_VALID);
				catgCustDisc.setCreateStaff(catgCustDiscListReqDTO.getStaff().getId());
				catgCustDisc.setUpdateStaff(catgCustDiscListReqDTO.getStaff().getId());
				catgCustDisc.setCreateTime(new Timestamp(System.currentTimeMillis()));
				catgCustDisc.setUpdateTime(new Timestamp(System.currentTimeMillis()));
				catgCustDisc.setId(seqGdsCatgCustDisc.nextValue());
				gdsCatgCustDiscMapper.insertSelective(catgCustDisc);
			}

			for (GdsCatgCustDiscReqDTO catgCustDiscReqDTO : catgCustDiscListReqDTO.getCatgCustDiscReqDTOs()) {
				String key = "GDS_CATGCUST_DISC_" + catgCustDiscReqDTO.getShopId() + "_" + catgCustDiscReqDTO.getCustLevelCode() + "_" + catgCustDiscReqDTO.getCatgCode();
				CacheUtil.addItem(key, catgCustDiscReqDTO);
			}
			
		}

	}

	@Override
	public void editGdsCatgCustDisc(GdsCatgCustDiscListReqDTO catgCustDiscListReqDTO) throws BusinessException {
		int key = 0;
		for (GdsCatgCustDiscReqDTO catgCustDiscReqDTO : catgCustDiscListReqDTO.getCatgCustDiscReqDTOs()) {

			GdsCatgCustDisc catgCustDisc = new GdsCatgCustDisc();
			catgCustDisc.setDiscount(catgCustDiscReqDTO.getDiscount());
			catgCustDisc.setUpdateStaff(catgCustDiscReqDTO.getStaff().getId());
			catgCustDisc.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			GdsCatgCustDiscCriteria example = new GdsCatgCustDiscCriteria();
			GdsCatgCustDiscCriteria.Criteria criteria = example.createCriteria();
			criteria.andIdEqualTo(catgCustDiscReqDTO.getId());

			key = gdsCatgCustDiscMapper.updateByExampleSelective(catgCustDisc, example);

			if (key == 0) {
				throw new BusinessException(GdsErrorConstants.GdsCatgCustDisc.ERROR_GOODS_CATG_CUST_DISC_240303);
			}
		}

		for (GdsCatgCustDiscReqDTO catgCustDiscReqDTO : catgCustDiscListReqDTO.getCatgCustDiscReqDTOs()) {

			String itemKey = "GDS_CATGCUST_DISC_" + catgCustDiscReqDTO.getShopId() + "_" + catgCustDiscReqDTO.getCustLevelCode() + "_" + catgCustDiscReqDTO.getCatgCode();
			CacheUtil.addItem(itemKey, catgCustDiscReqDTO);
		}
	}

	@Override
	public void deleteGdsCatgCustDisc(GdsCatgCustDiscReqDTO catgCustDiscReqDTO) throws BusinessException {
		// 获取店铺在该分类下的所有有效折扣
		GdsCatgCustDiscCriteria exampleAll = new GdsCatgCustDiscCriteria();
		GdsCatgCustDiscCriteria.Criteria criteriaAll = exampleAll.createCriteria();
		criteriaAll.andCatgCodeEqualTo(catgCustDiscReqDTO.getCatgCode());
		criteriaAll.andShopIdEqualTo(catgCustDiscReqDTO.getShopId());
		criteriaAll.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		List<GdsCatgCustDisc> catgCustDiscs = gdsCatgCustDiscMapper.selectByExample(exampleAll);

		GdsCatgCustDisc catgCustDisc = new GdsCatgCustDisc();
		catgCustDisc.setStatus(GdsConstants.Commons.STATUS_INVALID);
		catgCustDisc.setUpdateStaff(catgCustDiscReqDTO.getStaff().getId());
		catgCustDisc.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		GdsCatgCustDiscCriteria example = new GdsCatgCustDiscCriteria();
		GdsCatgCustDiscCriteria.Criteria criteria = example.createCriteria();
		criteria.andCatgCodeEqualTo(catgCustDiscReqDTO.getCatgCode());
		criteria.andShopIdEqualTo(catgCustDiscReqDTO.getShopId());

		int key = gdsCatgCustDiscMapper.updateByExampleSelective(catgCustDisc, example);
		if (key == 0) {
			throw new BusinessException(GdsErrorConstants.GdsCatgCustDisc.ERROR_GOODS_CATG_CUST_DISC_240302);
		} else {
			for (GdsCatgCustDisc before : catgCustDiscs) {
				String itemKey = "GDS_CATGCUST_DISC_" + before.getShopId() + "_" + before.getCustLevelCode() + "_" + before.getCatgCode();
				CacheUtil.delItem(itemKey);
			}
		}
	}

	@Override
	public PageResponseDTO<GdsCatgCustDiscRespDTO> queryGdsCatgCustDiscByPage(GdsCatgCustDiscReqDTO catgCustDiscReqDTO) throws BusinessException {
		GdsCatgCustDiscCriteria example = new GdsCatgCustDiscCriteria();
		GdsCatgCustDiscCriteria.Criteria criteria = example.createCriteria();
		criteria.andShopIdEqualTo(catgCustDiscReqDTO.getShopId());
		if (catgCustDiscReqDTO.getCatgCode() != null && !"".equals(catgCustDiscReqDTO.getCatgCode())) {
			criteria.andCatgCodeEqualTo(catgCustDiscReqDTO.getCatgCode());

		}

		if (catgCustDiscReqDTO.getCustLevelCode() != null && !"".equals(catgCustDiscReqDTO.getCustLevelCode())) {
			criteria.andCustLevelCodeEqualTo(catgCustDiscReqDTO.getCustLevelCode());

		}
		criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		example.setLimitClauseStart(catgCustDiscReqDTO.getStartRowIndex());
		example.setLimitClauseCount(catgCustDiscReqDTO.getPageSize());

		return super.queryByPagination(catgCustDiscReqDTO, example, true, new PaginationCallback<GdsCatgCustDisc, GdsCatgCustDiscRespDTO>() {

			@Override
			public List<GdsCatgCustDisc> queryDB(BaseCriteria criteria) {
				return gdsCatgCustDiscMapper.selectByExample((GdsCatgCustDiscCriteria) criteria);
			}

			@Override
			public long queryTotal(BaseCriteria criteria) {
				return gdsCatgCustDiscMapper.countByExample((GdsCatgCustDiscCriteria) criteria);
			}

			@Override
			public GdsCatgCustDiscRespDTO warpReturnObject(GdsCatgCustDisc t) {
				GdsCatgCustDiscRespDTO respDTO = new GdsCatgCustDiscRespDTO();
				ObjectCopyUtil.copyObjValue(t, respDTO, null, false);
				String custLevelName = BaseParamUtil.fetchParamValue("STAFF_CUST_LEVEL", "" + t.getCustLevelCode());
				respDTO.setCustLevelName(custLevelName);
				GdsCategory category = gdsCategorySV.queryGdsCategoryById(t.getCatgCode());
				respDTO.setCatgName(category.getCatgName());
				return respDTO;
			}

		});
	}

	@Override
	public void deleteGdsCatgCustDiscByGroup(GdsCatgCustDiscBatchDelReqDTO batchDelReqDTO) throws BusinessException {
		for (GdsCatgCustDiscReqDTO catgCustDiscReqDTO : batchDelReqDTO.getCatgCustDiscReqDTOs()) {
			BaseStaff baseStaff = new BaseStaff();
			baseStaff.setId(batchDelReqDTO.getStaff().getId());
			catgCustDiscReqDTO.setStaff(baseStaff);
			this.deleteGdsCatgCustDisc(catgCustDiscReqDTO);

		}

	}

	@Override
	public BigDecimal calCatgCustDisc(CalCatgCustDiscReqDTO reqDto) throws BusinessException {
		GdsCatgCustBlacklistReqDTO reqDTO = new GdsCatgCustBlacklistReqDTO();
		reqDTO.setGdsId(reqDto.getGdsId());
		reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
		GdsCatgCustBlacklistRespDTO gdsCatgCustBlacklistRespDTO = null;
		try {
			gdsCatgCustBlacklistRespDTO = gdsCatgCustBlacklistSV.queryGdsCatgcustBlacklistByExample(reqDTO);
			if(null!=gdsCatgCustBlacklistRespDTO&&gdsCatgCustBlacklistRespDTO.getGdsId()!=null&&gdsCatgCustBlacklistRespDTO.getGdsId()>0L){
				return new BigDecimal(100.0);
			}
		} catch (Exception e) {
			LogUtil.error(MODULE, "折扣黑名单获取异常", e);
		}
		if (reqDto.getGdsId() == null || 0L == reqDto.getGdsId()) {
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100, new String[] { "gdsId" });
		}
		// 查询商品信息
		GdsInfoReqDTO gdsInfo = new GdsInfoReqDTO();
		gdsInfo.setId(reqDto.getGdsId());
		GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQuerySV.queryGdsInfoByOption(gdsInfo, new GdsQueryOption[] { GdsQueryOption.BASIC });

		// 查询所有分类，包含本身
		String mainCatg = gdsInfoRespDTO.getMainCatgs();
		List<GdsCategoryRespDTO> categoryRespDTOs = gdsCategorySV.queryCategoryTraceUpon(mainCatg);

		//
		String custLevelCode = "";
		if (StringUtil.isNotBlank(reqDto.getCustLevelCode())) {
			custLevelCode = reqDto.getCustLevelCode();
		} else if (StringUtil.isNotBlank(reqDto.getStaff().getStaffLevelCode())) {
			custLevelCode = reqDto.getStaff().getStaffLevelCode();
		}else if (reqDto.getCustNo() != null && reqDto.getCustNo()!=0L) {
            CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
            custInfoReqDTO.setId(reqDto.getCustNo());
            CustInfoResDTO custInfoResDTO = custInfoRSV.getCustInfoById(custInfoReqDTO);
            custLevelCode = custInfoResDTO.getCustLevelCode();
        }else{
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100, new String[] { "custLevelCode" });
		}

		if (CollectionUtils.isNotEmpty(categoryRespDTOs)) {
			for (int i = categoryRespDTOs.size() - 1; i >= 0; i--) {
				GdsCategoryRespDTO categoryRespDTO = categoryRespDTOs.get(i);
				String catgCode = categoryRespDTO.getCatgCode();
				String itemKey = "GDS_CATGCUST_DISC_" + gdsInfoRespDTO.getShopId() + "_" + custLevelCode + "_" + catgCode;
				// 先从缓存查找，从最末级开始匹配
				try {
					GdsCatgCustDiscReqDTO catgCustDisc = (GdsCatgCustDiscReqDTO) CacheUtil.getItem(itemKey);
					if (catgCustDisc != null) {
						return catgCustDisc.getDiscount();
					}

				} catch (BusinessException e) {
					LogUtil.error(MODULE, "折扣计算异常", e);
				}
			}
			List<String> catIds = new ArrayList<String>();
			for (GdsCategoryRespDTO categoryRespDTO : categoryRespDTOs) {
				catIds.add(categoryRespDTO.getCatgCode());
			}
			// 获取所有的符合等级的折扣
			GdsCatgCustDiscCriteria example = new GdsCatgCustDiscCriteria();
			GdsCatgCustDiscCriteria.Criteria criteria = example.createCriteria();
			criteria.andCatgCodeIn(catIds);
			criteria.andShopIdEqualTo(gdsInfoRespDTO.getShopId());
			criteria.andCustLevelCodeEqualTo(custLevelCode);
			criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
			List<GdsCatgCustDisc> catgCustDiscs = gdsCatgCustDiscMapper.selectByExample(example);

			if (catgCustDiscs != null && catgCustDiscs.size() > 0) {
				// 遍历分类列表，从最低级开始
				for (int i = categoryRespDTOs.size() - 1; i >= 0; i--) {
					GdsCategoryRespDTO categoryRespDTO = categoryRespDTOs.get(i);
					// 遍历折扣列表，找到匹配的，没找到则匹配下层
					for (GdsCatgCustDisc catgCustDisc : catgCustDiscs) {
						if (catgCustDisc.getCatgCode() != null && catgCustDisc.getCatgCode().equals(categoryRespDTO.getCatgCode())) {
							String itemKey = "GDS_CATGCUST_DISC_" + gdsInfoRespDTO.getShopId() + "_" + custLevelCode + "_" + catgCustDisc.getCatgCode();
							GdsCatgCustDiscReqDTO catgCustDiscReqDTO = new GdsCatgCustDiscReqDTO();
							ObjectCopyUtil.copyObjValue(catgCustDisc, catgCustDiscReqDTO, null, false);
							CacheUtil.addItem(itemKey, catgCustDiscReqDTO);
							return catgCustDiscs.get(0).getDiscount();

						}

					}
				}

			}
		}

		return new BigDecimal(100.0);

	}
}
