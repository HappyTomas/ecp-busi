package com.zengshi.ecp.app.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.app.req.Prom003Req;
import com.zengshi.ecp.app.resp.Prom003Resp;
import com.zengshi.ecp.app.resp.PromListRespVO;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;



@Service("prom003")
@Action(bizcode="prom003",userCheck=false)
@Scope("prototype")
public class Prom003Action extends AppBaseAction<Prom003Req, Prom003Resp>{

    @Resource
    private IPromRSV iPromRSV;
    
	@Override
	protected void getResponse() throws BusinessException, SystemException, Exception {
        
        // 1、入参组织参数
        PromRuleCheckDTO promRuleCheckDTO = new PromRuleCheckDTO();
        promRuleCheckDTO.setGdsId(this.request.getGdsId());
        promRuleCheckDTO.setShopId(this.request.getShopId());
        promRuleCheckDTO.setChannelValue(this.request.getSource());//来源 1：WEB、2：APP、 3：其他
        promRuleCheckDTO.setSkuId(this.request.getSkuId());
        promRuleCheckDTO.setStaffId(this.request.getStaffId()+"");
        // 自由搭配1  固定搭配2 
        promRuleCheckDTO.setMatchType(this.request.getMatchType());
        
        PromCommonUtil.setPromRuleCheckDTO(promRuleCheckDTO);
      
        List<PromMatchDTO> matchList = iPromRSV.queryMatchList(promRuleCheckDTO);

        //3、封装返回页面参数（返回的bean 是否可以优化 层级 相同的部分只存一次 减少流量 具体重新定义 待定？？）
        if(!CollectionUtils.isEmpty(matchList)){
            List<PromListRespVO> resList=new ArrayList<PromListRespVO>();
            for(PromMatchDTO dto:matchList){
                if(dto!=null && !CollectionUtils.isEmpty(dto.getPromMatchSkuDTOList())){
                    for(PromMatchSkuDTO matchDTO:dto.getPromMatchSkuDTOList()){
                        PromListRespVO v=new PromListRespVO();
                        v.setDiscountFinalPrice(new BigDecimal(matchDTO.getPrice()));
                        v.setPromId(matchDTO.getPromId());
                        v.setPromTheme(dto.getPromInfoDTO().getPromTheme());
                        v.setSkuId(matchDTO.getSkuId());
                        resList.add(v);
                    }
                }
            }
            this.response.setResList(resList);
        }
    }

}
