package com.zengshi.ecp.sys.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dao.model.MsgInsite;
import com.zengshi.ecp.sys.dubbo.dto.MsgDefineReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgInsiteReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgInsiteResDTO;

public interface IMsgInsiteRSV {

    /**
     * 
     * saveMsgInsite 保存用户接收的站内消息；
     * 
     * @author yugn 
     * @throws Exception 
     * @since JDK 1.6
     */
    public void insertMsgInsite(MsgInsiteReqDTO req);
    
    
    /**
     * 
     * countMsgInsite: 根据用户ID，统计该用户有多少的站内消息 <br/> 
     * 
     * @author yugn 
     * @param staffId
     * @throws Exception 
     * @since JDK 1.6
     */
    public long countMsgInsite(long staffId);

    
    /**
     * 
     * updateMsgInsite:(未读消息更新为已读). <br/> 
     * @author chenyz3 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int updateMsgInsite(MsgInsiteReqDTO req) throws BusinessException;
   
    
    /**
     * 
     * deleteMsgInsite:(删除站内消息). <br/> 
     * @author chenyz3 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int deleteMsgInsite(MsgInsiteReqDTO req) throws BusinessException;
    
    
    /**
     * 
     * listMsgInsite:(通过MSG_TYPE查询消息模版定义表获得MSG_CODE；再根据MSG_CODE、STAFF_ID查询站内消息). <br/> 
     * @author chenyz3 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<MsgInsiteResDTO> listMsgInsiteBymsgType(MsgInsiteReqDTO req) throws BusinessException;
    
    
    /**
     * 
     * countMsgInsiteByrole:(根据根据用户ID、消息分类、统计该用户此类消息有多少). <br/> 
     * @author chenyz3 
     * @param req
     * @return 
     * @since JDK 1.6
     */
    public long countMsgInsiteByrole(MsgInsiteReqDTO req);
    
    
    /**
     * 
     * listMsgInsiteByStaff:(查询用户的消息列表). <br/> 
     * 不区分买卖家，同一个用户一起查出来
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<MsgInsiteResDTO> listMsgInsiteByStaff(MsgInsiteReqDTO req) throws BusinessException;

    /**
     * 
     * countMsgInsiteByStaff:(查询用户消息数量). <br/> 
     * 主要根据staffId、realFlag来查询
     * @author huangxl 
     * @param req
     * @return 
     * @since JDK 1.6
     */
    public long countMsgInsiteByStaff(MsgInsiteReqDTO req);
}

