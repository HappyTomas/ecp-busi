package com.zengshi.ecp.im.queue;

/**
 * Created by jiangysh on 2016/9/17.
 */
public interface Observer {

    void update(Observable o, Object arg);
}
