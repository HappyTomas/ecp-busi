package com.zengshi.ecp.staff.service.busi.custinfo.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dao.mapper.busi.CustGrowInfoMapper;
import com.zengshi.ecp.staff.dao.model.CustGrowInfo;
import com.zengshi.ecp.staff.dao.model.CustGrowInfoCriteria;
import com.zengshi.ecp.staff.dubbo.dto.CustGrowInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustGrowInfoResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.custinfo.interfaces.ICustGrowInfoSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 会员成长值信息服务接口实现类<br>
 * Date:2015-8-24下午5:51:35  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version  
 * @since JDK 1.7
 */
public class CustGrowInfoSVImpl extends GeneralSQLSVImpl implements ICustGrowInfoSV{
    
    @Resource
    private CustGrowInfoMapper custGrowInfoMapper;
    
    
    

    @Override
    public PageResponseDTO<CustGrowInfoResDTO> queryCustGrowInfo(
            CustGrowInfoReqDTO custGrowInfoReqDTO) throws BusinessException {
        PageResponseDTO<CustGrowInfoResDTO> page = new PageResponseDTO<CustGrowInfoResDTO>();
        CustGrowInfoCriteria c = new CustGrowInfoCriteria();
        c.createCriteria().andStaffIdEqualTo(custGrowInfoReqDTO.getStaffId()).andStatusEqualTo(StaffConstants.custInfo.CUST_STATUS_NORMAL);
        c.setOrderByClause("CREATE_TIME desc");
        page.setPageNo(custGrowInfoReqDTO.getPageNo());
        page.setPageSize(custGrowInfoReqDTO.getPageSize());
        c.setLimitClauseCount(page.getPageSize());
        c.setLimitClauseStart(page.getStartRowIndex());
        page =  super.queryByPagination(custGrowInfoReqDTO, c,true, new PaginationCallback<CustGrowInfo, CustGrowInfoResDTO>() {
            //查询记录集
            @Override
            public List<CustGrowInfo> queryDB(BaseCriteria criteria) {
                
                return custGrowInfoMapper.selectByExample((CustGrowInfoCriteria)criteria);
            }
            //查询总记录数
            @Override
            public long queryTotal(BaseCriteria criteria) {
                
                return custGrowInfoMapper.countByExample((CustGrowInfoCriteria)criteria);
            }
            //可以不定义
            @Override
            public List<Comparator<CustGrowInfo>> defineComparators() {
                List<Comparator<CustGrowInfo>> ls=new ArrayList<Comparator<CustGrowInfo>>();
                ls.add(new Comparator<CustGrowInfo>(){

                    @Override
                    public int compare(CustGrowInfo o1, CustGrowInfo o2) {
                        return o1.getCreateTime().after(o2.getCreateTime())?-1:1;
                    }
                    
                });
                return ls;
            }
            //查询结果转换
            @Override
            public CustGrowInfoResDTO warpReturnObject(CustGrowInfo t) {
                CustGrowInfoResDTO dto=new CustGrowInfoResDTO();
                BeanUtils.copyProperties(t, dto);
                return dto;
            }
        });
        return page;
    }

  
    
}

