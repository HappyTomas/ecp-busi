package com.zengshi.ecp.system.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.base.util.ApplicationContextUtil;
import com.zengshi.ecp.base.util.WebContextUtil;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupTypeRespDTO;
import com.zengshi.ecp.coupon.dubbo.util.CoupCacheUtil;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsTypeRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsCatalogUtil;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SiteLocaleUtil;
import com.zengshi.ecp.staff.dubbo.dto.AuthManageReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthMenuResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthPrivilegeResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaff2RoleReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaff2RoleResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanyInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAuthRelManageRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;


public class ConstantTool{
    private static final String MODULE = ConstantTool.class.getName();
    /** 
     * querySiteListCache:(获取站点列表,取缓存). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @return 
     * @since JDK 1.6 
     */ 
    public Map<Long,CmsSiteRespDTO> querySiteListCache(){
        Map<Long,CmsSiteRespDTO> map = new HashMap<Long, CmsSiteRespDTO>();
        try {
            map= CmsCacheUtil.queryCmsSiteCache();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
     }
   
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
     * 获取商品目录,目前先从数据库获取。 
     * 
     * @author liyong7
     * @return 
     * @since JDK 1.6
     */
    public List<GdsCatalogRespDTO> getGdsCatlog(){
    	try{
    		return GdsCatalogUtil.loadCatalogFromDB();
    	}catch (Exception e) {
    		e.printStackTrace();
    		return Collections.emptyList();
		}
    }
    /**
     * 获得促销类型
     * @return
     * @author huangjx
     */
    public Map<Long,CoupTypeRespDTO> searchCoupTypeCache(){
        Map<Long,CoupTypeRespDTO> map = new HashMap<Long, CoupTypeRespDTO>();
        try {
            map= CoupCacheUtil.searchCoupTypeCache();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
     }
    
    
    public List<AuthMenuResDTO> getMenuByStaffCache(){
        
        List<AuthMenuResDTO> list = new ArrayList<AuthMenuResDTO>();
        try {
            List<Long> privList = WebContextUtil.getCurrentUser().getPrivList();
            AuthManageReqDTO reqDto = new AuthManageReqDTO();
            reqDto.setPrivList(privList);
            list = StaffUtil.getMenuByStaff(reqDto);
        } catch (Exception e) {
           LogUtil.error(MODULE, "获取权限对应的菜单失败", e);
        }
        return list;
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
     * getImageUrl:(根据上传到mongoDB的图片ID 从mongoDB上获取图片路径). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param vfsId
     * @param param
     * @return 
     * @since JDK 1.6 
     */ 
    public static String getImageUrl(String vfsId,String param){
        StringBuilder sb=new StringBuilder();
        sb.append(vfsId);
        if(!StringUtil.isBlank(param)){
            sb.append("_");
            sb.append(param);
        }
        return ImageUtil.getImageUrl(sb.toString());
    }
    
    /** 
     * inputStream2Bytes:(将InputStream转换成byte数组). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param in
     * @return
     * @throws IOException 
     * @since JDK 1.6 
     */ 
    public static byte[] inputStream2Bytes(InputStream in) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[4096];
        int count = -1;
        while ((count = in.read(data, 0, 4096)) != -1)
            outStream.write(data, 0, count);
        data = null;
        return outStream.toByteArray();
    }
    
    /** 
     * getExtensionName:(获取文件拓展名). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param fileName
     * @return 
     * @since JDK 1.6 
     */ 
    public static String getExtensionName(String fileName) {
        if ((fileName != null) && (fileName.length() > 0)) {
            int dot = fileName.lastIndexOf('.');
            if ((dot > -1) && (dot < (fileName.length() - 1))) {
                return fileName.substring(dot + 1);
            }
        }
        return fileName;
    }
    
    /**
     * 
     * getCustInfo:(这里用一句话描述这个方法的作用). <br/> 
     * 
     * @author wangbh
     * @param staffId
     * @return 
     * @since JDK 1.7
     */
    public AuthPrivilegeResDTO getCustInfo(){
        com.zengshi.ecp.server.front.security.AuthPrivilegeResDTO auth =  WebContextUtil.getCurrentUser();
        AuthPrivilegeResDTO res = new AuthPrivilegeResDTO();
        if(null==auth||0==auth.getStaffId()){
            return res;
        } else {
        	ObjectCopyUtil.copyObjValue(auth, res, null, false);
        }
        return res;
    }
    
    @Resource(name="cmsTemplateRSV")
    private ICmsTemplateRSV cmsTemplateRSV;
    /**
     * 
     * queryTemplateListCache:(获取所属模板列表). <br/> 
     * 
     * @author gxq 
     * @return 
     * @since JDK 1.6
     */
    public List<CmsTemplateRespDTO> queryTemplateListCache(String siteId){
        try {
            CmsTemplateReqDTO cmsTemplateReqDTO = new CmsTemplateReqDTO();
            if(StringUtil.isNotBlank(siteId)){
                cmsTemplateReqDTO.setSiteId(Long.valueOf(siteId));
            }
            return cmsTemplateRSV.queryCmsTemplateList(cmsTemplateReqDTO);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "获取模板列表失败！", e);
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取模板列表失败！", e);
        }
        return new ArrayList<CmsTemplateRespDTO>();
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
    
    /**
     * 
     * findRoleByStaffId:(通过当前用户的id，获取其对应的所有角色字符串). <br/> 
     * 
     * @author huangxl5 
     * @return 
     * @since JDK 1.6
     */
    public String findRoleByStaffId() {
    	String roleIds = "";
    	AuthStaff2RoleReqDTO req = new AuthStaff2RoleReqDTO();
    	req.setStaffId(req.getStaff().getId());
    	IAuthRelManageRSV authRelManageRSV = ApplicationContextUtil.getBean("authRelManageRSV",IAuthRelManageRSV.class);
        List<AuthStaff2RoleResDTO> respLst = authRelManageRSV.listStaffRoleRel(req);
        if(!CollectionUtils.isEmpty(respLst)){
        	for(AuthStaff2RoleResDTO resp : respLst){
        		roleIds += resp.getRoleId() + ",";
            }
        }
        return roleIds;
    }
    
}
