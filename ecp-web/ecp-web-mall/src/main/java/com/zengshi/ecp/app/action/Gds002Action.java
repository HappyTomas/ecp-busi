package com.zengshi.ecp.app.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Gds002Req;
import com.zengshi.ecp.app.resp.Gds002Resp;
import com.zengshi.ecp.app.resp.gds.GdsDetailBaseInfo;
import com.zengshi.ecp.app.resp.gds.GdsSkuBaseInfo;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;
import com.alibaba.fastjson.JSONObject;

import net.sf.json.JSONArray;

/**
 * 相关分类商品
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016年3月10日上午10:18:08  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zjh
 * @version  
 * @since JDK 1.6
 */
@Service("gds002")
@Action(bizcode = "gds002", userCheck = false)
@Scope("prototype")
public class Gds002Action extends AppBaseAction<Gds002Req, Gds002Resp> {
    
    @Resource
    private IGdsInfoQueryRSV gdsInfoQueryRSV;
    
    @Resource
	private IGdsInfoQueryRSV iGdsInfoQueryRSV;
    
    private static final String MODULE = Gds002Action.class.getName();
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        Gds002Req gds002Req = this.request;
        Gds002Resp gds002Resp = this.response; 
//		BasicNameValuePair base = new BasicNameValuePair("skuId","277682");
//		String url ="http://shoptest1.pmph.com:19419/ecp_httpsv/service/recommendRelatedGds";
        
//        GdsInfoReqDTO reqDTO = new GdsInfoReqDTO();
//        reqDTO.setId(gds002Req.getGdsId());
//        reqDTO.setGdsQueryOptions(new GdsQueryOption[]{GdsQueryOption.BASIC,GdsQueryOption.MAINPIC});
//        reqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
//        GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQueryRSV.queryGdsInfoByOption(reqDTO);
//        List<GdsInfoDetailRespDTO> gdsInfoDetailRespDTOs = null;
//        reqDTO.setMainCatgs(gdsInfoRespDTO.getMainCatgs());
//        reqDTO.setId(gds002Req.getGdsId());
//        reqDTO.setPageNo(1);
//        reqDTO.setPageSize(5);
        try {
//            gdsInfoDetailRespDTOs = gdsInfoQueryRSV.queryGdsSkuInfoListByCatgRela(reqDTO);
//        	gdsInfoDetailRespDTOs = gdsInfo;
        	Long gdsId_req = gds002Req.getGdsId();
            Long skuId_req = getSkuId(gdsId_req);
            String url = getAnalysisUrl()+"/service/recommendRelatedGds";
            BasicNameValuePair base = new BasicNameValuePair("skuId",skuId_req.toString());
    		List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
    		formparams.add(base);
    		String json = doRequest(url, formparams);
        	JSONObject jsonObj = JSONObject.parseObject(json);
        	String serviceState =null;
        	if(null!=jsonObj&&null!=jsonObj.get("serviceState")){
        		serviceState = jsonObj.get("serviceState").toString();
        	}
    		List<GdsInfoDetailRespDTO> gdsInfo = new ArrayList<GdsInfoDetailRespDTO>();
    		if("0000".equals(serviceState)){
    			String goodsList = jsonObj.get("goodsList").toString();
    			JSONArray jsonArray = JSONArray.fromObject(goodsList);  
    			int length;
    			if(jsonArray.size()<=5){
    				length=jsonArray.size();
    			}else{
    				length=5;
    			}
    			for(int i=0;i<length;i++){
    				net.sf.json.JSONObject jo= jsonArray.getJSONObject(i); //转换成JSONObject对象
    				String gdsName = jo.get("gdsName").toString();
    				jo.get("gdsStatus");
    				jo.get("mainPicId");
    				String skuId = jo.get("skuId").toString();
    				String gdsId = jo.get("gdsId").toString();
    				String guidePrice = jo.get("guidePrice").toString();
    				String skuProps = jo.get("skuProps").toString();
    				JSONArray jy_skuProps = JSONArray.fromObject(skuProps);
    				Map<String,GdsPropRespDTO> propMap=new HashMap<String,GdsPropRespDTO>();
    				for(int j=0;j<jy_skuProps.size();j++){
    					List<GdsPropValueRespDTO> propValueList = new ArrayList<GdsPropValueRespDTO>();
    					GdsPropRespDTO propResp = new GdsPropRespDTO();
    					GdsPropValueRespDTO propValue = new GdsPropValueRespDTO();
    					net.sf.json.JSONObject jo_prop= jy_skuProps.getJSONObject(j); //转换成JSONObject对象
    					String prop_id =(String) jo_prop.get("prop_id");
    					String prop_name =(String) jo_prop.get("prop_name");
    					String prop_value =(String) jo_prop.get("prop_value");
    					propValue.setPropValue(prop_value);
    					propValueList.add(propValue);
    					propResp.setId(Long.valueOf(prop_id));
    					propResp.setPropName(prop_name);
    					propResp.setValues(propValueList);
    					propMap.put(prop_id, propResp);
    				}
    				GdsInfoDetailRespDTO detailResp = new GdsInfoDetailRespDTO();
    				detailResp.setGdsName(gdsName);
    				detailResp.setGuidePrice(Long.valueOf(guidePrice));
    				GdsSkuInfoRespDTO GdsSkuInfo = new GdsSkuInfoRespDTO();
    				GdsSkuInfo.setGdsId(Long.valueOf(gdsId));
    				GdsSkuInfo.setId(Long.valueOf(skuId));
    				GdsMediaRespDTO mainPic = new GdsMediaRespDTO();
    				mainPic.setMediaUuid(jo.get("mainPicId").toString());
    				GdsSkuInfo.setMainPic(mainPic);
    				GdsSkuInfo.setAllPropMaps(propMap);
    				long discountPrice = getDiscountPrice(Long.valueOf(gdsId),Long.valueOf(skuId));
    				GdsSkuInfo.setDiscountPrice(discountPrice);
    				detailResp.setSkuInfo(GdsSkuInfo);
    				gdsInfo.add(detailResp);
    			} 
    		}
            List<GdsDetailBaseInfo> gdsDetailBaseInfos = new ArrayList<GdsDetailBaseInfo>();
            if (gdsInfo != null) {
                for (GdsInfoDetailRespDTO detailRespDTO : gdsInfo) {
                	GdsDetailBaseInfo gdsDetailBaseInfo = new GdsDetailBaseInfo();
                ObjectCopyUtil.copyObjValue(detailRespDTO, gdsDetailBaseInfo, null, false);
                GdsSkuBaseInfo gdsSkuBaseInfo = new GdsSkuBaseInfo();
                ObjectCopyUtil.copyObjValue(detailRespDTO.getSkuInfo(), gdsSkuBaseInfo, null, false);
                gdsDetailBaseInfo.setGdsSkuBaseInfo(gdsSkuBaseInfo);
                if(detailRespDTO.getSkuInfo().getMainPic() != null){
                gdsDetailBaseInfo.setMainPicUrl(ImageUtil.getImageUrl(detailRespDTO.getSkuInfo().getMainPic().getMediaUuid()));
                }else{
                    gdsDetailBaseInfo.setMainPicUrl(ImageUtil.getImageUrl(null));

                }
                gdsDetailBaseInfo.setParams(null);
                gdsDetailBaseInfos.add(gdsDetailBaseInfo);
                }
                gds002Resp.setCommondCatGds(gdsDetailBaseInfos);

            } else {
                gds002Resp.setCommondCatGds(new ArrayList<GdsDetailBaseInfo>());
            }

        } catch (BusinessException e) {
            LogUtil.error(MODULE, "获取相关分类推荐失败！", e);
            gds002Resp.setCommondCatGds(new ArrayList<GdsDetailBaseInfo>());
        }
    }
    
    public Long getDiscountPrice(long gdsId, long skuId) {
    	Long DiscountPrice = null ;
		GdsInfoReqDTO dto = new GdsInfoReqDTO();
		dto.setId(gdsId);
		dto.setSkuId(skuId);
		GdsQueryOption[] gdsQueryOptions = new GdsQueryOption[] { GdsQueryOption.BASIC, GdsQueryOption.GDSTYPE};
		SkuQueryOption[] skuQueryOptions = new SkuQueryOption[] { SkuQueryOption.BASIC, SkuQueryOption.PROP,SkuQueryOption.MEDIA,
				SkuQueryOption.SHOWSTOCK, SkuQueryOption.CAlDISCOUNT};
		dto.setGdsQueryOptions(gdsQueryOptions);
		dto.setSkuQuerys(skuQueryOptions);
		GdsInfoDetailRespDTO resultDto = null;
		try {
			resultDto = iGdsInfoQueryRSV.queryGdsInfoDetail(dto);
			if (resultDto != null && resultDto.getSkuInfo() != null) {
				// 发送消息

			} else {
				resultDto = new GdsInfoDetailRespDTO();
				GdsSkuInfoRespDTO gdsSkuInfoRespDTO = new GdsSkuInfoRespDTO();
				gdsSkuInfoRespDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_DELETE);
				resultDto.setSkuInfo(gdsSkuInfoRespDTO);
			}
		} catch (BusinessException e) {
			if (resultDto == null || GdsErrorConstants.GdsInfo.ERROR_GOODS_GDSINFO_210005.equals(e.getErrorCode())) {
				resultDto = new GdsInfoDetailRespDTO();
				GdsSkuInfoRespDTO gdsSkuInfoRespDTO = new GdsSkuInfoRespDTO();
				gdsSkuInfoRespDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_DELETE);
				resultDto.setSkuInfo(gdsSkuInfoRespDTO);
			}
			LogUtil.error(MODULE, "无法获取商品折后价！", e);
		}
		if (StringUtil.isNotEmpty(resultDto)) {
			DiscountPrice = resultDto.getSkuInfo().getDiscountPrice();
		}
		return DiscountPrice;
	}
    
    public Long getSkuId(long gdsId) {
    	Long skuId = null ;
		GdsInfoReqDTO dto = new GdsInfoReqDTO();
		dto.setId(gdsId);
		GdsQueryOption[] gdsQueryOptions = new GdsQueryOption[] { GdsQueryOption.BASIC, GdsQueryOption.GDSTYPE};
		SkuQueryOption[] skuQueryOptions = new SkuQueryOption[] { SkuQueryOption.BASIC, SkuQueryOption.PROP,SkuQueryOption.MEDIA,
				SkuQueryOption.SHOWSTOCK, SkuQueryOption.CAlDISCOUNT};
		dto.setGdsQueryOptions(gdsQueryOptions);
		dto.setSkuQuerys(skuQueryOptions);
		GdsInfoDetailRespDTO resultDto = null;
		try {
			resultDto = iGdsInfoQueryRSV.queryGdsInfoDetail(dto);
			if (resultDto != null && resultDto.getSkuInfo() != null) {
				// 发送消息

			} else {
				resultDto = new GdsInfoDetailRespDTO();
				GdsSkuInfoRespDTO gdsSkuInfoRespDTO = new GdsSkuInfoRespDTO();
				gdsSkuInfoRespDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_DELETE);
				resultDto.setSkuInfo(gdsSkuInfoRespDTO);
			}
		} catch (BusinessException e) {
			if (resultDto == null || GdsErrorConstants.GdsInfo.ERROR_GOODS_GDSINFO_210005.equals(e.getErrorCode())) {
				resultDto = new GdsInfoDetailRespDTO();
				GdsSkuInfoRespDTO gdsSkuInfoRespDTO = new GdsSkuInfoRespDTO();
				gdsSkuInfoRespDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_DELETE);
				resultDto.setSkuInfo(gdsSkuInfoRespDTO);
			}
			LogUtil.error(MODULE, "无法获取商品折后价！", e);
		}
		if (StringUtil.isNotEmpty(resultDto)) {
			skuId = resultDto.getSkuInfo().getId();
		}
		return skuId;
	}
    
    public static String doRequest(String url, List<BasicNameValuePair> formparams) {
        LogUtil.info(MODULE, "httpclient请求开始");
        // 1 入参验证
        if (StringUtil.isBlank(url)) {
            LogUtil.error(MODULE, "httpclient请求结束：地址为空");
            return null;
        }
        if (CollectionUtils.isEmpty(formparams)) {
            formparams = new ArrayList<BasicNameValuePair>();
        }

        CloseableHttpClient httpclient = null;
        HttpPost httppost = null;
        CloseableHttpResponse response = null;
        String result = null;
        // 处理请求
        try {
            // 创建默认的httpClient实例
            httpclient = HttpClients.createDefault();
            // 创建httppost
            httppost = new HttpPost(url);
            // 设置请求实体
            httppost.setEntity(new UrlEncodedFormEntity(formparams, "UTF-8"));
            // 执行请求
            response = httpclient.execute(httppost);
            //处理结果
            if(response!=null && response.getStatusLine() !=null){//请求成功
               LogUtil.info(MODULE, "httpclient请求返回状态码："
                       + response.getStatusLine().getStatusCode()); 
               HttpEntity entity = response.getEntity();  
               result = EntityUtils.toString(entity);
            }
           
        } catch (ClientProtocolException e) {
            LogUtil.error(MODULE, "远程服务协议异常", e);
        } catch (IOException e) {
            LogUtil.error(MODULE, "远程服务连接异常", e);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, e.getMessage(), e);
        } catch (Exception e) {
            LogUtil.error(MODULE, "调用远程服务未知异常", e);
        } finally {
            // 关闭连接,释放资源
            try {
                if(response != null){
                    try { 
                        response.close();
                    }
                    catch (Exception e) {  
                        e.printStackTrace();
                    } 
                }
                if(httppost != null){
                    try { 
                        httppost.releaseConnection();
                    }
                    catch (Exception e) {  
                        e.printStackTrace();
                    } 
                }
                if(httppost!=null){
                    try { 
                        httppost.abort();
                    }
                    catch (Exception e) {  
                        e.printStackTrace();
                    } 
                }
                if(httpclient !=null){
                    try { 
                        httpclient.close(); 
                    }
                    catch (Exception e) {  
                        e.printStackTrace();
                    } 
                }  
            } catch (Exception e) {
                LogUtil.error(MODULE, "关闭httpclient连接异常", e);
            }
        }
        LogUtil.info(MODULE, "httpclient请求结束");
        return result;
    }
    
    private static String getAnalysisUrl() {
        LogUtil.info(MODULE, "获取行为分析地址开始");
        BaseSysCfgRespDTO XwAnaUrlDto = null;
        try {
            XwAnaUrlDto = SysCfgUtil.fetchSysCfg("XW_ANAL_SYS_URL");
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取用户行为分析地址结束：异常", e);
            return null;
        }

        if (XwAnaUrlDto == null || StringUtil.isBlank(XwAnaUrlDto.getParaValue())) {
            LogUtil.error(MODULE, "用户行为分析地址结束：未配置：常量库字段为XW_ANAL_SYS_URL");
            return null;
        }
        String url = XwAnaUrlDto.getParaValue();
        LogUtil.info(MODULE, "行为分析地址结束：成功 地址为" + url);

        return url;
    }

}
