package com.zengshi.ecp.busi.search.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.search.dubbo.dto.IndexReusltRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecConfigReqDTO;
import com.zengshi.ecp.search.dubbo.interfaces.ISecConfigPlanRSV;
import com.zengshi.ecp.search.dubbo.util.SearchCacheUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2015年9月9日下午2:18:17 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "/search")
public class SearchController extends EcpBaseController {
    
    @Resource
    private ISecConfigPlanRSV secConfigPlanRSV;
    
    @RequestMapping(value="/init")
    public String init(){
        return "/search/search-init";
    }
    
    @RequestMapping(value="/clearCache")
    @ResponseBody
    public Map<String,String> indexManage(@RequestParam("siteId") String siteId){
        Map<String,String> map=new HashMap<String,String>();
        SearchCacheUtils.clean();
        map.put("message","索引配置缓存清空成功");
        return map;
    }
    
    @RequestMapping(value="/{operType}")
    @ResponseBody
    public IndexReusltRespDTO indexManage(@RequestParam("siteId") String siteId,@RequestParam("configId") String configId
            ,@PathVariable("operType") String operType) throws Exception{
        
        final IndexReusltRespDTO[] idexReusltRespDTO=new IndexReusltRespDTO[1];
        idexReusltRespDTO[0]=null;
        
        //人卫商城
        if(StringUtils.equals(siteId, "1")){
            if(StringUtils.equals(operType, "reFullImportIndex")){
                SecConfigReqDTO secConfigReqDTO=new SecConfigReqDTO();
                secConfigReqDTO.setId(Long.parseLong(configId));
                idexReusltRespDTO[0]=secConfigPlanRSV.reFullImportIndex(secConfigReqDTO, true);
            }else{
                idexReusltRespDTO[0].setMessage("错误操作类型："+operType);
            }
        }else if(StringUtils.equals(siteId, "2")){//积分商城
            if(StringUtils.equals(operType, "reFullImportIndex")){
                SecConfigReqDTO secConfigReqDTO=new SecConfigReqDTO();
                secConfigReqDTO.setId(Long.parseLong(configId));
                idexReusltRespDTO[0]=secConfigPlanRSV.reFullImportIndex(secConfigReqDTO, true);
            }else{
                idexReusltRespDTO[0].setMessage("错误操作类型："+operType);
            }
        }else{
            idexReusltRespDTO[0].setMessage("错误站点ID："+siteId);
        }
        
        return idexReusltRespDTO[0];
    }

}
