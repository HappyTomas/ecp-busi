package com.zengshi.ecp.sys.service.common.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.sys.dao.mapper.common.ReportItemMapper;
import com.zengshi.ecp.sys.dao.model.ReportItem;
import com.zengshi.ecp.sys.dao.model.ReportItemCriteria;
import com.zengshi.ecp.sys.dao.model.ReportItemKey;
import com.zengshi.ecp.sys.dubbo.dto.ReportItemReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.ReportItemRespDTO;
import com.zengshi.ecp.sys.service.common.interfaces.IReportItemSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.db.sequence.Sequence;

@Service("reportItemSV")
public class ReportItemSVImpl implements IReportItemSV {

	@Resource
	private ReportItemMapper reportItemMapper;

	@Resource(name = "seq_report_item")
	private Sequence seqReportItem;

	@Override
	public void addReportItem(ReportItemReqDTO reportItemReqDTO) throws Exception {
		// TODO Auto-generated method stub
		// 判断是否已经存在当天的统计数据
		// 存在则删除之前的
		if (isExsistItem(reportItemReqDTO)) {
			this.delReportItem(reportItemReqDTO);
		}
		ReportItem item = new ReportItem();
		ObjectCopyUtil.copyObjValue(reportItemReqDTO, item, null, false);
		item.setId(seqReportItem.nextValue());
		item.setCreateTime(new Timestamp(System.currentTimeMillis()));
		item.setStatus("1");
		reportItemMapper.insert(item);
	}

	@Override
	public List<ReportItemRespDTO> queryReportItemByCondition(ReportItemReqDTO reportItemReqDTO) throws Exception {
		// TODO Auto-generated method stub
		ReportItemCriteria reportItemCriteria = new ReportItemCriteria();
		reportItemCriteria.setOrderByClause("CAL_DATE ASC");
		ReportItemCriteria.Criteria criteria = reportItemCriteria.createCriteria();
		criteria.andItemCodeEqualTo(reportItemReqDTO.getItemCode());
		
		if (reportItemReqDTO.getShopId() != null) {
			criteria.andShopIdEqualTo(reportItemReqDTO.getShopId());
		}
		if (reportItemReqDTO.getCalDate() != null) {
			criteria.andCalDateEqualTo(reportItemReqDTO.getCalDate());
		}
		if (reportItemReqDTO.getCalDateStart() != null) {
			criteria.andCalDateGreaterThanOrEqualTo(reportItemReqDTO.getCalDateStart());
		}
		if (reportItemReqDTO.getCalDateEnd() != null) {
			criteria.andCalDateLessThanOrEqualTo(reportItemReqDTO.getCalDateEnd());
		}

		if (reportItemReqDTO.getShopId() != null) {
			
			criteria.andShopIdEqualTo(reportItemReqDTO.getShopId() );
		}
		
		criteria.andStatusEqualTo("1");
		List<ReportItemRespDTO> reportItemDTOs = new ArrayList<ReportItemRespDTO>();
		List<ReportItem> reportItems = reportItemMapper.selectByExample(reportItemCriteria);
		if (CollectionUtils.isNotEmpty(reportItems)) {
			for (ReportItem reportItem : reportItems) {
				ReportItemRespDTO reportItemDTO = new ReportItemRespDTO();

				ObjectCopyUtil.copyObjValue(reportItem, reportItemDTO, null, false);
				reportItemDTOs.add(reportItemDTO);
			}

		}
		return reportItemDTOs;
	}

	@Override
	public void delReportItem(ReportItemReqDTO reportItemReqDTO) throws Exception {
		// TODO Auto-generated method stub
		ReportItemKey key = new ReportItemKey();
		key.setCalDate(reportItemReqDTO.getCalDate());
		key.setItemCode(reportItemReqDTO.getItemCode());
		key.setShopId(reportItemReqDTO.getShopId());
		key.setItemSource(reportItemReqDTO.getItemSource());
		reportItemMapper.deleteByPrimaryKey(key);

	}

	private Boolean isExsistItem(ReportItemReqDTO reportItemReqDTO) throws Exception {
		ReportItemKey itemKey = new ReportItemKey();
		itemKey.setCalDate(reportItemReqDTO.getCalDate());
		itemKey.setItemCode(reportItemReqDTO.getItemCode());
		itemKey.setItemSource(reportItemReqDTO.getItemSource());
		itemKey.setShopId(reportItemReqDTO.getShopId());
		ReportItem reportItem = reportItemMapper.selectByPrimaryKey(itemKey);
		if (reportItem != null) {

			return true;
		} else {
			return false;
		}
	}
}
