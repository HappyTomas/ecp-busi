package com.zengshi.ecp.order.service.busi.impl.report;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.order.dao.mapper.busi.GoodStaffPayedReportMapper;
import com.zengshi.ecp.order.dao.mapper.busi.OrdSubStaffIdxMapper;
import com.zengshi.ecp.order.dao.mapper.busi.manual.OrdMainShopIdxUalMapper;
import com.zengshi.ecp.order.dao.model.GoodStaffPayedReport;
import com.zengshi.ecp.order.dao.model.GoodStaffPayedReportCriteria;
import com.zengshi.ecp.order.dao.model.OrdSubStaffIdxCriteria;
import com.zengshi.ecp.order.dubbo.dto.OrdMainShopUal;
import com.zengshi.ecp.order.dubbo.dto.ROrdStaffTradeInfoReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdStaffTradeInfoResp;
import com.zengshi.ecp.order.dubbo.dto.RQueryGoodPayedRequest;
import com.zengshi.ecp.order.service.busi.interfaces.report.IGoodStaffPayedSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Created by wang on 15/12/31.
 */
public class GoodStaffPayedSVImpl extends GeneralSQLSVImpl implements IGoodStaffPayedSV {
    @Resource
    private GoodStaffPayedReportMapper goodStaffPayedReportMapper;

    @Resource
    private OrdSubStaffIdxMapper ordSubStaffIdxMapper;
    
    @Resource
    private OrdMainShopIdxUalMapper ordMainShopIdxUalMapper;

    @Override
    public Long querySumBuyNumByGoodStaff(RQueryGoodPayedRequest rQueryGoodPayedRequest)
            throws BusinessException{
//        GoodStaffPayedReportCriteria cri = new GoodStaffPayedReportCriteria();
        OrdSubStaffIdxCriteria cri = new OrdSubStaffIdxCriteria();
        List<String> notPayList = new ArrayList<>();
        notPayList.add("99");
        notPayList.add("80");
        cri.createCriteria().andSkuIdEqualTo(rQueryGoodPayedRequest.getSkuId())
                .andSiteIdEqualTo(rQueryGoodPayedRequest.getSiteId())
                .andStaffIdEqualTo(rQueryGoodPayedRequest.getStaff().getId())
                .andStatusNotIn(notPayList);
        return this.ordSubStaffIdxMapper.countByExample(cri);
    }
    @Override
    public Long queryStaffBuyNumByGoodStaff(RQueryGoodPayedRequest rQueryGoodPayedRequest)
            throws BusinessException{
        GoodStaffPayedReportCriteria cri = new GoodStaffPayedReportCriteria();
        cri.createCriteria().andSkuIdEqualTo(rQueryGoodPayedRequest.getSkuId())
                .andSiteIdEqualTo(rQueryGoodPayedRequest.getSiteId())
                .andStaffIdEqualTo(rQueryGoodPayedRequest.getStaff().getId());
        return this.goodStaffPayedReportMapper.countByExample(cri);
    }
    /**
     * queryExistSkuId:判断SkuId是否存在. <br/>
     * @author cbl
     * @param goodStaffPayedReport
     * @return
     * @since JDK 1.6
     */
    private GoodStaffPayedReport queryGoodStaffPayedReport(GoodStaffPayedReport goodStaffPayedReport){

        GoodStaffPayedReportCriteria example = new GoodStaffPayedReportCriteria();
        example.createCriteria().andSkuIdEqualTo(goodStaffPayedReport.getSkuId())
                .andSiteIdEqualTo(goodStaffPayedReport.getSiteId())
                .andStaffIdEqualTo(goodStaffPayedReport.getStaffId());
        List<GoodStaffPayedReport> goodStaffPayedReports = this.goodStaffPayedReportMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(goodStaffPayedReports)){
            return goodStaffPayedReports.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void saveGoodStaffPayed(GoodStaffPayedReport goodStaffPayedReport) throws BusinessException {

        GoodStaffPayedReport ret =  queryGoodStaffPayedReport(goodStaffPayedReport);
        if(ret != null){
            GoodStaffPayedReport gpr = new GoodStaffPayedReport();
            gpr.setBuyNum(ret.getBuyNum() + goodStaffPayedReport.getBuyNum());
            gpr.setOrdNum(ret.getOrdNum() + 1);
            gpr.setUpdateTime(DateUtil.getSysDate());
            gpr.setUpdateStaff(goodStaffPayedReport.getUpdateStaff());

            GoodStaffPayedReportCriteria example = new GoodStaffPayedReportCriteria();
            example.createCriteria().andSkuIdEqualTo(goodStaffPayedReport.getSkuId())
                    .andSiteIdEqualTo(goodStaffPayedReport.getSiteId())
                    .andStaffIdEqualTo(goodStaffPayedReport.getStaffId());
            this.goodStaffPayedReportMapper.updateByExampleSelective(gpr, example);

        } else {
            goodStaffPayedReport.setId(StringUtil.getRandomString(32));
            goodStaffPayedReport.setStaffId(goodStaffPayedReport.getStaffId());
            goodStaffPayedReport.setOrdNum(1l);
            goodStaffPayedReport.setCreateTime(DateUtil.getSysDate());
            goodStaffPayedReport.setUpdateTime(DateUtil.getSysDate());
            this.goodStaffPayedReportMapper.insert(goodStaffPayedReport);
        }
    }

    @Override
    public ROrdStaffTradeInfoResp queryStaffTradeTimes(ROrdStaffTradeInfoReq rOrdStaffTradeInfoReq)
            throws BusinessException {
        List<OrdMainShopUal> staffIds = this.ordMainShopIdxUalMapper.selectStaffTradeTimesByExample(rOrdStaffTradeInfoReq);
        if(staffIds == null)
            return null;
        ROrdStaffTradeInfoResp rOrdStaffTradeInfoResp = new ROrdStaffTradeInfoResp();
        rOrdStaffTradeInfoResp.setStaffIds(staffIds);
        return rOrdStaffTradeInfoResp;
    }

    @Override
    public PageResponseDTO<ROrdStaffTradeInfoResp> queryStaffTradeMoney(ROrdStaffTradeInfoReq rOrdStaffTradeInfoReq)
            throws BusinessException {
        return null;
    }
}
