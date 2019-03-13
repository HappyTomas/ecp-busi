package com.zengshi.ecp.search.test.lang;

import com.zengshi.ecp.search.dubbo.search.util.ELang;
import com.zengshi.ecp.search.dubbo.search.util.LangUtils;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by HDF on 2016/11/21.
 */
public class LangCheckTest {

    private static void testUnicode(){

        String[] checkStrs=new String[]{
                //textcn
                "123秋色正浓",
                //textes
                "123órgano ",
                //textfr
                "123américain ",
                //textru
                "123Китайские",
                //textar
                "لسلامة النووية123"
        };

        System.out.println("共："+checkStrs.length);
        for(String checkStr:checkStrs){
            if(StringUtils.isNotBlank(checkStr)&&!StringUtils.equals(checkStr, SearchConstants.STAR)) {
                char[] checkChars = checkStr.toCharArray();
                for (int i = 0; i < checkChars.length; i++) {
                    char checkChar = checkChars[i];

                    //跳过空字符
                    if (checkChar == ' ') {
                        continue;
                    }

                    Character.UnicodeScript unicodeScript = LangUtils.langUnicodeScript(checkChar);
                    System.out.print(unicodeScript+"：");

                    Character.UnicodeBlock unicodeBlock = LangUtils.langUnicodeBlock(checkChar);
                    System.out.print(unicodeBlock+"：");

                    break;
                }

                List<ELang> langs=LangUtils.checkLang(checkStr);
                if(CollectionUtils.isNotEmpty(langs)){
                    for(ELang lang:langs){
                        System.out.print(lang.getLang()+" ");
                    }
                }
                System.out.println();

            }
        }

    }

    public static void main(String[] args) {
        testUnicode();
    }

}
