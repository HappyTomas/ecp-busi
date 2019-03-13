package com.zengshi.ecp.goods.service.busi.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsInfoMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsShiptempMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsShiptempPriceMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsShiptempShopIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsShop2ShiptempMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.manual.GdsShiptempShopIdxExtraMapper;
import com.zengshi.ecp.goods.dao.mapper.common.GdsCategoryMapper;
import com.zengshi.ecp.goods.dao.model.GdsCategory;
import com.zengshi.ecp.goods.dao.model.GdsCategoryCriteria;
import com.zengshi.ecp.goods.dao.model.GdsInfo;
import com.zengshi.ecp.goods.dao.model.GdsInfoCriteria;
import com.zengshi.ecp.goods.dao.model.GdsShiptemp;
import com.zengshi.ecp.goods.dao.model.GdsShiptempCriteria;
import com.zengshi.ecp.goods.dao.model.GdsShiptempPrice;
import com.zengshi.ecp.goods.dao.model.GdsShiptempPriceCriteria;
import com.zengshi.ecp.goods.dao.model.GdsShiptempShopIdx;
import com.zengshi.ecp.goods.dao.model.GdsShiptempShopIdxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsShop2Shiptemp;
import com.zengshi.ecp.goods.dao.model.GdsShop2ShiptempCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.AreaCodeDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsInfoShipmentReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShipAreaCodeRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShipmentCalInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShiptempPriceReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShiptempPriceRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShiptempReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShiptempRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShiptempShopIdxRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShop2ShipmentReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsShipTempSV;
import com.zengshi.ecp.goods.service.busi.interfaces.external.IGdsInfoExternalSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySV;
import com.zengshi.ecp.prom.dubbo.dto.PromPostDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromPostRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromShipDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromShipRespDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromShipRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseAreaAdminUtil;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;

public class GdsShipTempSVImpl extends AbstractSVImpl implements IGdsShipTempSV {
    @Resource
    private GdsShiptempMapper gdsShiptempMapper;

    @Resource
    private GdsShiptempShopIdxMapper gdsShiptempShopIdxMapper;
    
    @Resource
    private GdsShiptempShopIdxExtraMapper gdsShiptempShopIdxExtraMapper;

    @Resource
    private GdsShiptempPriceMapper gdsShiptempPriceMapper;

    @Resource
    private GdsCategoryMapper gdsCategoryMapper;
    
    @Resource
    private GdsInfoMapper gdsInfoMapper;

    @Resource
    private IPromShipRSV promShipRSV;

    @Resource(name = "seq_gds_shiptemp")
    private Sequence seqGdsShipTemp;

    @Resource(name = "seq_gds_shiptemp_price")
    private Sequence seqGdsShipTempPrice;

    @Resource(name = "gdsInfoQuerySV")
    private IGdsInfoQuerySV gdsInfoQuerySV;

    @Resource(name = "gdsCategorySV")
    private IGdsCategorySV gdsCategorySV;

    @Resource
    private GdsShop2ShiptempMapper gdsShop2ShiptempMapper;

    @Resource
    private IGdsInfoExternalSV gdsInfoExternalSV;

    /**
     * 
     * TODO 简单描述该方法的实现功能（新增保存运费模板）.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsShipTempSV#saveGdsShipTemp(com.zengshi.ecp.goods.dubbo.dto.GdsShiptempReqDTO)
     */
    @Override
    public long saveGdsShipTemp(GdsShiptempReqDTO reqDTO) throws BusinessException {
        //判断运费模板是否是分类模板，如果是分类模板需要判断该分类在当前店铺下是否已经存在运费模板
    	if(!StringUtil.isBlank(reqDTO.getCatgCode())){
            GdsShiptempShopIdxCriteria example = new GdsShiptempShopIdxCriteria();
            GdsShiptempShopIdxCriteria.Criteria criteria = example.createCriteria();
            criteria.andCatgCodeEqualTo(reqDTO.getCatgCode());
            criteria.andShopIdEqualTo(reqDTO.getShopId());
            criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
            Long count = gdsShiptempShopIdxMapper.countByExample(example);
           if(count > 0){
               throw new BusinessException(GdsErrorConstants.GdsShipMent.ERROR_GOODS_SHIP_MENT_240008);
           }
        }
        // 新增运费模板表记录
        GdsShiptemp gdsShipTemp = new GdsShiptemp();
        long id = seqGdsShipTemp.nextValue();
        gdsShipTemp.setId(id);
        // 获取前店的参数
        getInsertGSTCondition(gdsShipTemp, reqDTO);
        gdsShipTemp.setCreateStaff(reqDTO.getStaff().getId());
        gdsShipTemp.setUpdateStaff(reqDTO.getStaff().getId());
        gdsShipTemp.setCreateTime(new Timestamp(System.currentTimeMillis()));
        gdsShiptempMapper.insert(gdsShipTemp);
        // 新增运费计价表记录    
            try {
                insertIntoGSTP(reqDTO, id);
            } catch (Exception e) {
               throw new BusinessException(GdsErrorConstants.GdsShipMent.ERROR_GOODS_SHIP_MENT_240007);
            }      
        // 新增运费模板店铺索引维度表记录
        GdsShiptempShopIdx gdsShiptempShopIdx = new GdsShiptempShopIdx();
        gdsShiptempShopIdx.setTempId(id);
        // 获取前店的参数
        getInsertGSSICondiction(gdsShiptempShopIdx, reqDTO);
        gdsShiptempShopIdx.setCreateStaff(reqDTO.getStaff().getId());
        gdsShiptempShopIdx.setUpdateStaff(reqDTO.getStaff().getId());
        gdsShiptempShopIdx.setCreateTime(new Timestamp(System.currentTimeMillis()));
        gdsShiptempShopIdxMapper.insert(gdsShiptempShopIdx);
        return id;
    }

    /**
     * 
     * TODO 删除运费模板（逻辑删除）.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsShipTempSV#delteGdsShipTemp(com.zengshi.ecp.goods.dubbo.dto.GdsShiptempReqDTO)
     */
    @Override
    public long delteGdsShipTemp(GdsShiptempReqDTO reqDTO) throws BusinessException {
        Long shopId = reqDTO.getShopId();
        Long id = reqDTO.getId();
        // 先判断是否是店铺的默认运费模板，
        GdsShop2ShiptempCriteria gdsShop2ShiptempCriteria = new GdsShop2ShiptempCriteria();
        GdsShop2ShiptempCriteria.Criteria shop2ShiptempCriteria = gdsShop2ShiptempCriteria
                .createCriteria();
        shop2ShiptempCriteria.andShipTemplateIdEqualTo(id);
        shop2ShiptempCriteria.andShopIdEqualTo(shopId);
        shop2ShiptempCriteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        long countShop = gdsShop2ShiptempMapper
                .countByExample(gdsShop2ShiptempCriteria);
        if (countShop > 0) {
            return -1;
        }
        GdsInfoCriteria example = new GdsInfoCriteria();
        GdsInfoCriteria.Criteria criteria = example.createCriteria();
        criteria.andShopIdEqualTo(shopId);
        criteria.andShipTemplateIdEqualTo(id);
        criteria.andGdsStatusNotEqualTo(GdsConstants.GdsInfo.GDS_STATUS_DELETE);
        Long count = gdsInfoMapper.countByExample(example);
        if (count > 0) {
            return -2;
        }
        // 失效运费模板店铺索引维度表记录
        GdsShiptempShopIdx gdsShiptempShopIdx = new GdsShiptempShopIdx();
        gdsShiptempShopIdx.setStatus(GdsConstants.Commons.STATUS_INVALID);
        GdsShiptempShopIdxCriteria gdsShiptempShopIdxCriteria = new GdsShiptempShopIdxCriteria();
        GdsShiptempShopIdxCriteria.Criteria tempCrit = gdsShiptempShopIdxCriteria.createCriteria();
        tempCrit.andShopIdEqualTo(shopId);
        tempCrit.andTempIdEqualTo(id);
        gdsShiptempShopIdxMapper.updateByExampleSelective(gdsShiptempShopIdx,
                gdsShiptempShopIdxCriteria);
        // 失效运费模板
        GdsShiptemp gdsShipTemp = new GdsShiptemp();
        gdsShipTemp.setStatus(GdsConstants.Commons.STATUS_INVALID);
        GdsShiptempCriteria gdsShiptempCriteria = new GdsShiptempCriteria();
        GdsShiptempCriteria.Criteria shipTempCrit = gdsShiptempCriteria.createCriteria();
        shipTempCrit.andShopIdEqualTo(shopId);
        shipTempCrit.andIdEqualTo(id);
        gdsShiptempMapper.updateByExampleSelective(gdsShipTemp, gdsShiptempCriteria);
        // 失效运费计价表
        GdsShiptempPrice gdsShiptempPrice = new GdsShiptempPrice();
        gdsShiptempPrice.setStatus(GdsConstants.Commons.STATUS_INVALID);
        GdsShiptempPriceCriteria gdsShiptempPriceCriteria = new GdsShiptempPriceCriteria();
        GdsShiptempPriceCriteria.Criteria shipPriceCrit = gdsShiptempPriceCriteria.createCriteria();
        shipPriceCrit.andShipTemplateIdEqualTo(id);
        gdsShiptempPriceMapper.updateByExampleSelective(gdsShiptempPrice, gdsShiptempPriceCriteria);
        return 1;
    }

    /**
     * 
     * TODO 查询店铺的运费模板列表.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsShipTempSV#queryGdsShipTemp(com.zengshi.ecp.goods.dubbo.dto.GdsShiptempReqDTO)
     */
    @Override
    public PageResponseDTO<GdsShiptempRespDTO> queryGdsShipTemp(GdsShiptempReqDTO reqDTO)
            throws BusinessException {
        final GdsShiptempShopIdxCriteria gdsShiptempShopIdxCriteria = new GdsShiptempShopIdxCriteria();
        PageResponseDTO<GdsShiptempRespDTO> resultPage = new PageResponseDTO<GdsShiptempRespDTO>();
        // 获取查询条件
        getReuestCondition(reqDTO, gdsShiptempShopIdxCriteria);
        PageResponseDTO<GdsShiptempShopIdxRespDTO> pageInfo = super.queryByPagination(reqDTO,
                gdsShiptempShopIdxCriteria, true,
                new PaginationCallback<GdsShiptempShopIdx, GdsShiptempShopIdxRespDTO>() {
                    // 查询记录集合
                    @Override
                    public List<GdsShiptempShopIdx> queryDB(BaseCriteria criteria) {
                    	return gdsShiptempShopIdxExtraMapper 
//                        return gdsShiptempShopIdxMapper 
                                .selectByExample((GdsShiptempShopIdxCriteria) criteria);
                    }

                    // 查询总数
                    @Override
                    public long queryTotal(BaseCriteria criteria) {
                        return gdsShiptempShopIdxExtraMapper.countByExample(
                                (GdsShiptempShopIdxCriteria) criteria).longValue();
                    }

                    @Override
                    public GdsShiptempShopIdxRespDTO warpReturnObject(GdsShiptempShopIdx t) {
                        GdsShiptempShopIdxRespDTO pageDTO = new GdsShiptempShopIdxRespDTO();
                        // 根据索引表获取记录
                        ObjectCopyUtil.copyObjValue(t, pageDTO, "", false);
                        pageDTO.setId(t.getTempId());
                        return pageDTO;
                    }
                });
        List<GdsShiptempRespDTO> shipList = new ArrayList<GdsShiptempRespDTO>();
        if (StringUtil.isNotEmpty(pageInfo) && StringUtil.isNotEmpty(pageInfo.getResult())) {
            for (GdsShiptempShopIdxRespDTO dto : pageInfo.getResult()) {
                GdsShiptempRespDTO resultDto = new GdsShiptempRespDTO();
                ObjectCopyUtil.copyObjValue(dto, resultDto, "", false);
                // 判断是否是店铺的默认运费模板，
                GdsShop2ShiptempCriteria gdsShop2ShiptempCriteria = new GdsShop2ShiptempCriteria();
                GdsShop2ShiptempCriteria.Criteria shop2ShiptempCriteria = gdsShop2ShiptempCriteria
                        .createCriteria();
                shop2ShiptempCriteria.andShipTemplateIdEqualTo(resultDto.getId());
                shop2ShiptempCriteria.andShopIdEqualTo(resultDto.getShopId());
                shop2ShiptempCriteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
                List<GdsShop2Shiptemp> list = gdsShop2ShiptempMapper
                        .selectByExample(gdsShop2ShiptempCriteria);
                if (CollectionUtils.isNotEmpty(list)) {
                    resultDto.setIfDefaultTemplate(GdsConstants.Commons.STATUS_VALID);
                }else{
                    resultDto.setIfDefaultTemplate(GdsConstants.Commons.STATUS_INVALID);
                }
                shipList.add(resultDto);
            }
        }
        resultPage.setCount(pageInfo.getCount());
        resultPage.setPageCount(pageInfo.getPageCount());
        resultPage.setPageNo(pageInfo.getPageNo());
        resultPage.setPageSize(pageInfo.getPageSize());
        resultPage.setResult(shipList);
        return resultPage;
    }

