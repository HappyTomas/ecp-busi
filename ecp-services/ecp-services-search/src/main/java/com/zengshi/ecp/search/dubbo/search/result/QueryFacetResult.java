package com.zengshi.ecp.search.dubbo.search.result;

import java.io.Serializable;
import java.util.List;

import com.zengshi.ecp.search.dubbo.search.Field;
import com.zengshi.ecp.search.dubbo.search.result.QueryFacetResult.Count;

public class QueryFacetResult extends ResultField<List<Count>>{

    private static final long serialVersionUID = 1L;
    
    public QueryFacetResult(String beanFieldName, String nameCn) {
        this.setName(beanFieldName);
        this.setNameCn(nameCn);
    }

    public void addCount(Count count) {
        this.getValue().add(count);
    }
    
    public static class Count implements Serializable{
        private static final long serialVersionUID = 1L;

        private String start;

        private String end;

        private int count;

        public Count(String start, String end, int count) {
            this.start = start;
            this.end = end;
            this.count = count;
        }

        public String getStart() {
            return start;
        }

        public String getEnd() {
            return end;
        }

        public int getCount() {
            return count;
        }
    }

}

