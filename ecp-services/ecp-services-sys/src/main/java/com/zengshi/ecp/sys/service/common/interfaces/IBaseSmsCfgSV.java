/** 
 * Project Name:ecp-services-sys 
 * File Name:IBaseSmsCfgSV.java 
 * Package Name:com.zengshi.ecp.sys.service.common.interfaces 
 * Date:2016年3月17日上午10:59:44 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.service.common.interfaces;

import com.zengshi.ecp.sys.dao.model.BaseSmsCfg;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2016年3月17日上午10:59:44  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public interface IBaseSmsCfgSV {
    
    /**
     * 
     * fetchSmsCfgByGateway: 根据短信网关获取该网关对应的配置信息 <br/> 
     * 
     * @author yugn 
     * @param gateway
     * @return 
     * @since JDK 1.6
     */
    public BaseSmsCfg fetchSmsCfgByGateway(String gateway);
    
    /**
     * 
     * saveSmsCfg:保存短信发送的配置信息 <br/> 
     * @author yugn 
     * @param smsCfg 
     * @since JDK 1.6
     */
    public void saveSmsCfg(BaseSmsCfg smsCfg, Long staffId);
    
    /**
     * 
     * fetchDefaultSmsCfg:获取系统设置的SmsCfg<br/> 
     * 
     * @author yugn 
     * @return 
     * @since JDK 1.6
     */
    public BaseSmsCfg fetchDefaultSmsCfg();
}

