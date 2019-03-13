/** 
 * Project Name:ecp-services-staff-server 
 * File Name:ShopAddrSVImpl.java 
 * Package Name:com.zengshi.ecp.staff.service.busi.shop.impl 
 * Date:2015年9月16日下午7:59:56 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.staff.service.busi.shop.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dao.mapper.busi.StaffPayShopCfgMapper;
import com.zengshi.ecp.staff.dao.model.PayShopCfg;
import com.zengshi.ecp.staff.dao.model.PayShopCfgCriteria;
import com.zengshi.ecp.staff.dao.model.PayShopCfgCriteria.Criteria;
import com.zengshi.ecp.staff.dubbo.dto.PayShopCfgReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.PayShopCfgResDTO;
import com.zengshi.ecp.staff.dubbo.dto.PayWayReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.PayWayResDTO;
import com.zengshi.ecp.staff.service.busi.shop.interfaces.IShopPayWayCfgSV;
import com.zengshi.ecp.staff.service.common.pay.interfaces.IShopPayWaySV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: <br>
 * Date:2015年9月16日下午7:59:56  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6 
 */
public class ShopPayWayCfgSVImpl extends GeneralSQLSVImpl  implements IShopPayWayCfgSV {

    private static final String MODULE = ShopPayWayCfgSVImpl.class.getName();
    
    @Resource
    private IShopPayWaySV shopPayWaySV;
    
    @Resource
    private StaffPayShopCfgMapper staffPayShopCfgMapper;

    @Override
    public PageResponseDTO<PayShopCfgResDTO> queryPayShopCfgList(PayShopCfgReqDTO payShopCfgReqDTO)
            throws BusinessException {
        PayShopCfgCriteria criteria = new PayShopCfgCriteria();
        Criteria sql = criteria.createCriteria();
        if(StringUtil.isNotBlank(payShopCfgReqDTO.getPayWay())){
            sql.andPayWayEqualTo(payShopCfgReqDTO.getPayWay());
        }
        
        if(null!=payShopCfgReqDTO.getShopId()&&0!=payShopCfgReqDTO.getShopId()){
            sql.andShopIdEqualTo(payShopCfgReqDTO.getShopId());
        }
        
        criteria.setLimitClauseCount(payShopCfgReqDTO.getPageSize());
        criteria.setLimitClauseStart(payShopCfgReqDTO.getStartRowIndex());
        criteria.setOrderByClause("CREATE_TIME DESC");
        
        return super.queryByPagination(payShopCfgReqDTO, criteria, true, new PaginationCallback<PayShopCfg, PayShopCfgResDTO>(){

            @Override
            public List<PayShopCfg> queryDB(BaseCriteria basecriteria) {
                return staffPayShopCfgMapper.selectByExample((PayShopCfgCriteria)basecriteria);
            }

            @Override
            public long queryTotal(BaseCriteria basecriteria) {
                return staffPayShopCfgMapper.countByExample((PayShopCfgCriteria)basecriteria);
            }
        
            @Override
            public PayShopCfgResDTO warpReturnObject(PayShopCfg info) {
                PayShopCfgResDTO sDto = new PayShopCfgResDTO();
                ObjectCopyUtil.copyObjValue(info, sDto, null, false);
                PayWayReqDTO payWayReqDTO = new PayWayReqDTO();
                payWayReqDTO.setId(sDto.getPayWay());
                PayWayResDTO payWayResDTO =  shopPayWaySV.getPayWayName(payWayReqDTO);
                sDto.setPayWayName(payWayResDTO.getPayWayName());
                sDto.setCerNameUrl(ImageUtil.getStaticDocUrl(sDto.getCerName(), "doc"));
                sDto.setKeyNameUrl(ImageUtil.getStaticDocUrl(sDto.getKeyName(), "doc"));
                return sDto;
            }
            
        });
    }

    @Override
    public int addPayShopCfg(PayShopCfgReqDTO payShopCfgReqDTO) throws BusinessException {
        
        PayShopCfg payShopCfg = new PayShopCfg();
        ObjectCopyUtil.copyObjValue(payShopCfgReqDTO, payShopCfg, null, false);
        payShopCfg.setCreateTime(DateUtil.getSysDate());
        payShopCfg.setCreateStaff(payShopCfgReqDTO.getStaff().getId());
        return staffPayShopCfgMapper.insertSelective(payShopCfg);
    }

    @Override
    public int updatePayShopCfg(PayShopCfgReqDTO payShopCfgReqDTO) throws BusinessException {
        
        PayShopCfg payShopCfg = new PayShopCfg();
        ObjectCopyUtil.copyObjValue(payShopCfgReqDTO, payShopCfg, null, false);
        PayShopCfgCriteria payShopCfgCriteria = new PayShopCfgCriteria();
        payShopCfgCriteria.createCriteria().andPayWayEqualTo(payShopCfgReqDTO.getPayWay()).andShopIdEqualTo(payShopCfgReqDTO.getShopId());
        payShopCfg.setUpdateTime(DateUtil.getSysDate());
        payShopCfg.setUpdateStaff(payShopCfgReqDTO.getStaff().getId());
        int i = staffPayShopCfgMapper.updateByExampleSelective(payShopCfg, payShopCfgCriteria);
        return i;
    }
    
   

}

