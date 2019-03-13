package com.zengshi.ecp.search.test.field;

import java.util.Date;

import org.junit.Test;

import com.zengshi.ecp.search.dubbo.dto.SecFieldReqDTO;
import com.zengshi.ecp.search.dubbo.search.util.SearchUtils;
import com.zengshi.ecp.search.index.util.SolrUtils;

public class FieldTest {

    @Test
    public void test() {

        SecFieldReqDTO secFieldReqDTO = new SecFieldReqDTO();
        secFieldReqDTO.setFieldBeanFieldName("guidePrice");
        secFieldReqDTO.setFieldTypeName("long");
        secFieldReqDTO.setFieldIfMutlivalue("1");
        secFieldReqDTO.setFieldIfSpellcheck("0");
        secFieldReqDTO.setFieldIfFacet("1");
        System.out.println(SolrUtils.generateSolrFieldName(secFieldReqDTO));

        System.out.println(new Date());
        System.out.println(SearchUtils.format(new Date()));

    }

}
