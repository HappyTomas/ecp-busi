package com.zengshi.ecp.search.dubbo.search.ext.filter.good;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.search.dubbo.search.ext.filter.ExtraQueryInfo;
import com.zengshi.ecp.search.dubbo.search.ext.filter.ExtraQueryInfo.QueryProperty;
import com.zengshi.ecp.search.dubbo.search.ext.filter.QueryFilter;
import com.zengshi.ecp.search.dubbo.search.ext.filter.QueryFilter.ORGroup.ORField;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: 商品属性过滤器，不同属性组之间使用AND查询，同一个属性组内的多个属性值使用OR查询<br>
 * Date:2015年9月7日上午10:29:26 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
public class GoodPropertyQueryFilter implements QueryFilter {

    @Override
    public QueryGroup generateQueryGroup(ExtraQueryInfo extraQueryInfo) {
        if (CollectionUtils.isNotEmpty(extraQueryInfo.getQueryPropertyList())) {

            QueryGroup gueryGroup = new QueryGroup();
            List<ORGroup> orGroupList = new ArrayList<ORGroup>();

            ORGroup orGroup = null;
            ORField orField=null;
            List<String> valueList = null;
            
            //不同属性之间使用AND
            for (QueryProperty queryProperty : extraQueryInfo.getQueryPropertyList()) {

                orGroup = new ORGroup();
                orField=new ORField();
                orField.setFieldName(extraQueryInfo.getPropertyFieldName()); 
                valueList = new ArrayList<String>();

                for (String valueCode : queryProperty.getPropertyValueCodeList()) {
                    valueList.add(queryProperty.getPropertyCode() + SearchConstants.UNDERLINE
                            + valueCode);
                }

                // 同一个属性组内的多个属性值使用OR查询
                orField.setValueList(valueList);
                orGroup.addORField(orField);
                orGroupList.add(orGroup);
            }

            gueryGroup.setOrGroupList(orGroupList);
            return gueryGroup;
        }

        return null;

    }
}
