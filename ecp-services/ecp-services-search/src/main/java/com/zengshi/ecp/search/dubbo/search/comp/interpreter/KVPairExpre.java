package com.zengshi.ecp.search.dubbo.search.comp.interpreter;

import com.zengshi.ecp.search.dubbo.search.SearchException;
import com.zengshi.ecp.search.dubbo.search.comp.BaseComp;
import com.zengshi.ecp.search.dubbo.search.util.ECompareStr;
import com.zengshi.ecp.search.dubbo.search.util.EOperator;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.dubbo.search.util.SearchUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HDF on 2016/10/8.
 */
public class KVPairExpre extends Expression {

    public static class Value{
        private String value;
        private ECompareStr eCompareStr;
        public Value(String value){
            this.value=value;
            this.eCompareStr=ECompareStr.EQUAL;
        }
        public Value(String value,ECompareStr eCompareStr) throws SearchException{
            if(eCompareStr==null){
                throw new SearchException("ECompareStr为空");
            }
            if(StringUtils.isBlank(value)){
                throw new SearchException("value为空");
            }
            this.value=value;
            this.eCompareStr=eCompareStr;
        }

        public ECompareStr geteCompareStr() {
            return eCompareStr;
        }

        public String getValue() {
            return value;
        }
    }

    private String key;

    //一个字段多值的情况，如分类多值、栏目多值、稿签多值
    private List<Value> values;

    //字段多值，多值之间的关系EValueOperator
    private EValueOperator eOperator;

    public enum EValueOperator {

        AND("AND"), OR("OR");

        //ECompareStr里面已经有NOT了，为了防止复杂化，Value之间的关系EValueOperator取消NOT，只保留AND和OR
        //NOT("NOT");

        private final String name;

        public String getName() {
            return this.name;
        }

        private EValueOperator(String name) {
            this.name = name;
        }

    }

    public KVPairExpre(String key,Value value) throws SearchException{
        if(value==null){
            throw new SearchException("Value为空");
        }
        this.key = key;
        this.values=new ArrayList<Value>();
        values.add(value);
    }

    /**
     * 支持简易传参（默认比较符ECompareStr，因接口签名歧义，故调整参数顺序）
     * @param key
     * @param eOperator
     * @param valuesStr
     * @throws SearchException
     */
    public KVPairExpre(String key,EValueOperator eOperator,List<String> valuesStr) throws SearchException{
        if(valuesStr==null||valuesStr.size()==0){
            throw new SearchException("List<String>为空");
        }
        if(eOperator==null){
            throw new SearchException("字段多值，多值之间的关系EOperator为空");
        }
        List<Value> values=new ArrayList<Value>();
        for(String s:valuesStr){
            values.add(new Value(s));
        }
        this.key = key;
        this.values=values;
        this.eOperator=eOperator;
    }

    public KVPairExpre(String key,List<Value> values,EValueOperator eOperator) throws SearchException{
        if(values==null||values.size()==0){
            throw new SearchException("List<Value>为空");
        }
        if(eOperator==null){
            throw new SearchException("字段多值，多值之间的关系EValueOperator为空");
        }
        this.key = key;
        this.values=values;
        this.eOperator=eOperator;
    }

    public String getKey() {
        return key;
    }

    public List<Value> getValues() {
        return values;
    }

