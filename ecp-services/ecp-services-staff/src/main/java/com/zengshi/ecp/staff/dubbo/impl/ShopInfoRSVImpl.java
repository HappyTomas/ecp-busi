package com.zengshi.ecp.staff.dubbo.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.model.ShopExpressRel;
import com.zengshi.ecp.staff.dao.model.ShopInfo;
import com.zengshi.ecp.staff.dubbo.dto.CompanyInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopExpressReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopExpressResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopSelectReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.dubbo.util.StaffUtil;
import com.zengshi.ecp.staff.facade.interfaces.eventual.IShopMainSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.ICompanyManageSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IShopMgrSV;
import com.zengshi.ecp.staff.service.busi.shop.interfaces.IShopExpressSV;
import com.zengshi.ecp.staff.service.util.StaffTools;
import com.zengshi.ecp.sys.dubbo.dto.BaseExpressReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.BaseExpressRespDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IBaseExpressRSV;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.paas.utils.ThreadId;
import com.alibaba.dubbo.common.utils.CollectionUtils;

public class ShopInfoRSVImpl implements IShopInfoRSV {
   
    /**
     * 调用service层店铺管理操作类，由spring框架注入bean
     */
    @Resource
    private IShopMgrSV shopMgrService;
    private static final String MODULE = ShopInfoRSVImpl.class.getName();

    @Resource
    private IShopMainSV shopMainSV;
    
    @Resource
    private IShopExpressSV shopexpressSV;
    
    @Resource
    private IBaseExpressRSV baseExpressRsv;
    
    @Resource
    private ICompanyManageSV companyManageSV;

