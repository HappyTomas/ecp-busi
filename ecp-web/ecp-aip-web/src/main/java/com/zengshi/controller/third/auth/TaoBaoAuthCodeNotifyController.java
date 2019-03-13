package com.zengshi.controller.third.auth;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthReqDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.token.IUnpfTokenRSV;
import com.zengshi.paas.utils.CacheUtil;

@Controller
@RequestMapping(value="/taobaoauth")
public class TaoBaoAuthCodeNotifyController {
	private final static Logger logger = LoggerFactory.getLogger(TaoBaoAuthCodeNotifyController.class);

   @Resource
    private IUnpfTokenRSV unpfTokenRSV;
	    
	@RequestMapping(value="/{id}", method = { RequestMethod.POST,RequestMethod.GET })
	public String authCode(Model model,@PathVariable("id")String id,String code) throws Exception{
		logger.info("请求数据id：" +id +"请求数据code：" +code );
		try {
			UnpfShopAuthReqDTO unpfShopAuthReqDTO=new UnpfShopAuthReqDTO();
			unpfShopAuthReqDTO.setId(Long.valueOf(id));
			unpfShopAuthReqDTO.setAuthCode(code);
			unpfTokenRSV.createToken(unpfShopAuthReqDTO);
			
		} catch (Exception e) {
			logger.error("请求数据id：" +id +"请求数据code：" +code );
			logger.error("调用异常：",e);
			throw e;
		} 
		
		CmsSiteRespDTO re=this.getSiteInfo(8L);
		
		//需要跳转到管理平台的 某个路径  可通过siteId取值 跳转  
		/*return "redirect:http://shoptest1.pmph.com:19419/ecp-web-mall";*/
		return "redirect:"+re.getSiteUrl()+"/platauth";
	}
	
    /**
     * 获取站点缓存
     * @param siteId
     * @return
     * @author huangjx
     */
    private  CmsSiteRespDTO getSiteInfo(Long siteId) {
        CmsSiteRespDTO  resp = new CmsSiteRespDTO();
        logger.info("========开始获取站点缓存："+siteId);
        try {
            Map map = (HashMap) CacheUtil
                    .getItem(CmsConstants.ParamConfig.CMS_SITE_CACHE);
            if(map!=null){
                Object obj= map.get(siteId);
                if(obj!=null){
                    resp=(CmsSiteRespDTO)obj;
                }
            }
            logger.info("========结束获取站点缓存："+resp);
        } catch (Exception e) {
        	 logger.info("========获取站点缓存报错："+resp);
        }
        if(resp==null){
            resp = new CmsSiteRespDTO();
        }
        return resp;
    }

}
