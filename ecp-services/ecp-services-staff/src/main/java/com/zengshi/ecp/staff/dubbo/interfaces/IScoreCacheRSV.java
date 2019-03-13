package com.zengshi.ecp.staff.dubbo.interfaces;

import com.zengshi.ecp.general.order.dto.PayQuartzInfoRequest;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 积分计算规则缓存接口<br>
 * Date:2016-2-17下午4:04:07  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public interface IScoreCacheRSV {

    /**
     * 
     * sizeScoreCache:(积分计算规则缓存数量). <br/> 
     * 
     * @author huangxl5
     * @return 
     * @since JDK 1.6
     */
   public long sizeScoreCache();
   
}

