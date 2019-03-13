package com.zengshi.ecp.busi.goods.gdsdetail.vo;

import java.io.Serializable;

public class GdsPdfVO implements Serializable{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 5427490109399047522L;
    /**
     * pdf文件的id
     */
    private String pdfId;
    public String getPdfId() {
        return pdfId;
    }
    public void setPdfId(String pdfId) {
        this.pdfId = pdfId;
    }
    
}

