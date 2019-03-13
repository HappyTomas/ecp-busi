package com.zengshi.ecp.staff.service.busi.manage.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dao.mapper.busi.CompanyShopIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.ShopInfoMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.ShopNameIDXMapper;
import com.zengshi.ecp.staff.dao.model.CompanyShopIDX;
import com.zengshi.ecp.staff.dao.model.CompanyShopIDXCriteria;
import com.zengshi.ecp.staff.dao.model.ShopInfo;
import com.zengshi.ecp.staff.dao.model.ShopInfoCriteria;
import com.zengshi.ecp.staff.dao.model.ShopInfoCriteria.Criteria;
import com.zengshi.ecp.staff.dao.model.ShopNameIDX;
import com.zengshi.ecp.staff.dao.model.ShopNameIDXCriteria;
import com.zengshi.ecp.staff.dubbo.dto.CompanyInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopCollectReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopSelectReqDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IShopMgrSV;
import com.zengshi.ecp.staff.service.cache.interfaces.ICompanyCacheSV;
import com.zengshi.ecp.staff.service.cache.interfaces.IShopCacheSV;
import com.zengshi.ecp.staff.service.util.StaffTools;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.distribute.DistributeRuleAssist;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015�?�?0日下�?:15:28 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version
 * @since JDK 1.6
 * 
 *        店铺管理实现类，实现店铺管理的功�?
 */
public class ShopMgrSVImpl extends GeneralSQLSVImpl implements IShopMgrSV {

    /**
     * 调用店铺资料表的DAO操作类，由spring统一管理bean注入
     */
    @Resource
    private ShopInfoMapper shopMapper;

    /**
     * 企业与店铺索引表，有spring统一管理bean注入
     */
    @Resource
    private CompanyShopIDXMapper companyShopIDXMapper;

    /**
     * 店面名与店铺ID索引表操作，由spring统一管理bean注入
     */
    @Resource
    private ShopNameIDXMapper shopNmaeIDXMapper;

    /**
     * Shop缓存管理对象
     */
    @Resource
    private IShopCacheSV shopCacheSV;

    @Resource
    private ICompanyCacheSV companyCacheSV;
    

    /**
     * 店铺ID序列�?
     */
    @Resource(name = "seq_staff_shop_id")
    private Sequence sequence;

