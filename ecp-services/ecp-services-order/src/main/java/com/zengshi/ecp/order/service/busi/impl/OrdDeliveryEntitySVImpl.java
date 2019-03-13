package com.zengshi.ecp.order.service.busi.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.order.dao.mapper.busi.OrdDeliveryEntityMapper;
import com.zengshi.ecp.order.dao.model.OrdDeliveryEntity;
import com.zengshi.ecp.order.dao.model.OrdDeliveryEntityCriteria;
import com.zengshi.ecp.order.dao.model.OrdDeliveryEntityCriteria.Criteria;
import com.zengshi.ecp.order.dao.model.OrdEntityChange;
import com.zengshi.ecp.order.dao.model.OrdFileImport;
import com.zengshi.ecp.order.dubbo.dto.REntityChgImportRequest;
import com.zengshi.ecp.order.dubbo.dto.REntityCodeChgRequest;
import com.zengshi.ecp.order.dubbo.dto.RShowEntityChgRequest;
import com.zengshi.ecp.order.dubbo.dto.RShowEntityChgResponse;
import com.zengshi.ecp.order.dubbo.dto.RShowImportChgInfoResponse;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndBatchInfo;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndImportInfo;
import com.zengshi.ecp.order.dubbo.util.DelyConstants;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdDealFileSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdDeliveryEntitySV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdEntityChangeSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdEntityChkSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdFileImportSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.sequence.Sequence;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月11日下午5:43:42 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version
 * @since JDK 1.6
 */
public class OrdDeliveryEntitySVImpl extends GeneralSQLSVImpl implements IOrdDeliveryEntitySV {
    
    private static final String MODULE = OrdDeliveryEntitySVImpl.class.getName();

    @Resource
    private OrdDeliveryEntityMapper ordDeliveryEntityMapper;

    @Resource
    private IOrdEntityChangeSV ordEntityChangeSV;

    @Resource
    private IOrdFileImportSV ordFileImportSV;

    @Resource
    private IOrdEntityChkSV ordEntityChkSV;

    @Resource
    private IOrdDealFileSV ordDealFileSV;

    @Resource(name = "seq_ord_delivery_entity")
    private Sequence seqOrdDeliveryEntity;

    @Override
    public void saveOrdDeliveryEntityList(List<OrdDeliveryEntity> ordDeliveryEntitys) {
        for (OrdDeliveryEntity ode : ordDeliveryEntitys) {
            ode.setId(this.seqOrdDeliveryEntity.nextValue());
            this.ordDeliveryEntityMapper.insert(ode);
        }
    }

    /**
     * updateEntityCode:更新实体编号. <br/>
     * 
     * @author cbl
     * @param rEntityCodeChgRequest
     * @since JDK 1.6
     */
    private void updateEntityCode(REntityCodeChgRequest rEntityCodeChgRequest) {
        final OrdDeliveryEntityCriteria odec = new OrdDeliveryEntityCriteria();
        odec.createCriteria().andOrderIdEqualTo(rEntityCodeChgRequest.getOrderId())
                .andShopIdEqualTo(rEntityCodeChgRequest.getShopId())
                .andEntityCodeEqualTo(rEntityCodeChgRequest.getOldEntityCode());
        OrdDeliveryEntity ode = new OrdDeliveryEntity();
        ode.setEntityCode(rEntityCodeChgRequest.getNewEntityCode());
        this.ordDeliveryEntityMapper.updateByExampleSelective(ode, odec);
    }

    private void updateEntityCodeImport(REntityChgImportRequest rEntityChgImportRequest) {
        SBaseAndImportInfo sbai = new SBaseAndImportInfo();
        sbai.setImportNo(rEntityChgImportRequest.getImportNo());
        sbai.setShopId(rEntityChgImportRequest.getShopId());
        List<OrdEntityChange> oecs = this.ordEntityChangeSV.queryByImportNo(sbai);
        for (OrdEntityChange oec : oecs) {
            REntityCodeChgRequest rec = new REntityCodeChgRequest();
            rec.setOrderId(oec.getOrderId());
            rec.setShopId(rEntityChgImportRequest.getShopId());
            rec.setOldEntityCode(oec.getEntityCodeBf());
            rec.setNewEntityCode(oec.getEntityCodeAf());
            updateEntityCode(rec);
        }
    }

    /**
     * addEntityImportNo:生成批次号. <br/>
     * 
     * @author cbl
     * @param rEntityCodeChgRequest
     * @return
     * @since JDK 1.6
     */
    private OrdFileImport addEntityImportNo(REntityCodeChgRequest rEntityCodeChgRequest) {
        OrdFileImport ofi = new OrdFileImport();
        ofi.setFromId(DelyConstants.FromId.FROM_ID_CHANGE_INPUT); // 实体编号变更页面录入
        ofi.setShopId(rEntityCodeChgRequest.getShopId());
        ofi.setOrderId(rEntityCodeChgRequest.getOrderId());
        ofi.setFileName("");
        ofi.setStatus(DelyConstants.ImportStatus.IMPORT_STATUS_INPUT);// 初录
        ofi.setRemark("");
        ofi.setImportTime(DateUtil.getSysDate());
        ofi.setImportStaff(0l);
        return this.ordFileImportSV.saveOrdFileImport(ofi);
    }

