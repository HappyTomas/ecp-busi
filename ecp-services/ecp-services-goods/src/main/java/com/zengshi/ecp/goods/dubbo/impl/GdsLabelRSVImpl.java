package com.zengshi.ecp.goods.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dubbo.dto.GdsLabelReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsLabelRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsLabelRSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsLabelSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
/**
 * 商品标签服务
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015-9-16下午5:27:52  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author zjh
 * @version  
 * @since JDK 1.6
 */
public class GdsLabelRSVImpl extends AbstractRSVImpl implements IGdsLabelRSV{
    
    @Resource(name="gdsLabelSV") 
    IGdsLabelSV gdsLabelSV;
    @Override
    public GdsLabelReqDTO saveGdsLabel(GdsLabelReqDTO gdsLabel) throws BusinessException {
        paramNullCheck(gdsLabel, true);
        return gdsLabelSV.saveGdsLabel(gdsLabel);
    }

    @Override
    public PageResponseDTO<GdsLabelRespDTO> queryGdsLabelRespDTOPaging(GdsLabelReqDTO dto)
            throws BusinessException {
        paramNullCheck(dto, true);
        return gdsLabelSV.queryGdsLabelRespDTOPaging(dto);
    }

    @Override
    public GdsLabelReqDTO editGdsLabel(GdsLabelReqDTO gdsLabel, Long updateStaff) throws BusinessException {
        paramNullCheck(gdsLabel, true);
        paramNullCheck(updateStaff, true);
        paramNullCheck(gdsLabel.getId(),true);
        return gdsLabelSV.editGdsLabel(gdsLabel, updateStaff);
    }

    @Override
    public int editStatus(Long labelId, String status, Long updateStaff) throws BusinessException {
        paramNullCheck(labelId, true);
        paramNullCheck(status, true);
        paramNullCheck(updateStaff, true);
        return gdsLabelSV.editStatus(labelId, status, updateStaff);
    }

    @Override
    public int executeDisableGdsLabel(Long labelId, Long updateStaff) throws BusinessException {
        paramNullCheck(labelId, true);
        paramNullCheck(updateStaff, true);
        return gdsLabelSV.executeDisableGdsLabel(labelId,  updateStaff);
    }

    @Override
    public int executeEnableGdsLabel(Long labelId, Long updateStaff) throws BusinessException {
        paramNullCheck(labelId, true);
        paramNullCheck(updateStaff, true);
        return gdsLabelSV.executeEnableGdsLabel(labelId, updateStaff);
    }

    @Override
    public boolean queryExist(String labelType, String labelTitle, String... status)
            throws BusinessException {
        paramNullCheck(labelType, true);
        paramNullCheck(status, true);
        paramNullCheck(labelTitle, true);
        return gdsLabelSV.queryExist(labelType, labelTitle, status);
    }

	@Override
	public GdsLabelReqDTO editGdsLabel(GdsLabelReqDTO gdsLabel)
			throws BusinessException {
		paramNullCheck(gdsLabel, true, "gdsLabel");
		return gdsLabelSV.editGdsLabel(gdsLabel);
	}

	@Override
	public int editStatus(GdsLabelReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO, true);
        paramNullCheck(reqDTO.getId(), "reqDTO.id");
        paramNullCheck(reqDTO.getStatus(), "reqDTO.status");
        return gdsLabelSV.editStatus(reqDTO.getId()	, reqDTO.getStatus(), reqDTO.getStaff().getId());
	}

	@Override
	public int executeDisableGdsLabel(GdsLabelReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO, true);
		paramNullCheck(reqDTO.getId(), "reqDTO.id");
        return gdsLabelSV.executeDisableGdsLabel(reqDTO.getId(),  reqDTO.getStaff().getId());
	}

	@Override
	public int executeEnableGdsLabel(GdsLabelReqDTO reqDTO)
			throws BusinessException {
		    paramNullCheck(reqDTO, true);
	        paramNullCheck(reqDTO.getId(), "reqDTO.id");
	        return gdsLabelSV.executeEnableGdsLabel(reqDTO.getId(), reqDTO.getStaff().getId());
	}

	@Override
	public boolean queryExist(GdsLabelReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, true);
		paramCheck(new Object[]{reqDTO.getLabelType(),reqDTO.getLabelTitle(),reqDTO.getStatus()}, new String[]{"reqDTO.labelType","reqDTO.labelTitle","reqDTO.status"});
        return gdsLabelSV.queryExist(reqDTO.getLabelType(), reqDTO.getLabelTitle(), reqDTO.getStatus());
	}
    
    

}

