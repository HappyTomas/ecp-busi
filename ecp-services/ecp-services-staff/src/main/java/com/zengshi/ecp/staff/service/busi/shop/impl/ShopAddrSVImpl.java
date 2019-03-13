/** 
 * Project Name:ecp-services-staff-server 
 * File Name:ShopAddrSVImpl.java 
 * Package Name:com.zengshi.ecp.staff.service.busi.shop.impl 
 * Date:2015年9月16日下午7:59:56 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.staff.service.busi.shop.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dao.mapper.busi.ShopShipperMapper;
import com.zengshi.ecp.staff.dao.model.ShopShipper;
import com.zengshi.ecp.staff.dao.model.ShopShipperCriteria;
import com.zengshi.ecp.staff.dubbo.dto.ShopShipperReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopShipperResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.shop.interfaces.IShopAddrSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.db.sequence.Sequence;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: <br>
 * Date:2015年9月16日下午7:59:56  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6 
 */
public class ShopAddrSVImpl extends GeneralSQLSVImpl  implements IShopAddrSV {

    private static final String MODULE = ShopAddrSVImpl.class.getName();
    
    @Resource(name = "seq_shop_shipper_id")
    private Sequence sequence; 
    
    @Resource
    ShopShipperMapper shopshipperMapper;

