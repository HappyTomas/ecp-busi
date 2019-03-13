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

import com.zengshi.ecp.sys.dubbo.dto.MsgInsiteResDTO;
import com.zengshi.butterfly.app.model.IBody;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 查询我的消息出参<br>
 * Date:2016-2-22下午6:53:17  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6 
 */
public class Staff117Resp extends IBody {
    
	List<MsgInsiteResDTO> resList;

	public List<MsgInsiteResDTO> getResList() {
		return resList;
	}

	public void setResList(List<MsgInsiteResDTO> resList) {
		this.resList = resList;
	}
    
}

