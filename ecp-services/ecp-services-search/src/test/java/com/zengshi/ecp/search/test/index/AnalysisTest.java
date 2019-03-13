package com.zengshi.ecp.search.test.index;

import com.zengshi.ecp.search.dubbo.search.util.ELang;
import com.zengshi.ecp.search.index.util.SolrUtils;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HDF on 2016/7/11.
 */
public class AnalysisTest {

    //7698 ms
    public static void test1(){

        String s="斯伯丁NBA篮球 74-604Y室外室内lanqiu经典掌控74-221l篮球";

        for(int i=0;i<10000;i++){
            //开启智能分词
            IKSegmenter ik = new IKSegmenter(new StringReader(s+"test"+i) , true);

            List<String> list=new ArrayList<String>();
            try {
                Lexeme lexeme ;
                while( ( lexeme = ik.next() ) != null ) {
                    list.add(lexeme.getLexemeText());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(list);
        }

    }

    //787
    public static void test2(){

        String s="斯伯丁NBA篮球 74-604Y室外室内lanqiu经典掌控74-221l篮球";

        //开启智能分词
        IKSegmenter ik = new IKSegmenter(new StringReader(s) , true);

        for(int i=0;i<10000;i++){

            ik.reset(new StringReader(s+"test"+i));

            List<String> list=new ArrayList<String>();
            try {
                Lexeme lexeme ;
                while( ( lexeme = ik.next() ) != null ) {
                    list.add(lexeme.getLexemeText());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(list);
        }

    }

    public static void test3(String s,boolean useSmart){

        //开启智能分词
        IKSegmenter ik = new IKSegmenter(new StringReader(s) , useSmart);

        List<String> list=new ArrayList<String>();
        try {
            Lexeme lexeme ;
            while( ( lexeme = ik.next() ) != null ) {
                list.add(lexeme.getLexemeText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(list);

    }

    private static void mulLanAnalysisTest(){
        Map<String,String> map=new HashMap<>();
        map.put("我是黄端锋huangdf你好", ELang.chinese.getLang());
        map.put("الصورة: مقتل 33 شخصا في انفجار غازي بمنجم فحم جنوب غربي الصين", ELang.Arabic.getLang());
        map.put("РФ и Китай продолжат сотрудничество по плавучим АЭС и быстрым реакторам - Росатом", ELang.Russian.getLang());
        map.put("(7)EGIPTO-CAIRO-SOCIEDAD-EVENTO", ELang.Spanish.getLang());
        map.put("L'actrice de Star Wars, Carrie Fisher, décède à l'âge de 60 ans", ELang.French.getLang());
        map.put("Honeywell introduz mandarim no sistema de advertência de cabine de helicóptero", ELang.Portuguese.getLang());
        for(Map.Entry<String,String> e:map.entrySet()){
            System.out.println("==============="+e.getKey()+"=================");
            List<String> list=SolrUtils.generateTerms(e.getKey(),e.getValue());
            for(String s:list){
                System.out.println(s);
            }
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }

    public static void main(String args[]){

        //性能相差了十倍
        long startTime=System.currentTimeMillis();
        //test1();
        //test2();
        for(int i=1;i<=10000;i++){
            mulLanAnalysisTest();
        }
        /*test3("内科学",true);
        test3("内科学",false);
        test3("A内科学",false);
        test3("B内科学",false);
        test3("C内科学",false);
        test3("A内科学M",false);*/
        /*test3("据说八路军驻京办事处旧址今天发生了恐怖袭击事件，习近平主席做出指示！",true);
        test3("据说八路军驻京办事处旧址今天发生了恐怖袭击事件，习近平主席做出指示！",false);*/
        long endTime=System.currentTimeMillis();
        System.out.println("耗时："+(endTime-startTime));

        /*System.out.println(SolrUtils.generateTerms("斯伯丁NBA篮球 74-604Y室外室内lanqiu经典掌控74-221l篮球"));
        System.out.println(SolrUtils.generateTerms("篮球正品红双喜牛皮质感真耐磨7号室内室外水泥地比赛蓝球lanqiu"));
        System.out.println(SolrUtils.generateTerms("李宁 7号6号5号篮球男女青少年篮球室内室外掌控防滑耐磨正品包邮"));
        System.out.println(SolrUtils.generateTerms("正品萨达吸湿真皮牛皮质感软皮7号室外翻毛篮球水泥地耐磨包邮"));*/
    }

}
