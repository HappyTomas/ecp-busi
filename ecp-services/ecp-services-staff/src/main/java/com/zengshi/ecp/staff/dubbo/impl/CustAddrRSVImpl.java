package com.zengshi.ecp.staff.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseAreaAdminUtil;
import com.zengshi.ecp.staff.dao.model.CustAddr;
import com.zengshi.ecp.staff.dubbo.dto.CustAddrReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAddrResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustAddrRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.custaddr.interfaces.ICustAddrSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.paas.utils.ThreadId;

public class CustAddrRSVImpl implements ICustAddrRSV
{
    @Resource(name="custAddrSV")
    private ICustAddrSV custAddrService;
    private static final String MODULE = CustAddrRSVImpl.class.getName();

    private static long CUST_ADDR_NUM_LIMIT = 10;
    @Override
    public PageResponseDTO<CustAddrResDTO>  listCustAddrPage(CustAddrReqDTO pRequestDTO) throws BusinessException
    {
        //1.执行参数校验规则
        if(pRequestDTO == null || pRequestDTO.getId() <= 0 || pRequestDTO.getStaffId() <= 0)
        {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        //2.创建返回值list
        PageResponseDTO<CustAddrResDTO>   result = null;
        //3.根据用户ID查询该用户收获地址
        try {
            result =  custAddrService.listCustAddr(pRequestDTO);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.info(MODULE, "ThreadId:["+ThreadId.getThreadId()+"];查询用户收货地址出现异常：", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{pRequestDTO.toString()});
        }

        return result;
    }

    @Override
    public CustAddrResDTO saveCustAddr(CustAddrReqDTO addrinfo) throws BusinessException 
    {
        //执行参数校验规则
        if(addrinfo == null || addrinfo.getStaffId() == null)
        {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        Long staffID = addrinfo.getStaffId();
        //如果当前用户收货地址总数大于等于20个，则抛出异常
        if(custAddrService.countCustAddrNum(staffID) >= CUST_ADDR_NUM_LIMIT)
        {
            LogUtil.info(MODULE, "ThreadId:["+ThreadId.getThreadId()+"];保存用户收货地址出现异常，用户地址总数超过10条!");
            throw new BusinessException(StaffConstants.custAddr.ADDR_NUMBER_ERROR);   
        }
        CustAddr addrinfoModle = new CustAddr();
        ObjectCopyUtil.copyObjValue(addrinfo, addrinfoModle, null, false);
        addrinfoModle.setCreateStaff(addrinfo.getStaff().getId());
        addrinfoModle.setUpdateStaff(addrinfo.getStaff().getId());
        Long addrid = 0L;
        try {
            addrid = custAddrService.saveCustAddr(addrinfoModle);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.info(MODULE, "ThreadId:["+ThreadId.getThreadId()+"];保存用户收货地址出现异常:", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR);
        }
        //填补地址id
        addrinfo.setId(addrid);
        CustAddrResDTO resdto = new CustAddrResDTO();
        ObjectCopyUtil.copyObjValue(addrinfo, resdto, null, false);
        
        return resdto;
    }

    @Override
    public void updateCustAddr(CustAddrReqDTO addrinfo) throws BusinessException 
    {
        //执行参数校验规则
        if(addrinfo == null || addrinfo.getStaffId() == null || addrinfo.getId() == null)
        {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        
        CustAddr addrinfoModle = new CustAddr();
        ObjectCopyUtil.copyObjValue(addrinfo, addrinfoModle, null, false);
        addrinfoModle.setUpdateStaff(addrinfo.getStaff().getId());
        try {
            custAddrService.updateCustAddr(addrinfoModle);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.info(MODULE, "ThreadId:["+ThreadId.getThreadId()+"];更新用户收货地址出现异常：", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR);
        }
        
    }

    @Override
    public void deleteCustAddr(CustAddrReqDTO custinfo) throws BusinessException 
    {
        //执行参数校验规则
        //执行参数校验规则
        if(custinfo == null || custinfo.getStaffId() == null || custinfo.getId() == null)
        {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        try {
            custAddrService.deleteCustAddr(custinfo.getId(), custinfo.getStaffId());
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.info(MODULE, "ThreadId:["+ThreadId.getThreadId()+"];删除用户收货地址出现异常：", e);
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR);
        }

    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.staff.dubbo.interfaces.ICustAddrRSV#listCustAddr(java.lang.Long) 
     */
    @Override
    public List<CustAddrResDTO> listCustAddr(CustAddrReqDTO reqDTO) throws BusinessException {
        if(reqDTO == null)
        {
            LogUtil.info(MODULE, "=========查询用户收获地址列表入参无效=============");
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);  
        }
        Long pStaffId = reqDTO.getStaffId();
        if(pStaffId == null)
        {
            LogUtil.info(MODULE, "=========查询用户收获地址列表入参无效=============");
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"======该用户ID入参无效======"});
        }
        List<CustAddrResDTO> records = null;
        try {
            records = custAddrService.listCustAddr(pStaffId);
            //拼接一个pccName：省分+地市+区县
            if (!CollectionUtils.isEmpty(records)) {
                for (CustAddrResDTO res : records) {
                    String province = "";
                    String city = "";
                    String county = "";
                    if (StringUtil.isNotBlank(res.getProvince())) {
                        province = BaseAreaAdminUtil.fetchAreaName(res.getProvince());//省份
                    }
                    if (StringUtil.isNotBlank(res.getCityCode())) {
                        city = BaseAreaAdminUtil.fetchAreaName(res.getCityCode());//地市
                    }
                    if (StringUtil.isNotBlank(res.getCountyCode())) {
                        county = BaseAreaAdminUtil.fetchAreaName(res.getCountyCode());//区县
                    }
                    res.setPccName(province + city + county);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.info(MODULE, "======根据用户Id["+pStaffId+"]查询收货地址列表失败======");
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{"======根据用户Id["+pStaffId+"]查询收货地址列表失败======"});
        }

        return records;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.staff.dubbo.interfaces.ICustAddrRSV#findDefaultAddr(java.lang.Long) 
     */
    @Override
    public CustAddrResDTO findDefaultAddr(CustAddrReqDTO custinfo) throws BusinessException {
        if(custinfo == null)
        {
            LogUtil.info(MODULE, "=========查询用户默认收货地址入参无效=============");
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);  
        }
        Long pStaffId = custinfo.getStaffId();
        if(pStaffId == null)
        {
            LogUtil.info(MODULE, "=========查询用户默认收货地址入参无效=============");
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"======该用户ID入参无效======"});
        }
        CustAddr record = null;
        try {
            record = custAddrService.findDefaultAddr(pStaffId);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.info(MODULE, "======根据用户Id["+pStaffId+"]查询默认收货地址失败======");
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{"======根据用户Id["+pStaffId+"]查询默认收货地址失败======"});
        }
        if(record != null)
        {
            CustAddrResDTO dto = new CustAddrResDTO();
            ObjectCopyUtil.copyObjValue(record, dto, null, false);
            return dto;
        }
        return null;
    }

    /** 
     * TODO 根据地址id与用户staffid找出用户收货地址. 
     * @see com.zengshi.ecp.staff.dubbo.interfaces.ICustAddrRSV#findAddr(com.zengshi.ecp.staff.dubbo.dto.CustAddrReqDTO) 
     */
    @Override
    public CustAddrResDTO findAddr(CustAddrReqDTO custinfo) throws BusinessException {
        if(custinfo == null)
        {
            LogUtil.info(MODULE, "=========获取用户收货地址入参无效=============");
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);  
        }
        Long pStaffId = custinfo.getStaffId();
        if(pStaffId == null)
        {
            LogUtil.info(MODULE, "=========获取用户收货地址入参无效=============");
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"======该用户ID入参无效======"});
        }
        
        CustAddr custAddr = new CustAddr();
        try {
            custAddr = custAddrService.findCustAddr(custinfo.getId(), custinfo.getStaffId());
        } catch (Exception e) {
            // TODO: handle exception
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{"======根据用户Id["+custinfo.getStaffId()+"]查询默认收货地址失败======"});
        }
        if(custAddr != null)
        {
            CustAddrResDTO res = new CustAddrResDTO();
            ObjectCopyUtil.copyObjValue(custAddr, res, null, false);
            return res;
        }
        return null;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.staff.dubbo.interfaces.ICustAddrRSV#installDefauleAddr(com.zengshi.ecp.staff.dubbo.dto.CustAddrReqDTO) 
     */
    @Override
    public int installDefauleAddr(CustAddrReqDTO addrinfo) throws BusinessException {
        if(addrinfo == null || addrinfo.getStaffId() == null || addrinfo.getId() == null)
        {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        
        try {
            custAddrService.updateToDefaultAddr(addrinfo.getId(), addrinfo.getStaffId());
        } catch (Exception e) {
            // TODO: handle exception
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR, new String[]{"======根据用户Id["+addrinfo.getStaffId()+"]设置默认收货地址失败======"});
        }
        return 0;
    }
    
}

