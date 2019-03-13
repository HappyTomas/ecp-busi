package com.zengshi.ecp.search.index.util;

import com.zengshi.paas.utils.LogUtil;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
  pinyin4j的主页：http://pinyin4j.sourceforge.net/
  pinyin4j能够根据中文字符获取其对应的拼音，而且拼音的格式可以定制。
  pinyin4j是一个支持将中文转换到拼音的Java开源类库。
    
    支持简体中文和繁体中文字符；
    支持转换到汉语拼音，通用拼音, 威妥玛拼音（威玛拼法）, 注音符号第二式, 耶鲁拼法和国语罗马字；
    支持多音字，即可以获取一个中文字符的多种发音；
    支持多种字符串输出格式，比如支持Unicode格式的字符ü和声调符号(阴平 "ˉ",阳平"ˊ",上声"ˇ",去声"ˋ")的输出。
 */
public class Pinyin4jUtil {

    public final static String MODULE = "【搜索引擎】Pinyin4jUtil";

    /**
     * 将汉字转换为全拼
     * 
     * @param src
     * @return String
     */
    public static String getPinYin(String src) {
        char[] charArr = src.toCharArray();
        if(charArr==null||charArr.length==0){
            return "";
        }
        int charArrLen = charArr.length;
        String[] t2;
        // 设置汉字拼音输出的格式
        HanyuPinyinOutputFormat hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
        hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        hanyuPinyinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        String pinyin = "";
        try {
            for (int i = 0; i < charArrLen; i++) {
                // 判断能否为汉字字符
                if (Character.toString(charArr[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(charArr[i], hanyuPinyinOutputFormat);// 将汉字的几种全拼都存到t2数组中
                    if(t2!=null&&t2.length>0){
                        pinyin += t2[0];// 取出该汉字全拼的第一种读音并连接到字符串t4后
                    }
                } else {
                    // 如果不是汉字字符，间接取出字符并连接到字符串t4后
                    pinyin += Character.toString(charArr[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            LogUtil.error(MODULE, "汉字转拼音抛出异常", e);
        }
        return pinyin;
    }
 
    /**
     * 提取每个汉字的首字母
     * 
     * @param str
     * @return String
     */
    public static String getPinYinHeadChar(String str) {
        String header = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            // 提取汉字的首字母
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                header += pinyinArray[0].charAt(0);
            } else {
                header += word;
            }
        }
        return header;
    }
    
}
