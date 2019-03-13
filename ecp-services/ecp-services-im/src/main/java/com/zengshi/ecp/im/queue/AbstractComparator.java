package com.zengshi.ecp.im.queue;

/**队列元素比较器
 * Created by jiangysh on 2016/9/9.
 */
public abstract class AbstractComparator<T extends QueueItem> {

    public abstract int comparable(T pre,T next);
}
