package com.zengshi.ecp.search.dubbo.search.score.valuesource;
import org.apache.lucene.queries.function.ValueSource;
import org.apache.lucene.queries.function.valuesource.LongFieldSource;
import org.apache.solr.common.util.NamedList;
import org.apache.solr.search.FunctionQParser;
import org.apache.solr.search.SyntaxError;
import org.apache.solr.search.ValueSourceParser;

/**
 * Created by HDF on 2016/12/15.
 */
public class DateValueSourceParser extends ValueSourceParser {
    @Override
    public void init(NamedList namedList) {
    }
    @Override
    public ValueSource parse(FunctionQParser fp) throws SyntaxError {

        //参数支持
        String args=fp.parseArg();

        //ValueSource不能获取两次。所以fp.parseValueSourceList()和fp.parseValueSource()只能用一个
        //获取这个ValueSource，并在一个sercher中重用它
        //TODO throw new UnsupportedOperationException();
        //ValueSource source = fp.parseValueSource();

        ValueSource source =new LongFieldSource(args);

        return new DateValueSource(source,args);
    }
}
