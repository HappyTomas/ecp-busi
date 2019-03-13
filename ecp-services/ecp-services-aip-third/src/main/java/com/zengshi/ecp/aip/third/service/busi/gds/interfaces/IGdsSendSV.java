package com.zengshi.ecp.aip.third.service.busi.gds.interfaces;

import java.util.HashMap;

import com.zengshi.ecp.aip.third.dubbo.dto.req.GdsSendThirdReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;


public interface IGdsSendSV {

    /**
     * 
     * send:商品推送 <br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
	
	//内部key定义说明
	/*num_iid Number 1489161932商品数字id 
	created Date 2000-01-01 00:00:00Item的发布时间
	iid String 13232商品iid  本系统编码*/
    public HashMap send(GdsSendThirdReqDTO gdsSendReqDTO)throws BusinessException;
}

