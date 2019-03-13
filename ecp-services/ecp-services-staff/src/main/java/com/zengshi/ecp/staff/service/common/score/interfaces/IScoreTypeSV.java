package com.zengshi.ecp.staff.service.common.score.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.model.ScoreType;
import com.zengshi.ecp.staff.dubbo.dto.ScoreTypeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreTypeResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 积分类型sv接口<br>
 * Date:2015-10-14下午9:38:06  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public interface IScoreTypeSV {

    /**
     * 
     * queryScoreTypeList:(查询积分类型列表). <br/> 
     * 
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public PageResponseDTO<ScoreTypeResDTO> queryScoreTypeList(ScoreTypeReqDTO req) throws BusinessException;
    
    /**
     * 
     * findScoreTypeByKey:(通过积分类型，获取积分类型整个对象). <br/> 
     * 
     * @author huangxl 
     * @param scoreType
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public ScoreType findScoreTypeByKey(String scoreType) throws BusinessException;
}

