/** 
 * Project Name:ecp-services-sys 
 * File Name:IGateWayBeanBuilderSV.java 
 * Package Name:com.zengshi.ecp.sys.sms.interfaces 
 * Date:2016年3月17日上午11:07:12 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.sms.interfaces;

import com.zengshi.ecp.sys.dao.model.BaseSmsCfg;
import com.zengshi.ecp.sys.sms.gateway.SmsGatewayBean;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2016年3月17日上午11:07:12  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public interface IGateWayBeanBuilderSV<T extends SmsGatewayBean> {
    
    public T parse(BaseSmsCfg smsCfg); 

}