    /**
     * 
     * TODO 获取单条运费模板记录的信息。用于前店的运费模板的编辑.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsShipTempSV#getSingleGdsShipTemp(com.zengshi.ecp.goods.dubbo.dto.GdsShiptempReqDTO)
     */
    @Override
    public GdsShiptempRespDTO getSingleGdsShipTemp(GdsShiptempReqDTO reqDTO)
            throws BusinessException {
        GdsShiptempRespDTO gdsShiptempRespDTO = new GdsShiptempRespDTO();
        GdsShiptempCriteria gdsShipTemCriteria = new GdsShiptempCriteria();
        GdsShiptempCriteria.Criteria criteria = gdsShipTemCriteria.createCriteria();
        Long id = reqDTO.getId();
        if (id >= 1) {
            criteria.andIdEqualTo(id);
        }
        Long shopId = reqDTO.getShopId();
        if (shopId != null) {
            criteria.andShopIdEqualTo(shopId);
        }
        criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        // 查询运费模板表
        List<GdsShiptemp> list = gdsShiptempMapper.selectByExample(gdsShipTemCriteria);
        if (CollectionUtils.isNotEmpty(list)) {
            GdsShiptemp gdsShiptemp = list.get(0);
            
            ObjectCopyUtil.copyObjValue(gdsShiptemp, gdsShiptempRespDTO, null, false);
            
        }
        // 查询运费计价表
        GdsShiptempPriceCriteria gdsShiptempPriceCriteria = new GdsShiptempPriceCriteria();
        GdsShiptempPriceCriteria.Criteria pCriteria = gdsShiptempPriceCriteria.createCriteria();
        if (id >= 1) {
            pCriteria.andShipTemplateIdEqualTo(id);
        }
        pCriteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        List<GdsShiptempPriceRespDTO> GdsShiptempPriceRespDTOList = new ArrayList<GdsShiptempPriceRespDTO>();
        GdsShiptempPriceRespDTO gdsShiptempPriceRespDTO = null;
        GdsShiptempPriceRespDTO defaultRespDTO = new GdsShiptempPriceRespDTO();
        List<GdsShiptempPrice> listPrice = gdsShiptempPriceMapper
                .selectByExample(gdsShiptempPriceCriteria);
        if (StringUtil.isNotEmpty(listPrice)) {
            for (GdsShiptempPrice gdsShiptempPrice : listPrice) {
                if (StringUtil.isEmpty(gdsShiptempPrice.getProvinceCode())) {
                    ObjectCopyUtil.copyObjValue(gdsShiptempPrice, defaultRespDTO, null, false);
                } else {
                    gdsShiptempPriceRespDTO = new GdsShiptempPriceRespDTO();
                    ObjectCopyUtil.copyObjValue(gdsShiptempPrice, gdsShiptempPriceRespDTO, null,
                            false);
                    GdsShiptempPriceRespDTOList.add(gdsShiptempPriceRespDTO);
                }

            }
        }
        gdsShiptempRespDTO.setGdsShipTempPriceRespDTO(GdsShiptempPriceRespDTOList);
        gdsShiptempRespDTO.setDefaultPriceRespDTO(defaultRespDTO);
        List<GdsShiptempPriceRespDTO> GdsShiptempPriceRespDTOListResult = new ArrayList<GdsShiptempPriceRespDTO>();
        parseResult(gdsShiptempRespDTO, GdsShiptempPriceRespDTOListResult);
        gdsShiptempRespDTO.setGdsShipTempPriceRespDTO(GdsShiptempPriceRespDTOListResult);
        // 获取模板类型名称
        if (StringUtil.isNotEmpty(gdsShiptempRespDTO.getCatgCode())) {
            GdsCategoryCriteria gdsCategoryCriteria = new GdsCategoryCriteria();
            GdsCategoryCriteria.Criteria catecriteria = gdsCategoryCriteria.createCriteria();
            catecriteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
            catecriteria.andCatgCodeEqualTo(gdsShiptempRespDTO.getCatgCode());
            List<GdsCategory> cateList = gdsCategoryMapper.selectByExample(gdsCategoryCriteria);
            if (StringUtil.isNotEmpty(cateList)) {
                GdsCategory gdsCategory = cateList.get(0);
                gdsShiptempRespDTO.setCatgName(gdsCategory.getCatgName());
            }
        }
        
        // 判断是否是店铺的默认运费模板，
        GdsShop2ShiptempCriteria gdsShop2ShiptempCriteria = new GdsShop2ShiptempCriteria();
        GdsShop2ShiptempCriteria.Criteria shop2ShiptempCriteria = gdsShop2ShiptempCriteria
                .createCriteria();
        shop2ShiptempCriteria.andShipTemplateIdEqualTo(gdsShiptempRespDTO.getId());
        shop2ShiptempCriteria.andShopIdEqualTo(gdsShiptempRespDTO.getShopId());
        shop2ShiptempCriteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        List<GdsShop2Shiptemp> relationList = gdsShop2ShiptempMapper
                .selectByExample(gdsShop2ShiptempCriteria);
        if (relationList != null && relationList.size() > 0) {
        	gdsShiptempRespDTO.setIfDefaultTemplate(GdsConstants.Commons.STATUS_VALID);
        }else{
        	gdsShiptempRespDTO.setIfDefaultTemplate(GdsConstants.Commons.STATUS_INVALID);
        }
        return gdsShiptempRespDTO;
    }
    
    
  /** 
    * TODO 获取运费模板名称
    * 
    * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsShipTempSV#getSingleGdsShipTemp(com.zengshi.ecp.goods.dubbo.dto.GdsShiptempReqDTO)
    */
   @Override
   public String getGdsShipTempName(GdsShiptempReqDTO reqDTO)
           throws BusinessException {
       GdsShiptempCriteria gdsShipTemCriteria = new GdsShiptempCriteria();
       GdsShiptempCriteria.Criteria criteria = gdsShipTemCriteria.createCriteria();
       Long id = reqDTO.getId();
       if (id!=null && id.longValue() >= 1) {
           criteria.andIdEqualTo(id);
       }
       Long shopId = reqDTO.getShopId();
       if (shopId != null) {
           criteria.andShopIdEqualTo(shopId);
       }
       criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
       // 查询运费模板表
       List<GdsShiptemp> list = gdsShiptempMapper.selectByExample(gdsShipTemCriteria);
       if (CollectionUtils.isNotEmpty(list)) {
           GdsShiptemp gdsShiptemp = list.get(0);
           return gdsShiptemp.getShipTemplateName();
       }
       return "";
    }

    /**
     * 
     * TODO 简单描述该方法的实现功能（编辑保存运费模板）.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsShipTempSV#editGdsShipTemp(com.zengshi.ecp.goods.dubbo.dto.GdsShiptempReqDTO)
     */
    @Override
    public void editGdsShipTemp(GdsShiptempReqDTO reqDTO) throws BusinessException {
        //判断运费模板是否是分类模板，如果是分类模板需要判断该分类在当前店铺下是否已经存在运费模板
        if(!StringUtil.isBlank(reqDTO.getCatgCode())){
            GdsShiptempShopIdxCriteria example = new GdsShiptempShopIdxCriteria();
            GdsShiptempShopIdxCriteria.Criteria criteria = example.createCriteria();
            criteria.andTempIdNotEqualTo(reqDTO.getId());
            criteria.andCatgCodeEqualTo(reqDTO.getCatgCode());
            criteria.andShopIdEqualTo(reqDTO.getShopId());
            criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
            Long count = gdsShiptempShopIdxMapper.countByExample(example);
           if(count > 0){
               throw new BusinessException(GdsErrorConstants.GdsShipMent.ERROR_GOODS_SHIP_MENT_240008);
           }
        }
        Long updateStaff = reqDTO.getUpdateStaff();
        Timestamp updateTime = new Timestamp(System.currentTimeMillis());
        // 更新运费模板主表
        GdsShiptemp gdsShiptemp = new GdsShiptemp();
        // 获取前店的参数
        getInsertGSTCondition(gdsShiptemp, reqDTO);
        gdsShiptemp.setUpdateStaff(updateStaff);
        gdsShiptemp.setUpdateTime(updateTime);
        GdsShiptempCriteria gdsShiptempCriteria = new GdsShiptempCriteria();
        GdsShiptempCriteria.Criteria shipCriteria = gdsShiptempCriteria.createCriteria();
        shipCriteria.andIdEqualTo(reqDTO.getId());
        shipCriteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        gdsShiptempMapper.updateByExampleSelective(gdsShiptemp, gdsShiptempCriteria);
        // 更新运费计价表
        editInitIntoGSTP(reqDTO);
        // 更新运费模板店铺维度索引表
        GdsShiptempShopIdx gdsShiptempShopIdx = new GdsShiptempShopIdx();
        GdsShiptempShopIdxCriteria gdsShiptempShopIdxCriteria = new GdsShiptempShopIdxCriteria();
        GdsShiptempShopIdxCriteria.Criteria idxCriteria = gdsShiptempShopIdxCriteria
                .createCriteria();
        // 获取更新的内容
        if (StringUtil.isNotEmpty(reqDTO.getShopId())) {
            idxCriteria.andShopIdEqualTo(reqDTO.getShopId());
        }
        if (StringUtil.isNotEmpty(reqDTO.getId())) {
            idxCriteria.andTempIdEqualTo(reqDTO.getId());
        }
        getInsertGSSICondiction(gdsShiptempShopIdx, reqDTO);
        gdsShiptempShopIdx.setUpdateStaff(updateStaff);
        gdsShiptempShopIdx.setUpdateTime(updateTime);
        gdsShiptempShopIdxMapper.updateByExampleSelective(gdsShiptempShopIdx,
                gdsShiptempShopIdxCriteria);
    }

    /**
     * 
     * getInsertGSTCondition:(获取前店参数用于入库到 运费模板表). <br/>
     * 
     * @author gxq
     * @param gdsShipTemp
     * @param reqDTO
     * @return
     * @since JDK 1.6
     */
    private GdsShiptemp getInsertGSTCondition(GdsShiptemp gdsShipTemp, GdsShiptempReqDTO reqDTO) {
        gdsShipTemp.setShipTemplateName(reqDTO.getShipTemplateName());
        gdsShipTemp.setShipTemplateType(reqDTO.getShipTemplateType());
        gdsShipTemp.setShopId(reqDTO.getShopId());
        gdsShipTemp.setIfFree(reqDTO.getIfFree());
        gdsShipTemp.setStatus(GdsConstants.Commons.STATUS_VALID);
        gdsShipTemp.setShipInstruction(reqDTO.getShipInstruction());
        gdsShipTemp.setCatgCode(reqDTO.getCatgCode());
        return gdsShipTemp;
    }

    /**
     * 
     * insertIntoGSTP:(lbr/>
     * 
     * @author gxq
     * @param reqDTO
     * @param id
     * @since JDK 1.6
     */
    private void insertIntoGSTP(GdsShiptempReqDTO reqDTO, long id)throws Exception {
        List<GdsShiptempPriceReqDTO> GdsShiptempPriceReqDTOList = reqDTO
                .getGdsShiptempPriceReqDTOList();
        if (GdsShiptempPriceReqDTOList != null) {
            for (GdsShiptempPriceReqDTO GdsShiptempPriceReqDTO : GdsShiptempPriceReqDTOList) {
                List<AreaCodeDTO> areaCodeList = GdsShiptempPriceReqDTO.getAreaCodeList();
                GdsShiptempPrice gdsShipTempPrice = new GdsShiptempPrice();
                // 获取参数
                getInsertGSTPCondition(gdsShipTempPrice, GdsShiptempPriceReqDTO);
                gdsShipTempPrice.setShipTemplateId(id);
                gdsShipTempPrice.setCreateStaff(reqDTO.getStaff().getId());
                gdsShipTempPrice.setUpdateStaff(reqDTO.getStaff().getId());
                gdsShipTempPrice.setCountryCode(GdsShiptempPriceReqDTO.getCountryCode());
                if (GdsConstants.GdsShiptemp.DEFAULT_AREA_CODE.equals(GdsShiptempPriceReqDTO
                        .getProvinceCode())) {
                    long pricingListId = seqGdsShipTempPrice.nextValue();
                    gdsShipTempPrice.setPricingListId(pricingListId);
                    Long areaLevel = GdsShiptempPriceReqDTO.getAreaLevel();
                    if (areaLevel != null) {
                        gdsShipTempPrice.setAreaLevel(areaLevel);
                    }
                    try {
                        gdsShiptempPriceMapper.insert(gdsShipTempPrice);
                    } catch (Exception e) {
                       
                        LogUtil.error(MODULE, "插入失败", e);
                        throw e;
                    }
                } else {
                    for (AreaCodeDTO areaCodeDTO : areaCodeList) {
                        long pricingListId = seqGdsShipTempPrice.nextValue();
                        gdsShipTempPrice.setPricingListId(pricingListId);
                        Long areaLevel = areaCodeDTO.getAreaLevel();
                        if (areaLevel != null) {
                            gdsShipTempPrice.setAreaLevel(areaLevel);
                        }
                        gdsShipTempPrice.setProvinceCode(areaCodeDTO.getProvinceCode());
                        gdsShipTempPrice.setCityCode(areaCodeDTO.getCityCode());
                        try {
                            gdsShiptempPriceMapper.insert(gdsShipTempPrice);
                        } catch (Exception e) {
                           
                            LogUtil.error(MODULE, "插入失败", e);
                            throw e;
                        }
                    }
                }

            }
        }
    }

