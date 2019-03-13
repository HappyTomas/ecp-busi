package com.zengshi.ecp.search.dubbo.search.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchConstants {

    public static final String STATUS_0 = "0";
    public static final String STATUS_1 = "1";
    public static final String STATUS_2 = "2";
    public static final String STATUS_VALID = STATUS_1;
    public static final String STATUS_INVALID = STATUS_0;
    public static final int STATUS_NEGATIVE_1= -1;
    public static final String COLLECTION_SUGGEST_SUFFIX = "4suggest";
    public static final String COLON = ":";
    public static final String COMMA = ",";
    public static final String UNDERLINE="_";
    public static final boolean DEBUG = true;
    public static final String QUERY_ALL = "*:*";
    public static final String FIELD_DF="aiecp_df";
    public static final String FIELD_SCORE="score";
    public static final String FIELD_ID_PARENT = "parentId";//数据源接口内部分页时存的外部主键
    public static final String FIELD_ID_CHILD = "childId";//数据源接口内部分页时存的内部主键(二级分页主键)
    public static final String ID_PARENT = FIELD_ID_PARENT+"_string_sv_none";
    public static final String ID_CHILD = FIELD_ID_CHILD+"_string_sv_none";
    public static final String ID = "id";//此id==（二级分页主键）；或==（外部主键+二级分页主键），如一个商品可能参与了多个促销商品编码无法保证在索引中唯一。
    public static final String TO="TO";
    public static final String STAR="*";
    public static final String BRACKETS_LEFT="(";
    public static final String BRACKETS_RIGHT=")";
    public static final String SEPERATOR="@";
    public static final String LANG_SEPERATOR=SEPERATOR;
    //注意了！！！该字段是SOLR字段，因此是固定的。记录多语言（非字段多语言），语言区分字段。
    // 区分与索引配置中的langField，langField（默认为"$lang$"）需要具备一定区分度（有的索引配置中字段也配置了语言字段名字可能也写成lang导致相互覆盖）
    //语言字段
    public static final String FIELD_LANG="lang";
    //语言的分词器字段
    public static final String FIELD_LANG_FIELD_TYPE ="langFieldType";

    //TODO 映射不单独通过Map定义，通过枚举统一定义

    /*//多语言映射定义
    public static final Map<String,String> langEn2SecFieldType=new HashMap<String,String>();
    public static final Map<String,String> langCn2SecFieldType=new HashMap<String,String>();

    //语言字段列表
    public static final List<String> langEnList=new ArrayList<>();

    static {

        langEn2SecFieldType.put("string","string");
        langCn2SecFieldType.put("纯文本","string");

        langEn2SecFieldType.put("english","texten");
        langCn2SecFieldType.put("英语","texten");

        langEn2SecFieldType.put("chinese","textcn");
        langCn2SecFieldType.put("汉语","textcn");

        langEn2SecFieldType.put("Arabic","textar");
        langCn2SecFieldType.put("阿拉伯语","textar");

        langEn2SecFieldType.put("Bulgarian","textbg");
        langCn2SecFieldType.put("保加利亚语","textbg");

        langEn2SecFieldType.put("Catalan","textca");
        langCn2SecFieldType.put("加泰罗尼亚语","textca");

        langEn2SecFieldType.put("Kurdish","textckb");
        langCn2SecFieldType.put("库尔德语","textckb");

        langEn2SecFieldType.put("Czech","textcz");
        langCn2SecFieldType.put("捷克语","textcz");

        langEn2SecFieldType.put("Danish","textda");
        langCn2SecFieldType.put("丹麦语","textda");

        langEn2SecFieldType.put("German","textde");
        langCn2SecFieldType.put("德语","textde");

        langEn2SecFieldType.put("Greek","textel");
        langCn2SecFieldType.put("希腊语","textel");

        langEn2SecFieldType.put("Spanish","textes");
        langCn2SecFieldType.put("西班牙语","textes");

        langEn2SecFieldType.put("Basque","texteu");
        langCn2SecFieldType.put("巴斯克语","texteu");

        langEn2SecFieldType.put("Persian","textfa");
        langCn2SecFieldType.put("波斯语","textfa");

        langEn2SecFieldType.put("Finnish","textfi");
        langCn2SecFieldType.put("芬兰语","textfi");

        langEn2SecFieldType.put("French","textfr");
        langCn2SecFieldType.put("法语","textfr");

        langEn2SecFieldType.put("Irish","textga");
        langCn2SecFieldType.put("爱尔兰语","textga");

        langEn2SecFieldType.put("Galician","textgl");
        langCn2SecFieldType.put("加里西亚语","textgl");

        langEn2SecFieldType.put("Hindi","texthi");
        langCn2SecFieldType.put("北印度语","texthi");

        langEn2SecFieldType.put("Hungarian","texthu");
        langCn2SecFieldType.put("匈牙利语","texthu");

        langEn2SecFieldType.put("Armenian","texthy");
        langCn2SecFieldType.put("亚美尼亚语","texthy");

        langEn2SecFieldType.put("Indonesian","textid");
        langCn2SecFieldType.put("印度尼西亚语","textid");

        langEn2SecFieldType.put("Italian","textit");
        langCn2SecFieldType.put("意大利语","textit");

        langEn2SecFieldType.put("Japanese","textja");
        langCn2SecFieldType.put("日语","textja");

        langEn2SecFieldType.put("Latvian","textlv");
        langCn2SecFieldType.put("拉脱维亚语","textlv");

        langEn2SecFieldType.put("Dutch","textnl");
        langCn2SecFieldType.put("荷兰语","textnl");

        langEn2SecFieldType.put("Norwegian","textno");
        langCn2SecFieldType.put("挪威语","textno");

        langEn2SecFieldType.put("Portuguese","textpt");
        langCn2SecFieldType.put("葡萄牙语","textpt");

        langEn2SecFieldType.put("Romanian","textro");
        langCn2SecFieldType.put("罗马尼亚语","textro");

        langEn2SecFieldType.put("Russian","textru");
        langCn2SecFieldType.put("俄语","textru");

        langEn2SecFieldType.put("Swedish","textsv");
        langCn2SecFieldType.put("瑞典语","textsv");

        langEn2SecFieldType.put("Thai","textth");
        langCn2SecFieldType.put("泰国语","textth");

        langEn2SecFieldType.put("Turkish","texttr");
        langCn2SecFieldType.put("土耳其语","texttr");



        langEnList.addAll(langEn2SecFieldType.keySet());

    }*/

    public final static class DeployType{
        public static final String STANDALONE = "1";//单点
        public static final String REPLICATION = "2";//主从
        public static final String CLOUD = "3";//cloud
    }
    
    public final static class Redirect{
        public static final String MATCH_TYPE_ALL = STATUS_1;
        public static final String MATCH_TYPE_CONTAIN = STATUS_2;
        public static final String REDIRECT_TYPE_NONEMATCHES = STATUS_0;
        public static final String REDIRECT_TYPE_URL = STATUS_1;
        public static final String REDIRECT_TYPE_NEWQUERY = STATUS_2;
    }
    
    public final static class Schema{
        public static final String FIELD_MV="mv";
        public static final String FIELD_SV="sv";
        public static final String FIELD_FACET="facet";
        public static final String FIELD_SPELLCHECK="spellcheck";//目前对于spellcheck拼写检查字段和suggest搜索建议的字段类型处理是一致的。
        public static final String FIELD_NONE="none";
        public static final String FIELD_DF="df";
    }
    
}
