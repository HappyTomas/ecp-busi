package com.zengshi.ecp.staff.service.busi.shop.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.staff.dao.model.ShopStaff2Group;
import com.zengshi.ecp.staff.dao.model.ShopStaff2GroupKey;
import com.zengshi.ecp.staff.dao.model.ShopStaffGroup;
import com.zengshi.ecp.staff.dubbo.dto.ShopStaffGroupReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopStaffGroupResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 店铺会员分组管理服务接口<br>
 * Date:2015年9月9日下午2:34:20  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public interface IShopStaffGroupSV extends IGeneralSQLSV{
    
    /**
     * 
     * saveShopStaffGroup:(新增店铺会员分组). <br/>
     *  
     * @author linby 
     * @param save
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long saveShopStaffGroup(ShopStaffGroup save) throws BusinessException;
    
    /**
     * 
     * findShopStaffGroup:(查找店铺会员分组信息). <br/> 
     * 操作时带上分库键shop_id
     * 
     * @author linby 
     * @param find
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public ShopStaffGroup findShopStaffGroup(ShopStaffGroup find) throws BusinessException;
    
    /**
     * 
     * saveShopStaff2Group:(店铺配置会员[创建会员与分组关系]). <br/> 
     * 
     * @author linby 
     * @param save
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void saveShopStaff2Group(ShopStaff2Group save) throws BusinessException;
    
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
     * findShopStaff2GroupByKey:(根据店铺会员分组key查找[校验]关系). <br/> 
     * 
     * @author linby 
     * @param key
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public ShopStaff2Group findShopStaff2GroupByKey(ShopStaff2GroupKey key) throws BusinessException;
    
    /**
     * 
     * updateShopStaffGroup:(店铺会员分组修改信息). <br/> 
     * 单条删除
     * 操作时带上分库键shop_id
     * 
     * @author linby 
     * @param update
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void updateShopStaffGroup(ShopStaffGroup update) throws BusinessException;
    
    /**
     * 
     * deleteShopStaffGroupById:(根据店铺会员分组id删除). <br/> 
     * 单条删除
     * 操作时带上分库键shop_id
     * 
     * @author linby 
     * @param id
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteShopStaffGroup(ShopStaffGroup delete) throws BusinessException;
    
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

}

