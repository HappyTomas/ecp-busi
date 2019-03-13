package com.zengshi.ecp.search.index;

import com.zengshi.ecp.search.dubbo.search.util.ELang;
import com.zengshi.ecp.search.dubbo.util.SearchCacheUtils;
import com.zengshi.ecp.search.index.util.SolrUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;

import com.zengshi.ecp.search.dubbo.search.comp.QuggestComp;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.index.util.Pinyin4jUtil;
import com.zengshi.paas.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年9月23日下午2:26:31 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
public class QuggestManager {
    
    public final static String MODULE = "【搜索引擎】QuggestManager";

    public final static String FIELD_PINYIN = "pinyin";

    public final static String FIELD_ABBRE = "abbre";

    public final static String FIELD_KW_SRC = "kwsrc";

    /**
     * 基于分词的全分词suggest构建
     * @param id
     * @param kw
     * @return
     */
    public static List<SolrInputDocument> createDocAll(String id, String kw) {

        //非多语言类型，默认是中文语言
        return createDocAll(id, ELang.chinese.getLang(), kw);
    }

    /**
     * 基于分词的全分词suggest构建
     * @param id
     * @param langObj
     * @param kw
     * @return
     */
    public static List<SolrInputDocument> createDocAll(String id,Object langObj,String kw) {
        String lang=ELang.chinese.getLang();
        if(langObj!=null){
            lang= (String) langObj;
        }
        return createDocAll(id,lang,kw);
    }

    /**
     * 基于分词的全分词suggest构建
     * @param id
     * @param lang
     * @param kw
     * @return
     */
    public static List<SolrInputDocument> createDocAll(String id,String lang,String kw) {
        List<SolrInputDocument> list=new ArrayList<SolrInputDocument>();
        List<String> terms=SolrUtils.generateTerms(kw,lang);
        List<String> pinYins=new ArrayList<String>();
        List<String> pinYinHeadChars=new ArrayList<String>();

        int size=terms.size();

        int count=0;
        String term;
        for(int i=0;i<size;i++){
            term=terms.get(i);

            //只有纯文本、中文支持拼音提示//英文不需要中文转拼音
            if(StringUtils.equals(lang,ELang.chinese.getLang())||
                    StringUtils.equals(lang, ELang.string.getLang())){
                pinYins.add(Pinyin4jUtil.getPinYin(term));
                pinYinHeadChars.add(Pinyin4jUtil.getPinYinHeadChar(term));
            }else{
                //值为空
                pinYins.add("");
                pinYinHeadChars.add("");
            }

            // 单个分词存储：跳过单字（单字的分词结果没意义，提示没意义）
            if(term.length()>1){
                list.add(createDoc(id+SearchConstants.SEPERATOR + (++count),1,term,pinYins.get(i),pinYinHeadChars.get(i),lang));
            }
        }

        // 组合分词存储
        int suggestionMaxterms=SearchCacheUtils.getSuggestionMaxterms();

        //n-1
        if(suggestionMaxterms>=2){
            for(int j=0;j<size-1;j++){
                list.add(createDoc(id+SearchConstants.SEPERATOR + (++count),2,terms.get(j)+terms.get(j+1),pinYins.get(j)+pinYins.get(j+1),pinYinHeadChars.get(j)+pinYinHeadChars.get(j+1),lang));
            }
        }

        //n-2
        if(suggestionMaxterms>=3){
            for(int j=0;j<size-2;j++){
                list.add(createDoc(id+SearchConstants.SEPERATOR + (++count),3,terms.get(j)+terms.get(j+1)+terms.get(j+2),pinYins.get(j)+pinYins.get(j+1)+pinYins.get(j+2),pinYinHeadChars.get(j)+pinYinHeadChars.get(j+1)+pinYinHeadChars.get(j+2),lang));
            }
        }

        //n-3
        if(suggestionMaxterms>=4){
            for(int j=0;j<size-3;j++){
                list.add(createDoc(id+SearchConstants.SEPERATOR + (++count),4,terms.get(j)+terms.get(j+1)+terms.get(j+2)+terms.get(j+3),pinYins.get(j)+pinYins.get(j+1)+pinYins.get(j+2)+pinYins.get(j+3),pinYinHeadChars.get(j)+pinYinHeadChars.get(j+1)+pinYinHeadChars.get(j+2)+pinYinHeadChars.get(j+3),lang));
            }
        }

        //n-4
        //最多支持5个分词的组合
        if(suggestionMaxterms>=5){
            for(int j=0;j<size-4;j++){
                list.add(createDoc(id+SearchConstants.SEPERATOR + (++count),5,terms.get(j)+terms.get(j+1)+terms.get(j+2)+terms.get(j+3)+terms.get(j+4),pinYins.get(j)+pinYins.get(j+1)+pinYins.get(j+2)+pinYins.get(j+3)+pinYins.get(j+4),pinYinHeadChars.get(j)+pinYinHeadChars.get(j+1)+pinYinHeadChars.get(j+2)+pinYinHeadChars.get(j+3)+pinYinHeadChars.get(j+4),lang));
            }
        }

        return list;
    }

