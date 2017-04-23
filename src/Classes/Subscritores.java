
package Classes;

import Subs.SubscriberInterface;
import java.io.Serializable;

// @authors: Tiago Jesus – a30961, João Saraiva, – a33345 Fábio Mota – a34693 UBI 2016/2017-SD

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
