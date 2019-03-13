package com.zengshi.ecp.order.service.busi.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.order.dao.mapper.busi.OrdEntityImportMapper;
import com.zengshi.ecp.order.dao.mapper.busi.manual.OrdEntityImportGroupMapper;
import com.zengshi.ecp.order.dao.model.OrdEntityImport;
import com.zengshi.ecp.order.dao.model.OrdEntityImportCriteria;
import com.zengshi.ecp.order.dao.model.OrdEntityImportCriteria.Criteria;
import com.zengshi.ecp.order.dao.model.OrdEntityImportGroup;
import com.zengshi.ecp.order.dao.model.OrdFileImport;
import com.zengshi.ecp.order.dubbo.dto.REntityImportRequest;
import com.zengshi.ecp.order.dubbo.dto.REntityInputRequest;
import com.zengshi.ecp.order.dubbo.dto.RFileImportRequest;
import com.zengshi.ecp.order.dubbo.dto.RShowEntityResponse;
import com.zengshi.ecp.order.dubbo.dto.RShowImportInfoResponse;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndImportInfo;
import com.zengshi.ecp.order.dubbo.dto.SBaseSplitInfo;
import com.zengshi.ecp.order.dubbo.dto.SEntityChkInfo;
import com.zengshi.ecp.order.dubbo.util.DelyConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdDealFileSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdEntityChkSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdEntityImportSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdFileImportSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月11日下午5:43:53 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version
 * @since JDK 1.6
 */
public class OrdEntityImportSVImpl extends GeneralSQLSVImpl implements IOrdEntityImportSV {
    @Resource
    private OrdEntityImportMapper ordEntityImportMapper;

    @Resource
    private OrdEntityImportGroupMapper ordEntityImportGroupMapper;

    @Resource
    private IOrdFileImportSV ordFileImportSV;

    @Resource
    private IOrdEntityChkSV ordEntityChkSV;

    @Resource
    private IOrdDealFileSV ordDealFileSV;

    @Resource(name = "seq_ord_entity_import")
    private Sequence seqOrdEntityImport;

    private static final String MODULE = OrdEntityImportSVImpl.class.getName();

    @Override
    public void saveOrdEntityImportList(List<OrdEntityImport> ordEntityImports) {
        for (OrdEntityImport oei : ordEntityImports) {
            oei.setId(this.seqOrdEntityImport.nextValue());
            this.ordEntityImportMapper.insert(oei);
        }
    }

    @Override
    public List<OrdEntityImport> findByOrderId(final SBaseSplitInfo sBaseSplitInfo) {
        final OrdEntityImportCriteria oeic = new OrdEntityImportCriteria();
        oeic.createCriteria().andOrderIdEqualTo(sBaseSplitInfo.getOrderId())
                .andShopIdEqualTo(sBaseSplitInfo.getShopId());
        return this.ordEntityImportMapper.selectByExample(oeic);
    }

    @Override
    public void deleteByOrderId(SBaseSplitInfo sBaseSplitInfo) {
        final OrdEntityImportCriteria oeic = new OrdEntityImportCriteria();
        oeic.createCriteria().andShopIdEqualTo(sBaseSplitInfo.getShopId())
                .andOrderIdEqualTo(sBaseSplitInfo.getOrderId());
        this.ordEntityImportMapper.deleteByExample(oeic);
    }

    /**
     * addEntityImportNo:新增实体编号录入批次号. <br/>
     * 
     * @author cbl
     * @param rEntityInRequest
     * @return
     * @since JDK 1.6
     */
    private OrdFileImport addEntityImportNo(REntityInputRequest rEntityInputRequest) {
        OrdFileImport ofi = new OrdFileImport();
        ofi.setFromId(DelyConstants.FromId.FROM_ID_ADD_INPUT); // 实体编号页面录入
        ofi.setShopId(rEntityInputRequest.getShopId());
        ofi.setOrderId(rEntityInputRequest.getOrderId());
        ofi.setFileName("");
        ofi.setStatus(DelyConstants.ImportStatus.IMPORT_STATUS_INPUT);
        ofi.setStatus("");
        ofi.setRemark("");
        ofi.setImportTime(DateUtil.getSysDate());
        ofi.setImportStaff(rEntityInputRequest.getStaff().getId());
        return this.ordFileImportSV.saveOrdFileImport(ofi);
    }

