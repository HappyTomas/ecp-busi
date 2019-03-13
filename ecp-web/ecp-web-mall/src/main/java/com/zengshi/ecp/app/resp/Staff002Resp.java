/** 
 * Project Name:ecp-web-mall 
 * File Name:DemoResp.java 
 * Package Name:com.zengshi.ecp.app.resp 
 * Date:2016-2-22下午6:53:17 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.app.resp;

import com.zengshi.butterfly.app.model.IBody;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 用户注册出参<br>
 * Date:2016-2-22下午6:53:17  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6 
 */
public class Staff002Resp extends IBody {
    
    private String tocken;
    
    public String getTocken() {
        return tocken;
    }

    public void setTocken(String tocken) {
        this.tocken = tocken;
    }

}

