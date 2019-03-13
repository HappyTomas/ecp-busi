package com.zengshi.ecp.staff.service.busi.score.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.general.order.dto.PayQuartzInfoRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dao.model.ScoreAction;
import com.zengshi.ecp.staff.dao.model.ScoreFuncDef;
import com.zengshi.ecp.staff.dao.model.ScoreInfo;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ProductInfoReqDto;
import com.zengshi.ecp.staff.dubbo.dto.ScoreFuncDefReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreResultResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.custinfo.interfaces.ICustInfoSV;
import com.zengshi.ecp.staff.service.busi.score.cacl.ICaclScore;
import com.zengshi.ecp.staff.service.busi.score.interfaces.IScoreCaclSV;
import com.zengshi.ecp.staff.service.busi.score.interfaces.IScoreInfoSV;
import com.zengshi.ecp.staff.service.cache.interfaces.IScoreCacheSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

public class ScoreCaclSVImpl implements IScoreCaclSV {
    @Resource
    private IScoreInfoSV scoreInfoSV;
    @Resource
    private ICustInfoSV custInfoSV;
    @Resource
    private IScoreCacheSV scoreCacheSV;
    
    private static final String MODULE = ScoreCaclSVImpl.class.getName();
    

    public ScoreCaclSVImpl(){
        
    }
    public void init(){
        
    }
    
    @Override
    public ScoreResultResDTO updateScore(String pActionType, CustInfoReqDTO pCustInfo, PayQuartzInfoRequest pOrderInfo) throws BusinessException
    {
        //判断下积分账户是否正常状态
        CustInfo custInfo = custInfoSV.findCustInfoById(pCustInfo.getId());
        ObjectCopyUtil.copyObjValue(custInfo, pCustInfo, null, false);

        ScoreResultResDTO score = null;
        //一种积分行为，只会对应一个处理类，所以直接取第一行数据
        List<ScoreFuncDef> beanList = scoreCacheSV.getFunList(pActionType);
        if (CollectionUtils.isEmpty(beanList)) {
            throw new BusinessException("通过积分行为：" + pActionType + "，无法找到对应的处理类。");
        }
        //加载对应的积分处理类
        ICaclScore caclScore = (ICaclScore)EcpFrameContextHolder.getBean(beanList.get(0).getClassBeanId());
        //调用积分计算的预处理方法
        score = caclScore.saveDeal(pActionType, pCustInfo, pOrderInfo);

        return score;
    }

    @Override
    public ScoreResultResDTO saveDoCaclScore(ScoreResultResDTO  scoreResultInfo, CustInfoReqDTO pCustInfo,ScoreInfoReqDTO scoreReq, ProductInfoReqDto pProductInfo) throws BusinessException{
        CustInfo cust = new CustInfo();
        ObjectCopyUtil.copyObjValue(pCustInfo, cust, null, false);
        ScoreInfo scoreInfo = new ScoreInfo();
        ObjectCopyUtil.copyObjValue(scoreReq, scoreInfo, null, false);
        String pActionType = scoreResultInfo.getActionType();
        List<ScoreFuncDef> beanList = scoreCacheSV.getFunList(pActionType);  
        if(CollectionUtils.isEmpty(beanList)) {
            LogUtil.info(MODULE, "======通过积分行为：["+pActionType+"] ，无法找到对应的处理类。========");
            return null;
        }
        ScoreAction sScoreAction = scoreCacheSV.getScoreActionMap().get(pActionType);
        if(sScoreAction == null){
            LogUtil.info(MODULE, "======该来源类型["+pActionType+"] 没有配置t_score_action表信息========");
            return null;
        }
        //从缓存中取出所有参数配置（如果缓存中没有，则取数据库）
        Map<Long, HashMap<Integer, String>> scoreParaMap = scoreCacheSV.getScoreParaMap();
        
        //构造返回结果
        List<ScoreResultResDTO> resultList = new ArrayList<ScoreResultResDTO>(beanList.size());
        
        //取出计算函数列表，则循环计算函数，得出结果值
        
        for(ScoreFuncDef sFun : beanList){
            Map<Integer, String> para = null;
            boolean bCacl= false;
            ScoreFuncDefReqDTO fun = new ScoreFuncDefReqDTO();
            ObjectCopyUtil.copyObjValue(sFun, fun, null, false);
            //加载对应的积分处理类
            ICaclScore caclScore = (ICaclScore)EcpFrameContextHolder.getBean(beanList.get(0).getClassBeanId());
            //不同的积分来源类型有不同的判断规则,符合判断规则，则返回参数，进行计算，否则不进行计算
            para = caclScore.judgeActionPara(fun, scoreParaMap, pCustInfo, pProductInfo);
            
            if(para != null && para.size() > 0) {
                bCacl = true; 
            }
            //有匹配的参数规则，则计算
            if(bCacl){
                ScoreResultResDTO resultDTO = null;
                try {
                    //4.实例化函数，执行计算
                    LogUtil.info(MODULE, "======实例化函数["+sFun.getFuncName()+"]进行积分计算========");

                    resultDTO = caclScore.caclScore(pActionType, para, pCustInfo, pProductInfo);  
                    resultDTO.setScoreType(sFun.getScoreType());
                    resultDTO.setOptType(scoreResultInfo.getOptType());
                    resultDTO.setActionType(scoreResultInfo.getActionType());
                    resultDTO.setSiteId(scoreResultInfo.getSiteId());
                    if(pProductInfo != null){
                        resultDTO.setOrderId(pProductInfo.getOrderId());
                        resultDTO.setOrderSubId(pProductInfo.getOrderSubId());
                    }
                    resultDTO.setRemark(scoreResultInfo.getRemark());
                } catch (Exception e) {
                    //系统异常
                    LogUtil.info(MODULE, "======实例化积分计算函数异常========");
                    throw new BusinessException(StaffConstants.Score.SCORE_FUNC_STATUS_INVALID);
                }
                //若计算积分大于，才记录明细
                if(resultDTO.getScore() > 0){
                    //更新积分来源明细表
                    scoreInfoSV.saveScoreSource(cust, scoreInfo, resultDTO);
                    
                    //更新积分操作日志表
                    scoreInfoSV.saveScoreOptLog(cust, scoreInfo, resultDTO);
                }
                
                //5.1如果是取优先级最高函数计算方式，则直接返回结果
                //可以加入一个条件，判断此次计算积分结果值是否大于0，不大于0则使用下个函数进行计算
                if(sScoreAction.getCalcType() == 2){
                    return resultDTO;
                }
                //5.2如果是累加计算方式，则保持计算结果
                resultList.add(resultDTO);
            }
        }//end by caclFunList
        //所得积分累计
        if(!CollectionUtils.isEmpty(resultList)){
            ScoreResultResDTO resultScore = new ScoreResultResDTO();
            resultScore.setScore(0L);
            resultScore.setActionType(pActionType);
            for(ScoreResultResDTO srDto : resultList){
                resultScore.setScore(resultScore.getScore() + srDto.getScore());
            }
            return resultScore;
        }
        return null;
    }
}











