package com.zengshi.ecp.goods.service.busi.impl.gdsinfores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.goods.dao.mapper.busi.GdsSku2MediaMapper;
import com.zengshi.ecp.goods.dao.model.GdsGds2Media;
import com.zengshi.ecp.goods.dao.model.GdsSku2Media;
import com.zengshi.ecp.goods.dao.model.GdsSku2MediaCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaCacheResp;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2MediaReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2MediaRespDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores.IGdsInfo2MediaSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores.IGdsSkuInfo2MediaSV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: 单品图片关系操作 <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2016年3月23日上午11:53:03 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsSkuInfo2MediaSVImpl extends AbstractSVImpl implements IGdsSkuInfo2MediaSV {

    @Resource
    private GdsSku2MediaMapper gdsSku2MediaMapper;

    @Resource
    private IGdsInfo2MediaSV gdsInfo2MediaSV;

    /**
     * 
     * 获取单品图片
     * 
     * @author linwb3
     * @param skuId
     * @param gdsId
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    @Override
    public GdsMediaRespDTO querySkuMainPicBySkuId(Long skuId, Long gdsId) throws BusinessException {
        GdsMediaRespDTO gdsMediaRespDTO = this.getMainPicCache(skuId);
        // 如果缓存获取不到，则获取对应的数据库记录
        if (gdsMediaRespDTO == null) {
            GdsSku2MediaReqDTO reqDTO = new GdsSku2MediaReqDTO();
            reqDTO.setSkuId(skuId);
            reqDTO.setMediaType(GdsConstants.GdsMedia.MEDIA_TYPE_PIC);
            reqDTO.setSortNo(GdsConstants.GdsMedia.MEDIA_MAINPIC_SORTNO);
            reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);

            // 查询主图
            List<GdsSku2Media> medias = getGdsSkuInfo2Media(reqDTO);
            if (CollectionUtils.isNotEmpty(medias)) {
                GdsSku2Media gds2Media = medias.get(0);
                gdsMediaRespDTO = new GdsMediaRespDTO();
                gdsMediaRespDTO.setMediaUuid(gds2Media.getMediaUuid());
                gdsMediaRespDTO.setGdsId(gds2Media.getGdsId());
                gdsMediaRespDTO.setSkuId(gds2Media.getSkuId());
                gdsMediaRespDTO.setURL(ImageUtil.getImageUrl(gds2Media.getMediaUuid() + "_200x200!"));
            } else {
                gdsMediaRespDTO = gdsInfo2MediaSV.queryGdsMainPicByGdsId(gdsId);
            }

            // 保存缓存
            try {
                if (gdsMediaRespDTO != null) {
                    GdsMediaCacheResp obj = new GdsMediaCacheResp();
                    obj.setMediaUuid(gdsMediaRespDTO.getMediaUuid());
                    obj.setGdsId(gdsMediaRespDTO.getGdsId());
                    CacheUtil.addItem(GdsConstants.GdsInfoCacheKey.SKU_MAINPIC_CACHE_KEY_PREFIX + skuId, obj);
                }
            } catch (Exception e) {
                LogUtil.error(MODULE, "add gdsInfo mainpic cache failed! please check  Cache Server!", e);
            }

        }
        return gdsMediaRespDTO;
    }

    /**
     * 
     * 获取单品图片缓存
     * 
     * @author linwb3
     * @param skuId
     * @return
     * @since JDK 1.6
     */
    @Override
    public GdsMediaRespDTO getMainPicCache(Long skuId) throws BusinessException {
        GdsMediaRespDTO gdsMediaRespDTO = null;
        try {
            GdsMediaCacheResp gdsMediaCacheResp = (GdsMediaCacheResp) CacheUtil.getItem(GdsConstants.GdsInfoCacheKey.SKU_MAINPIC_CACHE_KEY_PREFIX + skuId);
            if (gdsMediaCacheResp != null) {
                gdsMediaRespDTO = new GdsMediaRespDTO();
                gdsMediaRespDTO.setGdsId(gdsMediaCacheResp.getGdsId());
                gdsMediaRespDTO.setMediaUuid(gdsMediaCacheResp.getMediaUuid());
                gdsMediaRespDTO.setURL(ImageUtil.getImageUrl(gdsMediaCacheResp.getMediaUuid() + "_200x200!"));
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "get gdsInfo mainPic cache failed! please check  Cache Server!", e);
        }
        return gdsMediaRespDTO;
    }

    /**
     * 
     * 根据单品，商品Id，查询对应的单品图片关系（不继承商品图片关系）
     * 
     * @author linwb3
     * @param skuId
     * @param gdsId
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    @Override
    public List<GdsSku2MediaRespDTO> querySkuMediaBySkuIdWithOutGds(Long skuId, Long gdsId) throws BusinessException {
        List<GdsSku2Media> medisa=querySkuMediaModelBySkuIdWithOutGds(skuId, gdsId);
        List<GdsSku2MediaRespDTO> resp = copySkuMedia2Resp(medisa);
        return resp;
    }

    /**
     * 
     * 根据单品，商品Id，查询对应的单品图片关系
     * 
     * @author linwb3
     * @param skuId
     * @param gdsId
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    @Override
    public List<GdsSku2MediaRespDTO> querySkuMediaBySkuId(Long skuId, Long gdsId) throws BusinessException {
        List<GdsSku2Media> medisa=querySkuMediaModelBySkuId(skuId, gdsId); 
        List<GdsSku2MediaRespDTO> resp = copySkuMedia2Resp(medisa);
        return resp;
    }

    private List<GdsSku2MediaRespDTO> copySkuMedia2Resp(List<GdsSku2Media> medisa) {
        List<GdsSku2MediaRespDTO> resp=new ArrayList<GdsSku2MediaRespDTO>(); 
        for (GdsSku2Media gdsSku2Media : medisa) {
            GdsSku2MediaRespDTO sku2Media=new GdsSku2MediaRespDTO();
            sku2Media.setSkuId(gdsSku2Media.getSkuId());
            sku2Media.setGdsId(gdsSku2Media.getGdsId());
            sku2Media.setMediaId(gdsSku2Media.getMediaId());
            sku2Media.setMediaType(gdsSku2Media.getMediaType());
            sku2Media.setShopId(gdsSku2Media.getShopId());
            sku2Media.setMediaUuid(gdsSku2Media.getMediaUuid());
            sku2Media.setMediaRtype(gdsSku2Media.getMediaRtype());
            sku2Media.setSortNo(gdsSku2Media.getSortNo());
            sku2Media.setStatus(gdsSku2Media.getStatus());
            sku2Media.setCreateStaff(gdsSku2Media.getCreateStaff());
            sku2Media.setCreateTime(gdsSku2Media.getCreateTime());
            sku2Media.setUpdateStaff(gdsSku2Media.getUpdateStaff());
            sku2Media.setUpdateTime(gdsSku2Media.getUpdateTime());
            resp.add(sku2Media);
        }
        return resp;
    }

    /**
     * 
     * 根据单品，商品Id，查询对应的单品图片关系（不继承商品图片关系）
     * 
     * @author linwb3
     * @param skuId
     * @param gdsId
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    @Override
    public List<GdsSku2Media> querySkuMediaModelBySkuIdWithOutGds(Long skuId, Long gdsId) throws BusinessException {
        GdsSku2MediaReqDTO reqDTO = new GdsSku2MediaReqDTO();
        reqDTO.setSkuId(skuId);
        reqDTO.setGdsId(gdsId);
        reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        return getGdsSkuInfo2Media(reqDTO);
    }

    /**
     * 根据单品，商品Id，查询对应的单品图片关系
     * 
     * @author linwb3
     * @param skuId
     * @param gdsId
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    @Override
    public List<GdsSku2Media> querySkuMediaModelBySkuId(Long skuId, Long gdsId) throws BusinessException {
        List<GdsSku2Media> medias = querySkuMediaModelBySkuIdWithOutGds(skuId, gdsId);
        if (CollectionUtils.isEmpty(medias)) {
            List<GdsGds2Media> gdsmedias = gdsInfo2MediaSV.queryGds2MediasModelByGdsId(gdsId);
            if (CollectionUtils.isNotEmpty(gdsmedias)) {
                medias = new ArrayList<GdsSku2Media>();
                for (GdsGds2Media gdsGds2Media : gdsmedias) {
                    medias.add(convertToSku(gdsGds2Media, skuId));
                }
            }
        }
        return medias;
    }

    /**
     * 查询单品图片关系原子查询服务
     * 
     * @author linwb3
     * @param reqDTO
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    @Override
    public List<GdsSku2Media> getGdsSkuInfo2Media(GdsSku2MediaReqDTO reqDTO) throws BusinessException {
        GdsSku2MediaCriteria sku2MediaCriteria = new GdsSku2MediaCriteria();
        if (reqDTO != null) {
            GdsSku2MediaCriteria.Criteria criteria = sku2MediaCriteria.createCriteria();
            if (StringUtil.isNotBlank(reqDTO.getStatus())) {
                criteria.andStatusEqualTo(reqDTO.getStatus());
            }
            initAutormCriteria(reqDTO, criteria);
        }
        sku2MediaCriteria.setOrderByClause("sort_no asc");
        return gdsSku2MediaMapper.selectByExample(sku2MediaCriteria);
    }

    /**
     * 转换商品图片关系为单品图片关系
     * 
     * @author linwb3
     * @param media
     * @param skuId
     * @return
     * @since JDK 1.6
     */
    private GdsSku2Media convertToSku(GdsGds2Media media, Long skuId) throws BusinessException {
        GdsSku2Media gdsSku2Media = null;
        if (media != null) {
            gdsSku2Media = new GdsSku2Media();
            gdsSku2Media.setSkuId(skuId);
            gdsSku2Media.setGdsId(media.getGdsId());
            gdsSku2Media.setShopId(media.getShopId());
            gdsSku2Media.setSortNo(media.getSortNo());
            gdsSku2Media.setMediaUuid(media.getMediaUuid());
            gdsSku2Media.setMediaId(media.getMediaId());
            gdsSku2Media.setMediaRtype(media.getMediaRtype());
            gdsSku2Media.setMediaType(media.getMediaType());

            gdsSku2Media.setStatus(media.getStatus());
            gdsSku2Media.setCreateStaff(media.getCreateStaff());
            gdsSku2Media.setCreateTime(media.getCreateTime());
            gdsSku2Media.setUpdateStaff(media.getUpdateStaff());
            gdsSku2Media.setUpdateTime(media.getUpdateTime());
        }
        return gdsSku2Media;
    }

    /**
     * 
     * updateSku2Media:(更新单品图片关系). <br/> 
     * 
     * @author linwb3
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @Override
    public void updateSku2Media(GdsSku2MediaReqDTO reqDTO,GdsSku2MediaReqDTO query) throws BusinessException {
        paramNullCheck(reqDTO);
        paramNullCheck(query);
        paramNullCheck(query.getGdsId());
        paramNullCheck(query.getSkuId());

        Long staffId = reqDTO.getStaff().getId();

        GdsSku2Media gds2Media = new GdsSku2Media();
        ObjectCopyUtil.copyObjValue(reqDTO, gds2Media, null, true);
        gds2Media.setUpdateStaff(staffId);
        gds2Media.setUpdateTime(DateUtil.getSysDate());

        GdsSku2MediaCriteria example = new GdsSku2MediaCriteria();
        GdsSku2MediaCriteria.Criteria c = example.createCriteria();
        initAutormCriteria(query, c);
        if (StringUtil.isNotBlank(query.getOldMediaUuid())) {
            c.andMediaUuidEqualTo(query.getOldMediaUuid());
        }
        c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        gdsSku2MediaMapper.updateByExampleSelective(gds2Media, example);

    }

    /**
     * 
     * saveSku2Media:(保存单品图片关系). <br/> 
     * 
     * @author linwb3
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @Override
    public void saveSku2Media(GdsSku2MediaReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO);
        paramCheck(new Object[] { reqDTO.getGdsId(), reqDTO.getSkuId(), reqDTO.getMediaUuid() }, new String[] { "reqDTO.gdsId", "reqDTO.skuId", "reqDTO.mediaUuid" });
        Long staffId = reqDTO.getStaff().getId();
        GdsSku2Media gds2Media = new GdsSku2Media();
        ObjectCopyUtil.copyObjValue(reqDTO, gds2Media, null, false);
        if(gds2Media.getMediaId()!=null){
            //如果图片id不为空，则为引用
            gds2Media.setMediaRtype(GdsConstants.GdsMedia.MEDIA_RTYPE_REFRENCE);
        }else{
            gds2Media.setMediaRtype(GdsConstants.GdsMedia.MEDIA_RTYPE_UPLOAD);
        }
        gds2Media.setStatus(GdsConstants.Commons.STATUS_VALID);
        gds2Media.setCreateStaff(staffId);
        gds2Media.setCreateTime(DateUtil.getSysDate());
        gds2Media.setUpdateStaff(staffId);
        gds2Media.setUpdateTime(DateUtil.getSysDate());
        gdsSku2MediaMapper.insertSelective(gds2Media);
    }

    /**
     * 
     * delSku2Media:(逻辑删除单品与图片关系). <br/> 
     * 
     * @author linwb3
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @Override
    public void delSku2Media(GdsSku2MediaReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO);
        paramNullCheck(reqDTO.getGdsId());
        paramNullCheck(reqDTO.getSkuId());
        reqDTO.setStatus(GdsConstants.Commons.STATUS_INVALID);
        updateSku2Media(reqDTO,reqDTO);
    }

    /**
     * 
     * realDelSku2Media:(<font color='red'>物理删除单品与图片关联关系<font/>). <br/>
     * 
     * @author linwb3
     * @param reqDTO
     * @since JDK 1.6
     */
    @Override
    public void realDelSku2Media(GdsSku2MediaReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO);
        paramNullCheck(reqDTO.getGdsId());
        GdsSku2MediaCriteria example = new GdsSku2MediaCriteria();
        GdsSku2MediaCriteria.Criteria c = example.createCriteria();
        initAutormCriteria(reqDTO, c);
        if (StringUtil.isNotBlank(reqDTO.getOldMediaUuid())) {
            c.andMediaUuidEqualTo(reqDTO.getOldMediaUuid());
        }
        if (StringUtil.isNotBlank(reqDTO.getStatus())) {
            c.andStatusEqualTo(reqDTO.getStatus());
        }
        gdsSku2MediaMapper.deleteByExample(example);
    }

    /**
     * 
     * initAutormCriteria:(查询条件拼接). <br/> 
     * 
     * @author linwb3
     * @param reqDTO
     * @param criteria 
     * @since JDK 1.6
     */
    private void initAutormCriteria(GdsSku2MediaReqDTO reqDTO, GdsSku2MediaCriteria.Criteria criteria) {
        if (reqDTO.getSkuId() != null && reqDTO.getSkuId().longValue() != 0) {
            criteria.andSkuIdEqualTo(reqDTO.getSkuId());
        }
        if (reqDTO.getGdsId() != null && reqDTO.getGdsId().longValue() != 0) {
            criteria.andGdsIdEqualTo(reqDTO.getGdsId());
        }
        if (reqDTO.getSortNo() != null) {
            criteria.andSortNoEqualTo(reqDTO.getSortNo());
        }
        if (reqDTO.getMediaId() != null) {
            criteria.andMediaIdEqualTo(reqDTO.getMediaId());
        }
        if (StringUtil.isNotBlank(reqDTO.getMediaType())) {
            criteria.andMediaTypeEqualTo(reqDTO.getMediaType());
        }
        if (StringUtil.isNotBlank(reqDTO.getMediaRtype())) {
            criteria.andMediaRtypeEqualTo(reqDTO.getMediaRtype());
        }
        if (reqDTO.getShopId() != null) {
            criteria.andShopIdEqualTo(reqDTO.getShopId());
        }
    }

}
