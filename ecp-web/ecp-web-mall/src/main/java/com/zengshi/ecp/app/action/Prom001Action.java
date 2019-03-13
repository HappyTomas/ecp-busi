package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.app.req.Prom001Req;
import com.zengshi.ecp.app.resp.Prom001Resp;
import com.zengshi.ecp.app.resp.PromInfoRespVO;
import com.zengshi.ecp.prom.dubbo.dto.PromListRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;


@Service("prom001")
@Action(bizcode="prom001",userCheck=false)
@Scope("prototype")
public class Prom001Action extends AppBaseAction<Prom001Req, Prom001Resp>{
	
    @Resource
    private IPromQueryRSV promQueryRSV;
    
	@Override
	protected void getResponse() throws BusinessException, SystemException, Exception {
        
        //1、入参组织参数
	    PromRuleCheckDTO promRuleCheckDTO = new PromRuleCheckDTO();
        promRuleCheckDTO.setGdsId(this.request.getGdsId());
        promRuleCheckDTO.setShopId(this.request.getShopId());
        promRuleCheckDTO.setChannelValue(this.request.getSource());//来源 1：WEB、2：APP、 3：其他
        promRuleCheckDTO.setSkuId(this.request.getSkuId());
        promRuleCheckDTO.setStaffId(this.request.getStaffId()+"");
        PromCommonUtil.setPromRuleCheckDTO(promRuleCheckDTO);
        //2、调用促销后场服务
        List<PromListRespDTO> list = promQueryRSV.listProm(promRuleCheckDTO);
        
        //3、封装返回页面参数
        if(!CollectionUtils.isEmpty(list)){
            List<PromInfoRespVO> resList=new ArrayList<PromInfoRespVO>();
            for(PromListRespDTO dto:list){
                PromInfoRespVO v=new PromInfoRespVO();
                v.setId(dto.getPromInfoDTO().getId());
                v.setNameShort(dto.getPromInfoDTO().getNameShort());
                v.setPromContent(dto.getPromInfoDTO().getPromContent());
                v.setPromTheme(dto.getPromInfoDTO().getPromTheme());
                if(dto.getPromSkuPriceRespDTO()!=null){
                    v.setDiscountCaclPrice(dto.getPromSkuPriceRespDTO().getDiscountCaclPrice());
                    v.setDiscountFinalPrice(dto.getPromSkuPriceRespDTO().getDiscountFinalPrice());
                }
                resList.add(v);
            }
            this.response.setResList(resList);
        }
    }

}
