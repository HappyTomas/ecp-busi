package com.zengshi.ecp.unpf.dubbo.impl.gdssend;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.ShopTopicReqDTO;
import com.zengshi.ecp.aip.third.dubbo.interfaces.IUserPermitRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dao.model.UnpfShopAuthTopic;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfShopAuthTopicReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfShopAuthTopicRespDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.gdssend.IUnpfShopAuthTopicRSV;
import com.zengshi.ecp.unpf.dubbo.util.UnpfConstants;
import com.zengshi.ecp.unpf.service.busi.auth.interfaces.IUnpfShopAuthSV;
import com.zengshi.ecp.unpf.service.busi.good.send.interfaces.IUnpfShopAuthTopicSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
* @author  lisp: 
* @date 创建时间：2016年11月19日 上午11:12:45 
* @version 1.0 
**/
public class UnpfShopAuthTopicRSVImpl implements IUnpfShopAuthTopicRSV {
	
	@Resource
	private IUnpfShopAuthTopicSV unpfShopAuthTopicSV;
	
	@Resource
	private IUserPermitRSV userPermitRSV;
	
	 @Resource
	 private IUnpfShopAuthSV unpfShopAuthSV;

	@Override
	public UnpfShopAuthTopicRespDTO saveShopAuthTopic(UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO) throws BusinessException {
		UnpfShopAuthTopicRespDTO respDTO=new UnpfShopAuthTopicRespDTO();
		if(unpfShopAuthTopicSV.validShopAuthTopic(unpfShopAuthTopicReqDTO)!=null){
			return respDTO;
		}
		if(unpfShopAuthTopicReqDTO==null){
			throw new BusinessException("unpf.100001");
		}
		if(StringUtil.isEmpty(unpfShopAuthTopicReqDTO.getShopAuthId())){
			throw new BusinessException("unpf.100014");
		}
		if(StringUtil.isEmpty(unpfShopAuthTopicReqDTO.getNick())){
			throw new BusinessException("unpf.100024");
		}
		if(StringUtil.isEmpty(unpfShopAuthTopicReqDTO.getTopic())){
			throw new BusinessException("unpf.100017");
		}
		if(StringUtil.isBlank(unpfShopAuthTopicReqDTO.getStatus())){
			throw new BusinessException("unpf.100018");
		}
		if(StringUtil.isEmpty(unpfShopAuthTopicReqDTO.getCreateTime())){
			unpfShopAuthTopicReqDTO.setCreateTime(DateUtil.getSysDate());
		}
		if(StringUtil.isEmpty(unpfShopAuthTopicReqDTO.getCreateStaff())){
			unpfShopAuthTopicReqDTO.setCreateStaff(unpfShopAuthTopicReqDTO.getStaff().getId());
		}
		
		ShopTopicReqDTO shopTopicReqDTO = new ShopTopicReqDTO();
		
		//初始化 授权基本信息
		UnpfShopAuthReqDTO q=new UnpfShopAuthReqDTO();
		q.setId(unpfShopAuthTopicReqDTO.getShopAuthId());
		UnpfShopAuthRespDTO  uppfAuth=unpfShopAuthSV.queryPlatType4ShopById(q);

		BaseShopAuthReqDTO baseShopAuthReqDTO = new BaseShopAuthReqDTO();

		ObjectCopyUtil.copyObjValue(uppfAuth, baseShopAuthReqDTO,
				null, false);

		baseShopAuthReqDTO.setAuthId(uppfAuth.getId());
		
		ObjectCopyUtil.copyObjValue(baseShopAuthReqDTO, shopTopicReqDTO,
				null, false);
		shopTopicReqDTO.setNick(unpfShopAuthTopicReqDTO.getNick());
		shopTopicReqDTO.setTopic(unpfShopAuthTopicReqDTO.getTopic());
		shopTopicReqDTO.setStatus(unpfShopAuthTopicReqDTO.getStatus());
		
		userPermitRSV.createUserPerimit(shopTopicReqDTO);
		unpfShopAuthTopicReqDTO.setPlatType(baseShopAuthReqDTO.getPlatType());
		unpfShopAuthTopicReqDTO.setShopId(baseShopAuthReqDTO.getShopId());
		unpfShopAuthTopicSV.saveShopAuthTopic(unpfShopAuthTopicReqDTO);
		return null;
	}

