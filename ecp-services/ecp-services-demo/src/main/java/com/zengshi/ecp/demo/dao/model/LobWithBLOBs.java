package com.zengshi.ecp.demo.dao.model;

import java.io.Serializable;

public class LobWithBLOBs extends Lob implements Serializable {
    private Byte[] bLob;

    private String cLob;

    private static final long serialVersionUID = 1L;

    public Byte[] getbLob() {
        return bLob;
    }

    public void setbLob(Byte[] bLob) {
        this.bLob = bLob;
    }

    public String getcLob() {
        return cLob;
    }

    public void setcLob(String cLob) {
        this.cLob = cLob == null ? null : cLob.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bLob=").append(bLob);
        sb.append(", cLob=").append(cLob);
        sb.append("]");
        return sb.toString();
    }
}
