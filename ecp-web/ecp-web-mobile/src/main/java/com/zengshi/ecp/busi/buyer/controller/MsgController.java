package com.zengshi.ecp.busi.buyer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.buyer.vo.MsgInsiteVO;
import com.zengshi.ecp.busi.buyer.vo.MsgScrollResult;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgInsiteReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgInsiteResDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IMsgInsiteRSV;
import com.zengshi.paas.utils.DateUtil;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mobile <br>
 * Description: 我的消息<br>
 * Date:2016年8月4日下午6:14:14  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value="/msg")
public class MsgController  extends EcpBaseController{
    
	@Resource
    private IMsgInsiteRSV msgInsiteRSV;
	
	private final int PAGE_SIZE = 6;//我的消息明细默认一页展示的数据
    
    @RequestMapping(value="/index")
    public String index(Model model) {
    	/*1、查询我的消息数量（全部）*/
    	MsgInsiteReqDTO req = new MsgInsiteReqDTO();
    	req.setStaffId(req.getStaff().getId());
    	long totalCnt = msgInsiteRSV.countMsgInsiteByStaff(req);
    	if (totalCnt == 0L) {
    		return "/msg/msg-nodata";
    	} else {
    		/*2、查询我的消息数量（未读）*/
        	req.setReadFlag("00");//10:已读；00：未读 ；为空则查全部
        	long unreadCnt = msgInsiteRSV.countMsgInsiteByStaff(req);
            
            model.addAttribute("totalCnt", totalCnt);
            if (unreadCnt != 0L) {
            	model.addAttribute("unreadCnt", unreadCnt>99?99:unreadCnt);
            }
            MsgInsiteReqDTO listReq = new MsgInsiteReqDTO();
            listReq.setPageNo(1);
            listReq.setPageSize(1);//这里只查一条出来显示
            listReq.setStaffId(listReq.getStaff().getId());
            listReq.setReadFlag(unreadCnt > 0 ? "00" : "");//如果有未读，查未读，没有则查所有中的一条
            
            /*2.1、调用业务查询接口，查询我的消息列表*/
            PageResponseDTO<MsgInsiteResDTO> page = msgInsiteRSV.listMsgInsiteByStaff(listReq);
            
            model.addAttribute("msgInfo", page.getResult().get(0));
    	}
    	return "/msg/msg-index";
    }
    
    /**
     * 
     * list:(跳转到list页面). <br/> 
     * 
     * @author huangxl5 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/list")
    public String list(Model model) {
    	
    	return "/msg/msg-list";
    }
    /**
     * 
     * listdata:(查询列表时，同时把数据更新为已读). <br/> 
     * 
     * @author huangxl5 
     * @param model
     * @param pageNo
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/listdata")
    @ResponseBody
    public MsgScrollResult listdata(Model model,@RequestParam(value="page",required=false)int pageNo) {
    	
		MsgInsiteReqDTO listReq = new MsgInsiteReqDTO();
	    listReq.setPageNo(pageNo);
	    listReq.setPageSize(PAGE_SIZE);
	    listReq.setStaffId(listReq.getStaff().getId());
	    
	    /*1、调用业务查询接口，查询我的消息列表，这里查的是所有，因为下面会把消息全更新为已读*/
	    PageResponseDTO<MsgInsiteResDTO> page = msgInsiteRSV.listMsgInsiteByStaff(listReq);
	    List<MsgInsiteVO> resultList = new ArrayList<MsgInsiteVO>();
	    for (MsgInsiteResDTO msg : page.getResult()) {
	    	MsgInsiteVO vo = new MsgInsiteVO();
	    	vo.setMsgName(msg.getMsgName());
	    	vo.setMsgContext(msg.getMsgContext());
	    	vo.setCreateTime(DateUtil.getDateString(msg.getRecTime(), "yyyy-MM-dd HH:mm:ss"));
	    	resultList.add(vo);
	    }
	    
	    /*2、更新消息为已读*/
	    listReq.setReadFlag("10");
    	msgInsiteRSV.updateMsgInsite(listReq);
    	
    	MsgScrollResult msgResult = new MsgScrollResult();
    	
    	msgResult.setTotal(page.getPageCount());
    	msgResult.setDatas(resultList);
    	return msgResult;
    }
    
}

