 /**
 * @filename TmpTestVO.java
 * @package  com.zengshi.ecp.busi.staff.vo
 *
 * @author   PJieWin
 * @version  
 * @Date	 2015年9月7日		下午5:45:40
 *
 */


package com.zengshi.ecp.busi.staff.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Copyright (c) 2015, NEWLAND , LTD All Rights Reserved.
 *
 * @ClassName:TmpTestVO
 * @Description: TODO 一句话功能描述
 * @Funtion List:TODO 主要函数及其功能
 *
 * @author   PJieWin
 * @version  
 * @Date	 2015年9月7日		下午5:45:40
 *	 
 * @History: // 历史修改记录 
 * <author>  // 作者
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述
 */
public class TmpTestVO implements Serializable
{


    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getExpressFullName()
    {
        return expressFullName;
    }

    public void setExpressFullName(String expressFullName)
    {
        this.expressFullName = expressFullName;
    }

    public String getExpressName()
    {
        return expressName;
    }

    public void setExpressName(String expressName)
    {
        this.expressName = expressName;
    }

    public String getExpressWebsite()
    {
        return expressWebsite;
    }

    public void setExpressWebsite(String expressWebsite)
    {
        this.expressWebsite = expressWebsite;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public BigDecimal getSortNo()
    {
        return sortNo;
    }

    public void setSortNo(BigDecimal sortNo)
    {
        this.sortNo = sortNo;
    }

    public String getStatusCn()
    {
        return statusCn;
    }

    public void setStatusCn(String statusCn)
    {
        this.statusCn = statusCn;
    }

    private static final long serialVersionUID = 478711946770018867L;
    private String id;
    private String expressFullName;
    private String expressName;
    private String expressWebsite;
    private String status;
    private String statusCn;
    private BigDecimal sortNo;
}

