package com.zengshi.ecp.im.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class ofUser implements Serializable {
    private String username;

    private String storedkey;

    private String serverkey;

    private String salt;

    private BigDecimal iterations;

    private String plainpassword;

    private String encryptedpassword;

    private String name;

    private String email;

    private String creationdate;

    private String modificationdate;

    private static final long serialVersionUID = 1L;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getStoredkey() {
        return storedkey;
    }

    public void setStoredkey(String storedkey) {
        this.storedkey = storedkey == null ? null : storedkey.trim();
    }

    public String getServerkey() {
        return serverkey;
    }

    public void setServerkey(String serverkey) {
        this.serverkey = serverkey == null ? null : serverkey.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public BigDecimal getIterations() {
        return iterations;
    }

    public void setIterations(BigDecimal iterations) {
        this.iterations = iterations;
    }

    public String getPlainpassword() {
        return plainpassword;
    }

    public void setPlainpassword(String plainpassword) {
        this.plainpassword = plainpassword == null ? null : plainpassword.trim();
    }

    public String getEncryptedpassword() {
        return encryptedpassword;
    }

    public void setEncryptedpassword(String encryptedpassword) {
        this.encryptedpassword = encryptedpassword == null ? null : encryptedpassword.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(String creationdate) {
        this.creationdate = creationdate == null ? null : creationdate.trim();
    }

    public String getModificationdate() {
        return modificationdate;
    }

    public void setModificationdate(String modificationdate) {
        this.modificationdate = modificationdate == null ? null : modificationdate.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", username=").append(username);
        sb.append(", storedkey=").append(storedkey);
        sb.append(", serverkey=").append(serverkey);
        sb.append(", salt=").append(salt);
        sb.append(", iterations=").append(iterations);
        sb.append(", plainpassword=").append(plainpassword);
        sb.append(", encryptedpassword=").append(encryptedpassword);
        sb.append(", name=").append(name);
        sb.append(", email=").append(email);
        sb.append(", creationdate=").append(creationdate);
        sb.append(", modificationdate=").append(modificationdate);
        sb.append("]");
        return sb.toString();
    }
}