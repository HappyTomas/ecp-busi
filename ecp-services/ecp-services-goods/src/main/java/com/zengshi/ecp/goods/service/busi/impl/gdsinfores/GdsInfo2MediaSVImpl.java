package com.zengshi.ecp.goods.service.busi.impl.gdsinfores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.goods.dao.mapper.busi.GdsGds2MediaMapper;
import com.zengshi.ecp.goods.dao.model.GdsGds2Media;
import com.zengshi.ecp.goods.dao.model.GdsGds2MediaCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaCacheResp;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2MediaReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2MediaRespDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores.IGdsInfo2MediaSV;
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
 * Title: 商品图片关系操作 <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2016年3月22日下午5:42:04 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsInfo2MediaSVImpl extends AbstractSVImpl implements IGdsInfo2MediaSV {

    @Resource
    private GdsGds2MediaMapper gds2MediaMapper;

    /**
     * 获取商品主图
     * 
     * @author linwb3
     * @param gdsId
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    @Override
    public GdsMediaRespDTO queryGdsMainPicByGdsId(Long gdsId) throws BusinessException {
        // 先从cache 取商品主图缓存
        GdsMediaRespDTO gdsMediaRespDTO = getMainPicCache(gdsId);

        // 如果商品主图不存在cache，则从数据库取
        if (gdsMediaRespDTO == null) {
            GdsGds2MediaReqDTO reqDTO = new GdsGds2MediaReqDTO();
            reqDTO.setGdsId(gdsId);
            reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
            reqDTO.setMediaType(GdsConstants.GdsMedia.MEDIA_TYPE_PIC);
            reqDTO.setSortNo(GdsConstants.GdsMedia.MEDIA_MAINPIC_SORTNO);
            List<GdsGds2Media> medias = queryGdsInfo2Media(reqDTO);

            if (CollectionUtils.isNotEmpty(medias)) {
                GdsGds2Media gds2Media = medias.get(0);
                gdsMediaRespDTO = new GdsMediaRespDTO();
                gdsMediaRespDTO.setMediaUuid(gds2Media.getMediaUuid());
                gdsMediaRespDTO.setURL(ImageUtil.getImageUrl(gds2Media.getMediaUuid() + "_200x200!"));
                gdsMediaRespDTO.setGdsId(gds2Media.getGdsId());
                try {
                    GdsMediaCacheResp obj = new GdsMediaCacheResp();
                    obj.setGdsId(gdsMediaRespDTO.getGdsId());
                    obj.setMediaUuid(gdsMediaRespDTO.getMediaUuid());
                    CacheUtil.addItem(GdsConstants.GdsInfoCacheKey.GDS_MAINPIC_CACHE_KEY_PREFIX + gdsId, obj);
                } catch (Exception e) {
                    LogUtil.error(MODULE, "add gdsInfo mainpic cache failed! please check  Cache Server!", e);
                }
            }

        }
        return gdsMediaRespDTO;
    }

    /**
     * 从缓存获取商品主图
     * 
     * @author linwb3
     * @param gdsId
     * @return
     * @since JDK 1.6
     */
    @Override
    public GdsMediaRespDTO getMainPicCache(Long gdsId) throws BusinessException {
        GdsMediaRespDTO gdsMediaRespDTO = null;
        try {
            GdsMediaCacheResp gdsMediaCacheResp = (GdsMediaCacheResp) CacheUtil.getItem(GdsConstants.GdsInfoCacheKey.GDS_MAINPIC_CACHE_KEY_PREFIX + gdsId);
            if (gdsMediaCacheResp != null) {
                gdsMediaRespDTO = new GdsMediaRespDTO();
                gdsMediaRespDTO.setGdsId(gdsId);
                gdsMediaRespDTO.setMediaUuid(gdsMediaCacheResp.getMediaUuid());
//                ObjectCopyUtil.copyObjValue(gdsMediaCacheResp, gdsMediaRespDTO, null, false);
                gdsMediaRespDTO.setURL(ImageUtil.getImageUrl(gdsMediaCacheResp.getMediaUuid() + "_200x200!"));
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "get gdsInfo mainPic cache failed! please check  Cache Server!", e);
        }
        return gdsMediaRespDTO;
    }

    /**
     * 查询商品的图片列表
     * 
     * @author linwb3
     * @param gdsId
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    @Override
    public List<GdsGds2MediaRespDTO> queryGds2MediasByGdsId(Long gdsId) throws BusinessException {
        List<GdsGds2Media> medias=queryGds2MediasModelByGdsId(gdsId);
        List<GdsGds2MediaRespDTO> resp=copyGdsMedias2Resp(medias);
        return resp;
    }

    /**
     * 查询商品的图片列表
     * 
     * @author linwb3
     * @param gdsId
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    @Override
    public List<GdsGds2Media> queryGds2MediasModelByGdsId(Long gdsId) throws BusinessException {
        GdsGds2MediaReqDTO reqDTO = new GdsGds2MediaReqDTO();
        reqDTO.setGdsId(gdsId);
        reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        List<GdsGds2Media> gds2medias = queryGdsInfo2Media(reqDTO);
        return gds2medias;
    }

    /**
     * 查询商品图片关系原子服务
     * 
     * @author linwb3
     * @param reqDTO
     * @return
     * @since JDK 1.6
     */
    @Override
    public List<GdsGds2Media> queryGdsInfo2Media(GdsGds2MediaReqDTO reqDTO) throws BusinessException {
        GdsGds2MediaCriteria gds2MediaCriteria = new GdsGds2MediaCriteria();

        if (reqDTO != null) {
            GdsGds2MediaCriteria.Criteria criteria = gds2MediaCriteria.createCriteria();
            if (StringUtil.isNotBlank(reqDTO.getStatus())) {
                criteria.andStatusEqualTo(reqDTO.getStatus());
            }
            initAutormCriteria(reqDTO, criteria);
        }
        gds2MediaCriteria.setOrderByClause("sort_no asc");
        return gds2MediaMapper.selectByExample(gds2MediaCriteria);

    }

    /**
     * 
     * updateGds2Media:(根据商品，旧图片编码更新商品图片关系). <br/>
     * 
     * @author linwb3
     * @param reqDTO
     * @since JDK 1.6
     */
    @Override
    public void updateGds2Media(GdsGds2MediaReqDTO reqDTO,GdsGds2MediaReqDTO query) throws BusinessException {
        paramNullCheck(reqDTO);
        paramNullCheck(query);
        paramNullCheck(query.getGdsId());

        Long staffId = reqDTO.getStaff().getId();

        GdsGds2Media gds2Media = new GdsGds2Media();
        ObjectCopyUtil.copyObjValue(reqDTO, gds2Media, null, true);
        gds2Media.setUpdateStaff(staffId);
        gds2Media.setUpdateTime(DateUtil.getSysDate());

        GdsGds2MediaCriteria example = new GdsGds2MediaCriteria();
        GdsGds2MediaCriteria.Criteria c = example.createCriteria();
        initAutormCriteria(query, c);
        if (StringUtil.isNotBlank(query.getOldMediaUuid())) {
            c.andMediaUuidEqualTo(query.getOldMediaUuid());
        }
        c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        gds2MediaMapper.updateByExampleSelective(gds2Media, example);
    }

    /**
     * 
     * saveGds2Media:(保存商品图片关系). <br/>
     * 
     * @author linwb3
     * @param reqDTO
     * @since JDK 1.6
     */
    @Override
    public void saveGds2Media(GdsGds2MediaReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO);
        paramCheck(new Object[] { reqDTO.getGdsId(), reqDTO.getMediaUuid() }, new String[] { "reqDTO.gdsId", "reqDTO.mediaUuid" });

        Long staffId = reqDTO.getStaff().getId();
        GdsGds2Media gds2Media = new GdsGds2Media();
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
        gds2MediaMapper.insertSelective(gds2Media);
    }

    /**
     * 
     * delGds2Media:(删除商品图片关系). <br/>
     * 
     * 商品编码必传
     * 
     * @author linwb3
     * @param reqDTO
     * @since JDK 1.6
     */
    @Override
    public void delGds2Media(GdsGds2MediaReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO);
        paramNullCheck(reqDTO.getGdsId());
        reqDTO.setStatus(GdsConstants.Commons.STATUS_INVALID);
        updateGds2Media(reqDTO,reqDTO);
    }

    /**
     * 
     * realDelGds2Media:(<font color='red'>物理删除商品与图片关联关系<font/>). <br/>
     * 
     * @author linwb3
     * @param reqDTO
     * @since JDK 1.6
     */
    @Override
    public void realDelGds2Media(GdsGds2MediaReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO);
        paramNullCheck(reqDTO.getGdsId());
        GdsGds2MediaCriteria example = new GdsGds2MediaCriteria();
        GdsGds2MediaCriteria.Criteria c = example.createCriteria();
        initAutormCriteria(reqDTO, c);
        if (StringUtil.isNotBlank(reqDTO.getStatus())) {
            c.andStatusEqualTo(reqDTO.getStatus());
        }
        gds2MediaMapper.deleteByExample(example);
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
    private void initAutormCriteria(GdsGds2MediaReqDTO reqDTO, GdsGds2MediaCriteria.Criteria criteria) {
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
    
    private List<GdsGds2MediaRespDTO> copyGdsMedias2Resp(List<GdsGds2Media> medisa) {
        List<GdsGds2MediaRespDTO> resp=new ArrayList<GdsGds2MediaRespDTO>(); 
        for (GdsGds2Media gds2media : medisa) {
            GdsGds2MediaRespDTO gdsMediaResp = copyInfo2Resp(gds2media);
            resp.add(gdsMediaResp);
        }
        return resp;
    }

    private GdsGds2MediaRespDTO copyInfo2Resp(GdsGds2Media gds2media) {
        GdsGds2MediaRespDTO gdsMediaResp=new GdsGds2MediaRespDTO();
        gdsMediaResp.setGdsId(gds2media.getGdsId());
        gdsMediaResp.setMediaId(gds2media.getMediaId());
        gdsMediaResp.setMediaType(gds2media.getMediaType());
        gdsMediaResp.setShopId(gds2media.getShopId());
        gdsMediaResp.setMediaUuid(gds2media.getMediaUuid());
        gdsMediaResp.setMediaRtype(gds2media.getMediaRtype());
        gdsMediaResp.setSortNo(gds2media.getSortNo());
        gdsMediaResp.setStatus(gds2media.getStatus());
        gdsMediaResp.setCreateStaff(gds2media.getCreateStaff());
        gdsMediaResp.setCreateTime(gds2media.getCreateTime());
        gdsMediaResp.setUpdateStaff(gds2media.getUpdateStaff());
        gdsMediaResp.setUpdateTime(gds2media.getUpdateTime());
        return gdsMediaResp;
    }
}
