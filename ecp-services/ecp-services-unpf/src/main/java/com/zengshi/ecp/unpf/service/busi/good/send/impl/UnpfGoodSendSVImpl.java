package com.zengshi.ecp.unpf.service.busi.good.send.impl;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.unpf.dao.mapper.busi.UnpfGdsSendMapper;
import com.zengshi.ecp.unpf.dao.model.UnpfGdsSend;
import com.zengshi.ecp.unpf.dao.model.UnpfGdsSendCriteria;
import com.zengshi.ecp.unpf.dao.model.UnpfGdsSendCriteria.Criteria;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.*;
import com.zengshi.ecp.unpf.dubbo.interfaces.auth.IUnpfShopAuthRSV;
import com.zengshi.ecp.unpf.service.busi.good.send.interfaces.IUnpfGdsUnsendSV;
import com.zengshi.ecp.unpf.service.busi.good.send.interfaces.IUnpfGoodSendSV;
import com.zengshi.ecp.unpf.service.busi.good.send.interfaces.IUnpfSendLogSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.paas.validator.group.New;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
* @author  lisp: 
* @date 创建时间：2016年11月17日 下午2:57:07 
* @version 1.0 
* @parameter  
* @since  
* @return  */
public class UnpfGoodSendSVImpl extends GeneralSQLSVImpl implements IUnpfGoodSendSV {

	@Resource
	private UnpfGdsSendMapper unpfGdsSendMapper;//
	
	@Resource(name="seq_unpf_gds_send_id")
	private PaasSequence seq_unpf_gds_send_id;
	
	@Resource
	private IUnpfGdsUnsendSV unpfGdsUnsendSV;
	
	@Resource
	private IUnpfSendLogSV unpfSendLogSV;

	@Resource
	private IUnpfShopAuthRSV unpfShopAuthRSV;

	
	/**
	* 查询商品的推送结果
	* 
	* @author lisp
	* @param
	* @return 
	* @throws BusinessException
	*/
	@Override
	public List<UnpfGdsSend> querySends(UnpfGdsSendReqDTO unpfGdsSendReqDTO) throws BusinessException {
		UnpfGdsSendCriteria example = new UnpfGdsSendCriteria();
		Criteria cr = example.createCriteria();
		if(StringUtil.isNotEmpty(unpfGdsSendReqDTO.getShopId())){
			cr.andShopIdEqualTo(unpfGdsSendReqDTO.getShopId());
		}
		cr.andGdsIdEqualTo(unpfGdsSendReqDTO.getGdsId());
		
		if(unpfGdsSendReqDTO.getShopAuthId()!=null){
			cr.andShopAuthIdEqualTo(unpfGdsSendReqDTO.getShopAuthId());
		}
		if(StringUtil.isNotEmpty(unpfGdsSendReqDTO.getPlatType())){
			cr.andPlatTypeEqualTo(unpfGdsSendReqDTO.getPlatType());
		}
		List<UnpfGdsSend> sendList = unpfGdsSendMapper.selectByExample(example);
	    return sendList;
	}
	
