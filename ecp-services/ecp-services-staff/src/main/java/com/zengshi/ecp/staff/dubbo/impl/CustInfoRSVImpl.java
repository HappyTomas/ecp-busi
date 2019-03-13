package com.zengshi.ecp.staff.dubbo.impl;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.CustAuthstrReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustEmailLogInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.custinfo.interfaces.ICustInfoSV;
import com.zengshi.ecp.sys.dubbo.dto.ReportItemReqDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IReportItemRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.paas.utils.ThreadId;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: dubbo层 会员信息服务接口实现类<br>
 * Date:2015-8-24下午7:25:21  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public class CustInfoRSVImpl implements ICustInfoRSV{

    @Resource
    private ICustInfoSV custInfoSV;
    
    @Resource
    private IReportItemRSV iReportItemRSV;
    
    private static Logger logger = LoggerFactory.getLogger(CustInfoRSVImpl.class);
    
    private static final String MODULE = CustInfoRSVImpl.class.getName();
    
    @Override
    public int saveCustAuthstr(CustAuthstrReqDTO authstr) throws BusinessException {
        int result = 0;
        try {
            result = custInfoSV.saveCustAuthstr(authstr);
        } catch (Exception e) {
            logger.error("ThreadId:["+ThreadId.getThreadId()+"];保存异常：", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR,new String[]{"T_CUST_INFO"});
        }
        return result;
    }

    @Override
    public long saveCustEmailLog(CustEmailLogInfoReqDTO logInfo) throws BusinessException {
        long result = 0;
        try {
            result = custInfoSV.saveCustEmailLog(logInfo);
        } catch (Exception e) {
            logger.error("ThreadId:["+ThreadId.getThreadId()+"];保存异常：", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR,new String[]{"T_CUST_EMAIL_LOG"});
        }
        return result;
    }

    @Override
    public int updateCustEmailLog(CustEmailLogInfoReqDTO logInfo) throws BusinessException {
        int result = 0;
        try {
            result = custInfoSV.updateCustEmailLog(logInfo);
        } catch (Exception e) {
            logger.error("ThreadId:["+ThreadId.getThreadId()+"];更新异常：", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR,new String[]{"T_CUST_EMAIL_LOG"});
        }
        return result;
    }

    @Override
    public int updateCustInfo(CustInfoReqDTO custInfo) throws BusinessException {
        if (custInfo == null) {
            LogUtil.error(MODULE, "入参对象不能为空！");
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"入参对象"});
        }
        if (StringUtil.isBlank(custInfo.getNickname())) {
            LogUtil.error(MODULE, "昵称不能为空！");
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"昵称"});
        }
      /*  if (StringUtil.isEmpty(custInfo.getCustBirthday())) {
            LogUtil.error(MODULE, "生日不能为空！");
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"生日"});
        }*/
      /*  if (StringUtil.isEmpty(custInfo.getCustName())) {
            LogUtil.error(MODULE, "真实姓名不能为空！");
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"真实姓名"});
        }*/
        int result = 0;
        try {
            result = custInfoSV.updateCustInfo(custInfo);
        }catch(BusinessException err){
            logger.error("ThreadId:["+ThreadId.getThreadId()+"];更新异常：", err);
            throw err;
        }catch (Exception e) {
            logger.error("ThreadId:["+ThreadId.getThreadId()+"];更新异常：", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR,new String[]{"T_CUST_INFO"});
        }
        return result;
    }

    @Override
    public int saveAuthStaffEIDX(CustInfoReqDTO custInfo) throws BusinessException {
        int result = 0;
        try {
            result = custInfoSV.saveAuthStaffEIDX(custInfo);
        } catch (Exception e) {
            logger.error("ThreadId:["+ThreadId.getThreadId()+"];保存异常：", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR,new String[]{"T_AUTH_STAFF_E_IDX"});
        }
        return result;
    }

    @Override
    public int saveAuthStaffMIDX(CustInfoReqDTO custInfo) throws BusinessException {
        int result = 0;
        try {
            result = custInfoSV.saveAuthStaffMIDX(custInfo);
        } catch (Exception e) {
            logger.error("ThreadId:["+ThreadId.getThreadId()+"];保存异常：", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR,new String[]{"T_AUTH_STAFF_M_IDX"});
        }
        return result;
    }

    @Override
    public int saveCustPic(CustInfoReqDTO req)
            throws BusinessException {
    
        int result = 0;
        try {
            result = custInfoSV.saveCustPic(req);
        } catch (Exception e) {
            logger.error("ThreadId:["+ThreadId.getThreadId()+"];文件保存异常：", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR,new String[]{"mongodb"});
        }
        return result;
    }

    @Override
    public byte[] readFile(String fileId) throws BusinessException {
        if (StringUtil.isBlank(fileId)) {
            LogUtil.error(MODULE, "文件流不能为空！");
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"文件流"});
        }
        byte[] fileByte = new byte[]{};
        try {
            fileByte = custInfoSV.readFile(fileId);
        } catch (Exception e) {
            logger.error("ThreadId:["+ThreadId.getThreadId()+"];文件读取异常：", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR,new String[]{"mongodb"});
        }
        return fileByte;
    }

    @Override
    public void deleteFileById(String fileId) throws BusinessException {
        if (StringUtil.isBlank(fileId)) {
            LogUtil.error(MODULE, "文件ID不能为空！");
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"文件ID"});
        }
        try {
            custInfoSV.deleteFileById(fileId);
        } catch (Exception e) {
            logger.error("ThreadId:["+ThreadId.getThreadId()+"];文件删除异常：", e);
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR,new String[]{"mongodb"});
        }
        
    }

    @Override
    public CustInfoResDTO getCustInfoById(CustInfoReqDTO custInfoReqDTO) throws BusinessException {
        if(custInfoReqDTO == null || null==custInfoReqDTO.getId() || 0==custInfoReqDTO.getId()){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"custInfoReqDTO:id"});
        }
        
        CustInfoResDTO dto =new CustInfoResDTO();
        try {
            dto =  custInfoSV.getCustInfoById(custInfoReqDTO);
        } catch (Exception e) {
            logger.error("查询用户异常", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR,new String[]{"t_cust_info"});
        }
        
        return dto;
    }

	@Override
	public void getCustNewCount(CustInfoReqDTO custInfoReqDTO) throws BusinessException {
		
		BaseSysCfgRespDTO baseSysCfgRespDTO = SysCfgUtil.fetchSysCfg("SYS_ADMIN_ITEM_SOURCE");		
		String value = baseSysCfgRespDTO.getParaValue();
		int day = custInfoReqDTO.getCustDayTime();
		if(0==day){
			day=1;
		}
		Date dateday = DateUtil.getOffsetDaysDate(DateUtil.getSysDate(), -day);
		if(StringUtil.isNotBlank(value)&&"2".equals(value)){
		long count = custInfoSV.getCustNewCount(custInfoReqDTO);
		ReportItemReqDTO dto = new ReportItemReqDTO();
		dto.setItemDesc("会员新增数");
		dto.setShopId(-1L);
		dto.setItemCode("ITEM_ORD_NEW_MEMBER");
		dto.setItemValue(String.valueOf(count));
		dto.setItemSource("2");
		dto.setStatus(StaffConstants.PublicParam.STATUS_ACTIVE);
		dto.setCalDate(new Timestamp(dateday.getTime()));
		dto.setCreateTime(DateUtil.getSysDate());
		
		dto.setCreateStaff(dto.getStaff().getId());
		iReportItemRSV.addReportItem(dto);
		}else{
			String urlPart = "/service/dailyMemberAnaly";//交易分析
			
			//CmsSiteRespDTO anaSiteDto = null;
			Map<String,String> map = new HashMap<String,String>();
	       // anaSiteDto = CmsCacheUtil.getCmsSiteCache(Long.valueOf("6"));//站点：用户分析
	        BaseSysCfgRespDTO sysconfig =  SysCfgUtil.fetchSysCfg("XW_ANAL_SYS_URL");
	        if(sysconfig == null || StringUtil.isBlank(sysconfig.getParaValue())){
	        	LogUtil.error(MODULE, "站点未配置");
	        }
			// 创建默认的httpClient实例
	        CloseableHttpClient httpclient = HttpClients.createDefault(); 
	        // 创建httppost 
	        HttpPost httppost = new HttpPost(sysconfig.getParaValue()+urlPart);
	        // 创建参数队列
	        List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
	        formparams.add(new BasicNameValuePair("format","json"));//
	        formparams.add(new BasicNameValuePair("certainDate",DateUtil.getDateString(dateday, "yyyyMMdd")));//开始时间 
	        //formparams.add(new BasicNameValuePair("endDate",DateUtil.getDateString(DateUtil.getSysDate(), "yyyyMMdd")));//结束时间
	        
			//发请求
	        UrlEncodedFormEntity uefEntity; 
	        try {  
	            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
	            httppost.setEntity(uefEntity);
	            CloseableHttpResponse response = httpclient.execute(httppost);
	            try {  
	                if(response.getStatusLine() !=null && response.getStatusLine().getStatusCode()==200){//请求成功
	                    HttpEntity entity = response.getEntity();  
	                    if (entity != null && entity.getContent() !=null) {  
	                        // 处理响应内容    
	                        String strResult = EntityUtils.toString(entity);
	                        JSONObject myJsonObject = JSONObject.fromObject(strResult);
	                        if(myJsonObject.get("serviceState").equals("0000")){
	                        	String jString =  myJsonObject.getString("dataList");
	                        	jString = jString.substring(1, jString.length()-1);
	                        	JSONObject j = JSONObject.fromObject(jString);
	                        	long count = j.getLong("memberCount");
	                        	ReportItemReqDTO dto = new ReportItemReqDTO();
	                    		dto.setItemDesc("会员新增数");
	                    		dto.setShopId(-1L);
	                    		dto.setItemCode("ITEM_ORD_NEW_MEMBER");
	                    		dto.setItemValue(String.valueOf(count));
	                    		dto.setItemSource("1");
	                    		dto.setStatus(StaffConstants.PublicParam.STATUS_ACTIVE);
	                    		dto.setCalDate(new Timestamp(dateday.getTime()));
	                    		dto.setCreateTime(DateUtil.getSysDate());
	                    		dto.setCreateStaff(dto.getStaff().getId());
	                    		iReportItemRSV.addReportItem(dto);
	                        }
	                    }  
	                }else{
	                    throw new BusinessException(response.getStatusLine().toString());
	                }
	            } finally {  
	                response.close();  
	            }
	        }catch (ClientProtocolException e) {
	            LogUtil.error(MODULE, "远程服务协议异常", e);
	        }catch (IOException e) {  
	            LogUtil.error(MODULE, "远程服务连接异常", e);
	        }catch (BusinessException e){
	            LogUtil.error(MODULE, e.getMessage(), e);
	        }catch(Exception e){
	            LogUtil.error(MODULE, "调用远程交易分析服务未知异常", e);
	        }
	        finally {  
	            // 关闭连接,释放资源    
	            try {  
	                httpclient.close();  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        } 
		}
		
	}

}

