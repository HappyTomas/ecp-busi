/** 
 * Project Name:ecp-web-mall 
 * File Name:DemoResp.java 
 * Package Name:com.zengshi.ecp.app.resp 
 * Date:2016-2-22下午6:53:17 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.app.resp;


import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseAreaAdminRespDTO;
import com.zengshi.butterfly.app.model.IBody;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 获取地域列表信息出参<br>
 * Date:2016-2-22下午6:53:17  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6 
 */
public class Staff111Resp extends IBody {
    
    private List<BaseAreaAdminRespDTO> resList;

    public List<BaseAreaAdminRespDTO> getResList() {
        return resList;
    }

    public void setResList(List<BaseAreaAdminRespDTO> resList) {
        this.resList = resList;
    }
    
}

