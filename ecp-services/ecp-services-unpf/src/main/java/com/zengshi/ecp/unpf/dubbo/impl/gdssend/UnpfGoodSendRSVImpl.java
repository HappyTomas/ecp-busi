package com.zengshi.ecp.unpf.dubbo.impl.gdssend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.unpf.dao.model.UnpfGdsSend;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsSendLogRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsSendReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsSendRespDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.auth.IUnpfShopAuthRSV;
import com.zengshi.ecp.unpf.dubbo.interfaces.gdssend.IUnpfGoodSendRSV;
import com.zengshi.ecp.unpf.dubbo.util.UnpfConstants;
import com.zengshi.ecp.unpf.service.busi.good.send.interfaces.IUnpfGoodSendSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
* @author  lisp: 
* @date 创建时间：2016年11月16日 下午2:54:14 
* @version 1.0 
* @parameter  
* @since  
* @return  */
public class UnpfGoodSendRSVImpl implements IUnpfGoodSendRSV {

	@Resource
	private IUnpfShopAuthRSV unpfShopAuthRSV;
	
	@Resource
    private IGdsInfoQueryRSV iGdsInfoQueryRSV;
	
	@Resource
    private IUnpfGoodSendSV unpfGoodSendSV;
	
	@Override
	public PageResponseDTO<UnpfGdsSendRespDTO> queryGdsInfoListPage(GdsInfoReqDTO gdsInfoReqDTO) throws BusinessException{
		PageResponseDTO<UnpfGdsSendRespDTO> result = new PageResponseDTO<UnpfGdsSendRespDTO>();
		List<UnpfGdsSendRespDTO> gdsList = new ArrayList<UnpfGdsSendRespDTO>();
		UnpfShopAuthReqDTO unpfShopAuthReqDTO = new UnpfShopAuthReqDTO();
		unpfShopAuthReqDTO.setShopId(gdsInfoReqDTO.getShopId());
		unpfShopAuthReqDTO.setStatus(UnpfConstants.PlatType4Shop.STATUS_1);
		List<String> platTypes = unpfShopAuthRSV.queryPlatType4ShopByExample(unpfShopAuthReqDTO);
		Map<String, String> platType = new HashMap<String, String>();
		for (String string : platTypes) {
			platType.put(string, BaseParamUtil.fetchParamValue(UnpfConstants.UNPF_PLAT_TYPE,string));
		}
		gdsInfoReqDTO.setExt1Null(true);
		PageResponseDTO<GdsInfoRespDTO> list = iGdsInfoQueryRSV.queryGdsInfoListPageWithAuth(gdsInfoReqDTO);
		if(list!=null&&!CollectionUtils.isEmpty(list.getResult())){
			for (GdsInfoRespDTO gdsInfoRespDTO : list.getResult()) {
				UnpfGdsSendRespDTO gdsInfo = new UnpfGdsSendRespDTO();
				ObjectCopyUtil.copyObjValue(gdsInfoRespDTO, gdsInfo, null, false);
				gdsInfo.setPlatTypes(platType);


				UnpfGdsSendReqDTO unpfGdsSendReqDTO = new UnpfGdsSendReqDTO();
				unpfGdsSendReqDTO.setShopId(gdsInfoReqDTO.getShopId());
				unpfGdsSendReqDTO.setGdsId(gdsInfo.getId());
				List<UnpfGdsSend> unpfGdsSendList =  this.unpfGoodSendSV.querySends(unpfGdsSendReqDTO);
				Map<String, String> platStatus = new HashMap<String, String>();
				for (String key : platType.keySet()) {
					if(CollectionUtils.isNotEmpty(unpfGdsSendList)){
						String flag = "0";
						for(UnpfGdsSend unpfGdsSend :unpfGdsSendList){
							if(unpfGdsSend.getPlatType().equals(key) && "1".equals(unpfGdsSend.getStatus())){
								platStatus.put(key,"1"); //已推送
								flag = "1";
							}
						}
						if("0".equals(flag)){
							platStatus.put(key,"0");  //未推送
						}
					} else {
						platStatus.put(key,"0");
					}
				}
				gdsInfo.setPlatStatus(platStatus);
				gdsList.add(gdsInfo);
			}
		}
		ObjectCopyUtil.copyObjValue(list, result, null, false);
		result.setResult(gdsList);
		return result;
	}
	
	
	/**
     * 
     * queryGdsLogInfoListPage:(根据条件查询对应的商品列表，分页). <br/>
     * 商品推送记录查询
     * 
     * @author lisp
     * @param
     * @return
     * @since JDK 1.6
     */
	@Override
	public PageResponseDTO<UnpfGdsSendLogRespDTO> queryGdsLogInfoListPage(UnpfGdsSendReqDTO unpfGdsSendReqDTO) throws BusinessException{
		if(unpfGdsSendReqDTO==null){
			return null;
		}
		 PageResponseDTO<UnpfGdsSendLogRespDTO> gdsSendLogPage = unpfGoodSendSV.querySendLogForPage(unpfGdsSendReqDTO);
		 if(gdsSendLogPage!=null&&!CollectionUtils.isEmpty(gdsSendLogPage.getResult())){
			 GdsInfoReqDTO gdsInfo = new GdsInfoReqDTO();
			 for (UnpfGdsSendLogRespDTO unpfGdsSendLogRespDTO : gdsSendLogPage.getResult()) {
				 gdsInfo.setId(unpfGdsSendLogRespDTO.getGdsId());
				 GdsInfoRespDTO GdsInfoRespDTO =iGdsInfoQueryRSV.queryGdsInfo(gdsInfo);
				 ObjectCopyUtil.copyObjValue(GdsInfoRespDTO, unpfGdsSendLogRespDTO, null, false);
			}
		 }
		return gdsSendLogPage;
	}

	@Override
	public List<UnpfGdsSendLogRespDTO> querySends(UnpfGdsSendReqDTO unpfGdsSendReqDTO) throws BusinessException {
		List<UnpfGdsSendLogRespDTO> gdsList = new ArrayList<UnpfGdsSendLogRespDTO>();
		List<UnpfGdsSend> unpfGdsSendList = this.unpfGoodSendSV.querySends(unpfGdsSendReqDTO);
		if(CollectionUtils.isNotEmpty(unpfGdsSendList)){
			for(UnpfGdsSend unpfGdsSend:unpfGdsSendList){
				UnpfGdsSendLogRespDTO unpfGdsSendRespDTO = new UnpfGdsSendLogRespDTO();
				ObjectCopyUtil.copyObjValue(unpfGdsSend,unpfGdsSendRespDTO,null,false);
				gdsList.add(unpfGdsSendRespDTO);
			}
		}
		return gdsList;
	}

	@Override
	public void updateGdsSendSubmit(UnpfGdsSendReqDTO unpfGdsSendReqDTO) throws BusinessException {
		this.unpfGoodSendSV.updateGdsSendSubmit(unpfGdsSendReqDTO);
	}
}