	@Override
	public UnpfShopAuthTopicRespDTO queryShopAuthTopicById(UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO)
			throws BusinessException {
		if(unpfShopAuthTopicReqDTO==null){
			throw new BusinessException("unpf.100001");
		}
		if(StringUtil.isEmpty(unpfShopAuthTopicReqDTO.getId())){
			throw new BusinessException("unpf.100002");
		}
		return unpfShopAuthTopicSV.queryShopAuthTopicById(unpfShopAuthTopicReqDTO);
	}

	@Override
	public PageResponseDTO<UnpfShopAuthTopicRespDTO> queryShopAuthTopicForPage(
			UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO) throws BusinessException {
		if(unpfShopAuthTopicReqDTO==null){
			unpfShopAuthTopicReqDTO = new UnpfShopAuthTopicReqDTO();
		}
		if(StringUtil.isEmpty(unpfShopAuthTopicReqDTO.getPageSize())){
			unpfShopAuthTopicReqDTO.setPageSize(UnpfConstants.PAGESIZE);
		}
		if(StringUtil.isEmpty(unpfShopAuthTopicReqDTO.getPageNo())){
			unpfShopAuthTopicReqDTO.setPageNo(UnpfConstants.PAGENO);
		}
		return unpfShopAuthTopicSV.queryShopAuthTopicForPage(unpfShopAuthTopicReqDTO);
	}

	@Override
	public void updateShopAuthTopicByExample(UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO) throws BusinessException {
		if(unpfShopAuthTopicReqDTO==null){
			throw new BusinessException("unpf.100001");
		}
        ShopTopicReqDTO shopTopicReqDTO = new ShopTopicReqDTO();
		//初始化 授权基本信息
		UnpfShopAuthReqDTO q=new UnpfShopAuthReqDTO();
		q.setId(unpfShopAuthTopicReqDTO.getShopAuthId());
		UnpfShopAuthRespDTO  uppfAuth=unpfShopAuthSV.queryPlatType4ShopById(q);

		BaseShopAuthReqDTO baseShopAuthReqDTO = new BaseShopAuthReqDTO();

		ObjectCopyUtil.copyObjValue(uppfAuth, baseShopAuthReqDTO,
				null, false);

		baseShopAuthReqDTO.setAuthId(uppfAuth.getId());
		
		ObjectCopyUtil.copyObjValue(baseShopAuthReqDTO, shopTopicReqDTO,
				null, false);
		
		shopTopicReqDTO.setNick(unpfShopAuthTopicReqDTO.getNick());
		shopTopicReqDTO.setTopic(unpfShopAuthTopicReqDTO.getTopic());
		shopTopicReqDTO.setStatus(unpfShopAuthTopicReqDTO.getStatus());
		userPermitRSV.createUserPerimit(shopTopicReqDTO);
		unpfShopAuthTopicSV.updateShopAuthTopicByExample(unpfShopAuthTopicReqDTO);
	}

	@Override
	public void closeShopAuthTopicByExample(UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO) throws BusinessException {
		if(unpfShopAuthTopicReqDTO==null){
			throw new BusinessException("unpf.100001");
		}
		ShopTopicReqDTO shopTopicReqDTO = new ShopTopicReqDTO();
		//shopTopicReqDTO.setShopAuthId(unpfShopAuthTopicReqDTO.getShopAuthId());
		ObjectCopyUtil.copyObjValue(unpfShopAuthTopicReqDTO, shopTopicReqDTO, null, false);
		List<UnpfShopAuthTopic> list = unpfShopAuthTopicSV.shopAuthTopicList(unpfShopAuthTopicReqDTO);
		if(!CollectionUtils.isEmpty(list)){
			List<String> strList = new ArrayList<String>();
			for (UnpfShopAuthTopic unpfShopAuthTopic : list) {
				if(!strList.contains(unpfShopAuthTopic.getNick())){
					strList.add(unpfShopAuthTopic.getNick());
					shopTopicReqDTO.setNick(unpfShopAuthTopic.getNick());
					userPermitRSV.cancelUserPerimit(shopTopicReqDTO);
				}
			}
		}
		unpfShopAuthTopicSV.updateShopAuthTopicByExample(unpfShopAuthTopicReqDTO);
	}
}


