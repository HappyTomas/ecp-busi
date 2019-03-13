package com.zengshi.ecp.search.dubbo.search.support;

import com.zengshi.ecp.search.dubbo.search.SearchException;
import com.zengshi.ecp.search.dubbo.search.SearchParam;
import com.zengshi.ecp.search.dubbo.search.comp.BaseComp;
import com.zengshi.ecp.search.dubbo.search.ext.filter.ExtraQueryInfo;
import com.zengshi.ecp.search.dubbo.search.ext.filter.QueryFilter;
import com.zengshi.ecp.search.dubbo.search.ext.filter.QueryFilter.ANDField;
import com.zengshi.ecp.search.dubbo.search.ext.filter.QueryFilter.ORGroup;
import com.zengshi.ecp.search.dubbo.search.ext.filter.QueryFilter.ORGroup.ORField;
import com.zengshi.ecp.search.dubbo.search.ext.filter.QueryFilter.QueryGroup;
import com.zengshi.ecp.search.dubbo.search.util.ELang;
import com.zengshi.ecp.search.dubbo.search.util.EOperator;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.dubbo.search.util.SearchUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年9月7日下午8:01:41 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
public class FilterSupport {

    public static SolrQuery filter(SearchParam searchParam,SolrQuery solrQuery, ExtraQueryInfo extraQueryInfo,
                                   List<QueryFilter> filterList, Map<String, String> beanFieldName2IndexNameMap,Map<String,String> multiLangMap,Map<String,List<String>> indexName2MulLanIndexNameMapList)
            throws SearchException {
        return filter(searchParam.getLangs(),solrQuery,extraQueryInfo,filterList,beanFieldName2IndexNameMap,multiLangMap,indexName2MulLanIndexNameMapList);
    }

