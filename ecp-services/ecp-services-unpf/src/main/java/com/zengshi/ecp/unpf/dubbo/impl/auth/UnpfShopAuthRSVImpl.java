package com.zengshi.ecp.unpf.dubbo.impl.auth;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthRespDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.auth.IUnpfShopAuthRSV;
import com.zengshi.ecp.unpf.dubbo.util.UnpfConstants;
import com.zengshi.ecp.unpf.service.busi.auth.interfaces.IUnpfShopAuthSV;
import com.zengshi.paas.utils.StringUtil;

/**
* @author  lisp: 
* @date 创建时间：2016年11月8日 上午9:10:38 
* @version 1.0 
* Copyright (c) 2016 AI <br>
* */
public class UnpfShopAuthRSVImpl implements IUnpfShopAuthRSV {

	 @Resource
	 private IUnpfShopAuthSV unpfShopAuthSV;//unpfShopAuthSV

	@Override
	public int deletePlatType4Shop(UnpfShopAuthReqDTO unpfShopAuthReqDTO) throws BusinessException {
		if(StringUtil.isEmpty(unpfShopAuthReqDTO)){
			 //传入参数为空
            throw new BusinessException("unpf.100001");
		}
		if(StringUtil.isEmpty(unpfShopAuthReqDTO.getId())){
			 //传入参数id为空
            throw new BusinessException("unpf.100002");
		}
		return unpfShopAuthSV.deletePlatType4Shop(unpfShopAuthReqDTO);
	}

	@Override
	public int updatePlatType4Shop(UnpfShopAuthReqDTO unpfShopAuthReqDTO)  throws BusinessException{
		if(StringUtil.isEmpty(unpfShopAuthReqDTO)){
			 //传入参数为空
           throw new BusinessException("unpf.100001");
		}
		if(StringUtil.isEmpty(unpfShopAuthReqDTO.getId())){
			 //传入参数id为空
           throw new BusinessException("unpf.100002");
		}
		return unpfShopAuthSV.updatePlatType4Shop(unpfShopAuthReqDTO);
	}

	@Override
	public UnpfShopAuthRespDTO queryPlatType4ShopById(UnpfShopAuthReqDTO unpfShopAuthReqDTO)  throws BusinessException{
		if(StringUtil.isEmpty(unpfShopAuthReqDTO)){
			 //传入参数为空
          throw new BusinessException("unpf.100001");
		}
		if(StringUtil.isEmpty(unpfShopAuthReqDTO.getId())){
			 //传入参数id为空
          throw new BusinessException("unpf.100002");
		}
		return unpfShopAuthSV.queryPlatType4ShopById(unpfShopAuthReqDTO);
	}

	@Override
	public UnpfShopAuthReqDTO savePlatType4Shop(UnpfShopAuthReqDTO unpfShopAuthReqDTO)  throws BusinessException{
		if(StringUtil.isEmpty(unpfShopAuthReqDTO)){
			 //传入参数为空
          throw new BusinessException("unpf.100001");
		}
		//验证 店铺是否已经有效的授权 待实现 并且返回错误说明
		UnpfShopAuthReqDTO unpf = unpfShopAuthSV.validPlatType4Shop(unpfShopAuthReqDTO);
		if(StringUtil.isEmpty(unpf)){
			unpfShopAuthSV.savePlatType4Shop(unpfShopAuthReqDTO);
		}else{
			return unpf;
		}
		return null;
	}

	@Override
	public PageResponseDTO<UnpfShopAuthRespDTO> queryPlatType4ShopForPage(UnpfShopAuthReqDTO unpfShopAuthReqDTO)
			throws BusinessException {
		return unpfShopAuthSV.queryPlatType4ShopForPage(unpfShopAuthReqDTO);
	}

	@Override
	public List<String> queryPlatType4ShopByExample(UnpfShopAuthReqDTO unpfShopAuthReqDTO) throws BusinessException {
		if(StringUtil.isEmpty(unpfShopAuthReqDTO)){
			 //传入参数为空
         throw new BusinessException("unpf.100001");
		}
		if(StringUtil.isEmpty(unpfShopAuthReqDTO.getShopId())){
			 //传入参数为空
         throw new BusinessException("unpf.100003");
		}
		if(StringUtil.isEmpty(unpfShopAuthReqDTO.getStatus())){
			unpfShopAuthReqDTO.setStatus(UnpfConstants.PlatType4Shop.STATUS_1);
		}
		return unpfShopAuthSV.queryPlatType4ShopByExample(unpfShopAuthReqDTO);
	}
	
}


