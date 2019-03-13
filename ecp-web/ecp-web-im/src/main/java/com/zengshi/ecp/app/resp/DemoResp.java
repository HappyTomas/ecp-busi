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
 * Description: <br>
 * Date:2016-2-22下午6:53:17  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class DemoResp extends IBody {
    
    String msg;
    
    String name ; 

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
     * msg. 
     * 
     * @return  the msg 
     * @since   JDK 1.6 
     */
    public String getMsg() {
        return msg;
    }

    /** 
     * msg. 
     * 
     * @param   msg    the msg to set 
     * @since   JDK 1.6 
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    

}

