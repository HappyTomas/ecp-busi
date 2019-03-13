package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Prom007Req;
import com.zengshi.ecp.app.resp.KillGdsInfoRespVO;
import com.zengshi.ecp.app.resp.KillPromInfoRespVO;
import com.zengshi.ecp.app.resp.Prom007Resp;
import com.zengshi.ecp.prom.dubbo.dto.KillGdsInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.SecondKillPromRespDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;
import com.alibaba.dubbo.common.utils.CollectionUtils;



@Service("prom007")
@Action(bizcode="prom007",userCheck=false)
@Scope("prototype")
public class Prom007Action extends AppBaseAction<Prom007Req, Prom007Resp>{

	private static final int PAGESIZE = 10;
	private static final int PAGENO = 1;
	private static final String  PICSIZE = "320x320!";
    
    @Resource
    private IPromQueryRSV promQueryRSV;

	@Override
	protected void getResponse() throws BusinessException, SystemException, Exception {
        
        // 1、入参组织参数
		PromInfoDTO promInfoDTO = new PromInfoDTO();
		//促销编码
		if(this.request.getPromId()==null){
			 throw new Exception("请求参数promId为空");
		}
		promInfoDTO.setId(this.request.getPromId());
		
		if(this.request.getPageSize()!=0){
			promInfoDTO.setPageSize(this.request.getPageSize());
		}else{
			promInfoDTO.setPageSize(PAGESIZE);
		}
		
		if(this.request.getPageNo()!=0){
			promInfoDTO.setPageNo(this.request.getPageNo());
		}else{
			promInfoDTO.setPageNo(PAGENO);
		}
		
		SecondKillPromRespDTO secondKillPromRespDTO = promQueryRSV.listSkuOfSecondKillProm(promInfoDTO);
		PageResponseDTO<KillGdsInfoDTO> page =secondKillPromRespDTO.getPage();
		List<KillGdsInfoDTO> killGdsList = page.getResult();
		List<KillGdsInfoRespVO> killGdsInfoVoList = new ArrayList<KillGdsInfoRespVO>();
		if(page!=null&&!CollectionUtils.isEmpty(killGdsList)){
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
				killGdsInfoRespVO.setGdsTypeFlag(killGdsInfoDTO.getGdsTypeFlag());
				
				killGdsInfoVoList.add(killGdsInfoRespVO);
				}
		}
		this.response.setKillGdsList(killGdsInfoVoList);
		this.response.setIfStart(secondKillPromRespDTO.getPromInfoResponseDTO().getIfStart());
		this.response.setStartTime(secondKillPromRespDTO.getPromInfoResponseDTO().getStartTime());
		this.response.setEndTime(secondKillPromRespDTO.getPromInfoResponseDTO().getEndTime());
		this.response.setNowTime(secondKillPromRespDTO.getPromInfoResponseDTO().getNowTime());
		
	}
}
