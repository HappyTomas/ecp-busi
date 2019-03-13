package com.zengshi.ecp.busi.staff.buyer.controller;
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
import com.zengshi.ecp.busi.staff.buyer.vo.MsgInsiteVO;
import com.zengshi.ecp.search.dubbo.search.result.FieldFacetResult.Count;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.sys.dubbo.dto.MsgInsiteReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgInsiteResDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IMsgInsiteRSV;
import com.zengshi.ecp.sys.dubbo.util.BaseMsgConstants;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description:普通用户站内消息中心 <br>
 * Date:2016年5月17日下午5:36:17  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author chenyz3
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "/msginsite")
public class MsgInsiteController extends EcpBaseController {

    @Resource
    private IMsgInsiteRSV msgInsiteRSV;
    
    @Resource
    private ICustManageRSV custManageRSV;
    
    @RequestMapping(value = "/msgindex")
    public String msgIndex(Model model){
        
        return "/staff/buyer/message/message-comment";
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
        msgReqDTO.setMsgType("01");//消息分类；01：买家消息、 02：卖家消息、 03：管理员消息
        msgReqDTO.setStaffId(msgReqDTO.getStaff().getId());
        PageResponseDTO<MsgInsiteResDTO> page = msgInsiteRSV.listMsgInsiteBymsgType(msgReqDTO);
        model.addAttribute("msgPage", page);
        return "/staff/buyer/message/div/message-list";
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
        msgReqDTO.setMsgType("01");//消息分类；01：买家消息、 02：卖家消息、 03：管理员消息
        msgReqDTO.setReadFlag(BaseMsgConstants.SYS_MSGINSITE_READ);//00 ： 未读  ； 10 ： 已读
        msgReqDTO.setStaffId(msgReqDTO.getStaff().getId());
        PageResponseDTO<MsgInsiteResDTO> pagread = msgInsiteRSV.listMsgInsiteBymsgType(msgReqDTO);
        model.addAttribute("readPage", pagread);
        
        return "/staff/buyer/message/div/readed";
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
        msgReqDTO.setMsgType("01");//消息分类；01：买家消息、 02：卖家消息、 03：管理员消息
        msgReqDTO.setReadFlag(BaseMsgConstants.SYS_MSGINSITE_UNREAD);//00 ： 未读  ； 10 ： 已读
        msgReqDTO.setStaffId(msgReqDTO.getStaff().getId());//获取当前登录用户ID
        PageResponseDTO<MsgInsiteResDTO> pagunread = msgInsiteRSV.listMsgInsiteBymsgType(msgReqDTO);
        model.addAttribute("unreadPage", pagunread);
        
        return "/staff/buyer/message/div/unread";
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
     * readmsgDel:("查看已读"页面的单条消息删除操作). <br/> 
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
    
    
    /**
     * 
     * msgInsiteCnt:(统计站内未读消息数量，卖家和买家消息数量分开返回). <br/> 
     * @author chenyz3 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/msginsitecnt")
    @ResponseBody
    public Map<String,Object> msgInsiteCnt(){
        Map<String,Object> result = new HashMap<String,Object>();
        //判断用户是否登录
        MsgInsiteReqDTO msgReqDTO = new MsgInsiteReqDTO();//获取当前登录用户
        if (msgReqDTO.getStaff() == null || msgReqDTO.getStaff().getId() == 0L) {
            result.put("msgCnt", 0);//买家未读消息数量
            result.put("msgCnt1", 0);//卖家未读消息数量
            return result;
        }
        
        CustInfoResDTO custinfo = custManageRSV.findCustInfoById(msgReqDTO.getStaff().getId());//查询用户信息
        //查询不到用户时，也直接返回0
        if (custinfo == null) {
        	result.put("msgCnt", 0);//买家未读消息数量
            result.put("msgCnt1", 0);//卖家未读消息数量
            return result;
        }
        //获取未读消息数量
        msgReqDTO.setStaffId(msgReqDTO.getStaff().getId());//获取当前登录用户ID
        msgReqDTO.setReadFlag(BaseMsgConstants.SYS_MSGINSITE_UNREAD);//00 ： 未读  ； 10 ： 已读
        msgReqDTO.setMsgType("01");//消息分类；01：买家消息、 02：卖家消息、 03：管理员消息
        Long Count = msgInsiteRSV.countMsgInsiteByrole(msgReqDTO);//买家未读消息数量
        
        if(StringUtil.isNotBlank(custinfo.getCustShopFlag()) && StaffConstants.custInfo.SHOP_FLAG_YES.equals(custinfo.getCustShopFlag())){//如果是卖家用户则满足条件
            msgReqDTO.setMsgType("02");//消息分类；01：买家消息、 02：卖家消息、 03：管理员消息
            Long Count1 = msgInsiteRSV.countMsgInsiteByrole(msgReqDTO);//卖家未读消息数量
            if((Count1 != null && Count1 != 0L)){
                result.put("msgCnt1", Count1);
            }else {
                result.put("msgCnt1", 0);
            }
        }
        if((Count != null && Count != 0L)){
            result.put("msgCnt", Count);
        }else {
            result.put("msgCnt", 0);
        }
            result.put("msgcustflag", custinfo.getCustShopFlag());//作为判断是否是卖家的标志
            
        return result;
    }
}

