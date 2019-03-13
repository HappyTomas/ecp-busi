/** 
 * Project Name:ecp-services-sys 
 * File Name:IMsgInsiteSV.java 
 * Package Name:com.zengshi.ecp.sys.service.busi.interfaces 
 * Date:2016年3月11日上午10:54:18 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.sys.dao.model.MsgInsite;
import com.zengshi.ecp.sys.dubbo.dto.MsgDefineReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgInsiteReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgInsiteResDTO;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2016年3月11日上午10:54:18  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public interface IMsgInsiteSV extends IGeneralSQLSV{
    
    /**
     * 
     * saveMsgInsite 保存用户接收的站内消息；
     * 
     * @author yugn 
     * @throws Exception 
     * @since JDK 1.6
     */
    public void insertMsgInsite(MsgInsite msgInsite);
    
    
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
     * countMsgInsiteByrole:(根据根据用户ID、消息分类、统计该用户此类消息有多少). <br/> 
     * @author chenyz3 
     * @param req
     * @return 
     * @since JDK 1.6
     */
    public long countMsgInsiteByrole(MsgInsiteReqDTO req,List<String> codeList);
    
    
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
     * listMsgInsite:(查询站内消息). <br/> 
     * @author chenyz3 
     * @param req
     * @param codeList
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<MsgInsiteResDTO> listMsgInsite(MsgInsiteReqDTO req,List<String> codeList)throws BusinessException;
            
   

}

