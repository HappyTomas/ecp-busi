package com.zengshi.ecp.staff.service.busi.custaddr.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dao.mapper.busi.CustAddrMapper;
import com.zengshi.ecp.staff.dao.model.CustAddr;
import com.zengshi.ecp.staff.dao.model.CustAddrCriteria;
import com.zengshi.ecp.staff.dao.model.CustAddrCriteria.Criteria;
import com.zengshi.ecp.staff.dao.model.CustAddrKey;
import com.zengshi.ecp.staff.dubbo.dto.CustAddrReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAddrResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.custaddr.interfaces.ICustAddrSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.rpc.Result;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: <br>
 * Date:2015年8月31日下午5:29:53  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6
 * 
 * 用户收货地址管理类，处理用户收货地址信息
 */
public class CustAddrSVImpl extends GeneralSQLSVImpl implements ICustAddrSV {
    
    /**
     * 用户收货地址表mapper操作
     */
    @Resource
    private CustAddrMapper custAddrMapper;   
    
    @Resource(name = "seq_cust_addr_id")
    private Sequence seq_cust_addr_id; 
    
    private static final String MODULE = CustAddrSVImpl.class.getName();

//    private HashMap<Long, List<CustAddr>> custAddrHashMap;
    
    /**
     * 
     * TODO 根据pRequestDTO.staffId用户ID查询收货地址列表. 
     * @see com.zengshi.ecp.staff.service.busi.custaddr.interfaces.ICustAddrSV#listCustAddr(com.zengshi.ecp.staff.dubbo.dto.CustAddrReqDTO)
     */
    @Override
    public PageResponseDTO<CustAddrResDTO> listCustAddr(CustAddrReqDTO pRequestDTO) throws BusinessException 
    {
        CustAddrCriteria criteria = new CustAddrCriteria();
        criteria.setLimitClauseCount(pRequestDTO.getPageSize());
        criteria.setLimitClauseStart(pRequestDTO.getStartRowIndex());
        Criteria ca = criteria.createCriteria();
        ca.andStaffIdEqualTo(pRequestDTO.getStaffId());
        ca.andStatusEqualTo("1");//有效的
        
        return super.queryByPagination(pRequestDTO, criteria, true, new PaginationCallback<CustAddr, CustAddrResDTO>(){

            @Override
            public List<CustAddr> queryDB(BaseCriteria arg0) {
                return custAddrMapper.selectByExample((CustAddrCriteria)arg0);
            }

            @Override
            public long queryTotal(BaseCriteria arg0) {
                return custAddrMapper.countByExample((CustAddrCriteria)arg0);
            }

            @Override
            public CustAddrResDTO warpReturnObject(CustAddr arg0) {
                CustAddrResDTO sDto = new CustAddrResDTO();
                ObjectCopyUtil.copyObjValue(arg0, sDto, null, false);
                return sDto;
            }
            
        });
    }

    /**
     * 
     * TODO 保存用户收货地址信息. 
     * @see com.zengshi.ecp.staff.service.busi.custaddr.interfaces.ICustAddrSV#saveCustAddr(java.lang.Long, com.zengshi.ecp.staff.dao.model.CustAddr)
     */
    @Override
    public Long saveCustAddr(CustAddr addrinfo) throws BusinessException 
    {
        //1.dubbo传进来的参数进行转换成DAO层中的各式
        addrinfo.setStatus("1");
        addrinfo.setId(seq_cust_addr_id.nextValue());
        addrinfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        addrinfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        try {
            custAddrMapper.insertSelective(addrinfo);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.info(MODULE, "=======保存收获地址信息：["+addrinfo.toString()+"]出现异常:", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR, new String[]{addrinfo.toString()});
          }
//        //如果缓冲中有该对象的Map缓冲对象，则增加一条缓存记录
//        if(custAddrHashMap.get(staffID) != null)
//        {
//            custAddrHashMap.get(staffID).add(addrinfo);
//        }
//        //如果缓存Map对象中没有记录，则添加
//        else
//        {
//            List<CustAddr> tmpList = new ArrayList<CustAddr>();
//            tmpList.add(addrinfo);
//            custAddrHashMap.put(staffID, tmpList);
//        }
//        
        //如果当前有效记录只有一条记录，则设为默认收获地址
        if(countCustAddrNum(addrinfo.getStaffId()) == 1)
        {
            updateToDefaultAddr(addrinfo.getId(), addrinfo.getStaffId());
        }
        return addrinfo.getId();
    }

    /**
     * 
     * TODO 更新用户收货地址信息. 
     * @see com.zengshi.ecp.staff.service.busi.custaddr.interfaces.ICustAddrSV#updateCustAddr(java.lang.Long, com.zengshi.ecp.staff.dao.model.CustAddr)
     */
    @Override
    public void updateCustAddr(CustAddr addrinfo) throws BusinessException 
    {
        addrinfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        try {
            custAddrMapper.updateByPrimaryKeySelective(addrinfo);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.info(MODULE, "=======更新收获地址信息：["+addrinfo.toString()+"]出现异常:", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{addrinfo.toString()});
        }
//        //如果缓冲中有该对象的Map缓冲对象，则更新一条缓存记录
//        //先删后加
//        //如缓存没有该对象的Map对象，则不操作，等待查询时自动添加
//        List<CustAddr> listTmp = custAddrHashMap.get(staffID);
//        if(listTmp  != null)
//        { 
//            Iterator<CustAddr> iter = custAddrHashMap.get(staffID).iterator();
//            while(iter.hasNext())
//            {
//                if(iter.next().getId() == addrinfo.getId())
//                {
//                    iter.remove();
//                }
//            }
//            listTmp.add(addrinfo);
//        }
        
    }