    @Override
    public PageResponseDTO<ShopInfoResDTO> listShopInfoByCond(ShopSelectReqDTO sCond) throws BusinessException{
        

        //根据sCond查询分别查询符合条件的店铺信息
        //1.查询条件验证
        //1.1执行参数验证规则
        if(sCond == null)
        {
            LogUtil.info(MODULE, "ThreadId:["+ThreadId.getThreadId()+"];查询店铺信息listShopInfoByCond(ShopSelectCond sCond)入参异常");
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        //构造返回数据
        PageResponseDTO<ShopInfoResDTO> result = PageResponseDTO.buildByBaseInfo(sCond, ShopInfoResDTO.class);
        
        result.setPageNo(sCond.getPageNo());
        result.setPageSize(sCond.getPageSize());
        //1.2通过企业ID，查询店铺信息
        try {
            result = shopMgrService.listShopByCond(sCond);
        } catch (Exception e) {
            LogUtil.info(MODULE, "ThreadId:["+ThreadId.getThreadId()+"];通过查询条件查询店铺信息出现异常：", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR,new String[]{"t_shop_info"});
        }

        return result;
    }

    @Override
    public void invalidShop(ShopInfoReqDTO req) throws BusinessException{
        if(req == null)
        {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"======失效店铺信息入参不能为空======"});

        }
        try {
            //店铺失效，同时下架店铺所属商品的最终一致性事务方法
            shopMainSV.invalidShop(req);
        } catch (Exception e) {
            LogUtil.info(MODULE, "ThreadId:["+ThreadId.getThreadId()+"];执行失效店铺出现异常：", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR);
        }
    }
    @Override
    public void activeShop(Long shopID) throws BusinessException{
        if(shopID == null || shopID < 0)
        {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"======生效店铺信息入参不能为空======"});

        }
        //1.根据shopID使店铺状态生效
        ShopInfo shopInfo = new ShopInfo();
        shopInfo.setId(shopID);
        shopInfo.setShopStatus(StaffConstants.shopInfo.SHOP_STATUS_NOMAL);
        //如果该店铺有所属企业，则在激活之前，需要企业的状态为正常
        ShopInfo shopRes = shopMgrService.findShopByShopID(shopID);
        if (shopRes.getCompanyId() != null && shopRes.getCompanyId() != 0L) {
            CompanyInfoResDTO companyRes = companyManageSV.findCompanyInfoById(shopRes.getCompanyId());
            if (StaffConstants.companyInfo.COMPANY_STATUS_INVALID.equals(companyRes.getStatus())) {
                throw new BusinessException(StaffConstants.custInfo.CUST_STATUS_OPT_ERROR);
            }
        }
        try {
            shopMgrService.updateShopInfo(shopInfo);
            //更新店铺搜索数据
            StaffTools.solrUpdate("T_SHOP_INFO", shopInfo.getId());
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.info(MODULE, "ThreadId:["+ThreadId.getThreadId()+"];执行生效店铺出现异常：", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR);
        }
    }
    @Override
    public void insertShopInfo(ShopInfoReqDTO sReqDTO) throws BusinessException{
        if(sReqDTO == null)
        {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"======插入店铺信息入参不能为空======"});

        }
        //1.补偿用户信息
        ShopInfo shopInfo = convetToDAO(sReqDTO);
        shopInfo.setCreateStaff(sReqDTO.getStaff().getId());
        shopInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        shopInfo.setUpdateStaff(sReqDTO.getStaff().getId());
        shopInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        
        try {
            shopMgrService.insertShopInfo(shopInfo);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.info(MODULE, "ThreadId:["+ThreadId.getThreadId()+"];执行插入店铺信息出现异常：", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR);
        }
        //更新店铺搜索数据
        StaffTools.solrUpdate("T_SHOP_INFO", sReqDTO.getId());
    }
    
    /**
     * 
     * TODO 更新店铺信息. 
     * @see com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV#updateShopInfo(com.zengshi.ecp.staff.dubbo.dto.ShopInfoReqDTO)
     */
    @Override
    public int updateShopInfo(ShopInfoReqDTO sReqDTO) throws BusinessException{
        if(sReqDTO == null)
        {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"======更新店铺信息入参不能为空======"});

        }
        //转换DTO参数为DAO格式
        ShopInfo info = convetToDAO(sReqDTO);
        info.setUpdateStaff(sReqDTO.getStaff().getId());
        info.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        try {
            shopMgrService.updateShopInfo(info);
            if (StringUtil.isNotBlank(info.getShopStatus()) && StaffConstants.shopInfo.SHOP_STATUS_INVALID.equals(info.getShopStatus())) {
            	//更新店铺搜索数据
                StaffTools.solrDelete("T_SHOP_INFO", info.getId());
            } else {
            	//更新店铺搜索数据
                StaffTools.solrUpdate("T_SHOP_INFO", info.getId());
            }
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.info(MODULE, "ThreadId:["+ThreadId.getThreadId()+"];更新店铺信息出现异常：", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR);
   
        }
        return 0;
    }
    /**
     * 
     * TODO 根据店铺ID找到对应的店铺信息（可选）. 
     * @see com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV#findShopInfoByShopID(java.lang.Long)
     */
    @Override
    public ShopInfoResDTO findShopInfoByShopID(Long shopID) throws BusinessException{
        if(shopID == null || shopID < 0)
        {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"======根据店铺ID查找店铺信息入参不能为空======"});

        }
        ShopInfo info = null;
        try {
            info = shopMgrService.findShopByShopID(shopID);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.info(MODULE, "ThreadId:["+ThreadId.getThreadId()+"];根据店铺ID查找店铺信息出现异常：", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR);
        }
        if(info == null)
        {
            return null;
        }
        ShopInfoResDTO dto = convetToDTO(info);
        CompanyInfoResDTO infoResDTO = StaffUtil.getComapnyCache().get(dto.getCompanyId());
        if(infoResDTO != null)
        {
            dto.setCompanyName(infoResDTO.getCompanyName());
        }
        //将logopath值，转换为url地址
        if(!StringUtil.isBlank(dto.getLogoPath()))
        {
            String url = ImageUtil.getImageUrl(dto.getLogoPath());
            if(url != null)
            {
                dto.setLogoPathURL(url);
            } 
        }
        return dto;
    } 
    @Override
    public Map<String, ShopExpressResDTO> listShopExpress(Long shopId) throws BusinessException {
        List<ShopExpressRel> modelList = null;
        try {
            modelList = shopexpressSV.list(shopId);
        } catch (Exception e) {
            // TODO: handle exception
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{"通过该店铺ID："+shopId.toString()+"查询物流信息列表失败"});
        }
        if(!CollectionUtils.isEmpty(modelList))
        {
            Map<String, ShopExpressResDTO> result = new HashMap<String, ShopExpressResDTO>(modelList.size());
            for(ShopExpressRel modle: modelList)
            {
                ShopExpressResDTO dto = new ShopExpressResDTO();
                ObjectCopyUtil.copyObjValue(modle, dto, null, false);
                result.put(String.valueOf(dto.getExpressId()), dto);
            }
            return result;
        }
        return null;
    }
	/** 
	 * @Function: updateShopExpress
	 * @Description: <更新店铺物流信息>
	 *               <功能详细描述>
	 *
	 * @param reqDto
	 * @return
	 * @throws BusinessException 
	 * @see com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV#updateShopExpress(com.zengshi.ecp.staff.dubbo.dto.ShopExpressReqDTO) 
	 */ 
	@Override
	public int updateShopExpress(ShopExpressReqDTO reqDto) throws BusinessException
	{
		if(reqDto == null || Long.valueOf(reqDto.getShopId()) <= 0)
		{
			throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
		}
    
		try
		{
			shopexpressSV.update(reqDto);
		}
		catch( Exception e )
		{
			throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR, new String[]{"======更新物流信息时发生错误，参数："+reqDto.toString()});
		}

		return 0;
		
	}
    /** 
     * TODO 根据店铺ID，查询该店铺所对应的物流公司Map对象. 
     * @see com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV#listExpress(java.lang.Long) 
     */
    @Override
    public Map<Long, String> listExpress(Long pShopId) throws BusinessException {
        if(pShopId == null)
        {
            LogUtil.info(MODULE, "===============查询店铺物流公司入参不能为空=================");
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        //1.获取该店铺所对应的物流公司ID
        List<ShopExpressRel> shopExpressRelsList = null;
        try {
            shopExpressRelsList = shopexpressSV.list(pShopId);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.info(MODULE, "===============查询店铺["+pShopId+"]物流公司出现异常=================");
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{"通过该店铺ID："+pShopId.toString()+"查询物流信息列表失败"});
        }
        if(CollectionUtils.isEmpty(shopExpressRelsList))
        {
            LogUtil.info(MODULE, "===============该店铺["+pShopId+"]没有配置对应的物流公司=================");
            return null;
        }
        BaseExpressReqDTO baseexpressreqdto = new BaseExpressReqDTO();
        List<BaseExpressRespDTO> baseExpressReqDTOs = baseExpressRsv.fetchActiveExpressInfo(baseexpressreqdto);
        //如果平台上没有对应有效的物流公司，则返回空
        if(CollectionUtils.isEmpty(baseExpressReqDTOs))
        {
            LogUtil.info(MODULE, "===============平台没有配置有效的物流公司=================");
            return null;
        }
        //将List转换成Map
        Map<Long, String> beNameMap = new HashMap<Long, String>(baseExpressReqDTOs.size());
        for(BaseExpressRespDTO respDTO : baseExpressReqDTOs)
        {
            beNameMap.put(respDTO.getId(), respDTO.getExpressName());
        }
        Map<Long, String> result = new HashMap<Long, String>(shopExpressRelsList.size());
        //2.根据物流公司ID列表，构建id-name的Map对象
        for(ShopExpressRel rel: shopExpressRelsList)
        {
            if(beNameMap.get(rel.getExpressId()) != null)
             {
                result.put(rel.getExpressId(), beNameMap.get(rel.getExpressId()));
             }
            else {
                LogUtil.info(MODULE, "========平台没有对应店铺配置该物流公司["+rel.getExpressId()+"]============");
            }
        }
        //清理
        beNameMap.clear();
        baseExpressReqDTOs.clear();
        shopExpressRelsList.clear();
        
        return result;
    }
    /**
     * 
     * convetToDTO:(转换ShopInfo类型参数为ShopInfoRespDTO类型，通过dubbo服务返回). <br/> 
     * @author PJieWin 
     * @param sInfo
     * @return 
     * @since JDK 1.6
     */
    private ShopInfoResDTO convetToDTO(ShopInfo sInfo) throws BusinessException
    {
        ShopInfoResDTO dto = new ShopInfoResDTO();
        try {
            ObjectCopyUtil.copyObjValue(sInfo, dto, null, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dto;
    }
    /**
     * 
     * convetToDAO:(转换参数ShopInfoReqDTO类型的对象为ShopInfo类型，用于DAO层操作). <br/> 
     * @author PJieWin 
     * @param sReqDTO
     * @return 
     * @since JDK 1.6
     */
    private ShopInfo convetToDAO(ShopInfoReqDTO sReqDTO) throws BusinessException
    {
        ShopInfo info = new ShopInfo();
        try {
            ObjectCopyUtil.copyObjValue(sReqDTO, info, null, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return info;
    }

}

