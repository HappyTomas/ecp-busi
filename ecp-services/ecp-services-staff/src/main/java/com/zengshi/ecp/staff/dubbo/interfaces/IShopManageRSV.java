package com.zengshi.ecp.staff.dubbo.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopHotlineResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopHotlineReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopStaffGroupReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopStaffGroupResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopVipLevelResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopVipLevelReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopVipRealResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopVipRealSIDXReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopVipRealReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopVipRuleReqDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: dubbo层：店铺会员、客服服务接口<br>
 * Date:2015-8-20下午3:11:31  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public interface IShopManageRSV {

    /**
     * 
     * listShopHotline:(查询店铺客服列表). <br/> 
     * 
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public PageResponseDTO<ShopHotlineResDTO> listShopHotline(ShopHotlineReqDTO req) throws BusinessException;
    
    /**
     * 
     * saveShopHotline:(保存店铺客服信息). <br/> 
     * 
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int saveShopHotline(ShopHotlineReqDTO req) throws BusinessException;
    
    /**
     * 
     * updateShopHotline:(更新店铺客服信息). <br/> 
     * 
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int updateShopHotline(ShopHotlineReqDTO req) throws BusinessException;
    
    /**
     * 
     * deleteShopHotline:(删除店铺客服信息：物理删除). <br/> 
     * 
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int deleteShopHotline(ShopHotlineReqDTO req) throws BusinessException;
    
    /**
     * 
     * saveShopVipLevel:(保存店铺会员等级设置). <br/> 
     * 
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int saveShopVipLevel(ShopVipLevelReqDTO req) throws BusinessException;
    
    /**
     * 
     * updateShopVipLevel:(更新店铺会员等级设置). <br/> 
     * 
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int updateShopVipLevel(ShopVipLevelReqDTO req) throws BusinessException;
    
    /**
     * 
     * listShopVipLevel:(获取店铺会员等级列表). <br/> 
     * 根据店铺去查
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public PageResponseDTO<ShopVipLevelResDTO> listShopVipLevel(ShopVipLevelReqDTO req) throws BusinessException;
    
    
    /**
     * 
     * listShopVipReal:(获取店铺会员列表信息). <br/> 
     * 根据店铺去查
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public PageResponseDTO<ShopVipRealResDTO> listShopVipReal(ShopVipRealReqDTO req) throws BusinessException;
    
    /**
     * 
     * listShopVipReal:(获取店铺会员列表信息). <br/> 
     * 根据会员去查
     * 
     * @author linby
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public PageResponseDTO<ShopVipRealResDTO> listShopVipReal(ShopVipRealSIDXReqDTO req) throws BusinessException;
    
    /**
     * 
     * listShopStaffGroup:(根据既定条件查找店铺会员分组|分页). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<ShopStaffGroupResDTO> listShopStaffGroup(ShopStaffGroupReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * queryShopStaffGroup:(查询店铺会员所在的店铺会员分组关系). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public String queryShopStaffGroup(ShopStaffGroupReqDTO reqDto) throws BusinessException;
    
    
    /**
     * 
     * listShopVipReal:(店铺会员失效/生效操作). <br/> 
     * status='0'--失效 
     * status='1'--生效 
     * @author chenyz3 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int updateOffOrOnShopVipReal(ShopVipRealReqDTO req) throws BusinessException;
    
  
    
    /**
     * 
     * listShopVipReal:(店铺会员关系删除操作). <br/> 
     *  
     * @author chenyz3 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int deleteShopVipReal(ShopVipRealReqDTO req) throws BusinessException;
    
    /**
     * 
     * deleteShopVipLevel:(删除店铺vip等级信息). <br/> 
     * 主要是根据shopId删除
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int deleteShopVipLevel(ShopVipLevelReqDTO req) throws BusinessException;
    
    /**
     * 
     * saveShopVipRule:(保存店铺vip会员规则). <br/> 
     * 
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int saveShopVipRule(ShopVipRuleReqDTO req) throws BusinessException;
    
    /**
     * 
     * deleteShopVipRule:(删除店铺vip会员规则信息). <br/> 
     * 
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int deleteShopVipRule(ShopVipRuleReqDTO req) throws BusinessException;
    
    /**
     * 
     * deleteShopLevelByLevelCode:(通过vipLevelCode删除数据). <br/> 
     * 删除t_shop_vip_level，
     * 同时删除t_shop_vip_rule表中对应数据
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int deleteShopLevelByLevelCode(ShopVipLevelReqDTO req) throws BusinessException;
    
    /**
     * 
     * updateShopVipReal:(更新店铺会员表). <br/> 
     * 
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int updateShopVipReal(ShopVipRealReqDTO req) throws BusinessException;
    
    
    /**
     * 
     * saveShopVipReal:(保存店铺会员). <br/> 
     * 
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int saveShopVipReal(ShopVipRealReqDTO req) throws BusinessException;
    
}

