package com.zengshi.ecp.busi.order.util;

import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.base.velocity.ParamToolUtil;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.StringUtil;

@Service(value="paramsService")
public class ParamsTool {
	
	public static final String DETAIL_NO_HEAD_PREX = "/ord/detail";
	public static final String DETAIL_PREX="/ord/detail";
	public static final Long DEFALUT_SITE_ID = 1l;
	
//    public static List<String> getCategoryCodes(){
//        List<String> list = new ArrayList<String>();
//        list.add("1121");
//        return list;
//    }
    
    /**
     * getImageUrl:(根据上传到mongoDB的图片ID 从mongoDB上获取图片路径). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author jiangzh
     * @param vfsId
     *            图片ID
     * @param param
     *            图片规格
     * @return
     * @since JDK 1.6
     */
    public static String getImageUrl(String vfsId, String param) {
        StringBuilder sb = new StringBuilder();
      //入参的图片ID为空，那么使用默认图片；
        if(StringUtil.isBlank(vfsId)){
            sb.append(ImageUtil.getDefaultImageId());
        } else {
            sb.append(vfsId);
        }
        if (StringUtil.isNotBlank(param)) {
            sb.append("_");
            sb.append(param);
        }
        return ImageUtil.getImageUrl(sb.toString());
    }

    /**
     * data 默认时间初始化
     * @return
     */
    public static Map<String,Timestamp> params(){
        Map<String,Timestamp> params = new HashMap<String,Timestamp>();
        params.put("begDate",new Timestamp(DateUtils.addYears(new Date(), -1).getTime()));
        params.put("endDate",new Timestamp(new Date().getTime()));
        return params;
    }
    /**
     * data 默认时间初始化
     * @return
     */
    public static Map<String,Timestamp> paramsToday(){
        Map<String,Timestamp> params = new HashMap<String,Timestamp>();
        params.put("begDate",new Timestamp(new Date().getTime()));
        params.put("endDate",new Timestamp(new Date().getTime()));
        return params;
    }
    
    public static Map<String,Long> shopInit(){
    	Map<String,Long> shopInit = new HashMap<String,Long>();
    	shopInit.put("shopId", 35l);
    	return shopInit;
    }

