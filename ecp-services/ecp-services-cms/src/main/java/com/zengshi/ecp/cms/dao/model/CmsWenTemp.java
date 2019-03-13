package com.zengshi.ecp.cms.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CmsWenTemp implements Serializable {
    private String doctitle;

    private Timestamp crtime;

    private static final long serialVersionUID = 1L;

    public String getDoctitle() {
        return doctitle;
    }

    public void setDoctitle(String doctitle) {
        this.doctitle = doctitle == null ? null : doctitle.trim();
    }

    public Timestamp getCrtime() {
        return crtime;
    }

    public void setCrtime(Timestamp crtime) {
        this.crtime = crtime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", doctitle=").append(doctitle);
        sb.append(", crtime=").append(crtime);
        sb.append("]");
        return sb.toString();
    }
}
