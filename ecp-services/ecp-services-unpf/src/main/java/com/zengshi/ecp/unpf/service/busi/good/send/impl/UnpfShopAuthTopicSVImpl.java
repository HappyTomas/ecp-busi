package com.zengshi.ecp.unpf.service.busi.good.send.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.annotation.Resource;

import org.drools.compiler.lang.api.CEDescrBuilder;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.unpf.dao.mapper.busi.UnpfShopAuthTopicMapper;
import com.zengshi.ecp.unpf.dao.model.UnpfShopAuth;
import com.zengshi.ecp.unpf.dao.model.UnpfShopAuthTopic;
import com.zengshi.ecp.unpf.dao.model.UnpfShopAuthTopicCriteria;
import com.zengshi.ecp.unpf.dao.model.UnpfShopAuthTopicCriteria.Criteria;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfShopAuthTopicReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfShopAuthTopicRespDTO;
import com.zengshi.ecp.unpf.service.busi.good.send.interfaces.IUnpfShopAuthTopicSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

import kafka.api.TopicMetadataRequest;

/**
* @author  lisp: 
* @date 创建时间：2016年11月19日 上午11:05:30 
* @version 1.0 
* @parameter  
* @since  
* @return  */
public class UnpfShopAuthTopicSVImpl extends GeneralSQLSVImpl implements IUnpfShopAuthTopicSV {
	
	@Resource
	private UnpfShopAuthTopicMapper unpfShopAithTopicMapper;
	
	@Resource(name="seq_unpf_shop_auth_topic_id")
	private PaasSequence seq_unpf_shop_auth_topic_id;

	@Override
	public void saveShopAuthTopic(UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO) throws BusinessException {

		UnpfShopAuthTopic unpfShopAuthTopic = new UnpfShopAuthTopic();
		ObjectCopyUtil.copyObjValue(unpfShopAuthTopicReqDTO, unpfShopAuthTopic, null, false);
		if(StringUtil.isEmpty(unpfShopAuthTopic.getCreateStaff())){
			unpfShopAuthTopic.setCreateStaff(unpfShopAuthTopicReqDTO.getStaff().getId());
		}
		if(StringUtil.isEmpty(unpfShopAuthTopic.getCreateTime())){
			unpfShopAuthTopic.setCreateTime(DateUtil.getSysDate());
		}
		unpfShopAuthTopic.setId(seq_unpf_shop_auth_topic_id.nextValue());
		unpfShopAithTopicMapper.insert(unpfShopAuthTopic);
	}

	@Override
	public UnpfShopAuthTopicRespDTO queryShopAuthTopicById(UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO)
			throws BusinessException {
		UnpfShopAuthTopic unpfShopAuthTopic = unpfShopAithTopicMapper.selectByPrimaryKey(unpfShopAuthTopicReqDTO.getId());
		if(StringUtil.isEmpty(unpfShopAuthTopic)){
			return null;
		}
		UnpfShopAuthTopicRespDTO unpfShopAuthTopicRespDTO = new UnpfShopAuthTopicRespDTO();
		ObjectCopyUtil.copyObjValue(unpfShopAuthTopic, unpfShopAuthTopicRespDTO, null, false);
		return unpfShopAuthTopicRespDTO;
	}

	@Override
	public PageResponseDTO<UnpfShopAuthTopicRespDTO> queryShopAuthTopicForPage(
			UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO) throws BusinessException {
		UnpfShopAuthTopicCriteria example = new UnpfShopAuthTopicCriteria();
		example.setLimitClauseCount(unpfShopAuthTopicReqDTO.getPageSize());
		example.setLimitClauseStart(unpfShopAuthTopicReqDTO.getStartRowIndex());
		example.setOrderByClause("id");
		this.initShopAuthTopic(example, unpfShopAuthTopicReqDTO);
		return super.queryByPagination(unpfShopAuthTopicReqDTO, example, true,
				new PaginationCallback<UnpfShopAuthTopic, UnpfShopAuthTopicRespDTO>() {

					@Override
					public List<UnpfShopAuthTopic> queryDB(BaseCriteria example) {
						return unpfShopAithTopicMapper.selectByExample((UnpfShopAuthTopicCriteria)example);
					}

					@Override
					public long queryTotal(BaseCriteria example) {
						return unpfShopAithTopicMapper.countByExample((UnpfShopAuthTopicCriteria)example);
					}

					@Override
					public UnpfShopAuthTopicRespDTO warpReturnObject(UnpfShopAuthTopic t) {
						UnpfShopAuthTopicRespDTO unpfShopAuthTopicRespDTO = new UnpfShopAuthTopicRespDTO();
						ObjectCopyUtil.copyObjValue(t, unpfShopAuthTopicRespDTO, null, false);
						return unpfShopAuthTopicRespDTO;
					}
					@Override
		            public List<Comparator<UnpfShopAuthTopic>> defineComparators() {
		                List<Comparator<UnpfShopAuthTopic>> lst = new ArrayList<Comparator<UnpfShopAuthTopic>>();
		                lst.add(new Comparator<UnpfShopAuthTopic>() {
		                    @Override
		                    public int compare(UnpfShopAuthTopic o1, UnpfShopAuthTopic o2) {
		                        if (o1.getId() > o2.getId()) {
		                            return -1;
		                        } else if (o1.getId().equals(o2.getId())) {
		                            return 0;
		                        } else {
		                            return 1;
		                        }
		                    }
		                });
		                return lst;
		            }
		});
	}

