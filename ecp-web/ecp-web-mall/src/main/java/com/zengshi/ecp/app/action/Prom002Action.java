package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.app.req.Prom002Req;
import com.zengshi.ecp.app.resp.Prom002Resp;
import com.zengshi.ecp.app.resp.PromInfoRespVO;
import com.zengshi.ecp.prom.dubbo.dto.GdsPromListDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromListRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 促销域 查询促销信息action<br>
 * Date:2016-3-7下午3:43:32  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangjx
 * @version  
 * @since JDK 1.7
 */

@Service("prom002")
@Action(bizcode="prom002", userCheck=false)
@Scope("prototype")
public class Prom002Action extends AppBaseAction<Prom002Req, Prom002Resp> {

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
        //List<PromListRespDTO> list = promQueryRSV.listProm(promRuleCheckDTO);
        GdsPromListDTO promList = promQueryRSV.listPromNew(promRuleCheckDTO);
        
        //3、封装返回页面参数
        if(promList!=null&&!CollectionUtils.isEmpty(promList.getPromList())){
            List<PromInfoRespVO> resList=new ArrayList<PromInfoRespVO>();
            for(PromListRespDTO dto:promList.getPromList()){
                PromInfoRespVO v = DtoToVo(dto);
                resList.add(v);
            }
            this.response.setSeckillExist(promList.getSeckill().isExist());
            this.response.setSeckillStart(promList.getSeckill().isStart());
            this.response.setSeckillProm(DtoToVo(promList.getSeckill().getSeckillProm()));
            this.response.setResList(resList);
        }
    }
    private PromInfoRespVO DtoToVo(PromListRespDTO dto){
        if (dto == null) {
            return null;
        }
        PromInfoRespVO v=new PromInfoRespVO();
        v.setId(dto.getPromInfoDTO().getId());
        v.setNameShort(dto.getPromInfoDTO().getNameShort());
        v.setPromContent(dto.getPromInfoDTO().getPromContent());
        v.setPromTheme(dto.getPromInfoDTO().getPromTheme());
        if(dto.getPromSkuPriceRespDTO()!=null){
            v.setDiscountCaclPrice(dto.getPromSkuPriceRespDTO().getDiscountCaclPrice());
            v.setDiscountFinalPrice(dto.getPromSkuPriceRespDTO().getDiscountFinalPrice());
        }
        v.setStartTime(dto.getPromInfoDTO().getStartTime());
        v.setEndTime(dto.getPromInfoDTO().getEndTime());
        return v;
    }
    private PromInfoRespVO DtoToVo(PromInfoDTO dto){
        if (dto == null) {
            return null;
        }
        PromInfoRespVO v=new PromInfoRespVO();
        v.setId(dto.getId());
        v.setNameShort(dto.getNameShort());
        v.setPromContent(dto.getPromContent());
        v.setPromTheme(dto.getPromTheme());
        v.setStartTime(dto.getStartTime());
        v.setEndTime(dto.getEndTime());
        return v;
    }
}

