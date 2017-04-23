/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Subs.SubscriberInterface;
import java.io.Serializable;

/**
 *
 * @author TiagoMartins
 */
public class Subscritores implements Serializable {

    private int ids;
    private SubscriberInterface subscribers;

    public Subscritores() {

        ids = -1;
        subscribers = null;

    }

    public Subscritores(int ids, SubscriberInterface subscribers) {
        this.ids = ids;
        this.subscribers = subscribers;
    }

    public int getIds() {
        return ids;
    }

    public SubscriberInterface getSubscribers() {
        return subscribers;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public void setSubscribers(SubscriberInterface subscribers) {
        this.subscribers = subscribers;
    }

    @Override
    public String toString() {
        return "Subscritores{" + "ID'S=" + ids + ", subscribers=" + subscribers + '}';
    }

}
