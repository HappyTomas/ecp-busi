/** 
 * Project Name:ecp-services-staff-server 
 * File Name:ScoreCacheSVImpl.java 
 * Package Name:com.zengshi.ecp.staff.service.cache.impl 
 * Date:2015年9月16日上午10:50:05 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.staff.service.cache.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.staff.dao.mapper.common.ScoreActionMapper;
import com.zengshi.ecp.staff.dao.mapper.common.ScoreFuncDefMapper;
import com.zengshi.ecp.staff.dao.mapper.common.ScoreFuncParaMapper;
import com.zengshi.ecp.staff.dao.mapper.common.ScoreTypeMapper;
import com.zengshi.ecp.staff.dao.model.ScoreAction;
import com.zengshi.ecp.staff.dao.model.ScoreActionCriteria;
import com.zengshi.ecp.staff.dao.model.ScoreFuncDef;
import com.zengshi.ecp.staff.dao.model.ScoreFuncDefCriteria;
import com.zengshi.ecp.staff.dao.model.ScoreFuncPara;
import com.zengshi.ecp.staff.dao.model.ScoreFuncParaCriteria;
import com.zengshi.ecp.staff.dao.model.ScoreType;
import com.zengshi.ecp.staff.dao.model.ScoreTypeCriteria;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.cache.interfaces.IScoreCacheSV;
import com.zengshi.paas.utils.CacheUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: <br>
 * Date:2015年9月16日上午10:50:05  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6 
 */
@Service
public class ScoreCacheSVImpl implements IScoreCacheSV {
    
