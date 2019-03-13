package com.zengshi.ecp.unpf.service.busi.token.impl;

import java.sql.Timestamp;
import javax.annotation.Resource;
import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.TokenRespDTO;
import com.zengshi.ecp.aip.third.dubbo.interfaces.ITokenRSV;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.unpf.dao.mapper.busi.UnpfShopAuthMapper;
import com.zengshi.ecp.unpf.dao.model.UnpfShopAuth;
import com.zengshi.ecp.unpf.dao.model.UnpfShopAuthCriteria;
import com.zengshi.ecp.unpf.dao.model.UnpfShopAuthCriteria.Criteria;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthRespDTO;
import com.zengshi.ecp.unpf.service.busi.auth.interfaces.IUnpfShopAuthSV;
import com.zengshi.ecp.unpf.service.busi.token.interfaces.IUnpfTokenSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
* @author  huangjx: 
* @date 创建时间：2016年11月8日 上午9:30:20 
* @version 1.0 
* 
*  */
public class UnpfTokenSVImpl extends GeneralSQLSVImpl implements IUnpfTokenSV {

	@Resource
	private IUnpfShopAuthSV unpfShopAuthSV;
	
	@Resource
	private UnpfShopAuthMapper unpfShopAuthMapper;
	
	@Resource
	private ITokenRSV tokenRSV;
	/**
	* 对接平台 token授权
	* 
	* @author huangjx
	* @param BaseShopAuthReqDTO
	* @return 
	* @throws 
	*/
	@Override
	public void createToken(UnpfShopAuthReqDTO unpfShopAuthReqDTO) throws BusinessException{
		
		//1.调用aip生成 accesstoken json串
		//2.调用dubbo 更新授权码 和token  
		
		if(unpfShopAuthReqDTO==null || unpfShopAuthReqDTO.getId()==null){
			throw new BusinessException("unpf.100003");
		}
		if(unpfShopAuthReqDTO==null || StringUtil.isBlank(unpfShopAuthReqDTO.getAuthCode())){
			throw new BusinessException("unpf.100004");
		}
		UnpfShopAuthReqDTO req=new UnpfShopAuthReqDTO();
		req.setId(unpfShopAuthReqDTO.getId());
		//查询店铺授权基本信息
		UnpfShopAuthRespDTO resp=unpfShopAuthSV.queryPlatType4ShopById(req);
		
		if(resp==null || resp.getId()==null){
			throw new BusinessException("unpf.100005");
		}
		
		BaseShopAuthReqDTO baseShopAuthReqDTO=new BaseShopAuthReqDTO();
		
		ObjectCopyUtil.copyObjValue(resp, baseShopAuthReqDTO, null, false);
		
		//组织授权相关地址
		//数据字典获得
		BaseSysCfgRespDTO  sysDTO=SysCfgUtil.fetchSysCfg(resp.getPlatType()+"_auth_url");
		if(sysDTO!=null && StringUtil.isNotEmpty(sysDTO.getParaValue())){
			baseShopAuthReqDTO.setAuthUrl(sysDTO.getParaValue());
		} 
		//数据字典获得
		BaseSysCfgRespDTO  sysDTO1=SysCfgUtil.fetchSysCfg(resp.getPlatType()+"_redirect_uri");
		if(sysDTO!=null && StringUtil.isNotEmpty(sysDTO1.getParaValue())){
			baseShopAuthReqDTO.setRedirectUri(sysDTO1.getParaValue()+"/"+unpfShopAuthReqDTO.getId());
		} 
		
		BaseSysCfgRespDTO  sysDTO2=SysCfgUtil.fetchSysCfg(resp.getPlatType()+"_token_url");
		if(sysDTO2!=null && StringUtil.isNotEmpty(sysDTO2.getParaValue())){
			baseShopAuthReqDTO.setTokenUrl(sysDTO2.getParaValue());
		} 
		
    	baseShopAuthReqDTO.setAuthCode(unpfShopAuthReqDTO.getAuthCode());
    	baseShopAuthReqDTO.setAuthId(req.getId());
		//调用平台服务
		TokenRespDTO t=tokenRSV.fetchToken(baseShopAuthReqDTO);
		
		//更新表
		UnpfShopAuth unpfShopAuth =  new UnpfShopAuth();
		
		unpfShopAuth.setAccessToken(t.getAccessToken());
		unpfShopAuth.setAuthCode(baseShopAuthReqDTO.getAuthCode());
		unpfShopAuth.setAuthUrl(baseShopAuthReqDTO.getAuthUrl());
		if(t.getExpiredTime()!=null){
			unpfShopAuth.setExpiredTimeA(new Timestamp(t.getExpiredTime().getTime()));
		}
		if(t.getReexpiredTime()!=null){
			unpfShopAuth.setExpiredTimeR(new Timestamp(t.getReexpiredTime().getTime()));
		}
		
		unpfShopAuth.setRedirectUri(baseShopAuthReqDTO.getRedirectUri());
		unpfShopAuth.setRefreshToken(t.getRefreshToken());
		unpfShopAuth.setUpdateStaff(unpfShopAuthReqDTO.getStaff().getId());
		unpfShopAuth.setUpdateTime(DateUtil.getSysDate());
		UnpfShopAuthCriteria example = new UnpfShopAuthCriteria();
		Criteria cr = example.createCriteria();
		cr.andIdEqualTo(unpfShopAuthReqDTO.getId());
		
		unpfShopAuthMapper.updateByExampleSelective(unpfShopAuth, example);		
	}
}


