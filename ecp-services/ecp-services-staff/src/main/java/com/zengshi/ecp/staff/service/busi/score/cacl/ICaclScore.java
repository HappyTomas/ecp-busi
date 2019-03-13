package com.zengshi.ecp.staff.service.busi.score.cacl;

import java.util.HashMap;
import java.util.Map;

import com.zengshi.ecp.general.order.dto.PayQuartzInfoRequest;
import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dao.model.ScoreFuncDef;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ProductInfoReqDto;
import com.zengshi.ecp.staff.dubbo.dto.ScoreFuncDefReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreResultResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 积分计算接口<br>
 * Date:2016年5月18日上午9:56:56  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public interface ICaclScore {
    
    /**
     * 
     * caclScore:(积分计算具体逻辑，单纯根据参数，计算出积分). <br/> 
     * 
     * @author huangxl5 
     * @param pSourceType
     * @param pParaMap
     * @param pCustInfo
     * @param pProductInfo
     * @return 
     * @since JDK 1.6
     */
    public ScoreResultResDTO caclScore(String pSourceType, Map<Integer, String> pParaMap,
            CustInfoReqDTO pCustInfo, ProductInfoReqDto pProductInfo);
    
    /**
     * 
     * saveDeal:(积分计算处理). <br/> 
     * 包括处理完结果，更新用户积分账号及写日志等
     * @author huangxl5 
     * @param pSourceType
     * @param pCustInfo
     * @param pOrderInfo
     * @return 
     * @since JDK 1.6
     */
    public ScoreResultResDTO saveDeal(String pSourceType, CustInfoReqDTO pCustInfo,
            PayQuartzInfoRequest pOrderInfo);
    
    /**
     * 
     * judgeActionPara:(获取计算方法预定义的参数). <br/> 
     * 
     * @author huangxl5 
     * @param fun
     * @param paraMap
     * @return 
     * @since JDK 1.6
     */
    public Map<Integer, String> judgeActionPara(ScoreFuncDefReqDTO fun, Map<Long, HashMap<Integer, String>> paraMap,CustInfoReqDTO pCustInfo,ProductInfoReqDto productInfo);
}
