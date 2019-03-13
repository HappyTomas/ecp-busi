/** 
 * Project Name:ecp-web-mall 
 * File Name:CompanySignVO.java 
 * Package Name:com.zengshi.ecp.busi.staff.seller.vo 
 * Date:2015年9月17日下午2:29:47 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.seller.staff.vo;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 子帐号角色vo<br>
 * Date:2016-4-19上午9:07:59  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public class SubAcctRoleVO{
    
    private Long roleId;
    
    private String roleName;
    
    private String isExist;//是否存在该角色

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getIsExist() {
        return isExist;
    }

    public void setIsExist(String isExist) {
        this.isExist = isExist;
    }
    
}