    /**
     * 
     * getInsertGSTPCondition:(获取前店参数用于入库到运费统计表). <br/>
     * 
     * @author gxq
     * @param gdsShipTempPrice
     * @param GdsShiptempPriceReqDTO
     * @return
     * @since JDK 1.6
     */
    private void getInsertGSTPCondition(GdsShiptempPrice gdsShipTempPrice,
            GdsShiptempPriceReqDTO GdsShiptempPriceReqDTO) {
        gdsShipTempPrice.setContinueAmount(GdsShiptempPriceReqDTO.getContinueAmount());
        gdsShipTempPrice.setContinuePrice(GdsShiptempPriceReqDTO.getContinuePrice());
        gdsShipTempPrice.setFirstAmount(GdsShiptempPriceReqDTO.getFirstAmount());
        gdsShipTempPrice.setFirstPrice(GdsShiptempPriceReqDTO.getFirstPrice());
        gdsShipTempPrice.setFreeAmount(GdsShiptempPriceReqDTO.getFreeAmount());
        gdsShipTempPrice.setPricingMode(GdsShiptempPriceReqDTO.getPricingMode());
        gdsShipTempPrice.setStatus(GdsConstants.Commons.STATUS_VALID);
        gdsShipTempPrice.setCreateTime(new Timestamp(System.currentTimeMillis()));
        gdsShipTempPrice.setCreateStaff(GdsShiptempPriceReqDTO.getStaff().getId());
    }

    /**
     * 
     * getInsertGSSICondiction:(获取前店参数用于入库到运费模板店铺索引维度表). <br/>
     * 
     * @author gxq
     * @param gdsShiptempShopIdx
     * @param reqDTO
     * @return
     * @since JDK 1.6
     */
    private GdsShiptempShopIdx getInsertGSSICondiction(GdsShiptempShopIdx gdsShiptempShopIdx,
            GdsShiptempReqDTO reqDTO) {
        gdsShiptempShopIdx.setIfFree(reqDTO.getIfFree());
        gdsShiptempShopIdx.setShipInstruction(reqDTO.getShipInstruction());
        gdsShiptempShopIdx.setShipTemplateName(reqDTO.getShipTemplateName());
        gdsShiptempShopIdx.setShipTemplateType(reqDTO.getShipTemplateType());
        gdsShiptempShopIdx.setCatgCode(reqDTO.getCatgCode());
        gdsShiptempShopIdx.setShopId(reqDTO.getShopId());
        gdsShiptempShopIdx.setStatus(GdsConstants.Commons.STATUS_VALID);
        return gdsShiptempShopIdx;
    }

    /**
     * 
     * getReuestCondition:(获取前店参数用于查询运费模板列表). <br/>
     * 
     * @author gxq
     * @param reqDTO
     * @param gdsShiptempShopIdxCriteria
     * @return
     * @since JDK 1.6
     */
    public GdsShiptempShopIdxCriteria getReuestCondition(GdsShiptempReqDTO reqDTO,
            GdsShiptempShopIdxCriteria gdsShiptempShopIdxCriteria) {
        GdsShiptempShopIdxCriteria.Criteria criteria = gdsShiptempShopIdxCriteria.createCriteria();
        // 店铺ID
        Long shopId = reqDTO.getShopId();
        if (shopId != null) {
            criteria.andShopIdEqualTo(shopId);
        }
        // 运费模板的名称
        String tempName = reqDTO.getShipTemplateName();
        if (!StringUtil.isBlank(tempName)) {
            criteria.andShipTemplateNameLike("%" + tempName + "%");
        }
        // 运费模板编码
        Long tempId = reqDTO.getId();
        if (!StringUtil.isEmpty(tempId)) {
            criteria.andTempIdEqualTo(tempId);
        }
        // 运费模板计价方式
        String tempType = reqDTO.getShipTemplateType();
        if (!StringUtil.isBlank(tempType)) {
            criteria.andShipTemplateTypeEqualTo(tempType);
        }
        // 是否免邮
        String ifFree = reqDTO.getIfFree();
        if (!StringUtil.isBlank(ifFree)) {
            criteria.andIfFreeEqualTo(ifFree);
        }
        // 是否过滤按金额的计价方式
        if (StringUtil.isNotBlank(reqDTO.getIfFilterValue())) {
            if (GdsConstants.Commons.STATUS_VALID.equals(reqDTO.getIfFilterValue())) {
                criteria.andShipTemplateTypeNotEqualTo(reqDTO.getIfFilterValue());
            }
        }
        // 分类
        if (StringUtil.isNotBlank(reqDTO.getCatgCode())) {
        	List<String> catgCodeList=new ArrayList<String>();
        	catgCodeList.add("0");
        	catgCodeList.add(reqDTO.getCatgCode());
        	criteria.andCatgCodeIn(catgCodeList);
        }
        criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        gdsShiptempShopIdxCriteria.setOrderByClause("create_time desc,update_time desc");
        gdsShiptempShopIdxCriteria.setLimitClauseStart(reqDTO.getStartRowIndex());
        gdsShiptempShopIdxCriteria.setLimitClauseCount(reqDTO.getPageSize());
        return gdsShiptempShopIdxCriteria;
    }

    private void editInitIntoGSTP(GdsShiptempReqDTO reqDTO) {
        Long id = reqDTO.getId();
        // 根据运费模板id失效原来的运费计价表记录
        GdsShiptempPriceCriteria gdsShiptempPriceCriteria = new GdsShiptempPriceCriteria();
        GdsShiptempPriceCriteria.Criteria pCriteria = gdsShiptempPriceCriteria.createCriteria();
        if (StringUtil.isNotEmpty(reqDTO.getId())) {
            pCriteria.andShipTemplateIdEqualTo(reqDTO.getId());
        }
        pCriteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        List<GdsShiptempPrice> listPrice = gdsShiptempPriceMapper
                .selectByExample(gdsShiptempPriceCriteria);
        if (listPrice != null) {
            for (GdsShiptempPrice gdsShiptempPrice : listPrice) {
                GdsShiptempPrice update = new GdsShiptempPrice();
                update.setStatus(GdsConstants.Commons.STATUS_INVALID);
                update.setUpdateStaff(reqDTO.getStaff().getId());
                GdsShiptempPriceCriteria updateCriteria = new GdsShiptempPriceCriteria();
                GdsShiptempPriceCriteria.Criteria criteria = updateCriteria.createCriteria();
                criteria.andShipTemplateIdEqualTo(gdsShiptempPrice.getShipTemplateId());
                criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
                gdsShiptempPriceMapper.updateByExampleSelective(update, updateCriteria);
            }
        }
        // 新增运费计价表记录
        
        try {
            insertIntoGSTP(reqDTO, id);
        } catch (Exception e) {
           throw new BusinessException(GdsErrorConstants.GdsShipMent.ERROR_GOODS_SHIP_MENT_240007);
        }
    }

    private void parseResult(GdsShiptempRespDTO gdsShiptempRespDTO,
            List<GdsShiptempPriceRespDTO> GdsShiptempPriceRespDTOList) {
        if (StringUtil.isNotEmpty(gdsShiptempRespDTO.getGdsShipTempPriceRespDTO())) {
            Map<String, String> map = new HashMap<String, String>();
            // 一条模板记录
            GdsShiptempPriceRespDTO gdsShiptempPriceRespDTO = null;
            // 一条模板记录里面的省份列表
            List<GdsShipAreaCodeRespDTO> areaCodeList = null;
            // 省份列表中的一个省份记录
            GdsShipAreaCodeRespDTO provinceCode = null;
            // 一个省份记录里面的城市列表
            List<GdsShipAreaCodeRespDTO> cityCodeList = null;
            // 一个城市列表里的一个城市
            GdsShipAreaCodeRespDTO cityCode = null;
            List<GdsShiptempPriceRespDTO> list = new ArrayList<GdsShiptempPriceRespDTO>();
            list = gdsShiptempRespDTO.getGdsShipTempPriceRespDTO();
            for (GdsShiptempPriceRespDTO respDto : list) {
                StringBuffer str = new StringBuffer();
                str.append(respDto.getFirstAmount() + "_");
                str.append(respDto.getFirstPrice() + "_");
                str.append(respDto.getContinueAmount() + "_");
                str.append(respDto.getContinuePrice() + "_");
                str.append(respDto.getFirstAmount());
                if (map.containsKey(str.toString())) {
                    // 记录模板存在，则判断该模板记录的省份否已经存在
                    if (!map.containsKey(str.toString() + "_" + respDto.getProvinceCode())) {
                        // 省份不存在
                        // 如果不存在，则把省份，地市入参
                        provinceCode = new GdsShipAreaCodeRespDTO();
                        provinceCode.setProvinceCode(respDto.getProvinceCode());
                        if (StringUtil.isNotBlank(respDto.getProvinceCode())) {
                            provinceCode.setProvinceName(BaseAreaAdminUtil.fetchAreaName(respDto
                                    .getProvinceCode()));
                        }
                        // 记录模板没有，初始化省份列表里的一个城市列表，并入参
                        cityCodeList = new ArrayList<GdsShipAreaCodeRespDTO>();
                        // 记录模板没有，初始化城市列表里的一个城市，并入参
                        cityCode = new GdsShipAreaCodeRespDTO();
                        cityCode.setCityCode(respDto.getCityCode());
                        if (StringUtil.isNotBlank(respDto.getCityCode())) {
                            cityCode.setCityName(BaseAreaAdminUtil.fetchAreaName(respDto
                                    .getCityCode()));
                        }
                        cityCodeList.add(cityCode);
                        provinceCode.setCityCodeList(cityCodeList);
                        provinceCode.setProvinceCode(respDto.getProvinceCode());
                        if (StringUtil.isNotBlank(respDto.getProvinceCode())) {
                            provinceCode.setProvinceName(BaseAreaAdminUtil.fetchAreaName(respDto
                                    .getProvinceCode()));
                        }

                        areaCodeList.add(provinceCode);
                        map.put(str.toString() + "_" + respDto.getProvinceCode(), "1");
                        map.put(str.toString() + "_" + respDto.getProvinceCode() + "_"
                                + respDto.getCityCode(), "1");
                    } else {
                        // 判断地市是否存在
                        if (!map.containsKey(str.toString() + "_" + respDto.getProvinceCode() + "_"
                                + respDto.getCityCode())) {
                            // 地市不存在，入参
                            cityCode = new GdsShipAreaCodeRespDTO();
                            cityCode.setCityCode(respDto.getCityCode());
                            if (StringUtil.isNotBlank(respDto.getCityCode())) {
                                cityCode.setCityName(BaseAreaAdminUtil.fetchAreaName(respDto
                                        .getCityCode()));
                            }
                            cityCodeList.add(cityCode);
                            provinceCode.setCityCodeList(cityCodeList);
                        }
                    }
                } else {
                    map.put(str.toString(), "1");
                    map.put(str.toString() + "_" + respDto.getProvinceCode(), "1");
                    map.put(str.toString() + "_" + respDto.getProvinceCode() + "_"
                            + respDto.getCityCode(), "1");
                    // 记录模板没有，初始化一条模板记录，并入参
                    gdsShiptempPriceRespDTO = new GdsShiptempPriceRespDTO();
                    ObjectCopyUtil.copyObjValue(respDto, gdsShiptempPriceRespDTO, "", false);
                    // 记录模板没有，初始化一条模板记录里面的省份列表，并入参
                    areaCodeList = new ArrayList<GdsShipAreaCodeRespDTO>();
                    provinceCode = new GdsShipAreaCodeRespDTO();
                    provinceCode.setProvinceCode(respDto.getProvinceCode());
                    if (StringUtil.isNotBlank(respDto.getProvinceCode())) {
                        provinceCode.setProvinceName(BaseAreaAdminUtil.fetchAreaName(respDto
                                .getProvinceCode()));
                    }
                    // 记录模板没有，初始化省份列表里的一个城市列表，并入参
                    cityCodeList = new ArrayList<GdsShipAreaCodeRespDTO>();
                    // 记录模板没有，初始化城市列表里的一个城市，并入参
                    cityCode = new GdsShipAreaCodeRespDTO();
                    cityCode.setCityCode(respDto.getCityCode());
                    if (StringUtil.isNotBlank(respDto.getCityCode())) {
                        cityCode.setCityName(BaseAreaAdminUtil.fetchAreaName(respDto.getCityCode()));
                    }
                    cityCodeList.add(cityCode);
                    provinceCode.setCityCodeList(cityCodeList);
                    areaCodeList.add(provinceCode);
                    gdsShiptempPriceRespDTO.setAreaCodeList(areaCodeList);
                    GdsShiptempPriceRespDTOList.add(gdsShiptempPriceRespDTO);
                }
            }
            map.clear();
        }
        gdsShiptempRespDTO.setGdsShipTempPriceRespDTO(GdsShiptempPriceRespDTOList);
    }

