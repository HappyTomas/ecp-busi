package com.zengshi.ecp.system.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.base.util.ApplicationContextUtil;
import com.zengshi.ecp.base.util.WebContextUtil;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupTypeRespDTO;
import com.zengshi.ecp.coupon.dubbo.util.CoupCacheUtil;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsTypeRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.server.front.util.SiteLocaleUtil;
import com.zengshi.ecp.staff.dubbo.dto.AuthManageReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthMenuResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanyInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.SellerResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffUtil;
import com.zengshi.ecp.system.filter.SellerLocaleUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.mongodb.DBObject;


public class ConstantTool {

    private static final String MODULE = ConstantTool.class.getName();

    /**
     * 
     * getComapnyCache:(获取企业下拉框). <br/>
     * 
     * @author wangbh
     * @return
     * @since JDK 1.7
     */
    public Map<Long, CompanyInfoResDTO> getComapnyCache() {
        Map<Long, CompanyInfoResDTO> map = new HashMap<Long, CompanyInfoResDTO>();
        try {
            map = StaffUtil.getComapnyCache();
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
    public Map<Long, ShopInfoResDTO> getShopCache() {
        Map<Long, ShopInfoResDTO> map = new HashMap<Long, ShopInfoResDTO>();
        try {
            map = StaffUtil.getShopCache();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 
     * siteCache:(获取站点缓存). <br/>
     * 
     * @author jiangzh
     * @return
     * @since JDK 1.7
     */
    public CmsSiteRespDTO siteCache(String siteId) {
        CmsSiteRespDTO site = null;
        Map<Long, CmsSiteRespDTO> map = new HashMap<Long, CmsSiteRespDTO>();
        try {
            map = CmsCacheUtil.queryCmsSiteCache();
            if (!CollectionUtils.isEmpty(map)) {
                if (StringUtil.isNotBlank(siteId)) {
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
    public String stringEncode(String keyWord) {
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
    public String stringDecode(String keyWord) {
        String str = "";
        try {
            str = java.net.URLDecoder.decode(keyWord, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 
     * getCustInfo:(获取当前登录用户信息). <br/>
     * 
     * @author wangbh
     * @param staffId
     * @return
     * @since JDK 1.7
     */
    public CustInfoResDTO getCustInfo() {
        // CustInfoReqDTO dto = new CustInfoReqDTO();
        // CustInfoResDTO res = new CustInfoResDTO();
        com.zengshi.ecp.server.front.security.AuthPrivilegeResDTO auth = WebContextUtil.getCurrentUser();
        CustInfoResDTO res = new CustInfoResDTO();
        if (null == auth || 0 == auth.getStaffId()) {

            return res;
        }
        CustInfoReqDTO arg0  = new CustInfoReqDTO();
        arg0.setId(auth.getStaffId());
        CustInfoResDTO custInfoResDTO = StaffUtil.getCustInfo(arg0);
        ObjectCopyUtil.copyObjValue(custInfoResDTO, res, null, true);
        return res;
    }

    /**
     * @author wangxq 展示库存
     * @param countStr
     * @return
     */

    public String getStockStatus(String countStr) {
        return getStockStatus(countStr, "");
    }

    public String getStockStatus(String countStr, String typeIdStr) {
        if (StringUtil.isBlank(countStr)) {
            return "无货";
        }
        Long count = Long.valueOf(countStr);

        if (StringUtil.isBlank(typeIdStr)) {
            return GdsUtils.checkStcokStatusDesc(count, 2);
        }
        long typeId = Long.valueOf(typeIdStr);
        return GdsUtils.checkStcokStatusDesc(count, typeId);

    }

    /**
     * querySiteListCache:(获取站点列表,取缓存). <br/>
     * 
     * @author jiangzh
     * @return
     * @since JDK 1.6
     */
    public Map<Long, CmsSiteRespDTO> querySiteListCache() {
        Map<Long, CmsSiteRespDTO> map = new HashMap<Long, CmsSiteRespDTO>();
        try {
            map = CmsCacheUtil.queryCmsSiteCache();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 
     * getSellerList:(获取卖家中的店铺列表). <br/>
     * 
     * @author wangbh
     * @return
     * @since JDK 1.7
     */
    public List<ShopInfoResDTO> getSellerList() {
        SellerResDTO sellerResDTO = SellerLocaleUtil.getSeller();
        List<ShopInfoResDTO> shoplist = sellerResDTO.getShoplist();
        if (CollectionUtils.isEmpty(shoplist)) {
            return null;
        }
        return shoplist;

    }

    /**
     * 
     * getMenuByStaffCache:(获取菜单列表). <br/>
     * 
     * @author wangbh
     * @return
     * @since JDK 1.7
     */
    public static List<AuthMenuResDTO> getMenuByStaffCache() {

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
     * 获得促销类型
     * 
     * @return
     * @author huangjx
     */
    public Map<Long, CoupTypeRespDTO> searchCoupTypeCache() {
        Map<Long, CoupTypeRespDTO> map = new HashMap<Long, CoupTypeRespDTO>();
        try {
            map = CoupCacheUtil.searchCoupTypeCache();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 
     * getBreadCrumb:(获取菜单面包屑). <br/>
     * 
     * @author wangbh
     * @param url
     * @return
     * @since JDK 1.7
     */
    public static Map<String, String> getBreadCrumb(String url) {
        List<AuthMenuResDTO> list = ConstantTool.getMenuByStaffCache();
        Map<String,String> map = new HashMap<String, String>();
        for (AuthMenuResDTO authMenuResDTO : list) {
            List<com.zengshi.ecp.server.front.security.AuthMenuResDTO> list1 = authMenuResDTO
                    .getSonList();
            for (com.zengshi.ecp.server.front.security.AuthMenuResDTO authMenuResDTO2 : list1) {
                if(StringUtil.isNotBlank(authMenuResDTO2.getMenuUrl())&&authMenuResDTO2.getMenuUrl().equals(url)){
                    map.put("menuTitle", authMenuResDTO2.getMenuTitle());
                    map.put("pMenuTitle", authMenuResDTO.getMenuTitle());
                }
            }
        }
        return map;
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
	 * 将DBObject转换成Bean对象
	 * 
	 */
	public static <T> T dbObjectToBean(DBObject dbObject, T bean) {

		try {
			if (bean == null) {
				return null;
			}
			Field[] fields = bean.getClass().getDeclaredFields();
			for (Field field : fields) {
				String varName = field.getName();
				Object object = dbObject.get(varName);
				if (object != null) {
					BeanUtils.setProperty(bean, varName, object);
				}

			}
			return bean;
		} catch (Exception e) {
		LogUtil.error(MODULE, e.getMessage(), e);
		}
		return null;
	}
    
}
