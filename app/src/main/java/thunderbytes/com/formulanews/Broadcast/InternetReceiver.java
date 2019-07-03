package thunderbytes.com.formulanews.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import thunderbytes.com.formulanews.Observable.InternetObservable;
import thunderbytes.com.formulanews.Service.NetworkUtil;

public class InternetReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        String status = NetworkUtil.getConnectivityStatusString(context);

        if(status.isEmpty()) {
            status="Nessuna connessione a internet";
        }

        InternetObservable.getInstance().updateValue(status);
    }
}
