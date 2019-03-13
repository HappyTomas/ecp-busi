package com.zengshi.ecp.busi.staff.vo;

import com.zengshi.ecp.base.vo.EcpBaseResponseVO;

public class AuthMenuVO extends EcpBaseResponseVO{
    private String id;
    
    private String pId;
    
    private String name;
    
    private String menuTitle;

    private String menuUrl;

    private Boolean isParent;
    
    private String parentMenuId;

    private String menuType;

    private String sysCode;

    private String menuDesc;

    private String menuPic;
    
    private String menuPicURL;

    private Short sortOrder;

    private String status;

    private Boolean isNewCreate;;
    
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle == null ? null : menuTitle.trim();
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }


    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType == null ? null : menuType.trim();
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode == null ? null : sysCode.trim();
    }

    public String getMenuDesc() {
        return menuDesc;
    }

    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc == null ? null : menuDesc.trim();
    }

    public String getMenuPic() {
        return menuPic;
    }

    public void setMenuPic(String menuPic) {
        this.menuPic = menuPic == null ? null : menuPic.trim();
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public Short getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Short sortOrder) {
        this.sortOrder = sortOrder;
    }

    /** 
     * pId. 
     * 
     * @return  the pId 
     * @since   JDK 1.6 
     */
    public String getpId() {
        return pId;
    }

    /** 
     * pId. 
     * 
     * @param   pId    the pId to set 
     * @since   JDK 1.6 
     */
    public void setpId(String pId) {
        this.pId = pId;
    }

    /** 
     * name. 
     * 
     * @return  the name 
     * @since   JDK 1.6 
     */
    public String getName() {
        return name;
    }

    /** 
     * name. 
     * 
     * @param   name    the name to set 
     * @since   JDK 1.6 
     */
    public void setName(String name) {
        this.name = name;
    }

    /** 
     * menuPicURL. 
     * 
     * @return  the menuPicURL 
     * @since   JDK 1.6 
     */
    public String getMenuPicURL() {
        return menuPicURL;
    }

    /** 
     * menuPicURL. 
     * 
     * @param   menuPicURL    the menuPicURL to set 
     * @since   JDK 1.6 
     */
    public void setMenuPicURL(String menuPicURL) {
        this.menuPicURL = menuPicURL;
    }

    /** 
     * isParent. 
     * 
     * @return  the isParent 
     * @since   JDK 1.6 
     */
    public Boolean getIsParent() {
        return isParent;
    }

    /** 
     * isParent. 
     * 
     * @param   isParent    the isParent to set 
     * @since   JDK 1.6 
     */
    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }

    /** 
     * isNewCreate. 
     * 
     * @return  the isNewCreate 
     * @since   JDK 1.6 
     */
    public Boolean getIsNewCreate() {
        return isNewCreate;
    }

    /** 
     * isNewCreate. 
     * 
     * @param   isNewCreate    the isNewCreate to set 
     * @since   JDK 1.6 
     */
    public void setIsNewCreate(Boolean isNewCreate) {
        this.isNewCreate = isNewCreate;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see java.lang.Object#toString() 
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AuthMenuVO [id=").append(id).append(", pId=").append(pId).append(", name=")
                .append(name).append(", menuTitle=").append(menuTitle).append(", menuUrl=")
                .append(menuUrl).append(", parentMenuId=").append(parentMenuId)
                .append(", menuType=").append(menuType).append(", sysCode=").append(sysCode)
                .append(", menuDesc=").append(menuDesc).append(", menuPic=").append(menuPic)
                .append(", menuPicURL=").append(menuPicURL).append(", sortOrder=")
                .append(sortOrder).append(", status=").append(status).append("]");
        return builder.toString();
    }
    
    
}