package com.zengshi.ecp.search.dubbo.search.support;

import java.util.List;
import java.util.Map;

import com.zengshi.ecp.search.dubbo.search.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;

import com.zengshi.ecp.search.dubbo.search.comp.BaseComp;
import com.zengshi.ecp.search.dubbo.search.util.EOperator;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.dubbo.search.util.SearchUtils;

public class ExtraFieldQuerySupport {

    public static SolrQuery addExtraFieldQuerySupport(SolrQuery solrQuery, SearchParam searchParam,
            Map<String, String> beanFieldName2IndexNameMap,Map<String,String> multiLangMap,Map<String,List<String>> indexName2MulLanIndexNameMapList) throws SearchException {

        StringBuffer sb = new StringBuffer();
        
        boolean first=true;
        
        //id字段搜索
        if(StringUtils.isNotEmpty(searchParam.getId())){
            
            first=false;
            
            String id=searchParam.getId();

            //solr number类型模糊查询有问题，数据库是没问题的
            //solr id默认是string类型，因为id做模糊查询是没问题的
        	if(searchParam.isIfFuzzyQuery()){
        		id=SearchUtils.getFuzzyQueryKeyword(id);
        	}
            
            //排除查询，不使用隐义的NOT查询。
            if(searchParam.isIfIdNot()){
                sb.append(EOperator.NOT.getName() + " ");
            }
            
            sb.append(SearchConstants.ID
                    + SearchConstants.COLON +id);
        }

        String indexName="";

        //字段多值查询
        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(searchParam
                .getExtraANDMulValueFieldQueryList())) {
            for (MulValueExtraFieldQueryField extraFieldQueryField : searchParam
                    .getExtraANDMulValueFieldQueryList()) {
                BaseComp.checkSrcField(extraFieldQueryField.getName(),
                        beanFieldName2IndexNameMap);
                if (StringUtils.isNotBlank(extraFieldQueryField.getName())) {

                    if(first) {
                        first=false;
                    } else{
                        sb.append(" " + EOperator.AND.getName() + " ");
                    }

                    //排除查询
                    if(extraFieldQueryField.isExcept()){
                        sb.append(EOperator.NOT.getName() + " ");
                    }

                    //多语言字段
                    indexName=BaseComp.getIndexField(extraFieldQueryField.getName(), beanFieldName2IndexNameMap);

                    if(indexName2MulLanIndexNameMapList.containsKey(indexName)){
                        throw new SearchException("MulValueExtraFieldQueryField字段不支持多语言字段");
                    }

                    StringBuffer sb1=new StringBuffer();
                    boolean flag=true;
                    if(CollectionUtils.isNotEmpty(extraFieldQueryField.getValue())){
                        for(String s:extraFieldQueryField.getValue()){
                            if(flag) {
                                flag=false;
                                if(extraFieldQueryField.isIfFuzzyQuery()){
                                    sb1.append(SearchUtils.getFuzzyQueryKeyword(s));
                                }else{
                                    sb1.append(s);
                                }
                            }else{
                                if(extraFieldQueryField.isIfFuzzyQuery()){
                                    sb1.append(" " + EOperator.OR.getName() + " "+SearchUtils.getFuzzyQueryKeyword(s));
                                }else{
                                    sb1.append(" " + EOperator.OR.getName() + " "+s);
                                }
                            }
                        }
                    }
                    sb.append(indexName+ SearchConstants.COLON + SearchConstants.BRACKETS_LEFT+sb1.toString()+SearchConstants.BRACKETS_RIGHT);
                }
            }
        }

        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(searchParam
                .getExtraANDFieldQueryList())) {
            for (ExtraFieldQueryField extraFieldQueryField : searchParam
                    .getExtraANDFieldQueryList()) {
                BaseComp.checkSrcField(extraFieldQueryField.getName(),
                        beanFieldName2IndexNameMap);
                if (StringUtils.isNotBlank(extraFieldQueryField.getName())) {
                    
                    if(first) {
                        first=false;
                    } else{
                        sb.append(" " + EOperator.AND.getName() + " "); 
                    }

                    //排除查询
                    if(extraFieldQueryField.isExcept()){
                        sb.append(EOperator.NOT.getName() + " ");
                    }
                    
                    //多语言字段
                    indexName=BaseComp.getIndexField(extraFieldQueryField.getName(), beanFieldName2IndexNameMap);

                    if(indexName2MulLanIndexNameMapList.containsKey(indexName)){
                        throw new SearchException("ExtraFieldQueryField字段不支持多语言字段");
                    }
                    
                    if(extraFieldQueryField.isIfFuzzyQuery()){
                    	sb.append(indexName+ SearchConstants.COLON + SearchUtils.getFuzzyQueryKeyword(extraFieldQueryField.getValue()));
                	}else{
                		sb.append(indexName+ SearchConstants.COLON + extraFieldQueryField.getValue());
                	}
                    
                }

            }
        }

        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(searchParam
                .getExtraORFieldQueryGroupList())) {
            
            for (ExtraORFieldQueryGroup extraORFieldQueryGroup : searchParam.getExtraORFieldQueryGroupList()) {
                
                if(first) {
                    first=false;
                } else{
                    sb.append(" " + EOperator.AND.getName() + " ");
                }
                
                //或条件组在条件都是NOT的情况下需要把NOT提到子查询外面否则导致查询结果为空。
                boolean allNot=true;
                for (QueryField extraFieldQueryField : extraORFieldQueryGroup.getExtraORFieldQueryList()) {
                    BaseComp.checkSrcField(extraFieldQueryField.getName(),
                            beanFieldName2IndexNameMap);
                    if(!extraFieldQueryField.isExcept()){
                        allNot=false;
                        break;
                    }
                }
                
                if(allNot){
                    sb.append(EOperator.NOT.getName());
                }
                
                sb.append(SearchConstants.BRACKETS_LEFT);
                
                boolean isFirst = true;
                for (QueryField extraFieldQueryField : extraORFieldQueryGroup.getExtraORFieldQueryList()) {
                  
                    //多语言字段
                    indexName=BaseComp.getIndexField(extraFieldQueryField.getName(), beanFieldName2IndexNameMap);

                    if(indexName2MulLanIndexNameMapList.containsKey(indexName)){
                        throw new SearchException("ExtraFieldQueryField字段不支持多语言字段");
                    }

                    if(extraFieldQueryField instanceof ExtraFieldQueryField){//单值查询字段
                        String keyword= (String) extraFieldQueryField.getValue();
                        if(extraFieldQueryField.isIfFuzzyQuery()){
                            keyword=SearchUtils.getFuzzyQueryKeyword(keyword);
                        }

                        if (isFirst) {
                            isFirst = false;
                        } else {

                            // 泛查询
                            sb.append(" " + EOperator.OR.getName() + " ");

                        }

                        //排除查询
                        if(!allNot&&extraFieldQueryField.isExcept()){
                            sb.append(EOperator.NOT.getName() + " ");
                        }

                        sb.append(indexName+ SearchConstants.COLON + keyword);

                    }else if(extraFieldQueryField instanceof MulValueExtraFieldQueryField){//多值查询字段

                        if(isFirst) {
                            isFirst=false;
                        } else{
                            // 泛查询
                            sb.append(" " + EOperator.OR.getName() + " ");
                        }

                        //排除查询
                        if(!allNot&&extraFieldQueryField.isExcept()){
                            sb.append(EOperator.NOT.getName() + " ");
                        }

                        //多语言字段
                        indexName=BaseComp.getIndexField(extraFieldQueryField.getName(), beanFieldName2IndexNameMap);

                        if(indexName2MulLanIndexNameMapList.containsKey(indexName)){
                            throw new SearchException("MulValueExtraFieldQueryField字段不支持多语言字段");
                        }

                        StringBuffer sb1=new StringBuffer();
                        boolean flag=true;
                        List<String> values= (List<String>) extraFieldQueryField.getValue();
                        if(CollectionUtils.isNotEmpty(values)){
                            for(String s:values){
                                if(flag) {
                                    flag=false;
                                    if(extraFieldQueryField.isIfFuzzyQuery()){
                                        sb1.append(SearchUtils.getFuzzyQueryKeyword(s));
                                    }else{
                                        sb1.append(s);
                                    }
                                }else{
                                    if(extraFieldQueryField.isIfFuzzyQuery()){
                                        sb1.append(" " + EOperator.OR.getName() + " "+SearchUtils.getFuzzyQueryKeyword(s));
                                    }else{
                                        sb1.append(" " + EOperator.OR.getName() + " "+s);
                                    }
                                }
                            }
                        }
                        sb.append(indexName+ SearchConstants.COLON + SearchConstants.BRACKETS_LEFT+sb1.toString()+SearchConstants.BRACKETS_RIGHT);

                    }

                }
                
                sb.append(SearchConstants.BRACKETS_RIGHT);
                
            }
            
        }
        
        String fq=sb.toString();
        if(StringUtils.isNotBlank(fq)){
            solrQuery.addFilterQuery(fq);
        }
        
        return solrQuery;

    }

}
