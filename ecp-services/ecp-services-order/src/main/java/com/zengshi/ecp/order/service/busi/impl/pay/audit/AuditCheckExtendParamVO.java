package com.zengshi.ecp.order.service.busi.impl.pay.audit;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zengshi.ecp.order.dubbo.util.PayHelper;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.exception.BusinessException;
/**
 * 清算任务扩展参数
 * @author zhujl 2014-9-18 下午7:33:42
 */
public class AuditCheckExtendParamVO extends BaseInfo{
	
	/** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;

    private List<Timestamp> qsDates;
	
	private boolean ignoreRemoteDownloadQsFile;
	
	private boolean ignoreSaveQsFile;
	
	private boolean ignoreTransferData;
	
	public static String QsDates = "QsDates";
  	
  	//忽略远程下载文件步骤
  	public static String IgnoreRemoteDownloadQsFile = "IgnoreRemoteDownloadQsFile";
  	
  	//忽略解析文件入库
  	public static String IgnoreSaveQsFile= "IgnoreSaveQsFile";
  	
  	//忽略明细数据迁移到清算报表
  	public static String IgnoreTransferData= "IgnoreTransferData";

	public void buildSelf(Map<String, String> extendParams)throws BusinessException{
		try{
			if(extendParams == null || extendParams.size() == 0){
				this.setQsDates(new ArrayList<Timestamp>());
				return ;
			}
			this.setQsDates(PayHelper.parseQsdateAsTimestamp(extendParams));
			this.setIgnoreRemoteDownloadQsFile("true".equalsIgnoreCase(extendParams.get(IgnoreRemoteDownloadQsFile))?true:false);
			this.setIgnoreSaveQsFile("true".equalsIgnoreCase(extendParams.get(IgnoreSaveQsFile))?true:false);
			this.setIgnoreTransferData("true".equalsIgnoreCase(extendParams.get(IgnoreTransferData))?true:false);
		}catch(Exception e){
//			throw new BusinessException(QuartzJob.PARSE_EXTENDS_PARAM_ERROR,e.getMessage()+",extendParams="+extendParams);
		}
		
	}

	public List<Timestamp> getQsDates() {
		return qsDates;
	}

	public void setQsDates(List<Timestamp> qsDates) {
		this.qsDates = qsDates;
	}

	public boolean isIgnoreRemoteDownloadQsFile() {
		return ignoreRemoteDownloadQsFile;
	}

	public void setIgnoreRemoteDownloadQsFile(boolean ignoreRemoteDownloadQsFile) {
		this.ignoreRemoteDownloadQsFile = ignoreRemoteDownloadQsFile;
	}

	public boolean isIgnoreSaveQsFile() {
		return ignoreSaveQsFile;
	}

	public void setIgnoreSaveQsFile(boolean ignoreSaveQsFile) {
		this.ignoreSaveQsFile = ignoreSaveQsFile;
	}

	public boolean isIgnoreTransferData() {
		return ignoreTransferData;
	}

	public void setIgnoreTransferData(boolean ignoreTransferData) {
		this.ignoreTransferData = ignoreTransferData;
	}

}
