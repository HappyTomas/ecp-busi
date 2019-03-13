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


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月26日下午4:34:39  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6
 * 
 * 普通登陆计算积分
 */
public class caclScoreByLogin implements ICaclScore {
    
    
    @Resource
    private IScoreInfoRSV scoreInfoRSV;
    
    @Resource
    private IScoreCaclRSV scoreCaclRSV;
    
    //登陆积分来源类型
    private static final String LOGIN_SOURCE_TYPE = "1001";
    
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
        result.setRemark("登陆获得积分");
        
        ScoreResultResDTO score = scoreCaclRSV.saveDoCaclScore(result, pCustInfo,scoreInfo,null);
        if(score!= null &&score.getScore() > 0) {
            result.setScore(score.getScore());
            //更新积分账户信息
            scoreInfoRSV.updateScoreInfo(pCustInfo, scoreInfo, result);
        }
        return result;
    }

    @Override
    public Map<Integer, String> judgeActionPara(ScoreFuncDefReqDTO fun,
            Map<Long, HashMap<Integer, String>> paraMap, CustInfoReqDTO pCustInfo,ProductInfoReqDto productInfo) {
        //如果今天已经登陆获取了积分，则返回null，不进行计算
        if(loginAlready(pCustInfo)) {
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
     * loginAlready:(查询今天是否已经获得了登录积分). <br/> 
     * 
     * @author huangxl5 
     * @param custInfo
     * @return 
     * @since JDK 1.6
     */
    private boolean loginAlready(CustInfoReqDTO custInfo) {
        ScoreSourceReqDTO req = new ScoreSourceReqDTO();
        //查询时间为今天开始~今天结束
        req.setSelDateFrom(DateUtil.getTheDayFirstSecond(DateUtil.getSysDate()));
        req.setSelDateEnd(DateUtil.getTheDayLastSecond(DateUtil.getSysDate()));
        //用户id
        req.setStaffId(custInfo.getId());
        req.setSourceType(LOGIN_SOURCE_TYPE);
        req.setPageNo(0);
        boolean bLogined = scorescourceSV.listScoreSource(req).getResult() != null ? true : false;
        
        return bLogined;
    }
    
}
