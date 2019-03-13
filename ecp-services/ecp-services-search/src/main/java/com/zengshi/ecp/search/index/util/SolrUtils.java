package com.zengshi.ecp.search.index.util;

import com.zengshi.ecp.search.dubbo.dto.SecFieldReqDTO;
import com.zengshi.ecp.search.dubbo.search.util.ELang;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.paas.utils.LogUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.ar.ArabicAnalyzer;
import org.apache.lucene.analysis.bg.BulgarianAnalyzer;
import org.apache.lucene.analysis.ca.CatalanAnalyzer;
import org.apache.lucene.analysis.cz.CzechAnalyzer;
import org.apache.lucene.analysis.da.DanishAnalyzer;
import org.apache.lucene.analysis.de.GermanAnalyzer;
import org.apache.lucene.analysis.el.GreekAnalyzer;
import org.apache.lucene.analysis.es.SpanishAnalyzer;
import org.apache.lucene.analysis.eu.BasqueAnalyzer;
import org.apache.lucene.analysis.fa.PersianAnalyzer;
import org.apache.lucene.analysis.fi.FinnishAnalyzer;
import org.apache.lucene.analysis.fr.FrenchAnalyzer;
import org.apache.lucene.analysis.ga.IrishAnalyzer;
import org.apache.lucene.analysis.gl.GalicianAnalyzer;
import org.apache.lucene.analysis.hi.HindiAnalyzer;
import org.apache.lucene.analysis.hu.HungarianAnalyzer;
import org.apache.lucene.analysis.hy.ArmenianAnalyzer;
import org.apache.lucene.analysis.id.IndonesianAnalyzer;
import org.apache.lucene.analysis.it.ItalianAnalyzer;
import org.apache.lucene.analysis.ja.JapaneseAnalyzer;
import org.apache.lucene.analysis.lv.LatvianAnalyzer;
import org.apache.lucene.analysis.nl.DutchAnalyzer;
import org.apache.lucene.analysis.no.NorwegianAnalyzer;
import org.apache.lucene.analysis.pt.PortugueseAnalyzer;
import org.apache.lucene.analysis.ro.RomanianAnalyzer;
import org.apache.lucene.analysis.ru.RussianAnalyzer;
import org.apache.lucene.analysis.sv.SwedishAnalyzer;
import org.apache.lucene.analysis.th.ThaiAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tr.TurkishAnalyzer;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月11日下午11:12:33 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
public class SolrUtils {

    public final static String MODULE = "【搜索引擎】SolrUtils";

    //开启智能分词
    private static IKSegmenter ik = new IKSegmenter(new StringReader("") , true);

    //多语言分词器
    private static Analyzer analyzerAr = new ArabicAnalyzer(Version.LUCENE_44);
    private static Analyzer analyzerBg = new BulgarianAnalyzer(Version.LUCENE_44);
    private static Analyzer analyzerCa = new CatalanAnalyzer(Version.LUCENE_44);
    //private static Analyzer analyzerCkb = new KurdishAnalyzer(Version.LUCENE_44);
    private static Analyzer analyzerCz = new CzechAnalyzer(Version.LUCENE_44);
    private static Analyzer analyzerDa = new DanishAnalyzer(Version.LUCENE_44);
    private static Analyzer analyzerDe = new GermanAnalyzer(Version.LUCENE_44);
    private static Analyzer analyzerEl = new GreekAnalyzer(Version.LUCENE_44);
    private static Analyzer analyzerEs = new SpanishAnalyzer(Version.LUCENE_44);
    private static Analyzer analyzerEu = new BasqueAnalyzer(Version.LUCENE_44);
    private static Analyzer analyzerFa = new PersianAnalyzer(Version.LUCENE_44);
    private static Analyzer analyzerFi = new FinnishAnalyzer(Version.LUCENE_44);
    private static Analyzer analyzerFr = new FrenchAnalyzer(Version.LUCENE_44);
    private static Analyzer analyzerGa = new IrishAnalyzer(Version.LUCENE_44);
    private static Analyzer analyzerGl = new GalicianAnalyzer(Version.LUCENE_44);
    private static Analyzer analyzerHi = new HindiAnalyzer(Version.LUCENE_44);
    private static Analyzer analyzerHu = new HungarianAnalyzer(Version.LUCENE_44);
    private static Analyzer analyzerHy = new ArmenianAnalyzer(Version.LUCENE_44);
    private static Analyzer analyzerId = new IndonesianAnalyzer(Version.LUCENE_44);
    private static Analyzer analyzerIt = new ItalianAnalyzer(Version.LUCENE_44);
    private static Analyzer analyzerJa = new JapaneseAnalyzer(Version.LUCENE_44);
    private static Analyzer analyzerLv = new LatvianAnalyzer(Version.LUCENE_44);
    private static Analyzer analyzerNl = new DutchAnalyzer(Version.LUCENE_44);
    private static Analyzer analyzerNo = new NorwegianAnalyzer(Version.LUCENE_44);
    private static Analyzer analyzerPt = new PortugueseAnalyzer(Version.LUCENE_44);
    private static Analyzer analyzerRo = new RomanianAnalyzer(Version.LUCENE_44);
    private static Analyzer analyzerRu = new RussianAnalyzer(Version.LUCENE_44);
    private static Analyzer analyzerSv = new SwedishAnalyzer(Version.LUCENE_44);
    private static Analyzer analyzerTh = new ThaiAnalyzer(Version.LUCENE_44);
    private static Analyzer analyzerTr = new TurkishAnalyzer(Version.LUCENE_44);

