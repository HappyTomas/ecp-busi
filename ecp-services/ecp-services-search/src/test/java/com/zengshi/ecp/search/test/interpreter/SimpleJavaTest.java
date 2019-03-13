package com.zengshi.ecp.search.test.interpreter;

import com.zengshi.ecp.search.dubbo.search.SearchException;
import com.zengshi.ecp.search.dubbo.search.comp.interpreter.*;
import com.zengshi.ecp.search.dubbo.search.util.ECompareStr;
import com.zengshi.ecp.search.dubbo.search.util.EOperator;
import org.apache.commons.collections.MapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by HDF on 2016/10/8.
 */
public class SimpleJavaTest {

    public static void main(String[] args) {

        /*String s="1习近平XXX";
        System.out.println(s.substring(1));
        System.out.println(s.substring(1,2));
        System.out.println(s.substring(0,2));*/

        /*List<Long> longs=new ArrayList<Long>();
        longs.add(1l);
        longs.add(new Long(2));
        long l1=1l;
        Long l2=new Long(2);
        System.out.println(longs.contains(l1));
        System.out.println(longs.contains(l2));*/

        /*try {
            //testBooleanExpre();
            testSearchQueryExpre();
        } catch (SearchException e) {
            e.printStackTrace();
        }*/

        bfTest();
    }

    private static void bfTest(){
        String bf="dateFieldCacheSourceParser(${test})";

        //变量列表
        Map<String,String> var2ValueMap=new HashMap<String,String>();

        Pattern pattern=Pattern.compile("(\\$)(\\{)(.+?)(\\})");
        Matcher matcher=pattern.matcher(bf);
        while(true) {
            if (matcher.find()) {
                String var = matcher.group(3);
                var2ValueMap.put(var,"test_string_sv_none");
            }else break;
        }
        if(MapUtils.isNotEmpty(var2ValueMap)){
            for(Map.Entry<String,String> entry:var2ValueMap.entrySet()){
                bf=bf.replaceAll("\\$\\{"+entry.getKey()+"\\}",entry.getValue());
            }
        }

        System.out.println("bf="+bf);
    }

    public static void testSearchQueryExpre() throws SearchException{

        //interpreter上下文
        Map<String, String> beanFieldName2IndexNameMap=new HashMap<String,String>();
        Map<String,String> multiLangMap=new HashMap<String,String>();
        beanFieldName2IndexNameMap.put("categoryCodes","categoryCodes_string_mv_facet");
        beanFieldName2IndexNameMap.put("columnCodes","columnCodes_string_mv_facet");
        beanFieldName2IndexNameMap.put("wordCount","wordCount_long_sv_none");
        beanFieldName2IndexNameMap.put("peoples","peoples_string_mv_facet");
        beanFieldName2IndexNameMap.put("areas","areas_string_mv_facet");
        beanFieldName2IndexNameMap.put("source","source_string_sv_facet");
        Context ctx = new Context(beanFieldName2IndexNameMap,multiLangMap);

        //分类
        List<KVPairExpre.Value> values=new ArrayList<KVPairExpre.Value>();
        values.add(new KVPairExpre.Value("fl001"));
        values.add(new KVPairExpre.Value("fl002", ECompareStr.CONTAIN));
        values.add(new KVPairExpre.Value("fl003", ECompareStr.NOTCONTAIN));
        values.add(new KVPairExpre.Value("fl004", ECompareStr.EQUAL));
        values.add(new KVPairExpre.Value("fl005",ECompareStr.NOTEQUAL));
        values.add(new KVPairExpre.Value("fl006", ECompareStr.GREATER));
        values.add(new KVPairExpre.Value("fl007", ECompareStr.GREATERTHAN));
        values.add(new KVPairExpre.Value("fl008", ECompareStr.LESS));
        values.add(new KVPairExpre.Value("fl009", ECompareStr.LESSTHAN));
        values.add(new KVPairExpre.Value("fl0010"));
        KVPairExpre categoryCodes=new KVPairExpre("categoryCodes",values, KVPairExpre.EValueOperator.OR);

        //栏目
        KVPairExpre columnCodes=new KVPairExpre("columnCodes",new KVPairExpre.Value("lm001"));

        //稿签
        KVPairExpre wordCount=new KVPairExpre("wordCount",new KVPairExpre.Value("1000",ECompareStr.GREATER));
        KVPairExpre peoples=new KVPairExpre("peoples",new KVPairExpre.Value("习近平"));
        KVPairExpre areas=new KVPairExpre("areas",new KVPairExpre.Value("北京",ECompareStr.CONTAIN));
        KVPairExpre source=new KVPairExpre("source",new KVPairExpre.Value("新华社"));

        //递归文法树/表达式
        //Expression exp = new And(new Or(categoryCodes,columnCodes),new And(wordCount,peoples,areas,source));
        Expression exp = new And(new Or(null,null),new And(null,null,areas,null));
        System.out.println(exp.grammar(ctx));
    }

    public static void testBooleanExpre() throws SearchException{
        Context ctx = new Context(null,null);
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        ctx.assign(x, false);
        ctx.assign(y, true);

        Constant c = new Constant(true);

        System.out.println("x=" + x.interpret(ctx));
        System.out.println("y=" + y.interpret(ctx));

        //递归文法树/表达式
        Expression exp = new Or(new And(c, x), new And(y, new Not(x)));
        System.out.println(exp.toString() + "=" + exp.interpret(ctx));

        exp = new Or(new And(c, new Or(new And(new Or(new And(c, x), new And(y, new Not(x))), x), new And(y, new Not(x)))), new And(y, new Not(x)));
        System.out.println(exp.toString() + "=" + exp.interpret(ctx));
    }

}
