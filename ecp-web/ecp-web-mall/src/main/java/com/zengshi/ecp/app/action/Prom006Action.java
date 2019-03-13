package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Prom006Req;
import com.zengshi.ecp.app.resp.KillPromInfoRespVO;
import com.zengshi.ecp.app.resp.Prom006Resp;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoResponseDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;
import com.alibaba.dubbo.common.utils.CollectionUtils;



@Service("prom006")
@Action(bizcode="prom006",userCheck=false)
@Scope("prototype")
public class Prom006Action extends AppBaseAction<Prom006Req, Prom006Resp>{

	private static final Long SITE_ID = 1l;
	private static final int PAGENO = -1;
    
    @Resource
    private IPromQueryRSV promQueryRSV;

	@Override
	protected void getResponse() throws BusinessException, SystemException, Exception {
        
        // 1、入参组织参数
		PromInfoDTO promInfoDTO = new PromInfoDTO();
		//站点编码
		if(this.request.getSiteId()!=null){
			promInfoDTO.setSiteId(this.request.getSiteId());
		}else{
			promInfoDTO.setSiteId(SITE_ID);
		}
		
		if(this.request.getPageSize()>0){
			promInfoDTO.setPageSize(this.request.getPageSize());
		}
		
		if(this.request.getPageNo()!=0){
			promInfoDTO.setPageNo(this.request.getPageNo());
		}else{
			promInfoDTO.setPageNo(PAGENO);
		}
		
		PageResponseDTO<PromInfoResponseDTO> page = promQueryRSV.listSecondPromInfoForPage(promInfoDTO);
		List<KillPromInfoRespVO> resList = new ArrayList<KillPromInfoRespVO>();
		if(page!=null){
			List<PromInfoResponseDTO> promInfoList = page.getResult();
			if(!CollectionUtils.isEmpty(promInfoList)){
				for (PromInfoResponseDTO promInfoResponseDTO : promInfoList) {
					KillPromInfoRespVO killPromInfoRespVO = new KillPromInfoRespVO();
					killPromInfoRespVO.setId(promInfoResponseDTO.getId());
					killPromInfoRespVO.setPromTheme(promInfoResponseDTO.getPromTheme());
					resList.add(killPromInfoRespVO);
				}
			}
		}
		this.response.setResList(resList);
	}
}
