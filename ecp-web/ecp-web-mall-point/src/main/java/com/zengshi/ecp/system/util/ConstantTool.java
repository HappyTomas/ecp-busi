package com.zengshi.ecp.system.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.base.util.ApplicationContextUtil;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsTypeRSV;
import com.zengshi.ecp.server.front.util.SiteLocaleUtil;
import com.zengshi.ecp.staff.dubbo.dto.CompanyInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffUtil;
import com.zengshi.paas.utils.StringUtil;


public class ConstantTool{
   
    /**
     * 
     * getComapnyCache:(获取企业下拉框). <br/> 
     * 
     * @author wangbh
     * @return 
     * @since JDK 1.7
     */
    public Map<Long,CompanyInfoResDTO> getComapnyCache(){
        Map<Long,CompanyInfoResDTO> map = new HashMap<Long,CompanyInfoResDTO>();
        try {
            map =  StaffUtil.getComapnyCache();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
       return map;
    }
    /**
     * 
     * getComapnyCache:(获取店铺下拉框). <br/> 
     * 
     * @author wangbh
     * @return 
     * @since JDK 1.7
     */
    public Map<Long,ShopInfoResDTO> getShopCache(){
        Map<Long,ShopInfoResDTO> map = new HashMap<Long, ShopInfoResDTO>();
        try {
            map = StaffUtil.getShopCache();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
     }
    /**
     * 
     * getCustInfo:(获取登陆会员信息). <br/> 
     * 
     * @author PJieWin 
     * @return 
     * @since JDK 1.6
     */
    public CustInfoResDTO getCustInfo(){
        CustInfoReqDTO dto = new CustInfoReqDTO();
        CustInfoResDTO res = new CustInfoResDTO();
        dto.setId(dto.getStaff().getId());
        try {
         res =  StaffUtil.getCustInfo(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
       return res;
    }
    
    /**
     * 
     * siteCache:(获取站点缓存). <br/> 
     * 
     * @author jiangzh
     * @return 
     * @since JDK 1.7
     */
    public CmsSiteRespDTO siteCache(String siteId){
        CmsSiteRespDTO site = null;
        Map<Long,CmsSiteRespDTO> map = new HashMap<Long,CmsSiteRespDTO>();
        try {
            map =  CmsCacheUtil.queryCmsSiteCache();
            if(!CollectionUtils.isEmpty(map)){
                if(StringUtil.isNotBlank(siteId)){
                    site = map.get(Long.valueOf(siteId));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       return site;
    }
    
    /** 
     * siteName:(获取当前站点名称). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param siteId  当站点ID为空时，获取当前站点名称
     * @return 
     * @since JDK 1.6 
     */ 
    public CmsSiteRespDTO siteName(String siteId) {
        if(StringUtil.isEmpty(siteId)){
            siteId = String.valueOf(SiteLocaleUtil.getSite());
        }
        return CmsCacheUtil.getCmsSiteCache(Long.valueOf(siteId));
    }
    
    /**
     * 
     * stringEncode:(字符串转码). <br/> 
     * 
     * @author jiangzh
     * @return 
     * @since JDK 1.7
     */
    public String stringEncode(String keyWord){
        String str = "";
        try {
            str = java.net.URLEncoder.encode(keyWord, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } 
        return str;
    }
    
    /**
     * 
     * stringDecode:(字符串解码). <br/> 
     * 
     * @author jiangzh
     * @return 
     * @since JDK 1.7
     */
    public String stringDecode(String keyWord){
        String str = "";
        try {
            str = java.net.URLDecoder.decode(keyWord, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } 
        return str;
    }
    
    
    /**
     * 获取商品类型名称
     * @param id
     * @return
     */
    public String gdsTypeName(String id){
    	if(id!=null){
        	IGdsTypeRSV gdsTypeRSV=ApplicationContextUtil.getBean("gdsTypeRSV",IGdsTypeRSV.class);
        	GdsTypeRespDTO type=gdsTypeRSV.queryGdsTypeByPKFromCache(new LongReqDTO(Long.valueOf(id)));
        	if(type!=null){
        		return type.getTypeName();
        	}
        	return null;
    	}else{
    		return "";
    	}
    }
    
    /**
     * 是否需要库存 当前商品类型
     * @param id
     * @return
     */
    public boolean isNeedStock(String id){
    	if(id!=null){
    		IGdsInfoExternalRSV gdsInfoExternalRSV=ApplicationContextUtil.getBean("gdsInfoExternalRSV",IGdsInfoExternalRSV.class);
        	return gdsInfoExternalRSV.isNeedStockAmount(new LongReqDTO(Long.valueOf(id)));
    	}else{
    		return true;
    	}
    }
    
    /**
     * 是否允许购买多次
     * @param id
     * @return
     */
    public boolean isBuyMore(String id){
    	if(id!=null){
    		IGdsInfoExternalRSV gdsInfoExternalRSV=ApplicationContextUtil.getBean("gdsInfoExternalRSV",IGdsInfoExternalRSV.class);
        	return gdsInfoExternalRSV.isGdsTypeBuyMore(new LongReqDTO(Long.valueOf(id)));
    	}else{
    		return true;
    	}
    }
}
