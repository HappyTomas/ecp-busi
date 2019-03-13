package com.zengshi.ecp.search.dubbo.search.score.valuesource;

import org.apache.lucene.index.AtomicReaderContext;
import org.apache.lucene.queries.function.FunctionValues;
import org.apache.lucene.queries.function.ValueSource;
import org.apache.lucene.queries.function.docvalues.FloatDocValues;
import org.apache.lucene.search.IndexSearcher;

import java.io.IOException;
import java.util.Map;

/**
 * Created by HDF on 2016/12/15.
 */
public class DateValueSource extends ValueSource {
    private long now;
    protected final ValueSource source;
    protected String args;
    public DateValueSource(ValueSource source,String args) {
        this.source = source;
        this.args=args;
        now = System.currentTimeMillis();
    }
    @Override
    public FunctionValues getValues(Map context, AtomicReaderContext readerContext) throws IOException {
        final FunctionValues vals = source.getValues(context, readerContext);
        return new FloatDocValues(this) {

            float factor=-1f;

            @Override
            public float floatVal(int doc) {
                if(factor==-1f){
                    //TODO throw new UnsupportedOperationException();
                    long ptime = vals.longVal(doc);
                    factor= ScoreUtils.getNewsScoreFactor(now,ptime,args);
                }
                return factor;
            }

            @Override
            public String toString(int doc) {
                return ScoreUtils.MODULE+description() + "；intVal=" + intVal(doc) + "；floatVal=" + floatVal(doc);
            }
        };
    }
    @Override
    public void createWeight(Map context, IndexSearcher searcher) throws IOException {
        source.createWeight(context, searcher);
    }
    @Override
    public String description() {
        return "ECPSEC ValueSource实现类："+getClass().getName();
    }
    @Override
    public int hashCode() {
        return source.hashCode();
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DateValueSource))
            return false;
        DateValueSource other = (DateValueSource) o;
        return source.equals(other.source);
    }
}
