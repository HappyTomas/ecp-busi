package com.zengshi.ecp.system.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.base.velocity.AiToolUtil;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.StringUtil;

/**
 * 商品返回参数工具类  Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2016年1月26日上午10:11:43 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsParamsTool {
	
	public static final Long DEFALUT_SITE_ID = 1l;
    
	/**
	 * 批量处理商品返回参数的商品详情URL
	 * @param respVO
	 * @param urlKey  商品详情url的key
	 * @param siteId  可以不传，默认为1
	 * @return
	 */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static EcpBasePageRespVO<Map> batchGdsDetailUrl(EcpBasePageRespVO<Map> respVO,String urlKey,Long...siteId){ 
		List<Map> respList = new ArrayList<>();
		
    	if(!CollectionUtils.isEmpty(respVO.getList())){
    		Long site=DEFALUT_SITE_ID;
    		if(siteId!=null && siteId.length>0 && !"null".equals(siteId[0]+"")){
    			site=siteId[0];
    		}
    		AiToolUtil aiToolUtil =  new AiToolUtil();
    		for(Map m:respVO.getList()){
				Map respM = new HashMap();
    			respM.putAll(m);
    			String gdsdetailUrl = getGdsDetailSiteUrl(site,(String)m.get(urlKey));
    			respM.put("gdsDetailUrl", gdsdetailUrl);
    			respList.add(respM);
    		}
    	}
    	respVO.setList(respList);
    	return respVO;
    }
    
    /**
     * 
     * setMainPicURL:设置主图URL. <br/> 
     * 
     * @author liyong7
     * @param respVO 
     * @since JDK 1.6
     */
    public static void setMainPicURL(EcpBasePageRespVO<Map> respVO){
    	
    	if(null != respVO && !CollectionUtils.isEmpty(respVO.getList())){
    		AiToolUtil aiToolUtil =  new AiToolUtil();
    		for(Map m:respVO.getList()){
    			// 设置主图URL地址.
    			GdsMediaRespDTO mainPic = (GdsMediaRespDTO) m.get("mainPic");
    			if(null != mainPic && null != mainPic.getMediaUuid()){
    				mainPic.setURL(aiToolUtil.genImageUrl(mainPic.getMediaUuid(), "300x200"));
    			}else{
    				mainPic = new GdsMediaRespDTO();
    				mainPic.setURL(aiToolUtil.genImageUrl(null, "300x200"));
    				m.put("mainPic", mainPic);
    			}
    		}
    	}
    }
    
    
    /**
	 * 批量处理商品返回参数的商品详情URL
	 * @param respVO
	 * @param urlKey  商品详情url的key
	 * @param siteId  可以不传，默认为1
	 * @return
	 */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static EcpBasePageRespVO<Map> batchGdsDetailUrl(EcpBasePageRespVO<Map> respVO,Long...siteId){ 
		List<Map> respList = new ArrayList<>();
		
    	if(!CollectionUtils.isEmpty(respVO.getList())){
    		Long site=DEFALUT_SITE_ID;
    		if(siteId!=null && siteId.length>0 && !"null".equals(siteId[0]+"")){
    			site=siteId[0];
    		}
    		for(Map m:respVO.getList()){
				Map respM = new HashMap();
    			respM.putAll(m);
    			if(StringUtil.isNotEmpty(m.get("gdsId"))){
    				String gdsdetailUrl = getGdsDetailSiteUrl(site,(Long)m.get("gdsId"),(Long)m.get("skuId"),null);
        			respM.put("gdsDetailUrl", gdsdetailUrl);
    			}
    			respList.add(respM);
    		}
    	}
    	respVO.setList(respList);
    	return respVO;
    }
    
    
    /**
	 * 批量处理商品返回参数的商品详情URL
	 * @param respVO
	 * @param urlKey  商品详情url的key
	 * @param siteId  可以不传，默认为1
	 * @return
	 */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static EcpBasePageRespVO<Map> batchGdsAndOrdDetailUrl(EcpBasePageRespVO<Map> respVO,Long...siteId){ 
		List<Map> respList = new ArrayList<>();
		
    	if(!CollectionUtils.isEmpty(respVO.getList())){
    		Long site=DEFALUT_SITE_ID;
    		if(siteId!=null && siteId.length>0 && !"null".equals(siteId[0]+"")){
    			site=siteId[0];
    		}
    		for(Map m:respVO.getList()){
				Map respM = new HashMap();
    			respM.putAll(m);
    			
    			if(StringUtil.isNotEmpty(m.get("gdsId"))){
    				String gdsdetailUrl = getGdsDetailSiteUrl(site,(Long)m.get("gdsId"),(Long)m.get("skuId"),null);
        			respM.put("gdsDetailUrl", gdsdetailUrl);
    			}
    			if(StringUtil.isNotEmpty(m.get("orderId"))){
    				String ordDetailUrl =  ParamsTool.getOrdDetailUrl(site, (String)m.get("orderId"));
        			respM.put("ordDetailUrl", ordDetailUrl);
    			}
    			respList.add(respM);
    		}
    	}
    	respVO.setList(respList);
    	return respVO;
    }
    
    
    
    /**
     * 根据站点id获取商品详情URL
     * @param siteId
     * @param orderId
     * @return
     */
    public static String getGdsDetailSiteUrl(Long siteId,String gdsUrl){
        String detailUrl = CmsCacheUtil.getCmsSiteCache(siteId).getSiteUrl()+gdsUrl;
        return detailUrl;
    }

    
    /**
     * 根据站点id获取商品详情URL
     * @param siteId 
     * @param gdsId  
     * @param skuId
     * @param catalogId
     * @return
     */
    public static String getGdsDetailSiteUrl(Long siteId,Long gdsId,Long skuId,Long catalogId){
        String detailUrl = CmsCacheUtil.getCmsSiteCache(siteId).getSiteUrl()+GdsUtils.getGdsUrl(gdsId, skuId, catalogId);
        return detailUrl;
    }
    /**
     * 
     * fuzzyStaffCode:用户码结果模糊化,用户名1位后面跟4个星,2位保留一位后面跟4个星,其余的保留头尾中间3个星. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author liyong7
     * @param source 源字符串
     * @param replaceStr 替换字符串.
     * @return 
     * @since JDK 1.6
     */
    public static String fuzzyStaffCode(String source){
        String s = "";
        if(StringUtil.isNotBlank(source)){
            if(1 == source.length()){
                s = source+"****";
            }else{
                StringBuilder builder = new StringBuilder();
                if(2 == source.length()){
                    builder.append(source.substring(0, 1));
                    builder.append("****");
                }else{
                    builder.append(source);
                    builder.replace(1, source.length() - 1, "***");
                }
                s = builder.toString();
            }
            
        }
        return s;
    }
}

