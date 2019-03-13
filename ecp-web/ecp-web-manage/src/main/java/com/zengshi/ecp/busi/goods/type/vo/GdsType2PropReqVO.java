package com.zengshi.ecp.busi.goods.type.vo;

import java.util.ArrayList;
import java.util.List;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class GdsType2PropReqVO extends EcpBasePageReqVO{
    
    /** 
     * serialVersionUID:
     */ 
    private static final long serialVersionUID = 1L;

    private Long typeId;
    
    private List<Long> propIds;
    
    private String propName;
    
    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
    
    public List<Long> getPropIds() {
        return propIds;
    }

    public void setPropIds(List<Long> propIds) {
        this.propIds = propIds;
    }
    
    public void addPropId(Long propId){
        if(null ==  propId) 
            return ;
        if(null == propIds){
            propIds = new ArrayList<>();
        }
        propIds.add(propId);
        
    }
    
    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

}

