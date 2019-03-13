package com.zengshi.ecp.goods.dubbo.dto.category.dataimport;

import com.zengshi.ecp.goods.dubbo.constants.GdsDataImportConstants;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
import com.zengshi.paas.utils.StringUtil;

public class GdsInterfaceCatgRespDTO extends BaseResponseDTO {

	private static final long serialVersionUID = -1229606645837651868L;

	private Long id;

    private String catgCode;

    private String originCatgCode;

    private String originCatgName;

    private String origin;

    private String status;
    
    private String updateRule;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode == null ? null : catgCode.trim();
    }

    public String getOriginCatgCode() {
        return originCatgCode;
    }

    public void setOriginCatgCode(String originCatgCode) {
        this.originCatgCode = originCatgCode == null ? null : originCatgCode.trim();
    }

    public String getOriginCatgName() {
        return originCatgName;
    }

    public void setOriginCatgName(String originCatgName) {
        this.originCatgName = originCatgName == null ? null : originCatgName.trim();
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	public String getUpdateRule() {
		return updateRule;
	}

	public void setUpdateRule(String updateRule) {
		this.updateRule = updateRule;
	}



}