    /**
     * addEntityImportNo:生成批次号. <br/>
     * 
     * @author cbl
     * @param rEntityChgImportRequest
     * @return
     * @since JDK 1.6
     */
    private OrdFileImport addEntityImportNo(REntityChgImportRequest rEntityChgImportRequest) {
        OrdFileImport ofi = new OrdFileImport();
        ofi.setFromId(DelyConstants.FromId.FROM_ID_CHANGE_IMPORT); // 实体编号变更文件导入
        ofi.setShopId(rEntityChgImportRequest.getShopId());
        ofi.setOrderId("");
        ofi.setFileName(rEntityChgImportRequest.getFileName());
        ofi.setStatus(DelyConstants.ImportStatus.IMPORT_STATUS_INPUT);// 初录
        ofi.setRemark("");
        ofi.setImportTime(DateUtil.getSysDate());
        ofi.setImportStaff(0l);
        return this.ordFileImportSV.saveOrdFileImport(ofi);
    }

    /**
     * saveOrdEntityChange:保存实体编号变更中间表记录. <br/>
     * 
     * @author cbl
     * @since JDK 1.6
     */
    private void saveOrdEntityChange(REntityCodeChgRequest rEntityCodeChgRequest, Long improtNo) {
        OrdEntityChange oec = new OrdEntityChange();
        oec.setEntityCodeAf(rEntityCodeChgRequest.getNewEntityCode());
        oec.setEntityCodeBf(rEntityCodeChgRequest.getOldEntityCode());
        oec.setFromType(DelyConstants.FromType.FROM_TYPE_INPUT);
        oec.setImportNo(improtNo);
        oec.setOrderId(rEntityCodeChgRequest.getOrderId());
        oec.setShopId(rEntityCodeChgRequest.getShopId());
        oec.setStatus(DelyConstants.ImportStatus.IMPORT_STATUS_INPUT); // 初录
        this.ordEntityChangeSV.saveOrdEntityChange(oec);
    }

