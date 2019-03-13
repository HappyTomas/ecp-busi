package com.zengshi.ecp.search.test.service;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import com.zengshi.ecp.search.dubbo.dto.SecHotKeywordReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecHotKeywordRespDTO;
import com.zengshi.ecp.search.dubbo.interfaces.ISecHotKeywordRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.test.EcpServicesTest;

public class SecHotKeywordSVImplTest extends EcpServicesTest{
    
    @Resource
    private ISecHotKeywordRSV secHotKeywordRSV;
    
    @Test
    public void test(){
        SecHotKeywordReqDTO secHotKeywordReqDTO=new SecHotKeywordReqDTO();
        secHotKeywordReqDTO.setPageNo(1);
        secHotKeywordReqDTO.setPageSize(3);
        PageResponseDTO<SecHotKeywordRespDTO> resp=secHotKeywordRSV.querySecHotKeywordPage(secHotKeywordReqDTO);
        if(CollectionUtils.isNotEmpty(resp.getResult())){
            for(SecHotKeywordRespDTO secHotKeywordRespDTO:resp.getResult()){
                System.out.println(secHotKeywordRespDTO.getHotkeywordRank()+"-"+secHotKeywordRespDTO.getKeyword());
            }
        }
    }
    

}

