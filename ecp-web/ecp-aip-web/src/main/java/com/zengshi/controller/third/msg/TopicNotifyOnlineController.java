package com.zengshi.controller.third.msg;

import java.util.HashMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthRespDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.auth.IUnpfShopAuthRSV;
import com.zengshi.third.service.msg.DefaultGetMsgSVImpl;

@Controller
@RequestMapping(value="/topic_online")
public class TopicNotifyOnlineController {
	private final static Logger logger = LoggerFactory.getLogger(TopicNotifyOnlineController.class);
	
	@Resource
    private IUnpfShopAuthRSV unpfShopAuthRSV;
	   
	@Resource
    private DefaultGetMsgSVImpl defaultGetMsgSV;
		    
	@RequestMapping(value="init/{id}", method = { RequestMethod.POST,RequestMethod.GET })
	@ResponseBody
	public HashMap init(Model model,@PathVariable("id")String id) throws Exception{
		logger.info("请求数据id：" +id );
		HashMap map=new HashMap();
		map.put("SUCCESS", "OK");
		try {
			
			UnpfShopAuthReqDTO unpfShopAuthReqDTO=new UnpfShopAuthReqDTO();
			unpfShopAuthReqDTO.setId(Long.valueOf(id));
			UnpfShopAuthRespDTO r=unpfShopAuthRSV.queryPlatType4ShopById(unpfShopAuthReqDTO);
			if(r!=null && r.getId()!=null){
				logger.info("返回数据id：" +r.getId() );
				defaultGetMsgSV.handler(r.getId(),"init");
			}else{
				map.put("SUCCESS", "ERROR");
				map.put("MSG", "不需要启动，平台没有配置对应的授权消息列表");
			}
		} catch (Exception e) {
			logger.error("请求数据id：" +id);
			logger.error("调用异常：",e);
			map.put("SUCCESS", "ERROR");
			map.put("MSG", e.toString());
		} 
		return map;
	}
    
	@RequestMapping(value="destroy/{id}", method = { RequestMethod.POST,RequestMethod.GET })
	@ResponseBody
	public HashMap destroy(Model model,@PathVariable("id")String id) throws Exception{
		logger.info("请求数据id：" +id );
		HashMap map=new HashMap();
		map.put("SUCCESS", "OK");
		try {
			
			UnpfShopAuthReqDTO unpfShopAuthReqDTO=new UnpfShopAuthReqDTO();
			unpfShopAuthReqDTO.setId(Long.valueOf(id));
			UnpfShopAuthRespDTO r=unpfShopAuthRSV.queryPlatType4ShopById(unpfShopAuthReqDTO);
			if(r!=null && r.getId()!=null){
				defaultGetMsgSV.handler(r.getId(),"destroy");
			}else{
				map.put("SUCCESS", "ERROR");
				map.put("MSG", "不需要关闭客户端，平台没有配置对应的授权消息列表");
			}
		} catch (Exception e) {
			logger.error("请求数据id：" +id);
			logger.error("调用异常：",e);
			map.put("SUCCESS", "ERROR");
			map.put("MSG", e.toString());
		} 
		return map;
	}
}
