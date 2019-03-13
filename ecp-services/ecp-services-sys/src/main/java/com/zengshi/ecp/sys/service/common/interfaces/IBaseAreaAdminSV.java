/** 
 * Project Name:ecp-services-sys 
 * File Name:IBaseAreaAdminSV.java 
 * Package Name:com.zengshi.ecp.sys.service.common.interfaces 
 * Date:2015-8-24下午8:09:27 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseAreaAdminRespDTO;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2015-8-24下午8:09:27  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public interface IBaseAreaAdminSV extends IGeneralSQLSV {
    
    /**
     * 
     * fetchAreaAdminInfoByCode:<br/>
     * 根据 区域编码获取该区域的信息；
     * 
     * @author yugn 
     * @param areaCode 必填，如果为空，返回 null;
     * @return 
     * @since JDK 1.6
     */
    public BaseAreaAdminRespDTO fetchAreaAdminInfoByCode(String areaCode);
    
    /**
     * 
     * fetchChildAreaAdminInfoList:<br/>
     * 根据区域编码获取该区域的下级区域列表信息； 
     * 
     * @author yugn 
     * @param areaCode 区域编码，非空；空值，返回 null;
     * @return 
     * @since JDK 1.6
     */
    public List<BaseAreaAdminRespDTO> fetchChildAreaAdminInfoList(String areaCode);

}