    /**
     * addEntityImportNo:新增实体编号文件导入生成批次号. <br/>
     * 
     * @author cbl
     * @param rEntityAddImport
     * @return
     * @since JDK 1.6
     */
    private OrdFileImport addEntityImportNo(RFileImportRequest rEntityAddImport) {
        OrdFileImport ofi = new OrdFileImport();
        ofi.setFromId(DelyConstants.FromId.FROM_ID_ADD_IMPORT); // 实体编号页面录入
        ofi.setShopId(rEntityAddImport.getShopId());
        ofi.setOrderId(rEntityAddImport.getOrderId());
        ofi.setFileName(rEntityAddImport.getFileName());
        ofi.setStatus(DelyConstants.ImportStatus.IMPORT_STATUS_INPUT);
        ofi.setRemark("");
        ofi.setImportTime(DateUtil.getSysDate());
        ofi.setImportStaff(rEntityAddImport.getStaff().getId());
        return this.ordFileImportSV.saveOrdFileImport(ofi);
    }

    @Override
    public PageResponseDTO<RShowImportInfoResponse> saveEntityInput(
            REntityInputRequest rEntityInputRequest) {
        /* 先删除该子订单下的实体编号 */
        LogUtil.info(MODULE, "删除" + rEntityInputRequest.getOrderSubId() + "该子订单中间表下的所有实体编号");
        deleteByOrdSubId(rEntityInputRequest);
        /* 生成实体编号相关的批次号 */
        LogUtil.info(MODULE, "生成" + rEntityInputRequest.getOrderSubId() + "该子订单录入批次号");
        OrdFileImport ofi = addEntityImportNo(rEntityInputRequest);
        /* 生成实体中间表数据 */
        List<OrdEntityImport> oeis = new ArrayList<OrdEntityImport>();

        for (String entity : rEntityInputRequest.getEntitys()) {
            OrdEntityImport oei = new OrdEntityImport();
            oei.setImportNo(ofi.getId());
            oei.setShopId(rEntityInputRequest.getShopId());
            oei.setStatus(DelyConstants.ImportStatus.IMPORT_STATUS_INPUT);
            oei.setOrderId(rEntityInputRequest.getOrderId());
            oei.setOrderSubId(rEntityInputRequest.getOrderSubId());
            oei.setRemark("");
            oei.setEntityCode(entity);
            oeis.add(oei);
        }
        LogUtil.info(MODULE, "生成" + rEntityInputRequest.getOrderSubId() + "该子订单中间表数据");
        saveOrdEntityImportList(oeis);
        /* 校验中间表中生成的数据 */
        SBaseAndImportInfo sbai = new SBaseAndImportInfo();
        sbai.setShopId(rEntityInputRequest.getShopId());
        sbai.setImportNo(ofi.getId());
        LogUtil.info(MODULE, "进入" + rEntityInputRequest.getOrderSubId() + "该子订单实体编号校验");
        if (this.ordEntityChkSV.saveEntiytCodeAddChk(sbai)) {
            /* 校验成功 */
            return null;
        } else {
            /* 校验失败 */
            REntityImportRequest reir = new REntityImportRequest();
            reir.setImportNo(ofi.getId());
            reir.setShopId(rEntityInputRequest.getShopId());
            return queryFailImportInfo(reir);
        }
    }

    @Override
    public PageResponseDTO<RShowEntityResponse> queryOrderSubEntityPage(
            REntityInputRequest rEntityInputRequest) {
        OrdEntityImportCriteria oeic = new OrdEntityImportCriteria();
        oeic.setLimitClauseCount(rEntityInputRequest.getPageSize());
        oeic.setLimitClauseStart(rEntityInputRequest.getStartRowIndex());
        oeic.setOrderByClause("entity_code desc");
        oeic.createCriteria().andOrderIdEqualTo(rEntityInputRequest.getOrderId())
                .andShopIdEqualTo(rEntityInputRequest.getShopId())
                .andOrderSubIdEqualTo(rEntityInputRequest.getOrderSubId());
        return super.queryByPagination(rEntityInputRequest, oeic, true,
                new PaginationCallback<OrdEntityImport, RShowEntityResponse>() {

                    @Override
                    public List<OrdEntityImport> queryDB(BaseCriteria criteria) {
                        return ordEntityImportMapper
                                .selectByExample((OrdEntityImportCriteria) criteria);
                    }

                    @Override
                    public long queryTotal(BaseCriteria criteria) {
                        return ordEntityImportMapper
                                .countByExample((OrdEntityImportCriteria) criteria);
                    }

                    @Override
                    public List<Comparator<OrdEntityImport>> defineComparators() {
                        List<Comparator<OrdEntityImport>> ls = new ArrayList<Comparator<OrdEntityImport>>();
                        ls.add(new Comparator<OrdEntityImport>(){

                            @Override
                            public int compare(OrdEntityImport o1, OrdEntityImport o2) {
                                return o1.getId().compareTo(o2.getId());
                            }
                            
                        });
                        return ls;
                    }

                    @Override
                    public RShowEntityResponse warpReturnObject(OrdEntityImport t) {
                        RShowEntityResponse rser = new RShowEntityResponse();
                        BeanUtils.copyProperties(t, rser);
                        return rser;
                    }
                });
    }

