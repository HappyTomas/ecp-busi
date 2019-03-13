package com.zengshi.ecp.goods.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dao.model.GdsInterfaceGds;
import com.zengshi.ecp.goods.dao.model.GdsInterfaceGdsGidx;
import com.zengshi.ecp.goods.dubbo.dto.GdsInterfaceGdsReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsInterfaceGdsRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsInterfaceGdsGidxReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsInterfaceGdsGidxRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IRealOriginalGdsCodeProcessor;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInterfaceGdsRSV;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsInterfaceGdsSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ObjectCopyUtil;

public class GdsInterfaceGdsRSVImpl implements IGdsInterfaceGdsRSV{
    
    @Resource
    private IGdsInterfaceGdsSV gdsInterfaceGdsSV;

    @Override
    public void saveGdsInterfaceGds(GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO)
            throws BusinessException {
        this.gdsInterfaceGdsSV.saveGdsInterfaceGds(gdsInterfaceGdsReqDTO);
    }

    @Override
    public void deleteGdsInterfaceGdsByOriginGdsId(GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO)
            throws BusinessException {
        this.gdsInterfaceGdsSV.deleteGdsInterfaceGdsByGdsInfoAndOriginGdsInfo(gdsInterfaceGdsReqDTO);
    }

    @Override
    public void updateGdsInterfaceGdsByOriginGdsId(GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO)
            throws BusinessException {
        this.gdsInterfaceGdsSV.updateGdsInterfaceGdsByGdsInfoAndOriginGdsInfo(gdsInterfaceGdsReqDTO);
    }

    @Override
    public GdsInterfaceGdsRespDTO queryGdsInterfaceGdsByOriginGdsId(
            GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO) throws BusinessException {
        GdsInterfaceGds gdsInterfaceGds=this.gdsInterfaceGdsSV.queryGdsInterfaceGdsByOriginGdsId(gdsInterfaceGdsReqDTO);
        
        if(gdsInterfaceGds==null){
            return null;
        }
        
        GdsInterfaceGdsRespDTO gdsInterfaceGdsRRespDTO=new GdsInterfaceGdsRespDTO();
        ObjectCopyUtil.copyObjValue(gdsInterfaceGds, gdsInterfaceGdsRRespDTO, null, false);
        
        return gdsInterfaceGdsRRespDTO;
    }

    /*@Override
    public GdsInterfaceGdsGidxRespDTO queryGdsInterfaceGdsGidxByEcpGdsId(GdsInterfaceGdsGidxReqDTO gdsInterfaceGdsGidxReqDTO) throws BusinessException {
        return this.queryGdsInterfaceGdsGidxByEcpGdsId(gdsInterfaceGdsGidxReqDTO,null);
    }*/

    @Override
    public GdsInterfaceGdsGidxRespDTO queryGdsInterfaceGdsGidxByEcpGdsId(
            GdsInterfaceGdsGidxReqDTO gdsInterfaceGdsGidxReqDTO,IRealOriginalGdsCodeProcessor gdsDataImportExternalize) throws BusinessException {
        GdsInterfaceGdsGidx gdsInterfaceGdsGidx=this.gdsInterfaceGdsSV.queryGdsInterfaceGdsGidxByEcpGdsId(gdsInterfaceGdsGidxReqDTO);
        
        if(gdsInterfaceGdsGidx==null){
            return null;
        }
        
        GdsInterfaceGdsGidxRespDTO gdsInterfaceGdsGidxRespDTO=new GdsInterfaceGdsGidxRespDTO();
        ObjectCopyUtil.copyObjValue(gdsInterfaceGdsGidx, gdsInterfaceGdsGidxRespDTO, null, false);

        if(gdsDataImportExternalize!=null){
            String realOriginalGdsCode=gdsDataImportExternalize.getRealOriginalGdsCode(gdsInterfaceGdsGidxRespDTO.getOriginGdsId());
            gdsInterfaceGdsGidxRespDTO.setOriginGdsId(realOriginalGdsCode);
            gdsInterfaceGdsGidxRespDTO.setOriginSkuId(realOriginalGdsCode);
        }
        
        return gdsInterfaceGdsGidxRespDTO;
    }

	@Override
	public List<GdsInterfaceGds> queryGdsInterfaceGdsByDate(GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO)
			throws BusinessException {
		// TODO Auto-generated method stub
		return gdsInterfaceGdsSV.queryGdsInterfaceGdsByDate(gdsInterfaceGdsReqDTO);
	}
    
}

