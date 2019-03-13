package com.zengshi.third.service.msg;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthRespDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.auth.IUnpfShopAuthRSV;
import com.zengshi.ecp.unpf.dubbo.util.UnpfConstants;
import com.zengshi.model.ThirdMsgReq;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.third.service.msg.interfaces.IThirdGetMsgHandlerSV;


public class DefaultGetMsgSVImpl {
	
	private final static Logger logger = LoggerFactory.getLogger(DefaultGetMsgSVImpl.class);

   @Resource
    private IUnpfShopAuthRSV unpfShopAuthRSV;
   
   @Resource
   private HashMap<String,IThirdGetMsgHandlerSV> msgGetMap;

	public HashMap<String, IThirdGetMsgHandlerSV> getMsgGetMap() {
	return msgGetMap;
	}
	public void setMsgGetMap(HashMap<String, IThirdGetMsgHandlerSV> msgGetMap) {
		this.msgGetMap = msgGetMap;
	}
   //启动初始化
	public void init() throws Exception{
		this.handler(null,"init");
	}
	//销毁
	public void destroy() throws Exception{
	}
     //页面调用初始化
	public void handler(Long id,String flag) throws Exception{
	    List<BaseParamDTO> platList= BaseParamUtil.fetchParamList("UNPF_PLAT_TYPE");
		if(!CollectionUtils.isEmpty(platList)){
			//转为map
			HashMap map=new HashMap();
			for(BaseParamDTO d:platList){
				map.put(d.getSpCode(), d.getSpValue());
			}
			UnpfShopAuthReqDTO unpfShopAuthReqDTO=new UnpfShopAuthReqDTO();
			if(id!=null){
				unpfShopAuthReqDTO.setId(id);
				unpfShopAuthReqDTO.setStatus(UnpfConstants.PlatType4Shop.STATUS_1);
			}else{
				unpfShopAuthReqDTO.setStatus(UnpfConstants.PlatType4Shop.STATUS_1);
				unpfShopAuthReqDTO.setPageSize(Integer.MAX_VALUE);
			}
			PageResponseDTO<UnpfShopAuthRespDTO> p=unpfShopAuthRSV.queryPlatType4ShopForPage(unpfShopAuthReqDTO);
			
			HashMap mapTmp=new HashMap();
			
			if(p!=null && !CollectionUtils.isEmpty(p.getResult())){
				//初始化各个店铺的监听开始
				for(UnpfShopAuthRespDTO unpf:p.getResult()){
					    //包含 需要初始化
					    if(map.containsKey(unpf.getPlatType())){
					    	
					    	//如果加载 就不要加载
					    	if(!mapTmp.containsKey(unpf.getPlatType()+unpf.getShopId()+unpf.getAppkey())){
					    	 	ThirdMsgReq thirdMsgReq=new ThirdMsgReq();
					    	 	
						    	IThirdGetMsgHandlerSV sv=this.getSV(unpf.getPlatType());
						    	if(sv!=null){
						    		ObjectCopyUtil.copyObjValue(unpf, thirdMsgReq, "", false);
						    		//参数配置表获得url 
						    		 BaseSysCfgRespDTO sysDTO=null;
						    		try{
						    		    sysDTO=SysCfgUtil.fetchSysCfg(thirdMsgReq.getPlatType()+"_topic_url");
							    	}catch(Exception ex){
										logger.error(thirdMsgReq.getPlatType()+"_topic_url  报错啦 没有配置哦");
										logger.error("参数表没有配置topic url 不用启动监听");
									}
						    		if(sysDTO==null){
						    			logger.error("参数表没有配置topic url 不用启动监听");
						    			continue;
						    		}
						    		thirdMsgReq.setTopicUrl(sysDTO.getParaValue());
						    		if("destroy".equals(flag)){
						    			sv.destroy(thirdMsgReq);
						    		}else{
						    			sv.init(thirdMsgReq);
						    		}
						    		
						    		mapTmp.put(unpf.getPlatType()+unpf.getShopId()+unpf.getAppkey(), unpf.getAppkey());
						    	}
					    	}
					    }
					    
				}
				
			}else{
				logger.error("没有授权任何店铺 不用启动监听");
			}
			mapTmp.clear();
			map.clear();
		}else{
			logger.error("UNPF_PLAT_TYPE 数据字典没有配置");
		}
	
	}
	//获得各个平台sv配置
	private IThirdGetMsgHandlerSV getSV(String platType)throws BusinessException{
		
		IThirdGetMsgHandlerSV sv=null;
    	
    	if(CollectionUtils.isEmpty(msgGetMap)){
    		return sv;
    	}
    	if(!msgGetMap.containsKey(platType)){
    		return sv;
    	}else{
    		sv=msgGetMap.get(platType);
    	}
    	return sv;
	}


}
