package com.zengshi.ecp.order.service.busi.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.model.OrdEntityChange;
import com.zengshi.ecp.order.dao.model.OrdEntityImport;
import com.zengshi.ecp.order.dao.model.OrdEntityImportGroup;
import com.zengshi.ecp.order.dao.model.OrdSub;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndImportInfo;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndSubInfo;
import com.zengshi.ecp.order.dubbo.dto.SEntityChkInfo;
import com.zengshi.ecp.order.dubbo.util.DelyConstants;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdDeliveryEntitySV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdEntityChangeSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdEntityChkSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdEntityImportSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdFileImportSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

public class OrdEntiytChkSVImpl implements IOrdEntityChkSV {

    @Resource
    private IOrdEntityImportSV ordEntityImportSV;

    @Resource
    private IOrdFileImportSV ordFileImportSV;

    @Resource
    private IOrdDeliveryEntitySV ordDeliveryEntitySV;

    @Resource
    private IOrdSubSV ordSubSV;
    
    @Resource
    private IOrdEntityChangeSV OrdEntityChangeSV;
    
    private static final String MODULE = OrdEntiytChkSVImpl.class.getName();

    /**
     * updateImportNoStatus:根据批次号更新状态. <br/>
     * 
     * @author cbl
     * @param sBaseAndImportInfo
     * @since JDK 1.6
     */
    private void updateImportNoStatus(SBaseAndImportInfo sBaseAndImportInfo, String flag) {

        // 更新批次表记录状态
        this.ordFileImportSV.updateStatusByImportNo(sBaseAndImportInfo);
        if ("1".equals(flag)) {
            // 更新中间表批次记录状态
            this.ordEntityImportSV.updateStatusByImportNo(sBaseAndImportInfo);
        } else {
            //更新变更中间表记录状态
            this.OrdEntityChangeSV.updateStatusByImportNo(sBaseAndImportInfo);
        }
    }

    /**
     * updateImportRemark:更新实体编号的校验结果信息. <br/>
     * 
     * @author cbl
     * @param sEntityChkInfo
     * @since JDK 1.6
     */
    private void updateImportRemark(SEntityChkInfo sEntityChkInfo) {
        // 更新中间表记录的校验结果信息
        this.ordEntityImportSV.updateRemark(sEntityChkInfo);
    }
    /**
     * updateImportRemark:更新变更实体编号的校验结果信息. <br/>
     * 
     * @author cbl
     * @param sEntityChkInfo
     * @since JDK 1.6
     */
    private void updateImportRemarkChg(SEntityChkInfo sEntityChkInfo) {
        // 更新中间表记录的校验结果信息
        this.OrdEntityChangeSV.updateImportRemarkChg(sEntityChkInfo);
    }
    /**
     * chkByImportNo:从批次的维度进行校验. <br/>
     * 
     * @author cbl
     * @param sBaseAndImportInfo
     * @since JDK 1.6
     */
    private void chkByImportNo(SBaseAndImportInfo sBaseAndImportInfo) {
        /*
         * 从批次的维度进行校验当前批次数据是否有重复
         */
        Long importAll = this.ordEntityImportSV.queryCountByImportNo(sBaseAndImportInfo);
        Long importDist = this.ordEntityImportSV.queryCountByImportNoDist(sBaseAndImportInfo);
        // 如果两个不相等则表示有重复数据
        if (importAll.longValue() == 0) {
            throw new BusinessException("数据异常");
        } else if (!(importAll.equals(importDist))) {
            LogUtil.info(MODULE, importAll.toString()+"------"+importDist.toString());
            throw new BusinessException("有重复的实体编号");
        }
    }
    /**
     * chkByImportNo:从批次的维度进行校验. <br/>
     * 
     * @author cbl
     * @param sBaseAndImportInfo
     * @since JDK 1.6
     */
    private void chkByImportNoChg(SBaseAndImportInfo sBaseAndImportInfo) {
        /*
         * 从批次的维度进行校验当前批次数据是否有重复
         */
        //校验变更前和变更后实体编号是否有重复
        Long importAll = this.OrdEntityChangeSV.queryCountByImportNo(sBaseAndImportInfo);
        Long bfCount = this.OrdEntityChangeSV.queryCountEntityCodeBf(sBaseAndImportInfo);
        Long afCount = this.OrdEntityChangeSV.queryCountEntityCodeAf(sBaseAndImportInfo);
        // 如果两个不相等则表示有重复数据
        if (importAll.longValue() == 0) {
            throw new BusinessException("数据异常");
        } else {
            if (!(importAll.equals(bfCount))) {
                throw new BusinessException("变更前的实体编号有重复");
            }
            if(!(importAll.equals(afCount))){
                throw new BusinessException("新实体编号有重复");
            }
        } 
    }

