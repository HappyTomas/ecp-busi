package com.zengshi.aip.provider.appkey.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.aip.dao.mapper.common.AipAppInfoMapper;
import com.zengshi.ecp.aip.dao.mapper.common.AipSvLogMapper;
import com.zengshi.ecp.aip.dao.model.AipAppInfo;
import com.zengshi.ecp.aip.dao.model.AipAppInfoCriteria;
import com.zengshi.ecp.aip.dao.model.AipSvLogWithBLOBs;
import com.zengshi.aip.provider.appkey.VerifyAppkeyService;

public class VerfiyAppkeyServiceImpl implements VerifyAppkeyService {
	private final static Logger logger = LoggerFactory.getLogger(VerfiyAppkeyServiceImpl.class);
	
	@Autowired
	private AipAppInfoMapper aipAppInfoDAO;
	@Autowired
	AipSvLogMapper aipSvLogDAO;

	@Override
	public boolean isPermission(String appkey) throws Exception {
		AipAppInfo aipAppInfo = null;
		String resultMsg = "";// 异常信息
		AipSvLogWithBLOBs tAipSvLogWithBLOBs = new AipSvLogWithBLOBs();
		String contents = String.format("AppKey  ：%s", appkey);
		logger.info(contents);
		Timestamp requestTime = new Timestamp(new Date().getTime());
		tAipSvLogWithBLOBs.setAppKey(appkey);
		tAipSvLogWithBLOBs.setServiceName(appkey);
		tAipSvLogWithBLOBs.setRequest(contents);
		tAipSvLogWithBLOBs.setRequestTime(requestTime);
		try {
			if (StringUtils.isBlank(appkey)) {
				throw new Exception("AppKey为空");
			}
			logger.debug(appkey);
			aipAppInfo = aipAppInfoDAO.selectByPrimaryKey(appkey);

		} catch (Exception e) {
			logger.error("appkey验证异常", e);
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

		if (aipAppInfo == null)
			return false;

		return true;
	}

	@Override
	public boolean verifyAppSecret(String appKey, String appSecret)
			throws Exception {
		List<AipAppInfo> resultList= null;
		String resultMsg = "";// 异常信息
		AipSvLogWithBLOBs tAipSvLogWithBLOBs = new AipSvLogWithBLOBs();
		String contents = String.format("AppKey  ：%s，AppSecret  ：%s", appKey,appSecret);
		logger.info(contents);
		Timestamp requestTime = new Timestamp(new Date().getTime());
		tAipSvLogWithBLOBs.setAppKey(appKey);
		tAipSvLogWithBLOBs.setServiceName(appKey);
		tAipSvLogWithBLOBs.setRequest(contents);
		tAipSvLogWithBLOBs.setRequestTime(requestTime);
		try {
			if (StringUtils.isBlank(appKey)||StringUtils.isBlank(appSecret)) {
				throw new Exception("AppKey或为空AppSecret");
			}
			logger.debug(appKey,appSecret);
			AipAppInfoCriteria example=new AipAppInfoCriteria();
			AipAppInfo tAipAppInfo=new AipAppInfo();
			tAipAppInfo.setAppKey(appKey);
			tAipAppInfo.setAppSecret(appSecret);
			example=getCondition(tAipAppInfo);
			resultList=aipAppInfoDAO.selectByExample(example);
		} catch (Exception e) {
			logger.error("appkey验证异常", e);
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

		if (resultList == null)
			return false;

		return true;
	}

	/**
	 * 
	 * @Title: getCondition
	 * @Description:取得查询条件
	 * @author: luocan
	 * @Create: 2014年11月12日 下午3:39:38
	 * @Modify: 2014年11月12日 下午3:39:38
	 * @param:  AipAppInfo
	 * @return: AipAppInfoExample
	 */
	private AipAppInfoCriteria getCondition(AipAppInfo aipAppInfo) {
	    AipAppInfoCriteria where = new AipAppInfoCriteria();
	    AipAppInfoCriteria.Criteria cr = where.createCriteria();
		if (!StringUtils.isBlank(aipAppInfo.getAppKey())) {
			cr.andAppKeyEqualTo(aipAppInfo.getAppKey());
		}
		if (!StringUtils.isBlank(aipAppInfo.getAppSecret())) {
			cr.andAppSecretEqualTo(aipAppInfo.getAppSecret());
		}

		return where;
	}
}
