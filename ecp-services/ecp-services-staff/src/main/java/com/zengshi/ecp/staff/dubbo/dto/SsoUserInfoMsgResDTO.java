package com.zengshi.ecp.staff.dubbo.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

public class SsoUserInfoMsgResDTO  implements Serializable{

    /** 
     * serialVersionUID:TODO(sso用户数据同步消息). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;
    
    
    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    private String Message;
    
    private boolean flag;
    
    private String staffCode;
    
    
    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", Message=").append(Message);
        sb.append(", flag=").append(flag);
        sb.append("]");
        return sb.toString();
    }

}

