/** 
 * Project Name:ecp-services-staff-server 
 * File Name:IScoreCacheSV.java 
 * Package Name:com.zengshi.ecp.staff.service.cache.interfaces 
 * Date:2015年9月16日上午10:44:19 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.staff.service.cache.interfaces;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zengshi.ecp.staff.dao.model.ScoreAction;
import com.zengshi.ecp.staff.dao.model.ScoreFuncDef;
import com.zengshi.ecp.staff.dao.model.ScoreType;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: <br>
 * Date:2015年9月16日上午10:44:19  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6 
 * 
 * 积分来源计算缓存管理
 */
public interface IScoreCacheSV {

    public void init();
    public void destory();
    /**
     * 
     * getScoreActionMap:(获取来源行为类型表配置). <br/> 
     * 
     * @author PJieWin 
     * @return 
     * @since JDK 1.6
     */
    public Map<String, ScoreAction> getScoreActionMap();
    /**
     * 
     * getScoreTypeMap:(获取积分类型表配置). <br/> 
     * 
     * @author PJieWin 
     * @return 
     * @since JDK 1.6
     */
    public Map<String, List<ScoreType>> getScoreTypeMap();
    /**
     * 
     * getScoreFunMap:(获取积分来源计算函数配置). <br/> 
     * 
     * @author PJieWin 
     * @return 
     * @since JDK 1.6
     */
    public Map<String, ScoreFuncDef> getScoreFunMap();
    /**
     * 
     * getScoreParaMap:(获取积分来源计算函数参数配置). <br/> 
     * 
     * @author PJieWin 
     * @return 
     * @since JDK 1.6
     */
    public Map<Long, HashMap<Integer, String>> getScoreParaMap(); 
    /**
     * 
     * getFunList:(获取来源行为计算beanId列表). <br/> 
     * 
     * @author PJieWin 
     * @param pActionType
     * @return 
     * @since JDK 1.6
     */
    public List<ScoreFuncDef> getFunList(String pActionType);
    
    /**
     * 
     * sizeScoreCache:(计算积分规则缓存数量). <br/> 
     * 
     * @author huangxl5
     * @return 
     * @since JDK 1.6
     */
    public long sizeScoreCache();
}

