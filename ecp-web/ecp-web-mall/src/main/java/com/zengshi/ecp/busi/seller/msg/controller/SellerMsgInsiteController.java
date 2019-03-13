package com.zengshi.ecp.busi.seller.msg.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.util.WebContextUtil;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.seller.msg.vo.MsgInsiteVO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dubbo.dto.MsgInsiteReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgInsiteResDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IMsgInsiteRSV;
import com.zengshi.ecp.sys.dubbo.util.BaseMsgConstants;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description:卖家用户站内消息中心 <br>
 * Date:2016年5月30日下午6:56:31  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author chenyz3
 * @version  
 * @since JDK 1.6
 */

@Controller
@RequestMapping(value = "/seller/msginsite")
public class SellerMsgInsiteController extends EcpBaseController {

    @Resource
    private IMsgInsiteRSV msgInsiteRSV;
    
    @RequestMapping(value = "/msgindex")
    public String msgIndex(Model model){
        
        return "/seller/msginsite/selmessage-comment";
    }
    
    
    /**
     * 
     * msgList:(获取所有站内消息). <br/> 
     * @author chenyz3 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/msglist")
    public String msgList(Model model , MsgInsiteVO vo){
        MsgInsiteReqDTO msgReqDTO = new MsgInsiteReqDTO();//获取当前登录用户
        msgReqDTO.setPageNo(vo.getPageNumber());
        msgReqDTO.setPageSize(vo.getPageSize());
        msgReqDTO.setMsgType("02");//消息分类；01：买家消息、 02：卖家消息、 03：管理员消息
        msgReqDTO.setStaffId(msgReqDTO.getStaff().getId());
        PageResponseDTO<MsgInsiteResDTO> page = msgInsiteRSV.listMsgInsiteBymsgType(msgReqDTO);
        model.addAttribute("msgPage", page);
        return "/seller/msginsite/div/selmessage-list";
    }
    
    /**
     * 
     * readList:(获取已读站内消息). <br/> 
     * @author chenyz3 
     * @param model
     * @param vo
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/readed")
    public String readList(Model model , MsgInsiteVO vo){
        MsgInsiteReqDTO msgReqDTO = new MsgInsiteReqDTO();//获取当前登录用户
        msgReqDTO.setPageNo(vo.getPageNumber());
        msgReqDTO.setPageSize(vo.getPageSize());
        msgReqDTO.setMsgType("02");//消息分类；01：买家消息、 02：卖家消息、 03：管理员消息
        msgReqDTO.setReadFlag(BaseMsgConstants.SYS_MSGINSITE_READ);//00 ： 未读  ； 10 ： 已读
        msgReqDTO.setStaffId(msgReqDTO.getStaff().getId());
        PageResponseDTO<MsgInsiteResDTO> pagread = msgInsiteRSV.listMsgInsiteBymsgType(msgReqDTO);
        model.addAttribute("readPage", pagread);
        
        return "/seller/msginsite/div/selreaded";
    }
    
    /**
     * 
     * unreadList:(获取未读站内消息). <br/> 
     * @author chenyz3 
     * @param model
     * @param vo
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/unread")
    public String unreadList(Model model , MsgInsiteVO vo){
        MsgInsiteReqDTO msgReqDTO = new MsgInsiteReqDTO();//获取当前登录用户
        msgReqDTO.setPageNo(vo.getPageNumber());
        msgReqDTO.setPageSize(vo.getPageSize());
        msgReqDTO.setMsgType("02");//消息分类；01：买家消息、 02：卖家消息、 03：管理员消息
        msgReqDTO.setReadFlag(BaseMsgConstants.SYS_MSGINSITE_UNREAD);//00 ： 未读  ； 10 ： 已读
        msgReqDTO.setStaffId(msgReqDTO.getStaff().getId());//获取当前登录用户ID
        PageResponseDTO<MsgInsiteResDTO> pagunread = msgInsiteRSV.listMsgInsiteBymsgType(msgReqDTO);
        model.addAttribute("unreadPage", pagunread);
        
        return "/seller/msginsite/div/selunread";
    }
    
    
    /**
     * 
     * batchDel:(批量删除复选框选中的消息). <br/> 
     * @author chenyz3 
     * @param model
     * @param ids
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/batchdel")
    @ResponseBody
    public EcpBaseResponseVO batchDel(Model model, @RequestParam("ids") String ids) {
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        
        try {
            if (StringUtil.isEmpty(ids)) {
                throw new BusinessException("选中的复选框没有值");
            }
            
            //解析前端传入字符串
            String[] idsArr=ids.split("#");
            int count=idsArr.length;
            
            if(count<=0){
                throw new BusinessException("解析出错");
            }
            for(int i=0;i<count;i++){
                MsgInsiteReqDTO msgInsiteReqDTO = new MsgInsiteReqDTO();//获取当前登录用户
                msgInsiteReqDTO.setStaffId(msgInsiteReqDTO.getStaff().getId());//删除条件：当前登录用户ID
                msgInsiteReqDTO.setMsgInfoId(new Long(idsArr[i]));//删除条件：对接消息表的消息ID
                msgInsiteRSV.deleteMsgInsite(msgInsiteReqDTO);
                vo.setResultFlag("ok");
                vo.setResultMsg("操作成功");
            }
        } catch (Exception e) {
            vo.setResultFlag("fail");
            vo.setResultMsg("操作失败" + e.getMessage());
        }
        return vo;
    }
    
    
    /**
     * 
     * markRead:(标记已读). <br/> 
     * @author chenyz3 
     * @param model
     * @param ids
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/markread")
    @ResponseBody
    public EcpBaseResponseVO markRead(Model model, @RequestParam("ids") String ids) {
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        
        try {
            if (StringUtil.isEmpty(ids)) {
                throw new BusinessException("选中的复选框没有值");
            }
            
            //解析前端传入字符串
            String[] idsArr=ids.split("#");
            int count=idsArr.length;
            
            if(count<=0){
                throw new BusinessException("解析出错");
            }
            for(int i=0;i<count;i++){
                MsgInsiteReqDTO msgInsiteReqDTO = new MsgInsiteReqDTO();//获取当前登录用户
                msgInsiteReqDTO.setStaffId(msgInsiteReqDTO.getStaff().getId());//条件：当前登录用户ID
                msgInsiteReqDTO.setMsgInfoId(new Long(idsArr[i]));//条件： 对接消息表的消息ID
                msgInsiteReqDTO.setReadFlag(BaseMsgConstants.SYS_MSGINSITE_READ);//更新为 已读
                msgInsiteRSV.updateMsgInsite(msgInsiteReqDTO);
                vo.setResultFlag("ok");
                vo.setResultMsg("操作成功");
            }
        } catch (Exception e) {
            vo.setResultFlag("fail");
            vo.setResultMsg("操作失败" + e.getMessage());
        }
        return vo;
    }
    
    
    /**
     * 
     * msgDel:(“查看所有”页面的单条消息删除操作). <br/> 
     * @author chenyz3 
     * @param vo
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/msgdel")
    @ResponseBody
    public EcpBaseResponseVO msgDel(MsgInsiteVO vo){
        EcpBaseResponseVO ecpvo = new EcpBaseResponseVO();
        MsgInsiteReqDTO msgInsiteReqDTO = new MsgInsiteReqDTO();//获取当前登录用户
        msgInsiteReqDTO.setStaffId(msgInsiteReqDTO.getStaff().getId());//删除条件：当前登录用户ID
        msgInsiteReqDTO.setMsgInfoId(vo.getMsgInfoId());//删除条件：对接消息表的消息ID
        try {
            msgInsiteRSV.deleteMsgInsite(msgInsiteReqDTO);
            ecpvo.setResultFlag("ok");
            ecpvo.setResultMsg("操作成功");
        } catch (Exception e) {
            ecpvo.setResultFlag("fail");
            ecpvo.setResultMsg("操作失败" + e.getMessage());
        }
        return ecpvo;
    }
    
    
    /**
     * 
     * unreadmsgDel:("查看未读"页面单条消息删除操作). <br/> 
     * @author chenyz3 
     * @param vo
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/unread/mgdel")
    @ResponseBody
    public EcpBaseResponseVO unreadmsgDel(MsgInsiteVO vo){
        EcpBaseResponseVO ecpvo = new EcpBaseResponseVO();
        MsgInsiteReqDTO msgInsiteReqDTO = new MsgInsiteReqDTO();//获取当前登录用户
        msgInsiteReqDTO.setStaffId(msgInsiteReqDTO.getStaff().getId());//删除条件：当前登录用户ID
        msgInsiteReqDTO.setMsgInfoId(vo.getMsgInfoId());//删除条件：对接消息表的消息ID
        msgInsiteReqDTO.setReadFlag(BaseMsgConstants.SYS_MSGINSITE_UNREAD);//删除条件：未读消息
        try {
            msgInsiteRSV.deleteMsgInsite(msgInsiteReqDTO);
            ecpvo.setResultFlag("ok");
            ecpvo.setResultMsg("操作成功");
        } catch (Exception e) {
            ecpvo.setResultFlag("fail");
            ecpvo.setResultMsg("操作失败" + e.getMessage());
        }
        return ecpvo;
    }
    
    
    /**
     * 
     * readmsgDel:(查看已读"页面单条消息删除操作). <br/> 
     * @author asus1 
     * @param vo
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/readed/msdel")
    @ResponseBody
    public EcpBaseResponseVO readmsgDel(MsgInsiteVO vo){
        EcpBaseResponseVO ecpvo = new EcpBaseResponseVO();
        MsgInsiteReqDTO msgInsiteReqDTO = new MsgInsiteReqDTO();//获取当前登录用户
        msgInsiteReqDTO.setStaffId(msgInsiteReqDTO.getStaff().getId());//删除条件：当前登录用户ID
        msgInsiteReqDTO.setMsgInfoId(vo.getMsgInfoId());//删除条件：对接消息表的消息ID
        msgInsiteReqDTO.setReadFlag(BaseMsgConstants.SYS_MSGINSITE_READ);//删除条件：已读消息
        try {
            msgInsiteRSV.deleteMsgInsite(msgInsiteReqDTO);
            ecpvo.setResultFlag("ok");
            ecpvo.setResultMsg("操作成功");
        } catch (Exception e) {
            ecpvo.setResultFlag("fail");
            ecpvo.setResultMsg("操作失败" + e.getMessage());
        }
        return ecpvo;
    }
    
}

