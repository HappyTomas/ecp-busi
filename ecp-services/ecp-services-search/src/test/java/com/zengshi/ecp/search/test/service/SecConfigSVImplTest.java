package com.zengshi.ecp.search.test.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.search.dao.model.SecConfig;
import com.zengshi.ecp.search.dubbo.dto.SecConfigReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecConfigRespDTO;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.service.common.interfaces.ISecConfigSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.test.EcpServicesTest;

public class SecConfigSVImplTest extends EcpServicesTest {

    @Resource
    private ISecConfigSV secConfigSV;

    @Test
    public void testSaveSecConfig() {

        SecConfigReqDTO secConfigReqDTO = null;

        for (int i = 1; i <= 3; i++) {
            secConfigReqDTO = new SecConfigReqDTO();
            secConfigReqDTO.setConfigName("test索引配置" + i);
            secConfigReqDTO.setConfigDesc("test索引配置说明" + i);
            secConfigReqDTO.setConfigCollectionName("testcollection" + i);
            secConfigReqDTO.setConfigQueryOp("AND");
            secConfigReqDTO.setConfigQueryIfHl("1");
            secConfigReqDTO.setConfigQueryHlPre("<font color=red>");
            secConfigReqDTO.setConfigQueryHlPost("</font>");
            secConfigReqDTO.setConfigQueryIfSpellcheck(SearchConstants.STATUS_1);
            secConfigReqDTO.setConfigQuerySpellcheckCount((short) 10);

            this.secConfigSV.saveSecConfig(secConfigReqDTO);

        }

    }

    @Test
    public void testDeleteSecConfig() {

        SecConfigReqDTO secConfigReqDTO=new SecConfigReqDTO();
        secConfigReqDTO.setId(2012l);
        this.secConfigSV.deleteSecConfigAll(secConfigReqDTO);

    }

    @Test
    public void testUpdateSecConfig() {

        SecConfigReqDTO secConfigReqDTO = new SecConfigReqDTO();
        secConfigReqDTO.setId((long) 2013);
        secConfigReqDTO.setConfigName("商品索引配置");
        secConfigReqDTO.setConfigDesc("商品索引配置说明");
        secConfigReqDTO.setConfigCollectionName("gdscollection");
        secConfigReqDTO.setConfigQueryOp("AND");
        secConfigReqDTO.setConfigQueryHlPre("<pre>");
        secConfigReqDTO.setConfigQueryHlPost("</pre>");
        secConfigReqDTO.setConfigQueryIfSpellcheck(SearchConstants.STATUS_1);
        secConfigReqDTO.setConfigQuerySpellcheckCount((short) 10);
        this.secConfigSV.updateSecConfig(secConfigReqDTO);
    }

    @Test
    public void testQuerySecConfigById() {

        SecConfigReqDTO secConfigReqDTO=new SecConfigReqDTO();
        secConfigReqDTO.setId(33l);
        secConfigReqDTO.setStatus(SearchConstants.STATUS_VALID);
        System.out.println(this.secConfigSV.querySecConfigById(secConfigReqDTO));

    }

    @Test
    public void testListSecConfig() {

        SecConfigReqDTO secConfigReqDTO=new SecConfigReqDTO();
        secConfigReqDTO.setStatus(SearchConstants.STATUS_VALID);
        for (SecConfig secConfig : this.secConfigSV.listSecConfig(secConfigReqDTO)) {
            System.out.println(secConfig);
        }

    }

    @Test
    public void testQuerySecConfigPage() {

        // 分页查询全部测试
        SecConfigReqDTO secConfigReqDTO = new SecConfigReqDTO();
        secConfigReqDTO.setPageSize(3);
        int pageNo = 0;
        
        secConfigReqDTO.setPageNo(pageNo);
        PageResponseDTO<SecConfigRespDTO> prd=secConfigSV.querySecConfigPage(secConfigReqDTO);
        System.out.println("count:"+prd.getCount());
        for(SecConfigRespDTO conf : prd.getResult()){
            System.out.println(conf.toString());
        }

//        List<SecConfig> secConfigList = null;
//        while (true) {
//            pageNo++;
//            secConfigReqDTO.setPageNo(pageNo);
//            System.out.println("startRowIndex:" + secConfigReqDTO.getStartRowIndex());
//            System.out.println("endRowIndex:" + secConfigReqDTO.getEndRowIndex());
//            secConfigList = this.secConfigSV.querySecConfigPage(secConfigReqDTO);
//            if (secConfigList != null && secConfigList.size() > 0) {
//                for (SecConfig secConfig : secConfigList) {
//                    System.out.println(secConfig);
//                }
//            } else {
//
//                // 分页读取结束
//                break;
//            }
//
//        }

    }

}
