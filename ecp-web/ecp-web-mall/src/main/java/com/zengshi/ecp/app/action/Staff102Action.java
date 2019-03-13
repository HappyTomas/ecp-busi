/** 
 * Project Name:ecp-web-mall 
 * File Name:DemoAction.java 
 * Package Name:com.zengshi.ecp.app.action 
 * Date:2016-2-22下午6:51:19 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.app.req.Staff102Req;
import com.zengshi.ecp.app.resp.ScoreTrade;
import com.zengshi.ecp.app.resp.Staff102Resp;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreDetailSelReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreDetailSelResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IScoreInfoRSV;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 用户域：查询我的积分收支明细的action<br>
 * Date:2016-3-4下午3:43:32  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */

@Service("staff102")
@Action(bizcode="staff102", userCheck=true)
@Scope("prototype")
public class Staff102Action extends AppBaseAction<Staff102Req, Staff102Resp> {

    @Resource
    private IScoreInfoRSV scoreInfoRSV;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        /*1、获取入参*/
        int pageNo = this.request.getPageNo();//页数
        int pageSize = this.request.getPageSize();//页面展现的数量
        ScoreDetailSelReqDTO scoreReq = new ScoreDetailSelReqDTO();
        scoreReq.setStaffId(scoreReq.getStaff().getId());
        scoreReq.setPageNo(pageNo);
        scoreReq.setPageSize(pageSize);
        
        /*2.1、调用业务查询接口，查询用户积分收支明细*/
        PageResponseDTO<ScoreDetailSelResDTO> page = scoreInfoRSV.listScoreDetailForApp(scoreReq);
        List<ScoreTrade> list = new ArrayList<ScoreTrade>();
        if (page != null && !CollectionUtils.isEmpty(page.getResult())) {
            for (ScoreDetailSelResDTO score : page.getResult()) {
                ScoreTrade scoreTrade = new ScoreTrade();
                scoreTrade.setScoreType(score.getScoreTypeName());//积分类型名称
                scoreTrade.setOrderId(score.getOrderId());//订单编码
                scoreTrade.setCreateTime(score.getCreateTime());//创建时间
                scoreTrade.setScore(score.getConsumeScore());//积分
                list.add(scoreTrade);
            }
        }
        
        /*2.2、调用业务查询接口，查询用户积分账户*/
        ScoreInfoResDTO scoreInfo = scoreInfoRSV.findScoreInfoByStaffId(scoreReq.getStaff().getId());
        if (scoreInfo.getScoreBalance() == null) {
        	scoreInfo.setScoreBalance(0L);
        }
        
        /*3、返回结果*/
        this.response.setTotalScore(scoreInfo.getScoreBalance());//用户的可用积分
        this.response.setResList(list);
    }

}

