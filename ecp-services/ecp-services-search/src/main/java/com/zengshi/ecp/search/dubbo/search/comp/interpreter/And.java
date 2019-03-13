package com.zengshi.ecp.search.dubbo.search.comp.interpreter;

import com.zengshi.ecp.search.dubbo.search.SearchException;
import com.zengshi.ecp.search.dubbo.search.util.EOperator;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Created by HDF on 2016/10/8.
 */
public class And extends Expression {
    private Expression left, right;
    //支持多个AND
    private Expression[] more;

    public And(Expression left, Expression right,Expression... more) {
        this.left = left;
        this.right = right;
        this.more=more;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof And) {
            And other=((And) obj);

            if((left==null&&other.left!=null)||(left!=null&&other.left==null)
            ||(right==null&&other.right!=null)||(right!=null&&other.right==null)){
                return false;
            }

            if(left!=null&&other.left!=null){
                boolean flag1=left.equals(other.left);
                if(!flag1){
                    return false;
                }
            }

            if(right!=null&&other.right!=null){
                boolean flag1=right.equals(other.right);
                if(!flag1){
                    return false;
                }
            }

            int length1=(this.more==null?0:this.more.length);
            int length2=(other.more==null?0:other.more.length);
            if(length1!=length2){
                return false;
            }
            if (length1>0){
                for(int i=0;i<length1;i++){
                    if((this.more[i]==null&&other.more[i]!=null)||(this.more[i]!=null&&other.more[i]==null)){
                        return false;
                    }
                    if(this.more[i]!=null&&other.more[i]!=null){
                        if(!this.more[i].equals(other.more[i])){
                            return false;
                        }
                    }
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

    // 解释运算结果值
    @Override
    public boolean interpret(Context ctx) throws SearchException {

        //默认为true。当子表示式对象都为空应该返回true。
        boolean flag=true;

        if(left!=null){
            flag=left.interpret(ctx);
        }

        if(right!=null){
            flag=flag&& right.interpret(ctx);
        }

        if(this.more!=null&&this.more.length>0){
            for(Expression item:more){
                if(item!=null){
                    flag=flag&& item.interpret(ctx);
                }
            }
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
        if(left==null&&right==null&& ArrayUtils.isEmpty(more)){
            return "";
        }

        StringBuffer sb=new StringBuffer();
        String grammar="";
        boolean has=false;

        if(left!=null){
            grammar=left.grammar(ctx);
            if(StringUtils.isNotBlank(grammar)){
                sb.append(grammar);
                has=true;
            }
        }

        if(right!=null){
            grammar=right.grammar(ctx);
            if(StringUtils.isNotBlank(grammar)){
                if(has){
                    sb.append(" "+EOperator.AND.getName()+" ");
                }
                sb.append(grammar);
                has=true;
            }
        }

        if(this.more!=null&&this.more.length>0){
            for(Expression item:more){
                if(item!=null){
                    grammar=item.grammar(ctx);
                    if(StringUtils.isNotBlank(grammar)){
                        if(has){
                            sb.append(" "+ EOperator.AND.getName()+" ");
                        }
                        sb.append(grammar);
                        has=true;
                    }
                }
            }
        }

        grammar=sb.toString();

        if(StringUtils.isNotBlank(grammar)){
            grammar= SearchConstants.BRACKETS_LEFT+grammar+SearchConstants.BRACKETS_RIGHT;
        }

        return  grammar;
    }
}
