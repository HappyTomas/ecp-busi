package com.zengshi.ecp.staff.service.busi.shop.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dao.mapper.busi.ShopCollectMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.ShopInfoMapper;
import com.zengshi.ecp.staff.dao.model.ShopCollect;
import com.zengshi.ecp.staff.dao.model.ShopCollectCriteria;
import com.zengshi.ecp.staff.dao.model.ShopCollectCriteria.Criteria;
import com.zengshi.ecp.staff.dao.model.ShopInfo;
import com.zengshi.ecp.staff.dao.model.ShopInfoCriteria;
import com.zengshi.ecp.staff.dubbo.dto.ShopCollectReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.staff.service.busi.shop.interfaces.IShopCollectSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.sequence.Sequence;

public class ShopCollectSVImpl extends GeneralSQLSVImpl implements IShopCollectSV {
    
    @Resource
    private ShopCollectMapper shopCollectMapper;
    
    @Resource
    private ShopInfoMapper shopInfoMapper;
    
    @Resource
    private IShopInfoRSV shopInfoSV;
  
    @Resource(name = "seq_shop_collect_id")
    private Sequence sequence; 
    
    @Override
    public int insertShopCollect(ShopCollectReqDTO pShopClDTO) throws BusinessException {
        //生成ID序列号
        pShopClDTO.setId(sequence.nextValue());
        
        ShopCollect record = new ShopCollect();
        ObjectCopyUtil.copyObjValue(pShopClDTO, record, null, false);
        record.setCreateTime(DateUtil.getSysDate());
        record.setCreateStaff(pShopClDTO.getStaff().getId());
        record.setUpdateTime(DateUtil.getSysDate());
        record.setUpdateStaff(pShopClDTO.getStaff().getId());
        record.setStatus("1");
        try {
            shopCollectMapper.insertSelective(record);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        return 0;
    }

    @Override
    public int deleteShopCollect(Long pCollectID) throws BusinessException {
        try {
            shopCollectMapper.deleteByPrimaryKey(pCollectID);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        return 0;
    }

    @Override
    public List<ShopCollect> listShopCollect(ShopCollectReqDTO req)throws BusinessException {
        ShopCollectCriteria criteria = new ShopCollectCriteria();
        Criteria sql = criteria.createCriteria();
        if (req.getStaffId() != null && req.getStaffId() != 0L) {
        	sql.andStaffIdEqualTo(req.getStaffId());
        }
        if (StringUtil.isNotBlank(req.getShopId())) {
        	sql.andShopIdEqualTo(req.getShopId());
        }
        criteria.setOrderByClause(" CREATE_TIME DESC");
        List<ShopCollect> result = null;
        try {
            result = shopCollectMapper.selectByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }

      return result;
    }

    @Override
    public long countByShopId(ShopCollectReqDTO req) throws BusinessException {
        ShopCollect collect = new ShopCollect();
        collect.setShopId(req.getShopId());
        return shopCollectMapper.countByShopId(collect);
    }

    @Override
    public int deleteShopBySel(ShopCollectReqDTO req) throws BusinessException {
        ShopCollectCriteria cirteria = new ShopCollectCriteria();
        Criteria sql = cirteria.createCriteria();
        sql.andStaffIdEqualTo(req.getStaffId());
        sql.andShopIdEqualTo(req.getShopId());
        
        return shopCollectMapper.deleteByExample(cirteria);
    }

	@Override
	public PageResponseDTO<ShopInfoResDTO> listCollectShopForFav(ShopCollectReqDTO req) throws BusinessException {
		PageResponseDTO<ShopInfoResDTO> res = new PageResponseDTO<ShopInfoResDTO>();
		ShopCollectCriteria criteria = new ShopCollectCriteria();
        Criteria sql = criteria.createCriteria();
        if (req.getStaffId() != null && req.getStaffId() != 0L) {
        	sql.andStaffIdEqualTo(req.getStaffId());
        }
        //如果是用店铺名称查询，则是模糊查询，需要用in
        if (StringUtil.isNotBlank(req.getShopName())) {
        	ShopInfoCriteria shopInfoCriteria = new ShopInfoCriteria();
        	shopInfoCriteria.createCriteria().andShopNameLike("%" + req.getShopName() + "%");
        	shopInfoCriteria.setLimitClauseStart(0);
        	shopInfoCriteria.setLimitClauseCount(10000);
        	List<ShopInfo> shopList = shopInfoMapper.selectByExample(shopInfoCriteria);
        	if (CollectionUtils.isNotEmpty(shopList)) {
        		List<String> ids = new ArrayList<String>();
        		for (ShopInfo s : shopList) {
        			ids.add(String.valueOf(s.getId()));
        		}
        		sql.andShopIdIn(ids);
        	} else {
        		sql.andShopIdEqualTo("0");//查询不到取0
        	}
        } else {
        	if (StringUtil.isNotBlank(req.getShopId())) {
            	sql.andShopIdEqualTo(req.getShopId());
            }
        }
        criteria.setOrderByClause(" CREATE_TIME DESC");
        criteria.setLimitClauseCount(req.getPageSize());
        criteria.setLimitClauseStart(req.getStartRowIndex());
        
        res = super.queryByPagination(req, criteria, true, new PaginationCallback<ShopCollect, ShopInfoResDTO>() {

            @Override
            public List<ShopCollect> queryDB(BaseCriteria criteria) {
                return shopCollectMapper.selectByExample((ShopCollectCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return shopCollectMapper.countByExample((ShopCollectCriteria)criteria);
            }
            
            @Override
            public ShopInfoResDTO warpReturnObject(ShopCollect t) {
            	ShopInfoResDTO shopInfo = shopInfoSV.findShopInfoByShopID(Long.parseLong(t.getShopId()));
            	shopInfo.setCollTime(t.getCreateTime());//收藏时间
            	if (!StringUtil.isBlank(shopInfo.getLogoPath())) {
                    String url = ImageUtil.getImageUrl(shopInfo.getLogoPath());
                    if (!StringUtil.isBlank(url)) {
                    	shopInfo.setLogoPathURL(url);
                    } 
                } else {
                	shopInfo.setLogoPathURL(ImageUtil.getImageUrl(ImageUtil.getDefaultImageId()));
                }
                return shopInfo;
            }
        });
		return res;
	}
}

