package com.zengshi.ecp.busi.solr.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class SolrConfigVO extends EcpBasePageReqVO{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -441720222911369998L;
    
    private Long id;

    private String configName;

    private String configDesc;

    private String configCollectionName;

    private String configQueryOp;

    private String configQueryIfHl;

    private String configQueryHlPre;

    private String configQueryHlPost;

    private String configQueryIfSpellcheck;

    private Short configQuerySpellcheckCount;

    private String collectionStatus;

    private String indexStatus;

    private String configIfActive;

    private String status;
    
    private String configIfMultilan;
    
    private String lans;
    
    private String configParams;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigDesc() {
        return configDesc;
    }

    public void setConfigDesc(String configDesc) {
        this.configDesc = configDesc;
    }

    public String getConfigCollectionName() {
        return configCollectionName;
    }

    public void setConfigCollectionName(String configCollectionName) {
        this.configCollectionName = configCollectionName;
    }

    public String getConfigQueryOp() {
        return configQueryOp;
    }

    public void setConfigQueryOp(String configQueryOp) {
        this.configQueryOp = configQueryOp;
    }

    public String getConfigQueryIfHl() {
        return configQueryIfHl;
    }

    public void setConfigQueryIfHl(String configQueryIfHl) {
        this.configQueryIfHl = configQueryIfHl;
    }

    public String getConfigQueryHlPre() {
        return configQueryHlPre;
    }

    public void setConfigQueryHlPre(String configQueryHlPre) {
        this.configQueryHlPre = configQueryHlPre;
    }

    public String getConfigQueryHlPost() {
        return configQueryHlPost;
    }

    public void setConfigQueryHlPost(String configQueryHlPost) {
        this.configQueryHlPost = configQueryHlPost;
    }

    public String getConfigQueryIfSpellcheck() {
        return configQueryIfSpellcheck;
    }

    public void setConfigQueryIfSpellcheck(String configQueryIfSpellcheck) {
        this.configQueryIfSpellcheck = configQueryIfSpellcheck;
    }

    public Short getConfigQuerySpellcheckCount() {
        return configQuerySpellcheckCount;
    }

    public void setConfigQuerySpellcheckCount(Short configQuerySpellcheckCount) {
        this.configQuerySpellcheckCount = configQuerySpellcheckCount;
    }

    public String getCollectionStatus() {
        return collectionStatus;
    }

    public void setCollectionStatus(String collectionStatus) {
        this.collectionStatus = collectionStatus;
    }

    public String getIndexStatus() {
        return indexStatus;
    }

    public void setIndexStatus(String indexStatus) {
        this.indexStatus = indexStatus;
    }

    public String getConfigIfActive() {
        return configIfActive;
    }

    public void setConfigIfActive(String configIfActive) {
        this.configIfActive = configIfActive;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public String getConfigIfMultilan() {
		return configIfMultilan;
	}

	public void setConfigIfMultilan(String configIfMultilan) {
		this.configIfMultilan = configIfMultilan;
	}

	public String getLans() {
		return lans;
	}

	public void setLans(String lans) {
		this.lans = lans;
	}

	public String getConfigParams() {
		return configParams;
	}

	public void setConfigParams(String configParams) {
		this.configParams = configParams;
	}
    
}