    public static String generateSolrFieldName(SecFieldReqDTO secFieldReqDTO) {

        // 不使用搜索数据对象英文名作为前缀，出现字段不重叠/重叠情况，Schema字段配置取并集。
        String fieldIndexNamePost = secFieldReqDTO.getFieldBeanFieldName();
        String fieldTypeName = secFieldReqDTO.getFieldTypeName();
        String fieldIfMulti = secFieldReqDTO.getFieldIfMutlivalue();
        String fieldIfSpellCheck = secFieldReqDTO.getFieldIfSpellcheck();
        String fieldIfFacet = secFieldReqDTO.getFieldIfFacet();
        String fieldIfDf = secFieldReqDTO.getFieldIfBelongtoDf();

        StringBuffer sb = new StringBuffer();
        sb.append(fieldIndexNamePost);
        sb.append(SearchConstants.UNDERLINE + fieldTypeName);

        if (StringUtils.equals(SearchConstants.STATUS_1, fieldIfMulti)) {
            sb.append(SearchConstants.UNDERLINE + SearchConstants.Schema.FIELD_MV);
        } else {

            // 值为空默认当单值处理
            sb.append(SearchConstants.UNDERLINE + SearchConstants.Schema.FIELD_SV);
        }

        // 不允许同时配置成spellcheck和facet，先匹配spellcheck，后匹配facet
        if (StringUtils.equals(SearchConstants.STATUS_1, fieldIfSpellCheck)) {
            sb.append(SearchConstants.UNDERLINE + SearchConstants.Schema.FIELD_SPELLCHECK);
        } else if (StringUtils.equals(SearchConstants.STATUS_1, fieldIfFacet)) {
            sb.append(SearchConstants.UNDERLINE + SearchConstants.Schema.FIELD_FACET);
        } else {
            sb.append(SearchConstants.UNDERLINE + SearchConstants.Schema.FIELD_NONE);
        }

        //默认搜索字段
        if (StringUtils.equals(SearchConstants.STATUS_1, fieldIfDf)) {
            sb.append(SearchConstants.UNDERLINE + SearchConstants.Schema.FIELD_DF);
        }

        return sb.toString();

    }

