package com.zengshi.ecp.unpf.dubbo.impl.msgSyc;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.msgSyc.UnpfMsgSycReqDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.msgSyc.IUnpfMsgSycRSV;
import com.zengshi.ecp.unpf.dubbo.util.UnpfConstants;
import com.zengshi.ecp.unpf.service.busi.msgSyc.interfaces.IUnpfMsgSycSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
* @author  lisp: 
* @date 创建时间：2016年11月15日 下午2:28:18 
* @version 1.0 
* @parameter  
* @since  
* @return  */
public class UnpfMsgSycRSVImpl implements IUnpfMsgSycRSV {

	private static final String MODULE = UnpfMsgSycRSVImpl.class.getName();
	
	@Resource
     private IUnpfMsgSycSV unpfMsgSycSV;
	 
	@Override
	public void saveUnpfMsgSyc(UnpfMsgSycReqDTO unpfMsgSycReqDTO) throws BusinessException{

		if(unpfMsgSycReqDTO==null||StringUtil.isEmpty(unpfMsgSycReqDTO.getPlatType())||StringUtil.isEmpty(unpfMsgSycReqDTO.getShopId())
				||StringUtil.isEmpty(unpfMsgSycReqDTO.getMsg())||StringUtil.isEmpty(unpfMsgSycReqDTO.getTradeType())){
			 //传入参数为空
            throw new BusinessException("unpf.100001");
		}
		if(StringUtil.isBlank(unpfMsgSycReqDTO.getStatus())){
			unpfMsgSycReqDTO.setStatus(UnpfConstants.MsgSYc.STATUS_1);
		}
		if(StringUtil.isEmpty(unpfMsgSycReqDTO.getCreateTime())){
			unpfMsgSycReqDTO.setCreateTime(DateUtil.getSysDate());
		}
		if(StringUtil.isEmpty(unpfMsgSycReqDTO.getCreateStaff())){
			unpfMsgSycReqDTO.setCreateStaff(unpfMsgSycReqDTO.getStaff().getId());
		}
			unpfMsgSycSV.saveUnpfMsgSyc(unpfMsgSycReqDTO);
	}

	@Override
	public void saveUnpfMsgSycNoThrows(UnpfMsgSycReqDTO unpfMsgSycReqDTO) throws BusinessException{
		try{
			this.saveUnpfMsgSyc(unpfMsgSycReqDTO);
		}catch(Exception ex){
			LogUtil.error(MODULE, ex.toString());
		}
	}
	
	@Override
	public void updateUnpfMsgSyc(UnpfMsgSycReqDTO unpfMsgSycReqDTO) throws BusinessException{
		if(unpfMsgSycReqDTO==null){
			 //传入参数为空
            throw new BusinessException("unpf.100001");
		}
		if(StringUtil.isEmpty(unpfMsgSycReqDTO.getId())){
			 //传入参数为空
           throw new BusinessException("unpf.100002");
		}
			unpfMsgSycSV.updateUnpfMsgSyc(unpfMsgSycReqDTO);
	}
	
	@Override
	public void updateUnpfMsgSycNoThrows(UnpfMsgSycReqDTO unpfMsgSycReqDTO) throws BusinessException{
		try{
			this.updateUnpfMsgSyc(unpfMsgSycReqDTO);
		}catch(Exception ex){
			LogUtil.error(MODULE, ex.toString());
		}
	}

	@Override
	public void deleteUnpfMsgSyc(UnpfMsgSycReqDTO unpfMsgSycReqDTO) throws BusinessException{
		if(unpfMsgSycReqDTO==null){
			 //传入参数为空
           throw new BusinessException("unpf.100001");
		}
		if(StringUtil.isEmpty(unpfMsgSycReqDTO.getId())){
			 //传入参数为空
          throw new BusinessException("unpf.100002");
		}
			unpfMsgSycSV.deleteUnpfMsgSyc(unpfMsgSycReqDTO);
	}

	@Override
	public void deleteUnpfMsgSycNoThrows(UnpfMsgSycReqDTO unpfMsgSycReqDTO) throws BusinessException{
		try{
			this.deleteUnpfMsgSyc(unpfMsgSycReqDTO);
		}catch(Exception ex){
			LogUtil.error(MODULE, ex.toString());
		}
	}
	
	@Override
	public Long getMsgSycId() throws BusinessException{
		return unpfMsgSycSV.getMsgSycId();
	}

}