    @Override
    public PageResponseDTO<RShowImportInfoResponse> queryFailImportInfo(
            REntityImportRequest rEntityImportRequest) {
        OrdEntityImportCriteria oeic = new OrdEntityImportCriteria();
        oeic.setLimitClauseCount(rEntityImportRequest.getPageSize());
        oeic.setLimitClauseStart(rEntityImportRequest.getStartRowIndex());
        oeic.setOrderByClause("entity_code desc");
        oeic.createCriteria().andShopIdEqualTo(rEntityImportRequest.getShopId())
                .andImportNoEqualTo(rEntityImportRequest.getImportNo());
        return super.queryByPagination(rEntityImportRequest, oeic, true,
                new PaginationCallback<OrdEntityImport, RShowImportInfoResponse>() {

                    @Override
                    public List<OrdEntityImport> queryDB(BaseCriteria criteria) {
                        return ordEntityImportMapper
                                .selectByExample((OrdEntityImportCriteria) criteria);
                    }

                    @Override
                    public long queryTotal(BaseCriteria criteria) {
                        return ordEntityImportMapper
                                .countByExample((OrdEntityImportCriteria) criteria);
                    }

                    @Override
                    public RShowImportInfoResponse warpReturnObject(OrdEntityImport t) {
                        RShowImportInfoResponse rser = new RShowImportInfoResponse();
                        BeanUtils.copyProperties(t, rser);
                        return rser;
                    }
                });
    }

    @Override
    public void deleteFailImport(REntityImportRequest rEntityImportRequest) {
        final OrdEntityImportCriteria oeic = new OrdEntityImportCriteria();
        oeic.createCriteria().andShopIdEqualTo(rEntityImportRequest.getShopId())
                .andImportNoEqualTo(rEntityImportRequest.getImportNo());
        this.ordEntityImportMapper.deleteByExample(oeic);

        // 批次表该批次状态为06-删除
        SBaseAndImportInfo sba = new SBaseAndImportInfo();
        sba.setShopId(rEntityImportRequest.getShopId());
        sba.setImportNo(rEntityImportRequest.getImportNo());
        sba.setStatus(DelyConstants.ImportStatus.IMPORT_STATUS_DELETE);
        this.ordFileImportSV.updateStatusByImportNo(sba);
    }

    @Override
    public void saveEntityImport(RFileImportRequest rEntityAddImport) {

        /* 生成批次表数据 生成批次号 */
        OrdFileImport ofi = addEntityImportNo(rEntityAddImport);

        rEntityAddImport.setImportNo(ofi.getId());
        /* 读取文件并保存中间表 */
        this.ordDealFileSV.importEntityAddExcel(rEntityAddImport);
        /* 校验 */
        SBaseAndImportInfo sbai = new SBaseAndImportInfo();
        sbai.setShopId(rEntityAddImport.getShopId());
        sbai.setImportNo(ofi.getId());// 批次号
        sbai.setOrderId(rEntityAddImport.getOrderId());
        this.ordEntityChkSV.saveEntiytCodeAddChk(sbai);

    }

    @Override
    public void updateStatusByImportNo(SBaseAndImportInfo sBaseAndImportInfo) {
        final OrdEntityImportCriteria oeic = new OrdEntityImportCriteria();
        oeic.createCriteria().andShopIdEqualTo(sBaseAndImportInfo.getShopId())
                .andImportNoEqualTo(sBaseAndImportInfo.getImportNo());
        OrdEntityImport oei = new OrdEntityImport();
        oei.setStatus(sBaseAndImportInfo.getStatus());
        this.ordEntityImportMapper.updateByExampleSelective(oei, oeic);
    }