    /*private static SolrInputDocument createDoc(String id,String kw) {

        //非多语言类型，默认是中文语言
        return createDoc(id, SearchConstants.LANG_CN, kw);
    }*/

    /*private static SolrInputDocument createDoc(String id,String lang,String kw) {

        SolrInputDocument doc = new SolrInputDocument();
        doc.setField(SearchConstants.ID, id);
        doc.setField(QuggestComp.FIELD_KW, kw);
        
        //只有中文支持拼音提示
        if(StringUtils.equals(lang, "zh-cn")||
                StringUtils.equals(lang, "zh-tw")||
                StringUtils.equals(lang, "zh-hk")){
            
            //pinyin4j导致的内存溢出问题
            doc.setField(QuggestManager.FIELD_PINYIN, Pinyin4jUtil.getPinYin(kw));
            doc.setField(QuggestManager.FIELD_ABBRE, Pinyin4jUtil.getPinYinHeadChar(kw));
        }
        
        return doc;
    }*/

    private static SolrInputDocument createDoc(String id,int termCount,String kw,String pinYin,String pinYinHeadChar,String lang) {

        SolrInputDocument doc = new SolrInputDocument();
        doc.setField(SearchConstants.ID, id);
        doc.setField(QuggestComp.FIELD_KW, termCount+kw);

        //pinyin4j导致的内存溢出问题
        doc.setField(QuggestManager.FIELD_KW_SRC, kw);
        doc.setField(QuggestManager.FIELD_PINYIN, pinYin);
        doc.setField(QuggestManager.FIELD_ABBRE, pinYinHeadChar);

        //语言，用于多语言suggest选项
        doc.setField(SearchConstants.FIELD_LANG,lang);

        return doc;
    }

    public static void deleteIndex(SolrServer solrServer, String id) {

        try {
            solrServer.deleteByQuery(SearchConstants.ID + SearchConstants.COLON + id
                    + SearchConstants.SEPERATOR + SearchConstants.STAR);

        } catch (Exception e) {
            
            //特殊情况下的索引删除异常不应该中断索引重建过程
            LogUtil.error(MODULE, "索引删除异常", e);
//            throw new BusinessException(
//                    SearchMessageKeyContants.Error.KEY_ERROR_INDEX_DELETE, new String[] {
//                            id, SearchUtils.getExceptionMessage(e) });
        }
    }
    
    public static void deleteByQuery(SolrServer solrServer,String query) {

        try {
            solrServer.deleteByQuery(query);

        } catch (Exception e) {
            
            //特殊情况下的索引删除异常不应该中断索引重建过程
            LogUtil.error(MODULE, "索引删除异常", e);
//            throw new BusinessException(
//                    SearchMessageKeyContants.Error.KEY_ERROR_INDEX_DELETE, new String[] {
//                            query, SearchUtils.getExceptionMessage(e) });
        } 
    }

}
