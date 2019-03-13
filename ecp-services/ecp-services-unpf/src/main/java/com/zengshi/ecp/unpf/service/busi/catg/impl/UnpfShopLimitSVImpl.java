package com.zengshi.ecp.unpf.service.busi.catg.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.StaffLocaleUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.unpf.dao.mapper.busi.UnpfGdsCatgLimitMapper;
import com.zengshi.ecp.unpf.dao.mapper.busi.UnpfGdsLimitMapper;
import com.zengshi.ecp.unpf.dao.model.UnpfGdsCatgLimit;
import com.zengshi.ecp.unpf.dao.model.UnpfGdsCatgLimitCriteria;
import com.zengshi.ecp.unpf.dao.model.UnpfGdsLimit;
import com.zengshi.ecp.unpf.dao.model.UnpfGdsLimitCriteria;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfGdsCatgLimitReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfGdsCatgLimitRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfGdsLimitReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfGdsLimitRespDTO;
import com.zengshi.ecp.unpf.service.busi.catg.interfaces.IUnpfShopLimitSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

@Service(value="unpfShopLimitSV")
public class UnpfShopLimitSVImpl extends GeneralSQLSVImpl implements IUnpfShopLimitSV{
    
    private static final String MODULE  = UnpfShopLimitSVImpl.class.getName();
    @Resource
    private IGdsCategoryRSV gdsCategoryRSV;
    @Resource
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;
    @Resource
    private IGdsInfoQueryRSV gdsInfoQueryRSV;
    
    @Resource
    private UnpfGdsCatgLimitMapper unpfGdsCatgLimitMapper;
    @Resource
    private UnpfGdsLimitMapper unpfGdsLimitMapper;
    
    @Resource(name = "seq_unpf_gds_catg_limit_id")
    private PaasSequence seq_unpf_gds_catg_limit_id;
    
    @Resource(name = "seq_unpf_gds_limit_id")
    private PaasSequence seq_unpf_gds_limit_id;
    
