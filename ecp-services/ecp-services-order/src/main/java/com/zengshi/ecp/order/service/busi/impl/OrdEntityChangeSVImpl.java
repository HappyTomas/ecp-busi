package com.zengshi.ecp.order.service.busi.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.order.dao.mapper.busi.OrdEntityChangeMapper;
import com.zengshi.ecp.order.dao.mapper.busi.manual.OrdEntityChangeGroupMapper;
import com.zengshi.ecp.order.dao.model.OrdEntityChange;
import com.zengshi.ecp.order.dao.model.OrdEntityChangeCriteria;
import com.zengshi.ecp.order.dubbo.dto.REntityImportRequest;
import com.zengshi.ecp.order.dubbo.dto.RShowImportChgInfoResponse;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndImportInfo;
import com.zengshi.ecp.order.dubbo.dto.SEntityChkInfo;
import com.zengshi.ecp.order.dubbo.util.DelyConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdEntityChangeSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdFileImportSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.db.sequence.Sequence;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月6日下午3:34:08 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 *
 * @author cbl
 * @version
 * @since JDK 1.6
 */
public class OrdEntityChangeSVImpl extends GeneralSQLSVImpl implements IOrdEntityChangeSV {

    @Resource
    private OrdEntityChangeMapper ordEntityChangeMapper;
    
    @Resource
    private OrdEntityChangeGroupMapper ordEntityChangeGroupMapper;
    
    @Resource
    private IOrdFileImportSV ordFileImportSV;

    @Resource(name = "seq_ord_entity_change")
    private Sequence seqOrdEntityChange;

    @Override
    public void saveOrdEntityChange(final OrdEntityChange ordEntityChange) {
        ordEntityChange.setId(this.seqOrdEntityChange.nextValue());
        this.ordEntityChangeMapper.insert(ordEntityChange);
    }

