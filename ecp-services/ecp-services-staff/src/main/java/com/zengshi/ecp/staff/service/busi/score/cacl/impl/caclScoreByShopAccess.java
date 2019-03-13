/** 
 * Project Name:ecp-services-staff-server 
 * File Name:caclScoreByShopAccess.java 
 * Package Name:com.zengshi.ecp.staff.service.busi.score.cacl.impl 
 * Date:2015年9月25日下午2:25:01 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
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
import com.zengshi.ecp.staff.dubbo.interfaces.IScoreCaclRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IScoreInfoRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.score.cacl.ICaclScore;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: <br>
 * Date:2015年9月25日下午2:25:01  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6 
 * 
 * 评价赠送积分计算
 */
public class caclScoreByShopAccess implements ICaclScore{

    @Resource
    private IScoreInfoRSV scoreInfoRSV;
    
    @Resource
    private IScoreCaclRSV scoreCaclRSV;
    
    @Override
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
        result.setRemark("订单【"+pOrderInfo.getOrderId()+"】评价获得积分");
        
        ScoreResultResDTO score = scoreCaclRSV.saveDoCaclScore(result, pCustInfo,scoreInfo, null);
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
        //遍历函数参数Map容器
        Iterator<Entry<Long, HashMap<Integer, String>>> entryKeyIterator =  paraMap.entrySet().iterator();
        Map<Integer, String> para = null;
        while(entryKeyIterator.hasNext()) {
            Entry<Long, HashMap<Integer, String>> e = entryKeyIterator.next();
            para = e.getValue();
            if(para.get(0).equals(fun.getActionType()) && para.get(1).equals(fun.getFuncName())) {
                return para;
            }
        }
        return null;
    }

}

