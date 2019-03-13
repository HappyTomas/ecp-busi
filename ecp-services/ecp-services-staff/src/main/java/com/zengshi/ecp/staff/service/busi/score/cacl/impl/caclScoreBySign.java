package com.zengshi.ecp.staff.service.busi.score.cacl.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import com.zengshi.ecp.general.order.dto.PayQuartzInfoRequest;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ProductInfoReqDto;
import com.zengshi.ecp.staff.dubbo.dto.ScoreFuncDefReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreResultResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreSourceReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IScoreCaclRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IScoreInfoRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.score.cacl.ICaclScore;
import com.zengshi.ecp.staff.service.busi.score.interfaces.IScoreInfoSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

import scala.util.Random;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 签到送积分<br>
 * Date:2016年9月19日下午7:53:15  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public class caclScoreBySign implements ICaclScore {
    
    
    @Resource
    private IScoreInfoRSV scoreInfoRSV;
    
    @Resource
    private IScoreCaclRSV scoreCaclRSV;
    
    @Resource
    private IScoreInfoSV scoreInfoSV;
    
    //积分类型：签到送积分
    private static final String SIGN_SOURCE_TYPE = "7001";
    
    @Resource
    private IScoreInfoSV scorescourceSV;//积分账户、来源明细、操作日志管理

    public ScoreResultResDTO caclScore(String pSourceType, Map<Integer, String> pParaMap,
            CustInfoReqDTO pCustInfo, ProductInfoReqDto pProductInfo) {
        ScoreResultResDTO rs = new ScoreResultResDTO();
        String sc = pParaMap.get(2);
        Long n = Long.valueOf(sc);
        rs.setScore(n);
        return rs;
    }

    @Override
    public ScoreResultResDTO saveDeal(String pSourceType, CustInfoReqDTO pCustInfo,
            PayQuartzInfoRequest pOrderInfo) {
    	/*查询用户积分账户，不存在则创建*/
        ScoreInfoResDTO scoreRes = scoreInfoRSV.findScoreInfoByCustAndCreate(pCustInfo);
        ScoreInfoReqDTO scoreInfo = new ScoreInfoReqDTO();
        ObjectCopyUtil.copyObjValue(scoreRes, scoreInfo, null, false);
        
        ScoreResultResDTO result = new ScoreResultResDTO();
        result.setScore(0L);
        result.setActionType(pSourceType);
        result.setOptType(StaffConstants.Score.SCORE_OPT_TYPE_1);
        result.setRemark("签到获得积分");
        
        ScoreResultResDTO score = scoreCaclRSV.saveDoCaclScore(result, pCustInfo,scoreInfo,null);
        //如果有配置，则走配置的方式送积分
        if(score!= null && score.getScore() > 0) {
            result.setScore(score.getScore());
            //更新积分账户信息
            scoreInfoRSV.updateScoreInfo(pCustInfo, scoreInfo, result);
            
            //记录连续签到次数
        	scoreInfoSV.saveScoreSignCheck(pCustInfo.getId());
            //如果没有配置，则随机送1~10积分。
        } else {
        	//如果已经签到过，直接返回
        	if (this.signAlready(pCustInfo)) {
        		return result;
        	}
        	Random random = new Random();
        	Integer resScore = random.nextInt(10) + 1;//random.nextInt(10)是[0,10)，所以+1最多也是10
        	result.setScore(resScore.longValue());
        	//保存积分来源明细
        	ScoreSourceReqDTO scoreSourceReq = new ScoreSourceReqDTO();
        	scoreSourceReq.setStaffId(pCustInfo.getId());
        	scoreSourceReq.setScore(result.getScore());
        	scoreSourceReq.setOptType(StaffConstants.Score.SCORE_OPT_TYPE_1);
        	scoreSourceReq.setSourceType(SIGN_SOURCE_TYPE);
        	scoreSourceReq.setCreateStaff(pCustInfo.getId());
        	scoreSourceReq.setUpdateStaff(pCustInfo.getId());
        	scoreSourceReq.setRemark("签到送积分");
        	scoreInfoRSV.saveScoreAdd(scoreSourceReq);
        	
        	//记录连续签到次数
        	scoreInfoSV.saveScoreSignCheck(pCustInfo.getId());
        	
        }
        return result;
    }

    @Override
    public Map<Integer, String> judgeActionPara(ScoreFuncDefReqDTO fun,
            Map<Long, HashMap<Integer, String>> paraMap, CustInfoReqDTO pCustInfo,ProductInfoReqDto productInfo) {
        //如果今天已经签到获取了积分，则返回null，不进行计算
        if(signAlready(pCustInfo)) {
            return null;
        }
        //遍历函数参数Map容器
        Map<Integer, String> para = null;
        Iterator<Entry<Long, HashMap<Integer, String>>> entryKeyIterator =  paraMap.entrySet().iterator();
        
        while(entryKeyIterator.hasNext()) {
            Entry<Long, HashMap<Integer, String>> e = entryKeyIterator.next();
            para = e.getValue();
            if(para.get(0).equals(fun.getActionType()) && para.get(1).equals(fun.getFuncName())){
                return para;
            }
        }
        return null;
    }

    /**
     * 
     * loginAlready:(查询今天是否已经获得了签到积分). <br/> 
     * 
     * @author huangxl5 
     * @param custInfo
     * @return 
     * @since JDK 1.6
     */
    private boolean signAlready(CustInfoReqDTO custInfo) {
        ScoreSourceReqDTO req = new ScoreSourceReqDTO();
        //查询时间为今天开始~今天结束
        req.setSelDateFrom(DateUtil.getTheDayFirstSecond(DateUtil.getSysDate()));
        req.setSelDateEnd(DateUtil.getTheDayLastSecond(DateUtil.getSysDate()));
        //用户id
        req.setStaffId(custInfo.getId());
        req.setSourceType(SIGN_SOURCE_TYPE);
        req.setPageNo(0);
        boolean bLogined = scorescourceSV.listScoreSource(req).getResult() != null ? true : false;
        return bLogined;
    }
}
