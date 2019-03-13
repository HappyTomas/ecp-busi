package com.zengshi.ecp.staff.service.busi.custinfo.interfaces;

import com.zengshi.ecp.general.order.dto.PayQuartzInfoRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.model.AuthStaffCIDX;
import com.zengshi.ecp.staff.dao.model.AuthStaffNIDX;
import com.zengshi.ecp.staff.dao.model.CustGrowInfo;
import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dubbo.dto.CustAuthstrReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustEmailLogInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 会员资料信息服务接口<br>
 * Date:2015-8-24下午5:50:25  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public interface ICustInfoSV {

    /**
     * 
     * saveCustAuthstr:(保存会员申请企业资质信息). <br/> 
     * 
     * @author huangxl 
     * @param authstr
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int saveCustAuthstr(CustAuthstrReqDTO authstr) throws BusinessException;
    
    /**
     * 
     * saveCustEmailLog:(保存会员邮箱认证日志信息). <br/> 
     * 
     * @author huangxl 
     * @param logInfo
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public long saveCustEmailLog(CustEmailLogInfoReqDTO logInfo) throws BusinessException;
    
    /**
     * 
     * saveCustEmailLog:(更新会员邮箱认证日志信息). <br/> 
     * 
     * @author huangxl 
     * @param logInfo
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int updateCustEmailLog(CustEmailLogInfoReqDTO logInfo) throws BusinessException;
    
    /**
     * 
     * updateCustInfo:(更新会员信息). <br/> 
     *  注：有更新昵称时，需要把原来的昵称索引表删除，新增新的昵称索引表
     *  手机、邮箱的索引表，在手机验证与邮箱验证时，单独插入数据 
     * @author huangxl 
     * @param custInfo
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int updateCustInfo(CustInfoReqDTO custInfo) throws BusinessException;
    
    /**
     * 
     * saveAuthStaffEIDX:(保存邮箱与员工ID的索引表). <br/> 
     * 
     * @author huangxl 
     * @param custInfo
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int saveAuthStaffEIDX(CustInfoReqDTO custInfo) throws BusinessException;
    
    /**
     * 
     * saveAuthStaffEIDX:(保存手机号码与员工ID的索引表). <br/> 
     * 
     * @author huangxl 
     * @param custInfo
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int saveAuthStaffMIDX(CustInfoReqDTO custInfo) throws BusinessException;
    
    /**
     * 
     * findCustInfoById:(通过ID查询会员信息). <br/> 
     * 
     * @author huangxl 
     * @param staffId
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public CustInfo findCustInfoById(Long staffId) throws BusinessException;
    
    /**
     * 
     * findAuthStaffNIDX:(查询昵称是否存在). <br/> 
     * 
     * @author huangxl 
     * @param staffNIDX
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public AuthStaffNIDX findAuthStaffNIDX(AuthStaffNIDX staffNIDX) throws BusinessException;
    
    /**
     * 
     * saveCustPic:(文件保存到mogondb服务器上，并把返回的id，存到用户信息对应的custPic字段). <br/> 
     * 
     * @author huangxl 
     * @param byteFile
     * @param fileName
     * @param fileType
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int saveCustPic(CustInfoReqDTO req) throws BusinessException;
    
    /**
     * 
     * readFile:(通过文件ID获取文件字节). <br/> 
     * 
     * @author huangxl 
     * @param fileId
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public byte[] readFile(String fileId) throws BusinessException;
    
    /**
     * 
     * deleteFileById:(通过文件ID，删除文件). <br/> 
     * 
     * @author huangxl 
     * @param fileId
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void deleteFileById(String fileId) throws BusinessException;
    
    /**
     * 
     * findAuthStaffCIDX:(查询工号是否存在). <br/> 
     * 
     * @author huangxl 
     * @param staffNIDX
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public AuthStaffCIDX findAuthStaffCIDX(AuthStaffCIDX staffCIDX) throws BusinessException;
    
    /**
     * 
     * getCustInfoById:(查询客户信息，所在地域，提供订单使用). <br/> 
     * 
     * @author wangbh
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    
    public CustInfoResDTO getCustInfoById(CustInfoReqDTO custInfoReqDTO) throws BusinessException;
    
    
    /**
     * 
     * insertCustGrowInfo:(新增会员成长值明细). <br/> 
     * 
     * @author wangbh
     * @param custGrowInfo
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void insertCustGrowInfo(CustGrowInfo custGrowInfo) throws BusinessException;
    
    
    /**
     * 
     * updateCustLevelCode:(会员等级实时变更). <br/> 
     * 
     * @author wangbh
     * @param custInfoReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int updateCustLevelCode(PayQuartzInfoRequest dto) throws BusinessException;
    
    /**
     * 
     * insertCustGrowInfo:(新增会员成长值明细). <br/> 
     * 
     * @author wangbh
     * @param custGrowInfo
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void updateCustGrowInfo(CustGrowInfo custGrowInfo) throws BusinessException;
    
    /**
     * 
     * getCustNewCount:(获取前一天新增会员数). <br/> 
     * 
     * @author wangbh
     * @param custGrowInfo
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public Long getCustNewCount(CustInfoReqDTO custInfoReqDTO) throws BusinessException;
}

