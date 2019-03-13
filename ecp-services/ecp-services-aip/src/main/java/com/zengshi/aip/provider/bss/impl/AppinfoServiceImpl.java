package com.zengshi.aip.provider.bss.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.aip.dao.mapper.common.AipAppInfoMapper;
import com.zengshi.ecp.aip.dao.mapper.common.AipSvLogMapper;
import com.zengshi.ecp.aip.dao.model.AipAppInfo;
import com.zengshi.ecp.aip.dao.model.AipSvLogWithBLOBs;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.aip.provider.bss.AppinfoService;
import com.zengshi.aip.provider.bss.model.appinfo.AipAppInfoResult;

public class AppinfoServiceImpl implements AppinfoService {
	private final static Logger logger = LoggerFactory.getLogger(AppinfoServiceImpl.class);
	

	@Autowired
	private AipAppInfoMapper aipAppInfoDAO;
	@Autowired
	AipSvLogMapper aipSvLogDAO;

	@Override
	public AipAppInfoResult getAppinfoByAppkey(String appkey) throws Exception {
		AipAppInfo aipAppInfo = null;
		AipAppInfoResult aipAppInfoResult = new AipAppInfoResult();
		String resultMsg = "";// 异常信息
		AipSvLogWithBLOBs tAipSvLogWithBLOBs = new AipSvLogWithBLOBs();
		String contents = String.format("#AppKey=%s", appkey);
		logger.info(contents);
		Timestamp requestTime = new Timestamp(new Date().getTime());
		tAipSvLogWithBLOBs.setAppKey(appkey);
		tAipSvLogWithBLOBs.setServiceName(appkey);
		tAipSvLogWithBLOBs.setRequest(contents);
		tAipSvLogWithBLOBs.setRequestTime(requestTime);
		try {
			if (StringUtils.isBlank(appkey)) 
				throw new Exception("AppKey为空");
			logger.debug(appkey);
			aipAppInfo = aipAppInfoDAO.selectByPrimaryKey(appkey);
			if (aipAppInfo != null) {
			    ObjectCopyUtil.copyObjValue(aipAppInfo, aipAppInfoResult, "", true);
				resultMsg = aipAppInfoResult.toString();
				return aipAppInfoResult;
			} else {
				return null;
			}

		} catch (Exception e) {
			logger.error("根据appkey获取Appinfo异常", e);
			Writer result = new StringWriter();
			PrintWriter printWriter = new PrintWriter(result);
			e.printStackTrace(printWriter);
			// 将异常信息记录至日志表中
			resultMsg = result.toString();
			throw e;

		} finally {
			Timestamp responseTime = new Timestamp(new Date().getTime());
			tAipSvLogWithBLOBs.setResponseTime(responseTime);
			tAipSvLogWithBLOBs.setResponse(resultMsg);
			aipSvLogDAO.insert(tAipSvLogWithBLOBs);
		}
	}

}
