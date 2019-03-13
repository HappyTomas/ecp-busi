package com.zengshi.ecp.search.dubbo.search.result;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public class ClusteringReuslt  implements Serializable{
    
    /** 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;

    private List<String> labels;
    
    private String label="";

    private String score;
    
    private List<String> docs;

    public ClusteringReuslt(List<String> labels,String score,List<String> docs) {
        this.labels=labels;
        this.score=score;
        this.docs=docs;
        if(CollectionUtils.isNotEmpty(labels)){
            for(String l:labels){
                this.label+=l;
            }
        }
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public List<String> getDocs() {
        return docs;
    }

    public void setDocs(List<String> docs) {
        this.docs = docs;
    }

}