    /**
     * chkByOrdSub:从子订单的维度进行校验. <br/>
     * 
     * @author cbl
     * @param sBaseAndImportInfo
     * @since JDK 1.6
     */
    private Boolean chkByOrdSub(SBaseAndImportInfo sBaseAndImportInfo) {
        /*
         * 从子订单的维度进行校验子订单与订单号是否匹配子订单上传的实体编号数量大于子订单未发货数量
         */
        LogUtil.info(MODULE, "进入"+sBaseAndImportInfo.getImportNo()+" 该批次的子订单校验");
        Boolean isPass = true;
        List<OrdEntityImportGroup> oeigs = this.ordEntityImportSV
                .queryOrderSubByImportNo(sBaseAndImportInfo);
        if (CollectionUtils.isEmpty(oeigs)) {
            LogUtil.info(MODULE, "数据异常");
            throw new BusinessException("数据异常");
        }
        for (OrdEntityImportGroup oeig : oeigs) {
            Long num = this.ordSubSV.queryCountByOrdSubId(oeig);
            // 如果查询的记录数小于等于0 则表示订单号与子订单号不匹配
            if (num == null || num.longValue() <= 0) {
                isPass = false;
                SEntityChkInfo seci = new SEntityChkInfo();
                seci.setShopId(sBaseAndImportInfo.getShopId());
                seci.setImportNo(sBaseAndImportInfo.getImportNo());
                seci.setOrderId(sBaseAndImportInfo.getOrderId());
                seci.setOrderSubId(seci.getOrderSubId());
                seci.setRemark("子订单号[" + oeig.getOrderSubId() + "]" + "与主订单号不匹配");
                updateImportRemark(seci);
                continue;
            }
            // 获取子订单未发货数量
            SBaseAndSubInfo sbasi = new SBaseAndSubInfo();
            sbasi.setOrderId(oeig.getOrderId());
            sbasi.setOrderSubId(oeig.getOrderSubId());
            OrdSub os = this.ordSubSV.findByOrderSubId(sbasi);
            if(os == null){
                LogUtil.info(MODULE, "未找到["+sbasi.getOrderId()+"]该订单的子订单信息");
                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312002);
            }
            Long reamainAmout = os.getRemainAmount();
            // 子订单上传的实体编号数量大于子订单未发货数量
            SBaseAndImportInfo sbai = new SBaseAndImportInfo();
            sbai.setShopId(sBaseAndImportInfo.getShopId());
            sbai.setImportNo(sBaseAndImportInfo.getImportNo());
            sbai.setOrderSubId(oeig.getOrderSubId());
            Long sendAmount = this.ordEntityImportSV.queryCountByOrderSubId(sbai);
            if (reamainAmout < sendAmount) {
                isPass = false;
                SEntityChkInfo seci = new SEntityChkInfo();
                seci.setShopId(sBaseAndImportInfo.getShopId());
                seci.setImportNo(sBaseAndImportInfo.getImportNo());
                seci.setOrderId(sBaseAndImportInfo.getOrderId());
                seci.setOrderSubId(seci.getOrderSubId());
                seci.setRemark("子订单号[" + oeig.getOrderSubId() + "]" + "上传的实体编号数量大于子订单未发货数量");
                updateImportRemark(seci);
            }
        }
        return isPass;
    }
    /**
     * chkByEntityCode:从实体编号的维度进行校验. <br/>
     * 
     * @author cbl
     * @param sBaseAndImportInfo
     * @since JDK 1.6
     */
    private Boolean chkByEntityCode(SBaseAndImportInfo sBaseAndImportInfo) {
        /*
         * 从实体编号的维度进行校验 格式是否正确 实体编号是否已发货
         */
        LogUtil.info(MODULE, "进入"+sBaseAndImportInfo.getImportNo()+" 该批次的单个实体编号校验");
        Boolean isPass = true;
        SBaseAndImportInfo sbai = new SBaseAndImportInfo();
        sbai.setShopId(sBaseAndImportInfo.getShopId());
        sbai.setImportNo(sBaseAndImportInfo.getImportNo());
        List<OrdEntityImport> oeis = this.ordEntityImportSV.queryByImportNo(sBaseAndImportInfo);
        if (CollectionUtils.isEmpty(oeis)) {
            throw new BusinessException("未找到批次信息");
        }
        /* 校验 */
        for (OrdEntityImport oei : oeis) {
            // 实体编号是否已发货
            SBaseAndImportInfo sbai1 = new SBaseAndImportInfo();
            sbai1.setEntityCode(oei.getEntityCode());
            Long num = this.ordDeliveryEntitySV.queryByEntityCode(sbai1);
            if (num == null ||num > 0) {
                isPass = false;
                SEntityChkInfo seci = new SEntityChkInfo();
                seci.setShopId(sBaseAndImportInfo.getShopId());
                seci.setImportNo(sBaseAndImportInfo.getImportNo());
                seci.setOrderId(sBaseAndImportInfo.getOrderId());
                seci.setEntityCode(oei.getEntityCode());
                seci.setRemark("实体编号[" + oei.getEntityCode() + "]" + "该实体编号已发货");
                updateImportRemark(seci);
            }
            //实体编号是否已经在中间表中
            SBaseAndImportInfo sbai2 = new SBaseAndImportInfo();
            sbai2.setShopId(sBaseAndImportInfo.getShopId());
            sbai2.setEntityCode(oei.getEntityCode());
            Long inum = this.ordEntityImportSV.queryCountByEntityCode(sbai2);
            if(inum > 1){
                isPass = false;
                SEntityChkInfo seci = new SEntityChkInfo();
                seci.setShopId(sBaseAndImportInfo.getShopId());
                seci.setImportNo(sBaseAndImportInfo.getImportNo());
                seci.setOrderId(sBaseAndImportInfo.getOrderId());
                seci.setEntityCode(oei.getEntityCode());
                seci.setRemark("实体编号[" + oei.getEntityCode() + "]" + "有["+inum+"]重复记录");
                updateImportRemark(seci);
            }
            // 实体编号格式校验
        }
        return isPass;
    }
    
    /**
     * chkByEntityCode:从实体编号的维度进行校验. <br/>
     * 
     * @author cbl
     * @param sBaseAndImportInfo
     * @since JDK 1.6
     */
    private Boolean chkByEntityCodeChg(SBaseAndImportInfo sBaseAndImportInfo) {
        /*
         * 从实体编号的维度进行校验 格式是否正确 实体编号是否已发货
         */
        Boolean isPass = true;
        SBaseAndImportInfo sbai = new SBaseAndImportInfo();
        sbai.setShopId(sBaseAndImportInfo.getShopId());
        sbai.setImportNo(sBaseAndImportInfo.getImportNo());
        List<OrdEntityChange> oecs = this.OrdEntityChangeSV.queryByImportNo(sbai);
        if (CollectionUtils.isEmpty(oecs)) {
            throw new BusinessException("未找到批次信息");
        }
        /* 校验 */
        for (OrdEntityChange oec : oecs) {
            //校验变更前的实体编号是否存在
            SBaseAndImportInfo sbai0 = new SBaseAndImportInfo();
            sbai0.setShopId(sBaseAndImportInfo.getShopId());
            sbai0.setOrderId(sBaseAndImportInfo.getOrderId());
            sbai0.setEntityCode(oec.getEntityCodeBf());
            Long num0 = this.ordDeliveryEntitySV.queryByEntityCode(sbai0);
            if (num0 <= 0){
//                LogUtil.error(MODULE, "未找到实体编号信息:" + oec.getEntityCodeBf());
//                throw new BusinessException("未找到实体编号信息");
                isPass = false;
                SEntityChkInfo seci = new SEntityChkInfo();
                seci.setShopId(sBaseAndImportInfo.getShopId());
                seci.setImportNo(sBaseAndImportInfo.getImportNo());
                seci.setOrderId(sBaseAndImportInfo.getOrderId());
                seci.setEntityCode(oec.getEntityCodeAf());
                seci.setEntityCodeBf(oec.getEntityCodeBf());
                seci.setRemark("[" + oec.getEntityCodeBf() + "]" + "未找到该实体编号已发货信息");
                updateImportRemarkChg(seci);
                continue;
            } 
            
            // 实体编号是否已发货
            SBaseAndImportInfo sbai1 = new SBaseAndImportInfo();
            sbai1.setEntityCode(oec.getEntityCodeAf());
            Long num = this.ordDeliveryEntitySV.queryByEntityCode(sbai1);
            if (num > 0) {
                isPass = false;
                SEntityChkInfo seci = new SEntityChkInfo();
                seci.setShopId(sBaseAndImportInfo.getShopId());
                seci.setImportNo(sBaseAndImportInfo.getImportNo());
                seci.setOrderId(sBaseAndImportInfo.getOrderId());
                seci.setEntityCode(oec.getEntityCodeAf());
                seci.setEntityCodeBf(oec.getEntityCodeBf());
                seci.setRemark("[" + oec.getEntityCodeAf() + "]" + "该实体编号已发货");
                updateImportRemarkChg(seci);
            }
            // 实体编号格式校验
        }
        return isPass;
    }

    @Override
    public Boolean saveEntiytCodeAddChk(SBaseAndImportInfo sBaseAndImportInfo) {
        /* 修改批次状态为02校验中 */
        LogUtil.info(MODULE, "修改:"+sBaseAndImportInfo.getImportNo() +"该批次状态");
        sBaseAndImportInfo.setStatus(DelyConstants.ImportStatus.IMPORT_STATUS_PRO);
        updateImportNoStatus(sBaseAndImportInfo,"1");
        /*
         * 从批次的维度进行校验当前批次数据是否有重复
         */
        LogUtil.info(MODULE, "校验:"+sBaseAndImportInfo.getImportNo() +"该批次是否有重复数据");
        chkByImportNo(sBaseAndImportInfo);
        /*
         * 从子订单的维度进行校验和从实体编号的维度进行校验
         */
        LogUtil.info(MODULE, "校验:"+sBaseAndImportInfo.getImportNo() +"该批次的实体编号");
        if (chkByOrdSub(sBaseAndImportInfo) && chkByEntityCode(sBaseAndImportInfo)) {
            sBaseAndImportInfo.setStatus(DelyConstants.ImportStatus.IMPORT_STATUS_SUCCESS);
            updateImportNoStatus(sBaseAndImportInfo,"1");
        } else {
            sBaseAndImportInfo.setStatus(DelyConstants.ImportStatus.IMPORT_STATUS_FAIL);
            updateImportNoStatus(sBaseAndImportInfo,"1");
            return false;

        }
        return true;
    }

    @Override
    public Boolean saveEntiytCodeChgChk(SBaseAndImportInfo sBaseAndImportInfo) {
        /* 修改批次状态为02校验中 */
        sBaseAndImportInfo.setStatus(DelyConstants.ImportStatus.IMPORT_STATUS_PRO);
        updateImportNoStatus(sBaseAndImportInfo,"0");
        /*
         * 从批次的维度进行校验当前批次数据是否有重复
         */
        chkByImportNoChg(sBaseAndImportInfo);
        /*
         * 从订单的维度进行校验和从实体编号的维度进行校验
         */
        if (chkByEntityCodeChg(sBaseAndImportInfo)) {
            sBaseAndImportInfo.setStatus(DelyConstants.ImportStatus.IMPORT_STATUS_SUCCESS);
            updateImportNoStatus(sBaseAndImportInfo,"0");
        } else {
            sBaseAndImportInfo.setStatus(DelyConstants.ImportStatus.IMPORT_STATUS_FAIL);
            updateImportNoStatus(sBaseAndImportInfo,"0");
            return false;

        }
        return true;
    }

}