    @Resource
    private ScoreActionMapper scoreActionMapper;
    @Resource
    private ScoreTypeMapper scoreTypeMapper;
    @Resource
    private ScoreFuncDefMapper scoreFuncDefMapper;
    @Resource
    private ScoreFuncParaMapper scoreFuncParaMapper;
   
    
    private static final String MODULE = ScoreCacheSVImpl.class.getName();

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.staff.service.cache.interfaces.IScoreCacheSV#init() 
     */
    @Override
    public void init() {
        
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.staff.service.cache.interfaces.IScoreCacheSV#destory() 
     */
    @Override
    public void destory() {
    }

    /** 
     * TODO 获取来源行为类型表配置. 
     * @see com.zengshi.ecp.staff.service.cache.interfaces.IScoreCacheSV#getScoreActionMap() 
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, ScoreAction> getScoreActionMap() {
        //取出缓存记录
        Map<String, ScoreAction> scoreActionMap = (Map<String, ScoreAction>)CacheUtil.getItem(StaffConstants.Score.SCORE_ACTION_CACHE_KEY);

        if(scoreActionMap==null || scoreActionMap.isEmpty())
        {
            scoreActionMap = new HashMap<String, ScoreAction>();
            //取所有T_SCORE_ACTION表记录
            ScoreActionCriteria criteria1 = new ScoreActionCriteria();
            List<ScoreAction> listAction =  scoreActionMapper.selectByExample(criteria1);
            for(ScoreAction sa : listAction)
            {
                scoreActionMap.put(sa.getActionType(), sa);
            }
            //放入缓存
            CacheUtil.addItem(StaffConstants.Score.SCORE_ACTION_CACHE_KEY, scoreActionMap);

        }
        return scoreActionMap;
    }

    /** 
     * TODO 获取积分类型表配置. 
     * @see com.zengshi.ecp.staff.service.cache.interfaces.IScoreCacheSV#getScoreTypeMap() 
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, List<ScoreType>> getScoreTypeMap() {
        
        //1.取出缓存记录
        Map<String, List<ScoreType>> scoreTypeMap = (Map<String, List<ScoreType>> )CacheUtil.getItem(StaffConstants.Score.SCORE_TYPE_CACHE_KEY);

        if(scoreTypeMap == null || scoreTypeMap.isEmpty())
        {
            scoreTypeMap = new HashMap<String, List<ScoreType>>();
            //取所有T_SCORE_TYPE表记录
            ScoreTypeCriteria criteria2 = new ScoreTypeCriteria();
            List<ScoreType> listType = scoreTypeMapper.selectByExample(criteria2);
            for(ScoreType st : listType)
            {
                if(scoreTypeMap.get(st.getActionType()) != null)
                {
                    scoreTypeMap.get(st.getActionType()).add(st);
                }
                else {
                    List<ScoreType> tmpList = new ArrayList<ScoreType>();
                    tmpList.add(st);
                    scoreTypeMap.put(st.getActionType(), tmpList);
                }
            }
          //2.放入缓存
            CacheUtil.addItem(StaffConstants.Score.SCORE_TYPE_CACHE_KEY, scoreTypeMap);

        }
        return scoreTypeMap;
    }

    /** 
     * TODO 获取积分来源计算函数配置. 
     * @see com.zengshi.ecp.staff.service.cache.interfaces.IScoreCacheSV#getScoreFunMap() 
     */
    @Override
    public Map<String, ScoreFuncDef> getScoreFunMap() {
        
        //1.取出缓存记录
        @SuppressWarnings("unchecked")
        Map<String, ScoreFuncDef> scoreFunMap = (Map<String, ScoreFuncDef>)CacheUtil.getItem(StaffConstants.Score.SCORE_FUN_CACHE_KEY);

        if(scoreFunMap == null || scoreFunMap.isEmpty())
        {
            scoreFunMap = new HashMap<String, ScoreFuncDef>();
            //取所有积分计算函数定义表：T_SCORE_FUNC_DEF表记录,有效记录
            ScoreFuncDefCriteria criteria3 = new ScoreFuncDefCriteria();
            criteria3.createCriteria().andStatusEqualTo(StaffConstants.Score.SCORE_FUNC_STATUS_ACTIVE);
            List<ScoreFuncDef> listFunDef = scoreFuncDefMapper.selectByExample(criteria3);
            for(ScoreFuncDef sf : listFunDef)
            {
                scoreFunMap.put(sf.getScoreType(), sf);
            }
            //2.放入缓存
            CacheUtil.addItem(StaffConstants.Score.SCORE_FUN_CACHE_KEY, scoreFunMap);

        }
        return scoreFunMap;
    }

    /** 
     * TODO 获取积分来源计算函数参数配置. 
     * @see com.zengshi.ecp.staff.service.cache.interfaces.IScoreCacheSV#getScoreParaMap() 
     */
    @Override
    public Map<Long, HashMap<Integer, String>> getScoreParaMap() {
        
        //1.取出缓存记录
        @SuppressWarnings("unchecked")
        Map<Long, HashMap<Integer, String>> scoreParaMap = (Map<Long, HashMap<Integer, String>>)CacheUtil.getItem(StaffConstants.Score.SCORE_PARA_CACHE_KEY);

        if(scoreParaMap == null || scoreParaMap.isEmpty())
        {
            scoreParaMap = new HashMap<Long, HashMap<Integer, String>>();
            //取所有积分计算函数参数定义表：T_SCORE_FUNC_PARA表记录
            ScoreFuncParaCriteria criteria4 = new ScoreFuncParaCriteria();
            List<ScoreFuncPara> listFuncPara = scoreFuncParaMapper.selectByExample(criteria4);
            for(ScoreFuncPara sfp : listFuncPara)
            {
                long id = sfp.getParaId();
                int seq = sfp.getParaSeq();
                String value = sfp.getParaValue();
                //System.out.println("!!!===para.getParaValue() = "+value);
                if(scoreParaMap.get(id) != null)
                {
                    scoreParaMap.get(id).put(seq, value);
                }
                else
                {
                    HashMap<Integer, String> map = new HashMap<Integer, String>();
                    map.put(seq, value);
                    scoreParaMap.put(id, map);
                }
            }
            //2.放入缓存
            CacheUtil.addItem(StaffConstants.Score.SCORE_PARA_CACHE_KEY, scoreParaMap);

        }
        return scoreParaMap;
    }

    /** 
     *  获取来源行为计算函数列表. 
     * @see com.zengshi.ecp.staff.service.cache.interfaces.IScoreCacheSV#getFunMap() 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ScoreFuncDef> getFunList(String pActionType) {
        
        //1.取出缓存记录
        /**
         * 积分来源类型所对应需要计算的函数列表
         */
        
        Map<String, List<ScoreFuncDef>> mFunMap = (Map<String, List<ScoreFuncDef>>) CacheUtil.getItem(StaffConstants.Score.SCORE_CACLFUN_CACHE_KEY);
        if(mFunMap == null || mFunMap.isEmpty())
        {
            mFunMap = new HashMap<String, List<ScoreFuncDef>>();
        }
        List<ScoreFuncDef> caclFunList = null;
        caclFunList =  mFunMap.get(pActionType);
        if(CollectionUtils.isEmpty(caclFunList) || caclFunList.get(0) == null)
        {
            caclFunList = new ArrayList<ScoreFuncDef>();
            //1.取积分来源行为
            ScoreAction sScoreAction = getScoreActionMap().get(pActionType);
            //2.根据积分来源行为类型，取出积分类型列表
            List<ScoreType> sScoreTypes = getScoreTypeMap().get(pActionType);
            if(sScoreTypes != null)
            {
                caclFunList = new ArrayList<ScoreFuncDef>(sScoreTypes.size());         
                   //添加所有计算函数后，对计算函数进行优先级排序
                  for(ScoreType sType : sScoreTypes)
                  {
                      //将需要计算的函数添加到函数列表中
                     caclFunList.add(getScoreFunMap().get(sType.getScoreType()));
                   }
                  //进行排序，优先级高，排在前面先计算
                  CollectionUtils.sort(caclFunList);
            }
            mFunMap.put(pActionType, caclFunList);
            CacheUtil.addItem(StaffConstants.Score.SCORE_CACLFUN_CACHE_KEY, mFunMap);
        }
        return caclFunList;
    }
    @Override
    public long sizeScoreCache() {
        long count = 0L;
        /*取出缓存记录*/
        Map<String, ScoreAction> scoreActionMap = (Map<String, ScoreAction>)CacheUtil.getItem(StaffConstants.Score.SCORE_ACTION_CACHE_KEY);
        Map<String, List<ScoreType>> scoreTypeMap = (Map<String, List<ScoreType>> )CacheUtil.getItem(StaffConstants.Score.SCORE_TYPE_CACHE_KEY);
        Map<String, ScoreFuncDef> scoreFunMap = (Map<String, ScoreFuncDef>)CacheUtil.getItem(StaffConstants.Score.SCORE_FUN_CACHE_KEY);
        Map<Long, HashMap<Integer, String>> scoreParaMap = (Map<Long, HashMap<Integer, String>>)CacheUtil.getItem(StaffConstants.Score.SCORE_PARA_CACHE_KEY);
        Map<String, List<ScoreFuncDef>> mFunMap = (Map<String, List<ScoreFuncDef>>) CacheUtil.getItem(StaffConstants.Score.SCORE_CACLFUN_CACHE_KEY);; 
        
        /*计算缓存里面的数据*/
        if (scoreActionMap != null) {
            for(String key : scoreActionMap.keySet()) {
                count++;
            }
        }
        if (scoreTypeMap != null) {
            for(String key : scoreTypeMap.keySet()) {
                count++;
            }
        }
        if (scoreFunMap != null) {
            for(String key : scoreFunMap.keySet()) {
                count++;
            }
        }
        if (scoreParaMap != null) {
            for(Long key : scoreParaMap.keySet()) {
                count++;
            }
        }
        if (mFunMap != null) {
            for(String key : mFunMap.keySet()) {
                count++;
            }
        }
        
        return count;
    }

}

