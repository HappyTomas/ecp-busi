package com.zengshi.ecp.order.service.busi.impl.report;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.order.dao.mapper.busi.GoodPayedReportMapper;
import com.zengshi.ecp.order.dao.mapper.busi.manual.GoodPayedReportUalMapper;
import com.zengshi.ecp.order.dao.model.GoodPayedReport;
import com.zengshi.ecp.order.dao.model.GoodPayedReportCriteria;
import com.zengshi.ecp.order.dao.model.OrdMainShopIdx;
import com.zengshi.ecp.order.dao.model.OrdMainShopIdxCriteria;
import com.zengshi.ecp.order.dubbo.dto.RQueryGoodPayedRequest;
import com.zengshi.ecp.order.dubbo.dto.RSalesChartPageResponse;
import com.zengshi.ecp.order.dubbo.dto.RSalesChartRequest;
import com.zengshi.ecp.order.dubbo.dto.RSalesChartResponse;
import com.zengshi.ecp.order.dubbo.dto.SOrderIdx;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.service.busi.interfaces.report.IGoodPayedSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

public class GoodPayedSVImpl extends GeneralSQLSVImpl implements IGoodPayedSV {
    
    @Resource
    private GoodPayedReportUalMapper goodPayedReportUalMapper;
    
    @Resource
    private GoodPayedReportMapper goodPayedReportMapper;

    @Override
    public Long querySumBuyNumBySkuId(RQueryGoodPayedRequest rQueryGoodPayedRequest)
            throws BusinessException {
        GoodPayedReportCriteria  cri = new GoodPayedReportCriteria();
        cri.createCriteria().andSkuIdEqualTo(rQueryGoodPayedRequest.getSkuId())
                            .andSiteIdEqualTo(rQueryGoodPayedRequest.getSiteId());
        return this.goodPayedReportUalMapper.countSumBuyNumByExample(cri);
    }

    private PageResponseDTO<RSalesChartPageResponse> querySkuSalesChartPage(RSalesChartRequest rSalesChartRequest){
        GoodPayedReportCriteria example = new GoodPayedReportCriteria();
        example.setLimitClauseCount(rSalesChartRequest.getTopNum());
        example.setLimitClauseStart(0);
        example.setOrderByClause("buy_num desc");
        example.createCriteria().andSiteIdEqualTo(rSalesChartRequest.getSiteId());
        
        return super.queryByPagination(rSalesChartRequest, example, true, new PaginationCallback<GoodPayedReport, RSalesChartPageResponse>() {

            @Override
            public List<GoodPayedReport> queryDB(BaseCriteria bCriteria) {
                return goodPayedReportMapper.selectByExample((GoodPayedReportCriteria)bCriteria) ;
            }

            @Override
            public long queryTotal(BaseCriteria bCriteria) {
                return goodPayedReportMapper.countByExample((GoodPayedReportCriteria)bCriteria);
            }

            @Override
            public List<Comparator<GoodPayedReport>> defineComparators() {
                List<Comparator<GoodPayedReport>> ls = new ArrayList<Comparator<GoodPayedReport>>();
                ls.add(new Comparator<GoodPayedReport>(){

                    @Override
                    public int compare(GoodPayedReport o1, GoodPayedReport o2) {
                        return o2.getBuyNum().compareTo(o1.getBuyNum());
                    }
                    
                });
                return ls;
            }

            @Override
            public RSalesChartPageResponse warpReturnObject(GoodPayedReport goodPayedReport) {
                    RSalesChartPageResponse sdoi = new RSalesChartPageResponse();
                    BeanUtils.copyProperties(goodPayedReport, sdoi);
                    return sdoi;
            }

            
        });
    }
    
    @Override
    public RSalesChartResponse querySkuSalesChart(RSalesChartRequest rSalesChartRequest)
            throws BusinessException {
        RSalesChartResponse rSalesChartResponse = new RSalesChartResponse();
        PageResponseDTO<RSalesChartPageResponse>  pagd = querySkuSalesChartPage(rSalesChartRequest);
        if(CollectionUtils.isNotEmpty(pagd.getResult())){
            List<Long> skus = new ArrayList<Long>();
            for(RSalesChartPageResponse rSalesChartPageResponse :pagd.getResult()){
                skus.add(rSalesChartPageResponse.getSkuId());
            }
            rSalesChartResponse.setSkuIds(skus);
        }
        return rSalesChartResponse;
    }

    /** 
     * queryExistSkuId:判断SkuId是否存在. <br/> 
     * @author cbl 
     * @param goodPayedReport 
     * @return 
     * @since JDK 1.6 
     */ 
    private GoodPayedReport queryGoodPayedReportBySkuId(GoodPayedReport goodPayedReport){
        GoodPayedReportCriteria example = new GoodPayedReportCriteria();
        example.createCriteria().andSkuIdEqualTo(goodPayedReport.getSkuId())
                                .andSiteIdEqualTo(goodPayedReport.getSiteId());
        List<GoodPayedReport> goodPayedReports = this.goodPayedReportMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(goodPayedReports)){
            return goodPayedReports.get(0);
        } else {
            return null;
        }
    }
    @Override
    public void saveGoodPayedReport(GoodPayedReport goodPayedReport) throws BusinessException {
        GoodPayedReport ret =  queryGoodPayedReportBySkuId(goodPayedReport);
        if(ret != null){
            GoodPayedReport gpr = new GoodPayedReport();
            gpr.setBuyNum(ret.getBuyNum() + goodPayedReport.getBuyNum());
            gpr.setOrdNum(ret.getOrdNum() + 1);
            gpr.setUpdateTime(DateUtil.getSysDate());
            gpr.setUpdateStaff(goodPayedReport.getUpdateStaff());
            
            GoodPayedReportCriteria example = new GoodPayedReportCriteria();
            example.createCriteria().andSkuIdEqualTo(goodPayedReport.getSkuId())
                                    .andSiteIdEqualTo(goodPayedReport.getSiteId());
            this.goodPayedReportMapper.updateByExampleSelective(gpr, example);
            
        } else {
            goodPayedReport.setId(StringUtil.getRandomString(32));
            goodPayedReport.setOrdNum(1l);
            goodPayedReport.setCreateTime(DateUtil.getSysDate());
            goodPayedReport.setUpdateTime(DateUtil.getSysDate());
            this.goodPayedReportMapper.insert(goodPayedReport); 
        }
    }

}

