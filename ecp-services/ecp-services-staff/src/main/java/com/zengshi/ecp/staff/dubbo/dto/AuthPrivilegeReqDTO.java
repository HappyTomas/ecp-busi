package com.zengshi.ecp.staff.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class AuthPrivilegeReqDTO extends BaseInfo{
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;
    
    private Long id;

    private String roleAdmin;

    private String privilegeType;

    private String sysCode;

    private String status;

    private Long staffId; //用户ID

    private String staffCode;//登陆名

    private String staffClass;//用户类型
    
    private String password;//密码
    
    private String staffStatus;//账号状态
    

    /** 
     * staffId. 
     * 
     * @return  the staffId 
     * @since   JDK 1.6 
     */
    public Long getStaffId() {
        return staffId;
    }

    /** 
     * staffId. 
     * 
     * @param   staffId    the staffId to set 
     * @since   JDK 1.6 
     */
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    /** 
     * staffCode. 
     * 
     * @return  the staffCode 
     * @since   JDK 1.6 
     */
    public String getStaffCode() {
        return staffCode;
    }

    /** 
     * staffCode. 
     * 
     * @param   staffCode    the staffCode to set 
     * @since   JDK 1.6 
     */
    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    /** 
     * staffClass. 
     * 
     * @return  the staffClass 
     * @since   JDK 1.6 
     */
    public String getStaffClass() {
        return staffClass;
    }

    /** 
     * staffClass. 
     * 
     * @param   staffClass    the staffClass to set 
     * @since   JDK 1.6 
     */
    public void setStaffClass(String staffClass) {
        this.staffClass = staffClass;
    }

    /** 
     * password. 
     * 
     * @return  the password 
     * @since   JDK 1.6 
     */
    public String getPassword() {
        return password;
    }

    /** 
     * password. 
     * 
     * @param   password    the password to set 
     * @since   JDK 1.6 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /** 
     * staffStatus. 
     * 
     * @return  the staffStatus 
     * @since   JDK 1.6 
     */
    public String getStaffStatus() {
        return staffStatus;
    }

    /** 
     * staffStatus. 
     * 
     * @param   staffStatus    the staffStatus to set 
     * @since   JDK 1.6 
     */
    public void setStaffStatus(String staffStatus) {
        this.staffStatus = staffStatus;
    }


    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see java.lang.Object#toString() 
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AuthPrivilegeDTO [staffId=");
        builder.append(staffId);
        builder.append(", staffCode=");
        builder.append(staffCode);
        builder.append(", staffClass=");
        builder.append(staffClass);
        builder.append(", password=");
        builder.append(password);
        builder.append(", staffStatus=");
        builder.append(staffStatus);
        builder.append("]");
        return builder.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleAdmin() {
        return roleAdmin;
    }

    public void setRoleAdmin(String roleAdmin) {
        this.roleAdmin = roleAdmin;
    }

    public String getPrivilegeType() {
        return privilegeType;
    }

    public void setPrivilegeType(String privilegeType) {
        this.privilegeType = privilegeType;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    } 
    
}

