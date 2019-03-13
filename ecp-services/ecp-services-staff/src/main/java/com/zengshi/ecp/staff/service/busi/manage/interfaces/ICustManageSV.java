package com.zengshi.ecp.staff.service.busi.manage.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.staff.dao.model.AuthStaffCIDX;
import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 会员管理服务接口<br>
 * Date:2015-8-12下午7:59:00  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public interface ICustManageSV extends IGeneralSQLSV{

    /**
     * 
     * saveCustInfo:(保存会员信息). <br/> 
     * 如果有所属企业，则会同时保存企业与员工的对应关系索引表
     * @author huangxl 
     * @param custInfo
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int saveCustInfo(CustInfoReqDTO custInfo) throws BusinessException;
    
    /**
     * 
     * updateCustInfo:(更新会员信息). <br/> 
     * 如果更新了所属企业，则会同时保存企业与员工的对应关系索引表
     * @author huangxl 
     * @param custInfo
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int updateCustInfo(CustInfoReqDTO custInfo) throws BusinessException;
    
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
     * deleteCustInfoById:(通用客户id删除客户). <br/> 
     * 
     * @author linby 
     * @param custInfo
     * @param isPhy     是否物理删除，true则物理删除，否则逻辑删除；默认为逻辑删除
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteCustInfoById(CustInfo custInfo, Boolean isPhy) throws BusinessException;
    
    /**
     * 
     * updateCustTypeById:(批量修改会员类型). <br/> 
     * 
     * @author wangbh
     * @param id
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void updateCustTypeById(Long companyId,String custType) throws BusinessException;
    
    /**
     * 
     * saveCustInfoForRSV:(保存会员信息,提供给rsv). <br/> 
     * 如果有所属企业，则会同时保存企业与员工的对应关系索引表
     * @author huangxl 
     * @param custInfo
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public Long saveCustInfoForRSV(CustInfoReqDTO custInfo) throws BusinessException;
    
    /**
     * 
     * updateCustStatus:(更新会员状态信息). <br/> 
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
     * resetPwd:(密码重置). <br/> 
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
     * findAuthStaffCIDXByCode:(通过staffCode查询索引对象). <br/> 
     * 
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public AuthStaffCIDX findAuthStaffCIDXByCode(AuthStaffCIDX req) throws BusinessException;
    
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
     * 标识为敏感用户
     * @param custInfoReqDTO
     * @return
     * @throws BusinessException
     */
    public int updateScust(CustInfoReqDTO custInfoReqDTO)throws BusinessException;
    
    /**
     * 删除敏感标识
     * @param custInfoReqDTO
     * @return
     * @throws BusinessException
     */
    public int delScust(CustInfoReqDTO custInfoReqDTO)throws BusinessException;
}

