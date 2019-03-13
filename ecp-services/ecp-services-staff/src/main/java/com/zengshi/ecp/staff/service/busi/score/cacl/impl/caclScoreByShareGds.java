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
import com.alibaba.dubbo.common.utils.CollectionUtils;

public class caclScoreByShareGds implements ICaclScore{

	@Resource
    private IScoreInfoRSV scoreInfoRSV;
    
    @Resource
    private IScoreCaclRSV scoreCaclRSV;
    
	@Override
	public ScoreResultResDTO caclScore(String pSourceType, Map<Integer, String> pParaMap, CustInfoReqDTO pCustInfo,
			ProductInfoReqDto pProductInfo) {
		long score = 0L;
		/*参数2为1时，表示按固定值送积分*/
		if ("1".equals(pParaMap.get(2))) {
			score = Long.parseLong(pParaMap.get(5));
			/*不为2时，表示可按实际支付金额的一定比例送积分，参数3表示以多少钱为计算单元，参数4为每单元，送多少积分*/
		} else {
			score = (long) (Math.ceil(pProductInfo.getProductPrice()/Double.valueOf(pParaMap.get(3))*Double.valueOf(pParaMap.get(4))));
		}
        ScoreResultResDTO resultDTO = new ScoreResultResDTO();
        resultDTO.setScore(score);
        
        return resultDTO;
	}

	@Override
	public ScoreResultResDTO saveDeal(String pSourceType, CustInfoReqDTO pCustInfo, PayQuartzInfoRequest pOrderInfo) {
		/*查询用户积分账户，不存在则创建*/
        ScoreInfoResDTO scoreRes = scoreInfoRSV.findScoreInfoByCustAndCreate(pCustInfo);
        ScoreInfoReqDTO scoreInfo = new ScoreInfoReqDTO();
        ObjectCopyUtil.copyObjValue(scoreRes, scoreInfo, null, false);
        
        ScoreResultResDTO result = new ScoreResultResDTO();
        result.setScore(0L);
        result.setActionType(pSourceType);
        result.setOptType(StaffConstants.Score.SCORE_OPT_TYPE_1);
        result.setRemark("分享购买送积分");
        result.setOrderId(pOrderInfo.getOrderId());
        result.setSiteId(pOrderInfo.getSiteId());
        ProductInfoReqDto product = new ProductInfoReqDto();
        product.setProductPrice(pOrderInfo.getPayment());
        product.setOrderId(pOrderInfo.getOrderId());
        if (CollectionUtils.isNotEmpty(pOrderInfo.getOrdCartItemCommList())) {
        	product.setOrderSubId(pOrderInfo.getOrdCartItemCommList().get(0).getOrderSubId());
        }
        ScoreResultResDTO score = scoreCaclRSV.saveDoCaclScore(result, pCustInfo,scoreInfo,product);
        if(score!= null &&score.getScore() > 0) {
            result.setScore(score.getScore());
            //更新积分账户信息
            scoreInfoRSV.updateScoreInfo(pCustInfo, scoreInfo, result);
        }
        return result;
	}

	@Override
	public Map<Integer, String> judgeActionPara(ScoreFuncDefReqDTO fun, Map<Long, HashMap<Integer, String>> paraMap,
			CustInfoReqDTO pCustInfo, ProductInfoReqDto productInfo) {
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

