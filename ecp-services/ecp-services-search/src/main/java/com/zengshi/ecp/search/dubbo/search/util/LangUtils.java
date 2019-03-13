package com.zengshi.ecp.search.dubbo.search.util;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HDF on 2016/11/21.
 */
public class LangUtils {

    /**
     * 语言环境检测（可能匹配到多个语言环境）
     * @param checkStr
     * @return
     */
    public static List<ELang> checkLang(String checkStr){

        checkStr=checkStr.trim();

        if(StringUtils.isBlank(checkStr)||StringUtils.equals(checkStr,SearchConstants.STAR)){
            return null;
        }

        Map<String,ELang> langMap=new HashMap<>();

        char[] checkChars = checkStr.toCharArray();
        for(int i = 0; i < checkChars.length; i++){
            char checkChar = checkChars[i];

            //跳过空字符
            if(Character.isWhitespace(checkChar)){
                continue;
            }

            Character.UnicodeScript unicodeScript=langUnicodeScript(checkChar);

            if(unicodeScript==null){
                continue;
            }

            //1.不需要处理数字
            //2.不需要处理标点符号等一些非语言符号，第一是关键字中的标点符号会被分词过滤掉，第二是标点符号对于检索是无意义的

            //TODO 有些Script仅支持一种文字类型，有些可以支持多种，例如 Latin Script 支持 supports English, French, German, Italian, Vietnamese, Latin 等等，具体可见wikipedia
            //TODO 出现这种“有些Script支持多种文字类型”，也就有可能出现某种语言的文本被其它语言截胡了，不过关系不大，那就说明这几种语言的分析器是兼容的
            //TODO 为避免多匹配问题，需要设置匹配到的优先级（如英语->中文-等。。。>）
                /*if(unicodeScript==Character.UnicodeScript.HAN){//string//纯文本无所谓的多语言区分
                    return ELang.string;
                }else*/

            //TODO 中英文需统一处理原因，是有的中文稿件内容是英文单词或字母开头的，如："WTA年终总决赛－－齐布尔科娃夺得女单冠军"
            //TODO 但是可能出现的问题是，非中文的Script如LATIN被识别为中文了，导致检索不出结果
            if(unicodeScript==Character.UnicodeScript.LATIN||unicodeScript==Character.UnicodeScript.RUNIC){//english
                langMap.put( ELang.chinese.getLang(),ELang.chinese);
            }
            if(unicodeScript==Character.UnicodeScript.HAN){//chinese
                langMap.put( ELang.chinese.getLang(),ELang.chinese);
            }
            if(unicodeScript==Character.UnicodeScript.ARABIC){//Arabic
                langMap.put( ELang.Arabic.getLang(),ELang.Arabic);
            }
            if(unicodeScript==Character.UnicodeScript.CYRILLIC){//Bulgarian
                langMap.put( ELang.Bulgarian.getLang(),ELang.Bulgarian);
            }
            if(unicodeScript==Character.UnicodeScript.LATIN){//Catalan
                langMap.put( ELang.Catalan.getLang(),ELang.Catalan);
            }
            if(unicodeScript==Character.UnicodeScript.ARABIC||unicodeScript==Character.UnicodeScript.LATIN){//Kurdish
                langMap.put( ELang.Kurdish.getLang(),ELang.Kurdish);
            }
            if(unicodeScript==Character.UnicodeScript.LATIN){//Czech
                langMap.put( ELang.Czech.getLang(),ELang.Czech);
            }
            if(unicodeScript==Character.UnicodeScript.LATIN||unicodeScript==Character.UnicodeScript.RUNIC){//Danish
                langMap.put( ELang.Danish.getLang(),ELang.Danish);
            }
            if(unicodeScript==Character.UnicodeScript.LATIN){//German
                langMap.put( ELang.German.getLang(),ELang.German);
            }
            if(unicodeScript==Character.UnicodeScript.GREEK||unicodeScript==Character.UnicodeScript.ARABIC){//Greek
                langMap.put( ELang.Greek.getLang(),ELang.Greek);
            }
            if(unicodeScript==Character.UnicodeScript.ARABIC||unicodeScript==Character.UnicodeScript.CYRILLIC||unicodeScript==Character.UnicodeScript.LATIN){//Spanish
                langMap.put( ELang.Spanish.getLang(),ELang.Spanish);
            }
            if(unicodeScript==Character.UnicodeScript.LATIN){//Basque
                langMap.put( ELang.Basque.getLang(),ELang.Basque);
            }
            if(unicodeScript==Character.UnicodeScript.CYRILLIC||unicodeScript==Character.UnicodeScript.ARABIC){//Persian
                langMap.put( ELang.Persian.getLang(),ELang.Persian);
            }
            if(unicodeScript==Character.UnicodeScript.LATIN){//Finnish
                langMap.put( ELang.Finnish.getLang(),ELang.Finnish);
            }
            if(unicodeScript==Character.UnicodeScript.LATIN||unicodeScript==Character.UnicodeScript.ARABIC){//French
                langMap.put( ELang.French.getLang(),ELang.French);
            }
            if(unicodeScript==Character.UnicodeScript.LATIN||unicodeScript==Character.UnicodeScript.OGHAM){//Irish
                langMap.put( ELang.Irish.getLang(),ELang.Irish);
            }
            if(unicodeScript==Character.UnicodeScript.LATIN){//Galician
                langMap.put( ELang.Galician.getLang(),ELang.Galician);
            }
            if(unicodeScript==Character.UnicodeScript.DEVANAGARI){//Hindi
                langMap.put( ELang.Hindi.getLang(),ELang.Hindi);
            }
            if(unicodeScript==Character.UnicodeScript.LATIN){//Hungarian
                langMap.put( ELang.Hungarian.getLang(),ELang.Hungarian);
            }
            if(unicodeScript==Character.UnicodeScript.ARMENIAN){//Armenian
                langMap.put( ELang.Armenian.getLang(),ELang.Armenian);
            }
            if(unicodeScript==Character.UnicodeScript.LATIN){//Indonesian
                langMap.put( ELang.Indonesian.getLang(),ELang.Indonesian);
            }
            if(unicodeScript==Character.UnicodeScript.LATIN){//Italian
                langMap.put( ELang.Italian.getLang(),ELang.Italian);
            }
            if(unicodeScript==Character.UnicodeScript.KATAKANA){//Japanese
                langMap.put( ELang.Japanese.getLang(),ELang.Japanese);
            }
            if(unicodeScript==Character.UnicodeScript.LATIN){//Latvian
                langMap.put( ELang.Latvian.getLang(),ELang.Latvian);
            }
            if(unicodeScript==Character.UnicodeScript.LATIN){//Dutch
                langMap.put( ELang.Dutch.getLang(),ELang.Dutch);
            }
            if(unicodeScript==Character.UnicodeScript.LATIN){//Norwegian
                langMap.put( ELang.Norwegian.getLang(),ELang.Norwegian);
            }
            if(unicodeScript==Character.UnicodeScript.LATIN){//Portuguese
                langMap.put( ELang.Portuguese.getLang(),ELang.Portuguese);
            }
            if(unicodeScript==Character.UnicodeScript.LATIN){//Romanian
                langMap.put( ELang.Romanian.getLang(),ELang.Romanian);
            }
            if(unicodeScript==Character.UnicodeScript.CYRILLIC){//Russian
                langMap.put( ELang.Russian.getLang(),ELang.Russian);
            }
            if(unicodeScript==Character.UnicodeScript.LATIN){//Swedish
                langMap.put( ELang.Swedish.getLang(),ELang.Swedish);
            }
            if(unicodeScript==Character.UnicodeScript.THAI){//Thai
                langMap.put( ELang.Thai.getLang(),ELang.Thai);
            }
            if(unicodeScript==Character.UnicodeScript.LATIN){//Turkish
                langMap.put( ELang.Turkish.getLang(),ELang.Turkish);
            }

            if(MapUtils.isNotEmpty(langMap)){
                break;
            }

        }

        return new ArrayList(langMap.values());
    }

    public static Character.UnicodeScript langUnicodeScript(char checkChar){
        return Character.UnicodeScript.of(checkChar);
    }

    public static Character.UnicodeBlock langUnicodeBlock(char checkChar){
        return Character.UnicodeBlock.of(checkChar);
    }

}