    @Override
    public List<OrdEntityImport> queryByImportNo(SBaseAndImportInfo sBaseAndImportInfo) {
        final OrdEntityImportCriteria oeic = new OrdEntityImportCriteria();
        oeic.createCriteria().andShopIdEqualTo(sBaseAndImportInfo.getShopId())
                .andImportNoEqualTo(sBaseAndImportInfo.getImportNo());
        return this.ordEntityImportMapper.selectByExample(oeic);
    }

    @Override
    public Long queryCountByImportNo(SBaseAndImportInfo sBaseAndImportInfo) {
        final OrdEntityImportCriteria oeic = new OrdEntityImportCriteria();
        oeic.createCriteria().andShopIdEqualTo(sBaseAndImportInfo.getShopId())
                .andImportNoEqualTo(sBaseAndImportInfo.getImportNo());

        return this.ordEntityImportMapper.countByExample(oeic);
    }

    @Override
    public Long queryCountByImportNoDist(SBaseAndImportInfo sBaseAndImportInfo) {
        final OrdEntityImportCriteria oeic = new OrdEntityImportCriteria();
        oeic.createCriteria().andShopIdEqualTo(sBaseAndImportInfo.getShopId())
                .andImportNoEqualTo(sBaseAndImportInfo.getImportNo());
        return this.ordEntityImportGroupMapper.countEntityCode(oeic);
    }

    @Override
    public Long queryCountByEntityCode(SBaseAndImportInfo sBaseAndImportInfo) {
        final OrdEntityImportCriteria oeic = new OrdEntityImportCriteria();
        oeic.createCriteria().andShopIdEqualTo(sBaseAndImportInfo.getShopId())
                .andEntityCodeEqualTo(sBaseAndImportInfo.getEntityCode());
        return this.ordEntityImportMapper.countByExample(oeic);
    }

    @Override
    public List<OrdEntityImportGroup> queryOrderSubByImportNo(SBaseAndImportInfo sBaseAndImportInfo) {
        final OrdEntityImportCriteria oeic = new OrdEntityImportCriteria();
        oeic.createCriteria().andShopIdEqualTo(sBaseAndImportInfo.getShopId())
                .andImportNoEqualTo(sBaseAndImportInfo.getImportNo());

        return this.ordEntityImportGroupMapper.selectByExampleGroupByOrderAndSub(oeic);
    }

    @Override
    public void updateRemark(SEntityChkInfo sEntityChkInfo) {
        final OrdEntityImportCriteria oeic = new OrdEntityImportCriteria();
        Criteria cra = oeic.createCriteria();
        cra.andShopIdEqualTo(sEntityChkInfo.getShopId())
                .andOrderIdEqualTo(sEntityChkInfo.getOrderId())
                .andImportNoEqualTo(sEntityChkInfo.getImportNo());
        if (!(StringUtil.isEmpty(sEntityChkInfo.getOrderSubId()))) {
            cra.andOrderSubIdEqualTo(sEntityChkInfo.getOrderSubId());
        }
        if (!(StringUtil.isEmpty(sEntityChkInfo.getEntityCode()))) {
            cra.andEntityCodeEqualTo(sEntityChkInfo.getEntityCode());
        }

        OrdEntityImport oei = new OrdEntityImport();
        oei.setRemark(sEntityChkInfo.getRemark());
        this.ordEntityImportMapper.updateByExampleSelective(oei, oeic);
    }

    @Override
    public Long queryCountByOrderSubId(SBaseAndImportInfo sBaseAndImportInfo) {
        final OrdEntityImportCriteria oeic = new OrdEntityImportCriteria();
        oeic.createCriteria().andShopIdEqualTo(sBaseAndImportInfo.getShopId())
                .andImportNoEqualTo(sBaseAndImportInfo.getImportNo())
                .andOrderSubIdEqualTo(sBaseAndImportInfo.getOrderSubId());
        return this.ordEntityImportMapper.countByExample(oeic);
    }

    @Override
    public void deleteByOrdSubId(REntityInputRequest rEntityInputRequest) {
        final OrdEntityImportCriteria oeic = new OrdEntityImportCriteria();
        oeic.createCriteria().andShopIdEqualTo(rEntityInputRequest.getShopId())
                .andOrderIdEqualTo(rEntityInputRequest.getOrderId())
                .andOrderSubIdEqualTo(rEntityInputRequest.getOrderSubId());
        this.ordEntityImportMapper.deleteByExample(oeic);
    }

  

}
