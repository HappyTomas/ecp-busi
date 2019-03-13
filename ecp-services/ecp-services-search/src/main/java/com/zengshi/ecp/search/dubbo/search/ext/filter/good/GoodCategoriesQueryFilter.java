package com.zengshi.ecp.search.dubbo.search.ext.filter.good;

import com.zengshi.ecp.search.dubbo.search.ext.filter.ExtraQueryInfo;
import com.zengshi.ecp.search.dubbo.search.ext.filter.ExtraQueryInfo.QueryCategory;
import com.zengshi.ecp.search.dubbo.search.ext.filter.QueryFilter;
import com.zengshi.ecp.search.dubbo.search.ext.filter.QueryFilter.ORGroup.ORField;
import org.apache.commons.collections.CollectionUtils;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: 商品分类过滤器，多个分类使用OR操作<br>
 * Date:2015年9月7日上午10:29:16 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
public class GoodCategoriesQueryFilter implements QueryFilter {

    @Override
    public QueryGroup generateQueryGroup(ExtraQueryInfo extraQueryInfo) {

        // 目前设计上只需根据某个分类编码即可查询出该分类下的所有商品
        if (CollectionUtils.isNotEmpty(extraQueryInfo.getQueryCategoryList())) {
            
            QueryGroup gueryGroup = new QueryGroup();
            
            for(QueryCategory queryCategory:extraQueryInfo.getQueryCategoryList()){
                
                //多个分类或
                if(queryCategory.isCategoryOrRelation()&&queryCategory.getCategoryCodeList().size()>1){
                    
                    ORGroup orGroup = new ORGroup();
                    ORField orField=new ORField();
                    orField.setFieldName(queryCategory.getCategoryFieldName()); 
                    orField.setValueList(queryCategory.getCategoryCodeList());
                    orGroup.addORField(orField);

                    gueryGroup.addORGroup(orGroup);
                    
                }else{//多个分类与，单个分类
                    // 查询某个分类下的所有商品，应该是"与"的逻辑关系
                    ANDField andField = new ANDField();
                    andField.setFieldName(queryCategory.getCategoryFieldName());
                    andField.setValueList(queryCategory.getCategoryCodeList());

                    gueryGroup.addANDField(andField);
                }
                
            }
            
            return gueryGroup;

        }
        return null;
    }

}