    private static SolrQuery filter(List<ELang> langs, SolrQuery solrQuery, ExtraQueryInfo extraQueryInfo,
                                    List<QueryFilter> filterList, Map<String, String> beanFieldName2IndexNameMap, Map<String,String> multiLangMap,Map<String,List<String>> indexName2MulLanIndexNameMapList)
            throws SearchException {

        if (extraQueryInfo == null || CollectionUtils.isEmpty(filterList)) {
            return solrQuery;
        }

        List<QueryGroup> qeryGroupList = new ArrayList<QueryGroup>();
        QueryGroup qeryGroup = null;

        for (QueryFilter queryFilter : filterList) {
            qeryGroup = queryFilter.generateQueryGroup(extraQueryInfo);

            if (qeryGroup != null) {
                qeryGroupList.add(qeryGroup);
            }
        }

        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(qeryGroupList)) {

            StringBuffer sb = new StringBuffer();
            
            boolean first=true;

            List<ANDField> andFieldList = null;
            List<ORGroup> orGroupList = null;

            String indexName="";
            for (QueryGroup qg : qeryGroupList) {

                andFieldList = qg.getAndFieldList();
                if (org.apache.commons.collections.CollectionUtils.isNotEmpty(andFieldList)) {

                    for (ANDField andField : andFieldList) {

                        BaseComp.checkSrcField(andField.getFieldName(),
                                beanFieldName2IndexNameMap);

                        for (String value : andField.getValueList()) {
                            
                            String realV=value;
                            
                            if(first) {
                                first=false;
                            } else{
                                sb.append(" " + EOperator.AND.getName() + " "); 
                            }

                            //排除查询
                            if(realV.startsWith(EOperator.NOT.getName()+SearchConstants.UNDERLINE)){
                                sb.append(EOperator.NOT.getName() + " ");
                                realV=realV.substring(4);
                            }
                            
                        	if(andField.isIfFuzzyQuery()){
                        		realV=SearchUtils.getFuzzyQueryKeyword(realV);
                        	}
                            
                            //多语言字段
                            indexName=BaseComp.getIndexField(andField.getFieldName(), beanFieldName2IndexNameMap);

                            if(indexName2MulLanIndexNameMapList.containsKey(indexName)){
                                throw new SearchException("ANDField字段不支持多语言字段");
                            }
                            
                            // 精确查询
                            sb.append(indexName+ SearchConstants.COLON + realV);

                        }
                    }

                }

                orGroupList = qg.getOrGroupList();
                if (org.apache.commons.collections.CollectionUtils.isNotEmpty(orGroupList)) {

                    for (ORGroup orGroup : orGroupList) {

                        // 若属性组只有一个属性，且属性只有一个比较值，直接使用AND操作
                        if (orGroup.getOrFieldList().size() == 1
                                && orGroup.getOrFieldList().get(0).getValueList().size() == 1) {
                            
                            BaseComp.checkSrcField(orGroup.getOrFieldList().get(0).getFieldName(),
                                    beanFieldName2IndexNameMap);
                            
                            String realV=orGroup.getOrFieldList().get(0).getValueList().get(0);

                            if(first) {
                                first=false;
                            } else{
                                sb.append(" " + EOperator.AND.getName() + " "); 
                            }
                            
                            //排除查询
                            if(realV.startsWith(EOperator.NOT.getName()+SearchConstants.UNDERLINE)){
                                sb.append(EOperator.NOT.getName() + " ");
                                realV=realV.substring(4);
                            }
                            
                            if(orGroup.getOrFieldList().get(0).isIfFuzzyQuery()){
                        		realV=SearchUtils.getFuzzyQueryKeyword(realV);
                        	}
                            
                            //多语言字段
                            indexName=BaseComp.getIndexField(orGroup.getOrFieldList().get(0).getFieldName(), beanFieldName2IndexNameMap);

                            if(indexName2MulLanIndexNameMapList.containsKey(indexName)){
                                throw new SearchException("ORField字段不支持多语言字段");
                            }
                            
                            sb.append(indexName+ SearchConstants.COLON
                                    + realV);

                        } else {

                            for (ORField orField : orGroup.getOrFieldList()) {
                                
                                BaseComp.checkSrcField(orField.getFieldName(),
                                        beanFieldName2IndexNameMap);
                                
                                if(first) {
                                    first=false;
                                } else{
                                    sb.append(" " + EOperator.AND.getName() + " ");
                                }
                                
                                //或条件组在条件都是NOT的情况下需要把NOT提到子查询外面否则导致查询结果为空。
                                boolean allNot=true;
                                for (String value : orField.getValueList()) {
                                    if(!value.startsWith(EOperator.NOT.getName()+SearchConstants.UNDERLINE)){
                                        allNot=false;
                                        break;
                                    }
                                }
                                
                                if(allNot){
                                    sb.append(EOperator.NOT.getName()+" ");
                                }
                                
                                //多语言字段
                                indexName=BaseComp.getIndexField(orField.getFieldName(), beanFieldName2IndexNameMap);

                                if(indexName2MulLanIndexNameMapList.containsKey(indexName)){
                                    throw new SearchException("ORField字段不支持多语言字段");
                                }
                                
                                sb.append(indexName+SearchConstants.COLON+SearchConstants.BRACKETS_LEFT);

                                boolean isFirst = true;
                                for (String value : orField.getValueList()) {
                                    
                                    String realV=value;

                                    if (isFirst) {
                                        
                                        //排除查询
                                        if(realV.startsWith(EOperator.NOT.getName()+SearchConstants.UNDERLINE)){
                                            realV=realV.substring(4);
                                            
                                            if(!allNot){
                                                sb.append(EOperator.NOT.getName() + " ");
                                            }
                                        }
                                        
                                        if(orField.isIfFuzzyQuery()){
                                    		realV=SearchUtils.getFuzzyQueryKeyword(realV);
                                    	}
                                        
                                        // 泛查询
                                        sb.append(realV);
                                        isFirst = false;
                                    } else {

                                        // 泛查询
                                        sb.append(" "
                                                + EOperator.OR.getName()
                                                + " ");
                                        
                                        //排除查询
                                        if(realV.startsWith(EOperator.NOT.getName()+SearchConstants.UNDERLINE)){
                                            realV=realV.substring(4);
                                            
                                            if(!allNot){
                                                sb.append(EOperator.NOT.getName() + " ");
                                            }
                                        }
                                        
                                        if(orField.isIfFuzzyQuery()){
                                    		realV=SearchUtils.getFuzzyQueryKeyword(realV);
                                    	}
                                        
                                        sb.append(realV);
                                    }

                                }
                                
                                sb.append(SearchConstants.BRACKETS_RIGHT);

                            }

                        }

                    }

                }

            }
            
            String fq=sb.toString();
            if(StringUtils.isNotBlank(fq)){
                solrQuery.addFilterQuery(fq);
            }

        }

        return solrQuery;

    }
}
