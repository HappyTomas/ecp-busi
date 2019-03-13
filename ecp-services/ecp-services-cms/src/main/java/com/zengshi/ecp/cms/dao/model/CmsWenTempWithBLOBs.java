package com.zengshi.ecp.cms.dao.model;

import java.io.Serializable;

public class CmsWenTempWithBLOBs extends CmsWenTemp implements Serializable {
    private String docpubhtmlcon;

    private String importtext;

    private static final long serialVersionUID = 1L;

    public String getDocpubhtmlcon() {
        return docpubhtmlcon;
    }

    public void setDocpubhtmlcon(String docpubhtmlcon) {
        this.docpubhtmlcon = docpubhtmlcon == null ? null : docpubhtmlcon.trim();
    }

    public String getImporttext() {
        return importtext;
    }

    public void setImporttext(String importtext) {
        this.importtext = importtext == null ? null : importtext.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", docpubhtmlcon=").append(docpubhtmlcon);
        sb.append(", importtext=").append(importtext);
        sb.append("]");
        return sb.toString();
    }
}
