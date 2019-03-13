package com.zengshi.ecp.staff.service.common.score.interfaces;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.model.ScoreCategoryRatio;
import com.zengshi.ecp.staff.dubbo.dto.ScoreCategoryRatioReqDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 商品类型积分赠送比例配置服务接口<br>
 * Date:2016-3-21下午4:00:31  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public interface IScoreCategoryRatioSV {

    /**
     * 
     * findScoreCategoryRatio:(查询配置的比例). <br/> 
     * 通过积分行为actionType
     * 商品类型catgCode
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public ScoreCategoryRatio findScoreCategoryRatio(ScoreCategoryRatioReqDTO req) throws BusinessException;
}