    public static synchronized List<String> generateTerms(String s,String lang){

        List<String> list=new ArrayList<String>();

        try{
            //只有纯文本、英文、中文支持拼音提示
            if(StringUtils.equals(lang, ELang.chinese.getLang())||
                    StringUtils.equals(lang, ELang.english.getLang())||
                    StringUtils.equals(lang, ELang.string.getLang())){
                ik.reset(new StringReader(s));
                Lexeme lexeme ;
                while( ( lexeme = ik.next() ) != null ) {
                    list.add(lexeme.getLexemeText());
                }
            }else{

                Analyzer analyzer=null;

                //TODO 多语言分词
                if(StringUtils.equals(lang, ELang.Arabic.getLang())){
                    analyzer=analyzerAr;
                }else if(StringUtils.equals(lang, ELang.Bulgarian.getLang())){
                    analyzer=analyzerBg;
                }else if(StringUtils.equals(lang, ELang.Catalan.getLang())){
                    analyzer=analyzerCa;
                }else if(StringUtils.equals(lang, ELang.Kurdish.getLang())){
                    //analyzer=analyzer;
                }else if(StringUtils.equals(lang, ELang.Czech.getLang())){
                    analyzer=analyzerCz;
                }else if(StringUtils.equals(lang, ELang.Danish.getLang())){
                    analyzer=analyzerDa;
                }else if(StringUtils.equals(lang, ELang.German.getLang())){
                    analyzer=analyzerDe;
                }else if(StringUtils.equals(lang, ELang.Greek.getLang())){
                    analyzer=analyzerEl;
                }else if(StringUtils.equals(lang, ELang.Spanish.getLang())){
                    analyzer=analyzerEs;
                }else if(StringUtils.equals(lang, ELang.Basque.getLang())){
                    analyzer=analyzerEu;
                }else if(StringUtils.equals(lang, ELang.Persian.getLang())){
                    analyzer=analyzerFa;
                }else if(StringUtils.equals(lang, ELang.Finnish.getLang())){
                    analyzer=analyzerFi;
                }else if(StringUtils.equals(lang, ELang.French.getLang())){
                    analyzer=analyzerFr;
                }else if(StringUtils.equals(lang, ELang.Irish.getLang())){
                    analyzer=analyzerGa;
                }else if(StringUtils.equals(lang, ELang.Galician.getLang())){
                    analyzer=analyzerGl;
                }else if(StringUtils.equals(lang, ELang.Hindi.getLang())){
                    analyzer=analyzerHi;
                }else if(StringUtils.equals(lang, ELang.Hungarian.getLang())){
                    analyzer=analyzerHu;
                }else if(StringUtils.equals(lang, ELang.Armenian.getLang())){
                    analyzer=analyzerHy;
                }else if(StringUtils.equals(lang, ELang.Indonesian.getLang())){
                    analyzer=analyzerId;
                }else if(StringUtils.equals(lang, ELang.Italian.getLang())){
                    analyzer=analyzerIt;
                }else if(StringUtils.equals(lang, ELang.Japanese.getLang())){
                    analyzer=analyzerJa;
                }else if(StringUtils.equals(lang, ELang.Latvian.getLang())){
                    analyzer=analyzerLv;
                }else if(StringUtils.equals(lang, ELang.Dutch.getLang())){
                    analyzer=analyzerNl;
                }else if(StringUtils.equals(lang, ELang.Norwegian.getLang())){
                    analyzer=analyzerNo;
                }else if(StringUtils.equals(lang, ELang.Portuguese.getLang())){
                    analyzer=analyzerPt;
                }else if(StringUtils.equals(lang, ELang.Romanian.getLang())){
                    analyzer=analyzerRo;
                }else if(StringUtils.equals(lang, ELang.Russian.getLang())){
                    analyzer=analyzerRu;
                }else if(StringUtils.equals(lang, ELang.Swedish.getLang())){
                    analyzer=analyzerSv;
                }else if(StringUtils.equals(lang, ELang.Thai.getLang())){
                    analyzer=analyzerTh;
                }else if(StringUtils.equals(lang, ELang.Turkish.getLang())){
                    analyzer=analyzerTr;
                }

                if(analyzer!=null){
                    TokenStream ts = analyzer.tokenStream("field", s);
                    CharTermAttribute ch = (CharTermAttribute) ts.addAttribute(CharTermAttribute.class);

                    ts.reset();
                    while (ts.incrementToken()) {
                        list.add(ch.toString());
                    }
                    ts.end();
                    ts.close();
                }else{
                    //其它语种，原文返回
                    list.add(s);
                }

            }
        }catch (IOException e){
            LogUtil.error(MODULE, "分词异常", e);
        }

        return list;
    }

}
