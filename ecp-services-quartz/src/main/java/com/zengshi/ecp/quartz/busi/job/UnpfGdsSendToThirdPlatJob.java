package com.zengshi.ecp.quartz.busi.job;

import java.util.Map;

import javax.annotation.Resource;

import org.quartz.DisallowConcurrentExecution;
import com.zengshi.ecp.quartz.QuartzContextHolder;
import com.zengshi.ecp.quartz.busi.common.AbstractCommonQuartzJob;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.GdsSendReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsUnsendReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsUnsendRespDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.auth.IUnpfShopAuthRSV;
import com.zengshi.ecp.unpf.dubbo.interfaces.gdssend.IUnpfGdsUnsendRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.ecp.unpf.dubbo.interfaces.gdssend.IUnpfGdsSendRSV;
import com.alibaba.dubbo.common.utils.CollectionUtils;
@DisallowConcurrentExecution
public class UnpfGdsSendToThirdPlatJob  extends AbstractCommonQuartzJob{
    
    private static final String MODULE = UnpfGdsSendToThirdPlatJob.class.getName();

    private IUnpfGdsSendRSV unpfGdsSendRSV=QuartzContextHolder.getBean(IUnpfGdsSendRSV.class);
    
    private IUnpfShopAuthRSV unpfShopAuthRSV=QuartzContextHolder.getBean(IUnpfShopAuthRSV.class);
    
    private IUnpfGdsUnsendRSV unpfGdsUnsendRSV=QuartzContextHolder.getBean(IUnpfGdsUnsendRSV.class);
    
    @Override
    protected void doJob(Map<String, String> extendParams) throws Exception {
        
        //1. 遍历店铺授权列表
        UnpfShopAuthReqDTO unpfShopAuthReqDTO = new UnpfShopAuthReqDTO();
        unpfShopAuthReqDTO.setStatus("1");
        unpfShopAuthReqDTO.setPageSize(Integer.MAX_VALUE);
        if (extendParams.get("shopId") != null) {
            unpfShopAuthReqDTO.setShopId(Long.valueOf(extendParams.get("shopId")));
        }
        if (extendParams.get("platType") != null) {
            unpfShopAuthReqDTO.setPlatType(extendParams.get("platType"));
        }
        PageResponseDTO<UnpfShopAuthRespDTO> pageinfo = unpfShopAuthRSV.queryPlatType4ShopForPage(unpfShopAuthReqDTO);
       
        if (pageinfo!=null && CollectionUtils.isNotEmpty(pageinfo.getResult())) {
            //2. 根据店铺id取待发送的商品列表
            for(UnpfShopAuthRespDTO row : pageinfo.getResult()){
                
                UnpfGdsUnsendReqDTO unsendrReqDTO = new UnpfGdsUnsendReqDTO();
                unsendrReqDTO.setPageSize(Integer.MAX_VALUE);
                unsendrReqDTO.setStatus("1");
                unsendrReqDTO.setShopId(row.getShopId());
                
                PageResponseDTO<UnpfGdsUnsendRespDTO> unsendPage = unpfGdsUnsendRSV.queryGdsUnsendForPage(unsendrReqDTO);
                
                if (unsendPage != null && CollectionUtils.isNotEmpty(unsendPage.getResult())) {
                    for(UnpfGdsUnsendRespDTO unsendGds : unsendPage.getResult()){
                        try {
                            //3. 将商品按照每个店铺的授权信息推送出去
                            GdsSendReqDTO gdsSendReqDTO = new GdsSendReqDTO();
                            gdsSendReqDTO.setGdsId(unsendGds.getGdsId());
                            gdsSendReqDTO.setShopId(row.getShopId());
                            gdsSendReqDTO.setShopAuthId(row.getId());
                            gdsSendReqDTO.setPlatType(row.getPlatType());
                            
                            unpfGdsSendRSV.send(gdsSendReqDTO);

                        } catch (Exception e) {
                            // TODO: handle exception
                            LogUtil.error(MODULE, e.getMessage());
                        }
                    }
                }
            }
            

        }
        
    }

    @Override
    protected String getModule() {
        return MODULE;
    }

}