    @Override
    public Map<Long, Long> calcShipExpenseByCarts(ROrdCartsCommRequest cartsCommRequest)
            throws BusinessException {

        Map<Long, Long> map = new HashMap<Long, Long>();
        for (ROrdCartCommRequest cartCommRequest : cartsCommRequest.getOrdCartsCommList()) {
            GdsShipmentCalInfoReqDTO calInfoReqDTO = new GdsShipmentCalInfoReqDTO();
            calInfoReqDTO.setShopId(cartCommRequest.getShopId());
            List<Long> promIds=new ArrayList<Long>();
            if(cartCommRequest.isIfFulfillProm()){
                if(cartCommRequest.getPromId()!=null&&cartCommRequest.getPromId().longValue()!=0){
                    promIds.add(cartCommRequest.getPromId());
                }
            }
            List<ROrdCartItemCommRequest> commRequests = cartCommRequest.getOrdCartItemCommList();
            List<GdsInfoShipmentReqDTO> gdsInfos = new ArrayList<GdsInfoShipmentReqDTO>();
            if (CollectionUtils.isEmpty(commRequests)) {
                throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,
                        new String[] { "commRequests" });

            }
            for (ROrdCartItemCommRequest cartItemCommRequest : commRequests) {
                //排除数字印刷版
//                if(!"1".equals(cartItemCommRequest.getPrnFlag())){
                    GdsInfoShipmentReqDTO gdsInfoShipmentReqDTO = new GdsInfoShipmentReqDTO();
                    gdsInfoShipmentReqDTO.setCatgCode(cartItemCommRequest.getCategoryCode());
                    gdsInfoShipmentReqDTO.setGdsId(cartItemCommRequest.getGdsId());
                    gdsInfoShipmentReqDTO.setCount(cartItemCommRequest.getOrderAmount());
                    gdsInfoShipmentReqDTO.setTypeId(cartItemCommRequest.getGdsType());
                    gdsInfoShipmentReqDTO.setSkuId(cartItemCommRequest.getSkuId());
                    gdsInfoShipmentReqDTO.setAmount(cartItemCommRequest.getOrderMoney());
                    gdsInfos.add(gdsInfoShipmentReqDTO);
                    if(cartItemCommRequest.isIfFulfillProm()){
                        if(cartItemCommRequest.getPromId()!=null&&cartItemCommRequest.getPromId().longValue()!=0){
                            promIds.add(cartItemCommRequest.getPromId());
                        }
                    }
//                }
               
            }

            calInfoReqDTO.setAmount(cartCommRequest.getPayMoney());
            calInfoReqDTO.setGdsInfos(gdsInfos);
            calInfoReqDTO.setCityCode(cartsCommRequest.getCityCode());
            calInfoReqDTO.setProvinceCode(cartsCommRequest.getProvinceCode());
            calInfoReqDTO.setCountryCode(cartsCommRequest.getCountryCode());
            calInfoReqDTO.setCurrentSiteId(cartsCommRequest.getCurrentSiteId());
            calInfoReqDTO.setPromIds(promIds);
            Long shipExpense = this.calcShipExpense(calInfoReqDTO);
            map.put(cartCommRequest.getShopId(), shipExpense);

        }

