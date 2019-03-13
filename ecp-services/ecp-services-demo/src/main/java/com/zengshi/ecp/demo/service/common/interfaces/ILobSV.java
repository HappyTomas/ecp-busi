/** 
 * Project Name:ecp-services-demo 
 * File Name:ILobSV.java 
 * Package Name:com.zengshi.ecp.demo.service.common.interfaces 
 * Date:2015年8月7日上午11:45:52 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
*/
package com.zengshi.ecp.demo.service.common.interfaces;

import com.zengshi.ecp.demo.dao.model.LobWithBLOBs;

/** 
 * Title: ECP <br>
 * Project Name:ecp-services-demo <br>
 * Description: <br>
 * Date:2015年8月7日上午11:45:52  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangysh
 * @since JDK 1.6 
 * @see       
 */
public interface ILobSV {

    int insert(LobWithBLOBs lob);
    
    LobWithBLOBs findById(Long id);
}

