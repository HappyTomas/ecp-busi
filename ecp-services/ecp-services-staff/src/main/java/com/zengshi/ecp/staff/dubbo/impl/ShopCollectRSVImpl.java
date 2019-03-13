package com.zengshi.ecp.staff.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.model.ShopCollect;
import com.zengshi.ecp.staff.dubbo.dto.ShopCollectReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopCollectRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IShopMgrSV;
import com.zengshi.ecp.staff.service.busi.shop.interfaces.IShopCollectSV;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

public class ShopCollectRSVImpl implements IShopCollectRSV {

    @Resource
    private  IShopCollectSV shopCollectService;
    @Resource
    private IShopMgrSV shopMgrService;
    
    @Override
    public int insertShopCollect(ShopCollectReqDTO pShopClDTO) throws BusinessException {
        //1.执行参数规则校验
        if(pShopClDTO == null)
        {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        PageResponseDTO<ShopInfoResDTO> page = this.listShopCollect(pShopClDTO);
        if (page != null && CollectionUtils.isNotEmpty(page.getResult())) {
        	throw new BusinessException("您已经收藏了此店铺。");
        }
        try {
            shopCollectService.insertShopCollect(pShopClDTO);
        } catch (Exception e) {
            // TODO: 抛出更新异常
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR, new String[]{pShopClDTO.toString()});
        }
        
        return 0;
    }

    @Override
    public int deleteShopCollect(Long pCollectID) throws BusinessException {
        //1.执行参数规则校验
        if(pCollectID == null)
        {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        try {
            shopCollectService.deleteShopCollect(pCollectID);
        } catch (Exception e) {
            // TODO: 抛出删除异常
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR, new String[]{pCollectID.toString()});
        }
        
        return 0;
    }

    @Override
    public PageResponseDTO<ShopInfoResDTO> listShopCollect(ShopCollectReqDTO pShopCleDTO)throws BusinessException {
        //1.执行参数规则校验
        if(pShopCleDTO == null)
        {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        PageResponseDTO<ShopInfoResDTO> resultPage = new PageResponseDTO<ShopInfoResDTO>();
        //2.通过staffID找出关注店铺记录
        List<ShopCollect> resultList = null;
        try {
            resultList = shopCollectService.listShopCollect(pShopCleDTO);
        } catch (Exception e) {
            // TODO: 抛出查询异常
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{pShopCleDTO.toString()});
        }
                
        if(CollectionUtils.isEmpty(resultList))
        {
            resultPage.setCount(0);
            resultPage.setPageCount(0);
            return resultPage;
        }
        List<Long> sShopIDList = new ArrayList<Long>(resultList.size());
        for(ShopCollect sc : resultList)
        {
            sShopIDList.add(Long.valueOf(sc.getShopId()));
        }
        //构造返回数据
        PageResponseDTO<ShopInfoResDTO> result = PageResponseDTO.buildByBaseInfo(pShopCleDTO, ShopInfoResDTO.class);
        
        result.setPageNo(pShopCleDTO.getPageNo());
        result.setPageSize(pShopCleDTO.getPageSize());
        result = shopMgrService.listShop(pShopCleDTO, sShopIDList);
        return result;
    }

    @Override
    public long countByShopId(ShopCollectReqDTO req) throws BusinessException {
        //入参对象不能为空
        if (req == null) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"入参对象"});
        }
        if (StringUtil.isBlank(req.getShopId())) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"店铺id"});
        }
        return shopCollectService.countByShopId(req);
    }

    @Override
    public int deleteShopBySel(ShopCollectReqDTO req) throws BusinessException {
        //入参对象不能为空
        if (req == null) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"入参对象"});
        }
        //店铺id
        if (StringUtil.isBlank(req.getShopId())) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"店铺id"});
        }
        //会员id
        if (req.getStaffId() == null || req.getStaffId() == 0L) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"会员id"});
        }
        return shopCollectService.deleteShopBySel(req);
    }

	@Override
	public PageResponseDTO<ShopInfoResDTO> listShopCollectForFav(ShopCollectReqDTO req) throws BusinessException {
		
		return shopCollectService.listCollectShopForFav(req);
	}

}

