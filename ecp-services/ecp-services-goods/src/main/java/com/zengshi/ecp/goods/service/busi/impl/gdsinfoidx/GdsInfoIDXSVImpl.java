package com.zengshi.ecp.goods.service.busi.impl.gdsinfoidx;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.goods.dao.mapper.busi.GdsGds2CatgCatgIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsGds2PropMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsGds2PropPropIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsInfoShopIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsMediaShopIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsSku2PropMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsSku2PropPropIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsSkuInfoGdsIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsSkuInfoShopIdxMapper;
import com.zengshi.ecp.goods.dao.model.GdsGds2CatgCatgIdx;
import com.zengshi.ecp.goods.dao.model.GdsGds2CatgCatgIdxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsGds2PropPropIdx;
import com.zengshi.ecp.goods.dao.model.GdsGds2PropPropIdxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsInfo;
import com.zengshi.ecp.goods.dao.model.GdsInfoShopIdx;
import com.zengshi.ecp.goods.dao.model.GdsInfoShopIdxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsMedia;
import com.zengshi.ecp.goods.dao.model.GdsMediaShopIdx;
import com.zengshi.ecp.goods.dao.model.GdsMediaShopIdxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsSku2Prop;
import com.zengshi.ecp.goods.dao.model.GdsSku2PropCriteria;
import com.zengshi.ecp.goods.dao.model.GdsSku2PropPropIdx;
import com.zengshi.ecp.goods.dao.model.GdsSku2PropPropIdxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfo;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfoGdsIdx;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfoGdsIdxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfoShopIdx;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfoShopIdxCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsGds2CatgCatgIdxReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2CatgReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PropReqDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfoidx.IGdsInfoIDXSV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 商品单品索引表操作SV<br>
 * Date:2015年8月25日下午8:27:34 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsInfoIDXSVImpl extends AbstractSVImpl implements IGdsInfoIDXSV {

    @Resource
    private GdsInfoShopIdxMapper gdsInfoShopIdxMapper;

    @Resource
    private GdsSkuInfoGdsIdxMapper gdsSkuInfoGdsIdxMapper;

    @Resource
    private GdsSkuInfoShopIdxMapper gdsSkuInfoShopIdxMapper;

    @Resource
    private GdsGds2CatgCatgIdxMapper gds2CatgCatgIdxMapper;

    @Resource
    private GdsGds2PropPropIdxMapper gds2PropPropIdxMapper;

    @Resource
    private GdsSku2PropPropIdxMapper sku2PropPropIdxMapper;

    @Resource
    private GdsMediaShopIdxMapper gdsMediaShopIdxMapper;

    /**
     * 单品属性关系表操作Mapper
     */
    @Resource
    private GdsSku2PropMapper sku2PropMapper;

    /**
     * 商品属性关系表操作Mapper
     */
    @Resource
    private GdsGds2PropMapper gds2PropMapper;

    /**
     * 
     * TODO 添加商品索引表信息.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfoidx.IGdsInfoIDXSV#addGdsInfoIDX(com.zengshi.ecp.goods.dao.model.GdsInfo)
     */
    @Override
    public void addGdsInfoIDX(GdsInfo gdsInfo, List<GdsGds2CatgReqDTO> catgs, List<GdsGds2PropReqDTO> props) throws BusinessException {
        if (gdsInfo != null) {
            // 保存商品店铺维度索引表
            GdsInfoShopIdx shopIdx = new GdsInfoShopIdx();
            shopIdx.setGdsId(gdsInfo.getId());
            ObjectCopyUtil.copyObjValue(gdsInfo, shopIdx, null, false);
            gdsInfoShopIdxMapper.insertSelective(shopIdx);
        }

        // 新增商品分类维度索引表
        if (!CollectionUtils.isEmpty(catgs)) {
            for (GdsGds2CatgReqDTO gdsGds2CatgReqDTO : catgs) {
                GdsGds2CatgCatgIdx gds2CatgCatgIdx = new GdsGds2CatgCatgIdx();
                gds2CatgCatgIdx.setGdsId(gdsInfo.getId());
                gds2CatgCatgIdx.setStatus(GdsConstants.Commons.STATUS_VALID);
                ObjectCopyUtil.copyObjValue(gdsGds2CatgReqDTO, gds2CatgCatgIdx, null, false);
                
                Long staffId=gdsGds2CatgReqDTO.getStaff().getId();
                gds2CatgCatgIdx.setCreateStaff(staffId);
                gds2CatgCatgIdx.setCreateTime(DateUtil.getSysDate());
                gds2CatgCatgIdx.setUpdateStaff(staffId);
                gds2CatgCatgIdx.setUpdateTime(DateUtil.getSysDate());
                gds2CatgCatgIdxMapper.insertSelective(gds2CatgCatgIdx);
            }
        }

        // 新增商品属性关系表属性维度索引表
        if (!CollectionUtils.isEmpty(props)) {
            for (GdsGds2PropReqDTO gdsGds2PropReqDTO : props) {
                GdsGds2PropPropIdx gds2PropPropIdx = new GdsGds2PropPropIdx();
                gds2PropPropIdx.setGdsId(gdsGds2PropReqDTO.getGdsId());
                gds2PropPropIdx.setStatus(GdsConstants.Commons.STATUS_VALID);
                if (gdsInfo != null && StringUtil.isNotBlank(gdsInfo.getGdsStatus())) {
                    gds2PropPropIdx.setGdsStatus(gdsInfo.getGdsStatus());
                }
                ObjectCopyUtil.copyObjValue(gdsGds2PropReqDTO, gds2PropPropIdx, null, false);
                Long staffId=gdsGds2PropReqDTO.getStaff().getId();
                gds2PropPropIdx.setCreateStaff(staffId);
                gds2PropPropIdx.setCreateTime(DateUtil.getSysDate());
                gds2PropPropIdx.setUpdateStaff(staffId);
                gds2PropPropIdx.setUpdateTime(DateUtil.getSysDate());
                gds2PropPropIdxMapper.insertSelective(gds2PropPropIdx);
            }
        }
    }

    /**
     * 
     * TODO 删除商品索引表信息.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfoidx.IGdsInfoIDXSV#delGdsInfoIDX(com.zengshi.ecp.goods.dao.model.GdsInfo)
     */
    @Override
    public void delGdsInfoIDX(GdsInfo gdsInfo, List<GdsGds2CatgReqDTO> catgs, List<GdsGds2PropReqDTO> props)  throws BusinessException{
        if (gdsInfo != null) {
            // 删除商品店铺维度索引表记录
            GdsInfoShopIdxCriteria criteria = new GdsInfoShopIdxCriteria();
            criteria.createCriteria().andShopIdEqualTo(gdsInfo.getShopId()).andGdsIdEqualTo(gdsInfo.getId());
            gdsInfoShopIdxMapper.deleteByExample(criteria);
        }

        if (!CollectionUtils.isEmpty(catgs)) {
            for (GdsGds2CatgReqDTO gdsGds2CatgReqDTO : catgs) {
                String catgCode = gdsGds2CatgReqDTO.getCatgCode();
                Long gdsId = gdsGds2CatgReqDTO.getGdsId();
                GdsGds2CatgCatgIdxCriteria gds2CatgCatgIdxCriteria = new GdsGds2CatgCatgIdxCriteria();
                gds2CatgCatgIdxCriteria.createCriteria().andCatgCodeEqualTo(catgCode).andGdsIdEqualTo(gdsId);
                gds2CatgCatgIdxMapper.deleteByExample(gds2CatgCatgIdxCriteria);
            }
        }

        if (!CollectionUtils.isEmpty(props)) {
            for (GdsGds2PropReqDTO gdsGds2PropReqDTO : props) {
                GdsGds2PropPropIdxCriteria gds2PropPropIdxCriteria = new GdsGds2PropPropIdxCriteria();
                GdsGds2PropPropIdxCriteria.Criteria propCriteria = gds2PropPropIdxCriteria.createCriteria();
                propCriteria.andGdsIdEqualTo(gdsGds2PropReqDTO.getGdsId());
                propCriteria.andPropIdEqualTo(gdsGds2PropReqDTO.getPropId());
                propCriteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
                if (StringUtil.isNotBlank(gdsGds2PropReqDTO.getPropValue())) {
                    propCriteria.andPropValueEqualTo(gdsGds2PropReqDTO.getPropValue());
                } else {
                    propCriteria.andPropValueIsNull();
                }
                gds2PropPropIdxMapper.deleteByExample(gds2PropPropIdxCriteria);
            }
        }

    }

    /**
     * 
     * TODO 编辑商品索引表信息.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfoidx.IGdsInfoIDXSV#editGdsInfoIDX(com.zengshi.ecp.goods.dao.model.GdsInfo)
     */
    @Override
    public void editGdsInfoIDX(GdsInfo gdsInfo, List<GdsGds2CatgReqDTO> catgs, List<GdsGds2PropReqDTO> props) throws BusinessException {
        // 编辑商品店铺维度索引表记录
        if (gdsInfo != null) {
            GdsInfoShopIdxCriteria criteria = new GdsInfoShopIdxCriteria();
            criteria.createCriteria().andShopIdEqualTo(gdsInfo.getShopId()).andGdsIdEqualTo(gdsInfo.getId());
            GdsInfoShopIdx shopIdx = new GdsInfoShopIdx();
            ObjectCopyUtil.copyObjValue(gdsInfo, shopIdx, null, true);
            gdsInfoShopIdxMapper.updateByExampleSelective(shopIdx, criteria);
        }

        if (!CollectionUtils.isEmpty(catgs)) {
            for (GdsGds2CatgReqDTO gdsGds2CatgReqDTO : catgs) {
                String catgCode = gdsGds2CatgReqDTO.getCatgCode();
                Long gdsId = gdsGds2CatgReqDTO.getGdsId();
                GdsGds2CatgCatgIdxCriteria gds2CatgCatgIdxCriteria = new GdsGds2CatgCatgIdxCriteria();
                gds2CatgCatgIdxCriteria.createCriteria().andCatgCodeEqualTo(catgCode).andGdsIdEqualTo(gdsId);
                gds2CatgCatgIdxMapper.deleteByExample(gds2CatgCatgIdxCriteria);

                GdsGds2CatgCatgIdx gds2CatgCatgIdx = new GdsGds2CatgCatgIdx();
                gds2CatgCatgIdx.setGdsId(gdsId);
                gds2CatgCatgIdx.setStatus(GdsConstants.Commons.STATUS_VALID);
                ObjectCopyUtil.copyObjValue(gdsGds2CatgReqDTO, gds2CatgCatgIdx, null, false);
                Long staffId=gdsGds2CatgReqDTO.getStaff().getId();
                gds2CatgCatgIdx.setCreateStaff(staffId);
                gds2CatgCatgIdx.setCreateTime(DateUtil.getSysDate());
                gds2CatgCatgIdx.setUpdateStaff(staffId);
                gds2CatgCatgIdx.setUpdateTime(DateUtil.getSysDate());
                gds2CatgCatgIdxMapper.insertSelective(gds2CatgCatgIdx);
            }
        }

        if (!CollectionUtils.isEmpty(props)) {
            // 删除属性索引表
            GdsGds2PropPropIdxCriteria gds2PropExam = new GdsGds2PropPropIdxCriteria();
            GdsGds2PropPropIdxCriteria.Criteria gds2PropCriteria = gds2PropExam.createCriteria();
            gds2PropCriteria.andGdsIdEqualTo(props.get(0).getGdsId());
            gds2PropCriteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
            gds2PropPropIdxMapper.deleteByExample(gds2PropExam);

            for (GdsGds2PropReqDTO gdsGds2PropReqDTO : props) {
                GdsGds2PropPropIdx gds2PropPropIdx = new GdsGds2PropPropIdx();
                gds2PropPropIdx.setGdsId(gdsGds2PropReqDTO.getGdsId());
                gds2PropPropIdx.setStatus(GdsConstants.Commons.STATUS_VALID);
                ObjectCopyUtil.copyObjValue(gdsGds2PropReqDTO, gds2PropPropIdx, null, false);
                Long staffId=gdsGds2PropReqDTO.getStaff().getId();
                gds2PropPropIdx.setCreateStaff(staffId);
                gds2PropPropIdx.setCreateTime(DateUtil.getSysDate());
                gds2PropPropIdx.setUpdateStaff(staffId);
                gds2PropPropIdx.setUpdateTime(DateUtil.getSysDate());
                gds2PropPropIdxMapper.insertSelective(gds2PropPropIdx);
            }
        }
    }

    /**
     * 
     * TODO 添加单品索引表信息.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfoidx.IGdsInfoIDXSV#addSkuInfoIDX(com.zengshi.ecp.goods.dao.model.GdsSkuInfo)
     */
    @Override
    public void addSkuInfoIDX(GdsSkuInfo skuInfo, List<GdsSku2PropReqDTO> props) throws BusinessException {

        if (skuInfo != null) {
            // 新增商品维度索引表记录
            GdsSkuInfoGdsIdx skuInfoGdsIdx = new GdsSkuInfoGdsIdx();
            skuInfoGdsIdx.setSkuId(skuInfo.getId());
            ObjectCopyUtil.copyObjValue(skuInfo, skuInfoGdsIdx, null, false);
            gdsSkuInfoGdsIdxMapper.insertSelective(skuInfoGdsIdx);

            // 新增店铺维度索引表记录
            GdsSkuInfoShopIdx skuInfoShopIdx = new GdsSkuInfoShopIdx();
            skuInfoShopIdx.setSkuId(skuInfo.getId());
            ObjectCopyUtil.copyObjValue(skuInfo, skuInfoShopIdx, null, false);
            gdsSkuInfoShopIdxMapper.insertSelective(skuInfoShopIdx);
        }

        if (!CollectionUtils.isEmpty(props)) {
            for (GdsSku2PropReqDTO skuGds2PropReqDTO : props) {
                GdsSku2PropPropIdx sku2PropPropIdx = new GdsSku2PropPropIdx();
                sku2PropPropIdx.setSkuId(skuGds2PropReqDTO.getSkuId());
                sku2PropPropIdx.setStatus(GdsConstants.Commons.STATUS_VALID);
                ObjectCopyUtil.copyObjValue(skuGds2PropReqDTO, sku2PropPropIdx, null, false);
                sku2PropPropIdx.setStatus(GdsConstants.Commons.STATUS_VALID);
                
                Long staffId=skuGds2PropReqDTO.getStaff().getId();
                sku2PropPropIdx.setCreateStaff(staffId);
                sku2PropPropIdx.setCreateTime(DateUtil.getSysDate());
                sku2PropPropIdx.setUpdateStaff(staffId);
                sku2PropPropIdx.setUpdateTime(DateUtil.getSysDate());
                sku2PropPropIdxMapper.insertSelective(sku2PropPropIdx);
            }
        }

    }

    /**
     * 
     * TODO 删除单品索引表信息.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfoidx.IGdsInfoIDXSV#delSkuInfoIDX(com.zengshi.ecp.goods.dao.model.GdsSkuInfo)
     */
    @Override
    public void delSkuInfoIDX(GdsSkuInfo skuInfo, List<GdsSku2PropReqDTO> props)  throws BusinessException{

        // 删除商品维度索引表记录
        if (skuInfo != null) {
            GdsSkuInfoGdsIdxCriteria gdsIdxCriteria = new GdsSkuInfoGdsIdxCriteria();
            gdsIdxCriteria.createCriteria().andGdsIdEqualTo(skuInfo.getGdsId()).andSkuIdEqualTo(skuInfo.getId());
            gdsSkuInfoGdsIdxMapper.deleteByExample(gdsIdxCriteria);

            // 删除店铺维度索引表记录
            GdsSkuInfoShopIdxCriteria shopIdxCriteria = new GdsSkuInfoShopIdxCriteria();
            shopIdxCriteria.createCriteria().andShopIdEqualTo(skuInfo.getShopId()).andSkuIdEqualTo(skuInfo.getId());
            gdsSkuInfoShopIdxMapper.deleteByExample(shopIdxCriteria);
        }

        // 删除单品索引
        if (!CollectionUtils.isEmpty(props)) {
            for (GdsSku2PropReqDTO skuGds2PropReqDTO : props) {
                GdsSku2PropPropIdxCriteria sku2PropPropIdxCriteria = new GdsSku2PropPropIdxCriteria();
                GdsSku2PropPropIdxCriteria.Criteria propCriteria = sku2PropPropIdxCriteria.createCriteria();
                propCriteria.andSkuIdEqualTo(skuGds2PropReqDTO.getSkuId());
                propCriteria.andPropIdEqualTo(skuGds2PropReqDTO.getPropId());
                if (StringUtil.isNotBlank(skuGds2PropReqDTO.getPropValue())) {
                    propCriteria.andPropValueEqualTo(skuGds2PropReqDTO.getPropValue());
                } else {
                    propCriteria.andPropValueIsNull();
                }
                sku2PropPropIdxMapper.deleteByExample(sku2PropPropIdxCriteria);
            }
            // 更新商品属性关系表的商品状态
            GdsSku2PropCriteria sku2PropExam = new GdsSku2PropCriteria();
            GdsSku2PropCriteria.Criteria sku2PropCriteria = sku2PropExam.createCriteria();
            sku2PropCriteria.andSkuIdEqualTo(skuInfo.getId());
            sku2PropCriteria.andGdsIdEqualTo(skuInfo.getGdsId());
            sku2PropCriteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
            GdsSku2Prop sku2Prop = new GdsSku2Prop();
            sku2Prop.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_DELETE);
            sku2PropMapper.updateByExampleSelective(sku2Prop, sku2PropExam);
        }

    }

    /**
     * 
     * TODO 编辑单品索引表信息.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfoidx.IGdsInfoIDXSV#editSkuInfoIDX(com.zengshi.ecp.goods.dao.model.GdsSkuInfo)
     */
    @Override
    public void editSkuInfoIDX(GdsSkuInfo skuInfo, List<GdsSku2PropReqDTO> props) throws BusinessException {
        if (skuInfo != null) {
            // 编辑商品维度索引表记录
            GdsSkuInfoGdsIdxCriteria gdsIdxCriteria = new GdsSkuInfoGdsIdxCriteria();
            gdsIdxCriteria.createCriteria().andGdsIdEqualTo(skuInfo.getGdsId()).andSkuIdEqualTo(skuInfo.getId());

            GdsSkuInfoGdsIdx skuInfoGdsIdx = new GdsSkuInfoGdsIdx();
            skuInfoGdsIdx.setSkuId(skuInfo.getId());
            ObjectCopyUtil.copyObjValue(skuInfo, skuInfoGdsIdx, null, true);
            gdsSkuInfoGdsIdxMapper.updateByExampleSelective(skuInfoGdsIdx, gdsIdxCriteria);

            // 编辑店铺维度索引表记录
            GdsSkuInfoShopIdxCriteria shopIdxCriteria = new GdsSkuInfoShopIdxCriteria();
            shopIdxCriteria.createCriteria().andShopIdEqualTo(skuInfo.getShopId()).andSkuIdEqualTo(skuInfo.getId());

            GdsSkuInfoShopIdx skuInfoShopIdx = new GdsSkuInfoShopIdx();
            skuInfoShopIdx.setSkuId(skuInfo.getId());
            ObjectCopyUtil.copyObjValue(skuInfo, skuInfoShopIdx, null, true);
            gdsSkuInfoShopIdxMapper.updateByExampleSelective(skuInfoShopIdx, shopIdxCriteria);
        }

        if (!CollectionUtils.isEmpty(props)) {
            // 先删除所有的属性关系
            for (GdsSku2PropReqDTO skuGds2PropReqDTO : props) {
                GdsSku2PropPropIdxCriteria sku2PropPropIdxCriteria = new GdsSku2PropPropIdxCriteria();
                GdsSku2PropPropIdxCriteria.Criteria propCriteria = sku2PropPropIdxCriteria.createCriteria();
                propCriteria.andPropIdEqualTo(skuGds2PropReqDTO.getPropId());
                propCriteria.andSkuIdEqualTo(skuGds2PropReqDTO.getSkuId());
                sku2PropPropIdxMapper.deleteByExample(sku2PropPropIdxCriteria);
            }

            for (GdsSku2PropReqDTO skuGds2PropReqDTO : props) {
                // 如果更改商品状态，则同步到属性商品索引表
                GdsSku2PropPropIdx sku2PropPropIdx = new GdsSku2PropPropIdx();
                ObjectCopyUtil.copyObjValue(skuGds2PropReqDTO, sku2PropPropIdx, null, false);
                sku2PropPropIdx.setStatus(GdsConstants.Commons.STATUS_VALID);
                Long staffId=skuGds2PropReqDTO.getStaff().getId();
                sku2PropPropIdx.setCreateStaff(staffId);
                sku2PropPropIdx.setCreateTime(DateUtil.getSysDate());
                sku2PropPropIdx.setUpdateStaff(staffId);
                sku2PropPropIdx.setUpdateTime(DateUtil.getSysDate());
                sku2PropPropIdxMapper.insertSelective(sku2PropPropIdx);
            }
        }
    }
    

    @Override
    public void addGdsMediaIDX(GdsMedia gdsMedia)  throws BusinessException {
        GdsMediaShopIdx gdsMediaShopIdx = new GdsMediaShopIdx();
        gdsMediaShopIdx.setMediaId(gdsMedia.getId());
        ObjectCopyUtil.copyObjValue(gdsMedia, gdsMediaShopIdx, null, false);
        gdsMediaShopIdxMapper.insert(gdsMediaShopIdx);
    }

    @Override
    public void editGdsMediaIDX(GdsMedia gdsMedia)  throws BusinessException{
        GdsMediaShopIdxCriteria gdsMediaShopIdxCriteria = new GdsMediaShopIdxCriteria();
        gdsMediaShopIdxCriteria.createCriteria().andMediaIdEqualTo(gdsMedia.getId()).andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        GdsMediaShopIdx gdsMediaShopIdx = new GdsMediaShopIdx();
        gdsMediaShopIdx.setMediaId(gdsMedia.getId());
        ObjectCopyUtil.copyObjValue(gdsMedia, gdsMediaShopIdx, null, false);
        gdsMediaShopIdxMapper.updateByExampleSelective(gdsMediaShopIdx, gdsMediaShopIdxCriteria);
    }

    @Override
    public void delGdsMediaIDX(GdsMedia gdsMedia) throws BusinessException {
        GdsMediaShopIdxCriteria gdsMediaShopIdxCriteria = new GdsMediaShopIdxCriteria();
        gdsMediaShopIdxCriteria.createCriteria().andMediaIdEqualTo(gdsMedia.getId()).andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        GdsMediaShopIdx gdsMediaShopIdx = new GdsMediaShopIdx();
        gdsMediaShopIdx.setStatus(GdsConstants.Commons.STATUS_INVALID);
        gdsMediaShopIdxMapper.updateByExampleSelective(gdsMediaShopIdx, gdsMediaShopIdxCriteria);
    }

    @Override
    public void delGds2CatgCatgIDX(GdsGds2CatgCatgIdxReqDTO reqDTO) throws BusinessException {
        String catgCode = reqDTO.getCatgCode();
        Long gdsId = reqDTO.getGdsId();
        GdsGds2CatgCatgIdxCriteria gds2CatgCatgIdxCriteria = new GdsGds2CatgCatgIdxCriteria();
        gds2CatgCatgIdxCriteria.createCriteria().andCatgCodeEqualTo(catgCode).andGdsIdEqualTo(gdsId);
        gds2CatgCatgIdxMapper.deleteByExample(gds2CatgCatgIdxCriteria);
    }

    @Override
    public GdsGds2PropPropIdx queryGds2PropPropIdx(GdsGds2PropPropIdx gds2PropPropIdx)  throws BusinessException{
        paramNullCheck(gds2PropPropIdx, "gds2PropPropIdx");
        paramCheck(new Object[] { gds2PropPropIdx.getGdsId(), gds2PropPropIdx.getPropId() }, new String[] { "gdsId", "propId" });

        GdsGds2PropPropIdxCriteria example = new GdsGds2PropPropIdxCriteria();
        GdsGds2PropPropIdxCriteria.Criteria c = example.createCriteria();
        c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        c.andGdsIdEqualTo(gds2PropPropIdx.getGdsId());
        c.andPropIdEqualTo(gds2PropPropIdx.getPropId());

        List<GdsGds2PropPropIdx> lst = gds2PropPropIdxMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(lst)) {
            return lst.get(0);
        }
        return null;

    }

    @Override
    public GdsSku2PropPropIdx queryGdsSku2PropPropIdx(GdsSku2PropPropIdx gdsSku2PropPropIdx)  throws BusinessException{
        paramNullCheck(gdsSku2PropPropIdx, "gdsSku2PropPropIdx");
        paramCheck(new Object[] { gdsSku2PropPropIdx.getSkuId(), gdsSku2PropPropIdx.getPropId() }, new String[] { "skuId", "propId" });

        GdsSku2PropPropIdxCriteria example = new GdsSku2PropPropIdxCriteria();
        GdsSku2PropPropIdxCriteria.Criteria c = example.createCriteria();
        c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        c.andSkuIdEqualTo(gdsSku2PropPropIdx.getSkuId());
        c.andPropIdEqualTo(gdsSku2PropPropIdx.getPropId());

        List<GdsSku2PropPropIdx> lst = sku2PropPropIdxMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(lst)) {
            return lst.get(0);
        }
        return null;

    }

    @Override
    public void updateGds2PropPropIdx(GdsGds2PropPropIdx gds2PropPropIdx)  throws BusinessException{
        paramNullCheck(gds2PropPropIdx, "gds2PropPropIdx");
        paramCheck(new Object[] { gds2PropPropIdx.getGdsId(), gds2PropPropIdx.getPropId() }, new String[] { "gdsId", "propId" });
        GdsGds2PropPropIdxCriteria criteria = new GdsGds2PropPropIdxCriteria();
        GdsGds2PropPropIdxCriteria.Criteria c = criteria.createCriteria();
        c.andGdsIdEqualTo(gds2PropPropIdx.getGdsId());
        c.andPropIdEqualTo(gds2PropPropIdx.getPropId());
        c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);

        gds2PropPropIdxMapper.updateByExampleSelective(gds2PropPropIdx, criteria);

    }

    @Override
    public void updateGdsSku2PropPropIdx(GdsSku2PropPropIdx gdsSku2PropPropIdx)  throws BusinessException{
        paramNullCheck(gdsSku2PropPropIdx, "gdsSku2PropPropIdx");
        paramCheck(new Object[] { gdsSku2PropPropIdx.getSkuId(), gdsSku2PropPropIdx.getPropId() }, new String[] { "skuId", "propId" });
        GdsSku2PropPropIdxCriteria criteria = new GdsSku2PropPropIdxCriteria();
        GdsSku2PropPropIdxCriteria.Criteria c = criteria.createCriteria();
        c.andGdsIdEqualTo(gdsSku2PropPropIdx.getSkuId());
        c.andSkuIdEqualTo(gdsSku2PropPropIdx.getSkuId());
        c.andPropIdEqualTo(gdsSku2PropPropIdx.getPropId());
        c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);

        sku2PropPropIdxMapper.updateByExampleSelective(gdsSku2PropPropIdx, criteria);

    }

}
