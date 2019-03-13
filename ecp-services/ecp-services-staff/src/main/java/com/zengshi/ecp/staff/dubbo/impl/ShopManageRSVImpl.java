package com.zengshi.ecp.staff.dubbo.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopHotlineReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopHotlineResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopStaffGroupReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopStaffGroupResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopVipLevelReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopVipLevelResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopVipRealReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopVipRealResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopVipRealSIDXReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopVipRuleReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopManageRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IShopManageSV;
import com.zengshi.ecp.staff.service.busi.shop.interfaces.IShopStaffGroupSV;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: dubbo层：店铺会员、客服服务接口实现类<br>
 * Date:2015-8-20下午3:16:34  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public class ShopManageRSVImpl implements IShopManageRSV{
    
    private static final String MODULE = ShopManageRSVImpl.class.getName();

    @Resource
    private IShopManageSV shopManageSV;
    
    @Resource
    private IShopStaffGroupSV shopStaffGroupSV;
    
    private static Logger logger = LoggerFactory.getLogger(ShopManageRSVImpl.class);
    
    @Override
    public PageResponseDTO<ShopHotlineResDTO> listShopHotline(ShopHotlineReqDTO req)
            throws BusinessException {
        PageResponseDTO<ShopHotlineResDTO> page = new PageResponseDTO<ShopHotlineResDTO>();
        try {
            page = shopManageSV.listShopHotline(req);
        } catch (Exception e) {
            logger.error("查询异常：", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR,new String[]{});
        }
        return page;
    }

    @Override
    public int saveShopHotline(ShopHotlineReqDTO req) throws BusinessException {
        //入参对象不能为空
        if (req == null) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"入参对象"});
        }
        //客服QQ
        if (StringUtil.isBlank(req.getHotlineQq())) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"客服QQ"});
        }
        //客服名称
        if (StringUtil.isBlank(req.getHotlinePerson())) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"客服名称"});
        }
        //客服电话
        if (req.getHotlinePhone() == 0L) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"客服名称"});
        }
        //管辖业务
        if (StringUtil.isBlank(req.getModuleType())) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"管辖业务"});
        }
        //QQ类型
        if (StringUtil.isBlank(req.getQqType())) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"QQ类型"});
        }
        
        int result = 0;
        try {
            result = shopManageSV.saveShopHotline(req);
        } catch (Exception e) {
            logger.error("插入异常：", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR,new String[]{});
        }
        return result;
    }

    @Override
    public int updateShopHotline(ShopHotlineReqDTO req) throws BusinessException {
        //入参对象不能为空
        if (req == null) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"入参对象"});
        }
        //客服QQ
        if (StringUtil.isBlank(req.getHotlineQq())) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"客服QQ"});
        }
        //客服名称
        if (StringUtil.isBlank(req.getHotlinePerson())) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"客服名称"});
        }
        //客服电话
        if (req.getHotlinePhone() == 0L) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"客服名称"});
        }
        //管辖业务
        if (StringUtil.isBlank(req.getModuleType())) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"管辖业务"});
        }
        //QQ类型
        if (StringUtil.isBlank(req.getQqType())) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"QQ类型"});
        }
        
        int result = 0;
        try {
            result = shopManageSV.updateShopHotline(req);
        } catch (Exception e) {
            logger.error("更新异常：", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR,new String[]{});
        }
        return result;
    }

    @Override
    public int deleteShopHotline(ShopHotlineReqDTO req) throws BusinessException {
        int result = 0;
        try {
            result = shopManageSV.deleteShopHotline(req);
        } catch (Exception e) {
            logger.error("删除异常：", e);
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR,new String[]{});
        }
        return result;
    }

    @Override
    public int saveShopVipLevel(ShopVipLevelReqDTO req) throws BusinessException {
        //入参对象不能为空
        if (req == null) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"入参对象"});
        }
        //店铺会员等级编码不能为空
        if (StringUtil.isBlank(req.getVipLevelCode())) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"店铺会员等级编码"});
        }
        //店铺会员等级名称不能为空
        if (StringUtil.isBlank(req.getVipLevelName())) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"店铺会员等级名称"});
        }
        //店铺ID不能为空
        if (req.getShopId() == null || req.getShopId() == 0L ) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"店铺ID"});
        }
        //交易次数与消费金额不能同时为空
        if (StringUtil.isEmpty(req.getTradesNum()) && StringUtil.isEmpty(req.getOrderPay())) {
            throw new BusinessException("交易次数与消费金额不能同时为空");
        }
       /* //消费金额不能为空
        if (StringUtil.isEmpty(req.getOrderPay())) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"消费金额"});
        }*/
        int result = 0;
        try {
            result = shopManageSV.saveShopVipLevel(req);
        } catch (Exception e) {
            logger.error("插入异常：", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR,new String[]{});
        }
        return result;
    }

    @Override
    public int updateShopVipLevel(ShopVipLevelReqDTO req) throws BusinessException {
        //入参对象不能为空
        if (req == null) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"入参对象"});
        }
        //店铺会员等级编码不能为空
        if (StringUtil.isBlank(req.getVipLevelCode())) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"店铺会员等级编码"});
        }
        //店铺会员等级名称不能为空
        if (StringUtil.isBlank(req.getVipLevelName())) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"店铺会员等级名称"});
        }
        //店铺ID不能为空
        if (req.getShopId() == null || req.getShopId() == 0L ) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"店铺ID"});
        }
        //交易次数与消费金额不能同时为空
        if (StringUtil.isEmpty(req.getTradesNum()) && StringUtil.isEmpty(req.getOrderPay())) {
            throw new BusinessException("交易次数与消费金额不能同时为空");
        }
       /* //消费金额不能为空
        if (StringUtil.isEmpty(req.getOrderPay())) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"消费金额"});
        }*/
        int result = 0;
        try {
            result = shopManageSV.updateShopVipLevel(req);
        } catch (Exception e) {
            logger.error("更新异常：", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR,new String[]{});
        }
        return result;
    }

    @Override
    public PageResponseDTO<ShopVipLevelResDTO> listShopVipLevel(ShopVipLevelReqDTO req)
            throws BusinessException {
        PageResponseDTO<ShopVipLevelResDTO> page = new PageResponseDTO<ShopVipLevelResDTO>();
        try {
            page = shopManageSV.listShopVipLevel(req);
        } catch (Exception e) {
            logger.error("查询异常：", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR,new String[]{});
        }
        return page;
    }

    @Override
    public PageResponseDTO<ShopVipRealResDTO> listShopVipReal(ShopVipRealReqDTO req)
            throws BusinessException {

        PageResponseDTO<ShopVipRealResDTO> page = new PageResponseDTO<ShopVipRealResDTO>();
        page = shopManageSV.listShopVipReal(req);
        return page;

    }

    @Override
    public PageResponseDTO<ShopStaffGroupResDTO> listShopStaffGroup(ShopStaffGroupReqDTO reqDto)
            throws BusinessException {
        PageResponseDTO<ShopStaffGroupResDTO> res = new PageResponseDTO<ShopStaffGroupResDTO>();
        try {
            res = shopStaffGroupSV.listShopStaffGroup(reqDto);
        } catch (Exception e) {
            logger.error(MODULE, "查询异常：", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR,new String[]{"店铺会员分组"});
        }
        return res;
    }

    @Override
    public String queryShopStaffGroup(ShopStaffGroupReqDTO reqDto) throws BusinessException {
        return shopStaffGroupSV.queryShopStaffGroup(reqDto);
    }
   
   /**
    * 
    *  @author chenyz
    *  店铺会员生效/失效 
    */
     @Override
     public int updateOffOrOnShopVipReal(ShopVipRealReqDTO req) throws BusinessException {
         //入参对象不能为空
         if (req == null) {
             throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"入参对象"});
         }
         //会员状态不能为空
         if (req.getStatus() == null || req.getStatus() == " ") {
             throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"会员状态"});
         }
         
         /*//店铺ID不能为空
         if (req.getShopId() == null || req.getShopId() == 0L ) {
             throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"店铺ID"});
         }*/
      
         int result = 0;
         
         result = shopManageSV.updateOffOrOnShopVipReal(req);
         
         return result;
       
     }
     /**
      * 
      *  @author chenyz
      *  删除店铺会员关系
      */
     @Override
     public int deleteShopVipReal(ShopVipRealReqDTO req) throws BusinessException {
         //入参对象不能为空
         if (req == null) {
             throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"入参对象"});
         }
         //ID不能为空
         if (req.getId() == null || req.getId() == 0L ) {
             throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"ID"});
         }
         
        /* //店铺ID不能为空
         if (req.getShopId() == null || req.getShopId() == 0L ) {
             throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"店铺ID"});
         }*/
         int result = 0;
         try {
             result = shopManageSV.deleteShopVipReal(req);
         } catch (Exception e) {
             logger.error("删除异常：", e);
             throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR,new String[]{});
         }
         return result;
       
     }

    @Override
    public int deleteShopVipLevel(ShopVipLevelReqDTO req) throws BusinessException {
        //入参对象不能为空
        if (req == null) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"入参对象"});
        }
        //店铺id不能为空
        if (req.getShopId() == null || req.getShopId() == 0L) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"店铺id"});
        }
            
        return shopManageSV.deleteShopVipLevel(req);
    }

    @Override
    public int saveShopVipRule(ShopVipRuleReqDTO req) throws BusinessException {
        //入参对象不能为空
        if (req == null) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"入参对象"});
        }
        return shopManageSV.saveShopVipRule(req);
    }

    @Override
    public int deleteShopVipRule(ShopVipRuleReqDTO req) throws BusinessException {
        //入参对象不能为空
        if (req == null) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"入参对象"});
        }
        //店铺id不能为空
        if (StringUtil.isBlank(req.getShopId())) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"店铺id"});
        }
        return shopManageSV.deleteShopVipRule(req);
    }

    @Override
    public int deleteShopLevelByLevelCode(ShopVipLevelReqDTO req) throws BusinessException {
        //入参对象不能为空
        if (req == null) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"入参对象"});
        }
        //店铺会员等级
        if (StringUtil.isBlank(req.getVipLevelCode())) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"店铺会员等级"});
        }
        return shopManageSV.deleteShopLevelByLevelCode(req);
    }

	@Override
	public int updateShopVipReal(ShopVipRealReqDTO req)
			throws BusinessException {
		return shopManageSV.updateShopVipReal(req);
	}

	@Override
	public int saveShopVipReal(ShopVipRealReqDTO req) throws BusinessException {
		return shopManageSV.saveShopVipReal(req);
	}

	@Override
	public PageResponseDTO<ShopVipRealResDTO> listShopVipReal(ShopVipRealSIDXReqDTO req) throws BusinessException {
		return shopManageSV.listShopVipReal(req);
	}
    
}

