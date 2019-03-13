package com.zengshi.ecp.im.service.common.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.im.dao.mapper.common.ofUserMapper;
import com.zengshi.ecp.im.dao.model.ofUser;
import com.zengshi.ecp.im.service.common.interfaces.IOpenFireUserSV;
import com.zengshi.ecp.im.service.util.Blowfish;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;

/**
 * 获取用户关系
 * 
 * @author wangbh
 * 
 */
public class OpenFireUserSVImpl extends GeneralSQLSVImpl implements IOpenFireUserSV {

	private static String MODULE = OpenFireUserSVImpl.class.getName();
	
	@Resource
	private ofUserMapper ofUserMapper;
	

	@Override
	public int addOfUser(String userName, String passWord) throws BusinessException {
		BaseSysCfgRespDTO baseSysCfgRespDTO = SysCfgUtil.fetchSysCfg("IM_OPENFIRE_PASSWORD_KEY");
		Blowfish _encoder=new Blowfish(baseSysCfgRespDTO.getParaValue());  
		String _password = _encoder.encryptString(passWord);
		ofUser ofUser = new ofUser();
		ofUser.setUsername(userName);
		ofUser.setEncryptedpassword(_password);
		ofUser.setCreationdate(String.format("00%d", System.currentTimeMillis()));
		ofUser.setModificationdate(String.format("00%d", System.currentTimeMillis()));
		int i  = ofUserMapper.insertSelective(ofUser);
		return i;
	}



}
