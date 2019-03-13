package com.zengshi.ecp.search.dubbo.search.score.valuesource;

import org.apache.lucene.index.AtomicReaderContext;
import org.apache.lucene.queries.function.FunctionValues;
import org.apache.lucene.queries.function.docvalues.FloatDocValues;
import org.apache.lucene.queries.function.valuesource.FieldCacheSource;
import org.apache.lucene.search.FieldCache;

import java.io.IOException;
import java.util.Map;

/**
 * Created by HDF on 2016/12/16.
 */
public class DateFieldCacheSource extends FieldCacheSource {
    private static final long serialVersionUID = 6752223682280098130L;
    private long now;
    private FieldCache.Longs longs;
    private String field;
    public DateFieldCacheSource(String field) {
        super(field);
        now = System.currentTimeMillis();
        this.field=field;
    }
    @Override
    public FunctionValues getValues(Map context,
                                    AtomicReaderContext readerContext) throws IOException {

        //获取各个记录中的时间字段毫秒数
        longs = cache.getLongs(readerContext.reader(), field, false);

        /*return new FunctionValues() {//返回
            float factor=-1f;
            @Override
            public float floatVal(int doc) {
                //获取每个记录的时间衰减因子
                if(factor==-1f){
                    factor=ScoreUtils.getNewsScoreFactor(now, longs.get(doc),field);
                }
                return factor;
            }
            @Override
            public int intVal(int doc) {
                //获取每个记录的时间衰减因子
                if(factor==-1f){
                    factor=ScoreUtils.getNewsScoreFactor(now, longs.get(doc),field);
                }
                return (int) factor;
            }
            @Override
            public String toString(int doc) {
                return ScoreUtils.MODULE+description() + "；intVal=" + intVal(doc) + "；floatVal=" + floatVal(doc);
            }
        };*/

        return new FloatDocValues(this) {

            float factor=-1f;

            @Override
            public float floatVal(int doc) {
                if(factor==-1f){
                    long ptime = longs.get(doc);
                    factor= ScoreUtils.getNewsScoreFactor(now,ptime,field);
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
    public String description() {
        return "ECPSEC ValueSource实现类："+getClass().getName();
    }
}

