package com.zengshi.ecp.prom.service.busi.valid.impl;

import java.util.ArrayList;

import javax.annotation.Resource;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.service.busi.valid.interfaces.IPromValidSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class DefaultPromValidSVImpl {

    private static final String MODULE = DefaultPromValidSVImpl.class.getName();

    @Resource
    private ArrayList<IPromValidSV> ipromValidSVList;// 验证规则实现列表
 
    /**
     * 所有促销信息保存验证 促销基本信息验证 促销规则 验证 促销优惠规则验证
     * 
     * @param promDTO
     * @throws Exception
     * @author huangjx
     */
    public void valid(PromDTO promDTO) throws BusinessException {
        if (null != ipromValidSVList) {
            for (IPromValidSV promValidSV : ipromValidSVList) {
                if (promValidSV.needToVerified(promDTO)) {
                    promValidSV.valid(promDTO);
                }
            }
        }
    }

}
