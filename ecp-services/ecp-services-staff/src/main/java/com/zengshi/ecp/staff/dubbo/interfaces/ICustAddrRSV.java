package com.zengshi.ecp.staff.dubbo.interfaces;

import java.util.List;

import org.stringtemplate.v4.compiler.CodeGenerator.list_return;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustAddrReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAddrResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月5日下午8:55:48  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author pengjie
 * @version  0.0.1
 * @since JDK 1.7
 * 
 * User management interface to accept the goods address
 */
public interface ICustAddrRSV {
    
    /**
     * 
     * listCustAddr:(根据用户ID列出该用户收获地址信息). <br/> 
     * 
     * @author PJieWin 
     * @param pStaffId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<CustAddrResDTO> listCustAddr(CustAddrReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * listCustAddr:(根据用户ID列出该用户收货地址信息). <br/> 
     * dubbo层服务
     * 
     * @author PJieWin 
     * @param staffID
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<CustAddrResDTO> listCustAddrPage(CustAddrReqDTO pRequestDTO) throws BusinessException;
    /**
     * 
     * findDefaultAddr:(获取默认的用户收获地址). <br/> 
     * 
     * @author PJieWin 
     * @param pStaffId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public CustAddrResDTO findDefaultAddr(CustAddrReqDTO custinfo) throws BusinessException;
    /**
     * 
     * saveCustAddr:(保存用户收货地址信息). <br/> 
     * dubbo层服务
     * 
     * @author PJieWin 
     * @param staffID
     * @param addrinfo
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public CustAddrResDTO saveCustAddr(CustAddrReqDTO addrinfo) throws BusinessException;
    /**
     * 
     * updateCustAddr:(更新用户收货地址信息). <br/> 
     * dubbo层服务
     * 
     * @author PJieWin 
     * @param staffID
     * @param addrinfo
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void updateCustAddr(CustAddrReqDTO addrinfo) throws BusinessException;
    /**
     * 
     * deleteCustAddr:(删除用户收货地址信息). <br/> 
     * dubbo层服务
     * 
     * @author PJieWin 
     * @param id
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteCustAddr(CustAddrReqDTO custinfo) throws BusinessException;
    
    /**
     * 
     * findAddr:(获取用户收货地址). <br/> 
     * 
     * @author PJieWin 
     * @param custinfo
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public CustAddrResDTO findAddr(CustAddrReqDTO custinfo) throws BusinessException;
    
    /**
     * 
     * installDefauleAddr:(设置用户收货地址为默认收货地址). <br/> 
     * 
     * @author PJieWin 
     * @param addrinfo
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int installDefauleAddr(CustAddrReqDTO addrinfo) throws BusinessException;
}

