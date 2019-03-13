package com.zengshi.ecp.staff.dubbo.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: dubbo层 会员管理服务接口<br>
 * Date:2015-8-12下午8:54:54  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public interface ICustManageRSV {

    /**
     * 
     * saveCustInfo:(新增会员信息). <br/> 
     * 
     * @author huangxl 
     * @param custInfo
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public Long saveCustInfo(CustInfoReqDTO custInfo) throws BusinessException;
    
    /**
     * 
     * updateCustInfo:(编缉会员信息). <br/> 
     * 
     * @author huangxl 
     * @param custInfo
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int updateCustInfo(CustInfoReqDTO custInfo) throws BusinessException;
    
    /**
     * 
     * updateCustInfo:(变更会员信息状态). <br/> 
     * 
     * @author huangxl 
     * @param custInfo
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int updateCustStatus(CustInfoReqDTO custInfo) throws BusinessException;
    
    /**
     * 
     * listCustInfo:(客户信息列表). <br/> 
     * 需要根据条件进行分开查索引表
     * @author huangxl 
     * @param info
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public PageResponseDTO<CustInfoResDTO> listCustInfo(CustInfoReqDTO info) throws BusinessException;
    
    /**
     * 
     * findCustInfoById:(通过ID查询会员信息). <br/> 
     * 
     * @author huangxl 
     * @param id
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public CustInfoResDTO findCustInfoById(Long id) throws BusinessException;
    
    /**
     * 
     * resetPwd:(用户管理：密码重置). <br/> 
     * 
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int resetPwd(AuthStaffReqDTO req) throws BusinessException;
    
    /**
     * 
     * findCustInfo:(根据会员的信息查询会员). <br/> 
     * 查询条件只能是：
     * staffCode、email、nickname、serialNumber
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public CustInfoResDTO findCustInfo(CustInfoReqDTO req) throws BusinessException;
    
    /**
     * 
     * updatePhoneByStaffId:(更新用户手机号码). <br/> 
     * 参数：staffId、staffCode、serialNumber
     * staffCode主要是为了更新索引表用
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int updatePhoneByStaffId(CustInfoReqDTO req) throws BusinessException;
    
    /**
     * 
     * updateCustInfoForEmpty:(更新用户信息). <br/> 
     * 此方法主要是用于可把用户信息的字段，设为空值
     * 主要针对Long字段
     * @author huangxl 
     * @param custInfo
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int updateCustInfoForEmpty(CustInfoReqDTO custInfo) throws BusinessException;
    
    /**
     * 
     * checkCodeExist:(校验传入的编码是否存在). <br/> 
     * 传入登录名、手机号、邮箱，都可以，因为平台可以通过上述三个字段进行登录
     * 所以要对三者进行唯一校验
     * type:1、登录名； 2、手机号；3、邮箱
     * @author huangxl 
     * @param custInfo
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean checkCodeExist(String code,Long staffId,String type) throws BusinessException;
    
    /**
     * 打上敏感用户标识
     * @param custInfoReqDTO
     * @return
     * @throws BusinessException
     */
    public int updateScust(CustInfoReqDTO custInfoReqDTO) throws BusinessException;
    
    /**
     * 删除用户敏感标识
     * @param custInfoReqDTO
     * @return
     * @throws BusinessException
     */
    public int delScust(CustInfoReqDTO custInfoReqDTO) throws BusinessException;
}