	/**
	* 查询商品的推送结果
	* 
	* @author lisp
	* @param
	* @return 
	* @throws BusinessException
	*/
	@Override
	public GdsSendBaseRespDTO querySendResultForGds(UnpfGdsSendReqDTO unpfGdsSendReqDTO) throws BusinessException {
		GdsSendBaseRespDTO gdsSendRespDTO =null;
		UnpfGdsSendCriteria example = new UnpfGdsSendCriteria();
		Criteria cr = example.createCriteria();
		if(StringUtil.isNotEmpty(unpfGdsSendReqDTO.getShopId())){
			cr.andShopIdEqualTo(unpfGdsSendReqDTO.getShopId());
		}
		cr.andGdsIdEqualTo(unpfGdsSendReqDTO.getGdsId());
		
		if(unpfGdsSendReqDTO.getShopAuthId()!=null){
			cr.andShopAuthIdEqualTo(unpfGdsSendReqDTO.getShopAuthId());
		}
		if(StringUtil.isNotEmpty(unpfGdsSendReqDTO.getPlatType())){
			cr.andPlatTypeEqualTo(unpfGdsSendReqDTO.getPlatType());
		}
		List<UnpfGdsSend> sendList = unpfGdsSendMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(sendList)){
			gdsSendRespDTO =new GdsSendBaseRespDTO();
			ObjectCopyUtil.copyObjValue(sendList.get(0), gdsSendRespDTO, "", false);
		}
		return gdsSendRespDTO;
	}
	/**
	* 商品编码+平台类型
	* 
	* @author huangjx
	* @param
	* @return 
	* @throws 
	*/
	public Long countGds(UnpfGdsSendReqDTO unpfGdsSendReqDTO) throws BusinessException{
		
		UnpfGdsSendCriteria example = new UnpfGdsSendCriteria();
		Criteria cr = example.createCriteria();
		cr.andShopIdEqualTo(unpfGdsSendReqDTO.getShopId());
		cr.andGdsIdEqualTo(unpfGdsSendReqDTO.getGdsId());
		cr.andPlatTypeEqualTo(unpfGdsSendReqDTO.getPlatType());
		cr.andShopAuthIdEqualTo(unpfGdsSendReqDTO.getShopAuthId());
		Long count= unpfGdsSendMapper.countByExample(example);
		
		return count;
	}
	/**
	* 推送成功 更新数据
	* 
	* @author huangjx
	* @param
	* @return 
	* @throws 
	*/
	public void saveSendSucess(UnpfGdsSendReqDTO unpfGdsSendReqDTO) throws BusinessException{
		/*更新状态t_unpf_gds_send 成功
		保存t_unpf_send_log
		移除t_unpf_gds_unsend
		保存t_unpf_gds_unsend_his*/
		unpfGdsSendReqDTO.setSendCnt(unpfGdsSendReqDTO.getSendCnt().add(BigDecimal.ONE));
		unpfGdsSendReqDTO.setStatus("1");
		//更新当前发送量和状态
		this.updateById(unpfGdsSendReqDTO);
		
		//保存log
		UnpfSendLogReqDTO unpfSendLogReqDTO=new UnpfSendLogReqDTO();
		ObjectCopyUtil.copyObjValue(unpfGdsSendReqDTO, unpfSendLogReqDTO, "", false);
		unpfSendLogReqDTO.setErrors("推送成功！");
		unpfSendLogReqDTO.setSendId(unpfGdsSendReqDTO.getId());
		unpfSendLogReqDTO.setCreateStaff(unpfSendLogReqDTO.getStaff().getId());
		unpfSendLogSV.saveGdsSendLog(unpfSendLogReqDTO);
		//待推送表 数据移除
		UnpfGdsUnsendReqDTO unpfGdsUnsendReqDTO=new UnpfGdsUnsendReqDTO();
		unpfGdsUnsendReqDTO.setGdsId(unpfGdsSendReqDTO.getGdsId());
		unpfGdsUnsendSV.deleteGdsUnsendByGdsId(unpfGdsUnsendReqDTO);
	}
	/**
	* 推送失败 更新数据
	* 
	* @author huangjx
	* @param
	* @return 
	* @throws 
	*/
	public void saveSendError(UnpfGdsSendReqDTO unpfGdsSendReqDTO,Boolean ifRemove) throws BusinessException{
		/*更新状态t_unpf_gds_send 失败
		保存t_unpf_send_log
		移除t_unpf_gds_unsend
		保存t_unpf_gds_unsend_his*/
		
		unpfGdsSendReqDTO.setSendCnt(unpfGdsSendReqDTO.getSendCnt().add(BigDecimal.ONE));
		if(StringUtils.isBlank(unpfGdsSendReqDTO.getStatus())){
			unpfGdsSendReqDTO.setStatus("0");
		}
		
		//更新当前发送量和状态
		this.updateById(unpfGdsSendReqDTO);
		
		//保存log
		UnpfSendLogReqDTO unpfSendLogReqDTO=new UnpfSendLogReqDTO();
		ObjectCopyUtil.copyObjValue(unpfGdsSendReqDTO, unpfSendLogReqDTO, "", false);
		unpfSendLogReqDTO.setSendId(unpfGdsSendReqDTO.getId());
		unpfSendLogReqDTO.setCreateStaff(unpfSendLogReqDTO.getStaff().getId());
		unpfSendLogSV.saveGdsSendLog(unpfSendLogReqDTO);
		
		//待推送表 数据移除
		if(ifRemove){
			UnpfGdsUnsendReqDTO unpfGdsUnsendReqDTO=new UnpfGdsUnsendReqDTO();
			unpfGdsUnsendReqDTO.setGdsId(unpfGdsSendReqDTO.getGdsId());
			unpfGdsUnsendSV.deleteGdsUnsendByGdsId(unpfGdsUnsendReqDTO);
		}
	}
	
	/**
	* 更新数据
	* 
	* @author huangjx
	* @param
	* @return 
	* @throws 
	*/
	public int updateById(UnpfGdsSendReqDTO unpfGdsSendReqDTO) throws BusinessException{
		UnpfGdsSend record=new UnpfGdsSend();
        ObjectCopyUtil.copyObjValue(unpfGdsSendReqDTO, record, "", false);	
		int count= unpfGdsSendMapper.updateByPrimaryKeySelective(record);
		return count;
	}
	
	/**
	* save数据
	* 
	* @author huangjx
	* @param
	* @return 
	* @throws 
	*/
	public Long save(UnpfGdsSendReqDTO unpfGdsSendReqDTO) throws BusinessException{
		UnpfGdsSend unpfGdsSend = new UnpfGdsSend();
		ObjectCopyUtil.copyObjValue(unpfGdsSendReqDTO, unpfGdsSend, null, false);
		
		if(StringUtil.isEmpty(unpfGdsSend.getCreateStaff())){
			unpfGdsSend.setCreateStaff(unpfGdsSendReqDTO.getStaff().getId());
		}
		if(StringUtil.isEmpty(unpfGdsSend.getCreateTime())){
			unpfGdsSend.setCreateTime(DateUtil.getSysDate());
		}
		if(unpfGdsSend.getSendCnt()==null){
			unpfGdsSend.setSendCnt(BigDecimal.ZERO);
		}
		unpfGdsSend.setId(seq_unpf_gds_send_id.nextValue());
		unpfGdsSendMapper.insert(unpfGdsSend);
		return unpfGdsSend.getId();
	}
	@Override
	public PageResponseDTO<UnpfGdsSendLogRespDTO> querySendLogForPage(UnpfGdsSendReqDTO unpfGdsSendReqDTO)
			throws BusinessException {
		UnpfGdsSendCriteria example = new UnpfGdsSendCriteria();
		example.setLimitClauseCount(unpfGdsSendReqDTO.getPageSize());
		example.setLimitClauseStart(unpfGdsSendReqDTO.getStartRowIndex());
		Criteria cr = example.createCriteria();
		if(StringUtil.isNotEmpty(unpfGdsSendReqDTO.getStatus())){
			cr.andStatusEqualTo(unpfGdsSendReqDTO.getStatus());
		}
		if(StringUtil.isNotEmpty(unpfGdsSendReqDTO.getPlatType())){
			cr.andPlatTypeEqualTo(unpfGdsSendReqDTO.getPlatType());
		}
		if(StringUtil.isNotEmpty(unpfGdsSendReqDTO.getShopId())){
			cr.andShopIdEqualTo(unpfGdsSendReqDTO.getShopId());
		}
		if(StringUtil.isNotEmpty(unpfGdsSendReqDTO.getGdsId())){
			cr.andGdsIdEqualTo(unpfGdsSendReqDTO.getGdsId());
		}
		return super.queryByPagination(unpfGdsSendReqDTO, example, true, new PaginationCallback<UnpfGdsSend, UnpfGdsSendLogRespDTO>() {

			@Override
			public List<UnpfGdsSend> queryDB(BaseCriteria example) {
				return unpfGdsSendMapper.selectByExample((UnpfGdsSendCriteria)example);
			}

			@Override
			public long queryTotal(BaseCriteria example) {
				return unpfGdsSendMapper.countByExample((UnpfGdsSendCriteria)example);
			}

			@Override
			public UnpfGdsSendLogRespDTO warpReturnObject(UnpfGdsSend t) {
				UnpfGdsSendLogRespDTO unpfGdsSendLogRespDTO = new UnpfGdsSendLogRespDTO();
				ObjectCopyUtil.copyObjValue(t, unpfGdsSendLogRespDTO, null, false);
				return unpfGdsSendLogRespDTO;
			}
		});
	}

	@Override
	public List<UnpfGdsSend> queryGdsSendByOuterId(UnpfGdsSendReqDTO unpfGdsSendReqDTO) throws BusinessException {
		UnpfGdsSendCriteria example = new UnpfGdsSendCriteria();
		Criteria cr = example.createCriteria();
		cr.andPlatTypeEqualTo(unpfGdsSendReqDTO.getPlatType());
		cr.andOutGdsIdEqualTo(unpfGdsSendReqDTO.getOutGdsId());
		List<UnpfGdsSend> sendList = unpfGdsSendMapper.selectByExample(example);
		return sendList;
	}

	public List<UnpfGdsSend> queryGdsSendedByOuterId(UnpfGdsSendReqDTO unpfGdsSendReqDTO) throws BusinessException {
		UnpfGdsSendCriteria example = new UnpfGdsSendCriteria();
		Criteria cr = example.createCriteria();
		cr.andPlatTypeEqualTo(unpfGdsSendReqDTO.getPlatType());
		cr.andOutGdsIdEqualTo(unpfGdsSendReqDTO.getOutGdsId());
		cr.andShopIdEqualTo(unpfGdsSendReqDTO.getShopId());
		cr.andGdsIdNotEqualTo(unpfGdsSendReqDTO.getGdsId());
		List<UnpfGdsSend> sendList = unpfGdsSendMapper.selectByExample(example);
		return sendList;
	}

	@Override
	public void updateGdsSendSubmit(UnpfGdsSendReqDTO unpfGdsSendReqDTO) throws BusinessException {
		UnpfShopAuthReqDTO unpfShopAuthReqDTO = new UnpfShopAuthReqDTO();
		unpfShopAuthReqDTO.setShopId(unpfGdsSendReqDTO.getShopId());
		unpfShopAuthReqDTO.setPlatType(unpfGdsSendReqDTO.getPlatType());
		PageResponseDTO<UnpfShopAuthRespDTO> resp = unpfShopAuthRSV.queryPlatType4ShopForPage(unpfShopAuthReqDTO);
		if(resp.getResult() != null && CollectionUtils.isNotEmpty(resp.getResult())){
			unpfGdsSendReqDTO.setShopAuthId(resp.getResult().get(0).getId());
		}

		UnpfGdsSendReqDTO sendReqDTO1 = new UnpfGdsSendReqDTO();
		sendReqDTO1.setShopId(unpfGdsSendReqDTO.getShopId());
		sendReqDTO1.setPlatType(unpfGdsSendReqDTO.getPlatType());
		sendReqDTO1.setGdsId(unpfGdsSendReqDTO.getGdsId());
		sendReqDTO1.setOutGdsId(unpfGdsSendReqDTO.getOutGdsId());
		List<UnpfGdsSend> unpfGdsSends =  queryGdsSendedByOuterId(sendReqDTO1);
		if(CollectionUtils.isNotEmpty(unpfGdsSends)){
			throw new BusinessException("unpf.100030",new String[]{"商品ID【"+unpfGdsSendReqDTO.getOutGdsId()+"】已经与人卫商城的商品【"+unpfGdsSends.get(0).getGdsId()+"】有对应关系，对应关系不允许重复，请重新核对！"});
		}

		UnpfGdsSendReqDTO sendReqDTO = new UnpfGdsSendReqDTO();
		sendReqDTO.setShopId(unpfGdsSendReqDTO.getShopId());
		sendReqDTO.setPlatType(unpfGdsSendReqDTO.getPlatType());
		sendReqDTO.setGdsId(unpfGdsSendReqDTO.getGdsId());


		List<UnpfGdsSend> unpfGdsSendList = querySends(sendReqDTO);
		if(CollectionUtils.isEmpty(unpfGdsSendList)){  //未推送过的数据
			if(StringUtil.isBlank(unpfGdsSendReqDTO.getOutGdsId())){
				throw new BusinessException("unpf.100030",new String[]{"未推送过的商品无需修改为未推送"});
			}else {  //改为已推送
				unpfGdsSendReqDTO.setAction("1");
				unpfGdsSendReqDTO.setStatus("1");
				unpfGdsSendReqDTO.setSendCnt(new BigDecimal(1));
				this.save(unpfGdsSendReqDTO);

				//保存log
				UnpfSendLogReqDTO unpfSendLogReqDTO=new UnpfSendLogReqDTO();
				ObjectCopyUtil.copyObjValue(unpfGdsSendReqDTO, unpfSendLogReqDTO, "", false);
				unpfSendLogReqDTO.setAction("1");
				unpfSendLogReqDTO.setErrors("手工修改为已推送,新的对应编号："+unpfGdsSendReqDTO.getOutGdsId());
				unpfSendLogReqDTO.setCreateStaff(unpfGdsSendReqDTO.getStaff().getId());
				unpfSendLogSV.saveGdsSendLog(unpfSendLogReqDTO);
			}
		} else {  //已推送过的数据

			if(StringUtil.isBlank(unpfGdsSendReqDTO.getOutGdsId())) { //改为未推送
				if(!("1".equals(unpfGdsSendList.get(0).getStatus()))){ //未推送成功
					throw new BusinessException("unpf.100030",new String[]{"未推送成功的商品无需修改为未推送"});
				}
				unpfGdsSendReqDTO.setId(unpfGdsSendList.get(0).getId());
				unpfGdsSendReqDTO.setAction("9");
				unpfGdsSendReqDTO.setStatus("0");
				unpfGdsSendReqDTO.setSendCnt(new BigDecimal(1));
				unpfGdsSendReqDTO.setOutGdsId("");
				unpfGdsSendReqDTO.setUpdateTime(DateUtil.getSysDate());
				unpfGdsSendReqDTO.setUpdateStaff(unpfGdsSendReqDTO.getStaff().getId());
				this.updateById(unpfGdsSendReqDTO);
				//保存log
				UnpfSendLogReqDTO unpfSendLogReqDTO=new UnpfSendLogReqDTO();
				ObjectCopyUtil.copyObjValue(unpfGdsSendReqDTO, unpfSendLogReqDTO, "", false);
				unpfSendLogReqDTO.setAction("1");
				unpfSendLogReqDTO.setErrors("手工修改为未推送,原来的对应关系为："+unpfGdsSendList.get(0).getOutGdsId());
				unpfSendLogReqDTO.setCreateStaff(unpfGdsSendReqDTO.getStaff().getId());
				unpfSendLogSV.saveGdsSendLog(unpfSendLogReqDTO);
			} else { //对应商品编码修改
				unpfGdsSendReqDTO.setId(unpfGdsSendList.get(0).getId());
				unpfGdsSendReqDTO.setStatus("1");
				unpfGdsSendReqDTO.setUpdateTime(DateUtil.getSysDate());
				unpfGdsSendReqDTO.setUpdateStaff(unpfGdsSendReqDTO.getStaff().getId());
				this.updateById(unpfGdsSendReqDTO);
				//保存log
				UnpfSendLogReqDTO unpfSendLogReqDTO=new UnpfSendLogReqDTO();
				ObjectCopyUtil.copyObjValue(unpfGdsSendReqDTO, unpfSendLogReqDTO, "", false);
				unpfSendLogReqDTO.setAction("1");
				if(!("1".equals(unpfGdsSendList.get(0).getStatus()))){ //未推送成功
					unpfSendLogReqDTO.setErrors("手工修改为已推送,新的对应编号："+unpfGdsSendReqDTO.getOutGdsId());
				} else {
					unpfSendLogReqDTO.setErrors("手工修改对应关系,由原来的："+unpfGdsSendList.get(0).getOutGdsId()+"修改为："+unpfGdsSendReqDTO.getOutGdsId());
				}
				unpfSendLogReqDTO.setCreateStaff(unpfGdsSendReqDTO.getStaff().getId());
				unpfSendLogSV.saveGdsSendLog(unpfSendLogReqDTO);
			}
		}
	}
}


