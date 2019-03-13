package com.zengshi.ecp.goods.service.busi.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dao.mapper.busi.GdsScoreExtMapper;
import com.zengshi.ecp.goods.dao.model.GdsScoreExt;
import com.zengshi.ecp.goods.dao.model.GdsScoreExtCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsScoreExtReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsScoreExtRespDTO;
import com.zengshi.ecp.goods.dubbo.impl.AbstractRSVImpl;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsScoreExtSV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.db.sequence.Sequence;

public class GdsScoreExtSVImpl extends AbstractSVImpl implements IGdsScoreExtSV {
    @Resource
    private GdsScoreExtMapper gdsScoreExtMapper;

    @Resource(name = "seq_gds_score_ext")
    private Sequence seqGdsScoreExt;

    @Override
    public void addGdsScoreExt(GdsScoreExtReqDTO gdsScoreExtReqDTO) throws BusinessException {
        Long staffId = gdsScoreExtReqDTO.getStaff().getId();
        GdsScoreExt gdsScoreExt = new GdsScoreExt();
        ObjectCopyUtil.copyObjValue(gdsScoreExtReqDTO, gdsScoreExt, null, false);
        gdsScoreExt.setCreateTime(new Timestamp(System.currentTimeMillis()));
        gdsScoreExt.setCreateStaff(staffId);
        gdsScoreExt.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        gdsScoreExt.setUpdateStaff(staffId);
        gdsScoreExt.setId(seqGdsScoreExt.nextValue());
        gdsScoreExt.setStatus(GdsConstants.Commons.STATUS_VALID);
        gdsScoreExtMapper.insertSelective(gdsScoreExt);
    }

    @Override
    public void editGdsScoreExt(GdsScoreExtReqDTO gdsScoreExtReqDTO) throws BusinessException {
        Long staffId = gdsScoreExtReqDTO.getStaff().getId();
        GdsScoreExtCriteria example = new GdsScoreExtCriteria();
        GdsScoreExtCriteria.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(gdsScoreExtReqDTO.getId());
        GdsScoreExt ext = new GdsScoreExt();
        ext.setPrice(gdsScoreExtReqDTO.getPrice());
        ext.setScore(gdsScoreExtReqDTO.getScore());
        ext.setScoreType(gdsScoreExtReqDTO.getScoreType());
        ext.setIfDefault(gdsScoreExtReqDTO.getIfDefault());
        ext.setId(gdsScoreExtReqDTO.getId());
        ext.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        ext.setUpdateStaff(staffId);
        int key = gdsScoreExtMapper.updateByExampleSelective(ext, example);
        if (key == 0) {
            throw new BusinessException(GdsErrorConstants.GdsScoreExt.ERROR_GOODS_SCORE_EXT_240501);
        }
    }

