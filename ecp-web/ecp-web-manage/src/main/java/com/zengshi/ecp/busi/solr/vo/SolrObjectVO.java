package com.zengshi.ecp.busi.solr.vo;

import java.util.List;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class SolrObjectVO extends EcpBasePageReqVO{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -441720222911369990L;
    
    private Long id;
    
    private Long configId;
    
    private String objectNamecn;

    private String objectNameen;

    private String objectDesc;

    private String objectUniquefieldName;

    private String objectProcessorName;

    private String dubboServicename;

    private String objectParams;

    private String methodQuerybyidParamType;

    private String objectType;

    private String status;

    private Long createStaff;
    
    private String pagesize;
    
    private Long updateStaff;

    private String objectHandler;

    private String objectInspector;

    private String insidepager;

    private String insidepagerUniquefieldName;
  /*  
    private String objectIfMultilan;
    
    private String lans;*/
    
    private String fieldStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjectNamecn() {
        return objectNamecn;
    }

    public void setObjectNamecn(String objectNamecn) {
        this.objectNamecn = objectNamecn == null ? null : objectNamecn.trim();
    }

    public String getObjectNameen() {
        return objectNameen;
    }

    public void setObjectNameen(String objectNameen) {
        this.objectNameen = objectNameen == null ? null : objectNameen.trim();
    }

    public String getObjectDesc() {
        return objectDesc;
    }

    public void setObjectDesc(String objectDesc) {
        this.objectDesc = objectDesc == null ? null : objectDesc.trim();
    }

    public String getObjectUniquefieldName() {
        return objectUniquefieldName;
    }

    public void setObjectUniquefieldName(String objectUniquefieldName) {
        this.objectUniquefieldName = objectUniquefieldName == null ? null : objectUniquefieldName.trim();
    }

    public String getObjectProcessorName() {
        return objectProcessorName;
    }

    public void setObjectProcessorName(String objectProcessorName) {
        this.objectProcessorName = objectProcessorName == null ? null : objectProcessorName.trim();
    }

    public String getDubboServicename() {
        return dubboServicename;
    }

    public void setDubboServicename(String dubboServicename) {
        this.dubboServicename = dubboServicename == null ? null : dubboServicename.trim();
    }

    public String getObjectParams() {
        return objectParams;
    }

    public void setObjectParams(String objectParams) {
        this.objectParams = objectParams == null ? null : objectParams.trim();
    }

    public String getMethodQuerybyidParamType() {
        return methodQuerybyidParamType;
    }

    public void setMethodQuerybyidParamType(String methodQuerybyidParamType) {
        this.methodQuerybyidParamType = methodQuerybyidParamType == null ? null : methodQuerybyidParamType.trim();
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType == null ? null : objectType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }


    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public String getObjectHandler() {
        return objectHandler;
    }

    public void setObjectHandler(String objectHandler) {
        this.objectHandler = objectHandler == null ? null : objectHandler.trim();
    }

    public String getObjectInspector() {
        return objectInspector;
    }

    public void setObjectInspector(String objectInspector) {
        this.objectInspector = objectInspector == null ? null : objectInspector.trim();
    }

    public String getInsidepager() {
        return insidepager;
    }

    public void setInsidepager(String insidepager) {
        this.insidepager = insidepager == null ? null : insidepager.trim();
    }

    public String getInsidepagerUniquefieldName() {
        return insidepagerUniquefieldName;
    }

    public void setInsidepagerUniquefieldName(String insidepagerUniquefieldName) {
        this.insidepagerUniquefieldName = insidepagerUniquefieldName == null ? null : insidepagerUniquefieldName.trim();
    }

	public String getFieldStr() {
		return fieldStr;
	}

	public void setFieldStr(String fieldStr) {
		this.fieldStr = fieldStr;
	}

	public Long getConfigId() {
		return configId;
	}

	public void setConfigId(Long configId) {
		this.configId = configId;
	}

	public String getPagesize() {
		return pagesize;
	}

	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}
}