    /**
     * 
     * TODO 删除用户收货地址信息. 
     * @see com.zengshi.ecp.staff.service.busi.custaddr.interfaces.ICustAddrSV#deleteCustAddr(java.lang.Long)
     */
    @Override
    public void deleteCustAddr(Long id, Long staffid)
    {
        CustAddrKey key = new CustAddrKey();
        key.setId(id);
        key.setStaffId(staffid);
        CustAddr custaddrOld = null;
        try {
            custaddrOld = custAddrMapper.selectByPrimaryKey(key);
            custAddrMapper.deleteByPrimaryKey(key);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.info(MODULE, "=======删除收获地址信息：["+id.toString()+"]出现异常:", e);
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR, new String[]{id.toString()});
          }
        
        if(!StringUtil.isEmpty(custaddrOld))
        {
            if("1".equals(custaddrOld.getUsingFlag()))
            {
                List<CustAddrResDTO> recorDtos = listCustAddr(staffid);
                if(!CollectionUtils.isEmpty(recorDtos))
                {
                    updateToDefaultAddr(recorDtos.get(0).getId(), recorDtos.get(0).getStaffId());
                }
            }
        }
    }

    /**
     * 
     * TODO 统计用户收货地址数量. 
     * @see com.zengshi.ecp.staff.service.busi.custaddr.interfaces.ICustAddrSV#countCustAddrNum(java.lang.Long)
     */
    @Override
    public Long countCustAddrNum(Long staffID) {
        CustAddrCriteria criteria = new CustAddrCriteria();
        Criteria ca = criteria.createCriteria();
        ca.andStaffIdEqualTo(staffID);
        ca.andStatusEqualTo("1");//有效的
        
        /* 当前用户收货地址总数 */
        long num = 0;
        try {
            num = custAddrMapper.countByExample(criteria);
        } catch (Exception e) {
            LogUtil.info(MODULE, "=======统计收获地址信息：["+staffID.toString()+"]出现异常:", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{staffID.toString()});
          }
        return num;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.staff.service.busi.custaddr.interfaces.ICustAddrSV#listCustAddr(java.lang.Long) 
     */
    @Override
    public List<CustAddrResDTO> listCustAddr(Long pStaffId) throws BusinessException {
        CustAddrCriteria criteria = new CustAddrCriteria();
        Criteria ca = criteria.createCriteria();
        ca.andStaffIdEqualTo(pStaffId);
        ca.andStatusEqualTo("1");//有效的收货地址
        List<CustAddr> records = null;
        try {
            records = custAddrMapper.selectByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        if(!CollectionUtils.isEmpty(records))
        {
            List<CustAddrResDTO> result = new ArrayList<CustAddrResDTO>(records.size());
            //进行参数Dao转换成dao
            for(CustAddr ca1: records)
            {
                CustAddrResDTO dto = new CustAddrResDTO();
                ObjectCopyUtil.copyObjValue(ca1, dto, null, false);
                result.add(dto);
            }
            //进行排序
            Collections.sort(result);
            
            return result;
        }
        return null;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.staff.service.busi.custaddr.interfaces.ICustAddrSV#findDefaultAddr(java.lang.Long) 
     */
    @SuppressWarnings("null")
    @Override
    public CustAddr findDefaultAddr(Long pStaffId) throws BusinessException {
        
        CustAddrCriteria criteria = new CustAddrCriteria();
        Criteria ca = criteria.createCriteria();
        ca.andStaffIdEqualTo(pStaffId);
        ca.andStatusEqualTo("1");
        ca.andUsingFlagEqualTo("1");//默认收获地址标记
        
        List<CustAddr> result = null;
        try {
            result = custAddrMapper.selectByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        
        return CollectionUtils.isEmpty(result)?null:result.get(0);
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.staff.service.busi.custaddr.interfaces.ICustAddrSV#findCustAddr(java.lang.Long, java.lang.Long) 
     */
    @Override
    public CustAddr findCustAddr(Long id, Long staffid) {
        CustAddr custaddr = null;
        CustAddrKey key = new CustAddrKey();
        key.setId(id);
        key.setStaffId(staffid);
        try {
            custaddr = custAddrMapper.selectByPrimaryKey(key);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        
        return custaddr;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.staff.service.busi.custaddr.interfaces.ICustAddrSV#updateToDefaultAddr(java.lang.Long, java.lang.Long) 
     */
    @Override
    public int updateToDefaultAddr(Long id, Long staffid) {
        CustAddr recordOld = new CustAddr();
        recordOld.setUsingFlag("0");//设置为普通地址
        CustAddrCriteria criteriaOld = new CustAddrCriteria();
        criteriaOld.createCriteria().andStaffIdEqualTo(staffid);
        
        CustAddr recordNew = new CustAddr();
        recordNew.setUsingFlag("1");//设置为默认收货地址
        recordNew.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        CustAddrCriteria criteriaNew = new CustAddrCriteria();
        criteriaNew.createCriteria().andStaffIdEqualTo(staffid).andIdEqualTo(id);
               
        try {
            //1.先将该用户所有地址设置为普通收货地址
            custAddrMapper.updateByExampleSelective(recordOld, criteriaOld);
          //2.再将该id收货地址设为默认收货地址
            custAddrMapper.updateByExampleSelective(recordNew, criteriaNew);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }    
        return 0;
    }
}

