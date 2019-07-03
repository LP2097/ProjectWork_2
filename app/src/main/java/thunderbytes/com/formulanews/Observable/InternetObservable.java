package thunderbytes.com.formulanews.Observable;

import java.util.Observable;

public class InternetObservable extends Observable {
    private static InternetObservable instance = new InternetObservable();

    public static InternetObservable getInstance() {
        return instance;
    }


    public void updateValue(String status){
        synchronized(this){
            setChanged();
            notifyObservers(status);
        }
    }
}
