package com.zengshi.ecp.staff.service.busi.custinfo.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.mapper.busi.CustThirdCodeMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.CustThirdIdxMapper;
import com.zengshi.ecp.staff.dao.model.CustThirdCode;
import com.zengshi.ecp.staff.dao.model.CustThirdCodeCriteria;
import com.zengshi.ecp.staff.dao.model.CustThirdCodeCriteria.Criteria;
import com.zengshi.ecp.staff.dao.model.CustThirdIdx;
import com.zengshi.ecp.staff.dao.model.CustThirdIdxCriteria;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustThirdCodeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustThirdCodeResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.custinfo.interfaces.ICustThirdSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.ICustManageSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2016年2月22日上午11:10:18 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version
 * @since JDK 1.7
 */
public class CustThirdSVImpl implements ICustThirdSV {

    private static String MODULE = CustThirdSVImpl.class.getName();

    @Resource
    private CustThirdCodeMapper custThirdCodeMapper;

    @Resource
    private CustThirdIdxMapper custThirdIdxMapper;

    @Resource(name = "seq_cust_third_code")
    private Sequence seq_cust_third_code;

    @Resource(name = "seq_cust_third_idx")
    private Sequence seq_cust_third_idx;
    
    @Resource
    private ICustManageSV custManageSV;

    @Override
    public void saveCustThirdCode(CustThirdCodeReqDTO custThirdCodeReqDTO) throws BusinessException {
        //判断淘宝号是否已经被绑定
        checkCustThird(custThirdCodeReqDTO);
        //重复绑定，就删除原来的数据
        updateCustThirdCode(custThirdCodeReqDTO);
        CustThirdCode custThirdCode = new CustThirdCode();
        ObjectCopyUtil.copyObjValue(custThirdCodeReqDTO, custThirdCode, null, false);
        custThirdCode.setId(seq_cust_third_code.nextValue());
        custThirdCode.setStatus(StaffConstants.STAFF_STATUS_VALID);
        custThirdCode.setCreateTime(DateUtil.getSysDate());
        custThirdCode.setCreateStaff(custThirdCodeReqDTO.getStaff().getId());
        try {
            custThirdCodeMapper.insertSelective(custThirdCode);
        } catch (Exception e) {
            LogUtil.error(MODULE, "inset into t_cust_third_code is error", e);
        }

        CustThirdIdx custThirdIdx = new CustThirdIdx();
        custThirdIdx.setId(seq_cust_third_idx.nextValue());
        custThirdIdx.setStaffId(custThirdCodeReqDTO.getStaffId());
        custThirdIdx.setThirdCode(custThirdCodeReqDTO.getThirdCode());
        custThirdIdx.setThirdCodeType(custThirdCodeReqDTO.getThirdCodeType());
        try {
            custThirdIdxMapper.insert(custThirdIdx);
        } catch (Exception e) {
            LogUtil.error(MODULE, "insert into t_cust_third_idx", e);
        }

    }
    
    public void checkCustThird(CustThirdCodeReqDTO custThirdCodeReqDTO)throws BusinessException{
        
        CustThirdIdxCriteria c =  new CustThirdIdxCriteria();
        c.createCriteria().andThirdCodeEqualTo(custThirdCodeReqDTO.getThirdCode()).andStaffIdNotEqualTo(custThirdCodeReqDTO.getStaffId()).andThirdCodeTypeEqualTo(StaffConstants.custInfo.CUST_THIRD_CODE_TYPE_TMALL);
        List<CustThirdIdx> list = custThirdIdxMapper.selectByExample(c);
        if(!CollectionUtils.isEmpty(list)){
            new BusinessException("staff.100054",new String[]{custThirdCodeReqDTO.getThirdCode()});
        }
        
    }
    
    public void updateCustThirdCode(CustThirdCodeReqDTO custThirdCodeReqDTO) throws BusinessException{
        
        CustThirdCodeCriteria c = new CustThirdCodeCriteria();
        c.createCriteria().andStaffIdEqualTo(custThirdCodeReqDTO.getStaffId()).andThirdCodeTypeEqualTo(custThirdCodeReqDTO.getThirdCodeType());
        List<CustThirdCode> list = custThirdCodeMapper.selectByExample(c);
        if(!CollectionUtils.isEmpty(list)){
            CustThirdCodeCriteria cr = new CustThirdCodeCriteria();
            cr.createCriteria().andStaffIdEqualTo(list.get(0).getStaffId()).andThirdCodeTypeEqualTo(custThirdCodeReqDTO.getThirdCodeType());
            custThirdCodeMapper.deleteByExample(cr);
            
            CustThirdIdxCriteria idxc = new CustThirdIdxCriteria();
            idxc.createCriteria().andThirdCodeEqualTo(list.get(0).getThirdCode()).andThirdCodeTypeEqualTo(list.get(0).getThirdCodeType()).andStaffIdEqualTo(list.get(0).getStaffId());
            custThirdIdxMapper.deleteByExample(idxc);
            
        }
    }
    

    @Override
    public CustThirdCodeResDTO queryCustThirdCode(CustThirdCodeReqDTO custThirdCodeReqDTO)
            throws BusinessException {
        CustThirdCodeCriteria c = new CustThirdCodeCriteria();
        Criteria sql = c.createCriteria();
        
        if (custThirdCodeReqDTO.getStaffId() != null && custThirdCodeReqDTO.getStaffId() != 0L) {
        	sql.andStaffIdEqualTo(custThirdCodeReqDTO.getStaffId());
        }
        if(StringUtil.isNotBlank(custThirdCodeReqDTO.getThirdCodeType())){
            sql.andThirdCodeTypeEqualTo(custThirdCodeReqDTO.getThirdCodeType());
        }
        if (StringUtil.isNotBlank(custThirdCodeReqDTO.getThirdCode())) {
        	sql.andThirdCodeEqualTo(custThirdCodeReqDTO.getThirdCode());
        }
        List<CustThirdCode> list = custThirdCodeMapper.selectByExample(c);
        if (!CollectionUtils.isEmpty(list)) {
            CustThirdCodeResDTO resdto = new CustThirdCodeResDTO();
            ObjectCopyUtil.copyObjValue(list.get(0), resdto, null, false);
            CustInfoResDTO cust = custManageSV.findCustInfoById(resdto.getStaffId());
            resdto.setStaffCode(cust == null ? "" : cust.getStaffCode());
            return resdto;
        }

        return null;
    }

}
