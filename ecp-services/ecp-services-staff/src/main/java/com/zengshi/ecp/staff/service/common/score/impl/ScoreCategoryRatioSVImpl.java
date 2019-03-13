package com.zengshi.ecp.staff.service.common.score.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.mapper.common.ScoreCategoryRatioMapper;
import com.zengshi.ecp.staff.dao.model.ScoreCategoryRatio;
import com.zengshi.ecp.staff.dao.model.ScoreCategoryRatioKey;
import com.zengshi.ecp.staff.dubbo.dto.ScoreCategoryRatioReqDTO;
import com.zengshi.ecp.staff.service.common.score.interfaces.IScoreCategoryRatioSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 商品类型积分赠送比例配置服务接口实现类<br>
 * Date:2016-3-21下午4:09:19  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public class ScoreCategoryRatioSVImpl implements IScoreCategoryRatioSV{

    @Resource
    private ScoreCategoryRatioMapper scoreCategoryRatioMapper;
    
    @Override
    public ScoreCategoryRatio findScoreCategoryRatio(ScoreCategoryRatioReqDTO req) throws BusinessException {
        ScoreCategoryRatioKey key = new ScoreCategoryRatioKey();
        key.setActionType(req.getActionType());
        key.setCatgCode(req.getCatgCode());
        return scoreCategoryRatioMapper.selectByPrimaryKey(key);
    }

}

