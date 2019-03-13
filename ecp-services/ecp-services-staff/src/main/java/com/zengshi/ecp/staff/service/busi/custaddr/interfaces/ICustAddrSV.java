package com.zengshi.ecp.staff.service.busi.custaddr.interfaces;


import java.util.List;

import org.stringtemplate.v4.compiler.CodeGenerator.list_return;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.staff.dao.model.CustAddr;
import com.zengshi.ecp.staff.dubbo.dto.CustAddrReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAddrResDTO;

public interface ICustAddrSV extends IGeneralSQLSV{
    
    /**
     * 
     * listCustAddr:(根据用户ID列出该用户收获地址信息)). <br/> 
     * 
     * @author PJieWin 
     * @param pStaffId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<CustAddrResDTO> listCustAddr(Long pStaffId) throws BusinessException;
    /**
     * 
     * listCustAddr:(根据用户ID列出该用户收获地址信息). <br/> 
     * service服务
     * 
     * @author PJieWin 
     * @param staffID
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<CustAddrResDTO> listCustAddr(CustAddrReqDTO pRequestDTO) throws BusinessException;
    /**
     * 
     * findDefaultAddr:(根据用户ID找出默认的用户收获地址). <br/> 
     * 
     * @author PJieWin 
     * @param pStaffId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public CustAddr findDefaultAddr(Long pStaffId) throws BusinessException;
    /**
     * 
     * saveCustAddr:(保存该用户收货地址信息). <br/> 
     * service服务
     * 
     * @author PJieWin 
     * @param staffID
     * @param addrinfo
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public Long saveCustAddr(CustAddr addrinfo) throws BusinessException;
    /**
     * 
     * updateCustAddr:(更新该用户收货地址信息). <br/> 
     * service服务
     * 
     * @author PJieWin 
     * @param staffID
     * @param addrinfo
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void updateCustAddr(CustAddr addrinfo) throws BusinessException;
    /**
     * 
     * deleteCustAddr:(删除收货地址信息). <br/> 
     * service服务
     * 
     * @author PJieWin 
     * @param id
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteCustAddr(Long id, Long staffid) throws BusinessException;
    /**
     * 
     * countCustAddrNum:(根据用户ID获取当前用户的收货地址总数). <br/> 
     * 
     * @author PJieWin 
     * @param staffID
     * @return 
     * @since JDK 1.6
     */
    public Long countCustAddrNum(Long staffID);
    
    public CustAddr findCustAddr(Long id, Long staffid);
    
    public int updateToDefaultAddr(Long id, Long staffid);
}

