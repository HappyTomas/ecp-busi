package com.zengshi.ecp.quartz.busi.job;

import java.util.Map;

import org.quartz.DisallowConcurrentExecution;
import com.zengshi.ecp.quartz.QuartzContextHolder;
import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderLogisticsReqDTO;
import com.zengshi.ecp.quartz.busi.common.AbstractCommonQuartzJob;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthRespDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.auth.IUnpfShopAuthRSV;
import com.zengshi.ecp.unpf.dubbo.interfaces.order.IUnpfShopExpressRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

@DisallowConcurrentExecution
public class UnpfShopExpressJob  extends AbstractCommonQuartzJob{

	private static final String MODULE = UnpfShopExpressJob.class.getName();
	 
	private IUnpfShopAuthRSV unpfShopAuthRSV= QuartzContextHolder.getBean(IUnpfShopAuthRSV.class);
	
	private IUnpfShopExpressRSV unpfShopExpressRSV= QuartzContextHolder.getBean(IUnpfShopExpressRSV.class);;
	
	@Override
	protected void doJob(Map<String, String> arg0) throws Exception {
		// TODO Auto-generated method stub
		UnpfShopAuthReqDTO unpfShopAuthReqDTO = new UnpfShopAuthReqDTO();
		unpfShopAuthReqDTO.setStatus("1");
		unpfShopAuthReqDTO.setPageSize(10000);//默认10000条数据，应该没有那么多配置吧????
		PageResponseDTO<UnpfShopAuthRespDTO> page = unpfShopAuthRSV.queryPlatType4ShopForPage(unpfShopAuthReqDTO);
		if (page!=null && CollectionUtils.isNotEmpty(page.getResult())) {
			for(UnpfShopAuthRespDTO resp:page.getResult()){
				try{
					OrderLogisticsReqDTO req = new OrderLogisticsReqDTO();
					ObjectCopyUtil.copyObjValue(resp, req, null, false);
					req.setAuthId(resp.getId());
					unpfShopExpressRSV.dealShopExpress(req);
				}catch(Exception e){
					//记录日志，不能抛出，抛出你就完蛋了！！
					 LogUtil.error(MODULE,"=============UnpfShopExpressJob error =============",e);
				}
			}
		}
	}

	@Override
	protected String getModule() {
		// TODO Auto-generated method stub
		return MODULE;
	}

}
