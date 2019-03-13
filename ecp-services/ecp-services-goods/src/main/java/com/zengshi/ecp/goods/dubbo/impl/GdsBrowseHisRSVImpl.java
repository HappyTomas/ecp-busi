package com.zengshi.ecp.goods.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsBrowseHisReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsBrowseHisRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsBrowseHisRSV;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsBrowseHisSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;

public class GdsBrowseHisRSVImpl implements IGdsBrowseHisRSV {
    @Resource(name = "gdsBrowseHisSV")
    IGdsBrowseHisSV browseHisSV;

    private static final String MODULE = GdsBrowseHisRSVImpl.class.getName();

    @Override
    public PageResponseDTO<GdsBrowseHisRespDTO> queryGdsBrowseHisByPage(
            GdsBrowseHisReqDTO browseHisReqDTO) throws BusinessException {
        try {
            return browseHisSV.queryGdsBrowseHisByPage(browseHisReqDTO);
        } catch (BusinessException e) {
            throw e;

        } catch (Exception e) {
            LogUtil.error(MODULE, "查询用户浏览记录失败", e);

            throw new BusinessException(GdsErrorConstants.GdsBrowseHis.ERROR_GOODS_BROWSE_HIS_240402);
        }
    }

    @Override
    public void deleteGdsBrowseHisClear(GdsBrowseHisReqDTO browseHisReqDTO)
            throws BusinessException {
        
        try {
            browseHisSV.deleteGdsBrowseHisClear(browseHisReqDTO);
            
        }catch (BusinessException e) {
            throw e;

        } catch (Exception e) {
            LogUtil.error(MODULE, "定时清理用户浏览记录失败", e);

            throw new BusinessException(GdsErrorConstants.GdsBrowseHis.ERROR_GOODS_BROWSE_HIS_240402);
        }
    }

}