    // 订单详情构造进度条
    /**
     * getStatusMap:(订单详情构造进度条). <br/>
     * TODO(状态编码对应的第几个状态 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * @return
     */
    public static Map<String, Integer> getStatusMap() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("01", 1);
        map.put("02", 2);
        map.put("04", 2);
        map.put("05", 3);
        map.put("06", 4);
        map.put("80", 5);
        return map;
    }
    
    
    // 订单详情构造进度条
    /**
     * getStatusList:(订单详情构造进度条). <br/>
     * TODO(进度条对应的点 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * @return
     */
    public static List<String> getStatusList() {
        List<String> list = new ArrayList<String>();
        list.add(0, "01");
        list.add(1, "02");
        list.add(2, "05");
        list.add(3, "06");
        return list;
    }


    /**
     * 验证page 是否为空
     * @param page
     * @return
     * @author huangjx
     */
    public static boolean checkPageNull(PageResponseDTO<?> page) {
        if(page!=null){
             if(!CollectionUtils.isEmpty(page.getResult())){
                 return Boolean.TRUE;
             }
        }
        return Boolean.FALSE;

    }

    /**
     * 支付方式配置
     */
    public static Map<String,String> applyConfig(){
		List<BaseParamDTO> payList = new ParamToolUtil().getParamDTOList("ORD_PAY_TYPE");
    	Map<String,String> m = new HashMap<>();

		for(BaseParamDTO pay: payList){
			m.put(pay.getSpCode(),pay.getSpValue());
		}

    	return m;
    }

    /**
     * 加支付方式返回
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static EcpBasePageRespVO<Map> setApplyType(EcpBasePageRespVO<Map> respVO){
		List<Map> respList = new ArrayList<>();
    	if(!CollectionUtils.isEmpty(respVO.getList())){
    		for(Map m:respVO.getList()){
				Map respM = new HashMap();
    			respM.putAll(m);
                String applyType = "";
                if(!CollectionUtils.isEmpty(m)) applyType = (String)m.get("applyType");
    			respM.put("applyType", applyConfig().get(m.get("applyType")));
    			respList.add(respM);
    		}
    	}
    	respVO.setList(respList);
    	return respVO;
    }

    /**
     *
     * getCmsUrl:(获取站点链接). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     *
     * @author wang
     * @param siteId
     * @return
     * @since JDK 1.6
     */
    @SuppressWarnings("unchecked")
	public static String getSiteUrl(Long siteId){

    	Map<Long,CmsSiteRespDTO> map = (HashMap<Long,CmsSiteRespDTO>) CacheUtil.getItem(CmsConstants.ParamConfig.CMS_SITE_CACHE);

    	if(map!=null&&map.get(siteId)!=null){
    		return map.get(siteId).getSiteUrl();
    	}

    	return "";
    }

    /**
     *
     * setSiteUrl:(给结果集添加站点的链接). <br/>
     * TODO(不同的站点链接前缀 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     *
     * @author wang
     * @param
     * @param
     * @return 给respVO添加一个属性
     * @since JDK 1.6
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static EcpBasePageRespVO<Map> setSiteUrl(EcpBasePageRespVO<Map> respVO, String key_get, String key_put){

    	Map<Long,CmsSiteRespDTO> siteMap = CmsCacheUtil.queryCmsSiteCache();
		List<Map> respList = new ArrayList<>();
    	if(!CollectionUtils.isEmpty(respVO.getList())){
    		for(Map m:respVO.getList()){
				Map respM = new HashMap();
    			respM.putAll(m);
                if(!CollectionUtils.isEmpty(m))
    			respM.put(key_put, siteMap.get(respM.get("siteId")).getSiteUrl()+respM.get(key_get));
    			respList.add(respM);
    		}
    	}
    	respVO.setList(respList);
    	return respVO;
    }

    @SuppressWarnings("rawtypes")
	public static EcpBasePageRespVO<Map> setSiteUrl(EcpBasePageRespVO<Map> respVO){
    	return setSiteUrl(respVO, "gdsUrl",  "gdsUrl");
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static EcpBasePageRespVO<Map> ordDetailSiteUrl(EcpBasePageRespVO<Map> respVO){ 
		List<Map> respList = new ArrayList<>();
    	if(!CollectionUtils.isEmpty(respVO.getList())){
    		for(Map m:respVO.getList()){
				Map respM = new HashMap();
    			respM.putAll(m);
    			String ordDetailUrl = "";
    			if(StringUtil.isNotEmpty(m.get("siteId")) && StringUtil.isNotEmpty(m.get("orderId"))){
    				ordDetailUrl = getOrdDetailUrl(Long.valueOf(m.get("siteId").toString()),(String)m.get("orderId"));
    			}else if(StringUtil.isNotEmpty(m.get("siteId")) && StringUtil.isNotEmpty(m.get("id"))){
    				ordDetailUrl = getOrdDetailUrl(Long.valueOf(m.get("siteId").toString()),(String)m.get("id"));
    			}
    			respM.put("ordDetailUrl", ordDetailUrl);
    			respList.add(respM);
    		}
    	}
    	respVO.setList(respList);
    	return respVO;
    }
    
    /**
     * @author wangxq
     * @param siteId
     * @param orderId
     * @return 根据站点返回旗下的详细订单连接
     */
    public static String getOrdDetailUrl(Long siteId,String orderId){
        //String detailUrl = CmsCacheUtil.getCmsSiteCache(siteId).getSiteUrl()+DETAIL_PREX+"/"+orderId;
        String detailUrl = CmsCacheUtil.getCmsSiteCache(siteId).getSiteUrl()+DETAIL_PREX+"?orderId="+orderId;
        return detailUrl;
    }
    
    public static String MD5(String pwd) {
        //用于加密的字符
        char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            //使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中
            byte[] btInput = pwd.getBytes();

            //信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            //MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
            mdInst.update(btInput);

            // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
            byte[] md = mdInst.digest();

            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {   //  i = 0
                byte byte0 = md[i];  //95
                str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5
                str[k++] = md5String[byte0 & 0xf];   //   F
            }

            //返回经过加密后的字符串
            return new String(str);

        } catch (Exception e) {
            return null;
        }

    }

}

