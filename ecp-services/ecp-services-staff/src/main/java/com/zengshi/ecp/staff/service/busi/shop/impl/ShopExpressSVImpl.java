package com.zengshi.ecp.staff.service.busi.shop.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.antlr.v4.parse.ANTLRParser.throwsSpec_return;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.mapper.busi.ShopExpressRelMapper;
import com.zengshi.ecp.staff.dao.model.ShopExpressRel;
import com.zengshi.ecp.staff.dao.model.ShopExpressRelCriteria;
import com.zengshi.ecp.staff.dubbo.dto.ShopExpressReqDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.shop.interfaces.IShopExpressSV;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:店铺物流关系操作管理类 <br>
 * Description: <br>
 * Date:2015年9月6日下午4:22:41  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6
 */
public class ShopExpressSVImpl implements IShopExpressSV{

    /**
     * 店铺物流关系索引表操作
     */
    @Resource
    private ShopExpressRelMapper shopExpressRelMapper;
    @Resource(name = "seq_shop_express_rel_id")
    private Sequence sequence; 
    
    @Override
    public List<ShopExpressRel> list(Long pShopId) {
        
        ShopExpressRelCriteria criteria = new ShopExpressRelCriteria();
        criteria.createCriteria().andShopIdEqualTo(pShopId);
        
        List<ShopExpressRel> result = null;
        try {
            result = shopExpressRelMapper.selectByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        
        return result;
    }

	
	/** 
	 * @Function: insert
	 * @Description: <查询店铺物流关系表记录>
	 *               <直接查询多条记录>
	 *
	 * @param pRecords
	 * @return 
	 * @see com.zengshi.ecp.staff.service.busi.shop.interfaces.IShopExpressSV#insert(java.util.List) 
	 */ 
	@Override
	public int insert(ShopExpressRel record)
	{
		try
		{
			shopExpressRelMapper.insert(record);
		}
		catch( Exception e )
		{
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		
		return 0;
		
	}

	
	/** 
	 * @Function: delete
	 * @Description: <一句话功能描述>
	 *               <功能详细描述>
	 *
	 * @param pShopId
	 * @return 
	 * @see com.zengshi.ecp.staff.service.busi.shop.interfaces.IShopExpressSV#delete(java.lang.Long) 
	 */ 
	@Override
	public int delete(Long pShopId)
	{
		
		ShopExpressRelCriteria criteria = new ShopExpressRelCriteria();
		criteria.createCriteria().andShopIdEqualTo(pShopId);
		try
		{
			shopExpressRelMapper.deleteByExample(criteria);
		}
		catch( Exception e )
		{
			e.printStackTrace();
			throw e;
		}
		
		return 0;
		
	}


	
	/** 
	 * @Function: nextId
	 * @Description: <返回店铺物流关系表中的ID值>
	 *               <每次返回自曾>
	 *
	 * @return 
	 * @see com.zengshi.ecp.staff.service.busi.shop.interfaces.IShopExpressSV#nextId() 
	 */ 
	@Override
	public long nextId()
	{
		
		// TODO Auto-generated method stub
		return sequence.nextValue();
		
	}


    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.staff.service.busi.shop.interfaces.IShopExpressSV#update(com.zengshi.ecp.staff.dubbo.dto.ShopExpressReqDTO) 
     */
    @Override
    public int update(ShopExpressReqDTO reqDto) {
        
        //删除该店铺对应的物流信息
        delete(Long.valueOf(reqDto.getShopId()));

        if(!StringUtil.isBlank(reqDto.getExpressIds()))
        {
            String[] expressIds = reqDto.getExpressIds().split(",");
            for(String idString : expressIds)
            {
                ShopExpressRel rel = new ShopExpressRel();
                rel.setId(nextId());
                rel.setShopId(Long.valueOf(reqDto.getShopId()));
                rel.setExpressId(Long.valueOf(idString));
                rel.setStatus("1");
                rel.setCreateStaff(reqDto.getStaff().getId());
                rel.setCreateTiime(new Timestamp(System.currentTimeMillis()));
                rel.setUpdateStaff(reqDto.getStaff().getId());
                rel.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                try
                {
                    //插入物流信息
                    insert(rel);
                }
                catch( Exception e )
                {
                    throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR, new String[]{"======添加物流信息时发生错误，参数："+reqDto.toString()});
                }
                
            }
            
        }

        return 0;
        
    }

}

