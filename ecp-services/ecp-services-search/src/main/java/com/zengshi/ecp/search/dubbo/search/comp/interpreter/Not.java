package com.zengshi.ecp.search.dubbo.search.comp.interpreter;

import com.zengshi.ecp.search.dubbo.search.SearchException;
import com.zengshi.ecp.search.dubbo.search.util.EOperator;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import org.apache.commons.lang.StringUtils;

/**
 * Created by HDF on 2016/10/8.
 */
public class Not extends Expression {
    private Expression exp;

    public Not(Expression exp) {
        this.exp = exp;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Not) {

            Expression _exp=((Not) obj).exp;

            if((exp==null&&_exp!=null)||(exp!=null&&_exp==null)){
                return false;
            }

            if(exp!=null&&_exp!=null){
                return exp.equals(_exp);
            }

            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    // 解释运算结果值
    @Override
    public boolean interpret(Context ctx) throws SearchException{

        //默认为true。当子表示式对象都为空应该返回true。
        boolean flag=true;
        if(exp!=null){
            flag=!exp.interpret(ctx);
        }
        return flag;
    }

    // 显示运算字符串表示
    @Override
    public String toString() {
        try {
            return this.grammar(null);
        } catch (SearchException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 文法表示
    @Override
    public String grammar(Context ctx) throws SearchException{

        //空条件处理
        if(exp==null){
            return "";
        }

        String grammar=exp.grammar(ctx);

        if(StringUtils.isNotBlank(grammar)){
            //NOT 不提前导致无结果：(NOT id:201390)，应该改为NOT (id:201390)
            //return SearchConstants.BRACKETS_LEFT+ EOperator.NOT.getName()+" " + grammar + SearchConstants.BRACKETS_RIGHT;
            return EOperator.NOT.getName()+" " +SearchConstants.BRACKETS_LEFT+grammar + SearchConstants.BRACKETS_RIGHT;
        }

        return "";
    }
}
