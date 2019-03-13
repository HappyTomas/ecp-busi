package com.zengshi.ecp.search.test.interpreter;

import com.zengshi.ecp.search.dubbo.search.GrammarParam;
import com.zengshi.ecp.search.dubbo.search.SearchException;
import com.zengshi.ecp.search.dubbo.search.SearchFacade;
import com.zengshi.ecp.search.dubbo.search.SearchParam;
import com.zengshi.ecp.search.dubbo.search.comp.interpreter.And;
import com.zengshi.ecp.search.dubbo.search.comp.interpreter.Expression;
import com.zengshi.ecp.search.dubbo.search.comp.interpreter.KVPairExpre;
import com.zengshi.ecp.search.dubbo.search.comp.interpreter.Or;
import com.zengshi.ecp.search.dubbo.search.result.GrammarResult;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.search.dubbo.search.util.ECompareStr;
import com.zengshi.ecp.search.dubbo.search.util.EOperator;
import com.zengshi.ecp.server.test.EcpServicesTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by HDF on 2016/10/9.
 */
public class InterpreterTest extends EcpServicesTest {

    public Expression initComplexExpression() throws SearchException {
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
        Expression exp = new And(new Or(categoryCodes,columnCodes),new And(wordCount,peoples,areas,source));

        return exp;
    }

    public Expression initSimpleExpression() throws SearchException {

        //分类
        List<KVPairExpre.Value> values=new ArrayList<KVPairExpre.Value>();
        values.add(new KVPairExpre.Value("fl001",ECompareStr.NOTEQUAL));
        KVPairExpre categoryCodes=new KVPairExpre("categoryCodes",values, KVPairExpre.EValueOperator.OR);

        //栏目
        values=new ArrayList<KVPairExpre.Value>();
        values.add(new KVPairExpre.Value("201001",ECompareStr.NOTEQUAL));
        values.add(new KVPairExpre.Value("201007", ECompareStr.NOTEQUAL));
        values.add(new KVPairExpre.Value("201009", ECompareStr.NOTCONTAIN));
        KVPairExpre columnCodes=new KVPairExpre("columnCodes",values, KVPairExpre.EValueOperator.OR);

        //稿签
        KVPairExpre wordCount=new KVPairExpre("wordCount",new KVPairExpre.Value("158"));
        KVPairExpre wordCount2=new KVPairExpre("wordCount",new KVPairExpre.Value("158",ECompareStr.GREATERTHAN));
        /*KVPairExpre peoples=new KVPairExpre("peoples",new KVPairExpre.Value("习近平"));
        KVPairExpre areas=new KVPairExpre("areas",new KVPairExpre.Value("北京",ECompareStr.CONTAIN));
        KVPairExpre source=new KVPairExpre("source",new KVPairExpre.Value("新华社"));*/

        //递归文法树/表达式
        Expression exp = new And(new Or(categoryCodes,columnCodes),new And(wordCount,wordCount2));

        return exp;
    }

    @Test
    public void grammarTest(){
        GrammarParam grammarParam=new GrammarParam();
        grammarParam.setCollectionName("gdscollection");

        try {
            //grammarParam.setExp(initComplexExpression());
            grammarParam.setExp(initSimpleExpression());
        } catch (SearchException e) {
            e.printStackTrace();
        }
        GrammarResult grammarResult=SearchFacade.grammar(grammarParam);

        if(grammarResult.isIfSuccess()){
            System.out.println("文法生成成功！结果："+grammarResult.getGrammar());
        }else{
            System.out.println("文法生成失败！错误信息："+grammarResult.getMessage());
        }
    }

    @Test
    public void shortInterpreterTest(){

        String grammar="((categoryCodes_string_mv_facet:(fl001 OR *fl002* OR (* NOT *fl003*) OR fl004 OR (* NOT fl005) OR {fl006 TO *} OR [fl007 TO *] OR {* TO fl008} OR [* TO fl009] OR fl0010) OR columnCodes_string_mv_facet:lm001) AND (wordCount_long_sv_none:{1000 TO *} AND peoples_string_mv_facet:习近平 AND areas_string_mv_facet:*北京* AND source_string_sv_facet:新华社))";

        SearchParam interpreterParam=new SearchParam();
        interpreterParam.setGrammar(grammar);
        interpreterParam.setCollectionName("gdscollection");
        SearchResult<Map<String,Object>> searchResult=SearchFacade.search(interpreterParam,null,null,null);

        if(searchResult.isSuccess()){
            System.out.println("interpre成功！结果："+searchResult.getResultList());
        }else{
            System.out.println("interpre失败！错误信息："+searchResult.getMessage());
        }
    }

    @Test
    public void longInterpreterTest(){

        //超长文法POST测试
        String grammar="gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan OR " +
                "gdsName_textcn_sv_spellcheck:ActorsNaomiWattsan";

        SearchParam interpreterParam=new SearchParam();
        interpreterParam.setGrammar(grammar);
        interpreterParam.setCollectionName("gdscollection");
        SearchResult<Map<String,Object>> searchResult=SearchFacade.search(interpreterParam,null,null,null);

        if(searchResult.isSuccess()){
            System.out.println("interpre成功！结果："+searchResult.getResultList());
        }else{
            System.out.println("interpre失败！错误信息："+searchResult.getMessage());
        }
    }

}
