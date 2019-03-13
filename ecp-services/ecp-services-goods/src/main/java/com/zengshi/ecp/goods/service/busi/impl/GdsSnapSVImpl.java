package com.zengshi.ecp.goods.service.busi.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsGds2CatgMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsGds2CatgSnapMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsGds2MediaMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsGds2MediaSnapMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsGds2PropMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsGds2PropSnapMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsInfoMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsInfoSnapMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsPriceSnapMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsSku2MediaMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsSku2MediaSnapMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsSku2PriceMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsSku2PriceSnapMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsSku2PropMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsSku2PropSnapMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsSkuInfoMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsSkuInfoSnapMapper;
import com.zengshi.ecp.goods.dao.model.GdsGds2Catg;
import com.zengshi.ecp.goods.dao.model.GdsGds2CatgCriteria;
import com.zengshi.ecp.goods.dao.model.GdsGds2CatgCriteria.Criteria;
import com.zengshi.ecp.goods.dao.model.GdsGds2CatgSnap;
import com.zengshi.ecp.goods.dao.model.GdsGds2Media;
import com.zengshi.ecp.goods.dao.model.GdsGds2MediaCriteria;
import com.zengshi.ecp.goods.dao.model.GdsGds2MediaSnap;
import com.zengshi.ecp.goods.dao.model.GdsGds2Prop;
import com.zengshi.ecp.goods.dao.model.GdsGds2PropCriteria;
import com.zengshi.ecp.goods.dao.model.GdsGds2PropSnap;
import com.zengshi.ecp.goods.dao.model.GdsInfo;
import com.zengshi.ecp.goods.dao.model.GdsInfoSnap;
import com.zengshi.ecp.goods.dao.model.GdsSku2Media;
import com.zengshi.ecp.goods.dao.model.GdsSku2MediaCriteria;
import com.zengshi.ecp.goods.dao.model.GdsSku2MediaSnap;
import com.zengshi.ecp.goods.dao.model.GdsSku2Price;
import com.zengshi.ecp.goods.dao.model.GdsSku2PriceCriteria;
import com.zengshi.ecp.goods.dao.model.GdsSku2PriceSnap;
import com.zengshi.ecp.goods.dao.model.GdsSku2Prop;
import com.zengshi.ecp.goods.dao.model.GdsSku2PropCriteria;
import com.zengshi.ecp.goods.dao.model.GdsSku2PropSnap;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfo;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfoSnap;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsSnapSV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年9月25日下午4:58:22 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
@Deprecated
public class GdsSnapSVImpl extends AbstractSVImpl implements IGdsSnapSV {

    // -----------序列服务-----------

    @Resource(name = "seq_gds_info_snap")
    private PaasSequence seqGdsInfoSnap;

    // -----------快照源信息服务-----------

    @Resource
    private GdsInfoMapper gdsInfoMapper;

    @Resource
    private GdsGds2CatgMapper gdsGds2CatgMapper;

    @Resource
    private GdsGds2PropMapper gdsGds2PropMapper;

    @Resource
    private GdsGds2MediaMapper gdsGds2MediaMapper;

    @Resource
    private GdsSkuInfoMapper gdsSkuInfoMapper;

    @Resource
    private GdsSku2PropMapper gdsSku2PropMapper;

    @Resource
    private GdsSku2MediaMapper gdsSku2MediaMapper;

    @Resource
    private GdsSku2PriceMapper gdsSku2PriceMapper;

    // -----------快照服务-----------

    @Resource
    private GdsInfoSnapMapper gdsInfoSnapMapper;

    @Resource
    private GdsGds2CatgSnapMapper gdsGds2CatgSnapMapper;

    @Resource
    private GdsGds2PropSnapMapper gdsGds2PropSnapMapper;

    @Resource
    private GdsGds2MediaSnapMapper gdsGds2MediaSnapMapper;

    @Resource
    private GdsPriceSnapMapper gdsPriceSnapMapper;

    @Resource
    private GdsSkuInfoSnapMapper gdsSkuInfoSnapMapper;

    @Resource
    private GdsSku2PropSnapMapper gdsSku2PropSnapMapper;

    @Resource
    private GdsSku2MediaSnapMapper gdsSku2MediaSnapMapper;

    @Resource
    private GdsSku2PriceSnapMapper gdsSku2PriceSnapMapper;

    @Deprecated
    @Override
    public Long saveAllGdsRelatedSnap(GdsSkuInfoReqDTO gdsSkuInfoReqDTO)
            throws BusinessException {

        // 快照统一主键
        Long id = this.seqGdsInfoSnap.nextValue();

        GdsInfo gdsInfo = this.gdsInfoMapper.selectByPrimaryKey(gdsSkuInfoReqDTO.getGdsId());
        GdsSkuInfo gdsSkuInfo = this.gdsSkuInfoMapper.selectByPrimaryKey(gdsSkuInfoReqDTO.getId());

        // 商品和单品数据必须存在
        if (gdsInfo == null || gdsSkuInfo == null) {
            throw new BusinessException("");
        }

        // 重设主键，snap对象直接从源对象做全拷贝
        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setStaff(gdsSkuInfoReqDTO.getStaff());

        // 商品快照保存
        saveGdsInfoSnap(gdsInfo, baseInfo,id);

        GdsGds2CatgCriteria gdsGds2CatgCriteria = new GdsGds2CatgCriteria();
        Criteria cr1 = gdsGds2CatgCriteria.createCriteria();
        cr1.andGdsIdEqualTo(gdsInfo.getId());
        cr1.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        List<GdsGds2Catg> gdsGds2CatgList = this.gdsGds2CatgMapper
                .selectByExample(gdsGds2CatgCriteria);
        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(gdsGds2CatgList)) {
            for (GdsGds2Catg gdsGds2Catg : gdsGds2CatgList) {
                saveGdsCateSnap(gdsGds2Catg, baseInfo,id);
            }
        }

