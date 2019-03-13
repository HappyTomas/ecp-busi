/** 
 * Project Name:ecp-web-mall 
 * File Name:DemoAction.java 
 * Package Name:com.zengshi.ecp.app.action 
 * Date:2016-2-22下午6:51:19 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.app.action;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Staff117Req;
import com.zengshi.ecp.app.resp.Staff117Resp;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dubbo.dto.MsgInsiteReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgInsiteResDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IMsgInsiteRSV;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 用户域：查询我的消息列表action<br>
 * Date:2016-3-4下午3:43:32  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */

@Service("staff117")
@Action(bizcode="staff117", userCheck=true)
@Scope("prototype")
public class Staff117Action extends AppBaseAction<Staff117Req, Staff117Resp> {

	@Resource
    private IMsgInsiteRSV msgInsiteRSV;
	
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        /*1、获取入参*/
    	String readFlag = this.request.getReadFlag();//10:已读；00：未读 ；为空则查全部
    	int pageNo = this.request.getPageNo();//分页数
    	int pageSize = this.request.getPageSize();//每页数量
    	MsgInsiteReqDTO req = new MsgInsiteReqDTO();
    	//业务特殊，这里都查所有，因为app端点击进来，我这边要把用户下的所有消息都处理成已读
    	req.setPageNo(pageNo);
    	req.setPageSize(pageSize);
    	req.setStaffId(req.getStaff().getId());
        
    	/*2、如果有未读的消息，则更新成已读*/
        if ("00".equals(readFlag)) {
        	MsgInsiteReqDTO msgReq = new MsgInsiteReqDTO();
        	msgReq.setStaffId(req.getStaff().getId());
        	msgReq.setReadFlag("10");
        	msgInsiteRSV.updateMsgInsite(msgReq);
        }
        
        /*2.1、调用业务查询接口，查询我的消息*/
        PageResponseDTO<MsgInsiteResDTO> res = msgInsiteRSV.listMsgInsiteByStaff(req);
        
        /*3、返回结果*/
        List<MsgInsiteResDTO> resList = new ArrayList<MsgInsiteResDTO>();
        if (res != null && CollectionUtils.isNotEmpty(res.getResult())) {
        	this.response.setResList(res.getResult());
        } else {
        	this.response.setResList(resList);
        }
    }
}

