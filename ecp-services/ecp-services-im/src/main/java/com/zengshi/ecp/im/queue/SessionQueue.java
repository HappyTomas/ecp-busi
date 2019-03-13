package com.zengshi.ecp.im.queue;

import java.util.Vector;

/**
 * Created by jiangysh on 2016/9/17.
 */
public class SessionQueue extends ExtendQueue<SessionQueueItem> implements Observable{
    private boolean changed = false;
    private Vector obs;

    @Override
    public void gput(SessionQueueItem item) {
        super.gput(item);
        notifyObservers();
    }

    @Override
    public SessionQueueItem gpoll(String group) {
        return super.gpoll(group);
    }

    public SessionQueue() {
        obs = new Vector();
    }
    @Override
    public synchronized void addObserver(Observer o) {
        if (o == null)
            throw new NullPointerException();
        if (!obs.contains(o)) {
            obs.addElement(o);
        }
    }

    @Override
    public synchronized void clearChanged() {
        changed = false;
    }

    @Override
    public synchronized int countObservers() {
        return obs.size();
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        obs.removeElement(o);
    }

    @Override
    public synchronized void deleteObservers() {
        obs.removeAllElements();
    }

    @Override
    public synchronized boolean hasChanged() {
        return changed;
    }

    @Override
    public synchronized void notifyObservers() {
        notifyObservers(null);
    }

    @Override
    public synchronized void notifyObservers(Object arg) {
        Object[] arrLocal;

        synchronized (this) {
            if (!changed)
                return;
            arrLocal = obs.toArray();
            clearChanged();
        }

        for (int i = arrLocal.length-1; i>=0; i--)
            ((Observer)arrLocal[i]).update(this, arg);
    }

    @Override
    public synchronized void setChanged() {
        changed = true;
    }
}