    @Override
    public void insertGdsLimit(UnpfGdsLimitReqDTO gdsLimitReqDTO) throws BusinessException {
        if (gdsLimitReqDTO == null) {
            return;
        }
        UnpfGdsLimit record = new UnpfGdsLimit();
        
        ObjectCopyUtil.copyObjValue(gdsLimitReqDTO, record, null, false);
        record.setId(seq_unpf_gds_limit_id.nextValue());
        record.setStatus("1");
        record.setCreateStaff(StaffLocaleUtil.getStaff().getId());
        record.setCreateTime(DateUtil.getSysDate());
        record.setUpdateStaff(StaffLocaleUtil.getStaff().getId());
        record.setUpdateTime(DateUtil.getSysDate());
        
        try {
            unpfGdsLimitMapper.insertSelective(record);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, e.getMessage());
            throw e;
        }
    }

    @Override
    public void insertCatgLimit(UnpfGdsCatgLimitReqDTO catgLimitReqDTO) throws BusinessException {
        if (catgLimitReqDTO == null) {
            return;
        }
        UnpfGdsCatgLimit record = new UnpfGdsCatgLimit();
        
        ObjectCopyUtil.copyObjValue(catgLimitReqDTO, record, null, false);
        record.setId(seq_unpf_gds_catg_limit_id.nextValue());
        record.setStatus("1");
        record.setCreateStaff(StaffLocaleUtil.getStaff().getId());
        record.setCreateTime(DateUtil.getSysDate());
        record.setUpdateStaff(StaffLocaleUtil.getStaff().getId());
        record.setUpdateTime(DateUtil.getSysDate());
        
        try {
            unpfGdsCatgLimitMapper.insertSelective(record);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteGdsLimitByKey(Long key) throws BusinessException {
        if ( key == null) {
            return;
        }
        try {
            unpfGdsLimitMapper.deleteByPrimaryKey(key);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteCatgLimitByKey(Long key) throws BusinessException {
        if ( key == null) {
            return;
        }
        try {
            unpfGdsCatgLimitMapper.deleteByPrimaryKey(key);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, e.getMessage());
            throw e;
        }
    }

    @Override
    public PageResponseDTO<UnpfGdsLimitRespDTO> queryGdsLimitPage(UnpfGdsLimitReqDTO gdsLimitReqDTO)
            throws BusinessException {
        
        UnpfGdsLimitCriteria criteria = new UnpfGdsLimitCriteria();
        criteria.setLimitClauseCount(gdsLimitReqDTO.getPageSize());
        criteria.setLimitClauseStart(gdsLimitReqDTO.getStartRowIndex());
        criteria.setOrderByClause("ID DESC");
        
        com.zengshi.ecp.unpf.dao.model.UnpfGdsLimitCriteria.Criteria condition = criteria.createCriteria();
        
        if (StringUtil.isNotBlank(gdsLimitReqDTO.getLimitType())) {
            condition.andLimitTypeEqualTo(gdsLimitReqDTO.getLimitType());
        }
        if (StringUtil.isNotEmpty(gdsLimitReqDTO.getShopAuthId())) {
            condition.andShopAuthIdEqualTo(gdsLimitReqDTO.getShopAuthId());
        }
        if (StringUtil.isNotEmpty(gdsLimitReqDTO.getShopId())) {
            condition.andShopIdEqualTo(gdsLimitReqDTO.getShopId());
        }
        if (StringUtil.isNotBlank(gdsLimitReqDTO.getPlatType())) {
            condition.andPlatTypeEqualTo(gdsLimitReqDTO.getPlatType());
        }
        if (StringUtil.isNotEmpty(gdsLimitReqDTO.getGdsId())) {
            condition.andGdsIdEqualTo(gdsLimitReqDTO.getGdsId());   
        }
        if (StringUtil.isNotEmpty(gdsLimitReqDTO.getSkuId())) {
            condition.andSkuIdEqualTo(gdsLimitReqDTO.getSkuId());
        }
        if (StringUtil.isNotBlank(gdsLimitReqDTO.getStatus())) {
            condition.andStatusEqualTo(gdsLimitReqDTO.getStatus());
        }
        
        
        return super.queryByPagination(gdsLimitReqDTO, criteria, true, new PaginationCallback<UnpfGdsLimit, UnpfGdsLimitRespDTO>() {

            @Override
            public List<UnpfGdsLimit> queryDB(BaseCriteria criteria) {
                return unpfGdsLimitMapper.selectByExample((UnpfGdsLimitCriteria) criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return unpfGdsLimitMapper.countByExample((UnpfGdsLimitCriteria) criteria);
            }

            @Override
            public UnpfGdsLimitRespDTO warpReturnObject(UnpfGdsLimit row) {
                
                UnpfGdsLimitRespDTO record = new UnpfGdsLimitRespDTO();
                ObjectCopyUtil.copyObjValue(row, record, null, false);
                if ("1".equals(row.getLimitType())) {
                    addGdsSkuInfo(row, record);
                }else if ("2".equals(row.getLimitType())) {
                    addGdsInfo(row, record);
                }
                return record;
            }
        });
    }
    private void addGdsSkuInfo(UnpfGdsLimit row, UnpfGdsLimitRespDTO record){
        GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
        gdsSkuInfoReqDTO.setId(row.getSkuId());
        
        GdsSkuInfoRespDTO skuInfo = gdsSkuInfoQueryRSV.queryGdsSkuInfoResp(gdsSkuInfoReqDTO);
        if (skuInfo != null) {
            record.setGdsName(skuInfo.getGdsName());
            record.setGdsCatgCodeName(skuInfo.getMainCatgName());
            record.setGdsStatus(skuInfo.getGdsStatusName());
        }
    }
    private void addGdsInfo(UnpfGdsLimit row, UnpfGdsLimitRespDTO record){
        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
        gdsInfoReqDTO.setId(row.getGdsId());
        
        GdsInfoRespDTO gdsInfo = gdsInfoQueryRSV.queryGdsInfo(gdsInfoReqDTO);
        if (gdsInfo != null) {
            record.setGdsName(gdsInfo.getGdsName());
            record.setGdsCatgCodeName(gdsInfo.getMainCatgName());
            record.setGdsStatus(gdsInfo.getGdsStatusName());
        }
    }
    @Override
    public PageResponseDTO<UnpfGdsCatgLimitRespDTO> queryCatgLimitPage(
            UnpfGdsCatgLimitReqDTO catgLimitReqDTO) throws BusinessException {
        
        UnpfGdsCatgLimitCriteria criteria = new UnpfGdsCatgLimitCriteria();
        criteria.setLimitClauseCount(catgLimitReqDTO.getPageSize());
        criteria.setLimitClauseStart(catgLimitReqDTO.getStartRowIndex());
        criteria.setOrderByClause("ID DESC");
        
        com.zengshi.ecp.unpf.dao.model.UnpfGdsCatgLimitCriteria.Criteria condition = criteria.createCriteria();
        
        if (StringUtil.isNotEmpty(catgLimitReqDTO.getShopAuthId())) {
            condition.andShopAuthIdEqualTo(catgLimitReqDTO.getShopAuthId());
        }
        if (StringUtil.isNotEmpty(catgLimitReqDTO.getShopId())) {
            condition.andShopIdEqualTo(catgLimitReqDTO.getShopId());
        }
        if (StringUtil.isNotBlank(catgLimitReqDTO.getPlatType())) {
            condition.andPlatTypeEqualTo(catgLimitReqDTO.getPlatType());
        }
        if (StringUtil.isNotBlank(catgLimitReqDTO.getCatgCode())) {
            condition.andCatgCodeEqualTo(catgLimitReqDTO.getCatgCode());
        }
        if (StringUtil.isNotBlank(catgLimitReqDTO.getStatus())) {
            condition.andStatusEqualTo(catgLimitReqDTO.getStatus());
        }
        
        return super.queryByPagination(catgLimitReqDTO, criteria, true, new PaginationCallback<UnpfGdsCatgLimit, UnpfGdsCatgLimitRespDTO>() {

            @Override
            public List<UnpfGdsCatgLimit> queryDB(BaseCriteria criteria) {
                return unpfGdsCatgLimitMapper.selectByExample((UnpfGdsCatgLimitCriteria) criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return unpfGdsCatgLimitMapper.countByExample((UnpfGdsCatgLimitCriteria) criteria);
            }

            @Override
            public UnpfGdsCatgLimitRespDTO warpReturnObject(UnpfGdsCatgLimit row) {
                
                UnpfGdsCatgLimitRespDTO record = new UnpfGdsCatgLimitRespDTO();
                ObjectCopyUtil.copyObjValue(row, record, null, false);
                
                addCatgCodeName(row, record);
                
                return record;
            }
        });
    }
    private void addCatgCodeName(UnpfGdsCatgLimit row, UnpfGdsCatgLimitRespDTO record){
        GdsCategoryReqDTO gdsCateReqDTO = new GdsCategoryReqDTO();
        gdsCateReqDTO.setCatgCode(row.getCatgCode());
        GdsCategoryRespDTO gdsCateInfo = gdsCategoryRSV.queryGdsCategoryByPK(gdsCateReqDTO);
        if (gdsCateInfo != null) {
            record.setCatgCodeName(gdsCateInfo.getCatgName());
        }
    }

}

