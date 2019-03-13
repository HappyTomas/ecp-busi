package com.zengshi.ecp.search.dubbo.search.util;

/**
 * 当前搜索引擎支持的语言（分词）
 */
public enum ELang {

    string("string"),
    english("texten"),
    chinese("textcn"),
    Arabic("textar"),
    Bulgarian("textbg"),
    Catalan("textca"),
    Kurdish("textckb"),
    Czech("textcz"),
    Danish("textda"),
    German("textde"),
    Greek("textel"),
    Spanish("textes"),
    Basque("texteu"),
    Persian("textfa"),
    Finnish("textfi"),
    French("textfr"),
    Irish("textga"),
    Galician("textgl"),
    Hindi("texthi"),
    Hungarian("texthu"),
    Armenian("texthy"),
    Indonesian("textid"),
    Italian("textit"),
    Japanese("textja"),
    Latvian("textlv"),
    Dutch("textnl"),
    Norwegian("textno"),
    Portuguese("textpt"),
    Romanian("textro"),
    Russian("textru"),
    Swedish("textsv"),
    Thai("textth"),
    Turkish("texttr");

    private final String lang;

    public String getLang() {
        return this.lang;
    }

    private ELang(String lang) {
        this.lang = lang;
    }

}
