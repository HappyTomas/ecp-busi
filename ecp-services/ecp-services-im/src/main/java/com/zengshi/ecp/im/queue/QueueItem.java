package com.zengshi.ecp.im.queue;

import java.io.Serializable;

/**
 * Created by jiangysh on 2016/9/6.
 */
public class QueueItem implements Serializable,Comparable<QueueItem> {
    private static final long serialVersionUID = 8425902011264698977L;

    private long in;//入队列时间
    private long out;//出队列时间
    private int sessionNum;//会话数
    private String group;//组别
    private boolean working;//是否作业中
    private boolean pause;//是否暂停作业
    private Object target;//目标对象
    private AbstractComparator<QueueItem> comparator;

    public QueueItem(AbstractComparator comparator){
        this.comparator=comparator;
    }

    public long getIn() {
        return in;
    }

    public void setIn(long in) {
        this.in = in;
    }

    public long getOut() {
        return out;
    }

    public void setOut(long out) {
        this.out = out;
    }

    public int getSessionNum() {
        return sessionNum;
    }

    public void setSessionNum(int sessionNum) {
        this.sessionNum = sessionNum;
    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public int compareTo(QueueItem o) {
        if(null==o){
            return 0;
        }

        return this.comparator.comparable(this,o);
    }
}