    @Override
    public RShowImportChgInfoResponse updateEntityCodeChangeFromWeb(REntityCodeChgRequest rEntityCodeChgRequest) {
        /* 生成批次号 */
        OrdFileImport ofi = addEntityImportNo(rEntityCodeChgRequest);
        /* 生成变更中间表记录 */
        saveOrdEntityChange(rEntityCodeChgRequest, ofi.getId());
        /* 校验中间表中生成的数据 */
        SBaseAndImportInfo sbai = new SBaseAndImportInfo();
        sbai.setShopId(rEntityCodeChgRequest.getShopId());
        sbai.setImportNo(ofi.getId());
        if (this.ordEntityChkSV.saveEntiytCodeChgChk(sbai)) {
            /* 校验成功,修改实体编号 */
            updateEntityCode(rEntityCodeChgRequest);
            return null;
        } else {
            /* 校验失败 */
            LogUtil.info(MODULE, "校验不通过");
            RShowImportChgInfoResponse rsi = new RShowImportChgInfoResponse();
            List<OrdEntityChange> oec = this.ordEntityChangeSV.queryByImportNo(sbai);
            if(CollectionUtils.isEmpty(oec)){
                LogUtil.info(MODULE, "未找到中间表数据");
                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312000);
            } else {
                rsi.setOrderId(oec.get(0).getOrderId());
                rsi.setEntityCodeBf(oec.get(0).getEntityCodeBf());
                rsi.setEntityCodeAf(oec.get(0).getEntityCodeAf());
                rsi.setRemark(oec.get(0).getRemark());
            }
            return rsi;
        }
        
    }

    @Override
    public void updateEntityCodeChangeFromFile(REntityChgImportRequest rEntityChgImportRequest) {
        /* 生成批次表数据 生成批次号 */
        OrdFileImport ofi = addEntityImportNo(rEntityChgImportRequest);

        rEntityChgImportRequest.setImportNo(ofi.getId());
        /* 生成变更中间表记录 */
        this.ordDealFileSV.importEntityChgExcel(rEntityChgImportRequest);
        /* 校验 */
        SBaseAndImportInfo sbai = new SBaseAndImportInfo();
        sbai.setShopId(rEntityChgImportRequest.getShopId());
        sbai.setImportNo(ofi.getId());// 批次号
        if (this.ordEntityChkSV.saveEntiytCodeChgChk(sbai)) {
            /* 校验成功,修改实体编号 */
            updateEntityCodeImport(rEntityChgImportRequest);
        } else {
            /* 校验失败 */
        }
    }

    @Override
    public PageResponseDTO<RShowEntityChgResponse> queryEntityChgPage(
            RShowEntityChgRequest rShowEntityChgRequest) {
        OrdDeliveryEntityCriteria odec = new OrdDeliveryEntityCriteria();
        odec.setLimitClauseCount(rShowEntityChgRequest.getPageSize());
        odec.setLimitClauseStart(rShowEntityChgRequest.getStartRowIndex());
        odec.setDistinct(true);
        odec.setOrderByClause("order_id,entity_code asc");
        Criteria ca = odec.createCriteria().andShopIdEqualTo(rShowEntityChgRequest.getShopId())
                .andSendDateGreaterThanOrEqualTo(rShowEntityChgRequest.getBegDate())
                .andSendDateLessThanOrEqualTo(rShowEntityChgRequest.getEndDate());
        if (!(StringUtil.isEmpty(rShowEntityChgRequest.getOrderId()))) {
                ca.andOrderIdEqualTo(rShowEntityChgRequest.getOrderId());
        }
        if (!(StringUtil.isEmpty(rShowEntityChgRequest.getEntityCode()))) {
                ca.andEntityCodeEqualTo(rShowEntityChgRequest.getEntityCode());
        }
        if (!(StringUtil.isEmpty(rShowEntityChgRequest.getExpressNo()))) {
                ca.andExpressNoEqualTo(rShowEntityChgRequest.getExpressNo());
        }
        return super.queryByPagination(rShowEntityChgRequest, odec, true,
                new PaginationCallback<OrdDeliveryEntity, RShowEntityChgResponse>() {

                    @Override
                    public List<OrdDeliveryEntity> queryDB(BaseCriteria criteria) {
                        return ordDeliveryEntityMapper
                                .selectByExample((OrdDeliveryEntityCriteria) criteria);
                    }

                    @Override
                    public long queryTotal(BaseCriteria criteria) {
                        return ordDeliveryEntityMapper
                                .countByExample((OrdDeliveryEntityCriteria) criteria);
                    }

                    @Override
                    public List<Comparator<OrdDeliveryEntity>> defineComparators() {
                        List<Comparator<OrdDeliveryEntity>> ls = new ArrayList<Comparator<OrdDeliveryEntity>>();
                        ls.add(new Comparator<OrdDeliveryEntity>(){

                            @Override
                            public int compare(OrdDeliveryEntity o1, OrdDeliveryEntity o2) {
                                return o1.getId().compareTo(o2.getId());
                            }
                            
                        });
                        return ls;
                    }

                    @Override
                    public RShowEntityChgResponse warpReturnObject(OrdDeliveryEntity t) {
                        RShowEntityChgResponse rsecr = new RShowEntityChgResponse();
                        BeanUtils.copyProperties(t, rsecr);
                        return rsecr;
                    }
                });
    }

    @Override
    public Long queryByEntityCode(SBaseAndImportInfo sBaseAndImportInfo) {
        OrdDeliveryEntityCriteria odec = new OrdDeliveryEntityCriteria();
        Criteria ca = odec.createCriteria();
        if (sBaseAndImportInfo.getShopId() != null && sBaseAndImportInfo.getShopId() > 0)
            ca.andShopIdEqualTo(sBaseAndImportInfo.getShopId());
        if (!(StringUtil.isEmpty(sBaseAndImportInfo.getEntityCode())))
            ca.andEntityCodeEqualTo(sBaseAndImportInfo.getEntityCode());
        if (!(StringUtil.isEmpty(sBaseAndImportInfo.getOrderId())))
            ca.andOrderIdEqualTo(sBaseAndImportInfo.getOrderId());
        return this.ordDeliveryEntityMapper.countByExample(odec);
    }

    @Override
    public List<String> queryBySubId(SBaseAndBatchInfo sBaseAndBatchInfo) {
        final OrdDeliveryEntityCriteria oeic = new OrdDeliveryEntityCriteria();
        oeic.createCriteria().andShopIdEqualTo(sBaseAndBatchInfo.getShopId())
                .andBatchIdEqualTo(sBaseAndBatchInfo.getBatchId())
                .andOrderSubIdEqualTo(sBaseAndBatchInfo.getOrderSubId());
        List<String> entityCodes = new ArrayList<String>();
        List<OrdDeliveryEntity> oeis = this.ordDeliveryEntityMapper.selectByExample(oeic);
        if (CollectionUtils.isEmpty(oeis)) {
            LogUtil.info(MODULE, "未找到["+sBaseAndBatchInfo.getBatchId()+"|" + sBaseAndBatchInfo.getOrderSubId() + "]该子订单的实体信息");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312006);
        }
        for(OrdDeliveryEntity oei :oeis){
            entityCodes.add(oei.getEntityCode());
        }
        return entityCodes;
    }

}