    @Override
    public List<ShopInfo> listShop() {
        List<ShopInfo> resultInfos = null;
        try {
            // /空条件查询，查全�?
            ShopInfoCriteria criteria = new ShopInfoCriteria();
            resultInfos = shopMapper.selectByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        return CollectionUtils.isEmpty(resultInfos) ? null : resultInfos;
    }

    /**
     * 
     * TODO 根据企业ID，查询对应企业下面的店铺信息.
     * 
     * @see com.zengshi.ecp.staff.service.busi.manage.interfaces.IShopMgrSV#listShopByCompanyID(java.lang.Long)
     */
    @Override
    public List<ShopInfoResDTO> listShopByCompanyID(Long sCompanyID) {

        // 1.构造查询条�?
        List<ShopInfoResDTO> listResultdto = new ArrayList<ShopInfoResDTO>();
        List<CompanyShopIDX> cs = null;
        // 1.通过企业ID先查企业店铺索引表，再分别通过获得的shopID查店铺信�?
        CompanyShopIDXCriteria criteria = new CompanyShopIDXCriteria();
        criteria.createCriteria().andCompanyIdEqualTo(sCompanyID);
        try {
            cs = companyShopIDXMapper.selectByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        // 查无数据，则返回null
        if (CollectionUtils.isEmpty(cs)) {
            return null;
        }
        // 循环通过shopID列表查询店铺信息
        for (CompanyShopIDX idx : cs) {
            try {
                ShopInfo shopinfo =  shopMapper.selectByPrimaryKey(idx.getShopId());
                ShopInfoResDTO shopInfoResDTO  = new ShopInfoResDTO();
                if(null != shopInfoResDTO){
                ObjectCopyUtil.copyObjValue(shopinfo, shopInfoResDTO, null, false);
                listResultdto.add(shopInfoResDTO);
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                throw e;
            }
        }
     
        return listResultdto;
    }

    /**
     * 
     * TODO 更新店铺信息.
     * 
     * @see com.zengshi.ecp.staff.service.busi.manage.interfaces.IShopMgrSV#updateShopInfo(com.zengshi.ecp.staff.dao.model.ShopInfo)
     */
    @Override
    public void updateShopInfo(ShopInfo sShopInfo) {

        sShopInfo.setUpdateTime(DateUtil.getSysDate());
        
        Map<Long, ShopInfoResDTO> shopcache = shopCacheSV.getCacheShop();
        ShopInfoResDTO shopOld = shopcache.get(sShopInfo.getId());
        
        if(!StringUtil.isBlank(shopOld.getShopName())&&!StringUtil.isBlank(sShopInfo.getShopName()))
        {
            if(!shopOld.getShopName().equals(sShopInfo.getShopName()))
            {
                ShopNameIDXCriteria idxCriteria = new ShopNameIDXCriteria();
                idxCriteria.createCriteria().andShopNameEqualTo(shopOld.getShopName()).andShopIdEqualTo(shopOld.getId());
                
                ShopNameIDX record = new ShopNameIDX();
                record.setShopId(sShopInfo.getId());
                record.setShopName(sShopInfo.getShopName());
                
                try {
                    shopNmaeIDXMapper.deleteByExample(idxCriteria); 
                    shopNmaeIDXMapper.insert(record);
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                    throw e;
                }
            }
        }
        
        try {
            shopMapper.updateByPrimaryKeySelective(sShopInfo);        
            shopCacheSV.init();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
         
        StaffTools.logInfo("saveShopInfoLog", sShopInfo);
        // -----------------Shop缓存维护 开始-----------------------//
        ShopInfoResDTO sDto = new ShopInfoResDTO();
        ObjectCopyUtil.copyObjValue(sShopInfo, sDto, null, false);

        shopCacheSV.update(sDto);
        // -----------------Shop缓存维护 结束------------------------//
    }

    /**
     * 
     * TODO 保存店铺信息.
     * 
     * @see com.zengshi.ecp.staff.service.busi.manage.interfaces.IShopMgrSV#insertShopInfo(com.zengshi.ecp.staff.dao.model.ShopInfo)
     */
    @Override
    public Long insertShopInfo(ShopInfo sShopInfo) {
        long count = 0;
        ShopNameIDXCriteria idxCriteria = new ShopNameIDXCriteria();
        idxCriteria.createCriteria().andShopNameEqualTo(sShopInfo.getShopName());
        try {
            count = shopNmaeIDXMapper.countByExample(idxCriteria);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        if (count > 0) {
            throw new BusinessException(StaffConstants.shopInfo.SHOP_NAME_REPEAT,
                    new String[] { sShopInfo.getShopName() });
        }

        long id = sequence.nextValue();
        sShopInfo.setId(id);
        sShopInfo.setCreateTime(DateUtil.getSysDate());
        ShopNameIDX idx = new ShopNameIDX();
        idx.setShopId(id);
        idx.setShopName(sShopInfo.getShopName()); 
        int i =0;
        try {

            ShopInfoCriteria criteria = new ShopInfoCriteria();
            Long totalCount = shopMapper.countByExample(criteria) + 1;
            sShopInfo.setHotShowSort(totalCount.shortValue());
            i = shopMapper.insertSelective(sShopInfo);
            shopNmaeIDXMapper.insert(idx);
            
            StaffTools.logInfo("savaShopInfoLog", sShopInfo);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }

        if(i>0){
        ShopInfoResDTO sDto = new ShopInfoResDTO();
        ObjectCopyUtil.copyObjValue(sShopInfo, sDto, null, false);
        shopCacheSV.add(sDto);
        }
        return id;
    }

    /**
     * 
     * TODO 通过店铺名，查找索引表找到店铺ID.
     * 
     * @see com.zengshi.ecp.staff.service.busi.manage.interfaces.IShopMgrSV#findShopIdByName(java.lang.String)
     */
    @Override
    public Long findShopIdByName(String sName) {
        ShopNameIDXCriteria criteria = new ShopNameIDXCriteria();
        criteria.createCriteria().andShopNameEqualTo(sName);

        List<ShopNameIDX> idxList = null;
        try {
            idxList = shopNmaeIDXMapper.selectByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        // 由于店铺名称与ID是一一对应的关系，所以结果集肯定只有一�?
        // 需要考虑索引表的主键设置,无奈的写�?
        if (CollectionUtils.isEmpty(idxList)) {
            return null;
        } else {
            return idxList.get(0).getShopId();
        }
    }

    /**
     * 
     * TODO 根据店铺ID查询店铺信息.
     * 
     * @see com.zengshi.ecp.staff.service.busi.manage.interfaces.IShopMgrSV#findShopByShopID(java.lang.Long)
     */
    @Override
    public ShopInfo findShopByShopID(Long sShopID) {
        ShopInfo info = null;
        try {
            info = shopMapper.selectByPrimaryKey(sShopID);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        return info;
    }

    @Override
    public PageResponseDTO<ShopInfoResDTO> listShopByCond(ShopSelectReqDTO pSelectCond) {

        PageResponseDTO<ShopInfoResDTO> result = PageResponseDTO.buildByBaseInfo(pSelectCond,
                ShopInfoResDTO.class);

        List<Long> idList = new ArrayList<Long>();
        if (pSelectCond.getCompanyID() != null) {
            // 如何企业ID不为空，则根据企业ID查索引表找出对应的店铺ID列表
            // criteria.createCriteria().andCompanyIdEqualTo(pSelectCond.getCompanyID());
            CompanyShopIDXCriteria cIdxCriteria = new CompanyShopIDXCriteria();
            cIdxCriteria.createCriteria().andCompanyIdEqualTo(pSelectCond.getCompanyID());
            List<CompanyShopIDX> sShopIDXs = null;
            try {
                sShopIDXs = companyShopIDXMapper.selectByExample(cIdxCriteria);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                throw e;
            }
            if (sShopIDXs != null && sShopIDXs.size() > 0) {
                // 将超出的店铺ID列表添加到参数中

                for (CompanyShopIDX idx : sShopIDXs) {
                    idList.add(idx.getShopId());
                }
            }
            if (CollectionUtils.isEmpty(idList)) {
                result.setCount(0);
                result.setPageCount(0);
                return result;
            }
        }
        return listShop(pSelectCond, idList);
    }

    @Override
    public PageResponseDTO<ShopInfoResDTO> listShop(final BaseInfo baseInfo, List<Long> idList) {
        ShopInfoCriteria criteria = new ShopInfoCriteria();
        criteria.setLimitClauseCount(baseInfo.getPageSize());
        criteria.setLimitClauseStart(baseInfo.getStartRowIndex());
        criteria.setOrderByClause("create_time desc");

        Criteria sql = criteria.createCriteria();
        
        if (!CollectionUtils.isEmpty(idList)) {
            sql.andIdIn(idList);
        }
        if (baseInfo instanceof ShopCollectReqDTO) {
        	
            if(!StringUtil.isBlank(((ShopCollectReqDTO)baseInfo).getShopName()))
            {
                sql.andShopNameLike("%"+((ShopCollectReqDTO)baseInfo).getShopName()+"%");
            }
        }
        
        if(baseInfo instanceof ShopSelectReqDTO)
        {
        	
        	if(!((ShopSelectReqDTO)baseInfo).isShowScoreShop()){
        		BaseSysCfgRespDTO cfg=SysCfgUtil.fetchSysCfg("GDS_SCORE_SHOP_DEFAULT");
        		if(cfg != null && cfg.getParaValue()!=null){
        			try {
        				Long id=Long.parseLong(cfg.getParaValue());
                        sql.andIdNotEqualTo(id);
					} catch (Exception e) {
						LogUtil.error(this.getClass().getName(), "convert socre shop value to Long failed please check!");
					}
        		}
	        }
        	
            if(!StringUtil.isBlank(((ShopSelectReqDTO)baseInfo).getShopName()))
            {
                sql.andShopNameLike("%"+((ShopSelectReqDTO)baseInfo).getShopName()+"%");
            }
            if(!StringUtil.isBlank(((ShopSelectReqDTO)baseInfo).getStatus()))
            {
                sql.andShopStatusEqualTo(((ShopSelectReqDTO)baseInfo).getStatus());
            }
            if(((ShopSelectReqDTO)baseInfo).getShopId()!=null&&((ShopSelectReqDTO)baseInfo).getShopId()!=0){
                sql.andIdEqualTo(((ShopSelectReqDTO)baseInfo).getShopId());
            }
            if(StringUtil.isNotBlank(((ShopSelectReqDTO)baseInfo).getHotShowSupported())){
                sql.andHotShowSupportedEqualTo(((ShopSelectReqDTO)baseInfo).getHotShowSupported());
                criteria.setOrderByClause(" hot_show_sort asc");
            }
            if (((ShopSelectReqDTO)baseInfo).getTableIndex() != 0) {
                DistributeRuleAssist.setTableIndex(((ShopSelectReqDTO)baseInfo).getTableIndex());
            }
        }
        
        PageResponseDTO<ShopInfoResDTO> pages = super.queryByPagination(baseInfo, criteria, true,
                new PaginationCallback<ShopInfo, ShopInfoResDTO>() {

                    @Override
                    public List<ShopInfo> queryDB(BaseCriteria basecriteria) {
                        return shopMapper.selectByExample((ShopInfoCriteria) basecriteria);
                    }

                    @Override
                    public long queryTotal(BaseCriteria basecriteria) {
                        return shopMapper.countByExample((ShopInfoCriteria) basecriteria);
                    }

                    @Override
                    public List<Comparator<ShopInfo>> defineComparators() {
                        List<Comparator<ShopInfo>> ls = new ArrayList<Comparator<ShopInfo>>();
                        ls.add(new Comparator<ShopInfo>() {

                            @Override
                            public int compare(ShopInfo o1, ShopInfo o2) {
                            	if (baseInfo instanceof ShopSelectReqDTO) {
                            		if ("1".equals(((ShopSelectReqDTO)baseInfo).getHotShowSupported())) {
                            			return o1.getHotShowSort().longValue() > o2.getHotShowSort().longValue()?1:-1;
                            		} else {
                            			return o1.getCreateTime().getTime() < o2.getCreateTime().getTime()?1:-1;
                            		}
                            	} else {
                            		return o1.getCreateTime().getTime() < o2.getCreateTime().getTime()?1:-1;
                            	}
                            }

                        });
                        return ls;
                    }

                    @Override
                    public ShopInfoResDTO warpReturnObject(ShopInfo info) {
                        ShopInfoResDTO sDto = new ShopInfoResDTO();
                        ObjectCopyUtil.copyObjValue(info, sDto, null, false);
                        CompanyInfoResDTO infoResDTO = companyCacheSV.find(sDto.getCompanyId());
                        if (infoResDTO != null) {
                            sDto.setCompanyName(infoResDTO.getCompanyName());
                        }

                        if (!StringUtil.isBlank(info.getLogoPath())) {
                            String url = ImageUtil.getImageUrl(info.getLogoPath());
                            if (!StringUtil.isBlank(url)) {
                                sDto.setLogoPathURL(url);
                            } 
                        } else {
                            sDto.setLogoPathURL(ImageUtil.getImageUrl(ImageUtil.getDefaultImageId()));
                        }
                        return sDto;
                    }

                });
        DistributeRuleAssist.clearTableIndex();
        return pages;
    }

}
