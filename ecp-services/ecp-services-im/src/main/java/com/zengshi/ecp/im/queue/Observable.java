package com.zengshi.ecp.im.queue;

/**
 * Created by jiangysh on 2016/9/17.
 */
public interface Observable {

    void addObserver(Observer o);
    void clearChanged();
    int countObservers();
    void deleteObserver(Observer o);
    void deleteObservers();
    boolean hasChanged();
    void notifyObservers();
    void notifyObservers(Object arg);
    void setChanged();
}
