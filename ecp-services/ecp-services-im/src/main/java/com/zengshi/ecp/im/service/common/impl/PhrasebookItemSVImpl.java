package com.zengshi.ecp.im.service.common.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.im.dao.mapper.common.ImPhrasebookItemMapper;
import com.zengshi.ecp.im.dao.model.ImPhrasebookItem;
import com.zengshi.ecp.im.dao.model.ImPhrasebookItemCriteria;
import com.zengshi.ecp.im.dubbo.dto.ImPhrasebookItemReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImPhrasebookItemResDTO;
import com.zengshi.ecp.im.service.common.interfaces.IPhrasebookItemSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-im-server <br>
 * Description: <br>
 * 常用语服务
 * <br>
 * Date:2017年4月5日下午8:42:25  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.7
 */
public class PhrasebookItemSVImpl extends GeneralSQLSVImpl implements IPhrasebookItemSV {
	
	private static final String MODULE = PhrasebookItemSVImpl.class.getName();
	@Resource
	private ImPhrasebookItemMapper imPhrasebookItemMapper;
	
	@Resource(name = "seq_im_phrasebook_item_id")
	private Sequence seqImPhrasebookItemId;
	
	@Override
	public List<ImPhrasebookItemResDTO> findPhrasebookItemByGroupId(ImPhrasebookItemReqDTO reqDTO)
			throws BusinessException {
		if(reqDTO.getGroupId()==null){
			throw new BusinessException("groupId不能为空！");
		}
		List<ImPhrasebookItemResDTO> res = new ArrayList<>();
		ImPhrasebookItemCriteria itemCriteria = new ImPhrasebookItemCriteria();
		ImPhrasebookItemCriteria.Criteria sql = itemCriteria.createCriteria();
		sql.andGroupIdEqualTo(reqDTO.getGroupId());
		itemCriteria.setOrderByClause("sort_no asc,update_time desc");//sort_no升序，修改时间作为第二排序条件降序
		List<ImPhrasebookItem> list = imPhrasebookItemMapper.selectByExample(itemCriteria);
		if(CollectionUtils.isNotEmpty(list)){
			for (ImPhrasebookItem imPhrasebookItem : list) {
				ImPhrasebookItemResDTO dto = new ImPhrasebookItemResDTO();
				ObjectCopyUtil.copyObjValue(imPhrasebookItem, dto, null, false);
				res.add(dto);
			}
		}
		return res;
	}

	@Override
	public Long savePhrasebookItem(ImPhrasebookItemReqDTO reqDTO) throws BusinessException {
		if(reqDTO.getGroupId()==null){
			throw new BusinessException("groupId 不能为空！");
		}
		ImPhrasebookItem record = new ImPhrasebookItem();
		ObjectCopyUtil.copyObjValue(reqDTO, record, null, false);
		record.setId(seqImPhrasebookItemId.nextValue());
		//处理排序
		if(record.getSortNo()==null){
			ImPhrasebookItemCriteria findCriteria = new ImPhrasebookItemCriteria();
			ImPhrasebookItemCriteria.Criteria sql = findCriteria.createCriteria();
			sql.andGroupIdEqualTo(reqDTO.getGroupId());
			findCriteria.setOrderByClause("sort_no desc");//排序号降序
			List<ImPhrasebookItem> listFind = imPhrasebookItemMapper.selectByExample(findCriteria);
			if(CollectionUtils.isEmpty(listFind)){
				record.setSortNo(1L);//默认排序值为1
			}else{
				Long sortMax = listFind.get(0).getSortNo();
				record.setSortNo(sortMax+1);//加1作为新记录的排序值
			}
		}
		Long staffId = reqDTO.getStaff().getId();
		Timestamp optTime = DateUtil.getSysDate();
		record.setCreateStaff(staffId);
		record.setCreateTime(optTime);
		record.setUpdateStaff(staffId);
		record.setUpdateTime(optTime);
		imPhrasebookItemMapper.insertSelective(record);
		return record.getId();
	}

	@Override
	public int deletePhrasebookItemById(ImPhrasebookItemReqDTO reqDTO) throws BusinessException {
		if(reqDTO.getId()==null){
			throw new BusinessException("id不能为空!");
		}
		int affected = imPhrasebookItemMapper.deleteByPrimaryKey(reqDTO.getId());
		return affected;
	}

	@Override
	public int updatePhrasebookItemById(ImPhrasebookItemReqDTO reqDTO) throws BusinessException {
		if(reqDTO.getId()==null){
			throw new BusinessException("id不能为空!");
		}
		ImPhrasebookItem record = new ImPhrasebookItem();
		ObjectCopyUtil.copyObjValue(reqDTO, record, null, false);
		record.setUpdateStaff(reqDTO.getStaff().getId());
		record.setUpdateTime(DateUtil.getSysDate());
		int affected = imPhrasebookItemMapper.updateByPrimaryKeySelective(record);
		return affected;
	}

	@Override
	public PageResponseDTO<ImPhrasebookItemResDTO> queryPhrasebookItem(ImPhrasebookItemReqDTO reqDTO)
			throws BusinessException {
		ImPhrasebookItemCriteria itemCriteria = new ImPhrasebookItemCriteria();
		ImPhrasebookItemCriteria.Criteria sql = itemCriteria.createCriteria();
		if(reqDTO.getGroupId()!=null){
			sql.andGroupIdEqualTo(reqDTO.getGroupId());
		}
		if(StringUtil.isNotBlank(reqDTO.getKeyWord())){
			sql.andKeyWordLike(reqDTO.getKeyWord());
		}
		itemCriteria.setLimitClauseCount(reqDTO.getPageSize());
		itemCriteria.setLimitClauseStart(reqDTO.getStartRowIndex());
		itemCriteria.setOrderByClause("update_time desc");
		return super.queryByPagination(reqDTO, itemCriteria, false, new PaginationCallback<ImPhrasebookItem, ImPhrasebookItemResDTO>() {

			@Override
			public List<ImPhrasebookItem> queryDB(BaseCriteria arg0) {
				return imPhrasebookItemMapper.selectByExample((ImPhrasebookItemCriteria) arg0);
			}

			@Override
			public long queryTotal(BaseCriteria arg0) {
				return imPhrasebookItemMapper.countByExample((ImPhrasebookItemCriteria) arg0);
			}

			@Override
			public ImPhrasebookItemResDTO warpReturnObject(ImPhrasebookItem arg0) {
				ImPhrasebookItemResDTO dto = new ImPhrasebookItemResDTO();
				ObjectCopyUtil.copyObjValue(arg0, dto, null, false);
				return dto;
			}
		});
	}

	@Override
	public ImPhrasebookItemResDTO queryPhrasebookItemById(ImPhrasebookItemReqDTO reqDTO) throws BusinessException {
		if(reqDTO.getId()==null){
			throw new BusinessException("id不能为空！");
		}
		ImPhrasebookItem select = imPhrasebookItemMapper.selectByPrimaryKey(reqDTO.getId());
		ImPhrasebookItemResDTO res = new ImPhrasebookItemResDTO();
		ObjectCopyUtil.copyObjValue(select, res, null, false);
		
		return res;
	}

}