    @Override
    public void deleteGdsSoreExt(GdsScoreExtReqDTO gdsScoreExtReqDTO) throws BusinessException {
        try {
            Long staffId = gdsScoreExtReqDTO.getStaff().getId();
            GdsScoreExtCriteria example = new GdsScoreExtCriteria();

            GdsScoreExtCriteria.Criteria criteria = example.createCriteria();
            criteria.andIdEqualTo(gdsScoreExtReqDTO.getId());
            GdsScoreExt ext = new GdsScoreExt();
            ext.setStatus(GdsConstants.Commons.STATUS_INVALID);
            ext.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            ext.setUpdateStaff(staffId);
            int key = gdsScoreExtMapper.updateByExampleSelective(ext, example);
            if (key == 0) {
                throw new BusinessException(GdsErrorConstants.GdsScoreExt.ERROR_GOODS_SCORE_EXT_240502);
            }
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "", be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "", e);
            throw new BusinessException(GdsErrorConstants.GdsScoreExt.ERROR_GOODS_SCORE_EXT_240502);
        }
    }

    @Override
    public List<GdsScoreExtRespDTO> queryGdsScoreExtByGds(GdsScoreExtReqDTO gdsScoreExtReqDTO) throws BusinessException {
        List<GdsScoreExtRespDTO> gdsScoreExtRespDTOs = new ArrayList<GdsScoreExtRespDTO>();
        try {
            GdsScoreExtCriteria example = new GdsScoreExtCriteria();

            GdsScoreExtCriteria.Criteria criteria = example.createCriteria();
            criteria.andGdsIdEqualTo(gdsScoreExtReqDTO.getGdsId());
            criteria.andShopIdEqualTo(gdsScoreExtReqDTO.getShopId());
            criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
            List<GdsScoreExt> gdsScoreExts = gdsScoreExtMapper.selectByExample(example);
            if (gdsScoreExts != null && gdsScoreExts.size() > 0) {
                for (GdsScoreExt gdsScoreExt : gdsScoreExts) {
                    GdsScoreExtRespDTO gdsScoreExtRespDTO = copyInfo2Resp(gdsScoreExt);
                    gdsScoreExtRespDTOs.add(gdsScoreExtRespDTO);
                }

            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "", e);
            throw new BusinessException(GdsErrorConstants.GdsScoreExt.ERROR_GOODS_SCORE_EXT_240503);
        }
        return gdsScoreExtRespDTOs;
    }

    
    @Override
    public GdsScoreExtRespDTO queryGdsScoreExtById(GdsScoreExtReqDTO gdsScoreExtReqDTO) throws BusinessException {
        try {
            GdsScoreExtRespDTO extRespDTO = new GdsScoreExtRespDTO();
            GdsScoreExtCriteria gdsScoreExtCriteria = new GdsScoreExtCriteria();
            GdsScoreExtCriteria.Criteria criteria = gdsScoreExtCriteria.createCriteria();
            criteria.andGdsIdEqualTo(gdsScoreExtReqDTO.getGdsId());
            criteria.andIdEqualTo(gdsScoreExtReqDTO.getId());
            List<GdsScoreExt> gdsScoreExtList = gdsScoreExtMapper.selectByExample(gdsScoreExtCriteria);

            if (gdsScoreExtList == null || gdsScoreExtList.size() == 0) {
                throw new BusinessException();
            } else {
                extRespDTO = copyInfo2Resp(gdsScoreExtList.get(0));
            }
            return extRespDTO;
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "", be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "", e);
            throw new BusinessException(GdsErrorConstants.GdsScoreExt.ERROR_GOODS_SCORE_EXT_240503);
        }
    }

    @Override
    public void saveGdsScoreExtList(List<GdsScoreExtReqDTO> extReqDTOs) throws BusinessException {
        try {
            for (GdsScoreExtReqDTO extReqDTO : extReqDTOs) {
                if (extReqDTO.getIfDefault() == null || "".equals(extReqDTO.getIfDefault())) {
                    extReqDTO.setIfDefault(GdsConstants.Commons.STATUS_INVALID);
                } else {
                    extReqDTO.setIfDefault(extReqDTO.getIfDefault());
                }
                if (extReqDTO.getId() == null || 0L == extReqDTO.getId()) {
                    addGdsScoreExt(extReqDTO);
                } else {
                    editGdsScoreExt(extReqDTO);
                }
            }
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "", be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "", e);
            throw new BusinessException(GdsErrorConstants.GdsScoreExt.ERROR_GOODS_SCORE_EXT_240504);
        }
    }

    /**
     * 
     * copyInfo2Resp:(将info对象转换为resp对象). <br/> 
     * 
     * @author linwb3
     * @param info
     * @return 
     * @since JDK 1.6
     */
    private GdsScoreExtRespDTO copyInfo2Resp(GdsScoreExt info) {
        GdsScoreExtRespDTO resp = new GdsScoreExtRespDTO();
        resp.setId(info.getId());
        resp.setShopId(info.getShopId());
        resp.setGdsId(info.getGdsId());
        resp.setScoreType(info.getScoreType());
        resp.setScore(info.getScore());
        resp.setPrice(info.getPrice());
        resp.setIfDefault(info.getIfDefault());
        resp.setStatus(info.getStatus());
        resp.setCreateStaff(info.getCreateStaff());
        resp.setCreateTime(info.getCreateTime());
        resp.setUpdateStaff(info.getUpdateStaff());
        resp.setUpdateTime(info.getUpdateTime());
        return resp;
    }

}
