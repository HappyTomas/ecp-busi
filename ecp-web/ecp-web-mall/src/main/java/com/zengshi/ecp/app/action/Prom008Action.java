package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Prom008Req;
import com.zengshi.ecp.app.resp.KillGdsInfoRespVO;
import com.zengshi.ecp.app.resp.Prom008Resp;
import com.zengshi.ecp.prom.dubbo.dto.KillGdsInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;



@Service("prom008")
@Action(bizcode="prom008",userCheck=false)
@Scope("prototype")
public class Prom008Action extends AppBaseAction<Prom008Req, Prom008Resp>{

	private static final Long SITE_ID = 1l;
	
	private static final int PAGESIZE = 3;
	
	private static final String  PICSIZE = "320x320!";
    
    @Resource
    private IPromQueryRSV promQueryRSV;

	@Override
	protected void getResponse() throws BusinessException, SystemException, Exception {
        
        // 1、入参组织参数
		PromDTO promDTO = new PromDTO();
		//站点编码
		if(this.request.getSiteId()!=null){
			promDTO.setSiteId(this.request.getSiteId());
		}else{
			promDTO.setSiteId(SITE_ID);
		}
		if(this.request.getPageSize()!=0){
			promDTO.setPageSize(this.request.getPageSize());
		}else{
			promDTO.setPageSize(PAGESIZE);
			
		}
		
		List<KillGdsInfoDTO> killGdsList =promQueryRSV.killPromGdsinfoList(promDTO);
		List<KillGdsInfoRespVO> killGdsInfoVoList = new ArrayList<KillGdsInfoRespVO>();
		for (KillGdsInfoDTO killGdsInfoDTO : killGdsList) {
			
			KillGdsInfoRespVO killGdsInfoRespVO = new KillGdsInfoRespVO();
			
			killGdsInfoRespVO.setGdsId(killGdsInfoDTO.getGdsId());
			killGdsInfoRespVO.setGdsName(killGdsInfoDTO.getGdsName());
			killGdsInfoRespVO.setGdsDesc(killGdsInfoDTO.getGdsDesc());
			killGdsInfoRespVO.setSkuId(killGdsInfoDTO.getSkuId());
			killGdsInfoRespVO.setBasePrice(killGdsInfoDTO.getBasePrice());
			killGdsInfoRespVO.setBuyPrice(killGdsInfoDTO.getBuyPrice());
			killGdsInfoRespVO.setKillPrice(killGdsInfoDTO.getKillPrice());
			killGdsInfoRespVO.setMediaUuid(killGdsInfoDTO.getMediaUuid());
			killGdsInfoRespVO.setURL(ParamsTool.getImageUrl(killGdsInfoDTO.getMediaUuid(),PICSIZE));
			killGdsInfoRespVO.setBuyCnt(killGdsInfoDTO.getBuyCnt());
			killGdsInfoRespVO.setPromCnt(killGdsInfoDTO.getPromCnt());
			killGdsInfoRespVO.setPercent(killGdsInfoDTO.getPercent());
			killGdsInfoRespVO.setIfSell(killGdsInfoDTO.getIfSell());
			killGdsInfoRespVO.setDetailURL(killGdsInfoDTO.getDetailURL());
			
			killGdsInfoVoList.add(killGdsInfoRespVO);
		}
		this.response.setKillGdsList(killGdsInfoVoList);
	}
}
