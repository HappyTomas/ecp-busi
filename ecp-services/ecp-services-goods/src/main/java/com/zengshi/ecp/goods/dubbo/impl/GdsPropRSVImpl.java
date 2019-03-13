/** 
 * Project Name:ecp-services-goods 
 * File Name:GdsPropRSVImpl.java 
 * Package Name:com.zengshi.ecp.goods.dubbo.impl 
 * Date:2015年8月28日下午2:35:02 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsPropRSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropValueSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title:属性Dubbo服务实现类. <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月28日下午2:35:02  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class GdsPropRSVImpl extends AbstractRSVImpl implements IGdsPropRSV {

    @Resource(name="gdsPropSV")
    private IGdsPropSV gdsPropSV;
    @Resource(name="gdsPropValueSV")
    private IGdsPropValueSV gdsPropValueSV;
    /** 
     * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsPropRSV#createGdsProp(com.zengshi.ecp.goods.dubbo.dto.GdsPropReqDTO) 
     */
    @Override
    public void createGdsProp(GdsPropReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO, true);
        paramNullCheck(reqDTO.getPropName(), false, "reqDTO.propName");
        gdsPropSV.saveGdsProp(reqDTO);
    }

    /** 
     * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsPropRSV#editGdsProp(com.zengshi.ecp.goods.dubbo.dto.GdsPropReqDTO) 
     */
    @Override
    public int editGdsProp(GdsPropReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO, true);
        paramNullCheck(reqDTO.getId(), false, "reqDTO.id");
        paramNullCheck(reqDTO, false, "reqDTO.propName");
        return gdsPropSV.editGdsProp(reqDTO);
    }

    /** 
     * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsPropRSV#queryGdsPropPaging(com.zengshi.ecp.goods.dubbo.dto.GdsPropReqDTO) 
     */
    @Override
    public PageResponseDTO<GdsPropRespDTO> queryGdsPropPaging(GdsPropReqDTO reqDTO)
            throws BusinessException {
        paramNullCheck(reqDTO, false);
        return gdsPropSV.queryGdsPropRespDTOPaging(reqDTO, null, null);
    }

    /** 
     * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsPropRSV#addPropValue(com.zengshi.ecp.goods.dubbo.dto.GdsPropValueReqDTO) 
     */
    @Override
    public int addPropValue(GdsPropValueReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO, true);
        paramNullCheck(reqDTO.getId(), false, "reqDTO.id");
        paramNullCheck(reqDTO.getPropId(), false, "reqDTO.propId");
        return gdsPropValueSV.saveGdsPropValue(reqDTO);
    }

    /** 
     * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsPropRSV#queryGdsPropValuePaging(com.zengshi.ecp.goods.dubbo.dto.GdsPropValueReqDTO) 
     */
    @Override
    public PageResponseDTO<GdsPropValueRespDTO> queryGdsPropValuePaging(GdsPropValueReqDTO reqDTO)
            throws BusinessException {
        paramNullCheck(reqDTO, false);
        paramNullCheck(reqDTO.getPropId(), false, "reqDTO.propId");
        return gdsPropValueSV.queryGdsPropValueRespDTOPaging(reqDTO);
    }

    @Override
    public boolean queryExistPropValue(GdsPropValueReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO, false);
        paramNullCheck(reqDTO.getPropId(), false, "reqDTO.propId");
        paramNullCheck(reqDTO.getPropValue(), false, "reqDTO.propValue");
        return gdsPropValueSV.queryExists(reqDTO.getPropValue(), reqDTO.getPropId(), GdsConstants.Commons.STATUS_VALID);
    }

    @Override
    public int executeDisableGdsPropByPK(LongReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO, true);
        paramNullCheck(reqDTO.getId(), false, "reqDTO.id");
        return gdsPropSV.deleteGdsProp(reqDTO.getId(), reqDTO.getStaff().getId());
    }

    @Override
    public int executeEnableGdsPropByPK(LongReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO, true);
        paramNullCheck(reqDTO.getId(), false, "reqDTO.id");
        return gdsPropSV.executeEnableGdsProp(reqDTO.getId(), reqDTO.getStaff().getId());
    }

	@Override
	public boolean queryIsPropInUse(GdsCatg2PropReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO);
        paramNullCheck(reqDTO.getPropId(), "reqDTO.propId");
        return gdsPropSV.queryIsPropInUse(reqDTO);
	}

	@Override
	public PageResponseDTO<GdsPropRespDTO> queryGdsPropPagingInOrNot(GdsPropReqDTO reqDTO,List<Long> excludeIds, List<Long> includeIds) throws BusinessException {
		paramNullCheck(reqDTO, false);
        return gdsPropSV.queryGdsPropRespDTOPaging(reqDTO, excludeIds, includeIds);
	}

}