	@Override
	public void updateShopAuthTopicByExample(UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO) throws BusinessException {

		UnpfShopAuthTopicCriteria example = new UnpfShopAuthTopicCriteria();
		Criteria cr = example.createCriteria();
		UnpfShopAuthTopic unpfShopAuthTopic = new UnpfShopAuthTopic();
		if(StringUtil.isNotEmpty(unpfShopAuthTopicReqDTO.getShopAuthId())){
			cr.andShopAuthIdEqualTo(unpfShopAuthTopicReqDTO.getShopAuthId());
		}
		if(StringUtil.isNotEmpty(unpfShopAuthTopicReqDTO.getId())){
			cr.andIdEqualTo(unpfShopAuthTopicReqDTO.getId());
		}
		if(StringUtil.isNotBlank(unpfShopAuthTopicReqDTO.getNick())){
			unpfShopAuthTopic.setNick(unpfShopAuthTopicReqDTO.getNick());
		}
		if(StringUtil.isNotBlank(unpfShopAuthTopicReqDTO.getTopic())){
			unpfShopAuthTopic.setTopic(unpfShopAuthTopicReqDTO.getTopic());
		}
		if(StringUtil.isNotEmpty(unpfShopAuthTopicReqDTO.getStatus())){
			unpfShopAuthTopic.setStatus(unpfShopAuthTopicReqDTO.getStatus());
		}
		unpfShopAuthTopic.setUpdateStaff(unpfShopAuthTopicReqDTO.getStaff().getId());
		unpfShopAuthTopic.setUpdateTime(DateUtil.getSysDate());
		unpfShopAithTopicMapper.updateByExampleSelective(unpfShopAuthTopic, example);
	}

	
	private void initShopAuthTopic(UnpfShopAuthTopicCriteria example,UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO){
		
		Criteria cr = example.createCriteria();
		if(StringUtil.isNotEmpty(unpfShopAuthTopicReqDTO.getShopAuthId())){
			cr.andShopAuthIdEqualTo(unpfShopAuthTopicReqDTO.getShopAuthId());
		}
		if(StringUtil.isNotBlank(unpfShopAuthTopicReqDTO.getPlatType())){
			cr.andPlatTypeEqualTo(unpfShopAuthTopicReqDTO.getPlatType());
		}
		if(StringUtil.isNotEmpty(unpfShopAuthTopicReqDTO.getShopId())){
			cr.andShopIdEqualTo(unpfShopAuthTopicReqDTO.getShopId());
		}
		if(StringUtil.isNotBlank(unpfShopAuthTopicReqDTO.getTopic())){
			cr.andTopicEqualTo(unpfShopAuthTopicReqDTO.getTopic());
		}
		if(StringUtil.isNotBlank(unpfShopAuthTopicReqDTO.getNick())){
			cr.andNickEqualTo(unpfShopAuthTopicReqDTO.getNick());
		}
		if(StringUtil.isNotBlank(unpfShopAuthTopicReqDTO.getUserId())){
			cr.andUserIdEqualTo(unpfShopAuthTopicReqDTO.getUserId());
		}
		if(StringUtil.isNotBlank(unpfShopAuthTopicReqDTO.getIfValid())){
			cr.andIfValidEqualTo(unpfShopAuthTopicReqDTO.getIfValid());
		}
		if(StringUtil.isNotBlank(unpfShopAuthTopicReqDTO.getStatus())){
			cr.andStatusEqualTo(unpfShopAuthTopicReqDTO.getStatus());
		}
		if(StringUtil.isNotEmpty(unpfShopAuthTopicReqDTO.getCreateStaff())){
			cr.andCreateStaffEqualTo(unpfShopAuthTopicReqDTO.getCreateStaff());
		}
	}

	@Override
	public UnpfShopAuthTopicReqDTO validShopAuthTopic(UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO) throws BusinessException {
		UnpfShopAuthTopicCriteria example = new UnpfShopAuthTopicCriteria();
		Criteria cr = example.createCriteria();
		cr.andShopAuthIdEqualTo(unpfShopAuthTopicReqDTO.getShopAuthId());
		List<UnpfShopAuthTopic> list = unpfShopAithTopicMapper.selectByExample(example);
		if(!CollectionUtils.isEmpty(list)){
			for(UnpfShopAuthTopic unpfShopAuthTopic : list) {
				if(unpfShopAuthTopic.getTopic().equalsIgnoreCase(unpfShopAuthTopicReqDTO.getTopic())){
					return unpfShopAuthTopicReqDTO;
				}
			}
		}
		return null;
	}

	@Override
	public List<UnpfShopAuthTopic> shopAuthTopicList(UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO)
			throws BusinessException {
		UnpfShopAuthTopicCriteria example = new UnpfShopAuthTopicCriteria();
		Criteria cr = example.createCriteria();
		cr.andShopAuthIdEqualTo(unpfShopAuthTopicReqDTO.getShopAuthId());
		
		return unpfShopAithTopicMapper.selectByExample(example);
	}
}


