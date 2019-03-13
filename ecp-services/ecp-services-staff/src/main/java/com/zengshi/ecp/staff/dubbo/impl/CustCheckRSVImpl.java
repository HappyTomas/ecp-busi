package com.zengshi.ecp.staff.dubbo.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.mapper.busi.CustAuthstrMapper;
import com.zengshi.ecp.staff.dao.model.CustAuthstr;
import com.zengshi.ecp.staff.dubbo.dto.CustAuthstrReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAuthstrResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustCheckRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.facade.interfaces.eventual.ICompanyMainSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.ICustCheckSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.paas.utils.ThreadId;

public class CustCheckRSVImpl implements ICustCheckRSV {

    @Resource(name = "custCheckSV")
    private ICustCheckSV checkSV;

    @Resource(name="companyTxMainSV")
    private ICompanyMainSV companyMainSV;
    
    private static Logger logger = LoggerFactory.getLogger(CustCheckRSVImpl.class);

    @Override
    public PageResponseDTO<CustAuthstrResDTO> queryCustCheckList(CustAuthstrReqDTO info)
            throws BusinessException {
        PageResponseDTO<CustAuthstrResDTO> pageinfo = new PageResponseDTO<CustAuthstrResDTO>();
        try {
            pageinfo = checkSV.queryCustCheckList(info);
        } catch (Exception e) {
            logger.error("ThreadId:[" + ThreadId.getThreadId() + "];查询custAuthstr异常：", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR,
                    new String[] { "T_CUST_AUTHSTR" });
        }

        return pageinfo;

    }

    @Override
    public void checkCustToCompanyCust(CustAuthstrReqDTO info) throws BusinessException {

        if (StringUtil.isBlank(StringUtil.toString(info.getStaffId()))) {

            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,
                    new String[] { "staff_id" });
        }
        companyMainSV.CustCompanyBuildStock(info);

    }

    @Override
    public CustAuthstrResDTO queryCustAuthstr(CustAuthstrReqDTO info) throws BusinessException {

        CustAuthstrResDTO dto = new CustAuthstrResDTO();

        try {
            dto = checkSV.queryCustAuthser(info);
        } catch (Exception e) {
            logger.error("ThreadId:[" + ThreadId.getThreadId() + "];查询单个custAuthstr异常：", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR,
                    new String[] { "T_CUST_AUTHSTR" });
        }

        return dto;
    }

    @Override
    public void checkNoCustToCompanyCust(CustAuthstrReqDTO info) throws BusinessException {
        info.setCheckStaff(info.getStaff().getId());

        if (StringUtil.isBlank(StringUtil.toString(info.getId()))) {

            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[] { "id" });
        }
        if (StringUtil.isBlank(StringUtil.toString(info.getCheckStaff()))) {

            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,
                    new String[] { "check_staff" });
        }
        if (StringUtil.isBlank(info.getCheckRemark())) {

            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,
                    new String[] { "check_remark" });
        }

        try {
            checkSV.updateNoCustToCompanyCust(info);
        } catch (Exception e) {
            logger.error("ThreadId:[" + ThreadId.getThreadId() + "];审核不通过CustAuthstr异常：", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR,
                    new String[] { "T_CUST_AUTHSTR" });
        }

    }

    @Override
    public void removeCustAuthstr(CustAuthstrReqDTO info) throws BusinessException {

        if (StringUtil.isBlank(StringUtil.toString(info.getId()))) {

            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[] { "id" });
        }
        try {
            checkSV.removeCustAuthstr(info);
        } catch (Exception e) {
            logger.error("ThreadId:[" + ThreadId.getThreadId() + "];删除CustAuthstr异常：", e);
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR,
                    new String[] { "T_CUST_AUTHSTR" });
        }

    }

    @Override
    public int saveCustAuthstr(CustAuthstrReqDTO info) throws BusinessException {
        return checkSV.saveCustAuthstr(info);
    }

    @Override
	public int updateCustAuthstr(CustAuthstrReqDTO info) throws BusinessException {
    	 return checkSV.updateCustAuthstr(info);
	}
}