    @Override
    public PageResponseDTO<RShowImportChgInfoResponse> queryFailImportInfo(
            REntityImportRequest rEntityImportRequest) {
        OrdEntityChangeCriteria oecc = new OrdEntityChangeCriteria();
        oecc.setLimitClauseCount(rEntityImportRequest.getPageSize());
        oecc.setLimitClauseStart(rEntityImportRequest.getStartRowIndex());
        oecc.setOrderByClause("order_id  desc");
        oecc.createCriteria().andShopIdEqualTo(rEntityImportRequest.getShopId())
                             .andImportNoEqualTo(rEntityImportRequest.getImportNo());
        return super.queryByPagination(rEntityImportRequest, oecc, true, new PaginationCallback<OrdEntityChange, RShowImportChgInfoResponse>() {

            @Override
            public List<OrdEntityChange> queryDB(BaseCriteria criteria) {
                return ordEntityChangeMapper.selectByExample((OrdEntityChangeCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return ordEntityChangeMapper.countByExample((OrdEntityChangeCriteria)criteria);
            }

            @Override
            public List<Comparator<OrdEntityChange>> defineComparators() {
                List<Comparator<OrdEntityChange>> ls = new ArrayList<Comparator<OrdEntityChange>>();
                ls.add(new Comparator<OrdEntityChange>(){

                    @Override
                    public int compare(OrdEntityChange o1, OrdEntityChange o2) {
                        return o1.getId().compareTo(o2.getId());
                    }
                    
                });
                return ls;
            }

            @Override
            public RShowImportChgInfoResponse warpReturnObject(OrdEntityChange t) {
                RShowImportChgInfoResponse rsir = new RShowImportChgInfoResponse();
                BeanUtils.copyProperties(t, rsir);
                return rsir;
            }
        });
    }

    @Override
    public void deleteFailImport(REntityImportRequest rEntityImportRequest) {
        OrdEntityChangeCriteria oecc = new OrdEntityChangeCriteria();
        oecc.createCriteria().andShopIdEqualTo(rEntityImportRequest.getShopId())
                             .andImportNoEqualTo(rEntityImportRequest.getImportNo());
        this.ordEntityChangeMapper.deleteByExample(oecc);
        
      //批次表该批次状态为06-删除
        SBaseAndImportInfo sba = new SBaseAndImportInfo();
        sba.setShopId(rEntityImportRequest.getShopId());
        sba.setImportNo(rEntityImportRequest.getImportNo());
        sba.setStatus(DelyConstants.ImportStatus.IMPORT_STATUS_DELETE);
        this.ordFileImportSV.updateStatusByImportNo(sba);
    }
    
    @Override
    public Long queryCountByImportNo(SBaseAndImportInfo sBaseAndImportInfo) {
        final OrdEntityChangeCriteria oecc = new OrdEntityChangeCriteria();
        oecc.createCriteria().andShopIdEqualTo(sBaseAndImportInfo.getShopId())
                             .andImportNoEqualTo(sBaseAndImportInfo.getImportNo());
        
        return this.ordEntityChangeMapper.countByExample(oecc);
    }
    @Override
    public Long queryCountByImportNoDist(SBaseAndImportInfo sBaseAndImportInfo) {
        final OrdEntityChangeCriteria oecc = new OrdEntityChangeCriteria();
        oecc.createCriteria().andShopIdEqualTo(sBaseAndImportInfo.getShopId())
                             .andImportNoEqualTo(sBaseAndImportInfo.getImportNo());
        oecc.setDistinct(true);
        return this.ordEntityChangeMapper.countByExample(oecc);
    }
    
    @Override
    public void updateStatusByImportNo(SBaseAndImportInfo sBaseAndImportInfo) {
        final OrdEntityChangeCriteria oecc = new OrdEntityChangeCriteria();
        oecc.createCriteria().andShopIdEqualTo(sBaseAndImportInfo.getShopId())
                             .andImportNoEqualTo(sBaseAndImportInfo.getImportNo());
        OrdEntityChange oec = new OrdEntityChange();
        oec.setStatus(sBaseAndImportInfo.getStatus());
        this.ordEntityChangeMapper.updateByExampleSelective(oec, oecc);
    }

    @Override
    public List<OrdEntityChange> queryByImportNo(SBaseAndImportInfo sBaseAndImportInfo) {
        final OrdEntityChangeCriteria oecc = new OrdEntityChangeCriteria();
        oecc.createCriteria().andShopIdEqualTo(sBaseAndImportInfo.getShopId())
                             .andImportNoEqualTo(sBaseAndImportInfo.getImportNo());
        return this.ordEntityChangeMapper.selectByExample(oecc);
    }

    @Override
    public void updateImportRemarkChg(SEntityChkInfo sEntityChkInfo) {
        final OrdEntityChangeCriteria oecc = new OrdEntityChangeCriteria();
        oecc.createCriteria().andShopIdEqualTo(sEntityChkInfo.getShopId())
                             .andImportNoEqualTo(sEntityChkInfo.getImportNo())
                             .andEntityCodeAfEqualTo(sEntityChkInfo.getEntityCode())
                             .andEntityCodeBfEqualTo(sEntityChkInfo.getEntityCodeBf());
        OrdEntityChange oec = new OrdEntityChange();
        oec.setRemark(sEntityChkInfo.getRemark());
        this.ordEntityChangeMapper.updateByExampleSelective(oec, oecc);
    }

    @Override
    public Long queryCountEntityCodeAf(SBaseAndImportInfo sBaseAndImportInfo) {
        final OrdEntityChangeCriteria oecc = new OrdEntityChangeCriteria();
        oecc.createCriteria().andShopIdEqualTo(sBaseAndImportInfo.getShopId())
                             .andImportNoEqualTo(sBaseAndImportInfo.getImportNo());
        
        return this.ordEntityChangeGroupMapper.countEntityCodeAf(oecc);
    }

    @Override
    public Long queryCountEntityCodeBf(SBaseAndImportInfo sBaseAndImportInfo) {
        final OrdEntityChangeCriteria oecc = new OrdEntityChangeCriteria();
        oecc.createCriteria().andShopIdEqualTo(sBaseAndImportInfo.getShopId())
                             .andImportNoEqualTo(sBaseAndImportInfo.getImportNo());
        
        return this.ordEntityChangeGroupMapper.countEntityCodeBf(oecc);
    }
}
