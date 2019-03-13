package com.zengshi.ecp.staff.service.busi.score.cacl.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.general.order.dto.PayQuartzInfoRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dao.model.ScoreInfo;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ProductInfoReqDto;
import com.zengshi.ecp.staff.dubbo.dto.ScoreFuncDefReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreResultResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IScoreCaclRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IScoreInfoRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.score.cacl.ICaclScore;
import com.zengshi.ecp.staff.service.busi.score.interfaces.IScoreInfoSV;
import com.zengshi.paas.utils.MoneyUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 购物送积分计算处理类<br>
 * Date:2016年5月18日下午2:30:36  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public class caclScoreByShopping implements ICaclScore {
    
    @Resource
    private IScoreInfoRSV scoreInfoRSV;
    
    @Resource
    private IScoreInfoSV scoreInfoSV;
    
    @Resource
    private IScoreCaclRSV scoreCaclRSV;
    
    @Resource
    IGdsCategoryRSV goodsCategoryRSV;
    
    private static final String ORDER_PROM_SCORE_TYPE = "5001";//订单中促销赠送的积分类型  
    
    @Override
    public ScoreResultResDTO caclScore(String pSourceType, Map<Integer, String> pParaMap,
            CustInfoReqDTO pCustInfo, ProductInfoReqDto pProductInfo) {
        
        long score = (long) (Math.ceil(pProductInfo.getProductPrice()/Double.valueOf(pParaMap.get(4)))*Double.valueOf(pParaMap.get(5)));
        ScoreResultResDTO resultDTO = new ScoreResultResDTO();
        resultDTO.setScore(score);
        
        return resultDTO;
    }

    @Override
    public ScoreResultResDTO saveDeal(String pSourceType, CustInfoReqDTO pCustInfo,
            PayQuartzInfoRequest pOrderInfo) {
        
        /*查询用户积分账户，不存在则创建*/
        ScoreInfoResDTO scoreRes = scoreInfoRSV.findScoreInfoByCustAndCreate(pCustInfo);
        ScoreInfoReqDTO scoreInfo = new ScoreInfoReqDTO();
        ObjectCopyUtil.copyObjValue(scoreRes, scoreInfo, null, false);

        //积分结果集
        List<ScoreResultResDTO> resultList = new ArrayList<ScoreResultResDTO>();
        
        //订单中促销赠送的积分结果集
        List<ScoreResultResDTO> resultOrderPromList = new ArrayList<ScoreResultResDTO>();
        //积分结果
        ScoreResultResDTO result = new ScoreResultResDTO();
        result.setScore(0L);
        result.setActionType(pSourceType);
        result.setOptType(StaffConstants.Score.SCORE_OPT_TYPE_1);
        result.setRemark("订单【"+pOrderInfo.getOrderId()+"】支付送积分");
        result.setSiteId(pOrderInfo.getSiteId());
        
        List<ROrdCartItemCommRequest> orderItems =  pOrderInfo.getOrdCartItemCommList();
        /*对每个商品进行积分计算*/
        for(ROrdCartItemCommRequest item : orderItems) {
            ProductInfoReqDto product = new ProductInfoReqDto();
            product.setProductID(item.getGdsId() == null ? 0 : item.getGdsId());
            product.setProductType(item.getCategoryCode());
            product.setProductPrice(item.getDiscountMoney() == null ? 0 : item.getDiscountMoney());
            product.setOrderId(item.getOrderId());
            product.setOrderSubId(item.getOrderSubId());
    
            //2.1.子订单积分计算
            ScoreResultResDTO score = scoreCaclRSV.saveDoCaclScore(result, pCustInfo,scoreInfo, product);
            if(score == null) {
                //为了计算子订单的促销积分
                score = new ScoreResultResDTO();
                score.setScore(0L);
            }
            score.setActionType(pSourceType);
            score.setSiteId(pOrderInfo.getSiteId());//站点信息
            resultList.add(score);
            //处理子订单中处理域赠送的积分
            ScoreResultResDTO subOrderPromScore = saveSubOrderPromScore(score, pCustInfo, scoreInfo, item);
            if(subOrderPromScore != null && subOrderPromScore.getScore() > 0) {
                resultOrderPromList.add(subOrderPromScore);
            }
        }
        //积分计算结果总结
        for(ScoreResultResDTO res1 : resultList) {
            result.setScore(result.getScore() + res1.getScore());
        }
        //处理主订单中促销域赠送的积分
        //之所以在这里处理，是因为需要等待子订单所有积分都计算完成，才能进行主订单级别的促销积分计算
        ScoreResultResDTO orderMainPromScore = saveMainOrderPromScore(result, pCustInfo, scoreInfo, pOrderInfo);
        if(orderMainPromScore != null && orderMainPromScore.getScore() > 0) {
            resultOrderPromList.add(orderMainPromScore);
        }
        for(ScoreResultResDTO res2 : resultOrderPromList) {
            result.setScore(result.getScore() + res2.getScore());
        }
        //更新积分账户信息
        if(result.getScore() > 0) {
            scoreInfoRSV.updateScoreInfo(pCustInfo, scoreInfo, result);
        }
        return result;
    }

    /**
     * 
     * saveSubOrderPromScore:(处理子订单促销域中赠送的积分). <br/> 
     * 
     * @author huangxl5 
     * @param scoreResultInfo
     * @param custInfo
     * @param scoreInfo
     * @param suborder
     * @return 
     * @since JDK 1.6
     */
    private ScoreResultResDTO saveSubOrderPromScore(ScoreResultResDTO scoreResultInfo, CustInfoReqDTO custReq, ScoreInfoReqDTO scoreReq, ROrdCartItemCommRequest suborder) {
        
        
        //子订单中促销所获得的积分
        ScoreResultResDTO orderSubPromScore = new ScoreResultResDTO();
        /*1.促销积分赠送入库*/
        if(suborder.getSendOrderSubScoreTimes()!= 0.0 && scoreReq != null) {
            //计算促销积分
            double scoreprom = (double)scoreResultInfo.getScore() * suborder.getSendOrderSubScoreTimes();
            long scoreNew = Long.valueOf(MoneyUtil.convertDoubleToString(scoreprom, 0));
            //通过促销中积分赠送倍数获得的积分
            orderSubPromScore.setScore(orderSubPromScore.getScore() + scoreNew);
        }
        /*2.子订单促销积分入库*/
        if(suborder.getSendOrderSubScore() != null && suborder.getSendOrderSubScore() > 0) {
            orderSubPromScore.setScore(orderSubPromScore.getScore()+suborder.getSendOrderSubScore());
        }
        /*3.如果有获得促销赠送的积分，则入库*/
        if(orderSubPromScore.getScore() > 0) {
            CustInfo custInfo = new CustInfo();
            ObjectCopyUtil.copyObjValue(custReq, custInfo, null, false);
            
            ScoreInfo scoreInfo = new ScoreInfo();
            ObjectCopyUtil.copyObjValue(scoreReq, scoreInfo, null, false);
            
            orderSubPromScore.setActionType(scoreResultInfo.getActionType());
            orderSubPromScore.setScoreType(ORDER_PROM_SCORE_TYPE);//积分类型
            orderSubPromScore.setOptType(StaffConstants.Score.SCORE_OPT_TYPE_1);
            orderSubPromScore.setOrderId(suborder.getOrderId());
            orderSubPromScore.setOrderSubId(suborder.getOrderSubId());
            orderSubPromScore.setRemark("子订单【"+suborder.getOrderSubId()+"】促销送积分");
            orderSubPromScore.setSiteId(scoreResultInfo.getSiteId());
            //操作明细表新增记录
            scoreInfoSV.saveScoreOptLog(custInfo, scoreInfo, orderSubPromScore);   
            //积分来源表新增记录
            scoreInfoSV.saveScoreSource(custInfo, scoreInfo, orderSubPromScore);
        }
        return orderSubPromScore;
    }
    
    /**
     * 
     * saveMainOrderPromScore:(处理主订单中的促销积分计算). <br/> 
     * 
     * @author huangxl5 
     * @param scoreResultInfo
     * @param custInfo
     * @param scoreInfo
     * @param pOrderInfo
     * @return 
     * @since JDK 1.6
     */
    private ScoreResultResDTO saveMainOrderPromScore(ScoreResultResDTO scoreResultInfo, CustInfoReqDTO custReq, ScoreInfoReqDTO scoreReq,PayQuartzInfoRequest pOrderInfo) {
        //之所以在这里处理主订单级别的促销积分，是因为主订单级别的促销积分是需要积分整个订单的积分结果
        ScoreResultResDTO orderMainPromScore = new ScoreResultResDTO();

        //订单促销赠送的固定积分
        if(pOrderInfo != null && pOrderInfo.getSendOrderMainScore() != null && pOrderInfo.getSendOrderMainScore() > 0) {
            orderMainPromScore.setScore(pOrderInfo.getSendOrderMainScore());
        }
        //判断是否有主订单级别的积分赠送
        if(pOrderInfo != null && pOrderInfo.getSendOrderMainScoreTimes() > 0.0) {
            double scoreprom = (double)pOrderInfo.getSendOrderMainScoreTimes()*scoreResultInfo.getScore();
            long scoreNew = Long.valueOf(MoneyUtil.convertDoubleToString(scoreprom,0));
            orderMainPromScore.setScore(orderMainPromScore.getScore() + scoreNew);
        }
        if(pOrderInfo != null && orderMainPromScore.getScore() > 0) {
            CustInfo custInfo = new CustInfo();
            ObjectCopyUtil.copyObjValue(custReq, custInfo, null, false);
            
            ScoreInfo scoreInfo = new ScoreInfo();
            ObjectCopyUtil.copyObjValue(scoreReq, scoreInfo, null, false);
            orderMainPromScore.setActionType(scoreResultInfo.getActionType());
            orderMainPromScore.setScoreType(ORDER_PROM_SCORE_TYPE);//积分类型
            orderMainPromScore.setOptType(StaffConstants.Score.SCORE_OPT_TYPE_1);
            orderMainPromScore.setOrderId(pOrderInfo.getOrderId());//订单id
            orderMainPromScore.setOrderSubId(pOrderInfo.getOrderId());//子订单id
            orderMainPromScore.setRemark("订单【"+pOrderInfo.getOrderId()+"】促销送积分");
            orderMainPromScore.setSiteId(scoreResultInfo.getSiteId());
            //操作明细表新增记录
            scoreInfoSV.saveScoreOptLog(custInfo, scoreInfo, orderMainPromScore);
            //积分来源表新增记录
            scoreInfoSV.saveScoreSource(custInfo, scoreInfo, orderMainPromScore);
        }
        return orderMainPromScore;
    }

    @Override
    public Map<Integer, String> judgeActionPara(ScoreFuncDefReqDTO fun, Map<Long, HashMap<Integer, String>> paraMap, CustInfoReqDTO pCustInfo,ProductInfoReqDto productInfo) {

        Map<Integer, String> para = null;
        //遍历函数参数Map容器
        Iterator<Entry<Long, HashMap<Integer, String>>> entryKeyIterator = paraMap.entrySet().iterator();
        while(entryKeyIterator.hasNext()) {
            Entry<Long, HashMap<Integer, String>> e = entryKeyIterator.next();
            para = e.getValue();
            //1.首先根据商品ID判断是否有积分计算规则
            //0：积分来源类型   1：函数名  2：商品分类类型  3：商品ID
            if(para.get(0).equals(fun.getActionType()) && para.get(1).equals(fun.getFuncName()) && "*".equals(para.get(2))&& Long.valueOf(para.get(3)) == productInfo.getProductID()) {
                return para;
            }
        }

        //2.商品ID没有配置积分计算规则，则根据商品类型来判断积分计算规则
        //循环获取商品类型的父类型
        GdsCategoryReqDTO reqDto = new GdsCategoryReqDTO();
        reqDto.setCatgCode(productInfo.getProductType());
        List<GdsCategoryRespDTO> productTypeList =  goodsCategoryRSV.queryCategoryTraceUpon(reqDto);
        /*此代码不影响整体逻辑，主要为了不依赖商品域的分类数据，保证测试正常进行*/
        if(CollectionUtils.isEmpty(productTypeList)) {
            productTypeList = new ArrayList<GdsCategoryRespDTO>();
            GdsCategoryRespDTO categoryRespDTO = new GdsCategoryRespDTO();
            categoryRespDTO.setCatgCode(productInfo.getProductType());
            productTypeList.add(categoryRespDTO);
        }
        int listLength = productTypeList.size();
        for(int i = listLength - 1; i >= 0; i--) {
            //获取到商品类型，执行参数规则判断
            GdsCategoryRespDTO dto = productTypeList.get(i);
            Iterator<Entry<Long, HashMap<Integer, String>>> eIterator =  null;
            eIterator = paraMap.entrySet().iterator();
            while(eIterator.hasNext()) {
                Entry<Long, HashMap<Integer, String>> e = eIterator.next();
                para = e.getValue();
                //0: 积分来源类型
                //1：计算函数名
                //2：商品分类类型
                //3：商品ID
                if( para.get(0).equals(fun.getActionType()) && para.get(1).equals(fun.getFuncName())&& dto.getCatgCode().equals(para.get(2))&& "*".equals(para.get(3))){
                    return para;
                }
            }
        }
        return null;
    }
}