    /** 
     * TODO 保存店铺发货地址信息（service）. 
     * @see com.zengshi.ecp.staff.service.busi.shop.interfaces.IShopAddrSV#save(com.zengshi.ecp.staff.dao.model.ShopShipper) 
     */
    @Override
    public int save(ShopShipper shipper, long count) {
        //1.填补序列ID
        shipper.setId(sequence.nextValue());
        //如果当前店铺没有地址信息，则将该条信息设置为默认发货、退货地址
        if(count == 0)
        {
            shipper.setShipperUsingFlag("1");
            shipper.setBackUsingFlag("1");
        }
        else {
            shipper.setShipperUsingFlag("0");
            shipper.setBackUsingFlag("0");
        }
        try {
            shopshipperMapper.insertSelective(shipper);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        return 1;
    }

    /** 
     * TODO 根据id删除发货地址信息（service）. 
     * @see com.zengshi.ecp.staff.service.busi.shop.interfaces.IShopAddrSV#delete(java.lang.Long) 
     */
    @Override
    public int delete(Long id) {
        try {
            shopshipperMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        return 1;
    }

    /** 
     * TODO 更新店铺发货地址信息（service）. 
     * @see com.zengshi.ecp.staff.service.busi.shop.interfaces.IShopAddrSV#update(com.zengshi.ecp.staff.dao.model.ShopShipper) 
     */
    @Override
    public int update(ShopShipper shipper) {
        try {
            shopshipperMapper.updateByPrimaryKeySelective(shipper);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        return 1;
    }

    /** 
     * TODO 统计一个店铺下所有的收货地址总数. 
     * @see com.zengshi.ecp.staff.service.busi.shop.interfaces.IShopAddrSV#count(java.lang.Long) 
     */
    @Override
    public long count(Long shopId) {
        ShopShipperCriteria criteria = new ShopShipperCriteria();
        criteria.createCriteria().andShopIdEqualTo(shopId);
        Long count = 0L;
        try {
            count = shopshipperMapper.countByExample(criteria);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
//            throw new BusinessException(StaffConstants.ShopShipper.SHOP_SHIPPER_NUMBER_OVER);
        }
        return count;
    }

    /** 
     * TODO 查询店铺发货地址信息列表（service）. 
     * @see com.zengshi.ecp.staff.service.busi.shop.interfaces.IShopAddrSV#list(com.zengshi.ecp.staff.dubbo.dto.ShopShipperReqDTO) 
     */
    @Override
    public PageResponseDTO<ShopShipperResDTO> list(ShopShipperReqDTO req) {
        
        ShopShipperCriteria criteria = new ShopShipperCriteria();
        criteria.setLimitClauseCount(req.getPageSize());
        criteria.setLimitClauseStart(req.getStartRowIndex());
        criteria.setOrderByClause("id asc");
        
        return super.queryByPagination(req, criteria, true, new PaginationCallback<ShopShipper, ShopShipperResDTO>(){

            @Override
            public List<ShopShipper> queryDB(BaseCriteria basecriteria) {
                return shopshipperMapper.selectByExample((ShopShipperCriteria)basecriteria);
            }

            @Override
            public long queryTotal(BaseCriteria basecriteria) {
                return shopshipperMapper.countByExample((ShopShipperCriteria)basecriteria);
            }
            @Override
            public List<Comparator<ShopShipper>> defineComparators() {
                List<Comparator<ShopShipper>> ls=new ArrayList<Comparator<ShopShipper>>();
                ls.add(new Comparator<ShopShipper>(){

                    @Override
                    public int compare(ShopShipper o1, ShopShipper o2) {
                        return o1.getId() < o2.getId()?1:-1;
                    }
                    
                });
                return ls;
            }
            @Override
            public ShopShipperResDTO warpReturnObject(ShopShipper info) {
                ShopShipperResDTO sDto = new ShopShipperResDTO();
                ObjectCopyUtil.copyObjValue(info, sDto, null, false);
                
                return sDto;
            }
            
        });
    }

    /** 
     * TODO 设置默认发货地址（service）. 
     * @see com.zengshi.ecp.staff.service.busi.shop.interfaces.IShopAddrSV#choiseShipperAddr(java.lang.Long, java.lang.Long) 
     */
    @Override
    public int installShipperAddr(Long id, Long shopId) {
        ShopShipper oldrecord = new ShopShipper();
        ShopShipper newrecord = new ShopShipper();
        ShopShipperCriteria oldCriteria = new ShopShipperCriteria();
        ShopShipperCriteria newCriteria = new ShopShipperCriteria();

        
        //1.先将该店铺对应的默认发货地址取消
        oldrecord.setShipperUsingFlag("0");
        oldCriteria.createCriteria().andShopIdEqualTo(shopId).andShipperUsingFlagEqualTo("1");
        
        //2.将这条id发货地址设为默认发货地址
        newrecord.setShipperUsingFlag("1");
        newCriteria.createCriteria().andIdEqualTo(id).andShopIdEqualTo(shopId);
        try {
            shopshipperMapper.updateByExampleSelective(oldrecord, oldCriteria);
            shopshipperMapper.updateByExampleSelective(newrecord, newCriteria);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        return 0;
    }

    /** 
     * TODO 设置默认退货地址（service）. 
     * @see com.zengshi.ecp.staff.service.busi.shop.interfaces.IShopAddrSV#installReturnAddr(java.lang.Long, java.lang.Long) 
     */
    @Override
    public int installReturnAddr(Long id, Long shopId) {
        ShopShipper oldrecord = new ShopShipper();
        ShopShipper newrecord = new ShopShipper();
        ShopShipperCriteria oldCriteria = new ShopShipperCriteria();
        ShopShipperCriteria newCriteria = new ShopShipperCriteria();
        
        //1.先将该店铺对应的默认退货地址取消
        oldrecord.setBackUsingFlag("0");
        oldCriteria.createCriteria().andShopIdEqualTo(shopId).andBackUsingFlagEqualTo("1");
        
        //2.将这条id发货地址设为默认退货地址
        newrecord.setBackUsingFlag("1");
        newCriteria.createCriteria().andIdEqualTo(id).andShopIdEqualTo(shopId);
        
        try {
            shopshipperMapper.updateByExampleSelective(oldrecord, oldCriteria);
            shopshipperMapper.updateByExampleSelective(newrecord, newCriteria);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        return 0;
    }

    /** 
     * TODO 查询店铺默认收货地址信息（service）. 
     * @see com.zengshi.ecp.staff.service.busi.shop.interfaces.IShopAddrSV#select(java.lang.Long, java.lang.String) 
     */
    @Override
    public ShopShipper select(Long shopId, String flag) {
        ShopShipperCriteria criteria = new ShopShipperCriteria();
        List<ShopShipper> resultList = null;
        switch (flag) {
        case StaffConstants.ShopShipper.SHOP_SHIPPER_ADDR_FLAG:
            criteria.createCriteria().andShopIdEqualTo(shopId).andShipperUsingFlagEqualTo("1");
            break;
        case StaffConstants.ShopShipper.SHOP_RETURN_ADDR_FLAG:
            criteria.createCriteria().andShopIdEqualTo(shopId).andBackUsingFlagEqualTo("1");
            break;
        default:
            break;
        }
        try {
            resultList = shopshipperMapper.selectByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }

        return CollectionUtils.isEmpty(resultList)?null:resultList.get(0);
    }

}

