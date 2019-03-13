package com.zengshi.ecp.staff.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.security.AuthManageReqDTO;
import com.zengshi.ecp.server.front.security.AuthMenuResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IMenuManageRSV;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 配合框架权限改造<br>
 * Date:2016-4-13下午10:08:24  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public class MenuRSVImpl implements com.zengshi.ecp.server.front.security.IMenuRSV{

    @Resource
    private IMenuManageRSV menuManageRSV;
    
    @Override
    public List<AuthMenuResDTO> listAuthMenu(AuthManageReqDTO dto) {
        com.zengshi.ecp.staff.dubbo.dto.AuthManageReqDTO req = new com.zengshi.ecp.staff.dubbo.dto.AuthManageReqDTO();
        ObjectCopyUtil.copyObjValue(dto, req, null, true);
        List<com.zengshi.ecp.staff.dubbo.dto.AuthMenuResDTO> list = menuManageRSV.listAuthMenu(req);
        List<AuthMenuResDTO> securityRes = new ArrayList<AuthMenuResDTO>();
        for (com.zengshi.ecp.staff.dubbo.dto.AuthMenuResDTO res : list) {
            AuthMenuResDTO temp = new AuthMenuResDTO();
            ObjectCopyUtil.copyObjValue(res, temp, null, true);
            securityRes.add(temp);
        }
        return securityRes;
    }

}