        return map;
    }

    /**
     * 
     * TODO 计算商品运费（可选）.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsShipTempSV#calcShipExpense(com.zengshi.ecp.goods.dubbo.dto.GdsShipmentCalInfoReqDTO)
     */
    @Override
    public Long calcShipExpense(GdsShipmentCalInfoReqDTO calInfoReqDTO) throws BusinessException {
        Long price = 0L;
        List<GdsInfoShipmentReqDTO> gdsInfoShipmentReqDTOs = calInfoReqDTO.getGdsInfos();
        if (gdsInfoShipmentReqDTOs == null || gdsInfoShipmentReqDTOs.size() <= 0) {

            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,
                    new String[] { "gdsInfos" });

        }
        // 判断是否金额达到促销免邮，如果达到，直接返回0；
         PromShipDTO promShipDTO = new PromShipDTO();
         promShipDTO.setShopId(calInfoReqDTO.getShopId());
         promShipDTO.setMoney(calInfoReqDTO.getAmount());
         promShipDTO.setCountryCode(calInfoReqDTO.getCountryCode());
         promShipDTO.setCityCode(calInfoReqDTO.getCityCode());
         promShipDTO.setProvinceCode(calInfoReqDTO.getProvinceCode());
         promShipDTO.setCurrentSiteId(calInfoReqDTO.getCurrentSiteId());
         PromShipRespDTO promShipRespDTO = promShipRSV.qryPromShip(promShipDTO);
         if(promShipRespDTO.getMsgCode().equals("1")){
        
         return 0L;
         }

        //促销配置免邮（订单级）
        PromPostDTO promPostDTO=new PromPostDTO();
        //订单级和单品明细级促销id都需要传
        promPostDTO.setPromIds(calInfoReqDTO.getPromIds());
        PromPostRespDTO promPostRespDTO=promShipRSV.checkIfFreePost(promPostDTO);
        if(StringUtils.equals(promPostRespDTO.getIfFreePost(),GdsConstants.Commons.STATUS_VALID)){
            return 0L;
        }

        //TODO 促销免邮后，仍然需要计算未参加促销免邮的商品

        // 过滤虚拟商品
        this.delGdsInfosByTypeId(gdsInfoShipmentReqDTOs);
        // 过滤免邮商品
        this.delGdsInfosFree(gdsInfoShipmentReqDTOs);
        // 按规则1计算运费 ----商品-》分类—》店铺运费模板
        if (SysCfgUtil.checkSysCfgValue("GDS_SHIP_TEMPLATE_RULE",
                GdsConstants.GdsShiptemp.GDS_SHIPMENT_RULE_01)) {

            // 统计所有商品数量，以商品聚合数目;
            List<GdsInfoShipmentReqDTO> gdsInfoShipByGds = this
                    .delGdsShipInfoGroupByGdsId(gdsInfoShipmentReqDTOs);
            // 根据商品过滤

            this.delGdsInfoByFreeShipGds(calInfoReqDTO, gdsInfoShipmentReqDTOs, gdsInfoShipByGds);
            // 统计所有分类数量，以分类聚合数目；
            List<GdsInfoShipmentReqDTO> gdsInfoShipByCatgCode = this
                    .delGdsShipInfoGroupByCatgCode(gdsInfoShipmentReqDTOs);
            // 根据分类过滤
            this.delGdsInfoByFreeShipCatg(calInfoReqDTO, gdsInfoShipmentReqDTOs,
                    gdsInfoShipByCatgCode);
            // 根据店铺过滤
            this.delGdsInfoByFreeShipShop(calInfoReqDTO, gdsInfoShipmentReqDTOs);
            // **************过滤后，计算运费******************//
            // 统计所有商品数量，以商品聚合数目;
            List<GdsInfoShipmentReqDTO> gdsInfoShipByGdsForDeal = this
                    .delGdsShipInfoGroupByGdsId(gdsInfoShipmentReqDTOs);

            // 通过商品模板计算运费
            price = price
                    + this.dealGdsInfoByFreeShipGds(calInfoReqDTO, gdsInfoShipmentReqDTOs,
                            gdsInfoShipByGdsForDeal);
            // 统计所有分类数量，以分类聚合数目；
            List<GdsInfoShipmentReqDTO> gdsInfoShipByCatgCodeForDeal = this
                    .delGdsShipInfoGroupByCatgCode(gdsInfoShipmentReqDTOs);
            // 通过分类模板计算运费
            price = price
                    + this.dealGdsInfoByFreeShipCatg(calInfoReqDTO, gdsInfoShipmentReqDTOs,
                            gdsInfoShipByCatgCodeForDeal);
            // 通过店铺模板计算运费
            price = price + this.dealGdsInfoByFreeShipShop(calInfoReqDTO, gdsInfoShipmentReqDTOs);

            // 按规则1计算运费 ---- 店铺运费模板-》分类-》商品
        } else if (SysCfgUtil.checkSysCfgValue("GDS_SHIP_TEMPLATE_RULE",
                GdsConstants.GdsShiptemp.GDS_SHIPMENT_RULE_02)) {
            // 根据店铺过滤
            this.delGdsInfoByFreeShipShop(calInfoReqDTO, gdsInfoShipmentReqDTOs);

            // 统计所有分类数量，以分类聚合数目；
            List<GdsInfoShipmentReqDTO> gdsInfoShipByCatgCode = this
                    .delGdsShipInfoGroupByCatgCode(gdsInfoShipmentReqDTOs);
            // 根据分类过滤
            this.delGdsInfoByFreeShipCatg(calInfoReqDTO, gdsInfoShipmentReqDTOs,
                    gdsInfoShipByCatgCode);

            // 统计所有商品数量，以商品聚合数目;
            List<GdsInfoShipmentReqDTO> gdsInfoShipByGds = this
                    .delGdsShipInfoGroupByGdsId(gdsInfoShipmentReqDTOs);
            // 根据商品过滤

            this.delGdsInfoByFreeShipGds(calInfoReqDTO, gdsInfoShipmentReqDTOs, gdsInfoShipByGds);
            // 通过店铺模板计算运费
            price = price + this.dealGdsInfoByFreeShipShop(calInfoReqDTO, gdsInfoShipmentReqDTOs);

        }
        if (CollectionUtils.isNotEmpty(gdsInfoShipmentReqDTOs)) {
            throw new BusinessException(GdsErrorConstants.GdsShipMent.ERROR_GOODS_SHIP_MENT_240003);

        }
        return price;
    }

    /**
     * 
     * queryCalNumByCalRule:(根据运费模板类型计算商品货运数量). <br/>
     * 
     * @author zjh
     * @param gdsShiptemp
     * @param gdsId
     * @param count
     * @return
     * @since JDK 1.6
     */
    private Long queryCalNumByCalRule(GdsShiptemp gdsShiptemp, Long gdsId, Long count)
            throws BusinessException {
        if (gdsShiptemp.getShipTemplateType()
                .equals(GdsConstants.GdsShiptemp.GDS_SHIPMENT_BY_COUNT)) {
            return count * 1;
        } else if (gdsShiptemp.getShipTemplateType().equals(
                GdsConstants.GdsShiptemp.GDS_SHIPMENT_BY_WEIGHT)) {
            return count * 1;
        } else if (gdsShiptemp.getShipTemplateType().equals(
                GdsConstants.GdsShiptemp.GDS_SHIPMENT_BY_VOLUME)) {
            return count * 1;
        } else {
            return count * 1;
        }

    }

    /**
     * 
     * dealGdsInfoByFreeShipGds:(根据商品模板计算商品运费). <br/>
     * 
     * @author zjh
     * @param calInfoReqDTO
     * @param gdsInfoShipmentReqDTOs
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    protected Long dealGdsInfoByFreeShipGds(GdsShipmentCalInfoReqDTO calInfoReqDTO,
            List<GdsInfoShipmentReqDTO> gdsInfoShipmentReqDTOs,
            List<GdsInfoShipmentReqDTO> gdsInfoShipByGds) throws BusinessException {
        Long count = 0L;

        if (CollectionUtils.isEmpty(gdsInfoShipmentReqDTOs)) {
            return count;

        }
        // 以商品为聚合进行计费
        // 遍历商品聚合列表，对每个商品合并进行运费计算
        for (int i = gdsInfoShipByGds.size() - 1; i >= 0; i--) {
            GdsInfoShipmentReqDTO gdsInfoShipmentReqDTO = gdsInfoShipByGds.get(i);
            Long gdsId = gdsInfoShipmentReqDTO.getGdsId();
            if (gdsId == null || 0L == gdsId) {

                throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,
                        new String[] { "gdsId" });

            }
            GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQuerySV.queryGdsInfoById(gdsId);
            Long shipTempId = gdsInfoRespDTO.getShipTemplateId();
            if (shipTempId != null && 0L != shipTempId) {
                count = count
                        + this.dealGdsInfoByShipmentTempForGds(gdsInfoShipmentReqDTOs,
                                gdsInfoShipmentReqDTO, calInfoReqDTO, shipTempId);
            }

        }
        return count;

    }

    /**
     * 
     * dealGdsInfoByFreeShipCatg:(根据商品分类模板计算商品运费). <br/>
     * 
     * @author zjh
     * @param calInfoReqDTO
     * @param gdsInfoShipmentReqDTOs
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    protected Long dealGdsInfoByFreeShipCatg(GdsShipmentCalInfoReqDTO calInfoReqDTO,
            List<GdsInfoShipmentReqDTO> gdsInfoShipmentReqDTOs,
            List<GdsInfoShipmentReqDTO> gdsInfoShipByCatgCodeForDeal) throws BusinessException {
        Long count = 0L;
        if (CollectionUtils.isEmpty(gdsInfoShipmentReqDTOs)) {
            return count;
        }
        // 按分类计算商品运费
        // 遍历按商品分类聚合的列表
        for (GdsInfoShipmentReqDTO gdsInfoShipmentReqDTO : gdsInfoShipByCatgCodeForDeal) {
            // 获取顶级分类的运费模板
            GdsShiptempShopIdx gdsShiptemp = chooseShipTempByCatgCode(
                    gdsInfoShipmentReqDTO.getCatgCode(), calInfoReqDTO.getShopId());
            if (gdsShiptemp != null) {
                // 叠加每种顶级分类的运费
                count = count
                        + this.dealGdsInfoByShipmentTempForCatgCode(gdsInfoShipmentReqDTOs,
                                gdsInfoShipmentReqDTO, calInfoReqDTO, gdsShiptemp.getTempId());

            }

        }

        return count;

    }

    /**
     * 
     * dealGdsInfoByFreeShipShop:(根据店铺模板计算商品运费). <br/>
     * 
     * 
     * @author zjh
     * @param calInfoReqDTO
     * @param gdsInfoShipmentReqDTOs
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    protected Long dealGdsInfoByFreeShipShop(GdsShipmentCalInfoReqDTO calInfoReqDTO,
            List<GdsInfoShipmentReqDTO> gdsInfoShipmentReqDTOs) throws BusinessException {
        Long count = 0L;
        if (gdsInfoShipmentReqDTOs.size() == 0) {
            return count;

        }
        // 获取店铺默认运费模板
        Long shopId = calInfoReqDTO.getShopId();
        GdsShop2ShiptempCriteria example = new GdsShop2ShiptempCriteria();
        GdsShop2ShiptempCriteria.Criteria criteria = example.createCriteria();
        criteria.andShopIdEqualTo(shopId);
        criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        List<GdsShop2Shiptemp> gdsShop2Shiptemps = gdsShop2ShiptempMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(gdsShop2Shiptemps)) {
            GdsShop2Shiptemp gdsShop2Shiptemp = gdsShop2Shiptemps.get(0);
            count = count + this.dealGdsInfoByShipmentTempForShop(gdsInfoShipmentReqDTOs, calInfoReqDTO,
                            gdsShop2Shiptemp.getShipTemplateId());

        } else {
            // 没有默认运费，抛出异常
            throw new BusinessException(GdsErrorConstants.GdsShipMent.ERROR_GOODS_SHIP_MENT_240001);

        }

        return count;
    }

    /**
     * 
     * delGdsInfoByFreeShipGds:(根据商品模板删除免邮商品). <br/>
     * 
     * @author zjh
     * @param calInfoReqDTO
     * @param gdsInfoShipmentReqDTOs
     * @throws BusinessException
     * @since JDK 1.6
     */
    protected void delGdsInfoByFreeShipGds(GdsShipmentCalInfoReqDTO calInfoReqDTO,
            List<GdsInfoShipmentReqDTO> gdsInfoShipmentReqDTOs,
            List<GdsInfoShipmentReqDTO> gdsInfoShipByGds) throws BusinessException {

        // 先过滤免邮商品
        for (int i = gdsInfoShipByGds.size() - 1; i >= 0; i--) {
            // 取聚合后的商品集合
            GdsInfoShipmentReqDTO gdsInfoShipmentReqDTO = gdsInfoShipByGds.get(i);
            Long gdsId = gdsInfoShipmentReqDTO.getGdsId();
            if (gdsId == null || 0L == gdsId) {

                throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,
                        new String[] { "gdsId" });

            }
            // 获取商品对应的运费模板id
            GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQuerySV.queryGdsInfoById(gdsId);
            Long shipTempId = gdsInfoRespDTO.getShipTemplateId();
            if (shipTempId != null && 0L != shipTempId) {
                // 根据商品运费模板计算运费
                this.delGdsInfoByShipmentTempForGdsId(gdsInfoShipmentReqDTOs,
                        gdsInfoShipmentReqDTO, calInfoReqDTO, shipTempId);
            }

        }

    }

    /**
     * 
     * delGdsInfoByFreeShipCatg:(根据商品分类删除免邮商品). <br/>
     * 
     * @author zjh
     * @param calInfoReqDTO
     * @param gdsInfoShipmentReqDTOs
     * @throws BusinessException
     * @since JDK 1.6
     */
    protected void delGdsInfoByFreeShipCatg(GdsShipmentCalInfoReqDTO calInfoReqDTO,
            List<GdsInfoShipmentReqDTO> gdsInfoShipmentReqDTOs,
            List<GdsInfoShipmentReqDTO> gdsInfoShipByCatgCode) throws BusinessException {

        // 按顶级平台分类过滤免邮商品
        for (int i = gdsInfoShipByCatgCode.size() - 1; i >= 0; i--) {
            GdsInfoShipmentReqDTO gdsInfoShipmentReqDTO = gdsInfoShipByCatgCode.get(i);

            this.delByUpTraceCatgCode(gdsInfoShipmentReqDTOs, gdsInfoShipmentReqDTO, calInfoReqDTO);

        }

    }

    /**
     * 
     * delGdsInfoByFreeShipShop:(根据店铺模板删除免邮商品). <br/>
     * 
     * @author zjh
     * @param calInfoReqDTO
     * @param gdsInfoShipmentReqDTOs
     * @throws BusinessException
     * @since JDK 1.6
     */
    protected void delGdsInfoByFreeShipShop(GdsShipmentCalInfoReqDTO calInfoReqDTO,
            List<GdsInfoShipmentReqDTO> gdsInfoShipmentReqDTOs) throws BusinessException {

        // 获取店铺默认运费模板
        Long shopId = calInfoReqDTO.getShopId();
        GdsShop2ShiptempCriteria example = new GdsShop2ShiptempCriteria();
        GdsShop2ShiptempCriteria.Criteria criteria = example.createCriteria();
        criteria.andShopIdEqualTo(shopId);
        criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        List<GdsShop2Shiptemp> gdsShop2Shiptemps = gdsShop2ShiptempMapper.selectByExample(example);
        if (gdsShop2Shiptemps != null && gdsShop2Shiptemps.size() > 0) {
            GdsShop2Shiptemp gdsShop2Shiptemp = gdsShop2Shiptemps.get(0);
            this.delGdsInfoByShipmentTempForShop(gdsInfoShipmentReqDTOs, calInfoReqDTO,
                    gdsShop2Shiptemp.getShipTemplateId());

        } else {
            // 没有默认运费，抛出异常
            throw new BusinessException(GdsErrorConstants.GdsShipMent.ERROR_GOODS_SHIP_MENT_240001);
        }

    }

    /**
     * 
     * delGdsInfoByShipmentTemp:(根据模板判断商品是否免邮). <br/>
     * 
     * @author zjh
     * @param gdsInfoShipmentReqDTOs
     * @param gdsInfoShipmentReqDTO
     * @param calInfoReqDTO
     * @param shipTempId
     * @throws BusinessException
     * @since JDK 1.6
     */
    private void delGdsInfoByShipmentTempForGdsId(
            List<GdsInfoShipmentReqDTO> gdsInfoShipmentReqDTOs,
            GdsInfoShipmentReqDTO gdsInfoShipmentReqDTO, GdsShipmentCalInfoReqDTO calInfoReqDTO,
            Long shipTempId) throws BusinessException {
        if (shipTempId == -1L) {

            return;
        }
        Long gdsId = gdsInfoShipmentReqDTO.getGdsId();
        GdsShiptemp gdsShiptemp = gdsShiptempMapper.selectByPrimaryKey(shipTempId);

        if (!gdsShiptemp.getStatus().equals(GdsConstants.Commons.STATUS_INVALID)) {
            // 如果模板是免邮的，则移除该商品下所有单品运费元信息
            if (gdsShiptemp.getIfFree().equals(GdsConstants.Commons.STATUS_VALID)) {
                // 如果店铺运费模板是免邮，则直接清空运费列表;
                for (int i = gdsInfoShipmentReqDTOs.size() - 1; i >= 0; i--) {
                    GdsInfoShipmentReqDTO shipmentReqDTO = gdsInfoShipmentReqDTOs.get(i);
                    // 如果是商品聚合计算
                    // if(optType.equals("gdsId")){
                    if (shipmentReqDTO.getGdsId().equals(gdsInfoShipmentReqDTO.getGdsId())) {
                        gdsInfoShipmentReqDTOs.remove(shipmentReqDTO);
                    }

                }
            } else {
                GdsShiptempPriceCriteria example = new GdsShiptempPriceCriteria();
                GdsShiptempPriceCriteria.Criteria criteria = example.createCriteria();
                criteria.andShipTemplateIdEqualTo(gdsShiptemp.getId());
                if (!StringUtils.isBlank(calInfoReqDTO.getProvinceCode())) {
                    criteria.andProvinceCodeEqualTo(calInfoReqDTO.getProvinceCode());
                }else{
                    criteria.andProvinceCodeIsNull();
                }
                if (!StringUtils.isBlank(calInfoReqDTO.getCountryCode())) {
                    criteria.andCountryCodeEqualTo(calInfoReqDTO.getCountryCode());
                }else{
                    criteria.andCountryCodeIsNull();
                }
                if (!StringUtils.isBlank(calInfoReqDTO.getCityCode())) {
                    criteria.andCityCodeEqualTo(calInfoReqDTO.getCityCode());
                }else{
                    criteria.andCityCodeIsNull();
                }
                criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);

                List<GdsShiptempPrice> gdsShiptempPrices = gdsShiptempPriceMapper
                        .selectByExample(example);
                if (CollectionUtils.isNotEmpty(gdsShiptempPrices)) {
                    GdsShiptempPrice gdsShiptempPrice = gdsShiptempPrices.get(0);
                    // 计算当前商品聚合下
                    Long num = this.queryCalNumByCalRule(gdsShiptemp, gdsId,
                            gdsInfoShipmentReqDTO.getCount());
                    // 如果商品达到免邮的数量，则移除属于商品id的所有单品运费元信息
                    if (gdsShiptempPrice.getFreeAmount() <= num) {
                        for (int i = gdsInfoShipmentReqDTOs.size() - 1; i >= 0; i--) {
                            GdsInfoShipmentReqDTO shipmentReqDTO = gdsInfoShipmentReqDTOs.get(i);
                            if (shipmentReqDTO.getGdsId().equals(gdsId)) {
                                gdsInfoShipmentReqDTOs.remove(shipmentReqDTO);
                            }
                        }
                    }

                } else {

                    // 判断模板默认计价记录

                    GdsShiptempPriceCriteria exampleDef = new GdsShiptempPriceCriteria();
                    GdsShiptempPriceCriteria.Criteria criteriaDef = exampleDef.createCriteria();
                    criteriaDef.andShipTemplateIdEqualTo(gdsShiptemp.getId());
                    criteriaDef.andCountryCodeIsNull();
                    criteriaDef.andProvinceCodeIsNull();
                    criteriaDef.andCityCodeIsNull();
                    criteriaDef.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
                    List<GdsShiptempPrice> gdsShiptempPricesDef = gdsShiptempPriceMapper
                            .selectByExample(exampleDef);
                    if (CollectionUtils.isNotEmpty(gdsShiptempPricesDef)) {

                        GdsShiptempPrice gdsShiptempPrice = gdsShiptempPricesDef.get(0);

                        // 计算当前商品聚合下
                        Long num = this.queryCalNumByCalRule(gdsShiptemp, gdsId,
                                gdsInfoShipmentReqDTO.getCount());

                        // 如果商品达到免邮的数量，则移除属于商品分类的所有单品运费元信息
                        if (gdsShiptempPrice.getFreeAmount() <= num) {

                            for (int i = gdsInfoShipmentReqDTOs.size() - 1; i >= 0; i--) {
                                GdsInfoShipmentReqDTO shipmentReqDTO = gdsInfoShipmentReqDTOs
                                        .get(i);

                                if (shipmentReqDTO.getGdsId().equals(
                                        gdsInfoShipmentReqDTO.getGdsId())) {

                                    gdsInfoShipmentReqDTOs.remove(shipmentReqDTO);
                                }

                            }

                        }

                    }

                }
            }
        }

    }

    private void delGdsInfoByShipmentTempForShop(
            List<GdsInfoShipmentReqDTO> gdsInfoShipmentReqDTOs,
            GdsShipmentCalInfoReqDTO calInfoReqDTO, Long shipTempId) throws BusinessException {
        GdsShiptemp gdsShiptemp = gdsShiptempMapper.selectByPrimaryKey(shipTempId);

        if (!gdsShiptemp.getStatus().equals(GdsConstants.Commons.STATUS_INVALID)) {
            // 如果模板是免邮的，则移除该商品下所有单品运费元信息
            if (gdsShiptemp.getIfFree().equals(GdsConstants.Commons.STATUS_VALID)) {

                // 如果店铺运费模板是免邮，则直接清空运费列表;
                gdsInfoShipmentReqDTOs.clear();

            } else {
                GdsShiptempPriceCriteria example = new GdsShiptempPriceCriteria();
                GdsShiptempPriceCriteria.Criteria criteria = example.createCriteria();
                criteria.andShipTemplateIdEqualTo(gdsShiptemp.getId());
                criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);

                if (!StringUtils.isBlank(calInfoReqDTO.getProvinceCode())) {
                    criteria.andProvinceCodeEqualTo(calInfoReqDTO.getProvinceCode());
                }else{
                    criteria.andProvinceCodeIsNull();
                }
                if (!StringUtils.isBlank(calInfoReqDTO.getCountryCode())) {
                    criteria.andCountryCodeEqualTo(calInfoReqDTO.getCountryCode());
                }else{
                    criteria.andCountryCodeIsNull();
                }
                if (!StringUtils.isBlank(calInfoReqDTO.getCityCode())) {
                    criteria.andCityCodeEqualTo(calInfoReqDTO.getCityCode());
                }else{
                    criteria.andCityCodeIsNull();
                }
                List<GdsShiptempPrice> gdsShiptempPrices = gdsShiptempPriceMapper
                        .selectByExample(example);

                if (CollectionUtils.isNotEmpty(gdsShiptempPrices)) {
                    GdsShiptempPrice gdsShiptempPrice = gdsShiptempPrices.get(0);

                    // 统计店铺下所有商品的数量
                    Long num = 0L;
                    // 如果不是按金额计费，则统计运费的计费数量
                    if (!GdsConstants.GdsShiptemp.GDS_SHIPMENT_BY_MONEY.equals(gdsShiptemp
                            .getShipTemplateType())) {
                        for (int i = gdsInfoShipmentReqDTOs.size() - 1; i >= 0; i--) {
                            GdsInfoShipmentReqDTO shipmentReqDTO = gdsInfoShipmentReqDTOs.get(i);

                            num = num
                                    + this.queryCalNumByCalRule(gdsShiptemp,
                                            shipmentReqDTO.getGdsId(), shipmentReqDTO.getCount());

                        }

                    } else {
                        // 如果是按金额计费，则将金额作为计费的金额,
                        for (int i = gdsInfoShipmentReqDTOs.size() - 1; i >= 0; i--) {
                            GdsInfoShipmentReqDTO shipmentReqDTO = gdsInfoShipmentReqDTOs.get(i);
                            num = num + shipmentReqDTO.getAmount();
                        }

                    }

                    // 如果商品达到免邮的数量，则移除属于商品id的所有单品运费元信息
                    if (gdsShiptempPrice.getFreeAmount() <= num) {
                        gdsInfoShipmentReqDTOs.clear();

                    }

                } else {// 取默认运费模板

                    // 判断模板默认计价记录

                    GdsShiptempPriceCriteria exampleDef = new GdsShiptempPriceCriteria();
                    GdsShiptempPriceCriteria.Criteria criteriaDef = exampleDef.createCriteria();
                    criteriaDef.andShipTemplateIdEqualTo(gdsShiptemp.getId());
                    criteriaDef.andCountryCodeIsNull();
                    criteriaDef.andProvinceCodeIsNull();
                    criteriaDef.andCityCodeIsNull();
                    criteriaDef.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);

                    List<GdsShiptempPrice> gdsShiptempPricesDef = gdsShiptempPriceMapper
                            .selectByExample(exampleDef);
                    if (CollectionUtils.isNotEmpty(gdsShiptempPricesDef)) {

                        GdsShiptempPrice gdsShiptempPrice = gdsShiptempPricesDef.get(0);

                        // 统计商品分类下所有商品的数量
                        Long num = 0L;

                        // 如果不是按金额计费，则统计运费的计费数量
                        if (!GdsConstants.GdsShiptemp.GDS_SHIPMENT_BY_MONEY.equals(gdsShiptemp
                                .getShipTemplateType())) {
                            for (int i = gdsInfoShipmentReqDTOs.size() - 1; i >= 0; i--) {
                                GdsInfoShipmentReqDTO shipmentReqDTO = gdsInfoShipmentReqDTOs
                                        .get(i);

                                num = num
                                        + this.queryCalNumByCalRule(gdsShiptemp,
                                                shipmentReqDTO.getGdsId(),
                                                shipmentReqDTO.getCount());

                            }

                        } else {

                            // 如果是按金额计费，则将金额作为计费的金额,
                            for (int i = gdsInfoShipmentReqDTOs.size() - 1; i >= 0; i--) {
                                GdsInfoShipmentReqDTO shipmentReqDTO = gdsInfoShipmentReqDTOs
                                        .get(i);
                                num = num + shipmentReqDTO.getAmount();
                            }
                        }

                        // 如果商品达到免邮的数量，则移除属于商品分类的所有单品运费元信息
                        if (gdsShiptempPrice.getFreeAmount() <= num) {
                            gdsInfoShipmentReqDTOs.clear();

                        }

                    }

                }
            }
        }

    }

    private void delGdsInfoByShipmentTempForCatgCode(
            List<GdsInfoShipmentReqDTO> gdsInfoShipmentReqDTOs,
            GdsInfoShipmentReqDTO gdsInfoShipmentReqDTO, GdsShipmentCalInfoReqDTO calInfoReqDTO,
            Long shipTempId) throws BusinessException {
        GdsShiptemp gdsShiptemp = gdsShiptempMapper.selectByPrimaryKey(shipTempId);

        if (!gdsShiptemp.getStatus().equals(GdsConstants.Commons.STATUS_INVALID)) {
            // 如果模板是免邮的，则移除该分类下所有单品运费元信息
            if (gdsShiptemp.getIfFree().equals(GdsConstants.Commons.STATUS_VALID)) {

                for (int i = gdsInfoShipmentReqDTOs.size() - 1; i >= 0; i--) {
                    GdsInfoShipmentReqDTO shipmentReqDTO = gdsInfoShipmentReqDTOs.get(i);

                    // 如果是商品分类聚合计算
                    if (shipmentReqDTO.getParentCode().equals(gdsInfoShipmentReqDTO.getCatgCode())) {
                        gdsInfoShipmentReqDTOs.remove(shipmentReqDTO);
                    }

                }
            } else {
                // 否则查看是否满足免邮条件
                // 优先匹配收货地址
                GdsShiptempPriceCriteria example = new GdsShiptempPriceCriteria();
                GdsShiptempPriceCriteria.Criteria criteria = example.createCriteria();
                criteria.andShipTemplateIdEqualTo(gdsShiptemp.getId());
                criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
                if (!StringUtils.isBlank(calInfoReqDTO.getProvinceCode())) {
                    criteria.andProvinceCodeEqualTo(calInfoReqDTO.getProvinceCode());
                }else{
                    criteria.andProvinceCodeIsNull();
                    
                }
                if (!StringUtils.isBlank(calInfoReqDTO.getCountryCode())) {
                    criteria.andCountryCodeEqualTo(calInfoReqDTO.getCountryCode());
                }else{
                    criteria.andCountryCodeIsNull();
                }
                if (!StringUtils.isBlank(calInfoReqDTO.getCityCode())) {
                    criteria.andCityCodeEqualTo(calInfoReqDTO.getCityCode());
                }else{
                    criteria.andCityCodeIsNull();
                }

                List<GdsShiptempPrice> gdsShiptempPrices = gdsShiptempPriceMapper
                        .selectByExample(example);
                if (CollectionUtils.isNotEmpty(gdsShiptempPrices)) {
                    GdsShiptempPrice gdsShiptempPrice = gdsShiptempPrices.get(0);
                    // 统计商品分类下所有商品的数量
                    Long num = 0L;
                    for (int i = gdsInfoShipmentReqDTOs.size() - 1; i >= 0; i--) {
                        GdsInfoShipmentReqDTO shipmentReqDTO = gdsInfoShipmentReqDTOs.get(i);

                        if (shipmentReqDTO.getParentCode().equals(
                                gdsInfoShipmentReqDTO.getCatgCode())) {

                            num = num
                                    + this.queryCalNumByCalRule(gdsShiptemp,
                                            shipmentReqDTO.getGdsId(), shipmentReqDTO.getCount());
                        }

                    }
                    // 如果商品达到免邮的数量，则移除属于商品分类的所有单品运费元信息
                    if (gdsShiptempPrice.getFreeAmount() <= num) {

                        for (int i = gdsInfoShipmentReqDTOs.size() - 1; i >= 0; i--) {
                            GdsInfoShipmentReqDTO shipmentReqDTO = gdsInfoShipmentReqDTOs.get(i);

                            if (shipmentReqDTO.getParentCode().equals(
                                    gdsInfoShipmentReqDTO.getCatgCode())) {

                                gdsInfoShipmentReqDTOs.remove(shipmentReqDTO);
                            }

                        }

                    }

                } else {// 判断模板默认计价记录

                    GdsShiptempPriceCriteria exampleDef = new GdsShiptempPriceCriteria();
                    GdsShiptempPriceCriteria.Criteria criteriaDef = exampleDef.createCriteria();
                    criteriaDef.andShipTemplateIdEqualTo(gdsShiptemp.getId());
                    criteriaDef.andCountryCodeIsNull();
                    criteriaDef.andProvinceCodeIsNull();
                    criteriaDef.andCityCodeIsNull();
                    criteriaDef.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);

                    List<GdsShiptempPrice> gdsShiptempPricesDef = gdsShiptempPriceMapper
                            .selectByExample(exampleDef);
                    if (CollectionUtils.isNotEmpty(gdsShiptempPricesDef)) {

                        GdsShiptempPrice gdsShiptempPrice = gdsShiptempPricesDef.get(0);
                        // 统计商品分类下所有商品的数量
                        Long num = 0L;
                        for (int i = gdsInfoShipmentReqDTOs.size() - 1; i >= 0; i--) {
                            GdsInfoShipmentReqDTO shipmentReqDTO = gdsInfoShipmentReqDTOs.get(i);

                            if (shipmentReqDTO.getParentCode().equals(
                                    gdsInfoShipmentReqDTO.getCatgCode())) {

                                num = num
                                        + this.queryCalNumByCalRule(gdsShiptemp,
                                                shipmentReqDTO.getGdsId(),
                                                shipmentReqDTO.getCount());
                            }

                        }
                        // 如果商品达到免邮的数量，则移除属于商品分类的所有单品运费元信息
                        if (gdsShiptempPrice.getFreeAmount() <= num) {

                            for (int i = gdsInfoShipmentReqDTOs.size() - 1; i >= 0; i--) {
                                GdsInfoShipmentReqDTO shipmentReqDTO = gdsInfoShipmentReqDTOs
                                        .get(i);

                                if (shipmentReqDTO.getParentCode().equals(
                                        gdsInfoShipmentReqDTO.getCatgCode())) {

                                    gdsInfoShipmentReqDTOs.remove(shipmentReqDTO);
                                }

                            }

                        }

                    }

                }
            }

        }

    }

    /**
     * 
     * dealGdsInfoByShipmentTemp:(根据运费模板计算运费). <br/>
     * 
     * @author zjh
     * @param gdsInfoShipmentReqDTOs
     * @param gdsInfoShipmentReqDTO
     * @param calInfoReqDTO
     * @param shipTempId
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    private Long dealGdsInfoByShipmentTempForGds(
            List<GdsInfoShipmentReqDTO> gdsInfoShipmentReqDTOs,
            GdsInfoShipmentReqDTO gdsInfoShipmentReqDTO, GdsShipmentCalInfoReqDTO calInfoReqDTO,
            Long shipTempId) throws BusinessException {
        if (shipTempId == -1L) {

            return 0L;
        }
        // *************************************//
        Long price = 0L;
        Long gdsId = gdsInfoShipmentReqDTO.getGdsId();
        GdsShiptemp gdsShiptemp = gdsShiptempMapper.selectByPrimaryKey(shipTempId);

        if (!gdsShiptemp.getStatus().equals(GdsConstants.Commons.STATUS_INVALID)) {

            GdsShiptempPriceCriteria example = new GdsShiptempPriceCriteria();
            GdsShiptempPriceCriteria.Criteria criteria = example.createCriteria();
            criteria.andShipTemplateIdEqualTo(gdsShiptemp.getId());
            if (!StringUtils.isBlank(calInfoReqDTO.getProvinceCode())) {
                criteria.andProvinceCodeEqualTo(calInfoReqDTO.getProvinceCode());
            }else{
                criteria.andProvinceCodeIsNull();
                
            }
            if (!StringUtils.isBlank(calInfoReqDTO.getCountryCode())) {
                criteria.andCountryCodeEqualTo(calInfoReqDTO.getCountryCode());
            }else{
                criteria.andCountryCodeIsNull();
            }
            if (!StringUtils.isBlank(calInfoReqDTO.getCityCode())) {
                criteria.andCityCodeEqualTo(calInfoReqDTO.getCityCode());
            }else{
                criteria.andCityCodeIsNull();
            }
            criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);

            List<GdsShiptempPrice> gdsShiptempPrices = gdsShiptempPriceMapper
                    .selectByExample(example);
            if (CollectionUtils.isNotEmpty(gdsShiptempPrices)) {
                GdsShiptempPrice gdsShiptempPrice = gdsShiptempPrices.get(0);
                // 计算按商品id聚合后的商品的数量
                //Long num = this.queryCalNumByCalRule(gdsShiptemp, gdsId,
                //        gdsInfoShipmentReqDTO.getCount());
                // 此时不再过滤免邮，因为之前已经判断过了
               // price = calShipPrice(gdsShiptemp, num, gdsShiptempPrice);
                // 去除计费过得单品
                for (int i = gdsInfoShipmentReqDTOs.size() - 1; i >= 0; i--) {
                    GdsInfoShipmentReqDTO shipmentReqDTO = gdsInfoShipmentReqDTOs.get(i);
                    
                    if (shipmentReqDTO.getGdsId().equals(gdsInfoShipmentReqDTO.getGdsId())) {
                        gdsInfoShipmentReqDTOs.remove(shipmentReqDTO);
                        Long num = this.queryCalNumByCalRule(gdsShiptemp, gdsId,
                               GdsConstants.GdsShiptemp.GDS_SHIPMENT_BY_MONEY.equals(gdsShiptemp.getShipTemplateType())? shipmentReqDTO.getAmount() : gdsInfoShipmentReqDTO.getCount());
                        // 此时不再过滤免邮，因为之前已经判断过了
                        price = calShipPrice(gdsShiptemp, num, gdsShiptempPrice);
                    }
                    
                  /*  if (shipmentReqDTO.getGdsId().equals(gdsInfoShipmentReqDTO.getGdsId())) {

                        gdsInfoShipmentReqDTOs.remove(shipmentReqDTO);
                    }
*/
                }

            } else {

                // 判断模板默认计价记录

                GdsShiptempPriceCriteria exampleDef = new GdsShiptempPriceCriteria();
                GdsShiptempPriceCriteria.Criteria criteriaDef = exampleDef.createCriteria();
                criteriaDef.andShipTemplateIdEqualTo(gdsShiptemp.getId());
                criteriaDef.andCountryCodeIsNull();
                criteriaDef.andProvinceCodeIsNull();
                criteriaDef.andCityCodeIsNull();
                criteriaDef.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);

                List<GdsShiptempPrice> gdsShiptempPricesDef = gdsShiptempPriceMapper
                        .selectByExample(exampleDef);
                if (CollectionUtils.isNotEmpty(gdsShiptempPricesDef)) {

                    GdsShiptempPrice gdsShiptempPrice = gdsShiptempPricesDef.get(0);

                    // 统计商品分类下所有商品的数量
                    Long num = this.queryCalNumByCalRule(gdsShiptemp, gdsId,
                            gdsInfoShipmentReqDTO.getCount());

                    // 此时不再过滤免邮，因为之前已经判断过了
                    price = calShipPrice(gdsShiptemp, num, gdsShiptempPrice);
                    // 去除计费过得单品
                    for (int i = gdsInfoShipmentReqDTOs.size() - 1; i >= 0; i--) {
                        GdsInfoShipmentReqDTO shipmentReqDTO = gdsInfoShipmentReqDTOs.get(i);

                        if (shipmentReqDTO.getGdsId().equals(gdsInfoShipmentReqDTO.getGdsId())) {

                            gdsInfoShipmentReqDTOs.remove(shipmentReqDTO);
                        }

                    }

                } else {

                    // 没有默认抛出异常
                    throw new BusinessException(
                            GdsErrorConstants.GdsShipMent.ERROR_GOODS_SHIP_MENT_240002,
                            new String[] { String.valueOf(gdsShiptemp.getId()) });

                }

            }

        }
        return price;
    }

    private Long dealGdsInfoByShipmentTempForCatgCode(
            List<GdsInfoShipmentReqDTO> gdsInfoShipmentReqDTOs,
            GdsInfoShipmentReqDTO gdsInfoShipmentReqDTO, GdsShipmentCalInfoReqDTO calInfoReqDTO,
            Long shipTempId) throws BusinessException {

        // *************************************//
        Long price = 0L;
        GdsShiptemp gdsShiptemp = gdsShiptempMapper.selectByPrimaryKey(shipTempId);

        if (!gdsShiptemp.getStatus().equals(GdsConstants.Commons.STATUS_INVALID)) {

            GdsShiptempPriceCriteria example = new GdsShiptempPriceCriteria();
            GdsShiptempPriceCriteria.Criteria criteria = example.createCriteria();
            criteria.andShipTemplateIdEqualTo(gdsShiptemp.getId());
            if (!StringUtils.isBlank(calInfoReqDTO.getProvinceCode())) {
                criteria.andProvinceCodeEqualTo(calInfoReqDTO.getProvinceCode());
            }else{
                criteria.andProvinceCodeIsNull();
            }
            if (!StringUtils.isBlank(calInfoReqDTO.getCountryCode())) {
                criteria.andCountryCodeEqualTo(calInfoReqDTO.getCountryCode());
            }else{
                criteria.andCountryCodeIsNull();
            }
            if (!StringUtils.isBlank(calInfoReqDTO.getCityCode())) {
                criteria.andCityCodeEqualTo(calInfoReqDTO.getCityCode());
            }else{
                criteria.andCityCodeIsNull();
            }
            criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);

            List<GdsShiptempPrice> gdsShiptempPrices = gdsShiptempPriceMapper
                    .selectByExample(example);
            if (CollectionUtils.isNotEmpty(gdsShiptempPrices) ) {
                GdsShiptempPrice gdsShiptempPrice = gdsShiptempPrices.get(0);
                // 统计商品分类下所有商品的数量
                Long num = 0L;
                for (int i = gdsInfoShipmentReqDTOs.size() - 1; i >= 0; i--) {
                    GdsInfoShipmentReqDTO shipmentReqDTO = gdsInfoShipmentReqDTOs.get(i);
                    if (shipmentReqDTO.getParentCode().equals(gdsInfoShipmentReqDTO.getCatgCode())) {
                        num = num
                                + this.queryCalNumByCalRule(gdsShiptemp, shipmentReqDTO.getGdsId(),
                                        shipmentReqDTO.getCount());

                    }

                }
                price = calShipPrice(gdsShiptemp, num, gdsShiptempPrice);
                // 去除计费过得单品
                for (int i = gdsInfoShipmentReqDTOs.size() - 1; i >= 0; i--) {
                    GdsInfoShipmentReqDTO shipmentReqDTO = gdsInfoShipmentReqDTOs.get(i);

                    if (shipmentReqDTO.getParentCode().equals(gdsInfoShipmentReqDTO.getCatgCode())) {

                        gdsInfoShipmentReqDTOs.remove(shipmentReqDTO);
                    }

                }

            } else {

                // 判断模板默认计价记录

                GdsShiptempPriceCriteria exampleDef = new GdsShiptempPriceCriteria();
                GdsShiptempPriceCriteria.Criteria criteriaDef = exampleDef.createCriteria();
                criteriaDef.andShipTemplateIdEqualTo(gdsShiptemp.getId());
                criteriaDef.andCountryCodeIsNull();
                criteriaDef.andProvinceCodeIsNull();
                criteriaDef.andCityCodeIsNull();
                criteriaDef.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);

                List<GdsShiptempPrice> gdsShiptempPricesDef = gdsShiptempPriceMapper
                        .selectByExample(exampleDef);
                if (CollectionUtils.isNotEmpty(gdsShiptempPricesDef)) {

                    GdsShiptempPrice gdsShiptempPrice = gdsShiptempPricesDef.get(0);

                    // 统计商品分类下所有商品的数量
                    Long num = 0L;
                    for (int i = gdsInfoShipmentReqDTOs.size() - 1; i >= 0; i--) {
                        GdsInfoShipmentReqDTO shipmentReqDTO = gdsInfoShipmentReqDTOs.get(i);
                        // 统计属于当前分类
                        if (shipmentReqDTO.getParentCode().equals(
                                gdsInfoShipmentReqDTO.getCatgCode())) {
                            num = num
                                    + this.queryCalNumByCalRule(gdsShiptemp,
                                            shipmentReqDTO.getGdsId(), shipmentReqDTO.getCount());

                        }

                    }
                    // 此时不再过滤免邮，因为之前已经判断过了
                    price = calShipPrice(gdsShiptemp, num, gdsShiptempPrice);
                    // 去除计费过得单品
                    for (int i = gdsInfoShipmentReqDTOs.size() - 1; i >= 0; i--) {
                        GdsInfoShipmentReqDTO shipmentReqDTO = gdsInfoShipmentReqDTOs.get(i);

                        if (shipmentReqDTO.getParentCode().equals(
                                gdsInfoShipmentReqDTO.getCatgCode())) {

                            gdsInfoShipmentReqDTOs.remove(shipmentReqDTO);
                        }

                    }

                } else {

                    // 没有默认抛出异常
                    throw new BusinessException(
                            GdsErrorConstants.GdsShipMent.ERROR_GOODS_SHIP_MENT_240002,
                            new String[] { String.valueOf(gdsShiptemp.getId()) });

                }

            }

        }
        return price;
    }

    private Long dealGdsInfoByShipmentTempForShop(
            List<GdsInfoShipmentReqDTO> gdsInfoShipmentReqDTOs,
            GdsShipmentCalInfoReqDTO calInfoReqDTO, Long shipTempId) throws BusinessException {

        // *************************************//
        Long price = 0L;
        GdsShiptemp gdsShiptemp = gdsShiptempMapper.selectByPrimaryKey(shipTempId);

        if (!gdsShiptemp.getStatus().equals(GdsConstants.Commons.STATUS_INVALID)) {
            // 按地区匹配计价记录
            GdsShiptempPriceCriteria example = new GdsShiptempPriceCriteria();
            GdsShiptempPriceCriteria.Criteria criteria = example.createCriteria();
            criteria.andShipTemplateIdEqualTo(gdsShiptemp.getId());
            if (!StringUtils.isBlank(calInfoReqDTO.getProvinceCode())) {
                criteria.andProvinceCodeEqualTo(calInfoReqDTO.getProvinceCode());
            }else{
                criteria.andProvinceCodeIsNull();
            }
            if (!StringUtils.isBlank(calInfoReqDTO.getCountryCode())) {
                criteria.andCountryCodeEqualTo(calInfoReqDTO.getCountryCode());
            }else{
                criteria.andCountryCodeIsNull();
            }
            if (!StringUtils.isBlank(calInfoReqDTO.getCityCode())) {
                criteria.andCityCodeEqualTo(calInfoReqDTO.getCityCode());
            }else{
                criteria.andCityCodeIsNull();
            }
            criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);

            List<GdsShiptempPrice> gdsShiptempPrices = gdsShiptempPriceMapper
                    .selectByExample(example);
            if (CollectionUtils.isNotEmpty(gdsShiptempPrices)) {
                GdsShiptempPrice gdsShiptempPrice = gdsShiptempPrices.get(0);
                // 计算按店铺聚合后的商品的数量
                Long num = 0L;
                // 如果不是按金额计费，则统计运费的计费数量
                if (!GdsConstants.GdsShiptemp.GDS_SHIPMENT_BY_MONEY.equals(gdsShiptemp
                        .getShipTemplateType())) {
                    for (int i = gdsInfoShipmentReqDTOs.size() - 1; i >= 0; i--) {
                        GdsInfoShipmentReqDTO shipmentReqDTO = gdsInfoShipmentReqDTOs.get(i);

                        num = num+ this.queryCalNumByCalRule(gdsShiptemp, shipmentReqDTO.getGdsId(),
                                        shipmentReqDTO.getCount());

                    }

                } else {
                    // 如果是按金额计费，则将金额作为计费的金额,
                    for (int i = gdsInfoShipmentReqDTOs.size() - 1; i >= 0; i--) {
                        GdsInfoShipmentReqDTO shipmentReqDTO = gdsInfoShipmentReqDTOs.get(i);
                        num = num + shipmentReqDTO.getAmount();
                    }
                }

                // 此时不再过滤免邮，因为之前已经判断过了
                price = calShipPrice(gdsShiptemp, num, gdsShiptempPrice);

                gdsInfoShipmentReqDTOs.clear();

            } else {

                // 判断模板默认计价记录

                GdsShiptempPriceCriteria exampleDef = new GdsShiptempPriceCriteria();
                GdsShiptempPriceCriteria.Criteria criteriaDef = exampleDef.createCriteria();
                criteriaDef.andShipTemplateIdEqualTo(gdsShiptemp.getId());
                criteriaDef.andCountryCodeIsNull();
                criteriaDef.andProvinceCodeIsNull();
                criteriaDef.andCityCodeIsNull();
                criteriaDef.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);

                List<GdsShiptempPrice> gdsShiptempPricesDef = gdsShiptempPriceMapper
                        .selectByExample(exampleDef);
                if (CollectionUtils.isNotEmpty(gdsShiptempPricesDef)) {

                    GdsShiptempPrice gdsShiptempPrice = gdsShiptempPricesDef.get(0);
                    Long num = 0L;
                    // 如果不是按金额计费，则统计运费的计费数量
                    if (!GdsConstants.GdsShiptemp.GDS_SHIPMENT_BY_MONEY.equals(gdsShiptemp
                            .getShipTemplateType())) {
                        for (int i = gdsInfoShipmentReqDTOs.size() - 1; i >= 0; i--) {
                            GdsInfoShipmentReqDTO shipmentReqDTO = gdsInfoShipmentReqDTOs.get(i);

                            num = num
                                    + this.queryCalNumByCalRule(gdsShiptemp,
                                            shipmentReqDTO.getGdsId(), shipmentReqDTO.getCount());

                        }

                    } else {

                        // 如果是按金额计费，则将金额作为计费的金额,
                        for (int i = gdsInfoShipmentReqDTOs.size() - 1; i >= 0; i--) {
                            GdsInfoShipmentReqDTO shipmentReqDTO = gdsInfoShipmentReqDTOs.get(i);
                            num = num + shipmentReqDTO.getAmount();
                        }
                    }

                    // 此时不再过滤免邮，因为之前已经判断过了
                    price = calShipPrice(gdsShiptemp, num, gdsShiptempPrice);
                    // 去除计费过得单品
                    gdsInfoShipmentReqDTOs.clear();

                } else {

                    // 没有默认抛出异常
                    throw new BusinessException(
                            GdsErrorConstants.GdsShipMent.ERROR_GOODS_SHIP_MENT_240002,
                            new String[] { String.valueOf(gdsShiptemp.getId()) });

                }

            }

        }
        return price;
    }

    /**
     * 
     * calShipPrice:(根据运费计价表计算运费). <br/>
     * 
     * @author zjh
     * @param gdsShiptemp
     * @param num
     * @param gdsShiptempPrice
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    private Long calShipPrice(GdsShiptemp gdsShiptemp, Long num, GdsShiptempPrice gdsShiptempPrice)
            throws BusinessException {
        Long price = 0L;

        if (num > gdsShiptempPrice.getFirstAmount()) {
            price = (long) (gdsShiptempPrice.getFirstPrice() + (Math
                    .ceil((double) (num - gdsShiptempPrice.getFirstAmount())
                            / (double) gdsShiptempPrice.getContinueAmount()))
                    * gdsShiptempPrice.getContinuePrice());

        } else {
            price = gdsShiptempPrice.getFirstPrice();
        }

        return price;
    }

    /**
     * 
     * ifFreeCatgCode:(判断该分类是否存在免邮的运费模板). <br/>
     * 
     * @author zjh
     * @param catgCode
     * @param ShopId
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    private void delByUpTraceCatgCode(List<GdsInfoShipmentReqDTO> gdsInfoShipmentReqDTOs,
            GdsInfoShipmentReqDTO gdsInfoShipmentReqDTO, GdsShipmentCalInfoReqDTO calInfoReqDTO)
            throws BusinessException {
        // 获取顶级分类的运费模板
        GdsShiptempCriteria example = new GdsShiptempCriteria();
        GdsShiptempCriteria.Criteria criteria = example.createCriteria();
        criteria.andCatgCodeEqualTo(gdsInfoShipmentReqDTO.getCatgCode());
        criteria.andShopIdEqualTo(calInfoReqDTO.getShopId());
        criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        List<GdsShiptemp> gdsShiptemps = gdsShiptempMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(gdsShiptemps)) {
            GdsShiptemp gdsShiptemp = gdsShiptemps.get(0);
            // 过滤分类免邮或者满足免邮条件的
            this.delGdsInfoByShipmentTempForCatgCode(gdsInfoShipmentReqDTOs, gdsInfoShipmentReqDTO,
                    calInfoReqDTO, gdsShiptemp.getId());
        }

    }

    /**
     * 
     * chooseShipTempByCatgCode:(根据分类获取最近父节点的运费模板). <br/>
     * 
     * @author zjh
     * @param catgCode
     * @param ShopId
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    private GdsShiptempShopIdx chooseShipTempByCatgCode(String catgCode, Long ShopId)
            throws BusinessException {
        // // 从父到子排序。默认取出来值
        // List<GdsCategoryRespDTO> categoryRespDTOs = gdsCategorySV
        // .queryCategoryTraceUpon(catgCode);
        // // 倒置
        // for (int i = categoryRespDTOs.size() - 1; i >= 0; i--) {
        // GdsCategoryRespDTO categoryRespDTO = categoryRespDTOs.get(i);
        GdsShiptempShopIdxCriteria example = new GdsShiptempShopIdxCriteria();
        GdsShiptempShopIdxCriteria.Criteria criteria = example.createCriteria();
        criteria.andCatgCodeEqualTo(catgCode);
        criteria.andShopIdEqualTo(ShopId);
        criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        List<GdsShiptempShopIdx> gdsShiptemps = gdsShiptempShopIdxMapper.selectByExample(example);
        if (gdsShiptemps != null && gdsShiptemps.size() > 0) {
            GdsShiptempShopIdx gdsShiptemp = gdsShiptemps.get(0);
            return gdsShiptemp;
        } else {
            return null;
        }

        // }

    }

    protected List<GdsInfoShipmentReqDTO> delGdsShipInfoGroupByGdsId(
            List<GdsInfoShipmentReqDTO> gdsInfoShipmentReqDTOs) throws BusinessException {

        List<GdsInfoShipmentReqDTO> gdsInfoShipByGds = new ArrayList<GdsInfoShipmentReqDTO>();
        Set<Long> gdsIds = new HashSet<Long>();
        // 汇总所有gdsId
        for (int i = gdsInfoShipmentReqDTOs.size() - 1; i >= 0; i--) {
            GdsInfoShipmentReqDTO gdsInfoShipmentReqDTO = gdsInfoShipmentReqDTOs.get(i);
            gdsIds.add(gdsInfoShipmentReqDTO.getGdsId());
        }
        // 统计此批商品各商品的数量
        for (Long gdsId : gdsIds) {
            GdsInfoShipmentReqDTO gdsInfoShipmentReqDTO = new GdsInfoShipmentReqDTO();
            gdsInfoShipmentReqDTO.setCount(0L);
            gdsInfoShipmentReqDTO.setGdsId(gdsId);
            for (GdsInfoShipmentReqDTO shipmentReqDTO : gdsInfoShipmentReqDTOs) {
                if (shipmentReqDTO.getGdsId() == null || 0L == shipmentReqDTO.getGdsId()) {

                    throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,
                            new String[] { "gdsId" });

                }
                if (shipmentReqDTO.getGdsId().equals(gdsId)) {
                    gdsInfoShipmentReqDTO.setCount(gdsInfoShipmentReqDTO.getCount()
                            + shipmentReqDTO.getCount());

                }
            }
            gdsInfoShipByGds.add(gdsInfoShipmentReqDTO);
        }
        return gdsInfoShipByGds;
    }

    /**
     * 按照商品所属顶级平台分类聚合
     * 
     * @param gdsInfoShipmentReqDTOs
     * @return
     * @throws BusinessException
     */
    protected List<GdsInfoShipmentReqDTO> delGdsShipInfoGroupByCatgCode(
            List<GdsInfoShipmentReqDTO> gdsInfoShipmentReqDTOs) throws BusinessException {
        for (int i = gdsInfoShipmentReqDTOs.size() - 1; i >= 0; i--) {
            GdsInfoShipmentReqDTO gdsInfoShipmentReqDTO = gdsInfoShipmentReqDTOs.get(i);

            if (gdsInfoShipmentReqDTO.getCatgCode() == null
                    || "".equals(gdsInfoShipmentReqDTO.getCatgCode())) {

                throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,
                        new String[] { "catgCode" });

            }

            // 获取所属顶级平台分类
            GdsCategoryRespDTO categoryRespDTO = gdsCategorySV
                    .queryRootCategoryByPK(gdsInfoShipmentReqDTO.getCatgCode());
            if(categoryRespDTO!=null){
            	gdsInfoShipmentReqDTO.setParentCode(categoryRespDTO.getCatgCode());
            }

        }
        Set<String> catgCodes = new HashSet<String>();
        // 汇总所有顶级分类id
        for (GdsInfoShipmentReqDTO gdsInfoShipmentReqDTO : gdsInfoShipmentReqDTOs) {

            catgCodes.add(gdsInfoShipmentReqDTO.getParentCode());

        }

        List<GdsInfoShipmentReqDTO> gdsInfoShipByCatgCode = new ArrayList<GdsInfoShipmentReqDTO>();
        // 封装顶级分类列表
        for (String catgCode : catgCodes) {
            GdsInfoShipmentReqDTO gdsInfoShipmentReqDTO = new GdsInfoShipmentReqDTO();
            // gdsInfoShipmentReqDTO.setCount(0L);
            gdsInfoShipmentReqDTO.setCatgCode(catgCode);
            // for (GdsInfoShipmentReqDTO shipmentReqDTO :
            // gdsInfoShipmentReqDTOs) {
            //
            // if (shipmentReqDTO.getParentCode().equals(catgCode)) {
            // gdsInfoShipmentReqDTO.setCount(gdsInfoShipmentReqDTO
            // .getCount() + shipmentReqDTO.getCount());
            //
            // }
            // }
            gdsInfoShipByCatgCode.add(gdsInfoShipmentReqDTO);
        }

        return gdsInfoShipByCatgCode;
    }

    /**
     * 过滤虚拟商品类型的单品
     * 
     * @param gdsInfoShipmentReqDTOs
     * @return
     * @throws BusinessException
     */
    protected List<GdsInfoShipmentReqDTO> delGdsInfosByTypeId(
            List<GdsInfoShipmentReqDTO> gdsInfoShipmentReqDTOs) {

        for (int i = gdsInfoShipmentReqDTOs.size() - 1; i >= 0; i--) {
            GdsInfoShipmentReqDTO gdsInfoShipmentReqDTO = gdsInfoShipmentReqDTOs.get(i);
            if (gdsInfoExternalSV.isGdsTypeFreightFree(gdsInfoShipmentReqDTO.getTypeId())) {

                gdsInfoShipmentReqDTOs.remove(gdsInfoShipmentReqDTO);

            }

        }
        return gdsInfoShipmentReqDTOs;
    }

    /**
     * 过滤免邮商品的单品
     *
     * @param gdsInfoShipmentReqDTOs
     * @return
     * @throws BusinessException
     */
    protected List<GdsInfoShipmentReqDTO> delGdsInfosFree(
            List<GdsInfoShipmentReqDTO> gdsInfoShipmentReqDTOs) {

        for (int i = gdsInfoShipmentReqDTOs.size() - 1; i >= 0; i--) {
            GdsInfoShipmentReqDTO gdsInfoShipmentReqDTO = gdsInfoShipmentReqDTOs.get(i);

            GdsInfo info=gdsInfoMapper.selectByPrimaryKey(gdsInfoShipmentReqDTO.getGdsId());

            if (StringUtils.equals(info.getIfFree(),GdsConstants.Commons.STATUS_VALID)) {

                gdsInfoShipmentReqDTOs.remove(gdsInfoShipmentReqDTO);

            }

        }
        return gdsInfoShipmentReqDTOs;
    }

    @Override
    public void addGdsShop2Shiptemp(GdsShop2ShipmentReqDTO gdsShop2ShipmentReqDTO) throws Exception {
        GdsShop2Shiptemp gdsShop2Shiptemp = new GdsShop2Shiptemp();
        gdsShop2Shiptemp.setCreateStaff(gdsShop2ShipmentReqDTO.getStaff().getId());
        gdsShop2Shiptemp.setCreateTime(new Timestamp(System.currentTimeMillis()));
        gdsShop2Shiptemp.setShipTemplateId(gdsShop2ShipmentReqDTO.getShipmentId());
        gdsShop2Shiptemp.setShopId(gdsShop2ShipmentReqDTO.getShopId());
        gdsShop2Shiptemp.setStatus(GdsConstants.Commons.STATUS_VALID);
        gdsShop2Shiptemp.setType("1");
        gdsShop2ShiptempMapper.insertSelective(gdsShop2Shiptemp);

    }

    @Override
    public void editGdsShop2Shiptemp(GdsShop2ShipmentReqDTO gdsShop2ShipmentReqDTO)
            throws Exception {
        GdsShop2ShiptempCriteria gdsShop2ShiptempCriteria = new GdsShop2ShiptempCriteria();
        GdsShop2ShiptempCriteria.Criteria criteria = gdsShop2ShiptempCriteria.createCriteria();
        criteria.andShopIdEqualTo(gdsShop2ShipmentReqDTO.getShopId());
        List<GdsShop2Shiptemp> gdsShop2Shiptemps = gdsShop2ShiptempMapper
                .selectByExample(gdsShop2ShiptempCriteria);
        if (gdsShop2Shiptemps != null) {
            // 失效所有店铺的运费模板关系记录
            for (GdsShop2Shiptemp gdsShop2Shiptemp : gdsShop2Shiptemps) {
                gdsShop2Shiptemp.setStatus(GdsConstants.Commons.STATUS_INVALID);
                GdsShop2ShiptempCriteria example = new GdsShop2ShiptempCriteria();
                GdsShop2ShiptempCriteria.Criteria invCriteria = example.createCriteria();
                invCriteria.andShopIdEqualTo(gdsShop2ShipmentReqDTO.getShopId());
                invCriteria.andShipTemplateIdEqualTo(gdsShop2Shiptemp.getShipTemplateId());

                gdsShop2ShiptempMapper.updateByExampleSelective(gdsShop2Shiptemp, example);

            }

        }
        this.addGdsShop2Shiptemp(gdsShop2ShipmentReqDTO);

    }

    @Override
    public GdsShiptempRespDTO queryShopDefaultShipMent(GdsShop2ShipmentReqDTO gdsShop2ShipmentReqDTO)
            throws Exception {
        GdsShop2ShiptempCriteria gdsShop2ShiptempCriteria = new GdsShop2ShiptempCriteria();
        GdsShop2ShiptempCriteria.Criteria criteria = gdsShop2ShiptempCriteria.createCriteria();
        criteria.andShopIdEqualTo(gdsShop2ShipmentReqDTO.getShopId());
        criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        List<GdsShop2Shiptemp> gdsShop2Shiptemps = gdsShop2ShiptempMapper
                .selectByExample(gdsShop2ShiptempCriteria);
        if (CollectionUtils.isEmpty(gdsShop2Shiptemps)) {
            throw new BusinessException(GdsErrorConstants.GdsShipMent.ERROR_GOODS_SHIP_MENT_240001);
        } else {
            GdsShop2Shiptemp gdsShop2Shiptemp = gdsShop2Shiptemps.get(0);
            GdsShiptemp gdsShiptemp = gdsShiptempMapper.selectByPrimaryKey(gdsShop2Shiptemp
                    .getShipTemplateId());
            GdsShiptempRespDTO gdsShiptempRespDTO = new GdsShiptempRespDTO();
            ObjectCopyUtil.copyObjValue(gdsShiptemp, gdsShiptempRespDTO, null, false);
            return gdsShiptempRespDTO;
        }

    }

    @Override
    public void addShopDefaultShipMent(GdsShiptempReqDTO reqDTO) throws Exception {
        Long templateId = this.saveGdsShipTemp(reqDTO);
        GdsShop2ShipmentReqDTO gdsShop2ShipmentReqDTO = new GdsShop2ShipmentReqDTO();
        gdsShop2ShipmentReqDTO.setShipmentId(templateId);
        gdsShop2ShipmentReqDTO.setShopId(reqDTO.getShopId());
        gdsShop2ShipmentReqDTO.setStaff(reqDTO.getStaff());
        this.addGdsShop2Shiptemp(gdsShop2ShipmentReqDTO);

    }
}
