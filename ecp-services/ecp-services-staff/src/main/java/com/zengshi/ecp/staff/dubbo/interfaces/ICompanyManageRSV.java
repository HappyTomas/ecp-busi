package com.zengshi.ecp.staff.dubbo.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CompanyInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanyInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: dubbo层  企业管理服务接口<br>
 * Date:2015-8-21上午11:32:05  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public interface ICompanyManageRSV {

    /**
     * 
     * addShopInfo:(企业新增店铺信息). <br/> 
     *  1.新增t_shop_info店铺信息
     *  2.根据店铺联系人信息生成账号，t_auth_staff登录账号为联系人手机号码，初始化密码为OK，用户分组默认为卖家会员分组10003，新增t_cust_info会员信息。
     *  3.短信通知联系人手机号码。
     *  4.T_COMPANY_SHOP_IDX企业对应店铺索引表新增。
     *  5.T_SHOP_NAME_IDX店铺名称查询索引表新增。
     *  6.T_COMPANY_STAFF_IDX企业对应会员索引表新增。
     * @author huangxl 
     * @param shopInfo
     * @return shopInfo
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public ShopInfoResDTO saveShopInfo(ShopInfoReqDTO shopInfo) throws BusinessException;
    
    /**
     * 
     * listCompanyInfo:(企业列表查询). <br/> 
     * 
     * @author huangxl 
     * @param companyInfo
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public PageResponseDTO<CompanyInfoResDTO> listCompanyInfo(CompanyInfoReqDTO companyInfo) throws BusinessException;
    
    /**
     * 
     * addCompanyInfo:(新增企业信息). <br/> 
     * 
     * @author huangxl 
     * @param companyInfo
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public long saveCompanyInfo(CompanyInfoReqDTO companyInfo) throws BusinessException;
    
    /**
     * 
     * updateCompanyInfo:(更新企业信息). <br/> 
     * 
     * @author huangxl 
     * @param companyInfo
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int updateCompanyInfo(CompanyInfoReqDTO companyInfo) throws BusinessException;
    
  
    /**
     * 
     * findCompanyInfoById:(根据ID获取企业信息). <br/> 
     * 
     * @author huangxl 
     * @param id
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public CompanyInfoResDTO findCompanyInfoById(Long id) throws BusinessException;
    
    /**
     * 
     * findCompanyInfoByName:(通过企业名称查询企业信息). <br/> 
     * 
     * @author huangxl 
     * @param companyName
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public CompanyInfoResDTO findCompanyInfoByName(String companyName) throws BusinessException;
    
    /**
     * 
     * updateCompanyStatus:(更新企业状态). <br/> 
     * 要判断企业下挂的店铺、会员是否都失效了，如果没有，则不能进行企业状态的失效
     * 生效操作，不需要进行验证
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int updateCompanyStatus(CompanyInfoReqDTO req) throws BusinessException;
}

