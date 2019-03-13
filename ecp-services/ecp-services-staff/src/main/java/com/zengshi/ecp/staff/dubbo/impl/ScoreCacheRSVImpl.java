package com.zengshi.ecp.staff.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.staff.dubbo.interfaces.IScoreCacheRSV;
import com.zengshi.ecp.staff.service.cache.interfaces.IScoreCacheSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 积分计算接口实现类<br>
 * Date:2016-2-17下午4:07:43  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public class ScoreCacheRSVImpl implements IScoreCacheRSV{

    @Resource
    private IScoreCacheSV scoreCacheSV;

    @Override
    public long sizeScoreCache() {
        return scoreCacheSV.sizeScoreCache();
    }

}

