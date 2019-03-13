package com.zengshi.ecp.app.action;


import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Gds026Req;
import com.zengshi.ecp.app.resp.Gds026Resp;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsSku2PropPropIdxReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;

import scala.actors.threadpool.Arrays;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 根据条形码查询产品.<br>
 * Date:2016年10月9日下午6:01:41  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
@Service("gds026")
@Action(bizcode = "gds026", userCheck = false)
@Scope("prototype")
public class Gds026Action extends AppBaseAction<Gds026Req, Gds026Resp>{

    @Resource
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception{

        Gds026Req gds025Req = this.request;
        Gds026Resp gds025Resp = this.response;
        GdsSkuInfoRespDTO skuInfo = queryGdsSkuInfoByBarcode(gds025Req);
        if(null != skuInfo){
            gds025Resp.setGdsId(skuInfo.getGdsId());
            gds025Resp.setSkuId(skuInfo.getId());
        }

    }
    
    /**
     * 
     * queryGdsSkuInfoByBarcode:根据条形码查询指定产品. <br/> 
     * 
     * @author liyong7
     * @param gds026Req
     * @return 
     * @since JDK 1.6
     */
    private GdsSkuInfoRespDTO queryGdsSkuInfoByBarcode(Gds026Req gds026Req){
        if(StringUtils.isEmpty(gds026Req.getBarcode())){
            throw new  BusinessException("条形码为空!");
        }
        GdsSkuInfoRespDTO skuInfo = null;
        GdsSku2PropPropIdxReqDTO query = new GdsSku2PropPropIdxReqDTO();
        query.setPropIds(Arrays.asList(new Long[]{1002L}));
        query.setPropId(1050L);
        query.setPropValue(gds026Req.getBarcode());
        PageResponseDTO<GdsSkuInfoRespDTO> page = gdsSkuInfoQueryRSV.queryGdsSkuInfoPaging(query);
        if(0 < page.getCount()){
            for(GdsSkuInfoRespDTO respDTO : page.getResult()){
                if(StringUtils.contains(respDTO.getPlatCatgs(),"<1115>")){
                    skuInfo = respDTO;
                    break;
                }
            }
            if(null == skuInfo){
                skuInfo = page.getResult().get(0);
            }
        }
        return skuInfo;
    }



}