        GdsGds2PropCriteria gdsGds2PropCriteria = new GdsGds2PropCriteria();
        com.zengshi.ecp.goods.dao.model.GdsGds2PropCriteria.Criteria cr2 = gdsGds2PropCriteria
                .createCriteria();
        cr2.andGdsIdEqualTo(gdsInfo.getId());
        cr2.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        List<GdsGds2Prop> gdsGds2PropList = this.gdsGds2PropMapper
                .selectByExample(gdsGds2PropCriteria);
        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(gdsGds2PropList)) {
            for (GdsGds2Prop gdsGds2Prop : gdsGds2PropList) {
                saveGdsProperSnap(gdsGds2Prop, baseInfo,id);
            }
        }

        GdsGds2MediaCriteria gdsGds2MediaCriteria = new GdsGds2MediaCriteria();
        com.zengshi.ecp.goods.dao.model.GdsGds2MediaCriteria.Criteria cr3 = gdsGds2MediaCriteria
                .createCriteria();
        cr3.andGdsIdEqualTo(gdsInfo.getId());
        cr3.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        List<GdsGds2Media> gdsGds2MediaList = this.gdsGds2MediaMapper
                .selectByExample(gdsGds2MediaCriteria);
        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(gdsGds2MediaList)) {
            for (GdsGds2Media gdsGds2Media : gdsGds2MediaList) {
                saveGdsMediaSnap(gdsGds2Media, baseInfo,id);
            }
        }

        // 单品快照保存
        saveSkuInfoSnap(gdsSkuInfo, baseInfo,id);

        GdsSku2PropCriteria gdsSku2PropCriteria = new GdsSku2PropCriteria();
        com.zengshi.ecp.goods.dao.model.GdsSku2PropCriteria.Criteria cr5 = gdsSku2PropCriteria
                .createCriteria();
        cr5.andGdsIdEqualTo(gdsSkuInfoReqDTO.getGdsId());
        cr5.andSkuIdEqualTo(gdsSkuInfoReqDTO.getId());
        cr5.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        List<GdsSku2Prop> gdsSku2PropList = this.gdsSku2PropMapper
                .selectByExample(gdsSku2PropCriteria);
        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(gdsSku2PropList)) {
            for (GdsSku2Prop gdsSku2Prop : gdsSku2PropList) {
                saveSkuProperSnap(gdsSku2Prop, baseInfo,id);
            }
        }

        GdsSku2MediaCriteria gdsSku2MediaCriteria = new GdsSku2MediaCriteria();
        com.zengshi.ecp.goods.dao.model.GdsSku2MediaCriteria.Criteria cr6 = gdsSku2MediaCriteria
                .createCriteria();
        cr6.andSkuIdEqualTo(gdsSkuInfoReqDTO.getId());
        cr6.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        List<GdsSku2Media> gdsSku2MediaList = this.gdsSku2MediaMapper
                .selectByExample(gdsSku2MediaCriteria);
        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(gdsSku2MediaList)) {
            for (GdsSku2Media gdsSku2Media : gdsSku2MediaList) {
                saveSkuMediaSnap(gdsSku2Media, baseInfo,id);
            }
        }

        GdsSku2PriceCriteria gdsSku2PriceCriteria = new GdsSku2PriceCriteria();
        com.zengshi.ecp.goods.dao.model.GdsSku2PriceCriteria.Criteria cr7 = gdsSku2PriceCriteria
                .createCriteria();
        cr7.andSkuIdEqualTo(gdsSkuInfoReqDTO.getId());
        cr7.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        List<GdsSku2Price> gdsSku2PriceList = this.gdsSku2PriceMapper
                .selectByExample(gdsSku2PriceCriteria);
        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(gdsSku2PriceList)) {
            for (GdsSku2Price gdsSku2Price : gdsSku2PriceList) {
                saveSkuPriceSnap(gdsSku2Price, baseInfo,id);
            }
        }
        
        return id;

    }

    /**
     * 保存商品主表快照
     * 
     * @param gdsInfo
     * @return
     */
    public void saveGdsInfoSnap(GdsInfo gdsInfo, BaseInfo baseInfo,Long id) {

        GdsInfoSnap gdsInfoSnap = new GdsInfoSnap();
        ObjectCopyUtil.copyObjValue(gdsInfo, gdsInfoSnap, null, false);
        gdsInfoSnap.setSnapId(id);
        preInsert(baseInfo, gdsInfoSnap);
        this.gdsInfoSnapMapper.insert(gdsInfoSnap);
    }

    /**
     * 保存商品分类关系快照
     * 
     * @param gdsInfo
     * @return
     */
    public void saveGdsCateSnap(GdsGds2Catg gdsGds2Catg, BaseInfo baseInfo,Long snapId) {

        GdsGds2CatgSnap gdsGds2CatgSnap = new GdsGds2CatgSnap();
        ObjectCopyUtil.copyObjValue(gdsGds2Catg, gdsGds2CatgSnap, null, false);
        preInsert(baseInfo, gdsGds2CatgSnap);
        gdsGds2CatgSnap.setSnapId(snapId);
        this.gdsGds2CatgSnapMapper.insert(gdsGds2CatgSnap);
    }

    /**
     * 保存商品属性关系快照
     * 
     * @param gdsInfo
     * @return
     */
    public void saveGdsProperSnap(GdsGds2Prop gdsGds2Prop, BaseInfo baseInfo,Long snapId) {

        GdsGds2PropSnap gdsGds2PropSnap = new GdsGds2PropSnap();
        ObjectCopyUtil.copyObjValue(gdsGds2Prop, gdsGds2PropSnap, null, false);
        preInsert(baseInfo, gdsGds2PropSnap);
        gdsGds2PropSnap.setSnapId(snapId);
        this.gdsGds2PropSnapMapper.insert(gdsGds2PropSnap);
    }

    /**
     * 保存商品媒体关系快照
     * 
     * @param gdsInfo
     * @return
     */
    public void saveGdsMediaSnap(GdsGds2Media gdsGds2Media, BaseInfo baseInfo,Long snapId) {

        GdsGds2MediaSnap gdsGds2MediaSnap = new GdsGds2MediaSnap();
        ObjectCopyUtil.copyObjValue(gdsGds2Media, gdsGds2MediaSnap, null, false);
        preInsert(baseInfo, gdsGds2MediaSnap);
        gdsGds2MediaSnap.setSnapId(snapId);
        this.gdsGds2MediaSnapMapper.insert(gdsGds2MediaSnap);
    }


    /**
     * 保存单品主表快照
     * 
     * @param gdsInfo
     * @return
     */
    public void saveSkuInfoSnap(GdsSkuInfo gdsSkuInfo, BaseInfo baseInfo,Long id) {

        GdsSkuInfoSnap gdsSkuInfoSnap = new GdsSkuInfoSnap();
        ObjectCopyUtil.copyObjValue(gdsSkuInfo, gdsSkuInfoSnap, null, false);
        //快照表记录保存上次快照，以便可以依序递归获取有序快照记录
        gdsSkuInfoSnap.setSnapId(gdsSkuInfo.getSnapId());
        gdsSkuInfoSnap.setId(id);
        gdsSkuInfoSnap.setSkuId(gdsSkuInfo.getId());
        preInsert(baseInfo, gdsSkuInfoSnap);
        this.gdsSkuInfoSnapMapper.insert(gdsSkuInfoSnap);
    }

    /**
     * 保存单品属性关系快照
     * 
     * @param gdsInfo
     * @return
     */
    public void saveSkuProperSnap(GdsSku2Prop gdsSku2Prop, BaseInfo baseInfo,Long snapId) {

        GdsSku2PropSnap gdsSku2PropSnap = new GdsSku2PropSnap();
        ObjectCopyUtil.copyObjValue(gdsSku2Prop, gdsSku2PropSnap, null, false);
        preInsert(baseInfo, gdsSku2PropSnap);
        gdsSku2PropSnap.setSnapId(snapId);
        this.gdsSku2PropSnapMapper.insert(gdsSku2PropSnap);
    }

    /**
     * 保存单品媒体关系快照
     * 
     * @param gdsInfo
     * @return
     */
    public void saveSkuMediaSnap(GdsSku2Media gdsSku2Media, BaseInfo baseInfo,Long snapId) {

        GdsSku2MediaSnap gdsSku2MediaSnap = new GdsSku2MediaSnap();
        ObjectCopyUtil.copyObjValue(gdsSku2Media, gdsSku2MediaSnap, null, false);
        preInsert(baseInfo, gdsSku2MediaSnap);
        gdsSku2MediaSnap.setSnapId(snapId);
        this.gdsSku2MediaSnapMapper.insert(gdsSku2MediaSnap);
    }

    /**
     * 保存单品价格关系快照
     * 
     * @param gdsInfo
     * @return
     */
    public void saveSkuPriceSnap(GdsSku2Price gdsSku2Price, BaseInfo baseInfo,Long snapId) {

        GdsSku2PriceSnap gdsSku2PriceSnap = new GdsSku2PriceSnap();
        ObjectCopyUtil.copyObjValue(gdsSku2Price, gdsSku2PriceSnap, null, false);
        preInsert(baseInfo, gdsSku2PriceSnap);
        gdsSku2PriceSnap.setSnapId(snapId);
        this.gdsSku2PriceSnapMapper.insert(gdsSku2PriceSnap);
    }

}
