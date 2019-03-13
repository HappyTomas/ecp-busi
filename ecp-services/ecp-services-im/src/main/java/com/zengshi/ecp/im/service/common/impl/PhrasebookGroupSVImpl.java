package com.zengshi.ecp.im.service.common.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.im.dao.mapper.common.ImPhrasebookGroupMapper;
import com.zengshi.ecp.im.dao.mapper.common.ImPhrasebookItemMapper;
import com.zengshi.ecp.im.dao.model.ImPhrasebookGroup;
import com.zengshi.ecp.im.dao.model.ImPhrasebookGroupCriteria;
import com.zengshi.ecp.im.dao.model.ImPhrasebookItemCriteria;
import com.zengshi.ecp.im.dubbo.dto.ImPhrasebookGroupReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImPhrasebookGroupResDTO;
import com.zengshi.ecp.im.dubbo.dto.ImPhrasebookItemReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImPhrasebookItemResDTO;
import com.zengshi.ecp.im.dubbo.util.ImConstants;
import com.zengshi.ecp.im.service.common.interfaces.IPhrasebookGroupSV;
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
 * 常用语分组服务 
 * <br>
 * Date:2017年4月5日下午8:41:15  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.7
 */
public class PhrasebookGroupSVImpl extends GeneralSQLSVImpl implements IPhrasebookGroupSV {
	
	private static final String MODULE = PhrasebookGroupSVImpl.class.getName();
	@Resource
	private ImPhrasebookGroupMapper imPhrasebookGroupMapper;
	@Resource
	private IPhrasebookItemSV phrasebookItemSV;
	@Resource
	private ImPhrasebookItemMapper imPhrasebookItemMapper;
	
	@Resource(name = "seq_im_phrasebook_group_id")
	private Sequence seqImPhrasebookGroupId;

	@Override
	public List<ImPhrasebookGroupResDTO> findPhrasebookGroupByShopId(ImPhrasebookGroupReqDTO reqDTO)
			throws BusinessException {
		if(reqDTO.getShopId()==null){
			throw new BusinessException("shopId 不能为空！");
		}
		List<ImPhrasebookGroupResDTO> res = new ArrayList<>();
		ImPhrasebookGroupCriteria groupCriteria = new ImPhrasebookGroupCriteria();
		ImPhrasebookGroupCriteria.Criteria sql = groupCriteria.createCriteria();
		sql.andShopIdEqualTo(reqDTO.getShopId());
		if(StringUtil.isNotBlank(reqDTO.getGroupClass())){
			sql.andGroupClassEqualTo(reqDTO.getGroupClass());
		}
		if(StringUtil.isNotBlank(reqDTO.getStatus())){
			sql.andStatusEqualTo(reqDTO.getStatus());
		}else{
			sql.andStatusEqualTo(ImConstants.STATE_1);//缺省有效
		}
		if(reqDTO.getCreateStaff()!=null){
			sql.andCreateStaffEqualTo(reqDTO.getCreateStaff());//创建人id
		}
		if(reqDTO.getStaffId()!=null){
			sql.andStaffIdEqualTo(reqDTO.getStaffId());//归属人id
		}
		
		groupCriteria.setOrderByClause("sort_no asc, update_time desc");
		
		List<ImPhrasebookGroup> list = imPhrasebookGroupMapper.selectByExample(groupCriteria);
		if(CollectionUtils.isNotEmpty(list)){
			for (ImPhrasebookGroup imPhrasebookGroup : list) {
				ImPhrasebookGroupResDTO dto = new ImPhrasebookGroupResDTO();
				ObjectCopyUtil.copyObjValue(imPhrasebookGroup, dto, null, false);
				//查询常用语
				ImPhrasebookItemReqDTO itemReq = new ImPhrasebookItemReqDTO();
				itemReq.setGroupId(imPhrasebookGroup.getId());
				List<ImPhrasebookItemResDTO> listItem = phrasebookItemSV.findPhrasebookItemByGroupId(itemReq);
				dto.setItems(listItem);
				res.add(dto);
			}
		}
		return res;
	}

	@Override
	public PageResponseDTO<ImPhrasebookGroupResDTO> queryPhrasebookGroup(ImPhrasebookGroupReqDTO reqDTO)
			throws BusinessException {
		ImPhrasebookGroupCriteria groupCriteria = new ImPhrasebookGroupCriteria();
		ImPhrasebookGroupCriteria.Criteria sql = groupCriteria.createCriteria();
		//分组
		if(StringUtil.isNotBlank(reqDTO.getGroupClass())){
			sql.andGroupClassEqualTo(reqDTO.getGroupClass());
		}
		//状态
		if(StringUtil.isNotBlank(reqDTO.getStatus())){
			sql.andStatusEqualTo(reqDTO.getStatus());
		}
		//店铺id
		if(reqDTO.getShopId()!=null){
			sql.andShopIdEqualTo(reqDTO.getShopId());
		}
		//所属人
		if(reqDTO.getCreateStaff()!=null){
			sql.andCreateStaffEqualTo(reqDTO.getCreateStaff());
		}
		groupCriteria.setLimitClauseCount(reqDTO.getPageSize());
		groupCriteria.setLimitClauseStart(reqDTO.getStartRowIndex());
		groupCriteria.setOrderByClause("update_time desc");
		return super.queryByPagination(reqDTO, groupCriteria, false, new PaginationCallback<ImPhrasebookGroup, ImPhrasebookGroupResDTO>() {

			@Override
			public List<ImPhrasebookGroup> queryDB(BaseCriteria arg0) {
				return imPhrasebookGroupMapper.selectByExample((ImPhrasebookGroupCriteria) arg0);
			}

			@Override
			public long queryTotal(BaseCriteria arg0) {
				return imPhrasebookGroupMapper.countByExample((ImPhrasebookGroupCriteria) arg0);
			}

			@Override
			public ImPhrasebookGroupResDTO warpReturnObject(ImPhrasebookGroup arg0) {
				ImPhrasebookGroupResDTO dto = new ImPhrasebookGroupResDTO();
				ObjectCopyUtil.copyObjValue(arg0, dto, null, false);
				return dto;
			}
		});
	}

