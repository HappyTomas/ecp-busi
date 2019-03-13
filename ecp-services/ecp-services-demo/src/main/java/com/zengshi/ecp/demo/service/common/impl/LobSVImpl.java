/** 
 * Project Name:ecp-services-demo 
 * File Name:LobSVImpl.java 
 * Package Name:com.zengshi.ecp.demo.service.common.impl 
 * Date:2015年8月7日上午11:46:21 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
*/
package com.zengshi.ecp.demo.service.common.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.demo.dao.mapper.common.LobMapper;
import com.zengshi.ecp.demo.dao.model.LobWithBLOBs;
import com.zengshi.ecp.demo.service.common.interfaces.ILobSV;

/** 
 * Title: ECP <br>
 * Project Name:ecp-services-demo <br>
 * Description: <br>
 * Date:2015年8月7日上午11:46:21  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangysh
 * @since JDK 1.6 
 * @see       
 */
@Service
public class LobSVImpl implements ILobSV{
    
    @Resource
    private LobMapper lobMapper;

    @Override
    public int insert(LobWithBLOBs lob) {
        return lobMapper.insert(lob);
    }

    @Override
    public LobWithBLOBs findById(Long id) {
        return lobMapper.selectByPrimaryKey(BigDecimal.valueOf(id));
    }

    
}

