package com.zengshi.ecp.search.dubbo.search.score.valuesource;

import org.apache.lucene.queries.function.ValueSource;
import org.apache.solr.common.util.NamedList;
import org.apache.solr.search.FunctionQParser;
import org.apache.solr.search.SyntaxError;
import org.apache.solr.search.ValueSourceParser;

/**
 * Created by HDF on 2016/12/16.
 */
public class DateFieldCacheSourceParser extends ValueSourceParser {

    @Override
    public void init(NamedList namedList) {
    }
    @Override
    public ValueSource parse(FunctionQParser fp) throws SyntaxError {

        //参数支持
        String field=fp.parseArg();

        // 被自定义排序的字段
        return new DateFieldCacheSource(field);
    }


}