	@Override
	public int deletePhrasebookGroupById(ImPhrasebookGroupReqDTO reqDTO) throws BusinessException {
		if(reqDTO.getId()==null){
			throw new BusinessException("id不能为空！");
		}
		int affected = imPhrasebookGroupMapper.deleteByPrimaryKey(reqDTO.getId());
		//删除分组下的常用语
		Long groupId = reqDTO.getId();
		ImPhrasebookItemCriteria criteria = new ImPhrasebookItemCriteria();
		ImPhrasebookItemCriteria.Criteria sql = criteria.createCriteria();
		sql.andGroupIdEqualTo(groupId);
		imPhrasebookItemMapper.deleteByExample(criteria);
		return affected;
	}

	@Override
	public Long savePhrasebookGroup(ImPhrasebookGroupReqDTO reqDTO) throws BusinessException {
		//入参校验
		if(StringUtil.isBlank(reqDTO.getGroupName().toString())){
			throw new BusinessException("分组名称不能为空！");
		}
		if(StringUtil.isBlank(reqDTO.getGroupClass())){
			throw new BusinessException("分组类型不能为空！");
		}
		if(ImConstants.PhraseBook.GROUP_CLASS_PRIVATE.equals(reqDTO.getGroupClass())
				&&reqDTO.getShopId()==null){
			throw new BusinessException("店铺id不能为空！");
		}
		ImPhrasebookGroup record = new ImPhrasebookGroup();
		ObjectCopyUtil.copyObjValue(reqDTO, record, null, false);
		record.setId(seqImPhrasebookGroupId.nextValue());
		//排序值处理
		if(reqDTO.getSortNo()==null){
			//设置排序值
			//先查同组记录排序最大值
			ImPhrasebookGroupCriteria findCriteria = new ImPhrasebookGroupCriteria();
			ImPhrasebookGroupCriteria.Criteria sql = findCriteria.createCriteria();
			sql.andGroupClassEqualTo(reqDTO.getGroupClass());//分组类型
			sql.andShopIdEqualTo(reqDTO.getShopId());
			//个人分组
			if(ImConstants.PhraseBook.GROUP_CLASS_PRIVATE.equals(reqDTO.getGroupClass())){
				sql.andCreateStaffEqualTo(reqDTO.getStaff().getId());
			}
			findCriteria.setOrderByClause("sort_no desc");//按sort_no降序
			List<ImPhrasebookGroup> listFind = imPhrasebookGroupMapper.selectByExample(findCriteria);
			if(CollectionUtils.isEmpty(listFind)){
				record.setSortNo(1L);//默认排序值为1
			}else{
				Long sortMax = listFind.get(0).getSortNo();
				record.setSortNo(sortMax+1);//加1作为新记录的排序值
			}
		}
		//记录状态
		if(StringUtil.isBlank(reqDTO.getStatus())){
			record.setStatus(ImConstants.STATE_1);//缺省为1
		}
		Long staffId = reqDTO.getStaff().getId();
		Timestamp optTime = DateUtil.getSysDate();
		record.setCreateStaff(staffId);
		record.setCreateTime(optTime);
		record.setUpdateStaff(staffId);
		record.setUpdateTime(optTime);
		imPhrasebookGroupMapper.insertSelective(record);
		return record.getId();
	}

	@Override
	public int updatePhrasebookGroupById(ImPhrasebookGroupReqDTO reqDTO) throws BusinessException {
		if(reqDTO.getId()==null){
			throw new BusinessException("id不能为空！");
		}
		ImPhrasebookGroup record = new ImPhrasebookGroup();
		ObjectCopyUtil.copyObjValue(reqDTO, record, null, false);
		record.setGroupClass(null);//分组类型不允许修改
		record.setUpdateStaff(reqDTO.getStaff().getId());
		record.setUpdateTime(DateUtil.getSysDate());
		int affected = imPhrasebookGroupMapper.updateByPrimaryKeySelective(record);
		return affected;
	}

	@Override
	public ImPhrasebookGroupResDTO queryPhrasebookGroupById(ImPhrasebookGroupReqDTO reqDTO) throws BusinessException {
		if(reqDTO.getId()==null){
			throw new BusinessException("id不能为空！");
		}
		ImPhrasebookGroup select = imPhrasebookGroupMapper.selectByPrimaryKey(reqDTO.getId());
		ImPhrasebookGroupResDTO res = new ImPhrasebookGroupResDTO();
		ObjectCopyUtil.copyObjValue(select, res, null, false);
		
		return res;
	}

}
