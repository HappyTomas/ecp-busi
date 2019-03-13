package com.zengshi.ecp.search.dubbo.search.result;

import java.io.Serializable;
import java.util.List;

import com.zengshi.ecp.search.dubbo.search.Field;
import com.zengshi.ecp.search.dubbo.search.result.FieldFacetResult.Count;

public class FieldFacetResult extends ResultField<List<Count>>{
    
    private static final long serialVersionUID = 1L;

    public FieldFacetResult(String beanFieldName, String nameCn, List<Count> countList) {
        this.setName(beanFieldName);
        this.setNameCn(nameCn);
        this.setValue(countList);
    }
    
    public long getTotallyGroupCount() {
        return this.getValue() == null ? 0 : this.getValue().size();
    }
    
    public static class Count implements Serializable{
        private static final long serialVersionUID = 4052186733888606390L;
        private String value = null;
        //用于处理有些特殊字段的facet（categoryCodeAndValues或columnCodeAndValues等等），能够直接在数据结构上
        //直接支持做类似处理，前店无需再自定义数据结构
        private String valueId=null;
        private long count = 0;
        public Count(String v, long c ){
            value = v;
            count = c;
        }

        public Count(String v,String vId, long c ){
            value = v;
            valueId=vId;
            count = c;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getValueId() {
            return valueId;
        }

        public void setValueId(String valueId) {
            this.valueId = valueId;
        }

        public long getCount() {
            return count;
        }
        
        public void setCount(long count) {
            this.count = count;
        }
      
    }

}

