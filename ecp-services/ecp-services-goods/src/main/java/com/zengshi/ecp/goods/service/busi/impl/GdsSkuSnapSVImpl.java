package com.zengshi.ecp.goods.service.busi.impl;

import java.sql.Timestamp;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsSkuInfoMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsSkuSnapMapper;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfo;
import com.zengshi.ecp.goods.dao.model.GdsSkuSnap;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsSkuSnapSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoQuerySV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ObjectCopyUtil;

public class GdsSkuSnapSVImpl implements IGdsSkuSnapSV {
    // -----------序列服务-----------

    @Resource(name = "seq_gds_sku_snap")
    private PaasSequence seqGdsSkuSnap;

    @Resource
    private GdsSkuInfoMapper gdsSkuInfoMapper;

    @Resource
    private IGdsSkuInfoQuerySV gdsSkuInfoQuerySV;

    // -----------快照服务-----------

    @Resource
    private GdsSkuSnapMapper gdsSkuSnapMapper;

    @Override
    public Long addGdsSkuSnapInfo(GdsSkuInfoReqDTO gdsSkuInfoReqDTO) throws Exception {

        // 快照统一主键
        Long id = this.seqGdsSkuSnap.nextValue();
        GdsSkuInfo gdsSkuInfo = this.gdsSkuInfoMapper.selectByPrimaryKey(gdsSkuInfoReqDTO.getId());

        GdsSkuInfoReqDTO req=new GdsSkuInfoReqDTO();
        req.setId(gdsSkuInfoReqDTO.getId());
        GdsSkuInfoRespDTO gdsSkuInfoRespDTO = gdsSkuInfoQuerySV
                .querySkuInfoByOptions(req, new SkuQueryOption[] { SkuQueryOption.MAINPIC, SkuQueryOption.PRICE, SkuQueryOption.SHOWPRICE });
        // 商品和单品数据必须存在
        if (gdsSkuInfo == null) {
            throw new BusinessException("");
        }

        GdsSkuSnap gdsSkuSnap = new GdsSkuSnap();
        ObjectCopyUtil.copyObjValue(gdsSkuInfo, gdsSkuSnap, null, false);
        gdsSkuSnap.setId(id);
        gdsSkuSnap.setSkuId(gdsSkuInfo.getId());
        if (gdsSkuInfoRespDTO.getMainPic() != null)
            gdsSkuSnap.setMediaUuid(gdsSkuInfoRespDTO.getMainPic().getMediaUuid());
        gdsSkuSnap.setSkuPrice(gdsSkuInfoRespDTO.getRealPrice());
        gdsSkuSnap.setCreateTime(new Timestamp(System.currentTimeMillis()));
        gdsSkuSnap.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        gdsSkuSnap.setCreateStaff(gdsSkuInfoReqDTO.getStaff().getId());
        gdsSkuSnap.setUpdateStaff(gdsSkuInfoReqDTO.getStaff().getId());
        gdsSkuSnapMapper.insertSelective(gdsSkuSnap);
        return id;

    }

}