    public EValueOperator geteOperator() {
        return eOperator;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj instanceof KVPairExpre) {
            KVPairExpre other=((KVPairExpre) obj);
            boolean flag1= StringUtils.equals(this.getKey(),other.getKey());
            if(!flag1){
                return false;
            }
            boolean flag2=(this.getValues().size()==other.getValues().size());
            if(!flag2){
                return false;
            }
            for(int i=0;i<this.getValues().size();i++){
                if(!StringUtils.equals(this.getValues().get(i).getValue(),other.getValues().get(i).getValue())){
                    return false;
                }
                if(this.getValues().get(i).geteCompareStr()!=other.getValues().get(i).geteCompareStr()){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    private String getValue(Value _value){
        String stringValue="";
        switch (_value.geteCompareStr()){
            case CONTAIN://模糊查询
                stringValue= SearchUtils.getFuzzyQueryKeyword(_value.getValue());
                break;
            case NOTCONTAIN:
                //TODO NOT 不提前导致无结果：(NOT id:201390)，应该改为NOT (id:201390)
                //TODO NOT需要特殊处理，放到外部处理
                //stringValue= EOperator.NOT.getName()+" "+SearchUtils.getFuzzyQueryKeyword(_value.getValue());
                stringValue= SearchUtils.getFuzzyQueryKeyword(_value.getValue());
                break;
            case EQUAL:
                stringValue=_value.getValue();
                break;
            case NOTEQUAL:
                //TODO NOT 不提前导致无结果：(NOT id:201390)，应该改为NOT (id:201390)
                //TODO NOT需要特殊处理，放到外部处理
                //stringValue= EOperator.NOT.getName()+" "+_value.getValue();
                stringValue= _value.getValue();
                break;
            case GREATER:
                stringValue= "{"
                        + _value.getValue() + " " + SearchConstants.TO + " "
                        + SearchConstants.STAR + "}";
                break;
            case GREATERTHAN:
                stringValue= "["
                        + _value.getValue() + " " + SearchConstants.TO + " "
                        + SearchConstants.STAR + "]";
                break;
            case LESS:
                stringValue= "{"
                        + SearchConstants.STAR + " " + SearchConstants.TO + " "
                        + _value.getValue() + "}";
                break;
            case LESSTHAN:
                stringValue= "["
                        + SearchConstants.STAR + " " + SearchConstants.TO + " "
                        + _value.getValue() + "]";
                break;
        }

        return stringValue;
    }

    /**
     * 查询串拼装
     * @param ctx
     * @return
     */
    private String stringlize(Context ctx) throws SearchException {
        String stringKey=this.getKey();
        String stringValue="";
        //多值
        if(this.getValues().size()>1){

            boolean ifAllNot=true;
            for(Value v:this.getValues()){
                if(v.geteCompareStr()!=ECompareStr.NOTEQUAL&&v.geteCompareStr()!=ECompareStr.NOTCONTAIN){
                    ifAllNot=false;
                    break;
                }
            }

            StringBuffer sbAll =new StringBuffer();

            //TODO 多个值都是NOT，NOT好像又需要提前了？？？。如：NOT (id:(* NOT 201972 NOT 201976))。并且需要加括号为子查询
            if(ifAllNot){
                for(Value v:this.getValues()){

                    //TODO NOT 不提前导致无结果：(NOT id:201390)，应该改为NOT (id:201390)
                    //TODO NOT需要特殊处理，放到外部处理
                    stringValue=this.getValue(v);

                    if(StringUtils.isNotBlank(sbAll.toString())){
                        sbAll.append(" "+this.geteOperator().getName()+" ");
                    }
                    sbAll.append(stringValue);
                }
                if(ctx!=null){
                    //字段校验
                    BaseComp.checkSrcField(stringKey, ctx.getBeanFieldName2IndexNameMap());

                    //多语言字段
                    stringKey=BaseComp.getIndexField(stringKey, ctx.getBeanFieldName2IndexNameMap());

                    if((ctx.getLang()!=null)&&BaseComp.isMultiLangField(ctx.getMultiLangMap(),this.getKey())){
                        String segs[]=stringKey.split(SearchConstants.UNDERLINE);
                        stringKey=stringKey.replace(SearchConstants.UNDERLINE+segs[1]+SearchConstants.UNDERLINE,
                                SearchConstants.UNDERLINE+ctx.getLang().getLang()+SearchConstants.UNDERLINE);
                    }
                }

                return EOperator.NOT.getName()+" " +SearchConstants.BRACKETS_LEFT+stringKey+SearchConstants.COLON+SearchConstants.BRACKETS_LEFT+sbAll.toString()+SearchConstants.BRACKETS_RIGHT+SearchConstants.BRACKETS_RIGHT;
            }else{
                for(Value v:this.getValues()){

                    //TODO NOT 不提前导致无结果：(NOT id:201390)，应该改为NOT (id:201390)
                    //TODO NOT需要特殊处理，放到外部处理
                    if(v.geteCompareStr()==ECompareStr.NOTCONTAIN||v.geteCompareStr()==ECompareStr.NOTEQUAL){
                        //stringValue=EOperator.NOT.getName()+" " +SearchConstants.BRACKETS_LEFT+this.getValue(v)+SearchConstants.BRACKETS_RIGHT;
                        stringValue=EOperator.NOT.getName()+" " +this.getValue(v);
                    }else{
                        stringValue=this.getValue(v);
                    }

                    if(StringUtils.isNotBlank(sbAll.toString())){
                        sbAll.append(" "+this.geteOperator().getName()+" ");
                    }
                    sbAll.append(stringValue);
                }
                if(ctx!=null){
                    //字段校验
                    BaseComp.checkSrcField(stringKey, ctx.getBeanFieldName2IndexNameMap());

                    //多语言字段
                    stringKey=BaseComp.getIndexField(stringKey, ctx.getBeanFieldName2IndexNameMap());

                    if((ctx.getLang()!=null)&&BaseComp.isMultiLangField(ctx.getMultiLangMap(),this.getKey())){
                        String segs[]=stringKey.split(SearchConstants.UNDERLINE);
                        stringKey=stringKey.replace(SearchConstants.UNDERLINE+segs[1]+SearchConstants.UNDERLINE,
                                SearchConstants.UNDERLINE+ctx.getLang().getLang()+SearchConstants.UNDERLINE);
                    }
                }

                return stringKey+SearchConstants.COLON+SearchConstants.BRACKETS_LEFT+sbAll.toString()+SearchConstants.BRACKETS_RIGHT;
            }
        }else{//单值

            Value _value=this.getValues().get(0);
            stringValue=this.getValue(_value);

            if(ctx!=null){
                //字段校验
                BaseComp.checkSrcField(stringKey, ctx.getBeanFieldName2IndexNameMap());

                //多语言字段
                stringKey=BaseComp.getIndexField(stringKey, ctx.getBeanFieldName2IndexNameMap());

                if((ctx.getLang()!=null)&&BaseComp.isMultiLangField(ctx.getMultiLangMap(),this.getKey())){
                    String segs[]=stringKey.split(SearchConstants.UNDERLINE);
                    stringKey=stringKey.replace(SearchConstants.UNDERLINE+segs[1]+SearchConstants.UNDERLINE,
                            SearchConstants.UNDERLINE+ctx.getLang().getLang()+SearchConstants.UNDERLINE);
                }
            }

            //NOT升级为子查询，强化层级
            if(_value.geteCompareStr()==ECompareStr.NOTCONTAIN||_value.geteCompareStr()==ECompareStr.NOTEQUAL){
                //return SearchConstants.BRACKETS_LEFT+stringKey+SearchConstants.COLON+stringValue+SearchConstants.BRACKETS_RIGHT;
                //NOT 不提前导致无结果：(NOT id:201390)，应该改为NOT (id:201390)
                return EOperator.NOT.getName()+" " +SearchConstants.BRACKETS_LEFT+stringKey+SearchConstants.COLON+stringValue + SearchConstants.BRACKETS_RIGHT;
            }else{
                return stringKey+SearchConstants.COLON+stringValue;
            }
        }
    }

    // 显示变量字符串表示
    @Override
    public String toString() {
        //toString是Object方法，直接加抛出异常，不好
        try {
            return stringlize(null);
        } catch (SearchException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 文法表示
    @Override
    public String grammar(Context ctx) throws SearchException{
        return stringlize(ctx);
    }

    // 解释变量值
    @Override
    public boolean interpret(Context ctx) throws SearchException {

        //KVPairExpre不支持interpreter
        throw new SearchException("KVPairExpre不支持interpreter");
    }

}
